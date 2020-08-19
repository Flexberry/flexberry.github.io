--- 
title: a Few buttons to save web-form editing 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_few-save-buttons-editform.html 
lang: en 
autotranslated: true 
hash: 0050be3d3f1fa62bd49bbc2ad04bcc3dc90949f82fdbb583aa179879043bb329 
--- 

This article describes the algorithm of adding multiple buttons save to [web editor](fa_editform.html). 

## Algorithm adding a few buttons 

* Add a button to the page layout. 
* Change the selectors in the plugin settings icsEditForm.js. 
* To change the IDs of all buttons on the page so that they "were" the given selector. 

### the Add button in the page markup 

To add a button, simply copy the existing save button and place it to the right place. It looks like this: 

```csharp
<asp:ImageButton ID="ImageButton1" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
``` 

Leave it like this and move on to the next item. 

### Change selectors 

Read more about the plugin settings edit form you can see in [this article](fa_editform-configuration.html). 

Add to the page with the following code: 

```csharp
/// <summary> 
/// Override settings on plugin initialization 
/// </summary> 
public override object PluginInitSettings
{
    get 
    { 
        return new
            {
                saveBtnSelector = "input[id$=\"SaveBtn\"]"
            };
    }
}
``` 

{% include note.html content="If you want to add the button `Сохранить and закрыть`, you need to override `saveAndCloseBtnSelector`. "%} 

### change the ID of the buttons on the form 

By default, saveBtnSelector "looking" button, the ID ends in **SaveBtn** (do not forget what is really on the page it will turn into a **ContentPlaceHolder1_SaveBtn**). 

In the previous step, we override it so that it "searched" ID ending in **SaveBtn**. 

Thus, it is possible not to touch the ID of an existing button, but you have to change the ID we added in step 1 of the button as follows: 

```csharp
<asp:ImageButton ID="BottomSaveBtn" runat="server" SkinID="SaveBtn" OnClick="SaveBtn_Click" AlternateText="<%$ Resources: Resource, Save %>" ValidationGroup="savedoc" />
``` 

As ID you can use any ID that satisfies the selector "input[id$=\"SaveBtn\"]". 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}