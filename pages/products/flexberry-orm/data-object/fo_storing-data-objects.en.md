--- 
title: Storing object data in a relational database 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, database 
summary: Principles of storing data objects in a database 
toc: true 
permalink: en/fo_storing-data-objects.html 
lang: en 
autotranslated: true 
hash: b10b7e0f335740b512a0c5d80fdc5a34c25eb12531851fdab5fcc6899e70b15a 
--- 

## stored As object data 

Most [of data services](fo_data-service.html) provide relational storage. 
Object data [Flexberry ORM](fo_flexberry-orm.html) stores the following: 

* Each class is a separate table. 
* Each attribute is a separate field in the table. 
* [Artisans of communication](fd_master-association.html) — foreign keys table of the inner class. 
* [Delaloye links](fo_detail-associations-properties.html) — foreign key dyelovoi table on the aggregator (cap). 
* [Inherits](fd_inheritance.html) class — separate from the ancestor table. 
* If [the artisan class](fd_master-association.html) is the heir, then his table should be a foreign key in the table of the inner class. 
* Each inherited table stores all attributes of all ancestors. Thus, one instance of the class corresponds to one record in one table (why is it done so described in the article [Inheritance](fd_inheritance.html)). 

## Naming when storing object data 

All elements of the object model stored in the repository under the specified name. For example, since relational storage each class is a separate table, each property is a separate field, the names of storage is the table names, fields. 

By default, the naming is as follows: 

* The name of the storage object data type name class data. 
* The name of the store object property data property name in the class data. 
* The name of the storage artisan properties is the property name in the class data [number]. 
* Communication from dyelovoi table on the aggregator — similar Masterova property: the property name in the class data [number]. 

* The name of the store the [primary key](fo_primary-keys-objects.html) — `primaryKey`. 
* When you inherit the names of foreign keys are given the following: [<Karolinenstr>_M<ПорядкНомерВTypeUsage>.ПорядкНомерВTypeUsage» «— begins with 0](fo_type-usage.html) (this naming can be changed). 

The naming can be changed, what are the attributes of storage: 

* [ClassStorage](fd_data-classes.html) — should be attributed to the data class, specifying the required name of the store. 
* [PrimaryKeyStorage](fd_data-classes.html) — similar to a primary key assigned to the data class. 
* [PropertyStorage](fo_attributes-class-data.html) — the same for all properties (private, artisans), attributed to the appropriate property.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}