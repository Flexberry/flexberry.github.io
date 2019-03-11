--- 
title: auto-Completion in web systems (AjaxAutocomplete) 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_ajax-autocomplete.html 
lang: en 
autotranslated: true 
hash: 2276ac97f8ec78ce32dc6a4e0991bdf08979eafea25c0dfa77e6a7f5cf64bb51 
--- 

With `AjaxAutocomplete` possible, for example, when you edit the object name to get auto-completion for this field, which will offer the names of existing objects that begin with the entered characters. `AjaxAutocomplete` use of jquery ui so jquery ui will need to be connected. 

Also, it is possible to associate [with autocompletion lukapa](fa_link-ajax-autocomplete-ajax-lookup.html). Either enable auto-completion from [Ajax lucapa](fa_master-editor-ajax-lookup.html). 

# Connection 

If the file is not connected, you must perform the following: 
1. To update the version `ICSSoft.STORMNET.Web.AjaxControls.dll` 
2. Add styles `jquery-ui.css` and a picture with the loading `indicator.gif`; 
3. Add library `jquery ui`; 
4. To update the version `ICSSoft.STORMNET.Web.Tools.dll` 

# Use 

You should make sure that the settings for [ServiceSecurityProvider](fa_service-security-provider.html) are correct. 
In order to specify the control to which to apply autotopagnosia, you add a method 

```csharp
AjaxAutocomplete.AddControlAutocomplete(контрол, тип_объектов, свойство_объекта)
``` 

for example, in `Page_Load()`; 

You can specify whether to use search for a substring and limiting. 

```csharp
//AjaxAutocomplete.AddControlAutocomplete(control, typoberlin, svoistvakh, использовать_ли_поиск_по_подстроке, limit); 
AjaxAutocomplete.AddControlAutocomplete(ctrlЦена, typeof(Кошка), "The price", false, func2);
``` 

## Example 

```csharp
AjaxAutocomplete.AddControlAutocomplete(ctrlКличка, typeof(Кошка), "Nickname");
AjaxAutocomplete.AddControlAutocomplete(ctrlЦена, typeof(Кошка), "The price");
``` 

## Sorting and Distinct 

If input is specified in the view, then all the fields will fall under Distinct. To apply sorting on any property in view. 

## Coloring matching characters in the dropdown list 

All the javascript associated with the autocomplete file is `AjaxDataService.js`. 

Coloring characters were redefined `renderItem` the prototype autocomplete. 

```javascript
$(function () {
    $.ui.autocomplete.prototype._renderItem = function (ul, item) {
        var re = new RegExp(this.term, 'i');
        var t = item.label.replace(re, "<span style='font-weight:bold;color:Blue;'>" +
            this.term +
                "</span>");
        return $("<li></li>")
            .data("item.autocomplete", item)
            .append("<a>" + t + "</a>")
            .appendTo(ul);
    };
});
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}