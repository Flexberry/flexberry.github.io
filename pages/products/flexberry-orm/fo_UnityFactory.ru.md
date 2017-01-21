---
title: Flexberry UnityFactory
sidebar: flexberry-orm_sidebar
keywords: Flexberry, Public
toc: true
permalink: ru/fo_unity-factory.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Программная библиотека''': ICSSoft.Services.UnityFactory.dll
* '''Предназначение''': Flexberry UnityFactory позволяет стандартным образом работать с разрешением контейнеров [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx).
</td>
</tr></tbody></table></a>
</div>
# Flexberry UnityFactory
Flexberry UnityFactory является [продуктом платформы Flexberry](platform-structure.html) и позволяет стандартным образом работать с разрешением контейнеров [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx). Используется версия [Unity 2.1](https://msdn.microsoft.com/en-us/library/hh237493.aspx).

(((
<msg type=information>Flexberry UnityFactory доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.UnityFactory).</msg>
)))

Метод CreateContainer() создает контейнер по умолчанию, через который можно выполнить разрешение интерфейса в реальную инстанцию:
```cs
IUnityContainer container = ICSSoft.Services.UnityFactory.CreateContainer();
IService serviceInstance = container.Resolve<IService>();
```

Примеры настройки unity через конфигурационный файл:
* [Сервис текущего пользователя](current-user-service.html).
* [DRDataService](d-r-data-service.html)
