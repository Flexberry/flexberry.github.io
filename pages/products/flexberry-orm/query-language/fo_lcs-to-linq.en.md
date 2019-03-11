--- 
title: Transformation of the limit function in linq-restriction 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ASPNET Flexberry ORM Limitations 
summary: Methods and an example of conversion 
toc: true 
permalink: en/fo_lcs-to-linq.html 
lang: en 
autotranslated: true 
hash: 038b6bb771153e497cb315004bd404c00d62c09f651ed54956e235d02e7ae03b 
--- 

There are methods to convert from the [parental control](fo_limit-function.html) in the expression for [LINQProvider](fo_linq-provider.html) and [Vice versa](fo_limitation.html). 

* `Получение Linq expressions from the function ограничения` 
* `lf` - function limitation. 
* `expression` - Linq-expression. 

```csharp
var limitResolvingViews = new List<View>() { Кредит.Views.C__КредитE }; 
Expression expression = new LcsInterpretator().Interpret(typeof(Кредит), lf, "x", limitResolvingViews);
``` 

Here `limitResolvingViews` - [view](fd_view-definition.html) similar to [resolvingViews in LINQProvider](fo_linq-provider.html) required for correct conversion of [limit Function (Limit Function)](fo_limit-function.html) in [Linq-limit](fo_linq-provider.html) (first features are associated with the processing [of datalow](fo_detail-associations-properties.html)). To `limitResolvingViews` has the following requirements: 

* When you set the constraint of universality on detaily ([ExistExact](fo_exist-details.html)) in the view of detail should be included a reference to the aggregator. 
* A basic understanding of the aggregator should include all detaily that can occur in the constraint. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}