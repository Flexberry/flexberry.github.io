---
title: IReferencesNullDelete
sidebar: flexberry-orm_sidebar
keywords: DataObject Flexberry ORM, business servers, a cascading delete of objects
summary: Features cascade-delete objects with the use of Null
toc: true
permalink: en/fo_i-references-null-delete.html
lang: en
autotranslated: true
hash: 1d567ad672e0b79e54282559413296cf6e635e99c98267dab006df990bc35e2c
---

PstrfIReferencesNullDelete` interface enables cascading deletion of objects (deleting all the referencing objects are stamped with a NULL instead of a reference to a deleted object).

## How does IReferencesNullDelete

Logic for zero reference is spelled out in [business server](fo_business-server.html) interface `IReferencesNullDelete`; own properties and methods this interface does not provide. Thus, together with some object to remove all references to it, enough that the object class was inherited from `IReferencesNullDelete`.
The classes that refer to the desired, as follows:

* Defined the directory where is the Assembly with the class, to which we are looking for.
* In the directory are selected, all assemblies whose name ends with «(Objects).dll».
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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}