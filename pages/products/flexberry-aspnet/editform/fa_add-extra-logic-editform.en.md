--- 
title: Add additional logic when the object is saved 
sidebar: flexberry-aspnet_sidebar 
keywords: 
toc: true 
permalink: en/fa_add-extra-logic-editform.html 
lang: en 
autotranslated: true 
hash: 943599027e3abfd1e5c62d289ab904b5b7fe1052eb0d52136096e1ea16e954f8 
--- 

## Processing event save object on the client 

In the client event handlers clicking on the save button located on a Web edit form, before sending the callback and save the object, the system checks the editable data in the technological code (validators controls [AjaxGroupEdit](fa_ajax-group-edit.html)). 

The standard verification process of the edited data can be extended by adding event handlers using the function `$.icsEditForm.attachEventHandler`. It's possible to add handlers to the following events: 

| Event | Description| 
|---|---| 
| `onCheckStarts` | Triggers before checking the editable data in the technological code. In the handler of this event can be cancelled and then save the edited data or execute asynchronous operations that must complete before executing povertyreduction data in the technological code (due to the transfer of Deferred objects using the parameter handler of this event in the technological event handler to save the object).| 
| `onCheckStopped` | Triggered in the event of cancellation of preserving data in the event handler onCheckStarts.| 
| `onCheckSucceed` | Triggered in case of successful validation of the edited data in the technological code.| 
| `onCheckFailed` | Triggered in case of unsuccessful validation of data editable in a technological code or in case of transfer to a status of "cancelled" at least one of the Deferred-objects created in the event handler `onCheckStarts`.| 
| `onCheckProgress` | Initiated when a notification of progress from one of the Deferred-objects created in the event handler `onCheckStarts`.| 

### $Methods.icsEditForm.attachEventHandler and $.icsEditForm.attachOnceEventHandler 

$Methods.icsEditForm.attachEventHandler and $.icsEditForm.attachOnceEventHandler designed to register event handlers for the edit form. The difference between them is that the $.icsEditForm.attachEventHandler registers a handler that will be executed always when the corresponding event occurs, and $.icsEditForm.attachOnceEventHandler registers a handler that will be executed once. 

Both methods take two parameters: 

| Parameter | Description | 
|---|---| 
| `handler` | Function-event handler.| 
| `eventName` | event Name. If not specified, the registers an event handler `onCheckStarts`.| 

### event Handlers 

All event handlers take an object as argument. This object contains one property: saveOptions - the parameters with which the method was invoked save. 
According to these parameters it is possible to distinguish whether there was a method of preservation called from the process code (for example by clicking on the save button), or from an application. 


The argument of the event handlers onCheckStarts and onCheckSucceed contains two properties (stop and deferreds): 

|Property| Description| 
|---|---| 
|saveOptions| Options with which the method was invoked save.| 
|stop |Boolean property that allows you to cancel further persistence logic, if you set the value to true.| 
|deferreds| Array of Deferred objects for asynchronous operations to perform validation on editable data in the technological code. The event handler save the object defined in the technological code will be executed only if all Deferred objects attached to the given array, go into the completed state (i.e. a method called resolve() on each of them). In the case of the translation of at least one of the Deferred object's state to "canceled" verification of edited data in the technological code and the subsequent initiation of the process to save the object will fail, but will fail the event handler onCheckFailed.| 

## Example of adding additional logic when the object is saved 

```javascript

$(function() {
    // Register custom event handler onCheckStarts. 
    // Note! Custom checks are registered as event handlers {{onCheckStarts}} are executed to process checks. 
    $.icsEditForm.attachEventHandler(function(p) {
        var d1 = $.Deferred();
        p.deferreds.push(d1);
        // Function $.ics.dialog.confirm - asynchronous. 
        // Process the save handler object will be suspended as long as the object d1 will not be switched to the status "completed" or "cancelled". 
        $.ics.dialog.confirm({
            message: 'Данные изменены. Сохранить изменения?',
            callback: function (res) {
                if (!res) {
                    // In this case, to perform the verification of edited data in the technological code, and then save object in case of successful verification is complete. 
                    d1.reject();
                } else {
                    // In this case will execute the event handler onCheckFailed defined below. 
                    d1.resolve();
                }
            }
        });
    });
    // Perform actions in case of transfer of the object d1 is in state "cancelled" or in the case of validation errors, the data in the technological code. 
    $.icsEditForm.attachEventHandler(function (p) {
        // ... 
    }, 'onCheckFailed');
});

``` 

