--- 
title: Interface ISpecialEmptyValue 
sidebar: flexberry-orm_sidebar 
keywords: DataObject, Windows UI, a blank value 
summary: a Blank value for the given data type 
toc: true 
permalink: en/fo_i-special-empty-value.html 
lang: en 
autotranslated: true 
hash: 430071ba030ae47d9c6e185e64a36285982a1bd7693f2405ea38949b69c03234 
--- 

Interface `ISpecialEmptyValue` specifies the value to be considered as empty for the given type. If the type implements the given interface, then the verification fields (when saving and displaying `*`) for instance of the type method will be called `IsEmptyValue(object value)`, which must return a Boolean.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}