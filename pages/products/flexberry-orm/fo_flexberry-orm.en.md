---
title: Flexberry ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: en/fo_flexberry-orm.html
---
## Product information

`Flexberry ORM` is [product platform Flexberry](fp_landing_page.html). The product website `Flexberry ORM`: [http://flexberry.ru/FlexberryORM](http://flexberry.ru/FlexberryORM).

{% include note.html content="Flexberry ORM is available for installation in the project via [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM)." %}

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

## Description of product

By [Flexberry Designer](fd_flexberry.html) it is possible to create [class diagram](fd_class-diagram.html). This [chart](fd_class-diagram.html) you can generate the required database structure (DB) and the object model. [Component of BD](fo_data-service.html) carries out mapping of the structure of relational database tables to an object model. To the database to retrieve items that meet certain conditions, use the [components for working with filters and limits](fo_limitation.html).

The list of libraries that contain the Runtime components Flexberry ORM submitted [here](fo_flexberry-orm-libraries.html).

Supported typing is described in [separate article](fo_flexberry-orm-types.html).

## The demo application
The sample application source code is available at the following location: <https://github.com/Flexberry/FlexberryORM-DemoApp>.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}