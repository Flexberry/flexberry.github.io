--- 
title: Localization of user interface 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: as Stated in the application to establish the localization of 
toc: true 
permalink: en/fw_localization-ui.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 8604c2851ab0b7888cca5a96849bfd4a0d3343e4ca479860125362bedfbcfa41 
--- 

Localization consists of the following two tasks: 
* Localization `WinForms` (directly). 
* Localization string constants written directly into the code (messages to the user, etc.) 

The first is provided by means `Microsoft Visual Studio .NET`. 
For the second `Flexberry Platform` there are the following tools: 
* Receiving-to set the current culture `.Net Framework (ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian(), ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICulture(), ICSSoft.STORMNET.Windows.Forms.WinApplication.GetUICulture())`; 
* The ability to create class-a localizer, where do the calls to retrieve the relevant rows, as well as connect any standard локализатор; 
* Getting processed the localizer lines. 

So, in order to arrange in your app localization, you must: 
1. Select standard `ICSSoft.STORMNET.Windows.Forms.XMLLocalizator`, or create your own (to implement the interface `ICSSoft.STORMNET.Windows.Forms. ILocalizator`) 
0. To install the Assembly локализатор; 
0. If there is a need to establish clearly культуру; 
0. Replace(write) all the constants in your code in calls to ICSSoft.STORMNET.Windows.Forms.WinApplication. GetLocalString. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}