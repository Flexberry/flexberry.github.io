--- 
title: BaseMasterEditorLookUp 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_base-master-editor-lookup.html 
lang: en 
autotranslated: true 
hash: 43c8d1b97c9898da24c4e309d007e6b236df0e8da548825b79dc692925a7fa1f 
--- 

## Uniform changes in the properties of lyapov 

Implemented two static delegate: 
1. `InitLookUpSettings` - Delegate to initialize the settings. Settings you can override in the. aspx forms. 
2. `ChangeLookUpSettings` - Delegate to change settings, which allows you to bring all lucapa to the uniform mind. 

Example, you need to subscribe to in the Global.asax: 

```csharp
BaseMasterEditorLookUp.ChangeLookUpSettings = AllForms.ChangeLookUpSettings;
``` 

```csharp
/// <summary> 
/// Change settings lyapov 
/// </summary> 
/// <param name="lookup">Lookup who are changing the settings</param> 
public static void ChangeLookUpSettings(BaseMasterEditorLookUp lookup)
{
    lookup.LookUpFormHeight = 640;
    lookup.LookUpFormWidth = 854;
    lookup.ShowInThickBox = true;
    lookup.LookUpFormCaption = "Choose a value";
}
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/