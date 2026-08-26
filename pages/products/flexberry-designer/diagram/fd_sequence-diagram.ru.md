---
title: Диаграмма последовательности (Sequence diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, диаграммы, диаграмма последовательности, объекты, сценарий, время жизни, актор, процедура
summary: Основные сведения о диаграмме последовательности и ее элементах
toc: true
permalink: ru/fd_sequence-diagram.html
lang: ru
---

**Диаграмма последовательности** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).

![Пример](/images/pages/products/flexberry-designer/diagram/sequence-diagram.png)

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
![Пример](/images/pages/products/flexberry-designer/diagram/actor.jpg) | Участник (Actor)
![Пример](/images/pages/products/flexberry-designer/diagram/objectseq.jpg) | Объект (Object)
![Пример](/images/pages/products/flexberry-designer/diagram/activeobjectseq.jpg) | Активный объект (Active object)
![Пример](/images/pages/products/flexberry-designer/diagram/terminator.jpg) | Терминатор (Terminator)
![Пример](/images/pages/products/flexberry-designer/diagram/fwdnestedmsg.jpg) | Вызов процедуры (Procedure call)
![Пример](/images/pages/products/flexberry-designer/diagram/fwdmessage.jpg) | Сообщение (Flat message)
![Пример](/images/pages/products/flexberry-designer/diagram/fwdasyncmsg.jpg) | Асинхронное сообщение (Async message)
![Пример](/images/pages/products/flexberry-designer/diagram/dependency.jpg) | Сообщение с результатом (Return message)
![Пример](/images/pages/products/flexberry-designer/diagram/inscope.jpg) | Временной интервал (In scope)
![Пример](/images/pages/products/flexberry-designer/diagram/timeconstraint.jpg) | Временное ограничение (Time constraint)
![Пример](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![Пример](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![Пример](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
