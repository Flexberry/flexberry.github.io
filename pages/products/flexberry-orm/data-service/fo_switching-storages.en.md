--- 
title: Using Postgres as storage 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services, postgres 
summary: Example of using the Postgres database 
toc: true 
permalink: en/fo_switching-storages.html 
lang: en 
autotranslated: true 
hash: 93adf70a9736b5bc2b1187a96e6b950145bb1bc09c8d260fbfa024231566c7e1 
--- 

To accomplish the task, you need to install [Postgres](http://www.postgresql.org/). 

* Next, you must create the database by running the script 

```sql
FlexberryORM\Database\POSTGRES\create.sql
``` 

* Then `app.config` to find section `appSettings`. 
* Comment out options `DataServiceType` and `CustomizationStrings` and then rename options `DataServiceType_POSTGRE` and `CustomizationStrings_POSTGRe` in `DataServiceType` and `CustomizationStrings`. 
* Change the option `CustomizationStrings` to correctly connect to the server and the database. 
* To run the sample to verify that it works: check the creation and loading of data objects. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}