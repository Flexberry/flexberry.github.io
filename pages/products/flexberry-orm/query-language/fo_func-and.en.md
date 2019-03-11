--- 
title: FuncAND 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and an example of a function FuncAND 
toc: true 
permalink: en/fo_func-and.html 
lang: en 
autotranslated: true 
hash: 366a31a6e619f0bd513e35e7f6c146af629f3d9bc4b49612354935031e01025d 
--- 

`FuncAND` the same function as the logical "And" SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function funcAND, and then accepts N (>= 2) functions to be combined with logical And. 

For example, you want to subtract all the **Loans** specific **Customer** issued in excess of 100,000 rubles. 

The SQL statement would look as follows: 

```sql
SELECT * FROM Кредит WHERE Клиент = '{ID}' AND СуммаКредита > 100000@@
Где {ID} - [Primary-keys-objects|первичный ключ) искомого `Клиента`
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(
					langdef.funcAND,
					langdef.GetFunction(
						langdef.funcEQ, 
						new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Клиент)), 
						клиент.__PrimaryKey),
					langdef.GetFunction(
						langdef.funcG, 
						new VariableDef(langdef.NumericType, Information.ExtractPropertyPath<Кредит>(x => x.СуммаКредита)), 
						100000));
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}