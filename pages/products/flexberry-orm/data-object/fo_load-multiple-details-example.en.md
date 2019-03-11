--- 
title: Example of loading the object graph 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data objects, example 
summary: Features downloading of the object data with a large number of datalow 
toc: true 
permalink: en/fo_load-multiple-details-example.html 
lang: en 
autotranslated: true 
hash: 5e87aeb1ab8ebcb2ff1f0f14d15135467412e36104a767802c019cb4d8f854cf 
--- 

Here is the download of all objects created in the [example](fo_actions-saving-object.html). 

```csharp
Console.WriteLine("6. Loading a dataobject with multiple details.");
            
IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(D0));

D0 aggregator = new D0();
aggregator.SetExistObjectPrimaryKey(primaryKey);

// To speed up the boot, you can uncomment this line. 
// aggregator.DisableInitDataCopy(); 
Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

dataService.LoadObject(D0.Views.FULLD0_E, aggregator);
stopwatch.Stop();
Console.WriteLine("Time taken for loading: {0} ms.", stopwatch.ElapsedMilliseconds);

// Make sure each object in each dealova property contains 10 detailov that were stored there. 
ormSample.CheckDetailsQty(aggregator, 10);
Console.WriteLine("CheckDetailsQty: OK.");
``` 

Method code `CheckDetailsQty`: 

```csharp
internal void CheckDetailsQty(D dobj, int qtyInEach)
{
    string[] detprops = Information.GetPropertyNamesByType(dobj.GetType(), typeof(DetailArray));
    for (int i = 0; i < detprops.Length; i++)
    {
        DetailArray detarr = (DetailArray)Information.GetPropValueByName(dobj, detprops[i]);
        if (detarr.Count != qtyInEach) 
            throw new Exception(string.Format("Missing reading of {0}!", detprops[i]));

        for (int j = 0; j < detarr.Count; j++)
        {
            D obj = (D)detarr.ItemByIndex(j);
            CheckDetailsQty(obj, qtyInEach);
        }
    }
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}