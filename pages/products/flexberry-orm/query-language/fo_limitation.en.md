---
title: Фильтрация и ограничение выборки данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: en/fo_limitation.html
folder: products/flexberry-orm/
lang: en
---



## Описание компонент

Для фильтрации и ограничения выборки данных существует несколько классов, реализующих данную логику:

* [Функции ограничения (Limit Function)](fo_limit-function.html).
* [LINQProvider](fo_linq-provider.html) (компоненты для поддержки LINQ в [Flexberry ORM](fo_flexberry-orm.html)).

## Связь между функциями ограничения и LinqProvider

Существуют методы, позволяющие производить конвертацию из выражения для [LINQProvider](fo_linq-provider.html) в [LimitFunction](fo_limit-function.html). Возможности провести обратную конвертацию [Flexberry ORM](fo_flexberry-orm.html) не предоставляет.

`Получение функции ограничения из Linq-выражения`

`expression` - Linq-выражение.

`funcToReturn` - функция ограничения.

``` csharp
var queryExpression = LinqToLcs.GetExpressionToQueryFromWhereExpression(expression, typeof(Кредит));  
Function funcToReturn = LinqToLcs.GetLcs(queryExpression, Кредит.Views.C__КредитE, limitResolvingViews).LimitFunction;
```

## Применимость способов наложения ограничений

* [LINQProvider](fo_linq-provider.html)  - находится в стадии доработки, как следствие - реализует не все функции.
* [Функции ограничения](fo_limit-function.html) - является наиболее полным и объемлющим инструментом для вычитки данных. В перспективе будет вытеснен [LINQProvider](fo_linq-provider.html)'ом.
