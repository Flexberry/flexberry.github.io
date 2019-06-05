--- 
title: Configuring groups in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_grouping-age.html 
lang: en 
autotranslated: true 
hash: ea0021556ee413b97365bbff1ce648755a2464c2ce6f82e91e68efd109484290 
--- 

You can group columns on master (master of masters, etc.). For this AGE is the Grouping property. 

```csharp
public class Grouping
{
    /// <summary> 
    /// Hide empty groups (without objects) 
    /// </summary> 
    public bool HideEmptyGroups { get; set; }

    /// <summary> 
    /// Sort group 
    /// </summary> 
    public ColumnsSortDef MasterColSortDef

    /// <summary> 
    /// The name of the master (not the property of the master) 
    /// </summary> 
    public string MasterName 

    /// <summary> 
    /// Property name in the wizard that will appear in the group header 
    /// </summary> 
    public string MasterCaptionProp

    /// <summary> 
    /// Limit on group 
    /// </summary> 
    public Function MasterLimitFunction

    /// <summary> 
    /// Representation which will be read group 
    /// </summary> 
    public View MasterView
}
``` 

Example (gang wizard "Tiplady" as the group header will be used Tiplady.Name): 

```csharp
ctrlЛапа.Group =
                new Grouping(
                    new ColumnsSortDef(Information.ExtractPropertyPath<Лапа>(x => x.ТипЛапы.Название), SortOrder.Desc));
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}