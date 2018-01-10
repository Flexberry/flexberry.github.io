---
title: Мониторы задач Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Архив
toc: true
permalink: ru/fa_monitor-tasks.html
lang: ru
---

В Flexberry ASP.NET существует два класса, реализующих интерфейс [монитора задач](fo_business-task-monitor.html) `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`.
О том, как использовать функциональность одного из них, написано в статье [Создание и подключение монитора задач](fo_creating-connection-bt-monitor.html). Сами мониторы описаны ниже.

## EventTaskMonitor.EventTaskMon

Монитор задач, который отображает задачи в Windows Application Log.

## ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon

[ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon](fa_connect-task-monitor.html) - [монитор задач](fo_business-task-monitor.html), который отображает задачи в словарь (`object -> string`), хранящийся в HttpContext. В нем задачи хранятся по id, указанному при вызове методов `IBusinessTaskMonitor`. Ключ, по которому хранится словарь, содержится в поле `ASPContextBusinessTaskMon.ItemsKeyBTMonitorTasks` (он равен `"ItemsKeyBTMonitorTasks"`).
