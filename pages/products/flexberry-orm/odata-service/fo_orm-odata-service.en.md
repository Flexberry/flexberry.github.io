---
title: ODataService
sidebar: flexberry-orm_sidebar
keywords: OData
summary: Features, limitations, recommendations for use the ODataService
toc: true
permalink: en/fo_orm-odata-service.html
lang: en
autotranslated: true
hash: 6e25e3294f4584450d88dca20b22644c284c18b93d7d844843abb36937a57314
---

`Flexberry ORM ODataService` allows a convenient way to create OData services on top of storage.

{% include note.html content="`Flexberry ORM ODataService` is available for installation in the project via [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService)." %}

## The list of libraries `Flexberry ORM ODataService`

The composition NuGet package `Flexberry ORM ODataService` includes the following assemblies:

* NewPlatform.Flexberry.ORM.ODataService.dll

## Logic ODataService

The ODataService is a WebApi controller that provides access to data in the database using the OData Protocol V4.
A typical query to retrieve data works as follows:

* HTTP GET request is formulated by the client in the form of a url indicating the type of data required, the desired attributes, filtering, sorting, etc.
* Interpreterpath received the ODataService url in the terms .NET LINQ
* LINQ is passed to [LinqProvider](fo_linq-provider.html) which translates LINQ [LCS](fo_loading-customization-struct.html).
* LCS is passed to the ORM DataService that returns data as an array of data objects
* Collected service data an array of data objects is serialized and sent to customer

A typical query to update the data works as follows:

* The HTTP request corresponding to the operation being performed is passed to the WebApi controller
* Understand the data query to retrieve information about a specific mutable data object (its type and primary key)
* According to the data loaded object data and the master of the first level have their own properties. Computable fields are ignored.
* The obtained data objects the requested operation is executed changes to the data. If this detail, it loads in addition the aggregator and he added to this detail the collection is necessary for the operation of a business server platform if you change detail.
* Changed the data object is sent to the data service.
* After any operation on the repository is returned to the client serialized data object or response about the success of the operation.

## Limitations, features, recommendations for the design

There are a number of features in the design of objects that will be used through ORM `Flexberry ODataService`:

* E-view (a view that has the name "<Classname>E") of detail should be added a link to the aggregator.
* Connect `Flexberry ORM ODataService`. To connect in a web project (WebForms) to take advantage of the `Flexberry ORM ODataService`, you must do the following:

 * Connect the NuGet package `Flexberry ORM ODataService`.
 * In App_Start application to create a class "ODataConfig.cs."
 * Replace the contents of the class about the following:

```csharp
namespace ODataServiceTemplate
{
    using System;
    using System.Diagnostics.Contracts;
    using System.Reflection;
    using System.Web.Http;

    using ICSSoft.STORMNET;

    using Microsoft.Practices.Unity;

    using NewPlatform.Flexberry.ORM.ODataService;
    using NewPlatform.Flexberry.ORM.ODataService.Extensions;
    using NewPlatform.Flexberry.ORM.ODataService.Functions;
    using NewPlatform.Flexberry.ORM.ODataService.Model;

    internal static class ODataConfig
    {
        public static void Configure(HttpConfiguration config, IUnityContainer container, HttpServer httpServer)
        {
            // Use Unity as the WebAPI dependency resolver 
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Create entity data model builder 
            var assemblies = new[] { Assembly.GetCallingAssembly() };
            var builder = new DefaultDataObjectEdmModelBuilder(assemblies);

            // Map The OData Service 
            var token = config.MapODataServiceDataObjectRoute(builder, httpServer);

            // User functions 
            token.Functions.Register(new Func<QueryParameters, string>(Test));

            // Event handlers 
            token.Events.CallbackAfterCreate = CallbackAfterCreate;
        }

        private static void CallbackAfterCreate(DataObject dataObject)
        {
            // TODO: implement handler 
        }
    }
}
```

 * In The Global.asax, add:

