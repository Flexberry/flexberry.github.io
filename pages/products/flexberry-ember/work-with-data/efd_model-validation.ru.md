---
title: Валидация модели
sidebar: flexberry-ember_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd_model-validation.html
folder: products/ember-flexberry-data/models-and-projections/
lang: ru
summary: Описаны основные шаги по настройке валидации
---

## Аддоны валидации

Используемый аддон валидации зависит от версии используемого аддона `ember-flexberry`.
Для версий 2.* используется аддон валидации [Ember Validations](https://github.com/dockyard/ember-validations), а для версий 3.* - [ember-cp-validations](https://github.com/offirgolan/ember-cp-validations).

## Аддон Ember Validations

### Описание

Клиентская валидация настраивается в приложении на базе аддона [Ember Validations](https://github.com/dockyard/ember-validations) (в документации к данному аддону указано больше возможностей по заданию правил).

Валидация задаётся на уровне модели (остальные манипуляции с настройкой компонента зашиты в технологию Flexberry ASP.NET Ember):

```javascript
var Model = BaseModel.extend({
  номерПроекта: DS.attr('string'),
  краткоеСодержание: DS.attr('string'),
  визирование: DS.attr('boolean', {defaultValue: false}),
  примечание: DS.attr('string'),

  // Validation rules.
  validations: {
      номерПроекта: { presence: true },
      краткоеСодержание: { presence: true, length: { maximum: 255 } },
      визирование: { presence: true },
      примечание: { length: { maximum: 255 } },
  }
});
```

Для отображения валидации на форме редактирования в соответствующем шаблоне необходимо написать примерно следующее (стили и расположение элементов может отличаться):

```html
<div class="ui grid `if model.errors.номерПроекта 'error' ''`">
	<div class="ui two wide column right aligned middle aligned content">
		<label>Номер проекта</label>
	</div>
	<div class="ten wide column">
		<span class="flexberry-validation-error-message">`model.errors.номерПроекта`</span>
		`input type="text" placeholder="(no value)" value=model.номерПроекта`
	</div>
</div>
```

{% include note.html content="Поскольку при валидации изначально считается, что у полей логического типа значение `null`, то, чтобы не отображались сообщения валидации, можно задать [значение по умолчанию](http://guides.emberjs.com/v2.4.0/models/defining-models/) (как это сделано в примере выше)." %}

### Типы валидаторов

Существуют "стандартные" валидаторы, предоставляемые аддоном Ember Validations, а также кастомные валидаторы, предоставляемые вместе с аддоном [ember-flexberry](https://github.com/Flexberry/ember-flexberry).

#### "Стандартные" валидаторы

Аддоном Ember Validations предоставляются следующие валидаторы:

* `absence` - проверка того, что валидируемое свойство должно быть пустым. [Подробнее](https://github.com/dockyard/ember-validations#absence).
* `acceptance` - проверка того, что в валидируемое свойство введено допустимое значение (задается в валидаторе). [Подробнее](https://github.com/dockyard/ember-validations#acceptance).
* `confirmation` - проверка того, что значение валидируемого свойства совпадает со значением свойства propertyConfirmation, где property-имя валидируемого свойства. [Подробнее](https://github.com/dockyard/ember-validations#confirmation).
* `exclusion` - проверка того, что значение валидируемого свойства не входит в перечень недопустимых значений. [Подробнее](https://github.com/dockyard/ember-validations#exclusion).
* `format` - проверка того, что значение валидируемого свойства соответствует заданному регулярному выражению. [Подробнее](https://github.com/dockyard/ember-validations#format).
* `inclusion` - проверка того, что значение валидируемого свойства входит в перечень допустимых значений. [Подробнее](https://github.com/dockyard/ember-validations#inclusion).
* `length` - проверка того, что значение валидируемого свойства имеет заданную длину. [Подробнее](https://github.com/dockyard/ember-validations#length).
* `numericality` - проверка того, что значение валидируемого свойства является числовым. [Подробнее](https://github.com/dockyard/ember-validations#numericality).
* `presence` - проверка того, что валидируемое свойство должно быть не пустым. [Подробнее](https://github.com/dockyard/ember-validations#presence).

#### Кастомные валидаторы

В рамках аддона ember-flexberry реализованы следующие валидаторы:

* `datetime` - проверка корректности ввода даты/даты-времени. [Подробнее](efd_date-time-validator.html).

#### Условные валидаторы

Все валидаторы могут работать также в режиме условной валидации - их срабатывание будет зависеть либо от дополнительной логической функции, либо от логического свойства объекта. [Подробнее](https://github.com/dockyard/ember-validations#conditional-validators).

### Разработка кастомных валидаторов

Прикладные разработчики также могут создавать свои кастомные валидаторы, если имеющихся валидаторов не достаточно для реализации прикладной логики.
Существует 2 способа реализовать кастомные валидаторы:

* Реализовать отдельный валидатор, который будет возможно переиспользовать для моделей разных сущностей
* Реализовать inline-валидатор, который будет проверять корректность данных для модели одной конкретной сущности

Подробнее о процессе создания кастомных валидаторов можно посмотреть [здесь](https://github.com/dockyard/ember-validations#custom-validators).

Кроме этого, можно посмотреть исходный код валидаторов в [репозитории аддона Ember Validations на GitHub](https://github.com/dockyard/ember-validations/tree/master/addon/validators).

## Аддон ember-cp-validations

### Описание

Клиентская валидация настраивается в приложении на базе аддона [ember-cp-validations](https://github.com/offirgolan/ember-cp-validations) (в документации к данному аддону указано больше возможностей по заданию правил).

Валидация задаётся на уровне модели в объекте `ValidationRules` (часть валидаторов генерируется в автогенерируемый миксин модели).

### Типы валидаторов

Аддоном `ember-cp-validations` предоставляются следующие валидаторы (подробнее можно посмотреть в [документации аддона](https://rawgit.com/offirgolan/ember-cp-validations/c4123c983e54f24dd790ffa1bad66cfdf2f47ec6/docs/classes/Alias.html)):

* `alias` - связывает текущий атрибут с каким-нибудь другим, получает все его сообщени валидации и ошибки.
* `belongs-to` - указывает, что атрибут является `belongs-to` связью, получает все сообщения валидации связанной модели.
* `collection` - проверяет, что значение является массивом (или не является, в зависимости от параметров валидатора).
* `confirmation` - проверяет, что значение совпадает со значением указанного в параметрах поля.
* `date` - проверяет, что значение является датой, также содержит дополнительные проверки в зависимости от переданных параметров.
* `dependent` - проходит, если проходят валидаторы указанных полей.
* `ds-error` - отображает ошибки из аддона ember-data DS.Errors (на данный момент не реализовано в технологии).
* `exclusion` - проверяет, что значение не содержится в заданном списке.
* `format` - проверяет на соответствие регулярному выражению.
* `has-many` - указывает, что атрибут является `has-many` связью, получает все сообщения валидации связанных моделей.
* `inclusion` - проверяет, что значение содержится в заданном списке.
* `length` - проверяет длину значения на равенство числу, либо на вхождение в интервал.
* `number` - проверяет, что значение является числом, также содержит дополнительные проверки в зависимости от переданных параметров.
* `presence` - проверяет, что значение не пустое (можно проверить и то что значение пустое).

### Генерируемые валидаторы

Находятся в миксине модели (`mixins/regenerated/models`):

```javascript
export let ValidationRules = {
  имяСвойства: {
    descriptionKey: 'путь_локализации',
    validators: [
      validator(валидатор),
	  validator(валидатор2),
	  ...
    ],
  },
  ...
};
```

Автоматически генрируются валидаторы:
Для всех типов полей: `validator('ds-error')` и `validator('presence', true)`.

Для полей с типом дата: `validator('date')`.

Для числовых типов: `validator('number', { allowString: true, allowBlank: true, integer: true })`.
Значения параметров зависят от свойств поля. `allowString` - может ли число быть в виде строки. `allowBlank` - может ли значение быть пустым. `integer` - если true, то допускаются только целые числа.

Для `hasMany` связи: `validator('has-many')`.

### Дополнительные валидаторы

Для добавления своих правил валидации, которые нельзя сгенерировать, нужно в модели (в папке models) добавить их в объект `ValidationRules`.
Все свои валидаторы нужно добавлять до строки с определением `const Validations = buildValidations(...)`.
Например:
```javascript
ValidationRules.name = {
  descriptionKey: 'models.testModel.validations.name.__caption__',
  validators: [
    validator('length', { min: 5 })
  ],
};
```

Тут для поля `name` добавляется валидатор минимальной длины строки - 5. А также в `descriptionKey` указан путь в locales до сообщения валидатора.

### Компоненты валидации

Для отображения сообщений валидации в `ember-flexberry` есть два компонента - `flexberry-validationmessage` и `flexberry-validationsummary`.

`flexberry-validationmessage` - предназначен для отображения сообщений валидации определенного свойства модели. В шаблоне описывается как:

```javascript
{{flexberry-validationmessage error=(v-get validationObject "propertyName" "messages")}}
```

Где `validationObject` - валидируемая модель, `propertyName` - имя свойства модели, сообщения валидаторов которого отображаются в данном компоненте. `messages` - имя свойства с сообщениями валидации (по умолчанию все сообщения хранятся в `messages`, также в `message` можно получить наиболее приоритетное сообщение, тогда будет отображаться только 1 сообщение валидации, которое будет меняться по мере выполнения условий валидаторов). `v-get` - хелпер из аддона валидации, в документации по ссылке выше, можно найти дополнительную информацию.

{% include note.html content="Важно! Имя свойства не должно быть многоуровневым, т.е. если нужно отображать валидацию для поля мастера, то нельзя использовать в качестве имени свойства \"master.propertyName\", необходимо в качестве модели брать именно модель мастера:" %}

```javascript
{{flexberry-validationmessage error=(v-get validationObject.master "propertyName" "messages")}}
```

`flexberry-validationsummary` - предназначен для отображения списка сообщений валидации. По умолчанию используется на формах редактирования для отображения всех сообщений валидации редактируемой модели. Пример:

```javascript
{{flexberry-validationsummary errors=(v-get validationObject "messages")}}
```

### Валидация на формах редактирования

Для валидации на формах редактирования используются описанные выше компоненты. `flexberry-validationmessage` - в шаблоне располагается после каждого компонента для редактирования свойства модели. `flexberry-validationsummary` - по умолчанию располагается перед всеми компонентами для редактирования свойств.
Также стандартные компоненты для редактирования свойств модели из аддона `ember-flexberry`, меняют свой цвет, если валидация не проходит (по умолчанию красный). Для этого компоненту при ошибках валидации необходимо задать класс `error`. Пример:

```javascript
{{flexberry-field
    ...
    class=(if (v-get validationObject "propertyName" "isInvalid") "error")
}}
```

Где `validationObject` - валидируемая модель, `propertyName` - имя свойства, а `isInvalid` - флаг, определяющий, что хотя бы один валидатор у данного свойства не прошел.

### Собственные валидаторы

Аддон `ember-cp-validations` позволяет создавать собственные валидаторы. Для этого необходимо в консоли в папке с приложением выполнить команду `ember g validator validator-name`. Где `validator-name` - имя создаваемого валидатора.
Эта команда сгенерирует файл `app/validators/validator-name.js` и тест для него. В сгенерированном валидаторе необходимо доработать метод `validate`, он должен возвращать либо true, если валидатор проходит, либо сообщение валидации строкой. Также можно возвращать Promise, который разрешается в true или сообщение валидации.
Больше информации о собственных валидаторах можно получить в [документации аддона](https://rawgit.com/offirgolan/ember-cp-validations/c4123c983e54f24dd790ffa1bad66cfdf2f47ec6/docs/classes/Custom.html).
