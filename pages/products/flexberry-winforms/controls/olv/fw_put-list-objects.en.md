--- 
title: Entire list of objects in ObjectListView 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, controls, list, data objects 
summary: Algorithm for creating the list, set the type and presentation, inclusion of data 
toc: true 
permalink: en/fw_put-list-objects.html 
lang: en 
autotranslated: true 
hash: 61b090ed8fc6b9f4e0d546ca475e0e03c4cbca5306fc2d80ad822cbac1b1e900 
--- 

[ObjectListView](fw_objectlistview.html) is not used to display data from DB, the standard way to display a list of objects created directly in code, no. 

However, there is an algorithm solving this problem. 

## Algorithm add data 

* Create a list of data to display. 
* To install `ObjectListView` the type and performance. 
* Set `LimitFunction` to base nothing podgruzilo. 
* Call method `SetObjects`. 

### to create a data list 

First, create a proper list of the data that must be downloaded into `ObjectListView`. Not necessarily that the data was in the database. 

For example, the variable `myObjects` to which to save the created objects list. 

### Setting the type and presentation 

For example, to display objects of type `Адрес` representation `АдресL`. 

In the code ostanavlivajutsja settings `ObjectListView`: 

```csharp
objectListView1.DataObjectTypes = new[] { typeof(Адрес) };
objectListView1.ViewName = "АдресL";
``` 

{% include important.html content="Despite the fact that the data does not have to be in the database, the representation of the class responsible for this data __should be__." %} 

### Lock uploading data from the database 

To get data from database is not loaded during the update (pressing the button `Refresh`), you need to install `LimitFunction` have `ObjectListView` so that the condition is never fulfilled. For example, in the following way: 

```csharp
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
objectListView1.LimitFunction = langdef.GetFunction(langdef.funcSQL, "1 = 2");
``` 

### Adding objects to the list 

To add objects to the list, simply call the method `SetObjects` have `ObjectListView`: 

```csharp
objectListView1.SetObjects(myObjects.ToArray());
``` 

However, if you call this method at the time of creation of the form, the data will be lost when you press the button `Refresh`. 

To data is persisted, you must call this method __after loading data from the database__. For this, you need to subscribe to an event `AfterFillData` and add in a handler method call `SetObjects`, for example: 

```csharp
objectListView1.AfterFillData += (o, s) =>
{
	objectListView1.SetObjects(myObjects.ToArray());
};
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/