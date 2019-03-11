--- 
title: ObjectTile 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_ajax-controls-object-tile.html 
lang: en 
autotranslated: true 
hash: b9ccf8c9a4e8ade716cac03be431b65cdcb7eb896413eeadc7cc5d98689106c9 
--- 

Control is used to display objects that are `DataObject`, according to a given representation, in the form of tiles on a web page. The default is to cast the attribute values to string, to change this you need to use a property `AttributeRenderers`. Implements interfaces `IObjectTile`, `IHavingResources`. 

## Interface 

### Methods 

| Signature | Description| 
|----------------|--------------------| 
| `protected override void OnLoad(EventArgs e)` | Overload a class method `WebControl` is called when the download control| 
| `protected override void RenderContents(HtmlTextWriter writer)` | Overload a class method `WebControl` used for rendering the control when placing it directly on the web form| 
| `public string GetMarkup()` | interface Implementation `IObjectTile`. Method to retrieve the html markup of the control as a string, used when you need to work directly with the markup| 

### Properties 

| Name | Type | Description| 
|---------------|-------------------|----------------------------| 
| `View` | `ICSSoft.STORMNET.View` | interface Implementation `IObjectTile`. Used to set or retrieve the view according to which objects are displayed| 
| `DataObject` | `ICSSoft.STORMNET.DataObject` | interface Implementation `IObjectTile`. Used to set or retrieve a data object that is displayed according to the tile| 
| `AttributeRenderers` | `Dictionary&#60;string, AjaxControls.AttributeRenderer&#62;` | Used to customize the display of individual attributes| 
| `Styles` | `IEnumerable&#60string&zgl62` | interface Implementation `IHavingResources`. Use to retrieve collections related to control of resources (CSS). But when you call a method `OnLoad` resources are automatically added to the page| 

## AttributeRenderers 

`AttributeRenderers` - property that allows to change the display of individual object attributes by using user-defined functions. It is a dictionary with keys of type `string` (attribute name) and values of type `AttributeRenderer` (user-defined function that takes and returns `object` `string`). The returned string should contain html markup that will be displayed instead of just cast the attribute value to the string. `AttributeRenderer` - delegate declared in `AjaxControls`: 

```csharp
public delegate string AttributeRenderer(object attribute);
``` 

## Example of initializing 

```csharp
//tempCat - an instance of the class Cat 
ObjectTile tile = new ObjectTile();
tile.View = Information.GetView("КошкаL", typeof(Кошка));
tile.DataObject = tempCat;

tile.AttributeRenderers = new Dictionary<string, AttributeRenderer>();
tile.AttributeRenderers.Add("Nickname", delegate (object val)
                                      {
                                          return string.Format("<b>{0}</b>", val);
                                      });
``` 

## Kind of control 

![](/images/pages/products/flexberry-aspnet/controls/object-tile.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}