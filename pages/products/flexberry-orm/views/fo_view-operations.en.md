--- 
title: Operations with representations 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, performance 
summary: the Operators of Union, intersection, difference, and exclusion of representations 
toc: true 
permalink: en/fo_view-operations.html 
lang: en 
autotranslated: true 
hash: 1ee5d4fd120513c199380d3b20614e60e412108593e4afade050d0d7fbe5bef4 
--- 

Over the [views](fd_view-definition.html) as on sets properties of related data objects, the following operations are possible: 

* The Union operator `|`. 
* The intersection operator &. 
* The difference operator -. 
* Excluding the Union, the ^ operator. 

Associated [submission of datalow](fd_view-definition.html) are processed recursively. 

Operations performance are a very convenient opportunity when performing a [pochityvaya objects data services, data](fo_additional-loading.html) (for example, if a data object initialized with the part properties and want to display it in the view with a wider set of properties, you can calculate the difference and to read a data object only through it). 

An example of working with operations on views is available at [https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/