---
title: Диаграмма последовательности (Sequence diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_sequence-diagram.html
folder: products/flexberry-designer/diagram/
lang: ru
---

**Диаграмма последовательности** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).

![](/images/pages/products/flexberry-designer/diagram/sequence-diagram.png)

**Диаграммы последовательностей используются для уточнения диаграмм прецедентов**, более детального описания логики сценариев использования. Это отличное средство документирования проекта с точки зрения сценариев использования!

Диаграммы последовательностей обычно содержат **объекты**, которые **взаимодействуют в рамках сценария**, **сообщения**, которыми они обмениваются, и **возвращаемые результаты**, связанные с сообщениями. Впрочем, часто возвращаемые результаты обозначают лишь в том случае, если это не очевидно из контекста.

**Объекты** обозначаются прямоугольниками с подчеркнутыми именами (чтобы отличить их от классов).

**Сообщения (вызовы методов)** - линиями со стрелками.

**Возвращаемые результаты** - пунктирными линиями со стрелками.

Прямоугольники на вертикальных линиях под каждым из объектов показывают **"время жизни" (фокус) объектов**. Впрочем, довольно часто их не изображают на диаграмме, все это зависит от индивидуального стиля проектирования.

## Основные элементы диаграммы последовательности

На диаграмме последовательности можно отобразить следующие элементы нотации UML, доступные в панели элементов: 

Элемент/Нотация | Предназначение
:-----------------------------------------|:-------------------------------------------------------
![](/images/pages/products/flexberry-designer/diagram/actor.jpg) | Участник (Actor)
![](/images/pages/products/flexberry-designer/diagram/objectseq.jpg) | Объект (Object)
![](/images/pages/products/flexberry-designer/diagram/activeobjectseq.jpg) | Активный объект (Active object)
![](/images/pages/products/flexberry-designer/diagram/terminator.jpg) | Терминатор (Terminator)
![](/images/pages/products/flexberry-designer/diagram/fwdnestedmsg.jpg) | Вызов процедуры (Procedure call)
![](/images/pages/products/flexberry-designer/diagram/fwdmessage.jpg) | Сообщение (Flat message)
![](/images/pages/products/flexberry-designer/diagram/fwdasyncmsg.jpg) | Асинхронное сообщение (Async message)
![](/images/pages/products/flexberry-designer/diagram/dependency.jpg) | Сообщение с результатом (Return message)
![](/images/pages/products/flexberry-designer/diagram/inscope.jpg) | Временной интервал (In scope)
![](/images/pages/products/flexberry-designer/diagram/timeconstraint.jpg) | Временное ограничение (Time constraint)
![](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
