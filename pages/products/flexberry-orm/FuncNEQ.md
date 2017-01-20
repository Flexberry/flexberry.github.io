---
title: FuncNEQ
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/func-n-e-q.html
folder: product--folder
lang: ru
---

### FuncEQ

FuncNEQ = Not Equal

Функция, аналогичная неравенству в SQL.

### Пример
Рассмотрим пример

![](/images/pages/img/Filters/FilterExDiagram.PNG)

### Задача

Вычитать все `Кредиты`, не относящиеся к определенному `Клиенту`.

### SQL

SQL-выражение выглядело бы следующим образом:

```
SELECT * FROM Кредит WHERE Клиент <> '{ID}'
```
Где {ID} - [первичный ключ](primary-keys-objects.html) `Клиента`


### [FunctionalLanguage](function-list.html)

```cs    Клиент клиент = new Клиент();
	SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
	Function lf = langdef.GetFunction(langdef.funcNEQ,
				new VariableDef(langdef.GuidType, "Клиент"), клиент.__PrimaryKey);
```


### Параметры GetFunction
Из примера видно, что функция GetFunction принимает первым параметром тип функции funcNEQ, а дальше принимает 2 объекта на сравнение их между собой. Первым посылается описание переменной (Variable Definition), по которому будут определяться объекты для сравнения; а вторым параметром - объект, с которым будет происходить сравнение.

__Примечание__: несмотря на то, что при построении ограничения на переменную типа `bool` [FuncEQ](func-e-q.html) позволяет не посылать второй параметр, FuncNEQ не позволяет этого сделать. Таким образом ```
 langdef.GetFunction(langdef.funcNEQ, new VariableDef(langdef.BoolType, "SomeBoolFlag")) 
``` выдаст ошибку.


### Пример
```
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;

LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Кредит), Кредит.Views.КредитE);	
Function lf = langdef.GetFunction(langdef.funcNEQ, 
new VariableDef(langdef.GuidType, "Клиент"), клиент.__PrimaryKey);
lcs.LimitFunction = lf;

var credits = DataServiceProvider.DataService.LoadObjects(lcs);```


# См. также
[Перечень функций](function-list.html)

[FuncEQ](func-e-q.html)


