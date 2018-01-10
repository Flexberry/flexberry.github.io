---
title: Экспорт в Excel и XML
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_export-excel-xml.html
lang: ru
---

Вся логика экспорта в Excel и XML находится в Web.Tools.

## Использование в контролах

В данный момент выгрузка используется в [WOLV](fa_web-object-list-view.html)

## Изменение значения и типа ячеек в Excel

Для того, чтобы сменить значение и тип ячейки при выгрузке нужно переопределить статическое поле, например:

```csharp
Web.Tools.WOLVFeatures.ExcelXMLExport.InitExcelCell = AllForms.InitExcelCell;
```

И реализовать инициализацию свойств, например:

```csharp
public static void InitExcelCell(ExcelCellEventArgs args)
{
    if (args.PropValue is bool)
    {
        args.CellStyleId = DataType.String;
        args.StringedPropValue = ((bool)args.PropValue) ? "Да" : "Нет";
        args.Handled = true;
    }
}
```

## Задание формата выгрузки для дат

Для того, чтобы задать формат даты при выгрузке, нужно переопределить статическое поле:

```csharp
ICSSoft.STORMNET.Web.Tools.WOLVFeatures.ExcelXMLExport.InitExcelCellFormatDelegate
```

и обработать параметр необходимым образом. Передаваемый параметр следующий:

```csharp
public class ExcelCellFormatEventArgs : EventArgs
{
	/// <summary>
	/// Тип объекта.
	/// </summary>
	public readonly Type ObjectType;

	/// <summary>
	/// Тип свойства.
	/// </summary>
	public readonly Type PropertyType;

	/// <summary>
	/// Имя свойства.
	/// </summary>
	public readonly string PropertyName;

	/// <summary>
	/// Результирующий пользовательский формат.
	/// </summary>
	public string Format;

	/// <summary>
	/// Флаг, обозначающий, что значение было обработано.
	/// По умолчанию <c>false</c>.
	/// </summary>
	public bool Handled;
}
```

Например, зададим для свойства "PoleDateTime" класса "LimitEditorMainAgregator" следующий формат даты: "dd.MM.yyyy hh:mm:ss".

```csharp
ICSSoft.STORMNET.Web.Tools.WOLVFeatures.ExcelXMLExport.InitExcelFormat = InitExcelFormat;

/// <summary>
/// Настройка формата дат при экспорте в Excel.
/// </summary>
/// <param name="args">Параметры формата даты.</param>
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

{% include note.html content="При установке формата дат необходимо, чтобы Excel понимал передаваемый формат (следующие форматы были корректно обработаны: `dd.MM.yyyy hh:mm:ss`, `dd.MM.yyyy hh:mm`, `dd.MM.yyyy`, `hh:mm:ss`, `hh:mm`, `dd.MMM.yyyy`, `MM.dd.yyyy`, `yyyy.mm.dd`)." %}
