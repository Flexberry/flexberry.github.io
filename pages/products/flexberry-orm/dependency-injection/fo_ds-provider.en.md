---
title: Default data service
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data service
summary: Data service initialization algorithm
toc: true
permalink: en/fo_ds-provider.html
lang: en
---

`DataServiceProvider.DataService` - это [сервис данных](fo_data-service.html), который инициализируется на основании параметров, заданных в файле конфигурации. Таким образом, `DataServiceProvider.DataService` является [сервисом данных](fo_data-service.html) по умолчанию.

### Алгоритм инициализации DataServiceProvider.DataService

При инициализации `DataServiceProvider.DataService` используется следующий алгоритм (инициализация происходит, если нет закэшированного значения, либо стоит флаг, что всегда нужно возвращать новый сервис данных):

1.Производится попытка разрешить тип [сервиса данных](fo_data-service.html) через Unity. Например, чтобы использовался [MSSQLDataService](fo_mssql-data-service.html) из перечня [стандартных сервисов данных](fo_standard-data-services.html), в файле конфигурации требуется добавить следующее определение:

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

2.Если тип [сервиса данных](fo_data-service.html) удалось разрешить через Unity, то определяется строка соединения. Сначала в web-стиле, потом в win-стиле.

**web-стиль**:

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

**win-стиль**:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="CustomizationStrings" value="SERVER=server;Trusted_connection=yes;DATABASE=dbname;"/>
  </appSettings>
</configuration>
```

3. Если сервис данных не удалось получить, то он конструируется следующим образом: тип [сервиса данных](fo_data-service.html) определяется через настройку `DataServiceType` в файле конфигурации. А строка соединения определяется в зависимости от того, в каком режиме приложение, web или win.

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
  </appSettings>
</configuration>
```

## Строки соединения

Подробнее почитать про строки подключения для LocalDB можно почитать в  статье [Настройка строки соединения c БД](fd_sql-express-local-db.html).

Про строки подключения в целом написано в [msdn](https://msdn.microsoft.com/ru-ru/library/ms254500(v=vs.110).aspx).
