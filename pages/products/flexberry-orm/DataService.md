---
title: Сервис данных
sidebar: product--sidebar
keywords: Flexberry ORM, Public, Черновик статьи
toc: true
permalink: ru/data-service.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Программная библиотека:''' ICSSoft.STORMNET.Business.dll
* '''Предназначение:''' Компонент доступа к источнику данных.
</td>
</tr></tbody></table></a>
</div>

'''Сервис данных''' - компонент Flexberry ORM, обеспечивающий запись и чтение объектов данных в хранилище. В объектной модели Flexberry ORM сервис данных представлен классом, реализующим интерфейс `ICSSoft.STORMNET.Business.IDataService`.
Поддерживается несколько [ стандартных сервисов данных](standard-data-services.html), и при необходимости пользователями могут быть [разработаны](implement-a-custom--data-service.html) новые, удовлетворяющие специфическим требованиям (например, наследуя классы `ICSSoft.STORMNET.Business.ODBCDataService.ODBCDataService` или `ICSSoft.STORMNET.Business.[SQLDataService](s-q-l-data-service.html)`).

(((<msg type=important>При обращении к сервису данных в программном коде рекомендуется производить через интерфейс `ICSSoft.STORMNET.Business.IDataService`, т.к. это гарантирует  независимость кода от типа хранилища данных.</msg>)))

Перед использование сервиса данных в программном коде его необходимо [настроить тем или иным способом](construction--data-service.html). Также важными моментами при использовании сервиса данных являются:

* Существует [возможность настроить сервис данных таким образом, чтобы на регистрозависимом источнике данных приложение вело себя как при работе с регистронезависимым источником](c-a-s-e--insensitive-for--d-b.html). 
* Сервис данных [особым образом работает с логическим типом](interpretation-boolean-values--n-u-l-l.html).

# Основные возможности сервиса данных
Перед обращением в программном коде к сервису данных на него необходимо получить [ссылку](data-service-provider-data-service.html). При одновременном использовании нескольких хранилищ необходимо учесть некоторые [особенности](multibase.html).


Интерфейс `ICSSoft.STORMNET.Business.IDataService` содержит следующие основные группы методов:
#	Методы с именами типа `UpdateXXXXXXXX`, предназначенные для приведения хранилища данных ([обновление, удаление, создание записей](object-status-and-loading-state.html)) в соответствие с переданными одним или несколькими объектами данных:
#* `[#DSUpdateObject|UpdateObject]` - обновление одного объекта.
#* `[#DSUpdateObjects|UpdateObjects]` - обновление нескольких объектов.
#	Методы с именами типа `LoadXXXXXXXX`, предназначенные для чтения одного, либо нескольких объектов данных.
#* `[#DSLoadObject|LoadObject]` - загрузка объекта (в зависимости от параметров с помощью данного метода можно осуществлять [дочитку объекта](additional-loading-data-object.html)).
#* `[#DSLoadObjects|LoadObjects]` - загрузка объектов (в т.ч. перегрузки метода позволяют реализовать [порционное стение](reading-portion.html), [чтение принадлежащих различным классам объектов в одном представлении](reading-several-types-objects.html)).
#       Загрузка без создания объектов `[#DSLoadStringedObjectView|LoadStringedObjectView]`.
#       Метод получения количества объектов, удовлетворяющих условию `[#DSGetObjectsCount|GetObjectsCount]`.

Большинство методов чтения/записи имеют перегрузки, принимающие дополнительный параметр `[DataObjectCache](context--sensitive--cache--data--objects.html)`, позволяющий поддерживать одну инстанцию для нескольких экземпляров объектов. Передавать `[DataObjectCache](context--sensitive--cache--data--objects.html)` в методы зачитки нужно в том случае, если какой-то объект уже существует в памяти и после зачитки все ссылки на него должны указывать именно на этот объект. В методы обновления данный параметр передается для верной расстановки ссылок на экземпляры данных из кэша в связи с тем, что после сохранения в хранилище происходит обновление свойств объекта данных и инициализация его копии данных. Подробнее об особенностях использовании кэша см. в статье [Контекстно-зависимый кэш объектов данных](context--sensitive--cache--data--objects.html). Ниже, при рассмотрении методов, перегрузки с параметром `[DataObjectCache](context--sensitive--cache--data--objects.html)` отдельно не описываются.

## [anchor|#DSLoadObjects]Загрузка объектов данных

Для загрузки нескольких объектов данных служат перегрузки метода `LoadObjects`.



{| border="1"
! Назначение
! Перегрузки
! Параметры
! Описание
|- 
|  rowspan="2" | Загрузка объектов данных по [представлению](view-definition.html)(массиву [представлений](view-definition.html))
| '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View dataObjectView); ```
|   rowspan="2" | `dataObjectView(s)` - [представление](view-definition.html) (массив [представлений](view-definition.html))

| Загружаются все объекты данных, доступные в хранилище. При этом выполняется загрузка только тех свойств, которые указаны в [представлении](view-definition.html).
|-
| '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View[] dataObjectViews); ```
| Происходит последовательный вызов метода с параметром - представление для каждого элемента массива. Практическая применимость данной перегрузки неочевидна.
|-
|  rowspan="2" | [anchor|#LoadObjectsLCS]Загрузка объектов данных по настроечной структуре для выборки `[LoadingCustomizationStruct](loading-customization-struct.html)` (массиву структур)
| '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct); ```


'''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct, DataObjectCache DataObjectCache); ```
| rowspan="2" | `customizationStruct(s)` - [настроечная структура для выборки](loading-customization-struct.html) (массив структур)
| Данная перегрузка метода позволяет тонко настроить выборку загружаемых объектов за счёт использования структуры `[LoadingCustomizationStruct](loading-customization-struct.html)`: 

* указать представление (вертикальное ограничение выборки: загрузка только определенных свойств), 

* количество возвращаемых строк (для реляционных хранилищ ограничение `<nowiki>TOP</nowiki>` в `SELECT`), 

* параметры сортировки возвращаемых результатов (`ORDER BY`),
 
* ограничения на вычитываемые объекты данных (`WHERE`) 
и т.д.. Подробнее см. описание `[LoadingCustomizationStruct](loading-customization-struct.html)`.
 
 [Пример использования](load-limitation-example.html). 

'''Замечание''': Данная перегрузка позволяет реализовать [чтение принадлежащих различным классам объектов в одном представлении](reading-several-types-objects.html).
|-
| '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct[] customizationStructs); ```
| Происходит последовательный вызов метода с параметром - `[LoadingCustomizationStruct](loading-customization-struct.html)` для каждого элемента массива. Практическая применимость данной перегрузки неочевидна.
|-
| rowspan="2" | Загрузка объектов с использованием состояния вычитки (для реализации [порционного чтения](reading-portion.html))
| [anchor|#LoadObjectsRefState] '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct,ref object State); ``` 

'''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct customizationStruct, ref object State, DataObjectCache DataObjectCache);```
| rowspan="2" | `State` - Состояние вычитки(для последующей дочитки)
`customizationStruct` - [настроечная структура для выборки](loading-customization-struct.html) 
__Примечание__: Размер порции может быть задан с помощью параметра `LoadingBufferSize` структуры `[LoadingCustomizationStruct](loading-customization-struct.html)`.
| Получение первой порции при [порционном чтении](reading-portion.html). Кроме порции объектов данных, сервис данных возвращает некоторое состояние чтения. Это состояние передается  сервису данных для получения очередных порций при последующих вызовах (см. [#LoadObjectsByState|следующие] перегрузки). 
|-
| [anchor|#LoadObjectsByState] '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(ref object State); ``` 

'''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(ref object State, DataObjectCache DataObjectCache); ``` 

| Получение очередных порций при [порционном чтении](reading-portion.html). Должен предшествовать вызов в [#LoadObjectsRefState|указанной выше] перегрузке. 
<!--|-
| Загрузка объектов с делегатами для изменения
| '''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View dataObjectView, ChangeViewForTypeDelegate changeViewForTypeDelegate);``` 

'''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(ICSSoft.STORMNET.View[] dataObjectViews, ChangeViewForTypeDelegate changeViewForTypeDelegate); ``` 

'''LoadObjects'''
```cs
ICSSoft.STORMNET.DataObject[] LoadObjects(LoadingCustomizationStruct[] customizationStructs, ChangeViewForTypeDelegate changeViewForTypeDelegate); ```
| `changeViewForTypeDelegate` - Состояние вычитки(для последующей дочитки)
|  Задумка методов никак не реализована. Параметр-делегат никак не используется. Создана задача на определение дальнейшей судьбы методов. -->
<!--|-
| Загрузка объектов данных 
| '''LoadObjects'''
```cs
void LoadObjects(ICSSoft.STORMNET.DataObject[] dataobjects, ICSSoft.STORMNET.View dataObjectView, bool ClearDataobject, DataObjectCache DataObjectCache); ```
| `dataobjects` - исходные объекты

`dataObjectView` - представление

`ClearDataobject` - очищать ли существующие

| Особое внимание на то, что объекты передаются параметром. Можно использовать а-ля догрузка, но без должной обработки копии? Пока применение неизвестно, требуется детальное изучение кода. --> 
|}

## [anchor|#DSLoadObject]Загрузка одного объекта данных

Для загрузки одного объекта данных служат перегрузки метода `LoadObject`. 

Вычитка свойств из хранилища осуществляется по заданному в объекте данных [первичныму ключу](primary-keys-objects.html).

Примеры использования:
* [Обработка одного объекта](processing-one-object.html),
* [Пример: загрузка и изменение объекта](load-and-alter-objects.html).

{| border="1"
! Назначение
! Перегрузки
! Параметры
! Описание
|-
|  Загрузка объекта данных по [первичному ключу](primary-keys-objects.html)
| '''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.DataObject dobject) ```


'''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) ```
| `dobject` - Объект данных, который требуется загрузить
| Будут загружены только собственные свойства объекта. При отсутствии в хранилище данных объекта с заданным первичным ключом будет выдано исключение `CantFindDataObjectException`. Не следует использовать данную перегрузку метода для дочитки объекта данных, для этого применимы перегрузки методы с дополнительными параметрами (см. ниже).
|- 
| Загрузка объекта данных по [представлению](view-definition.html)
| '''LoadObject'''
```cs
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject) ```
 
