---
title: Режим группировки данных по заданному полю
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-group-mode.html
lang: ru
---

## Включение режима группировки данных
Для WebObjectListView предусмотрен режим отображения данных с группировкой по заданному полю, в котором все доступные данные
будут загружаться не постранично, а все сразу на единственной странице, при этом данные будут сгруппированы
по значениям заранее заданного свойства объектов.

Режим группировки данных по заданному полю можно включить из behind-кода страницы, проставив у контрола настройку GroupByProperty.

```csharp
/// <summary>
/// Вызывается самым первым в Page_Load.
/// </summary>
protected override void Preload()
{
    // Зададим свойство, по которому будет осуществляться группировка.
    WebObjectListView1.GroupByProperty = Information.ExtractPropertyPath<Квартира>(x => x.ВидОтделки);
    
    // Укажем, что хотим, чтобы на панели инструментов присутствовала кнопка включения/отключения режима группировки данных.
    // Эта кнопка не обязательна, группировка будет рабатать и без нее.
    WebObjectListView1.Operations.ToggleGroupByModeToolbarButton = true;
}
```

После загрузки списка он будет выглядеть следующим образом:

![](/images/pages/products/flexberry-aspnet/controls/wolv/group-by-mode1.png)

## Включение режима группировки данных по иерархическому мастеру
При группировке данных по полю иерархического мастера, может быть удобным, если для групп также будет выстроена иерархия.
Для WebObjectListView предусмотрен такой режим группировки данных.
Чтобы включить режим группировки данных по иерархическому мастеру необходимо задать несколько свойств контрола в behind-коде страницы
 
```csharp
/// <summary>
/// Вызывается самым первым в Page_Load.
/// </summary>
protected override void Preload()
{
    // Зададим иерархического мастера, по которому будет осуществляться группировка.
    WebObjectListView1.GroupByProperty = Information.ExtractPropertyPath<Квартира>(x => x.Дом.Город);
    
    // Зададим свойство иерархии внутри заданного иерархического мастера.
    WebObjectListView1.GroupByPropertyHierarchy = Information.ExtractPropertyPath<Город>(x => x.Иерархия);
    
    // Зададим подпись к группе записей,  в которых не выбран мастер.
    // Эта настройка опциональна, если она не задана, то будет использовано значение по умолчанию.
    WebObjectListView1.GroupByPropertyUndefinedText = "Квартиры с домами в неопределенных городах";
}
```

После загрузки списка он будет выглядеть следующим образом:

![](/images/pages/products/flexberry-aspnet/controls/wolv/group-by-mode2.png)

## Настройка подписей в заголовках групп
По умолчанию подписи в заголовках групп формируются из наименований видимых полей, относящихся к свойству группировки, и их значений разделенных двоеточием (Имя поля: значение поля у записей в группе Имя другого поля: значение поля у записей в группе ...).

При необходимости, подписи в заголовках групп можно гибко настраивать, для этого предусмотрен специальный делегат GroupByPropertyGetCaption.

```csharp
/// <summary>
/// Вызывается самым первым в Page_Load.
/// </summary>
protected override void Preload()
{
    // Зададим иерархического мастера, по которому будет осуществляться группировка.
    WebObjectListView1.GroupByProperty = Information.ExtractPropertyPath<Квартира>(x => x.Дом.Город);
    
    // Зададим свойство иерархии внутри заданного иерархического мастера.
    WebObjectListView1.GroupByPropertyHierarchy = Information.ExtractPropertyPath<Город>(x => x.Иерархия);
    
    // Зададим подпись к группе записей,  в которых не выбран мастер.
    // Эта настройка опциональна, если она не задана, то будет использовано значение по умолчанию.
    WebObjectListView1.GroupByPropertyUndefinedText = "Квартиры с домами в неопределенных городах";
    
    // Определим прикладной способ формирования подписей в строках, которые являются заголовками групп.
    WebObjectListView1.GroupByPropertyGetCaption = (WolvGroupByCaptionData captionData) =>
    {
        int cityNameIndexInView = captionData
            .RowContext
            .ViewToDisplay.GetPropertyIndex(Information.ExtractPropertyPath<Квартира>(x => x.Дом.Город.Наименование));

        return $"Квартиры с домами в городе \"{captionData.DataObject.ObjectedData[cityNameIndexInView]}\"";
    };
}
```

После загрузки списка он будет выглядеть следующим образом:

![](/images/pages/products/flexberry-aspnet/controls/wolv/group-by-mode3.png)
