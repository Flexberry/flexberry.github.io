---
title: Controllers in ember-flexberry applications
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_controller.html
lang: en
autotranslated: true
hash: 8f3aa56bf889a99daa88f766c0bdf4c9f0361f96a147110a38fa1dd11d7c6ab2
summary: Presents basic information about the structure of controllers in ember-flexberry applications.
---

## Description

Controllers in Ember applications are inherit from base class [Ember.Controller](http://emberjs.com/api/classes/Ember.Controller.html).

Technology implemented in the base controller:

* [list of forms](ef2_forms.html),
* [forms for editing and creation](ef2_edit-form.html).

Typical for the technology controller location, see [this article](ef2_router.html).

## Controllers for list forms
Controller for [list form](ef2_forms.html) is as follows. It derives from a certain technology controller `list-form`.

```javascript
import ListFormController from 'ember-flexberry/controllers/list-form';

var EmployeesController = ListFormController; // Can be defined in a separate variable. 
export default EmployeesController;
```

## Controllers for forms to edit and create
Controller [edit form](ef2_edit-form.html) is as follows. It derives from a certain technology controller `edit-form`.

```javascript
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController; // You can not define in a separate variable. 
```

Separate base controller for [form](ef2_edit-form.html) is not implemented. In General, the controller, the controller overrides the edit form:

```javascript
import EmployeeController from '../employee';

var EmployeeNewController = EmployeeController;
export default EmployeeNewController;
```

## App controller
The controller application is located in file `application.js` in the folder `components`.

In this controller it is possible to define the site menu (see example below), configure the processing common to all forms of settings (e.g., in order to [hide the menu bar on forms for the parameter](ef2_show-ember-form-in-frame.html)).

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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}