## Methods save and saveAndClose 

From a jQuery jquery plugin.icsEditForm there are public methods save and saveAndClose, which are respectively designed to save the edit form, and save, followed by the closure of the page. 

Both methods accept an object with options as parameter. 

|Option |Description |default| 
|---|---|---| 
|showSaveWaitingWindow| Flag indicating whether to display a modal window with a spinner, while waiting to save the form.| true| 
|saveWaitingWindow| Object with settings modal waiting|| 
|saveWaitingWindow.width| Width modal window standby| 310| 
|saveWaitingWindow.height| height of the modal window standby| 110| 
|saveWaitingWindow.title| the title of the modal waiting| 'please Wait'| 
|saveWaitingWindow.contentSelector| CSS selector for the markup element with the contents of the modal waiting| '#hiddenContent'| 
|postBackEventTarget |Value that gets into form.EVENTTARGET.value when you call submit() the form |Value of the attribute 'name' of process button 'Save' (method 'save') or click 'Save and close' (method 'saveAndClose')| 
|postBackEventArgument| Value that gets into form.EVENTARGUMENT.value when you call submit ()|"| 

## an Example of using the save method and handle events of the form 

Suppose that by pressing a button on the application you want to run all the necessary tests of the validity of the form, 
in the case of successful validation, without saving the data object to do a postback, indicating that it occurred by pressing the application button. 

```javascript

$(function() {
        var customSaveBtn = $('#<%= CustomSaveBtn.ClientID %>');
        var customSaveBtnId = customSaveBtn.attr('id');
        var messageColorDropDown = $("#<%= MessageColorDropDown.The ClientID %>");
        var pageForm = $('#pageForm');
        // Handle the event that signals the beginning of the structure. 
        // Do additional application inspection (it will fail to process checks). 
        // Note! If you want to do applied check after the process, you can absolutely likewise handle the event 'onCheckSucceed'. 
        $.icsEditForm.attachEventHandler(function (e) {
            if (e.saveOptions.postBackEventTarget !== customSaveBtnId) {
                // Perform the processing, if the method 'save' was invoked by clicking a process, not applied for the save button. 
                return;
            }
            var selectedColor = $('option:selected', messageColorDropDown).val();
            if (selectedColor === '') {
                alert('Форма не прошла дополнительную прикладную проверку. Не выбран цвет сообщения.');
                // Interrupt further preservation. 
                e.stop = true;
                return;
            }
        }, 'onCheckStarts');
        $.icsEditForm.attachEventHandler(function (e) {
            alert('Форма не прошла проверку. Возможно не заполнены обязательные поля.');
        }, 'onCheckFailed');
        // Handle a click on the application button on the edit form. 
        customSaveBtn.on('click', function () {
            // Call save method of the form to initiate applied and technological validation. 
            // In case of successful completion of all inspections will be made by postpack, in which 
            // postBackEventTarget will be available via Request.Form['__EVENTTARGET'], 
            // postBackEventArgument will be available via Request.Form['__EVENTARGUMENT']. 
            // Note! The preservation of the data object will not happen if postBackEventTarget does not coincide with the 'name' attribute, 
            // process the button 'Save' or 'Save and close'. 
            pageForm.icsEditForm('save', {
                showSaveWaitingWindow: false,
                postBackEventTarget: customSaveBtn.attr('id'),
                postBackEventArgument: ''
            });
            return false;
        });
    });

``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}