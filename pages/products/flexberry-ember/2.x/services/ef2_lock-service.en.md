---
title: Service locks Ember Flexberry
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember LockService
toc: true
permalink: en/ef2_lock-service.html
folder: products/ember-flexberry/services/
lang: en
autotranslated: true
hash: 18f7386b576a9f4107e5473985cee20faa4979da96b0d2835e6a3d03bebb7d86
summary: the description of the service locks
---

## Description

Service locks (NewPlatform.Flexberry.LockService) is designed for convenient implement locking mechanism. For example, you want to protect certain data object from changes by other users at the time how it is edited by any user.

In versions Flexberry Designer 2017.06.14-beta08 and subsequent LockService connects automatically.

## Add service locks for earlier versions

* To include in Ember Flexberry project LockService, adding `environment.js`:

```js
// Settings lock. 
lock: {
  enabled: true,
  openReadOnly: true,
  unlockObject: true,
}
```

Where:
> * enabled - enable/disable LockService.
> * openReadOnly - responsible for opening edit form read-only, if the form is locked.
> * unlockObject is responsible for deleting the lock when exiting the form.

* Add Backend [ODataService](fo_orm-odata-service.html) library NewPlatform.Flexberry.LockService.

* Add to ODataConfig assemblies type Lock:
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

* Retrieve current user for a lock now carried out via the method `getCurrentUserName` [service `user`](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/services/user.js) (defined in the addon `ember-flexberry-data`). By default, this method will return the user name `userName`. At the application level, you must override this method if you are using authentication in your app or if you want to return a different user name in case no authentication mechanism in the application.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}