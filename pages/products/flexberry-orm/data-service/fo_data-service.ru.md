---
title: Сервис данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, загрузка объектов данных, обновление объектов данных
summary: Особенности работы с объектами данных с использованием сервиса данных
toc: true
permalink: ru/fo_data-service.html
lang: ru
---

**Сервис данных** - компонент Flexberry ORM, обеспечивающий запись и чтение объектов данных в хранилище. В объектной модели Flexberry ORM сервис данных представлен классом, реализующим интерфейс `ICSSoft.STORMNET.Business.IDataService`.
Поддерживается несколько [стандартных сервисов данных](fo_standard-data-services.html), и при необходимости пользователями могут быть [разработаны](fo_implement-custom-ds.html) новые, удовлетворяющие специфическим требованиям (например, наследуя классы `ICSSoft.STORMNET.Business.ODBCDataService.ODBCDataService` или `ICSSoft.STORMNET.Business.`[`SQLDataService`](fo_sql-data-service.html)).

{% include important.html content="При обращении к сервису данных в программном коде рекомендуется производить через интерфейс `ICSSoft.STORMNET.Business.IDataService`, т.к. это гарантирует  независимость кода от типа хранилища данных." %}

Перед использование сервиса данных в программном коде его необходимо [настроить тем или иным способом](fo_construction-ds.html).

Следует __обратить внимание__ на следующие моменты:

* Существует возможность настроить сервис данных таким образом, чтобы на регистрозависимом источнике данных приложение вело себя как при работе с [регистронезависимым источником](fo_insensitivity-register-ds.html). 
* Сервис данных [особым образом работает с логическим типом](fo_interpretation-boolean-null.html).

## Основные возможности сервиса данных

Перед обращением в программном коде к сервису данных на него необходимо получить [ссылку](fo_ds-provider.html). При одновременном использовании нескольких хранилищ необходимо учесть некоторые [особенности](fo_multibase.html).

Интерфейс `ICSSoft.STORMNET.Business.IDataService` содержит следующие основные группы методов:

* __Методы с именами типа__ `UpdateXXXXXXXX`, предназначенные для приведения хранилища данных ([обновление, удаление, создание записей](fo_object-status.html)) в соответствие с переданными одним или несколькими объектами данных:
    * `UpdateObject` - обновление одного объекта.
    * `UpdateObjects` - обновление нескольких объектов.
* __Методы с именами типа__ `LoadXXXXXXXX`, предназначенные для чтения одного, либо нескольких объектов данных.
    * `LoadObject` - загрузка объекта (в зависимости от параметров с помощью данного метода можно осуществлять [дочитку объекта](fo_additional-loading.html)).
    * `LoadObjects` - загрузка объектов (в т.ч. перегрузки метода позволяют реализовать [порционное стение](fo_reading-portion.html), [чтение принадлежащих различным классам объектов в одном представлении](fo_reading-several-types-objects.html)).
* __Загрузка без создания объектов__ `LoadStringedObjectView`.
* __Метод получения количества объектов__, удовлетворяющих условию `GetObjectsCount`.

Большинство методов чтения/записи имеют перегрузки, принимающие дополнительный параметр [DataObjectCache](fo_context-sensitive-cache.html), позволяющий поддерживать одну инстанцию для нескольких экземпляров объектов. Передавать `DataObjectCache` в методы зачитки нужно в том случае, если какой-то объект уже существует в памяти и после зачитки все ссылки на него должны указывать именно на этот объект. В методы обновления данный параметр передается для верной расстановки ссылок на экземпляры данных из кэша в связи с тем, что после сохранения в хранилище происходит обновление свойств объекта данных и инициализация его копии данных.

Подробнее об особенностях использовании кэша см. в статье [Контекстно-зависимый кэш объектов данных](fo_context-sensitive-cache.html).

Ниже, при рассмотрении методов, перегрузки с параметром `DataObjectCache` отдельно не описываются.

## Загрузка объектов данных

Для загрузки нескольких объектов данных служат перегрузки метода `LoadObjects`.

### Загрузка объектов данных по представлению или массиву представлений

* Загружаются все объекты данных, доступные в хранилище. При этом выполняется загрузка только тех свойств, которые указаны в [представлении](fd_view-definition.html).

```csharp
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View dataObjectView);
```

* Происходит последовательный вызов метода с параметром - представление для каждого элемента массива. Практическая применимость данной перегрузки неочевидна.

```csharp
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View[] dataObjectViews);
```

#### Параметры

`dataObjectView(s)` - представление или массив представлений

### Загрузка объектов данных по настроечной структуре для выборки LoadingCustomizationStruct (массиву структур)

