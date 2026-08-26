---
title: Диаграмма состояний (Statechart diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, диаграммы, диаграмма состояний, состояние, история
summary: Основные сведения о диаграмме состояний и ее элементах
toc: true
permalink: ru/fd_statechart-diagram.html
lang: ru
---

**Диаграмма состояний** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).

![Пример](/images/pages/products/flexberry-designer/diagram/statechart-diagram.png)

Она показывает, как объект переходит из одного состояния в другое. Диаграммы состояний служат для моделирования динамических аспектов системы. Данная диаграмма полезна при моделировании жизненного цикла объекта.
От других диаграмм диаграмма состояний отличается тем, что описывает процесс изменения состояний только одного экземпляра определенного класса - одного объекта, причем объекта реактивного, то есть объекта, поведение которого характеризуется его реакцией на внешние события.

## Основные элементы диаграммы состояний

На диаграмме состояний можно отобразить следующие элементы нотации UML, доступные в панели элементов:

Элемент/Нотация | Предназначение
:-----------------------------------|:----------------------------------------------------------
![Пример](/images/pages/products/flexberry-designer/diagram/instance.jpg) | Класс (Class)
![Пример](/images/pages/products/flexberry-designer/diagram/state.jpg) | Состояние (State)
![Пример](/images/pages/products/flexberry-designer/diagram/stateex.jpg) | Состояние (StateEx)
![Пример](/images/pages/products/flexberry-designer/diagram/statecomposite.jpg) | Составное состояние (Composite state)
![Пример](/images/pages/products/flexberry-designer/diagram/concstate.jpg) | Разделитель (Concurrent state)
![Пример](/images/pages/products/flexberry-designer/diagram/history.jpg) | История (History)
![Пример](/images/pages/products/flexberry-designer/diagram/historydeep.jpg) | Глубокая история (Deep history)
![Пример](/images/pages/products/flexberry-designer/diagram/startstate.jpg) | Начальное состояние (Start state)
![Пример](/images/pages/products/flexberry-designer/diagram/finalstate.jpg) | Конечное состояние (Final state)
![Пример](/images/pages/products/flexberry-designer/diagram/complextransition.jpg)![Пример](/images/pages/products/flexberry-designer/diagram/complextransition_ver.jpg) | Синхронизатор/разветвитель (Complex transition)
![Пример](/images/pages/products/flexberry-designer/diagram/transition.jpg) | Переход (Transition)
![Пример](/images/pages/products/flexberry-designer/diagram/eventmessage.jpg) | Сообщение (Event message)
![Пример](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![Пример](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![Пример](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
