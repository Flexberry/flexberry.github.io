--- 
title: FuncToChar 
sidebar: flexberry-orm_sidebar 
keywords: DateTime, Flexberry ORM Limitations 
summary: Parameters and example FuncToChar 
toc: true 
permalink: en/fo_func-to-char.html 
lang: en 
autotranslated: true 
hash: 221e4ef8cf631b42bcdd043b9b9dabfd5f8262b07480db0d4b7274d0a78f5801 
--- 

`FuncToChar` - function [ExternalLangDef](fo_external-lang-def.html), which serves to specify a transformation expression to a string. This is necessary for correct comparison of values of expressions with string constants. Currently only implemented for [MSSQLDataService](fo_mssql-data-service.html) and [OracleDataService](fo_oracle-data-service.html). 

## Use 

The function can be used in two ways: 

* With two arguments where the first is any expression that returns a value, and the second is a number indicating the length of the resulting string. 
* With three arguments, where the first expression has type `DATETIME`, the second is the length of the string, and the third the number format to display the date. To set the last parameter you can use list `ExternalLangDef.DateFormats`. It defines the following values: 

* `German` (DD.MM.YY) 
* `GermanWithCentury` (DD.MM.YYYY) 
* `Month` (DD Mon YY abbreviated month name) 
* `MonthWithCentury` (DD Mon YYYY) 
* `Time` (hh:mi:ss - time) 

In addition, you can use other formats by specifying their number. Full list is [here](http://msdn.microsoft.com/ru-ru/library/ms187928.aspx) (this is possible only for [MSSQLDataService](fo_mssql-data-service.html). 

### Example usage 

Getting the expression that causes the date to string and compare with the function LIKE 

```csharp
var stringDate = _langdef.GetFunction(
					_langdef.funcToChar, 
					new VariableDef(_langdef.DateTimeType, propertyName),
					10, // The length of the string containing the date in format 'DD.MM.YYYY' 
					ExternalLangDef.DateFormats.GermanWithCentury);

return _langdef.GetFunction(
			_langdef.funcLike,
			stringDate,
			string.Format("%{0}%", searchValue.Trim().ToLower()));
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}