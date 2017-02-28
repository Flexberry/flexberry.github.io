---
title: FuncAND
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-a-n-d.html
---

##FuncAND
FuncAND - функция, аналогичная логическому "И" в SQL, в построителе функций ограничения SQLWhereLanguageDef.

##Параметры GetFunction
Функция GetFunction принимает первым параметром тип функции funcAND, а дальше принимает N (>= 2) функций, которые необходимо объединить логическим "И".

Рассмотрим пример. Требуется вычитать все Кредиты определенного Клиента, выданные на сумму, превышающую 100000 рублей.

SQL-выражение выглядело бы следующим образом:

```
SELECT * FROM Кредит WHERE Клиент = '{ID}' AND СуммаКредита > 100000
Где {ID} - первичный ключ искомого Клиента
```

Через SQLWhereLanguageDef:

```
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(
                    langdef.funcAND,
                    langdef.GetFunction(
                        langdef.funcEQ,
                        new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)),
                        клиент.__PrimaryKey),
                    langdef.GetFunction(
                        langdef.funcG,
                        new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)),
                        100000));
```