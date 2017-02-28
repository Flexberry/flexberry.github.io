---
title: FuncOR
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-o-r.html
---
##FuncOR
FuncOR - функция, аналогичная логическому "ИЛИ" в SQL, в построителе функций ограничения SQLWhereLanguageDef.

##Параметры GetFunction
Функция GetFunction принимает первым параметром тип функции funcAND, а дальше принимает N (>= 2) функций, которые необходимо объединить логическим "ИЛИ".

Рассмотрим пример. Требуется вычитать все Кредиты, выданные на сумму, превышающую 100000 рублей или меньше 10000 рублей.

SQL-выражение выглядело бы следующим образом:

```
SELECT * FROM Кредит WHERE СуммаКредита < 10000 OR СуммаКредита > 100000
```

Где {ID} - первичный ключ искомого Клиента

Через SQLWhereLanguageDef:

```
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcOR,
            langdef.GetFunction(
                langdef.funcL,
                new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)),
                10000),
            langdef.GetFunction(
                langdef.funcG,
                new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)),
                100000));
```


