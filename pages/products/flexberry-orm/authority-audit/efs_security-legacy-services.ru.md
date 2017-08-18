---
title: Сервис полномочий Flexberry Rights (CheckingLibrary)
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Security, Ключевые понятия
toc: true
permalink: ru/efs_security-legacy-services.html
lang: ru
---

`Сервис полномочий Flexberry Rights` - это альтернативный сервис [полномочий](efs_right-manager-module.html), разработанный с учётом растущих требований. Основной целью сервиса является предоставление опосредованного доступа к БД (защита БД от нежелательных изменений). 

### Вопросы совместимости

С целью обеспечения совместимости прикладных систем с новой системой полномочий, существующая клиент-серверная архитектура проверки полномочий оставлена.

Исполльзуются и [RightManager](efs_right-manager.html), и `AzManBridge` (перенаправляет вызовы не в AzMan, а в `CheckingLibrary`).

AzManBridgeService поддерживает работу с несколькими приложениями одновременно, то есть можно использовать один сервер для проверки полномочий для целого комплекса различных приложений.

### Режимы работы Flexberry Rights

Доступно несколько вариантов работы `RightManager`:

* `AzMan` - Autorization Manager (устаревшая технология, не рекомендуется к использованию на новых проектах)
* `SimpleCheck` - Проверка на подобие Autorization Manager, только используется CheckingLibrary.
* `SessionCheck` - Проверка с применением механизма сессий.

#### SessionCheck

Принцип работы в данном случае отличается от стандартного тем, что кроме информации о пользователе, для которого запрашивается проверка полномочий, `[RightManager)` передаёт ещё ключ сессии, который проверяется на стороне сервера. Таким образом, вводится обязательная проверка логина-пароля перед получением информации о правах того или иного пользователя. 

Ключ сессии выдаётся на клиентскую машину только в случае удачной проверки пары логин-пароль пользователя.

На серверной стороне задаётся время бездействия сессии, после которой ключ сессии удаляется из списка активных ключей. В случае, если `[RightManager)` запрашивает полномочия с ключом истекшей сессии, сервер ему ответит отказом и попросит снова пройти операцию логирования в систему. `[RightManager)` выкинет событие `NeedLogIn`. Программист должен обязательно подписаться на это событие и выполнить операцию:

``` csharp
        /// <summary>
        /// Авторизоваться и получить ключ сессии (выход происходит автоматически при вызове этой функции)
        /// </summary>
        /// <param name="login">логин</param>
        /// <param name="domain">домен</param>
        /// <param name="pwd">пароль</param>
        /// <param name="name">имя пользователя (для синхронизации)</param>
        /// <returns>успешность операции</returns>
        public static OperationResult LogInAndGetNewSessionKey(string login, string domain, string pwd, out string name)
```

