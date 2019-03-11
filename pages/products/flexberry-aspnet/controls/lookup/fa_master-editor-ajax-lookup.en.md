--- 
title: MasterEditorAjaxLookUp 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_master-editor-ajax-lookup.html 
lang: en 
autotranslated: true 
hash: 6a4ea7172d705fde146a1e6f6c8bc1f7e926624d294ea65547a5694c35f66875 
--- 

If there is a form on lookup, and then it selects the object, then the request for the service. The service returns the list of controls whose values you want to change, and the values themselves. 

{% include note.html content="When working with the service uses [ServiceSecurityProvider](fa_service-security-provider.html)." %} 

The control has a property: 

```csharp
/// <summary> 
/// To add the type in ServiceSecurityProvider when using control 
/// </summary> 
public bool AddTypeToSecurityProvider = true;
``` 

By default, the control will add all the necessary types and the name of the methods in [ServiceSecurityProvider](fa_service-security-provider.html), but it will happen in the runtime and in the configuration XML file will not change. So, if you want all settings [ServiceSecurityProvider](fa_service-security-provider.html) were taken from the file and not changed during the execution, you need to add `ServiceSecurityProvider.xml` settings for the method `LoadObject`. If the service will not have the power for proof-reading objects from the database, it will always return empty values. 

## Possible problems 

If you deploy applications that use this control, it is necessary to ensure that, in `iis web сад` set only 1 process. Otherwise lucapa will work "over time." 

## UpdateOnDocumentReady 

To use this feature you need to carefully. The property is responsible for updating the values of lucapa when the page loads. By default, the value of the property `false`. 
The value in lookup affix `WebBinder`, but if for some reason you need to update lookup during page load, we should pay attention to focus on the page and possible side effects. 

## Autocompletion 

It is possible for the rendered Masterova property (for example, lookup type `Standard`) to add autocompletion and introduced Masterova property will bear the value in lookup. It is possible to implement similar functionality: [Linking AjaxAutocomplete and AjaxLookup](fa_link-ajax-autocomplete-ajax-lookup.html). 
To enable autocompletion, you need to install lucapa property `Autocomplete = true` and make sure lucapa affixed the value of the property `PropertyToShow` (in the standard case, it makes `WebBinder`). 

For example, 

```xml
<ac:MasterEditorAjaxLookUp Autocomplete="true" ID="ctrlВладелецАвтокомплит" CssClass="descTxt" runat="server" />
``` 

And the object marked with the attribute 

```csharp
[MasterViewDefineAttribute("Koskee", "Plateletapheresis", ICSSoft.STORMNET.LookupTypeEnum.Standard, "", "Name")]
``` 

For autocompletion, you can specify the following settings: 

```csharp
    /// <summary> 
    /// Whether to use the value in the object selection in autocomplete. 
    /// </summary> 
    public bool ApplyOnAutocompleteSelect = true;

    /// <summary> 
    /// Whether to use the value in autocomplete, if the change of focus. 
    /// </summary> 
    public bool ApplyOnAutocompleteLostFocus;

    /// <summary> 
    /// The name of the view to predictive. 
    /// </summary> 
    public string AutocompleteViewName;

    /// <summary> 
    /// To search by substring for autocomplete. 
    /// </summary> 
    public bool IsSubstring;

    /// <summary> 
    /// Property to predictive - default PropertyToShow. 
    /// </summary> 
    public string PropertyToAutocomplete;

    /// <summary> 
    /// Property to predictive - Multiline. 
    /// </summary> 
    public TextBoxMode PropertyToAutocompleteMultiline;

    /// <summary> 
    /// The number of characters which begins with predictive text input (default 2). 
    /// </summary> 
    public int AutocompleteMinChars;

    /// <summary> 
    /// Number of possible options to choose from when predictive input (default is 10). 
    /// </summary> 
    public int AutocompleteLimitCount;
``` 

It should be noted that for the [completion](fa_ajax-autocomplete.html) is called by another service method(`GetPropertyValues`), so if the property is modified `AddTypeToSecurityProvider` on `false`, you need to make sure that the object type is spelled out in `ServiceSecurityProvider.xml`. 

{% include important.html content="default property `IsSubstring` matters `False` =&gt; without changing the value in `True` search for the substring will not work." %} 

