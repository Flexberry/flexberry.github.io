--- 
title: Access to own object attributes and attributes of related objects 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, attributes 
summary: Getting object properties 
toc: true 
permalink: en/fo_own-object-attributes.html 
lang: en 
autotranslated: true 
hash: cd81535ac3510b0557cda3587afaf065631e5d2e7dd25fb9452bc6f9bd0cf310 
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


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}