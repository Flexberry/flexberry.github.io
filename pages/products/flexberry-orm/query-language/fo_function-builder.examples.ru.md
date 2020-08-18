---
title: FunctionBuilder примеры
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, ограничения
summary: Примеры использования FunctionBuilder
toc: true
permalink: ru/fo_function-builder-examples.html
lang: ru
---

Для более детального понимания перехода с [SQLWhereLanguageDef](fo_function-list.html) на [FunctionBuilder](fo_function-builder.html) в данной статье приведены основные примеры рефактора, который был осуществлён на нескольких прикладных проектах.

## funcSQL
Особые изменения отсутствуют.

### Функция ограничения с использованием langdef
```csharp
    Function lf = LangDef.GetFunction(LangDef.funcSQL, "sql");
```

### Функция ограничения с использованием FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildSQL("sql");
```

## funcNOT
Особые изменения отсутствуют.

### Функция ограничения с использованием langdef
```csharp
	Function FuncTrue = LangDef.GetFunction(LangDef.funcEQ, 1, 1);
    Function lf = LangDef.GetFunction(LangDef.funcNOT, FuncTrue);
```

### Функция ограничения с использованием FunctionBuilder
``` csharp
	Function FuncTrue = FunctionBuilder.BuildTrue();
	Function lf = FunctionBuilder.BuildNot(FuncTrue);
```

## funcEQ
Ограничение по первичным ключам сопровождается обращением к свойству `__PrimaryKey`, что не уменьшает сложность и читаемость кода.

### Функция ограничения с использованием langdef
```csharp
	VariableDef PrimaryKeyVarDef = new VariableDef(LangDef.GuidType, SQLWhereLanguageDef.StormMainObjectKey);
    Function lf = LangDef.GetFunction(
        LangDef.funcEQ,
        PrimaryKeyVarDef,
        TestDataObject1.__PrimaryKey);
```

### Функция ограничения с использованием FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildEquals(TestDataObject1);
```

## funcIN - собственный первичный ключ
Главным неудобством `funcIN` является необходимость передавать вторым аргументом нетипизированный массив, первым элементом которого должен быть путь свойства. В `FunctionBuilder` данный недостаток довольно изящно преодолён - для построения функции ограничения по первичным ключам не обязательно указывать `SQLWhereLanguageDef.StormMainObjectKey`, т.е. не обязательно указывать путь свойства (в данном случае).

### Функция ограничения с использованием langdef
```csharp
    var clientKeys = new List<object> { new VariableDef(langdef.GuidType, SQLWhereLanguageDef.StormMainObjectKey) };
	clientKeys.AddRange(ordKeys);
	Function lf = langdef.GetFunction(langdef.funcIN, clientKeys.ToArray());
```

### Функция ограничения с использованием FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildIn(ordKeys);
```

## funcIN - поле класса
Для ограничения объектов по массиву перечислений не обязательно использовать `EnumCaption.GetCaptionFor`.

### Функция ограничения с использованием langdef
```csharp
	Function lf = langdef.GetFunction(langdef.funcIN, new VariableDef(langdef.StringType, "СпособВвода"),
		EnumCaption.GetCaptionFor(СпВвода),
		EnumCaption.GetCaptionFor(tСпособВвода.ИмпортАСПенсия));
```

### Функция ограничения с использованием FunctionBuilder
``` csharp
	Function lf = FunctionBuilder.BuildIn<Происхождение>(x => x.СпособВвода, СпВвода, tСпособВвода.ИмпортАСПенсия);
```

## funcExists
Синтаксис довольно сильно упрощен, метод-обертка `BuildExists` имеет два перегрузки:
* Function BuildExists(DetailVariableDef dvd, Function function = null)
* Function BuildExists(string connectMasterPorp, View view, Function function = null)

Основным отличием является необязательность передачи функции, по которой будет осуществлена выборка.

### Функция ограничения с использованием langdef
```csharp
	Function FuncTrue = LangDef.GetFunction(LangDef.funcEQ, 1, 1);
	string ConnectMasterProp = Information.ExtractPropertyName<TestDataObjectDetail>(x => x.TestDataObject);
	DetailVariableDef DetVarDef = FunctionHelper.GetDetailVarDef(TestDataObjectDetail.Views.D, ConnectMasterProp);
	Function lf = LangDef.GetFunction(LangDef.funcExist, DetVarDef, FuncTrue);
```

### Функция ограничения с использованием FunctionBuilder
Перегрузка 1:
``` csharp
	string ConnectMasterProp = Information.ExtractPropertyName<TestDataObjectDetail>(x => x.TestDataObject);
	DetailVariableDef DetVarDef = FunctionHelper.GetDetailVarDef(TestDataObjectDetail.Views.D, ConnectMasterProp);
	Function lf = FunctionBuilder.BuildExists(DetVarDef);
```
Перегрузка 2:
``` csharp
	string ConnectMasterProp = Information.ExtractPropertyName<TestDataObjectDetail>(x => x.TestDataObject);
	Function lf = FunctionBuilder.BuildExists(ConnectMasterProp, TestDataObjectDetail.Views.D, DetVarDef);
```

## Отсутствующий функционал
Более внимательный читатель может заменить, что в `FunctionBuilder` отсутствуют такие функции как `DatePart`, `YearPart`, `Count`, `SUM`, `MAX` и др. Данные функции не были "обернуты" умышлено, т.к. `FunctionBuilder` ориентирован на построение логических функций, каждая из которых в результате возвращает булево значение. Однако, многие методы `FunctionBuilder` имеют перегрузки, позволяющее передавать `Function` в качестве аргументов.

## Итого

Переход от `LangDef.GetFunction` к `FunctionBuilder` несет в себе не только количественные изменения в виде уменьшения длины кода, но и качественные:
* значительное повышение читаемости кода как для простых функций, так и для громоздких и многоэтажных
* сведение к минимуму ошибок при указании путей свойств за счет применения лямбд
* упрощение работы с первичными ключами за счет использования `PKHelper`