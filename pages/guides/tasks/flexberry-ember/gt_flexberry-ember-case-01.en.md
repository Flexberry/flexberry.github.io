--- 
title: Option 1 - «table tennis» 
keywords: Tasks 
sidebar: guide-tasks_sidebar 
toc: true 
permalink: en/gt_flexberry-ember-case-01.html 
lang: en 
autotranslated: true 
hash: 51d3c06d1cd86027438a262e064762f366ce2d663b128296b064bfcdb29ba54d 
summary: the Variant end-to-end specifications for the design and development using the framework Flexberry Ember 
--- 

## Job 

In the framework of the practical part of the course you will have developed end-to-end example: application «table tennis» (IP for automation of the account of results of games of table tennis). 

The first part of the practical exercises will be devoted to the development of basic technologies, such as C#, databases, client technology, etc., the second part will include a study of the possibilities of the platform Flexberry to efficiently create applications. 


## General description of the subject area 

There is a list of players who play table tennis. Every game involved two players. Each match consists of one or more parties. Each party is played to 11 or to 21 points one of the players, or when playing in more or less, to 12 or 22 points, respectively. In one match, all parties must play the same number of points. Each match is played in a certain date/time. Matches between players can be planned in advance. 

Each player has a rating. When you add to the system the player is assigned a rating of 1400. After the match between the two players, the rating of each of the participants must change in accordance with the calculation formula [Elo](https://ru.wikipedia.org/wiki/Рейтинг_Эло). The change needs to happen at each party, but only in the context of the match. Players can play unranked matches, the results of which should not be considered in the rating. 

Among a certain group of players can be arranged with the tournament. The tournament is held in a given period of time. Rules of the tournament secured many victories in the parties must continue the match between the players of the tournament, short or long should be of the party. All parties in the framework of the tournaments are rated. The result of the tournament is the final table with the distribution of seats in the tournament. 

Specifications: 

* The application is realized as [SPA -] (https://ru.wikipedia.org/wiki/Одностраничное_приложение) bakenda on the C# and EmberJS on the frontend. 
* Data is stored in the database Postgres. 

## Practical task no 1 - server development (C#, .NET ASP.NET) 

Will be required for implementation: 

* Microsoft Visual Studio 2017 
* Git for Windows 

### Job 

You want to implement the algorithm recalculate Elo for the match result between the two players with given rank values. The input of the algorithm ratings of both the participants and the match result is 1, 0, -1. The algorithm must return a new value of the rating of both players. Should be a flexible adjustment of the rating algorithm calculation to change the K-factor, as well as the feasibility of the algorithm with different K value for different values of the ratings of the players (similar to a chess rating in the article on Wikipedia). 

The implementation should be done .net-libraries and to prepare unit tests (xUnit), to demonstrate the percentage of code coverage from unit tests (the more the better). 

### Providing performance verification 

The implemented solution (Visual Studio Solution) to put it all in a repository on GitHub (the solution must compile and run). A link to the repository to provide the teachers of the course. 

## Practical exercise No. 2 - Client-side development (HTML, CSS, JS, jQuery) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

With use of opportunities HTML, CSS, JS, jQuery mark up a form where you can enter the match results. It should be possible to enter score and score in every game. The form should also be a unit for input of players, date of match, length of party and number of victories by the parties, which played the match. Must be controlled by business rules relating to the possible account of the party. Billing parties should be given the opportunity to choose the number of points in the beautiful a pop-up list. 

### to deliver results 

Implemented project to place in a repository on GitHub in the form of resident pages GitHub Pages that allows you to view the finished result. A link to the published repository and thus the project to provide teachers of the course. 

## Practical exercise No. 3 - Client-side development using a SPA-framework (EmberJS) 

Will be required for implementation: 

* Code editor for client development: Visual Studio Code 
* Git for Windows 

### Job 

You want to implement EmberJS app, which will add the form to enter match results. The form must be implemented using capabilities EmberJS. It should be possible to enter score and score in every game. The form should also be a unit for input of players, date of match, length of party and number of victories by the parties, which played the match. Must be controlled by business rules relating to the possible account of the party. 
In EmberJS-utilities are required to implement the algorithm recalculate Elo for the match result between the two players with the given values of the ratings (similar to the algorithm implemented on the server side). The input of the algorithm ratings of both the participants and the match result is 1, 0, -1. The algorithm must return a new value of the rating of both players. Should be a flexible adjustment of the rating algorithm calculation to change the K-factor, as well as the feasibility of the algorithm with different K value for different values of the ratings of the players (similar to a chess rating in the article on Wikipedia). 
To develop in the form of an Ember component, the controls that implement the logic input ratings of the two participants, and the conversion factor of rating and calculation of new ratings (fields to enter values button, the unit displays the result), to recalculate designed to connect EmberJS-utility. 

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

1. The current rating of the players in descending order 
2. The last N changes in the ranking of the player 
3. The last N matches of the player 
4. Latest N personal meetings between the two players 
5. The final table of the tournament 
6. All tournament matches in chronological order 

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

1. To implement a business server that will control the correctness of the length of the parties and account parties in one match 
2. To implement a business server, which will monitor the impossibility of creating in the tournament match with a date outside the timeframe of the tournament 
3. To implement a business server that will be used .net library to recalculate the ratings of players who played 
4. To add a stored field in the class of the tournament, which will contain the name of the winner. 


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
To transfer the generated application and implement the integration of forms for affixing of the results of the tournament matches with the business logic of the application. On the form controls with the choice should be taken from the database (through ORM and Lookup) players. The affixing of the results should also work with saving to the database. 
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

The implemented application to configure the subsystem powers. Players must start the administrator application. Authorization form-based. To create a hierarchy of roles, add transactions to view the results of matches and to grant rights to administrators only. To configure the roles and assign these roles to users. 
To configure the auditing subsystem. In the developed application, all changes to data objects must be recorded in the audit. In the navigation menu, you should add forms of audit that should only be shown to administrators. 

### to deliver results 

The revised application must be seamicro in the appropriate repository. Additionally, the repository must be added to the SQL scripts that need to perform to run an application with a subsystem of powers and audit. Link to the repository is available for inspection by the course instructors. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}