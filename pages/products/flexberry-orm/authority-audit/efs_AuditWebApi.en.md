--- 
title: API audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-web-api.html 
lang: en 
autotranslated: true 
hash: 8e113c12fbf82174a51ee7255dbadd2f82a99a448365b62341b13501956cdcd5 
--- 
(((This article applies to [new audit](fa_audit-web.html)))) 

# API and IAuditService 
API audit is contained in the class `IAuditService` and is intended for fixation of own operations audit. 

The implementation is in the class `AuditServce`. 

## WriteCommonAuditOperation 

Method `WriteCommonAuditOperation` `SQLDataService`'ω is used to record standard operations audit (create, modify, delete). 

__Not recommended__ to call this method yourself. 

A description of the method: 

```
 Guid? WriteCommonAuditOperation(
                        DataObject operationedObject,
                        IDataService dataService,
                        bool throwExceptions); ``` 
{Br} 
* `operationedObject` - the Object on which the operation is performed. 
* `dataService` data Service performing the operation. 
* `throwException` - Should push occurred during operation exception. If you set `false` and an error occurs, the method will return `null` 
* returns `Guid?` - returns `Guid` created audit records, `null` when an error occurs and set throwExceptions = false 

The method automatically calculates the status of a transaction (create, delete, or change), based on the status of the object (dataObject.GetStatus()). 

## WriteCustomAuditOperation 

Method `WriteCustomAuditOperation` designed to hold its own operations audit. 

A description of the method: 

```
Guid? WriteCustomAuditOperation(
            CustomAuditParameters customAuditParameters,
            string dataServiceConnectionString,
            Type dataServiceType,
            bool throwExceptions);
``` 

* `customAuditParameters` - audit settings (see below) 
* `dataServiceConnectionString` the connection string of the data service 
* `dataServiceType` - type data service 
* `throwException` - Should push occurred during operation exception. If you set `false` and an error occurs, the method will return `null` 
* returns `Guid?` - returns `Guid` created audit records, `null` when an error occurs and set throwExceptions = false 

### CustomAuditParameters 
Class containing the description of the audit settings for its own operations. 

Contains the following fields: 

* tTypeOfAuditOperation `CommonAuditOperation` - sets the operation type field `CustomAuditOperation` on the basis of existing types of operations (Create, modify, delete, read, execute). For added ease of use. 


* string `CustomAuditOperation` - type operations displayed in list form audit. 


* DataObject `AuditDataObject` - audited object. The object itself is not saved, is saved only by his `primaryKey`. Automatically oznachaet property `AuditObjectType`. 


* PstrfAuditObjectType` Type - type of the audited object. Indicated automatically when you install `AuditDataObject`. 


* object `AuditObjectPrimaryKey` - the primaryKey of the audited object. Indicated automatically when you install `AuditDataObject`. 


* string `AuditObjectTypeOrDescription` - Type (`AssemblyQualifiedName`) or description of the object that is being audited. Job `AuditDataObject` or `AuditObjectType` automatically mean that field. 


* DateTime `OperationTime` - time operation. If time is not set, set `DateTime.Now` ("__note__: in this case, when an asynchronous write of the real and the recorded dates will vary. It is recommended to oznacovat this field for themselves"). 


* tExecutionVariant `ExecutionResult` - the result of the operation (Not known, Not done, Done, Error). 


* CustomAuditFieldList `CustomAuditFieldList` - a list of change objects which should be recorded in the audit. 


* `UseDefaultWriteMode` bool - whether to use write mode by default. 


* tWriteMode `WriteMode` - write mode audit (synchronous or asynchronous). When installed `UseDefaultWriteMode` = true; this field is ignored. 

## RatifyAuditOperation 
PstrfRatifyAuditOperation` method is used to confirm the previously recorded audit operations. In fact, he's cheating on her `ExcecutionResult`. 


For example, if a developer wants to audit all pressing specific buttons, the first thing in the handler pressing is written the Directive to create a record in the audit (using the method `WriteCustomAuditOperation`) with the result of ExecutionResult = tExecutionVariant.Unknown; After performing all the operations necessary to change the status audit records (by calling the method `RatifyAuditOperation`) on true: if the process finished successfully, install ExecutionResult = tExecutionVariant.Executed, etc. 

```
bool RatifyAuditOperation(
            tExecutionVariant executionVariant, 
            List<Guid> auditOperationIdList, 
            string dataServiceConnectionString,
            Type dataServiceType,
            bool throwExceptions);
``` 

* `executionVariant` - operation status that you want to install. 
* `auditOperationIdList` - the list of IDs of records __audit__ for which you want to change the status 
* `dataServiceConnectionString` the connection string of the data service 
* `dataServiceType` - type data service 
* `throwException` - Should push occurred during operation exception. If you set `false` and an error occurs, the method will return `null` 
* returns `bool` - the result of the operation (`True` if successful). 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}