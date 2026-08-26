---
title: Диаграмма вариантов использования (UseCase diagram)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, диаграммы, диаграмма вариантов использования, участник, актор, прецедент
summary: Основные сведения о диаграмме вариантов использования и ее элементах
toc: true
permalink: ru/fd_use-case-diagram.html
lang: ru
---

Диаграмма вариантов использования - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).

![Пример](/images/pages/products/flexberry-designer/diagram/use-case-diagram.png)

Основные элементы диаграммы - участник (actor) и прецедент (вариант).

**Участник** - это множество логически связанных ролей, исполняемых при взаимодействии с прецедентами или сущностями (система, подсистема или класс). Участником может быть человек или другая система, подсистема или класс, которые представляют нечто вне сущности. Графически участник изображается "человечком".

**Прецедент (use case)** - описание множества последовательных событий (включая варианты), выполняемых системой, которые приводят к наблюдаемому участником результату. Прецедент представляет поведение сущности, описывая взаимодействие между участниками и системой. Прецедент не показывает, "как" достигается некоторый результат, а только "что" именно выполняется. Прецеденты обозначаются очень простым образом - в виде эллипса, внутри которого указано его название.

## Основные элементы диаграммы вариантов использования

На диаграмме вариантов использования можно отобразить следующие элементы нотации UML, доступные в панели элементов:

Элемент/Нотация | Предназначение
:---------------------|:-----------------------------------------------------
![Пример](/images/pages/products/flexberry-designer/diagram/actor.jpg) | Участник (Actor)
![Пример](/images/pages/products/flexberry-designer/diagram/usecase.jpg) | Вариант (Use case)
![Пример](/images/pages/products/flexberry-designer/diagram/boundary.jpg) | Граница (Boundary
![Пример](/images/pages/products/flexberry-designer/diagram/assoc.jpg) | Ненаправленная ассоциация (Undirected communication association)
![Пример](/images/pages/products/flexberry-designer/diagram/dircomm.jpg) | Направленная ассоциация (Directed communication association)
![Пример](/images/pages/products/flexberry-designer/diagram/inheritance.jpg) | Обобщение (Generalization)
![Пример](/images/pages/products/flexberry-designer/diagram/objectflow.jpg) | Зависимость (Dependency)
![Пример](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![Пример](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![Пример](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
