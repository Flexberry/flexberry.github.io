---
title: Модальный диалог
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, Lookup, Flexberry Ember Component
toc: true
permalink: ru/efd3_modal-dialog.html
lang: ru
summary: Обзор возможностей, настроек и особенностей устройства компонента модального диалога в технологии Flexberry Ember.
---

## Назначение компонента

Компонент модального диалога в технологии Flexberry Ember предназначен для отображения данных внутри модального окна.

Модальный диалог используется в таких компонентах технологии Flexberry Ember как:

* [Компонент лукапа](efd3_lookup.html) (выбор значения и просмотр выбранного значения в некоторых случаях производится на модальном диалоге).
* [Файловый компонент]() (просмотр файла происходит в модальном окне).
* [Списковый компонент]() и [компонент групэдита]() (некоторые настройки, такие как, например, настройка фильтров, производятся в модальном окне).

## Обзор возможностей и API компонента

[Компонент модального диалога](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalDialog.html) реализован на базе компонента [Semantic UI Modal](https://semantic-ui.com/modules/modal.html), поэтому часть настроек аналогичны:

* `title` - заголовок модального окна (рекомендуется задавать [локализованное значение]()).
* `sizeClass` - css-класс размера модального окна, возможные варианты: 'small', 'large', 'fullscreen'. Если ничего не указано, то используется 'small'.
* `viewImageContent` - флаг, определяющий, что в модальном диалоге будет отображаться изображение, что изменит используемые css-классы разметки (используется, в частности, в окне просмотра файла в [файловом контроле]()).
* `useOkButton` - флаг, определяющий, следует ли на модальном окне отображать кнопку "Ок". Если ничего не указано, то используется 'true'.
* `useCloseButton` - флаг, определяющий, следует ли на модальном окне отображать кнопку закрытия. Если ничего не указано, то используется 'true'.
* `settings` - [настройки](https://semantic-ui.com/modules/modal.html#/settings), которые передаются внутреннему компоненту [Semantic UI Modal](https://semantic-ui.com/modules/modal.html).
* `componentName` - имя компонента.

Если ничего не было переопределено, то:

* при срабатывании [`onVisible`](https://semantic-ui.com/modules/modal.html#/settings) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `created`.
* при срабатывании [`onApprove`](https://semantic-ui.com/modules/modal.html#/settings) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/), определённый для `ok`,
* при срабатывании [`onHidden`](https://semantic-ui.com/modules/modal.html#/settings) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `close`.

Пример использования модального диалога, применяемый в [компоненте лукапа](efd3_lookup.html).

{% raw %}
```hbs
{{#modal-dialog
  title=title
  sizeClass=modalDialogSettings.sizeClass
  close='removeModalDialog'
  created='createdModalDialog'
  useOkButton=modalDialogSettings.useOkButton
  useCloseButton=modalDialogSettings.useCloseButton
  componentName=componentName
  settings=modalDialogSettings.settings
  data-test-lookup-dialog=true
}}
  {{outlet 'modal-content'}}
{{/modal-dialog}}
```
{% endraw %}

## Особенности устройства роутинга и компонента для отображения модального диалога

Особенности устройства роутинга и компонента для отображения модального диалога будут рассмотрены на примере внутреннего устройства [компонента лукапа](efd3_lookup.html).

Пусть [компонент лукапа](efd3_lookup.html) располагается на [форме редактирования](efd3_editform.html) и настроен таким образом, что выбор значения осуществляется на списке в модальном окне.

* При нажатии на соответствующую кнопку [компонента лукапа](efd3_lookup.html) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `choose`, это `showLookupDialog`.
* Обработчик `showLookupDialog` находится в [миксине](https://api.emberjs.com/ember/3.1/classes/Mixin) [контроллера](https://guides.emberjs.com/v3.1.0/controllers/) текущей [формы редактирования](efd3_editform.html) [`FlexberryLookupMixin`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookupMixin.html), который в итоге [прокидывает наружу Action](https://api.emberjs.com/ember/3.25/classes/Controller/methods/send?anchor=send) `showModalDialog`.
* Обработчик `showModalDialog` находится в [миксине](https://api.emberjs.com/ember/3.1/classes/Mixin) [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) всего приложения [ModalApplicationRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalApplicationRoute.html), который стартует рендеринг [шаблона](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/), где используется компонент модального диалога. Параметры для рендеринга заданы в [базовом контроллере формы редактирования `EditFormController`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html) и приведут к отображению [шаблона lookup-dialog](https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/lookup-dialog.hbs), который в разметке использует компонент модального диалога.
* При срабатывании [`onVisible`](https://semantic-ui.com/modules/modal.html#/settings) (фактически - это окончание отрисовки модального окна) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `created`, здесь это `createdModalDialog`. Обработчик `createdModalDialog` находится в [контроллере lookup-dialog](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/controllers/lookup-dialog.js).
* При скрытии модального окна (в результате любой из причины) срабатывает [`onHidden`](https://semantic-ui.com/modules/modal.html#/settings) и наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `close`, здесь это `removeModalDialog`. Обработчик `removeModalDialog` находится в [миксине](https://api.emberjs.com/ember/3.1/classes/Mixin) [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) всего приложения [ModalApplicationRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalApplicationRoute.html) и скрывает отрендеренный шаблон, содержащий модальный диалог.

Таким образом, при использовании модального диалога типичным является то, что:
* Отрисовка шаблона ([render](https://api.emberjs.com/ember/3.1/classes/Route/methods/render?anchor=render)) компонента для отображения модального диалога и его скрытие ([disconnectOutlet](https://api.emberjs.com/ember/3.1/classes/Route/methods/disconnectOutlet?anchor=disconnectOutlet)) происходят на базе [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) всего приложения `application` (соответствующие методы добавляются из [миксина](https://api.emberjs.com/ember/3.1/classes/Mixin) [ModalApplicationRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalApplicationRoute.html)).
* Компонент для отображения модального диалога является не [компонентом в типичном варианте](https://api.emberjs.com/ember/3.1/classes/Component), а парой из [контроллера](https://guides.emberjs.com/v3.1.0/controllers/) и [шаблона](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/), которым соответствующим образом передаётся управление.

