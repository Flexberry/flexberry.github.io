--- 
title: data Types in ORM Flexberry 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM data types 
summary: brief characteristics of supported Flexberry ORM types 
toc: true 
permalink: en/fo_flexberry-orm-types.html 
lang: en 
autotranslated: true 
hash: af257975632901d4dbc8bf403ebbe53748a3d204a91f4df302754007aa2b8ae3 
--- 

[Flexberry ORM](fo_flexberry-orm.html) allows you to work with the following types: 

* .Net basic types (such as int, string, float, etc.) 
* .Net [Nullable-types](fd_nullable-types.html). 
* [User defined types](fo_convert-type-property.html). 
* [Nullable types NullableDateTime, NullableInt, NullableDecimal](fd_nullable-types.html) (located in the Assembly ICSSoft.STORMNET.UserDataTypes): 
* Optional types [Flexberry ORM](fo_flexberry-orm.html) (these types are serialized in XML format by Implicit transformations and is stored in a database (or other storage) in a serialized виде; the search attributes specified types can be produced only by a substring and this is the main difference from the full structure данных; are located in the Assembly ICSSoft.STORMNET.UserDataTypes). 
* Contact - a contact of the user (client or any other person). 
* Event - a calendar event. 
* GeoData - GEODATA (`Feature`). 
* Image - the image. 
* WebFile - a Web file. 
* [Types of object-based data](fo_data-object-as-attribute-type.html). 

## Card types 

A key element to define the display used for [class diagram](fd_class-diagram.html) types to database types or code is [map types](fd_types-map.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}