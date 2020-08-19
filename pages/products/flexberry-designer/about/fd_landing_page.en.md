---
title: Flexberry Designer Online
keywords: Ember Flexberry Designer, UML, design basics, modules, configure
summary: General information about Ember Flexberry Designer
sidebar: flexberry-designer_sidebar
toc: false
permalink: en/fd_landing_page.html
lang: en
autotranslated: true
hash: ae6e3eb9988d297614afee7e3a3ee2dd073145433e05bcca85bde1eb407c0105
---

## Start Flexberry Designer and create a new project

### Registration and logging in Flexberry Designer Online

To register in Flexberry Designer and log into the portal, you must perform the following steps:

1. First need to open the portal [flexberry.net](https://flexberry.net/).
2. Next button `Войти`. Open login page and register. If the check is already there, you can log in.
3. Note the checkbox `Я not работ`. To specify a real email address, you will receive an email with activation account. To specify the name and nagmati the register button.
4. The system displays a notification that the email address is sent a letter. To open the mail to find a letter from `no-reply@flexberry.net` where is the link to confirm your entry, also comes an email with a password for authorization.
5. Click on the link. Display a notice `Верификация successfully завершена`. Click `Ок`.

Open start page Flexberry Designer Online, where you can see parts of it.

* `Все проекты`. In this section there is a list of all created projects.
* `Мои license and обращения`. This section is a list of licenses and appeals in support. License Flexberry Designer Desktop. The list of complaints is all the questions and answers from the support team of the designer.
* `Flexberry Designer Desktop`. In this section you can download Flexberry Designer Desktop. This is the professional version of FD Online. Necessary for the development of offline and multi-structured or complex applications. Requires a special license. To create an application online a special license is not required.
* `Профиль пользователя`
* `Портал flexberry.net`. Translates into the portal Flexberry Platform where you can find variety of products developed using the platform.
* `Исходный code платформы`. Click on the button translates to GitHab to the system Flexberry Platform. It is possible to participate in the finalization of platform products through a system of pull-requests.
* `Документация`. The Documentation button opens the website with product documentation platform Flexberry. At the top is a link to the documentation for working with the designer.
* `Чат`. Here located» «room where you can ask questions about working with a designer, his design and documentation. The questions are answered by competent specialists who are always ready to help and support.

### Create a new project

To create your first project click `Все проекты` on the main page. After pressing menu `Все проекты` page will be displayed with the proposal to create the first draft. Click `Новый проект`, it will open the creation form of the project.

`Название проекта`, it is advisable to call the project so that it is clear which application you want to create. It should be brief and succinct.
`Кодовое name проекта` is how the project will be called in the team. Perhaps the abbreviation or the purpose of the project. If left blank, the field will be created automatically by the project name. Code name of the project should be in English or written in Latin.
`Доступ` – who will be able to view and edit the project.
`Описание` – brief description of the project.

The project not only create, but also to import already existing one. For this field to Select a project based on saved file, select project button to Select a file. After all fields are filled in, the project can be sustained. Later this data can be edited. This will open the project page where will be displayed a list of all generated classes. Creating classes is one of the main functions Flexberry Designer Online.

### A brief overview of what Flexberry Designer Online

When you create a project page opens `Модель приложения`, which is a list of the classes (essentially, a set of elements) that describe the domain of the application and its functionality. Flexberry Online Designer allows you to create different types of classes from simple entities (application objects) and forms to private types and business servers.
Classes can also be created during creation of the chart. `Диаграммы` allow you to not only create classes, but also to establish links between them, which in the future will allow to associate different forms in the application. Also, some types of charts help to detail the subject area, describe the processes that must implement the application.
`Навигация` allows you to create a menu of the application to distribute forms groups and/or roles.
`Генерация` allows you to create a working prototype application, which is suitable for the initial demonstration to the customer. On this basis the application is easy to modify in accordance with the wishes of the customer as the main points of the subject area had been introduced.
The app can `настроить` in accordance with the needs of your application, specify the path generation for client and server parts of the application, to Supplement the description. Also the settings menu option allows to delete the project.

### Project settings

`Название проекта`, code project name and description transcribed above.

`Доступ`:

* Open – access project of yours.
* Open to entire Internet – the project will have open access.

#### Settings generation

Can be for klientskiy and servernoe part if you want to split. The server part is usually used for complex, «heavy» algorithms not to overload the user interface. The client part is needed for the processing of» «on the fly.
Login, parol to the repository client part and Vetka to the client side as well as the same field for backend use, if you want to generate the application in an existing repository.
If repository have not, in the first generation designer himself will create a new repository on [Githab](https://github.com/) on the project name (it will generate and bind [ssh keys](https://ru.wikipedia.org/wiki/SSH)), and generates code in the created repository.
In addition, the project may be published in `gh-pages`.
It is also possible to change localizatio (the language used to display designer) and the theme designer.
For export project need to open the page `Настройки` and under `Действия` with the project to choose `Я want to export проекта`.
You can also make backup project button `Я want to make a backup проекта`.

## Creating basic types of charts. Basics of IC design

### Order IC design

The first paragraph of algorithm design information systems is the formulation in which it is necessary to determine:

* characteristics of the automation object
* define the main task, which will implement the application
* describe the business processes that will be implemented in the application

Next, you need to create [a diagram of the use cases](https://flexberry.github.io/ru/fd_use-case-diagram.html), which is the most common representation of functional requirements to the system. It allows us to describe the main processes that need to be implemented.
Then it is useful to make a chart [activities](https://flexberry.github.io/ru/fd_activity-diagram.html), which is a flowchart that shows the flow of control passes from one activity to another.
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

In order to create a new class open the project. The default open menu item `Модель приложения`, which is a list of all classes in the project. Then you press the button `Создать класс`.

#### Stereotypes of classes

`Сущность` (implementation) – the class representing the real-world object whose instances will be stored in the application database. Create a pair.

`Перечисление` (enumeration) data type, which is defined as a set of identifiers. Create a pair.

`Бизнес-класс` (businessserver) is a class whose code is invoked in the process of changing instances of related entities.

`Тип` (type) – a complex data type or a real-world object whose instances will be stored in the application database. It is advisable to create, if necessary in code, secondary business objects that do not need to be stored in the database.

`Typedef` (typedef) is a type of data that can be mapped manually generated to the database or application data types. Need, if you do not have default types.

`Интерфейс` (interface) contract in the form of a list of public properties and methods that must be implemented in the related entities or types.

`Внешняя сущность` (external) – a class that is not explicitly declared in the model but will be available in the source code, including an entity from a different project. When generating code-level means that the Declaration of this class contains the external or system library. In private means that you can specify for an external class reference to the class from another project (stage) in Flexberry Designer. In this particular case means that the class code is declared in another solyushene (addon), which must be connected to the main developers to the project manually (e.g., via a NuGet package is created based on another солюшена; connection of "external" libraries on the level generation is not implied). When it is used: the reference to class from other projects (i.e., stages) is indicated when multiple projects have some shared libraries, and classes from it pericolosa in different projects. Another option is the external class is a class from the system library or any external libraries (add-on). Then at the model level it is possible to put a link to an external class and include it in the view base class, and the code after generating the data type for the corresponding property will be a class that is declared in the "external" library (it needs to connect manually by the developer in проект; if it's a class from the system Assembly, for example, it is already the first generation of the project can be automatically already connected as an option).

`Приложение` (application) class that stores General information about the generated application.

`Пользовательская форма` (userform) – class representing» «blank form of application (all markup and logic for it to be done fully manually by the developer), which can also be added to the menu structure (in "container" class with the stereotype "application"). Can be used in the case when the level of the application model required special forms, which are neither list nor edit forms are not (i.e., to look at the model it was obvious what forms in the app).

`Слой geo-сервера` (geolayer) class, which configures the layer GIS subsystem.

`Стиль layer of geo-сервера` (geolayerstyle) class, which configures the layer GIS subsystem.

`Сущность стереотипом` with user-defined (custom) class with arbitrary semantics, which code is not generated to the application.

In the list of charts automatically generated charts for each class.
In the search field on the form `Модель приложения` enter the name (or part of) the class from the list. Mapped class (or several classes) that meet the specified condition.
In the field `Все типы` to select any of the created types. Only displays classes for the selected type.

### Filtering and searching charts

In the menu `Диаграммы` are all created in the framework of the project chart. They differ in the type have unique names. If the list is large, it is convenient to use search or filtering.
To search for diagrams in the field `Поиск` need to enter a name or part of the name of the chart. The list will be edited in accordance with the specified condition.
You can also filter the chart by type. For this drop down list, select the desired chart type.

## The basics of working with class diagrams

### Class diagram from» «analyst and after object of design

Analyst in communication with the customer creates with charts the idea of the subject area, which later refined and updated by the developer in accordance with architectural requirements and generate future project.
The project charts the analyst usually contain the name of clarification «As it should be,», and the project developer – «Implementation of».

Developer details subject area in order to:

* no contact with many-to-many (added intermediate classes),
* no relationships of type aggregation (can be used only Association, composition and inheritance), as well as links with a plurality of one-to-one,
* all attributes were rated necessary for storing and processing the data type,
* no contradictions in the structure chart, which can lead to failures of generation,
* classes were divided by type (entity, enum, user type, etc. – details in explained in Module 3 in screencast Menu «Model application»,
* charts were distributed through catalogs, clarifying the purpose of classes (reference, description of specific groups of classes, e.g., person, territory, etc.)
* class methods have been prescribed, if they are necessary at the stage of design and prototype your application.

The developer also creates forms and views to generate a prototype of the future application.
The following screencast we will talk about how to add and edit classes and relationships on the class diagram.

### Add and edit classes and relationships

A class can have different sterotype: the nature, application, transfer etc. (Details in screencast Module 3. The application model).
Connections are established mainly between the classes of type Entity» «(implementation). Also a link can be established between an entity and an interface or an external interface.

#### The properties of an Association

* `Описание связи` – explanation of communication (e.g. what it was or why exactly this).
* `Имя role мастера` – the name of the role from the master, duplicate role name in the diagram.
* `Имя publication role мастера` is the title wizard properties in the OData interface (it's web-based API for data access and manipulation).
* `Множественность have мастера` – can be 0..1 (then in the database for the primary class value of the master may be Null) or 1 (then in the database for the primary class value of the master can only be Not Null).
* `Модификатор access artisan свойства` is _protected_ (#), that is, access is limited to the containing class or types derived from the containing класса; _public_ ( ), that is, the property is общедоступным; _private_ (-), that is, the property access is restricted.
Communication with the master is stored is a flag for generation of a field in DB with reference to the master
* `Имя the role of the main класса` is the name that will appear in the main class in the table. For example, for the table Order it will be the Storekeeper.
* `Имя publications the role of the main класса` is the name of the properties of the main class in the OData interface.
* `Множественность the main класса` is `*` any value. For generation is only supported this option. If you need a specific value that is different from `*`, it is necessary to control in code.
* `Автогенерируемый TypeUsage` – identifier for the attribute, limiting the list of types in the inheritance hierarchy.
* `TypeUsage` - an attribute that limits the list of types in the inheritance hierarchy that is covered by this communication. When using inheritance, the problem arises of determining the right type to use for Association. In other words, if the master type is the type associated with inheritance, it is unclear what specific type of an inheritance hierarchy is a master. Class a is the master M, which has at least two successor: M1 and M2. To resolve the problem, you can use special metadata that allows you to specify that a property M (link to the artisan class) in the class data A, in this particular (practical) case, can take only values of type M, and also M1 and M2. This is done at the level of program code.
Accordingly, if a data object a (an instance of class A) that his master could be an instance of any of the classes M, M1, M2.

![A typeusage](/images/pages/products/flexberry-designer/about/flexberry-designer-online005.png)

* `Имя connection to database данных` is the name of the field in the database in which to store a reference to the master

#### Properties of the composition

* `Описание связи` - explanation of communication (e.g. what it was or why exactly this).
* `Имя role агрегатора` – the name of the role from the beginning of the track that duplicates the role name in the diagram.
* `Имя publication role агрегатора` is the name of the aggregator properties in the OData interface.
* `Имя role детейла` - the name that will appear in the main class in the table. For example, for a table of Sostanziosa it will be Ordering.
* `Имя publication role детейла` is the name of the aggregator properties in an OData interface
* `Множественность have агрегатора` is always 1, as in the case of compositions in the database for the primary class value of the master can not be Null.
* `Автогенерируемый a typeusage агрегатора` – similar to the same property of the Association.
* `TypeUsage агрегатора` – similar to the same property of the Association.
* `Имя connection to database данных` is the name of the field in the database in which to store a reference to the master
* `Автогенерируемый a typeusage детейла` – similar to the same property of the Association.
* `TypeUsage детейла` – similar to the same property of the Association.

### The attributes and attribute types data classes

Class attributes can be edited from the list of classes in the application model (app Model-group Entities – class click – edit) and charts (Charts-corresponding class diagram-a class is selected-the button edit (icon) – opens the same edit form class).
The attribute has `имя` (how it will be displayed in DB and code), `заголовок` (as the name will be displayed in the app), `описание` (for example, for which this attribute) and the type.
The attribute can be `хранимым and нехранимым` (in this case, its value is calculated in application code and is used for operations in code, without storing in DB).
The attribute type must be specified because it is necessary to store data in the database and operations in the program code. Flexberry Designer Online uses built-in data types (string string, int – integer, double point, bool – logical, etc., also there are types to store null values and the ability to create their own type).

#### The types of relationships between classes and non-obvious restrictions of the object structure

The class multiplicity is equal to 1 (rarely 0..1 Association) is the main, that is `мастером`. For example, the shelf and the goods on the shelf: one shelf can be a lot of goods, but one and the same product can lie on different shelves (it is the product, not the form, that is, a particular book, pen, Bank, etc.). Such a connection will `ассоциативной`, because if the shelf breaks, the goods can be easy to pass on to a friend.
Completely different situation is with a house and apartment. In the same house can be a lot of apartments, but the apartment is always owned by one house, it cannot be moved to another. Accordingly, when the disappearance of houses, apartments also will not be. This is referred to as `Композицией`.
Thus, a class, a plurality of which may vary from 0 to n will be subordinate, that is, detaila.
Classes for communication can be assigned to the role (ask the name of the foreign key in the DB). In the example application, the Warehouse (an example of the construction of class Diagram) these roles have a connection from a class Employee (Manager, Storekeeper, Accountant). Named roles will be named column in the table of detail for the table wizard. Show the class diagram.

#### Limitations when constructing class diagrams

1.Detail any level cannot be the successor of the class-masters, as in this case, it turns out that the object of the heir should include himself

![Example of the limitations of charts 1](/images/pages/products/flexberry-designer/about/flexberry-designer-online001.png)

2.The heir of detail may not be detalam heir class wizard.

![Example restrictions chart 2](/images/pages/products/flexberry-designer/about/flexberry-designer-online002.png)

3.Not to do cycles in the inheritance

![Example constraint diagrams 3](/images/pages/products/flexberry-designer/about/flexberry-designer-online003.png)

4.You cannot inherit a class from multiple classes (the class MB many descendants but only one parent).

![Example constraint diagrams 4](/images/pages/products/flexberry-designer/about/flexberry-designer-online004.png)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}