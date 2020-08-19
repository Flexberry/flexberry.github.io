---
title: Performance interaction in Windows applications
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforns, Forms
summary: Events, methods, object processing, the forms cache and access the system credentials, exceptional cases.
toc: true
permalink: en/fw_form-interaction.html
lang: en
autotranslated: true
hash: e4fa8572f681eb3ec27a577e1ffbe26535bde7caa0bcc50d9431cad5cd455786
---

Performanoe interaction is considered in the context of what events are happening, what methods are called, what exactly is checked and is going on at the same time, what objects and how it is processed, describes how to work with the forms cache and access the system credentials specified exceptional cases.

## Script open edit form from the list

### The event edit list form

* List forms are inherited from `BaseWinListStandard` or `BaseWinListHierarchical`. They differ in that in the first case the form control is [ObjectListView](fw_objectlistview.html), and in the second case, use [ObjectHierarchicalView](fw_editform.html). The event edit form come from one of these controls. Subscribing to this event is performed by the method `protected void prv_AttachObjectListView( ICSSoft.STORMNET.Windows.Forms.ObjectListView objectListView1 )` which is called from the constructor of the underlying list form. In the same method that initializes the properties `ListControl` basic list form.

![Schema inheritance list of forms](/images/pages/products/flexberry-winforms/forms/primer11.jpg)

* `BaseWinList`: event Handler `ObjectListView.EditObject` calls a method `protected virtual void objectListView1_EditObject(object sender, ICSSoft.STORMNET.Windows.Forms.GroupEventArgs e)`. Next is the call `protected virtual void prv_EditData()`, and hence `protected virtual void prv_EditData( ICSSoft.STORMNET.Windows.Forms.ObjectListView ol )`
* `BaseWinList.prv_EditData`: takes all selected objects, if more than one, it asks the question about the need to open to edit all of these objects. In the case of positive user response is called `OnEditEvent( "", ., "" )`, which triggers `EditEvent` on the form `BaseWinList`.
* `BaseIndpdList` was signed at the event `EditEvent` its dependent form, in the method `public virtual void Activate( object tag )`. This method involved in EBSI script, which runs on an independent list form. Ie this method gets triggered when the list form was launched.

![Independent list form](/images/pages/products/flexberry-winforms/forms/ListFormEBSD.jpg)

* In the event handler for the edit with the dependent form is called an edit event on the independent form, which is used for EBSD diagram.

### Receive form editor

* When called `EditEvent` on an independent list form included in the work of the interpreter EBSD script. This event results in a call to the method `GetEditor` independent list form. This method is overridden in the generated independent form. It returns the type of form editor on the basis of information about the editable object.

__Is required to return a type independent, because it is the subject of the work of the interpreter of scripts.__

* After receiving a form type of edit, then begins the work with the forms cache edit: `EditFormCache`. First checks of this form.
* If the form is not in the cache, the next step of the script will be `System.Activator.CreateInstance(editFormType)` and the room created the form in `EditFormCache`.
* If the form is found in cache, then it immediately executes the next step of the script.

### Start the form editor

