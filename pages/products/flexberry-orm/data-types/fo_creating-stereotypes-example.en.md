--- 
title: creating a custom data type 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data types, example 
summary: an Example of creating a stereotype typedef 
toc: true 
permalink: en/fo_creating-stereotypes-example.html 
lang: en 
autotranslated: true 
hash: 7f19157fb8c38661eb5f52a4085ad34f59557eea785b5f3a236e612576731711 
--- 

There is a possibility to define your own type on the chart Flexberry. This should place the new class in the diagram and change its stereotype to "typedef"](fd_typedef.html). 
As a result, this type can be used as an attribute to class properties at all stages Flexberry. 

However, this type is only an alias, and is used only at the level of abstraction in modeling. 

You need to allow this type of "typedef", putting the corresponding types in SQL C# and special card types Flexberry](fd_types-map.html). 

For example, if you look at the class String4000 (Flexberry sample repository, Entities diagram), you will see that it mapitse on the System.String and VARCHAR(4000). 

As an example of the generated properties, you should pay attention to the Description class CD: 

```csharp
[StrLen(4000)]
public virtual string Description
{
        get {...}
        set {...}
}
``` 

Full list of code examples Flexberry ORM is in [code Examples](fo_code-samples.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}