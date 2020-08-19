---
title: LookUp
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, LookUp
summary: appearance, multiple choice, overriding methods, it is possible to use problems and their solutions
toc: true
permalink: en/fw_lookup.html
lang: en
autotranslated: true
hash: fcceee7de27a210eafef7840b76316439b2e75ae3a6c139009f16e392c09ba09
---

`LookUp` (action) - select a craftsman of the object (putting links to the workman the object).

`LookUp` (control) - control for selection of artisan object that returns a reference to it.

## Return multiple objects

There is a possibility to get all the objects selected on the list for Lookup.

It is used in the [constraint editor](fw_limit-editor-advanced-view.html), where for the limit `Среди значений` returns all selected on list lucapa objects.

All selected objects can be obtained by contacting `DynamicProperties` standard of the returned object. Namely:

```csharp
o.DynamicProperties["retFromObjectListView"] as List<DataObject>
```

The resulting list will not contain the first selected object `o`.

__Note:__ for forms with multiple [ObjectListView](fw_objectlistview.html) or `ObjectHierarchicalView`, you need to remove the overload `prv_EditData` and a subscription to `OLV.ReturnData`.

## Override method Edited

The example shows how you can override the __Edited__ to use the information received from the LookUp data.

```csharp
public override void Edited(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
    {
        base.Edited (dataobject, contpath, propertyname);

        if (propertyname == "Employee")
        {
            Объекты.ДвижениеГруппыОтказнМатер Э = (Объекты.ДвижениеГруппыОтказнМатер)EditManager.DataObject;
            Объекты.Сотрудник р = (Объекты.Сотрудник)Э.Сотрудник;

            Э.ФИОСотрудника = Э.Сотрудник.ОпрПолноеФИО();
            if (р.Должность != null)
            {
                DataServiceProvider.DataService.LoadObject(р.Должность);
                Э.Должность = р.Должность.Наименование;
            }
            else Э.Должность = "";

            EditManager.Change("Posotrudnichat");
            EditManager.Change(Title);
        }

        if (propertyname == "Employee")
        {
            Объекты.ДвижениеГруппыОтказнМатер Э = (Объекты.ДвижениеГруппыОтказнМатер)EditManager.DataObject;
            Объекты.ОтвЗаРешение р = (Объекты.ОтвЗаРешение)Э.УКогоНаходится;

            if (р != null)
            {
                extTextControl1.Text = р.Наименование;
            }
            else extTextControl1.Text = "";
        }
    }
```

## Button display a list of

To add a button to display artisan list to a Lookup, you need to:

* To create flat control __button__
* Add icon from __ImageList__.
* At the push of a button to add a __call__ the following functions:

```csharp
public void ПоказатьСписок()
    {
        SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
        Function lf = null;

        // generated lf 
        LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Лицо), "ЛицоL");
        lcs.LimitFunction = lf;

        ICSSoft.STORMNET.DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

        if (objs.Length == 0)
        {
            Tools.ShowInformation("Not one person with the specified parameters");
            return;
        }
        else // at least someone found 
        {
            string contpath = "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/TabControl(Tab" + "Control)/panel(TabPage)/panel(GroupBox)/ctrlЛицо(LookUp)";
            base.OnEdit("Person", EditManager.DataObject, contpath, lf);
        }
    }
```

## The change in appearance

There are two ways to change the images on the buttons LookUp.

1.If you want to change the image within the application, you must initialize staticheskie ImageList_ when the application starts. This list must be present if the image name "LookUp", "Clear" and "Edit".

__Example:__

```csharp
LookUp.UserImageList.Images.Add("LookUp", Properties.Resources.LookUpImage);
LookUp.UserImageList.Images.Add("Clear", Properties.Resources.ClearImage);
LookUp.UserImageList.Images.Add("Edit", Properties.Resources.EditImage);
```

2.For a specific instance `LookUp` using the properties `UserImageList` you can assign polzovateli ImageList_, which also needs to be images with names "LookUp", "Clear" and "Edit".

### The size and borders of the buttons

The size of the buttons is set using the properties `ButtonSize`, changing this property should be complete recalculation of the size of the entire control.

For the display of the buttons property `ButtonBorder`.

## Prohibition of changes in BeforeChangeProperty

