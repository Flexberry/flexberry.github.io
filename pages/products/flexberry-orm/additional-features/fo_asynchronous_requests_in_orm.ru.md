---
title: Асинхронные запросы в ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, запрос, асинхронный, загрузка данных
summary: Особенности работы и используемые параметры при ассинхронной загрузке данных
toc: true
permalink: ru/fo_asynchronous_requests_in_orm.html
lang: ru
---

В [ORM](fo_flexberry-orm.html) существует возможность загружать данные в асинхронном режиме (async/await). [SQLDataService](fo_data-service.html) реализует интерфейс `IAsyncDataService`, который описывает асинхронные методы загрузки.

### Асинхронные методы

| Метод                | Описание                              | Параметры                                                                                   |
| -------------------- | ------------------------------------- | ------------------------------------------------------------------------------------------- |
| GetObjectsCountAsync | Загрузить количество объектов         | `LoadingCustomizationStruct` - запрос LCS                                                     |
| LoadObjectAsync      | Загрузка одного объекта данных        | [DataObject](fo_data-object.html) - объект, который нужно загрузить. `View` - представление для загрузки.            |
| LoadObjectsAsync     | Загрузка нескольких объектов данных   | `DataObject[]` - массив объектов, которые нужно загрузить. `View` - представление для загрузки. |
| UpdateObjectAsync    | Сохранение объекта данных             | `DataObject` - объект, который нужно сохранить.                                               |
| UpdateObjectsAsync   | Сохранение нескольких объектов данных | `DataObject[]`` - массив объектов, который нужно сохранить.                                    |

#### Дополнительные параметры

- `clearDataObject` (для загрузки) - очищать объект (`DataObject.Clear`) перед вычиткой (по умолчанию `true`);
- `alwaysThrowException` (для обновления) - сразу же останавливать метод при возникновении ошибки (если `false` - будет произведена попытка выполнить часть запросов несмотря на ошибки; по умолчанию `false`);
- `dataObjectCache` (для загрузки и обновления) - по умолчанию для каждого запроса используется отдельный кеш объектов, но можно передать свой кеш (например, чтобы кешировать объекты между несколькими запросами).

### Особенности работы

Асинхронные методы *изменяют переданные в метод экземпляры объектов*, т.е. работают с объектами как с mutable object. Это требует особого внимания при работе с объектами до завершения операций загрузки/обновления (состояние объектов может измениться). Во избежание ошибок, следует использовать `await` или `Task.Wait` до того, как будет продолжена работа с объектом (либо клонировать исходный объект).

### Примеры использования

#### Загрузка объекта данных по ключу

```csharp
using ICSSoft.STORMNET.Business;
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();

// объект для загрузки
var userToLoad = new Пользователь();
userToLoad.SetExistObjectPrimaryKey(user1.__PrimaryKey); //указываем ключ существующего объекта

// поля для загрузки:
var view = new View { DefineClassType = typeof(Пользователь) };
view.AddProperties(
  Information.ExtractPropertyPath<Пользователь>(x => x.ФИО),
  Information.ExtractPropertyPath<Пользователь>(x => x.ДатаРегистрации));

await DS.LoadObjectAsync(userToLoad, view); //обновляется объект userToLoad (догружаются указанные поля)
```

#### Загрузка нескольких объектов данных с помощью LCS

```csharp
var lcsDateAfter2000 = LoadingCustomizationStruct.GetSimpleStruct(typeof(Пользователь), view);

lcsDateAfter2000.LimitFunction = FunctionBuilder.BuildGreaterOrEqual<Пользователь>(x => x.ДатаРегистрации, new System.DateTime(2020, 01, 01)); // дата регистрации >= 01.01.2020

var loadedObjects = (await ds.LoadObjectsAsync(lcsDateAfter2000)).Cast<Пользователь>();
```

Другие примеры можно посмотреть в тестах [DataServiceAsyncTests](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/blob/3ec3dc517469e6df519035d750a3da6c44a91bac/NewPlatform.Flexberry.ORM.IntegratedTests/ICSSoft.STORMNET.Business/DataServiceAsyncTests.cs)

### Переход на асинхронный код

1. В прикладных функциях заменить `IDataService` на `IAsyncDataService`, либо использовать реализацию (напр. `PostgresDataService` или `SQLDataService`).
2. Вызывать асинхронные методы датасервиса из асинхронного кода (с модификатором `async`):

На текущий момент можно добавить модификатор `async`:

- для контроллеров WebApi в `ODataBackend`;
- для OData функций;
- для метода `Main` в консольных программах/сервисах (код сервиса рекомендуется также сделать асинхронным);
- для библиотек классов;

Нельзя добавить модификатор `async`:

- для бизнес-серверов (OnUpdate/OnDelete/OnInsert);
- для методов класса `Startup.cs`; если вам все-же нужны асинхронные операции в `Startup.cs`, см. статью ["Асинхронный код в Startup ASP.NET Core: 4 способа обхода GetAwaiter().GetResult()"](https://habr.com/ru/company/dododev/blog/496300/) (решение №2 и №3).

#### Преимущества перехода

1.Пропускная способность (throughput) асинхронного кода при нагрузке гораздо выше. Это особенно заметно, когда количество потоков, выполняемых одновременно, превышает thread pool (["Measuring performance for async vs sync code in ASP.NET Core"](https://stackoverflow.com/a/62919974)):

```csharp
5000 sync calls 5546ms 35 threads
5000 async calls 29ms 35 threads
```

Прирост пропуской способности достигается за счёт освобождения потока при вызове `await`. Синхронный код (без async/await) под нагрузкой требует больше потоков (["Async vs Sync Benchmark (.NET)"](https://artemmikulich.medium.com/async-vs-sync-benchmark-net-f1e752a57755)), соответственно его пропускная способность под нагрузкой ниже.

2.Код выполнится быстрее, если задействовать для задачи несколько потоков, работающих одновременно. В этом случае прирост к скорости будет приобретён ценой возросшей нагрузки на одну задачу. Прирост заметен только когда в thread pool есть свободные потоки (при невысокой нагрузке).
3.В UI приложениях (напр. [Windows Forms](fw_landing_page.html)) текущий поток может быть освобождён, и интерфейс не будет блокироваться ("замораживаться").

#### Недостатки

1. Сложность использования в синхронном коде. Асинхронный код можно вызывать через `Task.Run()`, но могут возникнуть проблемы (подробнее об этом в статье ["Avoid using Task.Run for long running work that blocks the thread"](https://github.com/davidfowl/AspNetCoreDiagnosticScenarios/blob/master/AsyncGuidance.md#avoid-using-taskrun-for-long-running-work-that-blocks-the-thread)).
