---
title: Аддон Ember flexberry infinite scroll
sidebar: flexberry-ember-3_sidebar
keywords: Ember Flexberry infinite scroll
toc: true
permalink: ru/ef3_ember_flexberry_infinite_scroll.html
lang: ru
summary: Описание аддона Ember flexberry infinite scroll
---

## Описание аддона

`ember-flexberry-infinite-scroll` - аддон для [ember-flexberry](https://github.com/Flexberry/ember-flexberry/tree/develop) с компонентом бесконечной прокрутки.

## Установка

```cmd
ember install ember-flexberry-infinite-scroll
```

Требуется версия node 12 или выше.

## Использование

В шаблоне для использования компонента прописать:

```hbs
{{flexberry-infinite-scroll
  modelProjection=modelProjection
  content=infiniteModel
  lastReached=(action "lastReached")
  estimateRowHeight=20
  bufferSize=1
}}
```

где:

* `modelProjection` - объект проекции, где определены отображаемые на списке свойства модели.
* `infiniteModel` - массив записей из модели, отображаемых в данных момент.
* `estimateRowHeight` - высота ячеек таблицы компонента.
* `bufferSize` - сколько дополнительных строк (сверх видимых) должно быть загружено и отображено (помогает сгладить переходы между страницами и обеспечивает более плавную прокрутку).

В контроллере прописываем вычислимое свойство записей модели и экшен догрузки `lastReached`.

Событие `lastReached` срабатывает, когда пользователь прокручивает таблицу до последней видимой строки.
Это позволяет обнаружить, когда пользователь достиг конца таблицы, и загрузить дополнительные данные, чтобы продолжить отображение.

Контроллер может выглядеть так:

```js
import Controller from '@ember/controller';
import { computed } from '@ember/object';
import Builder from 'ember-flexberry-data/query/builder';

export default Controller.extend({

  infiniteModel: computed('model', function() {
    return this.get('model').toArray();
  }),

  actions: {
    lastReached() {
      let infiniteModel = this.get('infiniteModel');
      if (this.get('model.meta.count') > infiniteModel.length) {
        const modelName = this.get('modelName'); // название модели строкой
        const projectionName = this.get('projectionName'); // название проекции строкой
        const store = this.store;
        const builder = new Builder(store)
          .from(modelName)
          .selectByProjection(projectionName)
          .top(15)
          .skip(infiniteModel.length)
          .orderBy('name asc')
          .count();
    
        return store.query(modelName, builder.build()).then((result) => {
          infiniteModel.pushObjects(result.toArray());
        });
      }
    }
  }
});
```
