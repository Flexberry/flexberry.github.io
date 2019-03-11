--- 
title: Flexberry Security API ISecurityManager 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Flexberry Security, authority, service authority 
summary: Services run-time subsystem powers Flexberry 
toc: true 
permalink: en/fo_i-security-manager.html 
lang: en 
autotranslated: true 
hash: 2b5335650908d006e212e8935fe5bd92b46942ed190b3615df7170dd5cbd2b52 
--- 

Services run-time subsystem powers Flexberry available through the interface `ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject`. 

## access to instance of ISecurityManager 

This interface allowed real authority [Flexberry UnityFactory](fo_unity-factory.html) as follows: 

```csharp
IUnityContainer container = UnityFactory.CreateContainer();
ISecurityManager securityManager = container.Resolve<ISecurityManager>();
``` 

## configuration ISecurityManager using Unity Container 

ISecurityManager used in [Flexberry ORM](fo_flexberry-orm.html) and must be configured through the Unity Container in one of the following ways. 

### Method 1. Setting in the configuration file 

Example settings in the configuration file (`app.config` or `web.config`) for included system powers. 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <!--...-->

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
    <container>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
       <constructor/>
      </register>

      <!--Define a named instance of the ISecurityManager that is written "new DefaultSecurityManager(false)".-->
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
        <lifetime type="singleton" />
        <constructor>
          <param name="enabled" type="System.Boolean" value="false" />
        </constructor>
      </register>

    </container>
  </unity>

  <!--...-->
	
</configuration>
``` 

**Note:** for correct interaction [CheckingLibrary](efs_security-legacy-services.html) [RightManager](efs_right-manager.html) in the configuration file must be named add registration permission `ISecurityManager` with the name `securityManagerWithoutRightscheck`. 

If the system of powers is not applied, it is possible to use a simplified version of the configuration: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <!--...-->

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject" />
    </container>
  </unity>

  <!--...-->
	
</configuration>
``` 

## Control access rights at the level of the service instance data 

To control the verification mechanism of [authority](efs_right-manager-module.html) at the level of [data service](fo_data-service.html) implemented a special constructor for [data services](fo_data-service.html) to disable or authorization check in this instance. This avoids switching off the authorization check for the entire application, if there is a real need to disable power to one or more operations. 

The constructor takes as a parameter the instance `ICSSoft.STORMNET.Security.ISecurityManager`, which contains the settings of the mechanism of authorization checks. As default implementation, you can use the implementation of this interface: `DefaultSecurityManager`. 

### Sample code 

```csharp
using ICSSoft.STORMNET.Security;
// ... 
ISecurityManager securityManager = new DefaultSecurityManager(false);
MSSQLDataService dataService = new MSSQLDataService(securityManager);

// If you need restrictions on detaily, the data service need and of this design. 
ExternalLangDef langdef = new ExternalLangDef { DataService = ds };

// Logic for working with the data service ignores the authorization check. 
// ... 
DataObject[] dataObjects = dataService.LoadObjects(lcs);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}