--- 
title: ObjectTileView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_ajax-controls-object-tile-view.html 
lang: en 
autotranslated: true 
hash: a310d36dd48f27d5fa879a02bc2790169401d0a48e4563ef5e44ee3ce075ef04 
--- 

To display a specific object is a control that implements the interface `IObjectTile`. Type of tiles is defined by the property TileType. The default as used tiles [AjaxControls.ObjectTile](fa_ajax-controls-object-tile.html). 

## Interface 

### Methods 

| Signature | Description| 
|-----------------------|------------------------------|-------------------------------| 
| `protected override void OnLoad(EventArgs e)` | Overload a class method `WebControl` is called when the download control| 
| `protected override void RenderContents(HtmlTextWriter writer)` | Overload a class method `WebControl` used for rendering the control when placing it directly on the web form| 
| `protected override IEnumerable<SсriptDesсriptor> GetSсriptDesсriptors()` | Overload a class method `SсriptControl`. Method to retrieve the handles of the scripts used control| 
| `protected override IEnumerable<SсriptReference> GetSсriptReferences()` | Overload a class method `SсriptControl`. Method for obtaining of links to scripting resources used by the control.| 
| `public string GetInitiaMarkup()` | Method that returns a string with the initial html markup of the control (containing a script to ajax the first page)| 
| `public static string GetMarkup(int pageNum, string guid)` | Static method that returns a string with the markup for the page you want. It calls the web service `ObjectTileViewService` when processing ajax requests. Settings are taken from the session on `guid`'| 

### Properties 

| Name | Type | Description| 
|-------------------------|---------------------------|----------------------------------| 
| `View` | `ICSSoft.STORMNET.View` | Used to set or retrieve the view according to which objects are displayed| 
| `Type` | `System.Type` | Used to set or retrieve the type of objects you want to upload and display| 
| `ItemsPerPage` | `uint` | Number of tiles per page (if `0`, the paging is off)| 
| `LimitFunction` | `ICSSoft.STORMNET.FunctionalLanguage.Function` | Restriction on downloadable objects| 
| `ColumnsSorting` | `List&#60ColumnsSortDef&zgl62` | List records the columns on which to sort the displayed objects, in order of priority| 
| `Styles` | `IEnumerable&#60string&zgl62` | interface Implementation `IHavingResources`. Use to retrieve collections related to control of resources (CSS). But when you call a method `OnLoad` resources are automatically added to the page| 
| `TileType` | `System.Type` | Grade control to be used as a tile (to display a single object). Must implement `AjaxControls.IObjectTile`. To the head of the page to connect stylesheets used control, you can implement an interface `AjaxConrols.IHavingResources`. By default, the class [AjaxControls.ObjectTile](fa_ajax-controls-object-tile.html)| 
| `TileProperties` | `Dictionary<string, object>` | Property allowing to specify the values of custom properties for the displayed tiles. Described in detail below| 

## TileProperties 

`TileProperties` - dictionary with keys of type `string` (property name) and values of type `object` (value properties). First, it is recommended to set the property names by using the define string constants for the reason that when you specify a non-existent name (or assuming the errors in it) the control will work normally except that the value of the property will not be rated. Secondly, should pay attention to the type of the transmitted value, which should be the heir of the type of the corresponding property or be them. 

### an Example of using properties `TileProperties` 

For a class of tiles `ObjectTile` sets the property `AttributeRenderers`. 

```csharp
//expander control class MasterExpander 
Dictionary<string, AttributeRenderer> renderers = 
                new Dictionary<string, AttributeRenderer>();
renderers.Add("Nickname", delegate (object val)
                        {
                            return string.Format("<b>{0}</b>", val);
                        });
expander.TileProperties.Add(ObjectTile.AttributeRenderersPropertyName, renderers);
``` 

## Web.config 

To enable the web service `ObjectTileViewService.asmx` you must add the entry in `Web.config`: 

#### IIS6 

```xml
<configuration>
...
<system.web>
    ...
    <httpHandlers>
        ...
        <add verb="*" path="ObjectTileViewService.asmx" validate="false" 
          type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.ObjectTileViewHandlerFactory" />
        ...  
    </httpHandlers>
    ...
  </system.web>
  ...
</configuration>
``` 

#### For IIS7 

```xml
<configuration>
...
<system.webServer>
    ...
    <handlers>
        ...
        <add name="ObjectTileViewHandler" path="ObjectTileViewService.asmx" 
          verb="*" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.ObjectTileViewHandlerFactory" 
          resourceType="Unspecified" preCondition="integratedMode" />
        ...  
    </handlers>
    ...
  </system.webServer>
  ...
</configuration>
``` 

## Kind of control 

![](/images/pages/products/flexberry-aspnet/controls/object-tile-view.png) 

## CSS 

To change the control, you can override the CSS attributes for the classes: 

* The outer div control: `div.otv-tile-view` 
* The display area of the tiles: `div.otv-tiles-area` 
* The selection page: `div.otv-page-select-area` 
* Select button page: `a.otv-page-button` 
* Button is selected page: `a.otv-selected-page-button` 

## Example of a separate use ObjectTileView 

To add a static control in aspx markup, you first need to register the namespace prefix (or tag) control, for example: 

```xml
...
<%@ Register TagPrefix="ac" Namespace="ICSSoft.STORMNET.Web.AjaxControls" Assembly="ICSSoft.STORMNET.Web.AjaxControls" %>
...
``` 

The control ObjectTileView must be present on the page ScriptManager'a: 

```xml
...
<asp:ScriptManager runat="server"/>
...
``` 

To desired location add the control: 

```xml
...
<ac:ObjectTileView runat="server" ID="myTileView"/>
...
``` 

To initialize the control you need when the page is loaded (in method `Page_Load`) to do the following: 

```csharp
// Set the view according to which database will be loaded and displayed objects 
myTileView.View = Information.GetView("DataTypeL", typeof(DataType));
// Set the class of the object that will be displayed in the control 
myTileView.Type = typeof(DataType);
``` 

To work control the necessary library connected `jQuery`. The other properties are optional and described above. If you want to customize appears in the control, see the property TileType. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}