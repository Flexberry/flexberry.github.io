---
title: Несколько списков на форме
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, OLV, настройка
toc: true
permalink: ru/ef2_multi-list-form.html
lang: ru
summary: Работа с несколькими списками на форме
---

Начиная с версии ember-flexberry@2.2.0 (3.2.0) доступно размещение нескольких компонентов flexberry-objectlistview на форме.

## Настройка списковой формы для работы с несколькими списками

1.Необходимо в роут формы добавить миксины `multi-list-route` и `multi-list-model`:

```javascript
import ListFormRoute from 'ember-flexberry/routes/list-form';
import MultiListRoute from 'ember-flexberry/mixins/multi-list-route';
import MultiListModel from 'ember-flexberry/mixins/multi-list-model';

export default ListFormRoute.extend(MultiListRoute, MultiListModel, {
  ...
});
```

2.В контроллер формы добавить миксин `multi-list-controller`:

```javascript
import ListFormController from 'ember-flexberry/controllers/list-form';
import MultiListController from 'ember-flexberry/mixins/multi-list-controller';

export default ListFormController.extend(MultiListController, {
  ...
});
```

3.В роуте формы в init добавить настройки для списков:

```javascript
import ListParameters from 'ember-flexberry/objects/list-parameters';
```

```javascript
  init() {
    this._super(...arguments);

    this.set('multiListSettings.ИмяКомпонентаСписка', new ListParameters({
      // Обязательные параметры.
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'ИмяКомпонентаСписка',
      modelName: 'ИмяМоделиСписка',
      projectionName: 'ИмяПредставленияСписка',
      editFormRoute: 'РоутФормыРедактирования',
      
      // Необязательные параметры (любые другие параметры списка).
      // Например для иерархического режима:
      inHierarchicalMode: true,
      hierarchicalAttribute: 'ИмяИерархическогоАтрибута'
    }));
    
    // Настройки для остальных списков, по примеру первого.
    ...
  },
  
  developerUserSettings: { ИмяКомпонентаСписка1: {}, ИмяКомпонентаСписка2: {}, ИмяКомпонентаСписка3: {}, ... },
```

4.В шаблоне списка добавить списки:
```hbs
<div class="row">
{% raw %}{{#with multiListSettings.ИмяКомпонентаСписка as |settings|}}
  {{flexberry-objectlistview
    modelName=settings.modelName
    modelProjection=settings.modelProjection
    editFormRoute=settings.editFormRoute
    content=settings.model
    createNewButton=true
    refreshButton=true
    orderable=true
    sorting=settings.computedSorting
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
{{/with}}{% endraw %}
</div>

<!-- Шаблоны для остальных списков по примеру первого -->
...
```
## Настройка формы редактирования для работы с несколькими списками

1.Необходимо в роут формы добавить миксины `multi-list-route` и `multi-list-model-edit`:

```javascript
import EditFormRoute from 'ember-flexberry/routes/edit-form';
import MultiListRoute from 'ember-flexberry/mixins/multi-list-route';
import MultiListModelEdit from 'ember-flexberry/mixins/multi-list-model-edit';

export default EditFormRoute.extend(MultiListRoute, MultiListModelEdit, {
  ...
});
```

2.В контроллер формы добавить миксин `multi-list-controller` и доработать `getCellComponent`:

```javascript
import EditFormController from 'ember-flexberry/controllers/edit-form';
import MultiListController from 'ember-flexberry/mixins/multi-list-controller';

export default EditFormController.extend(MultiListController, {
  ...
  getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);
    if (model !== null) {
      ...
      if (model.modelName === 'ТипСписка1'
        || model.modelName === 'ТипСписка2'
        || model.modelName === 'ТипСписка3') {
          cellComponent.componentName = undefined;
      }
    }

    return cellComponent;
  }
});
  ...
});
```

3.В роуте формы в init добавить настройки для списков:

```javascript
import ListParameters from 'ember-flexberry/objects/list-parameters';
```

```javascript
  init() {
    this._super(...arguments);

    this.set('multiListSettings.ИмяКомпонентаСписка', new ListParameters({
      // Обязательные параметры.
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'ИмяКомпонентаСписка',
      modelName: 'ИмяМоделиСписка',
      projectionName: 'ИмяПредставленияСписка',
      editFormRoute: 'РоутФормыРедактирования',
      
      // Необязательные параметры (любые другие параметры списка).
      // Например для иерархического режима:
      inHierarchicalMode: true,
      hierarchicalAttribute: 'ИмяИерархическогоАтрибута'
    }));
    
    // Настройки для остальных списков, по примеру первого.
    ...
  },
  
  developerUserSettings: { ИмяКомпонентаСписка1: {}, ИмяКомпонентаСписка2: {}, ИмяКомпонентаСписка3: {}, ... },
```

4.В шаблоне списка добавить списки:
```hbs
<div class="row">
{% raw %}{{#with multiListSettings.ИмяКомпонентаСписка as |settings|}}
  {{flexberry-objectlistview
    modelName=settings.modelName
    modelProjection=settings.modelProjection
    editFormRoute=settings.editFormRoute
    content=settings.model
    createNewButton=true
    refreshButton=true
    orderable=true
    sorting=settings.computedSorting
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
{{/with}}{% endraw %}
</div>

<!-- Шаблоны для остальных списков по примеру первого -->
...
```
