--- 
title: Restrictions on date part 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Constraints, ExternalLangDef 
summary: the Rules of formation restrictions on the year, month, day, hour, minute, particular date, time and day of the week, the parameters used 
toc: true 
permalink: en/fo_restriction-datetime.html 
lang: en 
autotranslated: true 
hash: 34a032d8c0032497c553526001ceeee4118ea31b849350b0a91ce463a66defb4 
--- 

## Restrictions on date part 

Restrictions on date part is used when you want to get data for part of the date. 

The function to retrieve parts of dates are available in [ExternalLangDef](fo_external-lang-def.html). 
In the examples below we will use the following code: 

```csharp
var langdef = ExternalLangDef.LanguageDef;
var order = LoadingCustomizationStruct.GetSimpleStruct(typeof (Заказ), Заказ.Views.ЗаказL);
``` 

### Limit for the year (funcYearPart) 

**Find orders placed in 2014.** 

The SQL statement looks like the following: 

```sql
SELECT * FROM [dbo].[Заказ] WHERE YEAR([ДатаВыдачи]) = 2014
``` 

[ExternalLangDef](fo_external-lang-def.html) 

```csharp
order.LimitFunction = langdef.GetFunction(langdef.funcEQ, 
                                          langdef.GetFunction(langdef.funcYearPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "2014");
``` 

### the limit for the month (funcMonthPart) 

**Find orders placed in may.** 

The SQL statement looks like the following: 

```sql
SELECT * FROM [dbo].[Заказ] WHERE Month([ДатаВыдачи]) = 05
``` 

[ExternalLangDef](fo_external-lang-def.html) 

```csharp
order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcMonthPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "05");
``` 

### the Restriction on day (funcDayPart) 

**Find orders placed on the 13th.** 

The SQL statement looks like the following: 

```sql
SELECT * FROM [dbo].[Заказ] WHERE Day([ДатаВыдачи]) = 13
``` 

[ExternalLangDef](fo_external-lang-def.html) 

```csharp
order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcDayPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "13");
``` 

### a limitation on the hours (funcHHPart) 

**Find orders placed at 10 am.** 

The SQL statement looks like the following: 

```sql
SELECT * FROM [dbo].[Заказ] WHERE DatePart(hh, [ДатаВыдачи]) = 10
``` 

[ExternalLangDef](fo_external-lang-def.html) 

```csharp
order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcHHPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "10");
``` 

### Restriction on minutes (funcMIPart) 

**Find orders placed in 20 minutes.** 

The SQL statement looks like the following: 

```sql
SELECT * FROM [dbo].[Заказ] WHERE DatePart(MINUTE, [ДатаВыдачи]) = 20
``` 

[ExternalLangDef](fo_external-lang-def.html) 

```csharp
order.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                          langdef.GetFunction(langdef.funcMIPart, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))),
                                          "20");
``` 

If you want the exact time, the constraint will look like this: 

```csharp
order.LimitFunction = langdef.GetFunction(
						  langdef.funcAND,
						  langdef.GetFunction(
								langdef.funcEQ, 
								langdef.GetFunction(
									langdef.funcHHPart, 
									new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))), 
								10),
						  langdef.GetFunction(
								langdef.funcEQ, 
								langdef.GetFunction(
									langdef.funcMIPart, 
									new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Заказ>(x => x.ДатаВыдачи))), 
								20));
``` 

### a date Restriction (funcOnlyDate) 

**To bring those who have a birthday today.** 

```csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            new VariableDef(langDef.DateTimeType, Information.ExtractPropertyPath<Человек>(x => x.ДатаРождения))),
                                        langDef.GetFunction(langDef.funcOnlyDate,
                                                            langDef.GetFunction("TODAY")));
``` 

### time Limit (funcOnlyDate) 

**Bring those born at 01:00:00.** 

```csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcEQ,
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            new VariableDef(langDef.DateTimeType, Information.ExtractPropertyPath<Человек>(x => x.ДатаРождения))),
                                        langDef.GetFunction(langDef.funcOnlyTime,
                                                            DateTime.Parse("01:00:00")));
``` 

### limit on the day of the week (funcDayOfWeek) 

Function to return day of week number. (1 = Monday, ..., 7 = Sunday) 

**Display only those numbers which fell on Monday.** 

```csharp
// Create a restriction object. 
 lcs.LimitFunction = 
           // The constraint that the function returns a objects. 
           ldef.GetFunction(
                    // Where parameter value 1 (=) to the value 2. 
                    ldef.funcEQ,
                    // Option 1: get the date and convert it to a number. 
                    ldef.GetFunction(
                        ldef.funcDayOfWeek,
                        new VariableDef(ldef.DateTimeType,
                            Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Option 2. 
                    1);
``` 


### limit on the day of the week (funcDayOfWeekZeroBased) 

Function to return day of week number. (0 = Sunday, 1 = Monday, ...) 

**Find all orders that were made on Sunday.** 

```csharp
// Create a restriction object. 
LimitFunction =
        // The constraint that the function returns a objects. 
        ldef.GetFunction(
                   // Where parameter value 1 (=) to the value 2. 
                   ldef.funcEQ,
                   // Option 1: Limit that from a "date" field, returns the day of the week. 
                   ldef.GetFunction(ldef.funcDayOfWeekZeroBased, new VariableDef(ldef.DateTimeType, Date)),
                   // Option 2: Returning the day of the week number should be 0 (Sunday). 
                   0)
``` 


