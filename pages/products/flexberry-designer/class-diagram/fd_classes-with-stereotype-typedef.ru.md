---
title: Синонимы типов (классы со стереотипом typedef) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fd_classes-with-stereotype-typedef.html
folder: products/flexberry-designer/
lang: ru
---

`Typedef` - [стереотип](fd_key-concepts-flexberry-designer.html), указывающий синоним типа. 

Назначение синонимов типов:
* Повышение уровня абстрации при моделировании.
* Точная настройка целевого типа языка, в который происходит генерация кода.

При генерации кода синонимы приводятся к базовым типам целевого языка (в код попадает базовый тип, а синоним не указывается никак).

Преобразование к целевому типу можно настроить в [настройках соответствующего модуля-генератора](fd_types-map.html).

На [диаграммах](fd_class-diagram.html) класс со [стереотипом](fd_key-concepts-flexberry-designer.html) Typedef не может быть связан никакими ассоциациями, указание атрибутов и методов не имеет смысла (не учитывается).
