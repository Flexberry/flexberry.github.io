---
title: Добавление пользователей в БД системы полномочий при windows-аутентификации
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Flexberry Security
toc: true
permalink: ru/fa_authentication-adapter.html
lang: ru
---

## AuthenticationAdapter

`AuthenticationAdapter` - класс, позволяющий осуществлять добавление пользователей в БД системы полномочий при windows-аутентификации.
Данный класс находится в `CheckingLibrary.dll` (версия сборки после 01.02.2013).

## Методы класса AuthenticationAdapter
Класс `AuthenticationAdapter` предоставляет следующие статические методы: 

1.

```csharp
/// <summary>
/// Получение объекта, соответствующего текущему пользователю в БД полномочий
/// (полное имя пользователя берётся как HttpContext.Current.User.Identity.Name)
/// </summary>
/// <returns>Объект или null, если ничего не было найдено</returns>
public static Agent GetDbUser()
```

2.

```csharp
/// <summary>
/// Получение объекта, соответствующего текущему пользователю в БД полномочий
/// </summary>
/// <param name="username">Полное имя пользователя</param>
/// <returns>Объект или null, если ничего не было найдено</returns>
public static Agent GetDbUser(string username)
```

3.

```csharp 
/// <summary>
/// Получение объекта, соответствующего текущему пользователю в БД полномочий.
/// </summary>
/// <param name="username">Полное имя пользователя.</param>
/// <param name="dataService">Сервис данных (<c>null</c>, если нужно использовать стандартный).</param>
/// <exception cref="InvalidOperationException">Выбрасывается в том случае, если в системе полномочий произошла ошибка.</exception>
/// <returns>Объект или <c>null</c>, если ничего не было найдено.</returns>
public static Agent GetDbUser(string username, IDataService dataService)
```

4.

```csharp
/// <summary>
/// Проверка существования в системе полномочий пользователя с указанным логином.
/// </summary>
/// <param name="login">Логин пользователя.</param>
/// <exception cref="InvalidOperationException">Выбрасывается в том случае, если в системе полномочий произошла ошибка.</exception>
/// <returns>Возвращает <c>true</c> если пользователь существует.</returns>
public static bool IsUserExist(string login)
```

5.

```csharp
/// <summary>
/// Создание пользователя в БД подсистемы полномочий
/// </summary>
/// <param name="username">Логин пользователя, возможно с доменом</param>
/// <param name="friendlyUserName">Имя пользователя</param>
/// <returns>Созданный пользователь</returns>
public static Agent CreateDbUser(string username, string friendlyUserName)
```

6.

```csharp
/// <summary>
/// Создание пользователя в БД подсистемы полномочий.
/// </summary>
/// <param name="username">Логин пользователя, возможно с доменом.</param>
/// <param name="friendlyUserName">Имя пользователя.</param>
/// <param name="addDefaultRoles">Следует ли добавлять роли по умолчанию для создаваемого пользователя.</param>
/// <param name="dataService">Сервис данных (<c>null</c>, если нужно использовать стандартный).</param>
/// <returns>Созданный пользователь.</returns>
public static Agent CreateDbUser(string username, string friendlyUserName, bool addDefaultRoles, IDataService dataService)
```

7.

```csharp
/// <summary>
/// Создание пользователя в БД подсистемы полномочий
/// (имя пользователя берётся из домена)
/// </summary>
/// <param name="username">Логин пользователя, возможно с доменом</param>
/// <returns>Созданный пользователь</returns>
/// <exception cref="Exception">Если пользователь не будет найден в домене, произойдёт исключительная ситуация</exception>
public static Agent CreateDbUser(string username)
```

{% include warning.html content="Данный метод стоит использовать, если есть уверенность, что в условиях, где развёрнуто приложение, настройки Active Directory позволят  корректно выполнить нижеприведённый код (если такой уверенности нет, лучше использовать перегрузку метода с двумя параметрами)" %}

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

## Особенности использования AuthenticationAdapter

1. При выполнении метода `CreateDbUser` в БД будут добавлены следующие объекты:

	* Пользователь с привязкой к домену и к ролям, заданным по умолчанию (если соответствующие роли будут найдены в БД полномочий).
	* Домен пользователя, если ранее он отсутствовал в системе полномочий.

2. Задание ролей по умолчанию происходит в конфиге приложения:

```xml
<configuration>
	<appSettings>
		<add key="DefaultRoles" value="Администраторы2, AnonimousUser"/>
		<!--...-->
	</appSettings>
	<!--...-->
</configuration>
```
## Пример использования AuthenticationAdapter

Использовать AuthenticationAdapter можно, например, при событии Page_Load в Site.Master:

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

## Создание пользователя с заполненным паролем

Если при создании пользователя нужно, чтобы его пароль не был равен NULL, то нужно взять исходный код метода 6 и подредактировать его, добавив задание значения пароля. Затем использовать полученный метод для создания пользователей. Например:

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
