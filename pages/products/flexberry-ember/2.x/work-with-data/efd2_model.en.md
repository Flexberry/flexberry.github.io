---
title: Description of a model in ember-flexberry application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/efd2_model.html
lang: en
autotranslated: true
hash: c1de3afeac3229e4578b8e3a8e19f41489bc8b19a27be7b0e4b273d7cbb99faf
summary: Presents a detailed description of how the model looks in the app.
---

## Description

Models in ember-application class inherit from Ember Data [DS.Model](http://emberjs.com/api/data/classes/DS.Model.html).

{% include note.html content="the Models are created in the folder `models` and are named as follows: if the corresponding C#-the class is called `NewPlatform.Someproject.Somemodel`, the file with the model should be called `new-platform-someproject-somemodel`. If OData-bakenda the attribute [`PublishName` to simplify the naming of models](https://flexberry.github.io/ru/fo_metadata-for-client.html), then the name of the namespace in this case, the client model may be missing (the name of the client model will accordingly be formed from the name in the entity data model to OData bekende)" %}

Models often have the following structure:

```javascript
// The imports. 
import DS from 'ember-data';
import { Projection } from 'ember-flexberry-data';

var Model = Projection.Model.extend({
  // Own attributes, master detaily. 
  name: DS.attr('string'),
  someMaster: DS.belongsTo('new-platform-someproject-somemaster', { inverse: 'somemodel', async: false, polymorphic: true }),
  ...

  // Validation rules. 
  validations: {
    name: { presence: true }
    ...
  }
});

// Define model-ancestor (if any). 
Model.reopenClass({
  _parentModelName: '...' // Specifies the name of the model, which is inherited by this model, such as the 'new-platform-someproject-parent'. 
});

// Define views. 
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

// Export the model. 
export default Model;
```

Imports and exports conform to the syntax [ember-cli](http://ember-cli.com).
The model inherited from [base technology class](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/models/model.js) defined in the addon [`ember-flexberry-data`](https://github.com/Flexberry/ember-flexberry-data).
Model [set view](efd2_model-projection.html).

There are also different approaches to [specify default values](ef2_default-value.html) for the attributes of the model.

## The definition of a model

The models are defined ["standard" method for Ember](https://guides.emberjs.com/v2.4.0/models/defining-models/).

Additionally, the models inherited from the base technological models are used [validation model](efd2_model-validation.html).

For attributes of any Ember models available built-in data types `string` (string), `number` (number) `boolean` (Boolean) and `date` (date). To define other data types in Ember models are used [transformation](https://guides.emberjs.com/v2.4.0/models/defining-models/#toc_transforms).

In addition to the standard data types in the expansion `ember-flexberry-data` was added transformation `decimal` (float type), `file` (type "File"), `flexberry-enum` (type for enumerations), `guid` (type "GUID", which is the default for primary keys).

For more information about using transformations for enumerations can be viewed [here](efd2_enum.html).

Example for a class with the master `employee1` type 'new-platform-someproject-employee' and detaylari `orders` type 'new-platform-someproject-order'.

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

{% include important.html content="property Names must start with a lowercase letter." %}

{% include warning.html content="For **each** of the model should be described [serializer](efd2_serializer.html) for correct interaction with the server." %}

## The primary keys in the model

The primary keys of the object are not specified in the model explicitly.
In the client code access the primary key can be done using [property `id`](http://emberjs.com/api/data/classes/DS.Model.html#property_id).

How is the corresponding property on the server that is defined in the [serializer](efd2_serializer.html).

### The primary key type

__The primary keys of the models__ in `Ember` applications are always strings, but on the server [this behavior can be changed](fo_primary-keys-objects.html).
If you change the primary key on the server, you must override a static property `idType` in the model class:

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

Sets the property `idType` using static functions `defineIdType` in the basic technology of the model:

```javascript
defineIdType: function (newIdType) {
  this.reopenClass({
    idType: newIdType,
  });
},
```

To call this method as follows:

```javascript
Model.defineIdType('string');
```

__Primary key__ is the metadata model, so the property `idType` defined precisely in the model and not, for example, in the adapter.

To the key type through the [`getMeta` utilities `information`](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/information.js#L137) from the addon `ember-flexberry-data`.

The query language is the key type is taken into account automatically, and when building requests to the OData backendu the key values in the URL query "arabicised" only if the key type `string`.
Currently, 3 types of keys on the client: `string`, `guid` and `number`. In other cases, when building requests to the OData-bakenda will throw an exception.

{% include note.html content="By default, the client model type as the primary key is used `guid`." %}

## inverse-connection

Reference [inverse-context](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations) is used, for example, when working with detaylari.

The job connection from the aggregator to detailu.

```javascript
var Model = BaseModel.extend({
  ...
  orders: DS.hasMany('order', { inverse: 'employee', async: false }),
});
```

The job from detail to the aggregator.

```javascript
var Model = BaseModel.extend({
  // ... 
  employee: DS.belongsTo('employee', { inverse: 'orders', async: false })
});
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}