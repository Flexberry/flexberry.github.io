---
title: Пример загрузки графа объектов
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, пример, детейл
summsry: Особенности генерации мультидетейлов
toc: true
permalink: ru/fo_create-multiple-details.html
lang: ru
---

Полный список примеров кода [Flexberry ORM](fo_flexberry-orm.html) находится в статье ["Примеры кода"](fo_code-samples.html).

## Создание и сохранение объекта с большим количеством детейлов

Данный тест осуществляет генерацию большого графа объектов с [детейловыми связями](fo_detail-associations-properties.html) и его сохранение в БД.
Используемая [диаграмма классов](fd_class-diagram.html) имеет вид:

![](/images/pages/products/flexberry-orm/data-object/details.png)

При этом все классы [наследуются](fd_inheritance.html) от нехранимого абстрактного класса `D`.
Для каждого массива детейлов каждого объекта генерируется по 10 детейлов, затем все это сохраняется в БД путем передачи [сервису данных](fo_data-service.html) корневого объекта графа.

```csharp
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

```csharp
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