```csharp
namespace ODataServiceTemplate
{
    using System;
    using System.Web;
    using System.Web.Http;

    using Microsoft.Practices.Unity;
    using Microsoft.Practices.Unity.Configuration;

    public class Global : HttpApplication
    {
        protected void Application_Start(object sender, EventArgs e)
        {
            IUnityContainer container = new UnityContainer();
            container.LoadConfiguration();

            GlobalConfiguration.Configure(configuration => ODataConfig.Configure(configuration, container, GlobalConfiguration.DefaultServer));
        }
    }
}
```

 * In order for code to compile, you may need to install additional NuGet packages in the app: [Microsoft.AspNet.WebApi.Cors](https://www.nuget.org/packages/Microsoft.AspNet.WebApi.Cors) and [microsoft.aspnet.webapi.webhost](https://www.nuget.org/packages/microsoft.aspnet.webapi.webhost/).

 * Add to web.config or to check the availability of the following records:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
<system.webServer>
    <handlers>
    <remove name="OPTIONSVerbHandler" />
    <remove name="TRACEVerbHandler" />
    <remove name="ExtensionlessUrlHandler-Integrated-4.0" />
    <add name="ExtensionlessUrlHandler-Integrated-4.0" path="*." verb="*" type="System.Web.Handlers.TransferRequestHandler" preCondition="integratedMode,runtimeVersionv4.0" />
    </handlers> 
</system.webServer>
</configuration>
```

## A mechanism for invoking logic after saving object using the callback-functions

```csharp
/// <summary> 
/// Register Data objects. 
/// </summary> 
/// <param name="config">the Http configuration.</param> 
public static void Register(HttpConfiguration config)
{
            var cors = new EnableCorsAttribute("http://localhost:4210,https://flexberry-ember-security-dev.firebaseapp.com", "*", "*") { SupportsCredentials = true };
            config.EnableCors(cors);

            config.DependencyResolver = new UnityDependencyResolver(container);

            var assemblies = new[]
            {
                typeof(Suggestion).Assembly,
                typeof(ApplicationLog).Assembly,
                typeof(UserSetting).Assembly,
                typeof(FlexberryUserSetting).Assembly,
                typeof(Agent).Assembly,
                typeof(AuditEntity).Assembly,
                typeof(Lock).Assembly
            };
            var modelBuilder = new DefaultDataObjectEdmModelBuilder(assemblies);
            modelBuilder.PropertyFilter = PropertyFilter;
            var token = config.MapODataServiceDataObjectRoute(modelBuilder);
            token.Events.CallbackAfterCreate = AfterCreate;
            token.Events.CallbackAfterUpdate = AfterUpdate;
            token.Events.CallbackAfterDelete = AfterDelete;
            config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
    }
    catch (Exception ex)
    {
        LogService.LogError("RunApp odata service error.", ex);
        throw;
    }
}


/// <summary> 
/// Method called after object creation. 
/// </summary> 
/// <param name="obj">Object after creation.</param> 
public static void AfterCreate(DataObject obj){

}
/// <summary> 
/// Method is called after updating the object. 
/// </summary> 
/// <param name="obj">the Object after the update.</param> 
public static void AfterUpdate(DataObject obj){

}
/// <summary> 
/// Method called after object removal. 
/// </summary> 
/// <param name="obj">the Object before the deletion.</param> 
public static void AfterDelete(DataObject obj){

}

```

PstrfAfterCreate` method will be called after each POST request made by a client to create the entity.
PstrfAfterUpdate` method will be called after each PATCH request made by a client to update the entity.
PstrfAfterDelete` method will be called after each DELETE request made by the client to remove the entity.

## Custom OData function

Example of registering a custom OData function

