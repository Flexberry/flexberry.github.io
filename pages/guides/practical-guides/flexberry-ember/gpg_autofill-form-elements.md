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

## Автоматическое вычисление значений атрибутов внутри одной модели

Рассмотрим настройку варианта логики автоматического вычисления атрибутов внутри одной модели на примере поля "**Цена с налогом**" формы редактирования **Заказа**. Так как это поле находится не среди полей самого Заказа, а в детейле "Содержимое заказа", то нам необходимо открыть исходный код соответствующей модели **Строка заказа**:

*`app → models → i-i-s-shop-order-item.js`*

![Модель заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-3.png)

Далее все модификации исходного кода будем производить **в данной модели**. 

Поле "**Цена с налогом**" зависит от **ставки налога**. Добавим соответствующее значение ставки в модель в виде свойства с фиксированным значением:

{% highlight javascript%}
{% raw %}
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, OrderItemMixin, Validations, {
  taxes: 10,
}
{% endraw %}
{% endhighlight %}

Эта величина будет соответствовать налогу в 10%.

Кроме ставки налога, "Цена с налогом" также зависит от выбранного значения в поле "**Товар**" (а конкретно - от цены товара). Если выбранный товар меняется, то должна измениться и "Цена с налогом". Следовательно, нам нужно необходимо реализовать логику, которая бы отслеживала бы изменения в поле "Товар" как при загрузке формы редактирования заказа, так и при изменении значения в поле "Товар". Для реализации данной логики будем использовать [обзервер](https://guides.emberjs.com/v3.1.0/object-model/observers/):

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

**Обзервер** - специальная функция фреймворка Ember.js, которая позволяет выполнять произвольную логику при изменении свойств Ember-объекта, в нашем случае - свойств модели. В обзервере перед функцией-колбэком указывается свойство, за которым мы будем "следить" - свойство **product**. 

{% include warning.html content="Внутри обзервера мы используем специальную функцию [once](https://api.emberjs.com/ember/3.1/functions/@ember%2Frunloop/once), которая позволяет [исключить повторные вызовы колбэк-функции обзервера](https://guides.emberjs.com/v3.1.0/object-model/observers/#toc_observers-and-asynchrony) в случае, если обзервер сработает несколько раз подряд. Это обязательное условие при использовании обзервера в нашем примере, так как он может срабатывать по нескольку раз подряд и вызывать &#34;перегрузку&#34; приложения. Обратите также внимание на то, что при большом количестве активных обзерверов Ember-приложение может перестать работать стабильно из-за большого количества производимых вычислений!" %}

Поскольку мы начали использовать в коде сразу три новых функции - **on**, **once** и **observer**, нужно их импортировать в рассматриваемую модель:

{% highlight javascript%}
{% raw %}
import { observer } from '@ember/object';
import { once } from '@ember/runloop';
import { on } from '@ember/object/evented';
{% endraw %}
{% endhighlight %}

Далее реализуем **логику** внутри метода `_priceWTaxesCompute`, которая на основании "**Цены товара**" будет непосредственно вычислять "**Цену товара с налогом**":

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

    if (!this.get('isDeleted')) { // проверяем, что текущая модель не была удалена
      this.set('priceWTaxes', result);
    }
  },
}
{% endraw %}
{% endhighlight %}

{% include important.html content="В конце метода обязательно нужно присвоить новое значение соответствующему свойству текущей модели. Это действие выполняется с использованием метода `object.set(property, value)`." %}

Так как в рассмотренном коде мы обращаемся к свойству `price` товара, необходимо **добавить** это свойство в **проекцию OrderE** модели `order` для связи **product**, которая в свою очередь является вложенной в связь **orderItem** (подробнее см. раздел "[Вычислимые свойства и проекции моделей](gpg_computable-properties-and-projections-of-models.html)").

