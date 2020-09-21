---
title: Контроллеры в ember-flexberry-приложениях
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_controller.html
lang: ru
summary: Представлена основная информация о структуре контроллеров в ember-flexberry-приложениях.
---

## Описание

Контроллеры в Ember-приложениях являются наследниками базового класса [Ember.Controller](http://emberjs.com/api/classes/Ember.Controller.html).

В технологии реализованы базовые контроллеры для:

* [списковых форм](ef2_forms.html),
* [форм редактирования и создания](ef2_edit-form.html).

О типичном для технологии расположении контроллеров см. [эту статью](ef2_router.html).

## Контроллеры для списковых форм
Контроллер для [списковой формы](ef2_forms.html) будет примерно следующий. Он наследуется от определённого в технологии контроллера `list-form`. 

```javascript
import ListFormController from 'ember-flexberry/controllers/list-form';

var EmployeesController = ListFormController; // Можно определять в отдельную переменную.
export default EmployeesController;
```

## Контроллеры для форм редактирования и создания
Контроллер для [формы редактирования](ef2_edit-form.html) будет примерно следующий. Он наследуется от определённого в технологии контроллера `edit-form`. 

```javascript
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController; // Можно не определять в отдельную переменную.
```

Отдельный базовый контроллер для [формы создания](ef2_edit-form.html) не реализован. В общем случае в контроллере формы создания переопределяют контроллер формы редактирования:

```javascript
import EmployeeController from '../employee';

var EmployeeNewController = EmployeeController;
export default EmployeeNewController;
```

## Контроллер приложения
Контроллер приложения расположен в файле `application.js` в папке `components`.

В данном контроллере возможно задать меню сайта (см. пример ниже), настроить обработку общих для всех форм параметров (например, для того, чтобы [скрыть меню на формах по параметру](ef2_show-ember-form-in-frame.html)).

```javascript
import Ember from 'ember';

export default Ember.Controller.extend({
  sitemap: {
    nodes: [{
      link: 'index',
      title: 'Home',
      children: null
    }, {
      link: null,
      title: 'Objects',
      children: [{
        link: 'employees',
        title: 'Employees',
        children: null
      }, {
        link: 'orders',
        title: 'Orders',
        children: null
      }]
    }]
  }
});
```
