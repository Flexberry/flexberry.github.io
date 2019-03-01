--- 
title: determination of load properties 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, properties, methods, 
summary: Using techniques GetLoadedProperties and CheckLoadedProperty 
toc: true 
permalink: en/fo_definition-loaded-properties.html 
lang: en 
autotranslated: true 
hash: 115c6fd9109fd7a3266b4f5193e0de4a643c7aaff2d4fc19e4fb40d65728abd7 
--- 

there are different ways to define retrieved properties. One of such ways is the use of methods `GetLoadedProperties` and `CheckLoadedProperty` with the following features: 

* An array of loaded properties will only include own properties, so check the load properties wizard impossible. The link to the wizard is also a property of the object and to check it you can, but see below 
* If [view](fd_view-definition.html) is a property of the form: "Master.Soilwater", then loaded the properties Master will be only in case if in the database the value "Master" null. 
* If [view](fd_view-definition.html) is the property of the "Master.Soilwater" and "Master", then in the loaded properties will always be set to "Master" regardless of the presence of this object in the master database 

Some recommendations when to choose a representation of themselves [masters](fd_master-association.html), and when simply their properties, presented in the article [in Master view](fd_masters-view.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/