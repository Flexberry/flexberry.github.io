--- 
title: FuncLike 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncLike 
toc: true 
permalink: en/fo_func-like.html 
lang: en 
autotranslated: true 
hash: 50ef23d87966bbb980d12dc7a1fe3ed239b7691bf09cd81fc09ab6de591ee57a 
--- 

`FuncLike` - equivalent of the test for occurrence of a substring in another string in SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

## Parameters GetFunction 

[GetFunction](fo_function-list.html) accepts the first argument type of a function `funcLike`, and then takes 2 objects to compare them with each other. The first is sent to the variable description (Variable Definition), which will determine the objects to сравнения; and the second parameter is the mask, which will be compared. 

## Example usage 

Let's consider an example. Required to deduct all `Клиентов` with a residence permit in the city of Perm. (For simplicity, we assume sufficient availability of the word "Perm" in the line `Клиент.Прописка`) 

The SQL statement would look as follows: 

```sql
SELECT * FROM Клиент 
WHERE Прописка like '%Пермь%'
``` 

[SQLWhereLanguageDef](fo_function-list.html): 

```csharp   
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf = langdef.GetFunction(
			langdef.funcLike,
			new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Прописка)), 
			"%Perm%");
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}