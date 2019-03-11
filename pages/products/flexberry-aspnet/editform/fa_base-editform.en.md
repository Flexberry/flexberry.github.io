--- 
title: BaseEditForm 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_base-edit-form.html 
lang: en 
autotranslated: true 
hash: 050dd869ee72721202306163f3806850114e41b10402e8c12dde262a287dd6c8 
--- 

From **BaseEditForm** inherit all the [web editor](fa_editform.html). This form is not technological, it can be changed depending on the requirements of the project. 
It was created for reuse, code structure and implementation of a uniform logic of edit forms. 

## Properties 

|Property|Description| 
|---|---| 
| DataObject | edit the data object.| 
| DataServiceType| Type of data service.| 
| IsObjectCreated| if the object Is new. Returns true when the object is created by pressing "Add" or "Create". In ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.Preload() always returns false! | 
| IsObjectPrototyped| whether the [prototype](http://wiki.ics.perm.ru/DataObjectPrototype.ashx). Returns true when the object is created by clicking "Create". In ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.Preload() always returns false!| 
| LockKey| Key [lock](fw_readonly-win.html) of the object. By default, the lock is on the name of the current authenticated user.| 
| LockObject | whether to lock the data object. By default, a lock is enabled when the specified primary key ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.PK, when you turn off the "read only" ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.ReadOnly and only for authorized users. The requirement of autorizovanem users due to the fact that the system lock key ICSSoft.STORMNET.Web.AjaxControls.Forms.BaseEditForm<T>.LockKey default is based on the name of the current authenticated user.| 
| OpenInModalWindow | Open the form in a popup window. The default is determined based on the query parameter ICSSoft.STORMNET.Web.Tools.WebParamController.OpenedInNewWindowParamName added to ICSSoft.STORMNET.Web.AjaxControls.WebObjectListView in the formation of URLs editing (ICSSoft.STORMNET.Web.AjaxControls.WebObjectListView.GetRealEditPage(System.String)) and create a new data object (ICSSoft.STORMNET.Web.AjaxControls.WebObjectListView.GetRealAddPage()). Only checks the presence parameter. | 
| PK| [Primary key](fo_primary-keys-objects.html) editable object.| 
| PluginInitSettings | Settings initialize the plugin edit form.| 
The | ReadOnly | Flag open the form read-only.| 
| ReturnUrl | return Address. The address is always checked on "locality" (under current host) to prevent Open Redirection Attack. If the return address is missing or does not match the host, it returns the address to the root server.| 
| ShowCancelButton | Determines whether to show the Close button. In the base implementation returns true if the ReturnUrl is not empty or open the form in a popup window.| 
| ShowSaveAndCloseButton | Determines whether to show the button "Save and close". In the base implementation returns true if the form is not ReadOnly and ReturnUrl is not empty or open the form in a popup window.| 
| ShowSaveButton | Determines whether to show the "Save" button. In the base implementation returns !ReadOnly.| 
| View | View to load the editable data.| 

## Examples 

* DataObject 

```csharp
<br>var cat = new Дом();
<br>var foots = new DetailArrayOfКвартира(cat)
<br>{
<br>new Квартира { Номер = 10, КоличествоПроживающих = 3, КоличествоКомнат = 2 },
<br>new Квартира { Номер = 23, КоличествоПроживающих = 5, КоличествоКомнат = 4 },
<br>};
<br>cat.Квартира = foots;
<br>DataObject = cat;
``` 

* DataServiceType 

```csharp
var dataServiceTypeName = dataService.GetType().Name;
<br>this.ctrlТипСервиса.Text = this.ctrlТипСервиса.Text + dataServiceTypeName;
``` 

* IsObjectCreated 

```csharp
protected void Button1_OnClick(object sender, EventArgs e)
<br>{
<br>TextBox1.Text = IsObjectCreated.ToString();
<br>}
``` 

* IsObjectProroryped 

```csharp
protected void Button1_OnClick(object sender, EventArgs e)
<br>{
<br>TextBox1.Text = IsObjectPrototyped.ToString();
<br>}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}