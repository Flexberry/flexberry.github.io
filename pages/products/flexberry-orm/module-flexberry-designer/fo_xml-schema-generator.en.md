--- 
title: Plugin to generate XSD schema 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry Designer, Flexberry ORM, plugins 
summary: Features XMLSchemaGenerator 
toc: true 
permalink: en/fo_xml-schema-generator.html 
lang: en 
autotranslated: true 
hash: f93ce0d13685b437544449c9b24bda7761a68040007478d8c6859f5c8227c6db 
--- 


`XMLSchemaGenerator` - Flexberry plugin to generate XSD schema of the database. The result of the operation of the generator is an XSD file with the schema of the database and an XML file with an empty database. 

`XMLSchemaGenerator` includes the following settings: 

* Enable\disable referential integrity constraints (including unique and primary key). It is the EnforceConstraints property in the XSD file. 
* Enable\disable strong name for the assemblies (this setting is necessary to generate schemas for different versions of assemblies). This setting replaces PublickKeyToken in the generated XSD schema for null. 

`XMLSchemaGenerator` supports any user-defined data types for object properties. To work correctly they need to make a custom data type supported interface `System.Xml.Serialization.IXmlSerializable`. 

By default, the database schema will get the standard table: `STORMAdvLimit`, `STORMFILTERDETAIL`, `STORMFILTERLOOKUP`, `STORMFILTERSETTING`, `STORMNETLOCKDATA`, `STORMSETTINGS`. Change its description in the XSD file is not recommended. 

The generated schema contains tags is set up to work with the XML database through `System.Data.DataSet`. Its support is implemented in [XMLFileDataService](fo_xml-file-ds.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}