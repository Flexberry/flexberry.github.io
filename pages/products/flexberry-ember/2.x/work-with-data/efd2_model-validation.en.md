---
title: validation of the model
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/efd2_model-validation.html
folder: products/ember-flexberry-data/models-and-projections/
lang: en
autotranslated: true
hash: f06cc56f55bd72bc15d30ca679b8e40b944bf27e0aa5e8cb32ee2dee9c139ae4
summary: Describes the basic steps for configuring validation
---

## Description

Client validation is configured in the application based on addon [Ember Validations](https://github.com/dockyard/ember-validations) (the documentation for this addon more than the capacity to define rules).

The validation set of the model (other manipulation of configuration are sewn in technology Flexberry ASP.NET Ember):

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

To display the validation on the edit form in the corresponding template you must write about the following (styles and position of elements may be different):

```html
<div class="ui grid `if model.errors.samarpreet 'error' "`">
	<div class="ui two wide column right aligned, middle aligned content">
		<label>Номер проекта</label>
	</div>
	<div class="ten wide column">
		<span class="flexberry-validation-error-message">`model.errors.номерПроекта`</span>
		`input type="text" placeholder="(no value)" value=model.номерПроекта`
	</div>
</div>
```

{% include note.html content="Since validation initially, it is believed that fields of type Boolean the value `null`, to not show the message validation, you can specify [default value](http://guides.emberjs.com/v2.4.0/models/defining-models/) (as is done in the example above)." %}

## Types of validators

There are standard validators provided by the addon Ember Validations and custom validators provided with the addon [ember-flexberry](https://github.com/Flexberry/ember-flexberry).

### Standard validators

Addon Ember Validations include the following validators:

* `absence` - verifying that validated the property must be empty. [Read more](https://github.com/dockyard/ember-validations#absence).
* `acceptance` - verifying that validated the property is a valid value (set in the validator). [Read more](https://github.com/dockyard/ember-validations#acceptance).
* `confirmation` - check that the value of the validated property is the value of the property propertyConfirmation, where property is the name you want to validate properties. [Read more](https://github.com/dockyard/ember-validations#confirmation).
* `exclusion` - check that the value of validated properties are not included in the list of invalid values. [Read more](https://github.com/dockyard/ember-validations#exclusion).
* `format` - check that the value you want to validate the property match the given regular expression. [Read more](https://github.com/dockyard/ember-validations#format).
* `inclusion` - check that the value you want to validate the properties included in the list of valid values. [Read more](https://github.com/dockyard/ember-validations#inclusion).
* `length` - check that the value of validated properties is the specified length. [Read more](https://github.com/dockyard/ember-validations#length).
* `numericality` - check that the value of the validated property is numeric. [Read more](https://github.com/dockyard/ember-validations#numericality).
* `presence` - verifying that validated the property must not be empty. [Read more](https://github.com/dockyard/ember-validations#presence).

### Custom validators

In the framework of the addon ember-flexberry implemented the following validators:

* `datetime` - validation of date input/date / time. [Read more](efd2_date-time-validator.html).

### Conditional validators

All validators can work in the mode of conditional validation - their operation will depend either on an additional logical function, or Boolean property of an object. [Read more](https://github.com/dockyard/ember-validations#conditional-validators).

## Development of custom validators

Application developers can also create their own custom validators if the validator is not enough to implement the application logic.
There are 2 ways to implement custom validators:

* Implement your own validator, which can be reused for different entities
* Implement inline validator that checks the correctness of the data model for one specific entity

Read more about the process of creating custom validators can be viewed [here](https://github.com/dockyard/ember-validations#custom-validators).

In addition, you can view the source code of validators in the [repository addon Ember Validations on GitHub](https://github.com/dockyard/ember-validations/tree/master/addon/validators).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}