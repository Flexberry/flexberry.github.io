---
title: UserSettingsService
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry UserSettingsService
toc: true
permalink: ru/fa_user-settings-service.html
lang: ru
---

`UserSettingsService` - это сервис, предназначенный для хранения и управления пользовательскими настройками. Он позволяет загружать, сохранять и удалять различные типы настроек (строковые, целочисленные, логические и т.д.) для различных модулей приложения.

1. Позволяет устанавливать, получать и удалять пользовательские настройки. Реализовано несколько полей с различными стандартными типами - удобнее использовать, не требуется затрат на преобразование типов.
2. Реализовано множество перегрузок методов для удобства применения.
3. Есть общие настройки (Common), которые задаются на всё приложение сразу.
4. Реализована поддержка указания настройки как по именам (пользователя, модуля и настройки), так и по уникальным идентификаторам. Эта реализация позволяет в прикладной части иметь соответствующие справочники, которые могут визуально редактироваться и использоваться для управления настройками.
5. Сервис может использоваться как сборка в рамках приложения, так и как WCF-сервис (возможность в разработке).
6. Имеется набор методов для работы с коллекциями настроек (в разработке). Это позволяет реализовывать функциональность вида: "дай мне все настройки пользователя N, чтобы я положил их в свой кэш; удалю из кэша, когда пользователь выйдет из системы".
7. Написание сервиса ведётся с применением Unit-тестирования (NUnit).

`UserSettingsService` использует отдельную таблицу в базе данных для хранения данных и взаимодействует с ней через IDataService.

## Основные компоненты

__1.Класс UserSettingsService:__

* Наследуется от `DataServiceWrapper` и реализует интерфейс `IUserSettingsService`.
* Обеспечивает потокобезопасный доступ к текущему экземпляру службы через свойство _Current_.

__2.Статическое свойство Current:__

* Предоставляет доступ к единому экземпляру `UserSettingsService`. Если экземпляр еще не создан, он создается автоматически.
* Потокобезопасность обеспечивается с помощью блокировки (`lock`).

__3.Свойства конфигурации:__

* `UseSettings`: Флаг, указывающий, включено ли использование настроек. Значение берется из конфигурационного файла.
* `AppName`: Имя приложения, используется для загрузки данных.

__4.Методы получения имени пользователя из Active Directory (AD):__

* `GetADUserName(string userName)`: Получает полное имя пользователя по его имени учетной записи из AD.
* `GetEnvOrADUserName()`: Получает имя текущего пользователя, используя данные окружения или AD.

## Методы загрузки настроек

__1.Основной метод загрузки настроек:__

* `GetSettings(...)`: Загружает значения настроек по имени и/или идентификатору пользователя и модуля. Поддерживает различные типы значений (строки, текст, числа, булевые значения и т.д.).

__2.Методы загрузки настроек по различным параметрам:__

* `GetAllSettingsByUser(...)`: Загружает все настройки для указанного пользователя.
* `GetAllSettingsNames(...)`: Загружает все имена настроек для указанного модуля и пользователя.
* `GetAllCommonSettings()`: Загружает все общие настройки.
* `GetAllSettingsByModule(...)`: Загружает все настройки для указанного модуля.

## Методы сохранения настроек

__1.Основной метод сохранения настроек:__

* `SetSettings(...)`: Сохраняет значения настроек по имени и/или идентификатору пользователя и модуля. Поддерживает различные типы значений.

__2.Методы сохранения настроек по различным параметрам:__

* `SetStrSetting(...)`, `SetTxtSetting(...)`, `SetIntSetting(...)`, и другие: сохраняют конкретные типы настроек (строки, текст, числа и т.д.).

## Методы удаления настроек

__1.Основной метод удаления настроек:__

* `DeleteSettings(...)`: Удаляет настройки по имени и/или идентификатору пользователя и модуля.

__2.Методы удаления настроек по различным параметрам:__

* `DeleteSettingsByUser(...)`, `DeleteSettingsByModule(...)`, `DeleteCommonSettings(...)`: Удаляют настройки для конкретных пользователей, модулей или общие настройки.

__Пример использования:__

