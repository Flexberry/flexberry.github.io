--- 
title: the function of the implication 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, limitations, implication? funcImplication 
summary: the Use of implication constraints 
toc: true 
permalink: en/fo_function-implication.html 
lang: en 
autotranslated: true 
hash: 14f3ec1abd5d086284697d694c1fb658c2f1fe7fed54b324cf1df98b5625d1cb 
--- 

`funcImplication` - [ExternalLangDef](fo_external-lang-def.html) to specify logical implication. 

The implication is a function that takes two logical operands: antecedents and consequences, can take the following values: 

Prerequisite | Consequence | Result 
:----------|:----------|:---------- 
0 | 0 | 1 
0 | 1 | 1 
1 | 0 | 0 
1 | 1 | 1 

Logically, the implication "If a, then b" is equal to "(not a) or b". 
For example, a constraint of the form: "If nickname = snow leopard, then gender = male" would lead to the conclusion all cats with the male sex and all (not snow leopard). 


## Example 

**All Ivans Ivanovs and not Ivanov** 

``` csharp
var langDef = new ExternalLangDef();
Function function = langDef.GetFunction(langDef.funcImplication,
                    langDef.GetFunction(langDef.funcEQ, new VariableDef(langDef.StringType, Last name),"Ivanov"),
                    langDef.GetFunction(langDef.funcEQ, new VariableDef(langDef.StringType, "Name"), "Ivan"));
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}