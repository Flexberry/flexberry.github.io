---
title: a Preparatory phase in the development of web applications
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_preparatory-stage.html
lang: en
autotranslated: true
hash: 6e07287c07529d9c796869b8b8ab21b3407d189af2966563efd4c2c106425fe3
---

{% include important.html content="the following algorithm for obtaining the web application is available after you have [a Practical guide for the creation of UML diagrams](gpg_practical-guides-uml.html)." %}

## Statement of the problem

The result of the practical example will be compiled and `сгенерированный код` ready Web application.

To the module code generation could generate the application, you need to create:

* submission
* form classes for editing
* form classes for lists
* the application class
* and also to perform certain settings.

To facilitate this process, in [Flexberry Designer](fd_flexberry-designer.html) use [rapid prototyping](fd_using-quick-prototyping.html), which performs these actions in a certain way. This results in a working application that can be used as a prototype when working with the customer in the initial stages of the project.

`Главное advantage of this способа` prototype obtained almost instantly» qmo, without noticeable downtime. Subsequently created with the help of prototypical objects can be modified, thereby bringing the system to the desired functionality.

## Prerequisites for generating a web application

To create [prototype web application](fd_prototype-creation.html) you must meet the following requirements:

1. Must be connected to the module ASP.NET in the application Flexberry Designer.
2. The repository should be created a structure up to the system.
3. Should be a class diagram.
4. Must have Visual Studio 2013 and above.
5. Must be installed MS SQL Server 2008 and above.

## Refinement of the model

Prototype applications can be created only if the [class diagram](fd_class-diagram.html). It should include all necessary attributes and their types, correctly describes the operation

To Refine the model, you need:

1. Open the class diagram `Сущности` established under [Practical guide for the creation of UML diagrams](gpg_practical-guides-uml.html).
2. To specify the types of class attributes, as is done in the following diagram:

![Example](/images/pages/guides/flexberry-aspnet/refined-ckass-diagram.png)

__Note:__

* The types of attributes you specify, and upon the first consideration of the system, but usually in the initial stages they fall in the uncertainty or the lack of need for them. At the stage of prototyping types must be specified.
* To verify the correctness of the attributes, you can view the properties of this class (in the class's popup menu select `Редактировать свойства`).
* When generating code from class diagram should be no relationships of type aggregation (can be used only Association, composition and inheritance), as well as links with a plurality `один-to-одному` or `многие-to-многим`.

3.To save the graph.

## Go

* [Practical guide «as I Do»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
* [Configure script generator for database](gpg_configuring-script-generator-db.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}