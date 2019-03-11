--- 
title: Synonyms of types (classes with stereotype typedef) 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, Flexberry ORM, typedef, type synonyms, stereotype 
summary: the Purpose and use of synonyms types of 
toc: true 
permalink: en/fd_typedef.html 
lang: en 
autotranslated: true 
hash: 30c97c7536905cde778e7f0d07ac41b82231f9026fe1f4b85dd0b78d721adacb 
--- 

`Typedef` - [stereotype](fd_key-concepts.html) indicating the synonym type. 

The purpose of synonyms types: 

* Increasing the level of abstraci in the simulation. 
* Fine tune the target type of the language in which the code generation. 

When generating code synonyms are given to basic types in the target language (in the code gets the base type, but a synonym does not specify in any way). 

The conversion to the target type, you can configure the [settings of the corresponding module generator](fd_types-map.html). 

On the [charts](fd_class-diagram.html) class with the [stereotype](fd_key-concepts.html) Typedef cannot be bound by the associations, the attributes and methods do not make sense (is not considered). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}