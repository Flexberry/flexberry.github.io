--- 
title: data Storage audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit database 
toc: true 
permalink: en/efs_audit-data-storage.html 
lang: en 
autotranslated: true 
hash: 41d98a9366c10cc1e6a83e3f9c323b75fbafea2f8edb92bb20985d0a96cf3a35 
--- 

# Storing audit data 

To store audit data, you can use a structure similar to those shown below (such a structure it is possible to use outside databases приложения; the names of the corresponding tables will be prefixed with STORM). This structure is used for storage in the implementation of [ICSSoft.STORMNET.Business.Audit.Audit interface IAudit](efs_i-audit.html). 

![Image](/images/img/page/AuditWeb/AuditStoreStructure.PNG) 

* Classes `Agent` and `LinkGroup` classes from [subsystem powers](efs_security.html). 

* `AuditEntity` – information about the audited event on the object (the same class used in [the old audit (`АудитОперации`)](efs_audit.html)) 
** `ObjectPrimaryKey` – [primary key object](fo_primary-keys-objects.html), on which was performed the audited operation. 
** `OperationTime` – time when the object was made of the audited operation. 
** `OperationType` – type is made on the object of the audited operation (if you have followed «typical», it will be passed as a string value of the type `tTypeOfAuditOperation`; if you have followed custom is the name of the corresponding operation). 
** `ExecutionResult` – the result of the audited operation. 
** `Source` – computer, where the operation was performed (e.g., «IP: 192.168.0.5»). 
** `SerializedField` – serialized representation of data on changes in the fields of AuditFields ("'not currently used"'). 

* `ObjectType` – type of the audited object. 
** `Name` – the name of the object type (if the type is standard, then if `AssemblyQualifiedName`; custom – passed to user-definition). 

* `AuditField` – details of an audited event on the object: which fields and how they were changed (the same class used in [the old audit(`АудитИзменения`)](efs_audit.html)): 

* `AuditSession` – information about user sessions (the same class used earlier (`Аудит_Сессия`), "'at the moment the table is not in use"'): 
** `StartTime` – the beginning of the session (e.g., «10.02.2013 16:00:23:123»). 
** `EndTime` – end time of the session (e.g., «10.02.2013 17:03:27:125»). 
** `LastActionTime` – the last time the audited action (e.g., «10.02.2013 16:30:23:123»). 
** `Source` – computer where you were logged into the application (e.g., «IP: 192.168.0.5»). 
** `Enabled` – active session at the moment (for example, false» qmo).
** `Duration` – session duration (calculated based on start and end сессии; for example, 0.01 qmo:03:04:002») 

## Data table AuditField 

* [Masters](fd_master-association.html) are on the same level as a private field. 
Field is the name мастера; old and new values are formed in the form of a string at a specified for audit representation. 
Old and new primary key value of the master is recorded as a separate line and it as MainChange written record of the change master (change to display the primary key will be only if in the class there is a corresponding setting). 
* [Detaily](fo_detail-associations-properties.html) are the same as masters, only field names are defined by the type <Classname(number)> (the same was done in [old audit](efs_audit.html)). 

## the Use of tables subsystem powers 

* Integration with elements of the system of powers will allow to avoid storing data about users of the system in several places in different formats, [as previously](efs_audit.html). 
* The use of tables subsystem powers will allow you to store in one database information subsystem powers and audit. 
* Similar structure to the data collected allow, if necessary, to carry out their integration with located in another database data [subsystem powers](efs_security.html) (or just add missing tables in the database audit, if you want to store the data subsystem powers in the same database). 

# Forms of audit 

To display the audit data in such a structure there are special [win](efs_audit-win-forms.html) [web-form](fa_audit-web-forms.html). 

There are 2 types of forms: tapicerowane and object-oriented. 

"'Tapicerowane form"' display the information in the audit record. 

"'The object-oriented form"' display the information on the audit object as a whole. 

![](/images/pages/img/page/AuditDataStorage/AuditForms.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}