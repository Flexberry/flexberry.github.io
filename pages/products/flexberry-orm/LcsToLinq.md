---
title: Преобразование функции ограничения в linq-ограничение
sidebar: product--sidebar
keywords: Flexberry ASP-NET, Flexberry ORM, Ограничения
toc: true
permalink: ru/lcs-to-linq.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Расширенный редактор ограничений в WEB](advanced-limit-editor.html)
* '''Программная библиотека''': IIS.Flex.CaseberryInteraction.dll
* '''Предназначение''': Существует возможность осуществить конвертацию [функции ограничения] в выражение для [LinqProvider](limit-function.html).
</td>
</tr></tbody></table></a>
</div>

# Преобразование функции ограничения в linq-ограничение
Существуют методы, позволяющие производить конвертацию из [функции ограничения](limit-function.html) в выражение для [LINQProvider](l-i-n-q-provider.html) и [наоборот](limitation.html).

'''Получение Linq-выражения из функции ограничения'''

`lf` - функция ограничения.

`expression` - Linq-выражение.
```cs
var limitResolvingViews = new List<View>() { Кредит.Views.C__КредитE }; 
Expression expression = new LcsInterpretator().Interpret(typeof(Кредит), lf, "x", limitResolvingViews);
```

Здесь "`limitResolvingViews`" - [представления](view-definition.html), аналогичные [resolvingViews в LINQProvider](l-i-n-q-provider.html), необходимые для корректного преобразования [Функции ограничения (Limit Function)](limit-function.html) в [Linq-ограничение](l-i-n-q-provider.html) (в первую очередь особенности связаны с обработкой [детейлов](detail-associations-and-their-properties.html)). К "`limitResolvingViews`" предъявляются следующие требования:
* При задании ограничения всеобщности на детейлы ([ExistExact](exist--exist-exact--exist-all--exist-all-exact.html)) в используемое представление детейла должна быть включена ссылка на агрегатора.
* В основное представление агрегатора должны быть включены все детейлы, которые могут встретиться в ограничении.
