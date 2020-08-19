--- 
title: Business logic 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, business servers 
summary: principles of the architecture of business server 
toc: true 
permalink: en/fo_business-logic.html 
lang: en 
autotranslated: true 
hash: cb411dd0c72bd482b9ec5ea71b7cdd094d7ae21fc5f072395b9c50a4eecafa69 
--- 

The business logic is part of the [three-tier architecture](https://ru.wikipedia.org/wiki/Трехуровневая_архитектура) between the client part and the database. 

The business logic of the application passed to make the [business server](fd_business-servers.html). By default, there are only check data update for certain classes (methods OnUpdate), but also there is a need to make methods that implement the business transactions and work with data ([reading from DB](fo_sql-query.html)). 

## Restrictions 

* All data and all business transactions should be made in a business server. 
* No user interface elements in the business servers should not be (Forms, MessageBox's, etc). 
* In each business server must be your [service data](fo_construction-ds.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}