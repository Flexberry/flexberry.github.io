--- 
title: Monitors tasks Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Archive 
toc: true 
permalink: en/fa_monitor-tasks.html 
lang: en 
autotranslated: true 
hash: 2ffe826c136ae47a7ccdc73d9513da721bccdf56420b5dfd0a0374f06555332f 
--- 

In Flexberry ASP.NET there are two classes that implement the interface [task monitor](fo_business-task-monitor.html) `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`. 
About how to use the functionality of one of them, written in the article [Creation and connection monitor task](fo_creating-connection-bt-monitor.html). The monitors described below. 

## EventTaskMonitor.EventTaskMon 

The monitor task that displays tasks in the Windows Application Log. 

## ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon 

[ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon](fa_connect-task-monitor.html) - [task monitor](fo_business-task-monitor.html), which displays the tasks in the dictionary (`object -> string`) stored in the HttpContext. Tasks are stored in the id specified when calling methods `IBusinessTaskMonitor`. The key under which is stored the dictionary contains field `ASPContextBusinessTaskMon.ItemsKeyBTMonitorTasks` (it is equal to `"ItemsKeyBTMonitorTasks"`). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}