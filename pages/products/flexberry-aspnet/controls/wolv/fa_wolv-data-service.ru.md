---
title: Сервис данных WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-data-service.html
folder: products/flexberry-aspnet/
lang: ru
---

## Сервис данных WOLV

У WOLV-а имеется публичное свойство DataService, через которое можно получать используемый сервис данных и/или устанавливать свой.

```cs
public IDataService DataService { get; set; }
```

Получив используемый сервис данных можно добавлять свои обработчики некоторых событий, к примеру:

```cs
(webObjectListView.DataService as SQLDataService).AfterUpdateObjects += delegate(object sender, DataObjectsEventArgs args)
{
    // ...
}
```

{% include note.html content='Обратите внимание, что без приведения к конкретному типу `DataService`, события привязать будет невозможно.' %}

## Список событий для SQLDataService

| Событие | Описание |
| ------- | -------- |
| `AfterGenerateSQLSelectQuery` | Срабатывает после генерации SQL SELECT'а. |
| `AfterUpdateObjects` | Срабатывает после обновления объектов. |
| `BeforeUpdateObjects` | Срабатывает перед обновлением объектов. |
| `OnCreateCommand` | Срабатывает при создании SQL-команды. |
| `OnGenerateSQLSelect` | Срабатывает при генерации SQL SELECT'а. |
