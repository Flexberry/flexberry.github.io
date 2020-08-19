---
title: the title Setting list form
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, list form
summary: Override the header of a list form use the list for detailov
toc: true
permalink: en/fw_list-form-caption.html
lang: en
autotranslated: true
hash: 97c635f87bedc05758029694541bbc18236c1160b70b2670bd2aa654db158355
---

Setting dynamic properties `ListFormCaption` data object allows you to override the title of the list form that opens when editing a feature (raising to LookUp).
It should be noted that the installation of the window title happens in the `BaseWinList.Edit`.

```csharp
public override void OnEdit(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
{
   if (propertyname == "Breed")
      if (!dataobject.DynamicProperties.ContainsKey("ListFormCaption"))
         dataobject.DynamicProperties.Add("ListFormCaption", "A list of all breeds");
  
   base.OnEdit(propertyname, dataobject, contpath, tag);
}
```

## Detail as a master

![Detail as a wizard](/images/pages/products/flexberry-winforms/forms/connect-details-master.png)

One feature of datalow is the lack of list form and edit form: both that and another is [GroupEdit](fw_group-edit.html) on the edit form of an aggregator. However, if the need arises to make detail master of another object, the first step is to create the edit form (and, consequently, [L-view](fd_l-view.html)).

### Necessary actions

* Create L-view for artisan of detail.
* Create a [list](fd_key-concepts.html) for artisan of detail.
* Configure the [E-submission](fd_e-view.html) artisan of detail.
* Configure the [LookUp](fw_lookup.html).

#### To create an L-representation

To create an L-representation, it is necessary to go into the properties of the class of the workman lucapa (in our case - Stratiography), go to the tab "View" and create a new view, naming it `СтрокаПогашенияПланаL`

![Presentation list](/images/pages/products/flexberry-winforms/forms/connect-details-master-l-view.png)

#### Create form list

To create the list, open the chart application and the forms created with the rapid prototyping. Create a new class, call it `СтрокаПогашенияПланаL`. Select the stereotype `listform`. Go to the class properties, in the tab "Composite view" select the view `СтрокаПланаПогашенияL` created in the previous step.

![List form](/images/pages/products/flexberry-winforms/forms/connect-details-master-l-form.png)

#### To configure E-submission

To detail it was not possible to change the aggregating object must be removed from the E-presentation all references to it, and add links to the object for which he will be the master.

![The view for the edit form](/images/pages/products/flexberry-winforms/forms/connect-details-master-e-view.png)

#### To Configure LookUp

The last step is to configure lyapov aggregating object of our "non-artisan of detail" (in our case, this object `Клиент`, which is an aggregator for `КредитнойКарты`). Go to the settings edit form class `КлиентE`, press the button `PropertyLookups` and choose the appropriate container (the form created in step 2).

![Setup Lookup](/images/pages/products/flexberry-winforms/forms/connect-details-master-lookups.png)

### Error

If something is made incorrectly, then when you try to open the object LookUp will appear error message `No Such Container`.

The same error occurs when trying to create a new object in the form of a list of detail as the edit form for him there.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}