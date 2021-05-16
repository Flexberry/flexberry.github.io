---
title: Автозаполнение полей формы
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, автозаполнение, детейл, observer
toc: true
permalink: ru/gpg_autofill-form-elements.html
lang: ru
---

Ранее в разделе "[Добавление тестовых данных](https://flexberry.github.io/ru/gpg_filling-application-primary-data.html)" мы говорили о том, что для некоторых полей на формах редактирования Заказов и Накладных требуется реализовать логику автоматического вычисления значений.

В данном разделе мы рассмотрим реализацию требуемой логики в соответствующих моделях:

**Заказ**

> Цена с налогом = Товар.Цена * Налог (константа, фиксированный)  
> Сумма по позиции = Количество * Цена с налогом  
> Стоимость заказа = СУММА(Содержимое заказа.Сумма по позиции)

![Связи между полями заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-1.png)

**Накладная**

> Сумма заказа = Заказ.Стоимость заказа  
> Список товаров к выдаче = Заказ.Содержимое заказа  
> Вес (кг) = Товар.Вес (кг) * Количество  
> Вес заказа (кг) = СУММА(Список товаров к выдаче.Вес (кг))  

![Связи между полями накладной](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-2.png)

## Автоматическое вычисление значений атрибутов агрегатора

Ранее, [мы реализовали вычислимый атрибут `TotalSum`](gpg_autocomplete-and-data-types.html), но так как его вычисление происходит на сервере, в момент загрузки данных, при редактировании данных на форме, его значение обновляется только после сохранения изменений.

Чтобы пользователь мог видеть актуальные данные до сохранения, мы можем реализовать аналогичные вычисления на стороне клиента.
Для этого, реализуем в модели `Order`, вычислимое свойсвто `actualTotalSum`:

```js
// app\models\i-i-s-shop-order.js

// ...

import { computed } from '@ember/object';

let Model = DocumentModel.extend(OrderMixin, Validations, {
  // ...

  actualTotalSum: computed('orderItem.@each.{amount,priceWTaxes}', function() {
    return this.get('orderItem').reduce((sum, item) => {
      const priceWTaxes = Number(item.get('priceWTaxes') || 0);
      const amount = Number(item.get('amount') || 0);
      if (Number.isNaN(priceWTaxes) || Number.isNaN(amount)) {
        throw new Error(`Invalid 'priceWTaxes' or 'amount' for order item: '${item}'.`);
      }

      return sum + priceWTaxes * amount;
    }, 0);
  }),
});

// ...

export default Model;
```

В шаблоне формы редактирования заказа, вместо свойства `totalSum`, значение которого вычисляется на сервере, будем использовать вычислимое свойство `actualTotalSum`:

```hbs
{% raw %}{{!-- app\templates\i-i-s-shop-order-e.hbs --}}

{{!-- ... --}}

<div class="field">
  {{flexberry-field
    readonly=true
    value=model.actualTotalSum
    label=(t "forms.i-i-s-shop-order-e.totalSum-caption")
  }}
</div>

{{!-- ... --}}{% endraw %}
```

---

**_Самостоятельно_** реализуйте аналогичным способом вычисления для следующих полей:

**Накладная**
> "Сумма заказа"  
> "Вес заказа"

---

## Автоматическое вычисление значений атрибутов внутри одной модели

