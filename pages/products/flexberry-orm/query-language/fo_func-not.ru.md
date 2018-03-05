---
title: FuncNOT
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncNOT
toc: true
permalink: ru/fo_func-not.html
lang: ru
---

`FuncNot` - функция, аналогичная отрицанию в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

[FuncNEQ](fo_func-neq.html) = FuncNOT ( [FuncEQ](fo_func-eq.html) )
[FuncL](fo_compare-functions.html) = FuncNOT ( [FuncGEQ](fo_compare-functions.html) )
[FuncLEQ](fo_compare-functions.html) = FuncNOT ( [FuncG](fo_compare-functions.html) )

и т.д.

## Параметры GetFunction

Для `FuncNOT` необходим один параметр - отрицаемая функция.

``` csharp    
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf1 = langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Фамилия)), клиент.Фамилия);
Function lf2 = langdef.GetFunction(langdef.funcNOT, langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Фамилия)), клиент.__PrimaryKey);
```

`lf1` и `lf2` эквивалентны.
