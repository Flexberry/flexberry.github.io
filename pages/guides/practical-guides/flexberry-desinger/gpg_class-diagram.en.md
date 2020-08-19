---
title: Build class diagrams
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_class-diagram.html
lang: en
autotranslated: true
hash: fb17c612bd1c08fc2b19991313a1269c7b19ee204257c63c4e023df700ee972f
---

Once defined the functional requirements for the system and its boundaries, should be subject `проанализировать область` to build [the class diagram](fd_class-diagram.html).

## Brief theoretical data on class diagrams

`Диаграмма классов` `типы defines the classes of the system and various kinds of static связи` that exist between them. The diagrams classes are represented as attributes of classes, operations of classes and the restrictions imposed on the relationships between classes. The view and interpretation of the class diagram essentially depends on the viewpoint (abstraction layer): classes to represent domain entities (in the analysis) or elements of software systems (in the processes of design and implementation).

## The main elements of class diagrams

![](/images/pages/guides/flexberry-designer/class-diagram-elements.png)

The main elements are `классы` and `связи` between them. Classes are characterized by using `атрибутов` and `операций`.

`Атрибуты` describe the properties of objects of the class. Most of the objects in the class get their individuality from differences in their attributes and relationships with other objects. However, objects with identical attribute values and relationships. I.e. the individuality of objects is determined by the fact of their existence, and not to differences in their properties. The attribute name must be unique within a class. An attribute name may be followed by its type and default value.

`Операция` is a function or transformation. The operation can have parameters and return values.

__Relations:__
* Association
* aggregation
* inheritance.

`Ассоциация` (`association`) – represents relationships between instances of classes.
Each `конец ассоциации` has `кратностью` (synonym – power orig. — multiplicity), which shows `сколько объектов` located with the corresponding end of the Association, `может to participate in this отношении`. In the example in figure each `Товар` has skol ugodno `Записей in накладной`, but each `Запись in накладной` necessarily Odin `Товар`. In the General case, multiplicity can be specified in any set.
The Association can be named. As `имени` usually chosen `глагол словосочетание` or verbal, in communicating the meaning and purpose of communication.
Also, at the ends of the Association under the multiplicity can be specified `имя роли`, i.e. what is the role of the objects from this end of the Association.

![](/images/pages/guides/flexberry-designer/association.png)

`Агрегация` (`aggregation`) is the Association of type «whole-part». Aggregation in UML appears to be a straight line with a diamond at the end.
`Ромб` connected specifies which class is `агрегирующим` (i.e. consisting of» qmo), a class from the opposite end — aggregate (i.e., the «part»).

![](/images/pages/guides/flexberry-designer/aggregation.png)

`Композиция` (`composition`) – this sort of aggregation where `объекты-parts cannot exist without the себе` and destroyed when the object is destroyed the aggregating class. Composition is depicted as well as the Association, only the diamond is shaded.
It is important to understand the difference between aggregation and composition: aggregation of objects-parts can exist by themselves, and if the composition is not. Example of aggregation: a car—wheel, an example of composition: home—room.

![](/images/pages/guides/flexberry-designer/composition.png)

`Наследование` (`inheritance`) is the ratio of type «total-private». Allows you to define such a relationship between classes when `один class has the behavior and structure of a number of other классов`. When you create a derived class from a base (one or more) there is a hierarchy of inheritance. Implementation inheritance is a key prerequisite for reusability of code, since it is the main tool to achieve polymorphism.

![](/images/pages/guides/flexberry-designer/inheritance.png)

## How to build class diagrams

1. To create a new graph with the name of the Entity» qmo.
2. To perform the subject area and to build a class diagram. You should get a chart similar to the example:

![](/images/pages/guides/flexberry-designer/class-diagram.png)

The main entity in the system will be the product. As we know from the design specification, the goods stored in the warehouse. But the concept of the product as a description of the goods, lying directly in the warehouse, are different from each other. Commodity, underlying stock, except that associated with the warehouse with respect to composition (aggregation is not really suitable, since in this system the product is a commodity until it leaves the warehouse), another is characterized by the amount. Similarly, you should talk and when considering the relationship of the Goods and Order Invoice. Due to the fact that `Заказ` and `Накладная` in fact are documents and have similar attributes, they were United through a common ancestor class `Документ`. It is noteworthy that the diagram shows two classes with the stereotype `Enumeration` (transfer). A stereotype can be set from the context menu for the class.
3.To save the graph.

## App for charting classes

For building UML diagrams, use [Flexberry Designer](https://designer.flexberry.net) - a convenient online tool. After a simple registration process is enough to create a project, go to list of charts and create the desired chart.

## Go

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [a diagram of activities](gpg_activity-diagram.html)
* [The diagram of interaction](gpg_interaction-diagram.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}