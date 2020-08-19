--- 
title: exception Handling 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_set-exception.html 
lang: en 
autotranslated: true 
hash: d77260c95356015101aec63a12974afb1cba1b7a192907821aaf81e26c2dbacc 
--- 

Check the quantity of goods specified in the order. 

Goal: to provide for possible data entry error, so the user was not able to enter completely the wrong value. For example, when the number of items in the order should not be able to enter a negative value. 

To do this, in the method `set` generate an exception if an invalid entry: 

```csharp
set
{
	// *** Start programmer edit section *** (Starokazache.The number Set start) 
	if (value < 0)
	{
		Exception ex = new Exception("The value of a quantity cannot be negative");
		throw ex;
	}
	// *** End programmer edit section *** (Starokazache.The number Set start) 
	this.fКоличество = value;
	// *** Start programmer edit section *** (Starokazache.Number of Set end) 

	// *** End programmer edit section *** (Starokazache.Number of Set end) 
}
``` 

When the exception is generated, in the form of a message about the incorrect input. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [the Imposition of restrictions on the LookUp in the AGE, associate lyapov.](gpg_limit-function-for-lookup-in-age.html) 
* [Automatic retrieving data from LookUp](gpg_auto-get-data-from-lookup.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}