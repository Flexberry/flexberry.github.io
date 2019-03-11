--- 
title: monitor Connection requests to the database 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP NET database 
toc: true 
permalink: en/fa_connect-task-monitor.html 
lang: en 
autotranslated: true 
hash: 9e372a03c44e72c82889a5833d9d3f5f908b488776a9d7f25032bcf75c5de26a 
--- 

## Monitor queries to SQL 

It is often necessary to see the queries that are performed by the service data to the repository. Not to connect third-party applications (like SQL profiler), you can use the [monitor task Flexberry ASP.NET](fa_monitor-tasks.html). 

### monitor Connection task 

To monitor a data warehouse query can 

* Write [private task monitor](fo_creating-connection-bt-monitor.html) 
* Attach existing 

Read more about the second option: 

To connect the monitor task `ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon` should: 

1. To add to the configuration file Web.Config line 

```html
<br><appSettings>
<br><add key="BusinessTaskMonitorType" value="ICSSoft.STORMNET.Web.Tools.WebBusinessTaskMon, ICSSoft.STORMNET.Web.Tools" />
<br></appSettings>
<br>
``` 

Then when loading any page will appear [ErrorBox](fa_exception-handling.html) containing all queries that were executed when loading the page. If requests were few, they will be placed on different "levels". 

For example, when loading the start page 2 request: 

![](/images/pages/products/flexberry-aspnet/aspnet/monitor1.png) 

![](/images/pages/products/flexberry-aspnet/aspnet/monitor2.png) 

When you load list forms will be significantly more requests: 

![](/images/pages/products/flexberry-aspnet/aspnet/monitor3.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}