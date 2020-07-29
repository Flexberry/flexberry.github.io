---
title: Implementation of information systems
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, sales, tasks narabotki, user interface, application logic, data validation, prototype, Refine prototype, the steps for creating application
summary: challenges for the finalization and implementation of information systems
toc: true
permalink: en/fd_development.html
lang: en
autotranslated: true
hash: 530a3f040301518bc58d614551c3ecdc25e5ada3a25c38d21a986dc0af1ad89a
---

After the generation of prototype applications using the [Flexberry Desinger](fd_flexberry-designer.html) there is a need for its setup and completion. In each project the process will have its own characteristics, however, we can identify a set of typical challenges inherent in any project.

Mandatory for completion of tasks allocate the following:

1. [Customize user interface](fw_customizing-user-interface.html).
2. [You can customize the visual logic of the application](fw_visual-logic.html).
3. Implement the [data Validation](fw_edit-form-validation.html) on the edit forms.
4. Implemented [business logic](fo_business-logic.html) applications.
5. [Configure error handling](fp_error-handle.html) in the application.

Full list of topics presented below.

## Full list of topics

Section | Description
:------|:--------
[Data validation](fw_edit-form-validation.html) | System validation provides mechanisms to validate user input before it hits the database. There are 2 versions of validation: on the edit form and save the data.
[User interface customization](fw_customizing-user-interface.html) | After the automatic generation of the application there is a need to change the look, bring it to a particular style, but also to control application with keyboard. Technology Flexberry allows you to change the appearance of forms and individual controls.
[Visual logic](fw_visual-logic.html)|, Along with user interface customization is necessary to configure the visual logic of the application, which improves the appearance and reduces the number of user errors when working with the interface. This includes highlighting the active field, tooltips, predictive and so on.
[Business logic](fo_business-logic.html) | Flexberry Technology provides the possibility of checking data for compliance with the business logic when updating data. Business server — the class that directly contains the code of the business operations, provides the ability to process and validate data before importing into the database, and also often contain methods for reading data. Work with a copy of the data.
[Error handling](fp_error-handle.html) | When the user makes a mistake or the program performs an illegal action and an unplanned error, the program behavior must not cause the user confusion. The program does not have to crash to stop their work. Technology offers the possibility of catching errors and outputting them on a special form. There is also a possibility of logging of errors that occurred during execution of the program.
[Desk](fw_app-desktop.html) | desktop is a directory of links providing access to the components of the developed application. Desk is created for each class with the stereotype "Application" at the time of generating the application. In Desk included Starters, turning into links. Customize your desktop either Flexberry by the addition of Starters in a class, or through Code: for Windows applications desktop will be transformed into the file <ApplicationName>DesktopCustomizer.cs that provides configuration forms Desktop. For Web-applications - menu with a set of links located on the site-master E.
[Changes in the application model](fd_change-model.html) | Technology Flexberry allows you to make changes to the application model and transfer the changes into the generated application project, with a minimum of effort and without losing already made to the draft changes. Adding new classes. Add fields to existing classes. Renaming classes. Adding new links.
[Change the view of the list form](fd_listform.html) | View in list form is responsible for displayed in a list form data. When changes in the L-representation of a class will change it's list form
[Edit view edit form](fd_change-e-view.html) | View edit form is responsible for setting the form elements for editing, and also for setting up a GroupEdit'for editing objects of the class. When changes in E-class view, change and edit its objects.
[System authority](efs_secutity.html) | authority System allows you to configure the permissions of users and groups of users to access data objects, views, and forms, as well as to perform certain operations. The creation and setting permissions is done via the tool Security Console.
[Filter system](fw_filtersand-limits.html) | Filters is a tool of imposing user restrictions on a list of objects that define the range of data displayed. Filters are applied to the representation of the object and its presentation of datalow (if necessary). To create and configure filters, you use the constraint editor.
[Lock data](fo_lock-service.html) | multi-user application it is very important to monitor the conflicts of data access by various users. If the user started to edit the object, it is necessary to prevent that object from other users. Technology Flexberry service data locking to solve this problem.
[Integration report](fp_create-uni-report.html) | Technology Flexberry provides integration of applications with two systems of reporting: Statfor and StimulSoft. System to generate and produce printable reports with data developed applications.
[Custom date format](fw_date-format.html) | windows Only applications. Allows you to specify the date format that will be displayed and entered data with type "date".
[Setup audit](efs_audit.html) | Audit allows you to track user actions in the system. For windows and web applications is different.
[Integration with map](fg_landing_page.html) | Technology Flexberry allows you to integrate the app with the map using the GIS subsystem.
[Add new application](fd_application.html) | when the need arises to delineate the scope of different categories of users, you can create not one, but several different apps. Every application will be visible to a set of available forms and actions.
Import to database | If the application is a replacement for an existing system, it is often necessary database migration of the old system to the database application under development.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}