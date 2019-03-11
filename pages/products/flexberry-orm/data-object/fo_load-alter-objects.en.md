--- 
title: Example of load changes of the object 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data objects, example 
summary: an Example of loading a data object on a view, change it and save 
toc: true 
permalink: en/fo_load-alter-objects.html 
lang: en 
autotranslated: true 
hash: 8de9aa739ec2ef2f49f6b5a22a9c5ced9c7c247645fbab2752c910595ffce3ab 
--- 

In this example, the following actions are performed: 

* Taken [primary key](fo_primary-keys-objects.html) of any of the available database objects of the class `CDDA`. 
* Creates a new instance of the class `CDDA`, it is assigned a this [primary key](fo_primary-keys-objects.html). 
* Loads the properties of this instance via [service data](fo_data-service.html). Values are retrieved from the record corresponding to the set [primary key](fo_primary-keys-objects.html). 
When loading is applied [view](fd_view-definition.html). 
* You modify the properties and save the object. 

```csharp
Console.WriteLine("2. How to load a dataobject in specific view, change it\'s property, then persist. Object loading status and state.");

IDataService dataService = DataServiceProvider.DataService;

// Initialize the helper object through which we get the primary key of an arbitrary object 
// of the specified type. 
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(CDDA));

// To download the item data, you must create a new instance of the data object to assign the existing primary key, and then 
// pass it to the method LoadObject data service. The record with the same primary key must exist in the database. 
CDDA cdda = new CDDA();
cdda.SetExistObjectPrimaryKey(primaryKey);

// Except for the instance whose properties you want to load, LoadObject method peredaetsa performance. 
// View is a set of object properties. Views can be created in Flexberry or attribute of the ViewAttribute. 
// In this case, the representation determines which properties will be loaded. 
dataService.LoadObject(CDDA.Views.CDDA_E, cdda);

// After loading the object status (cdda.GetStatus()) is equal to ObjectStatus.UnAltered. After calling the next line it changes to ObjectStatus.Altered. 
// You can only change the loaded properties. Otherwise, when the object is saved will receive an error. 
cdda.Name = "Blablabla " + DateTime.Now;

Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

// Save the object. Just uploaded updated properties. 
dataService.UpdateObject(cdda);
            
stopwatch.Stop();
Console.WriteLine("Time taken for persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}