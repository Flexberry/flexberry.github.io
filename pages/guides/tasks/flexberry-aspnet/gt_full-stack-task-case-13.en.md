--- 
title: Option 13 - «Aggregator contacts» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_full-stack-task-case-13.html 
folder: guides/tasks/ 
lang: en 
autotranslated: true 
hash: 6fac101deb397f84dff48b39eafdd1ed0faed65ea9ddec90ee050a55ba6a8df0 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Aggregator contacts». 
The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to create an application that allows you to merge contacts (friends) from various social networks into a single list. The app provides some features, including: 

* If the current user has contacts (friends) from social networks, which are also registered in the app, the app gives the ability to add to contacts (friends) his profile in alternative social networks (if it has not been added previously). 
* Search of possible acquaintances on the basis of the analysis of the graph of contacts. 

Specifications: 

* The application is implemented as a ASP.NET WebForms application. 
* Data is stored in the database MSSQL Server. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Git for Windows 

### Job. Part 1. 

You want to implement an efficient algorithm for matching user profiles. The input is 2 user profile, each of which is an array of strings, each string represents one of the following values (in this order): 

* Login 
* E-Mail 
* Last name 
* Name 
* Patronymic 
* Floor 
* Date of birth 
* City 
* Country 
* Website 

Any of these fields may be absent, some of the fields may not be in its place. The algorithm must output how many % the two specified profile correspond to each other. 
The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better). 

### Job. Part 2. 

Implement a simple ASP.NET WebForms-application (at this stage, no database), which contains the components (. ascx controls), realizowa: 

1. The logic of matching two users using the library from the 1st part of the assignment (the field for entering values, the button, the unit displays the result). 
2. Display a table of the common list of friends (the table in which the number of columns corresponds to the number of the connected social networks, and the rows specify the username of the contact in the respective social network).

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form that will display a list of contacts in the form of "dies". Each plate should contain all available information about the contact, but the information should appear in a popup on click block. Contacts should not be duplicated (the same contact from different social networking appears on one die). The form also needs to be a block with information about the total number of contacts. 

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

1. Output the top N users that has the maximum number of contacts. 
2. Output the common name of users. 
3. Output statistics the number of users for each type of social network in the context of gender and age groups. 
4. To obtain the average number of contacts for the user in the application. 
5. Display the percentage between the sexes of the users in the application. 

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

In generated using Flexberry Designer application you want to implement: 

* The page which would be printed results of the same 5 statistical queries implemented using SQL in task 3, only this time the queries need not be implemented in the SQL language, and using Flexberry ORM tools LINQ provider and LoadingCustomizationStruct. 
* The business logic of the application using Flexberry ORM and business servers. 
* Implement a business server, which will check the uniqueness of user logins for each of the types of the connected social networks. 
* Implement a business server, which will cause the logic to retrieve contacts from the connected to the current user's social networking and updating objects in the database. 
* Implement a business server that will be used .net-library and link contacts from different social networks. 
* Implement a business server that when you remove a user or contact marks it as irrelevant and hides from the application interface. 
* Add Nechranice field in the user class, which will show the total number of contacts of the user., the number of students. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical task №7 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2015 
* Microsoft SQL Server 
* Code editor for client development: Visual Studio Code, Atom, Sublime Text, etc. 
* Git for Windows 

### Job 

Tie the previously implemented ascx-components of assignments # 1 and # 2 to the data from the database. To place these components on the application forms.
To implement the logic, user registration via a social network profile and bind additional profiles from other types of social networking. Implement API call for social networks to obtain information about the user's contacts. 
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

The implemented application to configure the subsystem powers. Users must be registered independently and to role assignment. Authorization form-based. To create a hierarchy of roles, add transactions to view the data about users. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}