--- 
title: FuncIsNull 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncIsNull 
toc: true 
permalink: en/fo_func-is-null.html 
lang: en 
autotranslated: true 
hash: c428a8896b48f33223d91ec31ea7b65bbd41118f5c8d5e6bee8080e74673e322 
--- 

`FuncIsNull` function is the same as testing for null in SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

For `FuncIsNull` required one parameter - the variable description (Variable Definition), you need to check on `null`. 

Let's consider an example. Required to deduct all `Кредиты`, which equals `ДатаВыдачи` `null`. 

The SQL statement would look as follows: 

```sql
SELECT * FROM Кредит WHERE ДатаВыдачи is null
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

``` csharp    
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcIsNull, new VariableDef(langdef.DateTimeType, Information.ExtractPropertyPath<Клиент>(x => x.ДатаВыдачи)));
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}