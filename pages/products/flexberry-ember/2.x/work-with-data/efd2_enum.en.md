---
title: Enumerations in ember-flexberry-data
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/efd2_enum.html
folder: products/ember-flexberry-data/models-and-projections/
lang: en
autotranslated: true
hash: cf3a9d42d12dc0fbc73108ff5e603711be61239ec8a58b26b076538949215136
summary: Describes the basic features of working with enum types in ember-flexberry-data.
---

## Working with enumerations

If the object has a property of type enum, it in the [models](efd2_model.html) is required to do the following:

To determine the transformation for a specific type (to determine how the client will serialize and deserialize the relevant values).

For the server type is an enumeration, NewPlatform.Someproject.Gender in the transforms folder, you should create a file with the name `new-platform-someproject-gender.js`.

Generated class to posledovat from the finished version of the job of an enumerated type "enum-string", and then just in property "values" to specify valid values for an enumerated type (you can specify as an array, and with the help of the object).

```javascript
import EnumTransform from 'ember-flexberry/transforms/enum-string';

export default EnumTransform.extend({
  values: ['Male', 'Female', 'Unknown'] // Valid values for an enumerated type. 
});
```

## enum-string and enum-number

In `ember-flexbery-data` available in two base class for enumerations.

* `enum-string` - working with values passed from the server as strings.
* `enum-number` - works with values that are transmitted from the server in the form of numbers.

For example, if you specify valid values for any of the following methods, `enum-string` would expect from the server and send it to the server, the value 'Empty', 'First', 'Second', and `enum-number` the numbers 0, 1, 2 to set in an array and 1, 2, 3 to specify the type of the object (in this case interpreting it on the client as 'Empty', 'First', 'Second').

```javascript
['Пусто', 'Первая', 'Вторая']; // Set values in an array 

{ Пусто: 1, Первая: 2, Вторая: 3 }; // Set values in an object 
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}