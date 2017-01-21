---
title: SQLWhereLanguageDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_function-list.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* '''Программная библиотека''': ICSSoft.STORMNET.FunctionalLanguage.dll.
* '''Предназначение''': Общее описание работы построителя [функций ограничения](limit-function.html) SQLWhereLanguageDef.
</td>
</tr></tbody></table></a>
</div>

# SQLWhereLanguageDef
`SQLWhereLanguageDef` - класс-построитель [функций для наложения ограничений](limit-function.html) на вычитываемые объекты.

Существует расширение базового функционального языка SQLWhereLanguageDef, [ExternalLangDef](external-lang-def.html).

Подключение:
```cs
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
```
# Построение функции, метод GetFunction
Построение функции начинается с вызова метода `ICSSoft.STORMNET.FunctionalLanguage.FunctionalLanguageDef.GetFucntion(string, params object[]);`

В качестве первого параметра метод принимает тип функции (список доступных функций приведен ниже). Далее метод принимает набор параметров функции, количество и типы параметров варьируются в зависимости от типа функции, подробное их описание можно посмотреть в статье, посвященной определенному типу функции.

Метод возвращает объект типа `ICSSoft.STORMNET.FunctionalLanguage.Function`

## GetFunction и PrimaryKey
При построении ограничений на [первичные ключи вычитываемых объектов (собственные ключи)](primary-keys-objects.html), стоит учитывать, что `SQLWhereLanguageDef` не обрабатывает константу "`PrimaryKey`". Вместо константы "`PrimaryKey`" надо использовать StormMainObjectKey (определена соответствующая константа).

'''__Неверно__''':
```cs
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "PrimaryKey"), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

'''__Верно__''':
```cs
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, SQLWhereLanguageDef.StormMainObjectKey), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

(((<msg type=note>Стоит отметить, что ограничение на [первичный ключ](primary-keys-objects.html) __мастера__ накладывается следующим образом:
```
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, Information.ExtractPropertyPath<СамОбъект>(x => x.СсылкаНаМастера)), "84F456C1-312F-30C0-A238-11E3FE68E852");
```
где "СсылкаНаМастера" - ссылка на мастера.</msg>)))



# Наложение ограничений на перечислимый тип
[Перечислимые типы](enumerations.html) хранятся в базе как строки. Соответственно, при конструировании описания переменной (VariableDef) необходимо использовать StringType. В качестве аргумента для сравнения рекомендуется использовать Caption объекта перечисления, получить Caption можно при помощи класса `EnumCaption`, который является частью `ICSSoft.STORMNET`

Рассмотрим на примере:


![](/images/pages/img/page/FunctionList/Pol.PNG)

 
Чтобы наложить ограничение на пол клиента, необходимо составить следующую функцию:

```

using ICSSoft.STORMNET;
...
var ld = SQLWhereLanguageDef.LanguageDef;
var onlyMenFunction = ld.GetFunction(ld.funcEQ, new VariableDef(ld.StringType, Information.ExtractPropertyPath<Клиент>(x => x.Пол)), EnumCaption.GetCaptionFor(tПол.Мужской));
```


# Наложение ограничений на компоненты даты
Чтобы наложить ограничение на часть даты (на год, месяц или день), можно воспользоваться функциями `DayPart`, `MonthPart` и `YearPart` для задания ограничений на компоненты даты.


## Пример
```

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



# Примеры использования
Далее будут приведены несколько примеров наложения ограничений:

## Наложение ограничений на строковую переменную
```


var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Личность), Личность.Views.ЛичностьE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.StringType, Information.ExtractPropertyPath<Личность>(x => x.Фамилия)), "Петров");
var клиентыФамилияПетров = DataServiceProvider.DataService.LoadObjects(lcs);
```



## Наложение ограничений на мастеровой объект (по ключу)
```

var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность)), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
var кредиты = DataServiceProvider.DataService.LoadObjects(lcs);
```


## Наложение ограничений на мастеровой объект (по полю мастера)
```

var langdef = ExternalLangDef.LanguageDef;
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = langdef.GetFunction(langdef.funcEQ,
	            new VariableDef(langdef.GuidType, Information.ExtractPropertyPath<Кредит>(x => x.Личность.Фамилия)), "Петров");
var кредиты = DataServiceProvider.DataService.LoadObjects(lcs);
```

(((<msg type=important>Убедитесь, что в представлении `КредитE` есть мастер `Личность` и его поле `Фамилия`, иначе произойдёт ошибка при выполнении запроса.</msg>)))


# Список функций
* [FuncNOT](func-n-o-t.html)
* [FuncIsNull](func-is-null.html)
* [FuncEQ](func-e-q.html)
* [FuncNEQ](func-n-e-q.html)
* [FuncG](compare-functions.html)
* [FuncGEQ](compare-functions.html)
* [FuncL](compare-functions.html)
* [FuncLEQ](compare-functions.html)
* [FuncIN](func-i-n.html)
* [FuncAND](func-a-n-d.html)
* [FuncOR](func-o-r.html)
* [FuncLike](func-like.html)
* [FuncBETWEEN](func-between.html)
* [funcSQL](func-s-q-l.html)
