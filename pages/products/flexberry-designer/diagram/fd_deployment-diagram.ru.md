---
title: Диаграмма развёртывания (Deployment diagram) 
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, диаграммы, диаграмма развёртывания, сервер, инфраструктура
summary: Основные сведения о диаграмме развёртывания и ее элементах
toc: true
permalink: ru/fd_deployment-diagram.html
lang: ru
---

**Диаграмма развёртывания** - один из доступных [видов диаграмм](fd_editing-diagram.html), поддерживаемых [Flexberry Designer](fd_flexberry-designer.html).  
Корпоративные приложения часто требуют для своей работы некоторой ИТ-инфраструктуры, хранят информацию в базах данных, расположенных где-то на серверах компании, вызывают веб-сервисы, используют общие ресурсы и т. д. В таких случаях полезно иметь графическое представление инфраструктуры, на которую будет развернуто приложение. Для этого и нужны диаграммы развёртывания, которые иногда называют диаграммами размещения.

![Пример](/images/pages/products/flexberry-designer/diagram/deployment-diagram.png)

Такие диаграммы есть смысл строить только для аппаратно-программных систем, тогда как UML позволяет строить модели любых систем, не обязательно компьютерных.

### Польза диаграмм развёртывания

1. Графическое представление ИТ-инфраструктуры может помочь более рационально распределить компоненты системы по узлам сети, от чего зависит в том числе и производительность системы.
2. Такая диаграмма может помочь решить множество вспомогательных задач, связанных, например, с обеспечением безопасности.

Диаграмма развёртывания показывает топологию системы и распределение компонентов системы по ее узлам, а также соединения - маршруты передачи информации между аппаратными узлами. Это единственная диаграмма, на которой применяются "трехмерные" обозначения: узлы системы обозначаются кубиками. Все остальные обозначения в UML - плоские фигуры.

## Основные элементы диаграммы развёртывания

На диаграмме развёртывания можно отобразить следующие элементы нотации UML, доступные в панели элементов:

Элемент/Нотация | Предназначение
:--------------------------------|:----------------------------------------------------------
![Пример](/images/pages/products/flexberry-designer/diagram/component.jpg) | Компонент (Component)
![Пример](/images/pages/products/flexberry-designer/diagram/componentinstance.jpg) | Экземпляр компонента (Component instance)
![Пример](/images/pages/products/flexberry-designer/diagram/interface.jpg) | Интерфейс (Interface)
![Пример](/images/pages/products/flexberry-designer/diagram/node.jpg) | Узел (Node)
![Пример](/images/pages/products/flexberry-designer/diagram/nodeinstance.jpg) | Экземпляр узла (Node instance)
![Пример](/images/pages/products/flexberry-designer/diagram/instance.jpg) | Объект (Object)
![Пример](/images/pages/products/flexberry-designer/diagram/activeobject1.jpg)  | Активный объект (Active object)
![Пример](/images/pages/products/flexberry-designer/diagram/dependency1.jpg) | Зависимость (Dependency)
![Пример](/images/pages/products/flexberry-designer/diagram/connection.jpg) | Связь (Connection)
![Пример](/images/pages/products/flexberry-designer/diagram/corner.jpg) | Точка изгиба связей (Point)
![Пример](/images/pages/products/flexberry-designer/diagram/note.jpg) | Комментарий (Note)
![Пример](/images/pages/products/flexberry-designer/diagram/noteconn.jpg) | Коннектор комментария (Note connector)
