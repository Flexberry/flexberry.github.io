--- 
title: Getting metadata objects 
sidebar: flexberry-orm_sidebar 
keywords: DataObject Flexberry ORM methods 
summary: Methods of class Information 
toc: true 
permalink: en/fo_methods-class-information.html 
lang: en 
autotranslated: true 
hash: 9458229a679278234981cb2a7f192b93f3087c266f3ee9edc025cbe79cc36db5 
--- 

Any metadata can be obtained through the class `Information` with a set of static methods. 

Below are the most frequently used class methods `Information` grouped by use. 

## Work with object properties (determination of value, utilization, etc., value) 

### GetPropValueByName 

__Assign__: Get the value of a property of the data object for the property name 

__Settings__: 

* `obj` - data Object, the property value for which you want to 
* `propName` - the property Name of a data object whose value you want to 

__Signature__: 

```csharp
static public object GetPropValueByName(DataObject obj, string propName) 
``` 

### SetPropValueByName 

__Assign__: to Set the value property of a data object for the property name, value is string. When setting a property you are trying to convert a string value to a value of the appropriate type by calling the static method Parse(string) from the type. 

__Settings__: 

* `obj` - data Object, the property value of which is determined by this method 
* `propName` - the property Name of a data object whose value is set by this method 
* `PropValue ` the value of the property of the data object that will be set by this method 

__Signature__: 

```csharp
static public void SetPropValueByName(DataObject obj, string propName, string PropValue) 
``` 

### SetPropValueByName 

__Assign__: to Set the value property of a data object for the property name, the value is transferred timisoreana. If the type conversion fails, returns an error message. 

__Signature__: 

```csharp
static public void SetPropValueByName(DataObject obj, string propName, object PropValue) 
``` 

### TrimmedStringStorage 

__Assign__: to Trim or string for this property. 

__Settings__: 

* `tp` - type data object 
* `propName` - the property Name of the data object 

__Signature__: 

```csharp
static public bool TrimmedStringStorage(System.Type tp, string propname) 
``` 

### IsStoredProperty 

__Assign__: if Stored property 

__Settings__: 

* `type` - .Net class type of the data object 
* `propName` - the property name 

__Signature__: 

```csharp
static public bool IsStoredProperty(Type type, string propName) 
``` 

### GetPropertyNotNull 

__Assign__: Check if for the specified .Net-properties attribute `NotNullAttribute` 

__Settings__: 

* `type` - .Net class type of the data object 
* `property` - the property name 

__The result is__: true if installed, false otherwise 

__Signature__: 

```csharp
static public bool GetPropertyNotNull(System.Type type, string property) 
``` 

### GetPropertyStrLen 

__Assign__: Get the specified .Net-properties attribute `StrLenAttribute`. 

__Settings__: 

* `type` - .Net class type of the data object 
* `property` - the property name 

__The result is__: value of the set attribute (-1 if not set) 

__Signature__: 

```csharp
static public int GetPropertyStrLen(System.Type type, string property) 
``` 

### GetPropertyType 

__Assign__: To .Net-type properties of the class of the data object for the property name 

__Settings__: 

* `declarationType` - .Net class type of the data object 
* `propname` - the property name 

__Signature__: 

```csharp
static public Type GetPropertyType(System.Type declarationType, string propname) 
``` 

### CheckPropertyExist 

__Assign__: to Check whether such a property in the specified type 

__Settings__: 

* `type` - .Net class type of the data object 
* `propName` - the property Name 

__The result is__: 

* `true` - the property is, 
* `false` - no 

__Signature__: 

```csharp
static public bool CheckPropertyExist(System.Type type, string propName)
``` 

### ExtractPropertyName 

__Appointment___: Retrieval of properties within the current class 

__Settings__: 

* `propertyExpression` - Lambda expression for property access 
* `TSource` - Type class source 

__The result is__: the name of the property (single!) 

__Signature__: 

``` csharp
public static string ExtractPropertyName<TSource>(Expression<Func<TSource>> propertyExpression)
``` 

Cm. [Access to own object attributes and attributes of related objects](fo_own-object-attributes.html). 

### ExtractPropertyName 

__Assign__: Explicit extraction of properties by type 

__Settings__: 

* `propertyExpression` - Lambda expression for property access 
* `TSource` - Type class source 

__The result is__: the name of the property (single!) 

__Signature__: 

