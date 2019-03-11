--- 
title: Export to Excel and XML 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_export-excel-xml.html 
lang: en 
autotranslated: true 
hash: 0b3f8413dab96b5c21f3f8aeadea219b687f247f3dd66a695d6a30b8c21897b6 
--- 

The whole logic of the export to Excel and XML, is a Web.Tools. 

## Use controls 

At the moment unloading is used in [WOLV](fa_web-object-list-view.html) 

## change the value and type of cells in Excel 

In order to change the value and type of the cell when discharging you want to override a static field, for example: 

```csharp
Web.Tools.WOLVFeatures.ExcelXMLExport.InitExcelCell = AllForms.InitExcelCell;
``` 

And implement the initialization properties like: 

```csharp
public static void InitExcelCell(ExcelCellEventArgs args)
{
    if (args.PropValue is bool)
    {
        args.CellStyleId = DataType.String;
        args.StringedPropValue = ((bool)args.PropValue) ? Yes : "No";
        args.Handled = true;
    }
}
``` 

## specify extraction format for dates 

In order to set the date format when uploading, you need to override a static field: 

```csharp
ICSSoft.STORMNET.Web.Tools.WOLVFeatures.ExcelXMLExport.InitExcelCellFormatDelegate
``` 

and process the parameter as needed. Parameter passed in the following: 

```csharp
public class ExcelCellFormatEventArgs : EventArgs
{
	/// <summary> 
	/// The object type. 
	/// </summary> 
	public readonly Type ObjectType;

	/// <summary> 
	/// Type properties. 
	/// </summary> 
	public readonly Type PropertyType;

	/// <summary> 
	/// The name of the property. 
	/// </summary> 
	public readonly string PropertyName;

	/// <summary> 
	/// The resulting custom format. 
	/// </summary> 
	public string Format;

	/// <summary> 
	/// Flag to indicate that the value has been processed. 
	/// By default, <c>false</c>. 
	/// </summary> 
	public bool Handled;
}
``` 

For example, ask for the property "PoleDateTime" class "LimitEditorMainAgregator" the following date format: "dd.MM.yyyy hh:mm:ss". 

```csharp
ICSSoft.STORMNET.Web.Tools.WOLVFeatures.ExcelXMLExport.InitExcelFormat = InitExcelFormat;

/// <summary> 
/// Setting the format of dates when exporting to Excel. 
/// </summary> 
/// <param name="args">the parameters of the date format.</param> 
private static void InitExcelFormat(ExcelCellFormatEventArgs args)
{
	if (args.ObjectType == typeof(LimitEditorMainAgregator)
		&& args.PropertyName == Information.ExtractPropertyPath<LimitEditorMainAgregator>(x => x.PoleDateTime))
	{
		args.Handled = true;
		args.Format = "dd.MM.yyyy hh:mm:ss";
	}
}
``` 

{% include note.html content="When setting the date format you need to Excel recognize the transmitted format (the following formats has been correctly processed: `dd.MM.yyyy hh:mm:ss`, `dd.MM.yyyy hh:mm`, `dd.MM.yyyy`, `hh:mm:ss`, `hh:mm`, `dd.MMM.yyyy`, `MM.dd.yyyy`, `yyyy.mm.dd`)." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}