---
title: DataObject
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public, Ключевые понятия, Черновик статьи
toc: true
permalink: ru/fo_dataobject.html
---

## Описание
В Flexberry ORM **объектами данных** называются экземпляры классов наследников абстрактного класса `ICSSoft.STORMNET.DataObject`. Для объектов данных генераторами Flexberry создаётся код класса на .Net CLR-совместимом языке (C#, VB и т.п.).

## Объекты данных в модели
[Классы объектов данных](fo_data-object-generated-structure.html) описываются на [диаграмме классов](fd_class-diagram-constraction.html) в [Flexberry Designer](fd_landing_page.html) и имеют стереотип [implementation](fd_data-classes.html). При описании объекта данных задаются [атрибуты](fo_attributes-class-data.html), связи и метаданные. Атрибуты объекта данных могут быть двух типов стандартные (описанные на диаграмме) и динамические [динамические свойства объекта данных](fo_dynamic-properties.html). В случае если значение атрибута не предполагается размещать в хранилище, он может быть объявлен как [нехранимый](fo_not-stored-attributes.html). При описании атрибутов им могут быть присвоены [значения по умолчанию](fo_features-of-dafault-value-assignment.html). Также в Flexberry ORM поддерживаются несколько видов связей: [наследование](fo_inheritance.html), [мастеровые](fd_master-association.html) и [детейловые](fo_detail-associations-and-their-properties.html) связи.

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
* [Прототипирование (Создание объекта данных на основе существующего)](fo_data-object-prototype.html)
* [Копирование](fo_copying-data-objects.html).
* [Десериализация ранее сохраненного объекта](fo_aggregating-function.html).



## Обработка объекта данных

Одним из основных атрибутов объекта является [ключ объекта](fo_primary-keys-objects.html), для доступа к которому используется свойство `PrimaryKey`. В большинстве случаев для ключа используется глобальный уникальный идентификатор (GUID), хотя при необходимости могут быть использованы другие типы.

Наиболее часто встречаются следующие задачи обработки объектов данных: 

* [Изменение значений атрибутов](fo_own-object-attributes-and-attributes-related-objects.html).
* [Получение информации о статусе объекта](fo_processing-status-and-condition-of-load-object-data-services.html).
* [Получение информации о загруженных свойствах](fo_get-loaded-properties-and--check-loaded-property.html).
* [Получение метаданных объекта](fo_information-class-as-metadata-supervisor.html).
* [Блокировка объекта для монопольного редактирования](fo_lock-service.html).
* [Проверка совместимости объекта и представления](fo_test-object-for-viewing.html).
* [Получение презентационного значения для объекта данных](fo_data-object-get-presentation-value.html)
* [Дочитка объекта данных](fo_additional-loading-data-object.html)


## Сохранение объекта данных в хранилище
Для сохранения объекта данных используются методы [сервиса данных](fo_data-service.html) UpdateObject/UpdateObjects.

## Основные свойство и методы ICSSoft.STORMNET.DataObject

### Работа с копией данных

### `ContainsAlteredProps`

__Назначение__: Установить было ли изменение значений свойств объекта по сравнению с внутренней копией 

__Сигнатура__:

```cs
bool ICSSoft.STORMNET.DataObject.ContainsAlteredProps()
```

### `GetAlteredPropertyNames` 

__Назначение__: Возвращает список свойств (атрибутов, мастеров, детейлов), значения которых изменились по сравнению с внутренней копией объекта.

__Сигнатура__:

```cs
string[] ICSSoft.STORMNET.DataObject.GetAlteredPropertyNames()
```

### `GetDataCopy`

__Назначение__: Получить внутреннюю копию объекта данных

__Сигнатура__:

```cs
DataObject ICSSoft.STORMNET.DataObject.GetDataCopy()
```

### `InitDataCopy` 

__Назначение__: Проинициализировать копию данных

__Параметры__:
 
* DataObjectCache 
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.InitDataCopy()
```

```cs
void ICSSoft.STORMNET.DataObject.InitDataCopy(DataObjectCache DataObjectCache)
```

### `SetDataCopy`

__Назначение__: Установить внутреннюю копию объекта данных

__Параметры__:
 
* value - Устанавливаемый объект как копия существующего 
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.SetDataCopy (DataObject value) 
```

### `CheckNotNullProperties`  

__Назначение__: Вернуть список незаполненных полей (значения не могут быть пустыми согласно модели данных)

__Параметры__:
    
* `detailSkip` - Пропускать при проверке удаленные детейлы. Словарь со списком типов детейлов и флагами для них. Если детейла нет в словаре или значение для него False, то проверка детейла не будет пропущена. Параметр может иметь значение null.
* `view` - Представление, по свойствам которого происходит проверка и возвращение заголовков свойств. 
* `returnCaptions` - true при необходимости вернуть заголовки свойств из представления, а не имена свойств, false в противном случае.

__Сигнатура__:

```cs
public string[] CheckNotNullProperties()
```

```cs
string [] ICSSoft.STORMNET.DataObject.CheckNotNullProperties (Dictionary<Type, bool> detailSkip)
```

```cs
public string[] CheckNotNullProperties(View view, bool returnCaptions)
```

```cs
public string[] CheckNotNullProperties(View view, bool returnCaptions, Dictionary<Type, bool> detailSkip)
```

 ### `CopyTo`  

__Назначение__: Создать копию объекта данных. Если полученный в результате копирования объект планируется поместит в хранилище, у него необходимо   вызвать методы `InitDataCopy` или `ClearDataCopy`.

__Параметры__:
    
* `toObject` = объект данный, в который  
* `CreateDataObjectsCopy` - true - создавать копии связанных объектов, false - ограничиться копированием ссылки 
* `PrimaryKeyCopy` - true - копировать первичные ключи 
* `UseParentCaching` - использовать ранее установленное кеширование 

__Сигнатура__:

```cs 
  virtual void ICSSoft.STORMNET.DataObject.CopyTo(DataObject toObject,  
  bool  CreateDataObjectsCopy,  
  bool  PrimaryKeyCopy,  
  bool  UseParentCaching  
 )
 ```

### `GetStatus`

__Назначение__: Получение значения статуса объекта

__Параметры__:
 
* recountIfAutoaltered - обновить значение принудительно (если класс с автоматическим вычислением статуса)
 
__Сигнатура__:

```cs
ObjectStatus ICSSoft.STORMNET.DataObject.GetStatus()
```

```cs
ObjectStatus ICSSoft.STORMNET.DataObject.GetStatus(bool recountIfAutoaltered)
```

### `LockObject` 

__Назначение__: Заблокировать объект

__Параметры__:
 
* key ключ блокировки объекта
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.LockObject(object key)
```

### `Prototyping`  

__Назначение__: Прототипизировать

__Параметры__:
 
* withDetails - с детейлами или без
 
__Сигнатура__:

```cs virtual void ICSSoft.STORMNET.DataObject.Prototyping()
virtual void ICSSoft.STORMNET.DataObject.Prototyping(bool withDetails)
```

### `SetExistObjectPrimaryKey`  

__Назначение__: Установить первичный ключ в объект данных. Выполняется операция Clear() для объекта, присваивается первичный ключ, SetLoadingState(LoadingState.LightLoaded); SetLoadedProperties("__PrimaryKey");

__Параметры__:
 
* primaryKey Первичный ключ 
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.SetExistObjectPrimaryKey (object primaryKey)
```

### `SetLoadingState`  

__Назначение__: Установка состояния загрузки.

__Параметры__:
 
* newState - Состояние загрузки, устанавливаемое объекту. 
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.SetLoadingState (LoadingState newState)
```

### `SetLoadedProperties` 

__Назначение__: Установка состояния загрузки.

__Параметры__:
 
* newState - Состояние загрузки, устанавливаемое объекту. 
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.SetLoadingState (LoadingState newState)
```

### `SetStatus`  

__Назначение__: Установка статуса

__Параметры__:
 
* newState - Статус, устанавливаемый объекту.
 
__Сигнатура__:

```cs
virtual void ICSSoft.STORMNET.DataObject.SetStatus (ObjectStatus newState)
```

### `UnLockObject`  

__Назначение__: Разблокировать объект

__Параметры__:
 
* `key` - ключ блокировки объекта.
 
__Сигнатура__:

```cs
void ICSSoft.STORMNET.DataObject.UnLockObject (object key)
```

|

