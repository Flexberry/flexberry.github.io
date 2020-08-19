---
title: FileControl for Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP NET, JavaScript API, Web UI (Controls)
toc: true
permalink: en/fa_file-control-web.html
lang: en
autotranslated: true
hash: 5461f27526a37e451066fc7d3eb696978dee715f345dbbb888f8d3880f77593f
---

This control for working with data type `ICSSoft.STORMNET.FileType.File` in the web application. Files are saved in the database as the serialized
line, so this control is designed to work with small files.

In the browser the user is able to select a file from your file system and save it in the database in the corresponding field of the data object to which the set binding.

The control looks as follows:
![](/images/pages/products/flexberry-aspnet/aspnet/file-control.png)

First, you specify the name of the current saved or selected file.Next come the buttons:

* Choose file - open dialog for file selection
* Download file - download the current saved file from database
* Reset file to reset saved or just the selected file.

PstrfСкачать файл` button is dimmed if the file is missing from the database (i.e. the corresponding field of the data object is null).

{% include important.html content="When you select new file or click on `Сбросить файл` button `Скачать файл` to download the old file (from DB) until then, until you save the object data (the edit form)." %}

{% include note.html content="click on any of the three buttons does not produce any changes in the database, the changes will happen only when you save the editing form." %}

Button `Сбросить файл` resets the current selection, then the component will display "(no file)". When you save the form edit the old file in the database will be deleted, and in its place is written the value `null`.

The component is supported in [AjaxGroupEdit](fa_ajax-group-edit.html).

## The limit on file upload size

The default limit is taken from options [maxRequestLength](https://msdn.microsoft.com/en-us/library/e1f13641(v=vs.100).aspx) in the application configuration file (web.config). The value of this attribute `по default - 4 МБ` if it is not present in the configuration file.
This limitation can be overridden in the property `FileControl.MaxValueSize`. The new value cannot be less than 1 and more `maxRequestLength`, otherwise an exception will occur.

If the user selects a file that exceeds the specified limit, he will receive a message about exceeding the allowable file size, then the selected file will be reset:

![](/images/pages/products/flexberry-aspnet/aspnet/file-control-max-file-size.png).

{% include note.html content="a limit on the request size can be set by the IIS features, which may differ from the attribute `maxRequestLength`. This situation is not controlled component `FileControl`." %}

## Connection FileControl (Web) to the application

In order to `FileControl` appeared on the edit form, need to add it to `aspx-разметку`.

```html
<ac:FileControl ID="ctrlFile" runat="server"/>
```

Control is in the Assembly `ICSSoft.STORMNET.Web.AjaxControls.dll`.

To add to the controls that are generated dynamically (e.g., [AjaxGroupEdit](fa_ajax-group-edit.html)) you need to register in [WebControlProvider](fa_web-control-provider.html) the following lines:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="WebControlProvider.xsd">
<!-- ... -->
  <propertytype name="File">
    <control typename="ICSSoft.STORMNET.Web.AjaxControls.FileControlView, ICSSoft.STORMNET.Web.AjaxControls" property="Value" codefile=""/>
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.FileControl, ICSSoft.STORMNET.Web.AjaxControls" property="Value" codefile=""/>
  </propertytype>
<!-- ... -->
</root>
```

You also need to `web.config` to add the following lines:

```xml
<configuration>
<!-- ... -->
<system.web>
    <!-- ... -->
    <httpHandlers>
      <!-- ... -->
      <add verb=POST path="FileControlService.axd" type="ICSSoft.STORMNET.Web.HttpHandlers.FileControlHandler" validate="false" />
      <!-- ... -->
    </httpHandlers>
    <!-- ... -->
</system.web>
<system.webServer>
    <!-- ... -->
    <handlers>
      <!-- ... -->
      <add verb=POST name="FileControlService" path="FileControlService.axd" type="ICSSoft.STORMNET.Web.HttpHandlers.FileControlHandler" resourceType="Unspecified" preCondition="integratedMode" />
      <!-- ... -->
    </handlers>
    <!-- ... -->
  </system.webServer>
</configuration>
```

For the generated "clean" projects these settings are created automatically.

## FileControl in WOLV

The control can be placed in the column cells WOLV in ReadOnly mode (only download file).

You need to specify what to display the type of File you need to use FileControl:

```xml
<customproperty class="BugReport" property="Attachment">
    <control typename="ICSSoft.STORMNET.Web.AjaxControls.FileControl, ICSSoft.STORMNET.Web.AjaxControls" property="Value" codefile=""/>
  </customproperty>
```

In `WOLVSettApplyer.cs` you must subscribe to the event `WebControlProvider.TuneControlDelegateMethod` to configure the property `FileControl.ReadOnly = true` for all controls FileControl, located in WOLV:

```csharp
wolv.WebControlProvider.TuneControlDelegateMethod += TuneControlDelegateMethod;

/// <summary> 
/// Ponastroila controls in WOLV. 
/// </summary> 
/// <param name="control">the control Created in WOLV.</param> 
/// <param name="createdControlData">Information about the created control.</param> 
private void TuneControlDelegateMethod(Control control, CreatedControlData createdControlData)
{
    // FileControl to WOLV should be in the "read-Only". 
    var fileControl = control as FileControl;
    if (fileControl != null)
    {
        fileControl.ReadOnly = true;
        fileControl.EmptyFileNameText = string.Empty;
    }
}
```

## Using FileControl on the edit forms

Using FileControl on the edit forms described in the corresponding [article](fa_file-control-description.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}