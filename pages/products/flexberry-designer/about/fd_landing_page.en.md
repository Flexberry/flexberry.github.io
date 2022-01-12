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
2. Next button `Sign up`. Open login page and register. If the check is already there, you can log in.
3. Note the checkbox `I am not Robot`. To specify a real email address, you will receive an email with activation account. To specify the name and press the register button.
4. The system displays a notification that an email is sent. Open the mail to find a letter from `no-reply@flexberry.net` where there is a link to confirm your entry, also comes an email with a password for authorization.
5. Click on the link. Display a notice `Verfication successfully completed`. Click `Ок`.

Open start page Flexberry Designer Online, where you can see parts of it.

* `All Projects`. In this section there is a list of all created projects.
* `My license and Request`. This section is a list of licenses and appeals in support. The License is for Flexberry Designer Desktop. The list of complaints is all the questions and answers from the support team of the designer.
* `Flexberry Designer Desktop`. In this section you can download Flexberry Designer Desktop. This is the professional version of Flexberry Designer Online. Necessary for the development of offline and multi-structured or complex applications. Requires a special license. To create an application online a special license is not required.
* `User Profile`
* `flexberry.net Portal`. Translates into the portal Flexberry Platform where you can find variety of products developed using the platform.
* `Platform source code`. Opening this button opens the GitHab page of the Flexberry Platform. It is possible to participate in the finalization of platform products through a system of pull-requests.
* `Documentation`. The Documentation button opens the website with product documentation of flexberry platform. At the top is a link to the documentation for working with the designer.
* `Chat`. Here is a room where you can ask questions about working with the designer, its development and documentation. The questions are answered by competent specialists who are always ready to help and support.

### Create a new project

To create your first project click `All projects` on the main page. After pressing menu `All projects` page will be displayed with the proposal to create the first draft. Click `New project`, it will open the creation form of the project.

`Project name`, it is advisable to call the project so that it is clear which application you want to create. It should be brief and succinct.
`Identifier name` is how the project will be called in the team. Perhaps the abbreviation or the purpose of the project. If left blank, the field will be created automatically by the project name. Identifier name of the project should be in English or written in Latin.
`Access` – who will be able to view and edit the project.
`Description` – brief description of the project.

The project not only create, but also to import already existing one. For this field to Select a project based on saved file, select project button to Select a file. After all fields are filled in, the project can be sustained. Later this data can be edited. This will open the project page where will be displayed a list of all generated classes. Creating classes is one of the main functions Flexberry Designer Online.

### A brief overview of what Flexberry Designer Online

When you create a project, open the `Application model` page, which is a list of the classes (essentially, a set of elements) that describe the domain of the application and its functionality. Flexberry Online Designer allows you to create different types of classes from simple entities (application objects) and forms to private types and business servers.
Classes can also be created during creation of the diagram. `Diagrams` allow you to not only create classes, but also to establish relationship between them, which in the future will allow to associate different forms in the application. Also, some types of diagrams help to detail the subject area, to describe the processes that must be implemented by the application.
`Naviagtion` allows you to create a menu of the application to distribute forms groups and/or roles.
`Generation` allows you to create a working prototype application, which is suitable for the initial demonstration to the customer. On this basis the application is easy to modify in accordance with the wishes of the customer as the main points of the subject area had been introduced.
The app can be `configure` in accordance with the needs of your application, specify the path generation for client and server parts of the application, to Supplement the description. Also the settings menu option allows to delete the project.

### Project settings

`Project Name`, Project identifier name and description are explained above.

`Access`:

* Open – access project of yours.
* Open to entire Internet – the project will have open access.

#### Settings generation

