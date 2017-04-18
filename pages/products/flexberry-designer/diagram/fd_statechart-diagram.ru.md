---
title: Диаграмма состояний (Statechart diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_statechart-diagram.html
lang: ru
---

**Диаграмма состояний** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_landing_page.html).

![](/images/pages/products/flexberry-designer/diagram/statechart-diagram.png)

Она показывает, как объект переходит из одного состояния в другое. Диаграммы состояний служат для моделирования динамических аспектов системы. Данная диаграмма полезна при моделировании жизненного цикла объекта. 
От других диаграмм диаграмма состояний отличается тем, что описывает процесс изменения состояний только одного экземпляра определенного класса - одного объекта, причем объекта реактивного, то есть объекта, поведение которого характеризуется его реакцией на внешние события.

## Основные элементы диаграммы состояний

На диаграмме состояний можно отобразить следующие элементы нотации UML, доступные в панели элементов:

Элемент/Нотация | Предназначение
:-----------------------------------|:----------------------------------------------------------
![](/images/pages/products/flexberry-designer/diagram/instance.jpg) | Класс (Class)
![](/images/pages/products/flexberry-designer/diagram/state.jpg) | Состояние (State)
![](/images/pages/products/flexberry-designer/diagram/stateex.jpg) | Состояние (StateEx)
![](/images/pages/products/flexberry-designer/diagram/statecomposite.jpg) | Составное состояние (Composite state)
![](/images/pages/products/flexberry-designer/diagram/concstate.jpg) | Разделитель (Concurrent state)
![](/images/pages/products/flexberry-designer/diagram/history.jpg) | История (History)
![](/images/pages/products/flexberry-designer/diagram/historydeep.jpg) | Глубокая история (Deep history)
![](/images/pages/products/flexberry-designer/diagram/startstate.jpg) | Начальное состояние (Start state)
![](/images/pages/products/flexberry-designer/diagram/finalstate.jpg) | Конечное состояние (Final state)
![](/images/pages/products/flexberry-designer/diagram/complextransition.jpg)![](/images/pages/products/flexberry-designer/diagram/complextransition_ver.jpg) | Синхронизатор/разветвитель (Complex transition)
![](/images/pages/products/flexberry-designer/diagram/transition.jpg) | Переход (Transition)
![](/images/pages/products/flexberry-designer/diagram/eventmessage.jpg) | Сообщение (Event message)
![](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
