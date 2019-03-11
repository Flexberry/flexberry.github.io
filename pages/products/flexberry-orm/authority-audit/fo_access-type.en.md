--- 
title: Type of authorization check for the class 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry Security, authority, authorization type 
summary: a description of the types of authorization checks 
toc: true 
permalink: en/fo_access-type.html 
lang: en 
autotranslated: true 
hash: eb05e846e22f5afb9a81857411024be1520df6afbdf4d92115badc03db4b3e6e 
--- 

`AccessType` determines the type [authorization check](fa_right-manager.html) for the class. There are the following types: 

```csharp
public enum ICSSoft.STORMNET.AccessType
{
	none,  			// there is no check 
	@this, 			// checks only on the current class 
	@base, 			// check for the base class 
	@this_and_base  // check for the base class and the current 
}
``` 

## AccessType default 

In `Flexberry Designer` added the ability to define stages of the value `AccessType` default for the generated classes (in the context menu stage, select the "ORM -> C# -> model Properties -> Advanced", where to specify "Type check default access"). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}