---
title: Валидация данных
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember Validations
toc: true
permalink: ru/efd3_validations.html
folder: products/flexberry-ember/3.x/work-with-data/
lang: ru
summary: Описание валидации и её настройки в приложениях ember-flexberry
---

Практически неотъемлемой частью любого приложения, работающего с пользовательским вводом данных, является валидация этих данных. Валидация служит не только для предотвращения ошибок в приложении, но и для облегчения работы пользователя с приложением, предоставляя ему подсказки по ожидаемым форматам и ограничениям вводимых данных.  

Валидация данных в приложениях на основе `Flexberry Ember` дополняет серверную валидацию, обычно реализуемую с помощью [бизнес-сервера объектов данных Flexberry ORM](fo_business-server.html).  

По умолчанию, валидация данных осуществляется на уровне модели, поскольку модель является основной единицой пользовательского ввода. Валидация модели описывается в виде набора правил для свойств модели, которые должны соблюдатся, чтобы объект модели считался валидным. В контроллере формы редактирования, перед сохранением объекта модели, проверяется валидность этого объекта, по описанным правилам.

## Описание правил валидации

Способ описания правил валидации зависит от используемого в приложении аддона для валидации, это может быть [`ember-cp-validations`](#ember-cp-validations) или [`ember-validations`](#ember-validations). При генерации модели по её метаданным, также генерируется набор правил для её валидации, который зависит от различных параметров, указанных для атрибутов модели.

### ember-cp-validations

Аддон [`ember-cp-validations`](https://github.com/offirgolan/ember-cp-validations) имеет большой набор встроенных валидаторов гибко настраиваемых с помощью параметров, что позволяет описывать сложные правила валидации модели.

Чтобы реализвать валидацию модели с использованием аддона `ember-cp-validations`, необходимо использовать функцию `buildValidations`, предоставляемую этим аддоном, функция создаёт миксин, который нужно добавить к классу модели.

Если в приложении установлен аддон `ember-cp-validations`, вместе с миксином модели, будет сгенерирован объект `ValidationRules`, содержащий валидаторы для свойств модели. На основе объекта `ValidationRules` будет создан миксин, и добавлен к классу модели. По умолчанию генерируются следующие правила валидации:

* [`presence`](http://offirgolan.github.io/ember-cp-validations/docs/classes/Presence.html) - для свойств с типами `string` и `boolean` и установленным атрибутом `NotNull`, а так же обязательных связей один к одному
* [`number`](http://offirgolan.github.io/ember-cp-validations/docs/classes/Number.html) - для свойств с типами `number` и `decimal`
* [`date`](http://offirgolan.github.io/ember-cp-validations/docs/classes/Date.html) - для свойств с типом `date`
* [`has-many`](http://offirgolan.github.io/ember-cp-validations/docs/classes/Has%20Many.html) - для множественных связей
* [`ds-error`](http://offirgolan.github.io/ember-cp-validations/docs/classes/DS%20Error.html) - для всех свойств модели

Чтобы изменить правила валидации для модели, можно расширить объект `ValidationRules`, или описать новый объект с нужными правилами, например:

```js
// app/models/user.js
import DS from 'ember-data';
import { buildValidations, validator } from 'ember-cp-validations';
import BaseModel from 'ember-flexberry-data/models/model-without-validation';

import {
  ValidationRules,
  Model as UserMixin
} from '../mixins/regenerated/models/user';

const Validations = buildValidations({
  ...ValidationRules,
  firstName: null, // Убрать сгенерированные правила
  lastName: {
    descriptionKey: 'models.user.lastName-caption', // Путь для локализации имени свойства
    validators: [
      validator('ds-error'),
      validator('presence', true),
      validator('length', { min: 2, max: 50 }),
    ],
  },
});

const Model = BaseModel.extend(UserMixin, Validations, {
});

export default Model;
```

Полный список доступных валидаторов [смотрите в документации аддона `ember-cp-validations`](http://offirgolan.github.io/ember-cp-validations/docs/modules/Validators.html).

Для отображения ошибок валидации пользователю, используются компоненты `{% raw %}{{flexberry/validation-summary}}{% endraw %}` и `{% raw %}{{flexberry-validationmessage}}{% endraw %}`. Компонент `{% raw %}{{flexberry/validation-summary}}{% endraw %}` используется для отображания списока всех ошибок валидации, а компонент `{% raw %}{{flexberry-validationmessage}}{% endraw %}` для отображения ошибки валидации одного свойства. Пример использования этих компонентов в шаблоне формы редактирования:

```hbs
{% raw %}{{!-- app/templates/user-edit.hbs --}}{% endraw %}
<form class="ui form flexberry-vertical-form" role="form">
  <div class="field">
    {% raw %}{{flexberry/validation-summary errors=(v-get validationModel "messages")}}{% endraw %}
  </div>

  <div class="field {% raw %}{{if (v-get validationModel "lastName" "isInvalid") "error"}}{% endraw %}">
    {% raw %}{{flexberry-field{% endraw %}
      value=model.lastName
      label=(t "forms.user-edit.lastName-caption")
      readonly=readonly
    {% raw %}}}{% endraw %}
    {% raw %}{{flexberry-validationmessage error=(v-get validationModel "lastName" "message")}}{% endraw %}
  </div>
</form>
```

Обратите внимание что для отображения ошибок валидации одного свойства, в компоненте `{% raw %}{{flexberry-validationmessage}}{% endraw %}`, по умолчанию используется выражение `(v-get validationModel "lastName" "message")`. Таким образом будет отображена ошибка валидации только об одном не выполненном правиле валидации для этого свойства. Если необходимо выводить сразу все ошибки, можно использовать выражение `(v-get validationModel "lastName" "messages")`.

### ember-validations

Аддон [`ember-validations`](https://github.com/DavyJonesLocker/ember-validations) ранее использовался по умолчанию, при генерации приложения с использованием аддона `ember-flexberry`. Сейчас аддон `ember-validations` не поддерживается, и не рекомендуется к использованию.

Чтобы реализвать валидацию модели с использованием аддона `ember-validations`, необходимо добавить к классу модели миксин `EmberValidations`, предоставляемый этим аддоном, и описать правила валидации в свойстве `validations` класса модели.

При генерации модели, в качестве базового класса используется модель из аддона `ember-flexberry-data`, в которой уже добавлен миксин из аддона `ember-validations`, поэтому можно сразу описать правила валидации в свойстве `validations` класса модели, например:

```js
// app/models/user.js
import DS from 'ember-data';
import BaseModel from 'ember-flexberry-data/models/model';

import { Model as UserMixin } from '../mixins/regenerated/models/user';

const Model = BaseModel.extend(UserMixin, {
  validations: {
    lastName: {
      presence: true,
      length: { minimum: 2, maximum: 50 },
    },
  },
});

export default Model;
```

Полный список доступных валидаторов [смотрите в документации аддона `ember-validations`](https://github.com/DavyJonesLocker/ember-validations#validators).

Для отображения ошибок валидации пользователю, используются компоненты `{% raw %}{{flexberry-validationsummary}}{% endraw %}` и `{% raw %}{{flexberry-validationmessage}}{% endraw %}`. Компонент `{% raw %}{{flexberry-validationsummary}}{% endraw %}` используется для отображания списока всех ошибок валидации, а компонент `{% raw %}{{flexberry-validationmessage}}{% endraw %}` для отображения ошибки валидации одного свойства. Пример использования этих компонентов в шаблоне формы редактирования:

```hbs
{% raw %}{{!-- app/templates/user-edit.hbs --}}{% endraw %}
<form class="ui form flexberry-vertical-form" role="form">
  <div class="field">
    {% raw %}{{flexberry-validationsummary errors=(validationModel.errors)}}{% endraw %}
  </div>

  <div class="field {% raw %}{{if validationModel.errors.lastName "error"}}{% endraw %}">
    {% raw %}{{flexberry-field{% endraw %}
      value=model.lastName
      label=(t "forms.user-edit.lastName-caption")
      readonly=readonly
    {% raw %}}}{% endraw %}
    {% raw %}{{flexberry-validationmessage error=(validationModel.errors.lastName)}}{% endraw %}
  </div>
</form>
```

## Валидация на уровне контроллера

При наличии нескольких форм редактрования для одной модели, например, если разные пользователи приложения должны заполнять разные части одной модели, может быть трудно описать все правила валидации в модели. В таких случаях можно перенести валидацию на уровень контроллера, и описать правила валидации для каждой формы отдельно.

Чтобы перенести валидацию на уровень контроллера, необходимо в контроллере формы редактирования изменить значение свойства `validationModel`. Для этого можно использовать вычислимое свойство, которое возвращает ссылку на сам контроллер. По умолчанию, свойство `validationModel` является алиасом для совойства `model`. После того как свойство `validationModel` стало ссылатся на контроллер, миксин с правилами валидации можно добавлять к контроллеру.

Для более гибкой настройки поведения валидации, можно переопределить метод контроллера `validateModel`, например:

```js
// app/controllers/user-edit.js
import { computed } from '@ember/object';
import { resolve } from 'rsvp';
import { buildValidations, validator } from 'ember-cp-validations';

import EditFormController from 'ember-flexberry/controllers/edit-form';

const Validations = buildValidations({
  'model.lastName': { // Правило для свойства модели относительно контроллера
    descriptionKey: 'models.user.lastName-caption',
    validators: [
      validator('ds-error'),
      validator('presence', true),
      validator('length', { min: 2, max: 50 }),
    ],
  },
});

export default EditFormController.extend(Validations, {
  validationModel: computed(function () {
    return this;
  }),

  validateModel() {
    // Игнорировать ошибки валидации для новых моделей
    if (this.get('model.isNew')) {
      return resolve();
    }

    // Выполнять валидацию в остальных случаях
    return this._super(...arguments);
  },
});
```

Чтобы отключить валидацию в какой либо форме, необходимо установить `null` в значение свойства `validationModel` контроллера этой формы.

## Одновременное использование ember-cp-validations и ember-validations

При необходимости можно установить в приложении оба аддона для валидации, и `ember-cp-validations`, и `ember-validations`, генерация моделей при этом будет выполнятся для аддона `ember-cp-validations`.

Если в приложении стауновен аддон `ember-validations`, и вы хотите дополнительно установить `ember-cp-validations`, выполните команду:

```cmd
ember generate use-ember-cp-validations
```

При выполнении этой команды, в приложение будет установлен аддон `ember-cp-validations`, и добавлен сервис `validations` для совместимости аддонов.

Если в приложении стауновен аддон `ember-cp-validations`, и вы хотите дополнительно установить `ember-validations`, выполните команду:

```cmd
ember generate use-ember-validations
```

При выполнении этой команды, в приложение будет установлен аддон `ember-validations`, и добавлен сервис `validations` для совместимости аддонов.
