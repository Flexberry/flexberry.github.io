---
title: Запуск сгенерированных приложений
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_generated-app-start.html
lang: ru
summary: Обзор особенностей настройки и запуска сгенерированных приложений.
---

## Настройка Flexberry Ember-приложений
Для запуска сгенерированных приложений может потребоваться дополнительная настройка.

### Настройка OData-бакенда
Для того, чтобы запустить бакенд, нужно открыть сгенерированный файл с расширением `sln`, который находится в папке со сгенерированным из [Flexberry Designer](fd_flexberry-designer.html) решением, в среде разработки Visual Studio. По умолчанию проектом для запуска в сгенерированном решении может являться проект с объектами данных. Чтобы приложение запустилось, требуется назначить проектом для запуска `ODataBackend`.

Далее в файле `ODataConfig` требуется раскомментировать строчку 

```csharp
config.EnableCors(new DynamicCorsPolicyProvider());
```

![Включение корсов в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-backend-cors-enabling.png)

По умолчанию проект запустится по адресу `http://localhost:6500/`. Изменить эту настройку можно в свойствах проекта (правая кнопка мыши на проекте `ODataBackend` -> Properties -> Вкладка `Web` -> Раздел `Servers`).

![Изменение хоста, порта, базового роута для OData-бакенда в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-backend-url-changing.png)

После генерации OData-бакенд содержит файл конфигурации `Web.config` со следующими настройками, которые можно при необходимости менять

```xml
<?xml version="1.0" encoding="utf-8"?>

<configuration>
  <!-- Описание дополнительных секций в конфигурации -->
  <configSections>
    <!-- Система логирования -->
    <section name="log4net" type="System.Configuration.IgnoreSectionHandler" />

    <!-- Unity -->
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Unity.Configuration" />
  </configSections>

  <!-- Настройки приложения -->
  <appSettings>
    <!-- Тип используемого сервиса данных для взаимодействия с БД -->
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService" />

    <!-- Имя строки подключения, используемой по умолчанию -->
    <add key="DefaultConnectionStringName" value="DefConnStr" />
  </appSettings>
  
  <!-- Строки подключения к БД -->
  <connectionStrings>
    <add name="DefConnStr" connectionString="Host=localhost;Port=5432;Database=ember3gentest;User ID=postgres;Password=lalalapassword" />
  </connectionStrings>

  <!-- Включение отладки -->
  <system.web>
    <compilation debug="true" />
  </system.web>

  <!-- Настройки для работы web-приложения -->
  <system.webServer>
    ...
  </system.webServer>
  <runtime>
    ...
  </runtime>

  <!-- Настройки логирования -->
  <log4net>
    ...
  </log4net>

  <!-- Настройки Unity -->
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
      <!-- В качестве менеджера полномочий используется ICSSoft.STORMNET.Security.EmptySecurityManager-->
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject"
                mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject" />

       <!-- В качестве сервиса данных для работы с БД используется ICSSoft.STORMNET.Business.PostgresDataService-->
      <register type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business"
                mapTo="ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService">
        <constructor />
      </register>
    </container>
  </unity>
</configuration>
```

{% include important.html content="Более сложные варианты настройки OdataService указаны в [этой статье](fo_orm-odata-service.html)." %}

### Настройка клиентского приложения
Открыть `фронтенд`-часть приложения можно в `Visual Studio Code`, для этого нужно открыть папку `ember-app` внутри папки со сгенерированным из `Flexberry Designer` приложением (можно это сделать, выбрав пункт меню `File` → `Open folder`).

После генерации клиентское приложение содержит файл конфигурации `environment.js`.

