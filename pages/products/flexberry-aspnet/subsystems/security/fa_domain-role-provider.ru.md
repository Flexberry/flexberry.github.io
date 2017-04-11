---
title: CaseberryDomainRoleProvider
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_domain-role-provider.html
lang: ru
---

`CaseberryDomainRoleProvider` - это наследник [System.Web.Security.RoleProvider](http://msdn.microsoft.com/ru-ru/library/system.web.security.roleprovider.aspx). Находится в `CheckingLibrary.dll` и осуществляет проверку ролей пользователя с учётом его домена.

## Подключение

Подключение `CaseberryDomainRoleProvider` осуществляется аналогично тому, как описано [в статье CaseberryMembershipProvider - полномочия](fa_membership-provider.html).

```xml
<membership defaultProvider="CaseberryMembershipProvider">
  <providers>
	<clear/>
	<add name="CaseberryMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider" applicationName="SLAuthSample" />
  </providers>
</membership>

<roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
  <providers>
	<clear/>
	<add name="CaseberryRoleProvider" type="CheckingLibrary.CaseberryDomainRoleProvider"/>
  </providers>
</roleManager>
```

## Пример использования

Пусть через [консоль управления полномочиями](efs_security-console.html) создана группа `SOME_HOME`, в неё добавлен пользователь с логином `VPupkin`, которому определена роль "Администратор". 

Если [в качестве MembershipProvider] используется класс `CaseberryDomainMembershipProvider`, то вход в web-приложение будет осуществляться по логину с доменом `SOME_HOME\VPupkin`. Если в этом случае использовать `CaseberryRoleProvider`, то назначенные роли будут определяться для пользователя с логином `SOME_HOME\VPupkin`, а если прописать класс `CaseberryDomainRoleProvider` в качестве RoleProvider - для пользователя `VPupkin`, состоящего в группе `SOME_HOME`.
