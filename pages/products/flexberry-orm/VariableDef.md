---
title: Описание переменных при построении функций ограничения
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/variable-def.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* '''Программная библиотека''': ICSSoft.STORMNET.FunctionalLanguage.dll,ExternalLangDef.dll
* '''Предназначение''': Описаны классы `VariableDef` и `DetailVariableDef`, используемые для задания переменных при конструировании [функции ограничения](limit-function.html).
</td>
</tr></tbody></table></a>
</div>

При построении ограничений с помощью метода `GetFunction` параметром этому методу в зависимости от типа применяемой функции может передаваться описание переменной:
* при задании ограничения с помощью `[SQLWhereLanguageDef](function-list.html)` используется класс `VariableDef`,
* при наложении ограничения на детейл с помощью `[ExternalLangDef](external-lang-def.html)` для описания переменной-детейла применяется класс `DetailVariableDef`.

Рассмотрим далее данные классы.

# VariableDef
Класс `VariableDef` служит для определения переменной в ограничении (обычно указывает на атрибут в [объекте](dataobject.html)). Используется вместе с `[SQLWhereLanguageDef](function-list.html)`.

Определен в пространстве имен: `ICSSoft.STORMNET.FunctionalLanguage`.

Сборка: `ICSSoft.STORMNET.FunctionalLanguage.dll`.

Самый распространённый конструктор, который используется при построении ограничений:
```cs
public VariableDef(ObjectType objType, string objStringedView) ```

Ему передаются параметры:
* `objType` - ObjectType-тип переменной (например, `langdef.StringType`),
* `objStringedView` - имя свойства объекта, по которому собираемся строить ограничение.

Примеры конструирования `VariableDef` при задании ограничений доступны в статье `[SQLWhereLanguageDef](function-list.html)`.

## VariableDef для PrimaryKey
При построении ограничений на [первичные ключи вычитываемых объектов (собственные ключи)](primary-keys-objects.html) стоит учитывать, что `[SQLWhereLanguageDef](function-list.html)` не обрабатывает константу "`PrimaryKey`". Вместо константы "`PrimaryKey`" надо использовать `StormMainObjectKey` (определена соответствующая константа).

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
# DetailVariableDef
Класс `DetailVariableDef` служит для определения переменной ограничения, предназначенного для описания детейлов. Используется совместно с `[ExternalLangDef](external-lang-def.html)`.

Определен в пространстве имен: `ICSSoft.STORMNET.Windows.Forms`.

Сборка: `ExternalLangDef.dll`.

При конструировании `DetailVariableDef` следует задать параметры:
* `Type` - ObjectType-тип (можно получить `ldef.GetObjectType("Details")`),
* `View` - имя представления детейла,
* `ConnectMasterProp` - путь от детейла (см. описание далее),
* `OwnerConnectProp` - к детейлу от объекта (см. описание далее).

Примеры конструирования `DetailVariableDef` при задании ограничений доступны в статьях:
* [ExternalLangDef - ограничения на детейл (funcExist, funcExistExact, funcExistAll, funcExistAllExact)](exist--exist-exact--exist-all--exist-all-exact.html),
* [ExternalLangDef - ограничение на два детейла (funcExistDetails)](exist-detals.html),
* [Ограничение на детейлы с использованием свойств агрегатора](limit-details-by-agregators-prop.html).

## Задание `OwnerConnectProp` и `ConnectMasterProp`
Часто возникают сложности при определении, что указывать в качестве `ConnectMasterProp` и `OwnerConnectProp`.

`ConnectMasterProp` указывается путь от детейла, а в `OwnerConnectProp` - к детейлу от объекта. 

Связка происходит следующим образом: '''Детейл.ConnectMasterProp = Объект.OwnerConnectProp'''.

Cтоит отметить, что свойство `OwnerConnectProp` определяет, к какому объекту относятся детейлы. Если свойство не указано (т.е. указан `null`), то связка происходит по [первичному ключу](primary-keys-objects.html)(т.е. по `StormMainObjectKey`).

Продемонстрируем задание `OwnerConnectProp` и `ConnectMasterProp` на примерах:

'''Пример1:'''

![](/images/pages/img/page/VariableDef/Diagramm.JPG)

Необходимо отфильтровать УдостоверяющиеДокументы для ЗаявленияАбитуриента. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = Личность, `ConnectMasterProp` = Личность.

'''Пример2:'''

![Изображение](/images/img/Ограничения/Examples/Diagramm2.PNG)

Необходимо отфильтровать ОбразовательныеДокументы для СтрокиРекомендательногоСписка. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = ЗаявлениеАбитуриента.Личность, `ConnectMasterProp` = Личность.