'''LoadObject'''
```cs
void LoadObject(void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject) ```

'''LoadObject'''
```cs
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) ```

'''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) ```
| `dataObjectView(dataObjectViewName)` - [представление](view-definition.html) (имя представления)

| Выполняется загрузка только тех свойств, которые указаны в [представлении](view-definition.html).
|-
| Загрузка объекта данных с указанием дополнительных параметров
| '''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject) ``` 

'''LoadObject'''
```cs
 void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject) ``` 

'''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject) ``` 

'''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache) ``` 

'''LoadObject'''
```cs
void LoadObject(string dataObjectViewName, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache) ``` 

'''LoadObject'''
```cs
void LoadObject(ICSSoft.STORMNET.View dataObjectView, ICSSoft.STORMNET.DataObject dobject, bool ClearDataObject, bool CheckExistingObject, DataObjectCache DataObjectCache) ```
| `ClearDataObject` - очищать ли объект

`CheckExistingObject` - проверять ли существование объекта в хранилище (если указать `true`, при отсутствии объекта в базе будет выдано исключение типа `CantFindDataObjectException`)
| Данная перегрузка метода может быть использована, в частности, для выполнения [догрузки свойств объекта](additional-loading-data-object.html).
|}

## [anchor|#DSUpdateObject]Обновление одного объекта данных

Для обновления одного объекта данных служат перегрузки метода `UpdateObject`.

Примеры использования:
* [Обработка одного объекта](processing-one-object.html),
* [Пример: загрузка и изменение объекта](load-and-alter-objects.html).

Если перед выполнением сохранения требуется выполнение определенных действий, они могут быть реализованы в бизнес-сервере [указанным способом](data-object-update-hook-example.html).

Следует иметь в виду, что сохранение объекта может вызвать [сохранение связанных с ним объектов](update-related-objects.html).

{| border="1"
! Назначение
! Перегрузки
! Параметры
<!--! Описание-->
|- 
|  Обновление объекта данных
| '''UpdateObject'''
```
scharp void UpdateObject(ICSSoft.STORMNET.DataObject dobject) 
``` 

```
scharp void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject) 
``` 

```
scharp void UpdateObject(ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) 
``` 

```
scharp void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache) 
``` 

```
scharp void UpdateObject(ICSSoft.STORMNET.DataObject dobject, bool AlwaysThrowException) 
``` 

```
scharp void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, bool AlwaysThrowException) 
``` 

```
scharp void UpdateObject(ref ICSSoft.STORMNET.DataObject dobject, DataObjectCache DataObjectCache, bool AlwaysThrowException) 
```
|   `dobject`  - объект данных, который требуется обновить 

`DataObjectCache` - кеш объектов

`AlwaysThrowException` - Если произошла ошибка в базе данных, не пытаться выполнять других запросов, сразу взводить ошибку и откатывать транзакцию 
|}

## [anchor|#DSUpdateObjects]Обновление нескольких объектов данных

Для обновления нескольких объектов данных служат перегрузки метода `UpdateObjects`.


Запросы для всех обновляемых объектов выполняются в единой транзакции.


Обновляемые объекты данных могут быть как однотипными, так и разнотипными. В метод они передаются параметром — одномерным массивом типа `[DataObject](dataobject.html)`&#0091;&#0093;. 
В общем случае сервис данных умеет сам выстраивать порядок запросов на обновление объектов данных. Но возможны ситуации, когда для связанных объектов важен порядок следования объектов в массиве, подробная информация о порядке сохранения объектов приведена в [статье](processing-of-multiple-objects.html).

Следует иметь в виду, что для каждого элемента массива его сохранение может вызвать [сохранение связанных с ним объектов](update-related-objects.html).

[Пример использования](instantiate-and-persist-objects-example.html).

{| border="1"
! Назначение
! Перегрузки
! Параметры
<!--! Описание-->
|- 
|  Обновление нескольких объектов данных в хранилище 
| '''UpdateObjects'''
```cs
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects) ``` 

```cs
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, bool AlwaysThrowException) ``` 

```cs
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, DataObjectCache DataObjectCache) ``` 

```cs
void UpdateObjects(ref ICSSoft.STORMNET.DataObject[] objects, DataObjectCache DataObjectCache, bool AlwaysThrowException) ```
|   `objects` - объекты для обновления

`DataObjectCache` - кеш объектов

`AlwaysThrowException` - если произошла ошибка в базе данных, не пытаться выполнять других запросов, сразу взводить ошибку и откатывать транзакцию
|}


## [anchor|#DSLoadStringedObjectView]Загрузка без создания объектов

Можно выполнить загрузку из хранилища без создания экземпляров: каждый объект в этом случае представлен в виде строки из значений свойств с разделителями. Используется, когда не требуется редактирование объектов. Данный метод намного быстрее, чем создание объектов при загрузке в методе `LoadObjects`. Для загрузки в виде строкового представления предназначены перегрузки метода `LoadStringedObjectView`.

[Пример использования](load-limitation-example.html).

{| border="1"
! Назначение
! Перегрузки
! Параметры
! Описание
|- 
|  Загрузка без создания объектов 
| '''LoadStringedObjectView'''
```cs
ObjectStringDataView[] LoadStringedObjectView(char separator, LoadingCustomizationStruct customizationStruct) ```
| `separator` -  разделитель в строках 
  
`customizationStruct` -  настроичная структура для выборки `[LoadingCustomizationStruct](loading-customization-struct.html)`


 __Примечание__: порядок следования свойств объекта данных в результирующей строке с разделителями задаётся парметром `ColumnsOrder` структуры `customizationStruct`.
| __Возвращаемый результат__: массив структур `ObjectStringDataView`
|-
| rowspan="3" | Загрузка без создания объектов с использованием состояния вычитки (для реализации [порционного чтения](reading-portion.html))
| '''LoadStringedObjectView'''
```cs
ObjectStringDataView[] LoadStringedObjectView(char separator, LoadingCustomizationStruct customizationStruct, ref object State) ```
| rowspan="3" | `State` - Состояние вычитки(для последующей дочитки)
`customizationStruct` - [настроечная структура для выборки](loading-customization-struct.html) 
__Примечание__: Размер порции может быть задан с помощью параметра `LoadingBufferSize` структуры `[LoadingCustomizationStruct](loading-customization-struct.html)`.
| Получение первой порции при [порционном чтении](reading-portion.html). Кроме порции данных, сервис данных возвращает некоторое состояние чтения. Это состояние передается  сервису данных для получения очередных порций при последующих вызовах.
|-
| '''LoadStringedObjectView'''
```cs
ObjectStringDataView[] LoadStringedObjectView(ref object state) ```
| Получение очередных порций при [порционном чтении](reading-portion.html). Должен предшествовать вызов в предыдущей перегрузке.
|-
| '''CompleteLoadStringedObjectView'''
```cs
void CompleteLoadStringedObjectView(ref object state) ```
| Корректное завершения операции порционного чтения при `LoadStringedObjectView`.

|} 

## [anchor|#DSGetObjectsCount]Получение количества объектов, удовлетворяющих запросу

[Пример использования](load-limitation-example.html). 

{| border="1"
! Назначение
! Перегрузки
! Параметры
<!--! Описание-->
|- 
|  Возвращает количество объектов удовлетворяющих условиям выборки,не выполняя загрузку данных 
| '''GetObjectsCount'''
```cs
int GetObjectsCount(LoadingCustomizationStruct customizationStruct)```
|   `customizationStruct` - настроечная структура для выборки `[LoadingCustomizationStruct](loading-customization-struct.html)`  
|}

# Использование SQL при работе с сервисом данных
В некоторых ситуациях возможностей сервиса данных недостаточно для решения специфических задач, в таких случаях есть возможность [непосредственного выполнения SQL-запроса](Flexberry-s-q-l-query.html) и [Intercept-formation-SQL-query |изменение автоматически построенного запроса].
