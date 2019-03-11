--- 
title: FuncSQL 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncSQL 
toc: true 
permalink: en/fo_func-sql.html 
lang: en 
autotranslated: true 
hash: e09b14fde0b082949c597680c86351e02e33499db95d3151364b7c6bd4c062eb 
--- 

`FuncSQL` - function Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html), allowing to perform insert SQL. 

{% include note.html content="Use this function is not recommended." %} 

For the case when [ExternalLanguageDef](fo_external-lang-def.html) does not have the necessary set of functions [limit](fo_limit-function.html) can be built in the form of SQL statements. To use this feature with extreme caution is recommended because the switching data source types this is more difficult, error detection is also difficult. 

It is important to understand that `funcSQL` can be part of another "normal" function, in this case, you need not forget about the brackets outside the SQL statement. Brackets under the interpretation do not put. 

## Example 

The following expression 

```csharp
lcs.LimitFunction = ldef.GetFunction(ldef.funcAND,
                ldef.GetFunction(ldef.funcSQL, "\"abc\" = 1"),
                ldef.GetFunction(ldef.funcSQL, "\"def\" = 2")
                );

``` 

will be interpreted as follows: 

```sql
WHERE ( "abc" = 1 AND def = 2)
``` 

{% include note.html content="Names of attributes must be enclosed in quotation marks, it will help [Flexberry ORM](fo_flexberry-orm.html) to gracefully handle [limit](fo_limit-function.html)." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}