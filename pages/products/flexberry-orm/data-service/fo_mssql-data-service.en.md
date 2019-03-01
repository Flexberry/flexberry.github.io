--- 
title: MSSQLDataService 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, mssql, data services 
summary: the data Services to work with MS SQL Server 
toc: true 
permalink: en/fo_mssql-data-service.html 
lang: en 
autotranslated: true 
hash: 8ba38d0fd23f1a28a6dcb0431865a393806bfa3afd89de9af5ba768006d04ce2 
--- 

`MSSQLDataService` is [service data](fo_data-service.html) to work with MS SQL Server directly, bypassing ODBC is an implementation of the [abstract class SQLDataService](fo_sql-data-service.html). 

When specifying MSSQLDataService as a service data string is used `ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService`. 

## features of use 

* MSSQLDataService requires `SQL Client`, but the rate of work increases by approximately 25%. 
* MSSQLDataService [special handling of NULL and string.Empty](fo_updating-objects-empty-rows.html). 

*See also the article* [Processing registers object names to DBMS](fo_processing-registers-names.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/