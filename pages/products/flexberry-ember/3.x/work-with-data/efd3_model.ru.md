---
title: Модели в сгенерированных приложениях
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_model.html
lang: ru
summary: Обзор структуры моделей в сгенерированных приложениях.
---

## Особенности объявления сгенерированных моделей в коде

Модели в технологии Flexberry Ember определяются ["стандартным" для Ember способом](https://guides.emberjs.com/v3.1.0/models/defining-models/).
Создаваемая модель наследуется от [базового технологического класса](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/models/model.js) и предоставляет возможность применять [правила валидации модели](efd3_model-validation.html).

[Генерируемые из Flexberry Designer](efd3_generated-app-structure.html) [модели](https://guides.emberjs.com/v3.1.0/models/defining-models/) чаще всего имеют следующую структуру

```js
// Импорт для валидации.
import { buildValidations } from 'ember-cp-validations';

// Импорт базового класса для моделей во Flexberry Ember.
import EmberFlexberryDataModel from 'ember-flexberry-data/models/model';

// Импорт модели для работы в офлайн-режиме.
import OfflineModelMixin from 'ember-flexberry-data/mixins/offline-model';

// Проекции, правила валидации и сама модель определяются из соответсвующего миксина.
import {
  defineProjections,
  ValidationRules,
  Model as AgregatorClassMixin
} from '../mixins/regenerated/models/i-i-s-gen-test-agregator-class';

// Подготовка для задания на модель правил валидации данных.
const Validations = buildValidations(ValidationRules, {
  dependentKeys: ['model.i18n.locale'],
});

// Непосредственно определение модели.
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, AgregatorClassMixin, Validations, {
});

// Определение проекций (сами проекции заданы в миксине).
defineProjections(Model);

// Экспорт модели.
export default Model;
```

Соответствующий модели [миксин](https://api.emberjs.com/ember/3.1/classes/Mixin) с таким же именем расположен в папке `mixins/regenerated/models`. Его структура следующая:

```js
// Необходимые импорты.
import Mixin from '@ember/object/mixin';
import $  from 'jquery';
import DS from 'ember-data';
import { validator } from 'ember-cp-validations';

// Импорт для объявления проекций.
import { attr, belongsTo, hasMany } from 'ember-flexberry-data/utils/attributes';

// Импорт для перечислимого типа.
import Enum1TypeEnum from '../../../enums/i-i-s-gen-test-enum1-type';

// Объявление атрибутов модели.
export let Model = Mixin.create({
  ...
});

// Объявление типичных правил валидации.
export let ValidationRules = {
  ...
};

// Объявление проекций.
export let defineProjections = function (modelClass) {
  ...
};
```

[Генерируемые из Flexberry Designer](efd3_generated-app-structure.html) [модели](https://guides.emberjs.com/v3.1.0/models/defining-models/) создаются в папку `models` и именуются следующим образом:

* если соответствующий C#-класс на [OData-бэкенде](fo_orm-odata-service.html) называется `NewPlatform.Someproject.Somemodel`, то файл с моделью в клиентском приложении должен называться `new-platform-someproject-somemodel`,
* если на [OData-бэкенде](fo_orm-odata-service.html) используется атрибут [PublishName](fo_metadata-for-client.html) для упрощения именования моделей, то наименование пространства имен в этом случае в клиентской модели может отсутствовать (имя клиентской модели будет формироваться соответственно имени в EDM-модели на OData-бакенде).

### Объявление модели, имеющей предка
Если у модели есть класс-предок, то для класса-потомка объявление модели будет чуть отличаться:

```js
import $ from 'jquery';
import { buildValidations } from 'ember-cp-validations';

import {
  defineBaseModel, // Импорт метода для работы с моделью предка.
  defineProjections,
  ValidationRules,
  Model as Child1Mixin
} from '../mixins/regenerated/models/i-i-s-gen-test-child1';

// Импорт модели предка.
import ParentClassModel from './i-i-s-gen-test-parent-class';

// Импорт правил валидации для модели предка.
import { ValidationRules as ParentValidationRules } from '../mixins/regenerated/models/i-i-s-gen-test-parent-class';

// Определение правил валидации с учётом правил валидации модели предка.
const Validations = buildValidations($.extend({}, ParentValidationRules, ValidationRules), {
  dependentKeys: ['model.i18n.locale'],
});

// Модель является расширением ParentClassModel, а не EmberFlexberryDataModel.
let Model = ParentClassModel.extend(Child1Mixin, Validations, {
});

// Определение элементов от базовой модели.
defineBaseModel(Model);
defineProjections(Model);

export default Model;
```

Соответствующий [миксин](https://api.emberjs.com/ember/3.1/classes/Mixin) отличается тем, что добавлено определение `defineBaseModel`:
```js
...
export let defineBaseModel = function (modelClass) {
  modelClass.reopenClass({
    _parentModelName: 'i-i-s-gen-test-parent-class'
  });
};
...
```

## Базовые классы и миксины для моделей Flexberry Ember

В технологии `Flexberry Ember` доступны следующие базовые модели:

* [`ModelWithoutValidation`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Projection.ModelWithoutValidation.html) - базовая модель с поддержкой проекций и копирования.
* [`Model`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Projection.Model.html) - базовая модель, наследуемая от [`ModelWithoutValidation`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Projection.ModelWithoutValidation.html), с поддержкой валидации ([генерируемые модели](efd3_generated-app-structure.html) наследуются от неё).
* [`OfflineModel`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Offline.Model.html) - базовая модель, наследуемая от [`Model`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Projection.Model.html), для работы в [офлайн-режиме]().

В технологии `Flexberry Ember` доступны следующие базовые миксины для моделей:

* [`CopyableMixin`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/CopyableMixin.html) - миксин для поддержки возможности создания по прототипу, используется в базовой модели [`ModelWithoutValidation`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Projection.ModelWithoutValidation.html).
* [`AuditModelMixin`](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/mixins/audit-model.js) - миксин для работы [подсистемы аудита](), используется в миксине [`OfflineModelMixin`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Offline.ModelMixin.html).
* [`OfflineModelMixin`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Offline.ModelMixin.html) - миксин, добавляющий свойства для решения вопросов синхронизации, используется в базовой модели [`OfflineModel`](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Offline.Model.html), а также добавляется в сгенерированную модель.

## Правила генерации атрибутов и связей в модели
Атрибуты в моделях определяются ["стандартным" для Ember способом](https://guides.emberjs.com/v3.1.0/models/defining-models/#toc_defining-attributes), [также как и связи](https://guides.emberjs.com/v3.1.0/models/relationships/).

```js
export let Model = Mixin.create({
  // Определение собственных атрибутов.
  doubleField: DS.attr('decimal'),
  stringField: DS.attr('string'),

  // Определение мастера.
  myMaster: DS.belongsTo('i-i-s-gen-test-master-for-child1', { inverse: null, async: false }),

  // Определение детейлов.
  detail1ForChild1: DS.hasMany('i-i-s-gen-test-detail1-for-child1', { inverse: 'child1', async: false }),
  detail2ForChild1: DS.hasMany('i-i-s-gen-test-detail2-for-child1', { inverse: 'child1', async: false })
});
```

{% include important.html content="Имена свойств должны начинаться с маленькой буквы." %}

{% include note.html content="Помимо непосредственно объявленных атрибутов в модели могут быть добавлены поля [подсистемы аудита]() (`createTime`, `creator`, `editTime`, `editor`)." %}

### Генерируемые типы и трансформации
Дополнительно к стандартным типам данных для атрибутов моделей (`string` (строка), `number` (число), `boolean` (логический тип) и `date` (дата)) в технологии`Flexberry Ember` были добавлены [трансформации](https://guides.emberjs.com/v3.1.0/models/defining-models/#toc_transforms):
* `decimal` (вещественный тип),
* `file` (тип "Файл"),
* [`flexberry-enum`]() (тип для перечислений),
* `guid` (тип "GUID", который используется по умолчанию для идентификаторов).

### inverse-связи в модели

Задание [inverse-связи](https://guides.emberjs.com/v3.1.0/models/relationships/#toc_reflexive-relations) используется, например, при работе с детейлами.

Задание связи от агрегатора к детейлу.

```javascript
export let Model = Mixin.create({
  ...
  detail1ForChild1: DS.hasMany('i-i-s-gen-test-detail1-for-child1', { inverse: 'child1', async: false }),
});
```

Задание связи от детейла к агрегатору.

```javascript
export let Model = Mixin.create({
  ...
  child1: DS.belongsTo('i-i-s-gen-test-child1', { inverse: 'detail1ForChild1', async: false })
});
```

### Первичный ключ в модели
[Первичные ключи объекта](fo_primary-keys-objects.html) не задаются в модели явно.
В клиентском коде обращения к первичному ключу можно выполнить через [свойство `id`](http://emberjs.com/api/data/classes/DS.Model.html#property_id). Как называется соответствующее свойство на сервере, определяется в [сериализаторе](efd3_serializer.html).

```js
import { Serializer as MasterForAgregatorSerializer } from
  '../mixins/regenerated/serializers/i-i-s-gen-test-master-for-agregator';
import __ApplicationSerializer from './application';

export default __ApplicationSerializer.extend(MasterForAgregatorSerializer, {
  /**
  * Имя поля, где хранится первичный ключ модели.
  */
  primaryKey: '__PrimaryKey'
});
```

Первичные ключи моделей в `Ember`-приложениях всегда являются строками, но на сервере [это поведение можно изменить](fo_primary-keys-objects.html). При изменении типа первичного ключа на сервере необходимо переопределить статическое свойство `idType` в классе модели:

```javascript
import EmberFlexberryDataModel from 'ember-flexberry-data/models/model';

...

let Model = EmberFlexberryDataModel.extend( ... );

...

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

Тип первичного ключа - это метаданные модели, поэтому свойство `idType` определено именно в модели, а не, например, в адаптере.

Получить тип ключа можно через метод `getMeta` утилиты `information` (см. ниже).

В языке запросов тип ключа учитывается автоматически, и при построении запросов к OData-бакенду значения ключей в URL запросов "окавычиваются" только в том случае, если тип ключа `string`.

{% include important.html content="На данный момент поддерживается 3 типа ключей в клиентской части: `string`, `guid` и `number`. В других случаях при построении запросов к OData-бакенду будет выбрасываться исключение." %}

{% include note.html content="По умолчанию в клиентской модели в качестве типа первичного ключа используется `guid`." %}

## Проекции в моделях
Проекции используются для определения, какие свойства будут запрошены с сервера или отправлены на него. Определение проекций для модели осуществляется следующим образом:

```javascript
Model.defineProjection('<Имя проекции>', '<Имя класса>', '<Атрибуты проекции>');
```

* **Имя проекции** может быть произвольным. Чаще всего для [форм редактирования и создания](efd3_editform.html) используют представления с именем "<Короткое имя класса>E", а для [списковых форм](efd3_listform.html) - "<Короткое имя класса>L" (например, для модели `new-platform-gen-test-agregator-class` это будут `AgregatorClassE` и `AgregatorClassL`).
* **Имя класса** - это имя текущего класса, для которого определяется модель. Например, `new-platform-someproject-somemodel`.
* **Атрибуты проекции** - это атрибуты модели и зависимых моделей, которые входят в проекцию.

В примере ниже для модели `i-i-s-gen-test-agregator-class` определяется проекция `AgregatorClassE`.

```js
// Для модели i-i-s-gen-test-agregator-class определяется проекция AgregatorClassE.
modelClass.defineProjection('AgregatorClassE', 'i-i-s-gen-test-agregator-class', {
    // Добавлен атрибут перечислимого типа.
    enum1Field: attr('Перечисление 1', { index: 0 }),

    // Добавлена ссылка на мастера типа i-i-s-gen-test-child2.
    child2: belongsTo('i-i-s-gen-test-child2', 'Мастер потомок', {
      dateTimeField: attr('~', { index: 2, hidden: true })
    }, { index: 1, displayMemberPath: 'dateTimeField' }),

    // Добавлена ссылка на мастера типа i-i-s-gen-test-master-for-agregator.
    masterForAgregator: belongsTo('i-i-s-gen-test-master-for-agregator', 'Master for agregator', {
      enum2Field: attr('~', { index: 4, hidden: true })
    }, { index: 3, displayMemberPath: 'enum2Field' }),

    // Добавлена ссылка на детейл типа i-i-s-gen-test-detail-for-agregator.
    detailForAgregator: hasMany('i-i-s-gen-test-detail-for-agregator', 'Детейл агрегатора', {
      // Добавлен атрибут детейла целого типа.
      detailIntField: attr('Целое', { index: 0 }),

      // Добавлена ссылка на мастера детейла типа i-i-s-gen-test-master-for-agregator.
      masterForAgregator: belongsTo('i-i-s-gen-test-master-for-agregator', 'Мастеровое', {
        enum2Field: attr('~', { index: 2, hidden: true })
      }, { index: 1, displayMemberPath: 'enum2Field' })
    })
  });
```

* `enum1Field: attr('Перечисление 1', { index: 0 })` - в проекцию модели `i-i-s-gen-test-agregator-class` добавляется свойство `enum1Field` модели `i-i-s-gen-test-agregator-class` с заголовком `Перечисление 1`.
* `child2: belongsTo('i-i-s-gen-test-child2', 'Мастер потомок', { ... }, { index: 1, displayMemberPath: 'dateTimeField' })` - в проекцию модели `i-i-s-gen-test-agregator-class` добавляется ссылка на мастера `child2`  типа `i-i-s-gen-test-child2` с заголовком 'Мастер потомок', при этом на форме у данного свойства будет отображаться атрибут мастера `dateTimeField`. Сам же атрибут мастера `dateTimeField`, добавляемый кодом `dateTimeField: attr('~', { index: 2, hidden: true })`, скрыт (такое скрытие свойств мастеров часто используется для работы [лукапов](efd3_lookup.html)).
* `detailForAgregator: hasMany('i-i-s-gen-test-detail-for-agregator', 'Детейл агрегатора', { ... })` - в проекцию модели `i-i-s-gen-test-agregator-class` добавляется ссылка на детейлы `detailForAgregator` типа  `i-i-s-gen-test-detail-for-agregator` с заголовком 'Детейл агрегатора'. Из детейлов в представление попадают собственные свойства детейла, а также ссылка на мастера детейлов.

{% include important.html content="В связи с тем, что при наличии детейлов в проекции также указываются и собственные свойства детейлов, при изменении представления детейлов во [Flexberry Designer](fd_flexberry-designer.html) и последующей перегенерации требуется произвести перегенерацию как самих детейлов, так и агрегатора, чтобы проекция агрегатора обновилась правильно." %}

## Генерируемые сериализаторы для моделей

{% include warning.html content="Для **каждой** модели должен быть описан [сериализатор](efd3_serializer.html) для корректного взаимодействия с сервером." %}

## Метаданные модели и вспомогательный класс information

Вспомогательный класс [Information](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/information.js) позволяет получать метаданные [модели](efd3_model.html). Список доступных методов можно посмотреть в [автодокументации](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Utils.Information.html#index).

Ниже представлен пример использования [Information](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/information.js): в [роуте](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) [форм создания](efd3_editform.html) определяется тип поля `stringField`, и соответствующее значение записывается в поле `stringField` созданной модели.

```js
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

// Экспорт вспомогательного класса для последующей работы с ним.
import Information from 'ember-flexberry-data/utils/information';

export default EditFormNewRoute.extend({
  modelProjection: 'Child1E',
  modelName: 'i-i-s-gen-test-child1',
  templateName: 'i-i-s-gen-test-child1-e',

  afterModel(resolvedModel, transition){
    this._super(...arguments);

    // Создание экземпляра класса Information. Во Flexberry-эмбер приложениях в роутах и контроллерах инжектится сервис store и явным образом его получать не требуется.
    let information = new Information(this.get('store'));

    // Получение типа поля 'stringField' созданной модели.
    let fieldType = information.getType(this.get('modelName'), 'stringField');

    // Задание типа поля в качестве значения 'stringField'.
    resolvedModel.set('stringField', fieldType);
  }
});
```

## Вспомогательные модели Flexberry Ember

Вспомогательные модели Flexberry Ember включают:

* [модели](https://github.com/Flexberry/ember-flexberry-data/tree/develop/addon/models) для аудита. Структура хранения данных аудита аналогична [используемой во Flexberry ASP.Net](fa_audit-web.html). В настоящее время ведётся разработка нового варианта аудита для `Flexberry Ember`.
* [модели](https://github.com/Flexberry/ember-flexberry-data/tree/develop/addon/models) для [полномочий](efs_security_schema.html).
* [`flexberry-adv-limit`](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/models/flexberry-adv-limit.js) - модель для [задания ограничений](efd3_query-language.html).
* [`i-i-s-caseberry-logging-objects-application-log`](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/models/i-i-s-caseberry-logging-objects-application-log.js) - модель для [сервиса логирования](efd3_log-service.html).
* [`new-platform-flexberry-flexberry-user-setting`](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/models/new-platform-flexberry-flexberry-user-setting.js) - модель для [сервиса пользовательских настроек]().
* [`new-platform-flexberry-services-lock`](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/models/new-platform-flexberry-services-lock.js) - модель для [сервиса пессимистических блокировок]().