В [предыдущем параграфе](#автоматическое-вычисление-значений-атрибутов-агрегатора), мы реализовали автоматическое вычисление свойства `TotalSum` модели `Order`.
Модель `OrderItem` также имеет свойство `TotalSum`, которое по своей логике схоже со свойством `TotalSum` модели `Order`, поэтому, его стоит реализовать схожим образом.

Отметьте свойство `TotalSum` модели `OrderItem` как не хранимое, и реализуйте его, в объектах сервера, так же как [мы сделали это для свойства `TotalSum` модели `Order`](gpg_autocomplete-and-data-types.html#настройка-вычислимых-атрибутов).

Чтобы реализовать отображение актуальной суммы до сохранения изменений, так же реализуем вычислимое свойство `actualTotalSum` в модели `OrderItem` клиентского приложения.

```js
// app\models\i-i-s-shop-order-item.js

// ...

import { computed } from '@ember/object';

let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {
  // ...

  actualTotalSum: computed('priceWTaxes', 'amount', function() {
    const price = Number(this.get('priceWTaxes') || 0);
    const amount = Number(this.get('amount') || 0);

    return (price * amount).toFixed(2);
  }),
});

// ...

export default Model;
```

Разница в этом случае заключается в том что, нам необходимо вывести своство `actualTotalSum` не в форме непосредственно, а в компоненте `{% raw %}{{flexberry-groupedit}}{% endraw %}`.
Этот компонент не позволяет выводить в столбцах вычислимые свойства, описанные в модели клиентского приложения, однако он позволяет использовать любые компоненты, для отображения свойств модели в ячейках таблицы, этой возможностью мы и воспользуемся.

Сгенерируйте компонент, выполнив следующую команду в консоли:

```cmd
ember generate component order-item/total-sum
```

Мы использовали имя модели в названии компонента, чтобы упорядочить эти компоненты, при увеличении их количества, сам компонент не имеет дополнительной логики, поэтому у компонента можно оставить только шаблон.

Компонент, отображаемый в ячейках таблицы компонента `{% raw %}{{flexberry-groupedit}}{% endraw %}`, получает экземпляр модели в качестве параметра `relatedModel`, с его помощью выведем, описанное нами ранее, вычислимое свойство `actualTotalSum`, в шаблоне.

```hbs
{% raw %}{{!-- app\templates\components\order-item\total-sum.hbs --}}

{{relatedModel.actualTotalSum}}{% endraw %}
```

Для того чтобы компонент `{% raw %}{{flexberry-groupedit}}{% endraw %}` использовал компонент `{% raw %}{{order-item/total-sum}}{% endraw %}`, для отображения нужного нам свойства в ячейках таблицы, будем использовать метод `getCellComponent` соответствующего контроллера.
Метод `getCellComponent` вызывается для каждого свойства, отображаемого в таблице, компонентом `{% raw %}{{flexberry-groupedit}}{% endraw %}`, и должен вернуть объект с описанием настроек компонента, который будет использован для отображения соответствующего свойства в каждой строке таблицы.

```js
// app\controllers\i-i-s-shop-order-e.js

// ...

export default EditFormController.extend({
  // ...

  getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);

    // ...

    if (bindingPath === 'totalSum') {
      cellComponent.componentName = 'order-item/total-sum';
    }

    return cellComponent;
  },

  // ...
});
```

После этого, компонент `{% raw %}{{flexberry-groupedit}}{% endraw %}` для отображения свойства `totalSum` в ячейке таблицы, будет использовать компонент `{% raw %}{{order-item/total-sum}}{% endraw %}`.

В модели `OrderItem` есть ещё одно свойство, схожее со свойством `TotalSum`, свойство `PriceWTaxes` - это цена продукта умноженная на ставку налога.
Однако, в данном случае, использование вычислимого свойства, не совсем то что нам нужно, по скольку цена должна быть зафиксирована на момент оформления заказа, и не должна изменяться при изменении ставки налога.
Также, в данном случае, вычисления желательно выполнть на сервере, или как минимум валидировать их при сохранении, для этого создадим бизнес-сервер для модели `OrderItem`.

Чтобы создать бизнес-сервер, добавьте новый класс на диаграмме классов, и установите ему стереотип `«businessserver»`. Классы бизнес-серверов принято именовать добавляя окончание `BS` к имени класса модели, которую он будет обрабатывать, поэтому, дадим нашему классу имя `OrderItemBS`.

![Добавление класса бизнесс-сервера](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-3.png)

После добавления класса бизнес-сервера, его необходимо связать с моделью, которую он будет обрабатывать, для этого откройте окно редактирования свойств класса модели `OrderItem`, и выберете нужный класс в поле `BSClass`. После выбора класса бизнес-сервера, под полем `BSClass` появиться ещё один список элементов, в нём оставьте значение `OnAllEvents`.

![Связь модели и бизнесс-сервера](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-4.png)

Теперь нам нужно сгенерировать добавленный класс и изменения в классе модели, для этого нажмите правой кнопкой мыши на стадии `Стадия (ember)`, в меню выберите `ORM`, затем `C#`, и нажмите `Генерировать`. В диалоговом окне со списком проектов для генерации выберете `Все` и нажмите `ОК`.

![Генерация измменений](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-5.png)

Более подробно с бизнес-серверами можно познакомиться в статье [Бизнес-серверы и режим отладки](gpg_business-servers-and-debug-mode.html).

После генерации в проекте был добавлен класс `OrderItemBS` с методом `OnUpdateOrderItem`, в котором нам необходимо описать логику работы бизнес-сервера.
Напомню, что бизнес-сервер должен вычислить цену продукта с налогом, и записать её в свойство модели `PriceWTaxes`.

Для получения ставки налога, скорее всего, необходимо реализовать отдельный класс, предоставляющий эту функцию, но в данной статье мы не будем рассамтривать этот пример.
Вместо этого, сохраним ставку налога в свойстве `TAXES` класса `OrderItemBS`.
Метод `OnUpdateOrderItem` получает в качестве первого и единственого параметра, экземпляр класса `OrderItem` над которым выполняется операция.
Для создаваемых или изменяемых объектов, будем загружать продукт, на который ссылается этот объект и вычислять для него цену с налогом.

```csharp
// Shop\BusinessServers\OrderItemBS.cs

// ...

public class OrderItemBS : ICSSoft.STORMNET.Business.BusinessServer
{

    // *** Start programmer edit section *** (OrderItemBS CustomMembers)
    public const int TAXES = 10;
    // *** End programmer edit section *** (OrderItemBS CustomMembers)


    // *** Start programmer edit section *** (OnUpdateOrderItem CustomAttributes)

    // *** End programmer edit section *** (OnUpdateOrderItem CustomAttributes)
    public virtual ICSSoft.STORMNET.DataObject[] OnUpdateOrderItem(IIS.Shop.OrderItem UpdatedObject)
    {
        // *** Start programmer edit section *** (OnUpdateOrderItem)
        ICSSoft.STORMNET.ObjectStatus objectStatus = UpdatedObject.GetStatus();
        if (objectStatus == ICSSoft.STORMNET.ObjectStatus.Created || objectStatus == ICSSoft.STORMNET.ObjectStatus.Altered)
        {
            var product = new IIS.Shop.Product();

            product.SetExistObjectPrimaryKey(UpdatedObject.Product.__PrimaryKey);
            DataService.LoadObject(IIS.Shop.Product.Views.ProductL, product);

            double coefficient = TAXES / 100d + 1d;

            UpdatedObject.PriceWTaxes = product.Price * coefficient;
        }

        return new ICSSoft.STORMNET.DataObject[0];
        // *** End programmer edit section *** (OnUpdateOrderItem)
    }
}
```

После реализации биснес-сервера, цена с налогом будет вычисляться и сохраняться при добавлении или изменении содержимого заказа.

---

**_Самостоятельно_** реализуйте отображение актуальной цены с налогом при редактировании содержимого заказа, так же как мы сделали это для свойства `TotalSum`.

## Автозаполнение списка детейлов

В качестве примера рассмотрим логику автозаполнения детейла **Список товаров к выдаче** в **Накладной** на основе выбранного **Заказа**.
Для этого воспользуемся возможностью переопределения обработчика события изменения значения в компоненте `{% raw %}{{flexberry-lookup}}{% endraw %}`.

Опишем в контроллере формы редактирования накладной соответствующий обработчик:

```js
// app\controllers\i-i-s-shop-invoice-e.js

// ...

import Builder from 'ember-flexberry-data/query/builder';
import generateUniqueId from 'ember-flexberry-data/utils/generate-unique-id';

export default EditFormController.extend({
  // ...

  orderItemsLoading: false,

  actions: {
    orderChanged({ modelToLookup: invoice, newRelationValue: order }) {
      invoice.set('order', order);
      invoice.get('invoiceItem').toArray().forEach((item) => {
        item.deleteRecord();
      });

      if (order) {
        this.set('orderItemsLoading', true);

        const store = this.get('store');
        const modelName = 'i-i-s-shop-order-item';

        const query = new Builder(store, modelName)
          .selectByProjection('OrderItemE')
          .where('order', 'eq', order.get('id'))
          .build();

        store.query(modelName, query).then((orderItems) => {
          const invoiceItems = orderItems.map((orderItem) => {
            const id = generateUniqueId();
            const price = orderItem.get('priceWTaxes');
            const totalSum = orderItem.get('totalSum');
            const product = orderItem.get('product');
            const amount = Number(orderItem.get('amount'));
            const weight = Number(product.get('weight')) * amount;

            return store.createRecord('i-i-s-shop-invoice-item', { id, amount, weight, price, totalSum, product, invoice });
          });

          invoice.get('invoiceItem').pushObjects(invoiceItems);
        }).finally(() => {
          this.set('orderItemsLoading', false);
        });
      }
    }
  },

  // ...
});
```

В шаблоне формы редактирования накладной, установим имя описанного обработчика в качестве значения свойства `updateLookupAction` для соответсвующего компонента `{% raw %}{{flexberry-lookup}}{% endraw %}`:

```hbs
{% raw %}{{!-- app\templates\i-i-s-shop-invoice-e.hbs --}}

{{!-- ... --}}

<div class="field">
  <label>{{t "forms.i-i-s-shop-invoice-e.order-caption"}}</label>
  {{flexberry-lookup
    choose="showLookupDialog"
    remove="removeLookupValue"
    value=model.order
    displayAttributeName="number"
    autocomplete=true
    relationName="order"
    projection="OrderL"
    title=(t "forms.i-i-s-shop-invoice-e.order-caption")
    readonly=(or readonly orderItemsLoading)
    componentName="orderLookup"
    updateLookupAction="orderChanged"
  }}
  {{flexberry-validationmessage error=(v-get validationObject "order" "messages")}}
</div>

{{!-- ... --}}{% endraw %}
```

Детейлы создаются путем вызова метода [createRecord](https://guides.emberjs.com/v3.1.0/models/creating-updating-and-deleting-records/#toc_creating-records), который создает в `store` новую запись. Особо стоит отметить необходимость генерировать **id** для новых записей детейла, которые создаются программно: для этого используется утилита [generateUniqueId](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/generate-unique-id.js) аддона `ember-flexberry-data`.

Связываются детейлы с агрегатором с использованием метода [pushObjects](https://guides.emberjs.com/v3.1.0/models/relationships/#toc_creating-records) у соответствующего свойства агрегатора со связью `hasMany`, которое представляет собой массив записей.

Также в обработчике, на время пока выполняется запрос для загрузки строк выбранного заказа, устанавливается свойство `orderItemsLoading` в значение `true`, чтобы компонент был заблокирован, и пользователь не имел возможности изменить заказ, пока обработчик не отработает полностью.

**Проверим** работоспособность реализованного обработчика. Для этого создадим **новый заказ**:

**Заказ 2**
> *<u>Менеджер</u>*: Евгеньева (Евгения Евгеньевна, таб. номер 4)  
> *<u>Содержимое заказа</u>*:  
> Монитор игровой MSI Optix MAG241CP - 2 шт. (48378)  
> *<u>Стоимость заказа</u>*: 48378  

Перейдем к накладным, **создадим новую накладную** и выберем в поле "Заказ" сначала **Заказ №1**:

![Новая накладная с заказом №1](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-19.png)

Изменим заказ на **Заказ №2**:

![Новая накладная с заказом №2](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-20.png)

При выборе заказа у нас сразу добавилась сумма заказа, одновременно с этим отработал реализованный обработчик на добавление строк накладной, а после этого - ранее написанный скрипт на вес заказа. При изменении заказа строки изменятся, т.к. в коде мы каждый раз удаляем все строки из списка товаров к выдаче и добавляем их заново.

{% include warning.html content="Начиная с версии ember-flexberry@3.0.0 существует проблема с удалением агрегаторов, у которых программным (не ручным) методом добавляются строки детейла. Также иногда наблюдается ошибка при добавлении новых записей.<br><br>
Эти ошибки исправлены в версии ember-flexberry@3.4.0 и не требуют исправления в коде приложения." %}

## Итог

Автозаполнение полей - важная часть логики приложения: с одной стороны, она облегчает ввод данных пользователю, а с другой - позволяет избежать ошибок при заполнении логически связанных полей.

## Самостоятельная работа

Вы можете выполнить следующие доработки в приложении самостоятельно для более детальной проработки требований к функционалу приложения:
1. при заполнении поля "Дата и время отгрузки" в Накладной заполнять поле "Дата отгрузки" соответствующего Заказа.

## Перейти

* [Практическое руководство «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
* [Настройка кастомной валидации](gpg_custom-validation.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Наложение ограничений на лукапы](gpg_lookup-restrictions.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
