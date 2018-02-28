---
title: Описание переменных при построении функций ограничения
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Примеры использования VariableDef и DetailVariableDef
toc: true
permalink: ru/fo_variable-def.html
lang: ru
---

При построении ограничений с помощью метода `GetFunction` параметром этому методу в зависимости от типа применяемой функции может передаваться описание переменной:

* при задании ограничения с помощью [SQLWhereLanguageDef](fo_function-list.html) используется класс `VariableDef`,
* при наложении ограничения на детейл с помощью [ExternalLangDef](fo_external-lang-def.html) для описания переменной-детейла применяется класс `DetailVariableDef`.

## VariableDef

Класс `VariableDef` служит для определения переменной в ограничении (обычно указывает на атрибут в [объекте](fo_data-object.html)). Используется вместе с [SQLWhereLanguageDef](fo_function-list.html).

Определен в пространстве имен: `ICSSoft.STORMNET.FunctionalLanguage`.

Сборка: `ICSSoft.STORMNET.FunctionalLanguage.dll`.

Самый распространённый конструктор, который используется при построении ограничений:

```csharp
public VariableDef(ObjectType objType, string objStringedView)
```

Ему передаются параметры:

* `objType` - ObjectType-тип переменной (например, `langdef.StringType`),
* `objStringedView` - имя свойства объекта, по которому планируется ограничение.

Примеры конструирования `VariableDef` при задании ограничений доступны в статье [SQLWhereLanguageDef](fo_function-list.html).

### VariableDef для PrimaryKey

При построении ограничений на [первичные ключи](fo_primary-keys-objects.html) вычитываемых объектов (собственные ключи) стоит учитывать, что [SQLWhereLanguageDef](fo_function-list.html) не обрабатывает константу `PrimaryKey`. Вместо константы `PrimaryKey` лучше использовать `StormMainObjectKey` (определена соответствующая константа).

__Неверно__:

```csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "PrimaryKey"), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

__Верно__:

```csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, SQLWhereLanguageDef.StormMainObjectKey), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

Стоит отметить, что ограничение на [первичный ключ](fo_primary-keys-objects.html) __мастера__ накладывается следующим образом:

``` csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, Information.ExtractPropertyPath<СамОбъект>(x => x.СсылкаНаМастера)), "84F456C1-312F-30C0-A238-11E3FE68E852");
```

где "СсылкаНаМастера" - ссылка на мастера.

## DetailVariableDef

Класс `DetailVariableDef` служит для определения переменной ограничения, предназначенного для описания детейлов. Используется совместно с [ExternalLangDef](fo_external-lang-def.html).

Определен в пространстве имен: `ICSSoft.STORMNET.Windows.Forms`.
Сборка: `ExternalLangDef.dll`.

При конструировании `DetailVariableDef` следует задать параметры:

* `Type` - ObjectType-тип (можно получить `ldef.GetObjectType("Details")`),
* `View` - имя представления детейла,
* `ConnectMasterProp` - путь от детейла (см. описание далее),
* `OwnerConnectProp` - к детейлу от объекта (см. описание далее).

Примеры конструирования `DetailVariableDef` при задании ограничений доступны в статьях:

* [Ограничения на детейлы](fo_exist-details.html),
* [Ограничение на детейлы с использованием свойств агрегатора](fo_limit-details.html).

### Задание OwnerConnectProp и ConnectMasterProp

Часто возникают сложности при определении, что указывать в качестве `ConnectMasterProp` и `OwnerConnectProp`.

`ConnectMasterProp` указывается путь от детейла, а в `OwnerConnectProp` - к детейлу от объекта. 

Связка происходит следующим образом: **Детейл.ConnectMasterProp = Объект.OwnerConnectProp**.

Cтоит отметить, что свойство `OwnerConnectProp` определяет, к какому объекту относятся детейлы. Если свойство не указано (т.е. указан `null`), то связка происходит по [первичному ключу](fo_primary-keys-objects.html)(т.е. по `StormMainObjectKey`).

#### Примеры использования

##### Пример1

![](/images/pages/products/flexberry-orm/query-language/diagramm.JPG)

Необходимо отфильтровать УдостоверяющиеДокументы для ЗаявленияАбитуриента. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = Личность, `ConnectMasterProp` = Личность.

##### Пример2

![](/images/pages/products/flexberry-orm/query-language/diagramm-2.PNG)

Необходимо отфильтровать ОбразовательныеДокументы для СтрокиРекомендательногоСписка. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = ЗаявлениеАбитуриента.Личность, `ConnectMasterProp` = Личность.

Необходимо отфильтровать ОбразовательныеДокументы для СтрокиРекомендательногоСписка. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = ЗаявлениеАбитуриента.Личность, `ConnectMasterProp` = Личность.

### Задание ограничений на псевдодетейлы

Например, сущности "Клиент" и "Кредит" связаны представленным на изображении образом.

![](/images/pages/products/flexberry-orm/query-language/pseudo-details.png)

Нужно ограничить клиентов, задав при этом ограничение на ссылающихся на них кредиты.
 
Очевидно, что с точки зрения хранения данной объектной модели в БД в соответствии с [существующими правилами](fo_storing-data-objects.html), нет различия между агрегацией и простой ассоциацией. Поэтому запросы, ограничивающие выборку по критериям из дочерней таблицы, не отличаются в случае агрегации и ассоциации. Следовательно для построения ограничения в случае всевдодетейла необходимо использовать `DetailVariableDef` совместно с [ExternalLangDef](fo_external-lang-def.html). 
 
Если в описанном примере надо выбрать клиентов, у которых есть кредиты на срок более 15 лет, код будет выглядеть следующим образом:

``` csharp
ExternalLangDef ldef = ExternalLangDef.LanguageDef;
LoadingCustomizationStruct lcsДолгосрочныеКлиенты = LoadingCustomizationStruct.GetSimpleStruct(typeof(Клиент), "КлиентE");
lcsДолгосрочныеКлиенты.LoadingTypes = new[) { typeof(Клиент) };
var view = Information.GetView("КредитE", typeof(Кредит));
var dvd = new DetailVariableDef
{
    ConnectMasterPorp = "Клиент",
    OwnerConnectProp = new[) { SQLWhereLanguageDef.StormMainObjectKey },
    View = view,
    Type = ldef.GetObjectType("Details")
};
lcsДолгосрочныеКлиенты.LimitFunction = ldef.GetFunction(ldef.funcExist, dvd,
                                                        ldef.GetFunction(ldef.funcGEQ, 
                                                        new VariableDef(ldef.GuidType, "СрокКредита"), 15));
ICSSoft.STORMNET.DataObject[) dobjsДолгосрочныеКлиенты = DataServiceProvider.DataService.LoadObjects(lcsДолгосрочныеКлиенты);
```
