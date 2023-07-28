---
title: Инструкция по отладке кода проекта с ORM.ODataService во время выполнения
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, отладка, пример
summary: Пошаговая инструкция по удобному способу отладки
toc: true
permalink: ru/fo_debugging-odatabackend-runtime.html
lang: ru
---

В технологии [ORM.ODataService](fo_orm-odata-service.html) реализован кастомный фильтр исключений [CustomExceptionFilter](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/develop/NewPlatform.Flexberry.ORM.ODataServiceCore.Common/Exceptions/CustomExceptionFilter.cs), реализующий интерфейс [IExceptionFilter](https://learn.microsoft.com/ru-ru/dotnet/api/microsoft.aspnetcore.mvc.filters.iexceptionfilter?view=aspnetcore-7.0), который добавляется к применяемым фильтрам через операцию _options.Filters_.

В [коде](https://github.com/Flexberry/FlexberryEmberTestStand.ODataBackend/blob/7c0b0d8ca8e44c505a42661d531d534f245cca09/EmberFlexberry/ODataBackend/Startup.cs#L60) это выглядит следующим образом: Add\<CustomExceptionFilter>();.

Механизм работы таких фильтров доступен в официальной документации [Microsoft](https://learn.microsoft.com/en-us/aspnet/core/mvc/controllers/filters?view=aspnetcore-7.0).

Данный механизм позволяет удобно отлавливать ошибки в коде, использующем [ORM.ODataService](fo_orm-odata-service.html), во время выполнения, для этого необходимо сделать следующее:

* скопировать в проект приложения класс [CustomExceptionFilter.cs](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/develop/NewPlatform.Flexberry.ORM.ODataServiceCore.Common/Exceptions/CustomExceptionFilter.cs) и, например, изменить имя класса,
* в строке, аналогичной [данной](https://github.com/Flexberry/FlexberryEmberTestStand.ODataBackend/blob/), указать созданный ранее класс,
* и в нем установить [брекпойнт](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/c3ed1a3c181119606c87be6f1f89a2973d85b26a/NewPlatform.Flexberry.ORM.ODataServiceCore.Common/Exceptions/CustomExceptionFilter.cs#L50).