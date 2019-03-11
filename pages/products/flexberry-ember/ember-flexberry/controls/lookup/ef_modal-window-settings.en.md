--- 
title: setting up a raise on lucapa forms 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_modal-window-settings.html 
lang: en 
autotranslated: true 
hash: 6d08c8b327eb8ad6894f8ce1a762af12b3a657917868229df3c4d580ecb2af62 
summary: Presents the basic customization possibilities raised by lucapa form. 
--- 

## setting the modal window lucapa 

Settings modal window [lucapa](ef_lookup.html) is defined in `components/lookup-field/lookup-field-mixin.js`. 

```js
lookupSettings: {
	controllerName: undefined,
	template: undefined,
	contentTemplate: undefined,
	loaderTemplate: undefined,
	modalWindowWidth: undefined,
	modalWindowHeight:undefined
}
``` 

In the controller editor `controllers/edit-form.js` these settings set: 

```js
lookupSettings: {
    controllerName: 'lookup-dialog',
    template: 'lookup-dialog',
    contentTemplate: 'lookup-dialog-content',
    loaderTemplate: 'loading',
    modalWindowWidth: 750,
    modalWindowHeight:600
},
``` 

## adjust the size 

* `modalWindowWidth` is the width of the opening lucapa the modal window. 
* `modalWindowHeight` is the height of the opening lucapa the modal window. 

If you want to change the size of the opening lucapa modal window, you can override the default values. 

## setting the title of window opened on lucapa 

The title of the window opened by [luckau](ef_lookup.html), installed in the property `title` component `lookup-field` in the template corresponding edit form. For example, the template edit form `employee.hbs` embedding lucapa may look like the following: 

```hbs
{% raw %}{{lookup-field/lookup-field
  choose='showLookupDialog'
  remove='removeLookupValue'
  value=model.employee1.firstName
  relationName='employee1'
  projection='EmployeeL'
  title='Employees'
}}{% endraw %}
``` 

As a result, the title of the properties `title` component `lookup-field` will be displayed in a modal window open locapo: 

![](/images/pages/img/page/EditFormTitle/lookuptitle.png) 

## configure filtering and the number of elements on the page 

Settings filter and/or the number of elements on the page through the event `getLookupFolvProperties` in the controller of the form: 

```javascript
getLookupFolvProperties: function(options) {
    //... 

    if (methodArgs.relationName === 'type') {
    return {
        filterButton: true,
        filterByAnyWord: true,
        enableFiltres: true,
        refreshButton: true,
        perPage: 25,
      };
    }

    // ... 
}
``` 

{% include note.html content="you Must select a search type (`filterByAnyWord` or `filterByAllWords`), so lookup the form to use you may only use one of them." %} 

Next, specify the event in the template nastraivanie for LookUp list: 

```hbs
{% raw %}{{flexberry-lookup
    // ... 
    lookupWindowCustomProperties=(action 'getLookupFolvProperties')
}}{% endraw %}
``` 

The implementation displayed on [ember-stande](https://flexberry-ember-dev.firebaseapp.com/components-examples/flexberry-lookup/customizing-window-example). 

## hierarchy setting 

In the controller editor to specify: 

```javascript
export default EditFormController.extend({
    // ... 
    getLookupFolvProperties: function(options) {
      // ... 

      if (options.relationName === 'type') { // Property lucapa. 
        return {
            // ... 
	    // Whether to show the toggle button in the hierarchy, if the hierarchy for a list of available 
	    // (if false, the button is displayed) 
            disableHierarchicalMode: false,

            // Activate the hierarchy when you load a lookup-form. 
            inHierarchicalMode: true,
            hierarchicalAttribute: 'Name' // Property on which to build the hierarchy. 
        };
      }

     // ... 
});
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}