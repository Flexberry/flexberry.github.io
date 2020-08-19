---
title: Option 21 -» «much
keywords: Tasks
sidebar: guide-tasks_sidebar
toc: true
permalink: en/gt_flexberry-ember-case-21.html
lang: en
autotranslated: true
hash: fd661c485f7653a6b9fdbb8cc7393e6deac8290c57e20f8e87e9f1c97a3a72b5
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember
---

## Job

In the framework of the practical part of the course you will have developed end-to-end example: application «shift task teams Gorvodocanal» (IP for the automation of accounting applications and perform work on it).

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, the database client technologies, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications.

## A General description of the subject area

Dispatch water utility receives from the residents for the treatment of accidents in the city on the basis of emergency applications who include in their replacement jobs mobile brigade. Each application has an address and includes a few works (e.g., digging, brewing pipe, to bury). Each operation has a planned and actual duration in hours. The job change appears in the form of a list of items, one item (row) shift tasks can be performed one or more works on one application.

Specifications:

* The application is realized as [SPA-application](https://ru.wikipedia.org/wiki/Одностраничное_приложение) with bakenda on the C# and EmberJS on the frontend.
* Data is stored in the database Postgres.

## Practical exercise No. 1 - server development (C#, .NET ASP.NET)

Will be required for implementation:

* Microsoft Visual Studio 2017
* Git for Windows

### Job

You want to implement an algorithm for calculating the optimal distribution of work on replacement jobs, mobile crews within the day. Each application has its own importance from 0 to 4 (most important), every job in the application has a planned duration of the run.
Between the points of applications of the brigade moved 1 hour. An incomplete application is void.

The input to this algorithm is:

* An array of applications: number, importance
* An array of works: the name of the planned duration of the execution number of the application
* An array of teams: name, start time shift, end time shift

The output should be obtained two-dimensional array in which each row represents one team, and each element in the row by the number of bypass requests.

Implementation should be done in the form .Net-libraries and to prepare unit tests for it (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better).


### The provision of performance on the test

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course.

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery)

Will be required for implementation:

* Code editor for client development: Visual Studio Code
* Git for Windows

### Job

With use of opportunities HTML, CSS, JS, jQuery mark up a form where the user can specify the mobile brigade, a date and obtain information about its replacement assignment for the week. The form should remind you hourly calendar in terms of weeks. In each cell of the table should include a line of replacement jobs that have been or will be executed by the brigade.

### The presentation of the results

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course.

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS)

Will be required for implementation:

* Code editor for client development: Visual Studio Code
* Git for Windows

### Job

You want to implement EmberJS application, which will be implemented form where the user can specify the team and date and obtain information about its replacement assignment for the week. The form should remind you hourly calendar in terms of weeks. In each cell of the table should include a line of replacement jobs that have been or will be executed by the brigade. Under the calendar should display a list of offer applications for units (component Ember.js).

In the form of Ember.js-utilities need to implement an algorithm for calculating the optimal distribution of applications by teams within shifts (see # 1).

You want to implement the component, passing it the id brigade and date and it will display a list of applications that can be executed by the team with the greatest benefit for Utilities. To use a component in Ember.js-the tool that implements the algorithm for calculating the optimal distribution of bids.

### The presentation of the results

Implemented project to place in a repository on GitHub as a project Ember.js and published the app to GitHub Pages, which allow you to view the finished result. A link to the published repository and thus the project to provide teachers of the course.

## Practical assignment No. 4 Database

Will be required for implementation:

* Postgres
* pgAdmin
* Git for Windows

### Job

For the specified subject area to implement the database, fill the database with scripts (at least 5 records per table). Implement scripts to create constraints, indexes.

Prepare SQL scripts to obtain the following information:

1. Display top 5 locations with highest number of accidents
2. Print information about fixed number of applications each team in descending order
3. Display information about the average time taken to perform each type of work
4. Display information about the total work time of all teams by months
5. Bring the top 5 teams, most are idle


### The presentation of the results

Implemented scripts to create the database and fill a backup of the database commit in the GitHub repository. A link to the repository to provide the teachers of the course.

## The practical task №5 - IC Design

Will be required for implementation:

* Flexberry Designer

### Job

Draw a UML diagram for the designated subject area. The composition of the diagrams is determined by the trainees, but should provide a full description of the subject area.

### The presentation of the results

The result is uploaded in the format of a CRP stage of Flexberry Designer. Stage (a file with the extension *.CRP) should commit to the repository on GitHub, the link to provide the teachers of the course.

## The practical task №6 - the Object design and generation applications

Will be required for implementation:

* Flexberry Designer
* Microsoft Visual Studio 2017
* Postgres
* Git for Windows

### Job

To accomplish the object design and the generation of Ember-application for the described subject area. As a basis to use the previously implemented the UML model. Generation of the application and the database must be executed by means Flexberry Designer, the application must meet the requirements and be fully functional. Submission must be high quality customized signatures to classes and attributes on the forms should be adequate. The list of forms and attribute composition must correspond to the subject area and cover all of the required business function.

### The presentation of the results

The generated application and the scripts to create the database should be put in a repository on GitHub. To provide teachers with a reference to the repository.

## Practical exercise No. 7 - Development of business logic applications

Will be required for implementation:

* Flexberry Designer
* Microsoft Visual Studio 2017
* Postgres
* Git for Windows

### Job

In generated using Flexberry Designer application is required to implement the following business logic.

1. To implement a business server that will control that team is not getting more than one WorkOrder for a change. Should be generated in the event that evidence of non-events.
2. To implement a business server that will not allow the removal of shifts with shift assignments that had been performed.
3. To implement a business server that will control indicated whether all the actual values for execution of works in shift job and generates event-exception otherwise
4. To add a stored field in the class Line Replacement jobs, which will reflect the full information about it: date, team, address
5. To add a stored field application Status, which will show whether carried out works on elimination of the accident.

### The presentation of the results

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
On the form to control whether data should be taken from the database (through ORM and Lookup). Fill data shall also work with the saving.
You want to configure a beautiful look for all forms of application. To improve the visual design theme. Implement shortcuts on the desktop application for quick access to frequently used functions.
Implement additional forms, which will display the results of a query of the jobs database.

### The presentation of the results

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

### The presentation of the results

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}