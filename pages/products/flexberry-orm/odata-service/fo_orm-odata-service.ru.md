---
title: Flexberry ORM ODataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM ODataService, OData
summary: Особенности, ограничения, рекомендации по применению ODataService
toc: true
permalink: ru/fo_orm-odata-service.html
lang: ru
---

## Информация о продукте

`Flexberry ORM ODataService` является [продуктом платформы Flexberry](fp_platform-structure.html). Сайт продукта: [http://flexberry.ru](http://flexberry.ru/FlexberryORM).

`Flexberry ORM ODataService` позволяет удобным образом создать OData-сервис.

{% include note.html content="`Flexberry ORM ODataService` доступно для установки в проект через [NuGet-пакет](NewPlatform.Flexberry.ORM.ODataService)." %}

##№ Список библиотек `Flexberry ORM ODataService`

В состав NuGet-пакета `Flexberry ORM ODataService` входят следующие сборки:

* NewPlatform.Flexberry.ORM.ODataService.dll

## Ограничения, особенности, рекомендации к проектированию

Существует ряд особенностей при проектировании объектов, которые будут использоваться через `Flexberry ORM ODataService`:

* В E-представление (представление, имеющее название "<ИмяКласса>E") детейла должна быть добавлена ссылка на агрегатор.
* Подключение `Flexberry ORM ODataService`. Для подключения в web-проект (WebForms) воспользоваться возможностями `Flexberry ORM ODataService`, необходимо сделать следующее:

    * Подключить NuGet-пакет `Flexberry ORM ODataService`.
    * В App_Start приложения создать класс "ODataConfig.cs".
    * Заменить содержимое класса примерно на следующее:

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
        public static void Configure(HttpConfiguration config, IUnityContainer container)
        {
            Contract.Requires<ArgumentNullException>(config != null);
            Contract.Requires<ArgumentNullException>(container != null);

            // Use Unity as WebAPI dependency resolver
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Create EDM model builder
            var assemblies = new[] { Assembly.GetCallingAssembly() };
            var builder = new DefaultDataObjectEdmModelBuilder(assemblies);

            // Map OData Service
            var token = config.MapODataServiceDataObjectRoute(builder);

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

   * В Global.asax добавить:

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

            GlobalConfiguration.Configure(configuration => ODataConfig.Configure(configuration, container));
        }
    }
}
```

   * Для того, чтобы код компилировался, может потребоваться установить дополнительно NuGet-пакеты в приложение: [Microsoft.AspNet.WebApi.Cors](https://www.nuget.org/packages/Microsoft.AspNet.WebApi.Cors) и [microsoft.aspnet.webapi.webhost](https://www.nuget.org/packages/microsoft.aspnet.webapi.webhost/).

   * Добавить в web.config или проверить наличие следующих записей:

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

## Механизм вызова логики после сохранения объекта с использованием callback-функций

```csharp
/// <summary>
/// Register Data objects.
/// </summary>
/// <param name="config">Http configuration.</param>
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
/// Метод вызываемый после создания объекта.
/// </summary>
/// <param name="obj">Объект после создания.</param>
public static void AfterCreate(DataObject obj){

}
/// <summary>
/// Метод вызываемый после обновления объекта.
/// </summary>
/// <param name="obj">Объект после обновления.</param>
public static void AfterUpdate(DataObject obj){

}
/// <summary>
/// Метод вызываемый после удаления объекта.
/// </summary>
/// <param name="obj">Объект перед удалением.</param>
public static void AfterDelete(DataObject obj){

}

