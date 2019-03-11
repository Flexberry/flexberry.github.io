--- 
title: Service data 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, uploading data objects, updating data objects 
summary: features of data entities using the data service 
toc: true 
permalink: en/fo_data-service.html 
lang: en 
autotranslated: true 
hash: 5731622ef220414b9641261e9e27bec759be6c0590c105219e0e0036c1c17b5e 
--- 

**Service data** - Flexberry ORM component that provides read and write data objects in the repository. In the object model Flexberry ORM data service is represented by a class implementing the interface `ICSSoft.STORMNET.Business.IDataService`. 
Supported multiple [standard data](fo_standard-data-services.html), and, if necessary, users can be [developed](fo_implement-custom-ds.html) new to meet your specific requirements (for example, inheriting classes `ICSSoft.STORMNET.Business.ODBCDataService.ODBCDataService` or `ICSSoft.STORMNET.Business.`[`SQLDataService`](fo_sql-data-service.html)). 

{% include important.html content="When you access the service data in the code is recommended through the interface `ICSSoft.STORMNET.Business.IDataService`, because it guarantees the independence of the code from the type of data store." %} 

Before using the service data in the program code it is necessary [to set one way or another](fo_construction-ds.html). 

Should __pay attention__ on the following points: 

* You can configure a data service so that on case-sensitive data source, the application behaved as you would with [case-insensitive source](fo_insensitivity-register-ds.html). 
* Data service [in a particular way works with Boolean type](fo_interpretation-boolean-null.html). 

## Main features of the service data 

Before going to code the service data it is necessary to [link](fo_ds-provider.html). When using multiple vaults it is necessary to consider some of the [features](fo_multibase.html). 

Interface `ICSSoft.STORMNET.Business.IDataService` contains the following basic groups of methods: 

* __Methods with names like__ `UpdateXXXXXXXX` designed to bring the data store (update, delete, create record](fo_object-status.html)) in accordance with the transmitted one or more data objects: 
* `UpdateObject` - updating a single object. 
* `UpdateObjects` - update multiple objects. 
* __Methods with names like__ `LoadXXXXXXXX` designed for reading one or more data objects. 
* `LoadObject` - loading of the object (depending on settings using this method it is possible to carry out [dochitcu object](fo_additional-loading.html)). 
* `LoadObjects` - loading facilities (including method overloads allow you to implement [a portion Stena](fo_reading-portion.html), [read belonging to different object classes in a single view](fo_reading-several-types-objects.html)). 
* __Upload without creating objects__ `LoadStringedObjectView`. 
* __Method of obtaining numbers of objects__, satisfying the condition `GetObjectsCount`. 

Most of the methods read/write have overloads that accept an extra parameter [DataObjectCache](fo_context-sensitive-cache.html), allowing to maintain a single instance for multiple instances of objects. Pass `DataObjectCache` methods zachetki in that case, if some object already exists in memory and after zachistki all references to it should point exactly at the object. In methods of updating the parameter is passed to the correct placement of references to instances of data from the cache due to the fact that after saving it to the vault updates the properties of a data object and initialize its copy of the data. 

Read more about the features of using cache, see [Context-sensitive cache data objects](fo_context-sensitive-cache.html). 

Below, when considering methods, the overload parameter `DataObjectCache` not separately described. 

## Loading of data objects 

To load multiple data objects are overloads of the `LoadObjects`. 

### Download data objects for view or array of views 

* Loads all the data objects available in the repository. Thus loading only those properties that are specified in [view](fd_view-definition.html). 

```csharp
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View dataObjectView);
``` 

* There is a consistent method call with parameter representation for each element of the array. The practical applicability of this overload is not obvious. 

```csharp
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View[] dataObjectViews);
``` 

#### Settings 

`dataObjectView(s)` - view or array of views 

### Loading data objects using the configuration structure for the sample LoadingCustomizationStruct (array of structures) 

* This method overload allows you to fine-tune the selection of downloadable objects through the use of structure [LoadingCustomizationStruct](fo_loading-customization-struct.html): 

* specify the view (a vertical list: download only of certain properties), 
* the number of rows returned (for relational repositories limitation `<nowiki>TOP</nowiki>` in `SELECT`), 
* collation of results to return (`ORDER BY`), 
* restrictions on deducted data objects (`WHERE`) 
* etc. see description [LoadingCustomizationStruct](fo_loading-customization-struct.html). 

