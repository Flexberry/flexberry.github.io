--- 
title: Filtering and limiting data selection 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: the Description, the relationship between the functions of limitations and LinqProvider example convert 
toc: true 
permalink: en/fo_limitation.html 
lang: en 
autotranslated: true 
hash: 4ea0ba171d63827cf46c9c06183e8fc4a4a20378602f703312adf487e530385d 
--- 

To filter and limit the data selection there are several classes that implement this logic: 

* [The limit function (Limit Function)](fo_limit-function.html). 
* [LINQProvider](fo_linq-provider.html) (components for LINQ support in [Flexberry ORM](fo_flexberry-orm.html)). 

## the relationship between the functions, limitations and LinqProvider 

There are methods to convert from the expression for [LINQProvider](fo_linq-provider.html) in [LimitFunction](fo_limit-function.html). Opportunities to carry out reverse conversion [Flexberry ORM](fo_flexberry-orm.html) does not provide. 

__Finding the function limitation of Linq expressions__ 

`expression` - Linq-expression. 
`funcToReturn` - function limitation. 

``` csharp
var queryExpression = LinqToLcs.GetExpressionToQueryFromWhereExpression(expression, typeof(Кредит));  
Function funcToReturn = LinqToLcs.GetLcs(queryExpression, Кредит.Views.C__КредитE, limitResolvingViews).LimitFunction;
``` 

## the applicability of the methods of imposing restrictions 

* [LINQProvider](fo_linq-provider.html) - is being finalized, as a consequence not implement all of the functions. 
* [Parental control](fo_limit-function.html) - is the most complete and comprehensive tool for proofreading and editing of data. In the future will be superseded by [LINQProvider](fo_linq-provider.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}