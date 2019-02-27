--- 
title: ResponseFilterModule 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_response-filter-module.html 
lang: en 
autotranslated: true 
hash: 220ee02cadae975e25fb7d818e6ebae49b70f57ebaa462ab89db58ca2c6e0691 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/