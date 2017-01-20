---
title: Инициализация аудита
sidebar: product--sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/audit-setter.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Audit](audit-web.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.Audit.dll
* '''Предназначение''': Описан класс `AuditSetter`, реализующий инициализацию сервиса аудита.
</td>
</tr></tbody></table></a>
</div>

# AuditSetter
`AuditSetter` осуществляет [инициализацию настроек аудита](keep-and-use-audit-settings.html).

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
# Создаётся экземпляр класса `[AuditAppSetting](keep-and-use-audit-settings.html)`, куда записываются полученные настройки.
# Создаётся экземпляр класса `[AuditDSSetting](keep-and-use-audit-settings.html)`, куда передаётся полученный методом `AuditSetter.InitAuditService` [сервис данных](s-q-l-data-service.html) и для него формируется имя вида "&lt;AppNameForAudit&gt;_&lt;AuditConnectionStringName&gt;".

После этого происходит инициализация [AuditService](flexberry-audit-components.html).
```cs
AuditService.InitAuditService(auditAppSetting, new ICSSoft.STORMNET.Business.Audit.Audit());
```
