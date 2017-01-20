---
title: Пример: прототипизация объекта
sidebar: product--sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/prototyping-example.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

Полный список примеров кода [Flexberry ORM](flexberry-o-r-m.html) находится в статье ["Примеры кода"](code-samples.html).

</td>
</tr></tbody></table></a>
</div>

# Пример создания копии объекта данных

```cs
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
```cs
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
