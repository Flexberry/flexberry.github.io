--- 
title: Delete empty rows when saving 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, GroupEdit, GEEmptyDetailRemover 
summary: the connection Rules component to delete empty rows of datalow while maintaining 
toc: true 
permalink: en/fw_ge-empty-detail-remover.html 
lang: en 
autotranslated: true 
hash: dbae36218b3fcda2b061dfbddf91483fb1ada27548d49c0fa57222b6713fdccd 
--- 

`GEEmptyDetailRemover` - extension component [GroupEdit](fw_group-edit.html) which allows you to remove blank lines from `GroupEdit` when saving. When you save `GroupEdit` with empty lines you may see a message on blank fields (blank in this line). 

{% include important.html content="`GEEmptyDetailRemover` not supplied as standard Flexberry Winforms." %} 

## Connection GEEmptyDetailRemover 

1) In the dependent form class defines an object class `GEEmptyDetailRemover`: 

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
	public IIS.AMS02.GEEmptyDetailRemover gedr = new IIS.AMS02.GEEmptyDetailRemover();
	//... 
}
``` 

2) are Specified in the form designer `GroupEdit`, for which it is necessary to remove empty rows: 

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
	public WinformC__ПокупательE()
	{
		this.InitializeComponent();
		m_sCaption = "Buyer";
		this.prv_TuneLookupInformations();
		// *** Start programmer edit section *** (Form Constructor) 
		//... 
		#region возможность удаления пустых детейлов из GE
		gedr.AddGroupEdit(Покупки);
		gedr.AddGroupEdit(Продажи);
		#endregion возможность удаления пустых детейлов из GE
		//... 
		// *** End programmer edit section *** (Form Constructor) 
	}
	//... 
}
``` 

3) In independent form, the method [OnSave](fw_form-interaction.html) calls the function delete empty datalow: 

```csharp
public class C__ПокупательE : ICSSoft.STORMNET.UI.BaseIndpdEdit
{ 
	// *** Start programmer edit section *** (C__, Pokupatela CustomMembers) 
	protected override void OnSave()
	{
		//... 
		#region удаляем пустые детейлы
		(Editor as WinformC__ПокупательE).gedr.RemoveEmptyDetails();
		#endregion удаляем пустые детейлы
		//... 
	}
	//... 
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}