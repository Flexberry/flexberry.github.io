---
title: IReferencesCascadeDelete
sidebar: flexberry-orm_sidebar
keywords: data Objects, Flexberry ORM, business servers, cascading the deletion of objects
summary: Features cascading deletion of objects
toc: true
permalink: en/fo_i-references-cascade-delete.html
lang: en
autotranslated: true
hash: 760064edfd0350b3709ef7beebf7d60d54e57b75c499d9314fcf85b0c1e07e0c
---

## IReferencesCascadeDelete

PstrfIReferencesCascadeDelete` interface enables cascading deletion of objects (the object all the referencing object is also deleted).

## How does IReferencesCascadeDelete

The logic for cascading delete is spelled out in [business server](fo_business-server.html) interface `IReferencesCascadeDelete`, own properties and methods this interface does not provide. Thus, together with some object to remove all objects that refer to it, enough that the object class was inherited from `IReferencesCascadeDelete`.
The classes that refer to the desired, as follows:

1. Defines the directory where is the Assembly with the class, to which we are looking for.
2. In the directory are selected, all assemblies whose name ends with «(Objects).dll».
3. In assemblies looking for types that inherit from [DataObject](fo_data-object.html).
4. In the found types are defined by properties that contain a reference to the desired class or its ancestors.

## Example

Suppose we have a diagram of the form:

![](/images/pages/products/flexberry-orm/i-references-cascade-delete/i-references-cascade-delete.png)

On the chart attached `IReferencesCascadeDelete` with the stereotype [externalinterface](fd_external-interface.html) , from which inherited class `Territory2` and `Country2`. When you delete instances of this class will be deleted also all the objects that refer to them.
At the specified location of classes subject to the [inheritance](fd_inheritance.html):
1. When you delete a class instance `Territory2` will examine the links among instances of classes `Human2` and `Place2`.
2. When you delete a class instance `Country2` will examine the links among instances of classes `Human2`, `Place2`, `Adress2`, `Apparatus2` (instances of the class `Region` are detaylari and will be removed even without using its interface).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}