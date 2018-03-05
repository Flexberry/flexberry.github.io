---
title: FuncIN
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
summary: Параметры и пример использования функции FuncIN
toc: true
permalink: ru/fo_func-in.html
lang: ru
---

`FuncIN` - функция, аналогичная сравнению на равенство в SQL, в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

## Параметры GetFunction

Функция [GetFunction](fo_function-list.html) принимает один параметр: массив, состоящий из определения переменной (Variable Def) и объектов, среди которых будет производиться поиск.

## Пример использования

Рассмотрим пример. Требуется вычитать все `Кредиты`, выданные особым клиентам, список ключей которых нам известен.

![](/images/pages/products/flexberry-orm/query-language/filter-ex-diagram.png)

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредиты WHERE Клиент IN ('{IDList}')@@
Где {IDList} - список [Primary-keys-objects|первичных ключей) искомых `Клиентов`
```

Через [SQLWhereLanguageDef](fo_function-list.html):

``` csharp        
List<Клиент> клиенты = new List<Клиент>();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
List<object> clientKeys = new List<object>();
clientKeys.Add(new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)));

foreach (var клиент in клиенты)
	clientKeys.Add(клиент.__PrimaryKey);

Function lf = langdef.GetFunction(langdef.funcIN, clientKeys.ToArray());
```

## Особенности сравнения строк

{% include note.html content="При использовании [MS SQL DataService](fo_mssql-data-service.html) могут возникать [проблемы со сравнением строк с пробелами на конце](http://improvingsoftware.com/2009/09/09/beware-of-this-trap-when-comparing-strings-in-t-sql-with-trailing-spaces/)." %}

Дело в том, что MS SQL Sever следует стандарту [ANSI SQL-92](https://ru.wikipedia.org/wiki/SQL-92) в том, что касается сравнения строк.

Чтобы определить, равны ли строки неодинаковой длины, прежде всего с правой стороны более короткой строки добавляются пробелы, так что длины строк становятся равными.

Затем символы в первой строке сравниваются с символами второй с учетом их расположения. Если не равна хотя бы одна пара, строки считаются неравными.

Это касается сравнений типа WHERE strfield = '...', или HAVING strfield='...', или strfield IN ('...', '...', ...). В этих случаях строки 'abc' и 'abc ' будут считаться равными.

Исключением является оператор `LIKE` (WHERE strfield LIKE '...'), для него строки 'abc' и 'abc ' - различны, поэтому для наложения ограничений на строки, вместо `FuncIN` следует использовать сочетание функций [FuncOR](fo_func-or.html), [FuncLike](fo_func-like.html).
