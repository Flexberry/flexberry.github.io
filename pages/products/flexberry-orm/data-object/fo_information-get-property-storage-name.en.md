--- 
title: Information.GetPropertyStorageName 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, database 
summary: Peculiarities of using the method GetPropertyStorageName class Information 
toc: true 
permalink: en/fo_information-get-property-storage-name.html 
lang: en 
autotranslated: true 
hash: a74926eb59497f3948d3aa6afa5ced2888fa9f4fdd2aaeb30f77447a40467b52 
--- 

PstrfGetPropertyStorageName` static method class [Information](fo_methods-class-information.html) allows you to [name of storage .Net-properties set by the attribute `PropertyStorageAttribute`](fo_storing-data-objects.html). 

### Method without using the index 

The method does not require specifying an index when you call, have the following form: 

``` csharp
/// <param name="type">.Net class type of the data object</param> 
/// <param name="property">the property name</param> 
/// <returns>the name of the storage</returns> 
static public string GetPropertyStorageName(System.Type type, string property)
``` 

This method should be used if the property attribute is `PropertyStorageAttribute` is: 

``` csharp
[PropertyStorage("TimeQu"))
``` 

### Method using index 

Method that requires an index when you call, have the following form: 

``` csharp
/// <param name="type">.Net class type of the data object</param> 
/// <param name="property">the property name</param> 
/// <param name="index">the index to set</param> 
/// <returns>the name of the storage</returns> 
static public string GetPropertyStorageName(System.Type type, string property, int index)
``` 

This method should be used if the attribute Declaration properties `PropertyStorageAttribute` used an array of names: 

``` csharp
PropertyStorage(new string[]) {"Postclass"}))
``` 

In this case, the parameter `index` will determine the element number of the array names specified in the attribute Declaration properties `PropertyStorageAttribute`, which will be returned by the method. 

### Erroneous Information.GetPropertyStorageName 

* If the attribute `PropertyStorageAttribute` was declared without an array, but use the call `Information.GetPropertyStorageName` index, an exception will be thrown. 
* If the attribute `PropertyStorageAttribute` was declared with an array, but use the call `Information.GetPropertyStorageName` without an index, then an empty string is returned. 

## Features of generation attribute properties PropertyStorage 

* Attribute `Storage` specified for the [class properties](fo_attributes-class-data.html), after generation will be presented in the form of attribute declarations `PropertyStorage` without the array. 
* Attributes `Aggregator Storage` [dyelovoi links](fo_detail-associations-properties.html) and `Storage` [artisan links](fd_master-association.html) after generation will be presented in the form of attribute declarations `PropertyStorage` without the array, if the following conditions are met: 
* Storage-attribute contains only one nonempty value. 
* Attribute [a typeusage](fo_type-usage-problem.html) contains more than one value. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}