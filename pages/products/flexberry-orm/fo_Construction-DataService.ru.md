---
title: Конструирование/получение сервиса данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_construction--data-service.html
---
* **Продукт**: [Flexberry ORM](flexberry-o-r-m.html)
* **Компонент**: [Сервис данных](data-service.html)
* **Программная библиотека**: ICSSoft.STORMNET.Business.dll
* **Предназначение**: Существует несколько способов конструирования и получения [сервиса данных](data-service.html).

Создать [сервис данных](data-service.html) можно разными способами:

1.Сконструировать [сервис данных](data-service.html)

```cs
IDataService ds = new ODBCDataService();			
ds.CustomizationString="DSN=LibNetSample";
```

2.В WinForms приложениях можно «бросить» [сервис данных](data-service.html) на форму как контрол, затем настроить его через стандартное окно редактирования свойств в среде Visual Studio.

3.[Получить его у провайдера сервиса данных](data-service-provider-data-service.html).

```cs
IDataService ds = DataServiceProvider.DataService;
```
