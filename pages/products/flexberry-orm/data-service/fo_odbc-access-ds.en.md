--- 
title: data Services to access the Access database engine 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services, Access 
summary: Features use ODBCAccessDataService 
toc: true 
permalink: en/fo_odbc-access-ds.html 
lang: en 
autotranslated: true 
hash: 94b643ed336e2431b21ccd20568631d54d9bf6a8c2c99a211f46ca50f9a0b235 
--- 

`ODBCAccessDataService ` is [service data](fo_data-service.html), represents the implementation of [ODBCDataService](fo_odbc-data-service.html), optimized for data access DBMS Access. 

When specifying ODBCAccessDataService as a service data string is used 

`ICSSoft.STORMNET.Business.ODBCAccessDataService, ICSSoft.STORMNET.Business.ODBCDataService`. 

## features of use 

* The connection string used ODBCAccessDataService lines 

`Driver={Microsoft Access Driver (*.mdb)};Dbq=D:\Temp\Flexberry.mdb;` 

(in this example, the connection will be made to the database "Flexberry.mdb" located on my way "D:\Temp\"). 
* Due to the version of the driver, work with ODBCAccessDataService is only possible in applications that are compiled for x86. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/