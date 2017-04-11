---
title: CaseberryMembershipProvider 
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Flexberry Security
toc: true
permalink: ru/fa_membership-provider.html
lang: ru
---

## Проверка полномочий для веб-приложений

Для веб-приложений разработан специальный `MembershipProvider`, позволяющий проверять [полномочия](efs_right-manager-module.html) по БД полномочий без наличия AzManBridgeService.

## Подключение

Подключение выглядит следующим образом:

``` xml
<!-- ... -->
<connectionStrings>
    <add name="CaseberrySecurity" connectionString="Data Source=SQL2008R2;Initial Catalog=Test;Integrated Security=False;USER ID=editor;Password=123456;" providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService, Version=1.0.0.1, Culture=neutral, PublicKeyToken=49b42003269a4a66"/>
</connectionStrings>
<!-- ... -->

  <system.web>
        <membership defaultProvider="CaseberryMembershipProvider">
      <providers>
        <clear/>
        <add name="CaseberryMembershipProvider" type="ICSSoft.STORMNET.Security.CaseberryMembershipProvider" applicationName="SLAuthSample"/>
      </providers>
    </membership>

    <roleManager enabled="true" defaultProvider="CaseberryRoleProvider">
      <providers>
        <clear/>
        <add name="CaseberryRoleProvider" type="ICSSoft.STORMNET.Security.CaseberryRoleProvider" applicationName="SLAuthSample" />
      </providers>
    </roleManager>

    <profile enabled="true" defaultProvider="CaseberryProfileProvider" automaticSaveEnabled="false">
      <providers>
        <clear/>
        <add name="CaseberryProfileProvider" type="ICSSoft.STORMNET.Security.CaseberryProfileProvider" applicationName="SLAuthSample"/>
      </providers>
      <properties>
        <add name="FriendlyName" />
        <add name="AgentKey" />
      </properties>
    </profile>

<!-- ... -->
```

`CaseberryMembershipProvider ` располагается в сборке `CheckingLibrary.dll`
Чтобы полномочия проверялись, узлу требуется наличие следующих сборок:

* Security(Objects)
* Security(BusinessServers)
* Repository
* Microsoft.Practices.Unity
* LogService
* log4net
* IIS.Аудит(Objects)
* IIS.Аудит(BusinessServers)
* AppSettingsService
* CheckingLibrary
* ExternalLangDef
* ICSSoft.STORMNET.AzManBridge
* ICSSoft.STORMNET.Business
* ICSSoft.STORMNET.Business.MSSQLDataService
* ICSSoft.STORMNET.Business.OracleDataService
* ICSSoft.STORMNET.Collections
* ICSSoft.STORMNET.DataObject
* ICSSoft.STORMNET.FunctionalLanguage
* ICSSoft.STORMNET.RightManager
* ICSSoft.STORMNET.Tools
* ICSSoft.STORMNET.UserDataTypes
* ICSSoft.STORMNET.Web.Tools

### Особенности задания строки подключения

Работа со строкой подключения к БД полномочий происходит следующим образом:

* Если задана строка подключения с именем `CaseberrySecurity`, то в качестве строки подключения к БД полномочий используется она.
* Если строка подключения `CaseberrySecurity` не задана, то ищется строка соединения с именем  вида `CustomizationStrings_applicationName`; то есть если задано, что `applicationName="SLAuthSample"`, то имя строки соединения должно иметь вид `CustomizationStrings_SLAuthSample`.

## Формы редактирования полномочий в WEB

В Flexberry ASP.NET есть формы для редактирования полномочий, которые находятся в папке: /forms/Security
