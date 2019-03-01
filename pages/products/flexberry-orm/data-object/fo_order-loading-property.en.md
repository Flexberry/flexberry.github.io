--- 
title: the boot Order properties of the data object 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, loading objects 
summary: the Rules and the loading order of object properties 
toc: true 
permalink: en/fo_order-loading-property.html 
lang: en 
autotranslated: true 
hash: 59bd234984785569bf18c242d0c3c6856521bd987bd60339f76878f70e456a7d 
--- 

While loading the data the following rules apply: 

* The artisans first create the data objects. 
* Filled with artisans and their attributes in the order specified `LoadingOrderAttribute`, then all the rest. 
* Fill detaily, in accordance with the procedure specified in [view](fd_view-definition.html). 

## set the boot order properties of the data object (LoadingOrderAttribute) 

If you want to explicitly set the boot order properties of the data object (for example, in the case where it is important for the correct account [computable attributes](fo_nonstored-calculated-properties.html)), you can use [attribute `LoadingOrderAttribute`](fd_data-classes.html) [data class](fo_data-object.html). The parameter is passed in a one-dimensional string array with the names [attributes](fo_attributes-class-data.html) arranged in the order download.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/