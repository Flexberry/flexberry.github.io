---
title: Формы редактирования и создания Flexberry Ember
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_editform.html
lang: ru
summary: Предназначение, структура, функции, особенности форм редактирования и создания Flexberry Ember.
---

## Описание
**Форма редактирования** предназначена для редактирования объекта.

**Форма создания** - для создания нового объекта (хотя в общем случае это очень близкие по логике работы формы).

Чаще всего переход на формы редактирования и создания осуществляется со [списковой формы](efd3_listform.html).

Чтобы создать форму редактирования [модели по проекции](efd3_model.html), необходимо определить соответствующие [роуты](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/), [контроллеры](https://guides.emberjs.com/v3.1.0/controllers/) и [шаблоны](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/).

### Базовые элементы
Реализованные в технологии `Flexberry Ember` **базовые элементы** для форм редактирования и создания представляют собой:

* [базовый контроллер `EditFormController`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html),
* [базовый роут для формы создания `EditFormNewRoute`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormNewRoute.html), он унаследован от [базового роута для формы редактирования `EditFormRoute`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormRoute.html).

### Устройство роутов

Для формы редактирования по умолчанию роут [генерируется](efd3_generated-app-structure.html) по адресу: `app/routes/<название-модели>-e.js`.
Генерируемый роут формы редактирования выглядит следующим образом:

```js
import EditFormRoute from 'ember-flexberry/routes/edit-form'; // Базовый роут формы редактирования из Flexberry Ember.

export default EditFormRoute.extend({
  modelProjection: 'AgregatorClassE', // Название проекции.
  modelName: 'neo-platform-gen-test-agregator-class' // Название модели.
});
```

Для формы создания по умолчанию роут [генерируется](efd3_generated-app-structure.html) по адресу: `app/routes/<название-модели>-e/new.js`.
Генерируемый роут формы создания выглядит следующим образом:

```js
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new'; // Базовый роут формы создания из Flexberry Ember.

export default EditFormNewRoute.extend({
  modelProjection: 'AgregatorClassE', // Название проекции.
  modelName: 'neo-platform-gen-test-agregator-class', // Название модели.
  templateName: 'neo-platform-gen-test-agregator-class-e', // Название шаблона (по умолчанию используется тот же шаблон, что и для соответствующей формы редактирования).
});
```

Вычитка данных для формы осуществляется в роуте, поэтому именно здесь указаны имена [модели и проекции](efd3_model.html).

### Устройство контроллеров

Для формы редактирования по умолчанию [контроллер](https://guides.emberjs.com/v3.1.0/controllers/) [генерируется](efd3_generated-app-structure.html) по адресу: `app/controllers/<название-модели>-e.js`.
Генерируемый контроллер формы редактирования выглядит следующим образом и содержит указание на соответствующий роут родительской [списковой формы](efd3_listform.html), куда будет передано управление после завершения работы на данной форме:

```js
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  parentRoute: 'neo-platform-gen-test-child1-l',
});
```

Для формы создания по умолчанию [контроллер](https://guides.emberjs.com/v3.1.0/controllers/) [генерируется](efd3_generated-app-structure.html) по адресу: `app/controllers/<название-модели>-e/new.js`.
Генерируемый контроллер формы создания представляет собой наследника контроллера соответствующей формы редактирования, который при необходимости может быть переопределён:

```js
import NeoPlatgormGenTestChild1EController from '../neo-platform-gen-test-child1-e';

var NeoPlatgormGenTestChild1ENewController = NeoPlatgormGenTestChild1EController;
export default NeoPlatgormGenTestChild1ENewController;
```

В случае, если у [модели](efd3_model.html) есть детейлы, у которых есть мастера, то в контроллере формы редактирования дополнительно переопределён метод `getCellComponent`, определяющий, какой именно компонент осуществляет редактирование различных полей детейла. 

```js
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  parentRoute: 'neo-platform-gen-test-agregator-class-l', // Роут соответствующей списковой формы.

  getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments); // Получение стандартного компонета.
    if (attr.kind === 'belongsTo') { // Определение, что связь у детейла является мастеровой.
      switch (`${model.modelName}+${bindingPath}`) {
        case 'neo-platform-gen-test-detail-for-agregator+masterForAgregator': // Для конкретной мастеровой связи детейла задаются настройки для лукапа.
          cellComponent.componentProperties = {
            choose: 'showLookupDialog',
            remove: 'removeLookupValue',
            displayAttributeName: 'enum2Field',
            required: true,
            relationName: 'masterForAgregator',
            projection: 'MasterForAgregatorL',
            autocomplete: true,
          };
          break;

      }
    }

    return cellComponent;
  },
});
```

Для работы в режиме *только для чтения* в [базовом контроллере формы редактирования `EditFormController`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html) добавлено свойство [`readonly`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#property_readonly).

Чтобы открыть форму редактирования только на чтение, можно:

* Передать GET-параметр в строке запроса, например, так: `http://localhost:4200/orders/10251?readonly=true`.
* Переопределить определение значение свойства `readonly` в контроллере.

```js
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  readonly: true,
  ...
});
```

Обработка данного параметра используется в следующих вариантах:

* Данное свойство передаётся в [компоненты, наследуемые от базового компонента ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/components/flexberry-base-component.js).
* Данное свойство используется в шаблоне формы редактирования для определения, какие кнопки доступны пользователю (об этом ниже).

Доступен [пример формы в режиме "только для чтения"](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/integration-examples/edit-form/readonly-mode).

### Шаблоны

Для форм создания и редактирования используется один [шаблон](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/). Типичный шаблон для формы выглядит следующим образом:

{% raw %}
```hbs
<h3 class="ui header">{{t "forms.neo-platform-gen-test-agregator-class-e.caption"}}</h3> <!-- Локализованный заголовок формы -->

<form class="ui form flexberry-vertical-form" role="form">
  {{flexberry-error error=error}}
  <div class="field">
    <div class="flexberry-edit-panel">
      {{#unless readonly}} <!-- Кнопки выводятся, если форма не находится в режиме "readonly" -->
        <button class="ui button save-button" {{action "save"}}> <!-- Кнопка "сохранение" -->
          {{t "forms.edit-form.save-button-text"}}
        </button>
        {{#unless model.isNew}} <!-- Кнопка "удаление" не доступна на форме создания -->
          <button class="ui button save-del-button" {{action "delete"}}> <!-- Кнопка "удаление" -->
            {{t "forms.edit-form.delete-button-text"}}
          </button>
        {{/unless}}
      {{/unless}}
      <button class="ui button close-button" {{action "close"}}> <!-- Кнопка "закрытие" -->
        {{t "forms.edit-form.close-button-text"}}
      </button>
    </div>
  </div>
  <div class="field flexberry-validationsummary-container"> <!-- Вывод ошибок валидации -->
    <div class="sixteen wide">
      {{flexberry-validationsummary errors=(v-get validationObject "messages")}}
    </div>
  </div>
  <div class="field" data-test-neo-platform-gen-test-agregator-class-e-enum1Field="true">
    <label>{{t "forms.neo-platform-gen-test-agregator-class-e.enum1Field-caption"}}</label> <!-- Локализованный заголовок поля -->
    {{flexberry-dropdown ... }} <!-- Компонент для ввода значения поля -->
    {{flexberry-validationmessage error=(v-get validationObject "enum1Field" "message")}} <!-- Вывод ошибок валидации конкретного поля -->
  </div>
  <div class="field">
    ...
  </div>
  ...
</form>
```
{% endraw %}

## Вычитка данных

Вычитка данных для формы редактирования осуществляется в [базовом роуте `EditFormRoute`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormRoute.html). В общем случае вычитывается [модель по проекции](efd3_model.html).


## Cохранение данных
Сохранения данных для формы создания и редактирования осуществляется в [базовом контроллере `EditFormController`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html).

Алгоритм сохранения следующий:

* Если у текущей модели есть агрегатор (например, такое возможно в [офлайн-режиме]()), то вызывается сохранение соответствующего агрегатора.
* Если у текущей модели есть детейлы, которые были изменены, то происходит [Batch Update](https://devblogs.microsoft.com/odata/all-in-one-with-odata-batch/).
* Если у текущей модели нет детейлов или они не были изменены, то происходит отправка сведений об изменённых полях модели на сервер (+ отправляются мастеровые поля).

Например, при первом сохранении модели на сервер ушёл POST-запрос следующего содержания:

```json
// Поля аудита.
CreateTime: "2021-01-27T13:30:46.483Z"
Creator: "userName"
EditTime: "2021-01-27T13:30:46.483Z"
Editor: "userName"

// Собственные поля модели.
DoubleField: 1
ParentField: 3
StringField: "2"

// Мастеровое поле.
MyMaster@odata.bind: "NeoPlatformGenTestMasterForChild1s(a1bbe458-9beb-48e3-873c-6583ceeb55de)"

// Идентификатор модели.
__PrimaryKey: "e536452e-e69d-4fc5-989e-0a7c864e3029"
```

После изменения значения поля `StringField` и сохранения формы на сервер ушёл PATCH-запрос следующего содержания:

```json
// Поля аудита.
EditTime: "2021-01-27T13:33:47.054Z"

// Собственные поля модели.
StringField: "новое"

// Мастеровое поле.
MyMaster@odata.bind: "NeoPlatformGenTestMasterForChild1s(a1bbe458-9beb-48e3-873c-6583ceeb55de)"

// Идентификатор модели.
__PrimaryKey: "e536452e-e69d-4fc5-989e-0a7c864e3029"
```

После изменения собственных полей модели и добавлении детейлов происходит [Batch Update](https://devblogs.microsoft.com/odata/all-in-one-with-odata-batch/), в теле которого следующее (здесь убраны лишние для восприятия сведения):

```json
// Обновление полей модели-агрегатора.
PATCH http://localhost:6500/odata/NeoPlatformGenTestChild1s(e536452e-e69d-4fc5-989e-0a7c864e3029) HTTP/1.1
{"__PrimaryKey":"e536452e-e69d-4fc5-989e-0a7c864e3029","StringField":"изменено","EditTime":"2021-01-27T13:47:11.866Z","ParentField":33,"MyMaster@odata.bind":"NeoPlatformGenTestMasterForChild1s(a1bbe458-9beb-48e3-873c-6583ceeb55de)"}

// Создание детейла первого типа.
POST http://localhost:6500/odata/NeoPlatformGenTestDetail2ForChild1s HTTP/1.1
{"__PrimaryKey":"a59fda3f-431c-49ee-99f2-99d4b6850bb7","CreateTime":"2021-01-27T13:47:11.866Z","Creator":"userName","EditTime":"2021-01-27T13:47:11.866Z","Editor":"userName","IntFieldWithValue":28,"Child1@odata.bind":"NeoPlatformGenTestChild1s(e536452e-e69d-4fc5-989e-0a7c864e3029)"}

// Создание первого детейла второго типа.
POST http://localhost:6500/odata/NeoPlatformGenTestDetail1ForChild1s HTTP/1.1
{"__PrimaryKey":"70d72d3b-482d-4604-bef1-6afae24a83d3","CreateTime":"2021-01-27T13:47:11.866Z","Creator":"userName","EditTime":"2021-01-27T13:47:11.866Z","Editor":"userName","IntFieldWithValue1":122,"Child1@odata.bind":"NeoPlatformGenTestChild1s(e536452e-e69d-4fc5-989e-0a7c864e3029)"}

// Создание второго детейла второго типа.
POST http://localhost:6500/odata/NeoPlatformGenTestDetail1ForChild1s HTTP/1.1
{"__PrimaryKey":"15ca1447-3b61-422e-9255-fe9241572ea1","CreateTime":"2021-01-27T13:47:11.866Z","Creator":"userName","EditTime":"2021-01-27T13:47:11.866Z","Editor":"userName","IntFieldWithValue1":31,"Child1@odata.bind":"NeoPlatformGenTestChild1s(e536452e-e69d-4fc5-989e-0a7c864e3029)"}

// Запросы на получение созданных сущностей.
GET http://localhost:6500/odata/NeoPlatformGenTestChild1s(e536452e-e69d-4fc5-989e-0a7c864e3029)?$expand=MyMaster($select=__PrimaryKey,__PrimaryKey) HTTP/1.1

GET http://localhost:6500/odata/NeoPlatformGenTestDetail2ForChild1s(a59fda3f-431c-49ee-99f2-99d4b6850bb7)?$expand=Child1($select=__PrimaryKey,__PrimaryKey) HTTP/1.1

GET http://localhost:6500/odata/NeoPlatformGenTestDetail1ForChild1s(70d72d3b-482d-4604-bef1-6afae24a83d3)?$expand=Child1($select=__PrimaryKey,__PrimaryKey) HTTP/1.1

GET http://localhost:6500/odata/NeoPlatformGenTestDetail1ForChild1s(15ca1447-3b61-422e-9255-fe9241572ea1)?$expand=Child1($select=__PrimaryKey,__PrimaryKey) HTTP/1.1
```

## Пессимистические блокировки

Сервис блокировок предназначен для удобной реализации механизма пессимистических блокировок. Он используется, например, когда требуется защитить некоторый объект данных при редактировании пользователем от изменения другими пользователями в это же время.

Установка блокировки на редактируемый на форме объект происходит в [методе роута `beforeModel`](https://api.emberjs.com/ember/3.1/classes/Route/methods/beforeModel?anchor=beforeModel), а снятие - на [событие роута `willTransition`](https://api.emberjs.com/ember/3.1/classes/Route/events/willTransition?anchor=willTransition), если задана настройка снятия блокировок при закрытии формы.

Если пользователь пытается открыть на редактирование объект, для которой установлена блокировка, то пользователю форма будет открыта в режиме "только для чтения" или произойдёт возврат на родительский роут. Это поведение определяется настройками сервиса блокировок и может быть [переопределено](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/lock-route.js) в роуте формы редактирования:

```javascript
import EditFormRoute from 'ember-flexberry/routes/edit-form';

export default EditFormRoute.extend({
  ...
  openReadOnly(lockUserName) {
    return new Ember.RSVP.Promise((resolve) => {
      let answer = confirm(`Объект заблокирован пользователем: '${lockUserName}'. Открыть только на чтение?`);
      resolve(answer);
    });
  },
  ...
});
```

Также можно программно переопределить [метод, определяющий, следует ли снимать блокировку при закрытии формы](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/lock-route.js).

```javascript
import EditFormRoute from 'ember-flexberry/routes/edit-form';

export default EditFormRoute.extend({
  ...
  unlockObject() {
    return new Ember.RSVP.Promise((resolve) => {
      let answer = confirm(`Снять блокировку с объекта?`);
      resolve(answer);
    });
  },
  ...
});
```

{% include note.html content="Текущий пользователь, осуществляющий блокировку объекта, определяется через метод `getCurrentUserName` [сервиса `user`](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/services/user.js). По умолчанию данный метод возвращает пользователя с именем `userName`. Соответственно, для реализации блокировок необходимо переопределить данный метод." %}

### Настройки блокировок

Конфигурация сервиса блокировок устанавливается в файле `environment.js`.
Пример настроек сервиса блокировок в приложении Flexberry Ember представлен ниже.

```javascript
'use strict';

module.exports = function(environment) {
  ...
  let ENV = {
    ...
    APP: {
      ...
      // Lock settings.
     lock: {
        enabled: true,
        openReadOnly: true,
        unlockObject: true,
      }
    }
  };
  ...
  return ENV;
};
```

* `enabled` - флаг, определяющий, включён ли сервис блокировок.
* `openReadOnly` - флаг, определяющий, следует ли открывать форму с заблокированным объектом в режиме "только для чтения".
* `unlockObject` - флаг, определяющий, следует ли снимать блокировку с объекта после закрытия формы редактирования.

## Кастомизация логики сохранения на форме редактирования

Существует ряд возможностей кастомизировать логику сохранения на форме редактирования.

1. Переопределение методов [базового контроллера формы редактирования `EditFormController`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html), вызываемых при сохранении:

* [`onSaveActionStarted`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_onSaveActionStarted.) - вызывается перед началом сохранения.
* [`onSaveActionFulfilled`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_onSaveActionFulfilled.) - вызывается после успешного завершения сохранения.
* [`onSaveActionRejected`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_onSaveActionRejected.) - вызывается после отказа при сохранении.
* [`onSaveActionAlways`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_onSaveActionAlways.) - вызывается после сохранения (не важно, успешного или нет).


2. Переопределение обработчиков нажатия кнопок [базового контроллера формы редактирования `EditFormController`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html):

* [`actions.save`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_actions.save) - обработчик нажатия кнопки "Сохранить".
* [`actions.saveAndClose`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_actions.saveAndClose) - обработчик нажатия кнопки "Сохранить и закрыть".

```javascript
// app/controllers/your-controller.js
...
actions: {
  ...
  save() {
    if (confirm('Вы уверены, что хотите сохранить изменения?')) {
      this.save();
    }
  }
  ...
}
...
onSaveActionFulfilled() {
  alert('Сохранение прошло успешно!');
}
...
onSaveActionRejected() {
  alert('Ошибка сохранения!');
}
...
```

{% include note.html content="Возможно также переопределение самого метода сохранения [`save`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html#method_save), однако в общем случае этого лучше не делать." %}

## Несколько списков на форме редактирования

На форме редактирования можно расположить несколько [списков, представленных компонентом ObjectListViewComponent](), [пример такой формы есть на тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/ember-flexberry-dummy-multi-list-user-edit/new).

Настройка формы редактирования аналогична [настройке, когда на списковой форме располагаются несколько списков](efd3_listform.html). Для этого нужно в [роуте](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) соответствующей формы редактирования использовать специальные [миксины](https://api.emberjs.com/ember/3.1/classes/Mixin) [MultiListRouteMixin](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/multi-list-route.js) и [MultiListModelEditMixin](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/multi-list-model-edit.js), после чего корректно задать `multiListSettings` и `developerUserSettings`.

```js
import EditFormRoute from 'ember-flexberry/routes/edit-form';
import ListParameters from 'ember-flexberry/objects/list-parameters';
import MultiListRoute from 'ember-flexberry/mixins/multi-list-route';
import MultiListModelEdit from 'ember-flexberry/mixins/multi-list-model-edit';

export default EditFormRoute.extend(MultiListRoute, MultiListModelEdit, {
  modelProjection: 'ApplicationUserE',
  developerUserSettings: { MultiUserListOnEdit: {}, MultiUserList2OnEdit: {}, MultiSuggestionListOnEdit: {}, MultiHierarchyListOnEdit: {} },
  modelName: 'ember-flexberry-dummy-application-user',

  init() {
    this._super(...arguments);

    this.set('multiListSettings.MultiUserListOnEdit', new ListParameters({
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiUserListOnEdit',
      modelName: 'ember-flexberry-dummy-application-user',
      projectionName: 'ApplicationUserL',
      editFormRoute: 'ember-flexberry-dummy-application-user-edit'
    }));

    this.set('multiListSettings.MultiUserList2OnEdit', new ListParameters({
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiUserList2OnEdit',
      modelName: 'ember-flexberry-dummy-application-user',
      projectionName: 'ApplicationUserL',
      editFormRoute: 'ember-flexberry-dummy-application-user-edit'
    }));

    this.set('multiListSettings.MultiSuggestionListOnEdit', new ListParameters({
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiSuggestionListOnEdit',
      modelName: 'ember-flexberry-dummy-suggestion',
      projectionName: 'SuggestionL',
      editFormRoute: 'ember-flexberry-dummy-suggestion-edit',
      exportExcelProjection: 'SuggestionL'
    }));

    this.set('multiListSettings.MultiHierarchyListOnEdit', new ListParameters({
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiHierarchyListOnEdit',
      modelName: 'ember-flexberry-dummy-suggestion-type',
      projectionName: 'SuggestionTypeL',
      editFormRoute: 'ember-flexberry-dummy-suggestion-type-edit',
      inHierarchicalMode: true,
      hierarchicalAttribute: 'parent'
    }));
  },
});
```

В [контроллере](https://guides.emberjs.com/v3.1.0/controllers/) формы редактирования нужно использовать специальный [миксин](https://api.emberjs.com/ember/3.1/classes/Mixin) [MultiListControllerMixin](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/multi-list-controller.js)

```js
import EditFormController from 'ember-flexberry/controllers/edit-form';
import MultiListController from 'ember-flexberry/mixins/multi-list-controller';
import EditFormControllerOperationsIndicationMixin from 'ember-flexberry/mixins/edit-form-controller-operations-indication';

export default EditFormController.extend(EditFormControllerOperationsIndicationMixin, MultiListController, {
  parentRoute: 'ember-flexberry-dummy-multi-list',
  getCellComponent: null,
});

```

[Шаблон](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/) такой формы редактирования также требуется оформить особым образом. Настройки списков берутся из соответствующих `settings`, также дополнительно нужно пробросить некоторые action'ы.

{% raw %}
```hbs
{{flexberry-error error=error}}
<h3 class="ui header">{{t "forms.ember-flexberry-dummy-application-user-edit.caption"}}</h3>
<form class="ui form flexberry-vertical-form" role="form">
  {{ui-message
    type="success"
    closeable=true
    visible=showFormSuccessMessage
    caption=formSuccessMessageCaption
    message=formSuccessMessage
    onShow=(action "onSuccessMessageShow")
    onHide=(action "onSuccessMessageHide")
  }}
  {{ui-message
    type="error"
    closeable=true
    visible=showFormErrorMessage
    caption=formErrorMessageCaption
    message=formErrorMessage
    onShow=(action "onErrorMessageShow")
    onHide=(action "onErrorMessageHide")
  }}
  
  ...

  <hr/>
    <h3>{{t 'forms.ember-flexberry-dummy-multi-list.caption'}}</h3>
    <div class="row">
      {{#with multiListSettings.MultiUserListOnEdit as |settings|}}
        {{flexberry-objectlistview
          modelName=settings.modelName
          modelProjection=settings.modelProjection
          editFormRoute=settings.editFormRoute
          content=settings.model
          createNewButton=true
          refreshButton=true
          sorting=settings.computedSorting
          orderable=true
          sortByColumn=(action "sortByColumn")
          addColumnToSorting=(action "addColumnToSorting")
          beforeDeleteAllRecords=(action "beforeDeleteAllRecords")
          pages=settings.pages
          perPageValue=settings.perPageValue
          perPageValues=settings.perPageValues
          recordsTotalCount=settings.recordsTotalCount
          hasPreviousPage=settings.hasPreviousPage
          hasNextPage=settings.hasNextPage
          previousPage=(action "previousPage")
          gotoPage=(action "gotoPage")
          nextPage=(action "nextPage")
          componentName=settings.componentName
        }}
      {{/with}}
      <h3>{{t "forms.ember-flexberry-dummy-multi-list.multi-edit-form"}}</h3>
      {{#with multiListSettings.MultiUserList2OnEdit as |settings|}}
        {{flexberry-objectlistview
          modelName=settings.modelName
          modelProjection=settings.modelProjection
          editFormRoute=settings.editFormRoute
          content=settings.model
          createNewButton=true
          refreshButton=true
          sorting=settings.computedSorting
          orderable=true
          sortByColumn=(action "sortByColumn")
          addColumnToSorting=(action "addColumnToSorting")
          beforeDeleteAllRecords=(action "beforeDeleteAllRecords")
          pages=settings.pages
          perPageValue=settings.perPageValue
          perPageValues=settings.perPageValues
          recordsTotalCount=settings.recordsTotalCount
          hasPreviousPage=settings.hasPreviousPage
          hasNextPage=settings.hasNextPage
          previousPage=(action "previousPage")
          gotoPage=(action "gotoPage")
          nextPage=(action "nextPage")
          componentName=settings.componentName
        }}
      {{/with}}
      <h3>{{t "forms.ember-flexberry-dummy-multi-list.multi-edit-form"}}</h3>
      {{#with multiListSettings.MultiSuggestionListOnEdit as |settings|}}
        {{flexberry-objectlistview
          editFormRoute=settings.editFormRoute
          showCheckBoxInRow=true
          modelName=settings.modelName
          modelProjection=settings.modelProjection
          content=settings.model
          createNewButton=true
          enableFilters=true
          filters=settings.filters
          filterButton=true
          filterByAnyMatch=(action 'filterByAnyMatch')
          filterText=settings.filter
          refreshButton=true
          exportExcelButton=true
          sorting=settings.computedSorting
          orderable=true
          sortByColumn=(action "sortByColumn")
          addColumnToSorting=(action "addColumnToSorting")
          beforeDeleteAllRecords=(action "beforeDeleteAllRecords")
          applyFilters=(action "applyFilters")
          resetFilters=(action "resetFilters")
          pages=settings.pages
          perPageValue=settings.perPageValue
          perPageValues=settings.perPageValues
          recordsTotalCount=settings.recordsTotalCount
          hasPreviousPage=settings.hasPreviousPage
          hasNextPage=settings.hasNextPage
          previousPage=(action "previousPage")
          gotoPage=(action "gotoPage")
          nextPage=(action "nextPage")
          componentName=settings.componentName
          showDeleteMenuItemInRow=true
          deleteButton=true
        }}
      {{/with}}
      <h3>{{t "forms.ember-flexberry-dummy-multi-list.multi-edit-form"}}</h3>
      {{#with multiListSettings.MultiHierarchyListOnEdit as |settings|}}
        {{flexberry-objectlistview
          content=settings.model
          modelName=settings.modelName
          modelProjection=settings.modelProjection
          editFormRoute=settings.editFormRoute
          orderable=false
          componentName=settings.componentName
          beforeDeleteAllRecords=(action "beforeDeleteAllRecords")
          colsConfigButton=false
          disableHierarchicalMode=false
          showCheckBoxInRow=true
          pages=settings.pages
          perPageValue=settings.perPageValue
          perPageValues=settings.perPageValues
          recordsTotalCount=settings.recordsTotalCount
          hasPreviousPage=settings.hasPreviousPage
          hasNextPage=settings.hasNextPage
          previousPage=(action "previousPage")
          gotoPage=(action "gotoPage")
          nextPage=(action "nextPage")
          availableCollExpandMode=true
          inHierarchicalMode=settings.inHierarchicalMode
          hierarchicalAttribute=settings.hierarchicalAttribute
          inExpandMode=settings.inExpandMode
        }}
      {{/with}}
    </div>
</form>
```
{% endraw %}