---
title: SQLDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_s-q-l-data-service.html
folder: products/flexberry-orm/
lang: ru
---


<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll
* '''Предназначение''': Базовый абстрактный класс сервиса данных для реляционных хранилищ.
</td>
</tr></tbody></table></a>
</div>
# Описание `SQLDataService`
[Сервис данных](data-service.html), работающий с реляционными хранилищами.

Является абстрактным классом, от него наследуется 
* `[MSSQLDataService](m-s-s-q-l-data-service.html)`,
* `[ODBCDataService](o-d-b-c-data-service.html)`,
* `[OracleDataService](oracle-data-service.html)`,
* `[PostgresDataService](postgres-data-service.html)`.

# Основные возможности `SQLDataService`
Поскольку `SQLDataService` реализует интерфейс `ICSSoft.STORMNET.Business.IDataService`, то он поддерживает все [методы, определенные в данном интерфейсе](data-service.html).

Кроме того, `SQLDataService`, расширяя базовую функциональность, предоставляет дополнительные возможности. в т.ч. обусловленные реляционной природой хранилища:
* [#specificloadobject|дополнительные способы загрузки данных],
* [#updateobjectsordered|дополнительные способы обновления данных],
* [#byextconn|выполнение стандартных действий по загрузке и обновлению объектов, но в рамках указанных коннекции и транзакции],
* [#generatesql|генерация текстов SQL-запросов], в т.ч. получение текстов стандартных генерируемых запросов без выполнения операций с БД и формирование SQL-выражений, являющихся частью запроса (например, формирование WHERE, JOIN  и т.п.),
* [#querytext|выполнение операций с прямым указанием текста запроса].

Следует отметить, что часть методов лишь декларируется в классе `SQLDataService`, а их реализация должна быть выполнена в классах-наследниках.
Далее рассмотрим подробнее эти методы.

## [anchor|#SpecificLoadObject]Дополнительные способы загрузки данных

{| border="1"
! Метод
! Описание
|- 
|  `LoadRawValues` 
| __Назначение__: Загрузка без создания объектов при необходимости получить DISTINCT данные.
 Стандартные методы зачитки получают `[PrimaryKey](primary-keys-objects.html)` для возможности правильного создания объектов данных. Соответственно, `DISTINCT` с `[PrimaryKey](primary-keys-objects.html)` в запросе не даёт эффекта ([ключи](primary-keys-objects.html) уникальные, поэтому никакой группировки результатов не произойдёт - они все разные). Данный метод возвращает обычный двумерный массив (как это делает `ADO.NET`).

__Параметры__:
`customizationStruct` - Структура [LoadingCustomizationStruct](loading-customization-struct.html), определяющая, что и как грузить. Должен быть указан параметр `Distinct`. 

__Сигнатура__:
 ```
scharp virtual public object[][] LoadRawValues(LoadingCustomizationStruct customizationStruct) 
``` 

__Пример__:
 ```cs
SQLDataService ds = (SQLDataService)DataServiceProvider.DataService;
View v = new View();
v.DefineClassType = typeof (Door);
v.AddProperty("Street.Name");
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Door), v);
lcs.Distinct = false; //Получим двумерный массив свойств без DISTINCT в верхнем SELECT-е
object[][] loadDistinctValues = ds.LoadRawValues(lcs);
string s = loadDistinctValues.Length.ToString();

lcs.Distinct = true; //Получим двумерный массив свойств с DISTINCT в верхнем SELECT-е
object[][] loadDistinctValues1 = ds.LoadRawValues(lcs);
string s1 = loadDistinctValues1.Length.ToString();

MessageBox.Show(s + " " + s1);
```
<!--|- 
|  Загрузка без создания объектов ...
| ```
scharp virtual public ObjectStringDataView[] LoadValues(... 
```
| `...` - ...
|  -->
|- 
|  `SecondLoadObject` 
| __Назначение__: Метод для [AdditionalLoadingDataObject#usingsecondloadobject|дочитки объекта данных].
 Загруженные ранее свойства не затираются, изменённые свойства не затираются. Подменяются поштучно свойства копии данных. Перед использованием рекомендуется ознакомиться с описанием в [AdditionalLoadingDataObject#usingsecondloadobject|статье].

__Параметры__:
 `dataObjectView` - представление

`dataObject` - бъект данных, который требуется загрузить

`checkExistingObject` - проверять ли существование объекта в хранилище

`dataObjectCache` - кэш

__Сигнатура__:
 ```cs
protected virtual void SecondLoadObject(View dataObjectView, DataObject dataObject, bool checkExistingObject, DataObjectCache dataObjectCache) ```
|}

## [anchor|#UpdateObjectsOrdered]Дополнительные способы обновления данных

{| border="1"
! Метод
! Описание
|- 
| `UpdateObjectsOrdered`
| __Назначение__: Обновить объекты данных в указанном порядке. 

`SQLDataService` умеет сам выстраивать порядок запросов на обновление объектов данных. Особенно это актуально, когда есть большое количество разнотипных объектов в одной транзакции. К сожалению, не всегда есть возможность автоматизированно вычислить правильный порядок запросов. В первую очередь, это относится к ситуациям, когда в графе типов есть циклы. Для решения этой проблемы предлагается использовать данный метод, который выполняет обновление объектов последовательно в том порядке, в котором они приходят в этот метод.

__Параметры__:
 `objects` - обновляемые объекты 

`alwaysThrowException` - Если произошла ошибка в базе данных, не пытаться выполнять других запросов, сразу взводить ошибку и откатывать транзакцию.

__Сигнатура__:
 ```cs
virtual public void UpdateObjectsOrdered(ref DataObject[] objects, bool alwaysThrowException = true) ``` 

__Пример__:

 ```cs
protected void UpdateButtonClick(object sender, EventArgs e)
{
    SQLDataService ds = (SQLDataService)DataServiceProvider.DataService;
    var ko = ds.Query<КритерийОценки>(КритерийОценки.Views.КритерийОценкиE).First(o => o.Описание.StartsWith("kirlim"));
    ko.Описание = "kirlim-birlim";
DataObject[] dObjs = new DataObject[] { ko };
    ds.UpdateObjectsOrdered(ref dObjs);
}
``` 
|}


## [anchor|#ByExtConn]Выполнение операций в рамках указанных коннекции и транзакции

{| border="1"
! Метод
! Описание
|- 
| `LoadObjectByExtConn`
| __Назначение__: Загрузка объекта с указанной коннекцией в рамках указанной транзакции

__Параметры__:
 `dataObjectView` - Представление, по которому будет зачитываться объект.

`dobject` - Объект, который будет дочитываться/зачитываться.

`сlearDataObject` - Следует ли при зачитке очистить поля существующего объекта данных.

`сheckExistingObject` - Проверить существование встречающихся при зачитке объектов.

`dataObjectCache` - Кэш объектов.

`connection` - Коннекция, через которую будет происходить зачитка.

`transaction` - Транзакция, в рамках которой будет проходить зачитка.

__Сигнатура__:
 ```cs
public virtual void LoadObjectByExtConn(
    View dataObjectView,
    DataObject dobject, 
    bool сlearDataObject, 
    bool сheckExistingObject, 
    DataObjectCache dataObjectCache, 
    IDbConnection connection, 
    IDbTransaction transaction) ``` 
|- 
| `LoadObjectsByExtConn`
| __Назначение__: Загрузка объектов с использованием указанной коннекции и транзакции

__Параметры__:
 `customizationStruct` - Структура, определяющая, что и как грузить.

`state` - Состояние вычитки(для последующей дочитки, если используется [порционное чтение](reading-portion.html), размер порции задаётся в `customizationStruct.LoadingBufferSize`)
  
`dataObjectCache` - Кэш объектов для зачитки.

`connection` - Коннекция, через которую будут выполнена зачитка.

`transaction` - Транзакция, в рамках которой будет выполнена зачитка.

__Сигнатура__:
 ```cs
public virtual DataObject[] LoadObjectsByExtConn(
    LoadingCustomizationStruct customizationStruct,
    ref object state, 
    DataObjectCache dataObjectCache,
    IDbConnection connection,
    IDbTransaction transaction) ```
|- 
| `ReadFirstByExtConn`
| __Назначение__: Получение первой порции при [порционном чтении](reading-portion.html) с использованием указанной коннекции и транзакции. Кроме порции объектов данных, сервис данных возвращает состояние чтения `state`. Это состояние передается сервису данных для получения очередных порций (см. следующий метод).
Аналог предыдущего метода, но вместо настроечной структуры выборка определяется текстом запроса. 

__Параметры__:
 `Query` - Текст запроса для выборки данных 

`state` - Состояние вычитки(для последующей дочитки)
  
`LoadingBufferSize` - размер порции

`Connection` - Коннекция, через которую будут выполнена зачитка

`Transaction` - Транзакция, в рамках которой будет выполнена зачитка

__Сигнатура__:
 ```cs
public virtual object[][] ReadFirstByExtConn(string Query, ref object State, int LoadingBufferSize, System.Data.IDbConnection Connection, System.Data.IDbTransaction Transaction) ```
|- 
| `ReadNextByExtConn`
| __Назначение__: Получение  очередных порций при [порционном чтении](reading-portion.html). Должен предшествовать вызов одного из двух вышеуказанных методов с получением состояния `state`. 

__Параметры__:
 `state` - Состояние вычитки(для последующей дочитки)
  
`LoadingBufferSize` - размер порции

__Сигнатура__:
 ```cs
public virtual object[][] ReadNextByExtConn(ref object State, int LoadingBufferSize) ```
|- 
| `UpdateObjectsByExtConn`
| __Назначение__:  Обновить хранилище по объектам с использованием указанной коннекции и транзакции. 
 Замечание: Если параметр `alwaysThrowException`=`true`, всегда взводится ошибка. Иначе, выполнение продолжается. Однако, при этом есть опасность преждевременного окончания транзакции, с переходом для остальных запросов режима транзакционности в autocommit. Проявлением проблемы являются ошибки вроде: The COMMIT TRANSACTION request has no corresponding BEGIN TRANSACTION.

__Параметры__:
 `objects` - Объекты для обновления.

`dataObjectCache` - Кеш объектов.

`alwaysThrowException` - Если произошла ошибка в базе данных, не пытаться выполнять других запросов, сразу взводить ошибку и откатывать транзакцию.

`connection` - Коннекция (не забудьте закрыть).

`transaction` - Транзакция (не забудьте завершить).

__Сигнатура__:
 ```cs
public virtual void UpdateObjectsByExtConn(ref DataObject[] objects, DataObjectCache dataObjectCache, bool alwaysThrowException, IDbConnection connection, IDbTransaction transaction) ```
|}


## [anchor|#GenerateSQL]Генерация текстов SQL-запросов

{| border="1"
! Метод
! Описание
|- 
| `GenerateQueriesForUpdateObjects`
| __Назначение__: Генерация запросов для изменения объектов 

__Параметры__:
 `deleteQueries` - Запросы для удаление (выходной параметр)
`deleteTables` - Таблицы, из которых будет проведено удаление данных (выходной параметр)
`updateQueries` - Сгенерированные запросы для изменения (выходной параметр).
`updateTables` - Таблицы, в которых будет проведено изменение данных (выходной параметр).
`insertQueries` - Сгенерированные запросы для добавления (выходной параметр).
`insertTables` - Таблицы, в которые будет проведена вставка данных (выходной параметр).
`tableOperations` - Операции, которые будут произведены над таблицами (выходной параметр).
`queryOrder` - Порядок исполнения генерируемых запросов, задаваемый именами таблиц (выходной параметр).
`checkLoadedProps` - Проверять ли загруженность свойств.
`processingObjects` - Текущие обрабатываемые объекты (то есть объекты, которые данный сервис данных планирует подтвердить в БД в текущей транзакции). Выходной параметр.
`dataObjectCache` - Кэш объектов данных.
`auditObjects` - Список объектов, которые необходимо записать в аудит (выходной параметр). Заполняется в том случае, когда передан не null и текущий сервис аудита включен.
`dobjects` - Объекты, для которых генерируются запросы.
__Сигнатура__:
 ```cs
public virtual void GenerateQueriesForUpdateObjects(
    StringCollection deleteQueries,
    StringCollection deleteTables,
    StringCollection updateQueries,
    StringCollection updateTables,
    StringCollection insertQueries,
    StringCollection insertTables,
    SortedList tableOperations,
    StringCollection queryOrder,
    bool checkLoadedProps,
    System.Collections.ArrayList processingObjects,
    DataObjectCache dataObjectCache,
    params ICSSoft.STORMNET.DataObject[] dobjects) ``` 
 
В данной перегрузке дополнительно возвращается список объектов, для которых необходимо создание записей аудита:

```cs
public virtual void GenerateQueriesForUpdateObjects(
    StringCollection deleteQueries,
    StringCollection deleteTables,
    StringCollection updateQueries,
    StringCollection updateTables,
    StringCollection insertQueries,
    StringCollection insertTables,
    SortedList tableOperations,
    StringCollection queryOrder,
    bool checkLoadedProps,
    ArrayList processingObjects, 
    DataObjectCache dataObjectCache,
    List<DataObject> auditObjects,
    params ICSSoft.STORMNET.DataObject[] dobjects) ```   
|- 
| `GenerateSQLSelect`
| __Назначение__: Получить запрос на вычитку данных
 
__Параметры__:
 `customizationStruct` - настройка выборки

`StorageStruct` - возвращается соответствующая структура выборки

__Возвращаемый результат__: запрос

__Сигнатура__:
 ```cs
public virtual string GenerateSQLSelect(LoadingCustomizationStruct customizationStruct, bool ForReadValues, out STORMDO.Business.StorageStructForView[] StorageStruct, bool Optimized) ``` 

```cs
public virtual string GenerateSQLSelect(LoadingCustomizationStruct customizationStruct, bool Optimized) ```
|- 
| `GetLeftJoinExpression`
| __Назначение__: Получить LeftJoin выражение
 
__Параметры__:
 `subTable` - имя таблицы

`subTableAlias` - псевдоним таблицы

`parentAliasWithKey` 
 
`subTableKey`  

`subJoins`  

`baseOutline`  
 
__Сигнатура__:
 ```cs
public virtual void GetLeftJoinExpression(string subTable, string subTableAlias, string parentAliasWithKey, string subTableKey, string subJoins, string baseOutline, out string FromPart, out string WherePart) ```
|- 
| `GetInnerJoinExpression`
| __Назначение__: Получить InnerJoin выражение 

__Параметры__:
 `subTable` - имя таблицы 

`subTableAlias` - псевдоним таблицы 

`parentAliasWithKey` 
 
`subTableKey`  

`subJoins`  

`baseOutline`  

`FromPart`  

`WherePart`  

__Сигнатура__:
 ```cs
public virtual void GetInnerJoinExpression(string subTable, string subTableAlias, string parentAliasWithKey, string subTableKey, string subJoins, string baseOutline, out string FromPart, out string WherePart) ``` 
|- 
| `GetJoinTableModifierExpression`
| __Назначение__: Вернуть модификатор для обращения к таблице (напр WITH (NOLOCK))

Можно перегрузить этот метод в сервисе данных-наследнике для возврата соответствующего своего модификатора.
Базовый `SQLDataService` возвращает пустую строку. 

__Сигнатура__:
 ```cs
public virtual string GetJoinTableModifierExpression() ```
| 
|- 
| `GetINExpression`
| __Назначение__: Вернуть in выражение для where 

__Параметры__:
 `identifiers` - идентификаторы
 
__Сигнатура__:
 ```cs
public virtual string GetINExpression(params string[] identifiers) ```
|- 
| `GetIfNullExpression`
| __Назначение__: Вернуть ifnull выражение
 
__Параметры__:
 `identifiers` - идентификаторы

__Сигнатура__:
 ```cs
public virtual string GetIfNullExpression(params string[] identifiers) ```
|- 
| `PutIdentifierIntoBrackets`
| __Назначение__: Оформить идентификатор 

__Параметры__:
 `identifier` - идентификатор

__Возвращаемый результат__: оформленный идентификатор(например в кавычках)
 
__Сигнатура__:
 ```cs
public virtual string PutIdentifierIntoBrackets(string identifier) ```
|- 
| `CreateJoins`
| __Назначение__: Создать join соединения
 
__Параметры__:
 `source` - источник с которого формируется соединение

`parentAlias` - вышестоящий алиас

`index` - индекс источника

`keysandtypes` - ключи и типы

`baseOutline` - смещение в запросе

`joinscount` - количество соединений

__Сигнатура__:
 ```cs
public virtual void CreateJoins(STORMDO.Business.StorageStructForView.PropSource source,
    string parentAlias, int index,
    System.Collections.ArrayList keysandtypes,
    string baseOutline, out int joinscount,
    out string FromPart, out string WherePart) ```
|- 
| `CreateJoins`
| __Назначение__: Создать join соединения 

__Параметры__:
 `source` - источник с которого формируется соединение

`parentAlias` - вышестоящий алиас

`index` - индекс источника

`keysandtypes` - ключи и типы

`baseOutline` - смещение в запросе

`joinscount` - количество соединений
 
__Сигнатура__:
 ```cs
public virtual void CreateJoins(STORMDO.Business.StorageStructForView.PropSource source,
    string parentAlias, int index,
    System.Collections.ArrayList keysandtypes,
    string baseOutline, out int joinscount,
    out string FromPart, out string WherePart, bool MustNewGenerate) ```
|- 
| `GenerateSQLSelectByStorageStruct`
| __Назначение__: Получение SQL запроса в следующем формате

```
sql SELECT
  atr1,atr2,... atr3,
  Key1,Key2,... key3
FROM
  fromjoins ``` 

__Параметры__:
 `storageStruct` - структура хранилища 

`AddingAdvansedField` - довленные дополнительные свойства 

`AddingKeysCount` - добавленниые ключи

`addMasterFieldsCustomizer`  

`addNotMainKeys`  

`SelectTypesIds`  

__Сигнатура__:
 ```cs
virtual public string GenerateSQLSelectByStorageStruct(STORMDO.Business.StorageStructForView storageStruct, bool addNotMainKeys, bool addMasterFieldsCustomizer, string AddingAdvansedField, int AddingKeysCount, bool SelectTypesIds) ```

```cs
virtual public string GenerateSQLSelectByStorageStruct(STORMDO.Business.StorageStructForView storageStruct, bool addNotMainKeys, bool addMasterFieldsCustomizer, string AddingAdvansedField, int AddingKeysCount, bool SelectTypesIds, bool MustNewGenerate, bool MustDopSelect) ``` 
|- 
| `ConvertSimpleValueToQueryValueString`
| __Назначение__: Конвертация константных значений в строки запроса 

__Параметры__:
 `value` - значение

__Сигнатура__:
 ```cs
public virtual string ConvertSimpleValueToQueryValueString(object value) ```
|- 
| `ConvertValueToQueryValueString`
| __Назначение__: Конвертация значений в строки запроса 

__Параметры__:
 `value` - значение

__Сигнатура__:
 ```cs
public virtual string ConvertValueToQueryValueString(object value) ```
|- 
| `ConvertValueToQueryValueString`
| __Назначение__: Преобразование значение свойства в строку для запроса 

__Параметры__:
 `dataobject` - объект данных 

`propname` - имя свойства
 
__Сигнатура__:
 ```cs
public virtual string ConvertValueToQueryValueString(DataObject dataobject, string propname) ```
|- 
| `LimitFunction2SQLWhere`
| __Назначение__: Преобразование функции 

__Параметры__:
 `LimitFunction` - настроечная структура выборки

__Сигнатура__:
 ```cs
public virtual string LimitFunction2SQLWhere(STORMFunction LimitFunction, STORMDO.Business.StorageStructForView[] StorageStruct, string[] asnameprop, bool MustNewGenerate) ```
|- 
| `LimitFunction2SQLWhere`
| __Назначение__: Преобразование функции 

__Параметры__:
 `LimitFunction` - настроечная структура выборки

__Сигнатура__:
 ```cs
public virtual string LimitFunction2SQLWhere(STORMFunction LimitFunction) ```
|}


## [anchor|#QueryText]Выполнение операций с указанием текста запроса

{| border="1"
! Метод
! Описание
|- 
| `ReadFirst`
| __Назначение__: Вычитка первой партии данных при [порционном чтении](reading-portion.html). Кроме порции объектов данных, сервис данных возвращает состояние чтения `state`. Это состояние передается сервису данных для получения очередных порций (см. следующий метод).Выборка определяется текстом запроса.

__Параметры__:
 `Query` - Текст запроса для выборки данных 

`state` - Состояние вычитки(для последующей дочитки)
  
`LoadingBufferSize` - размер порции
   
__Сигнатура__:
 ```cs
public virtual object[][] ReadFirst(string Query, ref object State, int LoadingBufferSize) ```
|-
| `ReadNext`
| __Назначение__: Получение  очередной порции при [порционном чтении](reading-portion.html). Должен предшествовать вызов предыдущего метода с получением состояния `state`.

__Параметры__:
 `state` - Состояние вычитки(для последующей дочитки)
  
`LoadingBufferSize` - размер порции
  
__Сигнатура__:
 ```cs
public virtual object[][] ReadNext(ref object State, int LoadingBufferSize) ```
|-
| `ExecuteNonQuery `
| __Назначение__: Выполнить запрос

__Параметры__:
 `Query` - текст SQL-запроса 

__Возвращаемый результат__: количество задетых строк 
 
__Сигнатура__:
 ```cs
public virtual int ExecuteNonQuery(string Query) ```
|}
# Список событий для SQLDataService
{| border="1"
! Событие !! Описание
|-
| OnGenerateSQLSelect || Срабатывает перед генерацией SQL SELECT'а ([пример](intercept-formation--s-q-l-query.html)). 
Обрабатывая это событие можно, например, добавить дополнительные условия, которые должны попасть в формируемый запрос.
|-
| AfterGenerateSQLSelectQuery || Срабатывает после генерации SQL SELECT'а, но до вычитки данных ([пример](intercept-formation--s-q-l-query.html)). 
Можно использовать для внесения изменений в сгенерированный текст запроса.
|-
| BeforeUpdateObjects || Срабатывает перед обновлением объектов в базе, после отработки бизнес-серверов.
|-
| AfterUpdateObjects || Срабатывает после обновления объектов в базе.
|-
| OnCreateCommand || Срабатывает при создании SQL-команды ([пример](intercept-formation--s-q-l-query.html)).
|}

Замечание: кроме обработки указанных событий `SQLDataService` предоставляет возможность полного переопределения логики, т.к. содержит следующие делегаты:
```cs
// Делегат для события создания команды.
public delegate void OnCreateCommandEventHandler(object sender, CreateCommandEventArgs e);

// Делегат для события при генерации SQL Select запроса (перед).
public delegate void OnGenerateSQLSelectEventHandler(object sender, GenerateSQLSelectQueryEventArgs e);

// Делегат для события при генерации SQL Select запроса (после).
public delegate void AfterGenerateSQLSelectQueryEventHandler(object sender, GenerateSQLSelectQueryEventArgs e);

// The before update objects event handler.
public delegate void BeforeUpdateObjectsEventHandler(object sender, DataObjectsEventArgs e);

// The after update objects event handler.
public delegate void AfterUpdateObjectsEventHandler(object sender, DataObjectsEventArgs e);
```
# Задание `CommandTimeout`
Есть возможность указывать [IDbCommand.CommandTimeout](http://msdn.microsoft.com/ru-ru/library/system.data.idbcommand.commandtimeout.aspx). Для этого можно в конфигурационном файле задать параметр:
```xml
<add key="SQLDataServiceCommandTimeout" value="60"/>
```
либо присвоить значение явно:
```cs
SQLDataService ds = (SQLDataService)DataServiceProvider.DataService;
ds.UseCommandTimeout = true;
ds.CommandTimeout = 60;
```
`UseCommandTimeout` нужно указывать обязательно. По-умолчанию этот флаг имеет значение `false`.

Такая сложная реализация нужна для возможности включения-отключения использования уникальных значений без потери предыдущего значения таймаута.

Также важно понимать, что при выполнении любой операции (чтение/обновление и т.д.) коннекция создаётся, а в конце закрывается. Т.е. последовательные LoadObjects будут выполнены в на разных `System.Data.IDbConnection`.

Соответственно применяться настройка времени ожидания выполнения команды будет каждый раз заново.

<msg type=information head=Внимание>Время ожидания выполнения команды '''в секундах'''. Значение по умолчанию — 30 секунд.</msg>

# Смена строки соединения
`SQLDataService` поддерживает возможность смены строки соединения. Такая возможность, в частности, используется для работы с несколькими БД в одном приложении, описание доступно в [статье](multibase.html).

