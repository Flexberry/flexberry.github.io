--- 
title: Support for OData v4 
sidebar: flexberry-ember_sidebar 
keywords: function, action, callAction, callEmberOdataAction, callFunction, callEmberOdataFunction 
toc: true 
permalink: en/efd_odata.html 
lang: en 
autotranslated: true 
hash: 137945e9f65140beea89197b6cbd1c427014c923285a07a6df7d54e1327555c8 
summary: the Components for communicating with the backend via OData. 
--- 

## OData 

ember-flexberry-data supports the OData Protocol v4. 

* OData adapter 

## Call functions and actions via Ajax 

To facilitate references to the functions and action of the backend via ajax OData adapter provides two similar methods: `callFunction` and `callAction`. 

### callFunction 

This method is intended to call functions on the backend. callFunction(functionName, params, url, fields, successCallback, failCallback, alwaysCallback): 

* `functionName`: name the functions of the backend 
* `params`: an object containing query parameters 
* `url`: address OData backend. If not given, it is taken to the address specified in `environment.js` 
* `fields`: object { name: value} to change the values of the corresponding fields of the XMLHttpRequest object 
* `successCallback`: method or `Promise` executed upon successful execution of the request 
* `failCallback`: method or `Promise` performed in case of unsuccessful execution of the request 
* `alwaysCallback`: method or `Promise` executed in any case. 

#### Examples of how to use callFunction 

* Without the callback functions, URL of the backend are taken from `environment.js`: 
```
adapter.callFunction('test', { someParams: 'someParams' })
``` 
* Callback functions: 

```
adapter.callFunction(
  'test',
  { someParams: 'someParams' },
  null,
  null,
  () => {
    console.log("This is a successCallback function");
  },
  () => {
    console.log("This is a function failCallback");
  },
  () => {
    console.log("This is an alwaysCallback function");
  }
)
``` 

### callEmberOdataFunction 

The same method callFunction, but as a result, returns ambarnye model. Has two additional options: store and modelName. callEmberOdataFunction(functionName, params, url, fields, store, modelName, successCallback, failCallback, alwaysCallback): 

* `store`: object [DS.Store](https://emberjs.com/api/ember-data/release/classes/DS.Store) 
* `modelName`: name amberieu model 

### callAction 

This method is to call a backend action. callAction(actionName, data, url, fields, successCallback, failCallback, alwaysCallback): 

* `actionName`: name of action backend 
* `data`: an object containing query parameters 
* `url`: address OData backend. If not given, it is taken to the address specified in `environment.js` 
* `fields`: object { name: value} to change the values of the corresponding fields of the XMLHttpRequest object 
* `successCallback`: method or `Promise` executed upon successful execution of the request 
* `failCallback`: method or `Promise` performed in case of unsuccessful execution of the request 
* `alwaysCallback`: method or `Promise` executed in any case.

#### Examples of usage callAction 

* Without the callback functions, URL of the backend are taken from `environment.js`: 
```
adapter.callAction('test', { someParams: 'someParams' })
``` 
* Callback functions: 

```
adapter.callAction(
  'test',
  { someParams: 'someParams' },
  null,
  null,
  () => {
    console.log("This is a successCallback function");
  },
  () => {
    console.log("This is a function failCallback");
  },
  () => {
    console.log("This is an alwaysCallback function");
  }
)
``` 

### callEmberOdataAction 

The same method callAction, but as a result, returns ambarnye model. Has two additional options: store and modelName. callEmberOdataAction(functionName, params, url, fields, store, modelName, successCallback, failCallback, alwaysCallback): 

* `store`: object [DS.Store](https://emberjs.com/api/ember-data/release/classes/DS.Store) 
* `modelName`: name amberieu model 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}
