--- 
title: FuncNOT 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Parameters and example FuncNOT 
toc: true 
permalink: en/fo_func-not.html 
lang: en 
autotranslated: true 
hash: cf5b7f16dcd9246cf392f29094622726d97ec8e2fcd3aaf20554393f91451fef 
--- 

`FuncNot` function, negation in SQL Builder [function limitations](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html). 

[FuncNEQ](fo_func-neq.html) = FuncNOT ( [FuncEQ](fo_func-eq.html) ) 
[FuncL](fo_compare-functions.html) = FuncNOT ( [FuncGEQ](fo_compare-functions.html) ) 
[FuncLEQ](fo_compare-functions.html) = FuncNOT ( [FuncG](fo_compare-functions.html) ) 

etc. 

## Parameters GetFunction 

For `FuncNOT` the required one parameter is the negated function. 

``` csharp    
Клиент клиент = new Клиент();
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
Function lf1 = langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Фамилия)), клиент.Фамилия);
Function lf2 = langdef.GetFunction(langdef.funcNOT, langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Фамилия)), клиент.__PrimaryKey);
``` 

`lf1` and `lf2` equivalent. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}