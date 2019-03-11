--- 
title: FuncOR 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncOR 
toc: true 
permalink: en/fo_func-or.html 
lang: en 
autotranslated: true 
hash: e01a58d97ad21bd9a1639356f6563ea083bb524a628e2053e97b825cbebbd31a 
--- 

`FuncOR` the same function as the logical "OR" SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function `funcAND`, and then accepts N (>= 2) functions that you want to combine the logical "OR". 
Let's consider an example. Required to deduct all `Кредиты` issued in an amount exceeding 100,000 rubles or 10,000 rubles. 
The SQL statement would look as follows: 

```sql
SELECT * FROM Кредит 
WHERE СуммаКредита < 10000 OR СуммаКредита > 100000
``` 

Where {ID} [primary key](fo_primary-keys-objects.html) desired `Клиента`. 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp    
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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}