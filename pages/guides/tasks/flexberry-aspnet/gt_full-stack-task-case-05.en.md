--- 
title: Option 05 - «Electronic equipment heat» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-05.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: 32c594944d21df24f2d4f87b77b263ab77ee97e9c40fb8db2cb2d0588a3336be 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Electronic equipment heat» (module IC for thermal inspectors). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to create a separate application module for thermal inspectors. This module should cover the demand for accounting of objects of consumption areas and parts of the network. It is known that objects of consumption are in buildings, which are in turn attached to a network area. Each object of a consumption feature for any user, and can also have outer or inner parts of the network. Information about objects and their parts is fixed in the application. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement 2 of the algorithm generate a unique, alphanumeric code for the QR code of the object of consumption, based on following input data: 

* the name of the object 
* registration date (valid date), 
* the consumer's personal account (only integers). 

The algorithms forming the code you need to invent your own. The type of algorithm must be contained in this code. The length of the alphanumeric code must not exceed 18 characters. Code should be built in such a way that it should be implemented the potential to calculate the specific heat of the object (from a known list), without storing the correspondence between an object and code. Examples of alphanumeric code: `2017-01-01-ШШШШПВ`; `А665Б44В121212`. The algorithm follows in the comments to make a description of the selected variant of the algorithm, because valid different ways to generate the alphanumeric code. 

The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. The logic input of the search query alphanumeric code and using the library from the previous paragraph for the release of information about the object of heat consumption (field to enter a value, the button, the unit displays the result). 
2. The logic of obtaining the alphanumeric code for the specified information about the name of the object, its date of registration, the personal account of the user (input fields values button, the unit displays the result). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form where you will layout the sections of the network relative to the object of heat consumption. Sections can be moved inside of the object of consumption, then they automatically become internal, and the outside of the object (respectively, - outer). On the block part of the network should be displayed the number and the type of insulation, and the unit is painted in a color appropriate to the type of installation (aerial, underground, basement). When you click on the section of the network POPs up a form where you can specify the year of installation, type of insulation and type of pipeline. The form also needs to be a block with information about the object of consumption, the consumer and the building in which it is located. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of embedded pages [GitHub Pages](https://pages.github.com/) that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - the Database 

Will be required for implementation: 

* Microsoft SQL Server 
* Microsoft SQL Server Management Studio 
* Git for Windows 

### Job 

For the specified subject area to implement the database, fill the database with scripts (at least 5 records per table). Implement scripts to create constraints, indexes. To attach the scripts to create the database and populate, Bacup database. 

Prepare SQL scripts to obtain the following information: 

1. Display the list of network areas by number of objects presented in them. 
2. Display top N objects of consumption with the greatest number of network sections. 
3. To bring customers (counterparties), the objects of which the most inner parts of your network. 
4. Withdraw the rating of types of isolation of network sections network areas. 
5. Display information about 5 buildings in which most objects heat from the outer sections of the network.

### to deliver results 

Implemented scripts commit in the GitHub repository. A link to the repository to provide the teachers of the course. 

## Practical assignment # 4 - Designing the IP 

Will be required for implementation: 

* Flexberry Designer 

### Job 

Draw a UML diagram for the designated subject area. The composition of the diagrams is determined by the trainees, but should provide a full description of the subject area. 

### to deliver results 

The result is uploaded in the format of a CRP stage of Flexberry Designer. Stage (a file with the extension *.CRP) should commit to the repository on GitHub, the link to provide the teachers of the course. 

## the Practical task №5 - Object design and generation applications 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Git for Windows 

### Job 

Execute object design and generation of ASP.NET applications for the described subject area. As a basis to use the previously implemented the UML model. Generation of the application and the database must be executed by means Flexberry Designer, the application must meet the requirements and be fully functional. Submission must be high quality customized signatures to classes and attributes on the forms should be adequate. The list of forms and attribute composition must correspond to the subject area and cover all of the required business function. 

### to deliver results 

The generated application and the scripts to create the database should be put in a repository on GitHub. To provide teachers with a reference to the repository. 

## Practical task №6 - Development of business logic applications 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Git for Windows 

### Job 

In generated using Flexberry Designer application is required to implement the following business logic. 

1. To implement a business server that will be used .net library for affixing alphanumeric code for a QR code object of consumption. 
2. To implement a business server, which checks that the object of heat consumption has brought the duplicate sections, ie, there is no match in number and type of network. 
3. To add a stored field in the class building, which will contain the sum of the areas of objects of heat consumption in the building. Recalculation of this field to implement the business server. 
4. To add a stored field in a class part of the network, which will be a string containing the address of the building, the name of the object of consumption, the parcel number and the type of insulation. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors.

## Practical task №7 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

Tie the previously implemented ascx-components for working with alphanumeric codes to the data from the database. 

To realize the integration of forms layout of plots a network object for heating with the business logic of the application. On the form controls with the choice should be taken from the database (through ORM and Lookup). Drag and drop plots and their change should also work with saving to the database. 

You want to configure a beautiful look for all forms of application. To improve the visual design theme. Implement shortcuts on the desktop application for quick access to frequently used functions. 

Implement additional forms, which will display the results of a query of the jobs database. 

### to deliver results 

Modified UI logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - the Functional subsystem Flexberry 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Git for Windows 

### Job 

The implemented application to configure the subsystem powers. Users must start the application administrator. Authorization form-based. To create a hierarchy of roles (Administrator, Senior inspector, Inspector). The inspector has the full rights to sections of the network, the chief inspector has full rights to change the object of heat consumption and its detaili. The administrator has the right to change all data. To configure the roles and assign these roles to users. 

To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}