```csharp
// Получение текущего экземпляра сервиса
var userSettingsService = UserSettingsService.Current;

// Загрузка строки настройки
string stringValue;
bool success = userSettingsService.GetStrSetting("JohnDoe", "MyModule", "FontSize", out stringValue);

if (success)
{
    Console.WriteLine($"Font size: {stringValue}");
}
else
{
    Console.WriteLine("Failed to load setting.");
}

// Сохранение строки настройки
bool saveSuccess = userSettingsService.SetStrSetting("JohnDoe", "MyModule", "FontSize", "14px");

if (saveSuccess)
{
    Console.WriteLine("Setting saved successfully.");
}
else
{
    Console.WriteLine("Failed to save setting.");
}

// Удаление настройки
bool deleteSuccess = userSettingsService.DeleteSettings("JohnDoe", null, "MyModule", null, "FontSize", null);

if (deleteSuccess)
{
    Console.WriteLine("Setting deleted successfully.");
}
else
{
    Console.WriteLine("Failed to delete setting.");
}
```

### UserSetting

`UserSetting` представляет собой объект, содержащий информацию о настройке пользователя. Он используется в сервисе `UserSettingsService` для хранения и управления различными типами настроек (строковые, целочисленные, логические и т.д.) в базе данных. Каждый экземпляр UserSetting содержит данные о конкретной настройке, таких как имя приложения, имя пользователя, имя модуля, значение настройки и дата последнего доступа.

Свойства:

* Имя приложения (`AppName`): Строка длиной до 256 символов, представляющая имя приложения.
* Имя пользователя (`UserName`): Строка длиной до 512 символов, представляющая имя пользователя.
* Уникальный идентификатор пользователя (`UserGuid`): Необязательное значение типа Guid, представляющее уникальный идентификатор пользователя.
* Имя модуля (`ModuleName`): Строка длиной до 1024 символов, представляющая имя модуля.
* Уникальный идентификатор модуля (`ModuleGuid`): Необязательное значение типа Guid, представляющее уникальный идентификатор модуля.
* Имя настройки (`SettName`): Строка длиной до 256 символов, представляющая имя настройки.
* Уникальный идентификатор (`SettGuid`): Необязательное значение типа Guid, представляющее уникальный идентификатор настройки.
* Время последнего доступа (`SettLastAccessTime`): Дата и время последнего доступа к настройке.
* Значение строковой настройки (`StrVal`): Строка длиной до 256 символов, представляющая значение строковой настройки.
* Значение текстовой настройки (`TxtVal`): Строка, представляющая значение текстовой настройки.
* Значение целочисленной настройки (`IntVal`): Необязательное значение типа int, представляющее значение целочисленной настройки.
* Значение логической настройки (`BoolVal`): Необязательное значение типа bool, представляющее значение логической настройки.
* Значение настройки типа Guid (`GuidVal`): Необязательное значение типа Guid, представляющее значение настройки типа Guid.
* Значение десятичной настройки (`DecimalVal`): Необязательное значение типа decimal, представляющее значение десятичной настройки.
* Значение даты и времени настройки (`DateTimeVal`): Необязательное значение типа DateTime, представляющее значение настройки типа дата и время.

__Пример использования:__

Пример ниже демонстрирует, как можно использовать `UserSetting` в контексте сервиса `UserSettingsService` для создания и сохранения пользовательских настроек.

```csharp
using ICSSoft.Services;
using ICSSoft.STORMNET;
using System;

namespace ExampleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // Получить текущий экземпляр службы настроек
            IUserSettingsService userSettingsService = UserSettingsService.Current;

            // Создать новый объект настройки
            var userSetting = new UserSetting
            {
                AppName = "MyApplication",
                UserName = "JohnDoe",
                ModuleName = "SettingsModule",
                SettName = "FontSize",
                StrVal = "14px", // Строковое значение настройки
                SettLastAccessTime = DateTime.Now
            };

            // Сохранить настройку с использованием сервиса
            ...

            // Загрузить настройку
            ...

            // Удалить настройку
            ...
        }
    }
}
```

### Интерфейс IUserSettingsService

