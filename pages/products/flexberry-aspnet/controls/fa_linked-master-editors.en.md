--- 
title: Link controls editing masters 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_linked-master-editors.html 
lang: en 
autotranslated: true 
hash: 82a5ce7ccb1efd1d3376c12fecae96d0f537cb21f37732f2cd5d92f521bae50a 
--- 

This article tells how to link these web controls edit masters as [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) and [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html). 

## Methods [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) 

### Creating dependent lyapov inside AGE 

If lucapa are inside the [AGE](fa_ajax-group-edit.html), then you need to use [next method](fa_controls-age.html). 

### Work with LookUp'om from javascript 

Subscription to change of value in LookUp'e 

```javascript
$('#<%=ctrlМойЛукап.ClientID%>').on('change', function () {
  // alert('Value changed'); 
});
``` 

Alternatively, you can specify client-side change handler in server code: 

```csharp
    lookup.ChangeClientHandler = "alert('Value changed in lucapa with ID {0}');";
``` 

## Binding by using the server methods provided by technology 

For specific control of the edit master ([MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) or [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)) you can specify it workman the control ([MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) or [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)), i.e. the one from which it depends. When changing control of the workman will be called server-side handler to modify the properties of dependent control. 

```csharp
// It is necessary to write only when all properties of controls surrounding WebBinder (type, etc.) 
ctrlSlave.AddMasterLookup(ctrlMaster, MasterOfSlaveChanged);
``` 

`MasterOfSlaveChanged` is a delegate of the form: 

```csharp
/// <summary> 
/// Delegate the processing of changing the values in the AJAX lucapa 
/// </summary> 
/// <param name="masterSelectedPk">the Current value of the workman lucapa</param> 
/// <param name="selectedPk">the Current value of the dependent lucapa</param> 
/// <param name="masterLookups">a List of other workmen of lyapov</param> 
public delegate void AjaxLookUpChangeHandler(
    string masterSelectedPk,
    ref string selectedPk,
    List<MasterLookup> masterLookups);
``` 

For example, if you want to clear dependent control, if purified of the artisan: 

```csharp
private void MasterOfSlaveChanged(string masterselectedpk, ref string selectedpk, List<MasterLookup> masterLookups)
{
    if (string.IsNullOrEmpty(masterselectedpk))
    {
        selectedpk = string.Empty;
    }
}
``` 

Also, you can change LimitFunction, etc. 

In this handler comes to the value of the workman control - `masterselectedpk` and the value of the dependent control - `selectedPk` (passed by reference). (I.e. if `selectedpk` is changed, the javascript method is put the value and go another AJAX request to zachetku object `selectedpk`). 

{% include note.html content="Single control of the edit wizard can have several artisans of controls. When you link every time you set a new handler." %} 

{% include note.html content="In dynamics you can't change the master control." %} 

{% include note.html content="Have the opportunity to learn in the handler for which control editing of the master called him - `bool Triggered`." %} 

{% include important.html content="Artisan [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html) may be your dependent control dependent control." %} 

For example: 

```csharp
ctrl1.AddMasterLookup(ctrl2, ctrl1Ofctrl2Changed);
ctrl2.AddMasterLookup(ctrl1, ctrl2Ofctrl1Changed);
``` 

BUT: Artisan [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html) cannot be a dependent dependent control control type [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}