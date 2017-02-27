---
title: Настройка Flexberry Designer для Nullable-типов
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_create-nullable.html
folder: products/flexberry-designer/class-diagram/
lang: ru
---

Порядок настройки [Flexberry Designer](fd_flexberry-designer.html) для поддержки [Nullable-типов](fo_nullable-types.html) на примере создания типа `bool?`.

Требуется использовать на [диаграмме классов](fd_class-diagram.html) тип `bool?`. Для этого необходимо:
1. создать класс NullBool [со стереотипом typedef](fd_typedef.html)
2. на [карте типов](fd_types-map.html) генератора объектов добавить строку вида: 

Тип | Отображается в
:---------------|:---------------------------------------
NullBool | System.Nullable<esc><System.Boolean></esc>

3. на [карте типов](fd_types-map.html) генератора базы данных добавить строку вида: 

Тип | Отображается в
:---------------|:-----------
NullBool | BIT 

После этого становится возможным использовать на [диаграммах классов](fd_class-diagram.html) тип `NullBool`, который после генерации будет работать как `bool?`.
