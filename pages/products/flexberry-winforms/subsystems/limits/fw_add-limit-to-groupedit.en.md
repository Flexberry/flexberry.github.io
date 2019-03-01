--- 
title: the Imposition of limitations on GroupEdit 
sidebar: flexberry-winforms_sidebar 
keywords: 
summary: 
toc: true 
permalink: en/fw_add-limit-to-groupedit.html 
lang: en 
autotranslated: true 
hash: 0cf386238c63421b51f8afcff17b61bc6c01e45316bb4c6ca32cac794d7d827a 
--- 

## Delegate hiding rows in GroupEdit 

For imposing simple constraints on the entries shown in [GroupEdit](fw_group-edit.html), you can use a delegate to hide the rows of the control. This delegate defines the visibility of each record in [GroupEdit](fw_group-edit.html). 

```csharp
public IsObjectVisibleDelegate IsObjectVisible;
public delegate bool IsObjectVisibleDelegate(DataObject dataObject);
``` 

## an Example of using a delegate hide rows 

Let the form a [GroupEdit](fw_group-edit.html) `geВизитКлиентаВБанк` containing records `ВизитКлиентаВБанк` and `geВизитКлиентаВБанк` you want to display only those records that contain a blank value in the field `ЦельВизита`. 

For this task you need the following: 

1) to Determine the delegate (it returns `true` if the field `ЦельВизита` have an input of type record `ВизитКлиентаВБанк` not filled): 

```csharp
private bool IsObjectVisibleMyImplement(DataObject dataObject)
{
	return string.IsNullOrEmpty(((ВизитКлиентаВБанк)dataObject).ЦельВизита);
}
``` 

2) to Appoint a delegate `geВизитКлиентаВБанк`: 

```csharp
public class WinformБанкE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.LookUpEditManager2.DPDIБанкE
{
	//... 
	public WinformБанкE()
	{
		//... 
		this.geВизитКлиентаВБанк.IsObjectVisible = IsObjectVisibleMyImplement;
		//... 
	}
}
```


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/