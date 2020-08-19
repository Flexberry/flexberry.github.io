--- 
title: The Most interesting properties LookUp's for Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: JavaScript, Windows UI (Controls) 
toc: true 
permalink: en/fa_interesting-lookup-settigs.html 
lang: en 
autotranslated: true 
hash: d01fb31dfafb46ee368f0e493fa0061f81d707d0f4011ee47c8f78640bc750be 
--- 

The most interesting properties LookUp's: 

* PropertyToShow 
* MasterViewName 
* MasterTypeName 
* ObjectTypeName 
* ObjectViewName 

Below they are described in detail. 

### PropertyToShow 

Responsible for the display property __masters__ after selecting lucapa. 

Does not work when exposed to [AutoComplete](fa_predict-input-web.html). 

Set in the method `PostApplyToControls` or `PostLoad` edit form. If you set in the methods that are triggered before, the changes will overwrite WebBinder'ohms. 

Accepts composite properties (properties masters). For example, you can specify: 

```csharp
ctrlКлиент.PropertyToShow = "Registration.City";
``` 

Thus, when choosing a master `Клиент` will display the property `Клиент.Registration.Город`. 

### MasterViewName 

Sets the view that will be used to open the form lucapa. 

Unlike `PropertyToShow`, can be installed anywhere and does not depend on `AutoComplete`. 

### MasterTypeName 

Sets the type of object you want to select on the LookUp. True when a master object is associated with inheritance. That is, if there are two classes, related by inheritance (class a inherits from class B), and you need to LookUp had a class A (instead of the default open class B), you must install `MasterTypeName` as follows: 

```
ctrlM1.MasterTypeName = typeof(M1).AssemblyQualifiedName;
``` 

do not forget to set `MasterViewName` 

{% include note.html content="For signification `MasterTypeName` use `typeof(Type).AssemblyQualifiedName`. 
More details can be found [in the article the Problem of types (a typeusage)](fo_type-usage-problem.html)." %} 

### ObjectTypeName 

Sets the object type for which you are installing the master. 
Similarly `MasterTypeName` relevant to cases of succession, but this time involved in the inheritance base object and not the master. Set in a pair of `ObjectViewName`. 

### ObjectViewName 

Sets the representation of the underlying object. 
Similarly `MasterViewName` relevant to cases of succession, but this time involved in the inheritance base object and not the master. Set in a pair of `ObjectTypeName`.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}