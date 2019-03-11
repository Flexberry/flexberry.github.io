--- 
title: MongoDB 
keywords: NoSQL, BigData 
sidebar: guide-base-tech_sidebar 
toc: true 
permalink: en/gbt_mongodb.html 
lang: en 
autotranslated: true 
hash: 38f9bd842ab66d893d6e4014b2164a5b237cd0bef3788e67d2b0e864c0b07ac8 
--- 


## Brief description 

MongoDB is written in C document-oriented NoSQL database management system open source, does not require the description of the table schema (schemaless). 

MongoDB has the following levels of reporting: 
1. The document is a JSON object with an arbitrary number of fields. Fields can store both proste value to voennye objects and arrays. 
2. Collection (table)- the same type documents are stored in separate collections. The documents in the collection can be proindeksirovany. Access dokumentu possible by key and value fields. 
3. Database - a set of collections. 

Support MongoDB is implemented for most programming languages: 
- C; 
- C ; 
- C#; 
- Java; 
- Node.js; 
- Perl; 
- PHP; 
- Python; 
- Ruby; 
- Scala. 

Differences MongoDB from relational databases: 
- Not supported TransAsia. Atomicity is guaranteed only at the level of the whole document, that is, a partial document updates cannot occur. 
The absence of a mechanism of isolation» qmo. Any data that is read by the same client in parallel, which can be changed by another client. 

The benefits of MongoDB relational database before: 
- Supports horizontal scaling with replication data. Data can be stored on an arbitrary number of servers. Replication provides fault-tolerance support functions at the output nodes of the system. 
- Data storage format (document) is close to the format of data representation in programming languages (objects) does not require complex and expensive queries to get the desired object. 
- Support operations for MapReduce massively parallel data processing. 


## Links to materials for the study 

* [Getting Started with MongoDB (MongoDB Shell Edition)](https://docs.mongodb.com/getting-started/shell/); 
* [MongoDB QuickStart](http://www.w3ii.com/ru/mongodb/mongodb_quick_guide.html); 
* [Guide to MongoDB (memoirs of a thoughtful programmer)](http://proselyte.net/tutorials/mongodb/); 
* [Security guide MongoDB](http://security-corp.org/administration/sys_admin/39539-rukovodstvo-po-bezopasnosti-mongodb.html) 

### Presentation 
* [MongoDB introductory lecture](https://www.youtube.com/watch?v=tgckAOyjXPI) 

### Recommended books 

* [MongoDB in action](https://www.ozon.ru/context/detail/id/8688130/); 
* [MongoDB in action (electronic version)](https://cafe-aristokrat.nethouse.ru/static/doc/0000/0000/0165/165988.c2f3acpbax.pdf) 
* [The Little MongoDB Book](http://www.pvsm.ru/download/mongodb-ru.pdf); 


## Software 

* [Install MongoDB](https://docs.mongodb.com/manual/installation/); 
* [Official Docker image for MongoDB](https://hub.docker.com/_/mongo/) 
* [Docker official image of the WEB interface MongoDB](https://hub.docker.com/_/mongo-express/) 

## Laboratory work and practical tasks 

To install mongoDB the easiest to use [docker image with MongoDB](https://hub.docker.com/_/mongo/). 

Start the server by the command: 
```sh
docker run --name mongodb -p 27017:27017 -d mongo
``` 
To work with the mongo server in containere it is recommended to run within the running container mongo-shell command: 
```sh
docker exec -it mongodb mongo
``` 

As practical tasks for laboratory work you can use [of the Task for the laboratory work 5 MongoDB](https://github.com/mesdt/course/wiki/Tasks-Mongo). On this page is given as [link to the test dataset](https://yadi.sk/d/3l92O1G6fJst5), and a list of tasks for you to perform operations on these data sets. 

## Examples 

Examples of the CRUD and aggregation operations specified in the previous section, data sets, please see pages: 
* [Command CRUD in MongoDB](https://github.com/mesdt/course/wiki/Cheat-list-Mongo) 
* [MongoDB Aggregation Framework](https://github.com/mesdt/course/wiki/Cheat-list-Mongo-Aggregation-Framework) 

## Opportunities for certification 

* [MongoDB Professional Certification Program ](https://university.mongodb.com/certification) 
* [Certification mongoDB](https://habrahabr.ru/post/273011/) 

## Go 

* [Mobile application development](gbt_mobile.html) 
* [Course home page](gbt_landing-page.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}