--- 
title: the Validator is a datetime 
sidebar: ember-flexberry-data_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/efd_date-time-validator.html 
folder: products/ember-flexberry-data/models-and-projections/ 
lang: en 
autotranslated: true 
hash: 9099b3f46fdfee4e55e4750b5741ab1e2874909f4ae5f919fd0f8875c93c149e 
summary: explains the features and usage of the validator is a datetime. 
--- 

## the Purpose and capabilities of the validator 

Pstrfdatetime` validator allows you to check the values and the entered values in a field of type date/date-time. 
The value of validated properties is considered invalid if set to a value corresponding JavaScript object `Date` value `'invalid'`. 

## Options 

This validator supports the following options: 

* `true` - transfer this value includes the validator with the default options. In this mode, no value (empty value) for the property is not allowed and use the default message in the output validation errors. 
* `allowBlank` - if this option is set to `true`, then no value (empty value) for the property is allowed. 

## Messages 

Messages for the validator are installed in the property `messages` options: 

* `blank` message used when the value for the property is missing ("blank"). 
* `invalid` - message is used when a invalid value for the property. 

## Examples of usage 

The use of the validator with the default options: 

```javascript
var Model = BaseModel.extend({
  birthDate: DS.attr('date'),

  // Validation rules. 
  validations: {
    birthDate: {
      datetime: true
    }
  }
});
``` 

The use of the validator with installation of available options: 

```javascript
var Model = BaseModel.extend({
  orderDate: DS.attr('date'),
  
  // Validation rules. 
  validations: {
    orderDate: {
      datetime: { allowBlank: false, messages: { blank: 'order date can\'t be blank', invalid: 'please input valid date' } }
    }
  }
});
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/