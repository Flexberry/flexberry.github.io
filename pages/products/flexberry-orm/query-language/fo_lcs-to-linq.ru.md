---
title: Преобразование функции ограничения в linq-ограничение
sidebar: flexberry-orm_sidebar
keywords: Flexberry ASPNET, Flexberry ORM, Ограничения
summary: Методы и пример преобразования
toc: true
permalink: ru/fo_lcs-to-linq.html
lang: ru
---

Существуют методы, позволяющие производить конвертацию из [функции ограничения](fo_limit-function.html) в выражение для [LINQProvider](fo_linq-provider.html) и [наоборот](fo_limitation.html).

* `Получение Linq-выражения из функции ограничения`
* `lf` - функция ограничения.
* `expression` - Linq-выражение.

```csharp
var limitResolvingViews = new List<View>() { Кредит.Views.C__КредитE }; 
Expression expression = new LcsInterpretator().Interpret(typeof(Кредит), lf, "x", limitResolvingViews);
```

Здесь `limitResolvingViews` - [представления](fd_view-definition.html), аналогичные [resolvingViews в LINQProvider](fo_linq-provider.html), необходимые для корректного преобразования [Функции ограничения (Limit Function)](fo_limit-function.html) в [Linq-ограничение](fo_linq-provider.html) (в первую очередь особенности связаны с обработкой [детейлов](fo_detail-associations-properties.html)). К `limitResolvingViews` предъявляются следующие требования:

* При задании ограничения всеобщности на детейлы ([ExistExact](fo_exist-details.html)) в используемое представление детейла должна быть включена ссылка на агрегатора.
* В основное представление агрегатора должны быть включены все детейлы, которые могут встретиться в ограничении.
