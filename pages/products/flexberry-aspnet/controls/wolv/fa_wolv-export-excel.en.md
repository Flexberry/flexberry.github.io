--- 
title: Export data to Excel from WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Reports, database 
toc: true 
permalink: en/fa_wolv-export-excel.html 
lang: en 
autotranslated: true 
hash: 16df50707017d4ce65041e114c0b25eaba51f97163fcc01d8cac34096f4013a9 
--- 

## download to Excel (instructions to the programmer) 

[WebObjectListView](fa_web-object-list-view.html) allows to download data in Excel format. 

This feature is included with the operation: 

```csharp
/// <summary> 
/// Called the first in the Page_Load. 
/// </summary> 
protected override void Preload()
{
    WebObjectListView1.Operations.ExportToExcel = true;
}
``` 

### the View to export 

Ask a [view](fd_view-definition.html), the default for export is possible from code by setting the `ExportView` [WOLV](fa_web-object-list-view.html): 

```csharp
WebObjectListView1.ExportView = Клиент.View.КлиентExport;
``` 

### Unloading of datalow 

Unloading of datalow occurs if they are present in a [view](fd_view-definition.html). The user can configure the settings metalowego column as well as other properties of the object. 

There are 2 settings on the [web edit](fa_editform.html) to control the unloading of detailov. 

1. Detailov fields in separate columns. 
2. Detaily in a single row. 

* If 1 of the settings is not set, then detaily unloaded in 1 cell in the line: 

| Aggregator | Detail| 
|------------|--------| 
| Field of the aggregator | Detail: 1st Field детейла; Detail: Field детейла; Detail 2nd: Field 3rd детейла;| 

* If there is a setting `Поля of datalow in a separate столбцы`, detaily unloaded in 1 cell in a column: 

| Aggregator | Detail| 
|------------|---------| 
| Aggregator field | Field 1st of detail| 
||2nd field of detail| 
||3rd field of detail| 

* If you set both settings at once, each detail will be displayed in a new line, and a field aggregator with different strings will be merged into 1 cell. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/two-options.png) 

* To set only the setting `Детейлы in a separate строки` until bessmyslenno, conducted a revision this setting. 

__Note__: if you have an idea of the field of detail not specified title as the title field when uploading, you will use the field name. 

### data Service 

The default for the Excel spreadsheets used the data service `DataServiceProvider.DataService`. If you want to ask another, separate service data to unload in Excel, this can be done in `Web.config` by registering a service under the name `ExcelExportDataService` in `Unity`. The example configuration below for the Excel spreadsheets used the data service `IcsharpSoft.STORMNET.Business.DRDataService`: 

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
  <container>
    <register name="ExcelExportDataService" type="IcsharpSoft.STORMNET.Business.IDataService, IcsharpSoft.STORMNET.Business" mapTo="IcsharpSoft.STORMNET.Business.DRDataService, IcsharpSoft.STORMNET.Business.DRDataService">
      <constructor />
    </register>
  </container>
</unity>
``` 

#### Ogranichenie on the maximum number of objects 

To configure the maximum number of exported objects should be vopspolzovatsya configuration parameter `WOLVExportLimit`: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="WOLVExportLimit" value="10000" />
  </appSettings>
</configuration>
``` 

Negative values of the parameter, and 0 disables the limit on the maximum number of exported objects. 

### adding a table for storing settings 

To add a database table to store user settings, you must have a data structure that is used [UserSettingsService](fa_user-settings-service.html). The table is generated automatically. 

## download to Excel (user manual) 

To unload data from a list, click on `Выгрузить in Excel` ![](/images/pages/products/flexberry-aspnet/controls/wolv/export-button.png) on the toolbar, in the opened window configure the format of uploaded data and confirm the unload by clicking on the button `OK`. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/export-form.png) 

On the form you can: 

* Specify the visibility of the fields by selecting or deselecting the check box at the left of each row. 
* Specify the direction of the sort (no sorting, ascending or descending) for each column of data. 
* To specify the sort priority (if one of the sorts) 
* Specify the name of the data column 
* To change the order of columns of data 
* The buttons `вверх` and `вниз` the right side of each row 
* Or by dragging rows 

By default, unloaded all data contained in the list subject to the imposed filters and sorting. 

If you highlight any record with ticks, it will be loaded only the selected records. 

Setting of discharge can be saved for later use. The settings are individual users and cannot be shared with other users. 

To create a configuration, you must select the menu item `Создать set of fields for export...`. In the opened window it is necessary to produce the necessary configuration, specify the name of the configuration and click `Сохранить`. Saved settings are available from the list menu. When you select a saved settings are automatically prompted to save the Excel file. 

To edit or delete settings can be set by selecting the appropriate menu item. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/export-menu.png) 

Files are uploaded in XML, which is interpreted `Microsoft Excel`, the file extension is exposed as *.xls (the default extension of Excel documents). When you open 
the resulting file in Excel you will see the following warning: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/export-warning.png) 

{% include note.html content="If authentication is turned off or not working then the possibility of creating settings will be disabled." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}