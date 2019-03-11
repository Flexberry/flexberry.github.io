--- 
title: Auditing for Web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Audit 
toc: true 
permalink: en/fa_audit-web.html 
lang: en 
autotranslated: true 
hash: 5daa6906ca7cef6379176d3a90d1f97f4afae54d8966bfd756fdc2ea2301c64d 
--- 

## interaction pattern of the component audit 

![](/images/pages/products/flexberry-aspnet/audit/audit-diagramm1.png) 

*The scheme of interaction of the component audit without a single audit service* 

### AuditService 

* The application has a class AuditService. 
* A class implements AuditService API calls: 
* the class that inherits from the interface [IAudit](efs_i-audit.html), if the audit is not in a separate service. 
* audit service AuditWinService. 
* AuditService stores the application configuration audit, where they are loaded in the beginning of the application. 
* When performing potentially audited transaction data service of the application notify the class AuditService: 
* scans the existing configuration audit and decides on the need to implement audit records. 
* if auditing is required, then an appropriate message is either [IAudit](efs_i-audit.html), if no separate audit service or AuditWinService (waiting for a response will depend on the settings of the audit). 
* The class implements AuditService interface IAuditService, and also has a static field of type IAuditService where will be recorded the instance of a class AuditService (this will allow to work both with a static class through the reference «AuditService.CurrentAuditService»). All calls are made via an interface (if there is a need, the class can Audit it is easy to replace). 

![](/images/pages/products/flexberry-aspnet/audit/audit-diagramm2.png) 

*The scheme of interaction of the component the audit subsystem in the presence of a separate audit service* 

### IAudit 

* IAudit provides an interface to organize the logic of the audit (that is, the class that implements this interface will be responsible for recording audit information and proofreading). 

### AuditWinService 

* AuditWinService serves multiple applications and potentially located on a remote machine. 
* In the config AuditWinService provided data services and connection strings to databases of audit and application databases (if they are different). 
* When you run the query AuditService reports AuditWinService the row names of the database connections of the application and audit (connection string will not be transmitted because it is not safe). 
* Multiple applications do not share a single database audit. 
* AuditService interacts with AuditWinService through WCF (AuditWinService is the name of the service application. A class that inherits from IAudit, will implement the WCF contract to access the public methods via WCF. Also it will be designed so that one service handles requests from multiple applications.) 

### AsyncAuditController 

* AsyncAuditController class, organizing asynchronous access [IAudit](efs_i-audit.html) that allows you to defer for some time the recording of data on the audit. 

### ServiceAuditController 

* ServiceAuditController class responsible for the interaction through wcf win service audit AuditWinService associated with the wcf service, which implements the interface IAuditWcfServiceLibrary. 

![](/images/pages/products/flexberry-aspnet/audit/audit-diagramm3.png) 

*The scheme of interaction of the component audit* 

## layout of classes and interfaces in assemblies Flexberry 

To prevent a situation of cross-references in the project it was decided to place the main interfaces of the audit in the Assembly ICSSoft.STORMNET.Business.dll, which already includes data service interface IDataService, and class SQLDataService. 

Class [ICSSoft.STORMNET.Business.Audit.Audit](efs_i-audit.html) that implements the interface [IAudit](efs_i-audit.html), contains the logic of working with the information audit. 
The audit service AuditWinService c wcf service AuditWcfServiceLibrary supports IAuditWcfServiceLibrary are a container for the logic of work with the information audit. 

![](/images/pages/products/flexberry-aspnet/audit/audit-diagramm4.png) 

*Arrangement of classes audit in assemblies Flexberry and their interaction* 

**Note:** the implementation of interfaces corresponds to the present scheme, however, in accordance with the plan for the first phase of testing was only part of the functionality 

## displays the results of the audit 

* The form with information on user sessions (*to be implemented in the second stage*). 
* Viewing audit data throughout the system ([see here](fa_audit-web-forms.html)). 
* Viewing audit information for specific object ([see here](fa_audit-web-forms.html)). 
* Form «Who are on the site» (*to be implemented in the second stage*). 

The approximate set of controls on the form view audit data for a specific change: 

* Time change 
* Author of the change 
* The type of operation 
* Change: 
* private fields: changed field the old value (if saving is specified in the settings), the new value. 
* for masters and datalow old (if its retention is specified in the settings), new string representation (string representation of the type «field - value based on» selected for audit submission) and, if specified in the settings class, old/new primary key. 
* Element to invoke the edit form (the form displays the current status объекта; if the object is already deleted, then view its status will not).
* Elements of the audit object: 
* Creation date of the object 
* Creator of the object 
* Date of last change 
* Object editor (latest) 

In relation to the persistent field values should remark that the settings of the audit will determine: 

