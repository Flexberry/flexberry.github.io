---
title: Валидатор datetime
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd2_date-time-validator.html
folder: products/ember-flexberry-data/models-and-projections/
lang: ru
summary: Описание возможностей и способа использования валидатора datetime.
---

## Назначение и возможности валидатора

Валидатор `datetime` позволяет проверить наличие значения, а также корректность введенного значения в поле типа дата/дата-время.
Значение валидируемого свойства считается некорректным, если для него установлено значение, соответствующее JavaScript-объекту `Date` со значением `'invalid'`.

## Опции

Данным валидатором поддерживаются следующие опции:

* `true` - передача данного значения включает валидатор с опциями по умолчанию. В данном режиме отсутствие значения ("пустое" значение) для свойства не допускается и используются сообщения по умолчанию при выводе ошибок валидации.
* `allowBlank` - если данная опция установлена в значение `true`, то отсутствие значения ("пустое" значение) для свойства допускается.

## Сообщения

Сообщения для валидатора устанавливаются в свойстве `messages` опций:

* `blank` - сообщение используется, когда значение для свойства отсутствует ("пустое").
* `invalid` - сообщение используется, когда установлено некорректное значение для свойства.

## Примеры использования

Использование валидатора с опциями по умолчанию:

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

Использование валидатора с установкой возможных опций:

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