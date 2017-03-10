---
title: DataServiceProvider.DataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_data-service-provider-data-service.html
---

<<<<<<< HEAD
# DataServiceProvider.DataService
=======
## DataServiceProvider.DataService
>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f
DataServiceProvider.DataService - это [сервис данных](fo_data-service.html), который инициализируется на основании параметров, заданных в файле конфигурации. Таким образом, DataServiceProvider.DataService является [сервисом данных](fo_data-service.html) по умолчанию.

## Алгоритм инициализации DataServiceProvider.DataService
В версии после 06.04.2015 при инициализации DataServiceProvider.DataService используется следующий алгоритм (инициализация происходит, если нет закэшированного значения, либо стоит флаг, что всегда нужно возвращать новый сервис данных):

<<<<<<< HEAD
1. Производится попытка разрешить тип [сервиса данных](fo_data-service.html) через Unity. Например, чтобы использовался [Standard-Data-Services-Flexberry-FRAMEWORK|MSSQLDataService], в файле конфигурации требуется добавить следующее определение:
=======
1.Производится попытка разрешить тип [сервиса данных](fo_data-service.html) через Unity. Например, чтобы использовался [MSSQLDataService](fo_standard-data-services.html), в файле конфигурации требуется добавить следующее определение:

>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f
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
<<<<<<< HEAD
2. Если тип [сервиса данных](fo_data-service.html) удалось разрешить через Unity, то определяется строка соединения. Сначала в web-стиле, потом в win-стиле.
=======
2.Если тип [сервиса данных](fo_data-service.html) удалось разрешить через Unity, то определяется строка соединения. Сначала в web-стиле, потом в win-стиле.
>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f

'''web-стиль''':

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

'''win-стиль''':

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="CustomizationStrings" value="SERVER=server;Trusted_connection=yes;DATABASE=dbname;"/>
  </appSettings>
</configuration>
```

<<<<<<< HEAD
3. Далее получение DataServiceProvider.DataService происходит по старому алгоритму. Тип [сервиса данных](fo_data-service.html) - через настройку DataServiceType в файле конфигурации. А строка соединения определяется в зависимости от того, в каком режиме приложение, web или win.
=======
3.Далее получение DataServiceProvider.DataService происходит по старому алгоритму. Тип [сервиса данных](fo_data-service.html) - через настройку DataServiceType в файле конфигурации. А строка соединения определяется в зависимости от того, в каком режиме приложение, web или win.

>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f
```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <appSettings>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
  </appSettings>
</configuration>
```

## Строки соединения
Подробнее почитать про строки подключения для LocalDB можно почитать в [этой статье](Flexberry-tool-and-s-q-l-express-local-d-b.html).

Про строки подключения в целом можно почитать в [msdn](https://msdn.microsoft.com/ru-ru/library/ms254500(v=vs.110).aspx).
