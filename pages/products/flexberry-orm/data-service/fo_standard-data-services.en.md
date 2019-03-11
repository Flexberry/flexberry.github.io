--- 
title: Standard data services Flexberry ORM 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: inheritance Hierarchy and description of data services 
toc: true 
permalink: en/fo_standard-data-services.html 
lang: en 
autotranslated: true 
hash: 58d0e6ab4451c9ae16de8583a4774a4b1763a794aa9d064f9e760ba226f32920 
--- 

## the inheritance hierarchy for the data services 

![](/images/pages/products/flexberry-orm/data-service/i-data-service-inheritance.png) 

## Description of standard data services 

Following [data services](fo_data-service.html): 

* [ICSSoft.STORMNET.Business.XMLFileDataService](fo_xml-file-ds.html) to access the data in the format `XML` files. 
* [ICSSoft.STORMNET.Business.SQLDataService](fo_sql-data-service.html) — basic ancestor of all data services working with relational sources. 
* [ICSSoft.STORMNET.Business.MSSQLDataService](fo_mssql-data-service.html) is a data service to access the SQL `MS Server` directly, bypassing `ODBC`. Requires `SQL Client`, but the rate of work increases by approximately 25%. 
* [ICSSoft.STORMNET.Business.DRDataService](fo_dr-data-service.html) is a data service for `MS SQL Server` that implements a "dirty read" of the data (`WITH (NOLOCK)`). 
* [ICSSoft.STORMNET.Business.OracleDataService](fo_oracle-data-service.html) is a data service to access the `Oracle` directly, bypassing `ODBC`. Requires [client Oracle](fo_tools-oracle-ds.html). 
* [ICSSoft.STORMNET.Business.PostgresDataService](fo_postgres-data-service.html) 
* [ICSSoft.STORMNET.Business.ODBCDataService.ODBCDataService](fo_odbc-data-service.html) to access relational data sources. Independent of RDBMS, it is important the use of standards `ANSI` and `ODBC`. 
[ICSSoft.STORMNET.Business.ODBCDataService.ODBCAccessDataService](fo_odbc-access-ds.html) — [ODBCDataService](fo_odbc-data-service.html), optimized for data access `СУБД Access`. *Use DB Access is possible only in the x86 version!** 
* [ICSSoft.STORMNET.Business.ODBCDataService.ODBCMySqlDataService](fo_odbc-mysql-data-service.html) — [ODBCDataService](fo_odbc-data-service.html), optimized for data access `СУБД MySQL` (version 4.1 and later, with some restrictions on object structure). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}