---
title: Установка Legacy Security
sidebar: flexberry-security_sidebar
keywords: flexberry, legacysecurity, legacy, security, install
summary: Установка LegacySecurity
toc: true
permalink: ru/fs_legacy-security-install.html
lang: ru
---

##  Общие сведения о LegacySecurity
Классы старых полномочий (`RightManager`, `Checking` и пр., в том числе старые варианты мембершип- и прочих провайдеров для ASP.NET-приложений) на данный момент устанавливаются с пакетом `NewPlatform.Flexberry.LegacySecurity`.

##  Установка LegacySecurity
Последняя версия Flexberry ASP.NET совместима со старыми полномочиями, но изначально имеет в зависимостях пакет `NewPlatform.Flexberry.Security` (как и другие типы приложений, которые использовали полномочия).

Для установки пакета `NewPlatform.Flexberry.LegacySecurity` необходимо:

1. Удалить пакет `NewPlatform.Flexberry.Security` (если он установлен в приложений) и связанные с ним изменения в конфиге (не обращая внимания на связь с пакетом `NewPlatform.Flexberry.AspNet`, если речь идет о Flexberry ASP.NET-приложении).

2. Установить пакеты (последние бета-версии):
    * `NewPlatform.Flexberry.ORM`
    * `NewPlatform.Flexberry.AspNet` (если речь идет о Flexberry ASP.NET-приложении)
    * `NewPlatform.Flexberry.Audit`
    * `NewPlatform.Flexberry.LegacySecurity`

Почти во всех пакетах сейчас зашиты трансформации конфига, поэтому в секции `unity` само установится минимум из того, что нужно, но с параметрами по умолчанию (за исключением мембершип- и прочих провайдеров для Flexberry ASP.NET-приложений, их нужно настроить в конфиге вручную).

3. Настроить в конфигурационном файле имя приложения:

В нижеприведенном фрагменте файла конфигурации имя `WebApp` должно быть одинаковым в указанных местах. Имя приложения может иметь и любое другое имя - главное, чтобы оно совпадало во всех указанных местах.

``` xml
<configuration>
   ...
   <appSettings>
      ...
       <!-- Тут надо прописать имя приложения -->
      <add key="applicationName" value="WebApp" />
      ...
   </appSettings>
   <container>
      ...
      <!-- Конфигурация системы полномочий. -->
      <register type="ISecurityService" mapTo="CheckingInSessionMode">
        <lifetime type="singleton"/>
        <constructor>
          <!-- Тут надо прописать имя приложения -->
          <param name="sИмяПриложения" type="System.String" value="WebApp"/>
        </constructor>
      </register>
      ...
   </container>
   ...
   <system.web>
      <membership defaultProvider="CaseberryMembershipProvider">
        <providers>
          <clear />
          <!-- Тут надо прописать имя приложения -->
          <add name="CaseberryMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider" applicationName="WebApp" />
        </providers>
      </membership>
   </system.web>
</configuration>
```

Наличие конфигурации мембершип- и прочих провайдеров актуально только для Flexberry ASP.NET-приложений.

4. Проверить файл конфигурации на соответствие приведенному ниже примеру:

Для примера приводится конфигурация подсистемы полномочий на СУБД PostgreSQL.

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
      <!--Если БД полномочий лежит отдельно от основной, можно сослаться на другую строку соединения-->
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
         <!-- Конфигурация системы полномочий. -->
         <register type="ISecurityService" mapTo="CheckingInSessionMode">
            <lifetime type="singleton"/>
            <constructor>
               <!-- Тут надо прописать имя приложения -->
               <param name="sИмяПриложения" type="System.String" value="WebApp"/>
            </constructor>
         </register>
         ...
         <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
            <constructor>
               <param name="enabled" type="System.Boolean" value="true" />
               <!-- Следующие два параметра можно уаказывать при необходимости -->
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

Наличие конфигурации мембершип- и прочих провайдеров актуально только для Flexberry ASP.NET-приложений.

5. Для Flexberry ASP.NET-приложений есть также возможность использовать кеширующие декораторы для полномочий и сервиса пользовательских настроек:

Регистрацию интерфейсов `ISecurityManager` и `IUserSettingsService` в секции `unity` необходимо выполнить таким образом, как указано в примере ниже.

``` xml
<configuration>
   <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
      ...
      <container>
         ...
         <!-- Конфигурация системы полномочий. -->
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
         <!-- Конфигурация сервиса пользовательских настроек с поддержкой кэширования. -->
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
