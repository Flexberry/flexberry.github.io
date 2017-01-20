---
title: Синонимы типов (классы со стереотипом typedef) 
sidebar: product--sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/classes-with-stereotype--typedef.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': Классы со [стереотипом](key-concepts-flexberry-designer.html) Typedef на [диаграмме классов](class-diagram.html) представляют собой синонимы типа.
</td>
</tr></tbody></table></a>
</div>
# Синонимы типов (классы со стереотипом typedef) 
`Typedef` - [стереотип](key-concepts-flexberry-designer.html), указывающий синоним типа. 

Назначение синонимов типов:
* Повышение уровня абстрации при моделировании.
* Точная настройка целевого типа языка, в который происходит генерация кода.

При генерации кода синонимы приводятся к базовым типам целевого языка (в код попадает базовый тип, а синоним не указывается никак).

Преобразование к целевому типу можно настроить в [настройках соответствующего модуля-генератора](types-map.html).

На [диаграммах](class-diagram.html) класс со [стереотипом](key-concepts-flexberry-designer.html) Typedef не может быть связан никакими ассоциациями, указание атрибутов и методов не имеет смысла (не учитывается).
