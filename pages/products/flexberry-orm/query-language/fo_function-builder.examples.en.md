---
title: FunctionBuilder examples
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Limitations 
summary: Examples of using FunctionBuilder
toc: true
permalink: en/fo_function-builder-examples.html
lang: en 
autotranslated: false
---

For a more detailed understanding of the transition from [SQLWhereLanguageDef](f_function-list.html) on [FunctionBuilder](fo_function-builder.html) this article provides basic examples of the refactor that has been implemented on several application projects.

## funcSQL
No special changes.

### Limit function using langdef
```csharp
    Function lf = LangDef.GetFunction(LangDef.funcSQL, "sql");
```

### Limit function using FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildSQL("sql");
```

## funcNOT
No special changes.

### Limit function using langdef
```csharp
	Function FuncTrue = LangDef.GetFunction(LangDef.funcEQ, 1, 1);
    Function lf = LangDef.GetFunction(LangDef.funcNOT, FuncTrue);
```

### Limit function using FunctionBuilder
``` csharp
	Function FuncTrue = FunctionBuilder.BuildTrue();
	Function lf = FunctionBuilder.BuildNot(FuncTrue);
```

## funcEQ
The restriction on primary keys is accompanied by a reference to the `__Primary Key` property, which does not reduce the complexity and readability of the code.

### Limit function using langdef
```csharp
	VariableDef PrimaryKeyVarDef = new VariableDef(LangDef.GuidType, SQLWhereLanguageDef.StormMainObjectKey);
    Function lf = LangDef.GetFunction(
        LangDef.funcEQ,
        PrimaryKeyVarDef,
        TestDataObject1.__PrimaryKey);
```

### Limit function using FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildEquals(TestDataObject1);
```

## funcIN - own primary key
The main disadvantage of `funcIN` is the need to pass an untyped array with the second argument, the first element of which must be the property path. In the `Function Builder` the deficiency rather gracefully overcome to construct the function of columns in primary keys do not have to specify `SQLWhereLanguageDef.StormMainObjectKey`, i.e. it is not necessary to specify the property path (in this case).

### Limit function using langdef
```csharp
    var clientKeys = new List<object> { new VariableDef(langdef.GuidType, SQLWhereLanguageDef.StormMainObjectKey) };
	clientKeys.AddRange(ordKeys);
	Function lf = langdef.GetFunction(langdef.funcIN, clientKeys.ToArray());
```

### Limit function using FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildIn(ordKeys);
```

## funcIN - class field
It is not necessary to use `EnumCaption.GetCaptionFor` to limit objects to an array of enumerations.

### Limit function using langdef
```csharp
	Function lf = langdef.GetFunction(langdef.funcIN, new VariableDef(langdef.StringType, "СпособВвода"),
		EnumCaption.GetCaptionFor(СпВвода),
		EnumCaption.GetCaptionFor(tСпособВвода.ИмпортАСПенсия));
```

### Limit function using FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildIn<Происхождение>(x => x.СпособВвода, СпВвода, tСпособВвода.ИмпортАСПенсия);
```

## funcExists
The syntax is quite simplified, the `BuildExists` wrapper method has two overloads:
* Function BuildExists(DetailVariableDef dvd, Function function = null)
* Function BuildExists(string connectMasterPorp, View view, Function function = null)

The main difference is the optional argument `function` for which the sample will be carried out.

### Limit function using langdef
```csharp
	Function FuncTrue = LangDef.GetFunction(LangDef.funcEQ, 1, 1);
	string ConnectMasterProp = Information.ExtractPropertyName<TestDataObjectDetail>(x => x.TestDataObject);
	DetailVariableDef DetVarDef = FunctionHelper.GetDetailVarDef(TestDataObjectDetail.Views.D, ConnectMasterProp);
	Function lf = LangDef.GetFunction(LangDef.funcExist, DetVarDef, FuncTrue);
```

### Limit function using FunctionBuilder
Overload 1:
``` csharp
	string ConnectMasterProp = Information.ExtractPropertyName<TestDataObjectDetail>(x => x.TestDataObject);
	DetailVariableDef DetVarDef = FunctionHelper.GetDetailVarDef(TestDataObjectDetail.Views.D, ConnectMasterProp);
	Function lf = FunctionBuilder.BuildExists(DetVarDef);
```
Overload 2:
``` csharp
	string ConnectMasterProp = Information.ExtractPropertyName<TestDataObjectDetail>(x => x.TestDataObject);
	Function lf = FunctionBuilder.BuildExists(ConnectMasterProp, TestDataObjectDetail.Views.D, DetVarDef);
```

## Missing functionality
A more attentive reader can replace that in `Function Builder` there are no such functions as`DatePart`, `YearPart`, `Count`, `SUM`, `MAX`, etc. These functions were not 'wrapped' intentionally, because `FunctionBuilder` is focused on building logical functions, each of which as a result returns a Boolean value. However, many `FunctionBuilder` methods have overloads that allow you to pass `Function` as argument.

## Summary

Moving from `LangDef.GetFunction` to `FunctionBuilder` carries not only quantitative changes in the form of reducing the length of the code, but also qualitative:
* significant increase in code readability for both simple and highly complex functions
* minimize errors when specifying property paths by using lambdas
* simplification of work with primary keys through the use of  `PKHelper`