---
title: Flexberry ORM
keywords: storage, store, mapping, database
sidebar: flexberry-orm_sidebar
toc: false
permalink: en/fo_landing_page.html
lang: en
autotranslated: true
hash: cff2a25396e2f1bb6473a5e67cce1045a1f84707db1aa2b3add73392543fa3a5
---

`Flexberry ORM` is [technological component of the platform Flexberry](https://flexberry.net/ru/developers-flexberry-orm.html) is designed to provide the data access layer.

`Flexberry ORM` available for installation in the project via NuGet:

* [NewPlatform.Flexberry.ORM](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM).
* [NewPlatform.Flexberry.ORM.ODataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService).

PstrfFlexberry ORM` source code is available on [GitHub](https://github.com/search?q=topic:orm org:Flexberry&type=Repositories).

## The product

`Flexberry ORM` consists of the following modules:

* [Components for working with the object model (data objects).](fo_data-object.html)
* [Access components data source (data services).](fo_data-service.html)
* [Views](fd_view-definition.html)
* [Components for filtering the received data (constraints).](fo_limitation.html)
* [Pessimistic locking components, providing the ability to lock the data objects during the execution of operations over them.](fo_lock-service.html)
* [Business server](fo_business-logic.html).
* The [task monitor](fo_business-task-monitor.html)
* [Expansion module Flexberry Designer to generate code for uml models.](fo_orm-case-plugin.html)
* [Auxiliary tools ICSSoft.STORMNET.Tools.](fo_ics-soft-stormnet-tools.html)
* [Components for OData V4 interface for fast creation of progressive web services](fo_orm-odata-service.html)

## Opportunities and application

By [Flexberry Designer](fd_flexberry.html) creates a [class diagram](fd_class-diagram.html). This [chart](fd_class-diagram.html) you can generate the required database structure (DB) and [object model](fo_data-object.html). [Component of BD](fo_data-service.html) carries out mapping of the structure of relational database tables to an object model. To the database to retrieve items that meet certain conditions, use the [components for working with filters and limits](fo_limitation.html).

The list of libraries that contain the Runtime components Flexberry ORM submitted [here](fo_flexberry-orm-libraries.html).

Storage types and the corresponding [data services](fo_standard-data-services.html):

* [PostgreSQL](fo_postgres-data-service.html)
* [Microsoft SQL Server](fo_mssql-data-service.html)
* [Oracle](fo_oracle-data-service.html)
* [Yandex ClickHouse](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ClickHouseDataService)
* [MongoDB](fo_mongodb-data-service.html)

## The demo application

The sample application source code is available at the following location: <https://github.com/Flexberry/FlexberryORM-DemoApp>.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}