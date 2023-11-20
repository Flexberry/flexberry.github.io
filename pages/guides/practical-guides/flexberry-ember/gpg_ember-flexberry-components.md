---
title: Настройка компонентов
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, ember-flexberry
toc: true
permalink: ru/gpg_ember-flexberry-components.html
lang: ru
---

В сгенерированных приложениях на списковых формах и формах редактирования используются компоненты из аддона [ember-flexberry](https://github.com/Flexberry/ember-flexberry). Данный аддон содержит различные компоненты, используемые на любых страницах приложения, в котором установлен даный аддон.

В этом разделе будут рассмотрены принципы использования и настройки компонентов в приложениях на Flexberry Ember.

## Настройка свойств на примере компонента для работы с датой и временем

Процесс настройки на примере компонента для отображения атрибута **shipmentDateTime** ("Дата и время отгрузки") на форме редактирования "Накладной":

![Выбор даты без времени в поле "Дата и время отгрузки"](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-1.png)

Используемый компонент календаря по умолчанию позволяет выбрать только дату, без времени. В приложении необходимо помимо даты выбрать также и время.

Открыть **шаблон** соответствующей формы редактирования и найти в нем разметку для создания компонента [flexberry-simpledatetime](https://flexberry.github.io/ru/ef2_edit-form-components.html#flexberry-simpledatetime), который используется для отображения атрибута **model.shipmentDateTime**:

*`app → templates → i-i-s-shop-invoice-e.hbs`*

![Компонент для атрибута "Дата и время отгрузки"](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-2.png)

Для различных аддонов, входящих в состав фреймворка Flexberry Ember, имеются тестовые стенды, на которых есть возможность посмотреть и протестировать перечень входящих в состав соответствующего аддона компонентов. В частности, для того чтобы понять, как настроить различные компоненты из аддона `flexberry-ember`, можно открыть соответствующий [тестовый стенд](http://flexberry.github.io/ember-flexberry/dummy/master/):

![Компонент flexberry-simpledatetime на тестовом стенде](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-3.png)

Выбрав страницу с настройками нужного компонента, можно поэкспериментировать с изменением различных свойств выбранного компонента и посмотреть, как при этом будет меняться его поведение.

Для компонента `flexberry-simpledatetime` **свойство type** отвечает за выбор типа отображаемого компонента (с временем или без него). При установке в свойстве `type` значения **datetime-local** у компонента появляется возможность выбирать не только дату, но и время (вид компонента, соответствующий текущим настройкам, можно увидеть под таблицей настроек).

![Выбор даты и времени в компоненте flexberry-simpledatetime](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-4.png)

Установить в шаблоне формы редактирования накладной для рассматриваемого компонента значение **type="datetime-local"**:

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

После сохранения внесенных изменений компонент будет настроен требуемым образом.

> Независимо от того, настроен ли компонент для выбора даты или даты с временем, в БД всегда будет записываться полная дата (дата + время), так как во Flexberry Designer указан тип DateTime для полей, в которых должна храниться дата. Тип DateTime при генерации БД [сопоставляется с соответствующим типом данных](https://flexberry.github.io/ru/fd_types-map.html) для хранения даты и времени. Таким образом, если компонент в клиентском приложении не позволяет выбирать время, то оно по умолчанию будет иметь значение 00:00:00.

---

Чтобы потренироваться в настройке свойств компонентов, рекомендуется **самостоятельно** настроить режим "только для чтения" для компонентов, соответствующих следующим полям:

Класс **Накладная**
> Дата оформления  
> Сумма заказа  
> Вес заказа  
> Список товаров к выдаче (+ убрать кнопки над таблицей и "чекбокс" в её шапке)

Класс **Заказ**
> Дата оформления  
> Стоимость заказа  
> Дата отгрузки (по соответствующей накладной)

*<u>Подсказка</u>*: при необходимости воспользуйтесь возможностями тестового стенда аддона `ember-flexberry`.

---

## Настройка режима "только для чтения" у полей внутри детейлового компонента

Не всегда для компонентов аддона `ember-flexberry` возможно настроить свойства непосредственно в шаблоне формы. Особым образом должны настраиваться компоненты, которые отображаются внутри [детейлового компонента](https://flexberry.github.io/ru/ef2_groupedit.html). Например, настройка режима "только для чтения" у компонентов в столбцах "Цена с налогом" и "Сумма по позиции" детейлового компонента для работы со "строками заказа" на форме радактирования Заказа. Для этого перейти к соответствующему контроллеру:

*`app → controllers → i-i-s-shop-order-e.js`*

![Контроллер формы редактирования заказа](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-7.png)

В данном контроллере уже имеется код, который управляет настройкой свойств [лукапа](https://flexberry.github.io/ru/ef2_lookup-component.html) внутри детейлового компонента. **Добавить** следующий код, чтобы настроить режим "только для чтения" для содержимого отдельных столбцов детейлового компонента:

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

> Новую функцию мы объявили внутри свойства actions: значением этого свойства является объект с методами, которые можно будет назначить в качестве обработчиков событий какого-либо компонента в шаблоне (подробнее об actions см. [тут](https://guides.emberjs.com/v3.1.0/templates/actions/)).

Теперь остается только настроить свойства детейлового компонента в шаблоне требуемым образом:

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

Значения в компонентах внутри указанных столбцов будут недоступны для редактирования:

![Заблокированные для изменений поля содержимого заказа](/images/pages/guides/flexberry-ember/5-2-ember-flexberry-components/5-2-10.png)

## Итог

В данном разделе были продемонстрированы принципы настройки компонентов из аддона `ember-flexberry`, в том числе настройки компонентов внутри детейлового компонента. При этом для понимания того, как изменение конкретного свойства влияет на поведение компонента, можно воспользоваться возможностями тестового стенда аддона `flexberry-ember`.

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Условное отображение данных на форме](gpg_conditional-display-fields.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Вычислимые свойства и проекции моделей](gpg_computable-properties-and-projections-of-models.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
