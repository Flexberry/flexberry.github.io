--- 
title: Opportunities LinqProvider 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Supported, features, limitations and use in objects with aggregation, usage examples 
toc: true 
permalink: en/fo_linq-provider-faetures.html 
lang: en 
autotranslated: true 
hash: 8c4974bcded10c3144d9de50c6c622b758c523e0f17b3f524540519837a2fd8a 
--- 

Below are the main opportunities provided by [LINQProvider](fo_linq-provider.html). 

In the examples `ds` is the service data obtained in the following way: 

``` csharp
var ds = (SQLDataService)DataServiceProvider.DataService; 
``` 

## Supported 

### Number 

In addition to numerical attributes of the object in query is allowed to use constants, variables, and functions from them. All this will be calculated. 
For example, the query issuing all objects with the attribute Length set attribute Width 10: 

``` csharp
int Number1 = 8;
int Number2 = 2;
IQueryable<Кошка> queryList = ds.Query<Кошка>();
IQueryable<Кошка> query = from pn in queryList where pn.Длина == Number1 + Number2 + pn.Ширина select pn;
List<DataObject> data = query.Cast<DataObject>().ToList();
``` 

It supports not only integer when building restrictions. 

``` csharp
float length = 10.0f;
IQueryable<Улица> queryList = ds.Query<Улица>();
IQueryable<Улица> query = from pn in queryList where pn.Протяженность > length select pn;
List<DataObject> data = query.Cast<DataObject>().ToList();
``` 

### Line 

#### Contains, StartsWith, EndsWith 

To work with strings [Linq provider](fo_linq-provider.html) allows you to use the Contains methods as well as options of StartsWith, EndsWith a single operator. The use of other options StartsWith and EndsWith will throw an exception `MethodSignatureException`. 

**Choose cats whose names contain the string “Osh”** 

``` csharp
List<Кошка> cat = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.Кличка.Contains("Osh")).ToList();
``` 

#### Regex 

In [LINQProvider](fo_linq-provider.html) is the minimum support Regex (the main restrictions are related to the fact that the pattern for the regular expression translates into a pattern search for design like in sql). 

``` csharp
List<Порода> objss = ds.Query<Порода>(Порода.Views.ПородаE).Where(x => Regex.IsMatch(x.Название, "12.*3")).ToList();
``` 

Acceptable for use in regular expressions design: ".", ".*", "^", "$". 

### Date/Time 

The restrictions on dates you can use various properties `DateTime`. 

[Linq provider](fo_linq-provider.html) does not provide a method `DateTime.AddDays`. When you try to use exception will be thrown `NotImplementedException`. 

You can use the numeric date components such as year, month, day in month, hour, minute, day of the week. The days of the week are converted to numbers as in C# in the enumeration `DayOfWeek` (0 – Sunday, 1 – Monday .. 6 – Saturday). For this [were added the function DayOfWeekZeroBased in addition to the old DayOfWeek](fo_external-lang-def.html). 

When you try to use the properties of Ticks, Second, Millisecond, DayOfYear exception will be thrown `MethodSignatureException`. 

**Select fractures which were earlier than today:** 

``` csharp
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.Date < DateTime.Now.Date).ToList();
``` 

**To Apply AddYears** 

``` csharp
DateTime now = DateTime.Now;
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.AddYears(1) < now.Date).ToList();
``` 

**Select the fractures that occurred on Sunday.** 

``` csharp
List<Перелом> objss = ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Дата.DayOfWeek == DayOfWeek.Sunday).ToList();
``` 

#### NullableDateTime 

To make the comparison with `NullableDateTime` `DateTime` you need to bring them to the same type __out__ Linq query.

``` csharp
DateTime date = new DateTime(2012, 1, 1);
NullableDateTime nullableDate = new NullableDateTime { Value = date };
// Datawedge is of type NullableDateTime. 
List<Кредит> objss = ds.Query<Кредит>(Кредит.Views.КредитE).Where(к => к.ДатаВыдачи < nullableDate).ToList();
``` 

### Boolean 

It is possible to use not only expressions, but also their combinations with the operations of the algebra of logic, as well as the use of constants true and false. 

### an Enumeration (enum) 

Restriction on transfer rekomendovano to impose the following: 

