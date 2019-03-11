--- 
title: Caching of extended restrictions in WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP.NET 
toc: true 
permalink: en/fa_wolv-adv-limit-caching.html 
lang: en 
autotranslated: true 
hash: 51b2adfb95f39404d3069124fdfaad28ebca114a6c66e71de0c5076f9563aad5 
--- 

To improve the performance [WebObjectListView](fa_web-object-list-view.html) you can add caching custom extended restrictions. 

You can use the technological type `NewPlatform.Flexberry.Services.AdvLimitManagerCacheDecorator` that adds caching to any other seris as follows: 

```csharp
// Global.asax / Application_Start 
var decorable = new DefaultAdvLimitManager(BridgeToDS.GetDataService());
var cache = new InternalCacheService();
AdvLimitManager.Current = new AdvLimitManagerCacheDecorator(decorable, cache);
``` 

Or you can use the Unity configuration section. 

## Connection caching settings and functions limitations 

In order to have database queries once again not carried out need to set the current `UserSettingsService`. 

Example: 

```csharp
// Service settings of the user. 
if (container.IsRegistered<IUserSettingsService>())
    UserSettingsService.Current = container.Resolve<IUserSettingsService>();
else
    LogService.LogWarn("IUserSettingsService is not configured in Unity. Will use the default implementation.");
``` 

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <!-- ... -->
    <!-- The service configuration custom settings and enabled caching. -->
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

You can do without Unity and install `UserSettingsService.Current` software. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}