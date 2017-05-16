---
title: ShowHideDiv
sidebar: flexberry-aspnet_sidebar
keywords: 'Flexberry ASP-NET, Web UI (Контролы)'
toc: true
permalink: en/fa_show-hide-div.html
lang: en
published: true
---

`ICSSoft.STORMNET.Web.AjaxControls.ShowHideDiv` - control to show/hide a content. 

### Settings:

| Settings | Type | Description|
|-----------------|--------------------|----------------------------|
| `Content` | `System.Web.UI.ControlCollection` | Content to hide/show|
| `CookieName` | `string` | Returns the name to save the status of the control in cookies (it is formed from the name of the form and the ID of the control)|
| `Title` | `string` | Header|

### Methods:

| Method | Description|
|--------|---------|
| `HideDiv()` | Hide the content|
| `ShowDiv()` | Show the content|

## Example of use:

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

### In the showed state:

![](/images/pages/products/flexberry-aspnet/controls/show-hide-div-expanded.png)

## Client methods

Hide and show: `setExpand`

```csharp
$('#ИД').showHide('setExpand', false)
```
