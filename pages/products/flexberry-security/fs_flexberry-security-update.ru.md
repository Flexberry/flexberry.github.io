---
title: Обновление Flexberry Security
sidebar: flexberry-security_sidebar
keywords: flexberry, security, update
summary: Обновление Flexberry Security с 1.7.0 до 2.0.0 и выше.
toc: true
permalink: ru/fs_flexberry_security_update.html
lang: ru
---

##  Особенности новых полномочий
У нас есть RightManager ("представитель" старых полномочий), который исторически собрал в себе работу в 3-х разных вариантах реализации полномочий (начиная с AzMan) + есть CheckingLibrary, который реализует два из трех этих вариантов местами не оптимальным образом.
Плюс это еще все реализовано в static-методах, т.е. любые настройки в полномочиях влияют на всё приложение сразу и нет возможности как-то всё гибко сконфигурировать.
Т.е. есть куча устаревшего кода со своими тараканами, который нет ни смысла, ни желания поддерживать.

Плюс в новой версии полномочий есть новые фишки – например, возможность накладывать фильтры на экземпляры объектов данных (т.е. добавлять LimitFunction-ы на уровне полномочий), переписано кеширование, реализовано программное управление агентами, будет реализовано программное управление классами, операциями и пр. Т.е. всё, что в веб-админке или в консоли полнмочий можно делать через интерфейс, можно будет делать программно. 

##  Обновление Security

Классы старых полномочий (`RightManager`, `Checking` и пр., в том числе старые варианты мембершип-провайдеров всяких) живут теперь в пакете  [NewPlatform.Flexberry.LegacySecurity](fs_legacy-security-install.html).

