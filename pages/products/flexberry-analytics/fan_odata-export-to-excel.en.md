--- 
title: Export data to Excel from the ODataService 
sidebar: flexberry-analytics_sidebar 
keywords: ODataService, Excel, export 
toc: true 
permalink: en/fan_odata-export-to-excel.html 
lang: en 
autotranslated: true 
hash: 0cfcb94eb2d160fa538859106a58ac4a778ad476ce1d4d0c75ddce91d3778a30 
summary: Parameters, configuration, configuration file, and examples of usage 
--- 

## the query string Parameters to the ODataService 

Enabling the export to Excel happens when the query string is set `exportExcel=true`. 

In request to get the Excel document should contain the following parameters to set properties in `ExportParams`: 

* `colsOrder` - used to set the order of columns in the properties PropertiesOrder and HeaderCaptions 
* `detSeparateCols` - used to set properties DetailsInSeparateColumns 
* `detSeparateRows` - used to set properties DetailsInSeparateRows 

Example query string to the ODataService: 

```http
http://localhost/odata/Strana?exportExcel=true&colsOrder=Nazvanie/НазваниеCAPTION&detSeparatecols=false&detSeparateRows=false&$filter=contains(Nazvanie,'1') 
``` 

In the query string can be of any other settings that are needed in the implementation of interfaces to export data to Excel - `IODataExportService` and `ISpreadsheetCustomizer`. [Test example implementation of the data interfejsow](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/tree/develop/Tests/NewPlatform.Flexberry.ORM.ODataService.Tests/CRUD/Read/Excel). 

## setup configuration file 

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
<container>
    <register type="NewPlatform.Flexberry.IODataExportService, ICSSoft.STORMNET.Business" mapTo="NewPlatform.Flexberry.ORM.ODataService.Tests.CRUD.Read.Excel.ExportExcel,NewPlatform.Flexberry.ORM.ODataService.Tests">
    <lifetime type="singleton" />
    </register>
</container>
<alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
</unity>
``` 

### setting of the configuration file without the use of additional parameters 

Configure the configuration file for basic functionality without the use of additional HTTP request parameters and without the need of implementing interfaces to export data to Excel - `IODataExportService` and `ISpreadsheetCustomizer`. 

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
<container>
    <register name="Export" type="NewPlatform.Flexberry.IExportService, ICSSoft.STORMNET.Business" mapTo="NewPlatform.Flexberry.Reports.ExportToExcel.ExportExcelODataService, NewPlatform.Flexberry.Reports.ExportToExcel">
        <lifetime type="singleton" />
    </register>
</container>
<alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
</unity>
``` 

## an Example implementation of the interface IODataExportService 

```csharp
/// <summary> 
/// Implementation of the interface to export data from the ODataService. 
/// </summary> 
public class ExportExcel : IODataExportService
{
    /// <summary> 
    /// Creates an export file of data from ORM. 
    /// </summary> 
    /// <param name="dataService">data Service ORM.</param> 
    /// <param name="parameters">the parameters of the export.</param> 
    /// <param name="objs">the Objects to export.</param> 
    /// <param name="queryParams">Parameters in the HTTP request.</param> 
    /// <returns>Returns the export file in a MemoryStream.</returns> 
    public MemoryStream CreateExportStream(IDataService dataService, IExportParams parameters, DataObject[] objs, NameValueCollection queryParams)
    {
        var exportService = new DataExportLimitedService(parameters, dataService);
        var dataExport = exportService.GetDataExport(objs);
        var excelService = new ExcelExportService(parameters, queryParams);
        excelService.SpreadsheetCustomizer = new SpreadsheetCustomizer();
        MemoryStream result = excelService.GetExportStream(dataExport);
        return result;
    }
}
``` 

## an Example implementation of the interface ISpreadsheetCustomizer 

```csharp
/// <summary> 
/// Implementation of interface for handling Excel document. 
/// </summary> 
public class SpreadsheetCustomizer : ISpreadsheetCustomizer
{
    /// <summary> 
    /// Excel document. 
    /// </summary> 
    /// <param name="document">Excel Document.</param> 
    /// <param name="parameters">the parameters of the export.</param> 
    /// <param name="queryParams">Parameters in the HTTP request.</param> 
    public void Process(ref SpreadsheetDocument document, IExportParams parameters = null, NameValueCollection queryParams = null)
    {
        var worksheetPart = document.WorkbookPart.GetPartsOfType<WorksheetPart>().First();
        var sheetData = worksheetPart.Worksheet.GetFirstChild<SheetData>();
        var row = new Row();
        string query = null;
        foreach (var key in queryParams.AllKeys)
        {
            if (query != null)
            {
                query += "&";
            }

            query += $"{key}={queryParams[key]}";
        }

        var excelDataType = new ExcelTypeDefinition(CellValues.String, CustomStylesheet.StyleIndexTextAllBordersWrapAlignment);
        var cell = CreateCell(query, excelDataType);
        row.AppendChild(cell);
        sheetData.AppendChild(row);
        return;
    }

    /// <summary> 
    /// Create object of Excel cell with the specified value and format. 
    /// </summary> 
    /// <param name="value">the value of the cell.</param> 
    /// <param name="excelDataType">Format cells.</param> 
    /// <returns>the Cell Object.</returns> 
    private static Cell CreateCell(string value, ExcelTypeDefinition excelDataType)
    {
        var headerCell = new Cell { CellValue = new CellValue(value), StyleIndex = excelDataType.StyleIndex };

        if (excelDataType.CellValues != CellValues.Date)
        {
            headerCell.DataType = new EnumValue<CellValues>(excelDataType.CellValues);
        }

        return headerCell;
    }
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}