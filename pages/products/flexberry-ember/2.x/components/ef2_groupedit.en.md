---
title: Flexberry Groupedit
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, groupedit
toc: true
permalink: en/ef2_groupedit.html
lang: en
autotranslated: true
hash: ea1bfc565b815b084c724c160de52865f9f881cb20fcc72422e90bf9a3df618f
summary: Properties, implementation details, ability to sort and embedding of application components, the implementation in a separate roat
---

`flexberry-groupedit` is designed to work with [detaylari](fo_detail-associations-properties.html) on the edit form.

To add groupedit page, in the template you need to specify:

```hbs
{% raw %}{{flexberry-groupedit
  componentName="ordersGroupEdit"
  defaultSortingButton=true
  class="attached"
  modelProjection=modelProjection.attributes.orders
  content=model.orders
  readonly=readonly
  orderable=false
}}{% endraw %}
```

### Properties Flexberry Groupedit

Property | Brief description
:--------|:----------------
Properties `componentName` and `readonly`| Properties from the [basic control](ef2_controls.html).
`modelProjection`| Defines the view to be displayed.
`content`| Single records, an editable control.
`cellComponent`| Method that determines how the control component is edited.
`defaultSortingButton`| a Flag that determines whether to display button for setting the default sort. The default value is: true.
`orderable`| a Flag that determines whether to sort by columns in the component (if the flag value is changed to `true` need additional configuration, see below).
`showDeleteMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template FGE, `Удалить запись`. The default value is false.
`showEditMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template FGE, `Редактировать запись`. The default value is false.
`showDeleteButtonInRow`| Flag specifying to display a button `-` to delete the record in the browser and mobile template. The default value is false.
`singleColumnHeaderTitle`| Header for mobile view FGE, instead of column names.

The properties used to configure the edit in a separate router:

* `rowClickable`
* `rowClick`
* `editOnSeparateRoute`

## Features of the implementation

* Flexberry Groupedit consists of two components: `GroupeditToolbar` and `ObjectListView`.
* Property `class` applies to `ObjectListView`.

## Sort items

If the flag `orderable` to put a value `true`, then you also need to set:

```hbs
{% raw %}{{flexberry-groupedit
  ...
  orderable=true
  sortByColumn=(action "sortByColumn")
  addColumnToSorting=(action "addColumnToSorting")
}}{% endraw %}
```

* `sortByColumn` - action (action) of the controller that should be executed to sort by column.
* `addColumnToSorting` - action (action) of the controller that should be executed to add a sort by column.

Use the sorting in the template, if the controller template has been defined, the actions (action) with names `sortByColumn` and `addColumnToSorting`. Syntax `addColumnToSorting=(action \"addColumnToSorting\")` determines that you are using the ember closure action.

## The embedding of components in groupedit

Component `flexberry-groupedit` is a table in which the cells can be embedded on any component that inherits from [flexberry-base-component](ef2_controls.html).

For embedding components flexberry-groupedit `getCellComponent` finds a method in the current controller, and causes in the formation of each cell of the table.

