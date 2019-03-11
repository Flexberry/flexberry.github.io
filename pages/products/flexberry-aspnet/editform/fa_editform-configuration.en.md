--- 
title: configuring the Web edit form 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_editform-configuration.html 
lang: en 
autotranslated: true 
hash: bd0f7cea5782681c17c1457fb14d52146354f6c8b89b708518e5a0000bcf406f 
--- 

## Settings and jquery.icsEditForm.js 

Settings edit form stored in a javascript plugin'e that are in the file jquery.icsEditForm.js. 

By default, they are as follows: 

```javascript
configuration: {
            trackChanges: true,
            trackIncludeSelector: 'input,textarea,select',
            trackExcludeSelector: undefined,
            trackExcludeContainerSelector: undefined,
            saveBtnSelector: 'input[id$="_SaveBtn"]',
            saveAndCloseBtnSelector: 'input[id$="_SaveAndCloseBtn"]',
            cancelBtnSelector: 'input[id$="_CancelBtn"]',
            objectKey: '',
            currentUserName: '',
            declineReadonlyLink: '',
            lockServiceUrl: '/AjaxDataService.asmx/LockObject',
            lockTimeout: '',
            pinToolbar: true,
            pinToolbarSelector: '.ics-sticky',
            // Preserve the position in the horizontal scrolling window, false for performance reasons 
            pinToolbarStickToLeftBorder: false,
            showValidationErrorMessage: false,
            saveWaitingWindow: {
                width: 300,
                height: 115,
                title: 'Please wait',
                contentSelector: '#hiddenContent'
            },
            saveWge: {
                messages: {
                    handlersError: 'Incorrect data in related objects!',
                    confirmSavingMaster: 'You haven\'t saved related objects. Do you want to continue saving master object?',
                    confirmSavingButtonContinue: 'Continue',
                    confirmSavingButtonCancel: 'Cancel'
                }
            },
            messages: {
                objectChanged: 'The object has been changed. Do you want to save changes?',
                objectBlockedByUser: 'The object is blocked by user ',
                objectBlockedByUnknown: 'The object is blocked',
                openReadOnly: 'Do you want to open it only for reading?',
                validationErrorMessage: 'Not all values are correctly filled',
                okButton: 'OK',
                cancelButton: 'Cancel'
            }
        }
``` 

To an application developer was able to affect these settings with the server side, you create a property of the base form editing `PluginInitSettings`. 

## PluginInitSettings 

The property `PluginInitSettings` is no Setter, but it is declared as `virtual`, therefore, it is possible to override the desired form. 

You have to form that are inherited from `BaseEditForm`, write the following block: 

```csharp
public override object PluginInitSettings
{
    get 
    { 
        return new
            {
                saveBtnSelector = "input[id$=\"SaveBtn\"]",
                saveAndCloseBtnSelector: "input[id$=\"SaveAndCloseBtn\"]",
                messages = new 
                    {
                        okButton = "Good"
                    }        
            };
    }
}
``` 

Putting the overridable properties. 

In the example described above are overridden selector's responsible for the search buttons "Save" and "Save and close" on the edit form for the application of scripts saving the data. Also overrides the inscription on the OK button in the confirmation pop-up window of some action. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}