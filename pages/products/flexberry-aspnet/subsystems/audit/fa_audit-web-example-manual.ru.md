---
title: Пример подключения аудита к существующему Web-приложению без использования перегенерации проекта
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/fa_audit-web-example-manual.html
lang: ru
---

## Подключение подсистемы аудита с полной перегенерацией проекта

[Статья на тему "Пример подключения аудита к существующему Web-приложению с использованием перегенерации проекта"](fa_audit-web-example.html).

## Подключение подсистемы аудита без полной перегенерации проекта

Алгоритм подключения:

1. Перегенерировать объекты
2. Внести изменения в `web.config`
3. Добавить инициализацию аудита в `Global.asax.cs`
4. Подключить недостающие сборки

## Перегенерация объектов

Перегенерация объектов необходима, так как настройки аудита хранятся в классах (подробнее [в статье Аудит для Web-приложений](fa_audit-web.html).

## Изменения в Web.config

В блок `Configuration` - `appSettings` необходимо добавить следующие строчки:

```xml
<add key="AuditEnabled" value="True" />
<add key="WriteSessions" value="False" />
<add key="AuditWinServiceUrl" value="" />
<add key="DefaultDSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
<add key="DefaultWriteMode" value="Synchronous" />
<add key="IsAuditDatabaseLocal" value="True" />
```

А также 

```xml
<add key="AppNameForAudit" value="..." />
<add key="AuditConnectionStringName" value="..." />
```

указав заместо ... необходимые значения наименования сервиса и строки подключения к базе аудита.

## Инициализация аудита в Global.asax.cs

В файле `Global.asax.cs` необходимо добавить:

```csharp
using ICSSoft.STORMNET.Business.Audit; 
// ...

protected void Application_Start(object sender, EventArgs e)
{
    // Инициализация сервиса аудита
    AuditSetter.InitAuditService(BridgeToDS.GetDataService());
    // ...
}
```

## Необходимые для работы сборки

* ICSSoft.STORMNET.Tools.dll
* ICSSoft.STORMNET.Business.dll
* ICSSoft.STORMNET.Business.Audit.dll
* Security (сборки с объектами)
* CheckingLibrary.dll
* ICSSoft.STORMNET.DataObject.dll
* LogService
