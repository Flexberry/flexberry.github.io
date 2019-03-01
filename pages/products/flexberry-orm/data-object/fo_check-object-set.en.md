--- 
title: Verification of object fields in the set method 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data objects, exceptions 
summary: describes how to use the set method 
toc: true 
permalink: en/fo_check-object-set.html 
lang: en 
autotranslated: true 
hash: 3842fe37b4b3876a5af3742e861936faae1455de9b5e24c9618d97f7524f6c8b 
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

A comparison of this method with some others can be found in the article [data Validation on the form during editing](fw_check-form-field-during-edit.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/