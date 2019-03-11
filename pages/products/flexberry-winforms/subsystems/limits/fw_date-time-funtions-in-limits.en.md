--- 
title: Functions for working with date when setting restrictions 
sidebar: flexberry-winforms_sidebar 
keywords: Constraints 
summary: List of functions for working with date when specifying constraints, the example applied to your code 
toc: true 
permalink: en/fw_date-time-funtions-in-limits.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: c2ea2ad9e47ab9772c67513a907ae77ef7e4c9ef76af695117e25152c7e19a28 
--- 

| Function name | return Type | argument Types | note 
|--|--|--|--| 
| TODAY | date/Time | no parameters | Returns the current date along with time 
| Telcodata | Date/Time | :Date/Time 
| Tolkoviy | Date/Time | :Date/Time 
| Day of the week | :Number | date/Time | 1 = Monday, 2 = Tuesday, etc. 
| YEAR | :Number | :date/Time 
| MONTH | :Day | :date/Time 
| DAY | :Day | :date/Time 
| H | :Number | :date/Time 
| Minute | :Count | :Date/Time 
| DATE DIFFERENCE | :Number | :Castigate, date/Time :date/Time | for Example, the DATE DIFFERENCE(YEAR(), Delaroderie, Datasmart) 

Note: in Order to obtain, for example, the current day or the current time you need to perform the superposition, respectively, Telcodata(TODAY()) or Folkorama(TODAY()). 

## Example: to bring those who have birthday today 

```csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            new VariableDef(langDef.DateTimeType, "Dataromance")),
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            langDef.GetFunction("TODAY")));
``` 

## Example: to bring those who died at 01:00:00 

```csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            new VariableDef(langDef.DateTimeType, "Datasmart")),
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            DateTime.Parse("01:00:00")));
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}