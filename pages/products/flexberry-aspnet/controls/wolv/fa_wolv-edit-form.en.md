--- 
title: Options open edit form when creating or editing a feature in WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-edit-form.html 
lang: en 
autotranslated: true 
hash: 31efa05ed53f2b6f171f624cce37c312e448a2e172a22f727a332dd2596c6ba2 
--- 

[Web edit form](fa_editform.html) with the list form, it opens in the case where it is necessary: 

* Create a new object. 
* Create a new object based on existing one. 
* To edit an existing object. 
* Open an existing object for viewing. 

### Options open 

The form can be opened: 

1. In the current browser window. 
2. In another browser window: 
* the window can be in the form of **adjacent tabs**; 
* or **modal**. 

## setup open edit form 

Accordingly, to configure open [web editor](fa_editform.html): 

1. To decide whether you want to open [the web editor](fa_editform.html) in the current browser window or in a new 
2. If the new, whether it's a new tab or modal window 

Accordingly, after answering these questions you should set 2 settings: 

1. WebObjectListView1.Operations.OpenEditorInNewWindow = true/false; 
2. WebObjectListView1.[Operations](fa_wolv-operations.html).OpenEditorInModalWindow = true/false; 
3. WebObjectListView1.Operations.EditInRow = true; - edit by clicking in the row. 

{% include note.html content="If you set `OpenEditorInNewWindow == false` the second setting makes no sense." %} 

## open the form editing and Read-only mode 

When you open one user, the object is locked. In this time other users can open it read-only. 

If the object to [web editor](fa_editform.html) which tries to log the user blocked, then it will be the message 
"The object is locked by another user. Want to open it read-only?" and the response options "Yes" and "No". 

Accordingly, if you select "Yes" the user opens this form in [read only](fa_read-only-web.html). 

If the user selects "No", then it will return to the list form. 

Visual return will look different for different variants of open [web editor](fa_editform.html): 

1. If the web-editing form was opened in the same browser window, there will be a redirection (redirect) on the list form. 
2. If the web-editing form was opened in the adjacent tab, the tab closes and switches to the tab with the list. 
3. If the web-editing form was opened in a modal window, it will close the modal window. 

{% include note.html content="If the user is not authorized, then when you open them, it will not be unlocked." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}