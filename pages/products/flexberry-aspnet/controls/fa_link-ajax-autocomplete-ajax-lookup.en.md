---
title: Bondage AjaxAutocomplete and AjaxLookup
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_link-ajax-autocomplete-ajax-lookup.html
lang: en
autotranslated: true
hash: 9778fb809bd433ceed0798c63ae6423ea6be0748c80efdfc51cb52aa2e6db0c8
---

Has the ability to bind AjaxAutocomplete to AjaxLookup. Ie, if you select in control with the support `AjaxAutocomplete` will automatically put the value of lucapa, if the value of the specified property will be found in artisan objects, otherwise lucapa put down an empty value.
__Attention:__ do Not confuse it with the support `автодополнения in AjaxLookup`.

__Attention:__ If you want to use a calculated property, it must be indicated the introduction!

## Possible

![](/images/pages/products/flexberry-aspnet/controls/link-autocomplete.png)

For example, the edit form is displayed `Организация` lookup for the master `Адрес` and private property `АдресСтрокой`. You need to add autocompletion for `АдресСтрокой`, the values of which will be taken from `Адрес.АдресПолностью`. The coincidence of the entered value and the value of the master property `Адрес.АдресПолностью` put down the value of lucapa(the object reference). If you entered a value that is not in artisan values, the `АдресСтрокой` just continue, and reference to a master will not bear.

## Example

```csharp
AjaxAutocomplete.AddLinkedAutocomplete(ctrlАдрес, ctrlАдресСтрокой, typeof(Адрес), "Adrasmanskiy");
```

{% include note.html content="This code can be placed in the method `PreApplyToControls` [edit form](fa_form-interaction.html)." %}

Also, there is a setting connection between lucapa and input field. For example, if you change the value in the input field, if you change the value in lucapa.

## Example

```csharp
bool changeValueWithLookup = true;
AjaxAutocomplete.AddLinkedAutocomplete(ctrlАдрес, ctrlАдресСтрокой, typeof(Адрес), "Adrasmanskiy", changeValueWithLookup);
```

## An example of an explicit limit for completion

__Warning__: If the limit is set ajax lucapa and it is associated with a text field, not a restriction on completion, the limit on the completion of lucapa will undertake.

```csharp
Function licenceFunc = FunctionBuilder.BuildSQL("(select count(*) from Licenzirovanie where STORMMainObjectKey = Organization) > 0");
AjaxAutocomplete.AddLinkedAutocomplete(ctrlСоздательГеопункта, ctrlСоздательГеопунктаСтрокой, typeof(Организация), "ORGANIZACIJA",  true, true, licenceFunc);
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}