---
title: Кэширование расширенных ограничений в WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP.NET
toc: true
permalink: ru/fa_wolv-adv-limit-caching.html
lang: ru
---

Для повышения быстродействия [WebObjectListView](fa_web-object-list-view.html) можно добавить кэширование пользовательских расширенных ограничений.

Для этого можно воспользоваться технологическим типом `NewPlatform.Flexberry.Services.AdvLimitManagerCacheDecorator`, добавляющим кэширование для любого другого сериса следующим образом:

```csharp
// Global.asax / Application_Start
var decorable = new DefaultAdvLimitManager(BridgeToDS.GetDataService());
var cache = new InternalCacheService();
AdvLimitManager.Current = new AdvLimitManagerCacheDecorator(decorable, cache);
```

Либо воспользоваться конфигурационной секцией Unity.

## Подключение кэширования настроек и функций ограничений

Для того чтобы у нас запросы к БД лишний раз не выполнялись нужно настроить текущий `UserSettingsService`.

Пример:

```csharp
// Сервис настроек пользователя.
if (container.IsRegistered<IUserSettingsService>())
    UserSettingsService.Current = container.Resolve<IUserSettingsService>();
else
    LogService.LogWarn("IUserSettingsService не сконфигурирован в Unity. Будет использована реализация по умолчанию.");
```

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <!-- ... -->
    <!-- Конфигурация сервиса пользовательских настроек с поддержкой кэширования. -->
    <register name="DecorableUserSettingsService" type="IcsharpSoft.Services.IUserSettingsService, UserSettingsService" mapTo="IcsharpSoft.Services.UserSettingsService, UserSettingsService">
        <lifetime type="singleton" />
        <constructor />
    </register>
    <register name="CacheForUserSettingsService" type="NewPlatform.Flexberry.Services.ICacheService, IcsharpSoft.STORMNET.Web.Tools" mapTo="NewPlatform.Flexberry.Services.InternalCacheService, IcsharpSoft.STORMNET.Web.Tools">
        <lifetime type="singleton" />
        </register>
    <register type="IcsharpSoft.Services.IUserSettingsService, UserSettingsService" mapTo="NewPlatform.Flexberry.Services.UserSettingsServiceCacheDecorator, IcsharpSoft.STORMNET.Web.Tools">
        <lifetime type="singleton" />
        <constructor>
            <param name="decorable" dependencyName="DecorableUserSettingsService" />
            <param name="cache" dependencyName="CacheForUserSettingsService" />
        </constructor>
    </register>
    <!-- ... -->
</unity>
```

Можно обойтись без Unity и устанавливать `UserSettingsService.Current` программно.
