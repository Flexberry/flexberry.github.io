--- 
title: FuncNEQ 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncLike 
toc: true 
permalink: en/fo_func-neq.html 
lang: en 
autotranslated: true 
hash: c9d33068b079a15b6c2121f502c9c997933769e7393321476790bc001a3b731c 
--- 

`FuncNEQ` function similar comparison for inequality in the SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function `funcNEQ`, and then takes 2 objects to compare them with each other. The first is sent to the variable description (Variable Definition), which will determine the objects to сравнения; and the second parameter is the object that will be compared. 

__Note__: despite the fact that when you build a constraint to a variable of type `bool` [FuncEQ](fo_func-eq.html) allows to send the second parameter, FuncNEQ does not allow it. Thus, the following construction will fail: 

```csharp
langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.BoolType, "SomeBoolFlag"))
``` 

Let's consider an example. Required to deduct all `Кредиты` not related to a particular `Клиенту`. 

![](/images/pages/products/flexberry-orm/query language/filter-ex-diagram.png) 

The SQL statement would look as follows: 

```sql
SELECT * FROM Кредит WHERE Клиент <> '{ID}'
``` 

Where {ID} [primary key](fo_primary-keys-objects.html) desired `Клиента`. 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp    
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)), клиент.__PrimaryKey);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}