Интерфейс `IUserSettingsService` предоставляет методы для работы с пользовательскими настройками в приложении. Он позволяет загружать, сохранять и удалять различные типы настроек (строковые, целочисленные, логические и т.д.) для различных модулей приложения. Интерфейс используется сервисом `UserSettingsService`, который реализует все его методы.

__1.Свойства конфигурации:__

* `UseSettings`: Флаг, указывающий, включено ли использование настроек.
* `AppName`: Имя приложения, используется для загрузки данных.
* `CommonModuleName`: Имя модуля для общих настроек.
* `CommonUserName`: Имя пользователя для общих настроек.
* `CommonModuleGuid`: Уникальный идентификатор модуля для общих настроек.
* `CommonUserGuid`: Уникальный идентификатор пользователя для общих настроек.

__2.Методы получения имени пользователя из Active Directory (AD):__

* `GetADUserName(string userName)`: Получает полное имя пользователя по его имени учетной записи из AD.
* `GetEnvOrADUserName()`: Получает имя текущего пользователя, используя данные окружения или AD.

__3.Методы загрузки настроек:__

* `GetSettings(...)`: Загружает значения настроек по имени и/или идентификатору пользователя и модуля. Поддерживает различные типы значений (строки, текст, числа, булевые значения и т.д.).

__4.Методы загрузки всех настроек:__

* `GetAllSettingsByUser(...)`: Загружает все настройки для указанного пользователя.
* `GetAllSettingsNames(...)`: Загружает все имена настроек для указанного модуля и пользователя.
* `GetAllCommonSettings()`: Загружает все общие настройки.
* `GetAllSettingsByModule(...)`: Загружает все настройки для указанного модуля.

__5.Методы загрузки конкретных типов настроек:__

* `GetStrSetting(...)`, `GetTxtSetting(...)`, `GetIntSetting(...)`, `GetBoolSetting(...)`, `GetGuidSetting(...)`, `GetDecimalSetting(...)`, `GetDateTimeSetting(...)`: Загружают конкретные типы настроек (строки, текст, числа, булевые значения и т.д.).

__6.Методы сохранения настроек:__

* `SetSettings(...)`: Сохраняет значения настроек по имени и/или идентификатору пользователя и модуля. Поддерживает различные типы значений.
* `SetStrSetting(...)`, `SetTxtSetting(...)`, `SetIntSetting(...)`, `SetBoolSetting(...)`, `SetGuidSetting(...)`, `SetDecimalSetting(...)`, `SetDateTimeSetting(...)`: Сохраняют конкретные типы настроек (строки, текст, числа, булевые значения и т.д.).

__7.Методы удаления настроек:__

* `DeleteSettings(...)`: Удаляет настройки по имени и/или идентификатору пользователя и модуля.
* `DeleteSettingsByUser(...)`, `DeleteSettingsByModule(...)`, `DeleteCommonSettings(...)`: Удаляют настройки для конкретных пользователей, модулей или общие настройки.

__Пример использования:__

```csharp
using ICSSoft.Services;
using System;

namespace ExampleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // Получить текущий экземпляр службы настроек
            IUserSettingsService userSettingsService = UserSettingsService.Current;

            // Проверить, включены ли настройки
            if (userSettingsService.UseSettings)
            {
                Console.WriteLine("Настройки включены.");
            }
            else
            {
                Console.WriteLine("Настройки отключены.");
            }

            // Загрузить строковое значение настройки
            string stringValue;
            bool success = userSettingsService.GetStrSetting("JohnDoe", "MyModule", "FontSize", out stringValue);

            if (success && stringValue != null)
            {
                Console.WriteLine($"Значение шрифта: {stringValue}");
            }
            else
            {
                Console.WriteLine("Не удалось загрузить настройку.");
            }

            // Сохранить строковое значение настройки
            bool saveSuccess = userSettingsService.SetStrSetting("JohnDoe", "MyModule", "FontSize", "14px");

            if (saveSuccess)
            {
                Console.WriteLine("Настройка успешно сохранена.");
            }
            else
            {
                Console.WriteLine("Не удалось сохранить настройку.");
            }

            // Удалить настройку
            bool deleteSuccess = userSettingsService.DeleteSettings("JohnDoe", null, "MyModule", null, "FontSize", null);

            if (deleteSuccess)
            {
                Console.WriteLine("Настройка успешно удалена.");
            }
            else
            {
                Console.WriteLine("Не удалось удалить настройку.");
            }
        }
    }
}
```

