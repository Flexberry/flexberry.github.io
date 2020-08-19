---
title: Monitor task
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, task tracking
summary: Functions and primetimer use of the monitor task
toc: true
permalink: en/fo_business-task-monitor.html
lang: en
autotranslated: true
hash: cbee8114b873d1c5a3e79d39e1bd94e5cd4cc14d1d7ea223dd0943fb39cd0b48
---

## Description

Is convenient to connect to the application to a certain task monitor when you can track tasks (calls some methods), watching the whole process of working anywhere (in a separate window, in the log file). An example is the monitoring of the SQL queries that perform [data services](fo_data-service.html) in the vault.

## The connection monitor task to the application

In order to connect any monitor task, you must do one of the following:

* Code to initialize a static property `ICSSoft.STORMNET.Business.BusinessTaskMonitor.TaskMonitor` an instance of the class monitor tasks.
* Specify in the settings `config` file of the application configuration `BusinessTaskMonitorType` — type class, which actually is a monitor task. **This is the main way of connection to monitor**.

### Monitor the status of tasks via config

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

## The entry actions in the task monitor

There is a class `ICSSoft.STORMNET.Business.BusinessTaskMonitor`, with a set of static methods identical to those described in `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`. These methods and should be used if there is a need to write to the monitor task, any of their actions.

For example, to start the action, you must call the `BeginTask`.

## The list of standard monitors task Flexberry ORM

* [Service record SQL-scripts change data](fo_changes-sql-bt-monitor.html) - used to obtain scripts modifying data, working through the mechanism of the monitor task.

## Creating your own task monitor

Described in [Creating and connecting custom task monitor](fo_creating-connection-bt-monitor.html)

## Standard monitor task WinformBusinessTaskMonitor

One of the conveniences technology Flexberry monitors task looks like a window `WinForms` with a task list.

Full type name: `ICSSoft.STORMNET.BusinessTaskMonitor.WinformBusinessTaskMonitor` is in an Assembly `ICSSoft.STORMNET.BusinessTaskMonitor.dll`.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}