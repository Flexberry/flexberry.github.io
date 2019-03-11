--- 
title: Installing Legacy Security 
sidebar: flexberry-security_sidebar 
keywords: flexberry, legacysecurity, legacy, security, install 
summary: Installation LegacySecurity 
toc: true 
permalink: en/fs_legacy-security-install.html 
lang: en 
autotranslated: true 
hash: 94c6ef3ebf5cccacc675bef73ce08530e7e2c3838c4e3385235e8239f2fdaaf6 
--- 

## overview LegacySecurity 
Classes from the old powers (`RightManager`, `Checking`, etc., including the old membership and other providers for ASP.NET applications) currently installed package `NewPlatform.Flexberry.LegacySecurity`. 

## Installation LegacySecurity 
The latest version of Flexberry ASP.NET compatible with the old powers, but initially is in the dependency package `NewPlatform.Flexberry.Security` (as well as other types of applications that used the credentials). 

To install the package `NewPlatform.Flexberry.LegacySecurity` should: 

1. To remove a package `NewPlatform.Flexberry.Security` (if installed in applications) and the associated changes in the config (not paying attention to the relationship with the package `NewPlatform.Flexberry.AspNet`, if we are talking about Flexberry ASP.NET-app). 

2. Install the packages (the latest beta): 
* `NewPlatform.Flexberry.ORM` 
* `NewPlatform.Flexberry.AspNet` (if we are talking about Flexberry ASP.NET-app) 
* `NewPlatform.Flexberry.Audit` 
* `NewPlatform.Flexberry.LegacySecurity` 

Almost all packages now sewn the transformation of the config, so in section `unity` itself will set a minimum of what I need, but with the default settings (except the membership and other providers for Flexberry ASP.NET applications, they must be configured in the config file manually). 

3. Configure in the configuration file the name of the app: 

In the following fragment of a configuration file name `WebApp` must be the same in these locations. The application name can have any name - the main thing that it is identical in all of these areas. 

``` xml
<configuration>
   ...
   <appSettings>
      ...
       <!-- Here it is necessary to register the name of the application -->
      <add key="applicationName" value="WebApp" />
      ...
   </appSettings>
   <container>
      ...
      <!-- System configuration authority. -->
      <register type="ISecurityService" mapTo="CheckingInSessionMode">
        <lifetime type="singleton"/>
        <constructor>
          <!-- Here it is necessary to register the name of the application -->
          <param name="imprisone" type="System.String" value="WebApp"/>
        </constructor>
      </register>
      ...
   </container>
   ...
   <system.web>
      <membership defaultProvider="CaseberryMembershipProvider">
        <providers>
          <clear />
          <!-- Here it is necessary to register the name of the application -->
          <add name="CaseberryMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider" applicationName="WebApp" />
        </providers>
      </membership>
   </system.web>
</configuration>
``` 

The configuration membership and other providers relevant only for Flexberry ASP.NET applications. 

4. To validate the configuration file in the following example: 

For example, the configuration subsystem powers on PostgreSQL. 

