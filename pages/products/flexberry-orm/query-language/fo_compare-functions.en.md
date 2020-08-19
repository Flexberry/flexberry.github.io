--- 
title: comparison Functions 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, constraints, funcG, funcGEQ, funcL, funcLEQ 
summary: Building expressions with comparison functions 
toc: true 
permalink: en/fo_compare-functions.html 
lang: en 
autotranslated: true 
hash: 027426b2c34ce2bf562d00dac64bbfd7e91b6865dd59267fda83e88ff2bd51d2 
--- 

## comparison Function 
`FuncL` - function, analogous to the comparison "less than" in the SQL in the constructor [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

`FuncLEQ` - function, analogous comparison less than or equal to the SQL in the constructor [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

`FuncG` - function, analogous to the comparison "more" in the SQL in the constructor [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

`FuncLEQ` - function, analogous to the comparison "greater than or equal to" in SQL in the constructor [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function funcG (funcGEQ, funcL, funcLEQ), and then takes 2 objects to compare them with each other. The first is sent to the variable description (Variable Definition), which will determine the objects to сравнения; and the second parameter is the object that will be compared. 

For example, you want to deduct all `Кредиты`, the amount of which is greater (greater or equal, less than, less than or equal to) 100,000 rubles. 

The SQL statement would look as follows: 

```sql
SELECT * FROM Кредит WHERE СуммаКредита > 100000
SELECT * FROM Кредит WHERE СуммаКредита >= 100000
SELECT * FROM Кредит WHERE СуммаКредита < 100000
SELECT * FROM Кредит WHERE СуммаКредита <= 100000
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcG, //langdef.funcGEQ, langdef.funcL, langdef.funcLEQ 
			new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)), 100000);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}