**Проверим** результат на примере добавления нового **Заказа** (или изменения **Заказа** с номером **1**, если ранее он был сохранен - *в этом случае список товаров в нем должен был быть пуст*). На данный момент список товаров в "Содержимом заказа" должен быть пустым. При добавлении новой строки в "Содержимое заказа" "Цена с налогом" имеет значение 0, как и ожидалось:

![Новая строка содержимого заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-8.png)

Далее **выберем товар**:

![Вычисление цены с налогом при выборе товара](/images/pages/guides/flexberry-ember/4-2-filling-application-primary-data/4-2-10.png)

После выбор товара мы можем увидеть, что в поле "Цена с налогом" появилась автоматически рассчитанная цена. Проверим расчеты для "Цены с налогом": товар стоил 8990 + 899 (налог) = 9889. Таким образом, "Цена с налогом" была расчитана действительно верно:

![Автоматический расчет цены с налогом](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-10.png)

Изменим цену у выбранного нами товара (Монитор Samsung C24F390FHI) на 9000:

![Выбранный товар с измененной ценой](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-9.png)

Далее снова откроем **Заказ №1** и обновим страницу:

![Изменившаяся цена с налогом при изменении цены товара](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-11.png)

Несмотря на то, что в БД хранится до сих пор значение 9889 (если мы не сохраняли Заказ после изменения цены выбранного товара), обзервер её актуализировал на уровне клиентского приложения.

---

**_Самостоятельно_** реализуйте автозаполнение для следующих полей:

**Заказ**  
**_Содержимое заказа_**
> "Сумма по позиции" (обзервер на "Цену с налогом" и "Количество")

{% include important.html content="Не забудьте проверить, находится ли модель в состоянии &#34;удаленная&#34; (isDeleted)!" %}

{% include important.html content="Всегда проверяйте, срабатывает ли обзервер при перезагрузке страницы. Если необходимо - расширяйте проекции необходимыми свойствами." %}

---

