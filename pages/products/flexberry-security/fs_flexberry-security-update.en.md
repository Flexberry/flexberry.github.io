--- 
title: Update Flexberry Security 
sidebar: flexberry-security_sidebar 
keywords: flexberry, security, update 
summary: Update Flexberry Security from 1.7.0 to 2.0.0 and above. 
toc: true 
permalink: en/fs_flexberry_security_update.html 
lang: en 
autotranslated: true 
hash: f1898b6efdcc485422e7870a73140ae63837cce1cb3e63bfd0b51d18d1ee2590 
--- 

## Features new powers 
We have RightManager ("the representative" old power), which historically has gathered working in 3 different options of implementing powers (starting with AzMan) is CheckingLibrary that implements two out of three of these options are sometimes not optimally. 
Plus, it is all implemented as static methods, i.e. any of the settings in the powers affect the entire application at once, and no way of flexibility to configure. 
Ie, have a bunch of legacy code with cockroaches, which has no meaning, no desire to maintain. 

Plus the new version of office has new features such as the ability to apply filters to copies of the data objects (i.e. add LimitFunction-s-level powers), rewritten caching, implemented software control of the agents will be implemented program management classes, operations, etc. i.e. everything in the web admin interface or from the console polnocy can be done through the interface can be done programmatically. 

## Update Security 

Classes from the old powers (`RightManager`, `Checking`, etc., including the old membership providers all) now live in the package [NewPlatform.Flexberry.LegacySecurity](fs_legacy-security-install.html). 