* Form editor is started by calling the method `Edit` independent.
* If the form was taken from the cache earlier, this method is called without starting a new shell scripts and signing at the event.
* If the form is started for the first time, it will start a new shell script run form. The script for this form is obtained by the method invocation `GetScript()`, which always is overridden in the generated code. In addition, you subscribe to the event save (`SaveEvent`) and event cancellation (`CancelEvent`) raise independent.
* Method `Edit` independent edit form:
* Checks permission to open this form
* If the parameter `propertyname` is not empty, then it checks for the presence of such properties [`DataObject`](fo_data-object.html) the object passed to the edit.
* If the property is found, but this property in the object == `null`, then the edit is undone, the method terminates.
* If the property was found and != `null`, we will have to edit this workman the object instead of the passed parameter `dataobject`
* If the property is not found, then continue to work with the object passed by parameter `dataobject`
* If the form is raised for the first time (it is determined that no object data (`DataObject`) in independent form),
* Check authority to the editable object (`FullControl` or `Read`). If no such rights, then you get a form with `UnauthorizedAccessException` and edit the object on this end.
* If there is no right to `Insert` or `Update`, the object will open without lock - read-only.
* If the independent form of a data object (`DataObject`) already exists and its primary key is the same as what came on the edit, it is a call `Form.Select()` and exit from the method.
* If the independent form of a data object (`DataObject`),
* A blank in the dependent form, call it's method `Edit` with empty parameters
* Installs the dependent form status message download
* The method is called the independent form: `PrepareDataObjectForEdit(dataobject);` that is overridden in the generated code
* Copies of the edited object method `DataObject.CopyTo()` and this copy is assigned to a property `DataObject` independent edit form. This property will be used to validate the need to ask a question about the preservation of the object when the form is closed.
* Editable object is passed to the method `Edit` dependent form.
* Exhibited a status message dependent on the form of "Ready".
* Method `Edit` dependent edit form:
* Called `protected virtual void prv_Edit( Storm.DataObject dataobject, string contpath, string propertyname )`, which is assigned `EditManager.DataObject`, exhibiting `Caption` to form, depending on the status of the edited object and then called `EditManager.Change( false );`
* If `DataObject` in dependent form (which is a reference to `EditManager.DataObject`) != `null`, called event `ICSSoft.STORMNET.Windows.Forms.Desktop.GlobalWinformEvents.OnIntEditFormDataLoaded(this, EventArgs.Empty);`
* Method is called `Show();`
* If `DataObject` dependent on the form != `null`, called event `OnDataObjectOnFormLoaded(new EventArgs());`

## The script save the object

* `BaseIndpdEdit` - independent base form editing. In its constructor it calls a method `GetDpdForm();`, which is overridden in the generated independent form. This method should return an instance of dependent forms.
* The dependent form is assigned to a property `Editor` basic independent form, and in this moment the method is called `private prv_AttachSystemEvents void()` in which the event subscription dependent forms (closing, saving, Otomi, etc.).

![Edit form](/images/pages/products/flexberry-winforms/forms/EditFormEBSD.jpg)

