---
title: database Settings
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, stage, map types, database, model
summary: the Possibility of plugins in the configuration database
toc: true
permalink: en/fd_configure-ms-sql-generator.html
lang: en
autotranslated: true
hash: 495e3534b165071b0ad9150bda6708535548f06c1dff361f19f88b3729760c5c
---

Form database settings allows you to configure the database in accordance with the requirements of the application, set the basic settings to be considered when generating the database model. This form is called via the menu [plugin](fo_orm-case-plugin.html).

* `Название стадии` and `Описание` the same [corresponding settings of the model](fd_project-customization.html).
* `Карта типов` - [map types](fd_types-map.html).
* `Строка соединения` - specify the location of the database.
* `Не remove existing таблицы`. Enabled by default. (Used when [bringing the database into line with the model](fd_matching-db.html)).
* Pre-script - script that is inserted at the beginning of the generated script.
* Post-script - script that is inserted into the end of the generated script.

The following settings are used in subsystems that are __not available__ in the base installation [Flexberry Designer](fd_flexberry-designer.html):

* `БД audit database приложения` - if the option is set in the database when you generate appear in the audit tables.
* `БД web database reports приложения` - if the option is set in the database when generating the table will appear in the web reports.
* `БД powers in DB приложения` - if the option is set in the database when you generate will appear in the table of powers.

![Property script](/images/pages/products/flexberry-designer/generate/sql properties.png)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}