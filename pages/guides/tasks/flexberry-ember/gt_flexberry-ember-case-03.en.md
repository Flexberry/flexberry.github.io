--- 
title: Option 3 - «Network educational program» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-03.html 
lang: en 
autotranslated: true 
hash: d3dd8fbe6ce35c870d8985bd069a29951720d352d96991471812c0a84502897c 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Network educational program». 
The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to develop an application to control the network educational programs. The student goes to basic school, but in the course of learning may take individual modules (similar to semesters) to other organizations (for example, at another University, or industrial company) with which the base organization has agreements. 
We assume that the master's program is considered, i.e. 4 modules. To pass the module in another organization, the student indicates their preference: first, second and third priority. 
Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement an algorithm for checking the correctness of the entered number agreement. The number format of the contract must be independently invented, e.g., `00ААА0000А0`. 
The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form that will display the list of choices available in educational programs for the entire period of study. Each semester must be a separate unit indicating the number of semesters and date interval (blocks semesters go from top to bottom). Within each block of the semester are available for student blocks educational programs with a brief description (placed horizontally at the same level). From semester to semester, the number can change. The student should develop these programs in accordance with their preferences (by clicking and dragging the blocks with a brief description of the educational program with the mouse or by using the buttons on the interface) - unit educational program located on the left has the highest priority, the block on the far right is the smallest. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of embedded pages [GitHub Pages](https://pages.github.com/) that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented form, which will display a list of choices available in educational programs for the entire period of study. Each semester must be a separate unit indicating the number of semesters and date interval (blocks semesters go from top to bottom). Within each block of the semester are available for student blocks educational programs with a brief description (placed horizontally at the same level). From semester to semester, the number can change. The student should develop these programs in accordance with their preferences (by clicking and dragging the blocks with a brief description of the educational program with the mouse or by using the buttons on the interface) - unit educational program located on the left has the highest priority, the block on the far right is the smallest. 

In EmberJS-utilities need to implement an algorithm for checking the correctness of the entered number agreement (similar to the algorithm from task 1). 
To develop in the form of an Ember component, the controls that implement: 
1. The logic of check of a correctness of entering of the contract using the library of tasks above (field values button, the unit displays the check result) 
2. Display brief information on the contract for the listed number (field values button, the unit displays the result). In this task summary information on contracts is enough to have a code, not to take from the database. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of EmberJS project and published the app to GitHub Pages, which allow you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical assignment No. 4 Database 

Will be required for implementation: 

* Postgres 
* pgAdmin 
* Git for Windows 

### Job 

For the specified subject area to implement the database, fill the database with scripts (at least 5 records per table). Implement scripts to create constraints, indexes. To attach the scripts to create the database and populate, Bacup database. 

Prepare SQL scripts to obtain the following information: 

1. List of students who chose the first priority for the specified organization. 
2. Withdraw the rating of associations is built on the principle: for each student 1-th priority is given 3 points, 2nd priority - 2 points, 3rd priority - 1 point. 
3. For each student, output the sign of the zodiac 
4. On a specified date to bring the list of educational organizations which have a valid contract. 
5. For the student to bring all his preferences for all modules. 


### to deliver results 

Implemented scripts commit in the GitHub repository. A link to the repository to provide the teachers of the course. 

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

Execute object design and generation of ASP.NET applications for the described subject area. As a basis to use the previously implemented the UML model. Generation of the application and the database must be executed by means Flexberry Designer, the application must meet the requirements and be fully functional. Submission must be high quality customized signatures to classes and attributes on the forms should be adequate. The list of forms and attribute composition must correspond to the subject area and cover all of the required business function. 

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

1. To implement a business server, which will verify that the dates of the semesters in blocks do not overlap. 
2. To implement a business server that will be used .net-library for verification of the Treaty. 
3. To implement a business server that will check for the absence of one educational program in two or more semesters. 
4. To add a stored field in the student class that will contain the number of selected educational programs for all semesters. Recalculation of this field to implement the business server. 
5. To add a stored field in the class student, which will be a string containing the selected educational program with a priority on semester (sort from high priority to low). Example: Semester number: 1-program 1 2-program 12, etc. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Code editor for client development: Visual Studio Code 
* Postgres 
* Git for Windows 

### Job 

To realize the integration of the form to enter data about the selected student educational programs with the business logic of the application. On the form controls with selection, values should be taken from the database (through ORM and Lookup). Data input on the selected program and the priority need to work with saving to the database. 

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

The implemented application to configure the subsystem powers. Users must start the application administrator. Authorization form-based.

To create a hierarchy of roles, add transactions to view/modify/delete data on the programs and their student's choice. To configure the roles and assign these roles to users. 

To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}