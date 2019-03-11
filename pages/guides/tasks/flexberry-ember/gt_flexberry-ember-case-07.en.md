--- 
title: Option 7 - «Program guide» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-07.html 
lang: en 
autotranslated: true 
hash: bcb2b0b03ee31f1bde904b0cf1cb3dfdd83a076538ffd909397c57cb6b4e67c6 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Program guide» (is to conduct programs of television broadcasts of different channels). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

Specialist television is a program guide for multiple channels. TV shows can be periodic or "one-off". Periodic television programs have a start date and end date of the period of broadcast (which can be from one month to several years). "One-time" programs have given the time of broadcast, but individual issues can be time-shifted. After approval of the program, which takes place during the 7 days before the broadcast, the television program becomes accessible to a wide range of users - viewers who have Internet access. 
The viewer can add TV show to favorites, in this case, the transfer will be highlighted by a corresponding icon in the schedule. The user can subscribe to a notification E-Mail about the upcoming live TV program indicating the time interval before the broadcast. If the TV program is periodic, the notification will come every time before air. 
When filling out the TV program, you should consider quotas on advertising for every hour of airtime should be allocated 15 minutes of commercials. Viewers are shown a TV program in which the advertisement included in the broadcast of TV programs. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement the algorithm of adding commercial breaks in a TV program on the day. For every hour of air time should be 15 minutes of commercials. The longest ad unit should be no more than 5 minutes. The shortest is 1 minute. First ad units should be placed between gears. If this is not possible, transmission can be interrupted by advertising, but the gap can be at intervals that are multiples of 15 minutes from the start of the program (note that one transfer may be interrupted several times, 15 minutes is calculated in the mounting time TV shows without commercials). 
The input to the algorithm is an array of structures that contain: 

* Program titles 
* id TV shows 
* The duration of the show at the minute 
* Serial number of the TV 
* The start time of the show (if it is hardcoded, otherwise it can not be specified) 

The output should be an array of tuples, each of which contains information about the start time of the block of broadcast, TV program id or null (if this is an ad unit). 
The implementation should be done .net-libraries and to prepare unit tests (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form where the user can easily see the program schedules for multiple channels simultaneously. TV shows on different channels must be aligned on a time scale - if the shows are at the same time - this should be reflected. By clicking on the show should open a popup window with detailed information about the television show. TV shows from Favourites should have next to his name the corresponding icon. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented form where the user can easily see the program schedules for multiple channels simultaneously. TV shows on different channels must be aligned on a time scale - if the shows are at the same time - this should be reflected. By clicking on the show should open a popup window with detailed information about the television show. TV shows from Favourites should have next to his name the appropriate icon, by clicking on this icon shows you add or delete from favorites. 

In EmberJS-utilities need to implement an algorithm of adding commercial breaks in the program guide for a day (see # 1). 

You want to implement a component display program, which is transmitted EmberJS-model programs. In the component must implement the mode of watching television with the information about ad units, which is taken from EmberJS-utilities. 

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

1. Display the top 5 TV shows from favourites to all users 
2. Display information about the television show, which is the longest broadcast 
3. Display information about the television show, which often was in the air of several TV channels 
4. Display information about the total time of broadcast for the specified TV show 
5. List the TV programs that go on all TV channels at a specified time 

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

1. To implement a business server that will control that in the approved program correctly identifies all time slots and quotas advertising time. 
2. To implement a business server that will instead of deleting system objects to transfer them to the state archive facility. 
3. To implement a business server that will control what is added to favorites program, where there is still no similarly to remove from favorites program have to be in it. 
4. To add a stored field in the class Employee, which will be the name one line from Surname Name and Patronymic. 
5. To add the string is not a stored field that contains all the necessary information about the program (start and end Time, title, description, type, etc.). 

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

The implemented application to configure the subsystem powers. Users of television should start, the application administrator, viewers register themselves (a simplified diagram). Authorization form-based. To create a hierarchy of roles, add transactions to view information about the internal structure of the TV program. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects, except the elect, must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}