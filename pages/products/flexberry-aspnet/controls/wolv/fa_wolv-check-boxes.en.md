--- 
title: The Behavior of the check boxes to select items WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-check-boxes.html 
lang: en 
autotranslated: true 
hash: 00b51acb85305af753e8d709bf9359f2dc29d7da8bbbace16a7bc081ee86f26f 
--- 

[WOLV](fa_web-object-list-view.html) provides the ability to select multiple items to perform on them some action (e.g. delete). 

{% include note.html content="This option is available only if the corresponding set [WOLV](fa_web-object-list-view.html): 

```csharp
 webObjectListView1.Operations.MultiSelect = true;
``` 
"%} 

Select object by clicking left mouse button on the checkbox in the row. 

Also for the convenience of working with a variety of objects added two buttons: 

* `Отметить все` 
* `Отметить all in all страницах` 

## Mark all 

This option selects all objects on the current page. Pressing again deselects all objects on the page. 

## Mark all on all pages 

This option selects all objects on all pages and blocks the possibility of manual selection. 

## Behavior of selected objects 

After filtering, the selected objects retain their selection. 

For example, if the list contains 3 objects: `Саша Masha Вася`, the user has selected the objects `Саша` and `Вася`, and then used the [search](fa_wolv-search.html) mask `*аша`, then the list will display 2 objects: `Саша Маша`, this will select only 1 object `Саша`. 

{% include note.html content="If you marked more than 100 elements, and found more than 100 items from a list is marked, then the marked will be only the first 100, and the user will see a warning message. At the moment, the number 100 cannot be changed." %} 

Also, the selection is preserved when changing the page displayed objects and when you change the number displayed on the page objects. 

However, a Search for [WOLV'y](fa_web-object-list-view.html) resets the selected objects. Before you reset the user asks a clarification question. 

## JS API 

For the manipulation of flags on the client side you can use [JS API](fa_js-api-wolv.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}