---
title: Сервис блокировок Ember Flexberry
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember LockService
toc: true
permalink: ru/ef2_lock-service.html
folder: products/ember-flexberry/services/
lang: ru
summary: Представлено описание сервиса блокировок
---

## Описание

Сервис блокировок (NewPlatform.Flexberry.LockService) предназначен для удобной реализации механизма блокировок. Например, требуется защитить некоторый объект данных от изменения другими пользователями в то время, как он редактируется каким-либо пользователем.

В версиях Flexberry Designer 2017.06.14-beta08 и последующих LockService подключается автоматически.

## Добавление сервиса блокировок для более ранних версий

* Включить в Ember Flexberry-проекте LockService, добавив в `environment.js`:

```js
// Settings lock.
lock: {
  enabled: true,
  openReadOnly: true,
  unlockObject: true,
}
```

Где:
> * enabled - включение/выключение LockService.
> * openReadOnly - отвечает за открытие формы редактирования только для чтения, если форма заблокирована.
> * unlockObject -  отвечает за удаление блокировки при выходе с формы.

* Добавить в Backend [ODataService](fo_orm-odata-service.html) библиотеку NewPlatform.Flexberry.LockService.

* Добавить в ODataConfig assemblies тип Lock:
```cs
namespace NewPlatform.Flexberry.Services
{
  ...
  var assemblies = new[] {
    ...
    typeof(NewPlatform.Flexberry.Services.Lock).Assembly
  };
  ...
};
```

* Получение текущего пользователя для блокировок сейчас осуществляется через метод `getCurrentUserName` [сервиса `user`](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/services/user.js) (определен в аддоне `ember-flexberry-data`). По умолчанию данный метод будет возвращать пользователя с именем `userName`. На прикладном уровне необходимо переопределить данный метод, если используется аутентификация в приложении или если необходимо возвращать другое имя текущего пользователя в случае отсутствия механизма аутентификации в приложении.
