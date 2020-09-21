---
title: Роуты в приложениях
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_route.html
lang: ru
---

## Описание

Роуты в ember-приложениях являются наследниками базового класса [Ember.Route](http://emberjs.com/api/classes/Ember.Route.html).

В технологии реализованы базовые роуты для:

* [списковых форм](ef2_forms.html),
* [форм создания](ef2_forms.html),
* [форм редактирования](ef2_edit-form.html).

О типичном для технологии Ember расположении роутов см. [эту статью](ef2_router.html).

В прикладном роуте необходимо указать имя редактируемой [модели](efd2_model.html) (свойство `modelName`) и имя используемого [представления](efd2_model-projection.html) (свойство `modelProjection`).

## Роуты для списковых форм
Роут для [списковой формы](ef2_forms.html) будет примерно следующий. Он наследуется от определённого в технологии роута `list-form`. 

```
import ListFormRoute from 'ember-flexberry/routes/list-form';

export default ListFormRoute.extend({
  modelName: 'employee',
  modelProjection: 'EmployeeL'
});
```

## Роуты для форм редактирования
Роут для [формы редактирования](ef2_edit-form.html) будет примерно следующий. Он наследуется от определённого в технологии роута `edit-form`. 

```
import EditFormRoute from 'ember-flexberry/routes/edit-form';

export default EditFormRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
```

## Роуты для форм создания
Роут для [формы создания](ef2_edit-form.html) будет примерно следующий. Он наследуется от определённого в технологии роута `edit-form-new`. 

```
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

export default EditFormNewRoute.extend({
  modelProjection: 'EmployeeE',
  modelName: 'employee'
});
```
