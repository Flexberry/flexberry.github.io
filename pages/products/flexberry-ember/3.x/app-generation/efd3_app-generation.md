---
title: Генерация приложений
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_app-generation.html
lang: ru
summary: Настройка и генерация метаданных приложения, генерация разными способами фронтенда и бакэнда.
---

## Подготовка к генерации

Для того, чтобы сгенерировать приложение Flexberry Ember, необходимо во [Flexberry Designer](fd_flexberry-designer.html) представить предметную область в виде [диаграммы классов](gpg_class-diagram.html).

{% include note.html content="[Построение других UML-диаграмм](gpg_practical-guides-uml.html) также необходимо для глубокого анализа и проработки предметной области, однако непосредственно генерация производится по [диаграмме классов](gpg_class-diagram.html)." %}

{% include important.html content="При разработке приложений на Flexberry Ember [рекомендовано использовать английский язык](gpg_setting-language-and-structure.html) для именования тех элементов модели, с которыми работает разработчик." %}

В [диаграмме классов](gpg_class-diagram.html) должны быть [сгенерированы](gpg_create-and-configure-application-structure.html) или созданы вручную [представления и формы](gpg_create-and-configure-application-structure.html), [настроены контейнеры](gpg_create-and-configure-application-structure.html) (впоследствии на основании настроек контейнеров в [контроллер](https://guides.emberjs.com/v3.1.0/controllers/) "application" [после генерации будет добавлена структура](efd3_generated-app-structure.html), соответствующая сайтмапу приложения Flexberry Ember).

## Плагин генерации для Flexberry Ember во Flexberry Designer

Для корректной работы плагина генерации для Flexberry Ember во Flexberry Designer требуется проверить, что на компьютере установлено [необходимое программное обеспечение](gpg_ember-application-generation.html).

Далее наобходимо [установить плагин](fd_flexberry-plugins.html) Flexberry Ember CasePlugin в папку [Flexberry Designer](fd_flexberry-designer.html) (или удостовериться, что он уже установлен).

В конфигурационном файле Flexberry Designer нужно добавить настройку, которая отвечает за версию устанавливаемого ember-аддона:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  ...
  <appSettings>
    ...
    <add key="EmberPluginAddonName" value="ember-flexberry@3.5.0"/>
  </appSettings>
  ...
</configuration>
```

Или при желании можно указать прямо ссылку на [git-репозиторий](https://github.com/Flexberry/ember-flexberry):

```xml
<add key="EmberPluginAddonName" value="https://github.com/Flexberry/ember-flexberry.git#develop" />
```

Вызов плагина происходит из стадии во [Flexberry Designer](fd_flexberry-designer.html), где после клика правой кнопкой мыши на стадии в контекстном меню выбираем пункт "Ember".

### Меню плагина генерации

Плагин генерации для Flexberry Ember во Flexberry Designer предоставляет следующие возможности посредством меню:

![Меню плагина генерации для Flexberry Ember во Flexberry Designer](/images/pages/products/flexberry-ember/ember-flexberry/generation/FE-plugin.png)

* `Свойства модели` - основные настройки генерации: расположение генерируемого кода, название продукта, общепроектные настройки, [карта типов](fd_types-map.html).

В процессе генерации типы .NET из карты типов будут автоматически заменены на соответствующие типы Javascript согласно этой таблицы:

| Типы .NET | Типы Javascript
|-----------|----------------|
| bool, bool? | boolean |
| decimal, decimal?, double, double?, short, short?, int, int?, long, long?, float, float?, ushort, ushort?, uint, uint?, ulong, ulong?, NullableInt, NullableDecimal | number |
| DateTime, DateTime?, NullableDateTime | date |
| Все остальные типы | number |

* `Создать представления, формы и приложение` - создание быстрого прототипа на базе реализованной [диаграммы классов](gpg_class-diagram.html) (часть созданных элементов может потребоваться [донастроить вручную](gpg_create-and-configure-application-structure.html)).

* `Frontend` - подменю, связанное с генерацией фронтенда.
  * `EmberJS` - генерация фронтенда на EmberJS
    * `Генерировать всё` - генерация
    * `Генерировать метаданные` - генерация метаданных, об этом ниже.
    * `Компилировать` - сборка сгенерированного приложения (в общем случае это приводит к запуску команды [Ember CLI](https://cli.emberjs.com/release/) [`ember build -e development`](https://cli.emberjs.com/release/basic-use/cli-commands/)).
    * `Запустить сервер` - запуск сгенерированного приложения (в общем случае это приводит к запуску команды [Ember CLI](https://cli.emberjs.com/release/) [`ember serve`](https://cli.emberjs.com/release/basic-use/cli-commands/)).
    * `Открыть каталог` - открытие папки со сгенерированным кодом.
  * `Apache Cordova` - генерация [фронтенда на Apache Cordova]()
    * `Генерировать проект Apache Cordova`
    * `Собрать мобильное приложение`

* `Backend`  - подменю, связанное с генерацией бакенда.
  * `ASP.NET` - генерация бакенда для [ASP.NET](https://dotnet.microsoft.com/apps/aspnet).
    * `Генерировать`
    * `Открыть каталог`
    * `Открыть в Visual Studio...`
  * `ASP.NET Core 3.1` - генерация бакенда для [ASP.NET Core 3.1](https://dotnet.microsoft.com/learn/aspnet/what-is-aspnet-core).
    * `Генерировать`
    * `Открыть каталог`
    * `Открыть в Visual Studio...`
  * `JAVA` - в настоящий момент данный пункт меню находится в разработке.
  * `PHP` - в настоящий момент данный пункт меню находится в разработке.

* `Docker` - работа с [Docker](gbt_deployment_docker.html).
  * `Создать Dockerfile`
  * `Создать Dockerfile для автоматической сборки`
  * `Создать скрипт сборки образов`
  * `Создать Swarn-конфигурацию и скрипт запуска Swarn-конфигурации`
  * `Запустить Swarn-конфигурацию`

* `Storage` - подменю, связанное с генерацией скриптов для приложения для разных СУБД (пункты меню у разных СУБД аналогичны, поэтому указаны только для Postgre SQL).
  * `Microsoft SQL server` - настройки и генерация скриптов для [Microsoft SQL server](https://ru.wikipedia.org/wiki/Microsoft_SQL_Server).
  * `Postgre SQL` - настройки и генерация скриптов для [Postgre SQL](https://www.postgresql.org/).
    * `Настройка БД`
    * `Привести БД в соответствие с моделью`
    * `Сгенерировать SQL`
    * `Полномочия`
      * `Создать полномочия`
      * `Создать пользователя по умолчанию`
  * `ClickHouse` - настройки и генерация скриптов для [ClickHouse](https://clickhouse.tech/docs/ru/).
  * `Oracle` - настройки и генерация скриптов для [Oracle](https://www.oracle.com/ru/index.html).
  * `Microsoft Access` - настройки и генерация БД для [Microsoft Access](https://ru.wikipedia.org/wiki/Microsoft_Access).

* `Утилиты` - [вспомогательные утилиты](fo_orm-case-plugin.html) для работы со стадией.
  * `Менеджер классов`
  * `Менеджер ассоциаций`
  * `Менеджер наследований`
  * `Менеджер представлений`
  * `Менеджер запусков генерации`
  * `Обновить представления`
  * `Найти ошибки в модели`
  * `Экспорт стадии`
  * `Сохранить диаграммы в формате wmf`
  * `Генерация документации по диаграммам`
  * `Переместить...`
  * `История изменений стадии`
  * `Создать бекап`

* `Информация о лицензии`

## Генерация метаданных приложения

Генерация приложений Flexberry Ember основана на [технологии Blueprints](https://ember-cli.com/api/classes/blueprint), встроенной в EmberJS, для чего требуются специального вида метаданные. Под метаданными понимаются файлы определённого формата, содержащие некоторое описание моделей, перечислений, форм и т.д. Генерация приложений Flexberry Ember выполняется итеративно, т.е. предполагается, что в первую очередь изменяются метаданные и именно в них содержится правильное описание структуры приложения. После изменения метаданных выполняется повторная генерация приложения.

Метаданные могут быть получены при помощи плагина генерации для Flexberry Ember во Flexberry Designer. Сгенерированнае с помощью плагина метаданные располагаются в папке `vendor/flexberry/`. После генерации структура метаданных следующая:

* application
  * sitemap.json
* edit-forms
  * "название формы редактирования".json
  * ...
* enums
  * "название перечислимого типа".json
  * ...
* list-forms
  * "название списковой формы".json
  * ...
* models
  * "название модели".json
  * ...
* objects

### Метаданные уровня приложения

На уровне приложения к метаданным относится описание навигационного меню (`sitemap.json`), основанное на [настройках контейнеров во Flexberry Designer](gpg_create-and-configure-application-structure.html). Пример такого сайтмапа:

```json
{
  "applicationCaption": "Проверка генерации",
  "applicationTitle": "Ember3 gen",
  "items": [
    {
      "link": null,
      "menuName": "базовая-часть",
      "caption": "Базовая часть",
      "title": "Базовая часть",
      "children": [
        {
          "link": "neo-platform-gen-test-child1-l",
          "menuName": null,
          "caption": "Первый потомок",
          "title": "",
          "children": null
        },
        ...
      ]
    },
    ...
  ],
  "mobile": true
}
```

### Метаданные форм редактирования

Для каждой формы редактирования в приложении создаётся свой файл метаданных. Имя файла с метаданными должно соответствовать имени [будущей формы в приложении](efd3_editform.html). Пример метаданных формы редактирования:

```json
{
  "propertyLookup": [ // Описание ссылок из класса на мастера и детейлы.
    { // Описание мастеровых ссылок.
      "master": null,
      "displayAttributeName": null,
      "required": false,
      "relationName": "MasterForAgregator",
      "projection": "MasterForAgregatorL",
      "detailModelName": null
    },
    ...
    { // Описание детейловых ссылок.
      "master": "masterForAgregator",
      "displayAttributeName": "enum2Field",
      "required": true,
      "relationName": "DetailForAgregator.MasterForAgregator",
      "projection": "MasterForAgregatorL",
      "detailModelName": "neo-platform-gen-test-detail-for-agregator"
    },
    ...
  ],
  // Непосредственно описание формы.
  "external": false,
  "caption": "Агрегатор",
  "name": "NeoPlatformGenTestAgregatorClassE",
  "className": "AgregatorClassE",
  "attrs": [],
  "projections": [
    {
      "modelName": "neo-platform-gen-test-agregator-class",
      "modelProjection": "AgregatorClassE"
    }
  ]
}
```

### Метаданные перечислений

Пример метаданных для [перечислимого типа]():

```json
{
  "nameSpace": "NeoPlatform.GenTest",
  "className": "Enum1Type",
  "enumObjects": {
    "FirstValue": "Первый",
    "SecondValue": "Второй",
    "ThirdValue": "Третий"
  }
}
```

### Метаданные списковых форм

Для каждой списковой формы в приложении создаётся свой файл метаданных. Имя файла с метаданными должно соответствовать имени [будущей формы в приложении](efd3_listform.html). Пример метаданных списковой формы:

```json
{
  "newForm": "neo-platform-gen-test-master-for-agregator-e",
  "editForm": "neo-platform-gen-test-master-for-agregator-e",
  "external": false,
  "caption": "Мастера агрегаторов",
  "name": "NeoPlatformGenTestMasterForAgregatorL",
  "className": "MasterForAgregatorL",
  "attrs": [],
  "projections": [
    {
      "modelName": "neo-platform-gen-test-master-for-agregator",
      "modelProjection": "MasterForAgregatorL"
    }
  ]
}
```

### Метаданные моделей

Метаданные [моделей](efd3_model.html) располагаются в каталоге `models`. Пример метаданных модели:

```json
{
  "name": "NeoPlatformGenTestAgregatorClass", // Полное название класса в CASE-инструментарии.
  "modelName": "neo-platform-gen-test-agregator-class", // Название модели в ember-приложении.
  "className": "AgregatorClass", // Краткое название класса в CASE-инструментарии.
  "nameSpace": "NeoPlatform.GenTest", // Пространство имён в CASE-инструментарии.
  "parentModelName": null, // Название родительской модели.
  "parentClassName": null, // Название родительского класса в CASE-инструментарии.
  "attrs": [ // Описание атрибутов модели.
    {
      "name": "enum1Field", // Название атрибута.
      "type": "neo-platform-gen-test-enum1-type", // Тип атрибута (в данном случае это перечисление).
      "flexberryType": "Enum1Type", // Тип атрибута в CASE-инструментарии.
      "notNull": true, // Допустимы ли null-значения.
      "defaultValue": "FirstValue", // Значение атрибута по умолчанию.
      "stored": true, // Хранимый ли атрибут.
      "ordered": false // Является ли данный атрибут order-атрибутом.
    }
  ],
  "belongsTo": [ // belongsTo связи (мастера).
    {
      "polymorphic": false,
      "presence": true,
      "name": "masterForAgregator", // 
      "relatedTo": "neo-platform-gen-test-master-for-agregator",
      "inverse": null
    },
    ...
  ],
  "hasMany": [ // hasMany связи (детейлы).
    {
      "name": "detailForAgregator",
      "relatedTo": "neo-platform-gen-test-detail-for-agregator",
      "inverse": "myAgregator"
    }
  ],
  "projections": [ // Описание проекций (представлений).
    {
      "name": "AgregatorClassE",
      "modelName": "neo-platform-gen-test-agregator-class",
      "attrs": [
        {
          "name": "enum1Field",
          "caption": "Перечисление 1",
          "hidden": false,
          "index": 0
        }
      ],
      "belongsTo": [
        {
          "lookupValue": "DateTimeField",
          "lookupValueField": "dateTimeField",
          "relationName": "Child2",
          "belongsTo": [],
          "hidden": false,
          "attrs": [
            {
              "name": "dateTimeField",
              "caption": "~",
              "hidden": true,
              "index": 2
            }
          ],
          "index": 1,
          "type": "standard",
          "caption": "Мастер потомок",
          "name": "child2",
          "relatedTo": "neo-platform-gen-test-child2",
          "inverse": null
        },
        ...
      ],
      "hasMany": [
        {
          "projectionName": "DetailForAgregatorE",
          "caption": "Детейл агрегатора",
          "name": "detailForAgregator",
          "relatedTo": "neo-platform-gen-test-detail-for-agregator",
          "inverse": null
        }
      ]
    },
    ...
  ],
  "stored": true,
  "offline": true,
  "external": false
}
```

## Генерация фронтенда. Блюпринты для генерации. Дефолтные блюпринты

## Генерация клиентского приложения из командной строки

## Генерация бакенда

## Установка пакета ODataService «вручную» в приложение ASP.NET Web API

Существует возможность установить пакет ODataService «вручную» в приложение ASP.NET Web API (и таким образом получить Flexberry Ember бакенд). Ниже будет рассмотрен пример, когда сгенерированный по диаграмме классов проект с объектами данных будет подключён к приложению ASP.NET Web API и будет настроена работа через OData, чтобы клиентское Flexberry Ember-приложение могло получать с него данные.

Для этого требуется сделать следующее:

* Создать приложение ASP.NET Web API.

![Выбор типа проекта](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-chooseprojecttypet.png)

* Задать название проекта.

![Задание названия проекта](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-createproject.png)

{% include important.html content="Поскольку проект с объектами данных сгенерирован для .Net Framework 4.5, то и у создаваемого проекта следует выбрать такой же фреймворк." %}

* Выбрать тип проекта Web API.

![Выбор типа проекта](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-webapi.png)

* Добавить в созданный Solution проект с объектами данных.

![Добавление в созданный Solution проекта с объектами данных](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-addexistingproject.png)

* Добавить проектную ссылку из проекта ASP.NET Web API на добавленный проект с объектами данных.

![Добавление проектной ссылки](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-addprojectreference.png)

* Установить необходимые [Flexberry Nuget-пакеты](flexberry-nuget-packages.html). Это можно сделать, например, через менеджер пакетов в студии (версии пакетов должны соответствовать используемым в проекте с объектами).
    * [NewPlatform.Flexberry.AspNet.WebApi.Cors](https://www.nuget.org/packages/NewPlatform.Flexberry.AspNet.WebApi.Cors/)
    * [NewPlatform.Flexberry.LockService](https://www.nuget.org/packages/NewPlatform.Flexberry.LockService)
    * [NewPlatform.Flexberry.LogService](fo_log-service-log4net.html)
    * [NewPlatform.Flexberry.LogService.Objects](https://www.nuget.org/packages/NewPlatform.Flexberry.LogService.Objects)
    * [NewPlatform.Flexberry.ORM](fo_flexberry-orm.html)
    * [NewPlatform.Flexberry.ORM.ODataService](fo_orm-odata-service.html)
    * [NewPlatform.Flexberry.UserSettingsService](https://www.nuget.org/packages/NewPlatform.Flexberry.UserSettingsService)

![Установленные Flexberry Nuget-пакеты](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-installedflexberry.png)

* Установить при необходимости проект Unity.AspNet.WebApi.

![Установленные Unity Nuget-пакеты](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-installedunity.png)

* Удалить лишние автосгенерированные элементы в проекте.

![Удаление лишних автосгенерированных элементов в проекте](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-removeextras.png)

* Добавить в проект ODataConfig.

![Выбор типа проекта](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-addodataconfig.png)

```cs
namespace ManualOdataAddingTest
{
    using System;
    using System.Reflection;
    using System.Web.Http;

    using ICSSoft.STORMNET;
    using ICSSoft.Services;
    using IIS.Caseberry.Logging.Objects;

    using NewPlatform.Flexberry;
    using NewPlatform.Flexberry.AspNet.WebApi.Cors;
    using NewPlatform.Flexberry.ORM.ODataService;
    using NewPlatform.Flexberry.ORM.ODataService.Extensions;
    using NewPlatform.Flexberry.ORM.ODataService.Functions;
    using NewPlatform.Flexberry.ORM.ODataService.Model;
    using NewPlatform.Flexberry.Services;

    using Unity;
    using Unity.AspNet.WebApi;

    internal static class ODataConfig
    {
        public static void Configure(HttpConfiguration config, IUnityContainer container, HttpServer httpServer)
        {
            if (config == null)
            {
                throw new ArgumentNullException("config");
            }

            if (container == null)
            {
                throw new ArgumentNullException("container");
            }

            config.EnableCors(new DynamicCorsPolicyProvider());
            config.DependencyResolver = new UnityDependencyResolver(container);
            var assemblies = new[]
            {
                Assembly.Load("GenTest.Objects"),
                typeof(ApplicationLog).Assembly,
                typeof(UserSetting).Assembly,
                typeof(FlexberryUserSetting).Assembly,
                typeof(Lock).Assembly
            };

            var builder = new DefaultDataObjectEdmModelBuilder(assemblies);
            var token = config.MapODataServiceDataObjectRoute(builder, httpServer);
            token.Functions.Register(new Func<QueryParameters, string>(Test));
            token.Events.CallbackAfterCreate = CallbackAfterCreate;
        }

        private static void CallbackAfterCreate(DataObject dataObject)
        {
        }

        private static string Test(QueryParameters queryParameters)
        {
            return "Hello world!";
        }
    }
}
```

`Assembly.Load("GenTest.Objects")` - загрузка сборки с объектами из подключенного проекта.

* Изменить содержимое Global.asax.cs

```cs
namespace ManualOdataAddingTest
{
    using System;
    using System.Web;
    using System.Web.Http;

    using ICSSoft.STORMNET.Business;

    using Microsoft.Practices.Unity.Configuration;
    using Unity;

    public class Global : HttpApplication
    {
        protected void Application_Start(object sender, EventArgs e)
        {
            IUnityContainer container = new UnityContainer();
            container.LoadConfiguration();
            container.RegisterInstance(DataServiceProvider.DataService);
            GlobalConfiguration.Configure(configuration => ODataConfig.Configure(configuration, container, GlobalConfiguration.DefaultServer));
        }
    }
}

```

* Изменить содержимое представления Global.asax

```cs
<%@ Application Codebehind="Global.asax.cs" Inherits="ManualOdataAddingTest.Global" Language="C#" %>
```

* Удалить из файла конфигурации Web.config настройки, связанные с Oracle.

![Настройки, связанные с Oracle, в файле конфигурации Web.config](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-removeoracle.png)

* Заменить в файле конфигурации Web.config секцию `appSettings` и добавить секцию `connectionStrings` (в случае, если используется БД, отличная от Postgres, то значения настроек будут несколько отличаться).

{% raw %} 
```xml
<appSettings>
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService" />
    <add key="DefaultConnectionStringName" value="DefConnStr" />
</appSettings>
<connectionStrings>
    <add name="DefConnStr" connectionString="Host=localhost;Port=5432;Database=ember3gentest;User ID=postgres;Password=lalalapassword;" />
</connectionStrings>
```
{% endraw %}

* Заменить в файле конфигурации Web.config секцию `unity` (в случае, если используется БД, отличная от Postgres, то значения настроек будут несколько отличаться).

{% raw %} 
```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
        <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
                    mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject" />
        <register type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
                    mapTo="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService">
            <constructor />
        </register>
    </container>
</unity>
```
{% endraw %}

* Запустить проект, который после запуска вернёт сообщение "403" (адрес запуска проекта [можно изменить](efd3_generated-app-start)).

![Ошибка 403 на запущенном проекте](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-forbidden.png)

* Перейти по адресу `/odata` и удостовериться, что [OData API](efd3_generated-app-start) функционирует.

![Получение доступа к OData](/images/pages/products/flexberry-ember/ember-flexberry/generation/FEOdata-viewodata.png.png)