```csharp
/// <summary> 
/// Register Data objects. 
/// </summary> 
/// <param name="config">the Http configuration.</param> 
public static void Register(HttpConfiguration config)
{
    var cors = new EnableCorsAttribute("*", "*", "*");
    config.EnableCors(cors);

    var container = new UnityContainer().LoadConfiguration();
    // WebApi will create the controllers, passing in the constructors of the various parameters of the instance which will be requested at this very DependencyResolver. 
    config.DependencyResolver = new UnityDependencyResolver(container);

    // The most important thing for the ODataService to know what data service is used. You can register here or on the web.config in the section Unity. 
    container.RegisterInstance(DataServiceProvider.DataService);

    try
    {
        var edmModel = config.CreateDataObjectEdmModel(new[] {
                    typeof(SomeApplicationDataObject).Assembly.FullName,
                    // Assembly types to write and read logs of the application. 
                    typeof(IIS.Flexberry.Logging.Objects.ApplicationLog).Assembly.FullName,
                    // Assembly types to write and read user settings. 
                    typeof(NewPlatform.Flexberry.FlexberryUserSetting).Assembly.FullName,
                    // TODO: after fix bug in ODataService, because of which all ancestors also need to specify the Assembly is not needed. 
                    typeof(UserSetting).Assembly.FullName });
        Dictionary<string, Type> parametersTypes = new Dictionary<string, Type>
        {
            {
                "topicId", typeof(string)
            }
        };
        edmModel.RegisterODataUserFunction("GetLastRoundIdForTopic", GetLastRoundIdForTopic, typeof(string), parametersTypes);
    }
    catch (Exception ex)
    {
        LogService.LogError("RunApp odata service error.", ex);
        throw;
    }
}

/// <summary> 
/// The function to call <see cref="ApplicationLogicBS.GetLastRoundIdForTopic"/>. 
/// http://localhost:6500/odata/GetLastRoundIdForTopic(topicId='E3D832DF-6C36-41A6-A548-451F1BED74D9') 
/// </summary> 
/// <param name="queryParameters"></param> 
/// <param name="parameters"></param> 
/// <returns></returns> 
private static object GetLastRoundIdForTopic(ODataFunctions.QueryParameters queryParameters, Dictionary<string, object> parameters)
{
    ApplicationLogicBS bs = new ApplicationLogicBS { DataService = DataServiceProvider.DataService };
    return bs.GetLastRoundIdForTopic((string)parameters["topicId"]);
}
```

## Filtering of properties for the type when they register the metadata in the ODataService

An example of filter properties for types

```csharp
/// <summary> 
/// Register Data objects. 
/// </summary> 
/// <param name="config">the Http configuration.</param> 
public static void Register(HttpConfiguration config)
{
            var cors = new EnableCorsAttribute("http://localhost:4210,https://flexberry-ember-security-dev.firebaseapp.com", "*", "*") { SupportsCredentials = true };
            config.EnableCors(cors);

            config.DependencyResolver = new UnityDependencyResolver(container);

            var assemblies = new[]
            {
                typeof(Suggestion).Assembly,
                typeof(ApplicationLog).Assembly,
                typeof(UserSetting).Assembly,
                typeof(FlexberryUserSetting).Assembly,
                typeof(Agent).Assembly,
                typeof(AuditEntity).Assembly,
                typeof(Lock).Assembly
            };
            var modelBuilder = new DefaultDataObjectEdmModelBuilder(assemblies);
            modelBuilder.PropertyFilter = PropertyFilter;
            var token = config.MapODataServiceDataObjectRoute(modelBuilder);
            config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
    }
    catch (Exception ex)
    {
        LogService.LogError("RunApp odata service error.", ex);
        throw;
    }
}

/// <summary> 
/// A function that filters a property for the type. In this case, the registration will be excluded property Agent.Pwd. 
/// This function is called when registering the properties in the metadata. 
/// </summary> 
/// <param name="propertyInfo">the Property type for which you want to filter.</param> 
/// <returns>returns true If the property will be registered, otherwise no.</returns> 
private static bool PropertyFilter(PropertyInfo propertyInfo)
{
    return Information.ExtractPropertyInfo<Agent>(x => x.Pwd) != propertyInfo;
}
```

## Filtering of the result set in ODataService using callback functions

An example of filtering of the result set

