--- 
title: Processing multiple objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: how to upgrade antipsych and different types of objects 
toc: true 
permalink: en/fo_processing-multiple-objects.html 
lang: en 
autotranslated: true 
hash: 2efd3af7e4eed039c526d315f43ad7a3994840289ee3474e86984bea2323078a 
--- 

## to update the objects 

To update in the store several data objects, whether they are homogeneous or heterogeneous, should call the method [of data services](fo_data-service.html) `UpdateObjects` with the parameter — a one-dimensional array (type [DataObject](fo_data-object.html)) these data objects. Requests for all of the transferred assets will be made in a single transaction. 

It should be remembered that the update of an object can call [update related objects](fo_update-related-objects.html), which clearly has not been specified in the array. 

In the General case [service data](fo_data-service.html) he knows how to build the right query to update the data objects. But possible situation when the related objects are important, the order of the objects in the array, for example: 

* The presence of cycles in the graph types. 
In particular, when objects are deleted in the case of circular references, the programmer must not only control the order of objects in the array, but deliberately to annihilate some link. 
* Removal of the object and its masters in the same transaction. 
The order in which objects came to the data service. 
* The situation where the aggregator and detail have a wizard of the same type. 
* Other options if you have related objects with different [status](fo_object-status.html), i.e. when a part is added, the part is updated, the part is removed. 

## General recommendation 

In the case of related objects, the artisans must be at the beginning of the array. That is, if there is a situation: 

![](/images/pages/products/flexberry-orm/data-service/primer-7.jpg) 

and there are objects: a, m1, m2, m3, then the correct array would be: 

```csharp
new DataObject[]){m3,m2,m1,a}
``` 

## Erroneous calculation of object status after refresh 

If the specified placement rule of the masters in the mountains is not observed, possibly incorrect calculation of the [status](fo_object-status.html) object after the update, if your objects had the status of `Сreated`. Namely: the created object and wizards to him (both with the status `Сreated`), the object sent for update. But the result is the following: 

```csharp
GetStatus() == ObjectStatus.Altered 
``` 

and [copy data](fo_data-object-copy.html) master of none. 

For example, created the following graph of objects with the master and aggregator: 

![](/images/pages/products/flexberry-orm/data-service/model.png) 

in `UpdateObjects` should pass objects in a certain order: artisan object (`Модель`) must be added before metalowy object (`Автомобиль`) using it: 

```csharp
    var objects = new List<DataObject>();

    var модель = new Модель { Наименование = "VAZ" };
    var клиент = new Клиент { Фамилия = "Ivanov", Имя = "Peter" };
    var авто = new Автомобиль { ГодВыпуска = 2012, /*Customer = customer, aggregator will be inserted automatically*/ Модель = модель };
    клиент.Автомобиль.Add(авто);
    objects.Add(модель);  // the wizard added before 
    objects.Add(клиент);

    var dataObjects = objects.ToArray();
    ds.UpdateObjects(ref dataObjects);
``` 

Otherwise Decalogue objects (`Автомобиль`) may have a [status](fo_object-status.html) `Altered` because of incorrect [data copy](fo_data-object-copy.html). 

__Note__: If [service data](fo_data-service.html) is the heir [SQLDataService](fo_sql-data-service.html) to solve the wrong for automatically determining the data service processing order of objects is proposed to use the method `UpdateObjectsOrdered` that performs the update of objects sequentially in the order in which they come in this method. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}