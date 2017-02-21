---
title: Nullable-типы
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_nullable-types.html
folder: products/flexberry-orm/
lang: ru
---
# Nullable-типы
Nullable-типы являются расширением обычных типов, позволяющим хранить значение `null`. В отличии от скалярных типов, Nullable-типы позволяют пользователю не хранить никакого значения в поле скалярного типа.

''__Важно__: при проектировании информационной системы предпочтительнее использовать Nullable типы заместо обычных (т.е. к примеру NullableDateTime заместо DateTime)''

# Nullable-типы .Net
''__Важно__: корректная работа Nullable-типов .Net при объявлении через Flexberry гарантируется в версиях, выпущенных позднее 25.06.2013''

С некоторой версии .NET Framework стал поддерживать [Nullable-типы](http://msdn.microsoft.com/en-us/library/1t3y8s4s%28v=vs.110%29.aspx). Чтобы при генерации кода были использованы типы `System.Nullable<esc><T></esc>` (или '''T?''', что эквивалентно согласно [msdn](http://msdn.microsoft.com/en-us/library/1t3y8s4s%28v=vs.110%29.aspx)), необходимо [создать класс со стереотипом typedef](classes-with-stereotype--typedef.html). 

## Пример использования Nullable-типа
Например, если нужно использовать на [диаграмме классов](fd_class-diagram.html) тип "`bool?`", то можно:


1. создать класс NullBool [со стереотипом typedef](classes-with-stereotype--typedef.html);


2. на карте типов .Net CSharp (.NET CSharp -> Редактировать -> Карта типов) добавить строку вида: 

{| border="1" 
! Тип !! Отображается в
|-
| NullBool || System.Nullable<esc><System.Boolean></esc>
|-
| NullDateTime || System.Nullable<esc><System.DateTime></esc>
|}


3. на карте типов MSSQLServer Direct Generator (MSSQLServer Direct Generator -> Редактировать -> Карта типов) добавить строку вида: 

{| border="1" 
! Тип !! Отображается в
|-
| NullBool || BIT 
|-
| NullDateTime || DATETIME
|}


После этого становится возможным использовать на [диаграммах классов](class-diagram.html) тип `NullBool`, который после генерации будет работать как `bool?`.
----