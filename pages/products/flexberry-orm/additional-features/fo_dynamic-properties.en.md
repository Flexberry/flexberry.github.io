--- 
title: Dynamic object properties 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, example, property 
summary: Features, algorithms creating and using dynamic properties 
toc: true 
permalink: en/fo_dynamic-properties.html 
lang: en 
autotranslated: true 
hash: 77bb2654655aa2b6b0bd2798e0a4b72ee72725dca4ff4f59947ffe692c26cd8c 
--- 

`DynamicProperties` - NameObjectCollection instance of a class (class extension [NameObjectCollectionBase](http://msdn.microsoft.com/ru-ru/library/system.collections.specialized.nameobjectcollectionbase.aspx))that is, in fact, a dictionary that stores pairs of values a string object. 

Dynamic properties allow you to store additional information about the object and help in situations where the logic of the use of the object differs from the circumstances (call with different shapes, different users, etc.). 

Dynamic properties __do not remain__ in base. 

{% include important.html content="a Private instance `DynamicProperties` is contained in __every__ object of a class that inherits from [DataObject](fo_data-object.html)." %} 

## the Use of dynamic properties 

To access dynamic properties of an object, use the following: 

```csharp
dataObject.DynamicProperties
``` 

`Добавление свойства` 

```csharp
var someObject = new object();
dataObject.DynamicProperties.Add("propertyName", someObject);
``` 

`Проверка on the availability of properties at объекта` 

```csharp
if (dataObject.DynamicProperties.ContainsKey("propertyName"))
{
    ...
}
``` 

`Удаление properties объекта` 

```csharp
dataObject.DynamicProperties.Remove("propertyName");
``` 

## the Basic scenario using dynamic object properties 

The main usage scenario for the application projects the following: 

* In the dynamic properties of the object is added to a flag. 
* In [business server](fo_bs-wrapper.html) update object, check for this flag and is running or not running a set of certain actions. 

### Example 

![](/images/pages/products/flexberry-orm/additional-features/templates.png) 

Object `СтрокаПланаПогашения`. For example, you must enter a payment template, which contains information about `КредитнойКарте` and `Кредите`, but blank `ДатыОплаты` and `Суммы`. However, in the business server update the object already is check responsible for filling these fields. Hence, the object will not be updated and saved back into the database. 

On the form create a template of payment before sending the object to save, you need to add a dynamic property 

```csharp
dataObject.DynamicProperties.Add("Template", true); @@
``` 

And the business server to modify the test as follows: 

```csharp
if (!UpdatedObject.DynamicProperties.ContainsKey("Template"))
{
    // Check for mandatory filling of fields, the Amount and Dataplate 
}
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/