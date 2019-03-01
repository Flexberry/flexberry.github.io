--- 
title: Monitors tasks Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Archive 
toc: true 
permalink: en/fa_monitor-tasks.html 
lang: en 
autotranslated: true 
hash: 15b74000d5d9b1cf0edbfdd98ef6778954c1d207869a0342b395488b2d20949b 
--- 

In Flexberry ASP.NET there are two classes that implement the interface [task monitor](fo_business-task-monitor.html) `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`. 
About how to use the functionality of one of them, written in the article [Creation and connection monitor task](fo_creating-connection-bt-monitor.html). The monitors described below. 

## EventTaskMonitor.EventTaskMon 

The monitor task that displays tasks in the Windows Application Log. 

## ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon 

[ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon](fa_connect-task-monitor.html) - [task monitor](fo_business-task-monitor.html), which displays the tasks in the dictionary (`object -> string`) stored in the HttpContext. Tasks are stored in the id specified when calling methods `IBusinessTaskMonitor`. The key under which is stored the dictionary contains field `ASPContextBusinessTaskMon.ItemsKeyBTMonitorTasks` (it is equal to `"ItemsKeyBTMonitorTasks"`). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/