---
title: Модели в ember-flexberry-приложении
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_generated-model.html
lang: ru
summary: Представлено детализированное описание того, какие модели находятся в сгенерированном ember-flexberry-приложении (папка models), и их структура.
---

## Модели в приложении
При генерации в клиентское приложение добавляются следующие модели (папка models)

![Модели в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-models.png).

Выделены модели, соответствующие классам данных, о структуре которых речь пойдёт ниже.

{% include note.html content="Модели создаются в папку `models` и именуются следующим образом: если соответствующий C#-класс называется `NewPlatform.Someproject.Somemodel`, то файл с моделью должен называться `new-platform-someproject-somemodel`. Если на OData-бакенде используется атрибут [`PublishName` для упрощения именования моделей](https://flexberry.github.io/ru/fo_metadata-for-client.html), то наименование пространства имен в этом случае в клиентской модели может отсутствовать (имя клиентской модели будет соответствующим образом формироваться из имени в EDM-модели на OData-бакенде)" %}

Также генерируется 'custom-inflector-rules.js', где посредством [Ember Inflector](https://github.com/emberjs/ember-inflector) задаётся соответствие имён в единственном и множественном числе (если присмотреться, то можно заметить, что там указаны последние слова в именах моделей, соответствующих классам данных).

```javascript
import Inflector from 'ember-inflector';

const inflector = Inflector.inflector;

inflector.irregular('agregator', 'Agregators');
inflector.irregular('child1', 'Child1s');
inflector.irregular('child2', 'Child2s');
inflector.irregular('class', 'Classs');

export default {};
```

## Описание

Модели определяются ["стандартным" для Ember способом](https://guides.emberjs.com/v3.20.0/models/defining-models/) и чаще всего имеют следующую структуру:

```javascript
// Импорты.
import { buildValidations } from 'ember-cp-validations';
import EmberFlexberryDataModel from 'ember-flexberry-data/models/model';
import OfflineModelMixin from 'ember-flexberry-data/mixins/offline-model';

// Импорты вынесенных в миксины частей модели.
import {
  defineProjections,
  ValidationRules,
  Model as AgregatorClassMixin
} from '../mixins/regenerated/models/i-i-s-gen-test-agregator-class';

// Определение правил валидации данных.
const Validations = buildValidations(ValidationRules, {
  dependentKeys: ['model.i18n.locale'],
});

// Объявление модели.
let Model = EmberFlexberryDataModel.extend(OfflineModelMixin, AgregatorClassMixin, Validations, {
});

// Объявление представлений.
defineProjections(Model);

// Экспорт модели.
export default Model;
```

В случае наличия класса-предка структура модели будет чуть иной (в примере класс 'IISGenTestParentClass' является предком класса 'IISGenTestChild1')

```javascript
// Импорты.
import $ from 'jquery';
import { buildValidations } from 'ember-cp-validations';

// Импорты вынесенных в миксины частей модели.
import {
  defineBaseModel,
  defineProjections,
  ValidationRules,
  Model as Child1Mixin
} from '../mixins/regenerated/models/i-i-s-gen-test-child1';

// Импорты модели-предка.
import ParentClassModel from './i-i-s-gen-test-parent-class';
import { ValidationRules as ParentValidationRules } from '../mixins/regenerated/models/i-i-s-gen-test-parent-class';

// Определение правил валидации данных с учётом правил модели-предка.
const Validations = buildValidations($.extend({}, ParentValidationRules, ValidationRules), {
  dependentKeys: ['model.i18n.locale'],
});

// Объявление модели.
let Model = ParentClassModel.extend(Child1Mixin, Validations, {
});

// Объявление модели предка.
defineBaseModel(Model);

// Определение представлений.
defineProjections(Model);

// Экспорт модели.
export default Model;
```

Импорты и экспорт соответствуют требованиям синтаксиса [ember-cli](http://ember-cli.com).
Создаваемая модель наследуется от [базового технологического класса](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/models/model.js), определенного в аддоне [`ember-flexberry-data`](https://github.com/Flexberry/ember-flexberry-data).

Дополнительно в моделях, унаследованных от базовой технологической модели, используются правила валидации модели и задаются представления (однако непосредственно атрибуты модели, правила валидации и представления, получаемые при генерации, описаны в соответствующих [миксинах](efd3_generated-mixin.html)).

{% include warning.html content="Для **каждой** модели должен быть описан [сериализатор](efd3_generated-serializer.html) для корректного взаимодействия с сервером." %}
