--- 
title: Class ICSSoft.STORMNET.KeyGen.KeyGuid 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, KeyGen, KeyGuid 
summary: description of the class KeyGuid 
toc: true 
permalink: en/fo_key-guid.html 
lang: en 
autotranslated: true 
hash: 41bae79befe4574eb0560c47adc85c0c91b6eb5910751d956016bbcb340c278c 
--- 

`KeyGuid` different from `System.Guid` method `Parse` the presence and absence of some designers. 
The class is in the Assembly `ICSSoft.STORMNET.DataObject`. 

### KeyGuid Convert to a string 

In order to obtain the value `KeyGuid` in the string without the brackets: 

```csharp
string stringKeyGuid = keyGuid.Guid.ToString("D");
//5ed31914-6827-485d-b34d-df1f65a08f47 
``` 

In order to obtain the value `KeyGuid` in the string with braces: 

```csharp
string stringKeyGuid3 = keyGuid.Guid.ToString("B");
//{5ed31914-6827-485d-b34d-df1f65a08f47} 
``` 

[Additional information](https://msdn.microsoft.com/ru-ru/library/97af8hh4) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/