--- 
title: setup Flexberry Designer for Nullable types 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, nullable, typedef, map types, example 
summary: Features setting nullable type for example nullbool 
toc: true 
permalink: en/fd_create-nullable.html 
lang: en 
autotranslated: true 
hash: a429308611d0567b7c01baae33ed011e2b108147eb566f4365540114f6916b89 
--- 

Setting procedure [Flexberry Designer](fd_landing_page.html) to support [Nullable-types](fd_nullable-types.html) by building type `bool?`. 

You want to use [the class diagram](fd_class-diagram.html) type `bool?`. To do this: 

1.to create a class NullBool [with stereotype typedef](fd_typedef.html) 
2.[card types](fd_types-map.html) generator objects to add the line: 

Type | Appears to 
:---------------|:--------------------------------------- 
NullBool | System.Nullable<esc><System.Boolean></esc> 

3.[card types](fd_types-map.html) generator database add the line: 

Type | Appears to 
:---------------|:----------- 
NullBool | BIT 

After that, it becomes possible to use the [diagrams](fd_class-diagram.html) type `NullBool`, which when generated will work as `bool?`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/