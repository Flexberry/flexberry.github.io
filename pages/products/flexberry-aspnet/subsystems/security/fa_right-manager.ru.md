---
title: Подсистема полномочий для Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Flexberry Security
toc: true
permalink: ru/fa_right-manager.html
lang: ru
---

На данный момент [подсистема полномочий](efs_right-manager-module.html) Web-приложений не требует сторонних сервисов и поставляется вместе с приложением при генерации из [Flexberry Designer](fd_flexberry-designer.html).

База данных может располагаться как в базе приложения, так и в отдельной базе.

В файле конфигурации за настройки отвечает следующий блок (Forms-аутентификация):

```xml  
<!-- Система полномочий -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <alias alias="ISecurityService" type="ICSSoft.STORMNET.Security.ISecurityService, ICSSoft.STORMNET.DataObject" />
    <alias alias="CheckingInSessionMode" type="ICSSoft.STORMNET.Security.CheckingInSessionMode, CheckingLibrary" />
    <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
    <container>
      <!-- Конфигурация системы полномочий. -->
      <register type="ISecurityService" mapTo="CheckingInSessionMode">
        <lifetime type="singleton" />
        <constructor>
          <!-- Тут надо прописать имя приложения -->
          <param name="sИмяПриложения" type="System.String" value="WebApp" />
        </constructor>
      </register>
    </container>
  </unity>

  <authentication mode="Forms" >
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

А также возможен вариант с Windows-аутентификацией:

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

Чтобы использовать AzMan необходимо подменить провайдеры.

Полномочия можно также настраивать с помощью [Security Console](efs_security-console.html).

## Добавление пользователей в БД системы полномочий при windows-аутентификации

Добавление пользователей в БД системы полномочий при windows-аутентификации описано в соотвествующей [статье](fa_authentication-adapter.html).

## Создание полномочий на классы для Flexberry ASP.NET

Создание полномочий на классы для Flexberry ASP.NET описано в соотвествующей [статье](fa_authority-classes.html).

## CaseberryDomainRoleProvider

Использование `CaseberryDomainRoleProvider` описано в соотвествующей [статье](fa_domain-role-provider.html).

## CaseberryMembershipProvider

Использование `CaseberryMembershipProvider` описано в соотвествующей [статье](fa_membership-provider.html).

## Web-формы подсистемы полномочий

Использование подсистемы полномочий для web-форм описано в [статье Web-формы подсистемы полномочий](fa_security-forms.html).

## UserSettingsService

Использование `UserSettingsService` описано в соотвествующей [статье](fa_user-settings-service.html).