```csharp
/// <summary> 
/// Register Data objects. 
/// </summary> 
/// <param name="config">the Http configuration.</param> 
public static void Register(HttpConfiguration config)
{
    var cors = new EnableCorsAttribute("http://localhost:4210,https://flexberry-ember-security-dev.firebaseapp.com", "*", "*") { SupportsCredentials = true };
    config.EnableCors(cors);

    config.DependencyResolver = new UnityDependencyResolver(container);

    var assemblies = new[]
    {
        typeof(Suggestion).Assembly,
        typeof(ApplicationLog).Assembly,
        typeof(UserSetting).Assembly,
        typeof(FlexberryUserSetting).Assembly,
        typeof(Agent).Assembly,
        typeof(AuditEntity).Assembly,
        typeof(Lock).Assembly
    };
    var modelBuilder = new DefaultDataObjectEdmModelBuilder(assemblies);
    modelBuilder.PropertyFilter = PropertyFilter;
    var token = config.MapODataServiceDataObjectRoute(modelBuilder);
    token.Events.CallbackBeforeGet = BeforeGet;
    token.Events.CallbackAfterGet = AfterGet;
    config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
    }
    catch (Exception ex)
    {
        LogService.LogError("RunApp odata service error.", ex);
        throw;
    }
}


/// <summary> 
/// Method called before loading the objects. It produces additional configuration of lcs. 
/// </summary> 
/// <param name="lcs">Lcs formed from the query string ODataService.</param> 
/// <returns>Returns true if need to query a ORM using the lcs.</returns> 
public static bool BeforeGet(ref LoadingCustomizationStruct lcs)
{
    return true;
} 

/// <summary> 
/// Method called after subtraction of objects. There is additional processing of the returned result. 
/// </summary> 
/// <param name="objs"></param> 
public static void AfterGet(ref DataObject[] objs)
{
}
```

## Exception handling in ODataService using a callback function

An example of exception handling:

```csharp
/// <summary> 
/// Register Data objects. 
/// </summary> 
/// <param name="config">the Http configuration.</param> 
public static void Register(HttpConfiguration config)
{
    var cors = new EnableCorsAttribute("http://localhost:4210,https://flexberry-ember-security-dev.firebaseapp.com", "*", "*") { SupportsCredentials = true };
    config.EnableCors(cors);

    config.DependencyResolver = new UnityDependencyResolver(container);

    var assemblies = new[]
    {
        typeof(Suggestion).Assembly,
        typeof(ApplicationLog).Assembly,
        typeof(UserSetting).Assembly,
        typeof(FlexberryUserSetting).Assembly,
        typeof(Agent).Assembly,
        typeof(AuditEntity).Assembly,
        typeof(Lock).Assembly
    };
    var modelBuilder = new DefaultDataObjectEdmModelBuilder(assemblies);
    var token = config.MapODataServiceDataObjectRoute(modelBuilder);
    token.Events.CallbackAfterInternalServerError = AfterInternalServerError;
    config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
   }
    catch (Exception ex)
    {
        LogService.LogError("RunApp odata service error.", ex);
       throw;
    }
}

/// <summary> 
/// Method called when the exception occurs. 
/// </summary> 
/// <param name="e">the Exception that occurred inside the ODataService.</param> 
/// <param name="code">the HTTP Return code. In umolchaniyu 500.</param> 
/// <returns>the Exception that is sent to the client.</returns> 
public static Exception AfterInternalServerError(Exception e, ref HttpStatusCode code)
{
    code = HttpStatusCode.InternalServerError;
    return e;
}
```

## Filtering in custom OData functions

Example usage in a custom OData functions, structure LCS, which is created from OData query string:

