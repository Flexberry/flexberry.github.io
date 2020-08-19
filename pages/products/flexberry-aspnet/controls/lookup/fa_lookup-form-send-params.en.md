--- 
title: Passing parameters into a LookUp form in a Web application 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP NET, JavaScript API 
toc: true 
permalink: en/fa_lookup-form-send-params.html 
lang: en 
autotranslated: true 
hash: d2aa51e1d2a84091ba5ef69c0a36ceb30944b363d7b475529b549032115ae0fe 
--- 

This article applies to technological [LookUp-form](fa_tech-forms-web.html) and describes how to transfer their own parameters in a LookUp-form with [JavaSript API](fa_lookup-overview.html). 

## LookUp-form 

When implementing your own LookUp form, you may need to pass several parameters and parse them when the form loads. This article will be reviewed by an example of transmission parameters when using the technological LookUp. 

## statement of the problem 

Given the edit form with lucapa `ctrlLookUp1`, lookup "looks" to the form `LookUpForm.cs` generated along with the application. 
Want to add to the edit form button, when clicked on the LookUp form is added to the transferred message. 

## solution Algorithm 

1. Create a button and register for her `click` method, which will be the transfer of additional parameters. 
2. "Catch" on the LookUp form, the arrival of additional parameters and add the desired message (if necessary). 

### creating a button and passing parameters 

* Create button: 

```xml
 <button id="addMessageButton">Добавить сообщение на LookUp-форму</button> 
``` 

* To create a handler for the clicking: 

```javascript
$('#addMessageButton').click(function () {
    $('#<%=ctrlLookUp1.ClientID%>').icsMasterEditorAjaxLookup('updateOptions', { formParams: 'ShowMessage=true&TheMessage=Сообщение' });
    return false;
});
``` 

{% include note.html content=">As in other cases work with the JS API, do not forget `return false;` to avoid PostBack." %} 


{% include warning.html content="In the code of applications is not necessary to explicitly use constants `LookUp` etc., as their value may change in the future. For special technological parameters from the request, use class [WebParamController](fa_get-query-parameters-forms.html) . Please check the latest projects." %} 

## Processing parameters on the LookUp form 

On the technological LookUp-the default form method added 

```csharp
 protected override void ApplyWolvSettings() 
``` 

Add the following code: 

```csharp
bool showMessage = Request["ShowMessage"] != null && Request["ShowMessage"] == "true";

if (showMessage)
{
    string message = Request["TheMessage"];
    // Add the message. 
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}