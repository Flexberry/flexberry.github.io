---
title: Поддержка транзакционной работы с данными
sidebar: flexberry-ember-3_sidebar
keywords: batch, batchUpdate, batchSelect, batchDelete, batchCreate, batchInsert
toc: true
permalink: ru/efd3_batch.html
lang: ru
summary: Поддержка транзакционной вычитки и изменения данных, примеры использования
---

## Поддержка транзакционных изменений данных

Поддержка отправки нескольких объектов модели для сохранения изменений в одной транзакции реализована через метод `batchUpdate` объекта `store`. Для получения актуального состояния объектов данных с учётом логики [бизнес-серверов](https://flexberry.github.io/ru/fd_business-servers.html) и триггеров базы данных для каждого запроса INSERT, UPDATE, DELETE отправляется дополнительный SELECT на изменяемые сущности.

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

## Вычитка данных при помощи Batch

Вычитка данных с помощью Batch может быть полезна в разных случаях. Например, если нужно загрузить несколько моделей разных типов одним запросом. Также если из-за сложных фильтров url запроса получается слишком длинный, то такой запрос может быть выполнен с помощью `batchSelect`.

Пример использования:

```javascript
const builder = new QueryBuilder(store)
  .from('ember-flexberry-dummy-application-user')
  .select('id, name')
  .where('name', '==', 'Vasya');
const builder2 = new QueryBuilder(store)
  .from('ember-flexberry-dummy-suggestion')
  .select('id, text')
  .where('text', '==', 'Test suggestion');
const queries = [builder.build(), builder2.build()];
store.batchSelect(queries);
```

В результате будет два массива. В первом все модели `ember-flexberry-dummy-application-user` с полем `name` равным `Vasya`. Во втором модели `ember-flexberry-dummy-suggestion`, у которых `text` равно `Test suggestion`.
