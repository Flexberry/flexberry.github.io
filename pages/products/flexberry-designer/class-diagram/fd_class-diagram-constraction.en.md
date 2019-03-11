--- 
title: construction Features class diagrams 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, class diagram, methods, parameters, attributes, Association, aggregation, composition, map types, inheritance, master, detail 
summary: Elements of class diagrams, relationships between classes, class structure 
toc: true 
permalink: en/fd_class-diagram-constraction.html 
lang: en 
autotranslated: true 
hash: c1da661279513694bd02d851c4539801f58dd3836280431dfeada91fe895df27 
--- 

* The elements used to build class diagrams, described on page [class Diagram (Class diagram)](fd_class-diagram.html). 
* Working with the editor of class diagrams is given in [relevant article](fd_class-diagram-editor-features-work.html). 

## Features create classes 

![](/images/pages/products/flexberry-designer/class-diagram/structure-of-class.png) 

The General structure of the class is shown in the figure above. 

* Renaming classes is presented in [Features of the editor class diagram](fd_class-diagram-editor-features-work.html) (why this should be done exactly the way explained in [this article](fd_recommended-structure-repository.html)). 
* Used stereotypes with the description given in the article Key concepts of object-oriented structure for applications developed on the Platform Flexberry](fd_key-concepts.html). 
* Features of the job methods classes presented in the article [class Methods and method parameters ](fd_methods-parameters.html). 
* Description of class attributes is presented in [Attributes data classes ](fo_attributes-class-data.html). 

The need to pay special attention to: 

* All names (classes and attributes) are written **without spaces**. If you want to present in behalf of several words, the words are written together, each with a letter (a description of this style are given [here](http://ru.wikipedia.org/wiki/CamelCase)). 
* Types that you can use, you can look at [card types](fd_types-map.html). 

## Recommendations for drawing class diagrams 

There are recommendations for drawing a class diagram: 

* The direction of the relationship 
* [Mechanics Association](fd_master-association.html) drawn "sideways". 
* [Decalogue songs](fo_detail-associations-properties.html) are drawn down. 
* [Inheritance](fd_inheritance.html) is drawn up. 
* Naming context 
* The name of [master](fd_key-concepts.html) usually the same as the class name. 
* Name [detail](fd_key-concepts.html) (name, which will be a collection in the object model) is usually in the plural. 
* The Association must be called from the master, the type of composition - from both sides. 
* Communication parameters 
* Classes are United by Association with multiplicity "1:1" are combined into a single class. 
* Connection type `Агрегация` replaced either `Ассоциацию` or on `Композицию`.

## features of using aggregation 

* If you have an aggregator of several detailov, the role of the aggregator can have the same names as names of objects of the other classes will refer to that object. 
**The role of detail must have different names** because these names are used to name properties wizard (the same property names are not valid). 

![](/images/pages/products/flexberry-designer/class-diagram/same-link-names.png) 

* Detail can act as a master. However, in this case, additional settings are required: 

![](/images/pages/products/flexberry-designer/class-diagram/connect-details-master.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}