--- 
title: the Core JavaScript API 
sidebar: flexberry-aspnet_sidebar 
keywords: JavaScript API 
toc: true 
permalink: en/fa_js-api-core.html 
lang: en 
autotranslated: true 
hash: c34e42905300a45ab7e1967d160acfea383d458c35e1597cc9095ed2cbb283c4 
--- 

## Dialog box 

All methods for working with dialog boxes are in the object 

```javascript
$.ics.dialog
``` 

#### confirm 

Dialog box to confirm user action: 

```javascript
$.ics.dialog.confirm(options);
``` 

{% include note.html content="a Method is asynchronous. To check the result of the actions of the user must install the `callback`." %} 

To support all functional classes with Helicobacter pylori opportunities should be connected to the file `jquery.alerts.js`. If the plugin is not connected it will be used by the built-in browser capabilities with the reduced functionality. 

Options: 

| Name | Description | default| 
|:--------------|:----------------|:------------------| 
| `message` | the query Text. | The empty string| 
| `title` | Header of the message pane. | The empty string| 
| `callback` | Function to handle the result of the user's choice. The first argument of the function is the result of the user's choice: `true` button `OK` and `false` button `Cancel`. | `undefined`| 
| `okButtonText` | text for the button `OK`. | `$.ics.configuration.dialog.okButtonText`| 
| `cancelButtonText` | text for the button `Cancel`. | `$.ics.configuration.dialog.cancelButtonText`| 

Example usage: 

```javascript
$.ics.dialog.confirm({
    message: 'Удалить файл?',
    title: 'Подтверждение удаления файла',
    callback: function (res) {
        if (res) {
            // When OK is pressed. 
            deleteFile();
        } else {
            // When pressing Cancel. 
            cancelFileDeleting();
        }
    }
});
``` 

The result (with connected plugin `jquery.alerts`): 
![](/images/pages/products/flexberry-aspnet/jsapi_dialog_confirm.png) 

The result (without plugin `jquery.alerts`): 
![](/images/pages/products/flexberry-aspnet/jsapi_dialog_confirm_without_plugin.png) 

#### alert 

Dialog box to display information to the user: 

```javascript
$.ics.dialog.alert(options);
``` 

{% include note.html content="a Method is asynchronous. To check the result of the actions of the user must install the `callback`." %} 

To support all functional classes with Helicobacter pylori opportunities should be connected to the file `jquery.alerts.js`. If the plugin is not connected it will be used by the built-in browser capabilities with the reduced functionality. 

Options: 
| Name | Description | default| 
|:--------------|:----------------|:------------------| 
| `message` | Text messages. | `undefined` (empty string)| 
| `title` | Header of the message pane. | `undefined` (empty string)| 
| `callback` | callback Function after the window is closed by the user. Takes no arguments. | `undefined`| 

If instead the options will be passed to the text, they will use the default options with the specified message. 

Example usage: 

```javascript
$.ics.dialog.alert({
    message: 'Файл успешно удалён!',
    title: 'Внимание',
    callback: function () {
        // After closing the window. 
        // ... 
    }
});
``` 

The result (with connected plugin `jquery.alerts`): 
![](/images/pages/products/flexberry-aspnet/jsapi_dialog_alert.png) 

The result (without plugin `jquery.alerts`): 
![](/images/pages/products/flexberry-aspnet/jsapi_dialog_alert_without_plugin.png) 

#### modal 

Dialog modal window: 
```javascript
$.ics.dialog.modal(options);
``` 

{% include note.html content="a Method is asynchronous. To check the result of the actions of the user must install the `callback`." %} 

For the operation method be sure to use a plug-`jQuery Thickbox`. 

Options: 

| Name | Description | default| 
|:--------------|:----------------|:------------------| 
| `content` | Content displayed in the window. Can be HTML. | `undefined` (empty string)| 
| `href` | Link to the page you want to display. This parameter has the higher priority compared to `content`. | `undefined` (empty string)| 
| `title` | Title of the window. | `undefined` (empty string)| 
| `callback` | callback Function before the window is closed by the user. The transmitted argument allows to cancel closing the window. | `undefined`| 
| `width` | Width of window in pixels. | `$.ics.configuration.dialog.width`| 
| `height` | Width of window in pixels. | `$.ics.configuration.dialog.height`|- 
| `modal` | Flag modal window. In the case `true` there is no possibility to close the window. | `true`| 

The returned object: 

| Name | Description| 
|:--------|:---------------------------------------------| 
| `close` | Method to close the window.| 
| `closed` | Flag closed window to determine if it's open another window.| 
| `context` | Context for the displayed window. If data is displayed on the link, you `context` refers to an object `window` open window, otherwise the object `window` the current window.| 

Example usage: 

```javascript
var modal = $.ics.dialog.modal({
    width: 500,
    content: '<strong>Выполняется сохранение...</strong>',
    title: 'Ждите!',
    modal: false,
    callback: function() {
        // After closing the window. 
        // ... 
    }
});

setTimeout(function() {
    modal.close();
}, 4000);
``` 

The result: 
![](/images/pages/products/flexberry-aspnet/jsapi_dialog_modal.png) 


## Special functions 

#### generateUniqueId 

Generating a unique identifier for the DOM element. 
```javascript
$.ics.generateUniqueId(prefix);
``` 

Parameters: 

| Name | Description | default| 
|:--------------|:----------------|:------------------| 
| `prefix` || Prefix for the generated ID. || `undefined` (empty string) 
|- 
|} 

Example usage: 
```javascript
var uniqueId = $.ics.generateUniqueId('element_'); // element_032452 
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}