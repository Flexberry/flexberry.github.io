---
title: .NET Кроссплатформенная разработка
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_dotnet_crossplatform.html
lang: ru
---

## Формат csproj-файла
Самым простым способом компиляции исходного кода в несколько фреймворков, например, `.NET Framework 4.0`, `.NET Standard 2.0` или `.NET 5`, является семейство форматов csproj [Microsoft.NET.Sdk](https://docs.microsoft.com/en-us/dotnet/core/project-sdk/overview). С полным списком целевых фреймворков можно ознакомиться [здесь](https://docs.microsoft.com/en-us/dotnet/standard/frameworks#supported-target-frameworks).

### Целевые платформы
Каждой целевой платформе соответствует моникер (TFM, стандартизированный маркер), например, платформе `.NET Framework 4.0` соответствует `net40`.

В классическом варианте в csproj-файле указывается одна целевая платформа с помощью свойства `TargetFramework`:
```xml
  <PropertyGroup>
    <TargetFramework>net40</TargetFramework>
  </PropertyGroup>
```

Для указания нескольких платформ необходимо использовать свойство `TargetFrameworks`:
```xml
  <PropertyGroup>
    <TargetFrameworks>net40;netcoreapp3.1;netstandard2.0</TargetFrameworks>
  </PropertyGroup>
```

### .NET Standard
`.NET Standard` расположен в несколько иной плоскости относительно `.NET Framework` и `.NET Core`. `.NET Standard` определяет набор спецификаций для `.NET API`, который может быть реализован в различных фреймворках. Подробнее об поддержке версий `.NET Standard` можно почитать [здесь](https://docs.microsoft.com/en-us/dotnet/standard/net-standard#net-implementation-support).

`net461` и `netcoreapp3.1` не только реализуют контракты api `netstandard2.0`, но и содержат функционал выходящий за него. Таким образом, если некий сторонний пакет собирается под три целевые платформы `net461;netcoreapp3.1;netstandard2.0`, то по умолчанию будет выбрана максимально совместимая сборка.

### Директивы препроцессора
 Для разделения кода между платформами рекомендуется использовать вшитые в формат `Microsoft.NET.Sdk` константы, [подробнее](https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/preprocessor-directives/preprocessor-if). Имеются константы определенные для все семейство платформ, например, `NETFRAMEWORK` для `.NET Framework`, так и для конкретной версии `NETCOREAPP3_1` для `netcoreapp3.1` соответственно.

### Условия
Часто требуется указывать дополнительные правила, по которым осуществляется сборка для конкретной платформы или группы платформ. К таких правилам, например, относятся ссылки и пакетные зависимости, которые подключают функциональные модули реализованные отличными способами для разных платформ.

Для подключения сборки `System.Configuration` для семейства платформ `.NET Framework` используется ссылка:
```xml
  <ItemGroup Condition=" $(DefineConstants.Contains(NETFRAMEWORK)) ">
    <Reference Include="System.Configuration" />
  </ItemGroup>
```
Для `.NET Standard` или `.NET Core`:
```xml
  <ItemGroup Condition=" '$(TargetFramework)' == 'netstandard2.0' Or '$(TargetFramework)' == 'netcoreapp3.1' ">
    <PackageReference Include="System.Configuration.ConfigurationManager" Version="4.5.0" />
  </ItemGroup>
```

### Про пакеты, сборки и .NET Framework
При переходе с классического формата csproj на `Microsoft.NET.Sdk` для `.NET Framework` стоит отметить, что может происходить неявное добавление ссылок на библиотеки платформы, например, `System.Data.DataSetExtensions`.

Таким образом следующие две конфигурации эквиваленты:
```xml
  <ItemGroup Condition=" '$(TargetFramework)' == 'net45' ">
    <Reference Include="System.Data.DataSetExtensions" />
  </ItemGroup>

  <ItemGroup Condition=" '$(TargetFramework)' == 'netstandard2.0' ">
    <PackageReference Include="System.Data.DataSetExtensions" Version="4.5.0" />
  </ItemGroup>
```

```xml
  <ItemGroup>
    <PackageReference Include="System.Data.DataSetExtensions" Version="4.5.0" />
  </ItemGroup>
```

Не только по причине того, что ссылка на библиотеку добавляется автоматически, но и из-за того, что пакет [System.Data.DataSetExtensions](https://www.nuget.org/packages/System.Data.DataSetExtensions/4.5.0) добавляет соответствующую библиотеку, как собственную зависимость в древо для `.NETFramework4.5 `:
```xml
    <frameworkAssemblies>
      <frameworkAssembly assemblyName="System.Data.DataSetExtensions" targetFramework=".NETFramework4.5" />
    </frameworkAssemblies>
```

### Исключение файлов
[Не рекомендуется использовать] 
```xml
  <ItemGroup Condition=" !$(DefineConstants.Contains(NETFRAMEWORK)) ">
    <Compile Remove="Helpers\HttpRequestHelper.cs" />
  </ItemGroup>
```

## Условная компиляция
 При поставке некого пакета\сборки, который должен реализовывать функциональную возможность под несовместимые платформы, например, глобальная обработка исключений в веб платформе (передача клиенту сообщения сформированного по определенным правилам и с определенной структурой):
 * `.NET Framework` - реализация интерфейса `IExceptionHandler`
 * `.NET Core` - реализация middleware аналогичной [ExceptionHandlerMiddleware](https://github.com/dotnet/aspnetcore/blob/master/src/Middleware/Diagnostics/src/ExceptionHandler/ExceptionHandlerMiddleware.cs)

 Либо webapi-контроллер: 
 * `.NET Framework` - класс наследуется от `System.Web.Http.ApiController`, ответы - `System.Web.Http.IHttpActionResult`
 * `.NET Core` - класс наследуется от `Microsoft.AspNetCore.Mvc.ControllerBase`, ответы - `Microsoft.AspNetCore.Mvc.IActionResult`

### Разделение по блокам
Самый распространенный способ, позволяет легко обходить незначительные отличия в api разных платформ.
```csharp
...
            foreach (var childItem in resourceInfoWrapper.NestedItems)
            {
#if NETFRAMEWORK
                IDictionary<string, object> props = readContext.Request.Properties;
#else
                IDictionary<object, object> props = readContext.Request.HttpContext.Items;
#endif
                if (!props.ContainsKey(Dictionary))
                {
                    props.Add(Dictionary, new Dictionary<string, object>());
                }

                var dictionary = (Dictionary<string, object>)props[Dictionary];
...
```
Данный код собирается под целевые платформы `net45;net461;netcoreapp3.1;netstandard2.0`.

### Разделение по методам
Особо актуален для конструкторов и методов, которые сильно отличаются по сигнатуре и\или телу метода.
```csharp
#if NETFRAMEWORK

        private readonly bool isSyncMode;
        
        private readonly IDataService dataService;
        
        public DataObjectODataBatchHandler(IDataService dataService, HttpServer httpServer, bool? isSyncMode = null)
            : base(httpServer)
        {
            this.dataService = dataService;
            this.isSyncMode = isSyncMode ?? Type.GetType("Mono.Runtime") != null;
        }
#else
        public DataObjectODataBatchHandler()
            : base()
        {
        }
#endif
```
Данный код собирается под целевые платформы `net45;net461;netcoreapp3.1;netstandard2.0`.

### Псевдонимы
В некоторых случаях вместо многократного разделения по блокам проще переопределить используемые имена через `alias`-ы:
```csharp
#if NETFRAMEWORK
    using DefaultAssembliesResolver = System.Web.Http.Dispatcher.DefaultAssembliesResolver;
    using IAssembliesResolver = System.Web.Http.Dispatcher.IAssembliesResolver;
#else
    using DefaultAssembliesResolver = Microsoft.AspNet.OData.Adapters.WebApiAssembliesResolver;
    using IAssembliesResolver = Microsoft.AspNet.OData.Interfaces.IWebApiAssembliesResolver;
#endif
...
    public partial class DataObjectController : ODataController
    {
        private static readonly IAssembliesResolver _defaultAssembliesResolver = new DefaultAssembliesResolver();
```
Данный код собирается под целевые платформы `net45;net461;netcoreapp3.1;netstandard2.0`.

### Разделение файлами
Для случаев, когда api совершенно не совместим (пример об глобальной обработке исключенийиз родительского раздела), следует выделять все содержимое файла в блок условной компиляции:
```csharp
#if NETFRAMEWORK
namespace Iis.Eais.Common.Web.Http
{
...
    /// <summary>
    /// Глобальный обработчик исключений.
    /// </summary>
    public class GlobalExceptionHandler : ExceptionHandler
    {
        /// <inheritdoc />
        public override bool ShouldHandle(ExceptionHandlerContext context)
        {
            return true;
        }

        /// <inheritdoc />
        public override void Handle(ExceptionHandlerContext context)
        {
...
        }
    }
}
#endif
```

```csharp
#if NETSTANDARD
namespace Iis.Eais.Common.Web.Extensions
{
...
    /// <summary>
    /// Extension methods for enabling <see cref="ExceptionHandlerMiddleware" />.
    /// </summary>
    public static class ExceptionHandlerExtensions
    {
        /// <summary>
        /// Adds a middleware to the pipeline that will catch exceptions, log them, and re-execute the request in an alternate pipeline.
        /// The request will not be re-executed if the response has already started.
        /// </summary>
        /// <param name="app">The <see cref="IApplicationBuilder"/>.</param>
        /// <returns>A reference to the <paramref name="app"/> after the operation has completed.</returns>
        public static IApplicationBuilder UseCustomExceptionHandler(this IApplicationBuilder app)
        {
            return app.UseExceptionHandler(
                errorApp =>
                {
                    errorApp.Run(
                        context =>
                        {
...
                        });
                });
        }
    }
}
#endif
```
Данный код собирается под целевые платформы `net45;netstandard2.0`.

## Перейти

* [Главная страница курса](gbt_landing-page.html)
