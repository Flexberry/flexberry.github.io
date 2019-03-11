--- 
title: Add a custom form on the Windows desktop application 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: step-by-Step description of adding your own shapes to the Windows desktop app example 
toc: true 
permalink: en/fw_add-form-to-win-desktop.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: ce1047fc33e132e86480cb5b02bb1488b829ef0bd6a2544189aadc1bba256c8c 
--- 
To add your own form on the windows desktop application, you must: 
1. To open a file `<Projectname>DesktopCustomizer.cs`. 
2. In the file to find the method `GetRunner()`. 
3. In [brackets programmer](fo_programmer-brackets.html) it is necessary to add the required [Starters](fw_app-desktop.html). 

In the end you should get something like the following: 

```csharp
public override ICSSoft.STORMNET.UI.Runner[] GetRunners()
{
    System.Collections.ArrayList arr = new System.Collections.ArrayList();
    arr.AddRange(base.GetRunners());
    // *** Start programmer edit section *** (Credits GetRunners()) 

    // *** End programmer edit section *** (Credits GetRunners()) 
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КредитныйИнспекторL), "The accounting system of credits", "Loan officer", ""));
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КредитL), "The accounting system of credits", "Credit", ""));
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КлиентL), "The accounting system of credits", "Client", ""));
    // *** Start programmer edit section *** (Credits GetRunners() End) 
    arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Кредиты.КлиентL), "The accounting system of credits\\Clients", Customers, ""));
    arr.Add(new ICSSoft.STORMNET.Windows.Forms.FormRunner(typeof(winformОтчеты), "The accounting system of credits", Reports, ""));
    // *** End programmer edit section *** (Credits GetRunners() End) 
    ICSSoft.STORMNET.UI.Runner[] retArray = new ICSSoft.STORMNET.UI.Runner[arr.Count];
    arr.CopyTo(retArray);
    return retArray;
}
``` 

Note1: you Can specify the path in the tree menu. 

Note2: the Form is usually called with the prefix `winform`. 

After launching the application, you can observe the following picture: 
![](/images/pages/products/flexberry-winforms/desktop/win-desktop-plus.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}