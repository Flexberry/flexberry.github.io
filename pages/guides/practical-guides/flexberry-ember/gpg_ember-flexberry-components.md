---
title: Компоненты ember-flexberry
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, ember-flexberry
toc: true
permalink: ru/gpg_ember-flexberry-components.html
lang: ru
---

Как мы уже говорили ранее, для создания элементов форм используются компоненты **аддона ember-flexberry**: он включает в себя различные компоненты, которые мы можем использовать в приложении. Рассмотрим варианты вазимодействия с этими компонентами.

## Свойства компонентов ember-flexberry

Разберем настройку компонентов на примере поля "Дата и время отгрузки" на форме редактирования "Накладной":

![Выбор даты без времени в поле "Дата и время отгрузки"](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-1.png)

Как мы видим, календарь по умолчанию позволяет выбрать дату, но не время. Это не отвечает требованиям к нашему приложению. Перейдем к **шаблону** формы редактирования и найдем там создание необходимого нам компонента:

*`app → templates → i-i-s-shop-invoice-e.hbs`*

![Компонент поля "Дата и время отгрузки"](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-2.png)

Данный компонент называется **flexberry-simpledatetime**. Чтобы понять, как его настроить, обратимся к [тестовому стенду ember-flexberry](http://flexberry.github.io/ember-flexberry/dummy/develop/#/):

![Flexberry-simpledatetime на тестовом стенде](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-3.png)

Нас интересует **свойство type**. Если мы посмотрим, как ведет себя компонент при установке в свойство type значения **datetime-local** (вид компонента соответствующий текущим настройкам можно увидеть под таблицей настроек), то заметим, что после выбора непосредственно даты имеется возможность выбрать также и время:

![Выбор даты и времени в компоненте flexberry-simpledatetime](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-4.png)

Установим для нашего поля значение **type="datetime-local"**:

{% highlight handlebars%}
{% raw %}
<div class="field">
    <label>{{t "forms.i-i-s-shop-invoice-e.shipmentDateTime-caption"}}</label>
    {{
      flexberry-simpledatetime
      type="datetime-local"
      placeholder=(t 'components.flexberry-datepicker.placeholder')
      readonly=readonly
      value=model.shipmentDateTime
      class=(if (v-get validationObject "shipmentDateTime" "isInvalid") "error")
    }}
    {{flexberry-validationmessage error=(v-get validationObject "shipmentDateTime" "messages")}}
  </div>
{% endraw %}
{% endhighlight %}

![Выбор даты и времени в поле "Дата и время отгрузки"](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-6.png)

Теперь компонент работает так как мы того хотели.

{% include note.html content="Вне зависимости от того, позволяет ли нам поле выбирать время или нет (только дата), в БД всегда сохраняются полные данные &#34;время + дата&#34;, так как во Flexberry Designer мы указывали для полей времени тип DateTime, который по умолчанию при генерации БД сопоставляется с типом для хранения даты и времени. Если компонент не позволяет выбирать время, то оно по умолчанию будет иметь значение 00:00:00." %}

---

**_Самостоятельно_** настройте режим "только для чтения" для следующих полей:

**Накладная**
> Дата оформления  
> Сумма заказа  
> Вес заказа  
> Список товаров к выдаче (+ убрать кнопки над таблицей и чекбокс в её шапке)

**Заказ**
> Дата оформления  
> Стоимость заказа  
> Дата отгрузки (по соответствующей накладной)

*<u>Подсказка</u>*: при необходимости обратитесь к тестовому стенду аддона ember-flexberry.

---

## Режим "только для чтения" у полей внутри детейлового компонента

Не для всех компонентов ember-flexberry возможно настроить свойства "напрямую". Особым образом должны настраиваться компоненты, которые отображаются внутри детейлового компонента. Рассмотрим настройку режима "**только для чтения**" у столбцов **Цена с налогом** и **Сумма по позиции** формы Заказ. Для этого перейдем в соответствующий Заказу контроллер:

*`app → controllers → i-i-s-shop-order-e.js`*

![Контроллер формы редактирования заказа](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-7.png)

В контроллере уже есть код, который управляет настройкой лукапа внутри детейлового компонента. **Добавим** следующий код, чтобы настроить атрибут, управляющий режимом "только для чтения" у отдельных столбцов детейлового компонента:

{% highlight javascript%}
{% raw %}
import { set } from '@ember/object';
{% endraw %}
{% endhighlight %}

{% highlight javascript%}
{% raw %}
actions: {
    configurateOrderItemRow(rowConfig) {
    let readonlyColumns = ['priceWTaxes', 'totalSum'];
    set(rowConfig, 'readonlyColumns', readonlyColumns);
    },
}
{% endraw %}
{% endhighlight %}

{% include important.html content="Новую функцию мы объявили внутри свойства actions: здесь объявляются функции, которые можно будет назначить в качестве обработчиков событий для какого-либо компонента в шаблоне (больше об actions см. [тут](https://guides.emberjs.com/v3.1.0/templates/actions/))" %}

Теперь остается только добавить обработку события в шаблоне:

*`app → templates → i-i-s-shop-order-e.js`*

{% highlight handlebars%}
{% raw %}
<div class="field">
    <label>{{t "forms.i-i-s-shop-order-e.orderItem-caption"}}</label>
    {{flexberry-groupedit
      componentName="orderItemGroupEdit"
      mainModelProjection=modelProjection
      modelProjection=modelProjection.attributes.orderItem
      content=model.orderItem
      readonly=readonly
      orderable=false
      configurateRow=(action "configurateOrderItemRow")
      class=(if (v-get validationObject "orderItem" "isInvalid") "error")
    }}
    {{flexberry-validationmessage error=(v-get validationObject "orderItem" "messages")}}
</div>
{% endraw %}
{% endhighlight %}

Если мы проверим теперь, как отображаются поля строки заказа, то увидим, что значения для указанных нами столбцов будут недоступны для редактирования:

![Заблокированные для изменений поля содержимого заказа](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-10.png)

## Итог

Настройка компонентов аддона ember-flexberry во многом определеяет внешний вид компонентов форм, а также их поведение в определенных ситуациях. Часть функционала не отображена ва тестовом стенде. Более подробно с функционалом того или иногло компонента можно ознакомиться в [репозитории](https://github.com/Flexberry/ember-flexberry/tree/ae9a7fd90d8f28775a6e1e42421ae6455f7a6f90/tests/dummy) тестового стенда или у технологов аддона.

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Условное отображение полей на форме](gpg_conditional-display-fields.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Вычислимые свойства и проекции моделей](gpg_computable-properties-and-projections-of-models.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>