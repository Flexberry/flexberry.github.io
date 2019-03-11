--- 
title: Database Flexberry Service Bus 
sidebar: flexberry-servicebus_sidebar 
keywords: Bus, DB 
toc: true 
permalink: en/fsb_database.html 
lang: en 
autotranslated: true 
hash: 9306eb90b96d3cead51ba6111ecada46f5d6d8e86fde9a5dda7062ada94734b3 
summary: description of the database of the enterprise service bus. 
--- 

## Introduction 

The database is used to store the following information: 
* Messages sent to customers (saved to ensure guaranteed delivery) 
* Settings required for operation of bus (information about customers, subscriptions, etc.) 
* Statistics of data transmission 

## data Structure 

Scripts to create the database dastony in [repository service bus](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Flexberry Service Bus/SQL). 

## the Docker image 

For convenient use of the database Flexberry Service Bus is automatically built Docker image [flexberry-service-bus-postgres-db](https://hub.docker.com/r/flexberry/flexberry-service-bus-postgres-db). 

## Supported storage 

Service bus and an administrative application used to access the data Flexberry ORM, respectively, for the operation is available to all, supported by Flexberry ORM DBMS types: 
* Postgre SQL 
* Microsoft SQL Server 
* Oracle 
* etc. 

The scripts to create the database structure for a specific repository can be generated from Flexberry Designer [UML models](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Flexberry Service Bus) (file `Flexberry Service Bus.crp`). 

Configure it for use is performed according to [instructions to Flexberry ORM](fo_ds-provider.html).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}