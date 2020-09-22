---
title: Роутер в ember-flexberry-приложении
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_router.html
folder: products/ember-flexberry/forms/
lang: ru
---

## Описание

Роутер определяется в файле `router.js` и является наследником класса [EMBER.ROUTER](http://emberjs.com/api/classes/Ember.Router.html).

Подробнее о содержимом роутера можно прочитать [на сайте Ember](http://emberjs.com/api/classes/Ember.Router.html).

Пример структуры для приложений.

Пусть у нас есть модель "new-platform-someproject-employee", тогда роутер для неё будет следующий:

```
import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  // /new-platform-someproject-employees
  this.route('new-platform-someproject-employees'); // Список записей.

  // /new-platform-someproject-employees/2 - render into outlet in application template
  this.route('new-platform-someproject-employee', { path: 'new-platform-someproject-employees/:id' }); // Форма редактирования записи с идентификатором id.

  // /new-platform-someproject-employees/new
  this.route('new-platform-someproject-employee.new', { path: 'new-platform-someproject-employees/new' }); // Форма создания записей.
});

export default Router;
```

Если есть потребность, чтобы при работе с приложением пользователь видел путь, отличный от имени модели (например, имя модели слишком длинное или записано русскими буквами), то можно писать роутер следующим образом (при этом имена сущностей в коде используются вида 'new-platform-someproject-employee', 'shortpaths' фигурирует только в роутере, либо если где-то будет указан url в явном виде, что не рекомендуется):

```
Router.map(function() {
  this.route('new-platform-someproject-employees', { path: 'shortpaths' });
  this.route('new-platform-someproject-employee', { path: 'shortpaths/:id' });
  this.route('new-platform-someproject-employee.new', { path: 'shortpaths/new' });
});
```

## Роутер и пути для контроллеров и роутов

Структура файлов [контроллеров](ef2_controller.html) и [роутов](ef2_route.html) тесно связана с содержимым роутера.

Пусть есть роутер следующего вида:

```
Router.map(function() {
  // /employees
  this.route('employees');

  // /employees/2 - render into outlet in application template
  this.route('employee', { path: 'employees/:id' });

  // /employees/new
  this.route('employee.new', { path: 'employees/new' });
});
```

Тогда структура для файлов [роутов](ef2_route.html) и [контроллеров](ef2_controller.html) будет устроена одинаково (описана наиболее типичная ситуация, некоторые контроллеры в общем случае могут отсутствовать или связи будут идти более хитро):

Корневая папка (`routes` или `controllers`), в которой:

* '''employee.js''' // [Форма редактирования](ef2_edit-form.html).
* '''employees.js''' // [Списковая форма](ef2_forms.html).
* вложенная папка '''employee''', в которой:
  * '''new.js''' // [Форма создания](ef2_edit-form.html).
