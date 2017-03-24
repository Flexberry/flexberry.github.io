---
title: Проверка уникальности введенных данных в бизнес-сервере
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Бизнес-серверы
toc: true
permalink: ru/fo_b-s-unique-data-check.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Бизнес-сервер](fo_business-logic.html)
* **Программная библиотека**: ICSSoft.STORMNET.Business.dll
* **Предназначение**: Приведён способ проверки уникальности данных в [бизнес-сервере](fo_business-logic.html).
## Проверка уникальности введенных данных

Если возникает необходимость проверки уникальности введенных данных, необходимо в методе `OnUpdate` [бизнес-сервера](fo_business--servers--wrapper--business--facade.html) производить вычитку данных и сравнивать данные из базы с введенными данными.

Необходимо учитывать, что объект может быть как только что создан, так и уже находиться в базе. Если объект уже есть в базе, то он попадет в список вычитанных данных и возникнет конфликт.

## Пример
![](/images/pages/products/flexberry-orm/Templates.PNG)

Предположим, что мы хотим проверять уникальность введенного номера кредитной карты.

* Добавим бизнес-сервер КредитнаяКартаБС и выставим у объекта КредитнаяКарта ссылку на этот [бизнес-сервер](fo_business--servers--wrapper--business--facade.html).
* В методе [`OnUpdate`](fo_b-s-example.html) необходимо [вычитать](fo_sql-query.html) все КредитныеКарты клиента и проверить, есть ли среди них карты с таким номером

```cs
var ds = (SQLDataService)DataServiceProvider.DataService;
var кредитныеКарты = ds.Query<КредитнаяКарта>(КредитнаяКарта.Views.КредитнаяКартаE)
                     .Count(k => k.Клиент.__PrimaryKey == UpdatedObject.Клиент.__PrimaryKey 
                              && k.Номер == UpdatedObject.Номер);
```

Либо так:

```cs
LoadingCustomizationStruct lcs = new LoadingCustomizationStruct(null);
lcs.LoadingTypes = new Type[] { typeof(КредитнаяКарта) };
lcs.View = КредитнаяКарта.Views.КредитнаяКартаE;
var ld = SQLWhereLanguageDef.LanguageDef;
lcs.LimitFunction = ld.GetFunction(ld.funcAND,
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "Клиент"), UpdatedObject.Клиент.__PrimaryKey),
ld.GetFunction(ld.funcEQ, new VariableDef(ld.NumericType, "Номер"), UpdatedObject.Номер));
var кредитныеКарты = DataServiceProvider.DataService.GetObjectsCount(lcs);
```

Таким образом, в переменной `кредитныеКарты` будет количество искомых кредитных карт. Если такие карты уже есть, это число будет неравным нулю. Однако, если мы не создаем Карту, а обновляем, то нам вернется единица, так как в базе лежит информация об обновляемой кредитной карте.

Доработаем условие, добавив сравнение с текущей картой:

```cs
var ds = (SQLDataService)DataServiceProvider.DataService;
var кредитныеКарты = ds.Query<КредитнаяКарта>(КредитнаяКарта.Views.КредитнаяКартаE)
                     .Count(k => k.Клиент.__PrimaryKey == UpdatedObject.Клиент.__PrimaryKey 
                              && k.Номер == UpdatedObject.Номер
                              && k.__primaryKey != UpdatedObject.__PrimaryKey);
```

либо:

```cs
LoadingCustomizationStruct lcs = new LoadingCustomizationStruct(null);
lcs.LoadingTypes = new Type[] { typeof(КредитнаяКарта) };
lcs.View = КредитнаяКарта.Views.КредитнаяКартаE;
var ld = SQLWhereLanguageDef.LanguageDef;
lcs.LimitFunction = ld.GetFunction(ld.funcAND,
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "Клиент"), UpdatedObject.Клиент.__PrimaryKey),
ld.GetFunction(ld.funcEQ, new VariableDef(ld.NumericType, "Номер"), UpdatedObject.Номер),
ld.GetFunction(ld.funcNEQ, new VariableDef(ld.GuidType, "STORMainObjectKey"), UpdatedObject.__PrimaryKey));
var кредитныеКарты = DataServiceProvider.DataService.GetObjectsCount(lcs);
```

Таким образом, мы проверяем уникальность номера создаваемой Кредитной карты.

