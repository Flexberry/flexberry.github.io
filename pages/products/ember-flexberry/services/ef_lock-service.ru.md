---
title: Сервис блокировок Ember Flexberry
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

В версиях Flexberry Designer 2017.06.14-beta08 и последующих LockService подключается автоматически.

## Добавление сервиса блокировок для более ранних версий

* Включить в Ember Flexberry проекте LockService, добавив в environment:

```js
// Settings lock.
lock: {
  enabled: true,
  openReadOnly: true,
  unlockObject: true,
}
```

Где:
>enabled - включение/выключение LockService.
>openReadOnly - отвечает за открытие формы редактирования только для чтения, если форма заблокирована.
>unlockObject -  отвечает за удаление блокировки при выходе с формы.

* Добавить в Backend [ODataService](flexberry-o-r-m-o-data-service.html) библиотеку NewPlatform.Flexberry.LockService.

* Добавить в ODataConfig тип Lock:
```cs
namespace NewPlatform.Flexberry.Services
{
  ...
  var assemblies = new[] {
    ...
    typeof(Lock).Assembly
  };
  ...
};
```

* Рекомендуется унаследовать все формы редактирования от созданной формы, где определён userName.
Так же возможно устанавливать userName в каждой отдельной форме редактирования.

(((
<msg type=important>Это решение временно и свойство usernem будет перенесено в один из сервисов.</msg>
)))
