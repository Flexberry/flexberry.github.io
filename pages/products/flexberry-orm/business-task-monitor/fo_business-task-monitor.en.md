--- 
title: Monitor task 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, task tracking 
summary: Functions and primetimer use of the monitor task 
toc: true 
permalink: en/fo_business-task-monitor.html 
lang: en 
autotranslated: true 
hash: 09b3bc761a9457ade297bc1d1faec58e19c76d3c2bd2ffcbe67bb7ed70b59912 
--- 

## Description 

Is convenient to connect to the application to a certain task monitor when you can track tasks (calls some methods), watching the whole process of working anywhere (in a separate window, in the log file). An example is the monitoring of the SQL queries that perform [data services](fo_data-service.html) in the vault. 

## connecting the monitor task to the application 

In order to connect any monitor task, you must do one of the following: 

* Code to initialize a static property `ICSSoft.STORMNET.Business.BusinessTaskMonitor.TaskMonitor` an instance of the class monitor tasks. 
* Specify in the settings `config` file of the application configuration `BusinessTaskMonitorType` — type class, which actually is a monitor task. **This is the main way of connection to monitor**. 

### monitor the status of tasks via config 

{% include important.html content="the Assembly with the task monitor must be located in the same directory as the application." %} 

For example, the generated Assembly `CustomTaskMon` where the identified monitor tasks `CustomTaskMon.EventTaskMon`, then the connection configuration file the following: 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <appSettings>
    <add key="BusinessTaskMonitorType" value="CustomTaskMon.EventTaskMon, CustomTaskMon, Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
  </appSettings>
</configuration>
``` 

## Record of actions in the task monitor 

There is a class `ICSSoft.STORMNET.Business.BusinessTaskMonitor`, with a set of static methods identical to those described in `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`. These methods and should be used if there is a need to write to the monitor task, any of their actions. 

For example, to start the action, you must call the `BeginTask`. 

## the List of standard monitors task Flexberry ORM 

* [Service record SQL-scripts change data](fo_changes-sql-bt-monitor.html) - used to obtain scripts modifying data, working through the mechanism of the monitor task. 

## Creating your own monitor task 

Described in [Creating and connecting custom task monitor](fo_creating-connection-bt-monitor.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}