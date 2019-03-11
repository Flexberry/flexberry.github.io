--- 
title: Windows-audit service 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-win-service.html 
lang: en 
autotranslated: true 
hash: 410c6a0c9e4aafbc86e81663ac182f72bfc1c87f747ff7c0224f895cfb36a645 
--- 

(((This article belongs to a new audit))) 

# Audit 
What is the audit can be found in [this article](fa_audit-web.html). 


# Service Audit 
Private Audit service provides the same capabilities as the built-in application audit, but he delivered in a separate Windows service. (this is a modified version after 02.10.2013) 

# connection to the audit service 
To connect to the service, you must: 

1. To add to the application configuration file key `AuditWinServiceUrl` that defines the url where the service. 

```xml
<add key="AuditWinServiceUrl" value="http://localhost:8733/AuditWcfService/" />
``` 
Above is example address. In a real situation address must be taken from the configuration file of the service: 
```xml
<services>
  <service name="ICSSoft.STORMNET.Business.AuditWcfServiceLibrary.AuditWcfService" behaviorConfiguration="MyBehavior">
	<endpoint address="" binding="basicHttpBinding" contract="ICSSoft.STORMNET.Business.AuditWcfServiceLibrary.IAuditWcfService">
	  <identity>
		<dns value="localhost"/>
	  </identity>
	</endpoint>
	<endpoint address="mex" binding="mexHttpBinding" contract="IMetadataExchange"/>
	<host>
	  <baseAddresses>
		<add baseAddress="http://localhost:8733/AuditWcfService/"/>
	  </baseAddresses>
	</host>
  </service>
</services>
``` 

2. To prepare an Assembly with the objects of audit which will be carried out. Build for method [WriteCommonAuditOperation](efs_audit-web-api.html), other methods audit records (including method of audit records to its own facilities and operations) don't require Assembly with the objects. 

((( 
<msg type=important>Please note that the Assembly object needs to be collected for version .Net Framework version 3.5.</msg> 
))) 

Prepared Assembly you must copy the folder "ObjectAssemblies", located next to the audit service. 

3. To configure the settings for the database (there is a [separate article that defines a mechanism to define connection settings to the database when audit](efs_data-service-for-audit.html)). 

To do this in the config application to specify the following settings (these settings are intended for reference only and may vary in a particular application): 
```xml
<add key="IsAuditDatabaseLocal" value="False" />
<add key="AppNameForAudit" value="TestFS" />
<add key="AuditConnectionStringName" value="AuditConnString" />
``` 

If `IsAuditDatabaseLocal = false`, then in the config of the service will be matched with the connection string specified by the parameter `AuditConnectionStringName` (that is, in this case - "`AuditConnString`"). 
```xml
<connectionStrings>
	<add name="AuditConnString" connectionString="SERVER=.;Trusted_connection=yes;DATABASE=AuditEtaloneDB;" />
</connectionStrings>
``` 

The service type data is always looked up in the config key `<esc><</esc>the name of the connection string<esc>></esc>_DSType` (if there is no such record, the data service specified in the setting "`DefaultDSType`", and if this does not work, then use the data service, specified in `DataServiceProvider.DataService`). 

```xml
<appSettings>
    <add key="AuditConnString_DSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
    <add key="DefaultDSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="CustomizationStrings" value="SERVER=.;Trusted_connection=yes;DATABASE=qwerty;" />
</appSettings>
``` 

If `IsAuditDatabaseLocal = true`, getting a name is the name of the connection string occurs in a specific pattern: the name is searched among [AuditDSSettings](fa_audit-web.html). The name of the default service defined as `<esc><</esc>AppNameForAudit<esc>></esc>_<esc><</esc>AuditConnectionStringName<esc>></esc>` (in our case, is the "`TestFS_AuditConnString`"). 

```xml
<connectionStrings>
	<add name="TestFS_AuditConnString" connectionString="SERVER=.;Trusted_connection=yes;DATABASE=AuditEtaloneDB;" />
</connectionStrings>
``` 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}