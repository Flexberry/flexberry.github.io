--- 
title: WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_web-object-list-view.html 
lang: en 
autotranslated: true 
hash: 4638e4b83fc0a4faadb9858b96057202ce253af142f72367e6e705cf3d91b86b 
--- 

**WebObjectListView** (hereinafter, `WOLV`) is a web control that is used to display a list of objects. 

## appearance WOLV 

### appearance WOLV when using different themes 

Appearance WOLV when using different themes you can see in the article, the Choice of theme of Web apps](fa_choose-theme.html). 

### Stylization 

Main article Pro [styling WebObjectListView](fa_wolv-stylization.html). 

### CSS classes 

Main article about [CSS-classes WebObjectListView](fa_wolv-css.html). 

### Paging 

Main article Pro [paging WebObjectListView](fa_wolv-paging.html). 

### Fixing caps list 

To fix the header of the list by adding to the page client script: 

```javascript
$('.wolv-caption-wrapper').sticky( { topSpacing : <x> } );
``` 

where `x` - height from top edge in pixels. 

### scroll the list `ScrollToSelectedObject` 

After the transaction with the item, the list scrolls to the selected data object. 
The setting is convenient to apply to large lists, when the selected object is not by default always occurs in the zone of visibility of the user. 

To switch the setting: 

```csharp
WebObjectListView1.Operations.ScrollToSelectedObject = true;
``` 

### Save state scroll list 

Included with settings: 

```csharp
WebObjectListView1.Operations.SaveHorizontalScroll = true;
WebObjectListView1.Operations.SaveVerticalScroll = true;
``` 

Position scrolls the list will always recover, including the page navigation and sorting in `WOLV`. 

### Displaying hierarchical data 

Main article Pro [hierarchical WebObjectListView](fa_wolv-hierarhy.html). 

### data Grouping 

Main article Pro [grouping data in WebObjectListView](fa_wolv-group-mode.html). 

### data Download and EmptyControl 

The main item on the [loading data and EmptyControl in WebObjectListView](fa_wolv-empty-control.html). 

## Operations 

Main article about the [operations WebObjectListView](fa_wolv-operations.html). 

## Events 

Main article Pro [event WebObjectListView](fa_wolv-events.html). 

## Interaction WebObjectListView and edit form 

### Display on one page in list form and edit form 

Display on one page in list form and edit form are described in the corresponding [article](fa_editor-in-frame.html). 

### create a new object based on (prototypical) 

Features of the use of prototypization in `WebObjectListView` described in [article Prototypical for Flexberry ASP.NET](fa_web-data-object-prototyping.html). 

### Options open the edit form when creating\editing a feature 

Main article Pro [editing form](fa_wolv-edit-form.html). 

## Filters 

Main article Pro [filters WebObjectListView](fa_wolv-filters.html). 

## Search 

Main article Pro [search WebObjectListView](fa_wolv-search.html). 

## Print 

There are settings WOLV, which you can print a list or part list. Description of print settings presented in the paper [Print list](fa_print-wolv.html). 

## Customizing WebObjectListView 

The configuration tool for WebObjectListView described in [article WolvSettApplyer](fa_wolv-sett-applyer.html). 

### configuring display columns 

Main article Pro [setting of display columns WebObjectListView](fa_wolv-columns.html). 

### sort 

Sort the list described in [article Sorting for WebObjectListView](fa_list-sort.html). 

### the ability to change the width of columns 

The user can change the width of each column in the list if set the property `Operations.AllowColumnResizing = true`. Customized sizes of columns are saved for each user on the server, and restored when the page is loaded. 
User defined width of the column a higher priority than the value specified in [ViewColumnProvider.xml](fa_view-column-provider.html). 
If you change the width of table columns used jQuery plugin jquery.colresize](fa_jquery-colresize.html). 
The behavior of the cell contents overflow the width of the column can be configured with the option `OperationsWOLV.OverflowWordEllipsis`. If the option 
established in `false` (default), then transfer the contents of a cell on a new line. If the option is set to `true`, the cell content is truncated 
and in the end added the ellipsis. 

### Add buttons to the toolbar and row 

The main article about [adding buttons to the toolbar and row WebObjectListView](fa_wolv-add-button.html). 

### Using native controls to display data 

To use your own controls for display data intended [WebControlProvider](fa_web-control-provider.html). 

### Localization of the title attributes 

Operation WOLV `UseLocalizedCaptions` you want to use if you need to support multiple languages. When it is enabled, WOLV uses the `View.GetLocalizedPropertyCaption(propName)`. To specify localized titles, you need to: 
* Assembly objects to create a resource file `Captions.resx`; 
* ask him Acess modifier `"Public"`; 
* place an appropriate resource file in the namespace `"<Assembly namespace>.ObjectsResources"` (you can create a file in the folder `ObjectsResources`); 
* for each localizable to add header lines with keys of the form: `"<Assembly namespace>_<class>_<view name>_<attribute name>"`, and in the namespace of the Assembly you need to replace the dots with "_"; 
* in the same namespace for each culture to create a resource file.

