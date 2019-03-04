--- 
title: How to edit data objects on forms, associating input fields with the properties of data object 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (Controls), the Windows UI (forms) 
summary: step-by-Step instructions for "manual" creation of the edit form object and bind its controls to the properties of the data object 
toc: true 
permalink: en/fw_edit-data-objects-on-forms.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 2069b90a53425763cfc35a71d18b2b29b46440844bd1af40992851c3a18f532f 
--- 

The article describes what needs to be done in order to `вручную` to create a form where is possible to edit the data object. 

## Linking via program code 

1) Place the necessary controls to форме; 

2) Create an instance of the class ICSSoft.STORMNET.Windows.Forms.Binders.EditManager (Manager editing is a specialized class for continuous binding controls to properties of the data object). 

Designer required parameter is the data class that is configured in Manager edit. 

__Example__: 

```csharp
em = new StormNetForms.Binders.EditManager(typeof(CDDD));
``` 

3) to Link controls to properties of the data object. 
For this you need to call EditManager AddControl method. The parameters are passed: the structure ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct Examples of binding a control to a property of the data object: 

```csharp
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtName, "Text"), "Name");
em.AddControl(new StormNetForms.Binders.ControlForBindStruct(txtCapacity, "Text"), "Volume");
``` 

4) Set property EditManager.DataObject the data object that you want to edit. 

After performing these steps, the controls will be connected to the properties of object data through the EditManager, respectively, when the user will edit the values in the controls at the same time will change the values of the properties of the data object. 

If the value of the data object is changed programmatically, to update values on the form, you must perform the EditManager.Change. 

EditManager has events that allow to determine the values of properties in the data object. This event BeforeChangePropertyValue triggered before setting the value and AfterChangeProperty triggered after setting the value. 

Also, you can associate a manually smeared controls with other controls, providing your EditManager, such as GroupEditBase, then it is possible to provide the editing values of the data object in the list, via the external controls. 

## Binding through the properties 

Instead of designing EditManager from the code, it is also possible `набросить` on the form as control and bind the controls with the properties using standard window editing properties in the Visual Studio environment. 

If the window edit properties of an EditManager field Bindings.<select view> for some reason, nothing to choose, then the code dependent forms, you can correct the line: 

```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("", null, null);
``` 

in the following, where indicated, with what view works EditManager: 
```csharp
this.editManagerMain.Bindings = new ICSSoft.STORMNET.Windows.Forms.Design.Binds("C__Client", typeof(IIS.TryFilter.Клиент), null);
``` 

Further through the field Bindings.<Add> to add the necessary properties of the object, and then in which appears below lines to define their controls from the list. 
Through the field Bindings.<Remove> possible to delete object properties of binding. 

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


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/