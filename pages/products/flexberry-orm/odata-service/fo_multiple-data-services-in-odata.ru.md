---
title: Настройка ODataService с несколькими типами сервисов данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM ODataService, DataService, OData
summary: Решение проблемы использования данных из разных хранилищ
toc: true
permalink: ru/fo_multiple-data-services-in-odata.html
lang: ru
---

## Проблема

Иногда возникает потребность использовать в одном приложении объекты, которые должны храниться в разных типах хранилищ.
Например, статистические данные, которые могут накапливаться в значительных объемах, при этом, отсутствует необходимость, в сложных выборках и их частом изменении.
Решением подобной проблемы может быть использование для таких данных [NoSQL](https://ru.wikipedia.org/wiki/NoSQL) хранилища, например [MongoDB](https://www.mongodb.com/).

## Решения

### Регистрация нескольких `ODataService`’ов

По умолчанию, определения сервиса данных для `ODataService` выполняется с помощью механизма внедрения зависимости `Unity Container`.
Изменить логику определения сервиса данных, можно, заменив сервис, реализующий интерфейс  `IHttpControllerActivator`, как показано на примере ниже:

```csharp
namespace IIS.MyProject
{
    using System;

    internal static class ODataConfig
    {
        public static void Configure(HttpConfiguration config, IUnityContainer container)
        {
            // Use Unity as WebAPI dependency resolver
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Map OData Service with basic objects
            config.MapODataServiceDataObjectRoute(new DefaultDataObjectEdmModelBuilder(new[]
            {
                Assembly.Load("MyProject.Objects"),
            }));

            // Map OData Service with service objects
            config.MapODataServiceDataObjectRoute(new DefaultDataObjectEdmModelBuilder(new[]
            {
                typeof(ApplicationLog).Assembly,
                typeof(UserSetting).Assembly,
                typeof(FlexberryUserSetting).Assembly,
                typeof(Lock).Assembly
            }), "service", "service");

            // Replace IHttpControllerActivator service
            config.Services.Replace(typeof(IHttpControllerActivator), new ControllerActivator(container));
        }
    }
}
```

{% include important.html content="Замена сервиса должна ваполняться после вызова метода `MapODataServiceDataObjectRoute`, в противном случае он будет повторно заменен реализацией `NewPlatform.Flexberry.ORM.ODataService.DataObjectControllerActivator`" %}

Сервис, реализующий интерфейс `IHttpControllerActivator`, может быть примерно следующим:

```csharp
namespace IIS.MyProject
{
    using System;

    public class ControllerActivator : IHttpControllerActivator
    {
        private readonly IUnityContainer _container;
        private readonly IHttpControllerActivator _defaultActivator;

        public ControllerActivator(IUnityContainer container)
        {
            _container = container;
            _defaultActivator = new DefaultHttpControllerActivator();
        }

        public IHttpController Create(HttpRequestMessage request, HttpControllerDescriptor controllerDescriptor, Type controllerType)
        {
            if (controllerDescriptor.ControllerName == "DataObject")
            {
                ManagementToken token = request.GetODataServiceToken();

                string dataServiceName = null;
                if (token.Route.RoutePrefix == "odata")
                    dataServiceName = "MSSQLDataService";
                else if (token.Route.RoutePrefix == "service")
                    dataServiceName = "MongoDbDataService";

                return new DataObjectController(_container.Resolve<IDataService>(dataServiceName), token.Model, token.Events, token.Functions);
            }

            return _defaultActivator.Create(request, controllerDescriptor, controllerType);
        }
    }
}
```

В данном случае, при обращении к `ODataService` зарегистрированному на маршруте `service`, будет использована именованая регистрация `IDataService` с именем `MongoDbDataService`, при обращении к `ODataService` зарегистрированному на маршруте `odata`, будет использована именованая регистрация `IDataService` с именем `MSSQLDataService`.

Пример конфигурации для данного сервиса:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <!-- ... -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
      <register name="MongoDbDataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="NewPlatform.Flexberry.ORM.MongoDbDataService, NewPlatform.Flexberry.ORM.MongoDbDataService">
        <constructor />
        <property name="CustomizationString" value="SERVER=server;Trusted_connection=yes;DATABASE=database;" />
      </register>
      <register name="MSSQLDataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor />
        <property name="CustomizationString" value="SERVER=server;Trusted_connection=yes;DATABASE=database;" />
      </register>
      <!-- ... -->
    </container>
  </unity>
</configuration>
```

{% include note.html content="При использовании такого решения, в `ember`-приложении потребуется [настройка адаптеров](efd2_adapters.html)." %}

### Определение типа сервиса данных по типу объектов

Также определять тип необходимого сервиса данных, можно, на основе типа объектов данных, к которым идет обращение, в данном случае можно зарегистрировать один `ODataService`, как в примере ниже:

```csharp
namespace IIS.MyProject
{
    using System;

    internal static class ODataConfig
    {
        public static void Configure(HttpConfiguration config, IUnityContainer container)
        {
            // Use Unity as WebAPI dependency resolver
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Map OData Service
            config.MapODataServiceDataObjectRoute(new DefaultDataObjectEdmModelBuilder(new[]
            {
                Assembly.Load("MyProject.Objects"),
                typeof(ApplicationLog).Assembly,
                typeof(UserSetting).Assembly,
                typeof(FlexberryUserSetting).Assembly,
                typeof(Lock).Assembly
            }));

            // Replace IHttpControllerActivator service
            config.Services.Replace(typeof(IHttpControllerActivator), new ControllerActivator(container));
        }
    }
}
```

Теперь сервис, реализующий интерфейс `IHttpControllerActivator`, может быть примерно следующим:

```csharp
namespace IIS.MyProject
{
    using System;

    public class ControllerActivator : IHttpControllerActivator
    {
        private readonly IUnityContainer _container;
        private readonly IHttpControllerActivator _defaultActivator;

        public ControllerActivator(IUnityContainer container)
        {
            _container = container;
            _defaultActivator = new DefaultHttpControllerActivator();
        }

        public IHttpController Create(HttpRequestMessage request, HttpControllerDescriptor controllerDescriptor, Type controllerType)
        {
            if (controllerDescriptor.ControllerName == "DataObject")
            {
                string dataServiceName = null;
                switch (request.GetRouteData().Values["odataPath"])
                {
                    case "ODataPathToMyObject":
                    case "ODataPathToMyOtherObject":
                        dataServiceName = "MSSQLDataService";
                        break;
                    default:
                        dataServiceName = "MongoDbDataService";
                        break;
                }

                ManagementToken token = request.GetODataServiceToken();
                return new DataObjectController(_container.Resolve<IDataService>(dataServiceName), token.Model, token.Events, token.Functions);
            }

            return _defaultActivator.Create(request, controllerDescriptor, controllerType);
        }
    }
}
```

Исходный код этого примера можно найти в репозитории [FlexberryOrmMongoDbDataServiceDemo.ODataBackend](https://github.com/Flexberry/FlexberryOrmMongoDbDataServiceDemo.ODataBackend).