``` csharp
public static string ExtractPropertyName<TSource>(Expression<Func<TSource, object>> propertyExpression)
``` 

### ExtractPropertyPath 

__Assign__: a Recursive method to get the path for the property specified via a nested lambda.A lambda expression can contain nested appeal to the masters. 

__Settings__: 

* `TProperty` - Type properties 
* `propertyExpression` - Lambda expression for property access 

__The result is__: the Full path to a property (separation point) 

__Signature__: 

``` csharp
public static string ExtractPropertyPath<TProperty>(Expression<Func<TProperty>> propertyExpression)
``` 

### ExtractPropertyPath 

__Assign__: a Recursive method to get the path for the property specified via a nested lambda.A lambda expression can contain nested appeal to the masters.

__Settings__: 

* `propertyExpression` - Lambda expression for property access 
* `TSource` - Type class source 

__The result is__: the Full path to a property (separation point) 

__Signature__: 

``` csharp
public static string ExtractPropertyPath<TSource>(Expression<Func<TSource, object>> propertyExpression)
``` 

### ExtractPropertyInfo 

__Assign__: Explicit extraction of properties by type 

__Settings__: 

* `propertyExpression` - Lambda expression for property access 
* `TSource` - Type class source 

__The result is__: `PropertyInfo` properties (the latest) 

__Signature__: 

``` csharp
public static PropertyInfo ExtractPropertyInfo<TSource>(Expression<Func<TSource, object>> propertyExpression) 
``` 

## Work with views 

### GetView 

__Assign__: to Get an idea on his name, and the class of the data object 

__Signature__: 

```csharp
static public View GetView(string ViewName, System.Type type) 
``` 

### GetCompatibleView 

__Assign__: to Get an idea, "compatible" with the given classes. Looking for a common ancestor, then trying to get the specified performance. If the view is not found, returns null. 

__Settings__: 

* `ViewName` - the name of the view 
* `types` - one-dimensional array types data classes 

__Signature__: 

```csharp
static public View GetCompatibleView(string ViewName, System.Type[] types) 
``` 

### AllViews 

__Assign__: Get a list of names of views for the specified class of data object 

__Settings__: 

`type` - class data object 

__Return value__: Array of strings containing the names of the views for the specified type 

__Signature__: 

```csharp
static public string[] AllViews(System.Type type) 
``` 

### AllViews 

__Assign__: Get a list of the names of the General ideas for these classes. We are talking about a situation when forming an inheritance hierarchy classes have representation, which means that there is a set of representations common to a plurality of classes. Pointing to this method is a lot of classes, You will receive the names of their General ideas. 

__Signature__: 

```csharp
static public string[] AllViews(params System.Type[] types) 
``` 

### CheckViewForClasses 

__Assign__: to Test whether the specified named view in all these classes. We are talking about a situation when forming an inheritance hierarchy classes have representation, which means that there is a set of representations common to a plurality of classes. 

__Signature__: 

```csharp
static public bool CheckViewForClasses(string ViewName, params System.Type[] types) 
``` 

### GetAllTypesFromView 

__Assign__: Return a list of all instances of view types, including detaily. 

__Settings__: 

`view` - View 

__Return value__: types List without duplicates 

__Signature__: 

```csharp
public static List<Type> GetAllTypesFromView(View view) 
``` 

### GetAllTypesFromView 

__Assign__: Return a list of all instances of view types, including detaily and pseudometal. 

__Settings__: 

`view` - Extended view (pseudocatalase). 

__Return value__: types List without duplicates 

__Signature__: 

```csharp
public static List<Type> GetAllTypesFromView(ExtendedView view) 
``` 

## Working with data objects 

### GetAlteredPropertyNames 

__Assign__: to Compare two data object and return a list of distinguished .Net-properties. (Object or property with the attribute `NotStored`проверяться will not) 

__Settings__: 

* `obj1` - 1-th data object 
* `obj2` - 2-y data object 
* `WithDetailsComparing` with comparing dealova objects 

__Return value__: the one-dimensional string array of property names 

__Signature__: 

```csharp
static public string[] GetAlteredPropertyNames(DataObject obj1, DataObject obj2, bool WithDetailsComparing) 
``` 

### GetAlteredProperyNames 

__Assign__: Use the GetAlteredPropertyNames. Left for compatibility reasons(a mistake in a method name). 

### GetAlteredPropertyNamesWithNotStored 

