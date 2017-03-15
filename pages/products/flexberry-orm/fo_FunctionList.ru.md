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



