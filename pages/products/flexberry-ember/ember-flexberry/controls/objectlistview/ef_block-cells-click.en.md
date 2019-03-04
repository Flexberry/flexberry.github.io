--- 
title: Lock individual cells in a list 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember, filters, OLV, and the list 
summary: Use parameters when working with strings, lists 
toc: true 
permalink: en/ef_block-cells-click.html 
lang: en 
autotranslated: true 
hash: 80cf909a2bb08a84e32509e4da0dd08a6d166cc5e873ea33b63840f57416bff6 
--- 

[List](ef_object-list-view.html) there is a possibility to lock individual safe Deposit box to open [edit](ef_edit-form.html), while leaving active-click on the line. 

For this to disable click on line you want to override the transition to the edit form using a parameter (params): 

```javascript
params.goToEditForm = false; 
``` 

Then call the `_super`. 

In the processor settings click on the row there: 

* the entry that was clicked 
* column which is clicked (it has a property name column header on the form cellComponent this column) 
* the index of the pressed column. 

These settings are used to disable click handling on the line under certain conditions (i.e. when clicking on certain cells). 

For example: 

```javascript
actions: {
    objectListViewRowClick(record, params) {
      if (params.column && params.column.cellComponent.componentName === 'flexberry-file' && params.originalEvent.target.tagName.toLowerCase() !== 'td') {
        params.goToEditForm = false;
      }

      this._super(...arguments);
    }
``` 

Read more in the application code [dummy](https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/routes/components-examples/flexberry-objectlistview/downloading-files-from-olv-list.js#L45). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/