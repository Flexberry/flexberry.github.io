---
title: ResponseFilterModule
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_response-filter-module.html
lang: ru
---

# Описание
В сгенерированных веб-приложениях, используется ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule. Он подключается в Web.config.
```
  <system.web>
    <httpModules>
      <add name="ResponseFilterModule" type="ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule, ICSSoft.STORMNET.Web.HttpModules"/>
    </httpModules>
  </system.web>
```
ResponseFilterModule используется для изменения html в конце обработки запроса. Например, в нем просматривается HttpContext на наличие записанных в него данных об исключениях, и по ним генерируется html код для того, чтобы красиво отобразить сообщение об исключении на странице. Подробности на <http://msdn.microsoft.com/ru-ru/library/system.web.httpresponse.filter.aspx>.
# Связанные ошибки
Если у вас не подключен ResponseFilterModul, то все файлы и скрипты, которые вы будете подключать через ContextHelper, не будут выведены на страницу.
# Версия IIS
Если вы используете IIS 7, то нужно помнить, что в нем модули подключаются другим образом, а именно:
```
<system.webServer>    
  <modules>    
      <add name="ResponseFilterModule" type="ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule, ICSSoft.STORMNET.Web.HttpModules"/>
  </modules>    
</system.webServer>  
```