--- 
title: WolvSettApplyer 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-sett-applyer.html 
lang: en 
autotranslated: true 
hash: 5cf75adb9215637df0b918b276b9da4cfe4aeab9d67c59ddbc41f7b947989c66 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/