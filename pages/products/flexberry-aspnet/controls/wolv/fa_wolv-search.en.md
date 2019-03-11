--- 
title: Search WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_wolv-search.html 
lang: en 
autotranslated: true 
hash: 4a47afbb3bc81362d22a68f8d8f57c91b878867859b840333dbeaaa7729c8c07 
--- 

The search system allows to search the displayed list items. In contrast [filters](fa_wolv-filters.html), search does not impose a limit on the list, it only selects objects using [flags](fa_wolv-check-boxes.html). 

{% include note.html content="When you search the currently selected objects is cleared." %} 

## Enabling search 

By default the search is enabled. The inclusion of search answers [operation](fa_wolv-operations.html) `Search`. To disable the search button you need the page loads to set this operation in `false`: 

```csharp
webObjectListView1.Operations.Search = false;
``` 

Enabling this option adds the Toolbar WOLV search button ![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-search-btn.png) 

Clicking on this button, an additional panel. 

## Simple search 

If [operation](fa_wolv-operations.html) `FullViewSearch == false`, the panel will appear simple search: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/simple-search.png) 

This type of search offers the user to select a specific column which is searched. 

## search the entire presentation 

If [operation](fa_wolv-operations.html) `FullViewSearch == true`, you will see a search bar throughout the performance: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-search-full.png) 

From a simple search it differs in the absence of selecting a data column, as the search is performed on all columns at once. 

## Rules 

1. If the field is a string, it is searched for occurrences of a substring in a string. 
2. If the field has any other type, it is searched for an exact match with the entered string. 

## Special Characters 

The input string is a valid use of the following symbols: 

1. `*` - any number of any characters (including 0). 
2. `?` - any single character. 
3. `[abcdef]` or `[a-f]` any of the characters. 
4. `[^abcdef]` or `[^a-f]` - any character except those specified. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}