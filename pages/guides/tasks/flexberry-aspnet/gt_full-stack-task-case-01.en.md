--- 
title: Option 01 - «Electronic gradebook» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-01.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: e348ef47a4a8780c49439c00f21c5778042b6ca6e677e63c8cd6cd9d0afd1ae3 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Electronic gradebook» (module on IP for universities). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to create a separate application module for universities. This module should cover the need of fixation of information about students and their academic achievement. It is known that students learn in groups, each group attached to any faculty. Each student must take tests and exams during the session. Information on these activities is recorded in the application. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement a algorithm generate a unique, alphanumeric code for the grade book, based on the following input data: name of student, date of birth, group number. The algorithm for generating this code, you need to invent your own. The length of the alphanumeric code must not exceed 8 characters. Code should be built in such a way that it should be implemented the potential to calculate a particular student (from a known list), without storing the correspondence between the student and code. Examples of alphanumeric code: *ШШШШПВВ4*; *А665Б44В*. The algorithm follows in the comments to make a description of the selected variant of the algorithm, because valid different ways to generate the alphanumeric code. 

The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. The logic input of the search query alphanumeric code and using the library from the previous paragraph for the release of information about the student (field values button, the unit displays the result). 
2. The logic of obtaining the alphanumeric code for the specified information full name of student, date of birth and group number field (input, button, block, displaying results).

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form that will display the location of the desks in the auditorium of the University. Desks can be moved between fixed cells (in accordance with the actual location of the part to a specific audience, which is the exam). Each row can be 0, 1 or 2 students. The form also needs to be a block with information about the room number, teacher and subject in which the exam is held. For exposure assessment the student can click on his name or image and select the rating in beautiful a pop-up list. 

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

1. List the teachers that put a maximum on the number of excellent marks. 
2. Output the top N most students are bad students. 
3. Display the subjects for which most of the debtors during the specified period. 
4. Withdraw the rating of faculties according to the average score of the students. 
5. To obtain an average score for each student of the University for years. 

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

1. To implement a business server, which will verify the grading for the exams only during the session (January and June). Outside the session, the setting and correction of assessments is not permitted. 
2. To implement a business server that will be used .net library for affixing alphanumeric code number grade book. 
3. To implement a business server, which will verify that the student is not placed in the new assessment exam, which had already marked as accepted (not created duplicate record). Thus, the edit set earlier assessments during the session for example. 
4. To add a stored field in the class group that will contain the number of students in this group. Recalculation of this field to implement the business server. 
5. To add a stored field in the class group, which will be a string containing the faculty name of the group, the number of students. 

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

To realize the integration of forms for Seating of students in class with the business logic of the application. On the form controls with the choice should be taken from the database (through ORM and Lookup) group students. Affixing assessments should also work with saving to the database. 

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

The implemented application to configure the subsystem powers. Users must start the application administrator. Authorization form-based. To create a hierarchy of roles, add transactions to view the data about grades and to grant rights only to teachers. To configure the roles and assign these roles to users. 

To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}