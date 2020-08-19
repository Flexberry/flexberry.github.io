---
title: Install and configure audit
sidebar: flexberry-audit_sidebar
keywords: flexberry, audit designer, install
summary: Installing and setting up auditing in the designer and after generation
toc: true
permalink: en/fau_audit-install.html
lang: en
autotranslated: true
hash: 2016becd5378321ca156ba18e66520ee3cc1f344859d6e2626d14fa2507d8a26
---

To work audit privileges in the database and user name.
In the absence of authority on the project, it is possible to set the user name when you initialize backend using CurentUserService.

1. Configuring the database in the designer
In the settings for generating the database (stage/SQL/<Selected database>settings/database) you want to install the "DB audit, database applications and database credentials in the application database"

![](/images/pages/products/flexberry-audit/designer-storage-audit.png)

also there is the possibility to deploy a separate database for auditing.

2. Customize your model in the designer
In the settings of the application model (stage/model Properties/configuration audit) is required to establish "the audit in the application" and "Enable auditing in all classes", adding audit fields

```
+CreateTime:AuditNullableDateTime
+Creator:string
+EditTime:AuditNullableDateTime
+Editor:string
```

to all classes of the application.

![](/images/pages/products/flexberry-audit/designer-model-audit.png)

3. Configuring audit in the generated objects
When generating objects with the configured audit models in each class are added to the configuration audit.

```сs
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
      public static bool UpdateAudit = false;
      
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
   }
```
There is the possibility of configuration operations and reporting, which be an audit (By default, the audit of the operation of changing the object off).

4. In the app or the backend (to project Ember) must have the Audit packages and Security.

5. In the config settings application and the date of service for audit.

```config
<appSettings>
   <add key="AuditEnabled" value="True" />
   <add key="AppNameForAudit" value="The same application" />
   <add key="AuditConnectionStringName" value="AuditConnString_56254" />
   <add key="WriteSessions" value="False" />
   <add key="AuditWinServiceUrl" value="" />
   <add key="DefaultDSType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
   <add key="DefaultWriteMode" value="Synchronous" />
   <add key="IsAuditDatabaseLocal" value="True" />
</appSettings>
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
   <container>
      <register name="dataServiceForAuditAgentManagerAdapter" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationStringName" dependencyType="System.String" value="DefConnStr" />
      </register>
   </container>
</unity>
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}