PstrfgetCellComponent` method already defined in the base controller edit form (`ember-flexberry/controllers/edit-form.js`), his logic is directed to the embedding of components, depending on the type of data in the cell, and is as follows:

```javascript
// ... 
  getCellComponent: function(attr, bindingPath, modelClass) {
  var cellComponent = {
    componentName: 'flexberry-textbox',
    componentProperties: null
  };

  if (attr.kind === 'belongsTo') {
    cellComponent.componentName = 'flexberry-lookup';
    return cellComponent;
  }

  var modelAttr = !Ember.isNone(modelClass) ? Ember.get(modelClass, 'attributes').get(bindingPath) : null;
  if (attr.kind === 'attr' && modelAttr && modelAttr.type) {
    switch (modelAttr.type) {
      case 'boolean':
        cellComponent.componentName = 'flexberry-checkbox';
        break;
      case 'date':
        cellComponent.componentName = 'flexberry-datepicker';
        break;
      case 'file':
        cellComponent.componentName = 'flexberry-file';
        break;
      default:

        // Current cell type is possibly custom transform. 
        var modelAttrType = getOwner(this)._lookupFactory('transform:' + modelAttr.type);

        // Handle enums (extended from transforms/enum-base.js). 
        if (modelAttrType && modelAttrType.isEnum) {
          cellComponent.componentName = 'flexberry-dropdown';
          cellComponent.componentProperties = {
            items: modelAttrType.create().getAvailableValuesArray()
          };
        }

        break;
    }
  }

  return cellComponent;
}
```

If you applied the edit form you want to embed the application component in the cell groupedit, you will need to override the method `getCellComponent` in the application controller that inherits from the base controller edit form (`ember-flexberry/controllers/edit-form.js`).

```javascript
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  title: 'My edit form',

  //... 

  getCellComponent: function(attr, bindingPath, model) {
    if (...) {
      return {
        componentName: 'my-component',
        componentProperties: null
      };
    }

    if (...) {
      return {
        componentName: 'my-another-component',
        componentProperties: {
          myAnotherComponentProperty1: 'someValue',
          myAnotherComponentProperty2:  4815162342
        }
      };
    }

    return this._super(...arguments);
  }

  //... 
});
```

It is important that the embedded component inherits from basic component (`ember-flexberry/components/flexberry-base-component.js`), and if the overridden method in the component `init`, at the end of application initialization, must be called by the initialization method from the base class, since there is a defined logic that initializes component properties from the object passed in the return value of the method `getCellComponent` key `componentProperties`.

## Edit detailov in a separate roat

Configuring templates and models aggregator and detail, serializer aggregator and controller of detail for enabling editing in a separate the router is a special case of component usage (for example, when working on mobile devices).

This should take into account some features of the application:

* Changes in the aggregator and detalaj persisted in the database only when you click `Сохранить` on the form of the aggregator.
* On the page of detail there are only two buttons: `Сохранить` and `Закрыть` (on the form there is no button to roll back the state of detail at the time of entry on the form).
* If you press the button `Сохранить` on the page of detail return to the aggregator page without saving changes to the database.

### Customize template aggregator and router

In the [template](ef2_template.html) edit form of the aggregator, the control `flexberry-groupedit` to define the following properties:

```hbs
{% raw %}
{{flexberry-groupedit
  ...
  rowClickable=true
  rowClick='rowClick'
  editOnSeparateRoute=true
}}{% endraw %}
```

* `rowClickable` - a flag that determines whether to treat depression is on the line. Defaults to the value `false` to edit detail in a separate roat need to ask `true`.
* `rowClick` - the action to perform when clicking the line. The default is set to "rowClick", to edit detail in a separate router need to act (action) with the given property name was defined in [controller](ef2_controller.html) or [roat](ef2_route.html) (`rowClick` defined in the base route edit form).

{% include note.html content="Current handler method `rowClick` implemented in such a way that if detail saved, then you will be redirected to Routh as `modelName/:id`, and if not saved, then `modelName.new` (respectively, [router](ef2_router.html) must be configured to divert could pass properly)." %}

* `editOnSeparateRoute` - a flag that determines whether edit detail in a separate route. Defaults to the value `false`. When you set the value to true changes the appearance `flexberry-groupedit`: disables the ability to edit directly in the control, clicking the button `Добавить` now a new record is created and is immediately redirected to route editing.

### Setting template detail

To be able to edit detail in separate routes, the appropriate form must be created.

If it is assumed that the edit form of detail can be used not only with the forms of the aggregator, but the list form of datalow, to customize the display of buttons `Сохранить`, `Удалить`, `Закрыть` you can use the conditions presented in article [edit Form and creation](ef2_edit-form.html).

### Controller configuration of detail

Controller edit form of detail must inherit from `detail-edit-form` is `edit-form`. In `detail-edit-form` there is additional logic that organizes the correct interaction between the forms of the aggregator and detail. If it is determined that the user has come in the form of detail not with the forms of the aggregator, it will execute the logic base controller `edit-form`.

```javascript
import DetailEditFormController from 'ember-flexberry/controllers/detail-edit-form';

