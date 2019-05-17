---
title: Manager edit
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, forms, controls, binding
summary: Definition, methods, techniques, creating application forms, examples
toc: true
permalink: en/fw_editmanager.html
lang: en
autotranslated: true
hash: b4bd77e6998a2862d4a96f6de9aa7223a4243bbd166385993436d171efbd302c
---

`EditManager` (`ICSSoft.STORMNET.Windows.Forms.Binders.EditManager`, Manager edit) — the special class for continuous binding controls to properties of the data object.

## The Method Of EditManager.Change

If the value of the data object is changed programmatically, to update values on the form are required to inform `EditManager` that the value (or the entire object has changed). This is done by calling the method from `EditManager` `Change()`. If the method is called without any parameters, updates all controls. If parameter (property name), then only the controls,» «tied to the specified property. If the argument is specified the name of the wizard that will update all the controls all the mechanics of the properties of this wizard.

An example of using this method is presented in [features of setting default values](fo_features-dafault-value.html).

Osobennosti:_ method without a parameter or with one parameter, the string will not cause `AfterChangeProperties()`.

## The Method Of EditManager.SetReadonlyFlagProperties

On the edit form, sometimes the required fields that are blocked via EditManager. However, there are situations when you are using `EditManager.SetReadonlyFlagProperties` after you save the object for some time, you unlock and the value can be changed. To prevent such situations, there is a method `AddControlsToForcedReadOnlylist` that block change flag `ReadOnly`. This method works with a list of controls. The list in turn can be edited: to add or remove controls.

```csharp
void EditManager.SetReadonlyFlagProperties(bool readonlyflag, params string[] properties)
```

Pstrfreadonlyflag` parameter defines the value that will be set the ReadOnly property of the controls, edit fields specified in `properties`.

An example of using this method is presented in [Forms and classes on the desktop app](fw_readonly-win.html).

## Method AddControlsToForcedReadOnlyList

```csharp
/// <summary> 
/// Add items to list controls, which EditManager will not change the ReadOnly flag. 
/// </summary> 
/// <param name="controlList">New items.</param> 
public void AddControlsToForcedReadOnlyList(List<Control> controlList)
```

__Example:__

```csharp
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
        {
            base.Edit(dataobject, contpath, propertyname, tag);
            if (DataObject != null)
            {
                EditManager.AddControlsToForcedReadOnlyList(new List<Control>() { ctrlФИО });
            }
        }
```

## Method RemoveControlsFromForcedReadOnlylist

This method allows you to remove the controls added by the AddControlsToForcedReadOnlyList.

```csharp
/// <summary> 
/// Remove elements from the list of controls, which EditManager will not change the ReadOnly flag. 
/// </summary> 
/// <param name="controlList">the Removed elements.</param> 
/// <param name="readOnlyFlag">Flag, which is removed from the list of items you need to put in the ReadOnly property.</param> 
public void RemoveControlsFromForcedReadOnlyList(List<Control> controlList, bool readOnlyFlag = false)
```

## Create edit form object

There are several ways to "manually" create the edit form of the object with the ability to associate it with the properties of an existing data object.

### Linking via program code

1.Place necessary controls on форме;

2.Create an instance of the class `ICSSoft.STORMNET.Windows.Forms.Binders.EditManager` (Manager editing is a specialized class for continuous binding controls to properties of the data object).

Designer required parameter is the data class that is configured in Manager edit.

Primer:_

```csharp
em = new StormNetForms.Binders.EditManager(typeof(CDDD));
```

3.To link controls to properties of the data object.

It is necessary to cause [EditManager](fw_editmanager.html) method `AddControl`. The parameters are passed: the structure `ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct`.

Primary associate a control with a property of the data object:_

```csharp
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtName, "Text"), "Name");
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtCapacity, "Text"), "Volume");
```

4.To set a property `EditManager.DataObject` the data object that you want to edit.

After performing these steps, the controls will be connected to the properties of object data through `EditManager`, respectively, when the user will edit the values in the controls at the same time will change the values of the properties of the data object.

If the value of the data object is changed programmatically, to update values on the form need to perform `EditManager.Change`.

`EditManager` has events that allow to determine the values of properties in the data object. This event `BeforeChangePropertyValue` triggered before setting the value and `AfterChangeProperty` triggered after setting the value.

Also, you can associate a manually smeared controls with other controls, providing your `EditManager`, such as `GroupEditBase`, then it is possible to provide the editing values of the data object in the list, via the external controls.

### Binding through the properties

Instead of designing EditManager from the code, it is also possible to throw» «on the form as control and bind the controls with the properties using standard window editing properties in the Visual Studio environment.

If the window edit properties of an EditManager field Bindings.<select view> for some reason, nothing to choose, then the code dependent forms, you can correct the line:

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("", null, null);
```

in the following, where indicated, with what view works EditManager:

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("C__Client", typeof(IIS.TryFilter.Клиент), null);
```

Further through the field Bindings.<Add> you need to add the necessary properties of the object, and then in which appears below lines to define their controls from the list. Through the field `Bindings.<Remove>` is possible to delete object properties of binding.

To associate input fields with object properties generated code similar to the following:

```csharp
Binds(string viewname, Type dataobjectType, OneBind[] binds) (параметры для создания объектов класса OneBind аналогичны параметрам структуры ControlForBindStruct).
this.editManagerMain.Bindings = new Binds("C__Client", typeof(IIS.TryFilter.Клиент),
    new ICSSoft.STORMNET.Windows.Forms.Design.OneBind[]
        {
            new OneBind(this.textBoxClientFIO, typeof(System.Windows.Forms.TextBox), "Text", null, "Name"),
            new OneBind(this.textBoxClientAdress, typeof(System.Windows.Forms.TextBox), "Text", null, "Registration")
        });
```

## Useful links

* [How to edit data objects on forms and to associate the input fields with object properties data using EditManager](fw_edit-objects-on-forms.html).
* [LookUp](fw_lookup.html).
* Information about [EditManager.ApplyDataFromControl](fw_editform.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}