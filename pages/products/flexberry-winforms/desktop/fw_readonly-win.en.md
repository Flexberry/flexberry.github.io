--- 
title: "read Only" in Windows applications 
sidebar: flexberry-winforms_sidebar 
keywords: DataObject (object data) Windows UI (forms) 
summary: is Described, which implies a "read Only" in the app. Methods of blocking the UI with EditManager, listed as lock object in application memory, and thereby achieving a lock in the database 
toc: true 
permalink: en/fw_readonly-win.html 
lang: en 
autotranslated: true 
hash: 888345665728ccd351b916734a1f5e2d1f1d2b3569809cd115431a405d701aac 
--- 

The object is opened in "read Only" (Read-only) cannot be changed by the user, the data stored in the object will be read-only. 

Mode `ReadOnly` may need in different situations, for example: 

* One user opened the object for editing, at this time another user tries to open the object for editing. 
* The user does not have rights to change any field of an object, or the object itself, but has the right to view the data. 

## Components of ReadOnly 

Mode can be divided into 3 components: 

* Lock user interface 
* Lock a data object in application memory 
* Lock a data object in the database 

### Lock user interface 

If the user cannot make changes to the object, it must realize it at the time of opening the edit form. If the object is currently unavailable because another user is advisable in the prevention of type "Object locked by another user. You want to access object read-only? Yes / No". If the user still wants to open it in ReadOnly mode, you must lock the controls to edit the fields of the object. You also need to hide the buttons `Сохранить` and `Сохранить and закрыть` (only for the case of full lock, and not lock the individual fields of the object). 

To lock the user interface in Windows-based applications use the class [EditManager](fw_editmanager.html). 

EditManager allows you to either lock the object completely, or to block only some of its fields. 

__Important__: it Should be noted that EditManager blocks only those fields of the object that was in EditManager'e. 

* Full lock of the object through the EditManager is the setting of the 

```csharp
 EditManager.ReadOnly = true;
``` 

* Partial lock object (lock individual fields) by calling [EditManager.SetReadonlyFlagProperties](fw_editmanager.html), more details can be read in [this article](fw_different-applications-and-fields.html). 

### Lock a data object in application memory 

Lock object in memory by calling the object method `DataObject.LockObject(Key)` where `Key` a key lock type `Guid`. To unlock an object can "only this key" method on the `DataObject.UnLockObject(Key)`.

When you call a method `LockObject` lock in the database is not sent. 

An example can be found [here](fo_read-only-object.html). 

### Lock a data object in the database 

To lock using a supported technology [Service locks](fo_lock-service.html). 

Lock object in the database iron protects the object against changes of the object by other users. Creates a record in the table `STORMNETLOCKDATA` and while it exists there, change the object, only the user that has locked the object. 

An example can be found [here](fo_lock-service.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}