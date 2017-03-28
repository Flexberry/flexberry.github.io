---
title: Сортировка списков
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_list-sort.html
folder: products/flexberry-winforms/
lang: ru
---

# Сортировка

Списки позволяют сортировать содержащиеся в них объекты по определенным столбцам. Пользователь может сам настроить необходимую ему сортировку ([ESB.%D0%A1%D0%BF%D0%B8%D1%81%D0%BA%D0%B8%20%D0%B4%D0%BE%D0%BA%D1%83%D0%BC%D0%B5%D0%BD%D1%82%D0%BE%D0%B2.ashx#F_%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D1%81%D0%BF%D0%B8%D1%81%D0%BA%D0%B0_4|подробнее]).

Также сортировку списка можно настроить из кода:

### Windows, ObjectListView
Настройка сортировки столбцов [ObjectListView](object-list-view.html) осуществляется путем указания типа сортировки у объекта `ICSSoft.STORMNET.Windows.Forms.ColumnInfo` свойства `.Columns`.

Пример:
```
this.objectListView1.Columns = new ICSSoft.STORMNET.Windows.Forms.ColumnInfo[] {
    new ICSSoft.STORMNET.Windows.Forms.ColumnInfo("ФИО", -1, true, ICSSoft.STORMNET.Business.SortOrder.Asc, ((byte)(0)), "ФИО"),
    new ICSSoft.STORMNET.Windows.Forms.ColumnInfo("Прописка", -1, true, ICSSoft.STORMNET.Business.SortOrder.Asc, ((byte)(1)), "Прописка")
}
```

Здесь конструктор `ColumnInfo` принимает третьим и четвертым параметрами способ сортировки типа `ICSSoft.STORMNET.Business.SortOrder` (Asc, Desc, None) и приоритет сортировки типа `byte`.

### Web, WebObjectListView
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

#### Доступ к текущим настройкам сортировки в наследниках
Текущие настройки WOLV складываются из наложенных разработчиком через публичное экземплярное свойство `InitialColumnsSort` + настройки, сделанные пользователем через интерфейс WOLV.

Получить доступ к агрегированным текущим настройкам сортировки можно через защищенное виртуальное свойство `ActualColumnSort` в классах-наследниках.