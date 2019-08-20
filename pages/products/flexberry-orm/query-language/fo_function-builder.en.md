--- 
title: FunctionBuilder 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Limitations 
summary: Building functions with FunctionBuilder
toc: true 
permalink: en/fo_function-builder.html 
lang: en 
--- 

[Limit functions](fo_limit-function.html) may be built using [SQLWhereLanguageDef](fo_function-list.html) or [ExternalLangDef](fo_external-lang-def.html), but they has rather bulky syntax.

`FunctionBuilder` was developed to simplify limit functions building code. `FunctionBuilder` is a wrapper around `ExternalLangDef` with set of methods for limit function building. Theese methods are analogous to `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFunction(string, params object[));` calls but with fixed function types and not necessary [VariableDef](fo_variable-def.html) arguments. `VariableDef` is created automatically from passed property path strings. Moreover there are generic methods that guard from mistakes with property namings.

`FunctionBuilder` supports detail limiting with [Exist function](fo_exist-details.html).

`FunctionBuilder` doesn't support all functions available in `GetFunction`, but necessary majority of it which allows developer to use FunctionBuilder most times. In case of lack of some method you may implement it same way with already existing methods.

`FunctionBuilder` and supporting classes source code is available on [ORM repository](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/ExternalLangDef/FunctionBuilder).

All methods are covered with unit tests, source code available in [ORM repository](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/NewPlatform.Flexberry.ORM.Tests/ICSSoft.STORMNET.FunctionalLanguage).

## Example

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

As you can see code with FunctionBuilder looks similar but is more readable because of less number of arguments and no need to create VariableDef manually in most cases. Moreover FunctionBuilder protects from making mistake in property path because of generic method using, which controls avaliable properties in object. Similar code may be implemented for `GetFunction` using `Information.ExtractPropertyPath<T>()` method, but it will be even more bulky.

## FunctionBuilder methods

List of methods may change during time, so you may find full list of available methods in [source code](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/ExternalLangDef/FunctionBuilder).