Ustanovka trait Cancel in a method BeforeChangeProperty if you change artisan field is not blocking Lookup, and then change the properties.

## Change handler look_LookUpEvent

To replace the processor on your `look_LookUpEvent`, you need to:

* create a container-runner
* to subscribe to events preserve (to keep),
* ask your actions when events occur.

```csharp
private void look_LookUpEvent1(object sender, System.EventArgs e)
{
    Посетитель oПосетитель = (Посетитель)this.EditManager.DataObject;
    
    Function lf = FunctionBuilder.BuildTrue();

    //create a container-runner 
    ICSSoft.АдресноеБюро.ЛичностьL FormЛичностьL = (ICSSoft.АдресноеБюро.ЛичностьL)      ICSSoft.STORMNET.UI.ContRunner.RunForm(typeof(ICSSoft.АдресноеБюро.ЛичностьL));
    FormЛичностьL.Edit(oПосетитель, "Personality", "Personality",lf);

    //subscribe to events 
    FormЛичностьL.SaveEvent+=new ICSSoft.STORMNET.UI.SaveEventArgsHandler(FormЛичностьL_SaveEvent);FormЛичностьL.CancelEvent+=new ICSSoft.STORMNET.UI.CancelEventArgsHandler(FormЛичностьL_CancelEvent);
}

private void FormЛичностьL_SaveEvent(object sender, ICSSoft.STORMNET.UI.SaveEventArgs e)
{
    //our action 
}

private void FormЛичностьL_CancelEvent(object sender, ICSSoft.STORMNET.UI.CancelEventArgs e)
{
    //our action 
}
```

## Possible problems of using

### Standard generated lookup

#### On the form

If not triggered, the event handler `look_LookUpEvent` (in the dependent form).

* Correctly specify the path on the form before `Lookup` in the method `prv_TuneLookupInformations` (in the dependent form).
* Specify the form of the call in the method `GetEditor` independent.
* The button `LookUp` (for example, for Citizenship it will be ctrlГражданство) to hang the handler __events__ LookUp.

```csharp
this.ctrlГражданство.LookUpEvent += look_LookUpEvent
```

#### In GroupEdit

* Snap [GropEdit](fw_group-edit.html) to form EditManager
* Presence of the handler `this.gr_SetupEditorEventHandler` type `OnSetupEditorEventHandler` for the event `SetupEditor`
* Mention `GroupEdit` in the method `prv_TuneLookupInformations` (in the dependent form), naprimer:

```csharp
m_arrGroupEditInformations.Add(
               new ICSSoft.STORMNET.UI.GroupEditInformation(this.ПредметыИзъятия,
               "Imageshare.Predmestie",
               "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/TabControl(TabControl)/panel(TabPage)/Predmestie(GroupEditBase)"));
```

### Non-standard use cases Lookup

#### Does not raise a form on Lookup

It is necessary to consider features of the control:

* If there is no property whose name is passed as `PropertyName`, it is best to specify an empty string, otherwise the method `Save` will not be called.
* Nahranie objects that are raised on Lookup scriptizer handled outside the box and the event needs to be written manually.

You should also check:

* If the object is stored, from which rises a LookUp.
* What forms are raised and with what shape.
* Rises with the form on the button LookUp. If Yes, then rises with forms LookUp. If Yes, specify nepravilno parametry. If not raised, neither the button nor from the method, that is, the error most likely is systemic.

#### Not the message about the preservation of the object when the form is closed

You need to check:

* Object has the status _Altered_
* The object has changed its properties from the moment of manual forms for Lookup. When picked up by the Lookup form is the initiator in the method `PrepareDataObjectForEdit` creates a copy of the data, and at the close compared this copy with the current on the close object. Conservation will only occur if there is a difference between an object and its copy.
* Form properly raised in the Lookup:
* passes the form object-initiator of the LookUp, not the one that should be edited
* indicated the existing property or the empty string

## Change the list name to Lookup

Change the name of the list for Lookup at different forms of editing, you can define conditions on the edit form and list form, add a handler for this event. For example, on the edit form when opening a Lookup in a data object add the desired title in the `DynamicProperties`, and in list form to read.

## Additionally

* [Lucap predictive](fw_extended-lookup.html)
* [The call to independent artists](fw_lookup-another-object.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}