* Данная перегрузка метода позволяет тонко настроить выборку загружаемых объектов за счёт использования структуры [LoadingCustomizationStruct](fo_loading-customization-struct.html): 

    * указать представление (вертикальное ограничение выборки: загрузка только определенных свойств), 
    * количество возвращаемых строк (для реляционных хранилищ ограничение `<nowiki>TOP</nowiki>` в `SELECT`), 
    * параметры сортировки возвращаемых результатов (`ORDER BY`),
    * ограничения на вычитываемые объекты данных (`WHERE`) 
    * и т.д.. Подробнее см. описание [LoadingCustomizationStruct](fo_loading-customization-struct.html).
 
 [Пример использования](fo_load-limitation-example.html). 

_Замечание_: Данная перегрузка позволяет реализовать [чтение принадлежащих различным классам объектов в одном представлении](fo_reading-several-types-objects.html).

```csharp
// 1.
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct);

// 2.
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct, DataObjectCache DataObjectCache);
```

* Происходит последовательный вызов метода с параметром - `LoadingCustomizationStruct` для каждого элемента массива. Практическая применимость данной перегрузки неочевидна.

```csharp
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct[] customizationStructs);
```

#### Параметры

`customizationStruct(s)` - [настроечная структура для выборки](fo_loading-customization-struct.html) (массив структур)

### Загрузка объектов с использованием состояния вычитки (для реализации порционного чтения)

* Получение первой порции при [порционном чтении](fo_reading-portion.html). Кроме порции объектов данных, сервис данных возвращает некоторое состояние чтения. Это состояние передается  сервису данных для получения очередных порций при последующих вызовах (см. Загрузка одного объекта данных). 

```csharp
// 1.
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct,ref object State);

// 2.
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct, ref object State, DataObjectCache DataObjectCache);
```

* Получение очередных порций при [порционном чтении](fo_reading-portion.html). Должен предшествовать вызов в указанной выше перегрузке.

```csharp
// 1.
ICSSoft.STORMNET.DataObject[] LoadObjects(ref object State);

// 2.
ICSSoft.STORMNET.DataObject[] LoadObjects(ref object State, DataObjectCache DataObjectCache);
``` 

#### Параметры

* `State` - Состояние вычитки(для последующей дочитки)
* `customizationStruct` - [настроечная структура для выборки](fo_loading-customization-struct.html)

_Примечание_: Размер порции может быть задан с помощью параметра `LoadingBufferSize` структуры [LoadingCustomizationStruct](fo_loading-customization-struct.html).

## Загрузка одного объекта данных

Для загрузки одного объекта данных служат перегрузки метода `LoadObject`. 
Вычитка свойств из хранилища осуществляется по заданному в объекте данных [первичному ключу](fo_primary-keys-objects.html).

Примеры использования:

* [Обработка одного объекта](fo_processing-one-object.html),
* [Пример загрузки и изменения объекта](fo_load-alter-objects.html).

### Загрузка объекта данных по первичному ключу

Будут загружены только собственные свойства объекта. При отсутствии в хранилище данных объекта с заданным первичным ключом будет выдано исключение `CantFindDataObjectException`. Не следует использовать данную перегрузку метода для дочитки объекта данных, для этого применимы перегрузки методы с дополнительными параметрами.

```csharp
// 1.
void LoadObject(ICSSoft.STORMNET.DataObject dobject)

// 2.
void LoadObject(ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache)
```

#### Параметры

`dobject` - Объект данных, который требуется загрузить

### Загрузка объекта данных по представлению

Выполняется загрузка только тех свойств, которые указаны в [представлении](fd_view-definition.html).

```csharp
// 1.
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject)

// 2.
void LoadObject(void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject)

// 3.
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache)

// 4.
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache)
```

#### Параметры

`dataObjectView(dataObjectViewName)` - [представление](fd_view-definition.html) (имя представления)

### Загрузка объекта данных с указанием дополнительных параметров

Данная перегрузка метода может быть использована, в частности, для выполнения [догрузки свойств объекта](fo_additional-loading.html).

```csharp
// 1.
void LoadObject(ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject)

// 2.
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject)

// 3.
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject)

// 4.
void LoadObject(ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache) 

// 5.
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache)

// 6.
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache)
```

#### Параметры

* `ClearDataObject` - очищать ли объект
* `CheckExistingObject` - проверять ли существование объекта в хранилище (если указать `true`, при отсутствии объекта в базе будет выдано исключение типа `CantFindDataObjectException`)

## Обновление одного объекта данных

Для обновления одного объекта данных служат перегрузки метода `UpdateObject`.

Примеры использования:

* [Обработка одного объекта](fo_processing-one-object.html),
* [Пример загрузки и изменения объекта](fo_load-alter-objects.html).

Если перед выполнением сохранения требуется выполнение определенных действий, они могут быть реализованы в бизнес-сервере [указанным способом](fo_actions-saving-object.html).

Следует иметь в виду, что сохранение объекта может вызвать [сохранение связанных с ним объектов](fo_update-related-objects.html).

Обновление объекта данных.

