--- 
title: Read objects with superimposed restriction 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: an Example based on the SQLWhereLanguageDef 
toc: true 
permalink: en/fo_load-by-limit.html 
lang: en 
autotranslated: true 
hash: cdd6396758459888997ab24ca9865bd2cddf867661bdf8ba516dda8532a58b91 
--- 

In order to impose the restriction that requires the property `LimitFunction` structure `LoadingCustomizationStruct` limiting set function (`ICSSoft.STORMNET.FunctionalLanguage.Function`) any language restrictions. 

In the example below, taken language `ICSSoft.STORMNET.FunctionalLanguage.SQLWhere. SQLWhereLanguageDef` for specifying constraints in SQL queries, and is formed by a limit (select all «Ivana Ivanovic»): 

```csharp
	LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Автор), Автор.Views.Главное);				
	/*Other initialization*/
	SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
	Function lf = langdef.GetFunction(langdef.funcAND,
				langdef.GetFunction(langdef.funcLike, 
				new VariableDef(langdef.StringType, "Name"), "Ivan"),
				langdef.GetFunction(langdef.funcLike, 
				new VariableDef(langdef.StringType, "Patronymic"), "I."));		
    lcs.LimitFunction = lf;
	/*Read*/
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}