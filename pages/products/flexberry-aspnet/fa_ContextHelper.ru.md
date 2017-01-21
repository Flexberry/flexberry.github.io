---
title: ContextHelper
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_context-helper.html
folder: products/flexberry-aspnet/
lang: ru
---



# Введение
`ContextHelper` нужен для того, чтобы подключать скриты (js), стили (css) в C#-коде.

<msg type=warning>`ContextHelper` устарел и не должен использоваться в новых проектах. Вместо него следует использовать [PageContentManager](page-content-manager.html).</msg> 

# Пример
```cs
ContextHelper.ПодключитьВнешнийФайл("/shared/script/ListView.js");

string script = string.Format("$('#{0}').rehabShowHide(`cookieName: '{1}'`);", ClientID, CookieName);
ContextHelper.ДобавитьСкриптВDocumentReady(script);
```

