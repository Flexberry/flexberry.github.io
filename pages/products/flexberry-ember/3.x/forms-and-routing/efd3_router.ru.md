---
title: Роутинг в сгенерированных приложениях
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_router.html
lang: ru
summary: Обзор работы роутинга в сгенерированных приложениях, варианты настройки URL для страниц.
---

Роутер определяется в файле `router.js` и является наследником класса [EmberRouter](https://api.emberjs.com/ember/3.1/classes/EmberRouter). Подробнее о содержимом роутера и его настройке можно прочитать [на сайте Ember](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/).

## Генерируемый роутер

Если для класса во [Flexberry Designer](fd_flexberry-designer.html) заданы списковая форма и форма редактирования, то при [генерации](efd3_generated-app-structure.html) для модели `new-platform-someproject-employee` в роутер будут добавлены следующие роуты для [списковой формы](efd3_listform.html), [формы создания и редактирования](efd3_editform.html):

```js
import EmberRouter from '@ember/routing/router';
import config from './config/environment';

const Router = EmberRouter.extend({
  location: config.locationType
});

Router.map(function () {
// Форма со списком записей.
  this.route('new-platform-someproject-employee-l');

// Форма редактирования записи с идентификатором id.
  this.route('new-platform-someproject-employee-e', { path: 'new-platform-someproject-employee-e/:id' });

// Форма создания записей.
  this.route('new-platform-someproject-employee-e.new', { path: 'new-platform-someproject-employee-e/new' });
});

export default Router;
```

{% include warning.html content="Настоятельно не рекомендуется указывать url в явном виде внутри Ember-приложения, ссылки следует создавать средствами Ember." %}

## Настройка вида URL
По какому именно URL-адресу будут доступны указанные в роутере роуты зависит от разных настроек.
Пусть приложение развёрнуто по адресу `http://localhost:4200`.
В файле конфигурации можно задавать [locationType](https://guides.emberjs.com/v3.4.0/configuring-ember/specifying-url-type/).

```js
'use strict';

module.exports = function(environment) {
  var backendUrl = 'http://localhost:6500';
  ...
  let ENV = {
    ...
    rootURL: '/',
    locationType: 'auto',
  };
  ...
  return ENV;
};

```

Настройку `location` [можно определять непосредственно в роутере](https://api.emberjs.com/ember/3.4/classes/AutoLocation) удобным способом, однако лучше всё же определять её в файле конфигурации:

```js
Router.map(function() {
  ...
});

Router.reopen({
  location: 'auto'
});
```

```js
// Это сгенерированный код в роутере.
const Router = EmberRouter.extend({
  location: config.locationType // Можно вместо вычитки значения из конфига задать своё значение, что не рекомендуется.
});
```

**locationType: auto и history**

По умолчанию генерируется настройка [`locationType: auto`](https://api.emberjs.com/ember/3.4/classes/AutoLocation) (чаще всего, [согласно документации](https://guides.emberjs.com/v3.4.0/configuring-ember/specifying-url-type/), это эквивалентно варианту [`locationType: history`](https://api.emberjs.com/ember/3.4/classes/HistoryLocation) ). Тогда записи в роутере из примера выше будут соответствоавать следующему:

* [роут](efd3_route.html) [списковой формы](efd3_listform.html) `app/routes/new-platform-someproject-employee-l` доступен по URL `http://localhost:4200/new-platform-someproject-employee-l`.
* [роут](efd3_route.html) [формы редактирования](efd3_editform.html) `app/routes/new-platform-someproject-employee-e` доступен по URL `http://localhost:4200/new-platform-someproject-employee-e/d7e880b2-74d3-4599-ab3d-d872fbc1c574`, где `d7e880b2-74d3-4599-ab3d-d872fbc1c574` - идентификатор редактируемой записи.
* [роут](efd3_route.html) [формы создания](efd3_editform.html) `new-platform-someproject-employee-e/new` доступен по URL `http://localhost:4200/new-platform-someproject-employee-e/new`.


**locationType: hash**

Если указать [`locationType: hash`](https://api.emberjs.com/ember/3.4/classes/HashLocation), то в адреса будет добавлен символ "#".
Например, в примере выше [роут](efd3_route.html) [списковой формы](efd3_listform.html) `app/routes/new-platform-someproject-employee-l` будет доступен по URL `http://localhost:4200/#/new-platform-someproject-employee-l`.


**locationType: none**

Если указать [`locationType: none`](https://api.emberjs.com/ember/release/classes/NoneLocation), то URL в браузере не будет изменяться при переходе между роутами. Значение `locationType: none` используется, например, в [этом случае](https://flexberry.github.io/ru/gma_setting_ember-mobile-app.html).