{% include note.html content="This package does NOT need to be put together with a package `NewPlatform.Flexberry.Security`! 
I.e. in the code should be based on either of the old classes of office, or only from the new one." %} 

The latest version of `ASP.NET` compatible with the old powers. 
There are a number of changes to section `Unity` in the config that have appeared since the release of the latest alpha versions of new powers. 

In General to install and configure necessary: 

1. Before updating all the packages to remove all old settings from `Unity`: 
* Named data services for the Manager powers of the Manager and agents: `ICSSoft.STORMNET.Security.ISecurityManager`, `DecorableSecurityManager`, `securityManagerWithoutRightscheck`. 
* Named cache services Manager of the authority and the providers of the agent Manager: `CacheForUserSettingsService`, `DecorableUserSettingsService`, `CacheForSecurityManager`. 
* Unnamed permissions interfaces `IPasswordHasher`, `IAgentManager`, `ISecurityManager`. 
* Other crap associated with powers. 

2. Upgrade packages (to latest beta): 
* `NewPlatform.Flexberry.Security` 
* `NewPlatform.Flexberry.ORM` 
* `NewPlatform.Flexberry.Caching` 
* `NewPlatform.Flexberry.AspNet` 
* `NewPlatform.Flexberry.Audit` 

Almost all packages now sewn the transformation of the config, so in section `Unity` almost established that the need itself, but with the default settings 

3. After the installation of the packages to change the settings, which is necessary, in the config file in the section `Unity`: 
* Set the desired types of data services in mapping. 
* To fix the value of the properties `CustomizationStringName` named in the registration data services: `dataServiceForSecurityManager`, `dataServiceForAuditAgentManageradapter` (this registration is done «just in case» - in case the audit authority for some reason does not transmit the data service is configured to work with classes of authority). In `CustomizationStringName` property specifies the connection string name from section `connectionStrings` configuration file. 
* If needed you can add salt to hash passwords. Now Sol is specified as a parameter in the constructor of hesher password (not the Manager agents as previously!). You can also change the type of hesher passwords from Sha1 to something else, if you are designing a new app and there is no database with the old hashes. 
* To replace the membership, role and profile providers on the providers from» «new powers: `FlexberryMembershipProvider`, `FlexberryRoleProvider` and `FlexberryProfileProvider`, namespace `NewPlatform.Flexberry.Security`, Assembly `NewPlatform.Flexberry.Security`. 
* Clean interface resolution `ISecurityService` on `CheckingInSessionMode` (remove from config if any). 

The main changes `Security`: 
1. New powers now have 2 of main interface (and thus their technological implementations): `ISecurityManager` (Manager of office) and `IAgentManager` (Manager agents). The Manager of the powers bound to the service data (as when reading data from the database should be checked powers). The agent Manager is not bound to a data service (for it service data can be configured) and is auxiliary (the whole software agents, checks for the existence of agents, obtaining a list of agents and so rendered there). At the moment the application needs to be registered necessarily (in Unity) one Manager agents using the unnamed register. Membership and other providers, as well as some other components of office work through this registration Manager agents. 
2. As for Manager powers, and of the agent Manager is implemented internal caching of objects of office – productivity with new powers should grow. This uses a new cache service from the new package (ICacheService interface and it is one technological implementation – MemoryCacheService based on the MemoryCache). 
3. Hashing passwords were issued to the business server for the agents of authority, so hashing passwords at the application level (before you record the agent in the database) all you need to poubirat! 
4. Clearing the cache from the Manager of the authority, and the agent Manager is also now in the business class servers of the powers during each operation of creating, modifying, or deleting classes of authority – thus ensuring the relevance of the data in the cache. If there is a software clearing your cache in the application layer during these operations, it can also poubirat. 
5. When hashing passwords is now possible to specify the salt and choose the hashing algorithm of passwords (`MD5`, `SHA1`, `SHA256` or empty» qmo). This case is configured in Unity by specifying the permissions interface `IPasswordHasher` (`Md5PasswordHasher`, `Sha1PasswordHasher`, `Sha256PasswordHasher` or `EmptyPasswordHasher`). Salt is indicated, if necessary, in the class constructor (the salt parameter of type string). 

<b>note.</b> In the Manager of agents have not yet been implemented delete operation for users, roles, groups (to exclude agents from the roles and groups you can, this is implemented). 
Configuration for new powers should now look like the following (when installing packages section of the config konfigurerede Unity approximately as in the following example): 

```xml
<appSettings>
    …
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="DefaultConnectionStringName" value="DefConnStr" />
    <!—Если БД полномочий лежит отдельно от основной, можно сослаться на другую строку соединения-->
    <add key="SecurityConnectionStringName" value="DefConnStr" />
    …
  </appSettings>
    …
  <connectionStrings>
    …
    <add name="DefConnStr" connectionString="THE CONNECTION STRING WHERE NECESSARY"
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    …
  </connectionStrings>
  …
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    …
    <container>
      …
      <!-- The configuration of the caching service. -->
      <register type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="defaultCacheForApplication" />
        </constructor>
      </register>
      <!-- System configuration authority. -->
         <register name="dataServiceForAuditAgentManagerAdapter" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationStringName" dependencyType="System.String" value="DefConnStr or another name of the connection string with the database AUTHORITY" />
      </register>
      <register name="dataServiceForSecurityManager" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationStringName" dependencyType="System.String" value="DefConnStr or another name of the connection string with the database AUTHORITY" />
      </register>
      <register name="cacheServiceForSecurityManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForSecurityManager" />
        </constructor>
      </register>
      <register name="cacheServiceForAgentManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForAgentManager" />
        </constructor>
      </register>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="NewPlatform.Flexberry.Security.SecurityManager NewPlatform.Flexberry.Security">
        <constructor>
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForSecurityManager" />
          </param>
          <param name="enabled" type="System.Boolean" value="true" />
          <param name="useRightsOnObjects" type="System.Boolean" value="false" />
          <param name="useRightsOnAttribute" type="System.Boolean" value="false" />
        </constructor>
      </register>
      <register type="NewPlatform.Flexberry.Security.IAgentManager, NewPlatform.Flexberry.Security" mapTo="NewPlatform.Flexberry.Security.AgentManager, NewPlatform.Flexberry.Security">
        <constructor>
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForAgentManager" />
          </param>
        </constructor>
      </register>
      <register type="NewPlatform.Flexberry.Security.IPasswordHasher, NewPlatform.Flexberry.Security" mapTo="NewPlatform.Flexberry.Security.Sha1PasswordHasher, NewPlatform.Flexberry.Security">
        <lifetime type="singleton" />
        <constructor />
      </register>
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject">
        <lifetime type="singleton" />
        <constructor />
      </register>
      <register type="ICSSoft.STORMNET.Business.IConfigResolver, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.ConfigResolver, ICSSoft.STORMNET.Business">
        <lifetime type="singleton" />
        <constructor />
      </register>
      …
    </container>
  </unity>
  …
  <system.web>
    …
    <membership defaultProvider="FlexberryMembershipProvider">
      <providers>
        <clear />
        <add name="FlexberryMembershipProvider" type="NewPlatform.Flexberry.Security.FlexberryMembershipProvider, NewPlatform.Flexberry.Security, Version=1.0.0.0, Culture=neutral, PublicKeyToken=b04c483e14c0d306" applicationName="SLAuthSample" />
      </providers>
    </membership>
    <roleManager defaultProvider="FlexberryRoleProvider" enabled="true">
      <providers>
        <clear />
        <add name="FlexberryRoleProvider" type="NewPlatform.Flexberry.Security.FlexberryRoleProvider, NewPlatform.Flexberry.Security, Version=1.0.0.0, Culture=neutral, PublicKeyToken=b04c483e14c0d306" />
      </providers>
    </roleManager>
    <profile defaultProvider="FlexberryProfileProvider" enabled="true" automaticSaveEnabled="false">
      <providers>
        <clear />
        <add name="FlexberryProfileProvider" type="NewPlatform.Flexberry.Security.FlexberryProfileProvider, NewPlatform.Flexberry.Security, Version=1.0.0.0, Culture=neutral, PublicKeyToken=b04c483e14c0d306" />
      </providers>
      <properties>
        <add name="FriendlyName" />
        <add name="AgentKey" />
      </properties>
    </profile>
    …
  </system.web>
```` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}