{% include warning.html content="Для того, чтобы обзерверы срабатывали корректно, они должны по &#34;отслеживаемым&#34; свойствам либо быть независимыми друг от друга, либо действовать строго по цепочке.<br><br>
Например, если добавить для &#34;Строки заказа&#34; два обзервера - на &#34;Цену с налогом&#34; и на &#34;Сумму по позиции&#34;, - и привязать их к изменению Товара или &#34;Товар + Количество&#34; (ведь при изменении Товара или его количества должна изменяться и сумма по позиции) соответственно, то при изменении Товара они будут срабатывать &#34;одновременно&#34;, что будет приводить к некорректному поведению приложения.<br><br>
В этом случае нужно понимать, что &#34;Сумма по позиции&#34; зависит не от Товара напрямую, а от &#34;Цены с налогом&#34;. Следовательно, вначале должен отработать обзервер на &#34;Цену с налогом&#34;, а уже после его выполнения должен выполняться второй, на &#34;Сумму по позиции&#34;.<br><br>
Если фактически обзервер зависит от результата выполнения другого обзервера, то в &#34;отслеживаемых&#34; свойствах такого обзервера следует указывать именно свойство с результатом предыдущего обзервера, а не то свойство, которое пользователь будет непосредственно изменять на форме и которое по факту будет являться &#34;начальным триггером&#34; для цепочки изменений." %}

## Автоматическое вычисление значений атрибутов агрегатора

Автоматическое вычисление значений атрибутов агрегатора на основании атрибутов детейла рассмотрим на следующем примере: **при изменении** у Заказа в любой строке "Содержимого заказа" поля "**Сумма по позиции**" должна также пересчитываться и "**Стоимость заказа**".

Если в подобной ситуации создать обзервер в самой модели Заказа, то привязать его к модели для "Строки заказа" тогда будет проблематично. Будет проще реализовать функционал пересчета "Стоимости заказа" непосредственно при изменении поля "Сумма по позиции" в конкретной "Строке заказа".

Создадим **новый обзервер** в модели "**Строка заказа**", который зависит от поля **totalSum**:

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

{% include warning.html content="Внимательно следим за последовательностью срабатывания связанных обзерверов: priceWTaxes (product) → totalSum (priceWTaxes, amount) → order.totalSum (totalSum)." %}

Чтобы получить Заказ (агрегатор для "Строки заказа"), достаточно обратиться к соответствующему свойству `order` в модели "Строки заказа", а для подсчета "Суммы заказа" можно перебрать все записи из свойства `orderItem` модели "Заказа", просуммировав соответствующие "Суммы по позиции": 

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

Теперь проверим, срабатывает ли обзервер. Для этого изменим количество для существующего товара в Заказе №1 на 2 шт.:

![Логика работы автозаполнения стоимости заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-14.png)

Попробуйте сохранить заказ, изменить количество товара, товар, обновить страницу. Если все выполнено правильно, то "Стоимость заказа" должна корректно рассчитываться на основании всех позиций в "Содержимом заказа".

---

**_Самостоятельно_** реализуйте автозаполнение для следующих полей:

**Накладная**
> "Сумма заказа" (обзервер на "Заказ") <sup>1</sup>.
> "Вес заказа" (обзервер на "Вес" из "Списка товаров к выдаче") <sup>1</sup>.

<sup>1</sup> Поскольку "Список товаров к выдаче" в Накладной должен заполняеться автоматически, а поля "Сумма заказа" и "Вес заказа" должны в свою очередь также рассчитываться автоматически на основании записей из "Списка товаров к выдаче", то на данный момент расчет "Суммы заказа" и "Веса заказа" производиться не будет. Данные обзерверы будут срабатывать после того, как мы далее реализуем автозаполнение "Списка товаров к выдаче" на основе выбранного "Заказа" в Накладной.

---

## Автозаполнение списка детейлов

Автоматически можно вычислять не только значения собственных атрибутов модели, но также при необходимости и изменять перечень связанных записей.

В качестве примера рассмотрим логику автозаполнения детейла **Список товаров к выдаче** в **Накладной** на основе выбранного **Заказа**:

*`app → models → i-i-s-shop-invoice.js`*

![Модель накладной с реализованной суммой заказа](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-15.png)

Создадим **новый обзервер**, добавив следующий код:

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

Детейлы создаются путем вызова метода [createRecord](https://guides.emberjs.com/v3.1.0/models/creating-updating-and-deleting-records/#toc_creating-records), которая создает в store новую запись. Особо стоит отметить необходимость генерировать **id** для новых записей детейла, которые создаются программно: для этого используется утилита [generateUniqueId](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/generate-unique-id.js) аддона `ember-flexberry-data`.

Связываются детейлы с агрегатором с использованием метода [pushObject](https://guides.emberjs.com/v3.1.0/models/relationships/#toc_creating-records) у соответствующего свойства агрегатора со связью `hasMany`, которое представляет собой массив записей.

Для того, чтобы список детейлов обновлялся в режиме реального времени, добавим свойство **searchForContentChange** в соответствующий компонент в шаблоне формы редактирования Накладной:

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

**Проверим** работоспособность созданного обзервера. Для этого создадим **новый заказ**:

**Заказ 2**
> *<u>Менеджер</u>*: Евгеньева (Евгения Евгеньевна, таб. номер 4)  
> *<u>Содержимое заказа</u>*:  
> Монитор игровой MSI Optix MAG241CP - 2 шт. (48378)  
> *<u>Стоимость заказа</u>*: 48378  

Перейдем к накладным, **создадим новую накладную** и выберем в поле "Заказ" сначала **Заказ №1**:

![Новая накладная с заказом №1](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-19.png)

Изменим заказ на **Заказ №2**:

![Новая накладная с заказом №2](/images/pages/guides/flexberry-ember/6-1-autofill-form-elements/6-1-20.png)

При выборе заказа у нас сразу добавилась сумма заказа, одновременно с этим отработал обзервер на добавление строк накладной, а после этого - ранее написанный скрипт на Вес заказа. При изменении заказа строки изменятся, т.к. в коде мы каждый раз удаляем все строки из списка товаров к выдаче и добавляем их заново.

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
