---
title: Пример загрузки графа объектов
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, объекты данных, пример
summary: Особенности загрузки объекта данных с большим количеством детейлов
toc: true
permalink: ru/fo_load-multiple-details-example.html
lang: ru
---

Здесь производится загрузка всех объектов, созданных в [примере](fo_actions-saving-object.html).

```csharp
Console.WriteLine("6. Loading a dataobject with multiple details.");
            
IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(D0));

D0 aggregator = new D0();
aggregator.SetExistObjectPrimaryKey(primaryKey);

// Чтобы ускорить загрузку, можно раскомментировать эту строку.
// aggregator.DisableInitDataCopy();
Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

dataService.LoadObject(D0.Views.FULLD0_E, aggregator);
stopwatch.Stop();
Console.WriteLine("Time taken for loading: {0} ms.", stopwatch.ElapsedMilliseconds);

// Убедимся, что в каждом объекте, в каждом детейловом свойстве содержится 10 детейлов, которые были сохранены туда.
ormSample.CheckDetailsQty(aggregator, 10);
Console.WriteLine("CheckDetailsQty: OK.");
```

Код метода `CheckDetailsQty`:

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
