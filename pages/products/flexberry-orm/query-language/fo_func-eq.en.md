--- 
title: FuncEQ 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncEQ 
toc: true 
permalink: en/fo_func-eq.html 
lang: en 
autotranslated: true 
hash: d3e2f2f96b6281f57ff4dbeb0fab7a7929b7bf8e2916aed35fac79bd17440c1f 
--- 

`FuncEQ` the same function as the equality comparison in SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function `funcEQ`, and then takes 2 objects to compare them with each other. The first sent a description of the variable (`Variable Definition`), which will determine the objects to сравнения; and the second parameter is the object that will be compared. 

**Exception** is type `bool`: `langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.BoolType, "SomeBoolFlag"))` will work without specifying the second parameter (the default will be compared with `true`). 

**Check for `null`** should be carried out with [FuncIsNull](fo_func-is-null.html), when you try to check anything on `null` with `FuncEQ` will receive an exception **Object reference not set to an instance of an object.** 

## Example usage 

For example, you want to subtract all the **Loans** specific **Customer**. 

![](/images/pages/products/flexberry-orm/query language/filter-ex-diagram.png) 

The SQL statement would look as follows: 

```sql
SELECT * FROM Кредит WHERE Клиент = '{ID}'@@
Где {ID} - [Primary-keys-objects|первичный ключ) искомого `Клиента`
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp   
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)), клиент.__PrimaryKey);
``` 

## Features string comparison 

{% include important.html content="If you use [MS SQL DataService](fo_mssql-data-service.html) you can have [problems with comparing strings with spaces in conce](http://improvingsoftware.com/2009/09/09/beware-of-this-trap-when-comparing-strings-in-t-sql-with-trailing-spaces/)." %} 

The fact that MS SQL Sever follows the standard [ANSI 92 SQL](https://ru.wikipedia.org/wiki/SQL-92) in regard to string comparisons. 

To determine whether strings of unequal length, especially on the right side the shorter string adds spaces, so the string lengths become equal. 

Then the symbols in the first string are compared with characters of the second with regard to their location. If not equal at least one pair, the strings are considered unequal. 

This applies to comparisons such as WHERE strfield = '...', or HAVING strfield='...', or strfield IN ('...', '...', ...). In these cases, the string 'abc' and 'abc' are considered equal. 

The exception is the operator `LIKE` (WHERE strfield LIKE '...'), for it is the string 'abc' and 'abc' is different, therefore, to impose restrictions on the string, you should use the function [funcLike](fo_func-like.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}