---
title: DatePicker
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP NET, JavaScript API, Web UI (Controls)
toc: true
permalink: en/fa_date-picker.html
lang: en
autotranslated: true
hash: 3afd019b4814f8479565d6fca81a24c0f7905e50f046ed1c683ae935a98553ea
---

**DataPicker** web - control for selecting a date and time.

## The most interesting properties

* `public DataPickerDisplayMode DisplayMode { get; set; }` - Show block select the date by clicking on the button next to the field, when focusing control, or both. Default `OnButtonClicked`.

* `public bool OnlyDate { get; set; }` - whether to Use the format without time (only date). Defaults to true

* `public string YearRange { get; set; }` - Defines the range of years that will appear in the drop-down list to select a year. This property can be set relative to the current year format **nn: nn**, or with respect to the displayed calendar year, in the format **c-nn: c nn**, or a specific date range: **nnnn:nnnn**. These options can be combined: **nnnn nn**. Except in this property, restrictions on the date range in the drop-down list of dates will affect the specified maximum and minimum date (properties `minDate` and `maxDate`). The default value of **c-10:c 10**.

* `public string BeforeShowFunctionName { get; set; }` - the Name of js function running before showing the control.

### Example of setting properties

Properties better to ask in the method `PostApplyToControls()`.

In the following example, the control sets the range of years available for input:

```csharp
protected override void PostApplyToControls()
{
    ctrlOnlyDate.YearRange = "2001:2002";
}
```

In this example, control set minute step:

```csharp
protected override void PostApplyToControls()
{
    ctrlStepMinute.OnlyDate = false;
    ctrlStepMinute.StepMinute = 10; 
}
```

## Setting the date format

To configure the output date format, you must set the property `DateFormat` (and `TimeFormat` if you want to set the time format). The chosen format will be used:

* When displaying information on the screen.
* When validating the data.
* When saving data to the database.

## Display settings in AGE

The easiest way to display a DatePicker for all [AjaxGroupEdit'Ah](fa_ajax-group-edit.html) application to create the method producing the needed actions on an instance of a web control (which is passed to it as parameters), and assign it a static property `InitSettings` class `DatePicker`. [Read more](fa_init-control-settings-delegate.html).

## The overriding picture of the advent calendar

To override the image that serves as the button for the appearance/hiding the calendar, you need to override a CSS class `.ui-datepicker-trigger`.

## [JS API](fa_javascript-api.html)

To manipulate the DatePicker on the client side, use JS `DatePicker API`, which is a jQuery plugin (`icsDatePicker`).

Example usage:

```javascript
$(document).ready(function () {
    $('#getValBtn').bind('click', function () {
        // get the value 
        var vl = $('#<%=ctrlДеньРождения.ClientID%>').icsDatePicker('val');
        alert(vl);
    });
    $('#setValBtn').bind('click', function () {
        // set the value 
        $('#<%=ctrlДеньРождения.ClientID%>').icsDatePicker('val', '01.01.2010');
    });
    $('#clearValBtn').bind('click', function () {
        // clear the value 
        $('#<%=ctrlДеньРождения.ClientID%>').icsDatePicker('val', '');
    });

});
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}