### Бизнес-сервер

`FlexberryUserSettingBS` представляет собой бизнес-сервер, предназначенный для обработки изменений объектов типа `FlexberryUserSetting`. Он обеспечивает контроль за корректностью данных при создании и редактировании настроек пользователей. В частности, он проверяет, что текущий пользователь соответствует пользователю, связанному с изменяемой записью. `FlexberryUserSettingBS` предоставляет удобный механизм для управления пользовательскими настройками, обеспечивая контроль за корректностью данных. С его помощью можно легко создавать и редактировать настройки пользователей, соблюдая правила безопасности и целостности данных.

`OnUpdateFlexberryUserSetting(FlexberryUserSetting updatedObject)` - метод для обработки изменения объекта `FlexberryUserSetting`. Он устанавливает текущего пользователя для новых записей и проверяет соответствие пользователя для изменяемых записей. Вызывается при создании или редактировании объекта `FlexberryUserSetting`.

```csharp
public DataObject[] OnUpdateFlexberryUserSetting(FlexberryUserSetting updatedObject)
```

__Параметры:__

`updatedObject`: Объект типа `FlexberryUserSetting`, который подлежит изменению.

__Возвращаемое значение:__

Массив объектов типа `DataObject`, которые требуется сохранить. В данном случае возвращается пустой массив, так как дополнительных объектов для сохранения нет.

__Исключения:__

`System.Exception`: Исключение выбрасывается, если пользователь, связанный с изменяемой записью, не совпадает с текущим пользователем.

__Пример использования:__

```csharp
using ICSSoft.Services;
using ICSSoft.STORMNET;
using NewPlatform.Flexberry.ORM.ODataService.UserSettingsService;

namespace ExampleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // Создать новый объект настройки
            var newSetting = new FlexberryUserSetting
            {
                ModuleName = "MyModule",
                SettName = "FontSize",
                StrVal = "14px"
            };

            // Получить текущий экземпляр бизнес-сервера
            var businessServer = new FlexberryUserSettingBS();

            try
            {
                // Сохранить новую настройку (она будет создана с текущим пользователем)
                var savedObjects = businessServer.OnUpdateFlexberryUserSetting(newSetting);
                Console.WriteLine("Настройка успешно создана.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при создании настройки: {ex.Message}");
            }

            // Загрузить существующую настройку для редактирования
            var existingSetting = LoadExistingSetting(); // Предполагаемый метод загрузки существующей записи
            existingSetting.StrVal = "16px";

            try
            {
                // Редактируем существующую настройку (проверка на соответствие пользователя)
                var savedObjects = businessServer.OnUpdateFlexberryUserSetting(existingSetting);
                Console.WriteLine("Настройка успешно обновлена.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при обновлении настройки: {ex.Message}");
            }
        }

        static FlexberryUserSetting LoadExistingSetting()
        {
            // Примерный код загрузки существующей записи из базы данных
            // Реализуется в соответствии требованиями проекта
            return new FlexberryUserSetting
            {
                UserName = "Domain\\CurrentUserName", // Проверить, что имя пользователя соответствует текущему пользователю
                ModuleName = "MyModule",
                SettName = "FontSize",
                StrVal = "14px"
            };
        }
    }
}
```

### FlexberryUserSetting

`FlexberryUserSetting` представляет собой расширение стандартного класса `UserSetting`, предназначенное для удобной работы с настройками пользователя в клиентском коде. Он предоставляет предопределенные представления (views) и использует бизнес-сервер для обработки событий, связанных с изменениями объектов этого класса.

__Атрибуты класса:__

`[ClassStorage("UserSetting")]`: Указывает, что данные этого класса хранятся __в таблице UserSetting__.
`[BusinessServer(typeof(FlexberryUserSettingBS), DataServiceObjectEvents.OnAllEvents)]`: Указывает, что для обработки событий, связанных с объектами этого класса, используется бизнес-сервер FlexberryUserSettingBS.
`[View(...)]` : Определяет предопределенное представление `FlexberryUserSettingE`, которое содержит все основные свойства объекта.

