--- 
title: setup Flexberry Designer for Nullable types 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, nullable, typedef, map types, example 
summary: Features setting nullable type for example nullbool 
toc: true 
permalink: en/fd_create-nullable.html 
lang: en 
autotranslated: true 
hash: 08e7224784a5d08e4dfb18bbe141247fe767d898b7f8cab5e13eb76b0b5fd1e9 
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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}