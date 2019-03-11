--- 
title: the Status and the download status of the data object 
sidebar: flexberry-orm_sidebar 
keywords: DataObject Flexberry ORM, status 
summary: Description and characteristics of generation and load status data objects 
toc: true 
permalink: en/fo_object-status.html 
lang: en 
autotranslated: true 
hash: a4fdaf1b3a35d264e37cfba1a69dec10b7c45c3a5282a08baa6e8f9f09302b46 
--- 

## the statuses of the data objects 

The statuses of the data objects can be: 

* `ObjectStatus.UnAltered` object data is not changed. 
* `ObjectStatus.Created` — a data object created. 
* `ObjectStatus.Altered` object data changed. 
* `ObjectStatus.Deleted` — data object deleted. 

The object status can be ascertained by the method of the data object `GetStatus` and set — `SetStatus`. Status allows you to determine exactly what changes were made to the data object. 

### AutoAltered 

Status `Altered` can be calculated automatically at the time you call `GetStatus`. This requires the class of the data object attribute to the attribute `AutoAltered`. If this attribute is not assigned, then set status `Altered` programmer must perform» «manually. You should carefully use this opportunity to remember that the installation `AutoAltered` slows down because *each time the status of the real test object properties data on the change*. Default `AutoAltered=True;` 

There is a possibility not to be recalculated after the status of the established `AutoAltered`, it is necessary to call the method `GetStatus(false)`. 

To check if the attribute `AutoAltered` by using [Information](fo_methods-class-information.html)`.AutoAlteredClass`. 

### Test to modify the properties of a custom type 

If the data object contains the properties of a custom type (e.g., type of Money), then the default value comparison of an attribute of the object itself and [copy data](fo_data-object-copy.html) will be made through the implementation of this attribute to a string by `ToString()` and then comparing strings. Not for all types the approach is correct and works fast, so there is a `IComparableType` interface that contains method for comparison. If a custom type inherited from the type the comparison is not to be through strings, and calling a special method contained in the interface. It is recommended to use this interface, all user attribute types. 

## download Status of the data object 

The loading state of the data object: 

* `LoadingState.NotLoaded` object data is not loaded. 
* `LoadingState.Loaded` — proofread all native attributes of the data object, all the artisans of the first level, all dealove the first level. 
* `LoadingState.LightLoaded` object data is partially loaded, the details can be clarified by calling object methods [GetLoadedProperties and CheckLoadedProperty](fo_definition-loaded-properties.html). 

The download status can be ascertained through the method `GetLoadingState` and set — `SetLoadingState`. 

## Features of relationship status and download status 

Below are some features of the relationship between status and status of the download of data objects: 

* When a data object is created, it always `NotLoaded Created` (the same thing happens when you call the Clear method). If the object `NotLoaded`, it can only be `Created`. 
* If the object has a status `Loaded/LightLoaded`, it under no circumstances cannot have the status of `Created`. 
* Object does not happen `NotLoaded UnAltered`. 
* [In a special way processed status and the download status of the object when it is refreshed](fo_processing-status-condition-load.html). 
* If you need to move the object from state to state `Created` `Altered`, it is enough to call the method `SetLoadingState(LoadingState.LightLoaded)` thereby putting that object in the database is present. More correct way is to call `SetExistObjectPrimaryKey(object primaryKey)` is not only indicates that the object in the database is present, but immediately sets the right [key](fo_primary-keys-objects.html) of the object in the database. 
* Method to set the status of the data object DataObject.SetStatus() has special logic that is triggered when the switching status. 

| **Set The \ Old** | UnAltered | Created | Altered | Deleted| 
|:---------------|:---------------|:---------------|:---------------|:--------------- 
| UnAltered | UnAltered | **Created** | UnAltered | **if are not loaded are Created, UnAltered otherwise**| 
| Created | Created ** Are Not Loaded Are** | Created ** Are Not Loaded Are** | Created ** Are Not Loaded Are** | Created ** Are Not Loaded Are**| 
| Altered| Altered | **Created** | Altered | **Created if are not loaded are otherwise Altered**| 
| Deleted | Deleted | Deleted | Deleted | Deleted|


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}