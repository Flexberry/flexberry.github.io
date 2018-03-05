---
title: FuncAND
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncAND
toc: true
permalink: ru/fo_func-and.html
lang: ru
---

`FuncAND` - функция, аналогичная логическому "И" в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction

Функция [GetFunction](fo_function-list.html) принимает первым параметром тип функции funcAND, а дальше принимает N (>= 2) функций, которые необходимо объединить логическим "И".

Например, требуется вычитать все **Кредиты** определенного **Клиента**, выданные на сумму, превышающую 100000 рублей.

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредит WHERE Клиент = '{ID}' AND СуммаКредита > 100000@@
Где {ID} - [Primary-keys-objects|первичный ключ) искомого `Клиента`
```

Через [SQLWhereLanguageDef](fo_function-list.html):

```csharp
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
