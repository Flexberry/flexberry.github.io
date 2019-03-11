--- 
title: LINQProvider 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: 
toc: true 
permalink: en/fo_linq-provider.html 
lang: en 
autotranslated: true 
hash: c697f344999f1c7823c50c0e0b5070dcd5d155e03ffc1965f411d6776d8c9be1 
--- 

## Support Linq 

Class `LinqToLcs` namespace `ICSSoft.STORMNET.Business.LINQProvider` designed to support [LINQ](http://ru.wikipedia.org/wiki/LINQ) requests for data services [`SQLDataService`](fo_sql-data-service.html). 

Main features LINQProvider described in the article [Features LinqProvider](fo_linq-provider-faetures.html). 

## How to use LinqProvider 

To enable LINQ-provider requires a reference to System.Linq 

``` csharp
 using System.Linq;
``` 

**And project link to `ICSSoft.STORMNET.Business.MSSQLDataService.DLL`.** 

When you connect the namespace `ICSSoft.STORMNET.Business.LINQProvider` objects [SQLDataService](fo_sql-data-service.html) is added to Query method with the following prototypes: 

``` csharp
public static IQueryable<T> Query<T>(this SQLDataService ds, string viewName) where T : DataObject

public static IQueryable<T> Query<T>(this SQLDataService ds, View view, IEnumerable<View> resolvingViews = null) where T : DataObject

public static IQueryable<T> Query<T>(this SQLDataService ds) where T : DataObject
``` 

Parameters: 

| Name | Description | 
|:----|:----| 
| `ds` | [Service data, the heir SQLDataService](fo_sql-data-service.html), to query| 
| `view` | [View](fd_view-definition.html) used to download| 
| `resolvingViews` | [View](fd_view-definition.html) masters containing their detaily used in the query (if there are none, then `null`)| 

In the latter case overload [view](fd_view-definition.html) to download the object will be formed dynamically (it will get the properties used in the query). 

Thus, if the view is specified statically, it is preferable to use the second option overload, putting the view `Тип.Views.ИмяТипа`. 

The method returns `IQueryable` that you can pass the query by using the methods-extensions to LINQ: 
* [Where](http://msdn.microsoft.com/en-us/library/system.linq.queryable.where.aspx) 
* [First](http://msdn.microsoft.com/en-us/library/system.linq.queryable.first.aspx) 
* [Any](http://msdn.microsoft.com/en-us/library/system.linq.queryable.any.aspx) 
* [All](http://msdn.microsoft.com/en-us/library/bb534754.aspx) 
* [Count](http://msdn.microsoft.com/en-us/library/bb534754.aspx) 
* [FirstOrDefault](http://msdn.microsoft.com/ru-ru/library/system.linq.queryable.firstordefault.aspx) 

{% include note.html content="Proofreading occurs each time you call ToList or ToArray, so it is advisable to obtain a collection of data, and then to work with her." %} 

## Examples of usage 

### the first suitable object 

``` csharp
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.Business.LINQProvider;
//... 
var ds = (SQLDataService)DataServiceProvider.DataService; // The data service. 
Кошка cat = ds.Query<Кошка>(Кошка.Views.КошкаE).First(o => o.Кличка.Contains("Osh")); // Get the object. 
Console.WriteLine(cat.Кличка); //Use. 
``` 

### the first suitable object (with the generation `TOP 1` in the request body when using `FirstOrDefault` and `First`) 

``` csharp
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.Business.LINQProvider;
//... 
var ds = (SQLDataService)DataServiceProvider.DataService; // The data service. 
Кошка cat = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.Кличка.Contains("Osh")).Take(1).FirstOrDefault(); // Get the object. 
Console.WriteLine(cat.Кличка); //Use. 
``` 

### a collection of objects 

``` csharp
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.Business.LINQProvider;
//... 
var ds = (SQLDataService)DataServiceProvider.DataService; // The data service. 
IQueryable<Кошка> objs = ds.Query<Кошка>(Кошка.Views.КошкаE); 
IQueryable<Кошка> query = from o in objs where o.PrimaryKey == "6211E0DE-3E7A-4A68-866A-AB206A005B1C" select o; // Get the cats on a given key value. 
List<Кошка> data = query.ToList(); // Subtract data to the collection. 
Console.WriteLine(data[0).Кличка); // Use the received data. 
``` 

#### the Following code is equivalent to the previous 

``` csharp
var ds = (SQLDataService)DataServiceProvider.DataService; // The data service. 
IQueryable<Кошка> objs = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.PrimaryKey == "6211E0DE-3E7A-4A68-866A-AB206A005B1C"); // Get the cats on a given key value. 
List<Кошка> data = objs.ToList(); // Subtract data to the collection. 
Console.WriteLine(data[0).Кличка); // Use the received data. 
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}