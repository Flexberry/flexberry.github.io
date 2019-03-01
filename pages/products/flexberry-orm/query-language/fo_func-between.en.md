--- 
title: FuncBETWEEN 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncBETWEEN 
toc: true 
permalink: en/fo_func-between.html 
lang: en 
autotranslated: true 
hash: 52024ab0213d4097e4e529327620b0349224786f00fca6a0afce5abe881013e2 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/