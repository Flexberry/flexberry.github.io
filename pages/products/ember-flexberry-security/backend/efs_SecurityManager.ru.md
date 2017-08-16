---
title: SecurityManager
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security, Ключевые понятия
toc: true
permalink: ru/efs_security-manager.html
lang: ru
summary: Основная реализация интерфейса модуля аутентификации и авторизации ISecurityManager - SecurityManager.
---

## Описание SecurityManager

`SecurityManager` используется в качестве класса для разграничения прав пользователей на серверной стороне. Данный класс реализует общий интерфейс `ISecurityManager`, используемый компонентами приложений Flexberry для обращения к API подсистемы полномочий на сервере. Если в приложении требуется отличная от предоставляемой SecurityManager реализация подсистемы полномочий, то следует реализовать интерфейс ISecurityManager и зарегистрировать эту реализацию в Unity-секции конфигурационного файла.

### API подсистемы полномочий на сервере

#### Проверка доступа текущего пользователя к указанному типу данных

#### Проверка доступа текущего пользователя к указанной операции



## Настройка приложения на использование SecurityManager

Настройка использования SecurityManager в секции Unity конфигурационного файла веб-приложения.

``` xml
  <appSettings>
    <!-- … -->
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="DefaultConnectionStringName" value="DefConnStr" />

    <!-- Если БД полномочий лежит отдельно от основной, можно сослаться на другую строку соединения -->
    <add key="SecurityConnectionStringName" value="DefConnStr" />
    <!-- … -->
  </appSettings>

    <!-- … -->
  <connectionStrings>
    <!-- … -->
    <add name="DefConnStr" connectionString="СТРОКА СОЕДИНЕНИЯ с БД ПРИЛОЖЕНИЯ" 
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <!-- … -->
  </connectionStrings>

  <!-- … -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <!-- … -->
    <container>
      <register name="dataServiceForSecurityManager" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" 
        mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" >
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationString" dependencyType="System.String" value="ТУТ ДОЛЖНА НАХОДИТЬСЯ СТРОЕКА СОЕДИНЕНИЯ С БД ПОЛНОМОЧИЙ"/>
      </register>
      <register name="cacheServiceForSecurityManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" 
        mapTo=" NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching" >
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForSecurity" />
        </constructor>
      </register>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="NewPlatform.Flexberry.Security.SecurityManager, NewPlatform.Flexberry.Security">
        <constructor>
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForSecurityManager" />
          </param>
          <param name="enabled" type="System.Boolean" value="true" />
          <param name="useRightsOnObjects" type="System.Boolean" value="true или false" />
          <param name="useRightsOnAttribute" type="System.Boolean" value="false" />
        </constructor>
      </register>
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
         mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject">
        <lifetime type="singleton" />
      </register>
    <!-- … -->
    </container>
  </unity>
```