---
title: Пусто/Непусто в расширенном редакторе ограничений в WEB
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_web-limit-editor-null.html
lang: ru
---

## Пусто/Непусто

В [расширенном редакторе ограничений](fa_advanced-limit-editor.html) есть поддержка выражений Пусто/Непусто (Пусто/Заполнено).

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/web-limit-editor-null.png)

`Пусто` соответствует функции [funcIsNull](fo_func-is-null.html) из [SQLWhereLanguageDef](fo_function-list.html). 

`Непусто` соответствует функции funcNotIsNull из [ExternalLangDef](fo_external-lang-def.html).

## Возможности выражений Пусто/Непусто

Выражения Пусто/Непусто можно накладывать на:

* собственные свойства,
* свойства детейлов,
* мастера,
* свойства мастеров (при этом выражение вида `Пусто(A.B.C)` интерпретируется как `A==null || A.B==null || A.B.C==null`).

## Несравнимые с null свойства и Пусто/Непусто

Если имеется класс `Кредит` со свойством `СуммаКредита` вещественного типа, то при переводе в [LINQProvider|LinqProvider) оно будет представляться следующим образом:

``` csharp
var ds = (SQLDataService)DataServiceProvider.DataService;
IQueryable<Кредит> limit = ds.Query<Кредит>(Кредит.Views.C__КредитE).Where(x => (x.СуммаКредита as object) == null);
```

а при переводе в lcs:

``` csharp
Function lf = FunctionBuilder.BuildIsNull<Кредит>(x => x.СуммаКредита);
```
