---
title: FuncIN
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_func-i-n.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Компоненты для фильтрации и ограничения выборки получаемых данных](fo_limitation.html)
* **Программная библиотека**: ICSSoft.STORMNET.FunctionalLanguage.dll.
* **Предназначение**: Общее описание работы функции FuncIN в построителе [функций ограничения](fo_limit-function.html) [SQLWhereLanguageDef](fo_function-list.html).

FuncIN = In

Функция, аналогичная проверке на вхождение в SQL.

## Пример

Рассмотрим пример

![](/images/pages/products/flexberry-orm/func-in/FilterExDiagram.PNG)

## Задача

Вычитать все `Кредиты`, выданные особым клиентам, список ключей которых нам известен.

## SQL

SQL-выражение выглядело бы следующим образом:

```sql
SELECT * FROM Кредиты WHERE Клиент IN ('{IDList}')
```

Где {IDList} - список [первичных ключей](fo_primary-keys-objects.html) искомых `Клиентов`

## [FunctionalLanguage](fo_function-list.html)

```cs       
		List<Клиент> клиенты = new List<Клиент>();
        SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
        List<object> clientKeys = new List<object>();
        clientKeys.Add(new VariableDef(langdef.GuidType, "Клиент"));

        foreach (var клиент in клиенты)
            clientKeys.Add(клиент.__PrimaryKey);

        Function lf = langdef.GetFunction(langdef.funcIN, clientKeys.ToArray());
```


## Параметры GetFunction

Из примера видно, что функция GetFunction принимает один параметр: массив, состоящий из определения переменной (Variable Def) и объектов, среди которых будет производиться поиск.

## Пример

Более сложный пример использования FuncIN вкупе с другими функциями-ограничителями

```cs 
lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Награждение), "НаграждениеВСпискеНаграждений2L");
SQLWhereLanguageDef ldef = SQLWhereLanguageDef.LanguageDef;

// Создадим список объектов, по которым будем ограничивать FuncIN
List<object> clientKeys = new List<object>();

// Добавим первым параметром VariableDef объекта, который мы будем ограничивать функцией FuncIN
clientKeys.Add(new VariableDef(ldef.GuidType, "Гражданин.УИК"));

// Добавим все ключи, по которым будем ограничивать FuncIN
for (int i = 0; i < dobjs.Length; i++)
{
	clientKeys.Add(dobjs[i].__PrimaryKey);
}

lcs.LimitFunction = ldef.GetFunction(ldef.funcAND,

// Добавим FuncIN в качестве ограничения
ldef.GetFunction(ldef.funcIN, clientKeys.ToArray()),
ldef.GetFunction(ldef.funcEQ,
	new VariableDef(ldef.DateTimeType, "Гражданин.ДатаРождения"),
	fDataObject.ДатаРождения),
ldef.GetFunction(ldef.funcNEQ,
	new VariableDef(ldef.GuidType, "Гражданин"),
	fDataObject.__PrimaryKey),
ldef.GetFunction(ldef.funcEQ,
	new VariableDef(ldef.NumericType, "Актуально"),
	1),
ldef.GetFunction(ldef.funcNOT,
	ldef.GetFunction(ldef.funcIsNull,
		new VariableDef(ldef.StringType, "Гражданин.Комментарий"))),
ldef.GetFunction(ldef.funcEQ,
	new VariableDef(ldef.StringType, "СписокНаграждений.Награда.Наименование"),
	nagr.СписокНаграждений.Награда.Наименование));
```



## См. также

[Перечень функций](fo_function-list.html)
