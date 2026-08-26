---
title: Диаграмма активностей (Activity diagram)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, диаграммы, диаграмма активностей, дорожки
summary: Основные сведения о диаграмме активностей и ее элементах
toc: true
permalink: ru/fd_activity-diagram.html
lang: ru
---

**Диаграмма активностей (видов деятельности)** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).
Она, как и [диаграмма состояний](fd_statechart-diagram.html), **отражает динамические аспекты поведения системы**. По существу, эта диаграмма представляет собой блок-схему, которая наглядно показывает, как поток управления переходит от одной деятельности к другой.  

![Пример](/images/pages/products/flexberry-designer/diagram/activity-diagram.png)

Активности на диаграмме "разбросаны" по беговым дорожкам, каждая из которых соответствует поведению одного из объектов (например, клиента, менеджера, веб-сервера, сервера БД и т.п.). Благодаря этому легко определить, каким из объектов выполняется каждая из активностей. Дорожка - часть области диаграммы деятельности, на которой отображаются только **те активности, за которые отвечает конкретный объект**. Предназначены дорожки для разбиения диаграммы в соответствии **с распределением ответственности за действия**. Имя дорожки может означать роль или объект, которому она соответствует.

## Основные элементы диаграммы активностей

На диаграмме активностей можно отобразить следующие элементы нотации UML, доступные в панели элементов:

Элемент/Нотация | Предназначение
:------------------------------------------------------------------|:--------------------------------------------
![Пример](/images/pages/products/flexberry-designer/diagram/decision.jpg) | Принятие решения (Decision)
![Пример](/images/pages/products/flexberry-designer/diagram/activeobject1.jpg) | Активное состояние (Active state)
![Пример](/images/pages/products/flexberry-designer/diagram/startstate.jpg) | Начальное состояние (Start state)
![Пример](/images/pages/products/flexberry-designer/diagram/finalstate.jpg) | Конечное состояние (Final state)
![Пример](/images/pages/products/flexberry-designer/diagram/complextransition.jpg)![Пример](/images/pages/products/flexberry-designer/diagram/complextransition_ver.jpg) | Синхронизатор/разветвитель (Complex transition)
![Пример](/images/pages/products/flexberry-designer/diagram/objinstate.jpg) | Объект в состоянии (Object in state)
![Пример](/images/pages/products/flexberry-designer/diagram/signalreceipt-l.jpg)![Пример](/images/pages/products/flexberry-designer/diagram/signalreceipt-r.jpg) | Получение сигнала (Signal receipt)
![Пример](/images/pages/products/flexberry-designer/diagram/signalsend-l.jpg)![Пример](/images/pages/products/flexberry-designer/diagram/signalsend-r.jpg) | Отправка сигнала (Signal sending)
![Пример](/images/pages/products/flexberry-designer/diagram/transition.jpg) | Переход (Transition) (Object in state)
![Пример](/images/pages/products/flexberry-designer/diagram/objectflow.jpg) | Изменение объекта (Object flow)
![Пример](/images/pages/products/flexberry-designer/diagram/partition.jpg) | Раздел (Partition)
![Пример](/images/pages/products/flexberry-designer/diagram/swlane-h.jpg)![Пример](/images/pages/products/flexberry-designer/diagram/swlane-v.jpg) | Разделитель плавательных дорожек (Swimlane separator)
![Пример](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![Пример](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![Пример](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
