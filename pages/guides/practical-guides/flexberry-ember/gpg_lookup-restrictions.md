---
title: Наложение ограничений на лукапы
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, лукап, lookup, предикат, predicate, lookup events, groupedit events
toc: true
permalink: ru/gpg_lookup-restrictions.html
lang: ru
---

Наложение ограничений на лукапы - важная задача. Она появляется тогда, когда нужно отфильтровать список записей, из которого выбирается значение лукапа. Так, например, положим, что нельзя совмещать должности менеджера и кладовщика. Иными словами, тот, кто принят на должность кладовщиком, не могут быть выбраны как менеджер заказа, и наоборот.

## Наложение ограничений на простой лукап

Реализуем первое ограничение: исключим тех, кто работает кладовщиком, из списка работников, которых можно выбрать в качестве менеджера заказа. Для этого откроем **контроллер формы редактирования заказа**:

*`app → controllers → i-i-s-shop-order-e.js`*

{% highlight javascript%}
{% raw %}
export default EditFormController.extend({
  parentRoute: 'i-i-s-shop-order-l',

  init() {
    this._super(...arguments);
  }

...

});
{% endraw %}
{% endhighlight %}

Для того, чтобы наложить ограничение на лукап, нужно создать соответствующий **[предикат](https://flexberry.github.io/ru/efd_query-language.html#%D0%BF%D1%80%D0%B5%D0%B4%D0%B8%D0%BA%D0%B0%D1%82%D1%8B)**. Необходимый нам предикат является простым (нужно проверить значение конкретного свойства - должности). **Простые, ни от чего не зависящие** предикаты можно создать в хуке init (вызывается автоматически при создании контроллера) через метод **set**:

{% highlight javascript%}
{% raw %}
export default EditFormController.extend({
  parentRoute: 'i-i-s-shop-order-l',

  init() {
    this._super(...arguments);

    // Ограничение на лукап менеджера
     this.set('managerLimitPredicate', new SimplePredicate('position', 'eq', 'Manager'));
  }

...

});
{% endraw %}
{% endhighlight %}

где managerLimitPredicate - имя нового свойства контроллера,  
position - проверяемое свойство модели,  
eq - оператор равенства,  
Manager - необходимое значение свойства модели.

Остается только передать созданное ограничение (фильтр) в виде предиката для лукапа. Для этого нужно **добавить атрибут** в соответствующий template:

*`app → templates → i-i-s-shop-order-e.js`*

{% highlight handlebars%}
{% raw %}
<div class="field">
    <label>{{t "forms.i-i-s-shop-order-e.manager-caption"}}</label>
    {{flexberry-lookup
      choose="showLookupDialog"
      remove="removeLookupValue"
      value=model.manager
      displayAttributeName="nameWInitials"
      autocomplete=true
      relationName="manager"
      projection="EmployeeL"
      title=(t "forms.i-i-s-shop-order-e.manager-caption")
      readonly=readonly
      lookupLimitPredicate=managerLimitPredicate
      componentName="managerLookup"
    }}
    {{flexberry-validationmessage error=(v-get validationObject "manager" "messages")}}
  </div>
{% endraw %}
{% endhighlight %}

**Проверим**, работает ли предикат:

![Ограничение на поле менеджер заказа по должности работника](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-4.png)

Все верно, в списке оказались лишь те сотрудники, у которых указана должность "Менеджер".

---

**_Самостоятельно_** создайте предикат для лукапа:

**Склад**
> Кладовщик - только сотрудники с должностью "кладовщик".

---

## Наложение ограничений на лукапы в groupedit

Более сложной задачей является наложение предиката на определенные поля в компоненте для работы с детейлами (groupedit). Рассмотрим данную задачу на примере ограничения выбора товаров в заказе: если товар уже один раз выбран в рамках заказа, то больше его в списке для выбора товаров выводить не будем.

Для того, чтобы ограничить лукап в groupedit, необходимо указать соответствующий предикат в функции **getCellComponent** в контроллере:

*`app → controllers → i-i-s-shop-order-e.js`*

![Автогенерированный компонент управления лукапом в детейле](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-5.png)

Для настройки компонентов, в частности лукапов, которые будут отображаться в ячейках groupedit, в контроллере соответствующей формы редактирования генерируется требуемый код в методе getCellComponent. В данном случае мы можем видеть установку перечня свойств, аналогичных свойствам обычного лукапа: сюда мы и добавим наш предикат.

Дополнительная сложность заключается в том, что **предикат** должен быть **динамическим**, т.е. меняться при определенных обстоятельствах. В нашем случае таким обстоятельством является взаимодействие с самим лукапом "Товар" в строке заказа: при выборе очередного товара, этот товар должен учитываться в предикате и больше не выводиться.

Рассмотрим события, при которых должен изменяться предикат:
1. открытие формы (если есть уже в списке детейлов какие-то товары, то предикат должен сразу ограничить их список для новых строк);
2. добавление новой строки;
3. удаление строки;
4. очистка лукапа.

Каждое из этих событий должно триггерить изменение предиката. Реализуем механизм для пересчета предиката и воспользуемся им последовательно в каждом из этих событий.<br><br>

### Механизм формирования предиката

Для того, чтобы применить **актуальный предикат** к лукапу Товара в определенный момент времени, необходимо выполнить **три шага**:

1. получить список значений определенного свойства модели Товар, из перечня товаров, которые уже используются;
2. вычислить предикат на основе полученного списка значений;
3. передать предикат в лукап.

**Второй шаг** (вычисление предиката) является потенциально **универсальным**: он может применяться не только для текущего лукапа, но и для других, в том числе на других формах. Чтобы не копировать каждый раз один и тот же код, создадим **[утилиту](https://guides.emberjs.com/v3.1.0/tutorial/service/)**, вычисляющую предикат по списку значений и имени свойства:

`ember generate util generate-predicate-by-list`

![Новая утилита](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-6.png)

Утилиты используются в тех случаях, когда необходимо простые функции использовать во многих местах кода. Утилиты доступны в любом месте ember-приложения и подключаются с использованием ключевого слова import.

**Заменим** содержимое утилиты следующим кодом:

{% highlight javascript%}
{% raw %}
import Condition from 'ember-flexberry-data/query/condition';
import {
  SimplePredicate,
  ComplexPredicate
} from 'ember-flexberry-data/query/predicate';
import { isNone } from '@ember/utils';

/**
 * Генерировать комплексный предикат по списку значений.
 * @param {*} condition Логический оператор для комплексного предиката.
 * @param {*} propertyName Имя свойства.
 * @param {*} filterOperator Оператор сравнения.
 * @param {*} listOfExpectedValues Список значений.
 * @returns Предикат.
 */
function generateComplexPredicateByList(condition, propertyName, filterOperator, listOfExpectedValues) {
  let resultPredicate;
  listOfExpectedValues.forEach(expectedValue => {
    const predicate = new SimplePredicate(propertyName, filterOperator, expectedValue);
    if (isNone(resultPredicate)) {
      resultPredicate = predicate;
    }  else {
      resultPredicate = new ComplexPredicate(condition, resultPredicate, predicate);
    }
  });
  return resultPredicate;
}

export {
  generateComplexPredicateByList
};
{% endraw %}
{% endhighlight %}


{% include important.html content="Для того, чтобы функции внутри утилиты были доступны извне, их надо объявить в конце внутри export. Все не объявленные внутри export функции будут локальными." %}

В рассмотренной функции, которую будем называть основной, формируется предикат по принципу "цепочки", звенья которой добавляются последовательно, сцепляемые определенным логическим оператором (и / или):

![Логика построения комплексного предиката](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-8.png)

{% include warning.html content="У описанного метода есть ограничение при большом списке выбранных товаров: если предикат слишком &#34;длинный&#34;, то соответствующий запрос на получение данных для лукапа может превысить максимально допустимую длину URL (такая проблема характерна для более старых версий браузеров, например, ограничение в <a href='https://support.microsoft.com/ru-ru/help/208427/maximum-url-length-is-2-083-characters-in-internet-explorer'>2083 символа для браузера Internet Explorer</a>), вследствие чего может возникнуть ошибка или требуемая логика будет работать не до конца корректно.<br><br>
На данный момент логика работы лукапов имеет данное ограничение в связи с особенностями использования протокола OData, и преодолеть его без изменения логики работы самого компонента не представляется возможным. В подобных случаях, которые на практике могут возникнуть не так часто, можно попробовать, как один из вариантов, наложить соответствующее ограничение на требуемый список записей на уровне сервера.<br><br>
В связи с наличием данного ограничения условимся, что список товаров в нашем приложении не является очень большим." %}

На базе основной функции построим еще две последовательные: **функцию ИЛИ** (с конкретным логическим оператором, в отличие от базовой) и **отрицающую логическую ИЛИ**. Вторая функция нам нужна для того, чтобы воплотить конструкцию типа 

"не( ЗНАЧЕНИЕ 1 ) и не( ЗНАЧЕНИЕ 2 ) и …. и не( ЗНАЧЕНИЕ N )"

Если её реализовывать "в лоб", то получится довольно сложная конструкция. Из логики известно следующее утверждение:

не( А или Б ) = не( А ) и не( Б )

Им и воспользуемся при построении **необходимого предиката**:

{% highlight javascript%}
{% raw %}
import Condition from 'ember-flexberry-data/query/condition';
import {
  SimplePredicate,
  ComplexPredicate,
  NotPredicate
} from 'ember-flexberry-data/query/predicate';
import { isNone } from '@ember/utils';

...

/**
 * Генерировать предикат логического или по списку значений.
 * @param {*} propertyName Имя свойства.
 * @param {*} filterOperator Оператор сравнения.
 * @param {*} listOfExpectedValues Список значений.
 * @returns Предикат.
 */
function generateOrPredicateByList(propertyName, filterOperator, listOfExpectedValues) {
  return generateComplexPredicateByList(Condition.Or, propertyName, filterOperator, listOfExpectedValues);
}

/**
 * Генерировать отрицание предиката логического или по списку значений.
 * @param {*} propertyName Имя свойства.
 * @param {*} filterOperator Оператор сравнения.
 * @param {*} listOfExpectedValues Список значений.
 * @returns Предикат.
 */
function generateNotOrPredicateByList(propertyName, filterOperator, listOfExpectedValues) {
  if (listOfExpectedValues.length > 0) {
    return new NotPredicate(generateOrPredicateByList(propertyName, filterOperator, listOfExpectedValues));
  }
  return undefined;
}

export {
  generateComplexPredicateByList,
  generateOrPredicateByList,
  generateNotOrPredicateByList
};
{% endraw %}
{% endhighlight %}

{% include important.html content="В функции generateNotOrPredicateByList() обязательно нужно проверять длину поступающего на вход массива значений. Это связано с тем, что две предыдущие функции валидно отрабатывали при нулевых массивах, а в текущей внутрь NotPredicate() должен поступать валидный предикат. Со значением undefined, которое возвращается из generateOrPredicateByList() в случае передачи нулевого массива, он работать не сможет." %}

Теперь вызовем функцию **generateNotOrPredicateByList** в **контроллере**:

{% highlight javascript%}
{% raw %}
import { generateNotOrPredicateByList } from '../utils/generate-predicate-by-list';
{% endraw %}
{% endhighlight %}

{% include note.html content="Путь, указываемый до утилиты, является относительным и начинается с папки, в которой находится контроллер." %}

Утилита, возвращающая предикат нужного нам вида, написана и доступна теперь из контроллера. Вынесем установку свойств лукапа в хук **init** в переменную **productProperties** и получим её значение в методе **getCellComponent**: 

{% highlight javascript%}
{% raw %}
 init() {
    this._super(...arguments);

    // Ограничение на лукап менеджера
    this.set('managerLimitPredicate', new SimplePredicate('position', 'eq', 'Manager'));

    // Настройки лукапа товара
    this.set('productProperties', {
      choose: 'showLookupDialog',
      remove: 'removeLookupValue',
      displayAttributeName: 'name',
      required: false,
      relationName: 'product',
      projection: 'ProductL',
      autocomplete: true,
      lookupLimitPredicate: undefined
    });
},

getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);

    if (attr.kind === 'belongsTo') {
    switch (`${model.modelName}+${bindingPath}`) {
        case 'i-i-s-shop-order-item+product':
        cellComponent.componentProperties = this.get('productProperties');
        break;
    }
    }
    return cellComponent;
},
{% endraw %}
{% endhighlight %}

Теперь при изменении свойства контроллера productProperties эти изменения будут динамически применяться к лукапу Товара. По умолчанию значением свойства lookupLimitPredicate является undefined: такое значение этого свойства подразумевает отсутствие ограничений на выборку.

Добавим **перед** методом getCellComponent функцию **setProductLookupPredicate()** для переопределения предиката:

{% highlight javascript%}
{% raw %}
/**
  * Обновление предиката для лукапа товара
  */
setProductLookupPredicate() {
    let productIds = [];
    let orderItems = this.get('model.orderItem');
    if (orderItems) {
    orderItems.forEach(item => {
        let product = item.get('product');
        if (product && product.get('id') !== recordId) {
        productIds.push(product.get('id'));
        }
    });
    }

    let predicate = generateNotOrPredicateByList('id', 'eq', productIds);
    this.set('productProperties.lookupLimitPredicate', predicate);
},

getCellComponent(attr, bindingPath, model) {
    ...
},
{% endraw %}
{% endhighlight %}

{% include note.html content="Предикат вычисляется при помощи созданной ранее нами утилиты (функция generateNotOrPredicateByList)." %}

Функция setProductLookupPredicate() по сути представляет собой сеттер, который меняет значение конкретного атрибута свойства productProperties. Таким образом, при вызове данной функции предикат будет пересчитан на основании текущего списка выбранных товаров и применен к лукапу как динамическое свойство.<br><br>

### Открытие формы

Для того, чтобы процесс формирования предиката запускался вместе с загрузкой формы, нужно его вызвать в момент, когда модель уже загружена. Для этого перейдем в соответствующий роут формы редактирования Заказа и кастомизируем хук setupController():

*`app → routes → i-i-s-shop-order-e.js`*

{% highlight javascript%}
{% raw %}
export default EditFormRoute.extend({
  modelProjection: 'OrderE',
  modelName: 'i-i-s-shop-order',

  setupController(controller) {
    this._super(...arguments);
    controller.setProductLookupPredicate();
  }
});
{% endraw %}
{% endhighlight %}

{% include important.html content="Не забудьте вначале вызвать 
this._super(...arguments);<br>
Так как это стандартный хук Ember.js, то при кастомизации нужно не забыть вызвать выполнение базовой логики." %}

**Проверим**, отрабатывает ли предикат при загрузке страницы. Для этого перейдем в один из ранее созданных заказов (например, **заказ 2**):

![Ограничение на выбор товара при загрузке страницы](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-15.png)

Предикат отработал корректно: среди вариантов товара новой строки товар с кодом 2 (выбранный ранее) отсутствует.<br><br>

### Добавление новой строки

Наиболее целесообразно будет привязать данное событие к **событию закрытия окна лукапа**: это событие легко отслеживается из **контроллера**. Для того, чтобы обработать это событие, добавим следующий код:

{% highlight javascript%}
{% raw %}
import { inject } from '@ember/service';

...

export default EditFormController.extend({
  parentRoute: 'i-i-s-shop-order-l',

  /**
    Сервис для событий лукапа
      @property lookupEventsService
      @type Service
    */
  lookupEventsService: inject('lookup-events'),

  init() {
     this._super(...arguments);

     ...

     // Событие закрытия окна лукапа
     this.get('lookupEventsService').on('lookupDialogOnHidden', this, this._setLookupPredicate);
   },

   willDestroy() {
     this._super(...arguments);
     this.get('lookupEventsService').off('lookupDialogOnHidden', this, this._setLookupPredicate);
   },
}):
{% endraw %}
{% endhighlight %}

{% include important.html content="Мы не только подключаем к событию закрытия лукапа кастомную логику в хуке init(), но также отключаем её в хуке willDestroy(). Это нужно для того, чтобы исключить повторную регистрацию обработчика соответствующего события на форме редактирования Заказа при повторных открытиях данной формы." %}

Теперь в методе **_setLookupPredicate** необходимо **выбрать** лукап, на который мы будем обращать внимание при закрытии модального окна. Дело в том, что в данный момент эта функция будет вызываться при закрытии **любого лукапа**, а нас интересует вполне конкретный. 

**На вход** данному методу (в параметр **componentName**) будет приходить **имя лукапа**: проверим это имя. Для этого выведем в консоль параметр componentName:

{% highlight javascript%}
{% raw %}
/**
  * Обновление лукапов
  */
  _setLookupPredicate(componentName) {
    console.log(componentName);
  },
{% endraw %}
{% endhighlight %}

Далее инициируем событие закрытия лукапа для выбора товара, расположенного в ячейке groupedit, закрыв соответствующее модальное окно:

![Окно лукапа товара](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-18.png)

![ComponentName в логах](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-19.png)

Копируем имя компонента целиком и вставим его в условие для проверки имени компонента в обработчике закрытия модального окна лукапа: 

{% highlight javascript%}
{% raw %}
/**
  * Обновление лукапов
  */
  _setLookupPredicate(componentName) {
    switch (componentName) {
      case '(orderItemGroupEdit_flexberry-lookup_product)':
        this.setProductLookupPredicate(record);
        break;
    }
  },
{% endraw %}
{% endhighlight %}

{% include note.html content="Имя метода начинается с символа \"_\". Это означает, что данный метод не предполагается вызывать извне, он является приватным внутри текущего контроллера. При написании приложений на JavaScript это является общепринятым соглашением: его соблюдать рекомендуется, но никаких функциональных ограничений на соответствующие свойства или методы оно не накладывает." %}

**Проверим**, работает ли изменение предиката на добавление строки groupedit (на примере **нового заказа**):

![Работа ограничения при закрытии окна лукапа](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-21.png)

Все работает корректно.<br><br>

### Удаление строки заказа

При удалении строки в groupedit так же, как и при закрытии окна лукапа, возникает особое событие в соответствующем сервисе - **olvRowDeleted**. Его также можно подключить при помощи метода inject и использовать для привязки кастомной логики:

{% highlight javascript%}
{% raw %}
import { inject } from '@ember/service';

export default EditFormController.extend({

    ...

  /**
  Сервис для событий groupedit
    @property lookupEventsService
    @type Service
  */
  groupEditEventsService: inject('objectlistview-events'),

  init() {
    
    ...

    // Событие удаления строки детейла
    this.get('groupEditEventsService').on('olvRowDeleted', this, this._setLookupPredicate);
  },

  willDestroy() {

    ...

    this.get('groupEditEventsService').off('olvRowDeleted', this, this._setLookupPredicate);
  },
}):
{% endraw %}
{% endhighlight %}

Уточним через консоль имя **componentName**, которое приходит в функцию **_setLookupPredicate()** и добавим его в конструкцию switch с тем же телом, что и в предыдущем случае: 


{% highlight javascript%}
{% raw %}
/**
  * Обновление лукапов
  */
  _setLookupPredicate(componentName, record) {
    switch (componentName) {
      case '(orderItemGroupEdit_flexberry-lookup_product)':
      case 'orderItemGroupEdit':
        this.setProductLookupPredicate(record);
        break;
    }
  },
{% endraw %}
{% endhighlight %}

На первый взгляд, этого должно быть достаточно. Однако, если мы проверим сейчас работоспособность кода, то лукап не будет учитывать удаленную запись. Почему так получается? Дело в том, что указанное нами событие и событие удаления записи происходят синхронно, а значит, на момент отработки нашей кастомной логики запись еще существует.

Для того, чтобы исправить этот момент, **добавим** в логику **следующий код**:

{% highlight javascript%}
{% raw %}
/**
  * Обновление предиката для лукапа товара
  */
setProductLookupPredicate(record) {
    let recordId;
    if (record) {
    recordId = record.get('product.id');
    }

    let productIds = [];
    let orderItems = this.get('model.orderItem');
    if (orderItems) {
    orderItems.forEach(item => {
        let product = item.get('product');
        if (product && product.get('id') !== recordId) {
        productIds.push(product.get('id'));
        }
    });
    }

    let predicate = generateNotOrPredicateByList('id', 'eq', productIds);
    this.set('productProperties.lookupLimitPredicate', predicate);
},

getCellComponent(attr, bindingPath, model) {
    ...
},
{% endraw %}
{% endhighlight %}

{% include note.html content="В случае с лукапом параметр <b>record</b> в обработчике события будет отсутствовать, и componentName будет единственным параметром, приходящим в _setLookupPredicate(). В случае же с событием olvRowDeleted вместе с именем компонента в обработчик события передаются еще и данные об удаляемом экземпляре строки заказа." %}

**Проверим** работу кода на примере **заказа 1**:

![Работа ограничения при удалении строки](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-25.png)

Все работает корректно.<br><br>

### Очистка лукапа

Для отслеживания очистки лукапа нет особого события среди событий в сервисе лукапа, чтобы можно было применить решение аналогичное предыдущим случаям. Однако, среди свойств лукапа, установку которых мы вынесли в **хук init()**, можно найти имя соответствующего **экшна**:

![Acton на очистку лукапа](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-26.png)

Дополним его кастомной логикой: 

{% highlight javascript%}
{% raw %}
actions: {
    configurateOrderItemRow(rowConfig) {
        let readonlyColumns = ['priceWTaxes', 'totalSum'];
        set(rowConfig, 'readonlyColumns', readonlyColumns);
    },

    /**
      * Очистка лукапа
      */
    removeLookupValue(lookupProperties) {
        this._super(...arguments);
        if (lookupProperties.relationName === 'product') {
          this.setProductLookupPredicate();
        }
    }
}
{% endraw %}
{% endhighlight %}

{% include important.html content="Так как лукап на странице может быть не один, в этом экшене также производится проверка входных параметров (в данном случае, свойств лукапа): только для лукапа, связанного с моделью product, будет отрабатываться пересчет предиката." %}

**Проверим** работу кода на примере **заказа 1**:

![Переопределение метода очистки лукапа](/images/pages/guides/flexberry-ember/6-2-lookup-restrictions/6-2-28.png)

Все работает корректно.

---

**_Самостоятельно_** настройте лукап для поля "Заказ" Накладной, чтобы нельзя было создать несколько накладных на один заказ.

*<u>Подсказка</u>*: воспользуйтесь вычиткой данных через builder, чтобы получить сведения о всех существующих накладных и их заказах.

---

## Итог

Установление предикатов на лукапы - часто совершаемая операция. Она необходима для более точной передачи логики выбора одного из нескольких значений, а также для исключения человеческих ошибок при работе с приложением.

При создании предикатов для лукапа всегда продумывайте варианты событий, при которых данный предикат должен пересчитываться, а также то, статическим или динамическим должен быть предикат.

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Автозаполнение полей формы](gpg_autofill-form-elements.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Бизнес-серверы и режим отладки](gpg_business-servers-and-debug-mode.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>