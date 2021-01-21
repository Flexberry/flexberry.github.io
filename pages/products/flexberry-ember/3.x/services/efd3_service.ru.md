---
title: Сервисы Flexberry Ember
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_service.html
lang: ru
summary: Описание и перечень сервисов Flexberry Ember.
---
## Описание

Сервисы являются наследниками класса [SERVICE](https://guides.emberjs.com/v3.4.0/applications/services/).

```js
import Service from '@ember/service';

export default Service.extend({
});
```

В ember-аддоне `ember-flexberry` доступны следующие сервисы:

* [Сервис интернационализации i18n](efd3_i18n.html)
* [Сервис настроек пользователя](efd3_user-settings-service.html)
* [Сервис логирования](efd3_log-service.html)
* [Сервис пессимистических блокировок](efd3_lock-service.html)