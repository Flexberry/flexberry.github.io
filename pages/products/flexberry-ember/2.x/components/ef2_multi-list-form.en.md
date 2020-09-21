---
title: Multiple lists on the form
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, OLV, setting
toc: true
permalink: en/ef2_multi-list-form.html
lang: en
autotranslated: true
hash: 2e6cd46822cde3391ea4c762598d58b93631dc90b267f2a5d1f03762e99034b2
summary: Work with multiple lists on the form
---

Starting with version ember-flexberry@2.2.0 (3.2.0) available the placement of a few components flexberry-objectlistview on the form.

## Customize list forms with multiple lists

1.You need to route the form to add mixins `multi-list-route` and `multi-list-model`:

```javascript
import ListFormRoute from 'ember-flexberry/routes/list-form';
import MultiListRoute from 'ember-flexberry/mixins/multi-list-route';
import MultiListModel from 'ember-flexberry/mixins/multi-list-model';

export default ListFormRoute.extend(MultiListRoute, MultiListModel, {
  ...
});
```

2.In the controller a form to add mixin `multi-list-controller`:

```javascript
import ListFormController from 'ember-flexberry/controllers/list-form';
import MultiListController from 'ember-flexberry/mixins/multi-list-controller';

export default ListFormController.extend(MultiListController, {
  ...
});
```

3.In roat of the form, in init, add the settings for the lists:

```javascript
import ListParameters from 'ember-flexberry/objects/list-parameters';
```

```javascript
  init() {
    this._super(...arguments);

    this.set('multiListSettings.ИмяКомпонентаСписка', new ListParameters({
      // Mandatory parameters. 
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'ИмяКомпонентаСписка',
      modelName: 'ИмяМоделиСписка',
      projectionName: 'ИмяПредставленияСписка',
      editFormRoute: 'РоутФормыРедактирования',
      
      // Optional parameters (any other list settings). 
      // Example for hierarchical mode: 
      inHierarchicalMode: true,
      hierarchicalAttribute: 'ИмяИерархическогоАтрибута'
    }));
    
    // Settings for the other lists, according to the first example. 
    ...
  },
  
  developerUserSettings: { ИмяКомпонентаСписка1: {}, ИмяКомпонентаСписка2: {}, ИмяКомпонентаСписка3: {}, ... },
```

4.In the template list to add a list:
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

<!-- Templates for other lists, for example first -->
...
```
## Customize edit form for works with multiple lists

1.You need to route the form to add mixins `multi-list-route` and `multi-list-model-edit`:

```javascript
import EditFormRoute from 'ember-flexberry/routes/edit-form';
import MultiListRoute from 'ember-flexberry/mixins/multi-list-route';
import MultiListModelEdit from 'ember-flexberry/mixins/multi-list-model-edit';

export default EditFormRoute.extend(MultiListRoute, MultiListModelEdit, {
  ...
});
```

2.In the controller a form to add mixin `multi-list-controller` and modify `getCellComponent`:

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

3.In roat of the form, in init, add the settings for the lists:

```javascript
import ListParameters from 'ember-flexberry/objects/list-parameters';
```

```javascript
  init() {
    this._super(...arguments);

    this.set('multiListSettings.ИмяКомпонентаСписка', new ListParameters({
      // Mandatory parameters. 
      objectlistviewEvents: this.get('objectlistviewEvents'),
      componentName: 'ИмяКомпонентаСписка',
      modelName: 'ИмяМоделиСписка',
      projectionName: 'ИмяПредставленияСписка',
      editFormRoute: 'РоутФормыРедактирования',
      
      // Optional parameters (any other list settings). 
      // Example for hierarchical mode: 
      inHierarchicalMode: true,
      hierarchicalAttribute: 'ИмяИерархическогоАтрибута'
    }));
    
    // Settings for the other lists, according to the first example. 
    ...
  },
  
  developerUserSettings: { ИмяКомпонентаСписка1: {}, ИмяКомпонентаСписка2: {}, ИмяКомпонентаСписка3: {}, ... },
```

4.In the template list to add a list:
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

<!-- Templates for other lists, for example first -->
...
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}