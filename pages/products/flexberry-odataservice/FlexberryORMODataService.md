---
title: Flexberry ORM ODataService
sidebar: product--sidebar
keywords: Flexberry ORM ODataService, OData
toc: true
permalink: ru/flexberry-o-r-m-o-data-service.html
folder: product--folder
lang: ru
---

## Информация о продукте

`Flexberry ORM ODataService` является [продуктом платформы Flexberry](fp_platform-structure.html). Сайт продукта: [http://flexberry.ru](http://flexberry.ru/FlexberryORM).

(((
<msg type=information>`Flexberry ORM ODataService` доступно для установки в проект через [NuGet-пакет](NewPlatform.Flexberry.ORM.ODataService).</msg>
)))

## Список библиотек `Flexberry ORM ODataService`

В состав NuGet-пакета `Flexberry ORM ODataService` входят следующие сборки:
* NewPlatform.Flexberry.ORM.ODataService.dll

## Описание продукта

Данный продукт позволяет удобным образом создать OData-сервис.

## Ограничения, особенности, рекомендации к проектированию

Существует ряд особенностей при проектировании объектов, которые будут использоваться через `Flexberry ORM ODataService`:
- В E-представление (представление, имеющее название "<ИмяКласса>E") детейла должна быть добавлена ссылка на агрегатор.

- Подключение `Flexberry ORM ODataService`
Чтобы в web-проекте (WebForms) воспользоваться возможностями `Flexberry ORM ODataService`, необходимо сделать следующее:

1. Подключить NuGet-пакет `Flexberry ORM ODataService`.

2. В App_Start приложения создать класс "ODataConfig.cs".

3. Заменить содержимое класса примерно на следующее:

```cs
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

4. В Global.asax добавить:

```cs
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

5. Для того, чтобы код компилировался, может потребоваться установить дополнительно NuGet-пакеты в приложение: [Microsoft.AspNet.WebApi.Cors](https://www.nuget.org/packages/Microsoft.AspNet.WebApi.Cors) и [microsoft.aspnet.webapi.webhost](https://www.nuget.org/packages/microsoft.aspnet.webapi.webhost/).

6. Добавить в web.config или проверить наличие следующих записей:

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

## Механизм вызова логики после сохранения объекта

Заменить содержимое класса "WebApiConfig.cs" примерно на следующее:

```cs
namespace SomeApp.App_Start
{
    using System;
    using System.Web.Http;
    using System.Web.Http.Cors;

    using ICSSoft.STORMNET;
    using NewPlatform.Flexberry.ORM.ODataService.Extensions;

    /// <summary>
    /// Класс конфигурации сервисов приложения.
    /// </summary>
    public class WebApiConfig : System.Web.HttpApplication
    {
        /// <summary>
        /// Метод для конфигурации всех сервисов приложения.
        /// </summary>
        public static void Register(HttpConfiguration config)
        {
            // TODO: настроить Cross Origin Requests.
            var cors = new EnableCorsAttribute("*", "*", "*");
            config.EnableCors(cors);

            var container = new UnityContainer().LoadConfiguration();
            // WebApi будет создавать контроллеры, передавая в конструкторы различные параметры, инстанции которых будут запрашиваться у этого самого DependencyResolver.
            config.DependencyResolver = new UnityDependencyResolver(container);

            // Самое главное для ODataService - знать какой сервис данных используется. Можно зарегистрировать тут или в web.config в секции Unity.
            container.RegisterInstance(DataServiceProvider.DataService);

            try
            {
                var dataObjectEdmModel = config.CreateDataObjectEdmModel(new[] {
                    typeof(SomeApplicationDataObject).Assembly.FullName,
                    // Сборка с типами для записи и чтения логов приложения.
                    typeof(IIS.Flexberry.Logging.Objects.ApplicationLog).Assembly.FullName,
                    // Сборка с типами для записи и чтения пользовательских настроек.
                    typeof(NewPlatform.Flexberry.FlexberryUserSetting).Assembly.FullName,
                    // TODO: после исправления бага в ODataService, из-за которого все предки тоже должны указываться эта сборка будет не нужна.
                    typeof(UserSetting).Assembly.FullName });

                dataObjectEdmModel.CallbackAfterCreate = AfterCreate;
                dataObjectEdmModel.CallbackAfterUpdate = AfterUpdate;
                dataObjectEdmModel.CallbackAfterDelete = AfterDelete;

            }
            catch (Exception ex)
            {
                // TODO: правильно внести информацию в лог приложения.
                LogService.LogError("RunApp odata service error.", ex);
                throw;
            }
        }
        /// <summary>
        /// Метод вызываемый после создания объекта.
        /// </summary>
        /// <param name="obj">Объект после создания.</param>
        public void AfterCreate(DataObject obj){

        }
        /// <summary>
        /// Метод вызываемый после обновления объекта.
        /// </summary>
        /// <param name="obj">Объект после обновления.</param>
        public void AfterUpdate(DataObject obj){

        }
        /// <summary>
        /// Метод вызываемый после удаления объекта.
        /// </summary>
        /// <param name="obj">Объект перед удалением.</param>
        public void AfterDelete(DataObject obj){

        }

    }
}
```

Метод `AfterCreate` будет вызываться после каждого запроса POST, сделанного клиентом для создания сущности.
Метод `AfterUpdate` будет вызываться после каждого запроса PATCH, сделанного клиентом для обновления сущности.
Метод `AfterDelete` будет вызываться после каждого запроса DELETE, сделанного клиентом для удаления сущности.


## Пользовательские OData-функции

Пример регистрации пользовательской OData-функции

```cs
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

```cs
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