```

Метод `AfterCreate` будет вызываться после каждого запроса POST, сделанного клиентом для создания сущности.
Метод `AfterUpdate` будет вызываться после каждого запроса PATCH, сделанного клиентом для обновления сущности.
Метод `AfterDelete` будет вызываться после каждого запроса DELETE, сделанного клиентом для удаления сущности.

## Пользовательские OData-функции

Пример регистрации пользовательской OData-функции

```csharp
/// <summary>
/// Register Data objects.
/// </summary>
/// <param name="config">Http configuration.</param>
public static void Register(HttpConfiguration config)
{
    var cors = new EnableCorsAttribute("*", "*", "*");
    config.EnableCors(cors);

    var container = new UnityContainer().LoadConfiguration();
    // WebApi будет создавать контроллеры, передавая в конструкторы различные параметры, инстанции которых будут запрашиваться у этого самого DependencyResolver.
    config.DependencyResolver = new UnityDependencyResolver(container);

    // Самое главное для ODataService - знать какой сервис данных используется. Можно зарегистрировать тут или в web.config в секции Unity.
    container.RegisterInstance(DataServiceProvider.DataService);

    try
    {
        var edmModel = config.CreateDataObjectEdmModel(new[] {
                    typeof(SomeApplicationDataObject).Assembly.FullName,
                    // Сборка с типами для записи и чтения логов приложения.
                    typeof(IIS.Flexberry.Logging.Objects.ApplicationLog).Assembly.FullName,
                    // Сборка с типами для записи и чтения пользовательских настроек.
                    typeof(NewPlatform.Flexberry.FlexberryUserSetting).Assembly.FullName,
                    // TODO: после исправления бага в ODataService, из-за которого все предки тоже должны указываться эта сборка будет не нужна.
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
/// Функция для вызова <see cref="ApplicationLogicBS.GetLastRoundIdForTopic"/>.
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

## Фильтрация свойств для типов при регистрации метаданных в ODataService

Пример фильтрации свойств для типов

```csharp
/// <summary>
/// Register Data objects.
/// </summary>
/// <param name="config">Http configuration.</param>
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
/// Функция, которая фильтрует свойство для типа. В данном случае из регистрации будет исключено свойство Agent.Pwd.
/// Эта функция вызывается при регистрации свойства в метаданных.
/// </summary>
/// <param name="propertyInfo">Свойство для типа, для которого нужно выполнить фильтрацию.</param>
/// <returns>Если возвращается true, то свойство будет зарегистрировано, иначе не будет.</returns>
private static bool PropertyFilter(PropertyInfo propertyInfo)
{
    return Information.ExtractPropertyInfo<Agent>(x => x.Pwd) != propertyInfo;
}
```

## Фильтрация результата выборки в ODataService с использованием callback-функций

Пример фильтрации результата выборки

```csharp
/// <summary>
/// Register Data objects.
/// </summary>
/// <param name="config">Http configuration.</param>
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
/// Метод вызываемый перед загрузкой объектов. В нем производится дополнительная настройка lcs.
/// </summary>
/// <param name="lcs">Lcs сформированная из строки запроса ODataService.</param>
/// <returns>Возвращает true, если нужно выполнить запрос к ORM с использованием данной lcs.</returns>
public static bool BeforeGet(ref LoadingCustomizationStruct lcs)
{
    return true;
} 

/// <summary>
/// Метод вызываемый после вычитывания объектов. В нем производится дополнительная обработка возвращаемого результата.
/// </summary>
/// <param name="objs"></param>
public static void AfterGet(ref DataObject[] objs)
{
}
```

## Обработка исключения в ODataService с использованием callback-функции

Пример обработки исключения:

```csharp
/// <summary>
/// Register Data objects.
/// </summary>
/// <param name="config">Http configuration.</param>
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
/// Метод вызываемый после возникновения исключения.
/// </summary>
/// <param name="e">Исключение, которое возникло внутри ODataService.</param>
/// <param name="code">Возвращаемый код HTTP. По-умолчанияю 500.</param>
/// <returns>Исключение, которое будет отправлено клиенту.</returns>
public static Exception AfterInternalServerError(Exception e, ref HttpStatusCode code)
{
    code = HttpStatusCode.InternalServerError;
    return e;
}
```

## Фильтрация в пользовательских OData-функциях

Пример использования в пользовательских OData-функциях структуры LCS, которая создана из строки запроса OData:

```csharp
/// <summary>
/// Configures Web API.
/// </summary>
/// <param name="config">Current configuration.</param>
/// <param name="container">DI container for WebAPI.</param>
/// <param name="activator">Controller activator for WebAPI.</param>
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
    Dictionary<string, Type> parametersTypes1 = new Dictionary<string, Type> { { "entitySet", typeof(string) } };
    odataServiceManagementToken.Functions.Register(new Function("FunctionWithLcs1", FunctionWithLcs1, typeof(IEnumerable<DataObject>), parametersTypes1));
    Dictionary<string, Type> parametersTypes2 = new Dictionary<string, Type> { { "entitySet", typeof(string) }, { "query", typeof(string) } };
    odataServiceManagementToken.Functions.Register(new Function("FunctionWithLcs2", FunctionWithLcs2, typeof(int), parametersTypes2));
}

/// <summary>
/// Функция которая используют LCS, созданный на основе запроса OData.
/// http://localhost/odata/FunctionWithLcs1(entitySet='Suggestions')?$filter=Text eq 'txt'
/// </summary>
/// <param name="queryParameters"></param>
/// <param name="parameters"></param>
/// <returns></returns>
private static object FunctionWithLcs1(QueryParameters queryParameters, IDictionary<string, object> parameters)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    var type = queryParameters.GetDataObjectType(parameters["entitySet"] as string);
    var lcs = queryParameters.CreateLcs(type);
    var dobjs = dataService.LoadObjects(lcs);
    return dobjs.AsEnumerable();
}

/// <summary>
/// Функция которая использует LCS, созданный на основе параметра функции.
/// http://localhost/odata/FunctionWithLcs2(entitySet='Suggestions',query='$filter=Text eq ''txt''')
/// </summary>
/// <param name="queryParameters"></param>
/// <param name="parameters"></param>
/// <returns></returns>
private static object FunctionWithLcs2(QueryParameters queryParameters, IDictionary<string, object> parameters)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    var type = queryParameters.GetDataObjectType(parameters["entitySet"] as string);
    var uri = $"http://a/b/c?{parameters["query"]}";
    var lcs = queryParameters.CreateLcs(type, uri);
    var dobjs = dataService.LoadObjects(lcs);
    return dobjs.Length;
}
```

## Использование actions

Пример использования в action структуры LCS, которая создана из строки запроса OData.

```csharp
/// <summary>
/// Configures Web API.
/// </summary>
/// <param name="config">Current configuration.</param>
/// <param name="container">DI container for WebAPI.</param>
/// <param name="activator">Controller activator for WebAPI.</param>
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
    Dictionary<string, Type> parametersTypes = new Dictionary<string, Type> { { "entitySet", typeof(string) }, { "query", typeof(string) } };
    odataServiceManagementToken.Functions.Register(new NewPlatform.Flexberry.ORM.ODataService.Functions.Action("ActionWithLcs", ActionWithLcs, typeof(IEnumerable<DataObject>), parametersTypes));
}

/// <summary>
/// Action, который использует LCS, созданный на основе параметра action.
/// http://localhost/odata/ActionWithLcs
/// Json в теле POST-запроса: {"entitySet": "Suggestions", "query": "$filter=Text eq 'txt'"}
/// </summary>
/// <param name="queryParameters"></param>
/// <param name="parameters"></param>
/// <returns></returns>
private static object ActionWithLcs(QueryParameters queryParameters, IDictionary<string, object> parameters)
{
    SQLDataService dataService = DataServiceProvider.DataService as SQLDataService;
    var type = queryParameters.GetDataObjectType(parameters["entitySet"] as string);
    var uri = $"http://a/b/c?{parameters["query"]}";
    var lcs = queryParameters.CreateLcs(type, uri);
    var dobjs = dataService.LoadObjects(lcs);
    return dobjs.AsEnumerable();
    }
}
```
