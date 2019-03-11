--- 
title: ShowHideDiv 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_show-hide-div.html 
lang: en 
autotranslated: true 
hash: 130ddd7cdd1ac4105c721e9b168013de3c552004ba695497c5c90ea27db899c7 
--- 

`ICSSoft.STORMNET.Web.AjaxControls.ShowHideDiv` - control for showing/hiding content. 

### Properties: 

| Property | Type | Description| 
|-----------------|--------------------|----------------------------| 
| `Content` | `System.Web.UI.ControlCollection` | Content that you want to hide/show| 
| `CookieName` | `string` | Returns the name to maintain state control in the cookies (formed from the form name and ID control)| 
| `Title` | `string` | Header| 

### Methods: 

| Method | Description| 
|--------|---------| 
| `HideDiv()` | Hide content| 
| `ShowDiv()` | Show content of| 

## Example usage: 

```xml
...
<%@ Register TagPrefix="ac" Namespace="ICSSoft.STORMNET.Web.AjaxControls" Assembly="ICSSoft.STORMNET.Web.AjaxControls" %>
...
        <ac:ShowHideDiv ID="shd" runat="server">
            <img src="http://flexberry.ru/App_Themes/Blue/images/flex/Logo_h113px.png" />         
        </ac:ShowHideDiv>
...
``` 

## View 

### In the closed state: 

![](/images/pages/products/flexberry-aspnet/controls/show-hide-div-collapsed.png) 

### open: 

![](/images/pages/products/flexberry-aspnet/controls/show-hide-div-expanded.png) 

## Client methods 

Concealment and disclosure: `setExpand` 

```csharp
$('#ИД').showHide('setExpand', false)
``` 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}