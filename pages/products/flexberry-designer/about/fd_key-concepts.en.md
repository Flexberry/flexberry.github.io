--- 
title: Key concepts object structure 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, master, detail, Association, inheritance, stereotypes 
summary: the Concept of the master, detail, relationships between classes, stereotypes of classes 
toc: true 
permalink: en/fd_key-concepts.html 
lang: en 
autotranslated: true 
hash: 96a3c07205a5b3331825ff9f3d800335d4f35815a0aa78aa23b71dd6d834876f 
--- 

## Key concepts 

The data structure defines the structure of the application. The basis of the system is placed to the data structure, expressed as a UML class diagram. 

Consider the example of the class diagram concepts on the basis of elementary concepts `UML` (not the concepts of attribute, method, method parameter, type, role, Association, etc., because they are obvious and entirely borrowed from `UML`). This [class diagram](fd_class-diagram.html) also illustrates typical situations that arise in the design. Any other situation — the implications of this chart. 

![](/images/pages/products/flexberry-designer/about/uml-example1.jpg) 

Class, relatively to which in a particular situation are considered Association, called `внутренним классом` and the rest `внешними`. 

`Вложением (subobject)` called class attribute whose type is another class. 

### Artisans Association 

Association similar to the Association between `Internal` and `Master1` called [the mechanics](fd_master-association.html), and the outer class from the multiplicity 1 or 0..1 is called `мастеровым` or `мастером`. 

[Artisans Association](fd_master-association.html) can be drawn between the classes, each of which is detalam, and hat they have in common. An example of this is the Association `Detail2-Detail3`. 

### Decalogue composition, aggregator, hat 

Association aggregation or aggregation-composition, such `Internal-Detail1` called [metalowymi](fo_detail-associations-properties.html) and the external class — `детейловым`, or just `детейлом`. Inner class in relation to detailo also called `агрегатором or шапкой`. 

### Inheritance 

Association of inheritance, such `Internal-InternalChild` called [inheritance](fd_inheritance.html), an internal class in the Association — `предок` and the external `потомок`. 

*When you inherit the attribute structure and composition of links is always increasing and can not be reduced.* 

[Flexberry ORM](fo_flexberry-orm.html) fully implements object-oriented concept, because in addition to encapsulation and polymorphism makes it possible to use inheritance with object-relational mapping. 

### Stereotypes 

Classes on the class diagram, the stereotypes attributed to that allows you to specify the appointment of an `UML` class. 

In `Flexberry Designer` the following predefined stereotypes: 

* [Implementation](fd_data-classes.html), the stereotype of default (the charts specify an empty value), the usual `UML` class implemented in the source code of classes of data objects. Classes can contact any ассоциациями; 
* [TypeDef](fd_typedef.html), a stereotype indicating the type of synonym. When generating code synonyms are given to basic types in the target language by the user (in the code gets the base type, but a synonym does not specify in any way). The diagrams may not be associated with any ассоциациями; 
* [Type](fd_data-types-properties.html), the stereotype to introduce a new type. When generating code in the target language you declare the type, and when declaring members of classes, parameters, etc is used. 
* [Enumeration](fd_enumerations.html), a stereotype that introduces the enum type. The diagrams may not be associated with any ассоциациями; 
* [Interface](fd_interfaces.html), stereotype, introducing `.Net` interface. The interfaces can contact any ассоциациями; 
* [BusinessServer](fd_business-servers.html), the stereotype, the corresponding business server `.Net` class, where the implementation of the business operations of applied системы; 
* [EditForm](fd_additional-stereotypes.html), a stereotype that corresponds to the edit form — `.Net` class, where the implementation of forms for editing of the object данных; 
* [ListForm](fd_additional-stereotypes.html), a stereotype that conforms to the shape of the list — `.Net` class, where the implementation of the form for displaying list of objects данных; 
* PrintForm, stereotype corresponding to the form of printing .Net class, where the implementation of forms for печати; 
* [UserForm](fd_additional-stereotypes.html), stereotype corresponding to the empty форме; 
* [EventArg](fd_eventarg.html), stereotype corresponding to the parameters of the event (event), when generating the resulting class inherits from `System.EventArgs`; 
* [Application](fd_additional-stereotypes.html), a stereotype that corresponds to the application: the form of desktop and adjustment классу; 
* Role, stereotype, appropriate role in the system полномочий; 
* [External](fd_external-classes.html), a stereotype that corresponds to any external (not declared in `CASE`, language) class. 
* [ExternalInterface](fd_externalInterface.html), a stereotype that corresponds to any external (non-declared) interface. 
* User can specify arbitrary stereotype. 

### non-Obvious restrictions of the object structure (what not to do) 

Notation `UML` describes the purpose of its elements, but does not declare the correctness of the» «their use. 

Therefore, we consider the main non-obvious restriction that is not described notation, cause difficulty in understanding, impossible in implementation, __to avoid__.

#### Cyclic detaily 

Detail any level can not be the heir to the caps, as in this case, it turns out that the object of the heir should include himself. 

![](/images/pages/products/flexberry-designer/about/uml-example2.jpg) 

#### Contradictory aggregation 

The heir of detail may not be detalam heir hats. 

![](/images/pages/products/flexberry-designer/about/uml-example3.jpg) 

However #### 

This situation is possible: 

![](/images/pages/products/flexberry-designer/about/lookup-as-master.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}