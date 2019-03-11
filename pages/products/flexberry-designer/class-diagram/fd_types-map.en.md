--- 
title: Map types 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, Flexberry ORM, map types, styles, settings, database, software code, attributes, generation 
summary: Creation and generation of data types for databases and programming code 
toc: true 
permalink: en/fd_types-map.html 
lang: en 
autotranslated: true 
hash: ac6429c9f7ea94eb743ea102d1288ca0ad9efe3bc305504f84fbf0e7d3d36bea 
--- 

When you create a [class diagram](fd_class-diagram.html) [attributes](fo_attributes-class-data.html) set types. In order to determine for the generator of the correspondence between the types used on [the class diagram](fd_class-diagram.html) and the types of databases and program code that uses the map types. 

{% include note.html content="it Is possible to copy cell card types. For copying, you must allocate the interest range and press `Ctrl С` or `Ctrl Insert`. To insert the is to determine the upper-left cell where to insert the range and then click `Ctrl V` or `Shift Insert`." %} 

Examples of the settings of the card types available in the following articles: 

* [Support binary arrays](fo_binary-array-ds.html) 
* [Setup Flexberry Designer for Nullable-types](fd_create-nullable.html) 

### the Permission of type when generating 

Expansion card types possible through the use of [synonym types (classes with stereotype typedef)](fd_typedef.html). 

The map types can write, what type of target language is displayed [synonym type in the diagram](fd_typedef.html). 

Type | Appears to 
:---------|:----------- 
Строка40 | string 
string | System.String 

Type conversion during code generation as follows: if the type is not standard (for `.Net framework`), it is converted in accordance with lookup. 
After substituting all repeated again converted, if it is not standard. 
This is repeated until there is reduction to the standard type that is generated code. 
If a non-standard type cannot be converted (to substitute specified a custom type for which there is no other substitution), code generation stops with an error. 
In this example, it is clear that Строка40 appears in the string (predefined type), which in turn is converted to the type `System.String Microsoft .Net framework`. 

### Map types for the database 

To access the map types to the database through the context menu of stages in the generator settings. 
The left column shows the types that you can use to [the class diagram](fd_class-diagram.html), right - corresponding database types: 

![](/images/pages/products/flexberry-designer/class-diagram/type-map.png) 

If you add [typedef](fd_typedef.html), the map may take the following form: 

![](/images/pages/products/flexberry-designer/class-diagram/type-map-db-ext.png) 

### Map types for program code 

To access the map types for software code via the context menu of stages in the generator settings. 

The map of types contains the following columns: 

* Types that you can use to [the class diagram](fd_class-diagram.html). 
* Relevant types of software code. 
* Assembly, which is a software type (used for types that are not .Net types). 

![](/images/pages/products/flexberry-designer/class-diagram/type-map-app.png) 

If you add [typedef](fd_typedef.html), the map may take the following form: 

![](/images/pages/products/flexberry-designer/class-diagram/type-map-app-ext.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}