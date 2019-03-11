--- 
title: Set field value of the created object 
sidebar: flexberry-orm_sidebar 
keywords: DataObject ORM Flexberry 
summary: for an Example of setting default values when the object is created 
toc: true 
permalink: en/fo_define-field-created.html 
lang: en 
autotranslated: true 
hash: 39dcd526ca2dbe62396e1925bbf291e4d4686d064dca34abcbc7e098a29d5c51 
--- 

If there is a current object, which should be specified by default when you create another object, you can override [Edit](fw_form-interaction.html), where to check, what is [the status of a load data is defined as `ObjectStatus.Created`](fo_object-status.html), and then install the current object of interest in the field and [to reflect the changes on the form using `EditManager`](fw_editmanager.html). 

```csharp
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
    base.Edit(dataobject, contpath, propertyname, tag); //call the base method 
    if (dataobject != null)
    {
        if (dataobject.GetStatus() == ObjectStatus.Created) //check that the object is not yet persisted 
        {
            ((Покупка)dataobject).МагазинПокупки = Магазин.CurrentShop; //set a current object 
            EditManager.Change("Shop"); //display changes on the form 
        }
    }
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}