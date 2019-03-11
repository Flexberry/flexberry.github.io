--- 
title: Lock object on the edit form 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_set-ctrl-read-only.html 
lang: en 
autotranslated: true 
hash: eebabbb25142c758ec99dd190de9dc91a0964a01efd1d692506a341005bd1ef4 
--- 

Goal: to avoid a situation where the number of goods in stock is subtracted several times, it is necessary to organize the blocking of the status of the order, if the order is already paid for. 

To implement this behavior you need to correct the method `PostApplyToControls()` in the form's code editing order `ZakazE.aspx.cs` as follows: 

```csharp
protected override void PostApplyToControls()
{
	if ((DataObject != null) && (DataObject.Статус == СостояниеЗаказа.Оплаченный))
	{
		wb.SetReadonlyToControl(ctrlСтатус, true);
	}

	Page.Validate();
}
``` 

Method `PostApplyToControl()` called just for `AfterApplyToControls()`, 
a method `AfterApplyToControl()`, in turn, is called after the distribution of the data object 
on the controls page. At the time of calling this method, all controls on the page have been updated in accordance with the data object. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [business server](gpg_business-server.html) 
* [Practical guide «as I Do»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}