---
title: Описание модели в ember-flexberry-приложении
sidebar: ember-flexberry-data_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd_model.html
folder: product--folder
lang: ru
summary: Представлено детализированное описание того, как выглядит модель в приложении.
---

## Описание

Модели в ember-приложениях основаны на классе Ember [Data DS.Model](http://emberjs.com/api/data/classes/DS.Model.html).

{% include note.html content="Модели создаются в папку `models` и именуются следующим образом: если имя соответствующего C#-класса `NewPlatform.Someproject.Somemodel`, то название файла с моделью должно быть `new-platform-someproject-somemodel`." %}

Модели чаще всего имеют следующую структуру:

```javascript
// Импорты.
import DS from 'ember-data';
import BaseModel from 'ember-flexberry/models/base';
import Proj from 'ember-flexberry-projections';

var Model = BaseModel.extend({
  // Собственные атрибуты, мастера, детейлы.
  ...

  // Правила валидации.
  validations: {
	...
  }
});

// Определение представлений.
Model.defineProjection(
 ...
});

Model.defineProjection(
 ...
});

// Экспорт.
export default Model;
```

Импорты и экспорт требуются синтаксисом [ember-cli](http://ember-cli.com).
Создаваемая модель наследуется от базового класса `BaseModel`.
Для модели [задаются представления](efd_model-projection.html).

Существуют разные подходы к [заданию значения по умолчанию](ef_default-value.html).

## Определение модели

В модели также задаются [правила валидации модели](efd_model-validation.html).
Описание моделей происходит [стандартным для ember способом](https://guides.emberjs.com/v2.4.0/models/defining-models/).

Доступны встроенные типы "string" (строка), "number" (число), "boolean" (логический тип) и "date" (дата).

В рамках ember-flexberry была добавлена трансформация "file" (для типа "Файл").

Для [перечислений трансформации задаются особым образом](efd_enum.html).

Пример модели для класса с мастером employee1 типа 'new-platform-someproject-employee' и детейлами orders типа 'new-platform-someproject-order'.

```javascript
var Model = BaseModel.extend({
  firstName: DS.attr('string'),
  lastName: DS.attr('string'),
  birthDate: DS.attr('date'),
  photo: DS.attr('file'),
  gender: DS.attr('new-platform-someproject-gender'),
  employee1: DS.belongsTo('new-platform-someproject-employee', { inverse: null, async: false }),
  orders: DS.hasMany('new-platform-someproject-order', { inverse: 'employee', async: false }),
});
```

{% include important.html content="Имена свойств должны идти с маленькой буквы." %}

{% include warning.html content="Для **каждой** модели должен быть описан [сериализатор](efd_serializer.html), без этого не будет проходить корректное взаимодействие с сервером." %}

## Первичные ключи в модели

Первичные ключи объекта не задаются в модели явно.
В клиентском коде обращения к первичному ключу можно выполнить через [свойство "id"](http://emberjs.com/api/data/classes/DS.Model.html#property_id).

Как называется соответствующее свойство на сервере, определяется в [сериализаторе](efd_serializer.html).

## inverse-связи
Задание [inverse-связи](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations) используется, например, при работе с детейлами.

Задание связи от агрегатора к детейлу.

```javascript
var Model = BaseModel.extend({
  ...
  orders: DS.hasMany('order', { inverse: 'employee', async: false }),
});
```

Задание связи от детейла к агрегатору.

```javascript
var Model = BaseModel.extend({
  // ...
  employee: DS.belongsTo('employee', { inverse: 'orders', async: false })
});
```