[Example usage](fo_load-limitation-example.html). 

Zamechanie: This overloading allows you to implement a [read belonging to different object classes in a single view](fo_reading-several-types-objects.html).

```csharp
// 1. 
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct);

// 2. 
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct, DataObjectCache DataObjectCache);
``` 

* There is a consistent method call with the parameter - `LoadingCustomizationStruct` for each element of the array. The practical applicability of this overload is not obvious. 

```csharp
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct[] customizationStructs);
``` 

#### Settings 

`customizationStruct(s)` - [adjusting structure for a sample](fo_loading-customization-struct.html) (array of structures) 

### Download objects using state of the editing (to implement batch reading) 

* Getting the first batch [batch reading](fo_reading-portion.html). In addition to portions of data objects, data services returns a read state. This condition is transmitted to the data service to retrieve the next portion in subsequent calls (see Loading a single data object). 

```csharp
// 1. 
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct,ref object State);

// 2. 
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct, ref object State, DataObjectCache DataObjectCache);
``` 

* Getting another batch [batch reading](fo_reading-portion.html). Should precede the call to that overload. 

```csharp
// 1. 
ICSSoft.STORMNET.DataObject[] LoadObjects(ref object State);

// 2. 
ICSSoft.STORMNET.DataObject[] LoadObjects(ref object State, DataObjectCache DataObjectCache);
``` 

#### Settings 

* `State` - a State of proof-reading(for the subsequent decide) 
* `customizationStruct` - [adjusting structure for a sample](fo_loading-customization-struct.html) 

Primechanie: the serving Size may be specified with the parameter `LoadingBufferSize` structure [LoadingCustomizationStruct](fo_loading-customization-struct.html). 

## Download one data object 

To load a single data object are method overloads `LoadObject`. 
Reading properties from the repository is given in the data object [primary key](fo_primary-keys-objects.html). 

Examples of usage: 

* [Processing one object](fo_processing-one-object.html), 
* [Example of load changes of the object](fo_load-alter-objects.html). 

### download of the data object primary key 

Fetch only the own properties of the object. In the absence of a data store object with the specified primary key, an exception will be thrown `CantFindDataObjectException`. Do not use this method overload to decide data object, applicable to overloading methods with optional parameters. 

```csharp
// 1. 
void LoadObject(ICSSoft.STORMNET.DataObject dobject)

// 2. 
void LoadObject(ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache)
``` 

#### Settings 

`dobject` - the data Object that you want to download 

### download of the object data representation 

Load only those properties that are specified in [view](fd_view-definition.html). 

```csharp
// 1. 
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject)

// 2. 
void LoadObject(void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject)

// 3. 
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache)

// 4. 
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache)
``` 

#### Settings 

`dataObjectView(dataObjectViewName)` - [view](fd_view-definition.html) (view name) 

### download of the data object specifying additional parameters 

This method overload can be used, in particular, to perform [reload object properties](fo_additional-loading.html).

```csharp
// 1. 
void LoadObject(ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject)

// 2. 
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject)

// 3. 
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject)

// 4. 
void LoadObject(ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache) 

// 5. 
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache)

// 6. 
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache)
``` 

#### Settings 

* `ClearDataObject` - clear whether the object 
* `CheckExistingObject` is to check whether the object exists in the repository (if you specify `true`, in the absence of the object in the database an exception will be thrown of type `CantFindDataObjectException`) 

## updating a single data object 

To update one data object are method overloads `UpdateObject`. 

Examples of usage: 

* [Processing one object](fo_processing-one-object.html), 
* [Example of load changes of the object](fo_load-alter-objects.html). 

If before saving requires the implementation of certain actions, they can be implemented in the business server [this way](fo_actions-saving-object.html). 

It should be borne in mind that the preservation of the object may cause [saving related objects](fo_update-related-objects.html). 

The update of the data object. 

```csharp
// 1. 
void UpdateObject(ICSSoft.STORMNET.DataObject dobject) 

// 2. 
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject) 

// 3. 
void UpdateObject(ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) 

// 4. 
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) 

// 5. 
void UpdateObject(ICSSoft.STORMNET.DataObject dobject, bool AlwaysThrowException) 

// 6. 
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, bool AlwaysThrowException) 

// 7. 
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache, bool AlwaysThrowException) 
``` 

