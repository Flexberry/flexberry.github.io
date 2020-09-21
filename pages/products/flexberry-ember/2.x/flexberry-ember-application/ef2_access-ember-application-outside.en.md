---
title: Access the Ember application from the outside
sidebar: flexberry-ember-2_sidebar
keywords: access outside
summary: Access to individual methods ember applications from outside
toc: false
permalink: en/ef2_access-ember-application-outside.html
lang: en
autotranslated: true
hash: 37dbac20c08a5a4a8bf98415e5922dd2638dbec928f0287ee1480e2ad6607cf4
---

Access the application using [Flexberry Ember](ef2_landing_page.html) (as to any Ember-application) are available through the global object whose name is specified in the configuration (`environment.js`) in the property `ENV.modulePrefix`.

For example, if `modulePrefix` 'ember-app', it will be globally available object with the name `EmberApp`. This is an instance of the class [`Ember.Application`](https://emberjs.com/api/ember/2.4/classes/Ember.Application).

At the instance of the class `Ember.Application` is» «container, which is instantiate and caching of application objects. It is available through the property `__container__`. Accordingly, this container has `lookup` method, which allows to copy any of the factory apps (e.g., controller, routes, services, models, etc). In this instance the corresponding [factory](https://guides.emberjs.com/v2.4.0/applications/dependency-injection/) to/necessary to change the behavior via the methods `get/set`.

For example, the property that stores the current filter for the search in the controller application dynamically you can change this:

```javascript
window.ИмяПриложения.__container__.lookup('controller:имя-контроллера').set('filter', 'значение');
```

To separate elements on the page from the "outside" can be reached either via the DOM using, e.g., [jQuery](https://jquery.com/) or through an instance of the respective components, if the appropriate markup vyalyaetsya part of a particular component.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}