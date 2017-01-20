---
title: Настройка Flexberry Designer для Nullable-типов
sidebar: product--sidebar
keywords: Flexberry Designer, How to (page), Public
toc: true
permalink: ru/flexberry-designer-and-nullable.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Предназначение''': Описан порядок настройки [Flexberry Designer](flexberry-designer.html) для поддержки [Nullable-типов](nullable-types.html).
</td>
</tr></tbody></table></a>
</div>
# Настройка Flexberry Designer для .Net Nullable-типов
Рассмотрим порядок настройки [Flexberry Designer](flexberry-designer.html) для поддержки [Nullable-типов](nullable-types.html) на следующем примере.

'''Нужно использовать на [диаграмме классов](class-diagram.html) тип "`bool?`"'''

1. создать класс NullBool [со стереотипом typedef](classes-with-stereotype--typedef.html);

2. на [карте типов](types-map.html) генератора объектов добавить строку вида: 

{| border="1" 
! Тип !! Отображается в
|-
| NullBool || System.Nullable<esc><System.Boolean></esc>
|-
| NullDateTime || System.Nullable<esc><System.DateTime></esc>
|}


3. на [карте типов](types-map.html) генератора базы данных добавить строку вида: 

{| border="1" 
! Тип !! Отображается в
|-
| NullBool || BIT 
|-
| NullDateTime || DATETIME
|}


После этого становится возможным использовать на [диаграммах классов](class-diagram.html) тип `NullBool`, который после генерации будет работать как `bool?`.
