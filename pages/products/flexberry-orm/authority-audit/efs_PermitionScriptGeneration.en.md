---
title: Generation of script assign privileges
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security
toc: true
permalink: en/efs_permition-script-generation.html
lang: en
autotranslated: true
hash: 454813e145b653382961680c1e426d9be4921fba77f93859fa1557460e61c6df
---

(((
In version [management authority](efs_security-console.html) after 21.03.2013 there is an additional menu item "align SQL server" (via the menu: SQL scripts -> align SQL server). After selecting this menu item the generate script to assign permissions to the application database.

In the version of the management console powers after 15.10.2014 considered the possibility of setting restrictions on table columns.
)))

# The script assign privileges

There is the application database and another database where you define users and their credentials. This implementation of interaction does not allow the use of SQL server for check authorization and implementation of data protection.

Generated script organizes the setting in the application database in SQL server according to the specified parameters in the database subsystem powers.

## An example script assigning authority

For example, we have user `ICS_HOME\VPupkin`. In [management authority](efs_security-console.html) we have a user with login `VPupkin` playgroup `ICS_HOME`. The user `VPupkin` assigned access `Insert` class `Учебник` from the app `АбвгдApp` database `АбвгдDB`.

Thus, if to simplify everything, the generated script needs to access the SQL server:
* create a login for a windows user `ICS_HOME\VPupkin` on the server if it was not created by ранее;
* create user application database `АбвгдDB` corresponding login `ICS_HOME\VPupkin` if it was not created by ранее;
* assign this user right to `Insert` in table `Учебник`, prohibit `Delete`, `Update` and `Select` in table `Учебник`;
 *define user privileges on the other tables if necessary.

## Users, roles and groups

Define the terminology used when generating the script to avoid any confusion:
* If the domain `ICS_HOME` is, the user `VPupkin`, the [management authority](efs_security-console.html) it will be set so the user `VPupkin` is in the group `ICS_HOME` (and `ics.perm.ru`, respectively).
* If the user `ICS_HOME\VPupkin` is in the group `ICS_HOME\Администраторы`, the management authority) is to be set so the user `VPupkin` is in the role `Администраторы`.

The script organizes not only create logins in SQL server for users in the console is defined in the desired group exist in the domain, but also for groups that are in the console, specified roles exist in the domain.

## Features of the script assign privileges

* "'Shared permission on the tables."' It is believed that if the class [AccessType](fo_access-type.html) is set to none, then no validation on this class should not be done. Accordingly, all users must be granted access to the appropriate class tables.
Other authorization checks for the class|AccessType] are interpreted as `@this`.
* "'The assignment of rights."' For classes that have [AccessType](fo_access-type.html) not `none` it is believed that the operation `Insert`, `Delete`, `Update` and `Select` prohibited, if the user has no special permissions.
* "'The right filter."' At the moment, the restriction of access include the filter, is ignored.
* "'(From MSDN)"' Ban (DENY) at the table level has a lower priority than granting permissions (GRANT) at the column level. This inconsistency in the permissions hierarchy has been preserved for backward compatibility.
* "'Owner of the database."' If the users in the system of authority present owner of the relevant database, the script generated will not be because the owner of the database right should not change.

## To set permissions for table columns

In version after 15.10.2014 available [specify the authority to separate object properties](efs_check-access-to-attribute.html). This functionality is also available to generate script to assign permissions in SQL server.
To assign permissions, you must specify the appropriate attributes for the purpose of operations on the properties of the object, then the user in the management console, the power to give rights to the appropriate operations.

"'Features"'
* If the property is, the operation and the user does not have any permission for this transaction, he receives a ban to SELECT from the column corresponding to property (limit is based only on a read operation).
* Detayli not taken into account when assignments of authority to the columns.
* As the masters of the object due to the inheritance can be multiple columns-vaults, then the same powers will be imposed on all the columns.

## A General algorithm generating the script for user

Algorithm script generation for a single user in a specific example.

* The user if it has not previously been added added to the server and corresponding database.
* Suppose there is a class `FirstClass` and `SecondClass` (the names of the relevant tables match the class names).
* Let both classes have the same properties with the designated operations:
** "Property1" – «operationName1, operationName2».
** "Property2" operation not assigned.
** "Property3" – «operationName3».
** "Property4" – «operationName2».
* Let the current user has the following permissions:
** On the class specified `FirstClass` access `Full` filter `Read`, `Insert` without filters.
** On the class specified `ThirdClass` access `Full` without a filter.
** For the operation operationName1» «permissions without filters: `Full`, `Read`, `Insert`.
** For the operation operationNameOther» «permissions without filters: `Full`.
** For the operation operationName2» «permissions» «Update without filter and `Full `с filter.

Then the script will be generated in the following way:

"'1."' Classes are searched, for which the user has no rights. All table columns are removed permissions that were able to be there.

In our case, the class `SecondClass` exists, but that the user has permission to it is not a ban on all transactions above the table, the removal of the bans from the column read operations (other operations we do not consider).

```csharp
SET @common_cmd = 'DENY SELECT, INSERT, UPDATE, DELETE  ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage1], [PropertyStorage2], [PropertyStorage3], [PropertyStorage4]) ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)
```
"'2."' Is determined, what rights do I have for the remaining classes, then define the set of operations that should be prohibited to the user.

In this case, the class» «FirstClass we have a right `Read `и `Insert`, but there is no `Delete `и `Update`.

```csharp
SET @specific_cmd = 'DENY DELETE, UPDATE  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT, INSERT  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
```

"'3."' The classes to which the user has permissions, define properties, which do not specify any operations. With such properties, reset all restrictions on available operations.

In this case, this property Property2» qmo.

```csharp
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage2]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)
```

