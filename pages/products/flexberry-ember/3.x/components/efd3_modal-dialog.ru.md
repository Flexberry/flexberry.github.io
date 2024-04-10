---
title: Модальное окно
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, Lookup, Flexberry Ember Component
toc: true
permalink: ru/efd3_modal-dialog.html
lang: ru
summary: Обзор возможностей, настроек и особенностей устройства компонента модального окна в технологии Flexberry Ember.
---

## Назначение компонента

Компонент модального окна в технологии [Flexberry Ember](https://flexberry.github.io/ru/ef3_landing_page.html) предназначен для отображения данных внутри модального окна.

Модальное окно используется в таких компонентах технологии Flexberry Ember как:

* [Компонент лукапа](ef3_flexberry-lookup.html): выбор значения и просмотр выбранного значения в некоторых случаях производится на модальном окне.
* Файловый компонент: просмотр файла происходит в модальном окне.
* [Списковый компонент](https://flexberry.github.io/ru/efd3_object-list-view.html) и [компонент групэдита](https://flexberry.github.io/ru/ef3_groupedit_with_multiselect.html): некоторые настройки, такие как, например, настройка фильтров, производятся в модальном окне.

## Обзор возможностей и API компонента

[Компонент модального окна](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalDialog.html) реализован на базе компонента [Semantic UI Modal](https://semantic-ui.com/modules/modal.html), поэтому часть настроек аналогичны:

* `title` - заголовок модального окна (рекомендуется задавать локализованное значение).
* `sizeClass` - css-класс размера модального окна, возможные варианты: 'small', 'large', 'fullscreen'. Если ничего не указано, то используется 'small'.
* `viewImageContent` - флаг, определяющий, что в модальном окне будет отображаться изображение, что изменит используемые css-классы разметки (используется, в частности, в окне просмотра файла в файловом контроле).
* `useOkButton` - флаг, определяющий, следует ли на модальном окне отображать кнопку "Ок". Если ничего не указано, то используется 'true'.
* `useCloseButton` - флаг, определяющий, следует ли на модальном окне отображать кнопку закрытия. Если ничего не указано, то используется 'true'.
* `componentName` - имя компонента.
* `useSidePageMode` - открывать модальное окошко в режиме `sidepage`
* `settings` - [настройки](https://semantic-ui.com/modules/modal.html#/settings), которые передаются внутреннему компоненту [Semantic UI Modal](https://semantic-ui.com/modules/modal.html)
  * _Параметр `context` позволяет изменить селектор области, которая должна быть закрыта фоном модального окна. По умолчанию параметр имеет значение `'.ember-application > .ember-view'` - т.е. весь экран._

Если ничего не было переопределено, то:

* при срабатывании [`onVisible`](https://semantic-ui.com/modules/modal.html#/settings) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `created`.
* при срабатывании [`onApprove`](https://semantic-ui.com/modules/modal.html#/settings) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/), определённый для `ok`,
* при срабатывании [`onHidden`](https://semantic-ui.com/modules/modal.html#/settings) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `close`.

Пример использования модального окна, применяемый в [компоненте лукапа](ef3_flexberry-lookup.html).

```hbs
{% raw %}
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
{% endraw %}
```

Дополнительные ссылки:
- [Обучающее видео о modal-dialog (с 5:47)](https://youtu.be/_LKhIKZ21n8?si=LJhWXBuuV8QjvP-D&t=347).

### Вызов модального окна через экшен
Вызов модального окна через action (`showModalDialog`) позволяет показывать модальное окно через контроллер. Это рекомендуемый способ работы с модальными окнами `ember-flexberry`.

Чтобы показать модальное окно, используя экшен (showModalDialog) необходимо:
- вынести шаблон модального окошка в отдельный файл (напр. `/templates/my-modal.hbs`):
```hbs
{% raw %}
{{#modal-dialog
  title="Выберите запись"
  sizeClass="fullscreen"
  close='removeModalDialog'
  created='createdModalDialog'
  useOkButton=true
  useCloseButton=false
  componentName=componentName
  settings=model.settings
}}
  {{#each model.records as record}}
    {{record}}
  {{/each}}
{{/modal-dialog}}
{% endraw %}
```
- вызвать action `showModalDialog`:
```javascript
this.send('showModalDialog', 'templates/my-modal',
{
  controller: 'my-modal-controller',
  model: {
    records: ['Запись 1', 'Запись 2', 'Запись 3'],
    settings: {
      closable: false
    }
  }
});
```
с указанием:
  * шаблона модального окна (`/templates/my-modal`)
  * контроллера модального окна (`my-modal-controller` - находится в папке `controllers`; можно использовать любой `L` и `E` контроллер)
  * передаваемых в модальное окошко данных (`model` - _данные можно передать только через параметр `model`_)

В итоге, модальное окно отобразит данные согласно своему шаблону. Обратите внимание, в шаблон модального окна данные передаются через параметр `model` .

## Особенности устройства роутинга и компонента для отображения модального окна

Особенности устройства роутинга и компонента для отображения модального окна будут рассмотрены на примере внутреннего устройства [компонента лукапа](ef3_flexberry-lookup.html).

Пусть [компонент лукапа](ef3_flexberry-lookup.html) располагается на [форме редактирования](efd3_editform.html) и настроен таким образом, что выбор значения осуществляется на списке в модальном окне.

* При нажатии на соответствующую кнопку [компонента лукапа](ef3_flexberry-lookup.html) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `choose`, это `showLookupDialog`.
* Обработчик `showLookupDialog` находится в [миксине](https://api.emberjs.com/ember/3.1/classes/Mixin) [контроллера](https://guides.emberjs.com/v3.1.0/controllers/) текущей [формы редактирования](efd3_editform.html) [FlexberryLookupMixin](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookupMixin.html), который в итоге [прокидывает наружу Action](https://api.emberjs.com/ember/3.25/classes/Controller/methods/send?anchor=send) `showModalDialog`.
* Обработчик `showModalDialog` находится в [миксине](https://api.emberjs.com/ember/3.1/classes/Mixin) [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) всего приложения [ModalApplicationRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalApplicationRoute.html), который стартует рендеринг [шаблона](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/), где используется компонент модального окна. Параметры для рендеринга заданы в [базовом контроллере формы редактирования EditFormController](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html) и приведут к отображению [шаблона lookup-dialog](https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/lookup-dialog.hbs), который в разметке использует компонент модального окна.
* При срабатывании [onVisible](https://semantic-ui.com/modules/modal.html#/settings) (фактически - это окончание отрисовки модального окна) наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `created`, здесь это `createdModalDialog`. Обработчик `createdModalDialog` находится в [контроллере lookup-dialog](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/controllers/lookup-dialog.js).
* При скрытии модального окна (в результате любой из причины) срабатывает [onHidden](https://semantic-ui.com/modules/modal.html#/settings) и наружу прокидывается [Action](https://guides.emberjs.com/v3.1.0/components/triggering-changes-with-actions/) определённый для `close`, здесь это `removeModalDialog`. Обработчик `removeModalDialog` находится в [миксине](https://api.emberjs.com/ember/3.1/classes/Mixin) [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) всего приложения [ModalApplicationRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalApplicationRoute.html) и скрывает отрендеренный шаблон, содержащий модальное окно.

Таким образом, при использовании модального окна типичным является то, что:

* Отрисовка шаблона ([render](https://api.emberjs.com/ember/3.1/classes/Route/methods/render?anchor=render)) компонента для отображения модального окна и его скрытие ([disconnectOutlet](https://api.emberjs.com/ember/3.1/classes/Route/methods/disconnectOutlet?anchor=disconnectOutlet)) происходят на базе [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) всего приложения `application` (соответствующие методы добавляются из [миксина](https://api.emberjs.com/ember/3.1/classes/Mixin) [ModalApplicationRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ModalApplicationRoute.html)).
* Компонент для отображения модального окна является не [компонентом в типичном варианте](https://api.emberjs.com/ember/3.1/classes/Component), а парой из [контроллера](https://guides.emberjs.com/v3.1.0/controllers/) и [шаблона](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/), которым соответствующим образом передаётся управление.
