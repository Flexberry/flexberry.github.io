--- 
title: getting a presentation of the value of the data object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM 
summary: Methods to obtain and override data objects 
toc: true 
permalink: en/fo_get-presentation-value.html 
lang: en 
autotranslated: true 
hash: fcbcede0b7c111fc99b286a1a5880e82c645442d8d612666e947cc5e72b2ea7a 
--- 

If you need the string representation of a [DataObject](fo_data-object.html) you can use the method `DataObject.GetPresentationValue()`. 

This method will return the value of the attributes "Name", "Name", "Name", if available, or the first string attribute. In the absence of string attributes return value of the method `DataObject.ToString()`. 

If necessary override this method in the application project must use the delegate `DataObject.GetPresentationValueDelegate`. 

Method `DataObject.GetPresentationValue()` used for the naming of the desktop shortcuts. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}