--- 
title: Setting the current object when you run the application 
sidebar: flexberry-orm_sidebar 
keywords: DataObject Flexberry ORM example 
summary: object Selection before you start the main application content 
toc: true 
permalink: en/fo_define-default-object.html 
lang: en 
autotranslated: true 
hash: b38c22959a67f811148c3a184678f03b49eab50898d2c69e6671b433c118aaef 
--- 

For example, when ordering you want the shop was already installed. This choice should be provided at the beginning of the program. 

Perhaps another solution to this problem: 

* To select the current object at the beginning of the program, you can use [generated Flexberry form to nehrenovo class](fo_using-not-stored-classes.html). 
* To store the current object, you can use a [static field](http://msdn.microsoft.com/library/98f28cdx.aspx) public class. 
* To set the current object, you can use the solution proposed in the article [setting the value field of the object to create](fo_define-field-created.html). 

A user-level solution would be: have a list of stores when you start the application form appears where you can select the current store. When you create a record of the purchase the current store is automatically inserted. 

## Work in Flexberry Tool 

In Flexberry was created the class diagram. To determine the current shop was created phranky class](fo_using-not-stored-classes.html) `ВыборМагазинаПоУмолчанию` and the generated code. 

![](/images/pages/products/flexberry-orm/data-object/class-diagram_shops.jpg) 

## Work with program code 

In public the applications you develop in class to determine [static field](http://msdn.microsoft.com/library/98f28cdx.aspx). 

```csharp
public class Магазин : ICSSoft.STORMNET.DataObject
{
	public static Магазин CurrentShop;
	//... 
}
``` 

On the form nehrenovo class to suppress the message about saving data on a form, override the method `OnClosing` where to ban the call to the base method. 

To set field values when the form is opened [to override the event `Edit`](fo_define-field-created.html). 

To determine the behavior of the form by clicking on the button. 

```csharp
private void buttonAuthorize_Click(object sender, EventArgs e) //clicking on "Authorize" 
{
	//verify that the store you selected 
	if ((DataObject as ВыборМагазинаПоУмолчанию).ТекущийМагазин != null)
	{		
		Магазин.CurrentShop = ((ВыборМагазинаПоУмолчанию)DataObject).ТекущийМагазин; //save the current store 
		this.DialogResult = DialogResult.OK; //to finish the authorization 
		this.Close();
	}
	else
	{
		MessageBox.Show("You have not selected a store for authorization.");
	}
}
private void buttonExit_Click(object sender, EventArgs e) //clicking the "Exit" button 
{
	this.Close();
}
``` 

To run a form modal to launch the desktop application. Example from class `МенеджерМагазиновDesktop`: 

```csharp
public static bool Run_SetCurrentShopForm()
{
	bool result = false;
	try
	{
		ICSSoft.STORMNET.RightManager.DisableAllRightChecks();
		CheckForIllegalCrossThreadCalls = false;
			IIS.WinUI.Runners.EditFormRunner fr =
				new IIS.WinUI.Runners.EditFormRunner(typeof(C__ВыборМагазинаПоУмолчаниюE),
											"", "Current store", "",
											new ВыборМагазинаПоУмолчанию(),
											typeof(ВыборМагазинаПоУмолчанию), false);
			System.Windows.Forms.Form frm = null;
			frm = fr.RunAndGetForm();
			if (!frm.IsDisposed && !frm.Disposing)
			{
				frm.BringToFront();
				frm.Visible = false;
				CheckForIllegalCrossThreadCalls = false;
				if (frm.ShowDialog() == DialogResult.OK)
				{
					result = true;
				}
			}
		return result;
	}
	catch (Exception)
	{
		throw new Exception("Failed to set the default value.");
	}
}

static void Main()
{
	try
	{
		// *** Start programmer edit section *** (Managermagazin Before authorization) 
		//below code for launching the form 
		if (!Run_SetCurrentShopForm())
		{
			return; //that is, the user did not want to choose the default value, stop working 
		}
		// *** End programmer edit section *** (Managermagazin Before authorization) 
		//... 
	}
	//... 
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}