---
title: Сортировка списков WOLV
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/list-sort-w-o-l-v.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Платформа''': [FlexberryASP.NET](flexberry-a-s-p-n-e-t.html).
* '''Компонент''': [WebObjectListView (WOLV)](web-object-list-view.html).
* '''Предназначение''': сортировка списков [WOLV](web-object-list-view.html).
</td>
</tr></tbody></table></a>
</div>



# WOLV
Эта статья описывает часть информации о [WebObjectListView](web-object-list-view.html).

# Сортировка

Списки позволяют сортировать содержащиеся в них объекты по определенным столбцам. Пользователь может сам настроить необходимую ему сортировку ([ESB.%D0%A1%D0%BF%D0%B8%D1%81%D0%BA%D0%B8%20%D0%B4%D0%BE%D0%BA%D1%83%D0%BC%D0%B5%D0%BD%D1%82%D0%BE%D0%B2.ashx#F_%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D1%81%D0%BF%D0%B8%D1%81%D0%BA%D0%B0_4|подробнее]).

Также сортировку списка можно настроить из кода:


# Сортировка списков WOLV
Настройка сортировки столбцов [WebObjectListView](web-object-list-view.html) осуществляется путем задания свойства `.InitialColumnsSort` типа `ICSSoft.STORMNET.Business.ColumnsSortDef[]`.

Пример:
```cs
wolv.InitialColumnsSort = new ColumnsSortDef[] 
{
    new ColumnsSortDef("ФИО", SortOrder.Asc),
    new ColumnsSortDef("Прописка", SortOrder.Asc),
};
```

Здесь конструктор `ColumnsSortDef` принимает 2 параметра: наименование колонки типа `string` и способ сортировки типа `ICSSoft.STORMNET.Business.SortOrder` (Asc, Desc, None).

Приоритет сортировки определяется порядком следования элементов в массиве `ColumnsSortDef`.

## Доступ к текущим настройкам сортировки в наследниках
Текущие настройки [WebObjectListView](web-object-list-view.html) складываются из наложенных разработчиком через публичное экземплярное свойство `InitialColumnsSort` + настройки, сделанные пользователем через интерфейс [WebObjectListView](web-object-list-view.html).

Получить доступ к агрегированным текущим настройкам сортировки можно через защищенное виртуальное свойство `ActualColumnSort` в классах-наследниках.

