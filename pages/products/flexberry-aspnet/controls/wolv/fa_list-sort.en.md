--- 
title: Sorting for WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_list-sort.html 
lang: en 
autotranslated: true 
hash: 96ad11fd385826cbb0e386964f9c06699d686c463cad95fb28a9938350ad47e9 
--- 

Lists allow you to sort the contained objects in specific columns. The user can set the necessary sorting. Also sorting the list can be customized from the code. 

## Sorting lists WOLV 

Sort columns [WebObjectListView](fa_web-object-list-view.html) by specifying properties `.InitialColumnsSort` type `ICSSoft.STORMNET.Business.ColumnsSortDef[]`. 

Example: 

```csharp
wolv.InitialColumnsSort = new ColumnsSortDef[] 
{
    new ColumnsSortDef("Name", SortOrder.Asc),
    new ColumnsSortDef("Registration", SortOrder.Asc),
};
``` 

Here `ColumnsSortDef` the constructor takes 2 parameters: the name of the column `string` and method of sorting type `ICSSoft.STORMNET.Business.SortOrder` (Asc, Desc, None). 

The sort priority determines the order of elements in the array `ColumnsSortDef`. 

### Access to current sorting settings in the heirs 

Current settings [WebObjectListView](fa_web-object-list-view.html) consist of imposed by the developer through a public instance property `InitialColumnsSort` settings made by the user via the interface [WebObjectListView](fa_web-object-list-view.html). 

To access the aggregated current sorting settings through a protected virtual property `ActualColumnSort` in descendant classes. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}