* Preserve in the record the old value of the audited fields of the object. 
* Whether to trim a string with the corresponding value of the field audit while maintaining the information and to what length. 
* Whether to compress the value of a field audit when saving and decompress when you display (*to be implemented in the third stage*). 

## Storing audit data 

A structure for storing audit data 

* Storage of audit data, you can use a structure similar to those shown below (such a structure it is possible to use outside databases приложения; the names of the corresponding tables will be prefixed with STORM). 
* Classes `Agent` and `LinkGroup` classes of subsystem power. 
* Integration with elements of the system of powers will allow to avoid storing data about users of the system in several places in different formats as it was before. 
* The use of tables subsystem powers will allow you to store in one database information subsystem powers and audit. 
* Similar structure to the data collected allow, if necessary, to carry out their integration with located in another database data subsystem power (or just add missing tables in the database audit, if you want to store the data subsystem powers in the same database). 
* `AuditSession` – information about user sessions (the same class used earlier (Auditmessage)): 
* `StartTime` – the beginning of the session (e.g., «10.02.2013 16:00:23:123»). 
* `EndTime` – end time of the session (e.g., «10.02.2013 17:03:27:125»). 
* `LastActionTime` – the last time the audited action (e.g., «10.02.2013 16:30:23:123»). 
* `Source` – computer where you were logged into the application (e.g., «IP: 192.168.0.5»). 
* `Enabled` – active session at the moment (for example, false» qmo). 
* `Duration` – session duration (calculated based on start and end сессии; for example, 0.01 qmo:03:04:002») 
* `AuditEntity` – information about the audited event on the object (the same class used earlier (Auditorily)) 
* `ObjectPrimaryKey` – the primary key of the object on which was performed the audited operation. 
* `OperationTime` – time when the object was made of the audited operation. 
* `OperationType` – type is made on the object of the audited operation (if you have followed «typical», it will be passed as a string value of the type `tTypeOfAuditOperation`; if you have followed custom is the name of the corresponding operation).
* `ExecutionResult` – the result of the audited operation. 
* `Source` – computer, where the operation was performed (e.g., «IP: 192.168.0.5»). 
* `SerializedField` – serialized representation of data on changes in the fields of AuditFields. 
* `ObjectType` – type of the audited object. 
* `Name` – the name of the object type (if the type is standard, then if AssemblyQualifiedName; custom – passed to user-definition). 
* `AuditField` – details of an audited event on the object: which fields and how they were changed (the same class used earlier (Auditionee)): 
* Masters are on the same level as a private field. Field is the name мастера; old and new values are formed in the form of a string at a specified for audit representation. Old and new primary key value of the master is recorded as a separate line and it as MainChange written record of the change master (change to display the primary key will be only if in the class there is a corresponding setting). 
* Detaily are the same as masters, only field names are defined by the type <Classname(number)> (the same was done earlier). 
* To be able to store audit data in application database and external database. Recording audit data can be performed synchronously (that is, while the data for the audit successfully will not be recorded, audited, the transaction will fail). 

