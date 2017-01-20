---
title: Пример: загрузка графа объектов
sidebar: product--sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/load-multiple-details-example.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

Полный список примеров кода [Flexberry ORM](flexberry-o-r-m.html) находится в статье ["Примеры кода"](code-samples.html).

</td>
</tr></tbody></table></a>
</div>



# Загрузка объекта с большим количеством детейлов

Здесь производится загрузка всех объектов, созданных в [предыдущем примере](data-object-update-hook-example.html).

```cs
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
```cs
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
