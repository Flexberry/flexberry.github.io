--- 
title: Copy of the data object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM copying 
summary: Features use the copy of data objects 
toc: true 
permalink: en/fo_data-object-copy.html 
lang: en 
autotranslated: true 
hash: 94e544065744c32f44f2241483ee87260f8c698e4945ae796708eceb2319e4dd 
--- 

[Service data](fo_data-service.html) builds a query to change rows in the database based on information about the changed properties of the object. To obtain this information at [the facility](fo_data-object.html) is a separate full copy of the object (in this case change detection is only required to compare the current field values of the object data and the corresponding values in its copies). 

A copy of the data object available through the method `dataobject.GetDataCopy()`. 

A copy of the data is populated from the values of the fields object in the following cases: 

* Review each object from the database (if you did not specify what to initialize the copy of the data is not necessary). 
* When you call a method `dataobject.InitDataCopy()` programmer. 

## Optimization 

The operation of creating copies of data is expensive in terms of performance, so it is recommended when editing objects that are not exactly updated in the database, disable the initialization of the copy data properties [LoadingCustomizationStruct](fo_loading-customization-struct.html): 

```csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Шапка), "Chapchae");
lcs.InitDataCopy = false;
``` 

Also in order to optimize not initialize the mechanics field. For masters only initialized [primary keys](fo_primary-keys-objects.html). If the workman the object has been checked with a sufficient number of fields and it is planned to update in the database, then immediately after zachistki for him, you must call the `InitDataCopy()`. 

## copy of the data object created 

If the object has [`Created` status](fo_object-status.html), then it does not have a copy of the data after the initialization copies the data (`InitDataCopy`), since the copy of the data object whose status `Created` is not necessary (while updating the object all goes into the database, and InitDataCopy skips such objects).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}
