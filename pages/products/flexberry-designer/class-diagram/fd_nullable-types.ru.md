---
title: Nullable-типы
sidebar: flexberry-designer_sidebar
keywords: Flexberry Desinger, nullable, nullable-типы, типы данных
summary: Поддержка nullable-типов Flexberry ORM и .NET
toc: true
permalink: ru/fd_nullable-types.html
lang: ru
---

Nullable-типы являются расширением обычных типов, позволяющим хранить значение `null` (то есть в отличии от стандартных типов, Nullable-типы позволяют пользователю не хранить никакого значения в поле скалярного типа). 

{% include note.html content="При проектировании информационной системы необходимо учитывать разницу между скалярными и nullable-типами, выбирая более подходящий для конкретной ситуации тип." %}

## Стандартные Nullable-типы во Flexberry ORM

[Flexberry ORM](fo_flexberry-orm.html) [предоставляет следующие Nullable-типы](fo_flexberry-orm-types.html) (расположены в сборке ICSSoft.STORMNET.UserDataTypes.dll):

* `NullableInt` - целочисленный тип + `null`. Принимает все значения, которые принимает `int`, а также может принимать значение null.
* `NullableDateTime` - тип для хранения даты и времени + `null`. Может хранить все значения, которые хранит `DateTime`, а также null.
* `NullableDecimal` - тип для хранения дробных чисел + `null`

{% include note.html content="Реализованные во [Flexberry ORM](fo_flexberry-orm.html) Nullable-типы на настоящий момент устарели. Предпочтительнее использовать Nullable-типы, предоставляемые .Net.</msg>
" %}

## Nullable-типы .Net

.NET Framework поддерживает [Nullable-типы]([http://msdn.microsoft.com/en-us/library/1t3y8s4s%28v=vs.110%29.aspx). [Flexberry ORM](fo_flexberry-orm.html) [поддерживает Nullable-типы .Net](fo_flexberry-orm-types.html).

Чтобы при генерации кода были использованы типы `System.Nullable<esc><T></esc>` (или `T?`, что эквивалентно согласно [msdn](http://msdn.microsoft.com/en-us/library/1t3y8s4s%28v=vs.110%29.aspx)), необходимо [произвести особую настройку](fd_create-nullable.html).
