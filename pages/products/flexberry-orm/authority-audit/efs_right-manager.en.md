--- 
title: Manager of office 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security, Key concepts 
toc: true 
permalink: en/efs_right-manager.html 
lang: en 
autotranslated: true 
hash: e7a43172abd70bc99e89ddc36eb408419c906f428c5584fff7a5dad844f1045b 
--- 

## RightManager 

`RightManager` - class-Manager powers. Helps the application programmer to interact with the [system authority](efs_right-manager-module.html). 

The most interesting methods RightManager'as described below. 

### Enable and disable authorization checks 

Sometimes it is necessary to perform a full disabling the authorization checks for the application (if it is a web application at once for the whole application and for all users), it needs to call: 

```csharp
RightManager.DisableAllRightChecks();
``` 

Back to include the authority, sufficient to cause: 

```csharp
RightManager.EnableAllRightChecks();
``` 

Check the current status of the subsystem powers: 

```csharp
bool dontCheckRights = RightManager.DontCheckRights;
``` 

### Dynamic change of access type for data objects 

In order not to disable the powers for the entire application, you can disable them only for specific class or group of classes. You can also include powers for classes, the code for which you cannot change. These features are available through a static method 
`RightManager.SetAdditionalAccessTypes(addAccessTypes);` 
Each invocation of this method empties caches of the service authority. 

``` csharp
var addAccessTypes = new Dictionary<Type, AccessType>
{
	{ lockDataType, AccessType.@this }
};
RightManager.SetAdditionalAccessTypes(addAccessTypes);
``` 

### Check class name with namespace 

If you are in the system objects, are imposed on the authority with the same name but different namespaces, you need to the configuration of the service authority and the management console to specify this setting: 
``` xml
    <!--this setting allows you to manage your classes name (true - name with a namespace, false for no namespace)-->
    <add key="UsingNamespaceForRights" value="false"/>
``` 
Software this setting can be obtained by contacting the property `RightManager.UsingNamespaceNotTypeName`. 

### access Checks to the object 

``` csharp
RightManager.AccessObjectCheck(EditManager.DataObject, "Update", false)
``` 

As operations can be transferred to the following values: 
the <ul> 
<a href = "FullControl":</li> 
the <li>"Full"</li> 
the <li>"Start"</li> 
the <li>"Execute"</li> 
the <li>"Open"</li> 
the <li>"Read"</li> 
the <li>"Insert"</li> 
the <li>"Delete"</li> 
the <li>"Update"</li> 
</ul> 

### getting a date of the last update of office 

``` csharp
        ///<summary> 
        /// Get the last modified date of authority 
        ///</summary> 
        ///<param name="lastUpdated">last updated</param> 
        ///<returns>the Result of the operation (OperationResult.Sibachrome if you set the application name for which the operation mode is different from CheckingMode.SimpleCheck or CheckingMode.SessionCheck)</returns> 
        ///<exception cref="ApplicationException">If you can't connect to the service, an exception will be raised</exception> 
        public static OperationResult GetLastSecurityUpdateTime(out DateTime? lastUpdated)
``` 
PstrfRightManager` this method allows to obtain the last modified date of reference (actually checked the date created and date modified of permissi, i.e. easy to add or remove users at that date not affected). This method need to organize caching of credentials at the application level. 

### get a list of the powers available to the current user 

``` csharp
        /// <summary> 
        /// Get all permissions for the specified user (SessionMode) 
        /// </summary> 
        /// <param name="subjects">List of available permissions</param> 
        /// <returns>the Result of the operation (OperationResult.Sibachrome if you set the application name for which the operation mode is different from CheckingMode.SessionCheck)</returns> 
        public static OperationResult GetAllPermitions(out List<string> subjects)
``` 
Returns a list of object names for which is it represents a permission. Even if none are selected [AccessType|AccessType), but it represents a permission to eat, then this object will also return as available.

### Update of information about the user 

``` csharp
        /// <summary> 
        /// Method to update the user information. 
        /// Only available for local service for web applications. 
        /// If the user is not found then the result will be "runtime Error". 
        /// </summary> 
        /// <param name="login">login of the user that updated information.</param> 
        /// <param name="name">the name of the user. If you pass null, it will not be updated.</param> 
        /// <param name="enabled">whether there Should be an active account user. If you pass null, it will not be updated.</param> 
        /// <returns>the Result of the operation.</returns> 
        public static UpdateResult UpdateUserInfo(string login, string name, bool? enabled)
``` 
This method works only in a web application integrated with service of office (without windows service). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}