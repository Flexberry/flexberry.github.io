---
title: Пример прототипизации объекта
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, пример
summary: Пример создания копии объекта данных
toc: true
permalink: ru/fo_prototyping-example.html
lang: ru
---

Полный список примеров кода [Flexberry ORM](fo_flexberry-orm.html) находится в статье ["Примеры кода"](fo_code-samples.html).

## Пример создания копии объекта данных

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

// Этот метод делает каждый объект в иерархии новым (листинг приведен ниже).
aggregator.Prototype(true);

stopwatch.Stop();
long prototypingTime = stopwatch.ElapsedMilliseconds;
stopwatch.Restart();

dataService.UpdateObject(aggregator);

stopwatch.Stop();
long updateTime = stopwatch.ElapsedMilliseconds;

Console.WriteLine("Time taken for loading: {1} ms{0}prototyping: {2} ms{0}persistence: {3} ms.", Environment.NewLine, loadObjectTime, prototypingTime, updateTime);
```

Код метода `Prototype`:

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