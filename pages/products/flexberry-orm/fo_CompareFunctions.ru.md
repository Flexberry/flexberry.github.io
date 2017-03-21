---
title: FuncG, FuncGE, FuncL, FuncLE
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_compare-functions.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* **Программная библиотека**: ICSSoft.STORMNET.FunctionalLanguage.dll.
* **Предназначение**: Общее описание работы функций FuncG, FuncGE, FuncL, FuncLE в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Функции сравнения

* FuncL - функция, аналогичная сравнению "меньше" в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).
* FuncLEQ - функция, аналогичная сравнению "меньше или равно" в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).
* FuncG - функция, аналогичная сравнению "больше" в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).
* FuncLEQ - функция, аналогичная сравнению "больше или равно" в SQL, в построителе [функций ограничения](fo_limit-function.html)[SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction
Функция [GetFunction](fo_function-list.html) принимает первым параметром тип функции funcG (funcGEQ, funcL, funcLEQ), а дальше принимает 2 объекта на сравнение их между собой. Первым посылается описание переменной (Variable Definition), по которому будут определяться объекты для сравнения; а вторым параметром - объект, с которым будет происходить сравнение.

Рассмотрим пример. Требуется вычитать все `Кредиты`, сумма которых больше (больше или равно, меньше, меньше или равно) 100000 рублей.

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредит WHERE СуммаКредита > 100000
SELECT * FROM Кредит WHERE СуммаКредита >= 100000
SELECT * FROM Кредит WHERE СуммаКредита < 100000
SELECT * FROM Кредит WHERE СуммаКредита <= 100000
```

Через [SQLWhereLanguageDef](fo_function-list.html):

```cs
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcG, //langdef.funcGEQ, langdef.funcL, langdef.funcLEQ
			new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)), 100000);
```

