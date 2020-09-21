---
title: Описание модели в ember-flexberry-приложении
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd2_model.html
lang: ru
summary: Представлено детализированное описание того, как выглядит модель в приложении.
---

## Описание

Модели в ember-приложениях наследуются от класса Ember Data [DS.Model](http://emberjs.com/api/data/classes/DS.Model.html).

{% include note.html content="Модели создаются в папку `models` и именуются следующим образом: если соответствующий C#-класс называется `NewPlatform.Someproject.Somemodel`, то файл с моделью должен называться `new-platform-someproject-somemodel`. Если на OData-бакенде используется атрибут [`PublishName` для упрощения именования моделей](https://flexberry.github.io/ru/fo_metadata-for-client.html), то наименование пространства имен в этом случае в клиентской модели может отсутствовать (имя клиентской модели будет соответствующим образом формироваться из имени в EDM-модели на OData-бакенде)" %}

Модели чаще всего имеют следующую структуру:

```javascript
// Импорты.
import DS from 'ember-data';
import { Projection } from 'ember-flexberry-data';

var Model = Projection.Model.extend({
  // Собственные атрибуты, мастера, детейлы.
  name: DS.attr('string'),
  someMaster: DS.belongsTo('new-platform-someproject-somemaster', { inverse: 'somemodel', async: false, polymorphic: true }),
  ...

  // Правила валидации.
  validations: {
    name: { presence: true }
    ...
  }
});

// Определение модели-предка (при наличии).
Model.reopenClass({
  _parentModelName: '...' // Указывается имя модели, от которой наследуется данная модель, например 'new-platform-someproject-parent'.
});

// Определение представлений.
Model.defineProjection(
  name: Projection.attr(''),
  someMaster: Projection.belongsTo('new-platform-someproject-somemaster', '', {
    ...
  })
  ...
});

Model.defineProjection(
 ...
});

// Экспорт модели.
export default Model;
```

Импорты и экспорт соответствуют требованиям синтаксиса [ember-cli](http://ember-cli.com).
Создаваемая модель наследуется от [базового технологического класса](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/models/model.js), определенного в аддоне [`ember-flexberry-data`](https://github.com/Flexberry/ember-flexberry-data).
Для модели [задаются представления](efd2_model-projection.html).

Существуют также разные подходы к [заданию значения по умолчанию](ef2_default-value.html) для атрибутов модели.

## Определение модели

Модели определяются ["стандартным" для Ember способом](https://guides.emberjs.com/v2.4.0/models/defining-models/).

Дополнительно в моделях, унаследованных от базовой технологической модели, используются [правила валидации модели](efd2_model-validation.html).

Для атрибутов любых моделей Ember доступны встроенные типы данных `string` (строка), `number` (число), `boolean` (логический тип) и `date` (дата). Для определения других типов данных в моделях Ember используются [трансформации](https://guides.emberjs.com/v2.4.0/models/defining-models/#toc_transforms).

Дополнительно к стандартным типам данных в аддоне `ember-flexberry-data` были добавлены трансформации `decimal` (вещественный тип), `file` (тип "Файл"), `flexberry-enum` (тип для перечислений), `guid` (тип "GUID", который используется по умолчанию для первичных ключей).

Подробнее об использовании трансформаций для перечислений можно посмотреть [тут](efd2_enum.html).

Пример модели для класса с мастером `employee1` типа 'new-platform-someproject-employee' и детейлами `orders` типа 'new-platform-someproject-order'.

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

{% include important.html content="Имена свойств должны начинаться с маленькой буквы." %}

{% include warning.html content="Для **каждой** модели должен быть описан [сериализатор](efd2_serializer.html) для корректного взаимодействия с сервером." %}

## Первичные ключи в модели

Первичные ключи объекта не задаются в модели явно.
В клиентском коде обращения к первичному ключу можно выполнить через [свойство `id`](http://emberjs.com/api/data/classes/DS.Model.html#property_id).

Как называется соответствующее свойство на сервере, определяется в [сериализаторе](efd2_serializer.html).

### Тип первичного ключа

__Первичные ключи моделей__ в `Ember`-приложениях всегда являются строками, но на сервере [это поведение можно изменить](fo_primary-keys-objects.html).
При изменении типа первичного ключа на сервере необходимо переопределить статическое свойство `idType` в классе модели:

```javascript
import { Projection } from 'ember-flexberry-data';

let Model = Projection.Model.extend({
  // ...
});

Model.reopenClass({
  idType: '...',
});

export default Model;
```

Устанавливается свойство `idType` при помощи статической функции `defineIdType` в базовой технологической модели:

```javascript
defineIdType: function (newIdType) {
  this.reopenClass({
    idType: newIdType,
  });
},
```

Вызвать этот метод можно следующим образом:

```javascript
Model.defineIdType('string');
```

__Тип первичного ключа__ - это метаданные модели, поэтому свойство `idType` определено именно в модели, а не, например, в адаптере.

Получить тип ключа можно через метод [`getMeta` утилиты `information`](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/information.js#L137) из аддона `ember-flexberry-data`.

В языке запросов тип ключа учитывается автоматически, и при построении запросов к OData-бакенду значения ключей в URL запросов "окавычиваются" только в том случае, если тип ключа - `string`.
На данный момент поддерживается 3 типа ключей в клиентской части: `string`, `guid` и `number`. В других случаях при построении запросов к OData-бакенду будет выбрасываться исключение.

{% include note.html content="По умолчанию в клиентской модели в качестве типа первичного ключа используется `guid`." %}

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
