--- 
title: DRDataService 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, service data, reading data 
summary: Using dirty read 
toc: true 
permalink: en/fo_dr-data-service.html 
lang: en 
autotranslated: true 
hash: e2e91d2775884bb474b21b4f5c7a28225646e5d462ff00ce4b95650c398c087e 
--- 

`DRDataService` - [service data](fo_data-service.html), the heir [SQLDataService](fo_sql-data-service.html), has the same functionality as conventional [MSSQLDataService](fo_mssql-data-service.html), but when reading data ["dirty read (dirty read)"](http://msdn.microsoft.com/ru-ru/library/ms173763.aspx). 

## receiving a copy DRDataService 

Instance DRDataService can be obtained through [UnityFactory](fo_unity-factory.html) using the following code: 

```csharp
 IUnityContainer container = UnityFactory.CreateContainer();
 IDataService dataService = container.Resolve<IDataService>("DRDataService");
 dataService.CustomizationString = DataServiceProvider.DataService.CustomizationString;
``` 

## Configure DataService 

Setting `DRDataService` via the configuration file : 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
     <register name="DRDataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.DRDataService, ICSSoft.STORMNET.Business.DRDataService">
        <constructor/>
      </register>
    </container>
  </unity>
</configuration>
``` 

The connection string in this example is configured as described in the article about [`DataServiceProvider`](fo_ds-provider.html) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/