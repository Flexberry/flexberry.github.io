--- 
title: FuncIN 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Public Restrictions 
summary: Parameters and an example of a function FuncIN 
toc: true 
permalink: en/fo_func-in.html 
lang: en 
autotranslated: true 
hash: 47b881144270d8351de4dd4851cd7d680ac617c45d3d06f4f1a9804eb71c2e81 
--- 

`FuncIN` the same function as the equality comparison in SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) takes one parameter: an array of variable definition (Variable Def) and the objects among which the search is performed. 

## Example usage 

Let's consider an example. Required to deduct all `Кредиты` issued special clients, the list of keys which are known to us. 

![](/images/pages/products/flexberry-orm/query language/filter-ex-diagram.png) 

The SQL statement would look as follows: 

```sql
SELECT * FROM Кредиты WHERE Клиент IN ('{IDList}')@@
Где {IDList} - список [Primary-keys-objects|первичных ключей) искомых `Клиентов`
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

``` csharp        
List<Клиент> клиенты = new List<Клиент>();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
List<object> clientKeys = new List<object>();
clientKeys.Add(new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)));

foreach (var клиент in клиенты)
	clientKeys.Add(клиент.__PrimaryKey);

Function lf = langdef.GetFunction(langdef.funcIN, clientKeys.ToArray());
``` 

## Features string comparison 

{% include note.html content="If you use [MS SQL DataService](fo_mssql-data-service.html) you can have [problems with comparing strings with spaces in conce](http://improvingsoftware.com/2009/09/09/beware-of-this-trap-when-comparing-strings-in-t-sql-with-trailing-spaces/)." %} 

The fact that MS SQL Sever follows the standard [ANSI 92 SQL](https://ru.wikipedia.org/wiki/SQL-92) in regard to string comparisons. 

To determine whether strings of unequal length, especially on the right side the shorter string adds spaces, so the string lengths become equal. 

Then the symbols in the first string are compared with characters of the second with regard to their location. If not equal at least one pair, the strings are considered unequal. 

This applies to comparisons such as WHERE strfield = '...', or HAVING strfield='...', or strfield IN ('...', '...', ...). In these cases, the string 'abc' and 'abc' are considered equal. 

The exception is the operator `LIKE` (WHERE strfield LIKE '...'), for it is the string 'abc' and 'abc' is different, therefore, to impose restrictions on the lines, instead of `FuncIN` you should use a combination of functions [FuncOR](fo_func-or.html), [FuncLike](fo_func-like.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}