---
title: Автозаполнение элементов формы
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, автозаполнение, детейл, observer
toc: true
permalink: ru/gpg_autofill-form-elements.html
lang: ru
---

Установим связи между полями Заказа и Накладной, которые следует настроить для полноценного функционирования некоторых форм редактирования:

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

## Настройка простого варианта автозаполнения

Рассмотрим настройку простого варианта автозаполнения на примере поля **Цена с налогом** формы **Заказ**. Так как это поле находится не в самом Заказе, а в Содержимом заказа, то откроем исходный код модели **Строка заказа**:

*`app → models → i-i-s-shop-order-item.js`*

![Модель заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-3.png)

Все действия будем производить **в соответствующей модели**. 

Поле **Цена с налогом** зависит от **ставки налога**. Добавим соответствующее значение в модель в виде константы:

{% highlight javascript%}
{% raw %}
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {
  taxes: 10,
}
{% endraw %}
{% endhighlight %}

Эта величина будет соответствовать налогу в 10%. К ней мы всегда сможем обратиться из модели: она стала её свойством с фиксированным значением.

Кроме ставки налога, Цена с налогом также зависит от **Товара** (конкретно - от его цены). Если товар меняется, также должна измениться и Цена. Следовательно, нам нужно инициировать **процесс**, который бы при загрузке данных, а также и при изменении данных Заказа, отслеживал бы изменения в поле Товар. Для этого будем использовать **обсервер**:

{% highlight javascript%}
{% raw %}
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {
  taxes: 10,

  /*
   * Цена с налогом
   */
  _priceWTaxesChanged: on('init', observer('product', function() {
    once(this, '_priceWTaxesCompute');
  })),
  _priceWTaxesCompute: function() {

  },
}
{% endraw %}
{% endhighlight %}

