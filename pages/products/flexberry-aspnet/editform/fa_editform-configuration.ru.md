---
title: Конфигурирование Web-формы редактирования
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_editform-configuration.html
lang: ru
---

## Настройки и jquery.icsEditForm.js

Настройки формы редактирования хранятся в javascript-plugin'e, находящемся в файле jquery.icsEditForm.js.

По умолчанию они выглядят следующим образом:

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
            // Сохранять ли позицию при горизонтальной прокрутке окна, false - по соображениям производительности
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

Чтобы у прикладного разработчика была возможность повлиять на эти настройки с серверной части приложения, было создано свойство базовой формы редактирования `PluginInitSettings`.

## PluginInitSettings

У свойства `PluginInitSettings` отсутствует Setter, однако, оно объявлено как `virtual`, следовательно, его можно переопределить на нужной форме.

Для этого необходимо на форме, наследующейся от `BaseEditForm`, написать следующий блок:

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
                        okButton = "Хорошо"
                    }        
            };
    }
}
```

Указав переопределяемые свойства.

В примере, описанном выше, переопределяются selector'ы, отвечающие за поиск кнопок "Сохранить" и "Сохранить и закрыть" на форме редактирования для применения к ним скриптов сохранения данных. Также переопределяется надпись на кнопке OK во всплывающем окне подтверждения какого-либо действия.
