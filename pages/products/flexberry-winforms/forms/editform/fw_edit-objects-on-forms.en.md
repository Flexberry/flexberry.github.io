---
title: Editing data objects on forms
sidebar: flexberry-winforms_sidebar
keywords: Controls, Windows
summary: step-by-Step instructions for "manual" creation of the edit form object and bind its controls to the properties of the data object
toc: true
permalink: en/fw_edit-objects-on-forms.html
lang: en
autotranslated: true
hash: 47e48e0c6f338416abe6762d6d1ea653ea80e16e8f68a6f227076bf95dffae5c
---

There are several ways to "manually" create the edit form of the object with the ability to associate it with the properties of an existing data object.

## Linking via program code

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

## Binding through the properties

Instead of designing EditManager from the code, it is also possible to throw» «on the form as control and bind the controls with the properties using standard window editing properties in the Visual Studio environment.

If the window edit properties of an EditManager field Bindings.<select view> for some reason, nothing to choose, then the code dependent forms, you can correct the line:

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("", null, null);
```

in the following, where indicated, with what view works EditManager:

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("C__Client", typeof(IIS.TryFilter.Клиент), null);
```

Further through the field Bindings.<Add> to add the necessary properties of the object, and then in which appears below lines to define their controls from the list. Through the field `Bindings.<Remove>` is possible to delete object properties of binding.

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


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}