{% include note.html content="Этот пакет НЕ нужно ставить вместе с пакетом `NewPlatform.Flexberry.Security`!
Т.е. в коде должны присутствовать зависимости либо только от старых классов полномочий, либо только от новых." %}

Последняя версия `ASP.NET`, совместима со старыми полномочиями.
Есть ряд изменений в секции `Unity` в конфиге, которые появились со времени выпуска последних альфа-версий новых полномочий.

В целом, чтобы всё установить и настроить надо:

1. Перед обновлением всех пакетов удалить все старые настройки из `Unity`:
    * Именованные сервисы данных для менеджера полномочий и менеджера агентов: `ICSSoft.STORMNET.Security.ISecurityManager`, `DecorableSecurityManager`, `securityManagerWithoutRightsCheck`.
    * Именованные сервисы кеша для менеджера полномочий и провайдеров менеджера агентов: `CacheForUserSettingsService`, `DecorableUserSettingsService`, `CacheForSecurityManager`.
    * Неименованные разрешения интерфейсов `IPasswordHasher`, `IAgentManager`, `ISecurityManager`.
    * Прочую лабуду, связанную с полномочиями.

2.	Обновить пакеты (до последних бета-версий):
    * `NewPlatform.Flexberry.Security`
    * `NewPlatform.Flexberry.ORM`
    * `NewPlatform.Flexberry.Caching`
    * `NewPlatform.Flexberry.AspNet`
    * `NewPlatform.Flexberry.Audit`

Почти во всех пакетах сейчас зашиты трансформации конфига, поэтому в секции `Unity` почти установится что нужно само, но с параметрами по умолчанию

3. После установки пакетов поменять настройки, какие надо, в конфиге в секции `Unity`:
    * Установить нужные типы сервисов данных в маппингах.
    * Исправить значение свойств `CustomizationStringName` в именованных регистрациях сервисов данных: `dataServiceForSecurityManager`, `dataServiceForAuditAgentManagerAdapter` (эта регистрация сделана «на всякий случай» - на случай если аудит в полномочия по каким-то причинам не передаст сконфигурированный сервис данных для работы с классами полномочий). В свойстве `CustomizationStringName` указывается имя строки соединения из секции `connectionStrings` конфигурационного файла.
    * Если надо – можно добавить соль для хеширования паролей. Теперь соль указывается в качестве параметра в конструкторе у хешера паролей (не у менеджера агентов как ранее!). Можно также сменить тип хешера паролей с Sha1 на что-нибудь другое, если у вас разрабатывается новое приложение и нет БД со старыми хешами.
    * Заменить мембершип-, роль- и профиль-провайдеры на провайдеры из «новых» полномочий: `FlexberryMembershipProvider`, `FlexberryRoleProvider` и `FlexberryProfileProvider`, пространство имен `NewPlatform.Flexberry.Security`, сборка `NewPlatform.Flexberry.Security`.
    * Убрать разрешение интерфейса `ISecurityService` на `CheckingInSessionMode` (удалить из конфига, если есть).

Основные изменения `Security`:
1. В новых полномочиях сейчас есть 2 основных интерфейса (и соответственно их технологических реализаций): `ISecurityManager` (менеджер полномочий) и `IAgentManager` (менеджер агентов). Менеджер полномочий привязан к сервису данных (т.к. при вычитке данных из БД должны проверяться полномочия). Менеджер агентов не привязан к сервису данных (для него сервис данных может быть сконфигурирован свой) и носит вспомогательный характер (вся программная работа с агентами, проверками существования агентов, получение перечня агентов и пр. вынесена туда). На данный момент в приложении должен быть зарегистрирован обязательно (в Unity) один менеджер агентов через неименованную регистрацию. Мембершип- и прочие провайдеры, а также некоторые другие компоненты полномочий работают через эту регистрацию менеджера агентов.
2. Как для менеджера полномочий, так и для менеджера агентов реализовано внутреннее кеширование объектов полномочий – за счет этого производительность работы с новыми полномочиями должна вырасти. При этом используется новый кеш сервис из нового пакета (есть интерфейс ICacheService и его пока одна технологическая реализация – MemoryCacheService на основе MemoryCache).
3. Хеширование паролей было вынесено в бизнес-сервер для агентов полномочий, поэтому хеширование паролей на прикладном уровне (перед записью агента в БД) нужно везде поубирать!
4. Очистка кеша как у менеджера полномочий, так и у менеджера агентов сейчас выполняется также в бизнес-серверах классов полномочий во время каждой операции создания, изменения или удаления классов полномочий – таким образом обеспечивается актуальность данных в кеше. Если есть программная очистка кеша на прикладном уровне во время этих операций, то можно ее тоже поубирать.
5. При хешировании паролей теперь можно указывать соль и выбирать используемый алгоритм хеширования паролей (`MD5`, `SHA1`, `SHA256` или «пустой»). Это дело конфигурируется в Unity через указание разрешения интерфейса `IPasswordHasher` (в `Md5PasswordHasher`, `Sha1PasswordHasher`, `Sha256PasswordHasher` или `EmptyPasswordHasher`). Соль указывается, если нужно, в конструкторе класса (параметр salt типа string).

<b>Примечание.</b> В менеджере агентов пока не реализованы операции удаления пользователей, ролей, групп (исключать агентов из роли и группы при этом можно, это реализовано).
Конфигурация для новых полномочий должна выглядеть сейчас примерно следующим образом (при установке пакетов секция Unity конфига сконфигурируется примерно так, как ниже в примере):

```xml
<appSettings>
    …
    <add key="DataServiceType" value="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    <add key="DefaultConnectionStringName" value="DefConnStr" />
    <!—Если БД полномочий лежит отдельно от основной, можно сослаться на другую строку соединения-->
    <add key="SecurityConnectionStringName" value="DefConnStr" />
    …
  </appSettings>
    …
  <connectionStrings>
    …
    <add name="DefConnStr" connectionString="СТРОКА СОЕДИНЕНИЯ КУДА НАДО"
      providerName="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService" />
    …
  </connectionStrings>
  …
  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    …
    <container>
      …
      <!-- Конфигурация сервиса кеширования. -->
      <register type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="defaultCacheForApplication" />
        </constructor>
      </register>
      <!-- Конфигурация системы полномочий. -->
         <register name="dataServiceForAuditAgentManagerAdapter" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationStringName" dependencyType="System.String" value="DefConnStr или другое имя строки соединения с БД ПОЛНОМОЧИЙ" />
      </register>
      <register name="dataServiceForSecurityManager" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService">
        <constructor>
          <param name="securityManager" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject">
            <dependency name="securityManagerWithoutRightsCheck" />
          </param>
        </constructor>
        <property name="CustomizationStringName" dependencyType="System.String" value="DefConnStr или другое имя строки соединения с БД ПОЛНОМОЧИЙ" />
      </register>
      <register name="cacheServiceForSecurityManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForSecurityManager" />
        </constructor>
      </register>
      <register name="cacheServiceForAgentManager" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching" mapTo="NewPlatform.Flexberry.Caching.MemoryCacheService, NewPlatform.Flexberry.Caching">
        <lifetime type="singleton" />
        <constructor>
          <param name="cacheName" type="System.String" value="cacheForAgentManager" />
        </constructor>
      </register>
      <register type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="NewPlatform.Flexberry.Security.SecurityManager, NewPlatform.Flexberry.Security">
        <constructor>
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForSecurityManager" />
          </param>
          <param name="enabled" type="System.Boolean" value="true" />
          <param name="useRightsOnObjects" type="System.Boolean" value="false" />
          <param name="useRightsOnAttribute" type="System.Boolean" value="false" />
        </constructor>
      </register>
      <register type="NewPlatform.Flexberry.Security.IAgentManager, NewPlatform.Flexberry.Security" mapTo="NewPlatform.Flexberry.Security.AgentManager, NewPlatform.Flexberry.Security">
        <constructor>
          <param name="dataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business">
            <dependency name="dataServiceForSecurityManager" />
          </param>
          <param name="cacheService" type="NewPlatform.Flexberry.Caching.ICacheService, NewPlatform.Flexberry.Caching">
            <dependency name="cacheServiceForAgentManager" />
          </param>
        </constructor>
      </register>
      <register type="NewPlatform.Flexberry.Security.IPasswordHasher, NewPlatform.Flexberry.Security" mapTo="NewPlatform.Flexberry.Security.Sha1PasswordHasher, NewPlatform.Flexberry.Security">
        <lifetime type="singleton" />
        <constructor />
      </register>
      <register name="securityManagerWithoutRightsCheck" type="ICSSoft.STORMNET.Security.ISecurityManager, ICSSoft.STORMNET.DataObject" mapTo="ICSSoft.STORMNET.Security.EmptySecurityManager, ICSSoft.STORMNET.DataObject">
        <lifetime type="singleton" />
        <constructor />
      </register>
      <register type="ICSSoft.STORMNET.Business.IConfigResolver, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.ConfigResolver, ICSSoft.STORMNET.Business">
        <lifetime type="singleton" />
        <constructor />
      </register>
      …
    </container>
  </unity>
  …
  <system.web>
    …
    <membership defaultProvider="FlexberryMembershipProvider">
      <providers>
        <clear />
        <add name="FlexberryMembershipProvider" type="NewPlatform.Flexberry.Security.FlexberryMembershipProvider, NewPlatform.Flexberry.Security, Version=1.0.0.0, Culture=neutral, PublicKeyToken=b04c483e14c0d306" applicationName="SLAuthSample" />
      </providers>
    </membership>
    <roleManager defaultProvider="FlexberryRoleProvider" enabled="true">
      <providers>
        <clear />
        <add name="FlexberryRoleProvider" type="NewPlatform.Flexberry.Security.FlexberryRoleProvider, NewPlatform.Flexberry.Security, Version=1.0.0.0, Culture=neutral, PublicKeyToken=b04c483e14c0d306" />
      </providers>
    </roleManager>
    <profile defaultProvider="FlexberryProfileProvider" enabled="true" automaticSaveEnabled="false">
      <providers>
        <clear />
        <add name="FlexberryProfileProvider" type="NewPlatform.Flexberry.Security.FlexberryProfileProvider, NewPlatform.Flexberry.Security, Version=1.0.0.0, Culture=neutral, PublicKeyToken=b04c483e14c0d306" />
      </providers>
      <properties>
        <add name="FriendlyName" />
        <add name="AgentKey" />
      </properties>
    </profile>
    …
  </system.web>
````
