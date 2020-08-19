--- 
title: Support binary arrays with data service 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data service, data types 
summary: configuring the binary arrays 
toc: true 
permalink: en/fo_binary-array-ds.html 
lang: en 
autotranslated: true 
hash: cf781c97c62d1474029cb882fdf7e58f36889782c2605f8627bfcb8020b0697f 
--- 

May support attributes of type binary array. It can be a field to store the [serialized objects](fo_aggregating-function.html), images, etc. 

To support binary arrays you want: 

* Select using [typedef](fd_typedef.html) new data type [charts](fd_class-diagram.html): 

![](/images/pages/products/flexberry-orm/data-service/byte-array.png) 

* [The map .NET types](fd_types-map.html) note byte[] 

![](/images/pages/products/flexberry-orm/data-service/types-net.png) 

* [The map of SQL types](fd_types-map.html) to set varbinary(MAX) 

![](/images/pages/products/flexberry-orm/data-service/types-sql.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}