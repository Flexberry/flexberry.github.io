--- 
title: editing Mode locking in GroupEdit 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, GroupEdit, lock 
summary: Properties, methods, and features of the overlay locks edit individual lines in GroupEdit 
toc: true 
permalink: en/fw_lock-rows-in-groupedit.html 
lang: en 
autotranslated: true 
hash: 014d8a4b9824420fc7f521c66ec1a3659f87412ecb0b7c38676899e30736ceea 
--- 

[GroupEdit](fw_group-edit.html) supports the ability to lock editing of individual rows. This allows a correct simultaneous editing of objects in `GroupEdit` and on separate forms. 

Locks are managed using the following properties, methods and events. 

* Property `AllowRowLocking` is responsible for enabling edit mode lock. 
* Method `DisableRow(DataObject dataObject)` locks the row with the specified data object for input. Row lock means the impossibility of data entry and allocation in gray. 
* Method `EnableRow(DataObject dataObject)` removes the lock with the string specified with the data object. 
* Method `IsRowDisabled(DataObject dataObject)` allows you to test the blocked string or not. 
* Method `LockDataObject(DataObject dataObject)` locks an object for exclusive editing in GroupEdit (the lock is through the mechanism of `LockService`. Object locks can be taken off the call `UnlockDataObject` or when the form is closed. 
* Method `UnlockDataObject(DataObject dataObject)` unlocks `LockDataObject` object. 
* Event `BeginEdit` when you start editing the attribute of an object. 
* Event `EndEdit` when you are finished editing attribute of the object. 

__Notes:__ 

1.When the form opens, the aggregator it is necessary to impose a lock on all objects detaily due to the following reasons 

* no need to write for the ordered detailov separate treatment, since the change of the ordered attribute also leads to a change of the object and the misalignment with open формой; 
* on the form of the aggregator can be implemented in some business transaction that changes detaily. 

2.When you open the form for editing GE checks locks on every object in GE and in the presence of locking is locking a row for input. 

3.When you start editing a specific data object in GE checks locks on every object in GE and if there is a lock, locks the string and issues a warning to the user. 

4.For the locked input rows is not available the delete operation. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}