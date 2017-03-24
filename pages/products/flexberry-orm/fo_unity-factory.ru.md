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
