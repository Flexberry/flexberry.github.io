--- 
title: IReferencesNullDelete 
sidebar: flexberry-orm_sidebar 
keywords: DataObject Flexberry ORM, business servers, a cascading delete of objects 
summary: Features cascade-delete objects with the use of Null 
toc: true 
permalink: en/fo_i-references-null-delete.html 
lang: en 
autotranslated: true 
hash: 4a15b08d4469fc84a3b31f0d08f33ea9e8e28aa23801f0cbc0eac399d3f45353 
--- 

PstrfIReferencesNullDelete` interface enables cascading deletion of objects (deleting all the referencing objects are stamped with a NULL instead of a reference to a deleted object). 

## How does IReferencesNullDelete 

Logic for zero reference is spelled out in [business server](fo_bs-wrapper.html) interface `IReferencesNullDelete`; own properties and methods this interface does not provide. Thus, together with some object to remove all references to it, enough that the object class was inherited from `IReferencesNullDelete`. 
The classes that refer to the desired, as follows: 

* Defined the directory where is the Assembly with the class, to which we are looking for. 
* In the directory are selected, all assemblies whose name ends in `(Objects).dll`. 
* In assemblies looking for types that inherit from [DataObject](fo_data-object.html). 
* In the found types are defined by properties that contain a reference to the desired class or its ancestors. 

{% include important.html content="If references to a deleted object is the attribute [NotNull](fo_attributes-class-data.html), it will be prosrochena exception `PropertyCouldnotBeNullException` and the object will not be deleted." %} 

## Example 

Suppose there is a diagram of the form: 

![](/images/pages/products/flexberry-orm/i-references-cascade-delete/i-references-null-delete.png) 

On the chart attached `IReferencesNullDelete` with the stereotype [externalinterface](fd_external-interface.html) , from which inherited class `Plant2` and `Cabbage2`. Deleting an instance of this class will be deleted all references to the object. 
At the specified location of classes subject to the [inheritance](fd_interfaces.html): 
* Deleting a class instance `Plant2` will examine the links among instances of classes `Salad2` and `Dish2`. 
* Deleting a class instance `Cabbage2` will examine the links among instances of classes `Salad2`, `Dish2`, `CabbageSalad`, `Soup2` (instances of the class `CabbagePart2` are detaylari and will be deleted). 
* If a deleted object will be, for example, in the instance of the class `Salad2` in the property `Ingridient2`, it will be prosrochena exception because this property is the attribute `NotNull`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/