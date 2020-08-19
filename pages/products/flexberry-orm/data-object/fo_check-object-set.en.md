---
title: Verification of object fields in the set method
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data objects, exceptions
summary: describes how to use the set method
toc: true
permalink: en/fo_check-object-set.html
lang: en
autotranslated: true
hash: f703a71e6f01bd5647045e0b45b4d5f093ef48689275cdfde2799dc2d3d90894
---

Validating data on the form can be achieved by throwing an exception if an invalid entry in the method `set` the corresponding field of the object.

```csharp
public class Кредит : ICSSoft.STORMNET.DataObject
{
	//... 
	public virtual double СуммаКредита
	{
		get
		{
			//... 
		}
		set
		{
			if (value <= 0)
			{
				Exception ex = new Exception("The value of the loan amount must be positive!");
				throw ex; 
			}
			this.fСуммаКредита = value;
		}
	}
}
```

Other methods of data validation on the form described in the article [data Validation](fw_edit-form-validation.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}