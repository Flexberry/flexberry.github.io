--- 
title: Connection Example of an audit to an existing Web application without using regeneration project 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/fa_audit-web-example-manual.html 
lang: en 
autotranslated: true 
hash: 303f350315bd485d255ffea23f74b430b334907eff13ee4faf40d220dc59b592 
--- 

## Connection to the audit subsystem with the full mistake of the draft 

[Article on "connection Example of the audit to the existing Web application using regeneration project"](fa_audit-web-example.html). 

## Connection to the audit subsystem without a full regeneration project 

Connection algorithm 

1. To regenerate the objects 
2. To amend `web.config` 
3. Add the initialization of the audit `Global.asax.cs` 
4. To connect the missing Assembly 

## the Mistake of objects 

The mistake of the objects is necessary because the audit settings are stored in the classroom (more information [in the Audit for Web applications](fa_audit-web.html). 

## Changes in the Web.config 

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

... instead of putting the required values of the service names and connection strings to the database audit. 

## Initialize audit Global.asax.cs 

In the file `Global.asax.cs` you need to add: 

```csharp
using ICSSoft.STORMNET.Business.Audit; 
// ... 

protected void Application_Start(object sender, EventArgs e)
{
    // Initialize audit service 
    AuditSetter.InitAuditService(BridgeToDS.GetDataService());
    // ... 
}
``` 

## Necessary for the Assembly 

* ICSSoft.STORMNET.Tools.dll 
* ICSSoft.STORMNET.Business.dll 
* ICSSoft.STORMNET.Business.Audit.dll 
* Security (build objects) 
* CheckingLibrary.dll 
* ICSSoft.STORMNET.DataObject.dll 
* LogService 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}