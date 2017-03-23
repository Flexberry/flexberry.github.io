---
title: Подключение монитора запросов к базе данных
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, БД
toc: true
permalink: ru/fa_event-task-mon-data-base-example.html
folder: products/flexberry-aspnet/
lang: ru

---

* **Платформа:** [Flexberry ASP.NET.](fa_flexberry-asp-net.html)
* **Компонент:** [Мониторы задач Flexberry ASP.NET.](fa_monitor-tasks--Flexberry--web.html)
* **Предназначение:** позволяет посмотреть запросы, которые выполняет сервис данных к хранилищу.

## Монитор запросов к SQL

Зачастую бывает необходимо посмотреть запросы, которые выполняет сервис данных к хранилищу. Чтобы не подключать сторонние приложения (по типу SQL-profiler), можно воспользоваться [монитором задач Flexberry Web](monitor-tasks--Flexberry--web.html).

### Подключение монитора задач

Чтобы мониторить запросы к хранилищу данных можно

* Написать [собственный монитор задач](fo_creating-and-connection--business-task-monitor.html)
* Подключить существующий

Рассмотрим второй вариант:

Чтобы подключить монитор задач ASPContextBusinessTaskMonitor.ASPContextBusinessTaskMon необходимо:

1. Добавить в файл конфигурации Web.Config строчку 

```html
<br><appSettings>
<br><add key="BusinessTaskMonitorType" value="ICSSoft.STORMNET.Web.Tools.WebBusinessTaskMon, ICSSoft.STORMNET.Web.Tools" />
<br></appSettings>
<br>
```

После чего при загрузке любой страницы будет появляться [ErrorBox](fa_web-error-box-and--error-form.html), содержащий все запросы, которые были выполнены при загрузке данной страницы. Если запросов было несколько, они будут помещены на различные "уровни".

К примеру, при загрузке стартовой страницы происходит 2 запроса:

![](/images/pages/products/flexberry-aspnet/aspnet/monitor1.png)

![](/images/pages/products/flexberry-aspnet/aspnet/monitor2.png)

При загрузке списковой формы будет происходить значительно больше запросов:

![](/images/pages/products/flexberry-aspnet/aspnet/monitor3.png)
