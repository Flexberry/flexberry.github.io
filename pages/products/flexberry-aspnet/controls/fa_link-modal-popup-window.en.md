--- 
title: LinkModalPopupWindow 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_link-modal-popup-window.html 
lang: en 
autotranslated: true 
hash: 7ae1e6d6af086b407ac3c6626a735fa7c8f31e7a62a16684bef07a1cdd970392 
--- 

With this control you can raise a modal window and open the page. 

## Connection 

The control can be connected in two ways: 

* positioning on the page 
* add dynamically. 

## Use 

Positioning the control on the page and configure: 

```xml
<ac:LinkModalPopupWindow ID="myLink" EnableViewState="false" runat="server" URL="homework.aspx" Enabled="true" Text=Link WindowTitle=Homework />
``` 

Can this control to add dynamically or even render manually. 
Or receive using the following function: 

```csharp
LinkModalPopupWindow.GetHtml(this.ClientID + "_lmpw" + j.ToString(), "Read more", "TemaPlanLessonE.aspx?LookUp=true&amp;pk={0}", "Thematic lesson plan", 640, 480, true)
``` 

### Settings control 

| Parameters | Description| 
|---------------|--------------------| 
| ClientId | ID-control| 
| Text | link Text| 
| Url | Address that you want to open| 
Enabled | Available link| 
| WindowTitle | window Title| 
| ATitle | Tooltip for link| 

### formatting Settings 

| Parameters | Description| 
|-----------------------|----------------------| 
| WindowWidth | Width of window| 
| WindowHeight | Height of window|- 
| CssStyle | Style links|- 
| CssClass | CSS Class links| 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}