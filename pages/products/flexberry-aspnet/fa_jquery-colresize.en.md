--- 
title: Plugin to resize table columns (wolv, age) 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_jquery-colresize.html 
lang: en 
autotranslated: true 
hash: 4c25db4167f871cb8a88498a3385a75dc673f23d6925a9236eb6efcedb7bc70c 
--- 

`jquery.colresize` - a jQuery plugin designed to add functionality for setting the column width html tables. Used `WebObjectListView` and `AjaxGroupEdit`. Supports setting width of columns by mouse, linking multiple tables for synchronous settings. 

## Use 

First of all on the page should be connected to the file `jquery.colresize.css` and `jquery.colresize.js`. In order to apply to the table plugin, you need to call for the corresponding object function `colresize`. If you want to link tables, the jQuery collection should contain all these tables. 

As a parameter to the function `colresize` you can pass an associative array of parameters. You can set the following parameters: 

| Key | Description| 
|:----------------|:-----------------------------------------------------------| 
| `customizable` | `Boolean`. Determines whether the user is allowed to change the column width with the mouse| 
| `minWidth` | `Number`. The minimum column width that can be set| 
| `defaultWidth` | `Number`. Column width by default. Is used when width is not set, and `useDefaultWidth == true`| 
| `widths` | `Array`. An array of values of the width of the columns in the order they appear. These values will be used when initializing colresize| 
| `onResize(eventArgs)` | `Function`. The event handler change the width of columns. While in `eventArgs` there is only `currentTarget` pointing to the first table of a group of related tables.| 

## Example usage 

```javascript
jQuery('.resizable-table').colresize({
    customizable: true,
    minWidth: 50,
    defaultWidth: 100,
    widths: [110, 70, 170, 80],
    onResize: function(e) {
        alert('resized: ' + jQuery(e.currentTarget).id);
    }
});
``` 

## Work with WebObjectListView and AjaxGroupEdit 

Settings of column width is performed in the following order: 

* width is taken by default depending on the type of данных; 
* used to adjust the width of `ViewColumnProvider` (if they are there); 
* apply the settings made by the user (if any). 

Thus, the resulting width is equal to the last applied width. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}