---
title: Flexberry UnityFactory
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, containers
summary: Features container
toc: true
permalink: en/fo_unity-factory.html
lang: en
autotranslated: true
hash: 0d858097730c3894e0cabc212b9c2f0ca2f4fdfaeffbac720f1898b4a6d5ff3f
---

`Flexberry UnityFactory` is [product platform Flexberry](fp_landing_page.html) and allows a standard way to work with a resolution of containers [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx). Use the version of [Unity 2.1](https://msdn.microsoft.com/en-us/library/hh237493.aspx).

{% include note.html content="Flexberry UnityFactory is available for installation in the project via the NuGet package Flexberry ORM." %}

Method `CreateContainer()` creates a default container to resolve the interface in real authority:

```csharp
IUnityContainer container = ICSSoft.Services.UnityFactory.CreateContainer();
IService serviceInstance = container.Resolve<IService>();
```

{% include note.html content="the Problem with this method: when you create the instance based on settings are taken into account the lifetime of the object. This setting tells how the container should create the object in question: every time a new, to create a single and priispolzovanii, etc. However, all these settings are working within a particular container instance, and each time CreateContainer creates a new container. This leads to the fact that objects of type singleton (ContainerControlledLifetimeManager) in fact are not." %}

### GetContainer

Method `GetContainer()`, returns a single (application domain) of the container instance that allows to work correctly with objects of type `ContainerControlledLifetimemanager`.

``` csharp
IUnityContainer container = ICSSoft.Services.UnityFactory.GetContainer();
IService serviceInstance = container.Resolve<IService>();
```

{% include note.html content="When you use UnityFactory it is recommended to use the method GetContainer()." %}


### Examples of configuration using unity configuration file

* [Service current user](fo_current-user-service.html).
* [DRDataService](fo_dr-data-service.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}