---
title: Business server (BusinessServer)
sidebar: flexberry-orm_sidebar
keywords: BS
summary: the Basic principles of developing a business server
toc: true
permalink: en/fo_business-server.html
lang: en
autotranslated: true
hash: 54a4d214c907262bcefe496875c32ea5883d7bedb64a4c9ae7530bc74f0693bc
---

The business server has a set of methods. In `UML` business server rendered [UML class with the executable businessserver» qmo](fd_business-servers.html). The system can contain an arbitrary number of business servers. In General, the number of business servers and their methods is defined by an application developer.

`Бизнес-сервер` class that contains the code of the business operations.

## Example of the generated code business server

For each data object, whose UML diagram is specified BusinesServer generated method like this (in this case, the class of Car specified in the compliance business server CarBS):

```csharp
/// <summary> 
/// CarBS. 
/// </summary> 
// *** Start programmer edit section *** (CarBS CustomAttributes) 

// *** End programmer edit section *** (CarBS CustomAttributes) 
[ICSSoft.STORMNET.AccessType(ICSSoft.STORMNET.AccessType.none)]
public class CarBS : ICSSoft.STORMNET.Business.BusinessServer
{
        
    // *** Start programmer edit section *** (CarBS CustomMembers) 

    // *** End programmer edit section *** (CarBS CustomMembers) 

    // *** Start programmer edit section *** (OnUpdateCar CustomAttributes) 

    // *** End programmer edit section *** (OnUpdateCar CustomAttributes) 
    public virtual ICSSoft.STORMNET.DataObject[] OnUpdateCar(NewPlatform.Flexberry.ORM.ODataService.Tests.Car UpdatedObject)
    {
        // *** Start programmer edit section *** (OnUpdateCar) 
        var dObjs = new List<DataObject>();

        if (UpdatedObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Created)
        {
            UpdatedObject.Driver.CarCount++;

            UpdatedObject.Number =
                string.Format("{0}/{1}",
                    "TECT",
                    UpdatedObject.Driver.CarCount);

            dObjs.Add(UpdatedObject.Driver);
        }

        // To get into a single transaction objects should be returned. 
        return dObjs.ToArray();
        // *** End programmer edit section *** (OnUpdateCar) 
    }
}
```

## Design guidelines business server

The business class server inherits from `ICSSoft.STORMNET.Business.BusinessServer`, which contains properties:

* `DataService` data service, which will work this business the server.
* `ObjectsToUpdate` - "adjacent" updating objects this transaction.
* `Order` - Streamlining business servers. 0 - will be executed before the others, int.MaxValue will be executed last. Default: 0.

For example, the operation updates the data in the following way:

```csharp
DataObject[] objectsCars = new DataObject[] { car1, car2 };
ds.UpdateObjects(ref objectsCars);
```

Shown in example `OnUpdateCar` method will be invoked sequentially for objects `car1` and `car2`. Through property `ObjectsToUpdate` you can access the array `objectsCars` passed to the method `UpdateObjects` data service. This can be useful if the business logic server depends on "nearby" objects.

The business server in the method `OnUpdateCar` available instance of the service data `ds` through property `DataService`. If necessary, access a data service from code-business server, you should use this instance of the service data, since applications can run on multiple data services and different stores. Obtaining service data from a provider may result in errors of an applied nature. Also note that at the time to call the business server data service is not executing SQL queries, but only prepares data for it. Thus, referring to the storage method from the business servers, we have seen the store able to change. Especially carefully it is necessary to use the refresh operations because transakcija not yet created and change the storage to the business server may cause inconsistentname condition, if the generated SQL query after the exhaust business servers for some reason will fail. That changed in the business server objects have fallen into a common transaction, they need to return in the method business server.

It is important to understand that the object `UpdatedObject` will have exactly the state that was transferred to `ds.UpdateObjects` plus changes in the "neighboring" business servers. It is good practice to check the utilization of the properties before you change them.

## Features of use

Additional information about the features of using a business server is available in the following articles:

* [Call order business servers](fo_order-calls-bs.html)
* [Testing custom operations in the data service (integration with the business server)](fo_user-operations-dataservice.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}