---
title: the Router in ember-flexberry application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_router.html
folder: products/ember-flexberry/forms/
lang: en
autotranslated: true
hash: 73e5a7884235c699cf4ef12ed2824171e5b4b7ff7347419df0fada823f2cfb5c
---

## Description

The router is defined in the file `router.js` and inherits from the class [EMBER.ROUTER](http://emberjs.com/api/classes/Ember.Router.html).

Read more about the contents of the router you can read on Ember website](http://emberjs.com/api/classes/Ember.Router.html).

An example of the structure for applications.

Suppose we have a model of "new-platform-someproject-employee", then the router it will be the following:

```
import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  // /new-platform-someproject-employees 
  this.route('new-platform-someproject-employees'); // The list of records. 

  // /new-platform-someproject-employees/2 - render into the outlet in the application template 
  this.route('new-platform-someproject-employee', { path: 'new-platform-someproject-employees/:id' }); // Form to edit a record with the identifier id. 

  // /new-platform-someproject-employees/new 
  this.route('new-platform-someproject-employee.new', { path: 'new-platform-someproject-employees/new' }); // Form to create records. 
});

export default Router;
```

If there is a need to when working with the application the user sees a path other than the model name (e.g. model name is too long or written in Russian letters), it is possible to write a router in the following way (in this case the names of the entities the code uses 'new-platform-someproject-employee', 'shortpaths' appears only in the router, or if it will set the url explicitly, which is not recommended):

```
Router.map(function() {
  this.route('new-platform-someproject-employees', { path: 'shortpaths' });
  this.route('new-platform-someproject-employee', { path: 'shortpaths/:id' });
  this.route('new-platform-someproject-employee.new', { path: 'shortpaths/new' });
});
```

## Router and path controller and ranting

File structure [controllers](ef2_controller.html) and [ranting](ef2_route.html) is closely connected with the content router.

Let router is the following:

```
Router.map(function() {
  // /employees 
  this.route('employees');

  // /employees/2 - render into the outlet in the application template 
  this.route('employee', { path: 'employees/:id' });

  // /employees/new 
  this.route('employee.new', { path: 'employees/new' });
});
```

The structure for file [ranting](ef2_route.html) and [controllers](ef2_controller.html) will have the same structure (the most common situation, some controllers in the General case may not be present or the relationship will go more cleverly):

The root folder (or `routes` `controllers`), in which:

* "'employee.js"' // [edit Form](ef2_edit-form.html).
* "'employees.js"' // [List form](ef2_forms.html).
* a sub-folder of "'employee"' in which:
* "'new.js"' // [Form create](ef2_edit-form.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}