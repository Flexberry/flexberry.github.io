--- 
title: Performance interaction in Web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_form-interaction.html 
lang: en 
autotranslated: true 
hash: 623abc3a71d28b0cfefe3a1b76fbc2336266845a0637ed92bd076e6624599151 
--- 

## the Script open the page to edit from the list 

Address edit page is determined by the setting of [WebObjectListView](fa_web-object-list-view.html).`EditPage`. Page parameters are sent through the address bar: 

* `Mode` - mode open pages can be `readonly`. 
* `ReturnUrl` - the return address contains the address of the page, but the address WOLV, which was caused by the edit page. 
* `DataObject` - passed with the POST request (passed via Request.Form["DataObject"]). Stores the serialized XML instance of the new object (see [the article is the Example of opening a web edit form with the transfer object with some fields already filled](fa_open-editform-custom-object.html)). 
* `PK` is the primary key of editable object. 

{% include note.html content="If the object is new, when you first open `PK` not sent, however, when you first save the object (without return to the list page), this option appears in the address bar." %} 

The edit page is inherited from [BaseEditForm](fa_base-edit-form.html). 

When the page loads triggered several virtual methods [BaseEditForm](fa_base-edit-form.html) that you can override at the [edit page](fa_editform.html): 

* `PreLoad()` - method is called very first in the method `Page_Load()`. 
* `PrepareDataObject` - preparation of the site for unloading the page. In this method (BaseEditForm) is proofreading `PK` object, or deserialize from a POST request (XML). It is not recommended to override this method. 
* `PreApplyToControls()` - method is called before the "smearing" of the data object at the controls of the page. At the time of calling this method the object is already deducted from the database (or when PostBack'e values are already stored in the object), you can perform some additional manipulation of the object before its value gets to the page. 
* `AfterApplyToControl()` - method is called after the "smearing" of the data object at the controls of the page. At the time of calling this method, all controls on the page have been updated in accordance with the data object. Also, the method is called after saving the object (see below). 
* `PostApplyToControl()` - called just for `AfterApplyToControls()` 
* `PostLoad()` - the latest is called when the page loads. 

## Script save the object 

Sequence of operation methods pressing `Сохранить`: 

* javaScript method `saveBtnClickHandler` (file: ~\shared\script\jquery.icsEditForm.js) 
* Start ASP.NET validators 
* Launch installed application razrabotciki event handlers save 
* Manual sending PostBack'and on the server 
* Server virtual method `SaveBtn_Click` - handler of clicking the button save `BaseEditForm`. 
* `PreSave()` - virtual method that is triggered before the actual preservation of the object. Returns a `bool`, if the return value `false`, the object will not go away for safekeeping. 
* `SaveObject()` - virtual method, which is directly to the preservation of the object. It is not recommended to override this method without calling the base method. 
* `PostSaveObject()` - virtual method that is triggered after saving the object. At the time of the method call object sent to the database, and therefore, triggered the business server updates this object and all the changes happening to the object in business server now available. Immediately after calling this method, there is a "smearing" of the data object at the controls. 
* `AfterApplyToControls()` - the method described in the block `Сценарий open the edit page with списка`. 
* If at the time you call save, the object was new (i.e. it was not in the database), then redirect to the same edit page, but the address bar is added `PrimaryKey` object. 

{% include note.html content="If the page does not pass validation, the data on the server are not sent at all. This reduces the load on the server, however, it is understood that the server-side handler for the save button does not work if the page has not passed validation." %} 

After you save object SaveObject method on the base form editing (BaseEditForm) method is called [WebBinder -] (fa_web-binder.html) ApplyDataToControls, it for controls that implement ISaveActionCompatible reset flag IsSaveNow, well, all the controls to the appropriate fields of the data object are filled in current values from the object. 

If there was an unhandled exception (for example vybrannoi in [business server](fo_bs-example.html)), the execution of the method SaveObject interrupted, and the [WebBinder -] (fa_web-binder.html) ApplyDataToControls not be invoked. 
The controls will not be reset the flag IsSaveNow is cerejota the fact that graphedit will not deal with the status of strings that match detalam and leave all rows status WGEStatus.None 
and the client code is not sending groupedit detaily with this status back to the server (it sends only new, edited or deleted), what if after an exception the user has not changed anything in the lines groupedit, and re-sent the form to save, then the data object will not be datalow (they just will not be sent from the client). 

To solve this problem, the basic form of the edit catches exception when the object is saved, and in case of their occurrence invokes a method [WebBinder-a](fa_web-binder.html) ApplySavingFlagToSaveActionCompatiblecontrols, which will reset the flag IsSaveNow the controls implementing ISaveActionCompatible, then the basic form edit forwarding exception on. 
Inside the method SaveObject, it looks like this: 

``` csharp
try
{
   ds.UpdateObject(ref dObj);
   PostSaveObject();
}
catch
{
   wb.ApplySavingFlagToSaveActionCompatibleControls(Controls, View, false);
 
   throw;
}
``` 

If the application code is not used technology, and applied basic edit form, override the method SaveObject, be sure to have similar code to handle exception when saving the object. 

## LookUp's 

### the Script open a page with a list to LookUp 

Settings [LookUp](fa_lookup-overview.html): 
* `LimitFunction` - the limitation of the display to open the LookUp page data. 
* `LookUpFormURL` - address of the page that visaversa [LookUp](fa_lookup-overview.html). By default, looks at `LookUpForm`. 

{% include note.html content="the page should be [WOLV](fa_web-object-list-view.html) with an installed setting `LookUp = true;`, and should be indiscriminately transferred through the address bar parameters. For details, see [LookUpForm](fa_lookup-form.html)" %} 

When you open the page on the [LookUp](fa_lookup-overview.html) is passed the address of the web control from which it was initiated (page and the location of the control). Also passed to the PrimaryKey of the selected object (if it was at the time of opening [LookUp'a](fa_lookup-overview.html)) to illuminate it on the open page. 

### the Script you select in the wizard for the LookUp page 

When you select any object on the LookUp list page returns the PrimaryKey of the object at the address that is sent when you open a LookUp page. 

After returning a value list on a web form, if necessary, populates the fields selected in the wizard. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}