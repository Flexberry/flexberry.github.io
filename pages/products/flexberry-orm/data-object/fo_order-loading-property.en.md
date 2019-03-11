--- 
title: the boot Order properties of the data object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, loading objects 
summary: the Rules and the loading order of object properties 
toc: true 
permalink: en/fo_order-loading-property.html 
lang: en 
autotranslated: true 
hash: 15f583aae4bf5646a7d26e653610674725b8d99a1b4032806db31560e290bf73 
--- 

While loading the data the following rules apply: 

* The artisans first create the data objects. 
* Filled with artisans and their attributes in the order specified `LoadingOrderAttribute`, then all the rest. 
* Fill detaily, in accordance with the procedure specified in [view](fd_view-definition.html). 

## set the boot order properties of the data object (LoadingOrderAttribute) 

If you want to explicitly set the boot order properties of the data object (for example, in the case where it is important for the correct account [computable attributes](fo_nonstored-calculated-properties.html)), you can use [attribute `LoadingOrderAttribute`](fd_data-classes.html) [data class](fo_data-object.html). The parameter is passed in a one-dimensional string array with the names [attributes](fo_attributes-class-data.html) arranged in the order download.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}