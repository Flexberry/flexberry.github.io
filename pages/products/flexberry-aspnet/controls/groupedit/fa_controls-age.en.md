--- 
title: Configuring controls inside AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_controls-age.html 
lang: en 
autotranslated: true 
hash: 0637f4bd804067e182d7313182d2adb96485ba47ae26558b5019d640166d56a8 

--- 

## Description settings 

Sometimes there is a problem additional setting properties of the controls generated to edit and display the property values of datalow in AGE. You can use the delegates defined statically for the class [AjaxGroupEdit](fa_ajax-group-edit.html). The delegate is set for a specific view name and is of type TuneControlDelegate, more about which you can read [here](fa_tune-control-delegate-method.html). To add a delegate, you can add using the method SetControlTuner: 

```csharp
/// <summary> 
/// Set method to configure the controls that appear in the AGE. 
/// </summary> 
/// <param name="viewName">view Name (detail displayed in AGE), which will be passed to invoked method.</param> 
/// <param name="method">Method that performs the setting of controls.</param> 
public static void SetControlTuner(string viewName, TuneControlDelegate method)
``` 

Ask delegates better in the Global.asax.cs, in the Application_Start method. Example usage: 

```csharp
AjaxGroupEdit.SetControlTuner(
    Подзадача.Views.ПодзадачаE.Name,
        (control, data) =>
           { 
              if (control.GetType() == typeof(TextBox) && data.PropertyName == Information.ExtractPropertyName<Подзадача>(x => x.Описание)) 
                 ((TextBox)control).TextMode = TextBoxMode.MultiLine; 
           });
``` 

Details about AjaxGroupEdit written in this [article](fa_ajax-group-edit.html). 

## Other options for the controls 

* [Settings for LookUp in AGE](fa_settings-lookup-age.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}