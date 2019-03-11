--- 
title: ResponseFilterModule 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_response-filter-module.html 
lang: en 
autotranslated: true 
hash: 28fc1393eb30d00fbf115808a70c2f9e118d652508c0cd3394b74c7c0d6d3572 
--- 

In the generated web applications used `ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule`. It connects to `Web.config`. 

```xml
  <system.web>
    <httpModules>
      <add name="ResponseFilterModule" type="ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule, ICSSoft.STORMNET.Web.HttpModules"/>
    </httpModules>
  </system.web>
``` 

`ResponseFilterModule` is used to change the html at the end of request processing. For example, it can be seen `HttpContext` the presence of recorded data about exceptions, and it generates the html code to nicely display the exception message on the page. Details [msdn.microsoft.com](http://msdn.microsoft.com/ru-ru/library/system.web.httpresponse.filter.aspx). 

## Related errors 

If not connected `ResponseFilterModul`, all the files and scripts that will be connected via `ContextHelper`, _not_ budut displayed on the page. 

## Version of IIS 

If you use `IIS 7`, you need to remember is that it's modules are connected in another way, namely: 

```xml
<system.webServer>    
  <modules>    
      <add name="ResponseFilterModule" type="ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule, ICSSoft.STORMNET.Web.HttpModules"/>
  </modules>    
</system.webServer>  
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}