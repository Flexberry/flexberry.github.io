---
title: data Objects
sidebar: flexberry-orm_sidebar
keywords: DataObject Flexberry ORM methods
summary: Characteristics, methods, and properties of the data object
toc: true
permalink: en/fo_data-object.html
lang: en
autotranslated: true
hash: 1f5924578a85ba5da5dd5c9c0f47cfb40f2afcd2f85e82d49662279c74ead6b9
---

In Flexberry ORM **data objects** instances of classes are called heirs of the abstract class `ICSSoft.STORMNET.DataObject`. For data objects Flexberry generators generate code for class .Net CLR-compliant language (C#, VB, etc.).

## Data objects in the model

[Classes of data objects](fo_data-object-structure.html) are described in [class diagram](fd_class-diagram-constraction.html) in [Flexberry Designer](fd_flexberry-designer.html) and have the stereotype [implementation](fd_data-classes.html). In the description of the data object are specified [attributes](fo_attributes-class-data.html), context and metadata.

The attributes of the data object can be of two types standard (described in the diagram) and dynamic [dynamic properties of the data object](fo_dynamic-properties.html). If the attribute value is not supposed to be placed in the repository, it can be declared as [phranky](fo_not-stored-attributes.html). In the description of the attributes may be assigned to the [default values](fo_features-dafault-value.html).

Also Flexberry ORM supports several types of links: [inheritance](fd_inheritance.html), [artisans](fd_master-association.html) and [Decalogue](fo_detail-associations-properties.html) communication.

The data objects may be linked to additional information (meta-information), for example, [views](fd_view-definition.html) or [picture](fo_class-image.html).

Stereotype [implementation](fd_data-classes.html) is the default value, i.e. when developing the chart it can be omitted leaving a blank.

## Data objects at run time

The life cycle of a data object traditionally consists of three stages:

* Create.
* Treatment.
* Save.

## Create a data object

The data object can be created in the following ways:

* Create an instance of the class standard means of language
* Download from repository methods LoadObject/LoadObjects [data service](fo_data-service.html).
* [Prototyping](fo_data-object-prototype.html)
* [Copy](fo_copying-data-objects.html).
* [Deserialize a previously saved object](fo_aggregating-function.html).

## Processing of the data object

One of the main attributes of an object is [object key](fo_primary-keys-objects.html), for which property is used `PrimaryKey`. In most cases, the key is the global unique identifier (GUID), although if necessary, can be used other types.

The most common problems in the processing data objects:

* [Change in attribute values](fo_own-object-attributes.html).
* [Obtaining information about the object status](fo_processing-status-condition-load.html).
* [Obtaining information about the loaded properties](fo_definition-loaded-properties.html).
* [Obtaining object metadata](fo_methods-class-information.html).
* [Lock object for exclusive editing](fo_lock-service.html).
* [Verification of compatibility of object and representation](fo_test-object-for-viewing.html).
* [Receipt of presentation of the value of the data object](fo_get-presentation-value.html)
* [Dochitcu of data object](fo_additional-loading.html)

## Saving the data object in the repository

To save the data object uses the methods of [data service](fo_data-service.html) UpdateObject/UpdateObjects. When working with data objects important concepts are [the status of objects, the upload status](fo_object-status.html) and [copy data](fo_data-object-copy.html).

## The basic properties and methods ICSSoft.STORMNET.DataObject

### Work with a copy of the data

#### GetDataCopy

__Assign__: Get the internal copy of the data object

__Signature__:

```csharp
DataObject ICSSoft.STORMNET.DataObject.GetDataCopy()
```

#### InitDataCopy

__Assign__ to Initialize the copy of the data

__Settings__:

`DataObjectCache`

__Signature__:

```csharp
// 1 
void ICSSoft.STORMNET.DataObject.InitDataCopy()

// 2 
void ICSSoft.STORMNET.DataObject.InitDataCopy(DataObjectCache DataObjectCache)
```

#### SetDataCopy

__Assign__: Set the internal copy of the data object

__Settings__:

`value` - Set object as a copy of an existing

__Signature__:

```csharp
void ICSSoft.STORMNET.DataObject.SetDataCopy (DataObject value)
```

### Getting and setting the state of the object

#### ContainsAlteredProps

__Assign__: Set whether the values of object properties in comparison with internal copy of

__Signature__:

```csharp
bool ICSSoft.STORMNET.DataObject.ContainsAlteredProps()
```

#### GetAlteredPropertyNames

__Assign__: Returns a list of properties (attributes, supervisors, datalow), the values of which are changed in comparison with the internal copy of the object.

__Signature__:

```csharp
string[] ICSSoft.STORMNET.DataObject.GetAlteredPropertyNames()
```

#### CheckNotNullProperties

__Assign__: Return a list of blank fields (values can't be empty according to data model)

__Settings__:

* `detailSkip` - Skip if you check the deleted detaily. A dictionary with a list of the types of datalow and flags for them. If detail not in the dictionary or a value for it to False, then check detail not to be missed. The parameter can be null.
* `view` - the View on which properties are checked and the return of property title.
* `returnCaptions` - true if the necessity to return the header properties from the view, not the names of the properties, false otherwise.

__Signature__:

```csharp
// 1 
public string[] CheckNotNullProperties()

// 2 
string [] ICSSoft.STORMNET.DataObject.CheckNotNullProperties (Dictionary<Type, bool> detailSkip)

// 3 
public string[] CheckNotNullProperties(View view, bool returnCaptions)

// 4 
public string[] CheckNotNullProperties(View view, bool returnCaptions, Dictionary<Type, bool> detailSkip)
```

#### GetStatus

__Assign__: getting the value of object status

__Settings__:

`recountIfAutoaltered` - update value of the force (if a class with automatic calculation status)

__Signature__:

```csharp
// 1 
ObjectStatus ICSSoft.STORMNET.DataObject.GetStatus()

// 2 
ObjectStatus ICSSoft.STORMNET.DataObject.GetStatus(bool recountIfAutoaltered)
```

#### SetLoadingState

__Assign__: set the status of the download.

__Settings__:

`newState` - load Status, set by the object.

__Signature__:

```csharp
void ICSSoft.STORMNET.DataObject.SetLoadingState (LoadingState newState)
```

#### SetLoadedProperties

__Assign__: set the status of the download.

__Settings__:

`newState` - load Status, set by the object.

__Signature__:

```csharp
void ICSSoft.STORMNET.DataObject.SetLoadingState (LoadingState newState)
```

#### SetStatus

__Assign__: set status

__Settings__:

`newState` - Status, set by the object.

__Signature__:

```csharp
virtual void ICSSoft.STORMNET.DataObject.SetStatus (ObjectStatus newState)
```

### Copying and duplication of data objects

#### CopyTo

__Assign__ to Create copy of the data object. If the resulting copy of the object will be placed in storage, it is necessary to call the methods `InitDataCopy` or `ClearDataCopy`.

__Settings__:

* `toObject` = object this, which
* `CreateDataObjectsCopy` - true - to create copies of linked objects, false - limited to copying and pasting the link
* `PrimaryKeyCopy` - true - copy primary keys
* `UseParentCaching` - use a previously-installed caching

__Signature__:

```csharp
  virtual void ICSSoft.STORMNET.DataObject.CopyTo(DataObject toObject,  
  bool  CreateDataObjectsCopy,  
  bool  PrimaryKeyCopy,  
  bool  UseParentCaching  
 )
 ```

#### Prototyping

__Assign__ A: Prototypicality

__Settings__:

`withDetails` - with or without detaylari

__Signature__:

```csharp virtual void ICSSoft.STORMNET.DataObject.Prototyping()
virtual void ICSSoft.STORMNET.DataObject.Prototyping(bool withDetails)
```

#### SetExistObjectPrimaryKey

__Assign__: Set the primary key in the data object. The operation is performed Clear() object is assigned a primary key, SetLoadingState(LoadingState.LightLoaded); SetLoadedProperties("__PrimaryKey");

__Settings__:

`primaryKey` - primary key

__Signature__:

```csharp
void ICSSoft.STORMNET.DataObject.SetExistObjectPrimaryKey (object primaryKey)
```

### Lock object

#### LockObject

__Assign__ to Lock the object

__Settings__:

`key` key lock facility

__Signature__:

```csharp
void ICSSoft.STORMNET.DataObject.LockObject(object key)
```

#### UnLockObject

__Assign__: to Unlock the object

__Settings__:

`key` - the key lock feature.

__Signature__:

```csharp
void ICSSoft.STORMNET.DataObject.UnLockObject (object key)
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}