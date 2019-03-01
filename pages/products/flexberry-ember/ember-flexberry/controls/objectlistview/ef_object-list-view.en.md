--- 
title: Flexberry Objectlistview 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_object-list-view.html 
lang: en 
autotranslated: true 
hash: 6de7d4630657eb705cf84d7844719fc21de99923044e17b6902559104e5ce8cc 
summary: description of the used components and configuration lists in applications ispoljavanje technology Flexberry Ember 
--- 

## Description 

The main purpose of __flexberry-objectlistview__ - view the list of available objects with the possibility of subsequent open and edit (in the built-paging control that allows you to view the data portion). 

To add a control to the page, you can use the [template](ef_template.html) (this is a template to add a [list form](ef_forms.html)): 

```hbs
{% raw %}{{flexberry-objectlistview
	componentName = "ordersObjectListView"
	modelController = this
	modelName = "order"
	modelProjection = modelProjection
	content = model

	createNewButton = true
	refreshButton = true
	showDeleteButtonInRow = true
    colsConfigButton = true

	sorting = computedSorting
	orderable = true
	sortByColumn = (action "sortByColumn")
	addColumnToSorting = (action "addColumnToSorting")
	action = "rowClick"

	pages = pages
	perPageValue = perPageValue
	perPageValues = perPageValues
	hasPreviousPage = hasPreviousPage
	hasNextPage = hasNextPage
	previousPage = (action "previousPage")
	gotoPage = (action "gotoPage")
	nextPage = (action "nextPage")
}}{% endraw %}
``` 

{% include note.html content="the Syntax `addColumnToSorting=(action \"addColumnToSorting\")` determines that closure is used _ember action_. If the requested action is not defined, then the call to the appropriate functionality in the browser console you will see error." %} 

If the form is used for more events in the template can be passed as the event name 

```hbs
{% raw %}{{flexberry-objectlistview
...
componentName="castomActionName"
}}{% endraw %}
``` 

and in fact the event itself: 

```hbs
{% raw %}{{flexberry-objectlistview
...
componentName=castomActionName
}}{% endraw %}
``` 

### the List of components 

The component name |description 
:----------------------------|:------------------------------ 
`componentName`| the name of the component. Must be unique within odnostranichnik. 
`modelController`| Current page controller. 
`modelName`| the name of the current model (a list of which objects are displayed). 
`modelProjection `| Current used view. 
`content `| Displayed in the control data. 
`createNewButton `| a Flag that determines whether to display the button to create on [control panel](ef_olv-toolbar.html). 
`refreshButton `| a Flag that determines whether to display the refresh button on the [control panel](ef_olv-toolbar.html). 
`deleteButton `| a Flag that determines whether to display the delete button on the [control panel](ef_olv-toolbar.html). 
`showCheckBoxInRow`| a Flag that determines whether to display checkboxes for selection of records in each line. 
`showDeleteButtonInRow`| a Flag that determines whether to display the delete button of the row in each line. 
`showDeleteMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template f-olv, "Delete record". 
`showEditMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template f-olv, "Edit record". 
`rowClickable `| a Flag that determines whether to treat depression is on the line. 
`action `| the Action to perform when clicking the line. 
`orderable`| a Flag that determines whether to sort by columns in the component. 
`sorting`| Method of determining the current sorting.
`sortByColumn`| Action (`action`) of the controller that should be executed to sort by column. 
`addColumnToSorting`| Action (`action`) of the controller that should be executed to add a sort by column. 
`pages`| Method for the determination of available pages to display in the paging. 
`perPageValue`| Method definitions for the current setting, which indicates the number of records displayed on the page. 
`perPageValues`| an Array defining the number of records per page can be displayed. 
`hasPreviousPage`| a Flag that determines whether the current page is the last. 
`hasNextPage`| a Flag that determines whether the current page later. 
`previousPage`| Method to move to the previous page. 
`gotoPage`| Method to move on a given page. 
`nextPage`| Method to move to the next page. 
`editFormRoute`| Specifies the name of the route edit form, which will open the model. 
`singleColumnHeaderTitle `| Header for mobile view component, instead of column names. If not specified or equal to "" then the header of the table in the component is hidden. 
`colsConfigButton`| Flag (`true`/`false`) enable/disable the display of buttons in the user settings. 

Default values: 

```javascript
action: 'rowClick',
createNewButton: false,
refreshButton: false,
orderable: false,
rowClickable: true,
showCheckBoxInRow: false,
showDeleteButtonInRow: false,
showDeleteMenuItemInRow: false,
showEditMenuItemInRow: true
colsConfigButton - true
``` 

## setup control panel 

The composition of the control Flexberry Objectlistview comes with [toolbar](ef_olv-toolbar.html) that you can customize and add custom buttons. 

