---
title: is Empty/is non-empty in the advanced editor, limitations in the WEB
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_web-limit-editor-null.html
lang: en
autotranslated: true
hash: eac6688c6e0a3cb65fa507adcb6f6b37cea630ab9581ac3e23dd77d45e0e8835
---

## Is Empty/Is Nonempty

In the expanded constraint editor](fa_advanced-limit-editor.html) there is support for expressions is Empty/is nonempty (Empty/Filled).

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/web-limit-editor is null.png)

`Пусто` corresponds to the function [funcIsNull](fo_func-is-null.html a) of [SQLWhereLanguageDef](fo_function-list.html).

`Непусто` corresponds to the function of funcNotIsNull [ExternalLangDef](fo_external-lang-def.html).

## The possibility expression is Empty/is nonempty

Expression is Empty/is nonempty can be applied to:

* own properties,
* properties of datalow,
* masters,
* properties of the masters (the expression of the `Пусто(A. B. C)` interpreted as `A==null || A. B==null || A. B. C==null`).

## Incomparable with null properties and Empty/is nonempty

If you have a class with a property `Кредит` `СуммаКредита` real type, the translation in [LINQProvider|LinqProvider) it will be presented as follows:

``` csharp
var ds = (SQLDataService)DataServiceProvider.DataService;
IQueryable<Кредит> limit = ds.Query<Кредит>(Кредит.Views.C__КредитE).Where(x => (x.СуммаКредита as object) == null);
```

and transfer in lcs:

``` csharp
Function lf = FunctionBuilder.BuildIsNull<Кредит>(x => x.СуммаКредита);
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}