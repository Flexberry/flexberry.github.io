--- 
title: Dochitcu data object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, dochitcu data, copies of data 
summary: describes how the data decide 
toc: true 
permalink: en/fo_additional-loading.html 
lang: en 
autotranslated: true 
hash: 058a84d171b824c74313d4e6072e5626e5fcd7c4f86ef586f816003ed2f33792 
--- 

Sometimes you want to read previously loaded properties [data object](fo_data-object.html). 

Let the object read [view](fd_view-definition.html) "Nettoe". It is possible that for the implementation of the business logic, for example, to perform checks when you save the object, you need an additional subset of the properties of the object, i.e. the object should be downloaded on submission of the "Ectoplasmosis". Accordingly, it is required to Read the object. 

There are several ways decide data. The perfect way, suitable in all cases does not exist. For the right decide data should read the manual completely and select the most appropriate method of loading data. 

{% include note.html content="pogruzheny If the object is just created and not yet persisted to the database ([status](fo_object-status.html) `Created`), then the notion `догрузка` not applicable, as it never loaded and does not exist in the database." %} 

## Features decide data 

* Depending on the needs of the tasks must be chosen way to work with [a copy of the data object](fo_data-object-copy.html). If, after decide will be storing an object in the database, the values in [copy](fo_data-object-copy.html) must be installed so as to ensure that all the required properties. 
* If the data object before gecitkoy were the properties with the changed values for decide have to select [view](fd_view-definition.html) thus, in order not to lose these new values. 

## Approaches to decide data 

There are two approaches to implementing decide: 

* Logic decide fully implemented application programmer through the use of method overloading `LoadObject`. 
In this case the whole sequence is, by definition, pogruzheny properties and the way to work with [copy of data](fo_data-object-copy.html) chosen by the programmer. This allows you to more finely consider the features of the further use of the object in the application situation. 
* Reload is done by using the method `SecondLoadObject` class `SQLDataService`.This method has `зашита` the standard, most commonly required logic with the copy of the data Flexberry Platform relieves the programmer from having its own implementation. 

### Implementation decide code data 

When you want to fetch data, not to deduct the new facility at the specified performance, use the [overload] method `LoadObject`(fo_data-service.html) with four parameters. A third of them `ClearDataObject` - set in `False`.

``` csharp
/// <summary> 
/// Load one object of data 
/// </summary> 
/// <param name="dataObjectView">view</param> 
/// <param name="dobject">bject data you want to load</param> 
/// <param name="ClearDataObject">clear whether the object</param> 
/// <param name="CheckExistingObject">check whether the object exists in the repository</param> 
virtual public void LoadObject(
            ICSSoft.STORMNET.View dataObjectView,
            ICSSoft.STORMNET.DataObject dobj, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache)
``` 

If you specify `CheckExistingObject = true`, in the absence of the object in the database an exception will be thrown of type `CantFindDataObjectException`. 

{% include important.html content="When decide is re-initialization of the copy of the data object. This means that changes made to the object from the time of loading object data until decide data object will not be saved, because when you save there is a comparison of field values to fields in the [data copy](fo_data-object-copy.html)."%} 

Thus it is necessary to temporarily disable the re-initialization of the copy of the data: 

``` csharp
dobj.DisableInitDataCopy(); // disallow copy initialization data 
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(view, dobj, false, false); // dochitcu data object 
dobj.EnableInitDataCopy();// return the initialized copy of the data 
dobj.GetStatus();//To the object status was calculated by modified properties relative to copies of the data. 
``` 

It is important to understand that after decidi [status data](fo_object-status.html) will be installed in `UnAltered`, therefore, if you plan further object is updated in the database, don't forget to call `dobj.GetStatus()` that will lead to the recomputation of the status on the changed properties relative to [data copy](fo_data-object-copy.html). 

This option applies only to the case when pogruzheny object is read-only and will not continue to be saved to the database. The above code will cause the object fields specified in [view](fd_view-definition.html) decide, filled, and in the copy of the data object will remain unchanged. This, in turn, will cause the loaded fields will be deemed modified (so as to diverge with a copy of the data), and will be updated in the database. So if you finish the data object will continue to be saved to the database, it is necessary to harmonize the [copy data](fo_data-object-copy.html): 

``` csharp
dobj.DisableInitDataCopy(); // disallow copy initialization data 
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(view, dobj, false, false); // dochitcu data object 

// manually updated the internal copy of the data 
ТипОбъекта внутренКопия = dobj.GetDataCopy();
внутренКопия.Поле1 = dobj.Поле1;
внутренКопия.Поле2 = dobj.Поле2;
//... 
	
внутренКопия.ПолеN = dobj.ПолеN;

dobj.EnableInitDataCopy();// return the initialized copy of the data 
``` 

Alternatively, the reload data is create a separate instance of the same type with the same [primary key](fo_primary-keys-objects.html) and proofreading for the necessary [view](fd_view-definition.html). [View](fd_view-definition.html) may be full or contain only those attributes that will be used in the future: 

