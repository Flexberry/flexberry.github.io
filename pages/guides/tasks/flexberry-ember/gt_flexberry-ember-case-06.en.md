--- 
title: Option 6 - «permit system in the office» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-06.html 
lang: en 
autotranslated: true 
hash: 408ae37dd050c4bdeae53c2d70ef6de74bb55ffe173685cb496449c1e9741758 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «permit system in the office» (is to automate the account of movement of employees). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

In the office of the company X introduced a permit system based on personal bandwidth maps. Using the card, the employee can go to any floor in the office, in public places (kitchen, peregovori, etc.) and only in his office. Required to develop an information system, which will include information on issued personal bandwidth maps, and information about staff movements. All doors of rooms in which working staff can be in 2 States: `замок открыт` and `замок закрыт`. During the working day, if the office employees are usually the door is in a state `замок открыт`. Thus, when moving between offices of the office staff do not always use the card. At the entrance and exit of the building office all staff members are required to use the card individually, it allows you to record the time of arrival of the employee in office and his care. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement an algorithm for computing the friendly relations of employees on the basis of information about a shared meal or arrival and departure from the office. If several employees throughout the week I left for lunch with a difference of no more than 5 minutes and was coming back from lunch with the same small difference is not less than 3 times, they are considered friends. If employees come into the office in the morning and leave in the evening at the same time with a difference not more than 5 minutes no less than 3 times, it is also considered to be friends.
The input to the algorithm is an array of structures that contain: 

* Name of employee and id 
* Array of tuples: date-time of arrival at the office, and date-time of departure from office 

The output should be an array of tuples, each of which contains information about the friendly relations between your employees. 
The implementation should be done .net-libraries and to prepare unit tests (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form where the user can specify the name of the employee, year, month, and receive information about his office visits with the granularity of each day. The shape should resemble a monthly calendar, i.e., the table must have 7 columns (mon, Tue, Wed, Thu, Fri, sat, sun) and week in the rows. In each cell of the table must show the number and time of arrival-departure at the office (employee may visit the office). 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented form where the user can specify the name of the employee, year, month, and receive information about his office visits with the granularity of each day. The shape should resemble a monthly calendar, i.e., the table must have 7 columns (mon, Tue, Wed, Thu, Fri, sat, sun) and week in the rows. In each cell of the table must show the number and time of arrival-departure at the office (employee may visit the office). Under the calendar should display a list of likely friends of the employee (EmberJS-component). 

In EmberJS-utilities need to implement an algorithm for computing the friendly relations of employees on the basis of information about a shared meal or arrival and departure from the office (see # 1). 

You want to implement the component, passing it the employee id, year and month, and it displays a list of employees who may be friends of the staff member. To use EmberJS component-a utility calculation algorithm of friendly relations.

### to deliver results 

Implemented project to place in a repository on GitHub in the form of EmberJS project and published the app to GitHub Pages, which allow you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical assignment No. 4 Database 

Will be required for implementation: 

* Postgres 
* pgAdmin 
* Git for Windows 

### Job 

For the specified subject area to implement the database, fill the database with scripts (at least 5 records per table). Implement scripts to create constraints, indexes. 

Prepare SQL scripts to obtain the following information: 

1. Display top 5 employees who spend in the office most of the time 
2. Display information about the number of opening-closing doors on each floor in decreasing order 
3. Print information about the working days, maximum, average and minimum number of employees who visited the office 
4. Display information about the total work time of all employees in the office by months 
5. Display the top 5 offices of which most are idle (the other bowl is locked) 

### to deliver results 

Implemented scripts to create the database and fill a backup of the database commit in the GitHub repository. A link to the repository to provide the teachers of the course. 

## the Practical task №5 - IC Design 

Will be required for implementation: 

* Flexberry Designer 

### Job 

Draw a UML diagram for the designated subject area. The composition of the diagrams is determined by the trainees, but should provide a full description of the subject area. 

### to deliver results 

The result is uploaded in the format of a CRP stage of Flexberry Designer. Stage (a file with the extension *.CRP) should commit to the repository on GitHub, the link to provide the teachers of the course. 

## Practical task №6 - the Object design and generation applications 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2017 
* Postgres 
* Git for Windows 

### Job 

To accomplish the object design and the generation of Ember-application for the described subject area. As a basis to use the previously implemented the UML model. Generation of the application and the database must be executed by means Flexberry Designer, the application must meet the requirements and be fully functional. Submission must be high quality customized signatures to classes and attributes on the forms should be adequate. The list of forms and attribute composition must correspond to the subject area and cover all of the required business function. 

### to deliver results 

The generated application and the scripts to create the database should be put in a repository on GitHub. To provide teachers with a reference to the repository.

## Practical task №7 - Development of business logic applications 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2017 
* Postgres 
* Git for Windows 

### Job 

In generated using Flexberry Designer application is required to implement the following business logic. 

1. To implement a business server, which will monitor that the officer is not out of the building, if he was not in it and Vice versa - is not included, if not out. Should be generated in the event that a malfunction of the equipment. 
2. To implement a business server that will not allow the removal of objects with information about the passage through the map (it was impossible to conceal the crime). 
3. To implement a business server that will shut down control if the staff of the office door before leaving. If an employee of the office leaves the latest out of the building and the door of the Cabinet is in a state `замок открыт`, you need to generate event information about the employee and the office number. 
4. To add a stored field in the class Employee, which will be the name one line from Surname Name and Patronymic. 
5. To add a stored field in the Suite class sotrudnik, which will signal that the office is an employee or not. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Postgres 
* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

Tie the previously implemented EmberJS additional forms and components of data from the database. 
To transfer the generated application and implement the integration of forms of assignments # 3 and the business logic of the application. 
On the form controls whether the data should be taken from the database (through ORM and Lookup). Fill data shall also work with the saving. 
You want to configure a beautiful look for all forms of application. To improve the visual design theme. Implement shortcuts on the desktop application for quick access to frequently used functions. 
Implement additional forms, which will display the results of a query of the jobs database. 

### to deliver results 

Modified UI logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors.

## Practical assignment No. 9 Functional subsystems Flexberry 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2017 
* Code editor for client development: Visual Studio Code 
* Postgres 
* Git for Windows 

### Job 

The implemented application to configure the subsystem powers. Employees and cards need to wind up by the application administrator. Authorization form-based. To create a hierarchy of roles, add transactions to view information and to grant rights to administrators only. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}