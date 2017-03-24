---
title: Flexberry UnityFactory
sidebar: flexberry-orm_sidebar
keywords: Flexberry, Public
toc: true
permalink: ru/fo_unity-factory.html
folder: products/flexberry-orm/
lang: ru
---

Flexberry UnityFactory является [продуктом платформы Flexberry](platform-structure.html) и позволяет стандартным образом работать с разрешением контейнеров [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx). Используется версия [Unity 2.1](https://msdn.microsoft.com/en-us/library/hh237493.aspx).

<div markdown="span" class="informationo"><i class="information"></i>Flexberry UnityFactory доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.UnityFactory).</div>

Метод CreateContainer() создает контейнер по умолчанию, через который можно выполнить разрешение интерфейса в реальную инстанцию:

```csharp
IUnityContainer container = ICSSoft.Services.UnityFactory.CreateContainer();
IService serviceInstance = container.Resolve<IService>();
```

Примеры настройки unity через конфигурационный файл:

* [Сервис текущего пользователя](fo_current-user-service.html).
* [DRDataService](fo_dr-data-service.html)






## Flexberry UnityFactory
Flexberry UnityFactory является [продуктом платформы Flexberry](platform-structure.html) и позволяет стандартным образом работать с разрешением контейнеров [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx). Используется версия [Unity 2.1](https://msdn.microsoft.com/en-us/library/hh237493.aspx).

<div markdown="span" class="informationo"><i class="information"></i>Flexberry UnityFactory доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.UnityFactory).</div>

### CreateContainer

Метод CreateContainer() создает контейнер по умолчанию, через который можно выполнить разрешение интерфейса в реальную инстанцию:

``` csharp
IUnityContainer container = ICSSoft.Services.UnityFactory.CreateContainer();
IService serviceInstance = container.Resolve<IService>();
```

<div markdown="span" class="informationo"><i class="information"></i>Проблема при использовании этого метода в следующем: при создании экземпляра зависимости учитываются настройки времени жизни объекта. Эта настройка говорит о том, как контейнер должен создавать объект при запросе: каждый раз новый, создать единственный и переиспользовтаь и т.п. Однако, все эти настройки работают внутри конкретного экземпляра контейнера, а метод CreateContainer каждый раз создает новый контейнер. Это приводит к тому, что объекты типа singleton (ContainerControlledLifetimeManager) на самом деле такими не являются.</div>

### GetContainer

Метод GetContainer(), возвращает единственный (на уровне домена приложения) экземпляр контейнера, что позволяет корректно работать с объектами типа ContainerControlledLifetimeManager.

``` csharp
IUnityContainer container = ICSSoft.Services.UnityFactory.GetContainer();
IService serviceInstance = container.Resolve<IService>();
```

<div markdown="span" class="informationo"><i class="information"></i>При использовании UnityFactory рекомендуется использовать метод GetContainer().</div>

### Примеры настройки unity через конфигурационный файл

* [Сервис текущего пользователя](fo_current-user-service.html).
* [DRDataService](fo_dr-data-service.html)