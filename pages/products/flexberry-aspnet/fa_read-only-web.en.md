--- 
title: Mode read-Only in Web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: DataObject (object data) Flexberry ASP-NET 
toc: true 
permalink: en/fa_read-only-web.html 
lang: en 
autotranslated: true 
hash: bfd79c4288add608eb049b0b83a850554ce581385358bc094ec5df71bd68856d 
--- 

## Read Only 

The object that is opened in `Только чтение` (`Read-only`) cannot be changed by the user, the data stored in the object will be read-only. 

Mode `ReadOnly` may need in different situations, for example: 

* One user opened the object for editing, at this time another user tries to open the object for editing. 
* The user does not have rights to change any field of an object, or the object itself, but has the right to view the data. 

## Components of ReadOnly 

Mode can be divided into 3 components: 

1. Lock user interface 
2. Locking a data object in application memory 
3. Locking a data object in the database 

### Lock user interface 

If the user cannot make changes to the object, it must realize it at the time of opening the edit form. If the object is currently unavailable because another user is advisable in the prevention of type `Объект locked by another user. You want to access object read-only? Yes\Нет`. 
If the user still wants to access the object in `ReadOnly`, it is necessary to lock the controls to edit the fields of the object. You also need to hide the buttons `Сохранить` and `Сохранить and закрыть` (only for the case of full lock, and not lock the individual fields of the object). 

To lock the user interface in a Web application using a class [WebBinder](fa_web-binder.html). 

WebBinder allows you to either lock the object completely, or to block only some of its fields. 

__Important__: it Should be noted that WebBinder blocks only those fields of the object that __was__ in WebBinder'e and are passed in __view__, and only __server-side controls__ that have properties `ReadOnly` or `Enabled`. 

* Full lock on the object via WebBinder is a method call 

```csharp
  wb.SetReadOnlyForm(this.Controls, this.View, true); 
``` 

where `wb` this instance WebBinder'on a specific page. 

* Another option full lock: set ReadOnly properties in a specific form 

```csharp
 this.ReadOnly = true; 
``` 

* Partial lock object (lock individual fields) is carried out calling the method 

```csharp
 wb.SetReadOnlyProperty(this.Controls, "Name", true); 
``` 

where `ФИО` is the name of the property that you want to lock for editing. 

* Another version of a partial lock - lock a specific control by calling method 

```csharp
 wb.SetReadonlyToControl(ctrlФИО, true); 
``` 

where `ctrlФИО` - control you want to lock for editing.

### Lock object data when opening the edit page 

To lock an object while opening the page is enough to send a GET-request parameter `&mode=readonly`. 

You can see example [add a button in the toolbar or in line WebObjectListView](fa_wolv-add-button.html). 

### Lock a data object in application memory 

Lock object in memory by calling the object method `DataObject.LockObject(Key)` where `Key` a key lock type `Guid`. To unlock the object can tolko this klucom using the method `DataObject.UnLockObject(Key)`. 

When you call a method `LockObject` lock in the database is not sent. 

You can see example [in the article How to access object read-only](fo_read-only-object.html). 

### Lock a data object in the database 

To lock using a supported technology [Service locks](fo_lock-service.html). 

Lock object in the database iron protects the object against changes of the object by other users. Creates a record in the table `STORMNETLOCKDATA` and while it exists there, change the object, only the user that has locked the object. 

An example can be found [in the Service of locks](fo_lock-service.html). 

## Mode "read only" in Windows applications 

["Read only" in Windows applications](fw_readonly-win.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}