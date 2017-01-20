---
title: Подключение монитора запросов к базе данных
sidebar: product--sidebar
keywords: Flexberry ASP-NET, БД
toc: true
permalink: ru/event-task-mon-data-base-example.html
folder: product--folder
lang: ru
---

# Монитор запросов к SQL
Зачастую бывает необходимо посмотреть запросы, которые выполняет сервис данных к хранилищу. Чтобы не подключать сторонние приложения (по типу SQL-profiler), можно воспользоваться [монитором задач Flexberry Web](monitor-tasks--Flexberry--web.html).

## Подключение монитора задач
Чтобы мониторить запросы к хранилищу данных можно 
# Написать [собственный монитор задач](creating-and-connection--business-task-monitor.html)
# Подключить существующий

Рассмотрим второй вариант:

Чтобы подключить монитор задач `ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon` необходимо:

# Добавить в файл конфигурации `Web.Config` строчку 
```
<add key="BusinessTaskMonitorType" value="ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon, ASPContextBusinessTaskMonitor" />
```
В раздел `AppSettings`

После чего при загрузке любой страницы будет появляться `[ErrorBox](web-error-box-and--error-form.html)`, содержащий все запросы, которые были выполнены при загрузке данной страницы. Если запросов было несколько, они будут помещены на различные "уровни".

К примеру, при загрузке стартовой страницы происходит 2 запроса:

![](/images/pages/img/Monitors/monitor1.png)

![](/images/pages/img/Monitors/monitor2.png)

При загрузке списковой формы будет происходить значительно больше запросов:

![](/images/pages/img/Monitors/monitor3.png)

