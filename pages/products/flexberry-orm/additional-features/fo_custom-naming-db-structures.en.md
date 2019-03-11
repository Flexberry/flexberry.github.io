--- 
title: Sample user names for the structures in the database 
sidebar: flexberry-orm_sidebar 
keywords: Public, example, database, Flexberry Desinger, charts 
summary: the Job names for the database 
toc: true 
permalink: en/fo_custom-naming-db-structures.html 
lang: en 
autotranslated: true 
hash: e52ecaef7a417a423caa333e40333be80f2442c529ae53fc973d815896129c1b 
--- 

There are 2 ways for [definition name](fo_storing-data-objects.html): 

* in Flexberry Desinger 
* C# code using attributes. 

If you choose the first path, then Flexberry Desinger will generate appropriate names automatically. 

You can relate data objects and attributes with any name, DB-structure](fo_storing-data-objects.html): 

* The table name for a class (attribute [ClassStorage](fd_data-classes.html) class); 
* The column name for the attribute (attribute [PropertyStorage](fo_attributes-class-data.html) for the property); 
* The name of the column [primary key (Primary key)](fo_primary-keys-objects.html) for the identifier of the object data (attribute [PrimaryKeyStorage](fd_data-classes.html) class); 
* Column name foreign key (Foreign key) to dependencies of the master (attribute [PropertyStorage](fd_master-association.html) for the property); 

For example, figure `DB structures custom naming`: 

* `CustomDBOwnClass` refers to table `CustomDBOwn`; 
* `CustomDBOwnClass.CustomOwnAttribute` refers to the column in the table `CustomOwn` `CustomDBOwn`; 
* `CustomDBMasterClass` refers to table `CustomDBMaster`; 
* `CustomDBMasterClass.CustomMasterAttribute` refers to the column in the table CustomMaster `CustomDBOwnCustomDBMaster`; 
* Attitude `CustomDBOwnClass` to `CustomDBMasterClass` determined `CustomDBMaster` column in the table `CustomDBOwnClass`; 
* The IDs of both classes are `pkey` column in the relevant tables `tables` 

Simply create objects and save them. 

```csharp
CustomDBOwnClass cdbo = new CustomDBOwnClass();
CustomDBMasterClass cdbm = new CustomDBMasterClass();
cdbm.CustomMasterAttribute = new RandomStringGenerator().Generate(200);
cdbo.CustomDBMasterClass = cdbm;
cdbo.CustomOwnAttribute = new RandomStringGenerator().Generate(200);

IDataService dataService = DataServiceProvider.DataService;
ICSSoft.STORMNET.DataObject[] objstoupd = new ICSSoft.STORMNET.DataObject[] { cdbo, cdbm };
dataService.UpdateObjects(ref objstoupd);

Console.WriteLine("OK!");
``` 

Full list of code examples Flexberry ORM is in ["code Examples"](fo_code-samples.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}