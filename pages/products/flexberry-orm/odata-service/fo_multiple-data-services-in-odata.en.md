--- 
title: configure ODataService with multiple types of data services 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM ODataService, DataService OData 
summary: problem of using data from different repositories 
toc: true 
permalink: en/fo_multiple-data-services-in-odata.html 
lang: en 
autotranslated: true 
hash: 17bb5b8985f66308d97d9b79a354aa6bd605b21195cda17d5e9036da6aa2c629 
--- 

## Problem 

Sometimes there is a need to use the same application objects that must be stored in different storage types. 
For example, statistical data that can accumulate in significant quantities, in this case, there is no need in complex samples and their frequent changes. 
The solution of such problems can be the use for such data [NoSQL](https://ru.wikipedia.org/wiki/NoSQL) repositories, e.g. [MongoDB](https://www.mongodb.com/). 

## Solutions 

### more `ODataService`'s 

By default, the service definition data `ODataService` is performed using the mechanism of dependency injection `Unity Container`. 
To change the logic definition of the service data by replacing the service that implements the interface `IHttpControllerActivator`, as shown in the example below: 

```csharp
namespace IIS.MyProject
{
    using System;

    internal static class ODataConfig
    {
        public static void Configure(HttpConfiguration config, IUnityContainer container)
        {
            // Use Unity as the WebAPI dependency resolver 
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Map an OData Service with basic objects 
            config.MapODataServiceDataObjectRoute(new DefaultDataObjectEdmModelBuilder(new[]
            {
                Assembly.Load("MyProject.Objects"),
            }));

            // Map an OData Service with service objects 
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

{% include important.html content="Replacement service should vypolnyaetsya after a method invocation `MapODataServiceDataObjectRoute`, otherwise it will be replaced once again by the realization `NewPlatform.Flexberry.ORM.The ODataService.DataObjectControllerActivator`" %} 

The service that implements the interface `IHttpControllerActivator` may be similar to the following: 

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

In this case, when referring to `ODataService` was on the route `service` will be used named check `IDataService` with the name `MongoDbDataService`, when referring to `ODataService` was on the route `odata` will be used named check `IDataService` with the name `MSSQLDataService`. 

An example configuration for this service: 

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

{% include note.html content="When you use this solution, `ember` application will need tuning adapters](efd2_adapters.html)." %} 

### the definition of the type of the service data object type 

Also define the type of data service is possible, based on the type of data objects to which there is an appeal, in this case, you can register one `ODataService`, as in the example below: 

```csharp
namespace IIS.MyProject
{
    using System;

    internal static class ODataConfig
    {
        public static void Configure(HttpConfiguration config, IUnityContainer container)
        {
            // Use Unity as the WebAPI dependency resolver 
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Map The OData Service 
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

Now the service that implements the interface `IHttpControllerActivator` may be similar to the following: 

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

Source code for this example can be found in the repository [FlexberryOrmMongoDbDataServiceDemo.ODataBackend](https://github.com/Flexberry/FlexberryOrmMongoDbDataServiceDemo.ODataBackend). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}