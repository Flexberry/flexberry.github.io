---
title: Конструирование/получение сервиса данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_construction--data-service.html
---

Создать [сервис данных](fo_data-service.html) можно разными способами:

1.Сконструировать [сервис данных](fo_data-service.html)

```csharp
IDataService ds = new ODBCDataService();			
ds.CustomizationString="DSN=LibNetSample";
```

2.В WinForms приложениях можно «бросить» [сервис данных](fo_data-service.html) на форму как контрол, затем настроить его через стандартное окно редактирования свойств в среде Visual Studio.

3.[Получить его у провайдера сервиса данных](fo_data-service-provider-data-service.html).

```cs
IDataService ds = DataServiceProvider.DataService;
```
