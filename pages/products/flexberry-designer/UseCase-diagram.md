---
title: Диаграмма вариантов использования (UseCase diagram)
sidebar: product--sidebar
keywords: Flexberry Designer, Public, Черновик статьи
toc: true
permalink: ru/use-case-diagram.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': UML-диаграмма вариантов использования.
</td>
</tr></tbody></table></a>
</div>
# Диаграмма вариантов использования (UseCase diagram)
Диаграмма вариантов использования - один из доступных [видов диаграмм](editing-diagram.html), поддерживаемых [Flexberry Designer](flexberry-designer.html).

Основные элементы диаграммы - участник (actor) и прецедент (вариант).

'''Участник''' - это множество логически связанных ролей, исполняемых при взаимодействии с прецедентами или сущностями (система, подсистема или класс). Участником может быть человек или другая система, подсистема или класс, которые представляют нечто вне сущности. Графически участник изображается "человечком".

'''Прецедент (use case)''' - описание множества последовательных событий (включая варианты), выполняемых системой, которые приводят к наблюдаемому участником результату. Прецедент представляет поведение сущности, описывая взаимодействие между участниками и системой. Прецедент не показывает, "как" достигается некоторый результат, а только "что" именно выполняется. Прецеденты обозначаются очень простым образом - в виде эллипса, внутри которого указано его название. 

# Основные элементы диаграммы вариантов использования
На диаграмме вариантов использования можно отобразить следующие элементы нотации UML, доступные в панели элементов: 

{| border="1" 
! Элемент/Нотация !! Предназначение
|-
| ![](/images/pages/img/Введение в Flexberry/actor.jpg) || Участник (Actor);
|-
| ![](/images/pages/img/Введение в Flexberry/usecase.jpg) || Вариант (Use case);
|-
| ![](/images/pages/img/Введение в Flexberry/boundary.jpg) || Граница (Boundary);
|-
| ![](/images/pages/img/Введение в Flexberry/assoc.jpg) || Ненаправленная ассоциация (Undirected communication association);
|-
| ![](/images/pages/img/Введение в Flexberry/dircomm.jpg) || Направленная ассоциация (Directed communication association);
|-
| ![](/images/pages/img/Введение в Flexberry/inheritance.jpg) || Обобщение (Generalization);
|-
| ![](/images/pages/img/Введение в Flexberry/objectflow.jpg) || Зависимость (Dependency);
|-
| ![](/images/pages/img/Введение в Flexberry/corner.jpg) || Точка изгиба связей (Point);
|-
| ![](/images/pages/img/Введение в Flexberry/note.jpg) || Комментарий (Note);
|-
| ![](/images/pages/img/Введение в Flexberry/noteconn.jpg) || Коннектор комментария (Note connector).
|}
