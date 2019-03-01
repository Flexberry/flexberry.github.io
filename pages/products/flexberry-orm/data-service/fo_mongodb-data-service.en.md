--- 
title: MongoDbDataService 
sidebar: flexberry-orm_sidebar 
keywords: MongoDB, NoSQL 
toc: true 
lang: en 
autotranslated: true 
hash: 96477616a07effefc1dbea221a3abc4419fd7ea0e795b4485472501a3655b6d2 
permalink: en/fo_mongodb-data-service.html 
summary: the data Services to work with NoSQL database MongoDB. 
--- 

## Description 

To use the technology of processing and storing large data, you can use the [service data](fo_data-service.html) [NoSQL store MongoDB](gbt_mongodb.html) - `MongoDbDataService`. The data service implements the standard interface `IDataService` that allows you to use it in the usual way. Also available a scenario when one application is working with multiple repositories, respectively, may be an option when one vault is a database [Postgres](gbt_postgresql.html) and the other is a [MongoDB](gbt_mongodb.html). 

## Installation 

In order to start using the data service `MongoDbDataService` to be installed in the project corresponding to the [NuGet package NewPlatform.Flexberry.ORM.MongoDbDataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.MongoDbDataService)that is available through public gallery <https://nuget.org>. 

## Example usage 

To meet example of the use `MongoDbDataService` the [repositories bakenda demo application FlexberryOrmMongoDbDataServiceDemo.ODataBackend](https://github.com/Flexberry/FlexberryOrmMongoDbDataServiceDemo.ODataBackend). In the code this application is an example of simultaneous work with two types of storage. 
[Frontend part of the app](https://github.com/Flexberry/flexberry-orm-mongodb-dataservice-demo-frontend) implements the mechanisms to switch between multiple backendname (another variant of the organization of access to storage of different types). 
Assembled and deployed backend demo applications working with big data store is available at <http://flexberry-orm-mongodb-dataservice-demo.azurewebsites.net/odata>. 

## completion of the data service 

Development `MongoDbDataService` being in the [open repositories on GitHub NewPlatform.Flexberry.ORM.MongoDbDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService). About the problems can be reported by creating relevant [Issue](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService/issues), you can also perform rework and offer [Pull request](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService/pulls) to the [develop branch](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.MongoDbDataService/tree/develop). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/