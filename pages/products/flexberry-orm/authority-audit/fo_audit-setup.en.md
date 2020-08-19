---
title: setting up auditing in Flexberry Designer
sidebar: flexberry-orm_sidebar
keywords: Flexberry Audit, Flexberry Designer
toc: true
permalink: en/fo_audit-setup.html
lang: en
autotranslated: true
hash: 2c008b5f4ec8ff4b572a39b7f59f27518f65ebbfde7dcad45009635cc029f540
---

## Audit

Setting the [audit](fa_audit-web.html) using [Flexberry Designer](fd_flexberry-designer.html) is carried out in several stages.

### Configuring database audit

Database auditing can be stored in a separate database and database application.

The configuration is done on the settings form [MS SQL Direct Generator](fd_configure-ms-sql-generator.html) (see para database audit, database applications). Also part of the database configuration is in the properties of a class with the stereotype [Application](fd_application.html).

{% include note.html content="When using database applications as a repository for audit `рекомедуется` to tick `Не remove existing таблицы` on the settings form [MS SQL Direct Generator](fd_configure-ms-sql-generator.html) to avoid problems with alignment of the database and application model." %}

### Setup stage

Configure auditing for the stage are performed on the settings form the Stage tab `Настройка аудита`

![Example](/images/pages/products/flexberry-orm/audit/audit-settings-stady.png)

* `Вести audit приложения` - the need to audit for the entire application.
* `Режим audit records for умолчанию` - mode recording audit data in the default database (synchronous or asynchronous). Will be set for classes when you generate the audit settings. If the class you specify a different configuration for this particular class will be used this it => priority settings the stage is less than the priority of the settings class.
* `Включить audit in all классах` - button switch audit all **available** classes stage. Enable auditing involves the installation settings `Вести audit класса`, create view `AuditView` and stamp of audit settings by default.
Setting default audit includes:
* Installation configuration audit `Вести класса`
* Installation settings `Добавить field аудита`
* Installation configuration audit `Вести operations создания`
* Installation configuration audit `Вести operations удаления`
* Installation settings `Режим record аудита` as `Синхронный`

When adding a new class requires that you manually enable auditing or repeated pressing of this button.
As the operation enable to output information to the log.

## Set up the classes

Configure auditing for classes are on the Class settings tab `Настройка аудита`

![Example](/images/pages/products/flexberry-orm/audit/audit-settings-class.png)

### Configure auditing

Setting name | Description
:------------------|:--------
`Вести класса` audit | audit this class
`Добавить field аудита` | Add the private field audit](efs_flexberry-audit-object-fields.html) in the field class.
`Использовать view умолчанию` | Use for all operations in the default view (the default view is the view with the name `AuditView`
`Вести audit operations создания` | necessity of conducting the audit object creation
`Имя submission to audit operations создания` | Performance, which is audited create objects. If you specify the default view, this setting is ignored
`Вести audit operations удаления` | necessity of conducting the audit the deletion of objects
`Имя submission to audit operations удаления` | Performance, which is audited deletion of objects. If you specify the default view, this setting is ignored
`Вести audit operations изменения` | the Necessity of conducting a change audit objects
`Имя submission to audit operations изменения` | Performance, which is audited object changes. If you specify the default view, this setting is ignored
`Вести audit operations чтения` | necessity of conducting the audit object read (*currently not supported*).
`Имя submission to audit operations чтения` | Performance, which is audited read objects. If you specify the default view, this setting is ignored
`Путь to view объекта` | Path to the display form of the object data (*option not yet available*). Allows you to view the object at a certain time (view audit)
`Режим record аудита` | write Mode audit data in the default database (synchronous or asynchronous)

## AuditView - performance audit

[View](fd_view-definition.html) for the audit is generated at the time of pressing the button `Включить audit in all классах` setting the stage for all data classes. It can also be created manually.
If you change the object will persist data on changes in **fields** in the performance `AuditView`.
If no changed **field** gets the view change record **object** is still created.
In a special way, it is necessary [to form an idea in the presence of calculated properties](efs_not-stored-properties-and-audit.html).

## Customize application

Configure auditing for a class of applications (with the stereotype [Application](fd_application.html)) have been made in the form of glass on the settings tab `Настройка аудита`

![Example](/images/pages/products/flexberry-orm/audit/audit-app-settings.png)

* `Имя connection string to the database аудита` - **name** connection string that will be passed to Win-an audit service, to point to the audit database.
* `Адрес service аудита` - the address at which you'll Win-service audit.
* `Вести audit sessions пользователей` - set of the audit sessions.

**Note:** these settings will only be valid after the implementation of the [second stage](devprocess_audit-stages.html)

## Generation settings

Among the settings of the audit defined in Flexberry Designer, we can distinguish the following types:

* **Setting the stage** (General settings for all audit generated from the stage of application).
* **Settings classes with the stereotype `application`** (auditing settings defined for a particular generated application (currently ASP generator supports generating only one application)).
* **Settings classes with the stereotype `implementation`** (auditing settings that are defined for specific classes that will be included in the Assembly of objects).

The generation of the settings of the audit of Flexberry Designer *web application*

![Example](/images/pages/products/flexberry-orm/audit/audit-setting-generate.png)

Settings classes with the stereotype `implementation` will be generated in the code object a generator object.
Setting the stage and class with the stereotype `application` will be generated. config file on a web ASP application generator.

## Examples of settings

* [Example of connection audit to an existing Web application using regeneration](fa_audit-web-example.html)

If there is no desire or ability to regenerate your project, you can use the following instructions:

* [Example of connection audit to an existing Web application without using regeneration project](fa_audit-web-example-manual.html)
* [Example of connection audit to an existing Win application without the use of a regeneration project](efs_audit-win-example-manual.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}