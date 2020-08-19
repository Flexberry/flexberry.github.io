---
title: ComboLookup
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, Lookup
summary: Properties, example of use, features generation
toc: true
permalink: en/fw_combo-lookup.html
lang: en
autotranslated: true
hash: f4916c632530138173b07465de9e4a3e040d919b5455d73bf4862006fa3261f7
---

Control to select master from the combined [list](fw_objectlistview.html) ComboBox.

Main properties:

* `DataObjectType` – type artisan object.
* `MasterPropertyName` is a property of the data object that is being edited ComboLookup.
* `ComboPropertyName` – craftsman of the object to be displayed in the list.
* `CachedData` – data is read from the database each time you open the list, or cached.
* `Limit` – the limit on a list of user controllable features. It should be noted that the property is of type `FunctionForControls`.

```csharp
comboLookup1.CachedData = true;
comboLookup1.ComboPropertyName = "Name";
comboLookup1.DataObjectType = typeof(IIS.КошкиСЛапами.Кошка);
comboLookup1.MasterPropertyName = "Breed";

Function lf = FunctionBuilder.BuildLike<Порода>(x => x.Название, "Persian%");
comboLookup1.Limit = new FunctionForControls("ПородаL", lf);
```

## Change type from Default to lucapa Combo

If the form already exists when you try to change the type from an existing lucapa change __not used__.

So the changes into effect you need to manually remove all the code associated with the control, of the code of the page and start regeneration.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}