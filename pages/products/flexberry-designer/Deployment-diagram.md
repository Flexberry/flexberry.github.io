---
title: Диаграмма развёртывания (Deployment diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public, Черновик статьи
toc: true
permalink: ru/fd_deployment-diagram.html
folder: products/flexberry-designer/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': UML-диаграмма развёртывания.
</td>
</tr></tbody></table></a>
</div>
# Диаграмма развёртывания (Deployment diagram) 
Диаграмма развёртывания - один из доступных [видов диаграмм](editing-diagram.html), поддерживаемых [Flexberry Designer](flexberry-designer.html).
Корпоративные приложения часто требуют для своей работы некоторой ИТ-инфраструктуры, хранят информацию в базах данных, расположенных где-то на серверах компании, вызывают веб-сервисы, используют общие ресурсы и т. д. В таких случаях полезно иметь графическое представление инфраструктуры, на которую будет развернуто приложение. Для этого и нужны диаграммы развертывания, которые иногда называют диаграммами размещения.

Такие диаграммы есть смысл строить только для аппаратно-программных систем, тогда как UML позволяет строить модели любых систем, не обязательно компьютерных.
Польза диаграмм развертывания:
# графическое представление ИТ-инфраструктуры может помочь более рационально распределить компоненты системы по узлам сети, от чего зависит в том числе и производительность системы,
# такая диаграмма может помочь решить множество вспомогательных задач, связанных, например, с обеспечением безопасности.

Диаграмма развертывания показывает топологию системы и распределение компонентов системы по ее узлам, а также соединения - маршруты передачи информации между аппаратными узлами. Это единственная диаграмма, на которой применяются "трехмерные" обозначения: узлы системы обозначаются кубиками. Все остальные обозначения в UML - плоские фигуры.

# Основные элементы диаграммы развёртывания
На диаграмме развёртывания можно отобразить следующие элементы нотации UML, доступные в панели элементов: 


{| border="1" 
! Элемент/Нотация !! Предназначение
|-
| ![](/images/pages/img/Введение в Flexberry/component.jpg) || Компонент (Component);
|-
| ![](/images/pages/img/Введение в Flexberry/componentinstance.jpg) || Экземпляр компонента (Component instance);
|-
| ![](/images/pages/img/Введение в Flexberry/interface.jpg) || Интерфейс (Interface);
|-
| ![](/images/pages/img/Введение в Flexberry/node.jpg) || Узел (Node);
|-
| ![](/images/pages/img/Введение в Flexberry/nodeinstance.jpg) || Экземпляр узла (Node instance);
|-
| ![](/images/pages/img/Введение в Flexberry/instance.jpg) || Объект (Object);
|-
| ![](/images/pages/img/Введение в Flexberry/activeobject1.jpg)  || Активный объект (Active object);
|-
| ![](/images/pages/img/Введение в Flexberry/dependency1.jpg) || Зависимость (Dependency);
|-
| ![](/images/pages/img/Введение в Flexberry/Connection.jpg) || Связь (Connection);
|-
| ![](/images/pages/img/Введение в Flexberry/corner.jpg) || Точка изгиба связей (Point);
|-
| ![](/images/pages/img/Введение в Flexberry/note.jpg) || Комментарий (Note);
|-
| ![](/images/pages/img/Введение в Flexberry/noteconn.jpg) || Коннектор комментария (Note connector).
|}
