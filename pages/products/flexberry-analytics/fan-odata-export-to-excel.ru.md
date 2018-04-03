
---
title: Экспорт данных из ODataService в Excel
keywords: ODataService, Excel, export
toc: true
permalink: ru/fan-odata-export-to-excel.html
lang: ru
summary: Экспорт данных из ODataService в Excel.
---

## Экспорт данных из ODataService в Excel

### Параметры строки запроса к ODataService
В запросе на получение документа Excel в текущей реализации ODataService должны присутствовать следующие параметры для установки свойств в ExportParams:
* colsOrder - используется для установки порядка колонок в свойствах PropertiesOrder и HeaderCaptions
* detSeparateCols -  используется для установки свойства DetailsInSeparateColumns
* detSeparateRows - используется для установки свойства DetailsInSeparateRows

Параметр exportExcel используется для перехода в режим экспорта в Excel.
Пример строки запроса к ODataService:
```
http://localhost/odata/Странаs?exportExcel=true&colsOrder=Название/НазваниеCAPTION&detSeparateCols=false&detSeparateRows=false&$filter=contains(Название,'1')
```
В строке запроса могут присутствовать любые другие параметры, которые необходимы в реализации интерфейсов экспорта данных в Excel - `IODataExportService` и `ISpreadsheetCustomizer`.

### Настройка файла конфигурации
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

### Пример реализации интерфейса IODataExportService
```csharp
    /// <summary>
    /// Реализация интерфейса для экспорта данных из ODataService.
    /// </summary>
    public class ExportExcel : IODataExportService
    {
        /// <summary>
        /// Создаёт файл экспорта  данных из ORM.
        /// </summary>
        /// <param name="dataService">Сервис данных ORM.</param>
        /// <param name="parameters">Параметры экпорта.</param>
        /// <param name="objs">Объекты для экспорта.</param>
        /// <param name="queryParams">Параметры в запросе HTTP.</param>
        /// <returns>Возвращает файл экспорта в виде MemoryStream.</returns>
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
### Пример реализации интерфейса ISpreadsheetCustomizer
```csharp
    /// <summary>
    /// Реализация интерфейса для обработки документа Excel.
    /// </summary>
    public class SpreadsheetCustomizer : ISpreadsheetCustomizer
    {
        /// <summary>
        /// Обработка документа Excel.
        /// </summary>
        /// <param name="document">Документ Excel.</param>
        /// <param name="parameters">Параметры экпорта.</param>
        /// <param name="queryParams">Параметры в запросе HTTP.</param>
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
        /// Создание объекта ячейки Excel с указанным значение и форматом.
        /// </summary>
        /// <param name="value">Значение ячейки.</param>
        /// <param name="excelDataType">Формат ячейки.</param>
        /// <returns>Объект Cell.</returns>
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
