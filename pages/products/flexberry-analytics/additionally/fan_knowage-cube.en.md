--- 
title: KNOWAGE. The business process of creating OLAP cube 
keywords: mydoc 
sidebar: flexberry-analytics_sidebar 
toc: false 
permalink: en/fan_knowage-cube.html 
lang: en 
autotranslated: true 
hash: 4d78a2b4cbea5831d5a4a7519f2fe5ddd1584c8d1b1fc4e7357e9e924d50535a 
--- 

`Knowage` is a package business intelligence open source, which combines traditional data and big data sources into valuable and meaningful information. It combines innovations coming from the community with experience and practice solutions at the enterprise level. 

`OLAP-куб` — (_On-Line Analytical Processing_ — interactive data analysis) multidimensional array data are usually sparse and long-term stored, used in OLAP. Can be implemented on the basis of a universal relational DBMS or specialized software. For details, see [OLAP cube](https://ru.wikipedia.org/wiki/OLAP-куб). 

## create a "Data source"configuration 

### the First step to build an OLAP cube 

The first step is to create an OLAP cube data source connection. To do this you must perform the following steps: 

* Log in under a role `Администратор`; 
* Open `Menu` and go in the directory `Data Providers` in `Data source` 
* Visit `Data source` click on the plus ` ` and configure the DB connection. Next, fill in: 
* Label 
* Description 
* Dialect (choose `PostgreSQL`) 
* Multischema 
* Read only (select `Read and write`) 
* Type (select `JDBC`) 
* URL (input format `jdbc:postgresql://<address>:<port>/<database name>`) 
* User 
* Password 
* Driver (for postgresql introduced `org.postgresql.Driver`) 

* After filling in the fields click on the button `Save`. 

## create a directory for the new document/cubes 

### the Second step is to create OLAP cube 

The second step to create the cube is creating the directory and giving permissions to users. 

* Log in under a role `Администратор`; 
* Open `Menu` and go to the directory `Profile Management` in `Functionalities management` 
* Visit `Functionalities management` to choose the root directory `Functionalities` and click on the plus ` ` to create a new directory for documents and cubes. Fields need to be filled: 
* Label 
* Name 
* Mark all checkboxes 
* After filling in the fields click on the button `Save`. 

## Add schema OLAP cube 

* Log in under a role `Администратор`; 
* Open `Menu` and go to the directory `Mondrian schemas catalog`; 
* Click on the icon `Плюс` and in the appeared window, fill in: 
* Name; 
* File Upload; 
* To save click the button `Save`.

## Create OLAP cube 

* Log in under a role `Администратор`; 
* Open `Menu` and go to `Documents development`; 
* Choose the available partition in the tree under `Functionalities`; 
* Click on the icon `Плюс` and in the appeared window choose `Generic document`; 
* In the opened page fill the following fields: 
* Label; 
* Name; 
* Type = On-line analytical processing; 
* Engine = OLAP Engine; 
* Data Source – select the setting for connecting to БД; 
* Click on the save icon. Before saving to make sure there is a tick to the right, under Show document templates in the right section. 

* Click on the Template icon build; 
* In the appeared window choose Mondian, schema and Cube 
* Then click continue. 

{% include important.html content="the Use of this tool was not effective enough. It is recommended to use Pentaho." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}