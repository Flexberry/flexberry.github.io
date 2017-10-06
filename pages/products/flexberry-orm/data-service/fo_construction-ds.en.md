---
title: Designing a Data Service
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data service
summary: Ways to create a data service in an application
toc: true
permalink: en/fo_construction-ds.html
lang: en
---

Создать [сервис данных](fo_data-service.html) можно разными способами:

1.Сконструировать [сервис данных](fo_data-service.html)

```csharp
IDataService ds = new ODBCDataService();			
ds.CustomizationString="DSN=LibNetSample";
```

2.В WinForms-приложениях можно «бросить» [сервис данных](fo_data-service.html) на форму как контрол, затем настроить его через стандартное окно редактирования свойств в среде Visual Studio.

3.[Получить его у провайдера сервиса данных](fo_ds-provider.html).

```csharp
IDataService ds = DataServiceProvider.DataService;
```
