--- 
title: ExternalLangDef 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Constraints, ExternalLangDef 
summary: Opportunities used functions and parameters ExternalLangDef 
toc: true 
permalink: en/fo_external-lang-def.html 
lang: en 
autotranslated: true 
hash: acff5e1a8d72f803ea10e82c475557fb134399d1b42adad3d73d7b51bcd097a4 
--- 

`ExternalLangDef` is an extension of basic functional language [SQLWhereLanguageDef](fo_function-list.html). 

The fundamental difference between `ExternalLangDef` [SQLWhereLanguageDef](fo_function-list.html) is the possibility of imposing restrictions on detali. 

## Main features ExternalLangDef 

Class `ExternalLangDef` is the heir [SQLWhereLanguageDef](fo_function-list.html), and the job restriction function is also carried out by the method invocation GetFucntion(string, params object;). However, the description of the variable-detail passed to the method parameter, instead of the base class [Explanation variables when building the functions, limitations](fo_variable-def.html) is performed with the help of his heir [DetailVariableDef](fo_variable-def.html). 

### Functions restrictions on the existence of datalow 

* `funcExistExact` 
* `funcExistAllExact` 
* `funcExist` 
* `funcExistDetails` 
* `funcExistAll` 

Read more in article [Restrictions on detali](fo_exist-details.htm). 

### Functions restrictions on the values of datalow 

* `funcMaxWithLimit`: the maximum value in detale restriction. 
* `funcMinWithLimit`: the minimum value in detale restriction. 
* `funcAvgWithLimit`: the average value in detale restriction. 
* `funcSumWithLimit`: the sum of the values in detale restriction. 
* `string funcCountWithLimit`: the number of values in detale restriction. 

### Function limitations on the date 

* `funcYearPart` - limitation for the year. 
* `funcMonthPart` limit for the month. 
* `funcDayPart` - limit for the day. 
* `funcHHPart` - maximum hours. 
* `funcMIPart` - limit for a moment. 
* `funcDATEDIFF` - restriction on the difference of dates. 
* `funcOnlyDate` - a date restriction. 
* `funcDayOfWeek` - limit on the day of the week. 
* `funcDayOfWeekZeroBased` - limit on the day of the week. 
* `funcOnlyTime` - restriction on time. 
* `funcDateAdd` - full analogue of SQL dateadd function. 
* `funcDaysInMonth` - a limit on the number of days in the month. 

For details, see [Restriction of operations with date and time](fo_restriction-datetime.html). 

##### Settings 

* `paramTODAY` - option to get today's date. 
* `paramYearDIFF` parameter to get the value from date. 
* `paramMonthDIFF` parameter to retrieve the value of month from the date. 
* `paramWeekDIFF` parameter to get the value of weeks from the date.
* `paramQuarterDIFF` parameter to retrieve the value of the quarter of the date. 
* `paramDayDIFF` parameter to retrieve the value of the day of the date. 

For details, see [Restriction of operations with date and time](fo_restriction-datetime.html). 

### Different 

* [funcToChar](fo_func-to-char.html) function convert an expression to a string. 
* `funcCurrentUser` 
* [funcImplication](fo_function-implication.html) - function implications. 
* `ExternalLangDef.ExistViewName` - a string constant that is used to indicate the name of the view when built restrictions on detali. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}