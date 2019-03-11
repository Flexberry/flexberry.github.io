--- 
title: External classes (classes with stereotype external) 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, Flexberry ORM, external-class, external, generation, stereotype 
summary: features Description external classes and their generation 
toc: true 
permalink: en/fd_external-classes.html 
lang: en 
autotranslated: true 
hash: a370f9c40f2361e5a92a7735f0325a19d3fa49f76002290161387cf2479d2ebb 
--- 

[Stereotype](fd_key-concepts.html) `external` allows you to declare a class that corresponds to any external (not declared, language) class. It is convenient and necessary in cases when you want to operate with the type that the model has not been announced, but declared somewhere in the code (for the case of `.Net`-language - source or connected to the source builds). 

An example of the outer class: 

![](/images/pages/products/flexberry-designer/class-diagram/external.jpg) 

## That is generated describing the external class 

Generated | Generate SQL DDL Generation .Net language 
:-------------------------------|:----------------------------|:------------------------------ 
Any part of any UML class (attribute, method parameter, etc.) declared by this class | or As is specified in the transformation map types for the generator As is. Attribute, method parameter, etc. are declared by this type 

## Links to other stages 

Through external class, you can put a link on another stage. In this case, will be generated all the variety of classes from this and from the stage. The generated SQL will be similar classes for both stages. 

## Features generating 

If in the initial stage and external stage there are classes with the same name, priority is given to external class. When generating the database all the attributes of external classes are stored and added to them the attributes of the original class. If the attribute is already present, it will not be added. 
Priority is given to external class, because most often it is obepechenie components, for example, "Audit", "Authority" and you cannot change them. 
**Example**: the outer stage there is a class called `Класс1` and attributes `А1` and `А2:string`, and in the initial stage there is a class with the same name and attributes `А2:int` and `А3`. Then, when bringing the database into compliance, it will generate a class with attributes `Класс1` `А1`, `А2:string` and `А3`. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}