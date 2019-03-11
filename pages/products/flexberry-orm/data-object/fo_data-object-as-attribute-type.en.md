--- 
title: class Attribute type data object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, data types, attributes 
summary: the nuances of the use of the data object as the type for the attribute class 
toc: true 
permalink: en/fo_data-object-as-attribute-type.html 
lang: en 
autotranslated: true 
hash: 69486951256fb1c77ece3faacc0c7004aeb91130143c0dcdc6c14496744a9d57 
--- 

If [the class diagram](fd_class-diagram.html) [attribute](fo_attributes-class-data.html) type is inherited from [DataObject](fo_data-object.html), you need to: 

* generate and compile this type 
* create the required classes with this attribute to specify the Namespace and the full name of the Assembly (relative to the folder Flexberry Designer) which defines the specified type. 

Only after this is the generation of this class. 

![](/images/pages/products/flexberry-orm/data-object/data-object-as-attribute-type.GIF) 

The use of the class attribute with the data object as the type attribute is not recommended in General, if it is possible to use [master](fd_master-association.html) or [detaily](fo_detail-associations-properties.html). 

The main feature of this solution is that between the classes in this case there is no connection (in this case between classes `Зоопарк` and `ДиректорЗоопарка`). Accordingly, the field `Директор` class `Зоопарк` will contain a link to `ДиректорЗоопарка`, and to keep [the serialized object](fo_aggregating-function.html) `ДиректорЗоопарка`. 

The use of the class attribute with a [object data](fo_data-object.html) as the type can be useful for saving custom settings, when returning an object model settings, not just a string. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}