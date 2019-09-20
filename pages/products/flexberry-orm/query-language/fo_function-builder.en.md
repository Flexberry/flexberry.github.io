--- 
title: FunctionBuilder 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Limitations 
summary: Building functions with FunctionBuilder
toc: true 
permalink: en/fo_function-builder.html 
lang: en 
autotranslated: false
--- 

[Limit functions](fo_limit-function.html) may be built using [SQLWhereLanguageDef](fo_function-list.html) or [ExternalLangDef](fo_external-lang-def.html), but they has rather bulky syntax.

`FunctionBuilder` was developed to simplify limit functions building code. `FunctionBuilder` is a wrapper around `ExternalLangDef` with set of methods for limit function building. Theese methods are analogous to `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFunction(string, params object[));` calls but with fixed function types and not necessary [VariableDef](fo_variable-def.html) arguments. `VariableDef` is created automatically from passed property path strings. Moreover there are generic methods that guard from mistakes with property namings.

`FunctionBuilder` supports detail limiting with [Exist function](fo_exist-details.html).

`FunctionBuilder` doesn't support all functions available in `GetFunction`, but necessary majority of it which allows developer to use FunctionBuilder most times. In case of lack of some method you may implement it same way with already existing methods.

## Example
Imagine domain model with Document entity and DocumentLink entity, which has two associations with Document, named Document and LinkDocument, and association with DocumentLinkType entity.

![](/images/pages/products/flexberry-orm/query-language/function-builder-example-model.png)

In case we need to load from database all DocumentLinks with specified link type name, which links two document and we shouldn't take in account the order of links. So we should get limit function similar to sql query:

``` sql
SELECT * 
FROM DocumentLink 
JOIN DocumentLinkType ON DocumentLinkType.PrimaryKey = DocumentLink.DocumentLinkType
WHERE DocumentLinkType.Name = @typeName
AND ((Document = @document1 AND LinkedDocument = @document2) OR (Document = @document2 AND LinkedDocument = @document1))
```

### Limit function using langdef
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

### Limit function using FunctionBuilder
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

As you can see code with FunctionBuilder looks similar but is more readable because of less number of arguments and no need to create VariableDef manually in most cases. Moreover FunctionBuilder protects from making mistake in property path because of generic method using, which controls avaliable properties in object. Similar code may be implemented for `GetFunction` using `Information.ExtractPropertyPath<T>()` method, but it will be even more bulky.

## Source code
`FunctionBuilder` and supporting classes source code is available on [ORM repository](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/ExternalLangDef/FunctionBuilder). All methods are covered with unit tests, source code available in [ORM repository](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/tree/develop/NewPlatform.Flexberry.ORM.Tests/ICSSoft.STORMNET.FunctionalLanguage).

 
## FunctionBuilder methods

List of methods may change during time, so you may find full list of available methods in [autodocumentation](https://flexberry.github.io/NewPlatform.Flexberry.ORM/autodoc/develop/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_functional_language_1_1_function_builder.html).

