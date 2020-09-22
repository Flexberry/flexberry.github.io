---
title: UI Message
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_ui-message.html
lang: en
autotranslated: true
hash: 2eb89e7ddfa6e339b78402895dc35e7638a23c032f2b90806a73e1fc49887ab3
summary: Description, properties, and usage example
---

## Description

The main purpose of __control UI Message__ - displays the status messages of the control. For example, displaying the success/failure of conservation forms, warnings, information, etc.

Message properties are defined in the page layout application.

### The list of properties described in the component UI Message

The property names |Short description
:-----------------|:------------------
`visible`| Display (visibility) of a message, the default `true`.
`floating`| the Effect of "floating" messages, default `false`.
`compact`| Display in a compact (compressed) form, default `false`.
`attached`| Adjacent whether the message to another message or content, default `false`.
`closeable`| Ability to hide the message, default `false`.
`type`| message Type (error, information, etc.), no default (`null`).
`color`| message Color, default is not set (`null`).
`size`| message Size, the default is not set (`null`).
`icon`| Icon for the message, default is not set (`null`). The types of icons available [on the website semantic-ui.com](http://semantic-ui.com/elements/icon.html).
`caption`| message Header by default is not set (`null`).
`message`| Content (text) messages, default is not set (`null`).

Details of the available properties is described [on the website semantic-ui.com](http://semantic-ui.com/collections/message.html).

## Example of display of a message

For example, you must add the error message when you save the page. The markup would look like this:

```hbs
<div class="field">
  {% raw %}{{#ui-message
    caption='Результат проверки'
    type='error'
    message='Операция не выполнена'
    closeable=true
  }}{% endraw %}
</div>
```
The specified message header (`caption`), type (`error`), message content (`message`) and the ability to hide the message (`closable`).
Eventually, the message will look as follows:

![](/images/pages/products/flexberry-ember/ember-flexberry/controls/example-for-ui-message.png)

{% include note.html content="the types __success__, __error__ and __warning__ the display depends on the state forms on which they are located, i.e. the first parent with class ui form. The element with the classes __ui form__ must be a class with the same name as the message type (__success__, __error__ or __warning__) to __ui-message__ is displayed.

Types `positive`, `negative`, `info` displayed and without a parent." %}

Message properties can be combined necessary to solve the task the way (change color, size, add icons, etc.).
However, there are some exceptions to the use of the properties:
* Property `compact` should not apply to property `icon`: a message will be displayed in the standard, not compact.
* Property `floating` should not be used with the property `type`: will not effect pop-UPS (shadow). To give the appearance of the message inherent to a particular type, you can use a property `color`.
* Property `attached` applies only in the case if you specify at least two elements.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}