```csharp
/// <summary> 
/// Configures Web API. 
/// </summary> 
/// <param name="config">Current configuration.</param> 
/// <param name="container">DI container for WebAPI.</param> 
/// <param name="activator">activator for WebAPI Controller.</param> 
public static void Register(HttpConfiguration config, IUnityContainer container, IHttpControllerActivator activator)
{
    var cors = new EnableCorsAttribute("*", "*", "*");
    config.EnableCors(cors);

    // Use Unity for DI in WebAPI. 
    config.DependencyResolver = new UnityDependencyResolver(container);

    var assemblies = new[]
    {
        typeof(Suggestion).Assembly,
        typeof(ApplicationLog).Assembly,
        typeof(UserSetting).Assembly,
        typeof(FlexberryUserSetting).Assembly,
        typeof(Lock).Assembly
    };
    var builder = new DefaultDataObjectEdmModelBuilder(assemblies);

    ManagementToken odataServiceManagementToken = config.MapODataServiceDataObjectRoute(builder);
    config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
    odataServiceManagementToken.Functions.Register(new Func<QueryParameters, string, IEnumerable<DataObject>>(FunctionWithLcs1));
    odataServiceManagementToken.Functions.Register(new Func<QueryParameters, string, string, int>(FunctionWithLcs2));    
}

/// <summary> 
/// A function that uses LCS, based on the OData request. 
/// http://localhost/odata/FunctionWithLcs1(entitySet='Suggestions')?$filter=Text eq 'txt' 
/// </summary> 
/// <param name="queryParameters"></param> 
/// <param name="parameters"></param> 
/// <returns></returns> 
private static IEnumerable<DataObject> FunctionWithLcs1(QueryParameters queryParameters, string entitySet)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    var type = queryParameters.GetDataObjectType(entitySet);
    var lcs = queryParameters.CreateLcs(type);
    var dobjs = dataService.LoadObjects(lcs);
    return dobjs.AsEnumerable();
}

/// <summary> 
/// Function which uses the LCS, based on the parameter of the function. 
/// http://localhost/odata/FunctionWithLcs2(entitySet='Suggestions',query='$filter=Text eq "txt"') 
/// </summary> 
/// <param name="queryParameters"></param> 
/// <param name="parameters"></param> 
/// <returns></returns> 
private static int FunctionWithLcs2(QueryParameters queryParameters, string entitySet, string query)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    var type = queryParameters.GetDataObjectType(entitySet);
    var uri = $"http://a/b/c?{query}";
    var lcs = queryParameters.CreateLcs(type, uri);
    var dobjs = dataService.LoadObjects(lcs);
    return dobjs.Length;
}
```

## The use of actions

Example of use in action structure LCS, which is created from OData query string.

```csharp
/// <summary> 
/// Configures Web API. 
/// </summary> 
/// <param name="config">Current configuration.</param> 
/// <param name="container">DI container for WebAPI.</param> 
/// <param name="activator">activator for WebAPI Controller.</param> 
public static void Register(HttpConfiguration config, IUnityContainer container, IHttpControllerActivator activator)
{
    var cors = new EnableCorsAttribute("*", "*", "*");
    config.EnableCors(cors);

    // Use Unity for DI in WebAPI. 
    config.DependencyResolver = new UnityDependencyResolver(container);

    var assemblies = new[]
    {
        typeof(Suggestion).Assembly,
        typeof(ApplicationLog).Assembly,
        typeof(UserSetting).Assembly,
        typeof(FlexberryUserSetting).Assembly,
        typeof(Lock).Assembly
    };
    var builder = new DefaultDataObjectEdmModelBuilder(assemblies);

    ManagementToken odataServiceManagementToken = config.MapODataServiceDataObjectRoute(builder);
    config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
    odataServiceManagementToken.Functions.RegisterAction(new Func<QueryParameters, string, string, IEnumerable<DataObject>>(ActionWithLcs));
}

/// <summary> 
/// Action that uses the LCS, based on the action parameter. 
/// http://localhost/odata/ActionWithLcs 
/// Json in the body of a POST request: {"entitySet": "Suggestions", "query": "$filter=Text eq 'txt'"} 
/// </summary> 
/// <param name="queryParameters"></param> 
/// <param name="parameters"></param> 
/// <returns></returns> 
private static IEnumerable<DataObject> ActionWithLcs(QueryParameters queryParameters, string entitySet, string query)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    var type = queryParameters.GetDataObjectType(entitySet);
    var uri = $"http://a/b/c?{query}";
    var lcs = queryParameters.CreateLcs(type, uri);
    var dobjs = dataService.LoadObjects(lcs);
    return dobjs.AsEnumerable();
    }
}
```

