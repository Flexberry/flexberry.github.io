--- 
title: Ranting and applications 
sidebar: flexberry-ember_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_route.html 
lang: en 
autotranslated: true 
hash: b90456d406157ceee5fd82bf965148d73094b50651bc8e281b23eef60274877e 
--- 

## Description 

Ranting in ember applications are inherit from base class [Ember.Route](http://emberjs.com/api/classes/Ember.Route.html). 

Technology implemented for the base of ranting: 

* [list of forms](ef_forms.html), 
* [forms](ef_forms.html), 
* [edit forms](ef_edit-form.html). 

Typical technology Ember of ranting and see [this article](ef_router.html). 

Applied to the router must specify the name editable [models](efd_model.html) (property `modelName`) and the name of the [views](efd_model-projection.html) (property `modelProjection`). 

## Ranting on list forms 
Routh [list form](ef_forms.html) is as follows. It derives from a certain technology can get `list-form`. 

```
import ListFormRoute from 'ember-flexberry/routes/list-form';

export default ListFormRoute.extend({
  modelName: 'employee',
  modelProjection: 'EmployeeL'
});
``` 

## Ranting for edit forms 
Routh [edit form](ef_edit-form.html) is as follows. It derives from a certain technology can get `edit-form`. 

```
import EditFormRoute from 'ember-flexberry/routes/edit-form';

export default EditFormRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
``` 

## Ranting for forms creation 
Route for [form](ef_edit-form.html) is as follows. It derives from a certain technology can get `edit-form-new`. 

```
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

export default EditFormNewRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}