--- 
title: Option 4 - «Subscription to the print edition of» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-04.html 
lang: en 
autotranslated: true 
hash: 5fb1a8bd81c91e437afb561cc6e33854b9cb61707bebbf080e46b7897f76a660 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «Subscription to the print edition of» (IP for automation of accounting of subscribers to the printed edition). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 


## General description of the subject area 

Paper Newspapers and magazines delivered by subscription. You have to develop a web application in which you can select a publication and subscribe to it. The unit of time for a subscription is a calendar month. You can subscribe for any number of months from 1 to 6 within six months or by 12 (January to December). The subscription for the following month is issued not later than the 15th day of the current month. In the case of a subscription in whole on half (January - June or July - December) the user receives a 5% discount. An annual subscription costs 10% cheaper. 
Each edition has a number of releases in a month or in six months. If the publication is published every month, the subscription is possible only for months when the edition comes out. The months in which the edition comes out are not included in the calculation of discounts. 
The application are publishers, who are filling the information available to subscribers Newspapers and magazines, confirmed the receipt of money for the subscription, and translate it as "Delivered". Postal workers see information about the subscription and execute the delivery of publications from publishers to subscribers, noting the fact of delivery (delivered this month). Subscribers use the application to subscribe. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement the algorithm for calculating the price based on the input data: 
* The subscription price for one month 
* Months absence edition 
* The months that the user has selected to subscribe 
The output should be represented by the subscription price with the discount, if applicable, and report the amount of the discount that was received.
The implementation should be done .net-libraries and to prepare unit tests (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form, which is used to specify the months for which he wants to subscribe the user. Months need to check. Months of absence of the publication needs to be blocked for selection. To prepare a static switch for selecting publications, calendar year and semester subscription (subscription for the whole year is available when the semester is not selected). Implement additional buttons "to Sign up for the half and Sign up for a year", which will put the appropriate checkboxes, skipping blocked. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS application, which will be implemented form, which is used to specify the months for which he wants to subscribe the user. 
To develop in the form of an Ember component, the controls that implement the logic of select the checkboxes of the months for which to subscribe. Months of absence of the publication needs to be blocked for selection. To prepare a static switch for selecting publications, calendar year and semester subscription (subscription for the whole year is available when the semester is not selected). Implement additional buttons "to Sign up for the half and Sign up for a year", which will put the appropriate checkboxes, skipping blocked. To embed this component on the form. 

In EmberJS-utilities are required to implement the algorithm for calculating the price based on the input data: 
* The subscription price for one month 
* Months absence edition 
* The months that the user has selected to subscribe 
The output should be represented by the subscription price with the discount, if applicable, and report the amount of the discount that was received.

To use this component in EmberJS-utility when you change any parameter and recalculate to display the subscription cost to the user. 

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

1. Display the top 5 publishers by revenue for periodicals for the year 
2. Display the top 5 subscribers, who spend the maximum amount of money per subscription on average per year 
3. To obtain the name of the publication, which is least of all 
4. Display all rooms and publication in the order received on the specified subscription key 
5. Find users who subscribe to the same publication at least 3 years in a row 

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

1. To implement a business server that will monitor the limit to create the subscription for the following month no later than the 15th of the month. 
2. To implement a business server, which will create objects of delivery for the specified subscription when it is saved. 
3. To implement a business server to restrict editing the subscription, if it is put in the status "Delivered". 
4. To add a stored field in the class Publication, which will display the subscription cost for half of the year. 
5. To add a stored field in the class Publication, which will display the subscription cost for a year. 

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
To transfer the generated application and implement the integration of the form to create the subscription with the business logic of the application. On the form controls whether the data should be taken from the database (through ORM and Lookup). Fill data shall also work with the saving. 
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

The implemented application to configure the subsystem powers. Users publishing and mail will start the application administrator, subscribers register on their own (simplified scheme). Authorization form-based. To create a hierarchy of roles, add transactions to view information about the delivery and to grant rights to administrators only. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}