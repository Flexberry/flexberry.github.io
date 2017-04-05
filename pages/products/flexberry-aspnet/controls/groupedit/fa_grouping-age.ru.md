---
title: Настройка группировки в AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_grouping-age.html
lang: ru
---

Можно сгруппировать колонки по мастеру (мастеру мастера и т.д.). Для этого в AGE есть свойство Grouping.

```csharp
public class Grouping
{
    /// <summary>
    /// Скрывать пустые группы (без объектов)
    /// </summary>
    public bool HideEmptyGroups { get; set; }

    /// <summary>
    /// Сортировка групп
    /// </summary>
    public ColumnsSortDef MasterColSortDef

    /// <summary>
    /// Название мастера (не свойство мастера)
    /// </summary>
    public string MasterName 

    /// <summary>
    /// Название свойства мастера, которое будет появляться в виде заголовка группы
    /// </summary>
    public string MasterCaptionProp

    /// <summary>
    /// Ограничение на группы
    /// </summary>
    public Function MasterLimitFunction

    /// <summary>
    /// Представление по которому будут читаться группы
    /// </summary>
    public View MasterView
}
```

Пример (групировка по мастеру "ТипЛапы", в качестве заголовка группы будет использоваться ТипЛапы.Название):

```csharp
ctrlЛапа.Group =
                new Grouping(
                    new ColumnsSortDef(Information.ExtractPropertyPath<Лапа>(x => x.ТипЛапы.Название), SortOrder.Desc));
```
 