``` xml
<configuration>
   <configSections>
      ...
      <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
      ...
   </configSections>
   ...
   <appSettings>
      ...
      <add key="applicationName" value="WebApp"/>
      <add key="DataServiceType" value="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService"/>
      <add key="DefaultConnectionStringName" value="DefConnStr"/>
      <add key="DRDataServiceType" value="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService"/>
      <add key="DefaultDSType" value="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService"/>
      ...
      <!--If the database authority lies separately from the main, you can reference a different connection string-->
      <add key="SecurityConnectionStringName" value="DefConnStr" />
      ...
   </appSettings>
   ...
   <connectionStrings>
      <add name="DefConnStr" connectionString="..." providerName="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService"/>
   </connectionStrings>
   ...
   <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
      <alias alias="ISecurityService" type="ICSSoft.STORMNET.Security.ISecurityService, ICSSoft.STORMNET.DataObject"/>
      <alias alias="CheckingInSessionMode" type="ICSSoft.STORMNET.Security.CheckingInSessionMode, CheckingLibrary"/>
      <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity"/>
      ...
      <container>
         ...
         <!-- System configuration authority. -->
         <register type="ISecurityService" mapTo="CheckingInSessionMode">
            <lifetime type="singleton"/>
            <constructor>
               <!-- Here it is necessary to register the name of the application -->
               <param name="imprisone" type="System.String" value="WebApp"/>
            </constructor>
         </register>
         ...
         <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
            <constructor>
               <param name="enabled" type="System.Boolean" value="true" />
               <!-- The following two parameters can be wakazashi if necessary -->
               <param name="useRightsOnObjects" type="System.Boolean" value="..." />
               <param name="useRightsOnAttribute" type="System.Boolean" value="..." />
            </constructor>
         </register>
         ...
         <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject">
            <lifetime type="singleton" />
            <constructor />
         </register>
         ...
         <register type="ICSSoft.STORMNET.Business.IConfigResolver, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.ConfigResolver, ICSSoft.STORMNET.Business">
            <lifetime type="singleton" />
            <constructor />
         </register>
         ...
      </container>
   </unity>
   ...
   <system.web>
      ...
      <membership defaultProvider="CaseberryMembershipProvider">
         <providers>
            <clear/>
            <add name="CaseberryMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider" applicationName="WebApp"/>
         </providers>
      </membership>
      <roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
         <providers>
            <clear/>
            <add name="CaseberryRoleProvider" type="CheckingLibrary.CaseberryDomainRoleProvider"/>
         </providers>
      </roleManager>
      ...
   </system.web>
   ...
<configuration>
``` 

The configuration membership and other providers relevant only for Flexberry ASP.NET applications. 

5. For Flexberry ASP.NET applications it is also possible to use decorators for caching of credentials and services user settings: 

Check interfaces `ISecurityManager` and `IUserSettingsService` in section `unity` must be performed in such manner as is specified in the example below. 

``` xml
<configuration>
   <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
      ...
      <container>
         ...
         <!-- System configuration authority. -->
         <register name="DecorableSecurityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
            <lifetime type="singleton"/>
            <constructor/>
         </register>
         <register name="CacheForSecurityManager" type="NewPlatform.Flexberry.Services.ICacheService, ICSSoft.STORMNET.Web.Tools" mapTo="NewPlatform.Flexberry.Services.InternalCacheService, ICSSoft.STORMNET.Web.Tools">
            <lifetime type="singleton"/>
         </register>
         <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="NewPlatform.Flexberry.Security.SecurityManagerCacheDecorator, ICSSoft.STORMNET.Web.Tools">
            <constructor>
               <param name="decorable" dependencyName="DecorableSecurityManager"/>
               <param name="cache" dependencyName="CacheForSecurityManager"/>
            </constructor>
         </register>
         ...
         <!-- The service configuration custom settings and enabled caching. -->
         <register name="DecorableUserSettingsService" type="ICSSoft.Services.IUserSettingsService, UserSettingsService" mapTo="ICSSoft.Services.UserSettingsService, UserSettingsService">
            <lifetime type="singleton"/>
            <constructor/>
         </register>
         <register name="CacheForUserSettingsService" type="NewPlatform.Flexberry.Services.ICacheService, ICSSoft.STORMNET.Web.Tools" mapTo="NewPlatform.Flexberry.Services.InternalCacheService, ICSSoft.STORMNET.Web.Tools">
            <lifetime type="singleton"/>
         </register>
         <register type="ICSSoft.Services.IUserSettingsService, UserSettingsService" mapTo="NewPlatform.Flexberry.Services.UserSettingsServiceCacheDecorator, ICSSoft.STORMNET.Web.Tools">
            <lifetime type="singleton"/>
            <constructor>
               <param name="decorable" dependencyName="DecorableUserSettingsService"/>
               <param name="cache" dependencyName="CacheForUserSettingsService"/>
            </constructor>
         </register>
         ...
      </container>
   </unity>
   ...
<configuration>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}