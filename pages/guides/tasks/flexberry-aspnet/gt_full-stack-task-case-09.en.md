--- 
title: Option 09 - «Preschool education» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-09.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: 9569eec40cdc72c2d07fe1023f1a87bde53b10f657e58108628d6d34c5566358 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Preschool education». 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to create a system that takes into account the movement of the contingent of students in educational institutions of preschool education. It is known that children learn in groups, each group is any educational organization. 

Each educational organization has its type (municipal, public, private, IP), and is probably a structural unit other educational organizations. 
The system must store data on the applications for transfer or admission to educational organization. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement the algorithm for calculating the value of the annual visits for the specified child. The cost of a visit depends on the cost of one lesson in a preschool institution for the specified group. You also need to consider the child's absence for a valid reason (such classes are paid for). Children with a preferential category have a 50% discount. The algorithm serves the parameters that affect the cost and output cost of the annual visit. 

The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. Logic to input parameters required to calculate the annual cost of visiting of the child, actually using a library to calculate and print the result. 
2. Lokigo to display a calendar of attendance a child (color is used to highlight dates on which the lesson). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course.

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form that will display the child's daily routine in the kindergarten with regard to the temporal vector. Each stage should correspond to the graphical element, displaying its duration. Thus, the screen formed «line employment» child. The duration of some stages can be changed by changing the boundaries of an object, displaying a certain stage. The form also needs to be a block with information about the group number, the caregiver, number of children in the group, the number of missing due to illness. You must implement the facility enabling parents to make choices (for instance, when you click on the item «training» becomes available a list of possible topics or courses, when you click on the element» «lunch menu). 

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

1. List of children, payment of kindergarten which was the maximum for the entire period of their visit (regardless of kindergarten). 
2. Display top N children with the highest number of transitions for kindergartens for the entire period. 
3. Bring kindergartens, which were less children in the past from the current year. 
4. Grade the kindergartens at an average price of education for the current year. 
5. Withdraw the amount of the fee for each child over the years, indicating the kindergarten group. 
6. Display kindergartens in which there were precedents with the processing of the application longer than N days. 

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

1. To implement a business server that will track that the transfer of children to another group or kindergarten is only possible if the application for translation. Without the application translation is not allowed. 
2. To implement a business server that will be used .net library for output on the page the total fees in kindergartens. 
3. To implement a business server, which will verify that the child at the moment of time belongs only to one group, one kindergarten. 
4. To add a stored field in the class group that will contain the number of children in this group. Recalculation of this field to implement the business server. 
5. To add a stored field in the class group, which will be a string containing the name of the kindergarten, name of group, number of children. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical task №7 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc.
* Git for Windows 

### Job 


### to deliver results 

Modified UI logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - the Functional subsystem Flexberry 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Git for Windows 

### Job 

The implemented application to configure the subsystem powers. Users must start the application administrator. Authorization form-based. To create a hierarchy of roles, add transactions to view the data and to grant rights only to specific users. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}