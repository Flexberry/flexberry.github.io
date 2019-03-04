--- 
title: Lucapa in ember-flexberry application 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_lookup.html 
lang: en 
autotranslated: true 
hash: 9d6637fba3100e43a9e9384a92a4edbd5b70eee46e6d908e0062502ff6980d29 
summary: describes the main features of lyapov 
--- 

## Description 

Lookup is a [control (control)](ef_controls.html), allowing to select the wizard. 

## setting lyapov in ember application 

To configure lyapov in the ember application, you need to verify the correctness of the configuration [models](efd_model.html), the serializer and the template. 

Let the entity `Подготовка` is the master `Редактор` type `Пользователь`. You want to configure lookup property `ExtendedName` master. 

### model configuration 

[Model](efd_model.html) `Подготовка` should contain a reference to the master. [View](efd_model-projection.html) to display the form where it will be located lookup should also include a description of the wizard. 

```javascript
var Model = BaseModel.extend({
  редактор: DS.belongsTo('пользователь', { inverse: null, async: false })
});

Model.defineProjection('ПодготовкаE', 'подготовка', {
  редактор: Proj.belongsTo('пользователь', 'Редактор', {
      extendedName: Proj.attr()
  })
});
``` 

[Models](efd_model.html) corresponding to the master type `Пользователь`, you must specify a [view](efd_model-projection.html) under which you will be displaying. 

```javascript
var Model = BaseModel.extend({
  extendedName: DS.attr('string')
});

Model.defineProjection('ПользовательE', 'пользователь', {
  extendedName: Proj.attr('Полное имя')
});
``` 

### configure serializers 

[Serializer](efd_serializer.html) `Подготовка` described by reference to the master. 

```javascript
export default ApplicationSerializer.extend({
  attrs: {
      редактор: { serialize: 'odata-id', deserialize: 'records' }
  },
  primaryKey: '__PrimaryKey'
});
``` 

[Serializer](efd_serializer.html) for the type `Пользователь` simpler: 

```javascript
export default ApplicationSerializer.extend({
  attrs: {  },
  primaryKey: '__PrimaryKey'
});
``` 

### customize template 

On the edit page class `Подготовка` you can insert design 

```hbs
{% raw %}<div class="field">
  <label>Редактор</label>
  {{#if model.errors.редактор }}
    <span style="color:red">{{model.errors.редактор }}</span>
  {{/if}}
  {{flexberry-lookup
    componentName="lookupUsers"
    choose='showLookupDialog'
    remove='removeLookupValue'
    value=model.редактор
    relatedModel=model
    relationName='редактор'
    projection='ПользовательE'
  }}
</div>{% endraw %}
``` 

### List of lucapa settings: 

Property | Description | Default value 
:---------------------|:------------------------------------------------------------|:---------------- 
`choose` | Specifies the name of the action and that will be happening by clicking on the button 'choose'.| 
`remove` | Specifies the name of the action and that will be happening by clicking on the button 'remove'.| 
`chooseText` | Defines the text/html inside the button 'choose'.| 
`removeText` | Defines the text/html inside the button 'remove'.| 
`chooseButtonClass` | Defines css class for button 'choose'.| 
`removeButtonClass` | Defines css class for button 'remove'.| 
`placeholder` | Defines placeholder | t('flexberry-lookup.placeholder') 
`value` | Determines the selected model instance (the master object) | 
`relatedModel` | Specifies the model for which will be edited the reference to a master object) | 
`relationName` | Specifies the name | 
`projection` | Determines which view will be displayed in the wizard list | 
`sizeClass` | Defines css class of the window size options are: small, large, fullscreen | small 
`title` | Header modal window | 
`autocomplete` | autocomplete Mode, in the mode of "read-Only" doesn't work | false 
`dropdown` | Mode drop-down list in the "read-Only" doesn't work | false 
`displayAttributeName` | the name of the model attribute (properties master), which is displayed to the user | 
`minCharacters` | Minimum number of characters to autocomplete, autocomplete mode and in the mode drop-down list | 1 
`maxResults` | Maximum number of records displayed in autocomplete mode and in the mode in the mode drop-down list, not required property | 10 

## Block form of the component 

{% include important.html content="currently available only for mobile template." %} 

It is possible to use a component in block form: 

