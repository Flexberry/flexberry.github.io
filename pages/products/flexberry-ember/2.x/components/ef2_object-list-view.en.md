---
title: Flexberry Objectlistview
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, OLV, lists
toc: true
permalink: en/ef2_object-list-view.html
lang: en
autotranslated: true
hash: 226da474808101446463d818559b29a526f60baa358c8d44e2db2b1bde4dbe71
summary: Used components and configuration lists in applications using the technology Flexberry Ember, export, delete items
---

The main purpose of __flexberry-ojectlistview__ - view the list of available objects with the possibility of subsequent open and edit (in the built-paging control that allows you to view the data portion).

To add a control to the page, you can use the [template](ef2_template.html) (see below for a template to add to [list form](ef2_forms.html)):

```hbs
{% raw %}{{flexberry-objectlistview
  componentName = "ordersObjectListView"
  modelController = this
  modelName = "order"
  modelProjection = modelProjection
  content = model

  createNewButton = true
  refreshButton = true
  defaultSortingButton = true
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
action="customActionName"
}}{% endraw %}
```

and in fact the event itself:

```hbs
{% raw %}{{flexberry-objectlistview
...
action=customActionName
}}{% endraw %}
```

### The list of components flexberry-ojectlistview

The component name |description
:-----------------------|:------------------------------
`componentName`| the name of the component. Must be unique within odnostranichnik.
`modelController`| Current page controller.
`modelName`| the name of the current model (a list of which objects are displayed).
`modelProjection`| Current used view.
`content`| Displayed in the control data.
`createNewButton`| a Flag that determines whether to display the button to create on [control panel](ef2_setting-lists.html).
`refreshButton`| a Flag that determines whether to display the refresh button on the control panel.
`defaultSortingButton`| a Flag that determines whether to display button for setting the default sort.
`deleteButton`| a Flag that determines whether to display the delete button on the control panel.
`showCheckBoxInRow`| a Flag that determines whether to display checkboxes for selection of records in each line.
`showDeleteButtonInRow`| a Flag that determines whether to display the delete button of the row in each line.
`showDeleteMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template f-olv, "Delete record".
`showEditMenuItemInRow`| a Flag that determines whether to display the item in the context menu mobile template f-olv, "Edit record".
`rowClickable`| a Flag that determines whether to treat depression is on the line.
`action`| the Action to perform when clicking the line.
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
`singleColumnHeaderTitle`| Header for mobile view component, instead of column names. If not specified or equal to "" then the header of the table in the component is hidden.
`colsConfigButton`| Flag (`true`/`false`) enable/disable the display of buttons in the user settings.

Default values:

```javascript
action: 'rowClick',
createNewButton: false,
refreshButton: false,
defaultSortingButton: true,
orderable: false,
rowClickable: true,
showCheckBoxInRow: false,
showDeleteButtonInRow: false,
showDeleteMenuItemInRow: false,
showEditMenuItemInRow: true
colsConfigButton - true
```

## Customize lists

Part of Flexberry Objectlistview control includes a toolbar that you can customize and add custom buttons.

Control panel, and buttons in rows, might be supplemented with custom buttons that implement the necessary functions to work with list. Described in detail in [Settings list](ef2_setting-lists.html).

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

## Deleting list items

To remove items from the list method is used `beforeDeleteRecord`. The method supports asynchronous mode, it is possible to return `promises` as the value of the result.

### Deleting all selected items

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

## Check list before removing items

When working with the list can return a promise using `return` in the handler's body `beforeDeleteRecord`, it is possible to implement asynchronous logic. If you do return <promise> then the delete operation will be called after promise is fulfilled . If he will be terminated, the deletion will fail.

## Display of error list

If the list contains errors, it will display indicating the error:

* when loading a list with an error the first time you will see a pattern `error.hbs`
* the controller has a list of previously downloaded items, error will be shown through the component.

To change this in the application layer by using the method `onModelLoadingRejected` in the router.

## Limitations of lists

Features to configure the limits on the lists described in the article [Tools limit the list](ef2_list-restriction-tools.html).

## Change the width of columns

Change column widths for list and [datalow](ef2_groupedit.html) on the edit form is implemented according to General principles.

To enable/disable changing of width HN of the columns, you must specify the control in the template property `allowColumnResize` (for normal applications this property has a default value `true` for mobile - `false`.

{% include note.html content="despite the fact that mobile applications changing the width of columns available to include the flag for this type of application is not recommended." %}

If the plugin was initialized correctly, after you download the control when you hover over the border between columns, the cursor changes its appearance and you can change the width of columns.

It is possible to prevent resizing of individual columns using the [initial configuration of columns to display](ef2_model-user-settings-service.html) in the property `columnWidths`.

## Service user settings

If [service user settings](ef2_model-user-settings-service.html) is enabled, when loading the list control to read the saved setting width of columns, and changing column width - the setting is saved.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}