---
title: Сервис текущего пользователя
sidebar: flexberry-orm_sidebar
keywords: Flexberry, Public
toc: true
permalink: ru/fo_current-user-service.html
---
* **Программная библиотека**: ICSSoft.Services.CurrentUserService.dll
* **Предназначение**: Flexberry CurrentUserService  позволяет получать текущего пользователя приложения. А также менять как отдельные свойства пользователя, так и сам способ работы с ним.


## CurrentUserService

ICSSoft.Services.CurrentUserService позволяет получать текущего пользователя приложения. А также менять как отдельные свойства пользователя, так и сам способ работы с ним.

Получить текущего пользователя приложения можно используя `ICSSoft.Services.CurrentUserService.CurrentUser`. Атрибут имеет интерфейс `ICSSoft.Services.CurrentUserService+IUser`:

```cs
        public interface IUser
        {
            /// <summary>
            /// Имя пользователя ("VASYA Pupkin")
            /// </summary>
            string FriendlyName { get; set; }

            /// <summary>
            /// Домен пользователя
            /// </summary>
            string Domain { get; set; }

            /// <summary>
            /// Логин пользователя ("vpupkin")
            /// </summary>
            string Login { get; set; }
        }
```

По умолчанию логин и домен пользователя берутся из переменных окружения (для win приложений) и из текущего HTTP контекста (для веб приложений). Имя пользователя, если не установлено явно, вычисляется через логин.

Если необходимо изменить способ работы с текущим пользователем (например, хранить логин пользователя в кэше веб приложения), нужно реализовать интерфейс `ICSSoft.Services.CurrentUserService+IUser` и сопоставить ему получившийся обработчик. Для сопоставления можно использовать метод `ICSSoft.Services.CurrentUserService.ResolveUser<T>()` и настройки в конфигурационном файле (используется [Flexberry UnityFactory](fo_unity-factory.html):

```xml

<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <configSections>
    <section name="unity"
        type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection,
              Microsoft.Practices.Unity.Configuration" />
  </configSections>

  <unity>
    <typeAliases>
      <typeAlias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity"/>
      <!--<typeAlias alias="external" type="Microsoft.Practices.Unity.ExternallyControlledLifetimeManager, Microsoft.Practices.Unity"/>-->
      <typeAlias alias="CurrentUserService" type="ICSSoft.Services.CurrentUserService+IUser, ICSSoft.Services.CurrentUserService"/>
    </typeAliases>
    <containers>
      <container>
        <types>
          <type type="CurrentUserService" mapTo="TestProjectWConfig.CustomUser, TestProjectWConfig">
            <lifetime type="singleton"/>
          </type>
        </types>
      </container>
    </containers>
  </unity>
</configuration>
```

## Переопределение сервиса текущего пользователя

**Вынести пример сюда**
Пример переопределения сервиса текущего пользователя находится [здесь](right-manager-for-strokes-example.html).

## Возможные ошибки
Если `CurrentUserService` выдает ошибку и сообщает, что не может найти сборку `Microsoft.Practices.Unity`.

Это связано с тем, что этой сборки действительно нет в папке с бинарниками проекта, так как она не копируется туда при сборке проекта. Однако, зачастую бывает, что она есть в глобальной папке со сборками операционной системы.

Чтобы исправить ситуацию необходимо у ссылки на эту сборку (Reference) в проекте поставить свойство `CopyLocal` = `true`.

