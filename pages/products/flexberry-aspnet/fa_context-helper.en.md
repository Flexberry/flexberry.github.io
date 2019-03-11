--- 
title: ContextHelper 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_context-helper.html 
lang: en 
autotranslated: true 
hash: 3a12a7bcdd21d1cc20c6be841df10e351fbf4c37f204642ba742c7185da4fba0 
--- 

ContextHelper is needed in order to connect skriti (js), styles (css) in C# code. 

{% include warning.html content="ContextHelper is deprecated and should not be used in new projects. Instead, use [PageContentManager](fa_page-content-manager.html)." %} 

## Example 

```csharp
ContextHelper.ПодключитьВнешнийФайл("/shared/script/ListView.js");

string script = string.Format("$('#{0}').rehabShowHide(`cookieName: '{1}'`);", ClientID, CookieName);
ContextHelper.ДобавитьСкриптВDocumentReady(script);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}