``` csharp
// First cache the enum value in a variable, then limit 
var типПерелома = ТипПерелома.Открытый;
ds.Query<Перелом>(Перелом.Views.ПереломE).Where(o => o.Тип == типПерелома).ToList();
``` 

## General capabilities and features 

### Comparison with null 

When comparing with null is available not only test for equality and inequality but also job operations ">", ">=", "<", "<=". 

As in standard work with Linq, the expression of the "`Property > null`" where `Property`, for example, of the type `int` will return `false`. 

``` csharp
ds.Query<Лапа>(Лапа.Views.ЛапаE).Where(x => x.РазмерNullableInt > null);
``` 

### the performance of simple arithmetic operations 

Inside Linq expressions can perform simple arithmetic operations: 

``` csharp
string prefix = "prefix";
string postfix = "postfix";
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(x => x.Кличка == prefix + postfix);
``` 

## Restrictions LINQProvider 

Among the major constraints can be specified as "projection" (the projection from the current sample, i.e. the descendants of the DataObject of some other type) and specification of constraints using grouping (Group By). 

To use projection or groups should: 

* To obtain a collection of data objects for which you want grouping or projection, use [LINQProvider](fo_linq-provider.html). 
* Make an additional request to the collection using [LINQ to Objects](https://msdn.microsoft.com/ru-ru/library/bb397919.aspx). 

## Work with the masters/detaylari/pseudocatalase 

### Keys (PrimaryKey): 

To compare objects [key](fo_primary-keys-objects.html) you must use the method `Equals`: 

``` csharp
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.__PrimaryKey.Equals(кошка.__PrimaryKey));
``` 

### Masters 

There is a possibility to impose restrictions on the master, or his attributes of type int, long, bool, string, DateTime. 
For example: 

``` csharp
Порода порода = ds.Query<Порода>(Порода.Views.ПородаE).First(); // Some object type breed 
Кошка кошка = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода == порода); //Get the first cat of this breed 
Кошка кошка2 = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода.Название == Wild); // Get the cat name breed 
``` 

In this case, allowed restrictions only to the masters of the first level. When you try to execute following code will throw an exception `MasterLevelException`. 

``` csharp
Кошка кошка = ds.Query<Кошка>(Кошка.Views.КошкаE).FirstOrDefault(o => o.Порода.ТипПороды == порода.ТипПороды); //Using the wizard, the wizard will throw an exception 
``` 

But it is possible to impose restrictions on the masters of the n-th level by its PrimaryKey: 

``` csharp
ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => ПородаPKs.Contains(o.Порода.ТипПороды.__PrimaryKey));
``` 

### Detaily 

Considering [the features of writing Linq queries to arrays of datalow](fo_functionality-work-detail-array.html): 

``` csharp
ds.Query<Порода>(Порода.Views.КотенокE).Where(
                x => x.Кошка.Лапа.Cast<Лапа>().Any(o => o.ТипЛапы.Название == "front")).ToList();
``` 

### Pseudometal 

Working with pseudocatalase described in the article [Pseudometal in LinqProvider](fo_psedodetails-linq-provider.html). 

## Examples of usage 

* Simple proofreading all entries: 

``` csharp
var credits = ds.Query<Кредит>(Кредит.Views.КредитL.Name);
``` 
* The imposition of constraints on a string field: 

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Фамилия == Petrov);
``` 
* Imposition of limitations on the date: 

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.ДатаРождения > new DateTime(1980, 1, 1));
``` 

* The imposition of constraints on Boolean type: 

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Уволен);
``` 

* Of course, terms can be combined: 

``` csharp
var личности = ds.Query<Личность>(Личность.Views.ЧеловекL.Name).Where(l => l.Уволен && l.ДатаУвольнения > new DateTime(2012, 1, 1));
``` 

* Imposition of limitations on the master (key): 

``` csharp
var кредиты = ds.Query<Кредит>(Кредит.Views.КредитL.Name).Where(k => k.Клиент == klient);
``` 
* Imposition of limitations on detali: 

``` csharp
ds.Query<Порода>(Порода.Views.КотенокE).Where(x => x.Кошка.Лапа.Cast<Лапа>().Any(o => o.ТипЛапы.Название == "front")).ToList();
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}