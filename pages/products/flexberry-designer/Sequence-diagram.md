---
title: Диаграмма последовательности (Sequence diagram) 
sidebar: product--sidebar
keywords: Flexberry Designer, Public, Черновик статьи
toc: true
permalink: ru/sequence-diagram.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': UML-диаграмма последовательности.
</td>
</tr></tbody></table></a>
</div>
# Диаграмма последовательности (Sequence diagram)
Диаграмма последовательности - один из доступных [видов диаграмм](editing-diagram.html), поддерживаемых [Flexberry Designer](flexberry-designer.html).
'''Диаграммы последовательностей используются для уточнения диаграмм прецедентов''', более детального описания логики сценариев использования. Это отличное средство документирования проекта с точки зрения сценариев использования!

Диаграммы последовательностей обычно содержат '''объекты''', которые '''взаимодействуют в рамках сценария''', '''сообщения''', которыми они обмениваются, и '''возвращаемые результаты''', связанные с сообщениями. Впрочем, часто возвращаемые результаты обозначают лишь в том случае, если это не очевидно из контекста.

'''Объекты''' обозначаются прямоугольниками с подчеркнутыми именами (чтобы отличить их от классов).

'''Сообщения (вызовы методов)''' - линиями со стрелками.

'''Возвращаемые результаты''' - пунктирными линиями со стрелками.

Прямоугольники на вертикальных линиях под каждым из объектов показывают '''"время жизни" (фокус) объектов'''. Впрочем, довольно часто их не изображают на диаграмме, все это зависит от индивидуального стиля проектирования.

# Основные элементы диаграммы последовательности
На диаграмме последовательности можно отобразить следующие элементы нотации UML, доступные в панели элементов: 


{| border="1" 
! Элемент/Нотация !! Предназначение
|-
| ![](/images/pages/img/Введение в Flexberry/actor.jpg) || Участник (Actor);
|-
| ![](/images/pages/img/Введение в Flexberry/objectseq.jpg) || Объект (Object);
|-
| ![](/images/pages/img/Введение в Flexberry/activeobjectseq.jpg) || Активный объект (Active object);
|-
| ![](/images/pages/img/Введение в Flexberry/terminator.jpg) || Терминатор (Terminator);
|-
| ![](/images/pages/img/Введение в Flexberry/fwdnestedmsg.jpg) || Вызов процедуры (Procedure call);
|-
| ![](/images/pages/img/Введение в Flexberry/fwdmessage.jpg) || Сообщение (Flat message);
|-
| ![](/images/pages/img/Введение в Flexberry/fwdasyncmsg.jpg) || Асинхронное сообщение (Async message);
|-
| ![](/images/pages/img/Введение в Flexberry/dependency.jpg) || Сообщение с результатом (Return message);
|-
| ![](/images/pages/img/Введение в Flexberry/inscope.jpg) || Временной интервал (In scope);
|-
| ![](/images/pages/img/Введение в Flexberry/timeconstraint.jpg) || Временное ограничение (Time constraint);
|-
| ![](/images/pages/img/Введение в Flexberry/corner.jpg) || Точка изгиба связей (Point);
|-
| ![](/images/pages/img/Введение в Flexberry/note.jpg) || Комментарий (Note);
|-
| ![](/images/pages/img/Введение в Flexberry/noteconn.jpg) || Коннектор комментария (Note connector).
|}

