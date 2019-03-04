--- 
title: Form for editing and creating Flexberry Ember 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_edit-form.html 
lang: en 
autotranslated: true 
hash: 43263dc9eb19f22536b09de956338fbaf73b6703e4b7e1bbe87f7e5f4bfc0c69 
summary: Purpose, structure, functions and error displays on the edit form 
--- 

## Description 

Edit form is used to edit the object. Creation form to create a new object (although in the General case is very similar in logic forms). 

To create the edit form - [models](efd_model.html) [view](efd_model-projection.html), define the appropriate [ranting](ef_route.html), [controllers](ef_controller.html) and [templates](ef_template.html). 

Implemented technology [form](ef_forms.html) the basic elements are a [controller](ef_controller.html), [template](ef_template.html) and different [ranting](ef_route.html) (for route creation form inherited from the roat for the editing form). 

## creation Form 

The creation form can be used to set the [default values for model properties](ef_default-value.html). 

## Functionality, forms creation and editing 

The table below provides information about the features of different modes for different types of forms create and edit. 

`Обычная форма` (edit form) - the primary work of the form of redaktirovaniya. 

`Детейловая form without saving or сохранением` (detail-edit-form) is a special kind of forms, when required [edit detaily in a separate ranting](ef_groupedit-detail-in-route.html). 

`ReadOnly`/`Not ReadOnly` - opens or not [the form in read-only mode](ef_read-only-form.html). 

`IsNew`/`Saved` - if the object is new or already stored. 

Operations: 

* Save (Save) 
* Save and close (Save and close) 
* Delete (Delete) 
* Close (Close) 

| Normal form | Dealova form without saving | Dealova form c saving | 
| (edit form) |(detail-edit-form) | (detail-edit-form) 
:------------| :---------------| :-------------------| :----------------- 
`ReadOnly IsNew `| close Only. The model does not change. | Close only. The status of unavailable. | Close only. The status of unavailable. 
`ReadOnly Saved` |close Only. The model does not change.|Only a close. The model does not change.|Only a close. The model does not change. 
`Not ReadOnly IsNew` | save, Save and close, close (rollback models).| Removal (full) and closing (without rolling back the model).| Save, save and close, close (rollback models). 
`Not ReadOnly Saved` |save, Save and close, delete (complete), closure (rollback models).|Removal (mark) and closing (without rolling back the model).| Save, save and close, delete (complete), closure (rollback models).

### the Template for a generic edit form 

Thus, in the design [template](ef_template.html) the edit form is the usual form given the read-only mode only the following designs: 

```hbs
`#unless readonly`
	<button type="submit" class="ui primary button" `action 'save'`>Save</button>
	<button type="submit" class="ui primary button" `action 'saveAndClose'`>Save and close</button>
	`#unless model.isNew`
		<button type="submit" class="ui primary button" `action 'delete'`>Delete</button>
	`/unless`
`/unless`
<button type="submit" class="ui primary button" `action 'close'`>Close</button>
``` 

### Template for the editing form of detail 

Thus, when you make the template edit form of detail given the read-only mode and the possibility to work in normal mode, only the following construction. 

`hasParentRoute` is a computable property of the controller that defines how the user came to the current form: with the aggregator or not. 

* Form `обычная` if `hasParentRoute = false`, `saveBeforeRouteLeave = false`. 
* Form `детейловая without сохранения` if `hasParentRoute = true`, `saveBeforeRouteLeave = false`. 
* Form `детейловая with сохранением` if `hasParentRoute = true`, `saveBeforeRouteLeave = true`. 

```hbs
`#unless readonly`
	`#unless (and hasParentRoute (not saveBeforeRouteLeave))`
		<button type="submit" class="ui positive button" `action 'save'`>Save</button>
	`/unless`
	`#unless (and hasParentRoute (not saveBeforeRouteLeave))`
		<button type="submit" class="ui positive button" `action 'saveAndClose'`>Save and close</button>
	`/unless`
	`#unless (and model.isNew (and (not hasParentRoute) (not saveBeforeRouteLeave)))`
		<button type="submit" class="ui negative button" `action 'delete'`>Delete</button>
	`/unless`
`/unless`
<button type="submit" class="ui button" `action 'close'`>Close</button>
``` 

### error Display on the edit form 

If the error appeared when loading the object to open the form, the edit template is displayed `error.hbs`. To change this behavior you can use the default behavior of ember. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/