--- 
title: Option 08 - «project Management - Calendar» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-08.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: 53d75659e3b2d1778ccbf72c6c8a3c3cd352375027fe6781265f818220a08eb9 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «project Management - Calendar» (module IC for project management). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You have to develop a module for project management. The project consists of a set of tasks, each of which has a contractor and the period of performance. The task has a certain duration, the start date and end date. The information system should allow accounting tasks with a time control of execution of tasks according to the workload and availability of performers. In other words, when you try to schedule a task so that the time of its execution falls on a weekend or holiday, the user should be issued a warning, blocking the action. Information about which days are holidays and weekends and is the basis for this module. Note that weekends can be national (holidays) and corporate (for one reason or another specified by the user). This module should allow to specify non-working days and they should be taken into account when planning work. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement the algorithms for working with a production calendar. On the basis of information about the standard working week, days, exceptions and gaps of working hours in the week/days (may be more) need to calculate the following information: 

* total duration of working time for a specified date range from ... to ..., including the borders (in hours/minutes); 
* date and end time of time period with specified beginning and a specified duration, given the weekend and after hours (for example, `57` working hours counting from `01.02.2017 `). 

Preferably, first create stubs for classes that implement algorithms and unit tests that checks the operation of the above functions (initially not running); then implement the logic to fix the work unit tests. 

The implementation should be done .net-libraries and to prepare unit tests for it (supplementing written in the beginning, for a more complete code coverage), to show the percentage of code coverage from unit tests (the more the better). The source of data on working days to make a substitute. 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. The calculation of the end date of the time interval based on user-entered start date and duration dependence (the component should use the library from the task above). 
2. The display of days of the selected month in a table, where the highlighted weekends. 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form that will display a table with the days of a particular month (in the calendar). When you click on a table cell in the popup window (or next) you should see a form where you can select the type of day (standard exception), to define a set of time intervals and recurrence, if you have selected the» «exception. 

Calendar view: 
![Calendar view](/images/pages/guides/tasks/full-stack/case8-task2-sample1.png) 

The approximate form of the edit of the day: 
![Example edit form](/images/pages/guides/tasks/full-stack/case8-task2-sample2.png) 

Shown and edited data must be stored in the javascript object. 


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

1. Print information about the working days exceptions in which the duration of working time less than 4 hours. 
2. To output the total number of working hours for a specified month. 
3. Write a stored function by the passed primary key recurring day exception and the date determines whether this day is the exception passed to the date (including repetition). 
4. Based on the functions from step 3 to write a query that calculates the number of days of exclusions in the specified month. 

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

1. Filling the end date of the recurrence of the day-the exception, if the user had completed the number of repetitions (end date – date of the last repetition), and the number of repetitions, if the user was filled in the end date. 
2. The formation of entities to describe the working time of the day-exceptions/the day of the week according to the model (in the form of editing control, editing working time intervals in the form of serialized text value and save this value in nekrasove property of the data object.

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical task №7 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

Implement editing of calendar days on a form with a table displaying the month and list days-exception (WOLV). Transition to must be made with the edit form of the calendar, which is the only field to fill in the calendar name and the button press which is only available after you save the form. On the edit form of calendar days should be able to add a day exception or by clicking on any day in the table, either by clicking the» «to Add to the list of days of exclusions. In addition, it should be possible to edit and delete existing days-exceptions to the table of the month and list days-no exceptions. 

To implement a custom edit form standard weeks calendar on the form must be a list of days of the week (Monday to Sunday); when you click on a specific day you should see a list of time intervals of the day, which can be edited. 

### to deliver results 

Modified UI logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - the Functional subsystem Flexberry 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Git for Windows 

### Job 

The implemented application to configure the subsystem powers. Users must start the application administrator. Authorization form-based. To create a hierarchy of roles, add transactions to view the data and to grant rights only to specific users. To configure the roles and assign these roles to users: 

1. The role `Администратор` – edit the calendar. 
2. The role `Пользователь` – only view of the calendar. 

To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}