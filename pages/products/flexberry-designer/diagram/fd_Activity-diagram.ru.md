---
title: Диаграмма активностей (Activity diagram)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public, Черновик статьи
toc: true
permalink: ru/fd_activity-diagram.html
folder: products/flexberry-designer/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': UML-диаграмма активностей.
</td>
</tr></tbody></table></a>
</div>
# Диаграмма активностей (Activity diagram)
'''Диаграмма активностей (видов деятельности)''' - один из доступных [видов диаграмм](editing-diagram.html), поддерживаемых [Flexberry Designer](flexberry-designer.html).
Она, как и [диаграмма состояний](statechart-diagram.html), '''отражает динамические аспекты поведения системы'''. По существу, эта диаграмма представляет собой блок-схему, которая наглядно показывает, как поток управления переходит от одной деятельности к другой.
Активности на диаграмме "разбросаны" по беговым дорожкам, каждая из которых соответствует поведению одного из объектов (например, клиента, менеджера, веб-сервера, сервера БД и т.п.). Благодаря этому легко определить, каким из объектов выполняется каждая из активностей. Дорожка - часть области диаграммы деятельности, на которой отображаются только '''те активности''', '''за которые отвечает конкретный объект'''. Предназначены дорожки для разбиения диаграммы в соответствии '''с распределением ответственности за действия'''. Имя дорожки может означать роль или объект, которому она соответствует. 

# Основные элементы диаграммы активностей
На диаграмме активностей можно отобразить следующие элементы нотации UML, доступные в панели элементов:


{| border="1" 
! Элемент/Нотация !! Предназначение
|-
| ![](/images/pages/img/Введение в Flexberry/decision.jpg) || Принятие решения (Decision)
|-
| ![](/images/pages/img/Введение в Flexberry/activeobject1.jpg) || Активное состояние (Active state)
|-
| ![](/images/pages/img/Введение в Flexberry/startstate.jpg) || Начальное состояние (Start state)
|-
| ![](/images/pages/img/Введение в Flexberry/finalstate.jpg) || Конечное состояние (Final state)
|-
| ![](/images/pages/img/Введение в Flexberry/complextransition.jpg)![](/images/pages/img/Введение в Flexberry/complextransition_ver.jpg) || Синхронизатор/разветвитель (Complex transition)
|-
| ![](/images/pages/img/Введение в Flexberry/objinstate.jpg) || Объект в состоянии (Object in state)
|-
| ![](/images/pages/img/Введение в Flexberry/signalreceipt_l.jpg)![](/images/pages/img/Введение в Flexberry/signalreceipt_r.jpg) || Получение сигнала (Signal receipt)
|-
| ![](/images/pages/img/Введение в Flexberry/signalsend_l.jpg)![](/images/pages/img/Введение в Flexberry/signalsend_r.jpg) || Отправка сигнала (Signal sending)
|-
| ![](/images/pages/img/Введение в Flexberry/transition.jpg) || Переход (Transition) (Object in state)
|-
| ![](/images/pages/img/Введение в Flexberry/objectflow.jpg) || Изменение объекта (Object flow)
|-
| ![](/images/pages/img/Введение в Flexberry/partition.jpg) || Раздел (Partition)
|-
| ![](/images/pages/img/Введение в Flexberry/swlane_h.jpg)![](/images/pages/img/Введение в Flexberry/swlane_V.jpg) || Разделитель плавательных дорожек (Swimlane separator)
|-
| ![](/images/pages/img/Введение в Flexberry/corner.jpg) || Точка изгиба связей (Point)
|-
| ![](/images/pages/img/Введение в Flexberry/note.jpg) || Комментарий (Note)
|-
| ![](/images/pages/img/Введение в Flexberry/noteconn.jpg) || Коннектор комментария (Note connector)
|}
