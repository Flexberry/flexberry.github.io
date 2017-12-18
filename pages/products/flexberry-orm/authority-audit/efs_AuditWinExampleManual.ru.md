---
title: Пример подключения аудита к существующему Win-приложению без использования перегенерации проекта. 
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_audit-win-example-manual.html
folder: products/ember-flexberry-security/backend/
lang: ru
---

# Подключение подсистемы аудита без полной перегенерации проекта
Алгоритм подключения:
# Перегенерировать объекты
# Внести изменения в app.config
# Добавить инициализацию аудита в main
# Подключить недостающие сборки

# Перегенерация объектов
Без перегенерации объектов все равно не обойтись, так как настройки аудита хранятся в классах (подробнее см. [здесь](fa_audit-web.html) в разделе "Настройка класса").

# Внесение изменений в app.config
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

# Подключение аудита в main
необходимо добавить вызов инициализации с помощью [Инициализация аудита](efs_audit-setter.html).
```

// *** Start programmer edit section *** (TestFS Main())
ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian();

// Инициализация сервиса аудита
AuditSetter.InitAuditService(DataServiceProvider.DataService); 
// *** End programmer edit section *** (TestFS Main())
```

# Подключение сборок
# Подключить к приложению и `DesktopCustomizer` ICSSoft.STORMNET.Business.Audit.dll.
# К приложению подключить сборки Security.

# См. также
[Пример подключения аудита к существующему Web-приложению без использования перегенерации проекта.](fa_audit-web-example-manual.html)
