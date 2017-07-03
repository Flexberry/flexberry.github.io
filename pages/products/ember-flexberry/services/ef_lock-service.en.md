---
title: Сервис блокировок Ember Flexberry
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember LockService
toc: true
permalink: en/ef_lock-service.html
folder: products/ember-flexberry/services/
lang: en
summary: Представлено описание сервиса блокировок
---

## Описание

Сервис блокировок (NewPlatform.Flexberry.LockService) предназначен для удобной реализации механизма блокировок. Например, требуется защитить некоторый объект данных от изменения другими пользователями в то время, как он редактируется каким-либо пользователем.

В версиях Flexberry Designer 2017.06.14-beta08 и последующих LockService подключается автоматически.

## Добавление сервиса блокировок для более ранних версий

Включить в EmberFlexberry проекте LockService, добавив в environment.js:

```js
// Settings lock.
lock: {
  enabled: true,
  openReadOnly: true,
  unlockObject: true, удалять блокировку при выходе с формы
}
```

Где:
* enabled - включение/выключение LockService.
* openReadOnly - отвечает за открытие формы редактирования только для чтения, если форма заблокирована.
* unlockObject -  отвечает за удаление блокировки при выходе с формы.

Добавить в Backend ODataService бибилиотеку NewPlatform.Flexberry.LockService.

Добавить в ODataConfig.cs библиотеку:

```cs
using NewPlatform.Flexberry.Services;
```

и тип Lock:
```cs
var assemblies = new[] {
  typeof(Lock).Assembly
};
```
