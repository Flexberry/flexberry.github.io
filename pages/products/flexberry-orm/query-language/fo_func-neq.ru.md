---
title: FuncNEQ
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncLike
toc: true
permalink: ru/fo_func-neq.html
lang: ru
---

`FuncNEQ` - функция, аналогичная сравнению на неравенство в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction

Функция [GetFunction](fo_function-list.html) принимает первым параметром тип функции `funcNEQ`, а дальше принимает 2 объекта на сравнение их между собой. Первым посылается описание переменной (Variable Definition), по которому будут определяться объекты для сравнения; а вторым параметром - объект, с которым будет происходить сравнение.

__Примечание__: несмотря на то, что при построении ограничения на переменную типа `bool` [FuncEQ](fo_func-eq.html) позволяет не посылать второй параметр, FuncNEQ не позволяет этого сделать. Таким образом, следующая конструкция приведёт к ошибке: 

```csharp
langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.BoolType, "SomeBoolFlag"))
``` 

Рассмотрим пример. Требуется вычитать все `Кредиты`, не относящиеся к определенному `Клиенту`.

![](/images/pages/products/flexberry-orm/query-language/filter-ex-diagram.png)

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредит WHERE Клиент <> '{ID}'
```

Где {ID} - [первичный ключ](fo_primary-keys-objects.html) искомого `Клиента`.

Через [SQLWhereLanguageDef](fo_function-list.html):

```csharp    
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)), клиент.__PrimaryKey);
```
