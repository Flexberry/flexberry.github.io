---
title: Сериализаторы в ember-flexberry-приложении
sidebar: ember-flexberry-data_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd_serializer.html
folder: product--folder
lang: ru
summary: Сериализаторы определяют, каким образом происходит десериализация и сериализация данных для сервера в ember-flexberry-приложении.
---

## Описание

Сериализатор представляет собой реализацию абстрактного класса [DS.Serializer](http://emberjs.com/api/data/classes/DS.Serializer.html).
Сериализаторы располагаются в папке `serializers` и их имя соответствует имени модели.

В настоящее время в [ember-flexberry-data](efd_landing_page.html) определён базовый сериализатор, наследованнный от [DS.RESTSerializer](http://emberjs.com/api/data/classes/DS.RESTSerializer.html).

Типичный сериализатор представлен ниже.

Сериализатор содержит миксин [DS.EmbeddedRecordsMixin](http://emberjs.com/api/data/classes/DS.EmbeddedRecordsMixin.html).

"'''primaryKey: '__PrimaryKey''''" - определяем, в каком поле хранится первичный ключ [модели](efd_model.html) при взаимодействии с сервером.

"'''attrs: { ... }'''" - описание всех ссылок на мастеров и детейлов (только первого уровня), которые присутствуют в [модели](efd_model.html).

"'''ссылкаНаМастераИлиАгрегатор: { serialize: 'odata-id', deserialize: 'records' }'''" - описание для ссылок на мастера (или агрегатора детейла). `ссылкаНаМастераИлиАгрегатор` - это имя мастерового свойства в [модели](efd_model.html) (или имя ссылки на агрегатор).

"'''ссылкаНаДетейл: { serialize: false, deserialize: 'records' }'''" - описание для ссылок детейлов. `ссылкаНаДетейл` - это имя детейлового свойства в [модели](efd_model.html).

(((
<msg type=important>При использовании работе с детейлами в [модели должны быть проставлены inverse-связи](flexberry-asp-net-ember-model.html).</msg>
)))

(((
<msg type=note>Если во всех моделях первичный ключ хранится в одном и том же поле, то можно выделить базовый сериализатор и наследоваться от него.</msg>
)))

'''odata-id''' - особый тип отношения для сериализаторов, обрабатываемый базовым сериализатором OData аддона ember-flexberry-data. Используется для передачи сведений о мастерах в правильном odata-формате: 

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

Если ссылки на мастеров и детейлов отсутствуют, то сериализатор записывают как:

```javascript
import DS from 'ember-data';
import ApplicationSerializer from './application';

export default ApplicationSerializer.extend({
  attrs: {},
  primaryKey: '__PrimaryKey'
});
```
