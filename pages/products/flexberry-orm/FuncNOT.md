---
title: FuncNOT
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/func-n-o-t.html
folder: product--folder
lang: ru
---

### FuncNOT

FuncNot = Not

Функция, аналогичная отрицанию в SQL.

### Пример
[FuncNEQ](func-n-e-q.html) = FuncNOT ( [FuncEQ](func-e-q.html) )

[FuncL](compare-functions.html) = FuncNOT ( [FuncGEQ](compare-functions.html) )

[FuncLEQ](compare-functions.html) = FuncNOT ( [FuncG](compare-functions.html) )

и т.д.

### [FunctionalLanguage](function-list.html)

```cs    Клиент клиент = new Клиент();
	SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
	Function lf1 = langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.GuidType, "Клиент"), клиент.__PrimaryKey);
	Function lf2 = langdef.GetFunction(langdef.funcNOT, langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "Клиент"), клиент.__PrimaryKey);
```

`lf1` и `lf2` эквивалентны.


### Параметры GetFunction
Для FuncNOT необходим один параметр - отрицаемая функция.

# См. также
[Перечень функций](function-list.html)


