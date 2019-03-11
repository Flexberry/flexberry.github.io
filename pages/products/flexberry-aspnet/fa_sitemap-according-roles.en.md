--- 
title: setup site map by role 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_sitemap-according-roles.html 
lang: en 
autotranslated: true 
hash: 3d2868cb3cee2aee9ff6fda728ecda8609041b44191f588587bbcf7129b9f2eb 
--- 

Example: a web application `Гостиница`, to be accessed by users with two possible roles `Приемщик` and `admin`. 

If the current user has a role `admin`, the site map should look like the following: 

![](/images/pages/products/flexberry-aspnet/admin-menu.png) 

If the current user has a role `Приемщик`, the site map should look like the following: 

![](/images/pages/products/flexberry-aspnet/priem-menu.png) 

You want to restrict access to the relevant application forms. To do this, specify the user role that should have access elementz site map, attribute `roles`. Have the following site map (contents of the file `web.sitemap`): 

```xml
<siteMapNode title="Hotel" roles="Inspector,admin" xmlns="">
      <siteMapNode title="Booking" description="" url="~/forms/Bronirovanie/G_BronirovanieL.aspx" roles="admin"/> 
      <siteMapNode title="License plate Fund" description="" url="~/forms/KomnataGostinicy/G_KomnataGostinicyL.aspx" roles="Inspector"/> 
      <siteMapNode title="Kind of animal" description="" url="~/forms/ZHivotnoe/G_ZHivotnoeL.aspx" roles="Inspector"/>
</sitemapNode> 
``` 

You need to pay attention to the fact that the node maps the top-level site (`Гостиница`) lists all the user roles that can have access to this menu (and `Приемщик`, and `admin`). 

To ensure correct display of sub-menu items depending on user role, need to folders of the web application that match the specified in `url` forms, add files `web.config`. 

Folder `forms/Bronirovanie` add `web.config` follows: 

```xml
<configuration>
    <system.web>
      <authorization>
        <allow roles="admin"/>
        <deny users="*"/>
      </authorization>  
    </system.web>
</configuration>
``` 

Folder `forms/KomnataGostinicy` and `forms/ZHivotnoe` add `web.config` follows: 

```xml
<configuration>
    <system.web>
      <authorization>
        <allow roles="Inspector"/>
        <deny users="*"/>
      </authorization>
    </system.web>
</configuration>
``` 

In the result sub-menu items will appear depending on what role the current user of the web application. 

If __windows-authentication__ users are processed together with the domain. Therefore, you must use providers that take into account domain. To do this in the application's configuration file must specify the following: 

```xml
<membership defaultProvider="CaseberryMembershipProvider">
  <providers>
    <clear />
    <add name="CaseberryMembershipProvider" type="CheckingLibrary.Web.CaseberryDomainMembershipProvider" applicationName="SLAuthSample" />
  </providers>
</membership>
<roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
  <providers>
    <clear />
    <add name="CaseberryRoleProvider" type="CheckingLibrary.CaseberryDomainRoleProvider" />
  </providers>
</roleManager>
``` 

In addition there is a __to pay attention to the feature of processing the sitemap file__ with windows authentication. Namely, the processing `sitemapNode`, which is `url` and `roles`. 

```xml
<siteMapNode title=Roles url="~/flexberry/DvorecList" roles="tsar" />
``` 

If you use the __standard sitemap provider__ the vertex is always visible, as in the presence of the attribute `url` checks availability of url node for the current user. 

To solve this problem in the following ways: 

1. To use a custom sitemap provider. 
2. Add an intermediate vertex without a url attribute. 
3. To configure the settings of file access. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}