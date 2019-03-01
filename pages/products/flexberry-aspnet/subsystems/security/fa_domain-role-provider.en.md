--- 
title: CaseberryDomainRoleProvider 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_domain-role-provider.html 
lang: en 
autotranslated: true 
hash: 9ebb89c533db53f307f0a9ef0e81e1f6de09de7a3abc80f7c163372a81b11069 
--- 

`CaseberryDomainRoleProvider` is the heir to the [System.Web.Security.RoleProvider](http://msdn.microsoft.com/ru-ru/library/system.web.security.roleprovider.aspx). Is `CheckingLibrary.dll` and checks the user roles with regard to its domain. 

## Connection 

Connection `CaseberryDomainRoleProvider` is carried out similarly to that described [in the article CaseberryMembershipProvider - powers](fa_membership-provider.html). 

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

## Example usage 

Let through [management authority](efs_security-console.html) `SOME_HOME` created a group, added a user with login `VPupkin`, which defines the role of "Administrator". 

If [as MembershipProvider] used class `CaseberryDomainMembershipProvider`, the web application will be login with the domain `SOME_HOME\VPupkin`. If you do this, use `CaseberryRoleProvider`, the assigned roles will be determined for the user with login `SOME_HOME\VPupkin`, and if you register a class `CaseberryDomainRoleProvider` as RoleProvider for user `VPupkin` playgroup `SOME_HOME`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/