--- 
title: Enable Read-only mode for an individual column AGE 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_read-only-age.html 
lang: en 
autotranslated: true 
hash: 7902ec5b187ed076a4d5eb9f8a30e0f756928d00f6dbf025bddb4174a974de88 
--- 

Since technology does not provide for the scenario where any individual column [AGE](fa_ajax-group-edit.html) read-only, standard method no. 

However, there is a workaround: 

1. To add to detail [calculated field](fo_not-stored-attributes.html). 
2. To determine the function of the calculation field so that it duplicated the column that you want to make read-only. 
3. [E-view](fd_e-view.html) detail created a calculated field to remove the visibility of the original field. 

After the generation of all objects will work. 

### Example 

Suppose that we are given the following detail: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age1.png) 

You must `Field2` make a field read-only. 

First we need to add a calculated field to the class: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age2.png) 

Then, configure the view: 

1. Add a calculated field 
2. To remove visibility from the "old" field 
3. To customize the header of computable fields 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age3.png) 

To regenerate the objects. After regeneration it is necessary to modify `getter` a calculated field so that it returns the value of the field that should be displayed read-only: 

```csharp
[ICSSoft.STORMNET.NotStored()]
[StrLen(255)]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "Field2")]
public virtual string Field2ReadOnly
{
    get
    {
        // *** Start programmer edit section *** (Side.Field2ReadOnly Get) 
        return Field2; // Here you specify the field. 
        // *** End programmer edit section *** (Side.Field2ReadOnly Get) 
    }
    set
    {
        // *** Start programmer edit section *** (Side.Field2ReadOnly Set) 

        // *** End programmer edit section *** (Side.Field2ReadOnly Set) 
    }
}
``` 

{% include note.html content="`DataServiceExpression` in this case is not necessary: when the AGE takes a real value and not `StringedView`. However, it is recommended to install it." %} 

After this AGE will look like the following: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/read-only-age4.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}