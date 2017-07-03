---
title: Сервис блокировок
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember LockService
toc: true
permalink: ru/ef_lock-service.html
folder: products/ember-flexberry/services/
lang: ru
summary: Представлено описание сервиса блокировок
---

## Описание

Сервис блокировок (NewPlatform.Flexberry.LockService) предназначен для удобной реализации механизма блокировок. Например, требуется защитить некоторый объект данных от изменения другими пользователями в то время, как он редактируется каким-либо пользователем.

В версиях Flexberry Designer 2017.06.26-beta09 и последующих LockService подключается автоматически при генерации Backend.

## Добавление сервиса блокировок

Включить в EmberFlexberry проекте LockService

```js
// Settings lock.
lock: {
  enabled: true, вкл/выкл
  openReadOnly: true, открывать ли форму только для чтения, если заблокированаа
  unlockObject: true, удалять блокировку при выходе с формы
}
```

Где:
* enabled - включение/выключение LockService
* openReadOnly - открывать ли форму только для чтения, если заблокированаа
* unlockObject - удалять блокировку при выходе с формы

Добавить в Backend ODataService бибилиотеку NewPlatform.Flexberry.LockService.

```cs
using NewPlatform.Flexberry.Services;
```

```cs
var assemblies = new[] {
  typeof(Lock).Assembly
};
```
