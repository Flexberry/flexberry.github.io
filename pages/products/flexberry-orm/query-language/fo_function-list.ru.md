---
title: SQLWhereLanguageDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Правила наложения ограничений на вычитываемые объекты
toc: true
permalink: ru/fo_function-list.html
lang: ru
---

`SQLWhereLanguageDef` - класс-построитель [функций для наложения ограничений](fo_limit-function.html) на вычитываемые объекты.

Существует расширение базового функционального языка `SQLWhereLanguageDef`, `[ExternalLangDef](fo_external-lang-def.html).

Подключение:

``` csharp
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
```

## Построение функции, метод GetFunction

Построение функции начинается с вызова метода `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFucntion(string, params object[));`

В качестве первого параметра метод принимает тип функции (список доступных функций приведен ниже). Далее метод принимает набор параметров функции, количество и типы параметров варьируются в зависимости от типа функции, подробное их описание можно посмотреть в статье, посвященной определенному типу функции.

Метод возвращает объект типа `ICSSoft.STORMNET.FunctionalLanguage.Function`.

В зависимости от типа функции параметром функции `GetFunction` может передаваться  описание переменной, задаваемое в помощью класса [VariableDef](fo_variable-def.html).

## Наложение ограничений на перечислимый тип

[Перечислимые типы](fd_enumerations.html) хранятся в базе как строки. Соответственно, при конструировании описания переменной ([VariableDef](fo_variable-def.html)) необходимо использовать `StringType`. В качестве аргумента для сравнения рекомендуется использовать `Caption` объекта перечисления, получить `Caption` можно при помощи класса `EnumCaption`, который является частью `ICSSoft.STORMNET`.

Например:

![Pol](/images/pages/products/flexberry-orm/query-language/Pol.PNG)

Чтобы наложить ограничение на пол клиента, необходимо составить следующую функцию:

```csharp
using ICSSoft.STORMNET;
// ...
var ld = SQLWhereLanguageDef.LanguageDef;
var onlyMenFunction = ld.GetFunction(ld.funcEQ, new VariableDef(ld.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Пол)), EnumCaption.GetCaptionFor(tПол.Мужской));
```

## Наложение ограничений на компоненты даты

Чтобы наложить ограничение на часть даты (на год, месяц или день), можно воспользоваться функциями `DayPart`, `MonthPart` и `YearPart` для задания ограничений на компоненты даты.

### Пример

```csharp

using ICSSoft.STORMNET.Windows.Forms;

IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);

var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);

lcs.LimitFunction = languageDef.GetFunction(languageDef.funcEQ,
                                         languageDef.GetFunction("YearPart", new VariableDef(languageDef.DateTimeType, "ДатаВыдачи")), "2013");

var only2013year = ds.LoadObjects(lcs);

lcs.LimitFunction = languageDef.GetFunction(languageDef.funcEQ,
                                         languageDef.GetFunction("MonthPart", new VariableDef(languageDef.DateTimeType, "ДатаВыдачи")), "12");

var onlyDecember = ds.LoadObjects(lcs);

lcs.LimitFunction = languageDef.GetFunction(languageDef.funcAND,
                languageDef.GetFunction(languageDef.funcEQ, 
                    languageDef.GetFunction("YearPart", new VariableDef(languageDef.DateTimeType, "ДатаВыдачи")), "2012"),
                languageDef.GetFunction(languageDef.funcEQ, 
                    languageDef.GetFunction("MonthPart", new VariableDef(languageDef.DateTimeType, "ДатаВыдачи")), "12"));

var onlyDecember2012 = ds.LoadObjects(lcs);
```

## Примеры использования

Далее будут приведены несколько примеров наложения ограничений:

### Наложение ограничений на строковую переменную

```csharp
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Личность), Личность.Views.ЛичностьE);
lcs.LimitFunction = languageDef.GetFunction(languageDef.funcEQ,
            new VariableDef(languageDef.StringType, Information.ExtractPropertyPath<Личность>(x => x.Фамилия)), "Петров");
var клиентыФамилияПетров = ds.LoadObjects(lcs);
```

### Наложение ограничений на мастеровой объект (по ключу)

```csharp
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);

var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = languageDef.GetFunction(languageDef.funcEQ,
            new VariableDef(languageDef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность)), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
var кредиты = ds.LoadObjects(lcs);
```

### Наложение ограничений на мастеровой объект (по полю мастера)

```csharp
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds);

var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = languageDef.GetFunction(languageDef.funcEQ,
            new VariableDef(languageDef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность.Фамилия)), "Петров");
var кредиты = ds.LoadObjects(lcs);
```

> Следует убедиться, что в представлении `КредитE` есть мастер `Личность` и его поле `Фамилия`, иначе произойдёт ошибка при выполнении запроса.

## Список функций

* [FuncNOT](fo_func-not.html)
* [FuncIsNull](fo_func-is-null.html)
* [FuncEQ](fo_func-eq.html)
* [FuncNEQ](fo_func-neq.html)
* [FuncG](fo_compare-functions.html)
* [FuncGEQ](fo_compare-functions.html)
* [FuncL](fo_compare-functions.html)
* [FuncLEQ](fo_compare-functions.html)
* [FuncIN](fo_func-in.html)
* [FuncAND](fo_func-and.html)
* [FuncOR](fo_func-or.html)
* [FuncLike](fo_func-like.html)
* [FuncBETWEEN](fo_func-between.html)
* [funcSQL](fo_func-sql.html)
