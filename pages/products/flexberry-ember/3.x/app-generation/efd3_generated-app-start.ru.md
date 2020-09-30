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
Для запуска может потребоваться дополнительная настройка сгенерированных приложений.

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

### Настройка клиентского приложения
Открыть `фронтенд`-часть приложения можно в `Visual Studio Code`, для этого нужно открыть папку `ember-app` внутри папки со сгенерированным из `Flexberry Designer` приложением (можно это сделать, выбрав пункт меню `File` → `Open folder`).

После генерации клиентское приложение содержит файл конфигурации `environment.js` (`targets.js` представляет меньший интерес, поскольку полностью идентичен генерируемому просто `Ember` файлу).

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

      // Настройки сервиса оценки скорости рендеринга компонентO.
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

## Запуск приложений
OData-бакенд и клиентское Ember-приложение можно запускать в произвольном порядке, однако предпочтительнее сначала запустить OData-бакенд, почле чего в браузере открывать клиентское приложение.

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

* Доступны списки объектов данных, куда есть возможность добавлять новые записи (во [Flexberry Designer](fd_flexberry-designer.html) спискам соответствуют классы со стереотипом `listform`).

![Пустая списковая форма клиентского приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-empty-listform.png)

* При добавлении или редактировании новой записи (это происходит на форме, соответствующей классу со стереотипом `editform` во [Flexberry Designer](fd_flexberry-designer.html)) для разных типов данных используются соответствующие контролы.

![Форма создания с перечислением](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-editform-with-enum.png)

![Форма создания с перечислением](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-editform-with-detail.png)

* На формах создания и редактирования работают встроенные типичные правила валидации данных

![Форма создания с перечислением](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-validation.png)

* При добавлении новой записи происходит переадресация с формы создания на форму редактирования

![Форма редактирования](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-saved-master.png)

* Добавленные записи отображаются на соответствующей списковой форме

![Списковая форма](/images/pages/products/flexberry-ember/ember-flexberry/generation/frontend-saved-masters.png)

## Обзор функционала OData API 

Обзор функционала OData API (отображение метаданных, примеры формирования запросов из браузера или Postman)
