--- 
title: the Imposition of limitations on GroupEdit 
sidebar: flexberry-winforms_sidebar 
keywords: 
summary: 
toc: true 
permalink: en/fw_add-limit-to-groupedit.html 
lang: en 
autotranslated: true 
hash: f47efea34fa77e1b557ef113797c02d36863b491126cbfa86a610cc6d0c0f80e 
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


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}