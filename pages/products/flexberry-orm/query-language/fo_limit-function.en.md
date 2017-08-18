---
title: Функции ограничения (Limit Function)
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: en/fo_limit-function.html
folder: products/flexberry-orm/
lang: en
---

Функции ограничения позволяют осуществлять фильтрацию и ограничения выборки данных и представляют собой специальный расширяемый язык ограничений, разработанный для [Flexberry ORM](fo_flexberry-orm.html).

Данный механизм лежит в основе функционирования [LINQProvider](fo_linq-provider.html), таким образом, [данные механизмы наложения ограничений тесно связаны](fo_limitation.html).

Существует [возможность сериализовать функцию ограничения](fo_limit-function-serialization.html). 

## "Построители" функций ограничений

[SQLWhereLanguageDef](fo_function-list.html) - класс-построитель функций для наложения ограничений на вычитываемые объекты.

[ExternalLangDef](fo_external-lang-def.html) - расширение языка ограничений для задания ограничений на зависимые объекты (детейлы).

Существует [возможность расширить реализованные языки ограничений](fo_creation-function-when-using-language-def.html).

## Применение функций ограничения

Работа с функциями ограничений часто связана с вычиткой данных. При вычитке данных функция ограничений указывается в структуре [LoadingCustomizationStruct](fo_loading-customization-struct.html).
