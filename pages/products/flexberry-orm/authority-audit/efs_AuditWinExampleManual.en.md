--- 
title: connection Example of an audit to an existing Win application without the use of a regeneration project. 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-win-example-manual.html 
folder: products/ember-flexberry-security/backend/ 
lang: en 
autotranslated: true 
hash: 196e8b49488691f8f9d828b39a45d97a1b0f37f24cdd919a84dc74441c62f8db 
--- 

# Connection to the audit subsystem without a full regeneration project 
Connection algorithm 
# to Regenerate objects 
# to Make changes in the app.config 
# Add initialization of audit main 
# to Connect the missing Assembly 

# mistake the objects 
Without regeneration of the objects is still not enough, because the audit settings are stored in the classes (see [here](fa_audit-web.html) in the section "configuring a class"). 

# changes in the app.config 
In block `Configuration` - `appSettings` need to add the following lines: 
```xml
    <add key="AuditEnabled" value="True" />
    <add key="WriteSessions" value="False" />
    <add key="AuditWinServiceUrl" value="" />
    <add key="DefaultDSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="DefaultWriteMode" value="Synchronous" />
    <add key="IsAuditDatabaseLocal" value="True" />
``` 
And 
```xml
    <add key="AppNameForAudit" value="..." />
    <add key="AuditConnectionStringName" value="..." />
``` 
... instead of putting the required values of the names of Win-service, and connection strings to the database audit. 

# Connection audit in main 
you need to add the initialization call using [Initial audit](efs_audit-setter.html). 
```

// *** Start programmer edit section *** (TestFS Main()) 
ICSSoft.STORMNET.Windows.Forms.WinApplication.SetUICultureAsRussian();

// Initialize audit service 
AuditSetter.InitAuditService(DataServiceProvider.DataService); 
// *** End programmer edit section *** (TestFS Main()) 
``` 

# Connection assemblies 
# to connect to the application and `DesktopCustomizer` ICSSoft.STORMNET.Business.Audit.dll. 
# To the application to include build Security. 

# Cm. also 
[Connection example of an audit to an existing Web application without the use of a regeneration project.](fa_audit-web-example-manual.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}