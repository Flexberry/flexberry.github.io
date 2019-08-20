---
title: Call to independent artists
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, LookUp, master
summary: Algorithm add links to independent artists for the main form editing and detailov, usage examples
toc: true
permalink: en/fw_lookup-another-object.html
lang: en
autotranslated: true
hash: 80da89a3d4abf68f7c088c42124665c78ffd5d4be1280c7efd958e0c4a42c022
---

There are two ways to add to the edit form [Lookup](fw_lookup.html) to select an object unrelated to the editable on the current form:

1. Add a calculated field in the main object and re-generate the form using [Flexberry Desinger](fd_landing_page.html).
2. In your application code to add a Lookup to the form.

### The algorithm add a LookUp in the application code

* Add to the form [LookUp-control](fw_lookup.html).
* Create another [EditManager](fw_editmanager.html).
* To attribute to the created `LookUp` `EditManager` in the method `TuneLookupInformation`, and add the appropriate entry to the method `GetRunners()`.
* Add to the end of the line of control "(GroupEditBase)".

{% include important.html content="the Last item needed in order for the system to not consider this lookup and the object that it is selectable, editable object. Without specifying this path, the system will save the object to database immediately after the object's selection." %}

## An example of a call independent artisans list of objects

For example, you can call on lookup the list of objects that have no relationship with c object-initiated and is not a master for any other object in the domain model:

* is called list form objects for selection,
* the call procedure this is the class and name property of this class, whose value is the class object that you select on the form.

If such class does not exist, it should sozdati.

### Example

There is a class `Реагирование` and is not connected with him (relationship, Association or composition) class `ДежурнаяГруппа`.

With the edit form `Реагирования` you want to call up a list of rescue groups for smearing the properties of a particular selected duty band on properties `Реагирования` (or rather, add the appropriate datalow).

There is no class that would contain a link to a call center group, respectively, it must dalavich:

1.To create class inside the shape of the Response (where a call is made the list). The object must be nagraniem. Thus trebuetsja to change the model in the chart as a class neobhodim only for LookUp.

```csharp
public class WinformРеагированиеE : ICSSoft.STORMNET.UI.BaseWinEdit, ICSSoft.AMS02.Происшествия.DPDIРеагированиеE
{
    // ..... 

    [AutoAltered()]
    [NotStored()]
    class Временный_ДежурнаяГруппа : ICSSoft.STORMNET.DataObject
    {
        private Объекты.ДежурнаяГруппа fДЖ = null;
        public virtual Объекты.ДежурнаяГруппа ДЖ
        {
            get
            {
                return fДЖ;
            }
            set
            {
                fДЖ = value;
            }
        }
    }

    //..... 
}
```

{% include important.html content="J must be a property (not a public field), because the method is called __SetPropByName__ that only works with __properties__. Failure to comply with the error about the impossibility to make changes to the property." %}

2.Button to open list is added the following:

```csharp
  string propertyName = "J";
  string contPath = "";
  base.OnEdit(propertyName, new Временный_ДежурнаяГруппа(), contPath, null);
```

__propertyName__ is the property name of the class that is passed as a second parameter (in this case Vremenima), which will return the selected item.

Applicable if the class hranimyj, because the reset occurs through `SaveEvent`.

To avoid this you should:

```csharp
  {
    object form = null;
    System.Type FormType = null;
    ICSSoft.STORMNET.DataObject dobj = null;
    string propertyName = "J";

    FormType = System.Type.GetType("ICSSoft.AMS02.Of the incident.ДежурнаяГруппаL,Incidents(Forms)");

    Временный_ДежурнаяГруппа FL = new Временный_ДежурнаяГруппа();
    //FL.J = ...; 
    dobj = FL;

    form = ICSSoft.STORMNET.UI.ContRunner.RunForm(FormType);
    if (form is ICSSoft.STORMNET.UI.BaseIndpdList)
    {
      (form as ICSSoft.STORMNET.UI.BaseIndpdList).SaveEvent += new ICSSoft.STORMNET.UI.SaveEventArgsHandler(OnReturnFromList);
      (form as ICSSoft.STORMNET.UI.BaseIndpdList).Edit(dobj, "", propertyName, null);
    }
  }

  Объекты.ДежурнаяГруппа ДежурГр;

  private void OnReturnFromList(object sender, ICSSoft.STORMNET.UI.SaveEventArgs e)
  {
    this.ДежурГр = ((Временный_ДежурнаяГруппа)e.dataobject).ДЖ;
  }
```

