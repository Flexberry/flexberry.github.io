--- 
title: Processing of registers in names of objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services, database 
summary: processing Rules registers for different DBMS 
toc: true 
permalink: en/fo_processing-registers-names.html 
lang: en 
autotranslated: true 
hash: 93bb4afd8a08cf6d77d1e37af9a67b863c8cb9d3c49e14c29a66de1ff34df4d1 
--- 

Working with [SQLDataService](fo_sql-data-service.html) you need to pay attention to the following processing registers: 

## Oracle 11g and PostgreSQL 9.1 

### ID is not shielded when the object is created CREATE TABLE Table1 

#### Sample screening SELECT * FROM “Table1” 

Conditionally acceptable. Only when specifying the ID in the upper - (ORACLE) or bottom (PostgreSQL) register (SELECT * FROM “TABLE1”), because it is stored when you create without a shield. In other embodiments, registers the object will not be found. 

#### Sample without escaping SELECT * FROM Table1 

Valid. The ID is not case sensitive. Is stored and converted to queries to uppercase (ORACLE) or bottom (PostgreSQL). 

### ID shielded when an object is created CREATE TABLE “Table1” 

#### Sample screening SELECT * FROM “Table1” 

Valid case-sensitive with respect, as was specified during creation. The ID of registertask shall be stored as specified. 

#### Sample without escaping SELECT * FROM Table1 

Conditionally acceptable. Only when the identifier was specified at the top (ORACLE) or bottom (PostgreSQL) register (CREATE TABLE “TABLE1”), because it is converted into a query without escaping them. In other embodiments, registers the object will not be found. 

### Particularly for identifiers that contain Cyrillic characters 

#### For Oracle 11g 

Without features. Treatment is similar to that of Latin. 

#### For PostgreSQL 9.1 

Regardless of the method of object creation (with shielding/no) ID is case sensitive. To refer to it in queries, regardless of the method, as with shielding and without, but strictly in compliance with the case of all characters (which was used to create). 

### features of use keywords as identifiers 

You can use, but an identifier such as when the object is created, and when used in queries should be escaped. Ie always case-sensitive. 

## MSSQL 2012 

Working with registers in the names of objects is performed in accordance with the Collation setting of the database regardless of whether you use shielding when creating objects or queries. When you specify CI (Case Insensitive) names are case-insensitive. And query creation, and in the samples, the registers can be specified arbitrarily. Shielding can also be used without any restrictions. If you specify CS (Case Sensitive) names are case sensitive. Registers requests must be specified exactly as when you created, otherwise the object will not be found. Shielding can also be used without any restrictions. Collation the database is set when you create database, or ALTER. By default, this parameter coincides with the Collation of a SQL Server instance. At the server, if not specified, the default CI. 

### Particularly for identifiers that contain Cyrillic characters 

Without features. Treatment is similar to that of Latin. 

### features of use keywords as identifiers 

You can use, but an identifier such as when the object is created, and when used in queries should be escaped (quotes or brackets). Case-sensitivity as in the General case, is determined only by the Collation setting of the database. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}