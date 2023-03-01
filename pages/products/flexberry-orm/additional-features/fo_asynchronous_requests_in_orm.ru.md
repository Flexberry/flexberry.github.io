---
title: Асинхронные запросы в ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, запрос, асинхронный, загрузка данных
summary: Особенности работы и используемые параметры при ассинхронной загрузке данных
toc: true
permalink: ru/fo_asynchronous_requests_in_orm.html
lang: ru
---

В [ORM](fo_flexberry-orm.html) существует возможность загружать данные в асинхронном режиме (async/await). `SQLDataService` реализует интерфейс `IAsyncDataService`, который описывает асинхронные методы загрузки.

#### Асинхронные методы

| Метод                | Описание                              | Параметры                                                                                   |
| -------------------- | ------------------------------------- | ------------------------------------------------------------------------------------------- |
| GetObjectsCountAsync | Загрузить количество объектов         | `LoadingCustomizationStruct` - запрос LCS                                                     |
| LoadObjectAsync      | Загрузка одного объекта данных        | `DataObject` - объект, который нужно загрузить. `View` - представление для загрузки.            |
| LoadObjectsAsync     | Загрузка нескольких объектов данных   | `DataObject[]` - массив объектов, которые нужно загрузить. `View` - представление для загрузки. |
| UpdateObjectAsync    | Сохранение объекта данных             | `DataObject` - объект, который нужно сохранить.                                               |
| UpdateObjectsAsync   | Сохранение нескольких объектов данных | `DataObject[]`` - массив объектов, который нужно сохранить.                                    |

##### Дополнительные параметры

- `clearDataObject` (для загрузки) - очищать объект (`DataObject.Clear`) перед вычиткой (по умолчанию `true`);
- `alwaysThrowException` (для обновления) - сразу же останавливать метод при возникновении ошибки (если `false` - будет произведена попытка выполнить часть запросов несмотря на ошибки; по умолчанию `false`);
- `dataObjectCache` (для загрузки и обновления) - по умолчанию для каждого запроса используется отдельный кеш объектов, но можно передать свой кеш (например, чтобы кешировать объекты между несколькими запросами).

### Особенности работы

Асинхронные методы <u>изменяют переданные в метод экземпляры объектов</u>, т.е. работают с объектами как с mutable object. Это требует особого внимания при работе с объектами до завершения операций загрузки/обновления (состояние объектов может измениться). Во избежание ошибок, следует использовать `await` или `Task.Wait` до того, как будет продолжена работа с объектом (либо клонировать исходный объект).

### Примеры использования

#### Загрузка объекта данных по ключу

```csharp
using ICSSoft.STORMNET.Business;
var DS = DataServiceProvider.DataService as SQLDataService;

// объект для загрузки
var userToLoad = new Пользователь();
userToLoad.SetExistObjectPrimaryKey(user1.\_\_PrimaryKey); //указываем ключ существующего объекта

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

