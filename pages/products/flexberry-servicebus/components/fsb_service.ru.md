---
title: Сервис шины
sidebar: flexberry-servicebus_sidebar
keywords: Шина
toc: true
permalink: ru/fsb_service.html
lang: ru
summary: Конфигурация и запуск сервиса шины.
---

**Сервис шины** - это приложение, которое обычно запускается в виде службы или сервиса, и работает в фоновом режиме.

Есть два готовых приложения с сервисом шины:

* `NewPlatform.Flexberry.ServiceBus.ConsoleHost` - консольное приложение, удобно использовать для отладки
* `NewPlatform.Flexberry.ServiceBus.WinServiceHost` - приложение где сервис шины реализован в виде сервиса windows, используется в релизной конфигурации

## Конфигурация сервиса

Для конфигурации сервиса необходимо задать компоненты с которыми будет запущен сервис, обычно это делается с помощью [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx). С компонентами и их назначением можно ознакомиться в статье [Внутренняя архитектура Flexberry Service Bus](fsb_internal-architecture.html).

## Пример конфигурации сервиса

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <!-- ... -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <assembly name="System, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089" />
    <assembly name="System.ServiceModel, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089" />
    <assembly name="NewPlatform.Flexberry.ServiceBus" />
    <assembly name="NewPlatform.Flexberry.ServiceBus.ClientTools" />
    <assembly name="NewPlatform.Flexberry.ServiceBus.Components" />
    <alias alias="singleton" type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager, Microsoft.Practices.Unity" />
    <container>
      <!-- Конфигурация для Flexberry ORM -->
      <register type="ICSSoft.STORMNET.Business.Audit.IAuditService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.Audit.AuditService, ICSSoft.STORMNET.Business" />
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject" />
      <register type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService">
        <property name="CustomizationString" value="" />
        <lifetime type="singleton" />
      </register>

      <!-- Компоненты сервиса -->
      <register type="NewPlatform.Flexberry.ServiceBus.Components.CrossBusCommunicationService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.CrossBusCommunicationService">
        <lifetime type="singleton" />
        <property name="Enabled" value="false" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.ILogger" mapTo="NewPlatform.Flexberry.ServiceBus.Components.Log4NetLogger">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.ISubscriptionsManager" mapTo="NewPlatform.Flexberry.ServiceBus.Components.CachedSubscriptionsManager">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IReceivingManager" mapTo="NewPlatform.Flexberry.ServiceBus.Components.DefaultReceivingManager">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.ISendingManager" mapTo="NewPlatform.Flexberry.ServiceBus.Components.PrioritySendingManager">
        <lifetime type="singleton" />
        <property name="ClearMessageStatusOnStart" value="true" />
        <property name="ScanningPeriodMilliseconds" value="3000" />
        <property name="AdditionalMinutesBetweenRetries" value="2" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IStatisticsSettings" mapTo="NewPlatform.Flexberry.ServiceBus.Components.DefaultStatisticsSettings">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IStatisticsSaveService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.DefaultStatisticsSaveService">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IStatisticsTimeService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.DefaultStatisticsTimeService">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IStatisticsService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.DefaultStatisticsService">
        <lifetime type="singleton" />
        <property name="CollectBusStatistics" value="false" />
        <property name="CollectAdvancedStatistics" value="false" />
        <property name="StatisticsSavingPeriod" value="60000" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IStatisticsCompressorService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.DefaultStatisticsCompressorService">
        <lifetime type="singleton" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.IObjectRepository" mapTo="NewPlatform.Flexberry.ServiceBus.Components.CachedDataServiceObjectRepository">
        <lifetime type="singleton" />
      </register>
      <register name="wcfAddress" type="System.Uri" mapTo="System.Uri">
        <constructor>
          <param name="uriString" value="http://localhost:7075/WcfService" />
        </constructor>
      </register>
      <register name="wcfBinding" type="System.ServiceModel.Channels.Binding" mapTo="System.ServiceModel.WSHttpBinding">
        <constructor>
          <param name="securityMode" value="None" parameterType="System.ServiceModel.SecurityMode" />
          <param name="reliableSessionEnabled" value="false" />
        </constructor>
      </register>
      <!-- Компонент WCF сервиса использующий настройки также из Unity. -->
      <register type="NewPlatform.Flexberry.ServiceBus.Components.WcfService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.WcfService">
        <lifetime type="singleton" />
        <property name="Address" dependencyName="wcfAddress" />
        <property name="Binding" dependencyName="wcfBinding" />
        <property name="UseWcfSettingsFromConfig" value="false" />
        <property name="PublishWSDL" value="true" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.WebApiService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.WebApiService">
        <lifetime type="singleton" />
        <constructor>
          <param name="baseAddress" value="http://+:7085/RestService" />
          <param name="sendingManager" />
          <param name="receivingManager" />
        </constructor>
      </register>
    </container>
  </unity>
  <system.serviceModel>
    <client>
      <!-- Именованая конечная точка используемая для отправки сообщений клиентам через WCF или WEB. -->
      <endpoint address="" name="CallbackClient" binding="basicHttpBinding" contract="HighwaySbWcf.ICallbackSubscriber" />
    </client>
  </system.serviceModel>
  <!-- ... -->
</configuration>
```
