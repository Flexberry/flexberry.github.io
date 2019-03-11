--- 
title: Service bus 
sidebar: flexberry-servicebus_sidebar 
keywords: Bus 
toc: true 
permalink: en/fsb_service.html 
lang: en 
autotranslated: true 
hash: fab04b5f03bb6908a6cc74acd2518eb9976234c200537570858d3f4befe39309 
summary: Configuration and start the service bus. 
--- 

**Service bus** - is an application that usually runs as a service or service, and runs in the background. 

There are two ready applications with the service bus: 

* `NewPlatform.Flexberry.ServiceBus.ConsoleHost` console application, useful for debugging 
* `NewPlatform.Flexberry.ServiceBus.WinServiceHost` application where service bus is implemented as a windows service, use the release configuration 

## configuration of the service 

For service configurations, you must specify the components which will run the service, usually this is done using the [Unity Container](https://msdn.microsoft.com/en-us/library/ff647202.aspx). With the components and their purpose can be found in the article [the Internal architecture Flexberry Service Bus](fsb_internal-architecture.html). 

## Example of a service configuration 

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
      <!-- Configuration for the ORM Flexberry -->
      <register type="ICSSoft.STORMNET.Business.Audit.IAuditService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.Audit.AuditService, ICSSoft.STORMNET.Business" />
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject" />
      <register type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService">
        <property name="CustomizationString" value="" />
        <lifetime type="singleton" />
      </register>

      <!-- Components of the service -->
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
      <!-- Component WCF service using the configuration from Unity. -->
      <register type="NewPlatform.Flexberry.ServiceBus.Components."WcfService"" mapTo="NewPlatform.Flexberry.ServiceBus.Components."WcfService"">
        <lifetime type="singleton" />
        <property name="Address" dependencyName="wcfAddress" />
        <property name="Binding" dependencyName="wcfBinding" />
        <property name="UseWcfSettingsFromConfig" value="false" />
        <property name="PublishWSDL" value="true" />
      </register>
      <register type="NewPlatform.Flexberry.ServiceBus.Components.WebApiService" mapTo="NewPlatform.Flexberry.ServiceBus.Components.WebApiService">
        <lifetime type="singleton" />
        <constructor>
          <param name="baseAddress" value="http:// :7085/RestService" />
          <param name="sendingManager" />
          <param name="receivingManager" />
        </constructor>
      </register>
    </container>
  </unity>
  <system.serviceModel>
    <client>
      <!-- Named endpoint used for sending messages to clients via WCF or WEB. -->
      <endpoint address="" name="CallbackClient" binding="basicHttpBinding" contract="HighwaySbWcf.ICallbackSubscriber" />
    </client>
  </system.serviceModel>
  <!-- ... -->
</configuration>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}