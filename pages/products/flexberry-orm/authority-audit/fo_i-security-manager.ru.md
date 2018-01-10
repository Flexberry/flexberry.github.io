---
title: Flexberry Security API - ISecurityManager
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Flexberry Security, полномочия, сервис полномочий
summary: Сервисы времени выполнения подсистемы полномочий Flexberry
toc: true
permalink: ru/fo_i-security-manager.html
lang: ru
---

Сервисы времени выполнения подсистемы полномочий Flexberry доступны через интерфейс `ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject`. 

## Получение доступа к инстанции ISecurityManager

Данный интерфес разрешается в реальную инстанцию через [Flexberry UnityFactory](fo_unity-factory.html) следующим образом:

```csharp
IUnityContainer container = UnityFactory.CreateContainer();
ISecurityManager securityManager = container.Resolve<ISecurityManager>();
```

## Конфигурирование ISecurityManager через Unity Container

ISecurityManager применяется в [Flexberry ORM](fo_flexberry-orm.html) и обязательно должен быть сконфигурирован через Unity Container одним из перечисленных ниже способов.

### Способ 1. Настройка в конфигурационном файле

Пример настройки в конфигурационном файле (`app.config` или `web.config`) для включенной системы полномочий.

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <!--...-->

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
    <container>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
       <constructor/>
      </register>

      <!--Определяем именованный экземпляр ISecurityManager, в который записывается "new DefaultSecurityManager(false)".-->
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.DefaultSecurityManager, ICSSoft.STORMNET.RightManager">
        <lifetime type="singleton" />
        <constructor>
          <param name="enabled" type="System.Boolean" value="false" />
        </constructor>
      </register>

    </container>
  </unity>

  <!--...-->
	
</configuration>
```

**Примечание:** для корректного взаимодействия [CheckingLibrary](efs_security-legacy-services.html) с [RightManager](efs_right-manager.html) в файл конфигурации необходимо добавить именованую регистрацию разрешения `ISecurityManager` с именем `securityManagerWithoutRightsCheck`.

Если система полномочий не применяется, то можно использовать упрощённый вариант конфигурации:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <!--...-->

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject" />
    </container>
  </unity>

  <!--...-->
	
</configuration>
```

## Управление правами доступа на уровне экземпляра сервиса данных

Для управления механизмом проверки [полномочий](efs_right-manager-module.html) на уровне [сервиса данных](fo_data-service.html) реализован специальный конструктор для [сервисов данных](fo_data-service.html), позволяющий выключить или проверки полномочий в рамках данной инстанции. Это позволяет избежать выключения проверки полномочий целиком для всего приложения, если есть реальная необходимость отключения полномочий для одной или нескольких операций.

Конструктор принимает в качестве параметра инстанцию `ICSSoft.STORMNET.Security.ISecurityManager`, которая содержит настройки механизма проверки полномочий. В качестве реализации по умолчанию можно использовать имплементацию этого интерфейса: `DefaultSecurityManager`.

### Пример кода

```csharp
using ICSSoft.STORMNET.Security;
// ...
ISecurityManager securityManager = new DefaultSecurityManager(false);
MSSQLDataService dataService = new MSSQLDataService(securityManager);

// Если нужны ограничения на детейлы, то сервис данных нужен и этой конструкции.
ExternalLangDef langdef = new ExternalLangDef { DataService = ds };

// Логика по работе с сервисом данных, который игнорирует проверку полномочий. 
// ...
DataObject[] dataObjects = dataService.LoadObjects(lcs);
```
