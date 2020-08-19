---
title: changes in the application model
sidebar: flexberry-designer_sidebar
keywords: Flexberry Security, View, brace programmer, relations, classes, performances, interface, powers, audit
summary: a refinement of the model application, modification, and deletion of attributes and relations
toc: true
permalink: en/fd_change-model.html
lang: en
autotranslated: true
hash: 24c5fb6a8c8de30cb42d4dfe69d9d4da27e698b8a3272305e3f1235160bb68c4
---

After generating a prototype of the application and make any changes, it is often necessary to make a number of changes to the application model. Adding new classes or changes to existing pretty hard work if you do it manually. If you make changes to the chart Flexberry and collect a prototype, then what will happen to already in the application changes?

Technology Flexberry allows you to make changes to the application model and transfer the changes into the generated application project, with a minimum of effort and without losing already made to the draft changes.

## Parentheses programmer

What is `скобки программиста` and why they are needed can be viewed in [article Brackets the programmer](fo_programmer-brackets.html)

## The impact of model changes on the app

Changes in the model, such as adding\deleting\renaming classes, adding or deleting connections between them or to add\remove fields from classes affect all the aspects of the generated application:

* Database
* [Views](fd_view-types.html)
* [Business logic](fo_business-logic.html)
* [Powers](efs_secutity.html)
* [Audit](efs_audit.html)
* [Filters](fw_filtersand-limits.html)
* [Reports](fp_create-uni-report.html)

### Adding classes

If the class is NOT detalam any other class, the changes affect:
* Forms: for a new class you must create L - and E - forms.
* Database: added new table
* Performance: for a new class you must create a view for all related classes - add new class to the view (where necessary).
* Powers: to create an access rule for the new class
* Reports (if any)
* Setting filters for a new class
* Set up auditing for a new class

If a class is detalam any other class, the changes affect:
* Forms: for class aggregator you need to make a change in the E-form (to add GroupEdit with a new class)
* Database: added a new table and link with class-aggregator
* Performance: for a new class you must create a presentation for class-aggregator - add detail in performance.
* Reports (if any)
* Setting filters for a new class of aggregator
* Configure auditing for a class-aggregator

### Remove class

* Forms: L - and E - forms to remove.
* Database: deletes the table that stores the class and all relations with this table
* Presentations: will be deleted along with the class.
* Reports (if any)
* Filter settings for the removed class and related classes
* Configure auditing for deleted class

### Adding relationships between classes

* Shape: for a class of non-artisans, on the edit form it is necessary to add the appropriate [LookUp-Overview](fa_lookup-overview.html)
* Database: added fields to tables to store non-artisans classes
* Presentations: you need to make changes in the presentation of all non-artisan classes affected by the new relationships, as well as all the other classes in which they appear.
* Reports (if any)


### Deletion of relationships between classes

* Shape: for a class that is not the workman, with the edit form it is necessary to remove the corresponding [LookUp-Overview](fa_lookup-overview.html)
* Database: make deleted fields to the tables that store non-artisans classes
* Presentations: you need to make changes in the presentation of all non-artisan classes affected by the deleted links and all the other classes in which they appear.
* Reports (if any)

#### Adding a field to an existing class

* Forms: you need to fix the L - and E-form for changing class
* Database: add a column to a table that stores the modified class
* View: change the view class by adding a new field, as well as in all performances, which featured this class.
* Reports (if any)

#### Removing a field from an existing class

* Forms: you need to fix E - and L - forms for modified class
* Database: delete a column from a table that stores the modified class
* Presentation: to change the representation of the class, removing the field, as well as from all views, which featured the class.
* Reports (if any)

## Algorithm changes

1. To make changes to the model classes using Flexberry
* Change classes.
* Edit links.
* Change the view.
2. To align database applications using Flexberry.
3. To regenerate the objects using Flexberry.
4. To compile objects through Flexberry.
5. To regenerate your forms via Flexberry.
6. To set permissions via the [Security Console](efs_security-console.html) (for Windows applications).
7. To configure filters using AdmConsole (optional).
8. To configure auditing(optional).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}