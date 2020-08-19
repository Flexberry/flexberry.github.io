--- 
title: a Mechanism for determining the settings for connecting to the database audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit database 
toc: true 
permalink: en/efs_data-service-for-audit.html 
lang: en 
autotranslated: true 
hash: d52641821e293dd67f7546175400eafb472e2a2693e33de52d4107b4cccd94a8 
--- 

This article applies to [new audit](fa_audit-web.html). 

# mechanism determining the connection string when the audit 
"'The mechanism works in versions of assemblies after 27.01.2015."' 

The conduct of the audit is important to determine how and what DB will be used to record the audit data. For this you define the connection string name to the audit and the type of [data service](fo_data-service.html). 

## specifying the name of the connection string 
Define the name of the connection string occurs according to the following algorithm: 

1. From the settings audit class ([AuditClassSetting](efs_keep-and-use-audit-settings.html)) does the setting `AuditClassConnectionStringName`. 

```cs
public class FirstClass : ICSSoft.STORMNET.DataObject, IDataObjectWithAuditFields
{
	...	
	
	/// <summary> 
	/// Audit class settings. 
	/// </summary> 
	public class AuditSettings
	{
		/// <summary> 
		/// The name of the connection string to the database where you want to write audit. 
		/// </summary> 
		public static string AuditClassConnectionStringName = "FirstClassConnectionStringName";
	}
}
``` 

If `AuditClassConnectionStringName` defined in the settings of the audit class and the value is a non-empty sequence of characters, then it is taken as the name of the connection string. 

2. If the previous method the name of the connection string was not specified, the setting [IsDatabaseLocal](efs_keep-and-use-audit-settings.html) (see also [following article](efs_audit-win-service.html)). 

2.1. If `IsDatabaseLocal = false`, as the name of the connection string takes the value [AuditConnectionStringName](efs_keep-and-use-audit-settings.html). 

2.2. If `IsDatabaseLocal = true`, then the structures [AuditDSSetting](efs_keep-and-use-audit-settings.html). 

It is believed that if `IsDatabaseLocal = true`, the audit record will be made in the "current" database by "current" [data service](fo_data-service.html) (the term "current" conventionally, as a method determine the name of the connection string type "current" [data service](fo_data-service.html) can get in various ways). 

If the "current" [data service](fo_data-service.html) is incorrect, as the name of the connection string, use the value [ConnStringName](efs_keep-and-use-audit-settings.html) of the first `AuditDSSetting` from `AuditDSSettings` array ([connection string name is defined by default as &lt;AppNameForAudit&gt;_&lt;auditconnectionstringname&gt;](efs_audit-setter.html)). 

Otherwise, the array is searched `AuditDSSettings` `AuditDSSetting`, where [ConnString](efs_keep-and-use-audit-settings.html) is the connection string "current" [data service](fo_data-service.html), and [DataServiceType](efs_keep-and-use-audit-settings.html) - type of the "current" [data service](fo_data-service.html), and the value of the first found `ConnStringName` is the required name of the connection string for the audit. 

Interface implementation [IAudit](efs_i-audit.html) `ICSSoft.STORMNET.Business.Audit.Audit` processes the received name of the connection string as follows: 
# Is searching for a connection string by name from the available in the configuration file. 
# If the connection string was not found, it uses the connection string from [DataServiceProvider.DataService](fo_ds-provider.html). 

## define the type of data service 
Type [service data](fo_data-service.html), through which will be recorded in the audit is not determined at the stage of preparing data for recording in the audit. The type determined in the interface implementation `IAudit`. 

Interface implementation `IAudit` `ICSSoft.STORMNET.Business.Audit.Audit` determines the type of the service data in the following way (see also [following article](efs_audit-win-service.html)): 
# Type of the service data is searched for in the config for the key <connection string Name>_DSType. 
# If there is no such record, the data service specified in the setting "DefaultDSType". 
# is used Otherwise the service type data [DataServiceProvider.DataService](fo_ds-provider.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}