{% include important.html content="If PropertyToShow uses a property of the data object is not of string type, such as the type of date you should carefully read [article auto-Completion in web systems (AjaxAutocomplete)](fa_ajax-autocomplete.html), and make the object of special Nechranice computable property. 
And as for `PropertyToShow` `MasterEditorAjaxLookup` to specify that it is a computable property." %} 

## Creating dependent lyapov 

About the creation of dependent lyapov described in [article Linking controls edit masters](fa_linked-master-editors.html). 

## Button to view 

If you specify lucapa property `ShowObjectUr`l, next to the buttons lucapa will see another button by clicking on which you can view artisan object 

```xml
<uc1:MasterEditorLookUp ID="ctrlВладелец" ShowObjectUrl="~/forms/Vladelec/VladelecE.aspx" CssClass="descTxt" runat="server" />
``` 

Also, if you have a WOLV on the rising form lucapa is not specified the path to the edit form, `ShowObjectUrl` put WOLV as `EditPage`. The size of the popup window is taken from the size of the form, which rises to lookup. 

## HTML rendering properties, for example, pictures 

If `PropertyToShow` need to display HTML, you need to have the properties in the object was an attribute `IsHTMLAttribute` (i.e. `ViewColumnProvider.GetPropertyIsHTML` of the property would return `true`). 

![Example](/images/pages/products/flexberry-aspnet/controls/lookup/ajax-look-html.png) 

## specifies the types of objects available for selection in lucapa 

For the job types displayed in `MasterEditorAjaxLookUp` objects, use the property `MasterTypes`: 

```csharp
public Type[] MasterTypes { get; set; }
``` 

By default, this property stores the types defined for an editable object properties in the attribute [a typeusage](fo_type-usage-problem.html). If the attribute of the corresponding property is missing, use the type of properties `MasterTypeName` and his descendants, this selects only the stored classes. To initialize property `MasterTypes` need to `OnInit` or `OnPreInit`. 

## [JS API](fa_javascript-api.html) 

For simple LookUp on the client side, use JS `LookUp API`, which is a jQuery plugin (`icsMasterEditorAjaxLookup`). 

### Operations 

| Name | Parameters | Description 
|------------------|--------------------|------------------------------| 
| clear | | Method of cleaning lucapa| 
| block | | blocking Method lucapa. Blocks the input field and sets the lockout flag handlers for clicks on the buttons| 
| show | | Method run window lucapa| 
| unblock | | unlock Method lucapa. Unlocks the entry field and resets the flag set of blocking handlers for clicks on the buttons| 
| val | value | Method to set the value lucapa. Lucapa is the value `value`| 
| updateOptions | Cm. in the table below. | Update method of the plugin options| 

### Parameters `updateOptions` 

| Parameter | Description| 
|---------------|---------------------------------| 
| `Свойства лукапа`|| 
| LFName | Name LimitFunction stored in session| 
| viewname | view Name, similar to lucapa `MasterViewName`| 
| typename | Name type to be used, similar to lucapa `MasterTypeName`| 
| connStrName | Name of the connection string, similar to lucapa `ConnStrName`| 
| nameValueControl | `ClientID` lucapa, which will bear value lookup forms `OBSOLETE`| 
| FormCaption | Header opened at lucap shape, similar to lucapa `LookUpFormCaption`| 
| CountOnPage | Number of elements on the page WOLV'and pages lucapa, lucapa similar to `LookUpFormCountOnPage`| 
| EditPage | edit Page elements WOLV'a page-lucapa| 
| csdName | Name set storage for columns stored in session|- 
| `Свойства open формы`|| 
| width | Width of the shape default `800`| 
| height | form Height, default `600`| 
| left | left margin| 
| top | the top margin| 
| status | the status bar Text in a standard browser window| 
| resizable | Can I change the window size| 
| menubar | whether to Show the browser menu| 
| toolbar | Show toolBar browser| 
| caption | window Title| 
| `Прочие параметры`|| 
| useDefaultWindow | Use the browser window to open lookup forms default `false`| 
| formParams | Url-window options lucapa| 
| url | Link to window lucapa| 
| additionalControls | Related lucapa| 

### Practical implementation 

The buttons that are: 

* clear lookup 
* block lookup 
* unlock lookup 
* open the form lucapa 
* replace the address of the form lucapa 

To add client button and hang handlers `onclick`: 

```javascript
<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('unblock'); return false;">РАЗБЛОКИРОВАТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('block')return ; false;">ЗАБЛОКИРОВАТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('clear'); return false;">ОЧИСТИТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('show')return ; false;">ОТКРЫТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('updateOptions', {lookup:{LFName:'LFName1'}}); return false;">Настройки</button>
``` 

{% include important.html content="Without adding `return false;` PostBack will occur and it will not work."%} 

To call `JS API MasterEditorAjaxLookup` when the page is loaded we need to call these functions at the end of the event `ready`. There are a couple of ways: 

1.Processing events `load`. 

```javascript
$(window).load(function () {
    $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('block');
    $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('clear');
});
``` 

2.Using the trick of adding an event handler `ready` when processing this same event. This method works because the handlers are executed in the order in which they were added.

```javascript
$(function () {
    $(function() {
        $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('block');
        $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('clear');
    });
});
``` 

#### Setting `MasterEditorAjaxLookup` 

For example, if the button is pressed `changeMasterLookUpValue` should change the value `MasterEditorAjaxLookup` `ctrlLimitEditorMaster1` specified in the control `masterLookUpValues`: 

```javascript
$(document).ready(function () {
$('#<%= changeMasterLookUpValue.ClientID %>').click(function () {
    $('#<%=ctrlLimitEditorMaster1.ClientID%>').icsMasterEditorAjaxLookup('val', $('#<%=masterLookUpValues.ClientID%>').val());
    return false;
});
});
``` 

### Events 

| Event | Description| 
|------------|---------------------------------------| 
| change | Triggered when you select the wizard, and "smearing" of values lucapa at the controls| 

#### Practical implementation of the control 

Cm. [LookUp](fw_lookup.html). 

### Bondage lyapov inside other elements 

Cm. [an article Linking LookUp's AGE with external LookUp'ω](fa_change-lcs-lookup-age.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}