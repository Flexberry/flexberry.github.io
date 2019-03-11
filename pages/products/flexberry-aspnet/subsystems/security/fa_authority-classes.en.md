--- 
title: Creation of powers for classes 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Security 
toc: true 
permalink: en/fa_authority-classes.html 
lang: en 
autotranslated: true 
hash: edb4ae5795f3f08303a7880578bd33b8df0eb30583b4e1e5bf7cb679be18767a 
--- 

It is sometimes necessary to restrict access to some users or users with a certain role to certain classes (editing, creating new objects/delete/view the list). To do this, you must configure the [powers](efs_security.html) for users/user roles. 

To create a role that will make some adjustments (for example, disabled the ability to remove objects from a list for a particular class), in two ways: 
* in the section "Administration\Roles" in приложении; 
* [Security Console](efs_security-console.html). 

The list of users is generated based on the database credentials. 

Credentials are configured in the section "Classes" on the form edit role or user. For example, there are 4 classes. One cannot be read, the second for editing, the third is to create a new object, a fourth to remove. The authorization setting is as follows: 

![](/images/pages/products/flexberry-aspnet/aspnet/authority-to-classes.png) 

## the Creation of powers for classes in authentication forms-based 

To configure the credentials you should add the user(s) in the section "Administration\Users" in the app (if users are limited and the list is not built based on the database of the authority). Execute one of the following: 

* assign the user role with limited полномочиями; 
* restrict permissions on the edit form of the user. 

## the Creation of powers for classes with windows authentication 

In order to limit the powers of the users in web application with windows-autentifikatsii follows: 

* create role [default](fa_authentication-adapter.html): 

```xml
<configuration>
	<appSettings>
		<add key="DefaultRoles" value=Tester/>
		<!--...-->
	</appSettings>
	<!--...-->
</configuration>
``` 

* configure it with полномочия; 
* [add users to the database system of powers](fa_authentication-adapter.html): 

```csharp
protected void Page_Load(object sender, EventArgs e)
{
	//... 
	ApplyTreeViewCookie();
	// Executing the method CreateDbUser in the database are added the following objects: 
        // 1. The user is bound to the domain and role assigned by default. 
        // 2. Domain user, if he previously absent in the system of authority. 
        if (AuthenticationAdapter.GetDbUser(Context.User.Identity.Name) == null)
        {
            AuthenticationAdapter.CreateDbUser(Context.User.Identity.Name);
        }
	//... 
	fio.Text = Context.User.Identity.Name;
	//... 
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}