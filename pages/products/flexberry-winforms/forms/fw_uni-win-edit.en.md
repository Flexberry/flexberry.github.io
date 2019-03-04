--- 
title: uniform edit 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: explains how to use the universal edit form object 
toc: true 
permalink: en/fw_uni-win-edit.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: e215a71fec38cf5a8b97a476aaf8942eec22e55f5c6adecb1e7da808463b475b 
--- 

Of course, you can `вручную` to place controls and link them via `EditManager` with data objects, however, to facilitate this work, there is no universal form of editing, which automatically, according to the type of the object data, views, places the controls and provides a user interface for editing a data object (toolbar, status line). 

In order to use the generic edit form, you must: 
* To design a universal form for editing, passing in a constructor parameter representation, which must be edited: 

```csharp
StormNetUI.IDpdEditForm editcont = new StormNetUI.UniWinEdit(new StormNet.View[]{viewforedit});
``` 

* Set handlers for events (at least `CloseEvent` to be able to close the form and `SaveEvent` to `отловить` call user save). 
* Edit to invoke the method, passing the data object. 

It should be understood that the form implements brand `голый` user interface, the whole reaction from the user is `наружу` forms through events, respectively, this management form is made through a set of methods. So when the user just closes the edit form, a message is sent to `CloseEvent`, but the form is not closed. To close the form, you must call `Close`. 



An example of an event handler, CloseEvent: 

```csharp
private void ContainerCloseHandler (object sender, StormNetUI.CloseEventArgs args)
		{
			((StormNetUI.IDpdForm)sender).CloseForm();
		}
``` 

{% include note.html content="If you need to make the transition to generated form from universal with controls ICustomizable, the Customize method must be invoked manually in the overloaded Edit method." %}


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/