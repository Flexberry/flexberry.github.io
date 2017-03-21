---
title: ContextHelper
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_context-helper.html
folder: products/flexberry-aspnet/
lang: ru

---

* **Платформа:** [FlexberryASP.NET.](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент:** [Web-контролы и web-компоненты.](fa_web-controls.html)
* **Предназначение:** класс, позволяющий подключать скриты (js), стили (css) в C#-коде.

## Введение

ContextHelper нужен для того, чтобы подключать скриты (js), стили (css) в C#-коде.

{% include warning.html content="ContextHelper устарел и не должен использоваться в новых проектах. Вместо него следует использовать [PageContentManager](fa_page-content-manager.html)." %}

## Пример

```cs
ContextHelper.ПодключитьВнешнийФайл("/shared/script/ListView.js");

string script = string.Format("$('#{0}').rehabShowHide(`cookieName: '{1}'`);", ClientID, CookieName);
ContextHelper.ДобавитьСкриптВDocumentReady(script);
```
