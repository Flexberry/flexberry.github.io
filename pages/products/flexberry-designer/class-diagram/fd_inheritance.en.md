--- 
title: Inheritance 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, Flexberry ORM data objects 
summary: the Main properties of inheritance 
toc: true 
permalink: en/fd_inheritance.html 
lang: en 
autotranslated: true 
hash: 973c77f5b75f95ca19202d2b91775b5b20bcb0fd0555458e83ef70a3f13b0bbd 
--- 

Association of inheritance, such `Internal-InternalChild` called `наследованием` class in the Association — `предок` and the external `потомок`. 

![](/images/pages/products/flexberry-designer/about/uml-example1.jpg) 

## Basic properties of inheritance 

* When you inherit the attribute structure and composition of links is always increasing and can not be reduced (each inherited table [stores `все` attributes `всех` ancestors](fo_storing-data-objects.html)). 
* The heir is available in the all attributes and associations of the ancestor. So, if we take an inner class `InternalChild`, its attributes will be `Attr1` and `Attr2`, masters `Master1`, `Master2`, `Master3`, detaily `Detail1, Detail2, Detail3, Detail4`. 
* Ancestor provides polymorphic access to all his heirs, which means that the instance of the inner class and its heirs (in this case `Internal and InternalChild`) can be set as an instance of the artisan class (in this case `Master2`) and an instance of any heir of the master (in this case `Master2Child`). The situation is similar with detaylari: instance `Internal` equally through `Detail2` operates with instances and `Detail2 Detail2Child`, and similarly for attributes and methods of the class. In practice, this phenomenon can cause the problem to be solved with the help of a typeusage](fo_type-usage-problem.html) that [in a particular way is displayed in the data structure](fo_type-usage.html). 
* If the heir has the attribute method (with the same composition parameters), the Association (with the same role name), it means that the heir `перегружает` this element ancestor. 
* Heir from the master can be detailon the same inner class (as in a situation `Internal-Master1-Detail4`), that's fine. 

{% include note.html content="a Frequently asked question as to why the table [of the heirs](fd_inheritance.html) keep all attributes of all ancestors, not just their own. A: then when reading the object-the heir you need to collect all the attributes from the tables of their ancestors along the chain through `JOIN` which is very slow, especially when reading different types of objects in a single query. Therefore, application performance will decrease with increasing number of levels of inheritance, which is unacceptable." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}