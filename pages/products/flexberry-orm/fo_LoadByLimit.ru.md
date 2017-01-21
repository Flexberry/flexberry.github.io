---
title: Чтение объектов с наложенным ограничением
sidebar: flexberry-orm_sidebar
keywords: Ограничения
toc: true
permalink: ru/fo_Чтение-объектов-с-наложенным-ограничением.html
folder: products/flexberry-orm/
lang: ru
---

Для того, чтобы наложить ограничение, требуется в свойство `LimitFunction` структуры `LoadingCustomizationStruct` установить ограничивающую функцию (`ICSSoft.STORMNET.FunctionalLanguage.Function`) любого языка ограничений.


В примере ниже берётся язык `ICSSoft.STORMNET.FunctionalLanguage.SQLWhere. SQLWhereLanguageDef` для задания ограничений SQL-запросов и формируется ограничение (выбираются все «Иваны Ивановичи»):

```cs
	LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Автор), Автор.Views.Главное);				
	/*Другая инициализация*/
	SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
	Function lf = langdef.GetFunction(langdef.funcAND,
				langdef.GetFunction(langdef.funcLike, 
				new VariableDef(langdef.StringType, "Имя"), "Иван"),
				langdef.GetFunction(langdef.funcLike, 
				new VariableDef(langdef.StringType, "Отчество"), "Иванович"));		
    lcs.LimitFunction = lf;
	/*Чтение*/
```