## Using custom functions for exporting to Excel

Example of using custom functions for export to Excel.
Example query:
http://localhost/odata/FunctionExportExcel(entitySet='Strana')?exportExcel=true&colsOrder=Nazvanie/Название&detSeparateCols=false&detSeparateRows=false&$filter=contains(Nazvanie,'1')
.

```csharp
/// <summary> 
/// Configures Web API. 
/// </summary> 
/// <param name="config">Current configuration.</param> 
/// <param name="container">DI container for WebAPI.</param> 
/// <param name="activator">activator for WebAPI Controller.</param> 
public static void Register(HttpConfiguration config, IUnityContainer container, IHttpControllerActivator activator)
{
    var cors = new EnableCorsAttribute("*", "*", "*");
    config.EnableCors(cors);

    // Use Unity for DI in WebAPI. 
    config.DependencyResolver = new UnityDependencyResolver(container);

    var assemblies = new[]
    {
        typeof(Suggestion).Assembly,
        typeof(ApplicationLog).Assembly,
        typeof(UserSetting).Assembly,
        typeof(FlexberryUserSetting).Assembly,
        typeof(Lock).Assembly
    };
    var builder = new DefaultDataObjectEdmModelBuilder(assemblies);

    ManagementToken odataServiceManagementToken = config.MapODataServiceDataObjectRoute(builder);
    config.MapODataServiceFileRoute("File", "api/File", HttpContext.Current.Server.MapPath("~/Uploads"), container.Resolve<IDataService>());
    odataServiceManagementToken.Functions.Register(new Func<QueryParameters, string, Страна[]>(FunctionExportExcel));
}

/// <summary> 
/// The function prepares the data for export to Excel. For proper operation it is necessary that the Declaration specified the actual type of the return value. 
/// Not suitable specify the type DataObject. 
/// </summary> 
/// <param name="queryParameters"></param> 
/// <param name="entitySet"></param> 
/// <returns></returns> 
private static Страна[] FunctionExportExcel(QueryParameters queryParameters, string entitySet)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    Type type = queryParameters.GetDataObjectType(entitySet);
    LoadingCustomizationStruct lcs = queryParameters.CreateLcs(type);
    Страна[] dobjs = dataService.LoadObjects(lcs).Cast<Страна>().ToArray();
    return dobjs;
}
```

## An example of a restriction on pseudometal in ODataService

`Flexberry ORM` allows you to build [restrictions on "Association in the opposite direction" or "pseudometal"](fo_psedodetails-linq-provider.html). This option is available when working with data using ODataService.
In order to have the opportunity to build the restriction of a similar kind, it is necessary to declare its validity at the level of metadata in the entity data model of OData.
Consider this scheme as an example:
![schema](/images/pages/products/flexberry-orm/query language/pseudo-details.png)
Example of registration of pseudometal:

```csharp
public static void Configure(HttpConfiguration config, IUnityContainer container, HttpServer httpServer)
{
    //... 
    var pseudoDetailDefinitions = new PseudoDetailDefinitions();

    pseudoDetailDefinitions.Add(new DefaultPseudoDetailDefinition<Клиент, Кредит>(
        Кредит.Views.PseudoDetailView,
        Information.ExtractPropertyPath<Кредит>(x => x.Клиент),
        "Loans"));

    var builder = new DefaultDataObjectEdmModelBuilder(DataObjectsAssembliesNames, UseNamespaceInEntitySetName, pseudoDetailDefinitions);
    //... 
}
```

This action adds the ability to filter objects by pseudometal similar to [filtering on detalam](efd2_query-language.html#querydetailpredicate). For data modification operations, this connection is not used, as there is no possibility to choose the data at this link.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}