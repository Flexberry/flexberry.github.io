--- 
title: Storing and using settings audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_keep-and-use-audit-settings.html 
lang: en 
autotranslated: true 
hash: ee27daf6551f8cedbf26550fc171d20b2d1dae883cc3050a044629d75fe7c82d 
--- 

# Storage settings 

The General structure for storing settings of the audit of the app: 

![Image](/images/img/page/AuditWeb/AuditStoreSettings.PNG) 

((( 
<msg type=note>At this stage of the implementation of the audit classes are used: `AuditAppSetting` and `AuditDSSetting`; class `AuditClassSetting` is not used in full.</msg> 
))) 

## General settings audit (AuditAppSetting) 
General settings audit are in the class `AuditService` where shall be deducted at the beginning of the application. 

General settings audit (`AuditAppSetting`): 
* `AppName` – the application name (used [service audit](efs_audit-win-service.html)) 
* `AuditEnabled` – enabled auditing in the application. 
* `IsDatabaseLocal` – [whether to use a local audit database](efs_data-service-for-audit.html) (i.e. the application database combined with database auditing). 
* `AuditConnectionStringName` – [name of the connection string to the database audit](efs_data-service-for-audit.html). 
* `AuditWinServiceUrl` – url, where is the [audit services](efs_audit-win-service.html). 
* `WriteSessions` – whether to store information about user sessions. 

