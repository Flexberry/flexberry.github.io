--- 
title: Initialization of the audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-setter.html 
lang: en 
autotranslated: true 
hash: e8e3cc1ae01951bf4097959776b8b05735f5c7637a6f67ddfdfdfed9f9943e96 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/