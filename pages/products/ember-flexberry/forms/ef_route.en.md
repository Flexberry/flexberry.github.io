---
title: Роуты в приложениях
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_route.html
folder: products/ember-flexberry/forms/
lang: en
---

## Описание

Роуты в ember-приложениях являются наследниками базового класса [Ember.Route](http://emberjs.com/api/classes/Ember.Route.html).

В технологии реализованы базовые роуты для:

* [списковых форм](ef_forms.html),
* [форм создания](ef_forms.html),
* [форм редактирования](ef_edit-form.html).

О типичном для технологии Ember расположении роутов см. [эту статью](ef_router.html).

В прикладном роуте необходимо указать имя редактируемой [модели](efd_model.html) (свойство `modelName`) и имя используемого [представления](efd_model-projection.html) (свойство `modelProjection`).

## Роуты для списковых форм
Роут для [списковой формы](ef_forms.html) будет примерно следующий. Он наследуется от определённого в технологии роута `list-form`. 

```
import ListFormRoute from 'ember-flexberry/routes/list-form';

export default ListFormRoute.extend({
  modelName: 'employee',
  modelProjection: 'EmployeeL'
});
```

## Роуты для форм редактирования
Роут для [формы редактирования](ef_edit-form.html) будет примерно следующий. Он наследуется от определённого в технологии роута `edit-form`. 

```
import EditFormRoute from 'ember-flexberry/routes/edit-form';

export default EditFormRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
```

## Роуты для форм создания
Роут для [формы создания](ef_edit-form.html) будет примерно следующий. Он наследуется от определённого в технологии роута `edit-form-new`. 

```
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

export default EditFormNewRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
```