## Custom button in the toolbar and string list 

Control panel, and buttons in rows, might be supplemented with custom buttons that implement the necessary functions to work with list. Described in detail in [Custom button for lists](ef_custom-buttons.html). 

## Export to Excel 

Technology `Ember Flexberry` provides the ability to export lists. To export became available in the app, you need to implement [settings backend](fan_odata-export-to-excel.html). 

In the template directly form must be specified 

```hbs
{% raw %}{{flexberry-objectlistview
...
exportExcelButton=true
{% endraw %}
``` 

Export settings can be saved by specifying a name. You can also change the name of the exported columns. 

## configuring a hierarchical list 

If the list is hierarchical (the object has a reference to itself), the hierarchy for the list is set by default. 

![](/images/pages/ABratchikova/Hierarchy folv.png) 

If the hierarchy for the list you want to disable, then in the template you should register `disableHierarchicalMode = true`. 

If in the model there are two or more links themselves (which might be hierarchy), then you need to specify the name of the `по which should be иерархия`: `hierarchyByAttribute = propertyName`.

## restraints 

Particularly the imposition of restrictions on Flexberry Objectlistview related to the fact that the data for the control shall be taxed in the router. Accordingly, in order to prevent the subtraction of data without restrictions, the restriction must be defined when you hook `model` in the router shape. 

Thus, to impose a restriction, you must override the method `objectListViewLimitPredicate` in the router application list forms to return a predicate for the constraint. 

For example, is a form `limit-function-example`. If the page displays an even number of records, you need to display records that have a field "address" is the letter "S". When the odd number - with the "address" field the letter "p". 

PstrfobjectListViewLimitPredicate` an overridable method in the router of the appropriate application list form. 

```javascript
import Ember from 'ember';
import ListFormRoute from 'ember-flexberry/routes/list-form';
import { StringPredicate } from 'ember-flexberry-data/query/predicate';

// ... 
export default ListFormRoute.extend({
  objectListViewLimitPredicate: function(options) {
    let methodOptions = Ember.merge({
      modelName: undefined,
      projectionName: undefined,
      params: undefined
    }, options);

    if (methodOptions.modelName === this.get('modelName') &&
        methodOptions.projectionName === this.get('modelProjection')) {
      let currentPerPageValue = methodOptions.params ? methodOptions.params.perPage : undefined;
      let limitFunction = (currentPerPageValue && currentPerPageValue % 2 === 0) ?
                          new StringPredicate('address').contains('S') :
                          new StringPredicate('address').contains('п');
      return limitFunction;
    }

    return undefined;
  }
});
``` 

## Deletion of list items 

To remove items from the list method is used `beforeDeleteRecord`. The method supports asynchronous mode, it is possible to return `promises` as the value of the result. 

### Delete all selected elements 

To configure simultaneous removal of all selected list items you need in the controller to register event `beforeDeleteAllRecords`: 

```javascript
import ListFormController from './list-form';

export default ListFormController.extend({
  actions: {
    beforeDeleteAllRecords(modelName, data) {
      if (modelName === 'application-user') {
        data.cancel = false;
      }
    }
  }
});
``` 

and specify it in the template list: 

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  beforeDeleteAllRecords=(action 'beforeDeleteAllRecords')
  ...
}}{% endraw %}
``` 

{% include note.html content="we recommend that you implement a notification-a question for the user about deletion of objects." %} 

## Check list before removing items 

When working with the list can return a promise using `return` in the handler's body `beforeDeleteRecord`, it is possible to implement asynchronous logic. If you do return <promise> then the delete operation will be called after promise is fulfilled . If he will be terminated, the deletion will fail. 

## Display of error list 

If the list contains errors, it will display indicating the error: 

* when loading a list with an error the first time you will see a pattern `error.hbs` 
* the controller has a list of previously downloaded items, error will be shown through the component. 

To change this in the application layer by using the method `onModelLoadingRejected` in the router. 

## configure filtering on the list 

Configuration of filters on the lists described in the article [customize filters for lists](ef_configuring-filters.html). 

## Search on all attributes 

Configuration standard control list and search for artisan of forms (picks up at LookUp) is described in the article [Search on all attributes](ef_search-attributes.html). 

## change the width of columns 

The principles of configuring and using properties change the width of the columns in the lists of items and their detailov described in the article [column width](ef_olv-resize.html).

## configure the control panel for lists 

The use of technology and custom buttons in the toolbar are described in the article [setting the control panel for list](ef_olv-toolbar.html). 

## Tools work with objects on the lists 

Tools work with objects on the page/ all pages described in article described in article [Tools work with objects on lists](ef_list-component-select-all.html). 

## configuration of cells list 

How the behavior of individual cells in a list described in the article [Lock individual cells in a list](ef_block-cells-click.html). 

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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/