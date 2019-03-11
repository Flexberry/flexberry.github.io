--- 
title: Initialization of the audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-setter.html 
lang: en 
autotranslated: true 
hash: 4ca7c90ae5588bd49e22948ce037f8035f7cb8ccba998bb0cfed18a48194422b 
--- 

# AuditSetter 

`AuditSetter` carries [initialization settings audit](efs_keep-and-use-audit-settings.html). 

The initialization is not necessarily performed by this class, it just contains the most successful initialization with default values. 

What happens when you call `AuditSetter.InitAuditService(IDataService dataService)`: 
# shall be Deducted the value from the configuration file: 
#* "AppNameForAudit" (the name of the settings in the configuration file for the application name to audit). 
#* "AuditEnabled" (the name of the settings in the configuration file to determine whether the audit in the Appendix). 
#* "IsAuditDatabaseLocal" (the name of the settings in the configuration file to determine whether the auditing database is local to the application). 
#* "AuditConnectionStringName" (the name of the settings in the configuration file for the name of the connection string to the database audit). 
#* "AuditWinServiceUrl" (the name of the settings in the config for the address win-service audit). 
#* "WriteSessions" (the name of the settings in the config to determine whether to write the session user). 
#* "DefaultWriteMode" (the name of the settings in the config for recording audit data by default). 
# If the values in the configuration file is invalid or not specified, the tabulated values are the following: 
#* "AppNameForAudit" = `"AuditAppName_" new Random().Next(65536)`. 
#* "AuditEnabled" = `false`. 
#* "IsAuditDatabaseLocal" = `true`. 
#* "AuditConnectionStringName" = `"AuditConnString_" new Random().Next(65536)`. 
#* "AuditWinServiceUrl" = `string.Empty`. 
#* "WriteSessions" = `false`. 
#* "DefaultWriteMode" = `tWriteMode.Synchronous`. 
# Creates an instance of the class [AuditAppSetting](efs_keep-and-use-audit-settings.html), which records the obtained settings. 
# Creates an instance of the class [AuditDSSetting](efs_keep-and-use-audit-settings.html), which receives obtained by `AuditSetter.InitAuditService` [service data](fo_sql-data-service.html) and it formed the view name "&lt;AppNameForAudit&gt;_&lt;auditconnectionstringname&gt;". 

After this initialization occurs [AuditService](efs_flexberry-audit-components.html). 
```cs
AuditService.InitAuditService(auditAppSetting, new ICSSoft.STORMNET.Business.Audit.Audit());
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}