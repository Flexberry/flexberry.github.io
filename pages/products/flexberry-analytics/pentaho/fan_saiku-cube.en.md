--- 
title: Saiku. The business process of creating OLAP cube 
keywords: mydoc 
sidebar: flexberry-analytics_sidebar 
toc: false 
permalink: en/fan_saiku-cube.html 
lang: en 
autotranslated: true 
hash: c74da06d7efdccb9e6021aeac8954b046e4ca5a8bfad0043d65ad5bcfe6aff77 
summary: 
--- 

`Saiku Analytics` is a client for working with datasets open source. Developed and maintained by the Corporation `Pentaho`. 

`OLAP-куб` — (_On-Line Analytical Processing_ — interactive data analysis) multidimensional array data are usually sparse and long-term stored, used in OLAP. Can be implemented on the basis of a universal relational DBMS or specialized software. For details, see [OLAP cube](https://ru.wikipedia.org/wiki/OLAP-куб). 

## configure a connection to a database 

The first step to build an OLAP cube is the data source connection. To do this you must perform the following steps: 

{% include important.html content="Pentaho is not able to connect to the database with Russian name of the database. But it works correctly with Russian names of the tables." %} 

1.Log in under a role `Администратор`; 

2.Open `Manage Data Sources` and in the popup window, click on the gear ![](/images/pages/products/flexberry-analytics/saiku-cube-settings.png); 

3.In the drop down menu click on `New connection`; 

4.In the opened pop up `Database Connection` to fill in fields to connect to the database: 

* Connection Name; 
* Database Type (if you select different types of databases are changing fields to configure the connection, following are the fields for PostgreSQL); 
* Access (by default, Native (JDBC)) 
* Host Name; 
* Database Name; 
* Port Number (default 5432); 
* User Name; 
* Password 

![](/images/pages/products/flexberry-analytics/saiku-cube001.png) 

5.After filling the fields, click the button to check `Test` соединения; 

![](/images/pages/products/flexberry-analytics/saiku-cube002.png) 

6.If everything worked correctly, click on the `OK` to save the configuration. 

## Publish OLAP schema cube 

The second step to create the cube is the publication scheme, developed in Pentaho Schema Workbrench or manually. 

1.Open `Manage Data Sources` and in the popup window, click on the gear ![](/images/pages/products/flexberry-analytics/saiku-cube-settings.png); 

2.In the drop down menu click on `Import Analysis`; 

3.In the pop-up window `Import Analysis` need to fill in the following fields: 

* Mondrian File (Select xml schema file from a directory on your PC); 
* Choose `Select from available data sources`; 
* Data Source (Choose from the list the DB connection you created in step 1) 

![](/images/pages/products/flexberry-analytics/saiku-cube003.png) 

4.After filling click on `Import`; 

## cube Creation 

1.On the main page of Pentaho click `Create New`; 

2.In the list that appears, click the button `Saiku Analytics`; 

![](/images/pages/products/flexberry-analytics/saiku-cube004.png) 

3.Next, click on the button `Create a new quary` 

4.In the opened window, right click on the icon ![](/images/pages/products/flexberry-analytics/saiku-cube-refresh.png) to update данных; 

5.Select the desired cube from the drop-down списка; 

6.Add dimensions and measures, see the result: 

![](/images/pages/products/flexberry-analytics/saiku-cube005.png) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/