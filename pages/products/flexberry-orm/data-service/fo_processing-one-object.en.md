--- 
title: Processing of a single object 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: Features update a data object using the data service 
toc: true 
permalink: en/fo_processing-one-object.html 
lang: en 
autotranslated: true 
hash: e2dfdeb992e105fe0dc4153a1975b911b1af6833279959f49d727d5b79a19a62 
--- 

In order to update the data in the [repository for the data object](fo_storing-data-objects.html), you must complete the [service data](fo_data-service.html) `UpdateObject`. 

To read object data from storage [key](fo_primary-keys-objects.html) you must call the service `LoadObject` data (the data object will be read in a [view](fd_view-definition.html) declared as "*"). 

The following is an example of when you created the data object is created in the repository, and then read [key](fo_primary-keys-objects.html). 

```csharp
static void Main(string[) args)
{
	//Save the same object 
	Страна странакоторуюпишем = new Страна();
	странакоторуюпишем.Наименование="Russia";
	UpdateObject(странакоторуюпишем);
	Console.WriteLine("End of save");			
	//read one object 
	Страна странакоторуючитаем = new Страна();
	странакоторуючитаем.SetExistObjectPrimaryKey(странакоторуюпишем.__PrimaryKey);
	LoadObject(странакоторуючитаем);
	Console.WriteLine("The end of the reading, the country is {0}", странакоторуючитаем.Наименование);			
	Console.Read();
}
private static void UpdateObject(DataObject dparam)
{
	IDataService ds = DataServiceProvider.DataService;			
	ds.UpdateObject(ref dparam);
}
private static void LoadObject(DataObject dparam)
{
	IDataService ds = DataServiceProvider.DataService;			
	ds.LoadObject(dparam);
}
``` 

It is important to consider `.Net`-attribute [AutoAltered](fo_object-status.html)! If read a certain data object, modified its properties, and is an upgrade in the storage, if the data object is not `AutoAltered`, you should not expect from [data service](fo_data-service.html) updates data in the repository without "manual" setting this object status `Altered`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/