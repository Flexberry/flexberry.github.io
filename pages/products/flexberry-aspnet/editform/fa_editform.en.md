---
title: Web form edit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Controls)
toc: true
permalink: en/fa_editform.html
lang: en
autotranslated: true
hash: 3932eb104611abf1c3e4a5d0e679158dbeb96d4ae39700ec5670a130d1ee0c92
---

Web editor designed to create and edit objects.

## Appearance

Appearance web edit forms using different topics can be found in the article [«theme Selection Web application»](fa_choose-theme.html).

## JS API

To work with the edit form on the client side you should use [JS API](fa_javascript-api.html), which is a jQuery plugin (`jQuery.icsEditForm`).

Key features:

```javascript
// Register custom event handler for the save object (the preferred option). 
$(function() {
    $.icsEditForm.attachEventHandler(function(p) {
        if (!condition)
            p.stop = true;    // Cancel the save. 
    });
});

// Register custom event handler to save the object 
// using the native capabilities of jQuery. 
$(function() {
    $('#pageForm').on('onCheckStarts', function(e, p) {
        if (!condition1)
            e.preventDefault();    // Cancel saving (option 1). 

        if (!condition2)
            p.stop = true;         // Cancel saving (option 2). 
    });
});
```

Read more about the function attachEventHandler and add additional logic when the object is saved on a Web edit form can be found in [Add additional logic when the object is saved on a Web edit form](fa_add-extra-logic-editform.html).

### The list of methods

|Method | Description|
|---|---|
|`save` | Save the object being edited.|
|`saveAndClose` | Save the edited object and close the page.|
|`formHasChanged` | Check whether it has been modified form.|
|`resetChangeState` | Method reset the saved state of the form.|
|`askSaveChanges` | Method to query the user must edit the document. The method is asynchronous. To check the result of the action the user should install the callback.|

### The list of events

|Event | Description|
|---|---|
|`initCompleted.icsEditForm`| End of initialization of a web edit form. The triggering of this event means that the field values of the object stored in the web controls.|

### Configuration

Read more on web edit forms see [this article](fa_editform-configuration.html)

#### Example usage

If you want to subscribe to the event of the end of the initialization, you must use the following options:

```javascript
$('#pageForm').on('initCompleted.icsEditForm', function () {
                    alert('Init completed!!!')
                });
```

## Data validation

Data validation occurs in several stages.

* First, you need to the maximum client-side validators (jQuery). These validators should block the form submission to the server if something is not filled or filled incorrectly (so we reduce server load and increase responsiveness and interactivity of the application). If it is a simple `required`, you can use [ASP.NET-validator](http://msdn.microsoft.com/en-us/library/system.web.ui.webcontrols.basevalidator(v=vs.100).aspx) enabled, the client handler then jQuery-validators is not necessary. The main thing to specify [`ValidationGroup`](http://msdn.microsoft.com/en-us/library/system.web.ui.webcontrols.basevalidator.validationgroup(v=vs.100).aspx).

* After client-side validators need to use the server and place it on the form [ValidationSummary](http://msdn.microsoft.com/en-us/library/system.web.ui.webcontrols.validationsummary(v=vs.100).aspx)you need to display a beautiful error messages.

* The third line – [business server](fo_business-server.html). It is also necessary, as the logic may be not only on the form and test it, as a rule, should always. Form, unlike the rest potential of» «visualmill, the response from the business server has to transform in clear view and show to the user. It is recommended to handle different from the standard way event save the object in the method `Save` and `SaveAndClose` through block `try-catch`. In `catch` to catch the message from the business server (preferably typed) and inform the user about the problem. This can be done either through a message to [WebErrorBox](fa_exception-handling.html) or mark on the page or something.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}