#### Settings 

* `dobject` - the data object to be updated 
* `DataObjectCache` - object cache 
* `AlwaysThrowException` - If an error occurred in the database, not attempt any other queries, just to cock a mistake and roll back the transaction 

## Update multiple data objects 

To update multiple data objects are overloads of the `UpdateObjects`. 
Requests for all updated objects are executed in the same transaction. 

Updated data objects can be homogeneous and heterogeneous. In the method they are passed a parameter a one-dimensional array of type [DataObject](fo_data-object.html)[]. 
In General, the service data is able to build himself the order of the queries to update the data objects. But possible situation when the related objects are important, the order of the objects in the array, detailed information about the order of saving objects is presented in [Processing multiple objects](fo_processing-multiple-objects.html). 

It should be borne in mind that for each element of the array, maintaining it may cause [saving related objects](fo_update-related-objects.html). 

[Example usage](fo_instantiate-persist-objects.html). 

Update multiple data objects in the repository. 

```csharp
// 1. 
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects)

// 2. 
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, bool AlwaysThrowException)

// 3. 
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, DataObjectCache DataObjectCache)

// 4. 
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, DataObjectCache DataObjectCache, bool AlwaysThrowException)
``` 

#### Settings 

* `objects` - object to update 
* `DataObjectCache` - object cache 
* `AlwaysThrowException` - if an error occurred in the database, not attempt any other queries, just to cock a mistake and roll back the transaction 

## Loading without object creation 

You can download from the store without instantiating every object in this case is represented as a string of property values delimited. Used when you should not edit the objects. This method is much faster than creating objects when you load the method `LoadObjects`. To download in the form of the string representation is designed method overload `LoadStringedObjectView`. 

[Example usage](fo_load-limitation-example.html). 

### Loading without object creation 

__The result is__: an array of structures `ObjectStringDataView` 

```csharp
ObjectStringDataView[] LoadStringedObjectView(char separator, LoadingCustomizationStruct customizationStruct)
``` 

#### Settings 

* `separator` separator in strings 
* `customizationStruct` - nastroika structure of the sample [LoadingCustomizationStruct](fo_loading-customization-struct.html) 

__Note__: the order of object properties data in the result string with the delimiters specified by parameter `ColumnsOrder` structure `customizationStruct`. 

### Download without creating objects using state of the proofreading (to implement batch reading) 

1.`LoadStringedObjectView` 

* Getting the first batch [batch reading](fo_reading-portion.html). In addition to serving data, the data service returns a read state. This condition is transmitted to the data service to retrieve the next portion in subsequent calls. 

```csharp
ObjectStringDataView[] LoadStringedObjectView(char separator, LoadingCustomizationStruct customizationStruct, ref object State)
``` 

* Getting another batch [batch reading](fo_reading-portion.html). Must be preceded by a call to the previous overload. 

```csharp
ObjectStringDataView[] LoadStringedObjectView(ref object state)
``` 

2.`CompleteLoadStringedObjectView` 

The correct operation to complete a portion of reading at `LoadStringedObjectView`. 

```csharp
void CompleteLoadStringedObjectView(ref object state)
``` 

#### Settings 

* `State` - a State of proof-reading(for the subsequent decide) 
* `customizationStruct` - [adjusting structure for a sample](fo_loading-customization-struct.html) 

__Note__: the batch Size can be specified with the parameter `LoadingBufferSize` structure [LoadingCustomizationStruct](fo_loading-customization-struct.html). 

## a number of objects that satisfy the query 

[Example usage](fo_load-limitation-example.html). 

Returns the number of objects satisfying conditions of the sample,without performing a data load. 

`GetObjectsCount` 

```csharp
int GetObjectsCount(LoadingCustomizationStruct customizationStruct)
``` 

#### Settings 

`customizationStruct` - adjusting structure for a sample of [LoadingCustomizationStruct](fo_loading-customization-struct.html) 

## Use SQL when working with data service 

In some situations, the service data is not enough to solve specific problems, in such cases, there is a possibility [of a direct SQL query](fo_sql-query.html) and [the change is automatically built query](fo_intercept-formation-sql-query.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}