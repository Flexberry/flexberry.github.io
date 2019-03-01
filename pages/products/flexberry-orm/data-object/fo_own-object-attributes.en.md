--- 
title: Access to own object attributes and attributes of related objects 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, attributes 
summary: Getting object properties 
toc: true 
permalink: en/fo_own-object-attributes.html 
lang: en 
autotranslated: true 
hash: 220710c2da3d5675f1db8d4298096dcffbef74c79bc67caab5257ab8385a07eb 
--- 

## Getting property value 

Access attributes [data object](fo_data-object.html) and the attributes of related objects standard. 

```csharp
SimpleDataObject sdo = new SimpleDataObject();
sdo.Name="Something";//Access a private attribute 
sdo.Master.DblAttr=3.14;//Access the attribute Masterova 
sdo.Details[0).StringAttr="DetailAttribute";//Access the attribute dealova 
``` 

## Stratifitsirovannoi retrieving properties as strings 

Instead use attribute names as strings-constants, for example: 

```csharp
var propertyName = ((Пользователь)dataObject).Наименование;
``` 

You can use stratifitsirovannykh access using the lambda method [`Information`](fo_methods-class-information.html): 

```csharp
var propertyName = Information.ExtractPropertyName<Пользователь>(x => x.Наименование);
``` 

All available methods: `ExtractPropertyName`, `ExtractPropertyPath`, `ExtractPropertyInfo` described in the article [retrieve metadata objects](fo_methods-class-information.html).


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/