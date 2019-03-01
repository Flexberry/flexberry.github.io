--- 
title: Controls ember-flexberry 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_controls.html 
lang: en 
autotranslated: true 
hash: 86bee94575f0f86b3d67ba180e198469f19ce9fca0d4212d36c3dc92bce74420 
summary: List of controls used in ember-flexberry 
--- 

## Basic control ember-flexberry 

For ease of dissemination the same type of logic was created base component, which is inherited by other ember-controls. 

The basic control contains the processing [mode "read-only"](ef_read-only-form.html)(`readonly`). 

Are derived from it: 

* `ObjectListView` 
* `FlexberryTextbox` 
* `DatetimePicker` 

## FlexberryTextbox 

`FlexberryTextbox` - control to display text fields. 

```hbs
{% raw %}
flexberry-textbox placeholder="(no value)" readonly=readonly required=true value=model.employee.firstName{% endraw %}
``` 

## FlexberrySimpledatetime 

`FlexberrySimpledatetime` - control to display the date/date and time. 

```hbs
{% raw %}{flexberry-simpledatetime
  	type=type
  	removeButton=removeButton
  	value=model.date
  	min=min
 	max=max
  	readonly=readonly
}}{% endraw %}
``` 
## ObjectListView 

`ObjectListView` - control to display the list of objects (can be used as a list form for displaying a list of objects and on the edit form to display datalow). 

```hbs
{% raw %}{{object-list-view
	class = "ui attached"
	modelProjection = modelProjection.attributes.orders
	content = model.orders
	cellComponent = getCellComponent
	readonly = readonly
}}{% endraw %}
``` 

## Lookup 

Lookup is a control (control), allowing you to select the value of the master. Its description can be found in [Lookup](ef_lookup.html). 

## UI Message 

The main purpose of [UI Message](ef_ui-message.html) - display status messages control. For example, displaying the success/failure of conservation forms, warnings, information, etc. 

## Flexberry-file 

Component `flexberry-file` provides a number of features for working with files in the application. Be described in more detail in [Component flexberry-file](ef_file.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/