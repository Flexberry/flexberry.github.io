---
title: Инициализация аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_audit-setter.html
lang: ru
---

# AuditSetter

`AuditSetter` осуществляет [инициализацию настроек аудита](efs_keep-and-use-audit-settings.html).

Инициализацию не обязательно выполнять посредством данного класса, в нём просто собрана наиболее удачная инициализация значениями по умолчанию.

Что происходит при вызове `AuditSetter.InitAuditService(IDataService dataService)`:
# Вычитываются значения из файла конфигурации:
#* "AppNameForAudit" (Имя настройки в конфиг-файле для имени приложения для аудита).
#* "AuditEnabled" (Имя настройки в конфиг-файле для определения, включён ли аудит в приложении).
#* "IsAuditDatabaseLocal" (Имя настройки в конфиг-файле для определения, является ли база данных аудита локальной по отношению к приложению).
#* "AuditConnectionStringName" (Имя настройки в конфиг-файле для имени строки соединения с БД аудита).
#* "AuditWinServiceUrl" (Имя настройки в конфиг-файле для адреса win-сервиса аудита).
#* "WriteSessions" (Имя настройки в конфиг-файле для определения, писать ли сессии по пользователям).
#* "DefaultWriteMode" (Имя настройки в конфиг-файле для режима записи данных аудита по умолчанию).
# Если значения в файле конфигурации некорректны или не заданы, то проставляются следующие значения:
#* "AppNameForAudit" = `"AuditAppName_" + new Random().Next(65536)`.
#* "AuditEnabled" = `false`.
#* "IsAuditDatabaseLocal" = `true`.
#* "AuditConnectionStringName" = `"AuditConnString_" + new Random().Next(65536)`.
#* "AuditWinServiceUrl" = `string.Empty`.
#* "WriteSessions" = `false`.
#* "DefaultWriteMode" = `tWriteMode.Synchronous`.
# Создаётся экземпляр класса [AuditAppSetting](efs_keep-and-use-audit-settings.html), куда записываются полученные настройки.
# Создаётся экземпляр класса [AuditDSSetting](efs_keep-and-use-audit-settings.html), куда передаётся полученный методом `AuditSetter.InitAuditService` [сервис данных](fo_sql-data-service.html) и для него формируется имя вида "&lt;AppNameForAudit&gt;_&lt;AuditConnectionStringName&gt;".

После этого происходит инициализация [AuditService](efs_flexberry-audit-components.html).
```cs
AuditService.InitAuditService(auditAppSetting, new ICSSoft.STORMNET.Business.Audit.Audit());
```
