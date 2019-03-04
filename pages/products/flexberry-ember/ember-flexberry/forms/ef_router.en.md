--- 
title: the Router in ember-flexberry application 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_router.html 
folder: products/ember-flexberry/forms/ 
lang: en 
autotranslated: true 
hash: 4700b83c5e0ee7c69bd149a1ca5d791a96cf5b6aac1018619302b5f1c720948f 
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

File structure [controllers](ef_controller.html) and [ranting](ef_route.html) is closely connected with the content router. 

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

The structure for file [ranting](ef_route.html) and [controllers](ef_controller.html) will have the same structure (the most common situation, some controllers in the General case may not be present or the relationship will go more cleverly): 

The root folder (or `routes` `controllers`), in which: 

* "'employee.js"' // [edit Form](ef_edit-form.html). 
* "'employees.js"' // [List form](ef_forms.html). 
* a sub-folder of "'employee"' in which: 
* "'new.js"' // [Form create](ef_edit-form.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/