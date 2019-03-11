--- 
title: FuncBETWEEN 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncBETWEEN 
toc: true 
permalink: en/fo_func-between.html 
lang: en 
autotranslated: true 
hash: c0ab093a82eb016f31cce5b23862b23ba2725e3b22ed3e9aafa715c9c34c37e6 
--- 

`FuncBETWEEN` function is the same as testing for hit element in the range of values in the SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function `funcBETWEEN`, and then takes 3 parameters. The first is sent to the variable description (Variable Definition), which will determine the objects to сравнения; and the second and third end of the range. 

For example, you want to subtract all the****, which amount is in the range of 50,000 rubles to 75,000 rubles. 

The SQL statement would look as follows: 

```sql
SELECT * FROM Клиент WHERE СуммаКредита BETWEEN 50000 AND 75000@@
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp 
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(langdef.funcBETWEEN,
			new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)),
							50000,
							75000);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}