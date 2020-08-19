---
title: data Service XML
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data services
summary: XMLFileDataService to work with the database in XML format
toc: true
permalink: en/fo_xml-file-ds.html
lang: en
autotranslated: true
hash: a25e85d43eed58298c506f6535d6f3b36095c5b1041c920cf190c25e2d497627
---

`XMLFileDataService` data service is designed to work with the database in XML format. For correct operation of the service data required by the XSD schema of the database and the XML database file. You can get them using [XMLSchemaGenerator](fo_xml-schema-generator.html) (extension module [Flexberry Designer](fd_flexberry-designer.html) to generate the XSD schema of the database).

To work with data in XML format is used `System.Data.DataSet` support transactions and SQL queries. PstrfDataSet` also supports referential integrity constraints. To work `XMLFileDataService` you must enable the referential integrity constraints. Enable referential integrity by enabling properties `EnforceConstraints`. `XMLSchemaGenerator` by default, this property adds to `true`.

As the connection string specifies the path to `XSD` and `XML` files without specifying the extension (both files must have the same name).

For example, in the folder `C:\DataBase\` are files `base.xsd` and `base.xml`, then the path will look like the following: `C:\DataBase\base`



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}