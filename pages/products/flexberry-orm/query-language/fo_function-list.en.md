--- 
title: SQLWhereLanguageDef 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Rules for the imposition of restrictions on the deducted objects 
toc: true 
permalink: en/fo_function-list.html 
lang: en 
autotranslated: true 
hash: ba32420ecf26a5cd6abe36058dd1070be45d8980b96cdf01f87825cf23655b25 
--- 

`SQLWhereLanguageDef` class Builder [functions, the restrictions](fo_limit-function.html) deducted on objects. 

There is an extension of basic functional language `SQLWhereLanguageDef`, `[ExternalLangDef](fo_external-lang-def.html). 

Connection: 

``` csharp
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
``` 

## the Construction of function, method GetFunction 

The construction of the function starts with calling the method `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFucntion(string, params object[));` 

As the first parameter the method takes a function type (a list of available functions below). The method accepts a set of function parameters, number and types of parameters vary depending on the type of function, a detailed description thereof can be found in the article devoted to a particular type of function. 

The method returns an object of type `ICSSoft.STORMNET.FunctionalLanguage.Function`. 

Depending on the type of the function parameter of the function `GetFunction` can be passed a description of the variable specified in the class [VariableDef](fo_variable-def.html). 

## the restrictions imposed on the enum type 

[Enumerations|Enumeration types) is stored in the database as strings. Accordingly, when constructing the variable description ([VariableDef](fo_variable-def.html)) you must use `StringType`. The argument for comparison is recommended to use `Caption` enumeration object, to `Caption` you can use class `EnumCaption`, which is part of `ICSSoft.STORMNET`. 

For example: 

![](/images/pages/products/flexberry-orm/query language/Pol.PNG) 

To impose restrictions on the gender of the client, you should compile the following function: 

```csharp
using ICSSoft.STORMNET;
// ... 
var ld = SQLWhereLanguageDef.LanguageDef;
var onlyMenFunction = ld.GetFunction(ld.funcEQ, new VariableDef(ld.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Пол)), EnumCaption.GetCaptionFor(tПол.Мужской));
``` 

## the Imposition of limitations on the date components 

To impose a restriction on the date part (year, month, or day), you can use the functions `DayPart`, `MonthPart` and `YearPart` for specifying constraints on components of the date. 

### Example 

```csharp
//ICSSoft.STORMNET.Windows.Forms.ExternalLangDef (ExternalLangDef.dll) 
//ICSSoft.STORMNET.Windows.Forms.ExternalLangDeflangdef = ExternalLangDef.LanguageDef; 
using ICSSoft.STORMNET.Windows.Forms;

var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);

lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                         langdef.GetFunction("YearPart", new VariableDef(langdef.DateTimeType, "Datavideo")), "2013");

var only2013year = DataServiceProvider.DataService.LoadObjects(lcs);

lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                         langdef.GetFunction("MonthPart", new VariableDef(langdef.DateTimeType, "Datavideo")), "12");

var onlyDecember = DataServiceProvider.DataService.LoadObjects(lcs);

lcs.LimitFunction = langdef.GetFunction(langdef.funcAND,
                langdef.GetFunction(langdef.funcEQ, 
                    langdef.GetFunction("YearPart", new VariableDef(langdef.DateTimeType, "Datavideo")), "2012"),
                langdef.GetFunction(langdef.funcEQ, 
                    langdef.GetFunction("MonthPart", new VariableDef(langdef.DateTimeType, "Datavideo")), "12"));

var onlyDecember2012 = DataServiceProvider.DataService.LoadObjects(lcs);
``` 

## Examples of usage 

Following are some examples of restrictions: 

### the Imposition of constraints on the string variable 

```csharp
var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Личность), Личность.Views.ЛичностьE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Личность>(x => x.Фамилия)), Petrov);
var клиентыФамилияПетров = DataServiceProvider.DataService.LoadObjects(lcs);
``` 

### the Imposition of limitations on workman the object (the key) 

```csharp
var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность)), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
var кредиты = DataServiceProvider.DataService.LoadObjects(lcs);
``` 

### the Imposition of limitations on workman the object (field master) 

```csharp
var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность.Фамилия)), Petrov);
var кредиты = DataServiceProvider.DataService.LoadObjects(lcs);
``` 

{% include important.html content="you Must ensure that the submission is `КредитE` master `Личность` and field `Фамилия`, or an error occurs during query execution." %} 

## List of functions 

* [FuncNOT](fo_func-not.html) 
* [FuncIsNull](fo_func-is-null.html) 
* [FuncEQ](fo_func-eq.html) 
* [FuncNEQ](fo_func-neq.html) 
* [FuncG](fo_compare-functions.html) 
* [FuncGEQ](fo_compare-functions.html) 
* [FuncL](fo_compare-functions.html) 
* [FuncLEQ](fo_compare-functions.html) 
* [FuncIN](fo_func-in.html) 
* [FuncAND](fo_func-and.html) 
* [FuncOR](fo_func-or.html) 
* [FuncLike](fo_func-like.html) 
* [FuncBETWEEN](fo_func-between.html) 
* [funcSQL](fo_func-sql.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}