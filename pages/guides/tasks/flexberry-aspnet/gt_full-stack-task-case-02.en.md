--- 
title: Option 02 - «Statements of applicants» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-02.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: c59474d65e6fcdce0a8aff316551706b505617c07641db72a155b8131c211739 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Statements of applicants» (module on IP for universities). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to create a module of accounting statements of applicants for admission to the University. Each person in one year may submit one application to one institution, which may indicate multiple occupations, which wants to do. These majors prioritizers (some want more, some less). All statements are broken down into different types: higher education (HE), vocational education (sve), personnel of higher qualification (Postgraduate). The system records various information about the applicants, including address. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement the algorithm of composing formatted in the order of the row address of the applicant on the basis of text input. The output line should contain the address in the format: code, state or province, city, street, house, building, apartment. An alternative option for rural settlements: Index, region or area, district, town, house, building, apartment. The input string may not contain all the address elements, in this case, you must substitute the default value (for example, Perm region, if the region was not specified), the order of address elements in the input string can be any, before each item indicates its type, for example, `г. Perm, St. Красная`, `ул. Blue, d. 45, kV. 111, mountains. Пермь`. If the address cannot be mapped, output the error message. 

The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. Logic input addresses, where each address element is entered in its own field and using the library from the preceding paragraph for the issuance of information in a uniform format (field values button, the unit displays the result). 
2. The logic of getting the full address on one input string and using the library from the preceding paragraph for the issuance of information in a uniform format (field values button, the unit displays the result). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

You have to develop a web interface for entering data about the applicant's application. To do this: 

* A hat to put the necessary private fields of application. 
* His masters and detaily (person / area) to place below the tabs. 
* Tab with career choices should allow you to drag and drop (Drag'n'drop) the appropriate specialty from the list in the list of the specific student. In this case from the General list to remove them, so you can't choose twice. 
* List of selected specialties to display in the header, separated by commas. 

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

1. Display the TOP 10 areas by number of applicants 
2. Bring specialty, which filed the largest number of applications 
3. List the areas and the average number of applications over the years 
4. List of specialties and their» «rating (based on priority) 
5. Bring the average age of University entrants on specialities 

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

1. A new application must have a filing date within the admission campaign from may to July inclusive (implement validation at the business server). 
2. Number when creating a new application must be placed automatically. Format: reduction of type zajavlenie («IN», and «SPO» or» «ASP), dash, sequence number of the statement. 
3. When saving statements to verify that he has no priorities with the same numbers. There shouldn't be gaps in the numbers of priorities statement (numbers must be consecutive from 1 to n). 
4. To add a stored field Panoramirovanie in the essence of the Territory. Be recalculated after this field when you save the site. 
5. Add Nechranice field name in the entity Person. The value of the field to collect the values of the fields Surname, Name, Patronymic. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors.

## Practical task №7 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

1. Tie the previously implemented controls (ascx) for displaying/entering the territory hierarchy data from database. 
2. Implement a form to enter a statement of the applicant. Associate it with the data from the database and logic from the business servers. To display the priorities to use AGE to select individuals and specialties – Lookup. 
3. Implement additional forms to output reports - query results from the task database. 
4. To improve the appearance of forms, reload the theme. 
5. Add shortcuts to the desktop app to access frequently used functions. 

### to deliver results 

Modified UI logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - the Functional subsystem Flexberry 

Will be required for implementation: 

* Flexberry Designer 
* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Git for Windows 

### Job 

1. The implemented application to configure the subsystem powers. Users and roles must be got only by the application administrator. To create a hierarchy of roles, create users and assign roles to them. To add operations to create statements applicants and allow them only to members of the selection Committee. Authorization using forms. 
2. To configure the auditing subsystem. In the implemented application, all changes to data objects should be recorded in the audit. In the navigation menu (desktop application), add the types of audits that must only be shown to members of the administrators group. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}