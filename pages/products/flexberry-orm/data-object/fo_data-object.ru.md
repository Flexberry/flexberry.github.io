---
title: Объекты данных
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM, методы
summary: Характеристика, методы и свойства объекта данных
toc: true
permalink: ru/fo_data-object.html
lang: ru
---

В Flexberry ORM **объектами данных** называются экземпляры классов наследников абстрактного класса `ICSSoft.STORMNET.DataObject`. Для объектов данных генераторами Flexberry создаётся код класса на .Net CLR-совместимом языке (C#, VB и т.п.).

## Объекты данных в модели

[Классы объектов данных](fo_data-object-structure.html) описываются на [диаграмме классов](fd_class-diagram-constraction.html) в [Flexberry Designer](fd_flexberry-designer.html) и имеют стереотип [implementation](fd_data-classes.html). При описании объекта данных задаются [атрибуты](fo_attributes-class-data.html), связи и метаданные.

Атрибуты объекта данных могут быть двух типов стандартные (описанные на диаграмме) и динамические [динамические свойства объекта данных](fo_dynamic-properties.html). В случае если значение атрибута не предполагается размещать в хранилище, он может быть объявлен как [нехранимый](fo_not-stored-attributes.html). При описании атрибутов им могут быть присвоены [значения по умолчанию](fo_features-dafault-value.html).

Также в Flexberry ORM поддерживаются несколько видов связей: [наследование](fd_inheritance.html), [мастеровые](fd_master-association.html) и [детейловые](fo_detail-associations-properties.html) связи.

К объектам данным может быть привязана дополнительная информация (метаинформация), например, [представления](fd_view-definition.html) или [изображение](fo_class-image.html).

Стереотип [implementation](fd_data-classes.html) является значением по умолчанию, т.е. при разработке диаграммы его можно не указывать, оставляя пустое значение.

## Объекты данных вo время выполнения

Жизненный цикл объекта данных традиционно состоит из трех этапов:

* Создание.
* Обработка.
* Сохранение.

## Создание объекта данных

Объект данных может быть создан следующими способами:

* Создание экземпляра класса стандартными средствами языка
* Загрузка из хранилища методами LoadObject/LoadObjects [сервиса данных](fo_data-service.html).
* [Прототипирование](fo_data-object-prototype.html)
* [Копирование](fo_copying-data-objects.html).
* [Десериализация ранее сохраненного объекта](fo_aggregating-function.html).

## Обработка объекта данных

Одним из основных атрибутов объекта является [ключ объекта](fo_primary-keys-objects.html), для доступа к которому используется свойство `PrimaryKey`. В большинстве случаев для ключа используется глобальный уникальный идентификатор (GUID), хотя при необходимости могут быть использованы другие типы.

Наиболее часто встречаются следующие задачи обработки объектов данных:

* [Изменение значений атрибутов](fo_own-object-attributes.html).
* [Получение информации о статусе объекта](fo_processing-status-condition-load.html).
* [Получение информации о загруженных свойствах](fo_definition-loaded-properties.html).
* [Получение метаданных объекта](fo_methods-class-information.html).
* [Блокировка объекта для монопольного редактирования](fo_lock-service.html).
* [Проверка совместимости объекта и представления](fo_test-object-for-viewing.html).
* [Получение презентационного значения для объекта данных](fo_get-presentation-value.html)
* [Дочитка объекта данных](fo_additional-loading.html)

## Сохранение объекта данных в хранилище

Для сохранения объекта данных используются методы [сервиса данных](fo_data-service.html) UpdateObject/UpdateObjects. При работе с объектами данных важными понятиями являются [статус объектов, состояние загрузки](fo_object-status.html) и [копия данных](fo_data-object-copy.html).

## Основные свойство и методы ICSSoft.STORMNET.DataObject

### Работа с копией данных

#### GetDataCopy

__Назначение__: Получить внутреннюю копию объекта данных

__Сигнатура__:

```csharp
DataObject ICSSoft.STORMNET.DataObject.GetDataCopy()
```

#### InitDataCopy

__Назначение__: Проинициализировать копию данных

__Параметры__:

`DataObjectCache`

__Сигнатура__:

```csharp
// 1
void ICSSoft.STORMNET.DataObject.InitDataCopy()

// 2
void ICSSoft.STORMNET.DataObject.InitDataCopy(DataObjectCache DataObjectCache)
```

#### SetDataCopy

__Назначение__: Установить внутреннюю копию объекта данных

__Параметры__:

`value` - Устанавливаемый объект как копия существующего

__Сигнатура__:

```csharp
void ICSSoft.STORMNET.DataObject.SetDataCopy (DataObject value)
```

### Получение и установка состояния объекта

#### ContainsAlteredProps

__Назначение__: Установить было ли изменение значений свойств объекта по сравнению с внутренней копией

__Сигнатура__:

```csharp
bool ICSSoft.STORMNET.DataObject.ContainsAlteredProps()
```

#### GetAlteredPropertyNames

__Назначение__: Возвращает список свойств (атрибутов, мастеров, детейлов), значения которых изменились по сравнению с внутренней копией объекта.

__Сигнатура__:

```csharp
string[] ICSSoft.STORMNET.DataObject.GetAlteredPropertyNames()
```

#### CheckNotNullProperties  

__Назначение__: Вернуть список незаполненных полей (значения не могут быть пустыми согласно модели данных)

__Параметры__:

* `detailSkip` - Пропускать при проверке удаленные детейлы. Словарь со списком типов детейлов и флагами для них. Если детейла нет в словаре или значение для него False, то проверка детейла не будет пропущена. Параметр может иметь значение null.
* `view` - Представление, по свойствам которого происходит проверка и возвращение заголовков свойств.
* `returnCaptions` - true при необходимости вернуть заголовки свойств из представления, а не имена свойств, false в противном случае.

__Сигнатура__:

```csharp
// 1
public string[] CheckNotNullProperties()

// 2
string [] ICSSoft.STORMNET.DataObject.CheckNotNullProperties (Dictionary<Type, bool> detailSkip)

// 3
public string[] CheckNotNullProperties(View view, bool returnCaptions)

// 4
public string[] CheckNotNullProperties(View view, bool returnCaptions, Dictionary<Type, bool> detailSkip)
```

#### GetStatus

__Назначение__: Получение значения статуса объекта

__Параметры__:

`recountIfAutoaltered` - обновить значение принудительно (если класс с автоматическим вычислением статуса)

__Сигнатура__:

```csharp
// 1
ObjectStatus ICSSoft.STORMNET.DataObject.GetStatus()

// 2
ObjectStatus ICSSoft.STORMNET.DataObject.GetStatus(bool recountIfAutoaltered)
```

#### SetLoadingState

__Назначение__: Установка состояния загрузки.

__Параметры__:

`newState` - Состояние загрузки, устанавливаемое объекту.

__Сигнатура__:

```csharp
void ICSSoft.STORMNET.DataObject.SetLoadingState (LoadingState newState)
```

#### SetLoadedProperties

__Назначение__: Установка состояния загрузки.

__Параметры__:

`newState` - Состояние загрузки, устанавливаемое объекту.

__Сигнатура__:

```csharp
void ICSSoft.STORMNET.DataObject.SetLoadingState (LoadingState newState)
```

#### SetStatus  

__Назначение__: Установка статуса

__Параметры__:

`newState` - Статус, устанавливаемый объекту.

__Сигнатура__:

```csharp
virtual void ICSSoft.STORMNET.DataObject.SetStatus (ObjectStatus newState)
```

### Копирование и дублирование объектов данных

#### CopyTo  

__Назначение__: Создать копию объекта данных. Если полученный в результате копирования объект планируется поместит в хранилище, у него необходимо   вызвать методы `InitDataCopy` или `ClearDataCopy`.

__Параметры__:

* `toObject` = объект данный, в который  
* `CreateDataObjectsCopy` - true - создавать копии связанных объектов, false - ограничиться копированием ссылки
* `PrimaryKeyCopy` - true - копировать первичные ключи
* `UseParentCaching` - использовать ранее установленное кеширование

__Сигнатура__:

```csharp
  virtual void ICSSoft.STORMNET.DataObject.CopyTo(DataObject toObject,  
  bool  CreateDataObjectsCopy,  
  bool  PrimaryKeyCopy,  
  bool  UseParentCaching  
 )
 ```

#### Prototyping  

__Назначение__: Прототипизировать

__Параметры__:

`withDetails` - с детейлами или без

__Сигнатура__:

```csharp virtual void ICSSoft.STORMNET.DataObject.Prototyping()
virtual void ICSSoft.STORMNET.DataObject.Prototyping(bool withDetails)
```

#### SetExistObjectPrimaryKey  

__Назначение__: Установить первичный ключ в объект данных. Выполняется операция Clear() для объекта, присваивается первичный ключ, SetLoadingState(LoadingState.LightLoaded); SetLoadedProperties("__PrimaryKey");

__Параметры__:

`primaryKey` - первичный ключ

__Сигнатура__:

```csharp
void ICSSoft.STORMNET.DataObject.SetExistObjectPrimaryKey (object primaryKey)
```

### Блокировка объекта

#### LockObject

__Назначение__: Заблокировать объект

__Параметры__:

`key` - ключ блокировки объекта

__Сигнатура__:

```csharp
void ICSSoft.STORMNET.DataObject.LockObject(object key)
```

#### UnLockObject  

__Назначение__: Разблокировать объект

__Параметры__:

`key` - ключ блокировки объекта.

__Сигнатура__:

```csharp
void ICSSoft.STORMNET.DataObject.UnLockObject (object key)
```
