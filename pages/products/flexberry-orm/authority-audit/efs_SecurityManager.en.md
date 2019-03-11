--- 
title: SecurityManager 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security, Key concepts 
summary: the Main implementation of the interface module authentication and authorization ISecurityManager - SecurityManager. 
toc: true 
permalink: en/efs_security-manager.html 
lang: en 
autotranslated: true 
hash: 363e0d917392f913ddd281555babf36ad9dacbc83dcdc3811ec3195c7c77860a 
--- 

## Description SecurityManager 

`SecurityManager` is used as the class for the user access rights differentiation on the server side. This class implements a common interface `ISecurityManager` used application components Flexberry to access the API subsystem powers on the server. If the application requires different from that provided by the SecurityManager implementation of subsystems of authority, it should implement the interface ISecurityManager and register this implementation with the Unity section of the configuration file. 

### engine API powers on the server 

#### access Checks the current user to the specified data type 

#### access Checks the current user for the specified operation 



## configure the application to use SecurityManager 

Configure the use `SecurityManager` in section `Unity` configuration file of the web application `web.config`. 

``` xml
  <appSettings>
    <!-- ... -->
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="DefaultConnectionStringName" value="DefConnStr" />

    <!-- If the database authority lies separately from the main, you can reference a different connection string -->
    <add key="SecurityConnectionStringName" value="CaseberrySecurity" />
    <!-- ... -->
  </appSettings>

  <!-- ... -->
  <!-- Connection string prilojeniya declared in this section. The format of connection strings, you can always peek on a special website <https://www.connectionstrings.com/>. -->
  <connectionStrings>
    <!-- ... -->
    <add name="DefConnStr" connectionString="CONNECTION STRING for the APPLICATION database" 
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService, Version=1.0.0.1, Culture=neutral, PublicKeyToken=49b42003269a4a66" />
    <add name="CaseberrySecurity"
      connectionString="THE CONNECTION STRING WITH THE DATABASE AUTHORITY"
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService, Version=1.0.0.1, Culture=neutral, PublicKeyToken=49b42003269a4a66"/>
    <!-- ... -->
  </connectionStrings>

  <!-- ... -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
      <!-- ... -->
      <!-- The configuration of the caching service. -->
      <register type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="defaultCacheForApplication" />
        </constructor>
      </register>

      <!-- securityManagerWithoutRightsCheck Manager powers off the credentials. -->
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
         mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject">
        <lifetime type="singleton" />
        <constructor />
      </register>

      <!-- The data service through which there will be a request to the authority. Here is duplicated the service type of the data, and also specify a connection string with the database credentials. To use this data service anywhere else is highly undesirable. -->
      <register name="dataServiceForSecurityManager" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
        mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" >
        <constructor>
          <!-- Manager of office used by data services to check credentials. To the data service had the opportunity to refer to objects of authority in the database for users who have not authenticated, he will use securityManagerWithoutRightsCheck is Manager powers off the credentials. -->
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <!-- The string name of the database connection credentials. -->
        <property name="CustomizationStringName" dependencyType="System.String" value="CaseberrySecurity" />
      </register>

      <!-- A caching service that will be used for temporary storage of settings, reads from the database for the Manager of the authority. Named this cache it can be completely clean if necessary. -->
      <register name="cacheServiceForSecurityManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching"
        mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching" >
        <lifetime type="singleton" />
        <constructor>
          <!-- The name of the cache that is used to carry out cleaning. -->
          <param name="cacheName" type="System.String" value="cacheForSecurityManager" />
        </constructor>
      </register>
      
      <!-- A caching service that will be used for temporary storage of settings, reads from the database for the agent Manager. Named this cache it can be completely clean if necessary. -->
      <register name="cacheServiceForAgentManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForAgentManager" />
        </constructor>
      </register>
      
      <!-- Manager of office, which will be used by the data service DataServiceProvider.DataService. -->
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
        mapTo="NewPlatform.Flexberry.Security.SecurityManager NewPlatform.Flexberry.Security">
        <constructor>
          <!-- dataServiceForSecurityManager data service, through which there will be a request to the authority. -->
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForSecurityManager" />
          </param>
          <!-- The credentials included. -->
          <param name="enabled" type="System.Boolean" value="true"/>
          <!-- The authorization check for the objects included. -->
          <param name="useRightsOnObjects" type="System.Boolean" value="true"/>
          <!-- Authorization for attributes is enabled. -->
          <param name="useRightsOnAttribute" type="System.Boolean" value="true"/>
        </constructor>
      </register>

      <!-- The agent Manager. -->
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

      <!-- Hesher password for the application that will be used in particular for password-hashing agents of authority. In the constructor, hesaru specify using salt string salt parameter.-->
      <register type="NewPlatform.Flexberry.Security.IPasswordHasher, NewPlatform.Flexberry.Security" mapTo="NewPlatform.Flexberry.Security.Sha1PasswordHasher, NewPlatform.Flexberry.Security">
        <lifetime type="singleton" />
        <constructor />
      </register>

      <!-- Service that allows to resolve the name of the connection string passed via the parameter CustomizationStringName.-->
      <register type="ICSSoft.STORMNET.Business.IConfigResolver, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.ConfigResolver, ICSSoft.STORMNET.Business">
        <lifetime type="singleton" />
        <constructor />
      </register>
    <!-- ... -->
    </container>
  </unity>
  <!-- ... -->
  <system.web>
    <!-- ... -->
    <!-- Provider that is used to perform user authentication. -->
    <membership defaultProvider="FlexberryMembershipProvider">
      <providers>
        <clear/>
        <add name="FlexberryMembershipProvider" type="NewPlatform.Flexberry.Security.FlexberryMembershipProvider"/>
      </providers>
    </membership>
    <!-- Provider that allows you to define roles that users have (used e.g. in the sitemap roles). -->
    <roleManager defaultProvider="FlexberryRoleProvider" enabled="true">
      <providers>
        <clear/>
        <add name="FlexberryRoleProvider" type="NewPlatform.Flexberry.Security.FlexberryRoleProvider"/>
      </providers>
    </roleManager>
    <!-- Provider profile the user. -->
    <profile enabled="true" defaultProvider="FlexberryProfileProvider" automaticSaveEnabled="false">
      <providers>
        <clear/>
        <add name="FlexberryProfileProvider" type="NewPlatform.Flexberry.Security.FlexberryProfileProvider"/>
      </providers>
      <properties>
        <add name="FriendlyName"/>
        <add name="AgentKey"/>
      </properties>
    </profile>
    <!-- ... -->
  </system.web>
  <!-- ... -->
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}