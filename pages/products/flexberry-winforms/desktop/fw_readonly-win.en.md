---
title: Lock changes on the forms
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, forms, and controls
summary: Lock the form and individual fields for changes, conditions, methods
toc: true
permalink: en/fw_readonly-win.html
lang: en
autotranslated: true
hash: 83c0fd38a28e2de259a2be17198159754b4a3d90833819a67d14e912010a134f
---

## Lock form for changes

The object is opened in read-Only (ReadOnly) cannot be changed by the user, the data stored in the object will be read-only.

Mode `ReadOnly` may need in different situations, for example:

* One user opened the object for editing, at this time another user tries to open the object for editing.
* The user does not have rights to change any field of an object, or the object itself, but has the right to view the data.

Mode can be divided into 3 components:

* Lock user interface
* Lock a data object in application memory
* Lock a data object in the database

### Lock user interface

If the user cannot make changes to the object, it must realize it at the time of opening the edit form. If the object is currently unavailable because another user is advisable in the prevention of type "Object locked by another user. You want to access object read-only? Yes / No". If the user still wants to open it in ReadOnly mode, you must lock the controls to edit the fields of the object. You also need to hide the buttons `Сохранить` and `Сохранить and закрыть` (only for the case of full lock, and not lock the individual fields of the object).

To lock the user interface in Windows-based applications use the class [EditManager](fw_editmanager.html).

EditManager allows you to either lock the object completely, or to block only some of its fields.

__Important__: it Should be noted that `EditManager` blocks only those fields of the object that was in it.

* Full lock of the object through the EditManager is the setting of the

```csharp
 EditManager.ReadOnly = true;
```

* Partial lock object (lock individual fields) by calling [EditManager.SetReadonlyFlagProperties](fw_editmanager.html).

### Locking a data object in application memory

Lock object in memory by calling the object method `DataObject.LockObject(Key)` where `Key` a key lock type `Guid`. To unlock the object can __only this key__ using the method `DataObject.UnLockObject(Key)`.

When you call a method `LockObject` lock in the database is not sent.

An example can be found in the article [Options object is opened read-only](fo_read-only-object.html).

### Locking a data object in the database

To lock using a supported technology [Service locks](fo_lock-service.html).

Lock object in the database iron protects the object against changes of the object by other users. Creates a record in the table `STORMNETLOCKDATA` and while it exists there, change the object, only the user that has locked the object.

An example can be found in the article [Service locks](fo_lock-service.html).

## Lock to edit individual form fields

We set the following goal: "to Make different applications available to different fields on the same form."
Perhaps another solution to this problem:

* Depending on what apps you have running, you can lock a field for editing with [EditManager.SetReadonlyFlagProperties](fw_editmanager.html).
* Identify a running application at startup by writing a specific value in a [static field](http://msdn.microsoft.com/library/98f28cdx.aspx) public class.

To demonstrate the solution to this problem below is the solution of the following problem: "There is a list of employees. HR staff can edit the fields "name", "date of birth" and "Address registration", and the heads of enterprises to evaluate the "Performance" employees."

### Work in Flexberry Tool

In Flexberry was created the class diagram.

![Class diagram](/images/pages/products/flexberry-winforms/desktop/class-diagram_-workers.jpg)

And then defines user applications and the generated code.

### Work with program code

In public the applications you develop the class defined by the static field.

```csharp
    public enum tWorkerShowType //an enum type to specify the running application 
    {
        Unknown, //it is not installed 
        ToHead, //head 
        ToPersonnelOffice //the personnel Department 
    }
    public class Сотрудник : ICSSoft.STORMNET.DataObject
    {
        public static WorkerShowType CurShowType = tWorkerShowType.Unknown; //static box to specify the running application 
        //... 
    }
```

In the application code determine the value of this static field.

```csharp
static void Main()
{
    try
    {
        Сотрудник.CurShowType = tWorkerShowType.ToPersonnelOffice; //define the value of a static field 
        //... 
    }
    //... 
}
```

An overridable method [Edit](fw_form-interaction.html) on the edit form.

```csharp
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
  base.Edit(dataobject, contpath, propertyname, tag); //call the base method 
  if (dataobject != null)
    {
      switch (Сотрудник.CurShowType)
        {
          case tWorkerShowType.ToHead: //if running the Manager application 
            EditManager.SetReadonlyFlagProperties(
            true, new string[] { "Name", "Dataromance", "Adresboek" });
            break;
          case tWorkerShowType.ToPersonnelOffice: //if the application is running the personnel Department. 
            EditManager.SetReadonlyFlagProperties(true, new string[] { "Performance" });
            break;
          case tWorkerShowType.Unknown: //if not specified the type of application 
            MessageBox.Show("You have not set the parameter, whose name was running form.");
            break;
        }
    }
}
```

{% include note.html content="Call `base.Edit(...)` must precede the definition of the fields available for editing." %}



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}