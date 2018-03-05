---
title: FuncBETWEEN
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncBETWEEN
toc: true
permalink: ru/fo_func-between.html
lang: ru
---

`FuncBETWEEN` - функция, аналогичная проверке на попадание элемента в диапазон значений в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction

Функция [GetFunction](fo_function-list.html) принимает первым параметром тип функции `funcBETWEEN`, а дальше принимает 3 параметра. Первым посылается описание переменной (Variable Definition), по которому будут определяться объекты для сравнения; а вторым и третьим - границы диапазона.

Например, требуется вычитать все **Кредиты**, сумма которых находится в диапазоне от 50000 рублей до 75000 рублей.

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Клиент WHERE СуммаКредита BETWEEN 50000 AND 75000@@
```

Через [SQLWhereLanguageDef](fo_function-list.html):

```csharp 
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcBETWEEN,
			new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)),
							50000,
							75000);
```
