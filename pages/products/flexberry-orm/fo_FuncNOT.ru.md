---
title: FuncNOT
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-n-o-t.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* **Программная библиотека**: ICSSoft.STORMNET.FunctionalLanguage.dll.
* **Предназначение**: Общее описание работы функции FuncNOT в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

FuncNot = Not

Функция, аналогичная отрицанию в SQL.

## Пример

[FuncNEQ](fo_func-n-e-q.html) = FuncNOT ( [FuncEQ](fo_func-e-q.html) )

[FuncL](fo_compare-functions.html) = FuncNOT ( [FuncGEQ](fo_compare-functions.html) )

[FuncLEQ](fo_compare-functions.html) = FuncNOT ( [FuncG](fo_compare-functions.html) )

и т.д.

## [FunctionalLanguage](fo_function-list.html)

```cs    
	Клиент клиент = new Клиент();
	SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
	Function lf1 = langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.GuidType, "Клиент"), клиент.__PrimaryKey);
	Function lf2 = langdef.GetFunction(langdef.funcNOT, langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "Клиент"), клиент.__PrimaryKey);
```

`lf1` и `lf2` эквивалентны.


## Параметры GetFunction

Для FuncNOT необходим один параметр - отрицаемая функция.

## См. также
[Перечень функций](fo_function-list.html)


