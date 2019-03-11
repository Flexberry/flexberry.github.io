--- 
title: Option 8 - «weather Forecast» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-08.html 
lang: en 
autotranslated: true 
hash: 59a99d01dcf111293594561fb4972fa38880fea6670bf3317ba07dd9b1e3f9aa 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «weather Forecast» (IP to conduct forecast the weather for different areas). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

Every 2 hours specialist meteorological service runs the algorithm for calculating the forecast for the given territory. The algorithm can give extreme values, which are adjusted in manual mode (for example, -150 ° C in June). Users of weather forecasts are accessed via the Internet and can choose the area for which the forecast is based. Available forecast for the month ahead from current date. The user can subscribe to a notification E-Mail about an upcoming natural disaster, specifying the time interval prior to and territories. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement an algorithm simulating the operation of the system of weather forecast. The input will need to submit a site id, the output should be a list of forecast parameters for each day for 30 days relative to the current date. The weather forecast settings: 
* Date 
* Air temperature in degrees Celsius 
* Cloud 
* Weather phenomena 
* Pressure in mm Hg 
* Wind direction 
* Wind speed 
* The wind speed in the gusts 

The forecast should be uniform, i.e. weather changes in various parameters should be relatively smooth, without abrupt jumps in different directions. The parameters must be consistent with each other, i.e., 28 degrees and snow do not occur. Add 5% deviation algorithm, which will give a sharply arbitrary, but consistent evidence of individual settings that you will need to adjust forecasters in manual mode. 
The implementation should be done .net-libraries and to prepare unit tests (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form where the user can easily see the forecast for several possible intervals: 7 days, 14 days, 30 days. The intervals need to switch tabs. 7 days are issued in the form of a single line. 14 days - two rows for 7 days in a row, 30 days - 3 lines, 10 days. Each cell containing a date must include all the information about the weather in a short form. By clicking on a cell should open a popup form with detailed information of each parameter when the weather is signed. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented form where the user can easily see the forecast for several possible intervals: 7 days, 14 days, 30 days. The intervals need to switch tabs. 7 days are issued in the form of a single line. 14 days - two rows for 7 days in a row, 30 days - 3 lines, 10 days. Each cell containing a date must include all the information about the weather in a short form. By clicking on a cell should open a popup form with detailed information of each parameter when the weather is signed. To provide for the editing mode that is available to the user of the meteorological service. In edit mode you can change the values of the weather parameters. 

In EmberJS-utilities need to implement an algorithm simulating the operation of the system of the weather (see # 1). 

The display of weather is required to: implement a component that receives EmberJS model. EmberJS-model, should be available from EmberJS-utility model hook in the routes of the form on which the component is added.

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

1. Display the top 5 territories where the least rainy days 
2. Display information about the average temperature on that date for all territories 
3. Display information about the user who adjusts more often than other data 
4. Display information about the temperature records for the specified site during the observation 
5. List the areas where there was no rain for 30 days in a row and Vice versa, precipitation does not stop for 30 days in a row 

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

1. To implement a business server that will control that in the forecast there are no conflicting options (for example, snow 25, etc.). 
2. To implement a business server that will instead of deleting the objects in the system translate them into state of the archive object (to fine-tune the forecast algorithm). 
3. To implement a business server that will control that in the tracking list cataclismo added territory, where there is still no similarly, for the removal of the territory have to be in it. 
4. To add a stored field in the class Employee, which will be the name one line from Surname Name and Patronymic. 
5. To add the string is not a stored field that contains all the necessary information about the forecast for a specific day. 

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

The implemented application to configure the subsystem powers. The staff of the meteorological service must make application administrator, all other users are logged on their own (simplified scheme). Authorization form-based. To create a hierarchy of roles, add transactions to view information about the internal structure of the forecast. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}