---
title: Функции ограничения
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Определение и предназначение построителей ограничений
toc: true
permalink: ru/fo_limit-function.html
lang: ru
---

Функции ограничения позволяют осуществлять фильтрацию и ограничения выборки данных и представляют собой специальный расширяемый язык ограничений, разработанный для [Flexberry ORM](fo_flexberry-orm.html).

Данный механизм лежит в основе функционирования [LINQProvider](fo_linq-provider.html), таким образом, [данные механизмы наложения ограничений тесно связаны](fo_limitation.html).

Существует [возможность сериализовать функцию ограничения](fo_limit-function-serialization.html). 

## "Построители" функций ограничений

[SQLWhereLanguageDef](fo_function-list.html) - класс-построитель функций для наложения ограничений на вычитываемые объекты.

[ExternalLangDef](fo_external-lang-def.html) - расширение языка ограничений для задания ограничений на зависимые объекты (детейлы).

[FunctionBuilder](fo_function-builder.html) - класс-обертка над ExternalLangDef с менее многословным синтаксисом для построения функций ограничений на вычитываемые объекты.

[FunctionBuilder примеры](fo_function-builder-examples.html) - более детальные примеры по рефактору `LangDef.GetFunction`.

Существует [возможность расширить реализованные языки ограничений](fo_using-languagedef.html).

## Применение функций ограничения

Работа с функциями ограничений часто связана с вычиткой данных. При вычитке данных функция ограничений указывается в структуре [LoadingCustomizationStruct](fo_loading-customization-struct.html).