```hbs
{% raw %}{{#flexberry-lookup ...}}
  {{model.employee1.firstName}}<br>
  {{model.employee1.lastName}}
{{/flexberry-lookup}}{% endraw %}
``` 
in this case, the application programmer can override how it will look in the display wizard on the form 

## setting the modal window 

How to set raise on lucapa modal window described in [setup at raising lucapa form](ef_modal-window-settings.html). 

## Job restrictions 

{% include important.html content="the Information is outdated, the property limitFunction removed." %} 

Setting limits on the values displayed in the list for selection, carried out by setting the filter ($filter) in the format of OData ([OData Version 4.0](http://docs.oasis-open.org/odata/odata/v4.0/errata02/os/complete/part2-url-conventions/odata-v4.0-errata02-os-part2-url-conventions-complete.html#_Toc406398094)). This filter is specified as a string in the property `limitFunction` when specifying the template. 

```hbs
{% raw %}
{{flexberry-lookup
  limitFunction=SomeFilterFunction
  // ... 
}}{% endraw %}
``` 

Consider the assignment constraints for example. Let the entity have `Employee` artisans link `Employee1` type `Employee`. Required by lucapa to display only those records that have a `FirstName` `FirstName` greater than or equal to the current selected record. 

PstrfEmployee` model has the following form: 

```javascript
var Model = BaseModel.extend({
  firstName: DS.attr('string'),
  lastName: DS.attr('string'),
  birthDate: DS.attr('date'),
  employee1: DS.belongsTo('employee', { inverse: null, async: false }),
});
``` 

In the controller type `Employee` create a calculated property `lookupLimitFunction`, which will return the filter to "show only those records that have a `FirstName` `FirstName` greater than or equal to the current selected record". 

```javascript
export default EditFormController.extend({
  // The Caption of this particular edit form. 
  title: 'Employee',

  /** 
* Current function to limit the accessible values of model employee1. 
* 
* @property lookupLimitFunction 
* @type String 
* @default undefined 
*/
  lookupLimitFunction: Ember.computed('model.employee1', function() {
    let currentLookupValue = this.get('model.employee1');
    if (currentLookupValue) {
      let currentLookupText = this.get('model.employee1.firstName');
      return 'FirstName ge \'' + currentLookupText + '\'';
    }

    return undefined;
  })
});
``` 

Next, the appropriate property must be specified in the form template: 

```hbs
{% raw %}
{{flexberry-lookup
  limitFunction=lookupLimitFunction
  // ... 
}}{% endraw %}
``` 

{% include important.html content="As the filter is directly defined by OData `FirstName` is capitalized and is the name of the properties in the OData model (ember-model `firstName` is written in small letters).<br/> 
In accordance with [standard OData Version 4.0](http://docs.oasis-open.org/odata/odata/v4.0/errata02/os/complete/part2-url-conventions/odata-v4.0-errata02-os-part2-url-conventions-complete.html#_Toc406398094) filter with reference to the field master will be recorded as `limitFunction='Libdocument/Name eq \'ORD - order\"` (through the symbol `\/`)." %} 

## Lookup mode completion 

Lookup can be converted into [completion mode](ef_lookup-autocomplete.html). 

## Button display the selected value 

To use this feature, lucapa is required to determine the following properties: 

```hbs
{% raw %}
{{flexberry-lookup
  preview=(action "previewLookupValue")
  showPreviewButton=true
  previewFormRoute=previewFormRoute
  // ... 
}}{% endraw %}
``` 

Below are attached to lookup properties for the operation of the preview button: 

Property | Description | Default value 
:---------------------|:------------------------------------------------------------|:---------------- 
`preview` | Specifies the name of the action and that will be happening by clicking on 'preview' button.| 
`showPreviewButton` | a Flag that determines whether to display the browse button. | false 
`previewFormRoute` | Defines a Route that will open the currently selected value.| 
`previewOnSeparateRoute` | a Flag that determines whether to open the selected value in a separate page (opens by default in a modal window)| false 
`previewButtonClass` | Defines css class on the 'preview' button.| 
`previewText` | Defines the text/html inside the button 'preview'.| 

## Custom settings for the list in lucapa 

For the list in lucapa you can use the [custom settings](ef_model-user-settings-service.html#пользовательские-settings-for-lists-in-lukapa). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/