They can be for the client and server parts, if you need to split them. The server part is usually used for "complex" algorithms, so as not to overload the user interface. The client part is needed for »on the fly« processing.
Login, password for the client part repository and branch for the client part, as well as similar fields for the backend part are used if you need to generate an application in an existing repository.
If you do not have a repository yet, the designer will create a new repository on [Githab](https://github.com/) by the project name (it will generate and bind [ssh keys](https://ru.wikipedia.org/wiki/SSH)), and generates code in the created repository.
In addition, the project may be published in `gh-pages`.
It is also possible to change localization (the language which the designer displays) and the theme designer.
To export project need to open the `Settings` page and under `Project Action` with the project to choose `I want to export project`.
You can also make backup project, click the button `I want to do Project backup`.

## Creating basic types of diagrams. Basics of IC design

### Order IC design

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

## The basics of working with class diagrams

### Class diagram from» «analyst and after object of design

When communicating with the customer, the analyst uses diagrams to create an idea of the subject area, which is subsequently refined and supplemented by the developer in accordance with the requirements of the architecture and the generation of the future project. A project with diagrams from the analyst usually contains in the title the specification «As it should be,», and the developer's project - «Implementation».


The developer details the subject area so that:

* there are no many-to-many links (intermediate classes are added),
* there are no aggregation type relationships (only association, composition, and inheritance can be used), as well as one-to-one multiplicity relationships,
* all attributes have been assigned the data type necessary for storage and processing,
* there are no contradictions in the structure of the diagram, which can lead to generation failures,,
* classes have been categorized by type (entity, enumeration, custom type, etc. - more in Module 3 in the «application model» Menu screencast. 
* the diagrams were distributed in catalogs specifying the purpose of classes (reference books, description of a specific group of classes, for example, personalities, territories, etc.),
* methods were written for the class, if they are needed at the design and prototyping stage of the application.

The developer also creates forms and representations for the subsequent generation of a prototype of the future application. In the next screencast, we will show about adding and editing classes and relationships on a class diagram.

### Add and edit classes and relationships

A class can have different sterotypes: entity, application, enumeration, etc. (More details in the screencast Module 3: Application Model).
Relationships are mainly established between »entity« classes (implementation). Also, a relationship can be established between an entity and an interface or an external interface.

#### The properties of an Association

* `Description of relation ` – explanation of communication (e.g. what it was or why exactly this).
* `Master class role name` – the name of the role from the main class, duplicate role name in the diagram.
* `Publication name for maaster class` this is the name of the main class property in the OData interface (it is a web-oriented API for accessing and manipulating data).
* `Multiplicity of master class` – can be 0..1 (then in the database for the primary class value of the main class may be Null) or 1 (then in the database for the primary class value of the main class can only be Not Null).
* `Master class access modifier` is _protected_ (#),that is, access is restricted to the class or type containing the class; public (+), that is, the property is public; private (-), that is, access to the property is restricted. The connection with the main class is stored – this is a flag for generating a field in the database with a reference to the main class.
* `Master class role name` is the name that will appear in the main class in the table. For example, for the table Order it will be the Storekeeper.
* `Publication role name for master class` is the name of the properties of the main class in the OData interface.
* `Multiplicity of Master class` this is `*` any value.This option supports only for generation. If you need a specific value that is different from `*`, it is necessary to control in code.
* `Autogenerated TypeUsage` – identifier for an attribute that restricts the list of types in the inheritance hierarchy.
* `TypeUsage` - an attribute that restricts the list of types in the inheritance hierarchy to which this relationship applies.When using inheritance, there is a problem of determining the right type when using an association.In other words, if the type master is an inheritance-associated type, it is not clear which particular type of the inheritance hierarchy is the master.Class A has a master M, from which there are at least two heirs: M1 and M2. To resolve the problem, you can use special metadata that allows you to specify that a property M (link to the artisan class) in the class data A, in this particular (practical) case, can take only values of type M, and also M1 and M2. This is done at the level of program code.
Accordingly, if a data object (an instance of class A) that his master could be an instance of any of the classes M, M1, M2.

![A typeusage](/images/pages/products/flexberry-designer/about/flexberry-designer-online005.png)

* `Name of connection to database` is the name of the field in the database in which to store a reference to the master

#### Properties of the composition

* `Connection description` - explanation of communication (e.g. what it was or why exactly this).
* `Aggregator Role name` – the name of the role from the beginning of the track that duplicates the role name in the diagram.
* `Publication role name for aggregator` is the name of the aggregator properties in the OData interface.
* `Detail role name - the name that will appear in the main class in the table. For example, for the Order Status table, this will be an Order..
* `Detail Publication role name` is the name of the aggregator properties in an OData interface
* `Multiplicity in aggregator` is always 1, as in the case of compositions in the database for the primary class value of the master can not be Null.
* `Auto-generated aggregator TypeUsage` – similar to the same property of the Association.
* `Aggregator TypeUsage` – similar to the same property of the Association.
* `Connection name to Database` is the name of the field in the database in which to store a reference to the master
* `Autogenerated child TypeUsage` – similar to the same property of the Association.
* `Detail TypeUsage` – similar to the same property of the Association.

### The attributes and attribute types data classes

Class attributes can be edited from the list of classes in the application model (app Model-group Entities – class click – edit) and charts (Charts-corresponding class diagram-a class is selected-the button edit (icon) – opens the same edit form class).
The attribute has `name` (how it will be displayed in DB and code), `caption` (as the name will be displayed in the app), `description` (for example, for which this attribute) and the type.
The attribute can be `stored and unstored` (in this case, its value is calculated in application code and is used for operations in code, without storing in DB).
The attribute type must be specified because it is necessary to store data in the database and operations in the program code. Flexberry Designer Online uses built-in data types (string string, int – integer, double point, bool – logical, etc., also there are types to store null values and the ability to create their own type).

#### The types of relationships between classes and non-obvious restrictions of the object structure

The class whose multiplicity is equal to 1 (in rare cases 0..1 for an association) is the main, that is `Master`. For example, a shelf and a product on a shelf: there may be many products on one shelf, but the same product cannot lie on different shelves (it is the product, not the type, that is, a specific book, pen, jar, etc.). This connection will be `associative`, because if the shelf breaks, the product can simply be moved to another. The situation will be quite different with the house and an apartment.There may be many apartments in one house, but a particular apartment always belongs to one house, it cannot be moved to another. Accordingly, if the house disappears, the apartment will also not be. This is referred to as `composition`. Accordingly, a class whose multiplicity can range from 0 to n will be subordinate, that is, detail class. 
Classes in relations can be assigned a role (set a name for the foreign key of the database). In the example of the Warehouse application (example of building a Class Diagram) such roles have relations from the Employee class (Manager, Storekeeper, Accountant). The name of the role will be used to name the column in the details's table for the master table. Show in the class diagram
#### Limitations when constructing class diagrams

1.Detail of any level cannot be the heir of a master class, since in such a case the object of the heir must include itself

![Example of the limitations of charts 1](/images/pages/products/flexberry-designer/about/flexberry-designer-online001.png)

2.The heir from the detail class cannot be the detail of the heir of the master class.

![Example restrictions chart 2](/images/pages/products/flexberry-designer/about/flexberry-designer-online002.png)

3.You cannot do loops in inheritance

![Example constraint diagrams 3](/images/pages/products/flexberry-designer/about/flexberry-designer-online003.png)

4.You cannot inherit a class from more than one class (a class may have many heirs but only one parent).

![Example constraint diagrams 4](/images/pages/products/flexberry-designer/about/flexberry-designer-online004.png)



