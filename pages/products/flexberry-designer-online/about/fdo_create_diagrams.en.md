---
title: Creating diagrams 
keywords: Ember Flexberry Designer, UML, design basics, modules, configure
summary: Information Systems design order and creating corresponding diagrams
sidebar: flexberry-designer-online_sidebar
toc: false
permalink: en/fdo_create_diagrams.html
lang: en
---

## Creating basic types of diagrams. Basics of Information Systems design

### Information Systems design order

The first point of the algorithm for the design of information systems is a problem statement, which should be defined:

* characteristics of the automation object
* define the main task to be implemented by the application
* describe the business processes that will be implemented in the application

Next, you need to create [a diagram of the use cases](https://flexberry.github.io/ru/fd_use-case-diagram.html), which is the most common representation of functional requirements to the system. It allows us to describe the main processes that need to be implemented.
Then it is useful to make a diagram [activities](https://flexberry.github.io/ru/fd_activity-diagram.html), which is a flowchart that shows the flow of control passes from one activity to another.
Once defined the functional requirements for the system and its boundaries, should examine the subject area with the aim of building [class diagram](https://flexberry.github.io/ru/fd_class-diagram.html).
Further, it is useful to describe scenarios that need to be implemented in the application using [sequence diagram](https://flexberry.github.io/ru/fd_sequence-diagram.html). Scenarios can be described using [chart cooperation](https://flexberry.github.io/ru/fd_collaboration-diagram.html), allowing to describe the interaction of objects and focuses primarily on the organization of the objects.
[State diagram](https://flexberry.github.io/ru/fd_statechart-diagram.html) determines the sequence of States of an object caused a sequence of events. This diagram may help to clarify the subject area, supplemented, if necessary, a class diagram.
[Chart deployment](fhttps://flexberry.github.io/ru/fd_deployment-diagram.html) is a physical diagram in the UML. It displays the physical relationship between hardware and software components of the designed system.

### The problem statement for the design

Described in the article [problem Statement](https://flexberry.github.io/ru/gpg_formulation-problem.html) for example.

### Create entity relationship diagram

Described in the article [building the entity relationship diagram](https://flexberry.github.io/ru/gpg_use-case-diagram.html) for example.

### Charting activities

Described in the article [diagram Construction activities](https://flexberry.github.io/ru/gpg_activity-diagram.html) for example.

### The construction of class diagrams

Described in the article [diagram](https://flexberry.github.io/ru/gpg_class-diagram.html) classes for example.

### The Model menu of the application

In order to create a new class open the project. The default open menu item `Application Model`, which is a list of all classes in the project. Then you press the button `Create class`.

#### Stereotypes of classes

`Entities` (implementation) – the class representing the real-world object whose instances will be stored in the application database. Create a pair.

`Enumeration` (enumeration) data type, which is defined as a set of identifiers. Create a pair.

`Business server` (businessserver) is a class whose code is invoked in the process of changing instances of related entities.

`Type` (type) – a complex data type or a real-world object whose instances will be stored in the application database. It is advisable to create, if necessary in code, secondary business objects that do not need to be stored in the database.

`Typedef` (typedef) is a type of data that can be mapped manually generated to the database or application data types. Need, if you do not have default types.

`Interface` (interface) contract in the form of a list of public properties and methods that must be implemented in the related entities or types.

`External entities` (external) – a class that is not explicitly declared in the model but will be available in the source code, including an entity from a different project. When generating code-level means that the Declaration of this class contains the external or system library. In private means that you can specify for an external class reference to the class from another project (stage) in Flexberry Designer. In this particular case means that the class code is declared in another solyushene (addon), which must be connected to the main developers to the project manually (e.g., via a NuGet package is created based on another солюшена; connection of "external" libraries on the level generation is not implied). When it is used: the reference to class from other projects (i.e., stages) is indicated when multiple projects have some shared libraries, and classes from it pericolosa in different projects. Another option is the external class is a class from the system library or any external libraries (add-on). Then at the model level it is possible to put a link to an external class and include it in the view base class, and the code after generating the data type for the corresponding property will be a class that is declared in the "external" library (it needs to connect manually by the developer in проект; if it's a class from the system Assembly, for example, it is already the first generation of the project can be automatically already connected as an option).

`Application` (application) class that stores General information about the generated application.

`User forms` (userform) – class representing» «blank form of application (all markup and logic for it to be done fully manually by the developer), which can also be added to the menu structure (in "container" class with the stereotype "application"). Can be used in the case when the level of the application model required special forms, which are neither list nor edit forms are not (i.e., to look at the model it was obvious what forms in the app).

`Geo-server layer` (geolayer) class, which configures the layer GIS subsystem.

`Geo-server layer styles` (geolayerstyle) class, which configures the layer GIS subsystem.

`Entities with user stereotypes` with user-defined (custom) class with arbitrary semantics, which code is not generated to the application.

The list of diagrams contains automatically created diagrams for each class. In the search field on the `Application Model` form enter the name (or part of it) of the class from the list. Select the class (or several classes) that meets the specified condition displayed. 
In the `All types` field, select any of the created types. Only classes of the selcted type are displayed.

### Filtering and searching charts

The `diagram` menu contains all diagrams created within the project. They differ in types and have unique names. If the list is long, it is convenient to use search or filtering. To search for diagrams, enter the name part of the name of the diagram in the`search` field. The list will be edited in accordance with the specified condition.
You can also filter the diagram by type. For this drop down list, select the desired diagram type.