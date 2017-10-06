---
title: DRDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных, чтение данных
summary: Использование dirty read
toc: true
permalink: ru/fo_dr-data-service.html
lang: ru
---

`DRDataService` - [сервис данных](fo_data-service.html), наследник [SQLDataService](fo_sql-data-service.html), обладает тем же функционалом, что и обычный [MSSQLDataService](fo_mssql-data-service.html), но при чтении данных используется ["грязное чтение (dirty read)"](http://msdn.microsoft.com/ru-ru/library/ms173763.aspx).

## Получение экземпляра DRDataService

Экземпляр DRDataService можно получить через [UnityFactory](fo_unity-factory.html), выполнив следующий код:

```csharp
 IUnityContainer container = UnityFactory.CreateContainer();
 IDataService dataService = container.Resolve<IDataService>("DRDataService");
 dataService.CustomizationString = DataServiceProvider.DataService.CustomizationString;
```

## Настройка DataService

Настройка `DRDataService` через конфигурационный файл :

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

Строка соединения в данном примере настраивается так, как указано в статье про [`DataServiceProvider`](fo_ds-provider.html)
