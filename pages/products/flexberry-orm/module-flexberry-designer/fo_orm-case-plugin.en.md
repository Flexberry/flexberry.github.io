---
title: Generation of application code for uml models
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM, plugins
summary: of Osobennosti work with the stage configuration and system database
toc: true
permalink: en/fo_orm-case-plugin.html
lang: en
autotranslated: true
hash: 32682583c43c5f787fceece140e7a00dea9f3bfe247cfcc4f12476c12b446e65
---

`Flexberry ORM Plugin` is an expander [Flexberry Designer](fd_flexberry-designer.html), provides opportunities for the creation and correction of application models, generate code and databases.

Depending on the [level of repository object](fd_recommended-structure-repository.html) plug-in provides different features.

Opportunities in General are divided into the following groups:

* `C#`. In this menu item holds all the operations for working with the application code.
* `SQL`. SQL language for database management. This menu item allows you to create and edit database modelled application.
* `Утилиты`. In this menu item features utility.

## Opportunities for stage

Below are the features provided by the plugin, for the stage.

### Code (C#)

#### The properties of the model

Form property settings of the model allows to describe basic and advanced properties of the model. Description of the form presented in the article [setting the model for generation](fd_project-customization.html).

#### Generate

Allows to generate a kind of "backbone" of the future application base code, which subsequently can be modified.

Selecting this menu item will display the form with the offer to generate (objects), business server (BusinessServers). Business servers will be generated only if it is provided by the model. Also you can generate all at the same time.

The features and capabilities of code generation:

* [Description of process of generation of code](fd_code-generation.html)
* [Brackets programmer](fo_programmer-brackets.html)
* [The location of assemblies after code generation](fo_location-assembly.html)
* Header (`Caption`) for classes is generated only if it differs from the class name.
* When the generation is taken into account [the specified path for generation](fd_generation-path.html).

#### Compile

Allows to obtain an equivalent description of the algorithm in machine-oriented language. Without compiling the code will be impossible.

Selecting this item will also be offered several options, similar to the item "Generate".

#### Generate and compile

Allows simultaneous generation and compilation of the application code based on the created model.

#### Open in Visual Studio

When you select this menu item to be generated (or generated and compiled) code will be opened in MS Visual Studio version that is installed on your PC.

### SQL

For all available databases, the list of menu options is about the same.

`Настройка БД`

The settings database in the MS SQL example described in the article [database Settings](fd_configure-ms-sql-generator.html).

`Привести database in accordance with моделью`

When you select this menu item, the database will be [adjusted in accordance with the model](fd_matching-db.html) (DB will be created (if it wasn't) and edited in accordance with the changes in the model).

`Сгенерировать SQL`

Allows you to generate a script to create/update the database.

#### Microsoft SQL Server

The main query language used — `Transact-SQL`. Used to work with databases in the size from personal to large databases across the enterprise.

#### Oracle

`Oracle` is one of the most common of today's database management systems. In this database are all the modern requirements such as multi-user access to the system in client-server mode, multi-level protection from unauthorized access, implementation of the principle of independence of data, etc.

#### Postgre SQL

A free object-relational database management system. `PostgreSQL` is based on the SQL language and supports many features of the SQL standard:2011.

#### Microsoft Access

Relational DBMS. Has a wide range of functions, including related queries, communication with external tables and databases.

### Utilities

#### Manager classes

[Manager class](fd_class-manager.html) contains information about all the classes of the created model, and also a indication of type and number of references in the model.

#### Manager associations

This paragraph contains information about the relationships between classes and specify the type of connection as well as the number of references to relations.

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/association manager.png)

#### Manager inheritances

This item contains information about the [inheritance](fd_inheritance.html): the indication of the ancestor, the heir(s), the number of references.

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/inheritance-manager.png)

#### Manager views

This section contains information about all the [views](fd_view-definition.html), existing models specify the class to which belongs the view name and view properties.

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/view-manager.png)

#### To update the submission

This menu item allows you to detect errors in submissions, and to remove irrelevant properties.

#### To find the errors in the model

Check models for the presence of unused objects and error metadata.

#### Export stage

Allows you to export the stage to either a local folder on your PC. This allows you to use the stage in other application models.

To import stage you need to use a menu module at the configuration level.

#### Save chart to wmf format

Allows you to save charts of the stages in the WMF format.

### License information

Contains information about licenses: product name, contact person, time of action, the license file and additional information about the license. Or, if the license is incorrect, will be to divert [to the page of acquisition of a license](http://flexberry.ru/Buy).

## Possibilities for configuration

Below are the features provided by the plugin for configuration.

### Utilities

#### Import stage

Allows you to import stage in the specified configuration.

In addition to this, to import stages there is a special import mechanism.

### License information

Similarly as for stage.

## Opportunities for system

Below are the features provided by the plugin system.

### Utilities

#### To create the views

This menu item invokes the create prototypes.

The basic version of this form __operates in a restricted mode__. In particular, as the name of the application you can enter an arbitrary name in the base installation it is __ignored__.
The prefix specifies the prefix name the created views (the prefix can be omitted). The result of the form will generate the presentation for all [classes data](fd_data-classes.html) presented at the [diagrams](fd_class-diagram.html) system, with the following names:

* [A<prefixpriority>_]<Imagescaling>L (a view with that name is not generated for [datalow](fd_key-concepts.html)).
* [A<Prefixpriority>_]<Imagescaling>E.

#### Charting classes in the database

There is possibility to generate class diagrams [on the structure of the database](fo_reverse-data-base.html).

#### License information

Similarly as for stage.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}