* Edit form works on the script:
The event of saving independent on the edit form.
* First option: button on the toolbar. First, triggered handler of the buttons on the toolbar, which causes `OnSaveEvent` dependent form. This event signed the handler in the independent, which first calls a method `protected virtual void OnSave(SaveEventArgs e)`, and then if not happened `m_bFailedSave` and dependent form is not in the closure of (`ClosingMode`), then the reproduction object data with the dependent form in the property `DataObject` independent.
* Another option: processing of the form is closed by clicking on the "X".
* In the event handler, the closure of dependent forms --a miracle occurs-- does the data object that was saved in an independent edit form and the data object dependent form.
* If the data object dependent on the form is not `null` and is not blocked, then turns out an array of distinct properties by the method `Information.GetAlteredProperyNames()`, including checking on the different properties of detailov.
* If the item's status to dependent form `Created` have equal or differing object-independent properties form, in the case `зависимая_форма.ClosingMode == false` method is called `public virtual ICSSoft.STORMNET.UI.DialogResult PromtUserForActionAtClose();` dependent editing form.
* `PromtUserForActionAtClose()` shows `MessageBox` with `MessageBoxButtons.YesNoCancel`.
* Accordingly to the answers of the user triggered events are independent of the edit form:
* `Yes`: `OnSave();` `e.Cancel = m_bFailedSave;`; i.e. in the overridden `OnSave` you can put `m_bFailedSave = true;` and closing the mold will not occur.
* `No`: `OnCancel();`, then call `protected virtual void prv_ApplyChanges(ICSSoft.STORMNET.DataObject dataobject, ICSSoft.STORMNET.DataObject dataobjectcopy)` to roll back changes that were made in the object editable on the dependent form.
* `Cancel`: simply cancel the form is closed: `e.Cancel = true;`
* If the modified properties and the status is not `Created`, then called `OnCancel(); e.Cancel = false;`
* If the cancellation of the closure had not happened, then at the end of the method resets the lock for this object.
* Method `OnSave` independent edit form:
* Checks `NotNull` properties of the edited object data.
* If there are empty properties, the method is called virtual void `public DisplayNullPropertiesWarning( string&#0091;&#0093; captions )` dependent form, exhibited a flag `m_bFailedSave = true;` and calls another method `public virtual void FailedSave( Exception e )` dependent forms, exhibiting a status message dependent form error saving. Returns from the function.
* If empty mandatory fields exist, then the flag `m_bFailedSave = false;`, put a status message dependent on the implementation of the storage process, check the editable status on the dependent form of the data object.
* Called `SaveEvent` independent edit form
* If the form is not closed (`ClosingMode`) and `m_bFailedSave != false`, dependent form exhibited a readiness status, otherwise the error status when you save and exits the function.
* If the editable object to perform `SaveEvent` was able `Created`, then it is lock.
* Method is called `public virtual void Edit(Storm.DataObject dataobject, string contpath, string propertyname)` dependent editing form. This happens for reinitializing the form editor, because when you save the object could be changed in the business server.
* The list form in your script was signed on the event of saving.
* The passed event parameter is edited `DataObject` is transferred to a special Business-service wrapper around a data service to update the object.
* In case of error when saving happens, the method call `FailedSave` independent edit form
* In case of successful saving will be called method `public virtual void Edited(DataObject dataobject, string contpath, string propertyname)` independent list of shape, which causes `public virtual void Edited(Storm.DataObject dataobject, string contpath, string propertyname)` dependent form, which will cause `protected virtual void prv_Edited( ICSSoft.STORMNET.DataObject dataobject )`, and this function adds an object to [ObjectListView](fw_objectlistview.html) on this form.

## The script to open list forms in lucap

* Scenario select masters lucap form

* Performance interaction in Flexberry Platform
To call list form, in which the elements are edited, you can use the OnEdit and to obtain a processed object in a method Edited.

In order to raise the modal form can be done, for example, as follows:

```csharp
(form as ICSSoft.STORMNET.UI.BaseIndpdList).SaveEvent += new ICSSoft.STORMNET.UI.SaveEventArgsHandler(OnReturnFromList);
(form as ICSSoft.STORMNET.UI.BaseIndpdList).Edit(dobj, "", LookUpProp, lf);
DPD_frm = (System.Windows.Forms.Form) (form as ICSSoft.STORMNET.UI.BaseIndpdList).EditInitiator;
DPD_frm.Hide();
DPD_frm.ShowDialog();
```

Although, from a technology point of view, it would be correct to call the form on OnEdit, and in the dependent form (the form which I want to raise), to override babymed Edit that looks like this:

```csharp
/// <summary> 
/// Start editing the properties of the data object (actually a call to lookup) 
/// </summary> 
/// <param name="dataobject">data object</param> 
/// <param name="contpath">some way on the form-the initiator to identify the object in the case when the form is editing multiple data objects</param> 
/// <param name="propertyname">property name</param> 
public virtual void Edit(Storm.DataObject dataobject, string contpath, string propertyname, object tag)
{m_objDataObject = dataobject;
m_sContPath = contpath;
m_sPropertyName = propertyname;
m_bLookupMode = true;objListView.Operations.Up = true;
if( tag is ICSSoft.STORMNET.FunctionalLanguage.Function && tag != null )
{objListView.LimitFunction = ( ICSSoft.STORMNET.FunctionalLanguage.Function )tag;}this.Show();
return;}
```

For example, instead of this.Show(); you can write this.ShowDialog();

## Cm. also

[Performanoe interaction in Web-applications](fa_form-interaction.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}