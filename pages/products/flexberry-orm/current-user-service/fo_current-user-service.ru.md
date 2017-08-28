---
title: Сервис текущего пользователя
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, текущий пользователь, пример
summary: Методы работы с текущим пользователем
toc: true
permalink: ru/fo_current-user-service.html
lang: ru
---

`Flexberry CurrentUserService`  является [продуктом платформы Flexberry](fp_landing_page.html) и позволяет получать текущего пользователя приложения, а также менять как отдельные свойства пользователя, так и сам способ работы с ним.

{% include note.html content="Flexberry CurrentUserService включен в NuGet-пакет Flexberry ORM." %}

Получить текущего пользователя приложения можно используя `ICSSoft.Services.CurrentUserService.CurrentUser`. Атрибут имеет интерфейс `ICSSoft.Services.CurrentUserService+IUser`:

```csharp
public interface IUser
{
	/// <summary>
	/// Имя пользователя ("VASYA Pupkin").
	/// </summary>
	string FriendlyName { get; set; }

	/// <summary>
	/// Домен пользователя.
	/// </summary>
	string Domain { get; set; }

	/// <summary>
	/// Логин пользователя ("vpupkin").
	/// </summary>
	string Login { get; set; }
}
```

По умолчанию логин и домен пользователя берутся из переменных окружения (для win-приложений) и из текущего HTTP-контекста (для веб-приложений).

Имя пользователя, если не установлено явно, вычисляется через логин.

## Переопределение сервиса текущего пользователя

Если необходимо изменить способ работы с текущим пользователем (например, хранить логин пользователя в кэше веб приложения), нужно реализовать интерфейс `ICSSoft.Services.CurrentUserService+IUser` и сопоставить ему получившийся обработчик. Для сопоставления можно использовать метод `ICSSoft.Services.CurrentUserService.ResolveUser<T>()` и настройки в конфигурационном файле (используется [UnityFactory](fo_unity-factory.html)):

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
		<register type="ICSSoft.Services.CurrentUserService+IUser, ICSSoft.Services.CurrentUserService" mapTo="<Полное имя типа>, <Имя сборки>">
		  <lifetime type="singleton" />
		</register>
	  </container>
  </unity>
</configuration>
```

При этом для переопределяющего типа необходимо заполнить "<Полное имя типа>, <Имя сборки>".

### Пример переопределения сервиса текущего пользователя

Пусть имя текущего пользователя хранится в статическом поле `IIS.TryAccessSystem.ПользовательПриложения.currentUserLogin`, тогда переопределение сервиса текущего пользователя будет следующим:

``` csharp
namespace IIS.TryAccessSystem
{
    /// <summary>
    /// Переопределяем сервис текущего пользователя
    /// </summary>
    public class ClassOtherUser : ICSSoft.Services.CurrentUserService.IUser
    {
        /// <summary>
        /// Имя пользователя ("VASYA Pupkin")
        /// </summary>
        public string FriendlyName
        {
            get { return IIS.TryAccessSystem.ПользовательПриложения.currentUserLogin; }
            set {}
        }

        /// <summary>
        /// Домен пользователя
        /// </summary>
        public string Domain {get; set;}

        /// <summary>
        /// Логин пользователя ("vpupkin")
        /// </summary>
        public string Login
        {
            get { return IIS.TryAccessSystem.ПользовательПриложения.currentUserLogin; }
            set {}
        }
    }
}
```

Если класс `IIS.TryAccessSystem.ClassOtherUser` содержит переопределение сервиса и расположен в сборке `TryAccessSystem(Forms)`, то для переопределения сервиса текущего пользователя в конфигурационный файл приложения достаточно дописать:

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
		<register type="ICSSoft.Services.CurrentUserService+IUser, ICSSoft.Services.CurrentUserService" mapTo="IIS.TryAccessSystem.ClassOtherUser, TryAccessSystem(Forms)">
		  <lifetime type="singleton" />
		</register>
	  </container>
  </unity>
</configuration>
```

## Возможные ошибки

`CurrentUserService` может выдавать ошибку и сообщать, что не может найти сборку `Microsoft.Practices.Unity`.

Это связано с тем, что этой сборки действительно нет в папке с бинарниками проекта, так как она не копируется туда при сборке проекта. Однако, зачастую бывает, что она есть в глобальной папке со сборками операционной системы.

Чтобы исправить ситуацию необходимо у ссылки на эту сборку (Reference) в проекте поставить свойство `CopyLocal` = `true`.