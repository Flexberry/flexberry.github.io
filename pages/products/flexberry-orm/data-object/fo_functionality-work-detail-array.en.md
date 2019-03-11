--- 
title: Functionality when working with arrays of objects metalowych 
sidebar: flexberry-orm_sidebar 
keywords: DataObject ORM Flexberry, detail 
summary: Features working with an array of datalow 
toc: true 
permalink: en/fo_functionality-work-detail-array.html 
lang: en 
autotranslated: true 
hash: e3a8d0ac13344ef9d2c27c1f72341ca7ea21453afd3b658f2f0a217f3c123c76 
--- 

## basic functionality for working with arrays of objects metalowych 

`DetailArray` supports basic functionality for working with arrays [metalowych](fo_detail-associations-properties.html) close 

* Support links to [hat (aggregator)](fd_key-concepts.html). 
* Support for collections: 
* add (AddObject, AddRange), 
* insert (Insert), 
* delete (Remove RemoveByIndex, RemoveByKey), 
* clear (Clear), 
* install/capture values (GetByKey, SetByKey), 
* move (Move) 
* the number of objects (Count), 
* iterates through the objects in the foreach construct. 
* Support the ordering (the order in which lie [data objects](fo_data-object.html)). 

### Aggregate functions 

Among the objects in the array [datalow](fo_detail-associations-properties.html), calculate some aggregate function (sum, max, min, mean, anything random), it is necessary in the class inherited from `DetailArray`, to define a function without parameters that returns a value. Further to this function, you must attribute the attribute `AggregationFunction`. Parameters are: name of the property for which this aggregation function, and a format string (`String.Format`), according to which the value is displayed to the user. 

{% include important.html content="Aggregate functions have processed visual components, editing the array of detailov. Attribute `AggregationFunction` taken to process the following: if you specify a property name, the value of the aggregation function is indicated directly next to the values of data objects (e.g., bottom of the table in the corresponding column) and type a function's return value must be the same or implicitly castable to the property type. If you specify a format string then the value is displayed separately in this format. 
"%} 

### Ordering 

The objects in the array of datalow can be ordered (it is ordered but not sorted), i.e., be placed in any strict order. 

In order to introduce a regularization, it is necessary to [metalowy class](fo_detail-associations-properties.html) of data to enter an integer (`System.Int32`) property with assigned attribute `Order`. 

{% include important.html content="Order processed visual components, editing the array of detailov." %} 

{% include note.html content="Without Order of the attributes detaili can return in any manner other than that in which they were saved."%} 

### get type DetailArray its name 

To get the type of detail, having the type of aggregator and the name DetailArray, it is necessary to use the method of [Information](fo_methods-class-information.html).GetItemType. 

Method signature: 

```csharp
public static System.Type ICSSoft.STORMNET.Information.GetItemType (
        System.Type AgregatorType,
        string DetailPropertyName ) 	
``` 

where `AgregatorType` is the class type of the aggregator, and `DetailPropertyName` is the name of the list of datalow whose type to get. 

The method returns an object of type `System.Type` containing a description of the type of detail. 

### Writing LINQ queries to the already made list DetailArrayOf... 

To write a LINQ query to detalam, you must first bring them to the required type (because DetailArrayOf... does not support the interface `IEnumerable`, directly to him LINQ methods (such as `Select`, `ToList`, etc.) will not apply). 

Suppose there is a class `Aggregator` having metalowy class `Det`, then the construction below will give `ошибку`, because it is necessary to specify the type of objects which will populate a new list: 

```csharp
Aggregator aggr = new Aggregator();
aggr.DetailArrayOfDet.ToList(); 
``` 

If you specify a type, then `ошибка` changes to `Невозможно lead type DetailArrayOfDet type IEnumerable&lt<Det>`: 

```csharp
Aggregator aggr = new Aggregator();
aggr.DetailArrayOfDet.ToList<Det>(); 
``` 

`Правильным способом` is: 

```csharp
Aggregator aggr = new Aggregator();
aggr.DetailArrayOfDet.Cast<Det>.ToList(); 
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}