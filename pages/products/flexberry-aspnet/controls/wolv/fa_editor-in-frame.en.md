--- 
title: Displaying on one page of a list form and edit form 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_editor-in-frame.html 
lang: en 
autotranslated: true 
hash: 14014d3f25c198b31860e10ac51119d1082520233f13026d7e2382fcf86823c3 
--- 

When the user selects an item in the list, it is useful to immediately see the item details and edit it. 

Below is an example of settings [WOLV](fa_web-object-list-view.html), allowing to display a list and 
[edit form](fa_editform.html) is selected in the list element with all its features. 

[Edit form](fa_editform.html) is displayed in the frame (if necessary, it can be collapsed or expanded). 

It is possible to configure where to display the [edit form](fa_editform.html) on the list: left, right, bottom, top. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/editor-in-frame1.png) 

## setup Example 

1. Add the iframe. 

```html
    <iframe id="editor" style="width:100%; height:600px;"></iframe> 
    ``` 

2. To install the event handler open the edit form from WOLV. 

```javascript
    $('#wolv').on('showeditingpage.wolv', function (e, d) {
        // Disables the underlying logic (go to the edit form). 
        e.preventDefault();
    
        // Setting address for the frame. 
        $('#editor').attr('src', d.data.editingUrl);
    });
    ``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}