--- 
title: configuring control panel for lists 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_olv-toolbar.html 
lang: en 
autotranslated: true 
hash: 58aa3776753079db0dbd2157065f5ca6c0add9c891f3ab184ccee3378f9e6798 
summary: the Use of technology and custom buttons in the toolbar 
--- 

Control panel (toolbar) [Flexberry Objectlistview](ef_object-list-view.html) based on technological control `olv-toolbar`. 

The configuration of the control panel occurs via the component `Flexberry Objectlistview`. 

## Button create a new record 

```hbs
{% raw %}
{{flexberry-objectlistview 
  createNewButton = true
}}{% endraw %}
``` 

`createNewButton` - a flag that determines whether to display the button to create on the control panel. 

## refresh Button 

```hbs
{% raw %}
{{flexberry-objectlistview
  refreshButton = true
}}{% endraw %}
``` 

`refreshButton` - a flag that determines whether to display the refresh button on the control panel. 

## Button delete the selected records 

```hbs
{% raw %}{{flexberry-objectlistview
	componentName = "..."
	deleteButton = true
	showCheckBoxInRow = true
	...
}}{% endraw %}
``` 

In order to start to function the delete button selected records, you must define the following properties: 

* `componentName` is the name of the control (used to identify the component parts of control that communicate through the [place](http://emberjs.com/api/classes/Ember.inject.html#method_service)). For example, the values for the list of records of type "employee" can specify "employeesFlexberryObjectListView". 
* `deleteButton` - a flag that determines whether to display the delete button on the control panel. 
* `showCheckBoxInRow` - a flag that determines whether to display the checkbox in the row 

{% include note.html content="If Flexberry Objectlistview, there was not one row, the delete button is not active. When there are marked records, the delete button becomes available." %} 

Deleting information is sent to the server to save the changes. 

## Button custom settings 

When the value of the attribute `colsConfigButton=true` on the control panel displays control buttons custom settings. 

```hbs
{% raw %}{{flexberry-objectlistview
	componentName = "..."
	colsConfigButton=true
	...
}}{% endraw %}
``` 

Details about the functionality of custom settings can be found in the article [Service user settings](ef_model-user-settings-service.html). 

### Add custom buttons to the control panel 

On the control panel, you can add buttons that implement custom logic. 
To add a custom button, perform the following steps: 

1.In the relevant application [controller](ef_controller.html) to define a calculated property with an arbitrary name, for example, `сustomButtons`, which returns an array of structs of the form: 

```javascript
{
 buttonName: '...',
 buttonAction: '...',
 buttonClasses: '...',
 disabled: true,
}
``` 

* `buttonName` - the name of the button on the user interface. If passed a null value, the button name is 'UserButton'. 
* `buttonAction` - the name of the action (action), which will be called by the button (when the action is used [sendAction](http://emberjs.com/api/classes/Ember.Component.html#method_sendAction), so the handler can be defined as in [controller](ef_controller.html) and the corresponding [roat](ef_route.html)). If passed null, then the browser console displays error message. It is advisable to call actions with the prefix "userButtonAction" so you don't accidentally grind a property of the control when this action. 
* `buttonClasses` classes that you want to add to the new custom button. 
* `disabled` - Boolean property, responsible for the state of the button if `true` the button is inactive, otherwise active. 

2.In the application controller (or [roat](ef_route.html)) to define an event handler whose name was specified in `buttonAction`. 

3.In the [template](ef_template.html) the appropriate form of the component `flexberry-objectlistview` to define properties: 

```hbs
{% raw %}
{{flexberry-objectlistview
  // ... 
	customButtons=customButtons  
	userButtonAction1='userButtonAction1'
	userButtonAction2='userButtonAction2'
	// ... 
	userButtonActionN='userButtonActionN'
}}{% endraw %}
``` 

* `customButtons` - defining the property, where you can take an array. 
* `userButtonAction1`, `userButtonAction2`, ... `userButtonActionN` - check action defined in a property `buttonAction` ([no such "check"](http://emberigniter.com/send-action-does-not-fire/) action may not be called and Ember will not give error messages). 

## Example of using custom buttons in the list 

For example, you want to add a custom button that, when clicked, will display a message to the user. 

1.In page template to set the title in a variable "header". 

```hbs
{% raw %}<h3 class="ui header">Страница с пользовательскими кнопками</h3>
<div class="row">
  {{flexberry-objectlistview
    // ... 
    customButtons=customButtons
    userButtonAction1='userButtonAction1'
    userButtonAction2='userButtonAction2'
    // ... 
    userButtonActionN='userButtonActionN'
  }}
</div>
<div class="row">{{messageForUser}}</div>{% endraw %}
``` 

2.In the controller to determine the required variables, set the localizable property of the computable "customButtons", which will return an array of descriptions of the custom buttons (in this case, one button) and the action "userButtonActionTest", which will handle the click on the button. 

```javascript
import Ember from 'ember';
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
  /** 
Property to count user clicks on a button. 

@property clickCounter 
@type Number 
@default 1 
*/
  clickCounter: 1,

  /** 
Property to show message after user click on user button. 

@property messageForUser 
@type String 
*/
  messageForUser: undefined,

  /** 
Property to form an array of special structures of custom user buttons. 

@property customButtons 
@type Array 
*/
  customButtons: Ember.computed('i18n.locale', function() {
    let i18n = this.get('i18n');
    return [{
      buttonName: i18n.t('forms.components-examples.flexberry-objectlistview.toolbar-custom-buttons-example.custom-button-name'),
      buttonAction: 'userButtonActionTest',
      buttonClasses: 'my-test-user-button test-click-button'
    }];
  }),

  actions: {
    /** 
Handler for user click on custom button. 

@method userButtonActionTest 
*/
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

3.In the template to specify a property to get custom buttons, and zaregistrovat a custom action. 

```hbs
{% raw %}<h3>{{header}}</h3>

<div class="row">
  {{flexberry-objectlistview
    // ... 
    customButtons=customButtons
    userButtonActionTest='userButtonActionTest'
  }}
</div>{% endraw %}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}