**[Обсервер](https://guides.emberjs.com/v3.1.0/object-model/observers/)** - специальная  Ember-функция, которая позволяет выполнять некие действия при изменении свойств Ember-объекта, в нашем случае - модели. В обсервере перед функцией-коллбеком указано свойство, за которым мы будем "следить" - свойство **product**. 

{% include warning.html content="Внутри обсервера мы используем специальную функцию once, которая позволяет исключить повторные вызовы коллбек-функции обсервера в случае, если обсервер сработает несколько раз подряд. Это обязательное условие при использовании обсервера в нашем примере, так как он может срабатывать по нескольку раз подряд и перегружать приложение. При большом количестве активных обсерверов Ember-приложение может перестать нормально работать." %}

Мы добавили в код сразу три новых встроенных функции - **on**, **once** и **observer**. Чтобы они работали, нужно их импортировать в модель:

{% highlight javascript%}
{% raw %}
import { observer } from '@ember/object';
import { once } from '@ember/runloop';
import { on } from '@ember/object/evented';
{% endraw %}
{% endhighlight %}

Далее напишем непосредственно **функцию**, которая на основании **Цены товара** будет высчитывать **Цену товара с налогом**:

{% highlight javascript%}
{% raw %}
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {
  taxes: 10,

  /*
   * Цена с налогом
   */
  _priceWTaxesChanged: on('init', observer('product', function() {
    once(this, '_priceWTaxesCompute');
  })),
  _priceWTaxesCompute: function() {
    let product = this.get('product');

    let result = 0; // При добавлении строки, когда товара еще нет
    if (product) {
      let price = Number(product.get('price'));
      let taxes = this.get('taxes') / 100 + 1;
      result = Number((price * taxes).toFixed(2)); // округление до 2 знаков
    }

    if (!this.get('isDeleted')) {
      this.set('priceWTaxes', result);
    }
  },
}
{% endraw %}
{% endhighlight %}

{% include important.html content="В самом конце функции обязательно нужно присвоить новое значение свойству модели (this). Это действие выполняется с использованием метода<br>
object.set(property, value)." %}

Так как в рассмотренном коде мы обращаемся к атрибуту **price** товара, необходимо **добавить** это свойство в **проекцию OrderE** модели order для связи **product**, которая в свою очередь является вложенной в связь **orderItem** (подробнее см. раздел “[Проекции моделей и их связь с валидируемыми данными](gpg_computable-properties-and-projections-of-models.html)”).

**Проверим** результат на примере **Заказа 1** (*до сих пор список товаров в нем был пуст*):

![Строка содержимого заказа без товара с вычислимой ценой с налогом](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-8.png)

При добавлении строки в содержимое заказа Цена с налогом имеет значение 0 - это корректное поведение. **Выберем товар**:

![Вычисление цены с налогом при выборе товара](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-9.png)

Видим, что в поле “Цена с налогом” появилась цена. Товар стоил 8990 + 899 (налог) = 9889. Все верно. 

Изменим цену у выбранного нами товара (Монитор Samsung C24F390FHI) на 9000. Откроем заказ 1, обновим страницу:

![Смена цены товара](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-10.png)

![Изменившаяся цена с налогом при изменении цены товара](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-11.png)

Несмотря на то, что в БД хранится до сих пор значение 9889 (если мы не сохранили Заказ), обсервер её актуализировал на уровне клиентского приложения.

---

**_Самостоятельно_** реализуйте автозаполнение для следующих полей:

**Заказ**  
**_Содержимое заказа_**
> Сумма по позиции (обсервер на Цену с налогом и Количество)

**Накладная**
> Сумма заказа (обсервер на Заказ) <sup>1</sup>.

<sup>1</sup> обсервер на сумму заказа пока никакого видимого эффекта давать не будет, т.к. сумма заказа еще не расчитана.

{% include important.html content="Не забудьте проверить модель на удаление (isDeleted)!" %}

{% include important.html content="Всегда проверяйте, срабатывает ли обсервер при перезагрузке страницы. Если необходимо - расширяйте проекции необходимыми свойствами." %}

---

{% include warning.html content="Для того, чтобы обсерверы срабатывали корректно, они должны по &#34;отслеживаемым&#34; свойствам либо быть независимыми друг от друга, либо действовать строго по цепочке.<br><br>
Например, если добавить для &#34;Строки заказа&#34; два обсервера - на &#34;Цену с налогом&#34; и на &#34;Сумму по позициям&#34;, - и привязать их к изменению Товара или &#34;Товар + Количество&#34; (ведь при изменении товара должна изменяться и сумма по позициям) соответственно, то при изменении Товара они будут срабатывать &#34;одновременно&#34;, что будет приводить к некорректному поведению приложения.<br><br>
В этом случае нужно понимать, что &#34;Сумма по позиции&#34; зависит не от Товара напрямую, а от &#34;Цены с налогом&#34;. Следовательно, вначале должен отработать обсервер на &#34;Цену с налогом&#34;, а уже после его выполнения должен подключаться второй, на &#34;Сумму по позиции&#34;.<br><br>
Если обсерверы по факту зависят один от результата другого, то в отслеживаемые свойства следует вносить именно свойство с результатом предыдущего обсервера, а не то поле, которое пользователь будет изменять и которое по факту является начальным триггером для цепочки изменений." %}

## Связь детейлов и агрегатора

Когда мы говорим о связи детейлов и агрегатора, мы имеем в виду ситуации следующего вида: **при изменении** в каждой новой строке Содержимого заказа поля **Сумма по позиции** должна меняться не только сама сумма, но также **пересчитываться Стоимость заказа**.

В такой ситуации можно настроить обсервер в самой модели заказа, но привязывать его к строке заказа будет тогда сложно. Проще реализовать функционал пересчета непосредственно при изменении поля Сумма по позиции.

Создадим **новый обсервер**, который зависит от поля **totalSum** строки заказа:

{% highlight javascript%}
{% raw %}
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {
  ...

  /*
   * Стоимость заказа
   */
  _orderSumChanged: on('init', observer('totalSum', function() {
    once(this, '_orderSumCompute');
  })),
  _orderSumCompute: function() {
      
  },
}
{% endraw %}
{% endhighlight %}

{% include warning.html content="Строго соблюдаем последовательность отработки связанных обсерверов: priceWTaxes (product) → totalSum (priceWTaxes, amount) → order.totalSum (totalSum)." %}

Чтобы получить Заказ (агрегатор для Строки заказа), достаточно обратиться к соответствующему свойству order и обработать его свойство orderItem: 

{% highlight javascript%}
{% raw %}
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {

  ...

  /*
   * Стоимость заказа
   */
  _orderSumChanged: on('init', observer('totalSum', function() {
    once(this, '_orderSumCompute');
  })),
  _orderSumCompute: function() {
    let order = this.get('order');
    let items = order.get('orderItem');
    let newSum = 0;
    items.forEach(function (item) {
      newSum += Number(item.get('totalSum'));
    });

    if (!this.get('isDeleted')) {
      order.set('totalSum', newSum);
    }
  },
}
{% endraw %}
{% endhighlight %}

Проверим, как работает скрипт: добавим данные о количестве для существующего товара в Заказе 1 - 2 шт.

![Логика работы автозаполнения стоимости заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-14.png)

Попробуйте сохранить заказ, изменить количество, товар, обновить страницу. Если все выполнено правильно, то Стоимость заказа должна корректно рассчитываться на основании всех позиций в Содержимом заказа.

---

**_Самостоятельно_** реализуйте автозаполнение для следующих полей:

**Накладная**
> Вес заказа(кг) (обсервер на Вес (кг) из Списка товаров к выдаче) <sup>1</sup>.

<sup>1</sup> обсервер на вес заказа пока никакого видимого эффекта давать не будет, т.к. не настроено пока заполнение Списка товаров к выдаче.

---

Результат последнего скрипта нельзя будет увидеть до тех пор, пока мы не реализуем заполнение Списка товаров к выдаче.

## Автозаполнение списка детейлов

До сих пор мы манипулировали достаточно простыми свойствами модели, значение которых легко было рассчитать и присвоить. Детейлы представляют собой куда более сложный объект, и потому создавать их нужно более сложным, чем просто использование метода set, образом.

Реализуем подобный скрипт на примере автозаполнения **Списков товара к выдаче** в **Накладной**:

*`app → models → i-i-s-shop-invoice.js`*

![Модель накладной с реализованной суммой заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-15.png)

Создадим **новый обсервер**, добавив следующий код:

{% highlight javascript%}
{% raw %}
import generateUniqueId from 'ember-flexberry-data/utils/generate-unique-id';
import { buildValidations } from 'ember-cp-validations';
{% endraw %}
{% endhighlight %}

{% highlight javascript%}
{% raw %}
/*
   * Список товаров к выдаче
   */
  _invoiceItemChanged: on('init', observer('order', function() {
      once(this, '_invoiceItemCompute');
  })),
  _invoiceItemCompute: function() {
    var me = this;

    if (!this.get('isDeleted')) {
      // Удаляем старые детейлы
      let currentItems = me.get('invoiceItem');
      currentItems.forEach(function (item) {
        item.deleteRecord();
      });

      let order = me.get('order');
      if (order) {
        let store = this.get('store');
        let orderId = order.get('id');

        let builder = new Builder(store, 'i-i-s-shop-order');
        builder.selectByProjection('OrderE');
        builder.byId(orderId);

        store.query('i-i-s-shop-order', builder.build())
          .then(function (orders) {
            orders.forEach(function(order) {
              let items = order.get('orderItem');
              items.forEach(function(item) {
                let product = item.get('product');
                let amount = Number(item.get('amount'));

                let weight = Number(product.get('weight'));
                let totalWeight = Number((weight * amount).toFixed(3));

                // Создаем новый детейл
                let invoiceItem = store.createRecord('i-i-s-shop-invoice-item', {
                  id: generateUniqueId(),
                  amount: amount,
                  weight: totalWeight,
                  price: item.get('priceWTaxes'),
                  totalSum: item.get('totalSum'),
                  product: product
                });

                // Добавляем детейл в список
                me.get('invoiceItem').pushObject(invoiceItem);
              });
            });
        });
      } else {
        this.set('totalWeight', 0);
      }
    }
  }
{% endraw %}
{% endhighlight %}

Детейлы создаются путем вызова метода [createRecord](https://guides.emberjs.com/v3.1.0/models/creating-updating-and-deleting-records/#toc_creating-records), которая создает в store новую запись. Особо стоит отметить необходимость генерировать для новый записей **id**: для этого используется утилита  **generateUniqueId** аддона **ember-flexberry-data**. 

Привязываются детейлы к агрегатору с использованием метода [pushObject](https://guides.emberjs.com/v3.1.0/models/pushing-records-into-the-store/) сервиса store.

Для того, чтобы список детейлов обновлялся в режиме реального времени, добавим свойство **searchForContentChange** в соответствующий компонент в templates:

*`app → templates → i-i-s-shop-invoice-e.hbs`*

{% highlight handlebars%}
{% raw %}
<div class="field">
    <label>{{t "forms.i-i-s-shop-invoice-e.invoiceItem-caption"}}</label>
    {{flexberry-groupedit
      componentName="invoiceItemGroupEdit"
      mainModelProjection=modelProjection
      modelProjection=modelProjection.attributes.invoiceItem
      content=model.invoiceItem
      readonly=true
      createNewButton=false
      deleteButton=false
      showCheckBoxInRow=false
      showDeleteButtonInRow=false
      defaultSortingButton=false
      defaultSettingsButton=false
      orderable=false
      searchForContentChange=true
      class=(if (v-get validationObject "invoiceItem" "isInvalid") "error")
    }}
    {{flexberry-validationmessage error=(v-get validationObject "invoiceItem" "messages")}}
</div>
{% endraw %}
{% endhighlight %}

**Проверим** работоспособность скрипта. Для этого создадим **новый заказ**:

**Заказ 2**
> *<u>Менеджер</u>*: Евгеньева (Евгения Евгеньевна, таб. номер 4)  
> *<u>Содержимое заказа</u>*:  
> Монитор игровой MSI Optix MAG241CP - 2 шт. (48378)  
> *<u>Стоимость заказа</u>*: 48378  

Перейдем к накладным, **создадим новую накладную** и выберем в поле “Заказ” вначале **Заказ 1**:

![Новая накладная с заказом 1](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-19.png)

Изменим заказ на **Заказ 2**:

![Новая накладная с заказом 2](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-20.png)

При выборе заказа у нас сразу добавилась сумма заказа, одновременно с этим отработал обсервер на добавление строк накладной, а после этого - ранее написанный скрипт на Вес заказа. При изменении заказа строки изменятся, т.к. в коде мы каждый раз удаляем все строки из списка товаров к выдаче и заполняем их заново.

{% include warning.html content="СВ версии ember-flexberry 3.0.0 существует проблема с удалением агрегаторов, у которых программным (не ручным) методом добавляются строки детейла. Также иногда наблюдается ошибка при добавлении.<br><br>
Эти ошибки будут исправлены в версии ember-flexberry 3.4.0 и не требуют исправления вручную." %}

## Итог

Автозаполнени полей - важная часть логики приложения: с одной стороны, она облегчает работу пользователя с приложением, с другой - позволяет избежать ошибок в заполнении ллогически связанных полей.

## Самостоятельная работа

Вы можете выполнить следующие доработки в приложении самостоятельно для более полной реализации функционала приложения:
1. при заполнении поля “Дата и время отгрузки” Накладной заполнять поле “Дата отгрузки” соответствующего Заказа.


## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Настройка кастомной валидации](gpg_custom-validation.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
<!-- * [Наложение ограничений на лукапы](gpg_lookup-restrictions.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> -->