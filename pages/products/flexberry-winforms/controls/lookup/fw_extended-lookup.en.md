--- 
title: Lucap predictive 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, LookUp, ExtendedLookUp 
summary: Description, properties, and events ExtendedLookUp 
toc: true 
permalink: en/fw_extended-lookup.html 
lang: en 
autotranslated: true 
hash: 63e2bbd51dbf831f29a18a8b7a0e90c002c2ea2f161e5db7d873d8aea1d3d7d3 
--- 

`ExtendedLookUp` is lucap predictive. Is the Assembly `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll` 

## ExtendedLookUp as lucapa 

How to install `ExtendedLookUp` as lucapa described in the article [Arbitrary control as lucapa](fo_custom-lookup.html). 

If you want to install `ExtendedLookUp` as lucapa in [GroupEdit](fw_group-edit.html), then the corresponding `GroupEdit` need to set the property `EnableValueDisplayResponsibility` in `true`. 

## Events 

PstrfValueSelected` event is triggered when you press Enter and close the list. 

## Properties 

### AutoCompleteBox.OrderColumns 

One of the components of `ExtendedLookUp` `AutoComleteBox` is responsible for displaying prompts in the input switched mode `AutoOpenListForSuggestions`. 

Property `AutoCompleteBox.OrderColumns` determines the columns on which sorting will be made when determining the order of elements in the displayed tooltip. 

```csharp
ctrlКлиент.AutoCompleteBox.OrderColumns = new ColumnsSortDef[] {new ColumnsSortDef("Registration", SortOrder.Asc) };
``` 

{% include note.html content="Task `AutoCompleteBox.OrderColumns` only possible in code." %} 

### MaxLength 

Property `MaxLength` control `ExtLookUpTextControl` responsible for the maximum length of user-entered text. 

It should be noted that in the code property `Text` you can specify a value whose length is greater than length the value specified by the property `MaxLength`, i.e., the property only affects text entered into the control at run time. In other words for LookUp (and using the proposed control option) can be selected text in excess of `MaxLength`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}