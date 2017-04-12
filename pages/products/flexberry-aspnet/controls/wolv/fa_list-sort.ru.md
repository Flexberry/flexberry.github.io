---
title: Сортировка для WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_list-sort.html
lang: ru
---

Списки позволяют сортировать содержащиеся в них объекты по определенным столбцам. Пользователь может сам настроить необходимую ему сортировку. Также сортировку списка можно настроить из кода.

## Сортировка списков WOLV

Настройка сортировки столбцов [WebObjectListView](fa_web-object-list-view.html) осуществляется путем задания свойства `.InitialColumnsSort` типа `ICSSoft.STORMNET.Business.ColumnsSortDef[]`.

Пример:

```csharp
wolv.InitialColumnsSort = new ColumnsSortDef[] 
{
    new ColumnsSortDef("ФИО", SortOrder.Asc),
    new ColumnsSortDef("Прописка", SortOrder.Asc),
};
```

Здесь конструктор `ColumnsSortDef` принимает 2 параметра: наименование колонки типа `string` и способ сортировки типа `ICSSoft.STORMNET.Business.SortOrder` (Asc, Desc, None).

Приоритет сортировки определяется порядком следования элементов в массиве `ColumnsSortDef`.

### Доступ к текущим настройкам сортировки в наследниках

Текущие настройки [WebObjectListView](fa_web-object-list-view.html) складываются из наложенных разработчиком через публичное экземплярное свойство `InitialColumnsSort` + настройки, сделанные пользователем через интерфейс [WebObjectListView](fa_web-object-list-view.html).

Получить доступ к агрегированным текущим настройкам сортировки можно через защищенное виртуальное свойство `ActualColumnSort` в классах-наследниках.

