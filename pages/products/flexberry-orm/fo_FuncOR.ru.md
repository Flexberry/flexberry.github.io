---
title: FuncOR
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-o-r.html
folder: products/flexberry-orm/
lang: ru
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* **Программная библиотека**: ICSSoft.STORMNET.FunctionalLanguage.dll.
* **Предназначение**: Общее описание работы функции FuncOR в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

FuncOR - функция, аналогичная логическому "ИЛИ" в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction

Функция [GetFunction](fo_function-list.html) принимает первым параметром тип функции funcAND, а дальше принимает N (>= 2) функций, которые необходимо объединить логическим "ИЛИ".

Рассмотрим пример. Требуется вычитать все `Кредиты`, выданные на сумму, превышающую 100000 рублей или меньше 10000 рублей.

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредит 
WHERE СуммаКредита < 10000 OR СуммаКредита > 100000
```

Где {ID} - [Primary-keys-objects|первичный ключ) искомого `Клиента`

Через [SQLWhereLanguageDef](fo_function-list.html):

``` csharp    
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