![Файлы конфигурации в сгенерированном клиентском `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-frontend-config.png)

``` js
'use strict';

module.exports = function(environment) {
  // Адрес OData-бакенда.
  var backendUrl = 'http://localhost:6500';

  // Возможна работа с локальной БД, тогда для запуска следует использовать `ember s -e development-loc`.
  if (environment === 'development-loc') {
    // Адрес OData-бакенда при работе с локальной БД.
    backendUrl = 'http://localhost:6500';
  }

  // Настройки приложения, аналогичны настройкам Ember.
  let ENV = {
    modulePrefix: 'ember-app',
    environment,
    rootURL: '/',
    locationType: 'auto',
    EmberENV: ...
    },

    APP: {
      // Имя приложения, используется сервисом пользовательских настроек.
      name: 'ember-app',

      // Адрес OData-бакенда.
      backendUrl: backendUrl,

      // Адрес OData-бакенда, через который непосредственно делаются запросы.
      backendUrls: {
        root: backendUrl,
        api: backendUrl + '/odata'
      },

      // Настройки логирования.
      log: { ... },

      // Настройки сервиса оценки скорости рендеринга компонент.
      perf: { ... },

      // Настройки сервиса блокировок.
      lock: { ... },

      // Флаг, использовать ли сервис пользовательских настроек.
      useUserSettingsService: true,

      // Флаг, использовать ли сервис задания ограничений.
      useAdvLimitService: true,

      // Настройки для работы в офлайн-режиме.
      offline: { ... },

      // Настройки работы некоторых Flexberry Ember-компонент.
      components: { ... },
    }
  };

  // Настройки CSP:
  // http://www.ember-cli.com/#content-security-policy
  // https://github.com/rwjblue/ember-cli-content-security-policy
  // http://content-security-policy.com
  ENV.contentSecurityPolicy = { ... };

  // Настройки ember-i18n: https://github.com/jamesarosen/ember-i18n.
  ENV.i18n = {
    // Локализация по умолчанию.
    defaultLocale: 'ru'
  };

  // Настройки ember-moment: https://github.com/stefanpenner/ember-moment.
  ENV.moment = {
    outputFormat: 'L'
  };

  // Настройки для режима разработки.
  if (environment === 'development') { ... }

  // Настройки для режима тестирования.
  if (environment === 'test') { ... }

  // Настройки для режима работы развёрнутого для пользователей приложения.
  if (environment === 'production') { ... }

  return ENV;
};
```

* `ENV.locationType` - [режим формирования URL для приложения](efd3_router.html).
* `ENV.APP.log` - настройки [сервиса логирования](efd3_log-service.html).
* `ENV.APP.lock` - настройки [сервиса пессимистических блокировок](efd3_editform.html).
* `ENV.APP.offline` - настройки для работы [в офлайн-режиме]().
* `ENV.APP.useUserSettingsService` - флаг, определяющий работу [сервиса пользовательских настроек]().
* `ENV.APP.useAdvLimitService` - флаг, определяющий работу [сервиса задания ограничений]().

Файл конфигурации `targets.js` полностью идентичен генерируемому `Ember` файлу, в нём указаны настройки для [транспилятора Babel](https://babeljs.io/).

Настройку хоста и порта для запуска приложения можно осуществить, как прописав соответствующие опции в файл `.ember-cli`, так и указав их непосредственно в командной строке (доступные опции можно узнать, выполнив команду `ember s --help` в командной строке).

## Запуск приложений
OData-бакенд и клиентское Ember-приложение можно запускать в произвольном порядке, однако предпочтительнее сначала запустить OData-бакенд, после чего в браузере открывать клиентское приложение.

### Запуск OData-бакенда
Для запуска настроенного OData-бакенда в `Visual studio` нужно нажать `Ctrl+F5` или выбрать пункт меню `Debug` → `Start Without Debugging`. При успешном запуске приложения в браузере, назначенном по умолчанию в `Visual Studio`, откроется страница с кодом 403.14 - Forbidden.

Для проверки, что сгенерированный API доступен, можно добавить к URL `/odata`.

![Проверка доступности OData API в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/backend-API.png)

### Запуск клиентского приложения
Можно запустить сгенерированное настроенное клиентское приложение в `Visual Studio Code`. Для этого следует открыть терминал `Terminal` -> `New terminal`.

![Запуск терминала в `Visual Studio Code`](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-start-terminal.png)

В запущенном терминале следует выполнить команду `ember server`.

Если сборка приложения завершилась успешно, то в терминале появится соответствующее сообщение, а также ссылка, по которой можно открыть запущенное приложение.

![Успешный запуск клиентского приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-build-success.png)

После открытия ссылки можно увидеть примерно следующее:

![Запущенное клиентское приложение](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-started.png)

## Обзор функционала клиентского приложения
Сгенерированное клиентское приложение предоставляет следующие возможности.

* Меню клиентского приложения соответствует структуре, заданной во [Flexberry Designer](fd_flexberry-designer.html) в классе со стереотипом `application`.

![Меню клиентского приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-menu.png)

* Доступны [списки объектов данных](efd3_listform.html), куда есть возможность добавлять новые записи (во [Flexberry Designer](fd_flexberry-designer.html) спискам соответствуют классы со стереотипом `listform`).

![Пустая списковая форма клиентского приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-empty-listform.png)

* При [добавлении или редактировании новой записи](efd3_editform.html) (это происходит на форме, соответствующей классу со стереотипом `editform` во [Flexberry Designer](fd_flexberry-designer.html)) для разных типов данных используются соответствующие компоненты.

![Форма создания с перечислением](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-editform-with-enum.png)

![Форма создания с детейлами](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-editform-with-details.png)

* На [формах создания и редактирования](efd3_editform.html) работают встроенные типичные правила [валидации данных](efd3_model-validation.html)

![Форма создания с перечислением](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-validation.png)

* При добавлении новой записи происходит переадресация [с формы создания на форму редактирования]((efd3_editform.html))

![Форма редактирования](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-saved-master.png)

* Добавленные записи отображаются на соответствующей [списковой форме](efd3_listform.html)

![Списковая форма](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-saved-masters.png)

## Обзор функционала OData API 

После запуска OData-бакенда по адресу `http://localhost:6500/odata/` отображается доступный API (адрес может быть изменён в настройках).

