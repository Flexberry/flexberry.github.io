---
title: Example of action when the object is saved
sidebar: flexberry-orm_sidebar
keywords: DataObject ORM Flexberry
summary: Additional steps when working with the data object in the application
toc: true
permalink: en/fo_actions-saving-object.html
lang: en
autotranslated: true
hash: f04613aaaf542116c2961f7c273ccbbabfb353a68c555e7ce9f2f0073aa328f3
---

Often in applications there is a need to perform any additional steps when adding, changing, or deleting data objects, for example, an entry in the application log.
In [Flexberry ORM](fo_flexberry-orm.html) this feature is implemented by using classes called [business server](fo_business-server.html). Such classes must contain the methods named a certain way, to perform actions to your desired stage.

```csharp
Console.WriteLine("4. How to do something persistence at the moment.");

IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(CDDA));

// How to do something in the time you save: 
// 1. Ask for the entity class business server Flexberry or using .Net-attribute BusinessServer (see CDDA class in the project CDLIB(Objects)). 
// 2. Generate from Flexberry or create your own business class server with a method that handles storing objects, and implement it 
// (see class CDLibBS in the project CDLIB(BusinessServers)) 
// 3. Perform any action with the object and save it. 
CDDA cdda = new CDDA();
cdda.SetExistObjectPrimaryKey(primaryKey);
            
Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();
            
dataService.LoadObject(CDDA.Views.CDDA_E, cdda);
cdda.Name = "Huh! " + DateTime.Now;
dataService.UpdateObject(cdda);
stopwatch.Stop();
Console.WriteLine("Time taken for loading and persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
```

Example [method business server, triggered when updating a data object](fo_user-operations-dataservice.html) class `CDDA`:

```csharp
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateCDDA(IIS.CDLIB.CDDA UpdatedObject)
{
    // Change the properties of the persisted object. 
    UpdatedObject.TotalTracks = UpdatedObject.Track.Count;

    // The return value contains an array of objects that need to be updated, in addition UpdatedObject. 
    // Contents depends on the business logic. If nothing else upgrade is not required, an empty array is returned. 
    return new ICSSoft.STORMNET.DataObject[0];
}
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}