## Settings class and set the fields (AuditClassSetting and AuditFieldSetting) 
The settings class is generated directly in the class code object (the same as in [code feature class attribute has been added `Views`](fo_static-view-accessors.html)): 
* `bool AuditEnabled` - enabled auditing for a class. 
* `bool UseDefaultView` - whether to use the default view during an audit. 
* `bool InsertAudit`, `string InsertAuditViewName` - settings operation audit create object (the name of the view is the audit for this operation). 
* `bool UpdateAudit`, `string UpdateAuditViewName` - settings audit of the operation of changing the object (the name of the view is the audit for this operation). 
* `bool SelectAudit`, `string SelectAuditViewName` - settings audit read operations of the object (the name of the view is the audit for this operation). 
* `bool DeleteAudit`, `string DeleteAuditViewName` - set audit delete operations on the object (the name of the view is the audit for this operation). 
* `string FormUrl` - URL to the display form of the object (as in the previous version of the audit subsystem using the form you can view the current status of object) ("'not used"'). 
* `ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode WriteMode` - mode recording audit data (synchronous or asynchronous) ("'only supported synchronous"').
* `int PrunningLength` - whether and how much should be cut off save the values of audited fields ("'not used"'). 
* `bool ShowPrimaryKey` - whether to display audit data in the old and new primary key value of the master/detail. 
* `bool KeepOldValue` - should the record of change to keep the old value of the audited field ("'not used"'). 
* `bool ` Compress – whether to compress stored fields values ("'not used"'). 
* `bool KeepAllValues` – whether to save only changed fields from the view, or all of the fields included in the view ("'not used"'). 
* `IAudit` [AuditClassService](efs_rights-and-audit-subsystems.html) - implementation of interface [IAudit](efs_i-audit.html) through which it is necessary to audit a particular class. 
* `string` [AuditClassConnectionStringName](efs_rights-and-audit-subsystems.html) - [a name of the connection string to the database, through which you want to write audit](efs_data-service-for-audit.html). 

```csharp
namespace ICSSoft.STORMNET.Security
{
    public class Agent : ICSSoft.STORMNET.DataObject, IDataObjectWithAuditFields
    {
        ///... 
        
        /// <summary> 
        /// Audit class settings. 
        /// </summary> 
        public class AuditSettings
        {
            
            /// <summary> 
            /// If auditing is enabled for the class. 
            /// </summary> 
            public static bool AuditEnabled = true;
            
            /// <summary> 
            /// Use the view name for auditing by default. 
            /// </summary> 
            public static bool UseDefaultView = false;
            
            /// <summary> 
            /// Whether the audit of a read operation. 
            /// </summary> 
            public static bool SelectAudit = false;
            
            /// <summary> 
            /// The name of the view for listening to the read. 
            /// </summary> 
            public static string SelectAuditViewName = "AuditView";
            
            /// <summary> 
            /// Have you included the audit create operation. 
            /// </summary> 
            public static bool InsertAudit = true;
            
            /// <summary> 
            /// The view name for the audit of the create operation. 
            /// </summary> 
            public static string InsertAuditViewName = "AuditView";
            
            /// <summary> 
            /// Have you included the audit of the operation changes. 
            /// </summary> 
            public static bool UpdateAudit = true;
            
            /// <summary> 
            /// The name of the view audit trail of changes. 
            /// </summary> 
            public static string UpdateAuditViewName = "AuditView";
            
            /// <summary> 
            /// Have you included the auditing of the delete operation. 
            /// </summary> 
            public static bool DeleteAudit = true;
            
            /// <summary> 
            /// The name of the view to audit the delete operation. 
            /// </summary> 
            public static string DeleteAuditViewName = "AuditView";
            
            /// <summary> 
            /// The path to view the results of the audit. 
            /// </summary> 
            public static string FormUrl = "";
            
            /// <summary> 
            /// Recording mode audit data (synchronous or asynchronous). 
            /// </summary> 
            public static ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode WriteMode = ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode.Synchronous;
            
            /// <summary> 
            /// The maximum length of the stored field value (if 0, the string will not be trimmed). 
            /// </summary> 
            public static int PrunningLength = 0;
            
            /// <summary> 
            /// Whether to show users change primary keys. 
            /// </summary> 
            public static bool ShowPrimaryKey = false;
            
            /// <summary> 
            /// Save old value. 
            /// </summary> 
            public static bool KeepOldValue = true;
            
            /// <summary> 
            /// Compress stored values. 
            /// </summary> 
            public static bool Compress = false;
            
            /// <summary> 
            /// Save all the values of attributes, not just modified. 
            /// </summary> 
            public static bool KeepAllValues = false;

            /// <summary> 
            /// The audit service, who will write the audit of the system of powers. 
            /// </summary> 
            public static IAudit AuditClassService = SecurityHelper.GetSecurityClassAudit();

            /// <summary> 
            /// The name of the connection string to the database where you want to write the system's audit powers. 
            /// </summary> 
            public static string AuditClassConnectionStringName = SecurityHelper.GetSecurityClassAuditConnectionStringName();
        }
    }
}
``` 

### development Plans 
"'AuditClassSetting"' 

In the beginning of the application setup class for audit will be deducted in the structure `AuditClassSetting` ("'at the moment, the class `AuditClassSetting` not used"'). 

Sometimes there is a need to configure additional auditing for specific objects (that is, in addition to the audit, which is conducted for the respective class). For example, for objects `ObjectA`, `ObjectB` class `Class1` for which audit is conducted only to read, to add audit record. 

How to do it: 
* Settings by means of special commands will be given in the program code and stored in application memory during runtime in the specified new format. 

Settings for auditing objects will be set in the program code will be stored in the structure `AuditClassSetting` and define fields: 
* `LimitFunction` – [limit](fo_limit-function.html) that must be met by objects. 
* `View` – [view](fd_view-definition.html), which needs to be unloaded objects, to them it was possible to impose a limit. 

If in the above-mentioned structure `LimitFunction` and `View` empty, it will mean that the record contains the settings for the entire class. 

"'AuditFieldSetting"' 

Setting the field class defined by the programmer in the program code and stored in the structure `AuditFieldSetting`: 
* `FieldName` – the field name for which you are defining the configuration. 
* `PrunningLength` – (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 
* `Compress` – (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 
* `KeepOldValue` - (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 
* `KeepAllValues` - (see `AuditClassSetting`) if `null`, then take the value from the corresponding `AuditClassSetting`. 

## Configuration data services (AuditDSSetting) 
Configuration data services, the application requires the audit service to an audit can determine what the [service data](fo_sql-data-service.html) and where you want to write audit data. 

Information about [data services](fo_sql-data-service.html) used by the application (`AuditDSSetting`): 
* `DataServiceType` – type data service. 
* `ConnString` – the connection string of the service. 
* `ConnStringName` – the connection string name (the name provided by the programmer to identify the service данных; later in the config of the service audit will be used is the name). 

The data service when they had identified a potentially audited operation, inform the class `AuditService`. If the class `AuditService` decided that it is necessary to conduct the record audit data, among sent [service audit](efs_audit-win-service.html) information is the name of the connection string to the application database ([name of the connection string is defined](efs_data-service-for-audit.html) from `AuditDSSetting` based on what data service is and what connection string applied to the class `AuditService`). 

# Algorithm for determining current auditing settings 
The scheme of determining whether the operation is audited for class (including planned improvements): 

![Image](/images/img/page/AuditWeb/AuditFindSetting.PNG) 

If the operation is audited, it was found from settings does the idea, then for your own fields, and datalow defines the options for saving audit information in the following order: 
# Among the settings for the fields in the configuration class, the default settings are searched for the required fields, where the interesting parameters are not `null`. 
# If the settings are not found, then it uses the parameter values specified in the customizing of the default class. 
# setup If the default class is missing, then a similar search is performed for the additional configuration where the object meets the applicable `LimitFunction`. 

# Reading settings 

[AuditService](efs_flexberry-audit-components.html) basic settings feature classes gets directly from the class using `reflection`. 
General settings of the auditing application is loaded into `AuditService` at the beginning of the web application in the Global.asax.cs where there is an appeal to a specialized class `[Initial audit](efs_audit-setter.html)`, which is responsible for the formation settings from the config file and initialization `AuditService`.

The scheme of reading of the settings for `AuditService`: 

![Image](/images/img/page/AuditWeb/AuditSettingRead.PNG) 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}