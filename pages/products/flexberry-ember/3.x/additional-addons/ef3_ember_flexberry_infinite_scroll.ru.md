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

* modelProjection - название проекции, где определены отображаемые на списке свойства модели.
* infiniteModel - название модели, чьи свойства будут отображены на списке.
* estimateRowHeight - высота ячеек таблицы компонента.
* bufferSize - сколько дополнительных строк (сверх видимых) должно быть загружено и отображено (помогает сгладить переходы между страницами и обеспечивает более плавную прокрутку).

В контроллере прописываем модель и экшен догрузки. Контроллер может выглядеть так:

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
        const modelName = this.get('modelName');
        const projectionName = this.get('projectionName');
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
