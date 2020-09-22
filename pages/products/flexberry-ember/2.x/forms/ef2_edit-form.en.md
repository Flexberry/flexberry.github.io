---
title: Form for editing and creating Flexberry Ember
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_edit-form.html
lang: en
autotranslated: true
hash: ef236853e88c8aad3fd45e6889b751fd0e068c7cf286483149ace3d29e7e9ea0
summary: Purpose, structure, functions and error displays on the edit form
---

## Description

Edit form is used to edit the object. Creation form to create a new object (although in the General case is very similar in logic forms).

To create the edit form - [models](efd2_model.html) by [view](efd2_model-projection.html), define the appropriate [ranting](ef2_route.html), [controllers](ef2_controller.html) and [templates](ef2_template.html).

Implemented technology [form](ef2_forms.html) the basic elements are a [controller](ef2_controller.html) one [template](ef2_template.html) and different [ranting](ef2_route.html) (for route creation form inherited from the roat for the editing form).

## Creation form

The creation form can be used to set the [default values for model properties](ef2_default-value.html).

## The functionality of the forms creation and editing

The table below provides information about the features of different modes for different types of forms create and edit.

`Обычная форма` (edit form) - the primary work of the form of redaktirovaniya.

`Детейловая form without saving or сохранением` (detail-edit-form) is a special kind of forms, when required [edit detaily in a separate ranting](ef2_groupedit-detail-in-route.html).

`ReadOnly`/`Not ReadOnly` - opens or not [the form in read-only mode](ef2_read-only-form.html).

`IsNew`/`Saved` - if the object is new or already stored.

Operations:

* Save (Save)
* Save and close (Save and close)
* Delete (Delete)
* Close (Close)

| Normal form | Dealova form without saving | Dealova form c saving |
| (edit form) |(detail-edit-form) | (detail-edit-form)
:------------| :---------------| :-------------------| :-----------------
`ReadOnly IsNew`| close Only. The model does not change. | Close only. The status of unavailable. | Close only. The status of unavailable.
`ReadOnly Saved` |close Only. The model does not change.|Only a close. The model does not change.|Only a close. The model does not change.
`Not ReadOnly IsNew` | save, Save and close, close (rollback models).| Removal (full) and closing (without rolling back the model).| Save, save and close, close (rollback models).
`Not ReadOnly Saved` |save, Save and close, delete (complete), closure (rollback models).|Removal (mark) and closing (without rolling back the model).| Save, save and close, delete (complete), closure (rollback models).

### The template for a generic edit form

Thus, in the design [template](ef2_template.html) the edit form is the usual form given the read-only mode only the following designs:

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

### The template for the editing form of detail

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

### Error display on the edit form

If the error appeared when loading the object to open the form, the edit template is displayed `error.hbs`. To change this behavior you can use the default behavior of ember and.

### QueryParams for the edit form

In the controller edit-form there is [queryParams](https://github.com/Flexberry/ember-flexberry/blob/adb541c44d902152d8c43ff588aa55d376a98ec8/addon/controllers/edit-form.js#L171). Setting this field is carried out the regular mechanisms Ember. A service lock provides `transition` on route with `readonly=true`, if he sees that the object is locked.

If you have to open up the editor to read, you can implement this similarly, by passing in query parameters (queryParam) the readonly option with the value true.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}