### Получение всех записей

Для отображения всех записей, например, типа `IISGenTestMasterForAgregator`, можно в браузере ввести 
`http://localhost:6500/odata/IISGenTestMasterForAgregators` (при этом в конце имени типа следует добавить символ `s`), тогда придёт ответ:

```json
{
  "@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestMasterForAgregators","value":[
    {
      "Enum2Field":"ValueSecond","__PrimaryKey":"6213c156-b061-4287-8275-b8533d9ecf7a"
    },{
      "Enum2Field":"ValueFirst","__PrimaryKey":"2ea43483-19db-4474-b523-426fec183aed"
    },{
      "Enum2Field":"ValueThird","__PrimaryKey":"fa621409-c381-41a1-b1ae-6767e404a99f"
    }
  ]
}
```

### Получение данных списковой формы

Для получения данных для [списковой формы](efd3_listform.html) на OData-бакенд идёт запрос вида

```
http://localhost:6500/odata/IISGenTestChild2s?
    $count=true
    &$select=__PrimaryKey,DateTimeField,ParentField
    &$skip=0
    &$top=5
```

```json
{
  "@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestChild2s(__PrimaryKey,DateTimeField,ParentField)","@odata.count":1,"value":[
    {
      "__PrimaryKey":"fe3325bf-c633-4661-ad6f-bba7eb24b46d","DateTimeField":"2020-09-30T19:30:55.112+05:00","ParentField":23
    }
  ]
}
```

* `http://localhost:6500/odata/IISGenTestChild2s` - указание запрашиваемого типа,
* `$count=true` - указание, что следует вернуть метаданные о количестве возвращённых записей (в ответе: `"@odata.count":1`),
* `$select=__PrimaryKey,DateTimeField,ParentField` - указание, что требуется вернуть поля `__PrimaryKey`, `DateTimeField` и `ParentField` (в ответе: `"@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestChild2s(__PrimaryKey,DateTimeField,ParentField)"`),
* `$skip=0` - указание, что записи следует возвращать, начиная с нулевой (фактически, пропускаем 0 записей с начала),
* `$top=5` - указание, что следует вернуть 5 записей.

Если на [списковой форме](efd3_listform.html) отображаются поля мастера, то запрос будет иметь вид

```
http://localhost:6500/odata/IISGenTestChild1s?
    $count=true
    &$expand=MyMaster($select=__PrimaryKey,IntField)
    &$select=__PrimaryKey,DoubleField,StringField,ParentField,MyMaster
    &$skip=0
    &$top=5
```

```json
{
  "@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestChild1s(__PrimaryKey,DoubleField,StringField,ParentField,MyMaster,MyMaster(__PrimaryKey,IntField))","@odata.count":1,"value":[
    {
      "__PrimaryKey":"aa447992-c783-41d3-80f0-c78ae200a881","DoubleField":1.0,"StringField":"\u0421\u0442\u0440\u043e\u043a\u0430","ParentField":33,"MyMaster":{
        "__PrimaryKey":"3235b1d9-66a9-4ee2-9698-faaa7465b1d0","IntField":12
      }
    }
  ]
}
```

В отличие от предыдущего примера здесь через `$expand` запрашиваются поля `__PrimaryKey` и `IntField` связанной  по связи `MyMaster` сущности (`$expand=MyMaster($select=__PrimaryKey,IntField)`).

### Получение данных формы редактирования

Для получения данных для [формы редактирования](efd3_editform.html) на OData-бакенд идёт запрос вида

```
http://localhost:6500/odata/IISGenTestChild2s?
    $filter=__PrimaryKey%20eq%20fe3325bf-c633-4661-ad6f-bba7eb24b46d
    &$select=__PrimaryKey,DateTimeField,ParentField
```

Для отображения только требуемой записи накладывается фильтр, что [первичный ключ](efd3_model.html) должен иметь заданное значение (`filter=__PrimaryKey%20eq%20fe3325bf-c633-4661-ad6f-bba7eb24b46d`).

```json
{
  "@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestChild2s(__PrimaryKey,DateTimeField,ParentField)","value":[
    {
      "__PrimaryKey":"fe3325bf-c633-4661-ad6f-bba7eb24b46d","DateTimeField":"2020-09-30T19:30:55.112+05:00","ParentField":23
    }
  ]
}
```

