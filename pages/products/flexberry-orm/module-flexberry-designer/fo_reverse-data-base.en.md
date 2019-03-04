--- 
title: Creating a class diagram for database 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry Designer, Flexberry ORM, database, example 
summary: the algorithm for generating the graphs in the DB for example 
toc: true 
permalink: en/fo_reverse-data-base.html 
lang: en 
autotranslated: true 
hash: 172de0fda41468dfd4028ca5ccc63390dfda606262a45f4fa1842eeb135ccd36 
--- 

[Flexberry Designer](fd_landing_page.html) allows you to create class diagrams for database to: 

* MS SQL Server 
* ORACLE 
* PostgreSQL. 

The use of this function will be illustrated on the example of MS SQL Server database. For Oracle and Postgre SQL operation is performed similarly with a choice of menu options corresponding to DBMS. 

## Use 

To create charts on the database should: 

* Create a new Stage. 
* To allocate a Stage to go to [ORM -> SQL](fd_configure-ms-sql-generator.html), specify the path to the database. 
* Create a new System in this Stage. 
Select System, select the menu [ORM -> Build diagrams, BD -> Microsoft SQL Server](fo_orm-case-plugin.html). 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reengineering-plugin.png) 

* Wait for the operation. 

{% include note.html content="When you create graphs in the database [Decalogue](fo_detail-associations-properties.html) communication will be interpreted as [Association](fd_master-association.html) 1 - * (due to the fact that at the DB level there is no difference between the mechanics and metalowymi connections)." %} 

When you create a chart in a standard way handled types of parameters that were specified by default in the map of SQL types](fd_types-map.html). 

__Example:__ type `VARCHAR(255)` there is default in the map of SQL types, so it will be converted to a class diagram in `string`. However, in this map no type `VARCHAR(25)`, so will be created [typedef](fd_typedef.html) `VARCHAR25` that in the map of SQL types will be sampireun on `VARCHAR(25)`, and the map generator C#-code - `string`. 

For special handling of parameter types, you can add them to the respective card types C# and SQL `перед` by creating a class diagram for the database." 

## Example of creating a class diagram for database 

* Create a class diagram, which will create a database: 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-7-0.png) 

* To highlight the stage and set the path to the database (in this case we will use the base name with A-Test-DB). 
* Generate script of database changes: 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-3.png) 

and apply it to A Test-DB. 
* Create the stage for generating class diagrams for the database, specify the database path A-Test-DB. 
* To create an object System. 
* Isolate the System and generate the chart: 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reengineering-plugin.png) 

* To check the result: needs to be created 4 class diagrams 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-7-1.png) 

`Reverse:` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-7-2.png) 

`Reverse Object1:` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-7-3.png) 

`Reverse Object2:` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-7-4.png) 

`Reverse Object3:` 

![](/images/pages/products/flexberry-orm/module-flexberry-designer/reeng-step-7-5.png) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/