---
title: Монитор задач
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, отслеживание задач
summary: Функции и примеример использования монитора задач
toc: true
permalink: ru/fo_business-task-monitor.html
lang: ru
---

## Описание

Бывает удобным подключить к приложению некоторый монитор задач, когда можно отслеживать задачи (вызовы некоторых методов), наблюдая весь процесс работы где-либо (в отдельном окне, в лог-файле). Примером может служить мониторинг SQL-запросов, которые выполняют [сервисы данных](fo_data-service.html) в хранилище.

## Подключение монитора задач к приложению

Для того чтобы подключить любой монитор задач, необходимо выполнить одно из следующего:

* В коде проинициализировать статическое свойство `ICSSoft.STORMNET.Business.BusinessTaskMonitor.TaskMonitor` экземпляром класса — монитора задач.
* Указать в настройках `config`-файла приложения настройку `BusinessTaskMonitorType` — тип класса, который собственно и является монитором задач. **Это основной способ подключения монитора**.

### Подключение монитора задач через config

{% include important.html content="Сборка с монитором задач должна обязательно находиться в том же каталоге, что и приложение." %}

Например, создана сборка `CustomTaskMon`, где определён монитор задач `CustomTaskMon.EventTaskMon`, тогда подключение в файле конфигурации следующее:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <appSettings>
    <add key="BusinessTaskMonitorType" value="CustomTaskMon.EventTaskMon, CustomTaskMon, Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
  </appSettings>
</configuration>
```

## Запись действий в монитор задач

Существует класс `ICSSoft.STORMNET.Business.BusinessTaskMonitor`, с набором статических методов, идентичных описанным в `ICSSoft.STORMNET.Business.IBusinessTaskMonitor`. Этими методами и нужно пользоваться, если есть потребность писать в монитор задач какие-либо свои действия.

Например, для начала действия необходимо вызвать метод `BeginTask`.

## Перечень стандартных мониторов задач Flexberry ORM

* [Сервис записи SQL-скриптов изменения данных](fo_changes-sql-bt-monitor.html) - служит для получения скриптов модификации данных, работает через механизм монитора задач.

## Создание собственного монитора задач

Описано в статье [Создание и подключение пользовательского монитора задач](fo_creating-connection-bt-monitor.html)

## Стандартный монитор задач WinformBusinessTaskMonitor

Один из предоставляемых технологией Flexberry мониторов задач выглядит как окно `WinForms` со списком задач.

Полное имя типа: `ICSSoft.STORMNET.BusinessTaskMonitor.WinformBusinessTaskMonitor`, находится в сборке `ICSSoft.STORMNET.BusinessTaskMonitor.dll`.