|Synchronous access Asynchronous access| 
|---|---| 
| **Database application** | In this case, the record audit events and the operations [can be combined in a single transaction. Accordingly, if something went wrong, all operations are rolled back. In this case, is the first priority of the audited operation. Audit data is sent to a separate process that is responsible to audit data being recorded (possible recording the data in some temporary storage).| 
| **External DB** | First, this will record the audit (Executed flag=false). If the record of the audit is successful, it is attempting to perform an audited operation. If an audited operation is successful, the audits Executed, the flag changes to true. In this case, is the first priority of the audited operation. Audit data is sent to a separate process that is responsible to audit data being recorded (possible recording the data in some temporary storage).| 

![](/images/pages/products/flexberry-aspnet/audit/audit-store-structure.png) 

*A database structure for storing audit information* 

## configuration audit 

Setting up auditing in Flexberry Tool 

### setting the stage 

* In settings, the stage will tick «Use of audit». The option is ignored when generating the application: if it is selected, additional items (additional items described in paragraph «Storage settings») used by audit will be generated, and the audit application to function. 
* In settings, the stage will be a button to Add «audit in all classes». When you press the button all the classes in the minimal way will be configured to conduct the audit. 
* In the settings stage you can choose to store the database audit: database application or in an external database. 

### class setting. 

* To audit the class must have a representation that is audited (the binding occurs via the view name). For different operations (create, modify, read, delete) should be able to specify their own performance. 
* In the settings class will have the opportunity to determine which operations (create, modify, read, delete) is audited. If checked at least one checkbox, then additional elements (additional elements described in paragraph «Storage settings») used by the audit, will be generated under the condition that the stage is the same tick. 
* In settings you can define whether you want to use to audit performance with the default name (AuditView). 
* In the settings class will tick «Additional audit fields». When you select this option in the class adds the following attributes: 
* CreateTime (date of establishment) 
* Creator (Creator) 
* EditTime (last modified) 
* Editor (Editor) 

**Note**: the user name is taken from the [Tools current user](fo_current-user-service.html) 

* When selecting checkboxes when code is generated from the class will be inheriting from an interface that contains four describes the fields. 
* Tick «Additional audit fields» will allow you to display a list of forms characteristic of the previous version of the audit additional fields. When you save an object class, where there is a check mark «Additional audit fields» will be checked that all of the added attributes present in the class (that is, not accidentally modified/deleted), otherwise an exception will be thrown. 
* In the settings class, you can choose the option record data for audit: synchronous or asynchronous. 

## Storage settings 

**Note:** on the current implementation of audit classes are used: AuditAppSetting and AuditDSSetting; class AuditClassSetting is not used in full. 

![](/images/pages/products/flexberry-aspnet/audit/audit-store-settings.png) 

*The overall structure for storing settings of the auditing application* 

### General settings audit (AuditAppSetting) 

General settings audit will be in the AuditService class, which will be deducted at the beginning of the application. 

General settings audit (AuditAppSetting): 
* AppName – the application name (will be used by the [audit services](efs_audit-win-service.html)) 
* AuditEnabled – enabled auditing in the application. 
* IsDatabaseLocal – [whether to use a local audit database](efs_data-service-for-audit.html) (i.e. the application database combined with database auditing). 
* AuditConnectionStringName – [name of the connection string to the database audit](efs_data-service-for-audit.html) (more about the logic of this configuration can be read [here](efs_data-service-for-audit.html)). 
* AuditWinServiceUrl – url, where is the [audit services](efs_audit-win-service.html). 
* WriteSessions – whether to store information about user sessions. 

### Settings class (AuditClassSetting) 

The configuration class will be generated directly into the code of the feature class (similar to how code feature class attribute has been added Views): 

* Did the audit for the class. 
* Whether to use the default view during an audit. 
* Set audit operations create object (the name of the view * did the audit for this operation). 
* Set audit operations of changing the object (the name of the view * did the audit for this operation). 
* Settings audit read operations of the object (the name of the view * did the audit for this operation). 
* Set audit operations removal of the object (the name of the view * did the audit for this operation). 
* URL to the display form of the object (as in the previous version of the audit subsystem using the form you can view the current state of the object). 
* Recording audit data (synchronous or asynchronous). 
* Should and how much should be cut off save the values of audited fields. 
* Whether to display audit data in the old and new primary key value of the master/detail. 
* Whether a recording of a change to keep the old value of the audited field. 

In the beginning of the application setup class for audit will be deducted in the structure `AuditClassSetting`: 

* `ObjectType` – type objects. 
* `AuditEnabled` – enabled auditing for a class. 
* `UseDefaultView` – to use for all operations in the default view with the name AuditView» qmo. 
* `SelectAuditViewName` – the name of the view to conduct audit read operations of the class. 
* `InsertAuditViewName` – the name of the view to conduct operation audit create the class. 
* `UpdateAuditViewName` – the name of the view for doing auditing operation changes the class. 
* ``DeleteAuditViewName` – the name of the view to conduct the audit of the operation of removal from class. 
* `FormUrl` - URL to the display form of the object. 
* `WriteMode` – mode recording audit data (synchronous or asynchronous). 
* `SelectAudit` – advanced audit read. 
* `InsertAudit` – in addition to audit of the establishment. 
* `UpdateAudit` – in addition to audit change. 
* `DeleteAudit` – advanced audit for deletion.
* `PrunningLength` – if the value is greater than zero then as many characters from a field value, starting from the beginning, will сохраняться; if null, the value will be saved as a whole. 
* `ShowPrimaryKey` – whether to display audit data in the old and new primary key value of the master/detail. 
* `KeepOldValue` – if you want to save the old value of the changed field. 
* `Compress` – whether to compress stored fields values. 
* `KeepAllValues` – whether to save only changed fields from the view, or all of the fields included in the view. 

Sometimes there is a need to configure additional auditing for specific objects (that is, in addition to the audit, which is conducted for the respective class). For example, for objects `ObjectA`, `ObjectB` class `Class1` for which audit is conducted only to read, to add audit record. 

How to do it: 

* Settings by means of special commands will be given in the program code and stored in application memory during runtime in the specified new format. 

Settings for auditing objects will be set in the program code will be stored in the structure `AuditClassSetting` and define fields: 

* `LimitFunction` – limit that must be met by objects. 
* `View` – representation that needs to be unloaded objects, to them it was possible to impose a limit. 

If in the above-mentioned structure `LimitFunction` and `View` empty, it will mean that the record contains the settings for the entire class. 

In the settings class also provides additional fields (using the information in [this article](efs_rights-and-audit-subsystems.html)): 

* `IAudit AuditClassService` - interface implementation [IAudit](efs_i-audit.html) through which it is necessary to audit a particular class. 
* `string AuditClassConnectionStringName` - [name of the connection string to the database, through which you want to write audit](efs_data-service-for-audit.html). 

### Settings fields (AuditFieldSetting) 

Setting the field class defined by the programmer in the program code and stored in the structure `AuditFieldSetting`: 

* `FieldName` – the field name for which you are defining the configuration. 
* `PrunningLength` – (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 
* `Compress` – (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 
* `KeepOldValue` - (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 
* ``KeepAllValues` - (see `AuditClassSetting`) if `null`, then take the value from the corresponding AuditClassSetting.

### Configuration data services (AuditDSSetting) 

Configuration data services, the application requires the audit service to audit to determine what data service you need to write audit data. 

Information about data services used by the application (`AuditDSSetting`): 

* `DataServiceType` – type data service. 
* `ConnString` – the connection string of the service. 
* `ConnStringName` – the connection string name (the name provided by the programmer to identify the service данных; later in the config of the service audit will be used is the name). 

The data service when they had identified a potentially audited operation, notify the AuditService class. If the class AuditService decided that it is necessary to conduct the record audit data, among sent [service audit](efs_audit-win-service.html) information is the name of the connection string to the application database ([name of the connection string is defined](efs_data-service-for-audit.html) from AuditDSSetting based on what data service is and what connection string applied to the class AuditService). 


## the Algorithm for determining current auditing settings 

![](/images/pages/products/flexberry-aspnet/audit/audit-find-setting.png) 

*Circuit determining whether the operation is audited for class* 

If the operation is audited, it was found from settings does the idea, then for your own fields, and datalow defines the options for saving audit information in the following order: 

* Among the settings for the fields in the configuration class, the default settings are searched for the required fields, where the interesting parameters are not null. 
* If parameters are not found, then it uses the parameter values specified in the customizing of the default class. 
* If the class setting there is no default, then a similar search is performed for advanced settings, where the object satisfies LimitFunction. 

## Generation settings 

Among the settings of the audit defined in Flexberry Tool, we can distinguish the following types: 

* Setting the stage (General settings for all audit generated from the stage of application). 
* Settings classes with the stereotype application» «(audit settings that you define for a particular generated application (currently ASP generator supports generating only one application)). 
* Settings classes with the stereotype» «implementation (audit settings that are defined for specific classes that will be included in the Assembly of objects). 

![](/images/pages/products/flexberry-aspnet/audit/audit-setting-generate.png) 

The generation of the settings of the audit of Flexberry Tool to the web application* 

Settings classes with the stereotype» «implementation will be generated into the code object a generator object. 
Setting the stage and class with the stereotype application» «will be generated. config file on a web ASP application generator.

## Reading settings 

AuditService basic settings feature classes gets directly from the class using reflection. 
General settings of the auditing application is loaded into AuditService at the beginning of the web application in the Global.asax.cs where there is an appeal to a specialized class AuditSetter, which is responsible for the formation settings from the config file and initialization AuditService. 

![Image](/images/pages/products/flexberry-aspnet/audit/audit-setting-read.png) 

*The scheme of reading the settings for the AuditService* 

## error Handling 

![](/images/pages/products/flexberry-aspnet/audit/audit-error-handle.png) 

* `AuditException` – the underlying exception audit. When performing such basic operations as of audit WriteCustomAuditOperation, RatifyAuditOperation, WriteCommonAuditOperation out propisyvayutsya only exceptions of this type. 
* `DisabledAuditException` the exception reports that the audit is turned off, accordingly, nothing in DB audit will not fall. 
* `DataNotFoundAuditException` – not all data necessary for the functioning of the audit, are available. 
* `ExecutionFailedAuditException` – exception reports that during the recording of audit data was something that was not allowed to record them. 
* `RatifyExecutionFailedAuditException` – exception reports, at which the audit record has failed to change the status. 

## Regular saving 

Those saved objects using the [DataService|data service) `[Processing-one-object|UpdateObject)` or `[Processing-of-multiple-objects|UpdateObjects)`. In this case, the transaction to perform actions on the database is opened after the audit receives all required data, so the audit does not lead to a situation of deadlock. 

## Ordered save 

When saving objects using the method `[SQLDataService#UpdateObjectsOrdered|UpdateObjectsOrdered)` transaction is opened before execute [Business-Servers-Wrapper-Business-Facade|business servers) and generates the needed queries. For this reason, AuditService, which prepares audit data to transmit them to the recording is passed the current open transaction to avoid deadlocks. It is worth noting that does not matter in which database and how (using [AuditWinService|win-service) or a static class) and the audit record, because after the transfer of data from AuditService no longer refers to the application database. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}