If you want to set the title, regardless of the view, instead of the name of repose, they should write "DefCaption". __Example:__ `"IIS_КошкиСЛапами_Кошка_DefCaption_кличка"`. 

### data Format 

In order to display data in a specific format, you can write a separate control. 
Also, it is possible to set the attribute `System.ComponentModel.DataAnnotations.DisplayFormat`. 

Example: 

```csharp
/// <summary> 
/// Rehabilitation 
/// </summary> 
// *** Start programmer edit section *** (Surreality.Virginmobileringtone CustomAttributes) 
[System.ComponentModel.DataAnnotations.DisplayFormat(DataFormatString = "F2")]
// *** End programmer edit section *** (Surreality.Virginmobileringtone CustomAttributes) 
public virtual System.Nullable<System.Double> ВыраженностьОграниченийЗначение
{
    get
    {
        //... 
    }
    set
    {
        //... 
    }
}
``` 

#### Format date and time 

* The format of the date displayed in the cell can be configured using the DisplayFormatAttribute attribute: 

```csharp
[DisplayFormat(DataFormatString = "yyyy.MM")]
public virtual System.DateTime OnlyDate
``` 

For this to work, the date should appear in cell WOLV using the standard component (i.e. if the component is not overridden in WebControlProvider.xml). 

* If `WebControlProvider.xml` set a custom component for displaying dates (e.g., `FormattedDateTimeControl`), then the format should be set directly in the component (using the `WebControlProvider.TuneControlDelegateMethod`). 
Or you can create a new component, inherited from `FormattedDateTimeControl` by overriding the property `Format`, and specify `WebControlProvider.xml`. 
* If `WebControlProvider.xml` set a custom component for displaying dates, but want to for a specific property, attribute worked 
`DisplayFormatAttribute`, `WebControlProvider.xml` you must reset a custom component for this property: 

```xml
<customproperty class="DatePickerTest" property="OnlyDate">
    <control typename="" property="" codefile="" />
</customproperty>
``` 

#### Format date and time using the additional class 

* How to specify format for date and time without using the DataFormatString attribute. 
You must create a new class: 

```csharp 
/// <summary> 
/// Helper class to display the date in the format "HH:mm". 
/// </summary> 
public sealed class DateTimeFormattedHHmm : FormattedDateTimeControl
{
    /// <summary> 
    /// Method to set the date format. 
    /// </summary> 
    public DateTimeFormattedHHmm()
    {
        Format = "HH:mm";
    }
} 
``` 

It is also necessary to amend `WebControlProvider.xml`: 

```xml 
<customproperty class="TestDataTimeClassObject" property="poleDateTime">
    <control typename="WebFormsTestStand.Forms.Controls.WOLV.AppearanceTests.DateTimeFormattedHHmm, TestStand(ASP.NET Application)" property="Text" codefile="" />
</customproperty> 
``` 

## the constraint Editor 

Details about editor limitations set forth in [article Advanced editor restrictions Flexberry ASP.NET](fa_advanced-limit-editor.html). 

* `WOLV` holds the indication is restricted in the corner. If a constraint name is known, it will be displayed. If not - it will display a message that the list is limited. 
__Attention:__ if you are working in the editor constraints to keep the constraint, and then to apply it, it does not guarantee that was applied is the constraint that has been preserved, so the constraint name on the list not displayed. 

* For speed control, you can add [caching saved limitations](fa_wolv-adv-limit-caching.html). 

## data Service WOLV 

Main article Pro [service data WebObjectListView](fa_wolv-data-service.html). 

## Export to Excel and XML 

Exports from WebObjectListView in Excel is described in the corresponding [article](fa_wolv-export-excel.html). 
Export to Excel and XML are described in the corresponding [article](fa_export-excel-xml.html).

## the Behavior of the checkboxes to select items 

The main article about [the behavior of the check boxes to select items WebObjectListView](fa_wolv-check-boxes.html). 

## JS API 

Main article about the [JavaScript API for WebObjectListView](fa_js-api-wolv.html). 

## Tips 

1. ID WOLV better to fill in the **Latin characters** is due to the fact that ID WOLV often displayed in the address bar of the browser and the Cyrillic characters dekogida, occupying a lot of space in the page URL. `WOLV` nothing to do with WebBinder, so ID can be set as convenient. 
2. Always abrasivity setting `WOLV` [WolvSettApplyer](fa_wolv-sett-applyer.html): 

```csharp
var wsa = new WOLVSettApplyer();
wsa.SettingsApply(WebObjectListView1);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}