--- 
title: MasterExpander 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_ajax-controls-master-expander.html 
lang: en 
autotranslated: true 
hash: c7126be85bcd7c4f89d0ad273556976cdf8eb4c23d5b89241ad8076f0334c198 
--- 

Web control for displaying objects deployed by groups formed according to the value of the master. Objects without a master are placed in the category `Остальное`. To display each group uses control [ObjecTileView](fa_ajax-controls-object-tile-view.html). 

## Interface 

### Methods 

| Signature | Description| 
|---------------------------------------|----------------------------------------| 
| `protected override void OnLoad(EventArgs e)` | Overload a class method `WebControl` is called when the download control| 
| `protected override void RenderContents(HtmlTextWriter writer)` | Overload a class method `WebControl` used for rendering the control when placed on a web form| 
| `protected override IEnumerable<SсriptDesсriptor> GetSсriptDesсriptors()` | Overload a class method `SсriptControl`. Method to retrieve the handles of the scripts used control| 
| `protected override IEnumerable<SсriptReference> GetSсriptReferences()` | Overload a class method `SсriptControl`. Method for obtaining of links to scripting resources used by the control.| 
| `public static string GetMarkup(string guid, int pageNum)` | Static method that returns a string with the markup for the page you want. It calls the web service `MasterExpanderService` when processing ajax requests. Settings are taken from the session on `guid`'| 
| `public static string GetTileViewMarkup(string guid, int index)` | Static method that returns a string with markup specific `ObjectTileView`. It calls the web service `MasterExpanderService` when processing ajax requests expand a category. Settings are taken from the session on `guid`'. Inside the method the instance `ObjectTileView`'s receipt markup| 

### Properties 

Properties which names match the properties of the `ObjecTileView` are simply passed to a specific instance of this control when it is created, about which you can read [the article ObjectTileView](fa_ajax-controls-object-tile-view.html). The value `TilesPerPage` will be recorded in `ItemsPerPage` each `ObjectTileView`. The other properties listed below: 

| Name | Type | Description| 
|--------------|------------------------|------------------------| 
| `MastersPerPage` | `uint` | the Number of categories on one page `MasterExpander`| 
| `MasterName` | `string` | the name of the attribute objects **stores master** used to group| 
| `MasterAttributeName` | `string` | the name of the attribute **masters**, which is separation. If, for example, to group objects by topic name, then `MasterName` will be equal to `Тема` and `MasterAttributeName` - `Название`| 
| `SectionHeaderUserContentGenerator` | `ICSSoft.STORMNET.Web.AjaxControls.<br>MasterExpanderUserContentDelegate` | is Used to specify a function that will generate the custom content in the headers of each category. Read more about the delegate type MasterExpanderUserContentDelegate written below.| 

### MasterExpanderUserContentDelegate 

```csharp
    public delegate string MasterExpanderUserContentDelegate<in TData>(
        MasterExpander.MasterExpanderSettings settings,
        TData data);
``` 

The delegate that will be used for the transmission of user-defined functions generating content in the control MasterExpander. As parameters the function should accept the settings MasterExpander (type MasterExpander.MasterExpanderSettings) and any data that depends on the context of use. The following is an example implementation of the function to add markup to the title of the category MasterExpander, where the "data" is the index categories, respectively, `TData` specified in `int`. 

```csharp
protected static string GenerateSectionHeaderContent(
    MasterExpander.MasterExpanderSettings settings,
    int index)
{
    return string.Format("<p>Category # {0} of {1}</p>", index, 
    settings.MastersValues.Count);
}
``` 

### Web.config 

To enable the web service `MasterExpanderService.asmx` you must add the entry in `Web.config`: 

#### IIS6 

```xml
<configuration>
...
<system.web>
    ...
    <httpHandlers>
        ...
        <add verb="*" path="MasterExpanderService.asmx" 
        validate="false" type="AjaxControls.MasterExpanderHandlerFactory" />
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
        <add name="MasterExpanderHandler" path="MasterExpanderService.asmx"
        verb="*" type="AjaxControls.MasterExpanderHandlerFactory" 
        resourceType="Unspecified" preCondition="integratedMode" />
        ...  
    </handlers>
    ...
  </system.webServer>
  ...
</configuration>
``` 

## Kind of control 

![](/images/pages/products/flexberry-aspnet/controls/master expander.png) 

## CSS 

To change the control, you can override the CSS attributes for the classes: 

* The outer div control: `div.me-masterName-expander` 
* The title of the category: `div.me-section-header` 
* The unfolding of div (containing `ObjectTileView`): `div.me-expandable` 
* The selection page: `div.me-page-select-area` 
* Select button page: `a.me-page-button` 
* Button is selected page: `a.me-selected-page-button` 
* Properties for `ObjectTileView` can also be viewed in the appropriate [article ObjectTileView](fa_ajax-controls-object-tile-view.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}