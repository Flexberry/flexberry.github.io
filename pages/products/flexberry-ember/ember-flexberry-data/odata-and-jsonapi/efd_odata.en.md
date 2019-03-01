--- 
title: Support for OData v4 
sidebar: ember-flexberry-data_sidebar 
keywords: function, action, callAction, callFunction 
toc: true 
permalink: en/efd_odata.html 
lang: en 
autotranslated: true 
hash: 6e138842936e21872ae19759aba8c2b102ac080d65846314095cc6364f93b86a 
summary: the Components for communicating with the backend via OData. 
--- 

## OData 

ember-flexberry-data supports the OData Protocol v4. 

* OData adapter 

## Call functions and actions via Ajax 

To facilitate references to the functions and action of the backend via ajax OData adapter provides two similar methods: `callFunction` and `callAction`. 

### callFunction 

This method is intended to call functions on the backend. This method takes the following parameters: 

* `url`: address OData backend. If not given, it is taken to the address specified in `environment.js` 
* `functionName`: name the functions of the backend 
* `params`: an object containing query parameters 
* `successCallback`: method or `Promise` executed upon successful execution of the request 
* `failCallback`: method or `Promise` performed in case of unsuccessful execution of the request 
* `alwaysCallback`: method or `Promise` executed in any case. 

#### Examples of usage callAction 

* Without the callback functions, URL of the backend are taken from `environment.js`: 
```
adapter.callFunction('test', { someData: 'someData' })
``` 
* Callback functions: 

```
adapter.callFunction(
  'test',
  { someData: 'someData' },
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

### callAction 

This method is to call a backend action. This method takes the following parameters: 

* `url`: address OData backend. If not given, it is taken to the address specified in `environment.js` 
* `actionName`: name of action backend 
* `data`: an object containing query parameters 
* `successCallback`: method or `Promise` executed upon successful execution of the request 
* `failCallback`: method or `Promise` performed in case of unsuccessful execution of the request 
* `alwaysCallback`: method or `Promise` executed in any case. 

#### Examples of usage callAction 

* Without the callback functions, URL of the backend are taken from `environment.js`: 
```
adapter.callAction('test', { someData: 'someData' })
``` 
* Callback functions: 

```
adapter.callAction(
  'test',
  { someData: 'someData' },
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/