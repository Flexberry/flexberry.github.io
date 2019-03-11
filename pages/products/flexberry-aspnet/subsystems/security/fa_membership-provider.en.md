--- 
title: CaseberryMembershipProvider 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Security 
toc: true 
permalink: en/fa_membership-provider.html 
lang: en 
autotranslated: true 
hash: fa61a7df84814149abd6869726115f1fd4c77aefdecc9ee266fd6ba554220107 
--- 

## the credentials for web applications 

For web applications developed a special `MembershipProvider` which you can check the [powers](efs_right-manager-module.html) in the database of office without the presence of AzManBridgeService. 

## Connection 

Connect as follows: 

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

`CaseberryMembershipProvider ` is in the Assembly `CheckingLibrary.dll` 
To the authority were checked, the node requires the following assemblies: 

* Security(Objects) 
* Security(BusinessServers) 
* Repository 
* Microsoft.Practices.Unity 
* LogService 
* log4net 
* IIS.Audit(Objects) 
* IIS.Audit(BusinessServers) 
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

### features of setting the connection string 

Work with the connection string to the database of the authority is as follows: 

* If the connection string is set with the name `CaseberrySecurity`, the connection string to the database authority is used. 
* If the connection string `CaseberrySecurity` is not specified, it looks for a connection string named `CustomizationStrings_applicationname`; that is, if you specified that `applicationName="SLAuthSample"`, the name of the connection string must be in the form `CustomizationStrings_SLAuthsample`. 

## edit Form of authority in WEB 

In Flexberry ASP.NET there are forms for editing permissions which are in the folder: /forms/Security 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}