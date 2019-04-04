---
title: Application neranenah classes
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data types, data objects
summary: Features run forms with the use of neranenah classes
toc: true
permalink: en/fo_using-not-stored-classes.html
lang: en
autotranslated: true
hash: 63f91303b22d39cd0ca3a31a5304d4b2a5128d961d215cb618a7ec100af3f916
---

Generated [Flexberry ORM](fo_flexberry-orm.html) forms for neranenah classes are useful for selecting certain of the current object, the user authorizations.

To make the class nagraniem, it is necessary when working with class diagram to remove a tick from parameter `Stored` in its properties (or add the "/" character in front of its name).

In order to suppress the message about the need to preserve the form data, you can override the form's method `OnClosing` where to ban the call to the base method.

```csharp
protected override void OnClosing(CancelEventArgs e)
{
	//base.OnClosing(e); 
}
```

## Launch a modeless form

To run the resulting forms are modeless, you can use [manual](fw_editform.html).

{% include note.html content="When you create a shape using independent shape, which will trigger the dependent form." %}

{% include note.html content="the Description method is only good to run a modeless form. When using this method to launch a modal form, you must override the method [Edit](fa_form-interaction.html), but a simple change in the corresponding code call `Show` on `ShowDialog` not resolve the problem, as this option does not account for that to work correctly, the form must undergo a certain sequence of calls." %}

## Launch the modal form

To run forms in modal mode, it is possible to connect the Assembly `IIS.WinUI` to the project, and later in the code to carry through her starting form (example of starting the form presented in the article [Setting the current object when you run the app](fo_define-default-object.html)).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}