"'4."' The classes to which the user has permissions, define properties, are imposed on transactions, but the user has no rights to any operation. On properties subject to restrictions in the available column operations.

In this case, this property «Property3» (qmc «on operationName3 the user has no rights).

```csharp
SET @common_cmd = 'DENY SELECT ([PropertyStorage3]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)
```

"'5."' For the remaining table properties on the basis of existing operations permissions, calculated permissions and they are appointed.

In this case, Property1» «has Full access, Property4 «and» has access to `Update `(enough to get the right reading.)

```csharp
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage1]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage4]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
```


"'The final script for the user:"'

```csharp
SET @common_cmd = 'DENY SELECT, INSERT, UPDATE, DELETE  ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage1], [PropertyStorage2], [PropertyStorage3], [PropertyStorage4]) ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'DENY DELETE, UPDATE  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT, INSERT  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage2]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'DENY SELECT ([PropertyStorage3]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage1]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage4]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
```

"'The whole script is entirely similar to the following:"'

<msg type=warning>In connection with the features of wiki markup in lines 31 and 68 of the code example below is deliberately wrong. To get the correct version of the code, you need to remove extra white space between repeating character @.</msg>

(((
```csharp
BEGIN TRY 
 BEGIN TRANSACTION ChangePermitions WITH MARK N'Changing permitions' 


  USE TestInhDB
 DECLARE  
    @user_name sysname, 
    @common_cmd nvarchar(MAX), 
    @specific_cmd nvarchar(MAX) 

 IF NOT EXISTS 
   (SELECT * FROM sys.server_principals where type='U' AND name = 'ICS_HOME\tosotova) 
 BEGIN 
 	CREATE LOGIN [ICS_HOME\ tosotova] FROM WINDOWS 
 END 

 IF NOT EXISTS 
   (SELECT *  FROM sys.database_principals WHERE type = 'U' AND SUSER_SNAME(sid) = 'ICS_HOME\ tosotova ')
 BEGIN 
 	CREATE USER [tosotova] FOR LOGIN [ICS_HOME\ tosotova] 
 END 

 DECLARE UserCursor CURSOR FOR  
 SELECT TOP 1 name  
 FROM sys.database_principals  
 WHERE type = 'U' AND SUSER_SNAME(sid) = 'ICS_HOME\ tosotova ' 
 OPEN UserCursor  
 
 FETCH NEXT FROM UserCursor INTO @user_name  
 
 IF @@fetch_status = 0 
 BEGIN  

SET @common_cmd = 'DENY SELECT, INSERT, UPDATE, DELETE  ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage1], [PropertyStorage2], [PropertyStorage3], [PropertyStorage4]) ON object::[SecondClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'DENY DELETE, UPDATE  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT, INSERT  ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @common_cmd = 'REVOKE SELECT ([PropertyStorage2]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @common_cmd = 'DENY SELECT ([PropertyStorage3]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@common_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage1]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  
SET @specific_cmd = 'REVOKE SELECT ([PropertyStorage4]) ON object::[FirstClass] TO ' + @user_name  
EXEC (@specific_cmd)  

 END  
 
 CLOSE UserCursor  
 DEALLOCATE UserCursor  
 

 COMMIT TRANSACTION ChangePermitions  
 END TRY 
 BEGIN CATCH 
   DECLARE @ErrorMessage NVARCHAR(4000); 
   DECLARE @ErrorSeverity INT; 
   DECLARE @ErrorState INT; 
  
   SELECT  
     @ErrorMessage = ERROR_MESSAGE(), 
     @ErrorSeverity = ERROR_SEVERITY(), 
     @ErrorState = ERROR_STATE(); 
     IF @@TRANCOUNT > 0 
         ROLLBACK TRANSACTION ChangePermitions  
     RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState) 
 END CATCH
```
)))

## The generation settings

When you select the menu item "align SQL server" window will appear with the settings for the generation

![](/images/pages/img/page/PermitionScriptGeneration/GenerationSettings.png)


### Removing the "extra" windows users

This setting is useful in the following situation: let the script was created some users and assigned rights. Further, the user has been deleted on the management console credentials. When you select this setting, the script will check for these users and try to remove them.

### The use of the script after generation

Without this setting, after generating the user will be given the opportunity to review and edit the script. If the setting is enabled, the script will be immediately submitted for execution without further proof.

### The connection string

This setting defines the connection string to the application database.

The connection string to the database application, you can specify in the config management authority in setting up `ApplicationConnectionString` in section `connectionStrings`:

```xml
<?xml version="1.0"?>
<configuration>
	<!-- ... -->
	<connectionStrings>
		<!-- ApplicationConnectionString - the connection string to the database application-->
		<add name="ApplicationConnectionString" connectionString="SERVER=server;Trusted_connection=yes;DATABASE=Security_test2;" />
	</connectionStrings>
	<!-- ... -->
</configuration>
```

If config is not specified this setting, the default connection string to the database management office.

### Build with objects

The build objects required to retrieve class attributes, which overlap the powers.

One of the attributes shall be deducted:

* `ClassStorageAttribute`(that is the name of the table that stores objects of the corresponding class);
* `AccessTypeAttribute` (that is, [the type of authorization checks for a class](fo_access-type.html)).

Select assemblies from the list that appears when you click "Choose Assembly...". Search assemblies with objects is done by default in the folder where the application was started. To change the folder where you will be searched assemblies with objects, you can press the button "Path to the Assembly..." (see the current path in the pop-up this button tooltip).

## Audit of the use of scripts

The system maintains [audit](efs_audit.html) scripting assignment authority.

To view the audit data through [label](fw_desktop-operations.html) on [desktop](fw_app-desktop.html): Audit -> Audit operations.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}