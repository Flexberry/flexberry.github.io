--- 
title: Service current user 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, current user, example 
summary: Methods of work with the current user 
toc: true 
permalink: en/fo_current-user-service.html 
lang: en 
autotranslated: true 
hash: 308af940aa6ecf24e983f3712100317efb7363e1289da3c45116a8eab6af9195 
--- 

`Flexberry CurrentUserService` is [product platform Flexberry](fp_landing_page.html) and allows to obtain the current user, and change as individual properties, and the way to work with it. 

{% include note.html content="CurrentUserService Flexberry included in the NuGet package Flexberry ORM." %} 

To get the current user using `ICSSoft.Services.CurrentUserService.CurrentUser`. An attribute has an interface `ICSSoft.Services.CurrentUserService IUser`: 

```csharp
public interface IUser
{
	/// <summary> 
	/// The user name ("VASYA Pupkin"). 
	/// </summary> 
	string FriendlyName { get; set; }

	/// <summary> 
	/// User domain. 
	/// </summary> 
	string Domain { get; set; }

	/// <summary> 
	/// User login ("vpupkin"). 
	/// </summary> 
	string Login { get; set; }
}
``` 

By default, the username and domain of the user taken from environment variables (for win) and from the current HTTP context (for web applications). 

The user name if not explicitly set, calculated via the login. 

## rewriting service the current user 

If you want to change the way of working with the current user (e.g., store the user ID in the cache, and a web application), we need to implement the interface `ICSSoft.Services.CurrentUserService IUser` and assign it the handler. For mapping you can use the method `ICSSoft.Services.CurrentUserService.ResolveUser<T>()` and settings in the configuration file (use [UnityFactory](fo_unity-factory.html)): 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <configSections>
    <section name="unity"
        type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection,
              Microsoft.Practices.Unity.Configuration" />
  </configSections>

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
	  <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
	  <container>
		<register type="ICSSoft.Services.CurrentUserService IUser, ICSSoft.Services.CurrentUserService" mapTo="<Full type name>, <Assembly Name>">
		  <lifetime type="singleton" />
		</register>
	  </container>
  </unity>
</configuration>
``` 

In addition, for overriding the type necessary to fill in the "<Full type name>, <Assembly Name>". 

### Example rewriting service the current user 

Let the name of the current user stored in the static field `IIS.TryAccessSystem.Polzovatelyami.currentUserLogin`, then rewriting service the current user is the following: 

``` csharp
namespace IIS.TryAccessSystem
{
    /// <summary> 
    /// Override the current user service 
    /// </summary> 
    public class ClassOtherUser : ICSSoft.Services.CurrentUserService.IUser
    {
        /// <summary> 
        /// The user name ("VASYA Pupkin") 
        /// </summary> 
        public string FriendlyName
        {
            get { return IIS.TryAccessSystem.ПользовательПриложения.currentUserLogin; }
            set {}
        }

        /// <summary> 
        /// Domain user 
        /// </summary> 
        public string Domain {get; set;}

        /// <summary> 
        /// User login ("vpupkin") 
        /// </summary> 
        public string Login
        {
            get { return IIS.TryAccessSystem.ПользовательПриложения.currentUserLogin; }
            set {}
        }
    }
}
``` 

If the class `IIS.TryAccessSystem.ClassOtherUser` contains an override of the service and located in the Assembly `TryAccessSystem(Forms)`, to override the service of the current user in the application configuration file, it is sufficient to add: 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <configSections>
    <section name="unity"
        type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection,
              Microsoft.Practices.Unity.Configuration" />
  </configSections>

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
	  <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
	  <container>
		<register type="ICSSoft.Services.CurrentUserService IUser, ICSSoft.Services.CurrentUserService" mapTo="IIS.TryAccessSystem.ClassOtherUser, TryAccessSystem(Forms)">
		  <lifetime type="singleton" />
		</register>
	  </container>
  </unity>
</configuration>
``` 

## Possible errors 

`CurrentUserService` may generate an error and report that it can not find the Assembly `Microsoft.Practices.Unity`. 

This is due to the fact that this build is really not in the folder with the binaries of the project, as it is not copied there when building the project. However, it is often that it is in the global folder with the assemblies of the operating system. 

To correct the situation you must have a reference to this Assembly (Reference) in the project to put the property `CopyLocal` = `true`.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}