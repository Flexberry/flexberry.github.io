---
title: Подключение монитора запросов к базе данных
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, БД
toc: true
permalink: ru/fa_connect-task-monitor.html
lang: ru
---

## Монитор запросов к SQL

Зачастую бывает необходимо посмотреть запросы, которые выполняет сервис данных к хранилищу. Чтобы не подключать сторонние приложения (по типу SQL-profiler), можно воспользоваться [монитором задач Flexberry ASP.NET](fa_monitor-tasks.html).

### Подключение монитора задач

Чтобы мониторить запросы к хранилищу данных можно

* Написать [собственный монитор задач](fo_creating-connection-bt-monitor.html)
* Подключить существующий

Подробнее о втором варианте:

Чтобы подключить монитор задач `ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon` необходимо:

1. Добавить в файл конфигурации Web.Config строчку 

```html
<br><appSettings>
<br><add key="BusinessTaskMonitorType" value="ICSSoft.STORMNET.Web.Tools.WebBusinessTaskMon, ICSSoft.STORMNET.Web.Tools" />
<br></appSettings>
<br>
```

После чего при загрузке любой страницы будет появляться [ErrorBox](fa_exception-handling.html), содержащий все запросы, которые были выполнены при загрузке данной страницы. Если запросов было несколько, они будут помещены на различные "уровни".

К примеру, при загрузке стартовой страницы происходит 2 запроса:

![](/images/pages/products/flexberry-aspnet/aspnet/monitor1.png)

![](/images/pages/products/flexberry-aspnet/aspnet/monitor2.png)

При загрузке списковой формы будет происходить значительно больше запросов:

![](/images/pages/products/flexberry-aspnet/aspnet/monitor3.png)
