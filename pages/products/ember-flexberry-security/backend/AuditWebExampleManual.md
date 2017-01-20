---
title: Пример подключения аудита к существующему Web-приложению без использования перегенерации проекта.
sidebar: product--sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/audit-web-example-manual.html
folder: product--folder
lang: ru
---

# Подключение подсистемы аудита с полной перегенерацией проекта
''[Статья на тему "Пример подключения аудита к существующему Web-приложению с использованием перегенерации проекта"](audit-web-example-manual.html)''

# Подключение подсистемы аудита без полной перегенерации проекта
Алгоритм подключения:
1. Перегенерировать объекты
2. Внести изменения в web.config
3. Добавить инициализацию аудита в Global.asax.cs
4. Подключить недостающие сборки

# Перегенерация объектов
Без перегенерации объектов все равно не обойтись, так как настройки аудита хранятся в классах (подробнее см. [auditWeb.ashx#%D0%A5%D1%80%D0%B0%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5_%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B5%D0%BA_5|здесь] в разделе "Настройка класса").

# Изменения в Web.config
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
указав заместо ... необходимые значения наименования Win-сервиса и строки подключения к базе аудита.

# Инициализация аудита в Global.asax.cs
В файле Global.asax.cs необходимо добавить:

```
using ICSSoft.STORMNET.Business.Audit; 
// ...

protected void Application_Start(object sender, EventArgs e)
{
    // Инициализация сервиса аудита
    AuditSetter.InitAuditService(BridgeToDS.GetDataService());
    // ...
}
```

# Необходимые для работы сборки
* ICSSoft.STORMNET.Tools.dll
* ICSSoft.STORMNET.Business.dll
* ICSSoft.STORMNET.Business.Audit.dll
* Security (сборки с объектами)
* CheckingLibrary.dll
* ICSSoft.STORMNET.DataObject.dll
* LogService

# См. также подключение аудита с использованием перегенерации
[Пример подключения аудита к существующему Win-приложению с использованием перегенерации проекта.](audit-win-example-manual.html)
