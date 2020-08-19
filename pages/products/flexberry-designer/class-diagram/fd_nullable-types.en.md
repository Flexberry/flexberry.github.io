--- 
title: Nullable types 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, nullable, the nullable types, the data types 
summary: Support nullable-types Flexberry ORM and .NET 
toc: true 
permalink: en/fd_nullable-types.html 
lang: en 
autotranslated: true 
hash: 64881fee041778336cd2e0c4d961d3a6becd9894552089d36583ce7cd08b98f4 
--- 

Nullable types are an extension to the usual types that can store the value `null` (that is unlike the standard types, Nullable types allow the user to store any value in a field of scalar type). 

{% include note.html content="When designing information systems it is necessary to consider the difference between scalar and nullable types, choosing the more suitable for a particular situation type." %} 

## Standard Nullable-types in ORM Flexberry 

[Flexberry ORM](fo_flexberry-orm.html) [provides the following Nullable-types](fo_flexberry-orm-types.html) (located in the Assembly ICSSoft.STORMNET.UserDataTypes.dll): 

* `NullableInt` - integer `null`. Accepts all values, which takes `int` and can also be null. 
* `NullableDateTime` - type for storing date and time `null`. Can store all of the values that keeps `DateTime` and null. 
* `NullableDecimal` - type to store fractional numbers `null` 

{% include note.html content="Implemented in [Flexberry ORM](fo_flexberry-orm.html) Nullable types have now become obsolete. It is preferable to use the Nullable types provided .Net.</msg> 
"%} 

## Nullable types .Net 

.NET Framework supports [Nullable-types]([http://msdn.microsoft.com/en-us/library/1t3y8s4s(v=vs.110).aspx). [Flexberry ORM](fo_flexberry-orm.html) [supports Nullable types .Net](fo_flexberry-orm-types.html). 

If code generation was used types `System.Nullable<esc><T></esc>` (or `T?`, which is equivalent according to [msdn](http://msdn.microsoft.com/en-us/library/1t3y8s4s(v=vs.110).aspx)), it is necessary [to produce the custom setting](fd_create-nullable.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}