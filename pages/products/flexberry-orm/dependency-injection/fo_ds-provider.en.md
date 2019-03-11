--- 
title: Receiving service data 
sidebar: flexberry-orm_sidebar 
keywords: Dataserviceprovider, DataService, connectionstring config, app.config web.config 
summary: How to configure the data service and get its authority from anywhere in the application 
toc: true 
permalink: en/fo_ds-provider.html 
lang: en 
autotranslated: true 
hash: 44521544d5e3234f0d31b8a66b6803ed7b139f8e127dbe772d433632ea2ae457 
--- 

`DataServiceProvider.DataService` is [service data](fo_data-service.html), which is initialized based on the settings specified in the configuration file (`App.cobfig` or `Web.config`). Thus, `DataServiceProvider.DataService` is [service data](fo_data-service.html) by default. 

### Algorithm initialization DataServiceProvider.DataService 

Initialization `DataServiceProvider.DataService` use the following algorithm (initialization, if there is a cached value, or is the flag that should always return a new data service): 

1.Attempts to resolve type [service data](fo_data-service.html) through Unity. For example, to use MSSQLDataService in the configuration file you want to add the following definition: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
    <register type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
        mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
      <constructor/>
    </register>
    </container>
  </unity>
</configuration>
``` 

2.If the type of [data service](fo_data-service.html) was resolved via Unity, is determined by the connection string. First, in the web style, then win in style. 

**web style**: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <connectionStrings>
    <add name="SomeConnectionStringName" connectionString="SERVER=server;Trusted_connection=yes;DATABASE=dbname;" />
  </connectionStrings>
  <appSettings>
    <add key="DefaultConnectionStringName" value="SomeConnectionStringName"/>
  </appSettings>
</configuration>
``` 

**win-style**: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="CustomizationStrings" value="SERVER=server;Trusted_connection=yes;DATABASE=dbname;"/>
  </appSettings>
</configuration>
``` 


3.Next, get DataServiceProvider.DataService occurs in the old algorithm. Type [service data](fo_data-service.html) - setting DataServiceType in the configuration file. And the connection string is determined depending on in which mode the app, web or win. 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
  </appSettings>
</configuration>
``` 

## connection String 

You can read more about connection strings for LocalDB can be read in [this article](fd_sql-express-local-db.html). 

About the connection strings in General can be found in [msdn](https://msdn.microsoft.com/ru-ru/library/ms254500(v=vs.110).aspx). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}