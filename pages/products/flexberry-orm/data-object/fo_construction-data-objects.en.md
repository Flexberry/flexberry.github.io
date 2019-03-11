--- 
title: Designing data objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data objects, example 
summary: Illustration of constructing a data object for example 
toc: true 
permalink: en/fo_construction-data-objects.html 
lang: en 
autotranslated: true 
hash: c782b271e595ba5624d71e9b7635320b3b6274bf118529c403d37ad8b017d8eb 
--- 

The developer will design any object data standard for `.Net` follows: call `new` with the necessary constructor. 

Example: 

```csharp
SimpleDataObject sdo = new SimpleDataObject();
sdo.Master = new MasterDataObject();
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}