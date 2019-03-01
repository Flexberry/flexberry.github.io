--- 
title: Custom buttons for lists 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember, buttons, OLV, list 
summary: the Embedding of the buttons in the toolbar and string list 
toc: true 
permalink: en/ef_custom-buttons.html 
lang: en 
autotranslated: true 
hash: 5a1f31801da0168092f3bad3bce40819ceed85d5ab04677598ee60b3ef7463b1 
--- 

## Embedding custom buttons to the toolbar list 

Embedded buttons in the controller form, we need to determine a number of properties: 

```javascript
{
    buttonName: '...', // Displayed name of the button. 
    buttonAction: '...', // Action called by the controller when this button is clicked (should be specified in the template). 
    buttonClasses: '...', // Css class of the button. 
    buttonTitle: '...' // Signature. 
}
``` 

If you want to add a few buttons, then their properties are set in the array: 

```javascript
[{ buttonName: ..., buttonAction: ..., buttonClasses: ... }, {...}, ...]
``` 

To add to the toolbar list custom button in the controller you need to define a method `customButtonsMethod`. For example: 

```javascript
import Ember from 'ember';
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
...
customButtonsMethod: Ember.computed('i18n.locale', function() {
    let i18n = this.get('i18n');
    return [{
        buttonName: i18n.t('forms.components-examples.flexberry-objectlistview.toolbar-custom-buttons-example.custom-button-name'),
        buttonAction: 'userButtonActionTest',
        buttonClasses: 'test-click-button'
        }];
    })
});
``` 

Further, in the controller, you need to specify the event `buttonAction` 

```javascript
...
clickCounter: 1,
messageForUser: undefined,

actions: {
    userButtonActionTest: function() {
    let i18n = this.get('i18n');
    let clickCounter = this.get('clickCounter');
    this.set('clickCounter', clickCounter + 1);
    this.set('messageForUser',
        i18n.t('forms.components-examples.flexberry-objectlistview.toolbar-custom-buttons-example.custom-message').string +
        ' ' + clickCounter);
    }
}
});
``` 

Certain methods and properties should be listed in the template list: 

```hbs
{% raw %}{{flexberry-objectlistview
...
customButtons=customButtonsMethod
userButtonActionTest='userButtonActionTest'
}}
{% endraw %}
``` 

## Embedding custom buttons to the rows list 

Custom buttons for the strings in the list are created on a similar principle. Event, as for the buttons in the toolbar can be set to a string. For example: 

```javascript
...
actions: {
    userButtonInRowActionTest(model) {
      this.set('modelFromClickedRow', model);
    },
});
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/