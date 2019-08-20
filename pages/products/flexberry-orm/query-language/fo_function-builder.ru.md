---
title: FunctionBUilder
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, ограничения
summary: Использование FunctionBuilder для построения функций ограничения
toc: true
permalink: ru/fo_function-builder.html
lang: ru
---

[Функции для наложения ограничений](fo_limit-function.html) можно строить с помощью [SQLWhereLanguageDef](fo_function-list.html) или [ExternalLangDef](fo_external-lang-def.html), однако они имеют довольно многословный синтаксис.

Чтобы упростить код построения функций ограничений был разработан `FunctionBuilder` - обертка над `ExternalLangDef`, предоставляющая набор методов для построения функций ограничения. Эти методы являются аналогами метода `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFunction(string, params object[));`, однако тип функции закодирован в самом имени метода, а описания переменных [VariableDef](fo_variable-def.html) формируются на основе передаваемых строковых путей к свойствам. Кроме того, для многих функций реализованы generic-варианты, позволяющие избежать ошибок при указании ограничиваемого свойства.

`FunctionBuilder` поддерживает также наложение ограничений на детейлы с использованием функции [Exist](fo_exist-details.html).

`FunctionBuilder` поддерживает не все множество функций, доступных для использования в `GetFunction`, однако большую его часть, что позволяет прикладному разработчику использовать его для построения функций ограничения в большинстве ситуаций, тем самым уменьшая громоздскость кода. При необходимости можно реализовать дополнительные методы FunctionBuilder по аналогии с уже реализованными.

С исходным кодом `FunctionBuilder-а` и вспомогательных классов можно ознакомиться в [репозитории ORM](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/ExternalLangDef/FunctionBuilder).

Все методы покрыты тестовыми сценариями, с кодом которых также можно ознакомиться в [репозитории ORM](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/NewPlatform.Flexberry.ORM.Tests/ICSSoft.STORMNET.FunctionalLanguage).

## Пример

``` sql
select * 
from DocumentLink 
join DocumentLinkType on DocumentLinkType.PrimaryKey=DocumentLink.DocumentLinkType
where DocumentLinkType.Name = @typeName
AND ((Document = @document AND LinkedDocument = @linked) OR (Document = @linked AND LinkedDocument = @document))
```

```csharp
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(DocumentLink), DocumentLink.Views.DocumentLinkE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcAND,
	langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.StringType, "DocumentLinkType.Name"), typeName),
	langdef.GetFunction(langdef.funcOR,
		langdef.GetFunction(langdef.funcAND, 
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "Document"), document.__PrimaryKey),
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "LinkedDocument"), linkedDocument.__PrimaryKey)),
		langdef.GetFunction(langdef.funcAND, 
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "Document"), linkedDocument.__PrimaryKey),
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "LinkedDocument"), document.__PrimaryKey))));
```

``` csharp
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(DocumentLink), DocumentLink.Views.DocumentLinkE);
lcs.LimitFunction = FunctionBuilder.BuildAnd(
	FunctionBuilder.BuildEquals<DocumentLink>(x => x.DocumentLinkType.Name, typeName),
	FunctionBuilder.BuildOr(
		FunctionBuilder.BuildAnd(
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.Document, document),
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.LinkedDocument, linkedDocument)
			),
		FunctionBuilder.BuildAnd(
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.Document, linkedDocument),
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.LinkedDocument, document)
			)
		)
	);
```

Как можно видеть из примера, код построения функции с использованием FunctionBuilder очень похож, но становится читаемее за счет меньшего количества аргументов у методов, а также отсутствия необходимости создавать VariableDef вручную в большинстве случаев. Кроме того, в примере с `FunctionBuilder-ом` нельзя допустить ошибку в пути свойства, поскольку используется generic-метод, контролирующий имеющиеся в объекте свойства. Аналогичный по возможностям код можно реализовать и в случае использования `GetFunction` с использованием метода `Information.ExtractPropertyPath<T>()`, однако это сделает код еще многословнее.

## Методы, доступные в FunctionBuilder

Список методов, перечисленных здесь, может со временем измениться, поэтому за полным списком возможностей рекомендуется обратиться к [исходному коду класса](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/ExternalLangDef/FunctionBuilder).

