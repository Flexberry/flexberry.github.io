---
title: Функции ограничения (Limit Function)
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Ограничения
toc: true
permalink: ru/limit-function.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Компоненты для фильтрации и ограничения выборки получаемых данных](limitation.html)
* '''Программная библиотека''': ICSSoft.STORMNET.FunctionalLanguage.dll, ExternalLangDef.dll.
* '''Предназначение''': Общее описание работы с функциями ограничения.
</td>
</tr></tbody></table></a>
</div>
# Функции ограничения (Limit Function)
Функции ограничения позволяют осуществлять фильтрацию и ограничения выборки данных и представляют собой специальный расширяемый язык ограничений, разработанный для [Flexberry ORM](flexberry-o-r-m.html).

Данный механизм лежит в основе функционирования [LINQProvider](l-i-n-q-provider.html), таким образом, [данные механизмы наложения ограничений тесно связаны](limitation.html).

Существует [возможность сериализовать функцию ограничения](limit-function--serialization.html). 

# "Построители" функций ограничений
[SQLWhereLanguageDef](function-list.html) - класс-построитель функций для наложения ограничений на вычитываемые объекты.

[ExternalLangDef](external-lang-def.html) - расширение языка ограничений для задания ограничений на зависимые объекты (детейлы).

Существует [возможность расширить реализованные языки ограничений](creation-function-when-using--language-def.html).

# Применение функций ограничения
Работа с функциями ограничений часто связана с вычиткой данных. При вычитке данных функция ограничений указывается в структуре [LoadingCustomizationStruct](loading-customization-struct.html).
