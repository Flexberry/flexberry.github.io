--- 
title: ContextHelper 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_context-helper.html 
lang: en 
autotranslated: true 
hash: e1d5a13910d4ac045f4d8c4cec5e17b8ff59af0a484a914c8ba5621f5a8cd57c 
--- 

ContextHelper is needed in order to connect skriti (js), styles (css) in C# code. 

{% include warning.html content="ContextHelper is deprecated and should not be used in new projects. Instead, use [PageContentManager](fa_page-content-manager.html)." %} 

## Example 

```csharp
ContextHelper.ПодключитьВнешнийФайл("/shared/script/ListView.js");

string script = string.Format("$('#{0}').rehabShowHide(`cookieName: '{1}'`);", ClientID, CookieName);
ContextHelper.ДобавитьСкриптВDocumentReady(script);
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/