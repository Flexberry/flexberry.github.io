---
title: Диаграмма активностей (Activity diagram)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_activity-diagram.html
folder: products/flexberry-designer/diagram/
lang: ru
---

**Диаграмма активностей (видов деятельности)** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).
Она, как и [диаграмма состояний](fd_statechart-diagram.html), **отражает динамические аспекты поведения системы**. По существу, эта диаграмма представляет собой блок-схему, которая наглядно показывает, как поток управления переходит от одной деятельности к другой.  

![](/images/pages/products/flexberry-designer/diagram/activity-diagram.png)

Активности на диаграмме "разбросаны" по беговым дорожкам, каждая из которых соответствует поведению одного из объектов (например, клиента, менеджера, веб-сервера, сервера БД и т.п.). Благодаря этому легко определить, каким из объектов выполняется каждая из активностей. Дорожка - часть области диаграммы деятельности, на которой отображаются только **те активности, за которые отвечает конкретный объект**. Предназначены дорожки для разбиения диаграммы в соответствии **с распределением ответственности за действия**. Имя дорожки может означать роль или объект, которому она соответствует. 

## Основные элементы диаграммы активностей

На диаграмме активностей можно отобразить следующие элементы нотации UML, доступные в панели элементов:

Элемент/Нотация | Предназначение
:------------------------------------------------------------------|:--------------------------------------------
![](/images/pages/products/flexberry-designer/diagram/decision.jpg) | Принятие решения (Decision)
![](/images/pages/products/flexberry-designer/diagram/activeobject1.jpg) | Активное состояние (Active state)
![](/images/pages/products/flexberry-designer/diagram/startstate.jpg) | Начальное состояние (Start state)
![](/images/pages/products/flexberry-designer/diagram/finalstate.jpg) | Конечное состояние (Final state)
![](/images/pages/products/flexberry-designer/diagram/complextransition.jpg)![](/images/pages/products/flexberry-designer/diagram/complextransition_ver.jpg) | Синхронизатор/разветвитель (Complex transition)
![](/images/pages/products/flexberry-designer/diagram/objinstate.jpg) | Объект в состоянии (Object in state)
![](/images/pages/products/flexberry-designer/diagram/signalreceipt-l.jpg)![](/images/pages/products/flexberry-designer/diagram/signalreceipt-r.jpg) | Получение сигнала (Signal receipt)
![](/images/pages/products/flexberry-designer/diagram/signalsend-l.jpg)![](/images/pages/products/flexberry-designer/diagram/signalsend-r.jpg) | Отправка сигнала (Signal sending)
![](/images/pages/products/flexberry-designer/diagram/transition.jpg) | Переход (Transition) (Object in state)
![](/images/pages/products/flexberry-designer/diagram/objectflow.jpg) | Изменение объекта (Object flow)
![](/images/pages/products/flexberry-designer/diagram/partition.jpg) | Раздел (Partition)
![](/images/pages/products/flexberry-designer/diagram/swlane-h.jpg)![](/images/pages/products/flexberry-designer/diagram/swlane-v.jpg) | Разделитель плавательных дорожек (Swimlane separator)
![](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
