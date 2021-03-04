---
title: Списковые формы Flexberry Ember
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_listform.html
lang: ru
summary: Предназначение, структура, функции, особенности списковых форм Flexberry Ember.
---

## Описание

Списковая форма предназначена для отображения списка объектов. Создание и редактирование объектов чаще всего осуществляется на [формах создания и редактирования объектов](efd3_editform.html).

Чтобы создать списковую форму, необходимо определить соответствующие [роуты](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/), [контроллеры](https://guides.emberjs.com/v3.1.0/controllers/) и [шаблоны](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/).

## Устройство роутов

Для списковой формы по умолчанию [роут](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) [генерируется](efd3_generated-app-structure.html) по адресу: `app/routes/<название-модели>-l.js`.
Генерируемый роут списковой формы выглядит следующим образом:

```js
import ListFormRoute from 'ember-flexberry/routes/list-form'; // Базовый роут списковой формы.
import { computed } from '@ember/object';
export default ListFormRoute.extend({
  modelProjection: 'MasterForChild1L', // Название проекции, где определены отображаемые на списке свойства модели.
  modelName: 'neo-platform-gen-test-master-for-child1', // Названия модели, чьи свойства будут отображены на списке.

  developerUserSettings: computed(function() {
    return { NeoPlatformGenTestMasterForChild1L: {} }
  }),
});
```

`developerUserSettings` - предопределённые программистом настройки для пользователя (см. [сервис текущего пользователя]()), определяющие настройки отображения списка (отображаемые столбцы, ширина столбцов, порядок сортировки). Формат настройки следующий:

{% raw %}
```json
{
    <componentName>: {
        <settingName>: {
            colsOrder: [ { propName :<colName>, hide: true|false }, ... ],
            sorting: [{ propName: <colName>, direction: "asc"|"desc" }, ... ],
            colsWidths: [ <colName>:<colWidth>, ... ],
        },
        ...
    },
    ...
}
```
{% endraw %}

* `componentName` - имя компонента, для которого задаётся настройка (в примере выше это "NeoPlatformGenTestMasterForChild1L"),
* `settingName` - название настройки (если указана пустая строка, то данная настройка будет использована как настройка по умолчанию),
* `colsOrder` - порядок отображения свойств на списке определяется порядком указанных имён свойств модели `colName`, при этом путём указания флага `hide` есть возможность скрыть отображение некоторых свойств на списке,
* `sorting`- порядок сортировки списка определяется порядком указанных имён свойств модели `colName`, направление задаётся значением свойства `direction` (`asc` - по возрастанию, `desc` - по убыванию).
* `colsWidths` - ширина столбцов свойств указывается как соответствие имени свойства модели `colName` и его ширины `colWidth`.

[Базовый роут списковой формы ListFormRoute](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormRoute.html) предоставляет ряд методов, позволяющих кастомизировать логику загрузки данных (списка) на форму:

* [`onModelLoadingStarted`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormRoute.html#method_onModelLoadingStarted.) - вызывается при начале загрузки модели (списка).
* [`onModelLoadingFulfilled`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormRoute.html#method_onModelLoadingFulfilled.) - вызывается при успешном окончании загрузки модели (списка).
* [`onModelLoadingRejected`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormRoute.html#method_onModelLoadingRejected.) - вызывается при отказе во время загрузки модели (списка).
* [`onModelLoadingAlways`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormRoute.html#method_onModelLoadingAlways.) - вызывается при окончании загрузки модели (списка), независимо от исхода загрузки.

```javascript
onModelLoadingRejected(errorData, transition) {
    alert('Model loading operation failed!');
}
```

## Устройство контроллеров

Для списковой формы по умолчанию [контроллер](https://guides.emberjs.com/v3.1.0/controllers/) [генерируется](efd3_generated-app-structure.html) по адресу: `app/controllers/<название-модели>-l.js`.
Генерируемый контроллер списковой формы выглядит следующим образом и содержит указание на соответствующий роут [формы редактирования объектов](efd3_editform.html), где будет проходить редактирование объектов, отображаемых на списке:

```js
import ListFormController from 'ember-flexberry/controllers/list-form'; // Базовый контроллер списковой формы.

export default ListFormController.extend({
  editFormRoute: 'neo-platform-gen-test-master-for-child1-e' // Роут соответствующей формы редактирования.
});
```

[Базовый контроллер списковой формы ListFormController](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormController.html) предоставляет ряд методов, позволяющих кастомизировать логику удаления элементов списка:

* [`onDeleteActionStarted`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormController.html#method_onDeleteActionStarted.) - вызывается при начале удаления элементов списка.
* [`onDeleteActionFulfilled`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormController.html#method_onDeleteActionFulfilled.) - вызывается при успешном окончании удаления элементов списка.
* [`onDeleteActionRejected`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormController.html#method_onDeleteActionRejected.) - вызывается при отказе во время удаления элементов списка.
* [`onDeleteActionAlways`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ListFormController.html#method_onDeleteActionAlways.) - вызывается при окончании удаления элементов списка, независимо от исхода операции.

```javascript
onDeleteActionRejected() {
    alert('Delete operation failed!');
}
```

## Шаблоны списковых форм

Типичный [шаблон](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/) для списковой формы выглядит следующим образом:

{% raw %}
```hbs
{{flexberry-error error=error}} <!-- Компонент для отображения ошибок -->
<h3>{{t "forms.neo-platform-gen-test-master-for-child1-l.caption"}}</h3> <!-- Локализованный заголовок формы -->
<div class="row"> <!-- Компонент flexberry-objectlistview для отображения списка -->
  {{flexberry-objectlistview 
    modelName=modelName
    modelProjection=modelProjection
    editFormRoute=editFormRoute
    content=model
    createNewButton=true
    refreshButton=true
    sorting=computedSorting
    orderable=true
    showCheckBoxInRow=true
    sortByColumn=(action "sortByColumn")
    addColumnToSorting=(action "addColumnToSorting")
    beforeDeleteAllRecords=(action "beforeDeleteAllRecords")
    pages=pages
    perPageValue=perPageValue
    perPageValues=perPageValues
    recordsTotalCount=recordsTotalCount
    hasPreviousPage=hasPreviousPage
    hasNextPage=hasNextPage
    previousPage=(action "previousPage")
    gotoPage=(action "gotoPage")
    nextPage=(action "nextPage")
    componentName="NeoPlatformGenTestMasterForChild1L"
  }}
</div>
```
{% endraw %}

Для отображения списка используется специальный [компонент flexberry-objectlistview]().

## Как происходит вычитка данных для списковых форм

При открытии списковой формы происходит обращение к бэкэнду за данными. При построении запроса учитываются:

* название [модели и проекции](efd3_model.html), что определяет какая сущность и какие её свойства будут отопражаться на списке,
* настройка количества элементов на странице (поскольку список может содержать большое колтчество записей, одномоментно отображается лишь одна страница),
* номер текущей отображаемой страницы (после наложения всех фильтров и сортировок записи списка делятся на страницы по N записей на странице (N определяется [настройками пейджинга компонента]())),
* настройки сортировки (порядок сортировки столбцов и направления сортировки (по возрастанию или убыванию)),
* настройки фильтрации и ограничений (возвращаемые данные должны удовлетворять заданным фильтрам и ограничениям),
* настройки работы в режиме иерархии (списки могут быть также иерархические, в этом случае сразу должны быть подтянуты сведения о дочерних элементах списка).

## Несколько списков на одной форме

Существует возможность расположить несколько [списков]() на списковой форме. [Пример есть на тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/ember-flexberry-dummy-multi-list).

Для этого нужно в [роуте](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) соответствующей списковой формы использовать специальные [миксины](https://api.emberjs.com/ember/3.1/classes/Mixin) [MultiListRouteMixin](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/multi-list-route.js) и [MultiListModelMixin](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/multi-list-model.js), после чего корректно задать `multiListSettings` и `developerUserSettings`.

```js
import ListFormRoute from 'ember-flexberry/routes/list-form';
import MultiListRoute from 'ember-flexberry/mixins/multi-list-route';
import MultiListModel from 'ember-flexberry/mixins/multi-list-model';
import ListParameters from 'ember-flexberry/objects/list-parameters';

export default ListFormRoute.extend(MultiListRoute, MultiListModel, {
  init() {
    this._super(...arguments);

    this.set('multiListSettings.MultiUserList', new ListParameters({ // Настройки для списка пользователей.
      objectlistviewEvents: this.get('objectlistviewEvents'), // Сервис для обработки событий компонента ObjectListViewComponent.
      componentName: 'MultiUserList', // Название компонента со списком.
      modelName: 'ember-flexberry-dummy-application-user', // Имя модели, чьи сущности указаны на списке.
      projectionName: 'ApplicationUserL', // Имя проекции, по которой отображается список.
      editFormRoute: 'ember-flexberry-dummy-multi-list-user-edit', // Роут формы редактирования элементов списка.
      advLimitButton: true // Отображать у списка кнопку задания ограничения.
    }));

    this.set('multiListSettings.MultiUserList2', new ListParameters({ // Настройки для второго списка пользователей.
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiUserList2',
      modelName: 'ember-flexberry-dummy-application-user',
      projectionName: 'ApplicationUserL',
      editFormRoute: 'ember-flexberry-dummy-multi-list-user-edit',
      advLimitButton: true
    }));

    this.set('multiListSettings.MultiSuggestionList', new ListParameters({ // Настройки для списка предложений.
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiSuggestionList',
      modelName: 'ember-flexberry-dummy-suggestion',
      projectionName: 'SuggestionL',
      editFormRoute: 'components-examples/flexberry-objectlistview/ember-flexberry-dummy-suggestion-multi-list-edit',
      exportExcelProjection: 'SuggestionL' // Задание на списке настроек для экспорта в эксель.
    }));

    this.set('multiListSettings.MultiHierarchyList', new ListParameters({ // Настройки для списка предложений, находящегося в режиме иерархи.
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'MultiHierarchyList',
      modelName: 'ember-flexberry-dummy-suggestion-type',
      projectionName: 'SuggestionTypeL',
      editFormRoute: 'ember-flexberry-dummy-suggestion-type-edit',
      inHierarchicalMode: true, // Переведение списка в режим работы с иерархией.
      hierarchicalAttribute: 'parent', // Атрибут, по которому на списке строится иерархия.
      hierarchyPaging: true // Использовать пэйджинг на иерархии.
    }));
  },

  developerUserSettings: { MultiUserList: {}, MultiUserList2: {}, MultiSuggestionList: {}, MultiHierarchyList: {} },
});
```

В [контроллере](https://guides.emberjs.com/v3.1.0/controllers/) списковой формы нужно использовать специальный [миксин](https://api.emberjs.com/ember/3.1/classes/Mixin) [MultiListControllerMixin](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/multi-list-controller.js)

```js
import ListFormController from 'ember-flexberry/controllers/list-form';
import MultiListController from 'ember-flexberry/mixins/multi-list-controller';

export default ListFormController.extend(MultiListController, {
});
```

[Шаблон](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/) такой списковой формы также требуется оформить особым образом. Настройки списков берутся из соответствующих `settings`, также дополнительно нужно пробросить некоторые action'ы.

{% raw %}
```hbs
{{flexberry-error error=error}}
<h3>{{t 'forms.ember-flexberry-dummy-multi-list.caption'}}</h3>
<div class="row">
  {{#with multiListSettings.MultiUserList as |settings|}}
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
      advLimitButton=true
      advLimitButton=settings.advLimitButton
    }}
  {{/with}}
  <h3>{{t "forms.ember-flexberry-dummy-multi-list.multi-edit-form"}}</h3>
  {{#with multiListSettings.MultiUserList2 as |settings|}}
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
      advLimitButton=settings.advLimitButton
    }}
  {{/with}}
  <h3>{{t "forms.ember-flexberry-dummy-multi-list.multi-edit-form"}}</h3>
  {{#with multiListSettings.MultiSuggestionList as |settings|}}
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
  {{#with multiListSettings.MultiHierarchyList as |settings|}}
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
      hierarchyPaging=settings.hierarchyPaging
    }}
  {{/with}}
</div>

```
{% endraw %}
