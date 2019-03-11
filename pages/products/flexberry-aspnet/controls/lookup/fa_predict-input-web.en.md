--- 
title: Predictive text input in Web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_predict-input-web.html 
lang: en 
autotranslated: true 
hash: 4e2014c4044c4f01d4d4c0b18116b977629e9d937dce2df60e0a54f2f3a95a48 
--- 

## setting predictive LookUp's 

To add to the LookUp predictive, you must: 

1. [E-view](fd_e-view.html) object to specify the master type LookUp'a `standart` and specify the property of the master, which will be the search for predictive 
2. To generate objects 
3. To compile the objects 
4. Generate ASP.NET app 

__Note__: [method](fw_predict-input.html), is described for Windows-based applications will work for the Web. 

## configuring E-class representation 

To turn a regular LookUp in the LookUp with predictive input, you must specify the master type used LookUp'a. 

1.Open the E-representation of the object and highlight the added master, to be used with predictive 

![](/images/pages/products/flexberry-aspnet/controls/lookup/select-master-web.png) 

2.In the field `Тип лукапа` specify `standart` 

![](/images/pages/products/flexberry-aspnet/controls/lookup/select-type-web.png) 

3.In the field `Свойство мастера` to specify the name of the property which will be the search for predictive. 

__Note__: the property master should be in view. The name of the property is entered from the keyboard. 

![](/images/pages/products/flexberry-aspnet/controls/lookup/select-property-web.png) 

4.Save changes 

### Result 

![](/images/pages/products/flexberry-aspnet/controls/lookup/predict-lookup-web.gif) 

## Principles of selection values 

Values are selected from a field of the wizard, you selected when setting up. Searched for values containing the substring entered by the user. Implemented advanced search for occurrences of the words. 

Suppose that we are given a list: 

```csharp
1) слово
2) пара слов
3) целых три слова
4) триангуляция слова
5) парад слов
``` 

The user enters `три слов` is 3), 4) 

In fact, the request of like `%three words%` looks like `%three%words%`. 

![](/images/pages/products/flexberry-aspnet/controls/lookup/predict.png) 

Read more about search by a substring you can see [in MasterEditorAjaxLookUp] (fa_master-editor-ajax-lookup.html). 

## Predictive in Windows applications 

[Predictive typing in Windows applications](fw_predict-input.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}