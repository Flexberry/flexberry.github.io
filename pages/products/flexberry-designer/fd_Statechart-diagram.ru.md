---
title: Диаграмма состояний (Statechart diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public, Черновик статьи
toc: true
permalink: ru/fd_statechart-diagram.html
folder: products/flexberry-designer/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': UML-диаграмма состояний.
</td>
</tr></tbody></table></a>
</div>
# Диаграмма состояний (Statechart diagram) 
Диаграмма состояний - один из доступных [видов диаграмм](editing-diagram.html), поддерживаемых [Flexberry Designer](flexberry-designer.html).
Она показывает, как объект переходит из одного состояния в другое. Диаграммы состояний служат для моделирования динамических аспектов системы. Данная диаграмма полезна при моделировании жизненного цикла объекта. 
От других диаграмм диаграмма состояний отличается тем, что описывает процесс изменения состояний только одного экземпляра определенного класса - одного объекта, причем объекта реактивного, то есть объекта, поведение которого характеризуется его реакцией на внешние события.

# Основные элементы диаграммы состояний
На диаграмме состояний можно отобразить следующие элементы нотации UML, доступные в панели элементов:


{| border="1" 
! Элемент/Нотация !! Предназначение
|-
| ![](/images/pages/img/Введение в Flexberry/instance.jpg) || Класс (Class);
|-
| ![](/images/pages/img/Введение в Flexberry/state.jpg) || Состояние (State);
|-
| ![](/images/pages/img/Введение в Flexberry/stateex.jpg) || Состояние (StateEx);
|-
| ![](/images/pages/img/Введение в Flexberry/statecomposite.jpg) || Составное состояние (Composite state);
|-
| ![](/images/pages/img/Введение в Flexberry/Concstate.jpg) || Разделитель (Concurrent state);
|-
| ![](/images/pages/img/Введение в Flexberry/history.jpg) || История (History);
|-
| ![](/images/pages/img/Введение в Flexberry/historydeep.jpg) || Глубокая история (Deep history);
|-
| ![](/images/pages/img/Введение в Flexberry/startstate.jpg) || Начальное состояние (Start state);
|-
| ![](/images/pages/img/Введение в Flexberry/finalstate.jpg) || Конечное состояние (Final state);
|-
| ![](/images/pages/img/Введение в Flexberry/complextransition.jpg)![](/images/pages/img/Введение в Flexberry/complextransition_ver.jpg) || Синхронизатор/разветвитель (Complex transition);
|-
| ![](/images/pages/img/Введение в Flexberry/transition.jpg) || Переход (Transition);
|-
| ![](/images/pages/img/Введение в Flexberry/eventmessage.jpg) || Сообщение (Event message);
|-
| ![](/images/pages/img/Введение в Flexberry/corner.jpg) || Точка изгиба связей (Point);
|-
| ![](/images/pages/img/Введение в Flexberry/note.jpg) || Комментарий (Note);
|-
| ![](/images/pages/img/Введение в Flexberry/noteconn.jpg) || Коннектор комментария (Note connector).
|}
