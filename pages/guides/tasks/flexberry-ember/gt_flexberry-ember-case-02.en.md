--- 
title: Option 2 - «sport school» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-02.html 
lang: en 
autotranslated: true 
hash: 30704921b0996005c0282048a3bae25bf30190c70b6b15c71e9a97c453b7bccf 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application» «sport school. 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

You want to create an app for sports schools. This application should cover the need of fixation of information about the athletes, their training and participation in competitions. 
It is known that athletes, within the section, organized into age groups. Each athlete must attend practices and games during the sports year. 
To provide in the application the ability to make a workout plan. Training consists of a set of exercises, each exercise is performed in a given pulse area, has its duration, number of repetitions and average heart rate. The results of the training and competition are recorded for each athlete separately. The pulse area is characterized by a minimum and maximum value, and score (an integer from 20 to 140). 
Information on these activities is recorded in the application. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

Required to implement the algorithm for calculating the load of the athlete during a training session, on the basis of time spent by the athlete in the pulse area. Each zone has its own score (units/hour): 1 – 20, 2 – 30, 3 – 40, 4-50, 5-60, 6-70, 7-80, 8-100, 9-120, 10-140. The algorithm of formation of the load following: the time spent in the area * the score of the corresponding zone. For example, during a training session the athlete was in the zone 1 12 minutes and 30 seconds in zone 2 and 30 minutes in zone 3, 5 minutes, 18 seconds. We define the received load as follows: (30/60 12)/60\*40 30/60\*60 (18/60 5)/60\*80 = 8,3 30 7=45,3 units/hour. 

The implementation should be done .net-libraries and to prepare unit tests for it, to show the percentage of code coverage from unit tests (the more the better).

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery impose a form of exercise. On the form you should see the following fields: 

* Name, date of birth and group of the athlete. 
* Date of training scheduled and actual load 
* List of exercises: 
* Title 
* Run time in the format HH:MM:SS 
* Average heart rate 
* Load (calculated on the client, based on average heart rate, time and pulse points of the zone exercises) 

The number of entries in the list for exercise, equal to the number of repetitions of exercises. 
Should be able to add, delete, and move exercises together. 
The form also needs to be a block with summary information about the training: 

* Number of exercises 
* Duration 
* Average heart rate 
* Total load (actual). 

To realize the control input. 
To rely on the client the values of the unit with total info as well: 
* Age 
* Actual load 
* The load in exercise 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of embedded pages [GitHub Pages](https://pages.github.com/) that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented as a form of exercise. On the form you should see the following fields: 

* Name, date of birth and group of the athlete. 
* Date of training scheduled and actual load 
* List of exercises: 
* Title 
* Run time in the format HH:MM:SS 
* Average heart rate 
* Load (calculated on the client, based on average heart rate, time and pulse points of the zone exercises) 

The number of entries in the list for exercise, equal to the number of repetitions of exercises. 
Should be able to add, delete, and move exercises together. 
The form also needs to be a block with summary information about the training: 

* Number of exercises 
* Duration 
* Average heart rate 
* Total load (actual). 

To realize the control input.
To rely on the client the values of the unit with total info as well: 
* Age 
* Actual load 
* The load in exercise 

In EmberJS-utilities are required to implement the algorithm for calculating the load of the athlete during a training session, on the basis of time spent by the athlete in the pulse area. Each zone has its own score (units/hour): 1 – 20, 2 – 30, 3 – 40, 4-50, 5-60, 6-70, 7-80, 8-100, 9-120, 10-140. The algorithm of formation of the load following: the time spent in the area * the score of the corresponding zone. For example, during a training session the athlete was in the zone 1 12 minutes and 30 seconds in zone 2 and 30 minutes in zone 3, 5 minutes, 18 seconds. We define the received load as follows: (30/60 12)/60\*40 30/60\*60 (18/60 5)/60\*80 = 8,3 30 7=45,3 units/hour. 

To develop in the form of an Ember component, the controls that implement: 
1. Search for athlete by name and code of the group. 
2. Enter data on time spent by the athlete in the pulse zones and the use of the library from the preceding paragraph for the issuance of information load (fields to enter values for each zone, button, block with the results displayed), to calculate designed to connect EmberJS-utility. 

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

1. Display a list of groups with number of athletes, as well as minimum and maximum age in the group. 
2. Output the number of visited and missed training athlete. 
3. Display summary information about training athletes over the past 3 weeks: date of exercise, amount of exercise, the training duration, actual load, average heart rate. 
4. Bring the top athletes load per workout. 
5. To obtain the average load for each athlete in the last 2 weeks, by days of the week. 

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

1. To implement a business server that will check the planned and actual workout time, and display the difference on screen. 
2. To implement a business server that will be used .net library for calculating the load of the athlete in exercise/training. 
3. To implement a business server that will check that the average heart rate at exercise was within the given framework. 
4. To add a stored field in the class group that will contain the number of athletes in this group. Recalculation of this field to implement the business server. 
5. To add a stored field in the class of athlete that will make a string that contains the following information about the last training session: date, name, duration, load. 

### to deliver results 

Modified business logic should be included in the development application and sakimichan in the appropriate repository. Link to the repository is available for inspection by the course instructors. 

## Practical exercise No. 8 - Development of the UI application logic 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Code editor for client development: Visual Studio Code 
* Postgres 
* Git for Windows 

### Job 

To realize the integration of the form to enter workout data with the business logic of the application. On the form controls with selection, values should be taken from the database (through ORM and Lookup). Data entry training should work with saving to the database.

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

To create a hierarchy of roles, add transactions to view the data about the training and to grant rights only to the athletes and coaches. To configure the roles and assign these roles to users. 

To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}