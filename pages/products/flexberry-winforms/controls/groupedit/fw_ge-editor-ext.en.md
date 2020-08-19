--- 
title: Editing of datalow on a separate form 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, GroupEdit, GEEditorExt 
summary: the connection Rules component to edit detailov on a separate form 
toc: true 
permalink: en/fw_ge-editor-ext.html 
lang: en 
autotranslated: true 
hash: 07eaebeca0190d1fd0a74176753f4047d9894ef63d72e164a7fc0ae9bbd23428 
--- 

`GEEditorExt` - extension component [GroupEdit](fw_group-edit.html), allowing to organize the following behavior: 

1. Toolbar `GroupEdit` added buttons "Create" and "Open" 
2. When you click on the button "Open" up the edit form corresponding to the object type in `GroupEdit`. When you pick up the form data entered in `GroupEdit` are recorded on the form. 
3. When you click on "Create" up edit form of detail corresponding object type in `GroupEdit`, while maintaining which creates a line in the `GroupEdit`. 
4. When you save detail on the edit form does not record in the database - detail will be recorded in the database along with his master when it is saved. 
5. In the case when the object is open for editing, it is forbidden to edit it in `GroupEdit`(a warning message is issued and displayed on the foreground of the edit form). For consistency of display in `GroupEdit` changed to the form data synchronized display of data in the edit form and when you activate it. 

{% include important.html content="GEEditorExt not supplied as standard Flexberry Winforms." %} 

## How to connect GEEditorExt 

Connection `GEEditorExt` considered by the example: "Buyer" is [class wizard](fd_key-concepts.html): Purchase - [class of detail](fd_key-concepts.html). 

![Example diagrammy](/images/pages/products/flexberry-winforms/controls/groupedit/class-diagram_-customer-purchase2.jpg) 

{% include note.html content="To edit detailov you must generate the appropriate form of editing (the quick prototypization it is not created)." %} 

1.In the form class aggregator declares a variable of type `GEEditorExt`: 

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
    IIS.AMS02.GEEditorExt extGEПокупки;
    //... 
}
``` 

2.In the method `Edit` forms of the aggregator adds code for initialization of this variable. The class constructor `GEEditorExt` as a parameter is passed [`GroupEdit`](fw_group-edit.html), the functionality of which is expected to increase. 

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
    IIS.AMS02.GEEditorExt extGEПокупки;
    public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
    {
        //... 
        #region возможность создания и редактирования реагирований на отдельной формы из GE
                        if (this.extGEПокупки == null)
                                this.extGEПокупки = new IIS.AMS02.GEEditorExt(Покупки);
                #endregion возможность создания и редактирования реагирований на отдельной формы из GE
        //... 
    }
    //... 
}
``` 

{% include note.html content="in place of paragraphs 1 and 2, if it is not necessary to perform additional actions with the buttons (hide, rename them, etc.) you can use the extension from the GroupEdit." %} 

3.In the method `GetEditor` forms of the aggregator code is added that indicates which form to raise for editing specific detail: 

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
    public override Type GetEditor(ICSSoft.STORMNET.UI.EventType eventtype, ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
    {
        if ((propertyname == "Buy"))
        {
            return System.Type.GetType("IIS.MasterField.C__Pokupke");
        }
        //... 
    }
    //... 
}
``` 

4.Override method `PromtUserForActionAtClose` forms of detail (this is due to the feature of the 1st class `GEEditorExt`): 

```csharp
public class WinformC__ПокупкаE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупкаE
{
    public override ICSSoft.STORMNET.UI.DialogResult PromtUserForActionAtClose()
    {
        if (EditManager.DataObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Created &&
            EditManager.DataObject.DynamicProperties.ContainsKey("Pseudocoherent") ||
            EditManager.DataObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.UnAltered &&
            EditManager.DataObject.GetAlteredPropertyNames().Length == 0)
            return ICSSoft.STORMNET.UI.DialogResult.Yes;
        return base.PromtUserForActionAtClose();
    }
    //... 
}
``` 

5.Independently of detail to override the reading from the DB (in order not shall be deducted the entire object when lifting the edit form): 

```csharp
public class C__ПокупкаE : ICSSoft.STORMNET.UI.BaseIndpdEdit
{
    protected override void PrepareDataObjectForEdit(ICSSoft.STORMNET.DataObject dobject)
    {
        // *** Start programmer edit section *** (PrepareDataObjectForEdit (DataObject) start) 
        if (dobject.GetStatus(false) == ICSSoft.STORMNET.ObjectStatus.Altered)
        {
            m_objView = ICSSoft.STORMNET.Information.GetView("C__Pokupke", typeof(IIS.MasterField.Покупка));
            return;
        }
        // *** End programmer edit section *** (PrepareDataObjectForEdit (DataObject) start) 
        m_objView = ICSSoft.STORMNET.Information.GetView("C__Pokupke", typeof(IIS.MasterField.Покупка));
        if ((dobject.GetStatus(false) != ICSSoft.STORMNET.ObjectStatus.Created)
                    || dobject.Prototyped)
        {
            ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(m_objView, dobject, false, false);
            dobject.InitDataCopy();
        }
        // *** Start programmer edit section *** (PrepareDataObjectForEdit (DataObject) end) 

        // *** End programmer edit section *** (PrepareDataObjectForEdit (DataObject) end) 
    }
    //... 
}
``` 

It assumes that the view to display detail on the edit form and in [GroupEdit](fw_group-edit.html) are the same (i.e. it is the same view, or views with the same set of attributes). If the views are different, then the code between the brackets of the programmer in the above method `PrepareDataObjectForEdit` should be replaced by this: 

```csharp
m_objView = ICSSoft.STORMNET.Information.GetView("C__Pokupke", typeof(IIS.MasterField.Покупка));
ICSSoft.STORMNET.View ge_View = ICSSoft.STORMNET.Information.GetView("C__ПокупкаD", typeof(IIS.MasterField.Покупка));
//finish what is on the edit form, but not in the GE 
ICSSoft.STORMNET.View дочитать_View = m_objView - ge_View;
//from this view, remove properties that could be changed on the edit form, 
//if it has been opened before 
foreach (string altprop in dobject.GetAlteredPropertyNames())
    дочитать_View.RemoveProperty(altprop);
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(дочитать_View, dobject, false, false);
return;
``` 

However, in this case (in the case of different representations) will appear feature: if you open the master, with nothing else on it not to change, to open detail on the edit and close the form, it closes without issue of preservation and the form wizard will ask about saving. This is due to the fact that after subtraction of additional properties of detail its set of attributes in the copy of the master will differ from a set in the wizard. To get rid of it by manual correction of the copy wizard before opening the edit form of detail. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}