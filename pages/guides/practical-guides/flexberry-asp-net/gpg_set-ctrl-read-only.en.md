---
title: Lock object on the edit form
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_set-ctrl-read-only.html
lang: en 
autotranslated: true 
hash: a3fe26e5149775969cf53ff25ad1b99bd9dabe3bb9b20f4dd9e6d6edafee8127
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
* [A practical guide "do as I Do"](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i> 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/