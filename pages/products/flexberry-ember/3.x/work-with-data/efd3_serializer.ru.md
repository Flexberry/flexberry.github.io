---
title: Сериализаторы в сгенерированных приложениях
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_serializer.html
lang: ru
summary: Обзор структуры сериализаторов в сгенерированных приложениях.
---

## Описание

Для **каждой** [модели](https://guides.emberjs.com/v3.1.0/models/defining-models/) должен быть описан [сериализатор](https://guides.emberjs.com/v3.1.0/models/customizing-serializers/) для корректного взаимодействия с сервером. Сериализаторы располагаются в папке `serializers` и их имя соответствует имени модели.

В настоящее время в технологии Flexberry Ember определён базовый сериализатор, наследованнный от [DS.RESTSerializer](http://emberjs.com/api/data/classes/DS.RESTSerializer.html), а также базовый [офлайн-сериализатор](), наследованный от [DS.JSONSerializer](http://emberjs.com/api/data/classes/DS.JSONSerializer.html).

[Генерируемые из Flexberry Designer](efd3_generated-app-structure.html) [сериализаторы](https://guides.emberjs.com/v3.1.0/models/customizing-serializers/) чаще всего имеют следующую структуру

```js
import { Serializer as AgregatorClassSerializer } from
  '../mixins/regenerated/serializers/i-i-s-gen-test-agregator-class';
import __ApplicationSerializer from './application';

export default __ApplicationSerializer.extend(AgregatorClassSerializer, {
  // Имя поля, где хранится первичный ключ.
  primaryKey: '__PrimaryKey'
});
```

* `primaryKey: '__PrimaryKey'` - определение, в каком поле хранится [первичный ключ модели](efd3_model.html) при взаимодействии с сервером.

Для каждого сериализатора в папку `mixins\regenerated\serializers` генерируется миксин следующего вида (также там присутствует миксин для [офлайн-сериализатора]() с аналогичным именем):

```js
import Mixin from '@ember/object/mixin';
import $ from 'jquery';

export let Serializer = Mixin.create({
  getAttrs: function () {
    let parentAttrs = this._super();
    let attrs = {
      child2: { serialize: 'odata-id', deserialize: 'records' },
      masterForAgregator: { serialize: 'odata-id', deserialize: 'records' },
      detailForAgregator: { serialize: false, deserialize: 'records' }
    };

    return $.extend(true, {}, parentAttrs, attrs);
  },
  init: function () {
    this.set('attrs', this.getAttrs());
    this._super(...arguments);
  }
});
```

* `attrs` - описание всех ссылок на мастеров и детейлов (только первого уровня), которые присутствуют в [модели](efd3_model.html) с учётом наследованных от базовой модели.
* `ссылкаНаМастераИлиАгрегатор: { serialize: 'odata-id', deserialize: 'records' }` - описание для ссылок на мастера (или агрегатора детейла). `ссылкаНаМастераИлиАгрегатор` - это имя мастерового свойства в модели (или имя ссылки на агрегатор). В конкретном примере ссылками на мастера являются `child2` и `masterForAgregator`.
* `ссылкаНаДетейл: { serialize: false, deserialize: 'records' }` - описание для ссылок детейлов. `ссылкаНаДетейл` - это имя детейлового свойства в [модели](efd3_model.html). В конкретном примере ссылкой на детейл является `detailForAgregator`.
* `odata-id` - особый тип отношения для сериализаторов, обрабатываемый базовым сериализатором OData технологии Flexberry Ember. Используется для передачи сведений о мастерах в правильном odata-формате (такая конструкция будет в запросе, например, при сохранении модели): 

```
'<ИмяСвязиДоМастера>@odata.bind': '<ТипМастера>s(<ИдентификаторМастера>')
Например, "'ReportsTo@odata.bind': 'Employees(502431BA-3B85-4A97-AF53-7AD193ED6AEC)'"
```
