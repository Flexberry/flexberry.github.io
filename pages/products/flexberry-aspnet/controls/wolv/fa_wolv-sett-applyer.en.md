--- 
title: WolvSettApplyer 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-sett-applyer.html 
lang: en 
autotranslated: true 
hash: 4540c7238b2970d0a48a9f0473c6c2a16275e8d93899cd729db6e98ba5f97393 
--- 

WolvSettApplyer needed in order to uniformly handle the application settings to [WOLV](fa_web-object-list-view.html) on the whole project. It comes with Flexberry ASP.NET but it can be modified to meet specific project needs. 

## Mission operations for WOLV in the form LookUp'and 

If you want to change [WOLV](fa_web-object-list-view.html) on the form [LookUp'a](fa_lookup-overview.html), it is necessary to use the following code in `WolvSettApplyer`: 

```csharp
/// <summary> 
/// Default method must be empty (all should be in the WOLV-e to initialize) 
/// Application programmers themselves to finish the logic here. 
/// </summary> 
/// <param name="wolv"></param> 
public void SettingsApply(WebObjectListView wolv)
{
  ...
  if (wolv.ID.Equals(LookUpForm.WebObjectListViewID))
  {
    // LookUpForm it WOLV 
  }
  ...
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}