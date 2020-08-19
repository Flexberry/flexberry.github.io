---
title: SecurityManager
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security, Ключевые понятия
summary: Основная реализация интерфейса модуля аутентификации и авторизации ISecurityManager - SecurityManager.
toc: true
permalink: ru/efs_security-manager.html
lang: ru
---

## Описание SecurityManager

`SecurityManager` используется в качестве класса для разграничения прав пользователей на серверной стороне. Данный класс реализует общий интерфейс `ISecurityManager`, используемый компонентами приложений Flexberry для обращения к API подсистемы полномочий на сервере. Если в приложении требуется отличная от предоставляемой SecurityManager реализация подсистемы полномочий, то следует реализовать интерфейс ISecurityManager и зарегистрировать эту реализацию в Unity-секции конфигурационного файла.

### API подсистемы полномочий на сервере

#### Проверка доступа текущего пользователя к указанному типу данных

#### Проверка доступа текущего пользователя к указанной операции



## Настройка приложения на использование SecurityManager

Настройка использования `SecurityManager` в секции `Unity` конфигурационного файла веб-приложения `web.config`.

``` xml
  <appSettings>
    <!-- … -->
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="DefaultConnectionStringName" value="DefConnStr" />

    <!-- Если БД полномочий лежит отдельно от основной, можно сослаться на другую строку соединения -->
    <add key="SecurityConnectionStringName" value="CaseberrySecurity" />
    <!-- … -->
  </appSettings>

  <!-- … -->
  <!-- Строки соединения вриложения объявляются в этой секции. Формат строк соединения всегда можно подсмотреть на специальном сайте <https://www.connectionstrings.com/>. -->
  <connectionStrings>
    <!-- … -->
    <add name="DefConnStr" connectionString="СТРОКА СОЕДИНЕНИЯ с БД ПРИЛОЖЕНИЯ" 
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService, Version=1.0.0.1, Culture=neutral, PublicKeyToken=49b42003269a4a66" />
    <add name="CaseberrySecurity"
      connectionString="СТРОКА СОЕДИНЕНИЯ С БД ПОЛНОМОЧИЙ"
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService, Version=1.0.0.1, Culture=neutral, PublicKeyToken=49b42003269a4a66"/>
    <!-- … -->
  </connectionStrings>

  <!-- … -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
      <!-- … -->
      <!-- Конфигурация сервиса кеширования. -->
      <register type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="defaultCacheForApplication" />
        </constructor>
      </register>

      <!-- securityManagerWithoutRightsCheck - менеджер полномочий с выключенной проверкой полномочий. -->
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
         mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject">
        <lifetime type="singleton" />
        <constructor />
      </register>

      <!-- Сервис данных, через который будет идти запрос к полномочиям. Здесь дублируется тип сервиса данных, а также указывается строка соединения с БД полномочий. Использовать данный сервис данных где-либо ещё крайне нежелательно. -->
      <register name="dataServiceForSecurityManager" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
        mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" >
        <constructor>
          <!-- Менеджер полномочий, который используется сервисом данных для проверки полномочий. Чтобы сервис данных имел возможность обращаться к объектам системы полномочий в БД для пользователей которые ещё не прошли аутентификацию, он будет использовать securityManagerWithoutRightsCheck - это менеджер полномочий с выключенной проверкой полномочий. -->
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <!-- Имя строки соединения с БД полномочий. -->
        <property name="CustomizationStringName" dependencyType="System.String" value="CaseberrySecurity" />
      </register>

      <!-- Сервис кэширования, который будет использоваться для временного хранения настроек, вычитанных из базы данных для менеджера полномочий. По имени данного кэша его можно полностью очистить в случае необходимости. -->
      <register name="cacheServiceForSecurityManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching"
        mapTo=" NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching" >
        <lifetime type="singleton" />
        <constructor>
          <!-- Имя кэша, по которому можно будет осуществить его очистку. -->
          <param name="cacheName" type="System.String" value="cacheForSecurityManager" />
        </constructor>
      </register>
      
      <!-- Сервис кэширования, который будет использоваться для временного хранения настроек, вычитанных из базы данных для менеджера агентов. По имени данного кэша его можно полностью очистить в случае необходимости. -->
      <register name="cacheServiceForAgentManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForAgentManager" />
        </constructor>
      </register>
      
      <!-- Менеджер полномочий, который будет использоваться сервисом данных DataServiceProvider.DataService. -->
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
        mapTo="NewPlatform.Flexberry.Security.SecurityManager, NewPlatform.Flexberry.Security">
        <constructor>
          <!-- dataServiceForSecurityManager - сервис данных, через который будет идти запрос к полномочиям. -->
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForSecurityManager" />
          </param>
          <!-- Проверка полномочий включена. -->
          <param name="enabled" type="System.Boolean" value="true"/>
          <!-- Проверка полномочий на объекты включена. -->
          <param name="useRightsOnObjects" type="System.Boolean" value="true"/>
          <!-- Проверка полномочий на атрибуты включена. -->
          <param name="useRightsOnAttribute" type="System.Boolean" value="true"/>
        </constructor>
      </register>

      <!-- Менеджер агентов. -->
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

      <!-- Хешер паролей для приложения, который будет использоваться в частности для хеширования паролей агентов полномочий. Можно в конструкторе хешеру указать соль через строковый параметр salt.-->
      <register type="NewPlatform.Flexberry.Security.IPasswordHasher, NewPlatform.Flexberry.Security" mapTo="NewPlatform.Flexberry.Security.Sha1PasswordHasher, NewPlatform.Flexberry.Security">
        <lifetime type="singleton" />
        <constructor />
      </register>

      <!-- Сервис, позволяющий разрешать имя строки соединения, переданное через параметр CustomizationStringName.-->
      <register type="ICSSoft.STORMNET.Business.IConfigResolver, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.ConfigResolver, ICSSoft.STORMNET.Business">
        <lifetime type="singleton" />
        <constructor />
      </register>
    <!-- … -->
    </container>
  </unity>
  <!-- … -->
  <system.web>
    <!-- … -->
    <!-- Провайдер, который используется для выполнения аутентификации пользователей. -->
    <membership defaultProvider="FlexberryMembershipProvider">
      <providers>
        <clear/>
        <add name="FlexberryMembershipProvider" type="NewPlatform.Flexberry.Security.FlexberryMembershipProvider"/>
      </providers>
    </membership>
    <!-- Провайдер, который позволяет определять роли, которые есть у пользователей (используется, например, в sitemap roles). -->
    <roleManager defaultProvider="FlexberryRoleProvider" enabled="true">
      <providers>
        <clear/>
        <add name="FlexberryRoleProvider" type="NewPlatform.Flexberry.Security.FlexberryRoleProvider"/>
      </providers>
    </roleManager>
    <!-- Провайдер профилей пользоватля. -->
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
    <!-- … -->
  </system.web>
  <!-- … -->
```
