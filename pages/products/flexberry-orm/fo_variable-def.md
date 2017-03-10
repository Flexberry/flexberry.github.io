---
title: Описание переменных при построении функций ограничения
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/fo_variable-def.html
---

При построении ограничений с помощью метода `GetFunction` параметром этому методу в зависимости от типа применяемой функции может передаваться описание переменной:

* при задании ограничения с помощью `[SQLWhereLanguageDef](fo_function-list.html)` используется класс `VariableDef`,
* при наложении ограничения на детейл с помощью `[ExternalLangDef](fo_external-lang-def.html)` для описания переменной-детейла применяется класс `DetailVariableDef`.

Рассмотрим далее данные классы.

## VariableDef

Класс `VariableDef` служит для определения переменной в ограничении (обычно указывает на атрибут в [объекте](fo_dataobject.html)). Используется вместе с `[SQLWhereLanguageDef](fo_function-list.html)`.

Определен в пространстве имен: `ICSSoft.STORMNET.FunctionalLanguage`.

Сборка: `ICSSoft.STORMNET.FunctionalLanguage.dll`.

Самый распространённый конструктор, который используется при построении ограничений:

```csharp
public VariableDef(ObjectType objType, string objStringedView)
```

Ему передаются параметры:

* `objType` - ObjectType-тип переменной (например, `langdef.StringType`),
* `objStringedView` - имя свойства объекта, по которому собираемся строить ограничение.

Примеры конструирования `VariableDef` при задании ограничений доступны в статье `[SQLWhereLanguageDef](fo_function-list.html)`.

### VariableDef для PrimaryKey

При построении ограничений на [первичные ключи вычитываемых объектов (собственные ключи)](fo_primary-keys-objects.html) стоит учитывать, что `[SQLWhereLanguageDef](fo_function-list.html)` не обрабатывает константу "`PrimaryKey`". Вместо константы "`PrimaryKey`" надо использовать `StormMainObjectKey` (определена соответствующая константа).

**__Неверно__**:

```csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "PrimaryKey"), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```

**__Верно__**:

```csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, SQLWhereLanguageDef.StormMainObjectKey), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
```
<div markdown="span" class="note"><i class="note">Стоит отметить, что ограничение на [первичный ключ](fo_primary-keys-objects.html) __мастера__ накладывается следующим образом:
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, Information.ExtractPropertyPath<СамОбъект>(x => x.СсылкаНаМастера)), "84F456C1-312F-30C0-A238-11E3FE68E852");
где "СсылкаНаМастера" - ссылка на мастера.</div>

## DetailVariableDef

Класс `DetailVariableDef` служит для определения переменной ограничения, предназначенного для описания детейлов. Используется совместно с `[ExternalLangDef](fo_external-lang-def.html)`.

Определен в пространстве имен: `ICSSoft.STORMNET.Windows.Forms`.

Сборка: `ExternalLangDef.dll`.

При конструировании `DetailVariableDef` следует задать параметры:

* `Type` - ObjectType-тип (можно получить `ldef.GetObjectType("Details")`),
* `View` - имя представления детейла,
* `ConnectMasterProp` - путь от детейла (см. описание далее),
* `OwnerConnectProp` - к детейлу от объекта (см. описание далее).

Примеры конструирования `DetailVariableDef` при задании ограничений доступны в статьях:

* [ExternalLangDef - ограничения на детейл (funcExist, funcExistExact, funcExistAll, funcExistAllExact)](fo_exist-exist-exact-exist-all-exist-all-exact.html),
* [ExternalLangDef - ограничение на два детейла (funcExistDetails)](fo_exist-detals.html),
* [Ограничение на детейлы с использованием свойств агрегатора](fo_limit-details-by-agregators-prop.html).

### Задание `OwnerConnectProp` и `ConnectMasterProp`

Часто возникают сложности при определении, что указывать в качестве `ConnectMasterProp` и `OwnerConnectProp`.

`ConnectMasterProp` указывается путь от детейла, а в `OwnerConnectProp` - к детейлу от объекта. 

Связка происходит следующим образом: **Детейл.ConnectMasterProp = Объект.OwnerConnectProp**.

Cтоит отметить, что свойство `OwnerConnectProp` определяет, к какому объекту относятся детейлы. Если свойство не указано (т.е. указан `null`), то связка происходит по [первичному ключу](fo_primary-keys-objects.html)(т.е. по `StormMainObjectKey`).

Продемонстрируем задание `OwnerConnectProp` и `ConnectMasterProp` на примерах:

**Пример1:**

![](/images/pages/img/page/VariableDef/Diagramm.JPG)
D:/PMasalkinGit/flexberry.github.io/images/pages/products/flexberry-orm/variable-def/diagramm.JPG

Необходимо отфильтровать УдостоверяющиеДокументы для ЗаявленияАбитуриента. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = Личность, `ConnectMasterProp` = Личность.

**Пример2:**

![Изображение](/images/img/Ограничения/Examples/Diagramm2.PNG)

Необходимо отфильтровать ОбразовательныеДокументы для СтрокиРекомендательногоСписка. 
При задании `DetailVariableDef` следует указать: `OwnerConnectProp` = ЗаявлениеАбитуриента.Личность, `ConnectMasterProp` = Личность.

