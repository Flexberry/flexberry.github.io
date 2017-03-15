---
title: FuncIsNull
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-is-null.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* **Программная библиотека**: ICSSoft.STORMNET.FunctionalLanguage.dll.
* **Предназначение**: Общее описание работы функции FuncIsNull в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

FuncIsNull = is null

Функция, аналогичная проверке на null в SQL

## Пример

Рассмотрим пример

![](/images/pages/products/flexberry-orm/func-e-q/FilterExDiagram.PNG)

## Задача

Вычитать все `Кредиты`, у которых `ДатаВыдачи` равняется `null`

## SQL

SQL-выражение выглядело бы следующим образом:

```sql
 SELECT * FROM Кредит WHERE ДатаВыдачи is null 
```

## [FunctionalLanguage](fo_function-list.html)

```cs    
	Клиент клиент = new Клиент();
	SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
	Function lf = langdef.GetFunction(langdef.funcIsNull, new VariableDef(langdef.DateTimeType, "ДатаВыдачи"));
```


## Параметры GetFunction

Для FuncIsNull необходим один параметр - описание переменной (Variable Definition), которую надо проверить на `null`

## Пример

```cs
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;

LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Кредит), Кредит.Views.КредитE);	
Function lf = langdef.GetFunction(langdef.funcIsNull, new VariableDef(langdef.DateTimeType, "ДатаВыдачи"));
lcs.LimitFunction = lf;

var credits = DataServiceProvider.DataService.LoadObjects(lcs);```
```


## См. также
[Перечень функций](fo_function-list.html)

----