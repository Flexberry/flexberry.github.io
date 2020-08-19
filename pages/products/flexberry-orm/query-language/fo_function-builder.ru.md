---
title: FunctionBuilder
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, ограничения
summary: Использование FunctionBuilder для построения функций ограничения
toc: true
permalink: ru/fo_function-builder.html
lang: ru
---

[Функции для наложения ограничений](fo_limit-function.html) можно строить с помощью [SQLWhereLanguageDef](fo_function-list.html) или [ExternalLangDef](fo_external-lang-def.html), однако они имеют довольно многословный синтаксис.

Чтобы упростить код построения функций ограничений, был разработан `FunctionBuilder` - обертка над `ExternalLangDef`, предоставляющая набор методов для построения функций ограничения. Эти методы являются аналогами метода `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFunction(string, params object[));`. Однако тип функции закодирован в самом имени метода, а описания переменных [VariableDef](fo_variable-def.html) формируются на основе передаваемых строковых путей к свойствам. Кроме того, для многих функций реализованы generic-варианты, позволяющие избежать ошибок при указании ограничиваемого свойства.

`FunctionBuilder` поддерживает также наложение ограничений на детейлы с использованием функции [Exist](fo_exist-details.html).

`FunctionBuilder` поддерживает не все множество функций, доступных для использования в `GetFunction`, однако большую его часть, что позволяет прикладному разработчику использовать его для построения функций ограничения в большинстве ситуаций, тем самым уменьшая громоздскость кода. При необходимости можно реализовать дополнительные методы FunctionBuilder по аналогии с уже реализованными.

## Пример
Представим себе модель данных, в которой есть сущность Документ (Document) и сущность СвязьДокументов (DocumentLink), имеющая две ссылки на Документ с наименованиями Document и LinkedDocument, а также ссылку на объект ТипСвязиДокументов (DocumentLinkType).

![](/images/pages/products/flexberry-orm/query-language/function-builder-example-model.png)

Предположим, нам необходимо вычитать из БД все объекты DocumentLink с определенным наименованием типа связи, связывающие два документа, и при этом не учитывать порядок, в котором фигурируют заданные документы в объекте СвязьДокументов. Таким образом мы должны получить ограничение, примерно соответствующее следующему sql-запросу:

``` sql
SELECT * 
FROM DocumentLink 
JOIN DocumentLinkType ON DocumentLinkType.PrimaryKey = DocumentLink.DocumentLinkType
WHERE DocumentLinkType.Name = @typeName
AND ((Document = @document1 AND LinkedDocument = @document2) OR (Document = @document2 AND LinkedDocument = @document1))
```

### Функция ограничения с использованием langdef
```csharp
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(DocumentLink), DocumentLink.Views.DocumentLinkE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcAND,
	langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.StringType, "DocumentLinkType.Name"), typeName),
	langdef.GetFunction(langdef.funcOR,
		langdef.GetFunction(langdef.funcAND, 
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "Document"), document1.__PrimaryKey),
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "LinkedDocument"), document2.__PrimaryKey)),
		langdef.GetFunction(langdef.funcAND, 
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "Document"), document2.__PrimaryKey),
			langdef.GetFunction(langdef.funcEQ, new VariableDef(langdef.GuidType, "LinkedDocument"), document1.__PrimaryKey))));
```

### Функция ограничения с использованием FunctionBuilder
``` csharp
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(DocumentLink), DocumentLink.Views.DocumentLinkE);
lcs.LimitFunction = FunctionBuilder.BuildAnd(
	FunctionBuilder.BuildEquals<DocumentLink>(x => x.DocumentLinkType.Name, typeName),
	FunctionBuilder.BuildOr(
		FunctionBuilder.BuildAnd(
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.Document, document1),
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.LinkedDocument, document2)
			),
		FunctionBuilder.BuildAnd(
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.Document, document2),
			FunctionBuilder.BuildEquals<DocumentLink>(x => x.LinkedDocument, document1)
			)
		)
	);
```

Как можно видеть из примера, код построения функции с использованием FunctionBuilder очень похож, но становится читаемее за счет меньшего количества аргументов у методов, а также отсутствия необходимости создавать VariableDef вручную в большинстве случаев. Кроме того, в примере с `FunctionBuilder-ом` нельзя допустить ошибку в пути свойства, поскольку используется generic-метод, контролирующий имеющиеся в объекте свойства. Аналогичный по возможностям код можно реализовать и в случае использования `GetFunction` с использованием метода `Information.ExtractPropertyPath<T>()`, однако это сделает код еще многословнее.

## Исходный код
С исходным кодом `FunctionBuilder` и вспомогательных классов можно ознакомиться в [репозитории ORM](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/ExternalLangDef/FunctionBuilder). Все методы покрыты тестовыми сценариями, с кодом которых также можно ознакомиться в [репозитории ORM](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/NewPlatform.Flexberry.ORM.Tests/ICSSoft.STORMNET.FunctionalLanguage).

## Методы, доступные в FunctionBuilder

Список методов, перечисленных здесь, может со временем измениться, поэтому за полным списком возможностей рекомендуется обратиться к [автодокументации](https://flexberry.github.io/NewPlatform.Flexberry.ORM/autodoc/develop/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_functional_language_1_1_function_builder.html).

