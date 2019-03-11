--- 
title: Option 5 - «Paid Parking» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-05.html 
lang: en 
autotranslated: true 
hash: 24ab27b0dd8ff7951cfad9ed53d60a6ba258c4fa729405a1dc3c57a0d074472a 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Paid Parking» (IP for automation of accounting of parked cars). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 

## General description of the subject area 

On the territory of the city are paid Parking areas with a total capacity of 1000 seats. The drivers can use to pay for Parking through the website. Monitoring payment runs reconciliation rooms a parked car with the list of paid Parking lots. You want to develop an application which will take into account the payment of Parking or the fact of evacuation of the car (the driver who violated the Parking rules through this application will be able to learn about the fact of evacuation, and the cost of the fine and the point of evacuation). For drivers proposed payment rate tied to an hour of Parking. One hour - 10 rubles. Payment can be made in one hour, per working day (the rate is set to 8 hours) or calendar month (150 hours). The penalties for non-payment of Parking depend on the number of violations: first offense - 100 hours, the second 200 hours, and the third 300 hours and so on. Payment invoice will be issued for Parking since the discovery till the time of evacuation and a fine. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement the algorithm of calculation of fees paid Parking during the month based on these parameters: 
* The cost of one hour Parking 
* Average monthly percentage of occupancy of Parking spaces 
* The number of violations (first, second, third time) 

The output should be represented by the sum of charges paid Parking. 
The implementation should be done .net-libraries and to prepare unit tests (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better).

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form where the user can enter data about your car (license plate) and go to the form of Parking fees or the form of search results of car among the evacuees. Form of payment of Parking should provide an interface for specifying payment period (how many hours, days, months) and to provide information about the cost in accordance with the selected number. The form of search results of car among the evacuees must first 5 seconds show the element mapping of the data then, depending on probability of 0.3 show a message with the information on the evacuated car or with information that the "car not found, please contact 112 about the theft". 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented form where the user can enter data about your car (license plate) and go to the form of Parking fees or the form of search results of car among the evacuees. Form of payment of Parking should provide an interface for specifying payment period (how many hours, days, months) and to provide information about the cost in accordance with the selected number. The form of search results of car among the evacuees must first 5 seconds show the element mapping of the data then, depending on probability of 0.3 show a message with the information on the evacuated car or with information that the "car not found, please contact 112 about the theft" (to bind to bakedu in this task is not necessary). 

In EmberJS-utilities are required to implement the algorithm for calculating the cost of Parking based on the input data: 
* The number of 
* Unit 

The output should be represented by the cost of Parking. 

To use this component in EmberJS-utility when you change any parameter to recalculate and display the Parking cost to the user.

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

1. What evacuation zone have the highest fines on the amounts? 
2. Display the top 5 cars that spend most of their time on paid Parking 
3. Display top-5 car owners who have more cars 
4. List the parked cars on the specified date and time 
5. List of cars that have been evacuated twice in the same day 

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

1. To implement a business server that will control that one and the same vehicle will not be created the object of the payment at the same time, what has already been paid. Business server must in this case move the start date paid time at the end of the previous billing period. 
2. To implement a business server that will not allow the removal of the removal of the car (so you can't reset the counter). In the same business server must be controlled by the amount of the fine based on the information about the number of fines issued earlier. 
3. To implement a business server that will monitor the payment of the fine - polzoatel should not have the ability to double-pay the fine. 
4. To add a stored field in the Owner class, which will represent the name one line from Surname Name and Patronymic. 
5. To add a stored field, Parked in the Car class, which will be evaluated by the presence of the active records of the paid Parking. 

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
To transfer the generated application and implement the integration of the form to create a payment with the business logic of the application. On the form controls whether the data should be taken from the database (through ORM and Lookup). Fill data shall also work with the saving. 
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

The implemented application to configure the subsystem powers. Users operator of paid Parking should start the administrator application, drivers self-register (simplified scheme). Authorization form-based. To create a hierarchy of roles, add transactions to view information about the evacuation and to grant rights to administrators only. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}