export default DetailEditFormController;
```

### Configuring aggregator models and detail

In the description [models](efd2_model.html) Agregator important to check that bearing dealova [inverse-link](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations).

```javascript
var Model = BaseModel.extend({
  ...
  orders: DS.hasMany('order', { inverse: 'employee', async: false }),
});
```

To describe the model detail important to check that indicated [inverse-link](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations) on the aggregator.

```javascript
var Model = BaseModel.extend({
  ...
  employee: DS.belongsTo('employee', { inverse: 'orders', async: false })
});
```

### Configuring the aggregator

It is important that [serializer](efd2_serializer.html) for the aggregator and detail have been configured correctly.

### Additional features of the implementation

* Base controller for the edit form has a property `returnToAgregatorRoute`, which determines whether to adjust for a potential return to the previous route aggregator. By default, the base controller value `false`. In the base controller detail this value is overridden at `true` (if necessary, in the application controller detail to change it back to `false` to prevent the return). Proofreading this flag happens in the roat [setupController](http://devdocs.io/ember/classes/ember.route#method_setupController).
* For organization to preserve information between ranting and aggregator and detail used special service `detail-interaction`. This service is not intended for use in applied projects.
* The basic route edit form myxinidae property `newRoutePath` where you determine how the router model determines the route for the new entry. Now the method is implemented as `currentPath '.new'`. Potentially this method can be overridden in the application route.

### Edit detailov in a separate router in Flexberry Groupedit preserving

There is a mode `flexberry-groupedit`, wherein the editing of detail occurs on a separate route, thus, in the transition from get the aggregator to route detail, saves the aggregator, and the transition back is the preservation of detail.

Configuration of this option is similar to the described above method. In the template of the control you need to set one additional flag `saveBeforeRouteLeave`.

```hbs
{% raw %}
{{flexberry-groupedit
  ...
  rowClickable=true
  rowClick='rowClick'
  editOnSeparateRoute=true
  saveBeforeRouteLeave=true
}}{% endraw %}
```

`saveBeforeRouteLeave` - a flag that determines whether to save the current model, and the transitions between the aggregator and detaila. Defaults to the value `false`.

About how to operate the buttons on this dyelovoi the form described in the article [edit Form and creation](ef2_edit-form.html).

## Computable properties in getCellComponent

To be able to modify the properties of embedded in a cell of the component, the component should use a mixin [DynamicPropertiesMixin](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/DynamicPropertiesMixin.html).

The properties of the component described by the object `componentProperties`, in hook controller `getCellComponent` will be assigned using the property [`dynamicProperties`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/DynamicPropertiesMixin.html#property_dynamicProperties) from the mixin.
Therefore, when you change object properties `componentProperties`, those changes will be made for the component.

Example implementation:

```javascript
// app/controllers/my-controller.js 
import Controller from '@ember/controller';
import { observer } from '@ember/object';

export default Controller.extend({
  checkboxValue: false,

  lookupReadonly: observer('checkboxValue', function() {
    this.set('componentDynamicProperties.readonly', this.get('checkboxValue'));
  }),

  getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);
    if (attr.kind === 'belongsTo') {
      this.set('componentDynamicProperties', {
        choose: 'showLookupDialog',
        remove: 'removeLookupValue',
        displayAttributeName: 'name',
        required: true,
        relationName: 'author',
        projection: 'ApplicationUserL',
        autocomplete: true,
        readonly: false,
      });

      cellComponent.componentProperties = this.get('componentDynamicProperties');
    }

    return cellComponent;
  },
});
```

You can look at [the example realizacja](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-groupedit/ember-flexberry-dummy-suggestion-list-groupedit-with-lookup-with-computed-atribute) on the test stand.

## Ordered properties in groupedit

When you add in the view, groupedit-ordered-properties there will be some features that you should consider:

1. Sorting in the groupedit-e will be ordered to the property. The usual sort in the GE will be disabled and cannot be enabled.
2. When inserting/deleting rows in groupedit automatically recalculated ordered attribute for all rows.
3. In groupedit when the model has the ordered attribute, you can move rows up and down using the arrows. To automatically recalculate the values of the ordered attribute of those rows that have changed position.
4. If ordered attribute appears in GE for the user, the corresponding value of this attribute will be readonly.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}