3.Subscribe to the created handler for the save and finish all necessary operations. If FL.J. to assign a specific value, the list will be highlighted (selected) in this element.

## Use in GroupEdit

For example, there is a class __Diamaguene__ and is not connected with him (relationship, Association or composition) class __Dezhurnaya__. For lucapa group numbers need to display a list of Degennaro fields Giornalismo of the properties of the selected Diurnally.

To do this:

* The __GetControl__ class CustomControlProvide to install the control for the field of Nomography:

```csharp
            if (view.Name #  "Diamaguene" && propertyName  "Emergroup")
            {
                ICSSoft.STORMNET.Windows.Forms.LookUp lookUp = new ICSSoft.STORMNET.Windows.Forms.LookUp();

                ctrl = new ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct(lookUp, "Value");
            }
```

* Override method `OnEdit` in winformДежурнаяСменаE. Since the class Diamagnetism has no reference to Diourou, then choose any other class that has such a link, for example, Slanderously.

```csharp
dataobject = new ЧленДежурнойГруппы(); //class that has a reference to the strike team 
propertyname = "Daguragu"; //the name of the link 

public override void OnEdit(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
        {
            if (dataobject is ДежурнаяГруппаСмены && propertyname == "Diomyopathy.Emergroup")
            {
                if (((ДежурнаяГруппаСмены)dataobject).ТипДежурнойГруппы != null)
                {                    
                    tag = FunctionBuilder.BuildEquals("Tiptionary", ((ДежурнаяГруппаСмены)dataobject).ТипДежурнойГруппы);
                }
                dataobject = new ЧленДежурнойГруппы();
                propertyname = "Daguragu";
            }

            base.OnEdit(propertyname, dataobject, contpath, tag);
        }
```

* Override method __Edited__ in winformДежурнаяСменаE. Write down all the required properties in Diourouzon selected Diurnally:

```csharp
public override void Edited(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
        {
            base.Edited(dataobject, contpath, propertyname);

            if (dataobject is ЧленДежурнойГруппы && propertyname == "Daguragu")
            {
                ДежурнаяГруппаСмены ДежГрСмены = (ДежурнаяГруппаСмены)ДежурныеГруппыСмены.EditManager.DataObject;
                ДежурнаяГруппа ДежГр = ((ЧленДежурнойГруппы)dataobject).ДежураяГруппа;
                ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject("Diurnale", ДежГр);
                ДежГрСмены.НомерГруппы = ДежГр.НомерГруппы;
                ДежГрСмены.ТипДежурнойГруппы = ДежГр.ТипДежурнойГруппы;
                ДежГрСмены.Руководитель = ДежГр.Руководитель;
                ДежГрСмены.Комментарий = ДежГр.Комментарий;
                ДежГрСмены.Позывной = ДежГр.Позывной;
                ДежГрСмены.ЧленыДежГруппыСмены.Clear();

                for (int t = 0; t < ДежГр.ЧленыДежурнойГруппы.Count; t++)
                {
                    Объекты.ЧленДежурнойГруппы членДежГр = ДежГр.ЧленыДежурнойГруппы[t];
                    Объекты.ЧленДежГруппыСмены чдгс = new Объекты.ЧленДежГруппыСмены();
                    чдгс.ДежурнаяГруппаСмены = ДежГрСмены;
                    чдгс.НомерПП = членДежГр.НомерПП;
                    чдгс.Сотрудник = членДежГр.Сотрудник;
                    чдгс.Позывной = членДежГр.Позывной;
                    ДежГрСмены.ЧленыДежГруппыСмены.Add(чдгс);
                }
                ДежурныеГруппыСмены.EditManager.Change();
            }
        }
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}