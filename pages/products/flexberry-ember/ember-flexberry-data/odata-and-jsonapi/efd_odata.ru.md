---
title: Поддержка протокола OData v4
sidebar: ember-flexberry-data_sidebar
keywords: function, action, callAction, callFunction
toc: true
permalink: ru/efd_odata.html
lang: ru
summary: Компоненты для взаимодействия с backend по протоколу OData.
---

## OData

ember-flexberry-data поддерживает протокол OData v4.

* OData adapter

## Вызов функций и экшенов через Ajax

Для облегчения обращений к функциям и экшенам бэкенда через ajax в OData-адаптере предусмотрены два схожих метода: `callFunction` и `callAction`.

### callFunction

Данный метод предназначен для вызова функций бэкенда. Метод принимает следующие параметры:

* `url`: адрес OData бэкенда. Если не указан – берётся адрес, указанный в `environment.js`
* `functionName`: имя функции бэкенда
* `params`: объект, содержащий параметры запроса
* `successCallback`: метод или `Promise`, исполняемые при успешном исполнении запроса
* `failCallback`: метод или `Promise`, исполняемые при неудачном исполнении запроса
* `alwaysCallback`: метод или `Promise`, исполняемые в любом случае.

#### Примеры использования callAction

* Без callback-функций, URL бэкенда берем из `environment.js`:
```
adapter.callFunction('test', { someData: 'someData' })
```
* С callback-функциями:

```
adapter.callFunction(
  'test',
  { someData: 'someData' },
  () => {
    console.log("This is a successCallback function");
  },
  () => {
    console.log("This is a failCallback function");
  },
  () => {
    console.log("This is an alwaysCallback function");
  }
)
```

### callAction

Данный метод предназначен для вызова экшенов бэкенда. Метод принимает следующие параметры:

* `url`: адрес OData бэкенда. Если не указан – берётся адрес, указанный в `environment.js`
* `actionName`: имя экшена бэкенда
* `data`: объект, содержащий параметры запроса
* `successCallback`: метод или `Promise`, исполняемые при успешном исполнении запроса
* `failCallback`: метод или `Promise`, исполняемые при неудачном исполнении запроса
* `alwaysCallback`: метод или `Promise`, исполняемые в любом случае.

#### Примеры использования callAction

* Без callback-функций, URL бэкенда берем из `environment.js`:
```
adapter.callAction('test', { someData: 'someData' })
```
* С callback-функциями:

```
adapter.callAction(
  'test',
  { someData: 'someData' },
  () => {
    console.log("This is a successCallback function");
  },
  () => {
    console.log("This is a failCallback function");
  },
  () => {
    console.log("This is an alwaysCallback function");
  }
)
```
