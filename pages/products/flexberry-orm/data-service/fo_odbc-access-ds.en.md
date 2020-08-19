--- 
title: data Services to access the Access database engine 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services, Access 
summary: Features use ODBCAccessDataService 
toc: true 
permalink: en/fo_odbc-access-ds.html 
lang: en 
autotranslated: true 
hash: f0c411372d0810ae62df95de9cf20731c71f9cf7062da926c58b3e2b89180f89 
--- 

`ODBCAccessDataService ` is [service data](fo_data-service.html), represents the implementation of [ODBCDataService](fo_odbc-data-service.html), optimized for data access DBMS Access. 

When specifying ODBCAccessDataService as a service data string is used 

`ICSSoft.STORMNET.Business.ODBCAccessDataService, ICSSoft.STORMNET.Business.ODBCDataService`. 

## features of use 

* The connection string used ODBCAccessDataService lines 

`Driver={Microsoft Access Driver (*.mdb)};Dbq=D:\Temp\Flexberry.mdb;` 

(in this example, the connection will be made to the database "Flexberry.mdb" located on my way "D:\Temp\"). 
* Due to the version of the driver, work with ODBCAccessDataService is only possible in applications that are compiled for x86. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}