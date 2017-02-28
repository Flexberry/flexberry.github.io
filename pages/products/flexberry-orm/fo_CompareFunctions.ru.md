---
title: FuncG, FuncGE, FuncL, FuncLE
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_compare-functions.html
---

## Функции сравнения
FuncL - функция, аналогичная сравнению "меньше" в SQL, в построителе [LimitFunction|функций ограничения] [FunctionList|SQLWhereLanguageDef].{br}
FuncLEQ - функция, аналогичная сравнению "меньше или равно" в SQL, в построителе [LimitFunction|функций ограничения] [FunctionList|SQLWhereLanguageDef].{br}
FuncG - функция, аналогичная сравнению "больше" в SQL, в построителе [LimitFunction|функций ограничения] [FunctionList|SQLWhereLanguageDef].{br}
FuncLEQ - функция, аналогичная сравнению "больше или равно" в SQL, в построителе [LimitFunction|функций ограничения] [FunctionList|SQLWhereLanguageDef].{br}

## Параметры GetFunction
Функция [FunctionList|GetFunction] принимает первым параметром тип функции funcG (funcGEQ, funcL, funcLEQ), а дальше принимает 2 объекта на сравнение их между собой. Первым посылается описание переменной (Variable Definition), по которому будут определяться объекты для сравнения; а вторым параметром - объект, с которым будет происходить сравнение.

Рассмотрим пример. Требуется вычитать все {{Кредиты}}, сумма которых больше (больше или равно, меньше, меньше или равно) 100000 рублей.

SQL-выражение выглядело бы следующим образом:

```
SELECT * FROM Кредит WHERE СуммаКредита > 100000
SELECT * FROM Кредит WHERE СуммаКредита >= 100000
SELECT * FROM Кредит WHERE СуммаКредита < 100000
SELECT * FROM Кредит WHERE СуммаКредита <= 100000
```

Через [FunctionList|SQLWhereLanguageDef]:

```cs
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcG, //langdef.funcGEQ, langdef.funcL, langdef.funcLEQ
			new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)), 100000);
```