__Assign__: to Compare two data object and return a list of distinguished .Net-properties. (NotStored attributes are not ignored and is also checked along with the rest) 

__Settings__: 

* `obj1` - 1-th data object 
* `obj2` - 2-y data object 
* `WithDetailsComparing` with comparing dealova objects 

__Return value__: the one-dimensional string array of property names 

__Signature__: 

```csharp
static public string[] GetAlteredPropertyNamesWithNotStored(DataObject obj1, DataObject obj2, bool WithDetailsComparing) 
``` 

### ContainsAlteredProps 

__Assign__: to Compare two data object and return true if the objects differ 

__Signature__: 

```csharp
 static public bool ContainsAlteredProps(DataObject obj1, DataObject obj2, bool WithDetailsComparing) 
``` 

### CheckNotNullAttributes 

__Assign__: Check for non-null values to NotNull .Net properties. 

__Settings__: 

`dataObject` - data object 

__Return value__: returns null if no non-empty values, otherwise the one-dimensional string array with property names where the value is 

__Signature__: 

```csharp
static public string[] CheckNotNullAttributes(DataObject dataObject) 
``` 

### GetAllPropertyNames 

__Assign__: Return all names .Net-properties for .Net class type of the data object 

__Settings__: 

`type` - .Net class type of the data object 

__Return value__: the one-dimensional string array of property names 

__Signature__: 

```csharp
static public string[] GetAllPropertyNames(System.Type type) 
``` 

## Information about the generator of primary keys 

### GetKeyGeneratorType 

__Assign__: Get the type of the key generator 

__Settings__: 

`typeofdataobject` - for what type 

__Signature__: 

```csharp
static public System.Type GetKeyGeneratorType(System.Type typeofdataobject) 
``` 

## the Names of storage objects 

### GetTypeStorageName 

__Assign__: the name of the store for the type 

__Signature__: 

``` csharp
 static public string GetTypeStorageName(System.Type type) 
``` 

### GetClassStorageName 

__Assign__: to Obtain the name of the store .Net class type of the data object specified by the attribute `ClassStorageAttribute`. 

__Settings__: 

`type` - .Net class type of the data object 

__Return value__: the name of the store in line 

__Signature__: 

```csharp
static public string GetClassStorageName(System.Type type) 
``` 

### GetPrimaryKeyStorageName 

__Assign__: the name to store the primary key specified by the attribute `PrimaryKeyStorageAttribute` 

__Settings__: 
`type` - .Net class type of the data object 

__Signature__: 

```csharp
static public string GetPrimaryKeyStorageName(System.Type type) 
``` 

### GetPropertyStorageName 

__Assign__: to Obtain the name of the store .Net-properties set by the attribute `PropertyStorageAttribute`.

__Signature__: 

```csharp
static public string GetPropertyStorageName(System.Type type, string property, int index) 
``` 

A more detailed description of this method can be found in [Information.GetPropertyStorageName](fo_information-get-property-storage-name.html). 

## the authentication object attributes 

### CheckAccessToAttribute 

__Assign__: Checking rights to object attributes. The method is a wrapper for the method CheckAccessToAttribute class `ICSSoft.STORMNET.RightManager` and used to verify the rights in Get'erach computable properties of the DataObject. Processing of the masters is not subject to. 

__Settings__: 

* `type` - .Net class type of the data object 
* `propertyName` - the property Name of the data object, which checks the rights. 
* `deniedAccessValue` - the attribute Value in the absence of rights. 

__Signature__: 

```csharp
public static bool CheckAccessToAttribute(Type type, string propertyName, out object deniedAccessValue) 
``` 

## Working with images 

### GetImageForInstance 

__Assign__: Return a picture object 

__Signature__: 

```csharp
public static System.Drawing.Image GetImageForInstance(DataObject dobject) 
``` 

### GetClassImage 

__Assign__: to Return the picture to the class, set the attribute `ClassImageFileAttribute`. 

__Settings__: 

`dataObjectType` - data Object in this class 

__Return value__: Image 

__Signature__: 

```csharp
public static System.Drawing.Image GetClassImage(System.Type dataObjectType) 
``` 

### GetClassImageProperty 

__Assign__: Return property-the image specified by the attribute `ClassImagePropertyAttribute` 

__Signature__: 

```csharp
public static string GetClassImageProperty(System.Type dataobjectType) 
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}