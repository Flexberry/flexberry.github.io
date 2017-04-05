---
title: Сервис данных WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-data-service.html
lang: ru
---

## Сервис данных WOLV

У [WOLV](fa_web-object-list-view.html) имеется публичное свойство `DataService`, через которое можно получать используемый сервис данных и/или устанавливать свой.

Для этого было используется публичное свойство:

```csharp
public IDataService DataService { get; set; }
```

Получив используемый сервис данных можно добавлять свои [обработчики некоторых событий](fo_sql-data-service.html), к примеру:

```csharp
(webObjectListView.DataService as SQLDataService).AfterUpdateObjects += delegate(object sender, DataObjectsEventArgs args)
{
    // ...
}
```

{% include note.html content="Без приведения к конкретному типу `DataService`, события привязать будет невозможно." %}
