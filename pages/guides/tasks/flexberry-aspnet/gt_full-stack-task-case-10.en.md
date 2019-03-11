--- 
title: Option 10 - «pre-trial appeal» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-10.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: d59dedf4bb4e601fc409597bc732f1c1e3b5d6580e1a4572b4eda33a18904e1c 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «pre-trial appeal to the Executive authorities» (module IC for state agencies). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

The applicant has the right to file a complaint against the actions or inaction of the authorities providing public services. For example, the place of provision of services refused to accept the documents or to provide the service, violated the terms of service or require additional documents or payment. 

A citizen makes a complaint to public authority (office). The complaint is made in the same office which rendered the state service. Decorated the complaint sent to the Federal state information system of state appeals (hereinafter FGIS). FGIS provides TO the process of pre-trial (extrajudicial) appeal of decisions and actions (inaction) committed in the provision of public and municipal services. 

You want to create a separate module of the application to a public authority (agencies). This module should cover the need of fixation of information about complaints of citizens to public services. It is known that: 

* the complaint is always the author (applicant); 
* compiled for a specific public service and the office of her оказывающее; 
* each Department has its own set of rendering state услуг; 
* the complaint can be attached документы; 
* the complaint should be handled by a specialist. Specialist records the date and complaint number, and monitors the processing status of the complaint. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement a algorithm generate a unique ID for the complaint TO FGIS on the basis of the following input data: 

* The ID of the Department. Strictly 11 digits. For example, 10000000959 (Agency archives). 
* Date of receiving the complaint. 
* The number of complaints. The sequence number of the complaints in the application. Every year, the counter is reset to zero. 

The algorithm of formation of this unique code you need to invent your own. Code should be built in such a way that it should be implemented the potential to calculate the complaint in the application (from a known list), without storing the correspondence between the complaint and the code TO FGIS. The algorithm follows in the comments to make a description of the selected variant of the algorithm, because valid different ways to generate the code. 

The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. Logic input search query unique code and use the library from the previous paragraph for the release of information about the complaint (field values button, the unit displays the result). In this case, instead of the ID office should display its name. 
2. The logic to obtain the unique code on the complaint number, the date of receipt of the complaint, the Department ID field (input, button, block, displaying results). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form that needs to display the current status of complaints through the city across agencies. 

Requirements to the form: 

1. The form must display the names of the departments (minimum of 3). 
2. Under the name of each Department should display your list of complaints (number of entries in the list from 0 to N). 
3. Should be able to view full details of the complaint: 

* Number of complaints 
* Date of admission 
* Applicant (name) 
* Status (Поступила; In обработке; Перенаправлена; Обработана; Closed) 

4. Must be able to move the complaints from one Agency to another. 
5. Complaints with a status of Received ««and»» Redirected to highlight. 
6. Each Agency must display comprehensive status. Comprehensive status is the sum of the statuses of all complaints: 

* If there is at least one complaint «and» has Received no complaints with a status of» «Redirected, then display comprehensive status = New» qmo. 
* When transferring a complaint to another Agency to change its status to Redirected» «complex the Agency status should change to Urgent» qmo. 

7. Complaints with statuses «Processed»,» «is Closed you cannot move.

### to deliver results 

Implemented project to place in a repository on GitHub in the form of embedded pages [GitHub Pages](https://pages.github.com/) that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - the Database 

Will be required for implementation: 

* Microsoft SQL Server 
* Microsoft SQL Server Management Studio 
* Git for Windows 

### Job 

For the specified subject area to implement the database, fill the database with scripts (at least 5 records per table). Implement scripts to create constraints, indexes. To attach the scripts to create the database and fill the backup. 

Prepare SQL scripts to obtain the following information: 

1. List of departments indicating the number of received complaints over the past year. The list should be sorted by Department name (ascending order). 
2. Withdraw the rating of agencies according to the average time of complaint handling. Work with the application is considered completed if the recorded text of the decision, dated the date of the signing of the decision, status = `Закрыта`. 
3. Output the top N most loaded departments for a specified period of time. The workload of the Department consists of a number of complaints with statuses `Поступила`, `В обработке`, `Перенаправлена`. 
4. Bring one staff member from each Department (name, position, telephone, name of the Department), having more of all processed complaints in the last month. 
5. Display the percentage of all complaints in the morning (9 to 13) and day (14 to 18) in the past day. 

### to deliver results 

Implemented scripts commit in the GitHub repository. A link to the repository to provide the teachers of the course. 

## Practical assignment # 4 - Designing the IP 

Will be required for implementation: 

* Flexberry Designer 

### Job 

Draw a UML diagram for the designated subject area. The composition of the diagrams is determined by the trainees, but should provide a full description of the subject area. 

### to deliver results 

The result is uploaded in the format of a CRP stage of Flexberry Designer. Stage (a file with extension \*.CRP) should commit to the repository on GitHub, the link to provide the teachers of the course. 

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

1. To implement a business server that will be used .net library for affixing the unique identifier TO FGIS for a new complaint. 
2. To add a stored field in a class of offices that will contain the number of complaints in this Department. Recalculation of this field to implement the business server. 
3. To implement a business server, which will verify whether complaints only during the work of the Ministry (from 9 to 13, from 14 to 18). Outside the operating mode of the Department the establishment of a complaint is prohibited. 
4. To implement a business server, which will check that the complaint for the service from the applicant have been recorded (not created duplicate record). 
5. To add a stored field in the class complaints, which will be a string containing the current status of the complaint, the date of receipt, name of applicant, applicant's telephone (if available), email of the applicant (if available). 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical task №7 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

Tie the previously implemented ascx-components to the data from the database. 

To realize the integration of forms for transfer cases from one Department to another with the business logic of the application. On the form controls with the choice should be taken from the database (through ORM and Lookup) office staff. 

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

1\. The implemented application to configure the subsystem powers. 
Create the following roles: 

* `Администратор`. Available to the administrator all operations on all objects, including: 

* user list 
* the list ролей; 
* the list классов; 
* list of the audit (see paragraph 3). 

Remove complaints available only to the Administrator. 
* `Руководитель`. The user role `Руководитель` all available data operations in addition to configuring users, roles, classes, audit, remove complaints. 
* `Оператор`. The operator can work only with those complaints which relate to his Department. The operator cannot view the list of employees of another Department. The operator can view a list of offices, but does not have rights to edit the records. 
* `Все пользователи`. Only list complaints in read mode. 

2\. Authorization form-based. 

3\. To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}