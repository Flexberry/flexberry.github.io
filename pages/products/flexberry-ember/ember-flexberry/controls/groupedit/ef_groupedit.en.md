--- 
title: Flexberry Groupedit 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_groupedit.html 
lang: en 
autotranslated: true 
hash: acd52f751380ee76ff4bfdffa409c8e875e3d1b287ca3692cdaf7a259a1bc9f9 
summary: Properties, implementation details, configuring the sorting and integration of application components 
--- 

## Description 

`flexberry-groupedit` is designed to work with [detaylari](fo_detail-associations-properties.html) on the edit form. 

To add groupedit page, in the template you need to specify: 

```hbs
{% raw %}{{flexberry-groupedit
  componentName="ordersGroupEdit"
  class="attached"
  modelProjection=modelProjection.attributes.orders
  content=model.orders
  readonly=readonly
  orderable=false
}}{% endraw %}
``` 

### Properties Flexberry Groupedit 

Property | Brief description 
:--------------------|:-------------- 
Properties `componentName` and `readonly`| Properties from the [basic control](ef_controls.html). 
`modelProjection`| Defines the view to be displayed. 
`content`| Single records, an editable control. 
`cellComponent`| Method that determines how the control component is edited. 
`orderable`| a Flag that determines whether to sort by columns in the component (if the flag value is changed to "true" will require additional configuration, see below). 
`showDeleteMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template f-ge, "Delete record". The default value is false. 
`showEditMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template f-ge, the "Edit record". The default value is false. 
`showDeleteButtonInRow`| Flag specifying to display the "-" button to delete the record in the browser and mobile template. The default value is false. 
`singleColumnHeaderTitle `| Header for mobile view f-ge, instead of column names. 

Can also be used properties: 

* `rowClickable` 
* `rowClick` 
* `editOnSeparateRoute` 

For details, see [article](ef_groupedit-detail-in-route.html)). 

## features of the implementation 

* Flexberry Groupedit consists of two components: `GroupeditToolbar` and `ObjectListView`. 
* Property `class` applies to `ObjectListView`. 

## of flag orderable 

If the flag `orderable` set the value to "true", requires to specify settings: 

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

## Embedding components in groupedit 

Component `flexberry-groupedit` is a table in which the cells can be embedded on any component that inherits from [flexberry-base-component](ef_controls.html). 

For embedding components flexberry-groupedit `getCellComponent` finds a method in the current controller, and causes in the formation of each cell of the table. 

PstrfgetCellComponent` method already defined in the base controller edit form (`ember-flexberry/controllers/edit-form.js`), his logic is aimed 
on the embedding of components, depending on the type of data in the cell, and is as follows: 

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

If you applied the edit form you want to embed the application component in the cell groupedit-but, you will need to override the method `getCellComponent` in the application controller 
inherited from base controller edit form (`ember-flexberry/controllers/edit-form.js`). 

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

It is important that the embedded component inherits from basic component (`ember-flexberry/components/flexberry-base-component.js`), 
and if a component of the overridden method `init`, at the end of application initialization, must be called by the initialization method from the base class, because there is a defined logic that initializes component properties from the object passed in the return value of the method getCellComponent key componentProperties. 

## Editing detailov in a separate roat 

Configuring templates and models aggregator and detail, serializer aggregator and controller of detail to implement editing options in a separate route described in the article [Edit detailov in a separate rout](ef_groupedit-detail-in-route.html) 

## Computable properties in getCellComponent 

To create a computed property need to `controllers`, `getCellComponent` add property `computedProperties: { thisController: this }`: 

```javascript
getCellComponent(attr, bindingPath, model) {
   let cellComponent = this._super(...arguments);
   if (attr.kind === 'belongsTo') {
     cellComponent.componentProperties = {
       choose: 'showLookupDialog',
       remove: 'removeLookupValue',
       displayAttributeName: 'name',
       required: true,
       relationName: 'author',
       projection: 'ApplicationUserL',
       autocomplete: true,
       computedProperties: { thisController: this },
       readonly: false,
      };
   }

   return cellComponent;
 },
``` 

Thus in the property `computedProperties` the current controller and will be `this` of [dynamic-properties](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/dynamic-properties.js) with all your observer-AMI. Now to change any of the properties strimage component is sufficient to change the value in `computedProperties`: 

```javascript
checkboxValue: false,

lookupReadonly: Ember.observer('checkboxValue', function() {
  if (!Ember.isNone(this.get('computedProperties.dynamicProperties.readonly'))) {
    if (this.get('checkboxValue')) {
      this.set('computedProperties.dynamicProperties.readonly', true);
    } else {
      this.set('computedProperties.dynamicProperties.readonly', false);
    }
  }

  return this.get('checkboxValue');
}),
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}