``` csharp
	// Manually updated the internal copy of the data 
	ТипОбъекта dobj_forLoading = new ТипОбъекта();
	dobj_forLoading.SetExistingPrimaryKey(dobj.__PrimaryKey);
	// view is required view. Its composition depends on the task. 
	ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(view, dobj_forLoading);
``` 

{% include note.html content="If you call a method `LoadObject` not explicitly specify a [view](fd_view-definition.html), will be deducted all private object fields and references to the wizard without their own field masters. Detaily object to be deducted **will**." %} 

To read the object thus, it is necessary to use a method overload without a parameter `LoadObject` type `View`. For example, suitable for the simplest overload: 

```csharp
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(dobj_forLoading);
``` 

In this case, the object `dobj_forLoading` also do not persist in the database. It should be understood that if the contact object has changed properties (status `Altered`), in `dobj_forLoading` they will not get, as yet not saved to the database. In practice, the logic should not depend on this factor, as gecitkoy dynamically determines the composition of the properties that you want to reload, and the altered properties (with proper verification) shall not be taken into account: 

```csharp
View viewДочитки = new View();
viewДочитки.DefineClassType = this.GetType();
if (!dobj.CheckLoadedProperty("Episode"))
    viewДочитки.AddProperty("Episode");
if (!dobj.CheckLoadedProperty("Episode.UD"))
    viewДочитки.AddProperty("Episode.UD");
if (!dobj.CheckLoadedProperty("Episode.UD.RUUD"))
    viewДочитки.AddProperty("Episode.UD.RUUD");
if (!dobj.CheckLoadedProperty("Episode.UD.RUUD.The name"))
    viewДочитки.AddProperty("Episode.UD.RUUD.The name");
if (!dobj.CheckLoadedProperty("Episode.UD.Getdocument"))
    viewДочитки.AddProperty("Episode.UD.Getdocument");
if (!dobj.CheckLoadedProperty("Episode.UD.Namerud"))
    viewДочитки.AddProperty("Episode.UD.Namerud");
if (!dobj.CheckLoadedProperty("Episode.Naberejnaya"))
    viewДочитки.AddProperty("Episode.Naberejnaya");
if (!dobj.CheckLoadedProperty("Databaseplugin"))
    viewДочитки.AddProperty("Databaseplugin");
if (viewДочитки.Properties.Length > 0)
{
    // dochitcu object 
}
``` 

### Implementation decide means Flexberry ORM 

Before you use this method of loading should be familiar with how updated the property values with the updated object and its copies, and take this into account when preparing the object for decide and its further use. 
The method has the following parameters: 

``` csharp
 /// <summary> 
/// Method for decide data object. Previously loaded properties are not overwritten, the modified properties are not overwritten. Replaced the piece the properties of the clone data. 
/// </summary> 
/// <param name="dataObjectView">view</param> 
/// <param name="dataObject">bject data you want to load</param> 
/// <param name="checkExistingObject">check whether the object exists in the repository</param> 
/// <param name="dataObjectCache"></param> 
protected virtual void SecondLoadObject(
View dataObjectView,
DataObject dataObject, bool checkExistingObject, DataObjectCache dataObjectCache)
``` 

### of Dochitcu own properties 

* All private properties are updated only if not downloaded previously. 

#### copy of the data 

* If the property has not been loaded but has been changed, updates the value of this property in the copy of the data (i.e. the value in the copy is synchronized with the database). 
* If the property was previously subtracted, the value in its copy of the data does not change. 
* If the copy data has not been initialized, it is initialized this time regarding the database for the new view. This means that **loaded-properties that are inherited from the previous zachistki will remain with incorrect values in the data copy, logic, by definition, the changed properties will not work, therefore all properties specified in the view will be updated, except those that are loaded in the array** 
* If the object is disabled, the initialization data copy method `DisableInitDataCopy()`, the copy data after decide will not be initialized. In essence, this means that it is impossible to understand what properties have changed and which are not. LoadedProperties array will contain an array of loaded properties. 

#### of Dochitcu masters 

* If the master was not deducted earlier, he will be credited with all his masters 
* If the wizard already has been proofread, it will be updated its properties as a General principle 
* **If the master was read previously but was replaced with another one, it will read the new object** 
* When decide master is put in LoadedProperties even if not explicitly specified in the representation of ancestor and in the database it is not (changed [standard logic](fo_definition-loaded-properties.html)). The master will not be listed in LoadedProperties only if LoadingState object: `LoadingState.NotLoaded` 

##### copy of the data 

* According to the standard logic of the workman a copy of the data object is initialized with only the primary key. 

#### of Dochitcu of datalow 

* If detail did not participate in loadedProperties, it is credited to a separate array and assigns it to 
* If detail was loaded, it is done element by element fusion. Objects that were not found in the collection, but was loaded from DB will be added


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}