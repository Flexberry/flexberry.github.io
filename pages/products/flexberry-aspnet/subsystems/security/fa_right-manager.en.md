---
title: the Subsystem authorizations for Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET Flexberry Security
toc: true
permalink: en/fa_right-manager.html
lang: en
autotranslated: true
hash: e9e29d4afc3265fc82afb365362136e3824e6dd64a6ea92474694fade63db2a9
---

At the moment [subsystem powers](efs_right-manager-module.html) Web application does not require third-party services and comes along with the application in the generation of [Flexberry Designer](fd_flexberry-designer.html).

The database can be located in the application or in a separate database.

In the configuration file for configuration meets the following unit (Forms authentication):

```xml  
<!-- Authority system -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <alias alias="ISecurityService" type="ICSSoft.STORMNET.Security.ISecurityService, ICSSoft.STORMNET.DataObject" />
    <alias alias="CheckingInSessionMode" type="ICSSoft.STORMNET.Security.CheckingInSessionMode, CheckingLibrary" />
    <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
    <container>
      <!-- System configuration authority. -->
      <register type="ISecurityService" mapTo="CheckingInSessionMode">
        <lifetime type="singleton" />
        <constructor>
          <!-- Here it is necessary to register the name of the application -->
          <param name="imprisone" type="System.String" value="WebApp" />
        </constructor>
      </register>
    </container>
  </unity>

  <authentication mode=Forms >
    <forms name=".ASPXFORMSAUTH" loginUrl="LoginForm.aspx" timeout="30" slidingExpiration="true" />
  </authentication>

  <authorization>
    <deny users="?" />
  </authorization>

  <membership defaultProvider="CaseberryMembershipProvider">
    <providers>
      <clear/>
      <add name="CaseberryMembershipProvider" type="ICSSoft.STORMNET.Security.CaseberryMembershipProvider" applicationName="SLAuthSample"/>
    </providers>
  </membership>

  <roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
    <providers>
      <clear/>
      <add name="CaseberryRoleProvider" type="ICSSoft.STORMNET.Security.CaseberryRoleProvider" />
    </providers>
  </roleManager>
 ```

As well as the variant with Windows authentication:

```xml  
<authentication mode="Windows" />

  <authorization>
    <deny users="?" />
  </authorization>

  <membership defaultProvider="CaseberryDomainMembershipProvider">
    <providers>
      <clear/>
      <add name="CaseberryDomainMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider, CheckingLibrary" applicationName="SLAuthSample"/>
    </providers>
  </membership>

  <roleManager defaultProvider="CaseberryDomainRoleProvider" enabled="true">
    <providers>
      <clear/>
      <add name="CaseberryDomainRoleProvider" type="CheckingLibrary.CaseberryDomainRoleProvider" applicationName="SLAuthSample" />
    </providers>
  </roleManager>
```

To use AzMan substitute providers.

Powers can also be configured using the [Security Console](efs_security-console.html).

## Add users to the database system of authority in windows authentication

Add users to the database system of authority when windows-authentication is described in the appropriate [article](fa_authentication-adapter.html).

## The creation of powers for classes for Flexberry ASP.NET

The creation of powers for classes for Flexberry ASP.NET described in the appropriate [article](fa_authority-classes.html).

## CaseberryDomainRoleProvider

Use `CaseberryDomainRoleProvider` described in the appropriate [article](fa_domain-role-provider.html).

## CaseberryMembershipProvider

Use `CaseberryMembershipProvider` described in the appropriate [article](fa_membership-provider.html).

## Web forms subsystem powers

The subsystem authorizations for the web-form described in [the Web forms subsystem powers](fa_security-forms.html).

## UserSettingsService

Use `UserSettingsService` described in the appropriate [article](fa_user-settings-service.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}