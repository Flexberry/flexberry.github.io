---
title: Получение сервиса данных
sidebar: flexberry-orm_sidebar
keywords: Dataserviceprovider, DataService, connectionstring, config, app.config, web.config
summary: Как настроить сервис данных и получить его инстанцию из любого места приложения
toc: true
permalink: ru/fo_ds-provider.html
lang: ru
---

`DataServiceProvider.DataService` - это [сервис данных](fo_data-service.html), который инициализируется на основании параметров, заданных в файле конфигурации (`App.cobfig` или `Web.config`). Таким образом, `DataServiceProvider.DataService` является [сервисом данных](fo_data-service.html) по умолчанию.

### Алгоритм инициализации DataServiceProvider.DataService

При инициализации `DataServiceProvider.DataService` используется следующий алгоритм (инициализация происходит, если нет закэшированного значения, либо стоит флаг, что всегда нужно возвращать новый сервис данных):

1.Производится попытка разрешить тип [сервиса данных](fo_data-service.html) через Unity. Например, чтобы использовался MSSQLDataService, в файле конфигурации требуется добавить следующее определение:

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


3.Далее получение DataServiceProvider.DataService происходит по старому алгоритму. Тип [сервиса данных](fo_data-service.html) - через настройку DataServiceType в файле конфигурации. А строка соединения определяется в зависимости от того, в каком режиме приложение, web или win.

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
  </appSettings>
</configuration>
```

## Строки соединения

Подробнее почитать про строки подключения для LocalDB можно почитать в [этой статье](fd_sql-express-local-db.html).

Про строки подключения в целом можно почитать в [msdn](https://msdn.microsoft.com/ru-ru/library/ms254500(v=vs.110).aspx).
