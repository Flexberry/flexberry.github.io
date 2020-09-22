---
title: Ranting and applications
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_route.html
lang: en
autotranslated: true
hash: 758656472361984557e1f91411d677ccbcd6b1bbf57bf1262f408aca0787cde9
---

## Description

Ranting in ember applications are inherit from base class [Ember.Route](http://emberjs.com/api/classes/Ember.Route.html).

Technology implemented for the base of ranting:

* [list of forms](ef2_forms.html),
* [forms](ef2_forms.html),
* [edit forms](ef2_edit-form.html).

Typical technology Ember of ranting and see [this article](ef2_router.html).

Applied to the router must specify the name editable [models](efd2_model.html) (property `modelName`) and the name of the [views](efd2_model-projection.html) (property `modelProjection`).

## Ranting on list forms
Routh [list form](ef2_forms.html) is as follows. It derives from a certain technology can get `list-form`.

```
import ListFormRoute from 'ember-flexberry/routes/list-form';

export default ListFormRoute.extend({
  modelName: 'employee',
  modelProjection: 'EmployeeL'
});
```

## Ranting to edit forms
Routh [edit form](ef2_edit-form.html) is as follows. It derives from a certain technology can get `edit-form`.

```
import EditFormRoute from 'ember-flexberry/routes/edit-form';

export default EditFormRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
```

## Ranting for forms creation
Route for [form](ef2_edit-form.html) is as follows. It derives from a certain technology can get `edit-form-new`.

```
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

export default EditFormNewRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}