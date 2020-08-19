--- 
title: Example of prototypical object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM example 
summary: an Example of creating copies of the data object 
toc: true 
permalink: en/fo_prototyping-example.html 
lang: en 
autotranslated: true 
hash: 30094c9be05483a861eca88ea278fc1daf00605c7fba5693253d33e9634cc2eb 
--- 

Full list of code examples [Flexberry ORM](fo_flexberry-orm.html) is in ["code Examples"](fo_code-samples.html). 

## Example of creating copies of the data object 

```csharp
Console.WriteLine("7. Prototyping.");
IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(D0));

D0 aggregator = new D0();
aggregator.SetExistObjectPrimaryKey(primaryKey);

Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

dataService.LoadObject(D0.Views.D0_E, aggregator);

stopwatch.Stop();
long loadObjectTime = stopwatch.ElapsedMilliseconds;
stopwatch.Restart();

// This method makes each object in the hierarchy of new (see listing below). 
aggregator.Prototype(true);

stopwatch.Stop();
long prototypingTime = stopwatch.ElapsedMilliseconds;
stopwatch.Restart();

dataService.UpdateObject(aggregator);

stopwatch.Stop();
long updateTime = stopwatch.ElapsedMilliseconds;

Console.WriteLine("Time taken for loading: {1} ms{0}prototyping: {2} ms{0}persistence: {3} ms.", Environment.NewLine, loadObjectTime, prototypingTime, updateTime);
``` 

Method code `Prototype`: 

```csharp
public virtual void Prototype(bool withDetails)
{
    KeyGenerator.Generate(this, null);
    this.SetStatus(ObjectStatus.Created);
    this.SetLoadingState(LoadingState.NotLoaded);
    this.SetLoadedProperties(new string[0]);
    InitDataCopy();
    if (withDetails)
    {
        string[] properties = Information.GetAllPropertyNames(GetType());
        foreach (string property in properties)
        {
            Type proptype = Information.GetPropertyType(GetType(), property);
            if (proptype.IsSubclassOf(typeof(DetailArray)))
            {
                foreach (D detobj in (DetailArray)Information.GetPropValueByName(this, property))
                    detobj.Prototype(withDetails);
            }
        }
    }
}
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}