---
title: Хранение и использование настроек аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_keep-and-use-audit-settings.html
lang: ru
---

# Хранение настроек

Общая структура для хранения настроек аудита приложения:

![Изображение](/images/img/page/AuditWeb/AuditStoreSettings.PNG)

(((
<msg type=note>На текущем этапе реализации аудита используются классы: `AuditAppSetting` и `AuditDSSetting`; класс `AuditClassSetting` используется не в полной мере.</msg>
)))

## Общие настройки аудита (AuditAppSetting)
Общие настройки аудита находятся в классе `AuditService`, куда вычитываются в начале работы приложения.

Общие настройки аудита (`AuditAppSetting`):
* `AppName` – имя приложения (используется [сервисом аудита](efs_audit-win-service.html))
* `AuditEnabled` – включён ли аудит в приложении.
* `IsDatabaseLocal` – [используется ли аудитом локальная база данных](efs_data-service-for-audit.html) (то есть БД приложения совмещена с БД аудита).
* `AuditConnectionStringName` – [имя cтроки подключения к БД аудита](efs_data-service-for-audit.html).
* `AuditWinServiceUrl` – url, где располагается [сервис аудита](efs_audit-win-service.html).
* `WriteSessions` – следует ли сохранять сведения о сессиях пользователей.

## Настройки класса и настройки полей  (AuditClassSetting и AuditFieldSetting)
Настройки класса генерируются непосредственно в код класса объектов (аналогично тому, как в [код класса объектов добавлен атрибут `Views`](fo_static-view-accessors.html)):
* `bool AuditEnabled` - включён ли аудит для класса.
* `bool UseDefaultView` - cледует ли использовать представление по умолчанию при аудите.
* `bool InsertAudit`, `string InsertAuditViewName` - настройки аудита операции создания объекта (имя представления + включён ли аудит для данной операции).
* `bool UpdateAudit`, `string UpdateAuditViewName` - настройки аудита операции изменения объекта (имя представления + включён ли аудит для данной операции).
* `bool SelectAudit`, `string SelectAuditViewName` - настройки аудита операции чтения объекта (имя представления + включён ли аудит для данной операции).
* `bool DeleteAudit`, `string DeleteAuditViewName` - настройки аудита операции удаления объекта (имя представления + включён ли аудит для данной операции).
* `string FormUrl` - URL к форме просмотра объекта (как и в предыдущей версии подсистемы аудита через форму можно будет просмотреть текущее состояние объекта) ('''не используется''').
* `ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode WriteMode` - режим записи данных аудита (синхронный или асинхронный) ('''поддерживается только синхронный''').
* `int PrunningLength` - следует ли и насколько сильно следует обрезать сохраняемые значения аудируемых полей ('''не используется''').
* `bool ShowPrimaryKey` - отображать ли в данных аудита старое и новое значение первичного ключа мастера/детейла.
* `bool KeepOldValue` - следует ли в записи об изменении сохранять старое значение аудируемого поля ('''не используется''').
* `bool Compress ` – следует ли сжимать сохраняемые значения полей ('''не используется''').
* `bool KeepAllValues` – следует ли сохранять только изменяемые поля из представления, либо все поля, входящие в представление ('''не используется''').
* `IAudit` [AuditClassService](efs_rights-and-audit-subsystems.html) - реализация интерфейса [IAudit](efs_i-audit.html), через который необходимо производить аудит конкретного класса.
* `string` [AuditClassConnectionStringName](efs_rights-and-audit-subsystems.html) - [имя строки подключения к БД, посредством которой необходимо писать аудит](efs_data-service-for-audit.html).

```csharp
namespace ICSSoft.STORMNET.Security
{
    public class Agent : ICSSoft.STORMNET.DataObject, IDataObjectWithAuditFields
    {
        ///...
        
        /// <summary>
        /// Audit class settings.
        /// </summary>
        public class AuditSettings
        {
            
            /// <summary>
            /// Включён ли аудит для класса.
            /// </summary>
            public static bool AuditEnabled = true;
            
            /// <summary>
            /// Использовать имя представления для аудита по умолчанию.
            /// </summary>
            public static bool UseDefaultView = false;
            
            /// <summary>
            /// Включён ли аудит операции чтения.
            /// </summary>
            public static bool SelectAudit = false;
            
            /// <summary>
            /// Имя представления для аудирования операции чтения.
            /// </summary>
            public static string SelectAuditViewName = "AuditView";
            
            /// <summary>
            /// Включён ли аудит операции создания.
            /// </summary>
            public static bool InsertAudit = true;
            
            /// <summary>
            /// Имя представления для аудирования операции создания.
            /// </summary>
            public static string InsertAuditViewName = "AuditView";
            
            /// <summary>
            /// Включён ли аудит операции изменения.
            /// </summary>
            public static bool UpdateAudit = true;
            
            /// <summary>
            /// Имя представления для аудирования операции изменения.
            /// </summary>
            public static string UpdateAuditViewName = "AuditView";
            
            /// <summary>
            /// Включён ли аудит операции удаления.
            /// </summary>
            public static bool DeleteAudit = true;
            
            /// <summary>
            /// Имя представления для аудирования операции удаления.
            /// </summary>
            public static string DeleteAuditViewName = "AuditView";
            
            /// <summary>
            /// Путь к форме просмотра результатов аудита.
            /// </summary>
            public static string FormUrl = "";
            
            /// <summary>
            /// Режим записи данных аудита (синхронный или асинхронный).
            /// </summary>
            public static ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode WriteMode = ICSSoft.STORMNET.Business.Audit.Objects.tWriteMode.Synchronous;
            
            /// <summary>
            /// Максимальная длина сохраняемого значения поля (если 0, то строка обрезаться не будет).
            /// </summary>
            public static int PrunningLength = 0;
            
            /// <summary>
            /// Показывать ли пользователям в изменениях первичные ключи.
            /// </summary>
            public static bool ShowPrimaryKey = false;
            
            /// <summary>
            /// Сохранять ли старое значение.
            /// </summary>
            public static bool KeepOldValue = true;
            
            /// <summary>
            /// Сжимать ли сохраняемые значения.
            /// </summary>
            public static bool Compress = false;
            
            /// <summary>
            /// Сохранять ли все значения атрибутов, а не только изменяемые.
            /// </summary>
            public static bool KeepAllValues = false;

            /// <summary>
            /// Cервис аудита, который будет писать аудит системы полномочий.
            /// </summary>
            public static IAudit AuditClassService = SecurityHelper.GetSecurityClassAudit();

            /// <summary>
            /// Имя строки соединения с БД, куда необходимо писать аудит системы полномочий.
            /// </summary>
            public static string AuditClassConnectionStringName = SecurityHelper.GetSecurityClassAuditConnectionStringName();
        }
    }
}
```

