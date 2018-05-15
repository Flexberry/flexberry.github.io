---
title: Access to the Ember application from outside
sidebar: ember-flexberry_sidebar
keywords: access outside
summary: Access to specific methods of ember applications from outside
toc: false
permalink: en/ef_access-ember-application-outside.html
lang: en
---

Доступ к приложению, использующему [Flexberry Ember](ef_landing_page.html) возможен через глобальный объект, имя которого определяется в конфигурации (`environment.js`) в свойстве `ENV.modulePrefix`.

Например, если в `modulePrefix` указано значение 'ember-app', то глобально будет доступен объект с именем `EmberApp`. Это есть экземпляр класса `Ember.Application`.

У класса `Ember.Application` есть «контейнер», который занимается инстанциированием и кешированием объектов приложения. Он доступен через свойство __container__. Соответственно у этого контейнера есть метод `lookup`, который позволяет получить экземпляр любой фабрики приложения (например, контроллера, роута, сервиса, модели и пр.). В этой инстанции соответствующей фабрики приложения можно получать/изменять необходимое поведение через методы `get/set`.
 
Например, фильтр в контроллере приложения динамически можно поменять так:

```javascript
window.ИмяПриложения.__container__.lookup('controller:имя-контроллера').set('filter', 'значение');
```
 
Однако при этом не меняется «визуально» значение в соответствующем `input`, где указан фильтр. До него можно получить доступ в ручную через DOM и поменять значение, либо через инстанцию фабрики соответствующего компонента.
