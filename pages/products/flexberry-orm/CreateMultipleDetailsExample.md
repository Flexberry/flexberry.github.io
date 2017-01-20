---
title: Пример: большое количество детейлов
sidebar: flexberry-orm_sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/fo_create-multiple-details-example.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

Полный список примеров кода [Flexberry ORM](flexberry-o-r-m.html) находится в статье ["Примеры кода"](code-samples.html).

</td>
</tr></tbody></table></a>
</div>

# Создание и сохранение объекта с большим количеством детейлов

Данный тест осуществляет генерацию большого графа объектов с [детейловыми связями](detail-associations-and-their-properties.html) и его сохранение в БД.
Используемая [диаграмма классов](class-diagram.html) имеет вид:
![Sample Picture Caption](/images/img/page/CreateMultipleDetailsExample/Details.png)

При этом все классы [наследуются](inheritance.html) от нехранимого абстрактного класса `D`.
Для каждого массива детейлов каждого объекта генерируется по 10 детейлов, затем все это сохраняется в БД путем передачи [сервису данных](data-service.html) корневого объекта графа.

```cs
Console.WriteLine("5. Create a dataobject with multiple details.");

// Создаем объект данных, у которого несколько детейловых свойств. 
D0 aggregator = new D0();

// Вызываем метод генерации детейловых объектов. Код метода приведен ниже.
// Иерархия детейлов устроена таким образом, что при задании количества детейлов 10 для каждого объекта
// общее число объектов данных будет превышать 30000.
OrmSample ormSample = new OrmSample();
ormSample.GenDetails(aggregator, 10);

Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

// Объекты будут сохранены в порядке, соответствующем связям между ними (от корня до кончиков).
DataServiceProvider.DataService.UpdateObject(aggregator);

stopwatch.Stop();
Console.WriteLine("Time taken for persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
```
Метод `GetDetails`:
```cs
internal void GenDetails(D dobj, int qtyInEach)
{
    RandomStringGenerator rsg = new RandomStringGenerator();
    dobj.Name = rsg.Generate(200);
    dobj.S1 = rsg.Generate(200);
    dobj.S2 = rsg.Generate(200);
    dobj.S3 = rsg.Generate(200);
    dobj.S4 = rsg.Generate(200);
    dobj.S5 = rsg.Generate(200);

    string[] detprops = Information.GetPropertyNamesByType(dobj.GetType(), typeof(DetailArray));
    for (int i = 0; i < detprops.Length; i++)
    {
        DetailArray detarr = (DetailArray)Information.GetPropValueByName(dobj, detprops[i]);
        Type dettypetocreate = Information.GetCompatibleTypesForDetailProperty(dobj.GetType(), detprops[i])[0];
        for (int j = 0; j < qtyInEach; j++)
        {
            D newobj = (D)Activator.CreateInstance(dettypetocreate);
            GenDetails(newobj, qtyInEach);
            detarr.AddObject(newobj);
        }
    }
}
```
