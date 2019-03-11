--- 
title: operations Management lists depending on user rights 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, controls, list 
summary: visibility configuration and deletion of user settings 
toc: true 
permalink: en/fw_objectlistview-rights.html 
lang: en 
autotranslated: true 
hash: 58881a4aaa5a72dc899643f82086ec06c4412931b9a3cf25bbbfa8b1f4f7f178 
--- 

LV will automatically set the accessibility of edit operations depending on the rights. The following rules apply: 

* In the absence of the rights to INSERT object on the list is unavailable the operation "Create" and "Create template". 
* In the absence of rights to DELETE the object is not available, the operation "Delete". 
* When (not authorized to UPDATE the object and you have permission to READ) the operation to be called "Viewing", and the object is opened only for viewing. 

If the list of several types of available operations are displayed depending on the selected object. 
The current setting can be read using the properties `ObjectListView.RightSet`, and shift availability to monitor events `ObjectListView.RightSetChanged`. 

## configure the visibility of columns in ObjectListView 

Let it be required, for example, depending on user permissions to set visibility of some column in [ObjectListView](fw_objectlistview.html). 

Change `objectListView1.Columns` during operation of the application. 

One of the solutions to the tasks can be use `ObjectListView.View.Properties`. 

```csharp
public class WinformC__ПользовательПриложенияL : ICSSoft.STORMNET.UI.BaseWinListStandard, IIS.TryAccessSystem.DPDIC__ПользовательПриложенияL
{
	public WinformC__ПользовательПриложенияL() //the constructor of the form 
	{
		this.InitializeComponent();
		this.prv_TuneAdditionalObjectListViews();
		// *** Start programmer edit section *** (Form Constructor) 
		//... 
		if (!ICSSoft.STORMNET.RightManager.AccessObjectCheck(curObject, "Update", false)) //check user permissions 
		{
			var columnInfoList = (from ICSSoft.STORMNET.PropertyInView mi in objectListView1.View.Properties
								  where mi.Name == "Loginpagetitle"
								  select mi).ToList(); //search for the desired property 
			if (columnInfoList.Count == 1) //check that the desired property is found 
			{
				ICSSoft.STORMNET.PropertyInView columnInfo = columnInfoList[0];
				int cINumber = objectListView1.View.Properties.ToList().IndexOf(columnInfo);
				objectListView1.View.Properties[cINumber].Visible = false; //set Visible to false 
			}
		}
		//... 
		// *** End programmer edit section *** (Form Constructor) 
	}
	//... 
}
``` 

{% include note.html content="the Specifics of array `ObjectListView.View.Properties` due to the fact that `PropertyInView` is [not a class but a structure](http://generally.wordpress.com/2007/06/21/c-list-of-struct/)." %} 

{% include important.html content="the visibility setting of the column occurs in the form designer." %} 

## Deleting user settings in ObjectListView 

To remove user settings through the settings window list. Please click on the `Сбросить settings...`, after confirmation of the operation settings will be deleted from the database. However, the changes will not appear immediately, but only after re-opening the list. This feature stems from the fact that the default values (customize columns) are assigned only in the initialization list (startup control).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}