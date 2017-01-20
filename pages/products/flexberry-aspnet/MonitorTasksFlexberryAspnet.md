---
title: Мониторы задач Flexberry Web
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Архив
toc: true
permalink: ru/fa_monitor-tasks--Flexberry--web.html
folder: products/flexberry-aspnet/
lang: ru
---

# Мониторы задач Flexberry Web
В Flexberry Web существует два класса, реализующих интерфейс [монитора задач](business-task-monitor.html) `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`.
О том, как использовать функциональность одного из них, написано в статье [Создание и подключение монитора задач](creating-and-connection--business-task-monitor.html). Сами мониторы описаны ниже.

## EventTaskMonitor.EventTaskMon
Монитор задач, который отображает задачи в Windows Application Log.

## ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon
[ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon](event-task-mon-data-base-example.html) - [монитор задач](business-task-monitor.html), который отображает задачи в словарь (`object -> string`), хранящийся в HttpContext. В нем задачи хранятся по id, указанному при вызове методов `IBusinessTaskMonitor`. Ключ, по которому хранится словарь, содержится в поле `ASPContextBusinessTaskMon.ItemsKeyBTMonitorTasks` (он равен `"ItemsKeyBTMonitorTasks"`).

 

