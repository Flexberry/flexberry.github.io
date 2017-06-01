---
title: Блокировка элемента на форме редактирования
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_set-ctrl-read-only.html
lang: ru
---

Цель: чтобы избежать такой ситуации, когда количество товара на складе вычитается несколько раз, необходимо организовать блокирование статуса заказа, если заказ уже оплачен.

Для реализации данного поведения нужно исправить метод `PostApplyToControls()` в коде формы редактирования заказа `ZakazE.aspx.cs` следующим образом:

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

Метод `PostApplyToControl()` вызывается сразу за `AfterApplyToControls()`, 
а метод `AfterApplyToControl()`, в свою очередь, вызывается после распределения данных объекта 
по контролам страницы. На момент вызова этого метода все контролы на странице уже обновлены в соответствии с данными объекта. 
 
## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Работа с бизнес-сервером](gpg_business-server.html)
* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i> 
