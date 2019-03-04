--- 
title: Web form edit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_editform.html 
lang: en 
autotranslated: true 
hash: aaabb3b95e9d79d8a96d5c8cfb111489ae29936fae14c8aeda41cead21568147 
--- 

Web editor designed to create and edit objects. 

## appearance 

Appearance web edit forms using different topics can be found in the article [`Выбор themes Web приложения`](fa_choose-theme.html). 

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

### a List of methods 

|Method | Description| 
|---|---| 
|`save` | Save the object being edited.| 
|`saveAndClose` | Save the edited object and close the page.| 
|`formHasChanged` | Check whether it has been modified form.| 
|`resetChangeState` | Method reset the saved state of the form.| 
|`askSaveChanges` | Method to query the user must edit the document. The method is asynchronous. To check the result of the action the user should install the callback.| 

### the List of events 

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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/