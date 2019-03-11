--- 
title: Add users to the database system of authority in windows authentication 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Flexberry Security 
toc: true 
permalink: en/fa_authentication-adapter.html 
lang: en 
autotranslated: true 
hash: 48ff3657aea82d9547356a0d3f97f03eedd6ce83298b950d2aa40f76b9e620d1 
--- 

## AuthenticationAdapter 

`AuthenticationAdapter` class that allows adding users in the database system powers up with windows authentication. 
This class is `CheckingLibrary.dll` (build version after 01.02.2013). 

## class Methods AuthenticationAdapter 
PstrfAuthenticationAdapter` class provides the following static methods: 

1. 

```csharp
/// <summary> 
/// Get the object corresponding to the current user in the database of the authority 
/// (the user's full name is taken as HttpContext.Current.User.Identity.Name) 
/// </summary> 
/// <returns>the Object or null if none was found</returns> 
public static Agent GetDbUser()
``` 

2. 

```csharp
/// <summary> 
/// Get the object corresponding to the current user in the database of the authority 
/// </summary> 
/// <param name="username">user Full name</param> 
/// <returns>the Object or null if none was found</returns> 
public static Agent GetDbUser(string username)
``` 

3. 

```csharp 
/// <summary> 
/// Get the object corresponding to the current user in the database credentials. 
/// </summary> 
/// <param name="username">the user's Full name.</param> 
/// <param name="dataService">data Service (<c>null</c> if you want to use the default).</param> 
/// <exception cref="InvalidOperationException">is Thrown in the case if the authority system error occurred.</exception> 
/// <returns>the Object or <c>null</c> if none was found.</returns> 
public static Agent GetDbUser(string username, IDataService dataService)
``` 

4. 

```csharp
/// <summary> 
/// Check existence in the system credentials of the user with the specified login. 
/// </summary> 
/// <param name="login">user Login.</param> 
/// <exception cref="InvalidOperationException">is Thrown in the case if the authority system error occurred.</exception> 
/// <returns>Returns <c>true</c> if the user exists.</returns> 
public static bool IsUserExist(string login)
``` 

5. 

```csharp
/// <summary> 
/// Create user in the database subsystem powers 
/// </summary> 
/// <param name="username">the username of the user, possibly with domain name</param> 
/// <param name="friendlyUserName">user Name</param> 
/// <returns>Created user</returns> 
public static Agent CreateDbUser(string username, string friendlyUserName)
``` 

6. 

```csharp
/// <summary> 
/// Create user in the database subsystem powers. 
/// </summary> 
/// <param name="username">the username of the user, perhaps with the domain.</param> 
/// <param name="friendlyUserName">user Name.</param> 
/// <param name="addDefaultRoles">whether to add the default roles for the created user.</param> 
/// <param name="dataService">data Service (<c>null</c> if you want to use the default).</param> 
/// <returns>the Created user.</returns> 
public static Agent CreateDbUser(string username, string friendlyUserName, bool addDefaultRoles, IDataService dataService)
``` 

7. 

```csharp
/// <summary> 
/// Create user in the database subsystem powers 
/// (the user name is taken from the domain) 
/// </summary> 
/// <param name="username">the username of the user, possibly with domain name</param> 
/// <returns>Created user</returns> 
/// <exception cref="Exception">If the user is not found in the domain, will happen exception</exception> 
public static Agent CreateDbUser(string username)
``` 

{% include warning.html content="This method should be used if there is confidence that in circumstances where an application is deployed, configure the Active Directory will properly execute the code below (if you are not sure, better to use an overload with two parameters)" %} 

```csharp
using (var context = new PrincipalContext(ContextType.Domain))
{
	using (UserPrincipal userPrincipal = UserPrincipal.FindByIdentity(context, username))
	{
		if (userPrincipal != null)
		{
			return CreateDbUser(username, userPrincipal.DisplayName);
		}
	}
}
``` 

## Features use AuthenticationAdapter 

1. The method `CreateDbUser` in the database are added the following objects: 

* The user bound to the domain and to roles, the default (if the corresponding roles are found in the database of the authority). 
* User domain, if it is previously absent in the system of authority. 

2. The job role is the default config of the application: 

```xml
<configuration>
	<appSettings>
		<add key="DefaultRoles" value="Администраторы2, AnonimousUser"/>
		<!--...-->
	</appSettings>
	<!--...-->
</configuration>
``` 
## Example of using AuthenticationAdapter 

Use AuthenticationAdapter you can, for example, in the Page_Load event in the Site.Master: 

```csharp
protected void Page_Load(object sender, EventArgs e)
{
	//... 
	ApplyTreeViewCookie();
	//... 
	if (AuthenticationAdapter.GetDbUser(Context.User.Identity.Name) == null)
		AuthenticationAdapter.CreateDbUser(Context.User.Identity.Name);
	//... 
	fio.Text = Context.User.Identity.Name;
	//... 
}
``` 

## create a user with a filled-in password 

If you create a user need to have his password was not equal to NULL, then you need to take the source code of the method 6 and edit it, adding the set value of the password. Then use this method to create users. For example: 

```csharp
public static Agent CreateDbUser(string username, string friendlyUserName, bool addDefaultRoles, IDataService dataService)
{
    string login;
    string domain = DomainHelper.GetDomainFromFullName(username, out login);
 
    var agent = new Agent
    {
        Login = login,
        Pwd = "5D70C3D101EFD9CC0A69F4DF2DDF33B21E641F6A",
        IsUser = true,
        Name = friendlyUserName
    };
 
    var meth = typeof(AuthenticationAdapter).GetMethod(
        "CreateDbAgentWithLinks",
        BindingFlags.Static | BindingFlags.NonPublic);
    meth.Invoke(null, new object[] { agent, domain, addDefaultRoles, dataService });
 
    return agent;
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}