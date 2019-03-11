--- 
title: Prototypical 
sidebar: flexberry-orm_sidebar 
keywords: DataObject Flexberry ORM methods 
summary: Features create data object based on an existing 
toc: true 
permalink: en/fo_data-object-prototype.html 
lang: en 
autotranslated: true 
hash: 171c606b21a40589f787377dca328496c53e94b8d4865521748a4ecb96eff925 
--- 

`Прототипизация` - create [data object](fo_data-object.html) on the basis of another. 

## Methods for prototypical DataObject 

For prototypical [DataObject](fo_data-object.html) there is a method `Prototyping`. 

```csharp
/// <summary> 
/// Prototypicality 
/// </summary> 
/// <param name="withDetails">detalaj or without</param> 
public virtual void Prototyping(bool withDetails)
``` 

There is also an overload of this method without any parameters (in this case just made the call to the method `Prototyping(true)`). 

## Prototypical DataObject 

If prototypization the following actions occur: 

* resets the [primary key object](fo_primary-keys-objects.html) (new generated); 
* [status](fo_object-status.html) is changed to `ObjectStatus.Created`; 
* [download status object](fo_object-status.html) is set to `LoadingState.NotLoaded`; 
* [a method is called InitDataCopy](fo_data-object-copy.html). 

If the passed parameter has a value `withDetails` `true`, prototypical will be performed for all detailov. 

## notes proletarization 

* Get a [primary key object](fo_primary-keys-objects.html), which he had before prototypically, through property `PrototypeKey`. 
* Cleaning properties `PrototypeKey` happens when you call a method `ClearPrototyping` (if the call was made with no parameters or the parameter value was `true`, the corresponding property will be cleared and datalow). 
* Call method `ClearPrototyping(true)` also happens when saving an object via the [SQLDataService](fo_sql-data-service.html). 
* To know whether prototipazione through the property `Prototyped`. 
* When you perform [decide object](fo_additional-loading.html) the data service will carry out the proofreading properties prototipazione object, using as [primary key](fo_primary-keys-objects.html) `PrototypeKey` (but when you run [UpdateObject](fo_data-service.html) in the database will create a new object).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}