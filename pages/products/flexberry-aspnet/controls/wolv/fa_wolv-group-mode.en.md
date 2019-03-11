--- 
title: Mode of grouping data by the specified field 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-group-mode.html 
lang: en 
autotranslated: true 
hash: 11542c7d72b229ed53b8300acc4dc8ba0733ec917436b07d0617dd10605cb607 
--- 

## mode group data 
For WebObjectListView mode display data grouped by the specified field, in which all available data 
will not load page by page, and all at once on a single page, the data will be grouped 
according to values of predetermined properties of objects. 

The mode of grouping data by the specified field can be enabled from the behind code page by checking the control setting GroupByProperty. 

```csharp
/// <summary> 
/// Called the first in the Page_Load. 
/// </summary> 
protected override void Preload()
{
    // Define the property that will be implemented by the group. 
    WebObjectListView1.GroupByProperty = Information.ExtractPropertyPath<Квартира>(x => x.ВидОтделки);
    
    // Indicate that you want the toolbar present button on/off mode of the group data. 
    // This button is not mandatory, the group will handle without it. 
    WebObjectListView1.Operations.ToggleGroupByModeToolbarButton = true;
}
``` 

After you download the list it will look like the following: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/group-by-mode1.png) 

## mode group data in a hierarchical wizard 
Grouping data by a hierarchical field in the wizard can be handy if groups will also be arranged hierarchy. 
WebObjectListView provided for this mode of grouping the data. 
To enable grouping of data in a hierarchical wizard, you must set several properties of a control in behind-code page 

```csharp
/// <summary> 
/// Called the first in the Page_Load. 
/// </summary> 
protected override void Preload()
{
    // Define a hierarchical wizard, which will be implemented by the group. 
    WebObjectListView1.GroupByProperty = Information.ExtractPropertyPath<Квартира>(x => x.Дом.Город);
    
    // Define a property hierarchy within a set of hierarchical master. 
    WebObjectListView1.GroupByPropertyHierarchy = Information.ExtractPropertyPath<Город>(x => x.Иерархия);
    
    // Set the caption for the group of records in which the selected master. 
    // This setting is optional, if not set, then it will use the default value. 
    WebObjectListView1.GroupByPropertyUndefinedText = "Apartment houses in the uncertain cities";
}
``` 

After you download the list it will look like the following: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/group-by-mode2.png) 

## setting of signatures in the group headers 
By default, the signature in the headers of the groups are formed from the names of the visible fields pertaining to property groups and their values separated by a colon (the field Name: field value from records in the group the Name of another field: the field value of the records in the group ...). 

If necessary, the signature in the headers of the groups can be flexibly configured, this is a special delegate GroupByPropertyGetCaption. 

```csharp
/// <summary> 
/// Called the first in the Page_Load. 
/// </summary> 
protected override void Preload()
{
    // Define a hierarchical wizard, which will be implemented by the group. 
    WebObjectListView1.GroupByProperty = Information.ExtractPropertyPath<Квартира>(x => x.Дом.Город);
    
    // Define a property hierarchy within a set of hierarchical master. 
    WebObjectListView1.GroupByPropertyHierarchy = Information.ExtractPropertyPath<Город>(x => x.Иерархия);
    
    // Set the caption for the group of records in which the selected master. 
    // This setting is optional, if not set, then it will use the default value. 
    WebObjectListView1.GroupByPropertyUndefinedText = "Apartment houses in the uncertain cities";
    
    // Define the application method of forming signatures in the rows which are headings of groups. 
    WebObjectListView1.GroupByPropertyGetCaption = (WolvGroupByCaptionData captionData) =>
    {
        int cityNameIndexInView = captionData
            .RowContext
            .ViewToDisplay.GetPropertyIndex(Information.ExtractPropertyPath<Квартира>(x => x.Дом.Город.Наименование));

        return $"Apartment houses in the city \"{captionData.DataObject.ObjectedData[cityNameIndexInView]}\"";
    };
}
``` 

After you download the list it will look like the following: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/group-by-mode3.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}