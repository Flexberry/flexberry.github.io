---
title: ResponseFilterModule
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_response-filter-module.html
lang: ru
---

В сгенерированных веб-приложениях, используется `ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule`. Он подключается в `Web.config`.

```xml
  <system.web>
    <httpModules>
      <add name="ResponseFilterModule" type="ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule, ICSSoft.STORMNET.Web.HttpModules"/>
    </httpModules>
  </system.web>
```

`ResponseFilterModule` используется для изменения html в конце обработки запроса. Например, в нем просматривается `HttpContext` на наличие записанных в него данных об исключениях, и по ним генерируется html код для того, чтобы красиво отобразить сообщение об исключении на странице. Подробности на [msdn.microsoft.com](http://msdn.microsoft.com/ru-ru/library/system.web.httpresponse.filter.aspx).

## Связанные ошибки

Если не подключен `ResponseFilterModul`, то все файлы и скрипты, которые будут подключены через `ContextHelper`, _не будут_ выведены на страницу.

## Версия IIS

Если используется `IIS 7`, то нужно помнить, что в нем модули подключаются другим образом, а именно:

```xml
<system.webServer>    
  <modules>    
      <add name="ResponseFilterModule" type="ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule, ICSSoft.STORMNET.Web.HttpModules"/>
  </modules>    
</system.webServer>  
```
