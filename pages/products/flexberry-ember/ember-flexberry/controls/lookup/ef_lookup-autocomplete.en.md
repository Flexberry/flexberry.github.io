--- 
title: Completion in lucapa 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_lookup-autocomplete.html 
lang: en 
autotranslated: true 
hash: 3c7f7f86d1f428b64693712ae3bbce30356f4c24ce3efa3c3816adf7fe881c15 
summary: Describes the basic features of the use of lucapa in the mode of completion 
--- 

## Description 

Autocompletion in [lucap](ef_lookup.html) allows keyboard entry of the value and subsequent selection of the proposed options. 

To translate lucap mode completion, it is enough to add the property: 

```hbs
{% raw %}
{{flexberry-lookup
        componentName="lookupUsers"
	choose='showLookupDialog'
	remove='removeLookupValue'
	value=model.employee1
	relationName='employee1'
	projection='EmployeeL'
	title='Employees'
	readonly=readonly
	displayAttributeName='FirstName'

	autocomplete=true
}}{% endraw %}
``` 

## Properties used in autocompletion 

Following are the key attached to lookup properties for work completion. 

Property | Description | Default value 
:--------------|:-----------------------------------------------------------|:------------- 
`autocomplete` | autocomplete Mode, in the mode of "read-Only" doesn't work | false 
`minCharacters` | Minimum number of characters to autocomplete, autocomplete mode and in the mode drop-down list | 1 
`maxResults` | Maximum number of records displayed in autocomplete mode and in the mode in the mode drop-down list, not required property | 10 
`autocompleteProjection` | Name of the projection which reads the fields in the query to display the records, not a mandatory property is used for calculated fields | undefined 
`autocompletePersistValue` | Flag to control whether or not to leave the entered value when focus is lost, if the results autocomplete was not selected no value | false 

## Features of the query to the server for autocomplete results 

{% include important.html content="the Information is outdated, the property limitFunction removed, download via `store`." %} 

When querying the server for autocomplete results are transmitted as follows is auto-generated options: 

* Maximum number of returned values is defined as autocompleteMaxResults. 
* Imposed a limit on returned values are defined as a Union via "And" functions imposed restrictions on lucap (limitFunction) and the function Contains. 

## Overlay sort on hidden fields in the wizard 

If there is a need for sorting hidden fields when using auto-completion, you should use a property `autocompleteOrder`. To this end, the application template, you must add this property to specify the fields by which to sort and the sort direction. For example: 

```hbs
autocompleteOrder="moderated asc, parent desc"
``` 

## Mode autocomplete with automatic selection according to the value entered 

In this mode: 
1. While typing in auto-completion of values that is not in the directory, it is not reset, but is set to an empty reference to a dictionary. 
2. While typing in auto-completion values, which is in the directory, set the reference to the dictionary.

This can be useful if introduced in lookup the value to be stored in a separate field, even if the reference lucapa this value is not. 

To enable this mode, you should `autocompletePersistValue` property is set to true and the property `displayValue` bind on the field to which you want to save displayed in lucapa value. 

```hbs
{% raw %}
{{flexberry-lookup
        ...

	autocomplete=true
	autocompletePersistValue=true
	displayValue=model.lookupDisplayValue
}}{% endraw %}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}