--- 
title: Communication models, systems and technological application architecture 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, logic levels, business logic, user interface 
summary: Description of the General concept of application tiers 
toc: true 
permalink: en/fd_communication-models-applications.html 
lang: en 
autotranslated: true 
hash: cfce4098cf1bc4b4780edf1b1b369466ada5f87d1d6976d593c4b323364e8c43 
--- 

## Logic levels 
In accordance with [Systemic-technological application architecture](fw_flexberry-winforms-architecture.html), there are three logical levels: 

1. User interface 
2. Business logic 
3. Data, metadata, and data access 

These logic levels can be implemented in different ways physically in a particular application system. 

`Flexberry PlugIns` extend the class diagram `UML` so that the system design was carried out in these logical and physical levels. 

## Data, metadata, and data access 

To describe the structure of applied data systems serve [data classes (classes with the stereotype `implementation` or without specifying the stereotype, their attributes, methods)](fd_data-classes.html) and the relationships between them ([inheritance](fd_inheritance.html), [Association](fd_master-association.html), [aggregation](fo_detail-associations-properties.html)). Examples of data classes are: Account, Bank, Counterparty, etc. 

Architecture `Flexberry Platform` and modules - code generators are arranged in such a way that a single data model occurs as generating data structures for storage (e.g., a relational table) and data structures programming language for data processing (the corresponding classes and relations between them). 

## Business logic 

To describe the business logic of the application system (system-wide business transactions) are the so-called [business servers (classes with the stereotype `businessserver`)](fd_business-servers.html), with which can be created the so-called "stubs" to provide business servers as COM components or as a Web service, and a business facade. 

## User interface 

For descriptions of the user interface are: 
1. [Edit form (classes with the stereotype `editform`)](fd_editform.html) 
2. [List form (classes with the stereotype `listform`)](fd_listform.html) 
3. Custom forms (classes with the stereotype `userform`) 
4. [Applications (classes with the stereotype `application`)](fd_application.html) 

## Common elements of models for all levels 

To describe the model in any of the levels are used: 

* [Interfaces (classes with the stereotype interface)](fd_interfaces.html) 
* Data types (specified types of attributes, method parameters, etc.): 
* [Synonyms of types (classes with stereotype typedef)](fd_typedef.html), are introduced for readability of the models and ease of transformation into code. Examples: Strokovne, Number, Строка255; 
* [Enumerated data types (classes with stereotype enumeration)](fd_enumerations.html); 
* Data types (classes with the stereotype `type`); 
* [External classes/types (classes with stereotype external)](fd_external-classes.html), not described in the charts, but known in the generated code (for example, in the case of C# from any of the connected Assembly); 
* Events 
* Event parameters ([classes with the stereotype eventarg](fd_eventarg.html)) 
* Role in the system of reference (classes with the stereotype `role`) 

In the description of the item belonging to any level can be used elements of other levels, as they all are, in the most General case, some "types", both at the level of the modeling language, and object-oriented programming language. The principles of creating the structural parts of the object-oriented model and the source code to object-oriented programming language - similar. So, for example, forms can have methods that return or accept parameters that are declared as data classes. Business services will have methods, mostly working with the settings declared as data classes. 

These levels are not regulated and not imposed in the simulation Flexberry. It's just explaining a logical grouping that helps to understand the relation of stereotypes to logical levels system and technology architecture. Accordingly, it is possible to locate classes with any of the [stereotypes](fd_key-concepts.html) on the charts as it is convenient. Location does not matter. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}