Если у записи требуется получить как мастера, так и детейлы, то запрос будет иметь вид

```
http://localhost:6500/odata/IISGenTestChild1s?
    $expand=MyMaster($select=__PrimaryKey,IntField),
            Detail1ForChild1($select=__PrimaryKey,IntFieldWithValue1),
            Detail2ForChild1($select=__PrimaryKey,IntFieldWithValue)
    &$filter=__PrimaryKey%20eq%20aa447992-c783-41d3-80f0-c78ae200a881
    &$select=__PrimaryKey,DoubleField,StringField,ParentField,MyMaster,Detail1ForChild1,Detail2ForChild1
```

Через `$expand` запрашиваются поля связанных по связям `MyMaster` (мастеровая связь), `Detail1ForChild1` (детейловая связь), `Detail2ForChild1` (детейловая связь) сущностей (`$expand=MyMaster($select=__PrimaryKey,IntField),Detail1ForChild1($select=__PrimaryKey,IntFieldWithValue1),Detail2ForChild1($select=__PrimaryKey,IntFieldWithValue)`). 

```json
{
  "@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestChild1s(__PrimaryKey,DoubleField,StringField,ParentField,MyMaster,Detail1ForChild1,Detail2ForChild1,MyMaster(__PrimaryKey,IntField),Detail1ForChild1(__PrimaryKey,IntFieldWithValue1),Detail2ForChild1(__PrimaryKey,IntFieldWithValue))","value":[
    {
      "__PrimaryKey":"aa447992-c783-41d3-80f0-c78ae200a881","DoubleField":1.0,"StringField":"\u0421\u0442\u0440\u043e\u043a\u0430","ParentField":33,"MyMaster":{
        "__PrimaryKey":"3235b1d9-66a9-4ee2-9698-faaa7465b1d0","IntField":12
      },"Detail1ForChild1":[
        {
          "__PrimaryKey":"7f6dc423-2e43-4d55-8b6f-0468eddc4ba2","IntFieldWithValue1":1
        },{
          "__PrimaryKey":"706dc254-ac6f-4321-8d17-b253a4435c11","IntFieldWithValue1":11
        }
      ],"Detail2ForChild1":[
        {
          "__PrimaryKey":"35436532-ace0-4723-b043-0e8c52d1cb84","IntFieldWithValue":2
        }
      ]
    }
  ]
}
```

Если у записи два мастера (`Child2`, `MasterForAgregator`), а детейл (`DetailForAgregator`) также имеет мастеровую связь (`MasterForAgregator`), то запрос происходит полностью аналогично с увеличением уровня вложенности

```
http://localhost:6500/odata/IISGenTestAgregatorClasss?
    $expand=Child2($select=__PrimaryKey,DateTimeField),
            MasterForAgregator($select=__PrimaryKey,Enum2Field),
            DetailForAgregator($select=__PrimaryKey,DetailIntField,MasterForAgregator;
                                $expand=MasterForAgregator($select=__PrimaryKey,Enum2Field))
    &$filter=__PrimaryKey%20eq%200ca7f760-2b1d-4be1-9659-be0bf6ab7faa
    &$select=__PrimaryKey,Enum1Field,Child2,MasterForAgregator,DetailForAgregator
```

```json
{
  "@odata.context":"http://localhost:6500/odata/$metadata#IISGenTestAgregatorClasss(__PrimaryKey,Enum1Field,Child2,MasterForAgregator,DetailForAgregator,Child2(__PrimaryKey,DateTimeField),MasterForAgregator(__PrimaryKey,Enum2Field),DetailForAgregator(__PrimaryKey,DetailIntField,MasterForAgregator,MasterForAgregator(__PrimaryKey,Enum2Field)))","value":[
    {
      "__PrimaryKey":"0ca7f760-2b1d-4be1-9659-be0bf6ab7faa","Enum1Field":"FirstValue","Child2":{
        "__PrimaryKey":"fe3325bf-c633-4661-ad6f-bba7eb24b46d","DateTimeField":"2020-09-30T19:30:55.112+05:00"
      },"MasterForAgregator":{
        "__PrimaryKey":"6213c156-b061-4287-8275-b8533d9ecf7a","Enum2Field":"ValueSecond"
      },"DetailForAgregator":[
        {
          "__PrimaryKey":"22f03fc5-6e6c-4350-84aa-b0c97299956c","DetailIntField":2,"MasterForAgregator":{
            "__PrimaryKey":"6213c156-b061-4287-8275-b8533d9ecf7a","Enum2Field":"ValueSecond"
          }
        }
      ]
    }
  ]
}
```

{% include note.html content="При обновлении соответствующий запрос на форме создания и редактирования [зависит от изменённых данных](efd3_editform.html)." %}