<!-- ### a Limit on the difference of dates (funcDATEDIFF) 

**Example in development.** 

### a limit on the number of days in the month (funcDaysInMonth) 

**Example in development.** !--> 

### Full analogue of SQL functions dateadd (funcDateAdd) 

**Find all orders which have been made for all time, except for those which have been made over the last year.** 

```csharp
// Create a restriction object. 
LimitFunction  =
         // The constraint that the function returns a objects. 
         ldef.GetFunction(
                // Which parameter values 1 (<) the value of the parameter 2. 
                ldef.funcL,
                // Parameter 1: the constraint that the from date field, date return the year value, which is increased by 1. 
                ldef.GetFunction(
                    ldef.funcDateAdd, // Full analog of the SQL dateadd function. 
                    ldef.GetFunction(ldef.paramYearDIFF),
                    1,
                    new VariableDef(ldef.DateTimeType, Date)),
                // Option 2: Limit that from today's date, return the date only(without time). 
                ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
``` 

### Options for working with date 

##### paramTODAY - option to get today's date 

**Find all orders which have been made over the past months ( ongoing).** 

```csharp
// Create the restriction object ("date"<=paramTODAY). 
LimitFunction =
      // The constraint that the function returns a objects. 
      ldef.GetFunction(   
                   // Which parameter values 1 (<=) to the value 2. 
                   ldef.funcLEQ, 
                   // Parameter 1: the constraint that the from date field and return only the values of the month. 
                   ldef.GetFunction(ldef.funcMonthPart, new VariableDef(ldef.DateTimeType, Date)),
                   // Option 2: Limit that from today's date, return only values for the month. 
                   ldef.GetFunction(ldef.funcMonthPart, ldef.GetFunction(ldef.paramTODAY)));
``` 

##### paramYearDIFF parameter to get the value of year from the date 

**Find all orders which have been made for all time, except for those which have been made over the past year.** 

```csharp
// Create a restriction object. 
lcs.LimitFunction = 
              // The constraint that the function returns a objects. 
              ldef.GetFunction(
                    // Which parameter values 1 (<) the value of the parameter 2. 
                    ldef.funcL,
                    // Option 1: Limit that from a "date" field returns the year of date, which is increased by 1. 
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramYearDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Option 2: Limit that from today's date, return the date only(without time). 
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
``` 

##### paramMonthDIFF parameter to retrieve the value of month from the date 

**Find all orders which have been made for all time, except for those which have been made over the last month.** 

```csharp
// Create a restriction object. 
lcs.LimitFunction = 
           // The constraint that the function returns a objects. 
           ldef.GetFunction(
                    // Which parameter values 1 (<) the value of the parameter 2. 
                    ldef.funcL,
                    // Option 1: Limit that from the field PoleDateTime returns the month value of the date, which is increased by 1. 
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramMonthDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Option 2: Limit that from today's date, return the date only(without time). 
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
``` 

##### paramWeekDIFF parameter to retrieve the value of week from date 

**Find all orders which have been made for all time, except for those which have been made over the last week.** 

```csharp
// Create a restriction object. 
lcs.LimitFunction = 
             // The constraint that the function returns a objects. 
             ldef.GetFunction(
                    // Which parameter values 1 (<) the value of the parameter 2. 
                    ldef.funcL,
                    // Option 1: Limit that from the field PoleDateTime returns the week date that is increased by 1. 
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramWeekDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Option 2: Limit that from today's date, return the date only(without time). 
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
``` 

##### paramQuarterDIFF parameter to retrieve the value of quarters from the date 

**Find all orders which have been made for all time, except for those which have been made over the last quarter.** 

```csharp
// Create a restriction object. 
lcs.LimitFunction = 
             // The constraint that the function returns a objects. 
             ldef.GetFunction(
                    // Which parameter values 1 (<) the value of the parameter 2. 
                    ldef.funcL,
                    // Option 1: Limit that from the field PoleDateTime returns quarter to date, which is increased by 1. 
                    ldef.GetFunction(
                        ldef.funcDateAdd,
                        ldef.GetFunction(ldef.paramQuarterDIFF),
                        1,
                        new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                    // Option 2: Limit that from today's date, return the date only(without time). 
                    ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));
``` 

##### paramDayDIFF parameter to get the value of days from the date 

**Find all orders which have been made for all time, except for those which have been made over the past three days.** 

```csharp
// Create a restriction object. 
LimitFunction  =
         // The constraint that the function returns a objects. 
         ldef.GetFunction(
                // Which parameter values 1 (<) the value of the parameter 2. 
                ldef.funcL,
                // Parameter 1: the Restriction that the Date is the days and their number increases by 3. 
                ldef.GetFunction(
                    ldef.funcDateAdd, // Full analog of the SQL dateadd function. 
                    ldef.GetFunction(ldef.paramDayDIFF),
                    3,
                    new VariableDef(ldef.DateTimeType, Information.ExtractPropertyPath<FullTypesMainAgregator>(x => x.PoleDateTime))),
                // Option 2: Limit that from today's date, return the date only(without time). 
                ldef.GetFunction(ldef.funcOnlyDate, DateTime.Now));             
``` 