```csharp
// 1.
void UpdateObject(ICSSoft.STORMNET.DataObject dobject) 

// 2.
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject) 

// 3.
void UpdateObject(ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) 

// 4.
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) 

// 5.
void UpdateObject(ICSSoft.STORMNET.DataObject dobject, bool AlwaysThrowException) 

// 6.
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, bool AlwaysThrowException) 

// 7.
void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache, bool AlwaysThrowException) 
```

#### Параметры

* `dobject`  - объект данных, который требуется обновить 
* `DataObjectCache` - кеш объектов
* `AlwaysThrowException` - Если произошла ошибка в базе данных, не пытаться выполнять других запросов, сразу взводить ошибку и откатывать транзакцию 

## Обновление нескольких объектов данных

Для обновления нескольких объектов данных служат перегрузки метода `UpdateObjects`.
Запросы для всех обновляемых объектов выполняются в единой транзакции.

Обновляемые объекты данных могут быть как однотипными, так и разнотипными. В метод они передаются параметром — одномерным массивом типа [DataObject](fo_data-object.html)[]. 
В общем случае сервис данных умеет сам выстраивать порядок запросов на обновление объектов данных. Но возможны ситуации, когда для связанных объектов важен порядок следования объектов в массиве, подробная информация о порядке сохранения объектов приведена в статье [Обработка множества объектов](fo_processing-multiple-objects.html).

Следует иметь в виду, что для каждого элемента массива его сохранение может вызвать [сохранение связанных с ним объектов](fo_update-related-objects.html).

[Пример использования](fo_instantiate-persist-objects.html).

Обновление нескольких объектов данных в хранилище.

```csharp
// 1.
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects)

// 2.
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, bool AlwaysThrowException)

// 3.
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, DataObjectCache DataObjectCache)

// 4.
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, DataObjectCache DataObjectCache, bool AlwaysThrowException)
```

#### Параметры

* `objects` - объекты для обновления
* `DataObjectCache` - кеш объектов
* `AlwaysThrowException` - если произошла ошибка в базе данных, не пытаться выполнять других запросов, сразу взводить ошибку и откатывать транзакцию

## Загрузка без создания объектов

Можно выполнить загрузку из хранилища без создания экземпляров: каждый объект в этом случае представлен в виде строки из значений свойств с разделителями. Используется, когда не требуется редактирование объектов. Данный метод намного быстрее, чем создание объектов при загрузке в методе `LoadObjects`. Для загрузки в виде строкового представления предназначены перегрузки метода `LoadStringedObjectView`.

[Пример использования](fo_load-limitation-example.html).

### Загрузка без создания объектов

__Возвращаемый результат__: массив структур `ObjectStringDataView`

```csharp
ObjectStringDataView[] LoadStringedObjectView(char separator, LoadingCustomizationStruct customizationStruct)
```

#### Параметры

* `separator` -  разделитель в строках 
* `customizationStruct` -  настроичная структура для выборки [LoadingCustomizationStruct](fo_loading-customization-struct.html)

__Примечание__: порядок следования свойств объекта данных в результирующей строке с разделителями задаётся парметром `ColumnsOrder` структуры `customizationStruct`.

### Загрузка без создания объектов с использованием состояния вычитки (для реализации порционного чтения)

1.`LoadStringedObjectView`

* Получение первой порции при [порционном чтении](fo_reading-portion.html). Кроме порции данных, сервис данных возвращает некоторое состояние чтения. Это состояние передается  сервису данных для получения очередных порций при последующих вызовах.

```csharp
ObjectStringDataView[] LoadStringedObjectView(char separator, LoadingCustomizationStruct customizationStruct, ref object State)
```

* Получение очередных порций при [порционном чтении](fo_reading-portion.html). Должен предшествовать вызов в предыдущей перегрузке.

```csharp
ObjectStringDataView[] LoadStringedObjectView(ref object state)
```

2.`CompleteLoadStringedObjectView`

Корректное завершения операции порционного чтения при `LoadStringedObjectView`.

```csharp
void CompleteLoadStringedObjectView(ref object state)
```

#### Параметры

* `State` - Состояние вычитки(для последующей дочитки)
* `customizationStruct` - [настроечная структура для выборки](fo_loading-customization-struct.html)

__Примечание__: Размер порции может быть задан с помощью параметра `LoadingBufferSize` структуры [LoadingCustomizationStruct](fo_loading-customization-struct.html).

## Получение количества объектов, удовлетворяющих запросу

[Пример использования](fo_load-limitation-example.html). 

Возвращает количество объектов удовлетворяющих условиям выборки,не выполняя загрузку данных.

`GetObjectsCount`

```csharp
int GetObjectsCount(LoadingCustomizationStruct customizationStruct)
```

#### Параметры

`customizationStruct` - настроечная структура для выборки [LoadingCustomizationStruct](fo_loading-customization-struct.html)  

## Использование SQL при работе с сервисом данных

В некоторых ситуациях возможностей сервиса данных недостаточно для решения специфических задач, в таких случаях есть возможность [непосредственного выполнения SQL-запроса](fo_sql-query.html) и [изменение автоматически построенного запроса](fo_intercept-formation-sql-query.html).
