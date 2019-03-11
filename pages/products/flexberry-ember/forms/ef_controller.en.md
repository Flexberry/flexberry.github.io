--- 
title: Controllers in ember-flexberry applications 
sidebar: flexberry-ember_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_controller.html 
lang: en 
autotranslated: true 
hash: 6e4aea565756f339782c048f968b85394cb056c986c32376f0beab0f947f6c5c 
summary: Presents basic information about the structure of controllers in ember-flexberry applications. 
--- 

## Description 

Controllers in Ember applications are inherit from base class [Ember.Controller](http://emberjs.com/api/classes/Ember.Controller.html). 

Technology implemented in the base controller: 

* [list of forms](ef_forms.html), 
* [forms for editing and creation](ef_edit-form.html). 

Typical for the technology controller location, see [this article](ef_router.html). 

## Controllers for list forms 
Controller for [list form](ef_forms.html) is as follows. It derives from a certain technology controller `list-form`. 

```javascript
import ListFormController from 'ember-flexberry/controllers/list-form';

var EmployeesController = ListFormController; // Can be defined in a separate variable. 
export default EmployeesController;
``` 

## Controllers for forms to edit and create 
Controller [edit form](ef_edit-form.html) is as follows. It derives from a certain technology controller `edit-form`. 

```javascript
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController; // You can not define in a separate variable. 
``` 

Separate base controller for [form](ef_edit-form.html) is not implemented. In General, the controller, the controller overrides the edit form: 

```javascript
import EmployeeController from '../employee';

var EmployeeNewController = EmployeeController;
export default EmployeeNewController;
``` 

## Controller app 
The controller application is located in file `application.js` in the folder `components`. 

In this controller it is possible to define the site menu (see example below), configure the processing common to all forms of settings (e.g., in order to [hide the menu bar on forms for the parameter](ef_show-ember-form-in-frame.html)). 

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