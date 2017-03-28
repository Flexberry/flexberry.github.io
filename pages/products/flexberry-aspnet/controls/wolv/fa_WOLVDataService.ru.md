---
title: Сервис данных WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_w-o-l-v-data-service.html
folder: products/flexberry-aspnet/
lang: ru
---

## Сервис данных WOLV

В версиях после 2013-09-13 появилась возможность переопределять логику сохранения объектов для конкретного WOLV. Для этого было добавлено публичное свойство

```cs
public IDataService DataService { get; }
```

Через это свойство можно добавлять свои обработчики некоторых событий, к примеру:

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

