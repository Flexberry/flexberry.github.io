---
title: SQLWhereLanguageDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_function-list.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* **Программная библиотека**: ICSSoft.STORMNET.FunctionalLanguage.dll.
* **Предназначение**: Общее описание работы построителя [функций ограничения](fo_limit-function.html) SQLWhereLanguageDef.

`SQLWhereLanguageDef` - класс-построитель [функций для наложения ограничений](fo_limit-function.html) на вычитываемые объекты.

Существует расширение базового функционального языка SQLWhereLanguageDef, [ExternalLangDef](fo_external-lang-def.html).

Подключение:

```cs
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
```

## Построение функции, метод GetFunction

Построение функции начинается с вызова метода `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFucntion(string, params object[]);`

В качестве первого параметра метод принимает тип функции (список доступных функций приведен ниже). Далее метод принимает набор параметров функции, количество и типы параметров варьируются в зависимости от типа функции, подробное их описание можно посмотреть в статье, посвященной определенному типу функции.

Метод возвращает объект типа `ICSSoft.STORMNET.FunctionalLanguage.Function`

### GetFunction и PrimaryKey

При построении ограничений на [первичные ключи вычитываемых объектов (собственные ключи)](fo_primary-keys-objects.html), стоит учитывать, что `SQLWhereLanguageDef` не обрабатывает константу "`PrimaryKey`". Вместо константы "`PrimaryKey`" надо использовать StormMainObjectKey (определена соответствующая константа).

**Неверно**:

```cs
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "PrimaryKey"), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

**Верно**:

```cs
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, SQLWhereLanguageDef.StormMainObjectKey), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

{% include note.html content="Стоит отметить, что ограничение на [первичный ключ](fo_primary-keys-objects.html) **мастера** накладывается следующим образом:

```cs
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, Information.ExtractPropertyPath<СамОбъект>(x => x.СсылкаНаМастера)), "84F456C1-312F-30C0-A238-11E3FE68E852");
```

где `СсылкаНаМастера` - ссылка на мастера." %}

## Наложение ограничений на перечислимый тип

[Перечислимые типы](enumerations.html) хранятся в базе как строки. Соответственно, при конструировании описания переменной (VariableDef) необходимо использовать StringType. В качестве аргумента для сравнения рекомендуется использовать Caption объекта перечисления, получить Caption можно при помощи класса `EnumCaption`, который является частью `ICSSoft.STORMNET`

Рассмотрим на примере:

![](/images/pages/products/flexberry-orm/function-list/Pol.PNG)

Чтобы наложить ограничение на пол клиента, необходимо составить следующую функцию:

```cs
using ICSSoft.STORMNET;
...
var ld = SQLWhereLanguageDef.LanguageDef;
var onlyMenFunction = ld.GetFunction(ld.funcEQ, new VariableDef(ld.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Пол)), EnumCaption.GetCaptionFor(tПол.Мужской));
```

## Наложение ограничений на компоненты даты

Чтобы наложить ограничение на часть даты (на год, месяц или день), можно воспользоваться функциями `DayPart`, `MonthPart` и `YearPart` для задания ограничений на компоненты даты.

### Пример

```cs
//ICSSoft.STORMNET.Windows.Forms.ExternalLangDef (ExternalLangDef.dll)
//ICSSoft.STORMNET.Windows.Forms.ExternalLangDeflangdef = ExternalLangDef.LanguageDef;

var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);

lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                         langdef.GetFunction("YearPart", new VariableDef(langdef.DateTimeType, "ДатаВыдачи")),
                                         "2013");

var only2013year = DataServiceProvider.DataService.LoadObjects(lcs);

lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
                                         langdef.GetFunction("MonthPart", new VariableDef(langdef.DateTimeType, "ДатаВыдачи")),
                                         "12");

var onlyDecember = DataServiceProvider.DataService.LoadObjects(lcs);

lcs.LimitFunction = langdef.GetFunction(langdef.funcAND,
                langdef.GetFunction(langdef.funcEQ, 
                    langdef.GetFunction("YearPart", new VariableDef(langdef.DateTimeType, "ДатаВыдачи")), "2012"),
                langdef.GetFunction(langdef.funcEQ, 
                    langdef.GetFunction("MonthPart", new VariableDef(langdef.DateTimeType, "ДатаВыдачи")), "12"));

var onlyDecember2012 = DataServiceProvider.DataService.LoadObjects(lcs);
```

## Примеры использования

Далее будут приведены несколько примеров наложения ограничений:

### Наложение ограничений на строковую переменную

```cs
var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Личность), Личность.Views.ЛичностьE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Личность>(x => x.Фамилия)), "Петров");
var клиентыФамилияПетров = DataServiceProvider.DataService.LoadObjects(lcs);
```

### Наложение ограничений на мастеровой объект (по ключу)

```cs
var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность)), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
var кредиты = DataServiceProvider.DataService.LoadObjects(lcs);
```

### Наложение ограничений на мастеровой объект (по полю мастера)

```cs
var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность.Фамилия)), "Петров");
var кредиты = DataServiceProvider.DataService.LoadObjects(lcs);
```
