--- 
title: guidelines for writing comments with autodocumentary in Flexberry Ember 
sidebar: flexberry-platform_sidebar 
keywords: code style, yuidoc, comments, autodoc 
toc: true 
permalink: en/fp_ember-comments-style-yuidoc.html 
lang: en 
autotranslated: true 
hash: 33dc939dd5e081e47c89db9883408978f9b6ad1934110dd9719278db35a9dcf6 
summary: There are certain rules of writing the review for the correct formation of autodocumentary in ember-ember flexberry and other projects. All comments are written according to the rules of YUIDoc. 
--- 

## Rules YUIDoc 

In EmberJS projects platform Flexberry adopted a standard of review - [YUIDoc](http://yui.github.io/yuidoc/syntax/index.html). These comments are used for the formation of autodocumentary code describing `API` developed platform modules Flexberry. 

### Policy private properties and methods 

All private properties and methods should start with `_`, for example: 

``` javascript
_somePrivateProperty: null
``` 

All inner classes for properties, for example, calculated on the basis of the public should always be private. 
**For components considered to be public properties** the component is given its configuration, data, and other properties specified in the form template. 
For other classes similarly. 

### the order of the properties and methods inside classes 

Within a class the order of the properties and methods should be as follows: 
1. private properties 
2. public properties 
3. actions 
4. public methods (should go first methods init and didInsertElement, if any, and other overloaded "amberlie" methods) 
5. private-methods 

### method Names for events 

The method names for the action-s document with a prefix of **actions.<methodname>** (to action-you can see from the method names in the documentation). For example: 

``` javascript
// ... 
actions: {
  /** 
This action is called when Superman fall from a skyscraper. 

@method actions.someAction 
*/
  someAction: function() {
    // ... 
  }
}
// ... 
``` 

### description of the events sent by the components or other classes "outside" 

Action-s send components or other classes "outside" (through the method sendAction) should be described at the end of class, in the comments, not tied to any properties and methods **with the prefix sendingActions.<имя_action-a>**. 

``` javascript
/** 
Invoking the component's action when the checkbox was clicked and it's 'checked' state changed. 

@method sendingActions.change 
@param {Object} e the Action's event object. 
@param {Boolean} e.newValue the Component's new value. 
@param {Object} e.originalEvent [jQuery event object](http://api.jquery.com/category/events/event-object/) 
which describes s inner input's 'change' event. 
*/
``` 

And in the comments to the methods that cause `sendAction` inside yourself, you need to refer to send "out" action-y. 

``` javascript
/** 
Handles bubbled s inner input's 'change' action. 
Invokes the component's `#crossLink "MyComponent/sendingActions.change:method"`'change'`/crossLink` action. 

@method actions.change 
@param {Object} e jQuery event object](http://api.jquery.com/category/events/event-object/) 
which describes s inner input's 'change' event. 
*/
change(e) {
  let checked = e.target.checked === true;

  // Invoke the component's custom 'change' action. 
  this.sendAction('change', {
    newValue: checked,
    originalEvent: e
  });
}
``` 

### Documenting overloaded properties and methods 

All overloads "amberlie" properties and methods in detail can not be documented, because public autodocumentation to include again the description of "standard" properties and methods that are in autodocumentation ember-and it makes no sense. 
**To document overloaded "amberlie" methods makes sense, if you have changed its signature** (e.g., adding options).
Enough of such descriptions: 

``` javascript
/** 
initializes component. 
*/
init() {
 // ... 
}
``` 

### Links to external documentation 

If you need to refer to external documentation for ember-a or somewhere else outside, but `yuidoc` supports `html` or `markdown` syntax directly in the comments. 
For example: 

``` javascript
/** 
Query for records that meet certain criteria. Resolves with [DS.RecordArray](http://emberjs.com/api/data/classes/DS.RecordArray.html). 
For more information see [query](http://emberjs.com/api/data/classes/DS.Store.html#method_query) method of [DS.Store](http://emberjs.com/api/data/classes/DS.Store.html). 
@method query 
@param {String} modelName 
@param {Object} query 
@param {Boolean} [query.useOnlineStore] Allow to explicitly specify online or offline independently using the store of global online status 
@return {Promise} promise 
*/
query(modelName, query) {
  // ... 
}
``` 

### Specify the parent class 

In autodocumentation to classes, do not forget to specify the parent class (`@extends`) and what the mixin class uses (`@uses`). 
If `@extends` amberny specifies the parent class (or class from some external addon), or in `@uses` used amberny of mixins (or mixin from some external addon) that prescribe it should reference (via `#crossLink`..`/crossLink`): 

``` javascript
/** 
This should be used as store:main 

@class BaseStore 
@extends <a href="http://emberjs.com/api/data/classes/DS.Store.html">DS.Store</a> 
*/
export default DS.Store.extend({
  // ... 
});
``` 

## Generation of autodocumentary based on reviews 

To see locally how it will look in the documentation, it should be in the folder with the source code, for example `ember-flexberry/addon`, run the command: 

``` bash
yuidoc . --server
``` 

If it was not done earlier, before starting you need to install `yuidoc` command: 

``` bash
npm install -g yuidocjs
``` 

Read more `YUIDoc` syntax can be found here: [http://yui.github.io/yuidoc/syntax/index.html](http://yui.github.io/yuidoc/syntax/index.html).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}