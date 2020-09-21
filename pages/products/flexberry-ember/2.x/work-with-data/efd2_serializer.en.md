---
title: Serializers in ember-flexberry application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/efd2_serializer.html
folder: products/ember-flexberry-data/models-and-projections/
lang: en
autotranslated: true
hash: 7a76d1743998e3e65419d4e2face62e9a4f79d3ab06f725f404b2e4401b7beab
summary: Serializers define how to deserialize and serialize data for the server in ember-flexberry application.
---

## Description

The serializer is an implementation of the abstract class [DS.Serializer](http://emberjs.com/api/data/classes/DS.Serializer.html).
Serializers are located in a folder `serializers` and their name corresponds to the name of the model.

Currently [ember-flexberry-data](efd2_landing_page.html) defined by the base serializer nasledovanie from [DS.RESTSerializer](http://emberjs.com/api/data/classes/DS.RESTSerializer.html).

A typical serializer is presented below.

The serializer contains a mixin [DS.EmbeddedRecordsMixin](http://emberjs.com/api/data/classes/DS.EmbeddedRecordsMixin.html).

""'primaryKey: '__PrimaryKey""" - define in what field stores the primary key in [models](efd2_model.html) when communicating with the server.

""'attrs: { ... } "'" the description of all references to masters and datalow (only first level), which are present in a [model](efd2_model.html).

""'selenamariegomez: { serialize: 'odata-id', deserialize: 'records' } "'" the description for links to the wizard (or the aggregator of detail). `ссылкаНаМастераИлиАгрегатор` is the name of the workman properties in [models](efd2_model.html) (or the name links to the aggregator).

""'salenatural: { serialize: false, deserialize: 'records' } "'" the description for links detailov. `ссылкаНаДетейл` is the name metalowego properties in [models](efd2_model.html).

{% include important.html content="When using working with detaylari in [models should be marked with inverse-context](efd2_model.html)." %}

{% include note.html content="If all the models primary key is stored in the same field, we can identify the underlying serializer to inherit from him." %}

"'odata-id"' - a special type of relationship for serializers that are processed by the underlying serializer OData addon ember-flexberry-data. Used to pass information about the masters in proper odata format:

```
'<ИмяСвязиДоМастера>@odata.bind': '<ТипМастера>s(<ИдентификаторМастера>')
Например, "'ReportsTo@odata.bind': 'Employees(502431BA-3B85-4A97-AF53-7AD193ED6AEC)'"
```

```javascript
import DS from 'ember-data';
import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {
    ссылкаНаМастераИлиАгрегатор: { serialize: 'odata-id', deserialize: 'records' },
    ссылкаНаДетейл: { serialize: false, deserialize: 'records' }
  },
  primaryKey: '__PrimaryKey'
});
```

If references to masters and detailhow do not exist, then the serializer is written as:

```javascript
import DS from 'ember-data';
import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {},
  primaryKey: '__PrimaryKey'
});
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}