Например, можно использовать [UsingSimpleLoginForm#SimpleLoginFormExample|стандартную форму логина) для (Windows-приложений).

####  Пример конфигурационного файла `AzManBridgeService` 

``` xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
	<appSettings>
		<add key="AzManSecurity" value="false"/>
                <!-- порт, по которому будет доступен этот сервис -->
		<add key="PortNumber" value="8085"/>

                <!-- флаг, указывающий на то, что этот сервис обрабатывает несколько приложений -->
		<add key="MultipleBases" value="true"/>

                <!-- выводить отладочную информацию, пишется лог практически всех проверок -->
		<add key="debugInformation" value="true"/>


		<add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
		<add key="CustomizationStrings" value="SERVER=Tornado;Trusted_connection=yes;DATABASE=MOB_Security;" />

                <!-- имя приложения из описанных в этом конфиге, в БД которого будут слиты сессии при перезапуске службы -->
		<add key="SessionStorageAppName" value="SessionModeTestingApp"/>
                <!-- Период в минутах, через который истекают сессии пользователей -->
		<add key="SessionExpireMin" value="5"/>

		<add key="DataServiceType_SessionModeTestingApp" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
		<add key="CustomizationStrings_SessionModeTestingApp" value="SERVER=storm;Trusted_connection=no;DATABASE=SecurityInSessionModeDB;uid=webuser1;pwd=1;" />
		<add key="CheckingMode_SessionModeTestingApp" value="SessionCheck"/>


		<add key="DataServiceType_PGSS" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService"/>
		<add key="CustomizationStrings_PGSS" value="SERVER=tornado;Trusted_connection=no;DATABASE=PGSS_Security;uid=pgss_sec_user;pwd=1" />
		<add key="CheckingMode_PGSS" value="SessionCheck"/>


		<!--эта настройка позволяет управлять именем классов (true - имя с неймспейсом, false - без неймспейса)-->
		<add key="UsingNamespaceForRights" value="false"/>


<!-- ["CheckingMode_" + appName) Возможные значения: AzMan, SimpleCheck, SessionCheck (Перезапуск сервиса необходим)-->
<!-- ["SessionStorageAppName") Имя приложения для сохранения сессий между перезагрузками сервиса -->
<!-- ["SessionExpireMin") Время, по прошествии которого сессия считается истекшей (в минутах) -->
	</appSettings>

</configuration>
```

### Задание имени приложения для проверки полномочий

Для того, чтобы один сервис полномочий мог обслуживать несколько приложений, ему необходимо получать информацию о том, какое приложение отправляет ему тот или иной запрос. По-умолчанию в качестве имени приложения берётся имя исполняемого файла без расширения. У программистов есть возможность через конфигурационный файл подменить это значение на другое.

####  web 

В конфигурационном файле необходимо указать имя приложения в блоке `unity`:

``` xml
<!-- Система полномочий -->
  <unity>
    <typeAliases>
      <typeAlias alias="ISecurityService" type="ICSSoft.STORMNET.Security.ISecurityService, ICSSoft.STORMNET.DataObject"/>
      <typeAlias alias="CheckingInSessionMode" type="ICSSoft.STORMNET.Security.CheckingInSessionMode, CheckingLibrary"/>
      <typeAlias alias="singleton"
                 type="Microsoft.Practices.Unity.ContainerControlledLifetimeManager,&#xD;&#xA;                     Microsoft.Practices.Unity"
                                                />
    </typeAliases>
    <containers>
      <container>
        <types>
          <type type="ISecurityService" mapTo="CheckingInSessionMode">
            <lifetime type="singleton" />
            <typeConfig extensionType="Microsoft.Practices.Unity.Configuration.TypeInjectionElement, Microsoft.Practices.Unity.Configuration">
              <constructor>
                <param name="sИмяПриложения" parameterType="System.String">
                  <!-- Тут надо прописать имя приложения -->
                  <value value="WebApp" type="System.String" />
                </param>
              </constructor>
            </typeConfig>
          </type>
        </types>
      </container>
    </containers>
  </unity>
  <!-- Система полномочий -->

<authentication mode="Forms" >
      <forms name=".ASPXFORMSAUTH" loginUrl="LoginForm.aspx" timeout="30" slidingExpiration="true" />
    </authentication>

    <authorization>
      <deny users="?" />
    </authorization>

    <membership defaultProvider="CaseberryMembershipProvider">
      <providers>
        <clear/>
        <add name="CaseberryMembershipProvider" type="ICSSoft.STORMNET.Security.CaseberryMembershipProvider" applicationName="SLAuthSample"/>
      </providers>
    </membership>

    <roleManager defaultProvider="CaseberryRoleProvider" enabled="true">
      <providers>
        <clear/>
        <add name="CaseberryRoleProvider" type="ICSSoft.STORMNET.Security.CaseberryRoleProvider" />
      </providers>
    </roleManager>
```

####  winforms 


В конфигурационном файле задаётся настройка: `AppNameForAzMan` или берётся

``` csharp
_securityApplicationName = System.IO.Path.GetFileNameWithoutExtension(Environment.GetCommandLineArgs()[0));
```

Также есть возможность программной установки имени приложения через `RightManager.SecurityApplicationName`.
