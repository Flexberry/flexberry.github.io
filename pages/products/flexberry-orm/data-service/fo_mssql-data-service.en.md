--- 
title: MSSQLDataService 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, mssql, data services 
summary: the data Services to work with MS SQL Server 
toc: true 
permalink: en/fo_mssql-data-service.html 
lang: en 
autotranslated: true 
hash: 83583198eec9f4ddeeabfbd27222b447e1aa0336b39120865ad13cff7e1f0d58 
--- 

`MSSQLDataService` is [service data](fo_data-service.html) to work with MS SQL Server directly, bypassing ODBC is an implementation of the [abstract class SQLDataService](fo_sql-data-service.html). 

When specifying MSSQLDataService as a service data string is used `ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService`. 

## features of use 

* MSSQLDataService requires `SQL Client`, but the rate of work increases by approximately 25%. 
* MSSQLDataService [special handling of NULL and string.Empty](fo_updating-objects-empty-rows.html). 

*See also the article* [Processing registers object names to DBMS](fo_processing-registers-names.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}