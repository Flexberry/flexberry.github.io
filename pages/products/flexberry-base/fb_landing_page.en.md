---
title: Flexberry Base
keywords: rms
sidebar: flexberry-platform_sidebar
toc: false
permalink: en/fb_landing_page.html
lang: en
---

### Description

**Flexberry Base** - This is a functional subsystem of the flexberry platform to create applications with a database. This subsystem is implemented in the frameworks [Flexberry Ember](ef3_landing_page.html), [Flexberry ASP.NET](fa_landing_page.html) and [Flexberry Winforms](fw_landing_page.html).

### Possibilities

* Saving data objects in a database
* Searching and viewing as a list of data objects from a database
* Editing and deleting data objects from the database
* Setting up a complex data selection (set of fields, sorting, filter) and saving this setting for later use
* Managing data reading based on sets of properties - views (projections, views)
* Support for enumerated data types
* Catalogue selection support (association link)
* Support for composite aggregation: The data object may include an array of other objects that will be edited and displayed with it.
* Support for inheriting data objects in the Table Per Class (TPC) paradigm.
* Support for pessimistic blocking while working with multiple users' data objects at the same time
* Support business logic at the data access level

### Architecture

The main concepts of the **Flexberry Base** architecture are:

* A data access layer with the ability to define business logic when accessing the database
* A desktop form with navigation
* An edit form with controls for editing a data object
* Form with a list of data objects (list form)


The hierarchical elements of the **Flexberry Base** architecture are used in the implemented application domain 


### How to use

To create an application based on **Flexberry Base** is enough to design and generate the application in [Flexberry Designer](developers-flexberry-designer.html). You can choose the appropriate framework for the application:[Flexberry Ember](ef3_landing_page.html), [Flexberry ASP.NET](fa_landing_page.html) and [Flexberry Winforms](fw_landing_page.html) - They all support the functionality of the subsystem **Flexberry Base**.
