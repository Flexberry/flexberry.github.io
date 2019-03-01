--- 
title: Interface ISpecialEmptyValue 
sidebar: flexberry-orm_sidebar 
keywords: DataObject, Windows UI, a blank value 
summary: a Blank value for the given data type 
toc: true 
permalink: en/fo_i-special-empty-value.html 
lang: en 
autotranslated: true 
hash: 7d13bde8c64f9b3c1415ac5e0dc359b5b58415f7c94e9043e47e0b85b8f63408 
--- 

Interface `ISpecialEmptyValue` specifies the value to be considered as empty for the given type. If the type implements the given interface, then the verification fields (when saving and displaying `*`) for instance of the type method will be called `IsEmptyValue(object value)`, which must return a Boolean.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/