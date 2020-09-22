---
title: Support for OData v4
sidebar: flexberry-ember-2_sidebar
keywords: function, action, callAction, callEmberOdataAction, callFunction, callEmberOdataFunction, batch batchUpdate, batchDelete, batchCreate, batchInsert
toc: true
permalink: en/efd2_odata.html
lang: en
autotranslated: true
hash: c80a8028ae81ecacaf8ed8757ea5cd8361b4161d90c04eeecb0635f101897139
summary: the Components for communicating with the backend via OData support transactional changes to data, call functions and actions via Ajax, examples of usage
---

## OData

ember-flexberry-data supports the OData Protocol v4.

* OData adapter

## Support transactional data changes

Support sending multiple model objects to save the changes in a single transaction implemented through the `batchUpdate` object `store`. To obtain the current status of data objects given the business logic servers and database triggers for each query, INSERT, UPDATE, DELETE, sends extra SELECT statement for the updated entities.

Example usage:

```javascript
const store = this.get('store');
const models = [
  store.createRecord('user', { name: 'Nif' }),
  store.createRecord('user', { name: 'Naf' }),
  store.createRecord('user', { name: 'Nuf' }),
];

store.batchUpdate(models).then((models) => {
  const [nif, naf, nuf] = models;
});
```

## Call functions and actions via Ajax

To facilitate references to the functions and action of the backend via ajax OData adapter provides two similar methods: `callFunction` and `callAction`.

### callFunction

This method is to call OData functions backend. callFunction(args):

* `args.functionName`: the name of the function backend (the only mandatory property)
* `args.params`: an object containing query parameters
* `args.url`: address OData backend. If not given, it is taken to the address specified in `environment.js`
* `args.fields`: object { name: value} to change the values of the corresponding fields of the XMLHttpRequest object
* `args.store`: object [DS.Store](https://emberjs.com/api/ember-data/release/classes/DS.Store)
* `args.modelName`: name amberieu model
* `args.modelProjection`: presentation amberieu model
* `args.successCallback`: method or `Promise` executed upon successful execution of the request
* `args.failCallback`: method or `Promise` performed in case of unsuccessful execution of the request
* `args.alwaysCallback`: method or `Promise` executed in any case.

Properties store, modelName and modelProjection needed only if the OData function returns ambarnye model.

{% include note.html content="For compatibility, it is possible to call a method with the signature of callFunction(functionName, params, url, fields, successCallback, failCallback, alwaysCallback)." %}

#### Examples of how to use callFunction

* Without the callback functions, URL of the backend are taken from `environment.js`:

```javascript
adapter.callFunction({ functionName: 'test', params: { someParams: 'someParams' } })
```

* To the callback functions:

```javascript
adapter.callFunction({
  functionName: 'test',
  params: { someParams: 'someParams' },
  successCallback: () => {
    console.log("This is a successCallback function");
  },
  failCallback: () => {
    console.log("This is a function failCallback");
  },
  alwaysCallback: () => {
    console.log("This is an alwaysCallback function");
  }
})
```

### callEmberOdataFunction (Deprecated)

The same method callFunction. To maintain compatibility, can be called with the signature callEmberOdataFunction(functionName, params, url, fields, store, modelName, successCallback, failCallback, alwaysCallback).

### callAction

This method is intended to invoke an OData action games backend. callAction(args):

* `args.actionName`: name of action backend (the only mandatory property)
* `args.data`: an object containing query parameters
* `args.url`: address OData backend. If not given, it is taken to the address specified in `environment.js`
* `args.fields`: object { name: value} to change the values of the corresponding fields of the XMLHttpRequest object
* `args.store`: object [DS.Store](https://emberjs.com/api/ember-data/release/classes/DS.Store)
* `args.modelName`: name amberieu model
* `args.successCallback`: method or `Promise` executed upon successful execution of the request
* `args.failCallback`: method or `Promise` performed in case of unsuccessful execution of the request
* `args.alwaysCallback`: method or `Promise` executed in any case.

The store property and modelName is only needed if the OData function returns ambarnye model. If the model contains masters or detaily, then this method won't bring them back. You need to use the callFunction method of specifying presentation.

{% include note.html content="For compatibility, it is possible to call a method with the signature callAction(actionName, data, url, fields, successCallback, failCallback, alwaysCallback)." %}

#### Examples of usage callAction

* Without the callback functions, URL of the backend are taken from `environment.js`:

```javascript
adapter.callAction({ actionName: 'test', data: { someParams: 'someParams' } })
```

* To the callback functions:

```javascript
adapter.callAction({
  actionName: 'test',
  data: { someParams: 'someParams' },
  successCallback: () => {
    console.log("This is a successCallback function");
  },
  failCallback: () => {
    console.log("This is a function failCallback");
  },
  alwaysCallback: () => {
    console.log("This is an alwaysCallback function");
  }
})
```

### callEmberOdataAction (Deprecated)

The same method callAction. To maintain compatibility, can be called with the signature callEmberOdataAction(actionName, data, url, fields, store, modelName, successCallback, failCallback, alwaysCallback).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}