---
title: Поддержка протокола OData v4
sidebar: flexberry-ember-2_sidebar
keywords: function, action, callAction, callEmberOdataAction, callFunction, callEmberOdataFunction, batch, batchUpdate, batchDelete, batchCreate, batchInsert
toc: true
permalink: ru/efd2_odata.html
lang: ru
summary: Компоненты для взаимодействия с backend по протоколу OData, поддержка транзакционных изменений данных, вызов функций и экшенов через Ajax, примеры использования
---

## OData

ember-flexberry-data поддерживает протокол OData v4.

* OData adapter

## Поддержка транзакционных изменений данных

Поддержка отправки нескольких объектов модели для сохранения изменений в одной транзакции реализована через метод `batchUpdate` объекта `store`. Для получения актуального состояния объектов данных с учётом логики бизнес-серверов и триггеров базы данных для каждого запроса INSERT, UPDATE, DELETE отправляется дополнительный SELECT на изменяемые сущности.

Пример использования:

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

## Вызов функций и экшенов через Ajax

Для облегчения обращений к функциям и экшенам бэкенда через ajax в OData-адаптере предусмотрены два схожих метода: `callFunction` и `callAction`.

### callFunction

Данный метод предназначен для вызова OData функций бэкенда. callFunction(args):

* `args.functionName`: имя функции бэкенда (единственное обязательное свойство)
* `args.params`: объект, содержащий параметры запроса
* `args.url`: адрес OData бэкенда. Если не указан – берётся адрес, указанный в `environment.js`
* `args.fields`: объект вида { имя: значение }, для изменения значений соответствующих полей объекта XMLHttpRequest
* `args.store`: объект [DS.Store](https://emberjs.com/api/ember-data/release/classes/DS.Store)
* `args.modelName`: имя эмберной модели
* `args.modelProjection`: представление эмберной модели
* `args.successCallback`: метод или `Promise`, исполняемые при успешном исполнении запроса
* `args.failCallback`: метод или `Promise`, исполняемые при неудачном исполнении запроса
* `args.alwaysCallback`: метод или `Promise`, исполняемые в любом случае.

Свойства store, modelName и modelProjection нужны только в случае, если OData функция возвращает эмберные модели.

{% include note.html content="Для поддержки совместимости, есть возможность вызывать метод с сигнатурой callFunction(functionName, params, url, fields, successCallback, failCallback, alwaysCallback)." %}

#### Примеры использования callFunction

* Без callback-функций, URL бэкенда берем из `environment.js`:

```javascript
adapter.callFunction({ functionName: 'test', params: { someParams: 'someParams' } })
```

* С callback-функциями:

```javascript
adapter.callFunction({
  functionName: 'test',
  params: { someParams: 'someParams' },
  successCallback: () => {
    console.log("This is a successCallback function");
  },
  failCallback: () => {
    console.log("This is a failCallback function");
  },
  alwaysCallback: () => {
    console.log("This is an alwaysCallback function");
  }
})
```

### callEmberOdataFunction (Deprecated)

Аналогичен методу callFunction. Для поддержки совместимости, может быть вызван с сигнатурой callEmberOdataFunction(functionName, params, url, fields, store, modelName, successCallback, failCallback, alwaysCallback).

### callAction

Данный метод предназначен для вызова OData экшенов бэкенда. callAction(args):

* `args.actionName`: имя экшена бэкенда (единственное обязательное свойство)
* `args.data`: объект, содержащий параметры запроса
* `args.url`: адрес OData бэкенда. Если не указан – берётся адрес, указанный в `environment.js`
* `args.fields`: объект вида { имя: значение }, для изменения значений соответствующих полей объекта XMLHttpRequest
* `args.store`: объект [DS.Store](https://emberjs.com/api/ember-data/release/classes/DS.Store)
* `args.modelName`: имя эмберной модели
* `args.successCallback`: метод или `Promise`, исполняемые при успешном исполнении запроса
* `args.failCallback`: метод или `Promise`, исполняемые при неудачном исполнении запроса
* `args.alwaysCallback`: метод или `Promise`, исполняемые в любом случае.

Свойства store и modelName нужны только в случае, если OData функция возвращает эмберные модели. Если модель содержит мастеров или детейлы, то данный метод их не вернет. Нужно воспользоваться методом callFunction с указанием представления.

{% include note.html content="Для поддержки совместимости, есть возможность вызывать метод с сигнатурой callAction(actionName, data, url, fields, successCallback, failCallback, alwaysCallback)." %}

#### Примеры использования callAction

* Без callback-функций, URL бэкенда берем из `environment.js`:

```javascript
adapter.callAction({ actionName: 'test', data: { someParams: 'someParams' } })
```

* С callback-функциями:

```javascript
adapter.callAction({
  actionName: 'test',
  data: { someParams: 'someParams' },
  successCallback: () => {
    console.log("This is a successCallback function");
  },
  failCallback: () => {
    console.log("This is a failCallback function");
  },
  alwaysCallback: () => {
    console.log("This is an alwaysCallback function");
  }
})
```

### callEmberOdataAction (Deprecated)

Аналогичен методу callAction. Для поддержки совместимости, может быть вызван с сигнатурой callEmberOdataAction(actionName, data, url, fields, store, modelName, successCallback, failCallback, alwaysCallback).