__Свойства класса:__

Все свойства класса наследуются от базового класса `ICSSoft.Services.UserSetting`. Они включают:

* `AppName`: Имя приложения.
* `UserName`: Имя пользователя.
* `UserGuid`: Уникальный идентификатор пользователя.
* `ModuleName`: Имя модуля.
* `ModuleGuid`: Уникальный идентификатор модуля.
* `SettName`: Имя настройки.
* `SettGuid`: Уникальный идентификатор настройки.
* `StrVal`: Значение строковой настройки.
* `TxtVal`: Значение текстовой настройки.
* `IntVal`: Значение целочисленной настройки.
* `BoolVal`: Значение логической настройки.
* `GuidVal`: Значение настройки типа Guid.
* `SettLastAccessTime`: Дата и время последнего доступа к настройке.
* `DecimalVal`: Значение десятичной настройки.
* `DateTimeVal`: Значение настройки типа дата и время.

__Внутренний класс Views:__

Класс `Views` содержит статическое свойство `FlexberryUserSettingE`, которое возвращает предопределенное представление `FlexberryUserSettingE`.

```csharp
using ICSSoft.STORMNET;
using ICSSoft.STORMNET.Business;
using NewPlatform.Flexberry;

namespace ExampleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // Получить текущий экземпляр службы настроек
            IUserSettingsService userSettingsService = UserSettingsService.Current;

            // Создать новый объект настройки
            var newSetting = new FlexberryUserSetting
            {
                AppName = "MyApplication",
                UserName = "JohnDoe",
                ModuleName = "SettingsModule",
                SettName = "FontSize",
                StrVal = "14px" // Строковое значение настройки
            };

            // Сохранить новую настройку с использованием сервиса
            bool saveResult = userSettingsService.SetStrSetting("JohnDoe", "SettingsModule", "FontSize", "14px");

            if (saveResult)
            {
                Console.WriteLine("Настройка успешно сохранена.");
            }
            else
            {
                Console.WriteLine("Не удалось сохранить настройку.");
            }

            // Загрузить строковое значение настройки
            string loadedValue;
            bool loadResult = userSettingsService.GetStrSetting("JohnDoe", "SettingsModule", "FontSize", out loadedValue);

            if (loadResult && loadedValue != null)
            {
                Console.WriteLine($"Загруженное значение настройки: {loadedValue}");
            }
            else
            {
                Console.WriteLine("Не удалось загрузить настройку.");
            }

            // Удалить настройку
            bool deleteResult = userSettingsService.DeleteSettings("JohnDoe", null, "SettingsModule", null, "FontSize", null);

            if (deleteResult)
            {
                Console.WriteLine("Настройка успешно удалена.");
            }
            else
            {
                Console.WriteLine("Не удалось удалить настройку.");
            }

            // Примечания по работе с представлениями
            // Для получения предопределенного представления FlexberryUserSettingE можно использовать следующий код:
            var view = FlexberryUserSetting.Views.FlexberryUserSettingE;

            // Теперь используем это представление для загрузки данных
            DataObject[] dataObjects = userSettingsService.DataService.LoadObjects(
                LoadingCustomizationStruct.GetSimpleStruct(typeof(FlexberryUserSetting), view));

            foreach (var obj in dataObjects)
            {
                var setting = (FlexberryUserSetting)obj;
                Console.WriteLine($"Настройка: {setting.SettName}, Значение: {setting.StrVal}");
            }
        }
    }
}
```

### FlexberryAdvLimit

`FlexberryAdvLimit` представляет собой объект, используемый для управления ограничениями (limits) в приложении. Он позволяет хранить и обрабатывать информацию о пользователе, модуле, имени и значении ограничения. Класс поддерживает различные представления (views), которые можно использовать для выборки данных из базы данных.

__Атрибуты класса:__

