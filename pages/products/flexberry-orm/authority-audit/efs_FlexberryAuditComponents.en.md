--- 
title: Elements Flexberry Audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_flexberry-audit-components.html 
lang: en 
autotranslated: true 
hash: 3f5e5d666f018b38f0302a663a07d126c5e5f355837b71a3c61f8b04cace1829 
--- 

# AuditService 
The application has a class `AuditService`. Through the class `AuditService` implemented API calls: 
* the class that inherits from the interface [IAudit](i-audit.html), if the audit is not in a separate service. 
* audit service `AuditWinService`. 

`AuditService` stores the application configuration audit, where they are loaded in the beginning of the application. 

When performing potentially audited operations [service data](fo_sql-data-service.html) the application informs the class `AuditService` that: 
* scans the existing configuration audit and decides on the need to implement audit records. 
* if auditing is required, then an appropriate message is either `IAudit`, if there is no separate audit service or `AuditWinService` (waiting for a response will depend on the settings of the audit). 

The class implements the interface `AuditService` `IAuditService`, and also has a static field of type `IAuditService` where will be recorded the instance of a class `AuditService` (this will allow to work both with a static class through the reference «AuditService.CurrentAuditService»). All calls are made via an interface (if there is a need, the class `Audit` easy to replace). 

# IAudit 

[IAudit](efs_i-audit.html) is an interface for you to organize the logic of the audit (that is, the class that implements this interface will be responsible for recording audit information and proofreading). 

# AuditWinService 

[AuditWinService](efs_audit-win-service.html) is a service through which it is possible to record audit data. 

# AsyncAuditController 

`AsyncAuditController` class, organizing asynchronous access `IAudit` that allows you to defer for some time the recording of data on audit ("'is not currently in use"'). 

# RemoteAuditController (ServiceAuditController) 

`RemoteAuditController (ServiceAuditController)` class responsible for the interaction through wcf win service audit `AuditWinService` associated with the wcf service, which implements the interface `IAuditWcfServiceLibrary`. 

# interaction Scheme 

## interaction pattern of the component audit without a single audit service 

![Image](/images/img/page/AuditWeb/AuditDiagramm1.PNG) 

## Scheme of interaction between a component of an audit when there is a distinct audit service 

![Image](/images/img/page/AuditWeb/AuditDiagramm2.PNG) 

## interaction pattern of the component audit 

![Image](/images/img/page/AuditWeb/AuditDiagramm3.PNG) 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}