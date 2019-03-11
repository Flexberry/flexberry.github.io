--- 
title: Adding objects in AjaxGroupEdit at form initialization (new object) 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_add-objects-age-initialization.html 
lang: en 
autotranslated: true 
hash: 0dfff5d9acfdfc8fc5592702bbc00451a865510859072857ff86ddb156c51ea7 
--- 

## Algorithm add objects to AGE at form initialization 

`AjaxGroupEdit` edits already read detaily artisan object (he AGE in DB reads nothing). Therefore, if you want when you create a new object add him detaily, then you need an editable object, just add detaily. 

## Example 

An example of the edit object "Cat" detaylari "paw": 

```csharp
/// <summary> 
/// It is best to write the business logic, operating only with the data object 
/// </summary> 
protected override void PreApplyToControls()
{
    if (!IsPostBack && (DataObject == null || DataObject.GetStatus(true) == ObjectStatus.Created))
    {
        var cat = new Кошка();
        var foots = new DetailArrayOfЛапа(cat)
            {
                new Лапа { Размер = 10 }, 
                new Лапа { Размер = 11 }
            };
        cat.Лапа = foots;

        DataObject = cat;
    }
}
``` 

If you want to add objects, but not to save the database, if the user has not changed, you will need to add, but with the status Unaltered. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}