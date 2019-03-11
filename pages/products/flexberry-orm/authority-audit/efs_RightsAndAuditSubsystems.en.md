--- 
title: the Subsystem of reference and audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit, Security Flexberry 
toc: true 
permalink: en/efs_rights-and-audit-subsystems.html 
lang: en 
autotranslated: true 
hash: b2d530b41dfd3d53f753e339ea5f7f7551cccf240c3ac5f9d771cf15de5639fe 
--- 

# Subsystem of reference and audit 
In version Flexberry after 28.01.2014 objects [Subsystem-power|subsystem powers] located in the Assembly `Security(Objects)`, ceased to depend on [the old audit](efs_audit.html) and now depend on [new audit](fa_audit-web.html) that will allow: 
* to produce more fine-grained object auditing полномочий; 
* maintain a "full" audit, preserving which fields which values was taken as a result of the audited operation (when the old audit this opportunity was limited). 

# setup [audit](fa_audit-web.html) for authorization objects 
Settings [audit](fa_audit-web.html) for authorization objects should be carried out in a special way, because: 
* you may need to maintain authorization objects "partial" auditing (i.e., to display information on the time and authorship of the creation and last modification of the object, but does not record exactly when and how was changed fields of the authorization objects), while the rest of the system should be audited at the "full" scheme. 
* you may need to write audit authorization objects in separate from the rest of the audit database. 

To address these issues has been expanded [settings class](fa_audit-web.html) and added support `AuditClassService` and `AuditClassConnectionStringName`. 

## setting up audit authority and `AuditClassService` 
Job `AuditClassService` will determine the method, exactly how you need to write the audit authority. 

The definition used to implement the interface [IAudit](efs_i-audit.html) for permission objects is done through the settings in the Unity configuration file (proofreading is done only once, after which the value is cached). If a dependency cannot be resolved, the settings are used for all audits in General. 

In the example below, as the implementation `IAudit` for permission objects is used [EmptyAudit](efs_i-audit.html) that will allow not to write a "complete" audit authorization objects. 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
	<configSections>
		<section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
	</configSections>

	<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
	  <!--Define a named instance of IAudit, which will be used to record audit authorization objects.-->
      <register name="securityAuditClassService"
				type="ICSSoft.STORMNET.Business.Audit.IAudit, ICSSoft.STORMNET.Business"
				mapTo="ICSSoft.STORMNET.Business.Audit.EmptyAudit, ICSSoft.STORMNET.Business">
      </register>
	</unity>
	
</configuration>
``` 

## setting up audit authority and `AuditClassConnectionStringName` 
Job `AuditClassConnectionStringName` will determine the name of the connection string to the database where to write the audit data. 

For authorization objects `AuditClassConnectionStringName` takes the value from the configuration file settings "`SecurityAuditClassConnectionstringname`" (proofreading is done only once, after which the value is cached). If this setting is not specified in the configuration file, then the name goes on [standard mechanism](efs_data-service-for-audit.html).

# Migration of existing projects 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}