* [PublishName("FlexberryAdvLimit")] : Указывает имя для публикации.
* [ClassStorage("STORMAdvLimit")] : Указывает таблицу в базе данных, где хранятся данные этого класса.
* [AutoAltered()] : Автоматическое изменение класса.
* [AccessType(ICSSoft.STORMNET.AccessType.none)] : Указывает доступ к данным.
* [View(...)] : Определяет предопределенные представления для класса.

__Свойства класса:__

* `User`: Имя пользователя (до 256 символов).
* `Published`: Логический флаг, указывающий на то, опубликовано ли ограничение.
* `Module`: Имя модуля (до 256 символов).
* `Name`: Имя ограничения (до 256 символов).
* `Value`: Значение ограничения.

__Внутренний класс Views:__

Класс `Views` содержит статические свойства для получения предопределенных представлений (`FlexberryAdvLimitE` и `FlexberryAdvLimitL`).

__Пример использования:__

Пример ниже демонстрирует, как можно использовать класс `FlexberryAdvLimit в` контексте сервиса UserSettingsService для создания и управления ограничениями.

```csharp
using System;
using ICSSoft.STORMNET;
using NewPlatform.Flexberry;

namespace ExampleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            // Создать новый объект ограничения
            var newLimit = new FlexberryAdvLimit
            {
                User = "JohnDoe",
                Published = true,
                Module = "MyModule",
                Name = "MaxUsers",
                Value = "100"
            };

            // Сохранить новое ограничение в базе данных
            using (IDataService dataService = new YourDataServiceImplementation())
            {
                dataService.UpdateObject(newLimit);
                Console.WriteLine("Ограничение успешно сохранено.");
            }

            // Загрузить существующее ограничение по имени и модулю
            string moduleName = "MyModule";
            string limitName = "MaxUsers";
            FlexberryAdvLimit loadedLimit = LoadLimitByNameAndModule(moduleName, limitName);

            if (loadedLimit != null)
            {
                Console.WriteLine($"Загруженное ограничение: {loadedLimit.Name}, Значение: {loadedLimit.Value}");
            }
            else
            {
                Console.WriteLine("Не удалось загрузить ограничение.");
            }

            // Примечания по работе с представлениями
            var view = FlexberryAdvLimit.Views.FlexberryAdvLimitE;

            // Теперь используем это представление для загрузки данных
            using (IDataService dataService = new YourDataServiceImplementation())
            {
                var limits = dataService.LoadObjects<FlexberryAdvLimit>(view);
                foreach (var limit in limits)
                {
                    Console.WriteLine($"Ограничение: {limit.Name}, Значение: {limit.Value}");
                }
            }
        }

        static FlexberryAdvLimit LoadLimitByNameAndModule(string moduleName, string limitName)
        {
            using (IDataService dataService = new YourDataServiceImplementation())
            {
                var view = FlexberryAdvLimit.Views.FlexberryAdvLimitE;
                var filter = new ExternalLangDef().Function(
                    ExternalLangDef.LanguageDef.funcAND,
                    new ExternalLangDef().Function(
                        ExternalLangDef.LanguageDef.funcEQ,
                        new ExternalLangDef().GetVariableDef("ModuleName"),
                        moduleName),
                    new ExternalLangDef().Function(
                        ExternalLangDef.LanguageDef.funcEQ,
                        new ExternalLangDef().GetVariableDef("Name"),
                        limitName));

                var limits = dataService.LoadObjects<FlexberryAdvLimit>(filter, view);
                return limits.FirstOrDefault();
            }
        }
    }
}
```

```csharp
var view = FlexberryAdvLimit.Views.FlexberryAdvLimitE;
```

Получение предопределенного представления `FlexberryAdvLimitE` может быть полезно при выполнении операций загрузки или обновления данных, когда требуется выбрать определенные поля объекта, а также при выполнении операций загрузки или обновления данных, когда требуется выбрать определенные поля объекта. Представление `FlexberryAdvLimitE` включает все основные свойства объекта, а представление `FlexberryAdvLimitL` включает только часть свойств.

`FlexberryAdvLimit` предоставляет удобный интерфейс для работы с ограничениями в приложении. Он упрощает процесс создания, загрузки, сохранения и удаления ограничений, а также обеспечивает использование различных представлений для гибкого выбора необходимых полей объекта при загрузке данных.