### Планы по развитию
'''AuditClassSetting'''

В начале работы приложения настройки класса для аудита будут вычитываться в структуру `AuditClassSetting` ('''на настоящий момент класс `AuditClassSetting` не используется''').

Иногда возникает потребность настроить дополнительный аудит для конкретных объектов (то есть помимо того аудита, что ведётся для соответствующего класса). Например, для объектов `ObjectA`, `ObjectB` класса `Class1`, для которого ведётся только аудит на чтение, добавить аудит на запись.

Как это можно сделать:
* Настройки посредством специальных команд будут задаваться в программном коде и храниться в памяти приложения во время выполнения в заданном новом формате.

Настройки для аудита объектов будут задаваться в программном коде и будут храниться в структуре `AuditClassSetting` и определяться полями:
* `LimitFunction` – [ограничение](fo_limit-function.html), которому должны удовлетворять объекты.
* `View` – [представление](fd_view-definition.html), по которому должны быть выгружены объекты, чтобы на них можно было накладывать ограничение.

Если в упомянутой выше структуре `LimitFunction` и `View` пусты, то это будет означать, что запись содержит настройки, заданные для всего класса.

'''AuditFieldSetting'''

Настройки полей класса определяются программистом в программном коде и хранятся в структуре `AuditFieldSetting`:
* `FieldName` – имя поля, для которого определяется настройка.
* `PrunningLength` – (см. `AuditClassSetting`) если `null`, то берётся значение из соответствующего `AuditClassSetting`.
* `Compress` – (см. `AuditClassSetting`) если `null`, то берётся значение из соответствующего `AuditClassSetting`. 
* `KeepOldValue` - (см. `AuditClassSetting`) если `null`, то берётся значение из соответствующего `AuditClassSetting`.
* `KeepAllValues` - (см. `AuditClassSetting`) если `null`, то берётся значение из соответствующего `AuditClassSetting`.

## Настройки сервисов данных (AuditDSSetting)
Настройки сервисов данных приложения необходимы подсистеме аудита, чтобы сервис аудита мог определить, с помощью какого [сервиса данных](fo_sql-data-service.html) и куда необходимо писать данные аудита.

Информация о [сервисах данных](fo_sql-data-service.html), используемых приложением (`AuditDSSetting`):
* `DataServiceType` – тип сервиса данных.
* `ConnString` – строка соединения сервиса.
* `ConnStringName` – имя строки соединения (имя задаётся программистом для идентификации сервиса данных; далее в конфиге сервиса аудита будет использоваться это имя).

Сервис данных, когда им была выявлена потенциально аудируемая операция, сообщает об этом классу `AuditService`. Если класс `AuditService` принял решение о том, что необходимо провести запись данных аудита, то среди отправляемой [сервису аудита](efs_audit-win-service.html) информации будет имя строки подключения к базе данных приложения ([имя строки подключения определяется](efs_data-service-for-audit.html) из `AuditDSSetting` на основании того, какой сервис данных и с какой строкой подключения обратился к классу `AuditService`).

# Алгоритм определения актуальных настроек аудита
Схема определения, является ли операция аудируемой для класса (с учётом запланированных доработок):

![Изображение](/images/img/page/AuditWeb/AuditFindSetting.PNG)

Если операция является аудируемой, то из найденной настройки берётся представление, после чего для собственных полей, мастеров и детейлов определяются параметры сохранения сведений аудита в следующем порядке:
# Среди настроек для полей в настройке класса по умолчанию ищутся настройки для искомого поля, где интересующие параметры не `null`.
# Если параметры не найдены, то используются значения параметров, указанных в настройке класса по умолчанию.
# Если настройка класса по умолчанию отсутствует, то аналогичный поиск осуществляется для дополнительных настроек, где объект удовлетворяет соответствующим `LimitFunction`.

# Чтение настроек

[AuditService](efs_flexberry-audit-components.html) основные настройки классов объектов получает непосредственно из классов через `reflection`.
Общие настройки аудита приложения загружаются в `AuditService` в начале работы web-приложения в Global.asax.cs, откуда идёт обращение к специализированному классу `[Инициализация аудита](efs_audit-setter.html)`, который отвечает за формирование настроек из config-файла и первичную инициализацию `AuditService`.

Схема чтения настроек для `AuditService`:

![Изображение](/images/img/page/AuditWeb/AuditSettingRead.PNG)

