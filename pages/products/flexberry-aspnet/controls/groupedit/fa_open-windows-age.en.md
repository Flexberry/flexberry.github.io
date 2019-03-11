--- 
title: Opening edit window in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_open-windows-age.html 
lang: en 
autotranslated: true 
hash: 11d118072f24beefbbc48ee945d2e301bac980e3e16af831131300e53396b3ad 
--- 

## Options open up a web-form editing in the AGE 

Edit records [AjaxGroupEdit](fa_ajax-group-edit.html) is possible in different modes: 

1. In the current окне; 
2. In modal окне; 
3. In a new window. 

### Opening in the current window 

In order to access [the web editor](fa_editform.html) in the current window, on the edit form wizard to specify ctrl<Name of detail>.DetailEditForm in `PostApplyToControls()`. 

```csharp
/// <summary> 
/// It is best to change the properties of controls on the page that are not handled WebBinder. 
/// </summary> 
protected override void PostApplyToControls()
{
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    Page.Validate();
}
``` 

### Opening in a modal window 

In order to access [the web editor](fa_editform.html) in a modal window, on the edit form wizard to specify `ctrl<Name of detail>.ModalWindowSettings` in `PostApplyToControls()`. 

```csharp
/// <summary> 
/// It is best to change the properties of controls on the page that are not handled WebBinder. 
/// </summary> 
protected override void PostApplyToControls()
{
    ctrlКвартира.ModalWindowSettings = new ThickBoxSettings(450, 700, "450*700");
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    ctrlКвартира.Operations.OpenEditorInNewWindow = true;
}
``` 

### Open in a new window 

In order to access [the web editor](fa_editform.html) in the new window, on the edit form wizard to specify `ctrl<Name of detail>.DetailEditForm` in `PostApplyToControls()`. 

```csharp
/// <summary> 
/// It is best to change the properties of controls on the page that are not handled WebBinder. 
/// </summary> 
protected override void PostApplyToControls()
{
    ctrlКвартира.DetailEditForm = КвартираE.FormPath;
    ctrlКвартира.Operations.OpenEditorInNewWindow = true;
    Page.Validate();
}
``` 

{% include note.html content="If [web edit form](fa_editform.html) opens in a separate tab (OpenEditorInNewWindow=1, OpenEditorInModalWindow=0), detail you can edit and tab with the [AGE](fa_ajax-group-edit.html), and on the edit page. 
This can be solved, for example, a row lock in the [AGE](fa_ajax-group-edit.html) is open another tab." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}