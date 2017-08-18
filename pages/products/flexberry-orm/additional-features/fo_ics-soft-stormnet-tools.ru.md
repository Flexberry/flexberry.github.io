---
title: ICSSoft.STORMNET.Tools.XmlTools
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, библиотеки, сериализация, десереализация
summary: Инструменты для сериализации-десериализации
toc: true
permalink: ru/fo_ics-soft-stormnet-tools.html
lang: ru
---

Библиотека `ICSSoft.STORMNET.Tools.dll` предоставляет различные вспомогательные инструменты для работы, в её классах реализованы функции:

* сериализации-десериализации,
* работы с XML,
* отправки почты,
* сжатия файлов,
* обработки подписанных сборок
* и др..

Данные вспомогательные методы рекомендованы к применению разработчикам.

## Инструменты для сериализации-десериализации

При выборе между бинарной и SOAP-сериализацией следует учесть, что бинарный вид сериализации более производительный и результирующие строки получаются короче.

### ICSSoft.STORMNET.Tools.ToolXML

`ICSSoft.STORMNET.Tools.ToolXML` - инструмент для сериализации-десериализации в XML.

Данный класс реализует два варианта преобразований в XML:

* SOAP сериализация с помощью стандартного `System.Runtime.Serialization.Formatters.Soap.SoapFormatter`,
* специально реализованная конвертация [объекта данных](fo_data-object.html) к `XmlDocument`, применима только для [`ICSSoft.STORMNET.DataObject`](fo_data-object.html) (информация о применении данного варианта доступна в [статье](fo_aggregating-function.html)).

В классе реализованы следующие методы:

#### [`XMLDocument2DataObject`](fo_aggregating-function.html)

__Назначение__: Получение объекта данных из ранее полученного XML документа. Данный метод написан специально для [`ICSSoft.STORMNET.DataObject`](fo_data-object.html)

__Параметры__:
 
* `dataObject` - Объект данных, в который будем десериализовывать 
* `xmlDoc` - Сериализованный объект данных 

``` csharp
public static void XMLDocument2DataObject(ref ICSSoft.STORMNET.DataObject dataObject, XmlDocument xmlDoc)
```

#### [`DataObject2XMLDocument`](fo_aggregating-function.html)

__Назначение__: Получение XML документа из объекта данных. Данный вид сериализации написан специально для [`ICSSoft.STORMNET.DataObject`](fo_data-object.html) и основан на переборе свойств, детейлов и ссылок на мастеров с последующим занесением всего этого в `XMLDocument`. 

__Параметры__:
 
* `dataObject` - Сам объект данных 
* `serializeAggregators` - Следует ли сериализовать детейлы 
* `setObjectLoadingStateLoaded` - Установить LoadingState объекта в Loaded 
* `setObjectStatusCreated` - Установить ObjectStatus объекта в Created 
* `serializeMasters` - Проводить полную сериализацию мастеров объектов 

__Возвращаемый результат__: Сериализованное представление объекта 

``` csharp
// 1.
public static XmlDocument DataObject2XMLDocument(ref ICSSoft.STORMNET.DataObject dataObject)

// 2.
public static XmlDocument DataObject2XMLDocument(ref ICSSoft.STORMNET.DataObject dataObject, bool serializeAggregators)

// 3.
public static XmlDocument DataObject2XMLDocument(ref ICSSoft.STORMNET.DataObject dataObject, bool serializeAggregators, bool setObjectLoadingStateLoaded, bool setObjectStatusCreated)

// 4.
public static XmlDocument DataObject2XMLDocument(
            ref ICSSoft.STORMNET.DataObject dataObject, 
            bool serializeAggregators,
            bool setObjectLoadingStateLoaded, 
            bool setObjectStatusCreated, 
            bool serializeMasters)
```

#### [`ObjectToString`](fo_aggregating-function.html)

__Назначение__: Сериализация объекта при помощи `SoapFormatter`. 

__Параметры__:
 
`o` - Объект для сериализации

__Возвращаемый результат__: Сериализованный объект

``` csharp
public static string ObjectToString(object o)
```

#### [`ObjectFromString`](fo_aggregating-function.html)

__Назначение__: Десериализация объекта при помощи `SoapFormatter`. 

__Параметры__:
 
`s` - Сериализованный объект

__Возвращаемый результат__: Востановленный объект 

``` csharp
public static object ObjectFromString(string s)
```

### ICSSoft.STORMNET.Tools.ToolBinarySerializer

`ICSSoft.STORMNET.Tools.ToolBinarySerializer` - инструмент для бинарной сериализации-десериализации.

Сериализованные байты конвертируются в `ToBase64String`. Реализация основана на применении`System.Runtime.Serialization.Formatters.Binary.BinaryFormatter`.

В классе реализованы следующие методы:

#### `ObjectToString`

__Назначение__: Сериализация объекта при помощи `BinaryFormatter`.

__Параметры__:

`o` - Объект

__Возвращаемый результат__: Строка.

``` csharp
public static string ObjectToString(object o)
```

#### `ObjectFromString`

__Назначение__: Десериализация объекта при помощи `BinaryFormatter`.

__Параметры__:

* `s` - Сериализованный объект
* `binder` - Binder, который необходимо указать, если при десереализации необходимо реализовать собственную логику по поиску типов. 

__Возвращаемый результат__: Востановленный объект.

``` csharp
// 1.
public static object ObjectFromString(string s)

// 2.
public static object ObjectFromString(string s, SerializationBinder binder)
```

## ICSSoft.STORMNET.Tools.XmlTools

`ICSSoft.STORMNET.Tools.XmlTools` - класс для работы с XML.

В классе реализованы следующие методы:

### `GetXDocumentByXElement`

__Назначение__: Конвертация `System.Xml.Linq.XElement` в `System.Xml.XmlDocument`.

__Параметры__:

* `xElement` - xElement для конвертации.
* `versionXmlDoc` - Версия создаваемого xml документа.
* `encodingXmlDoc` - Кодировка для создаваемого xml документа.

__Возвращаемый результат__: Результат конвертации - `XmlDocument`.

``` csharp
public static XmlDocument GetXDocumentByXElement(XElement xElement, string versionXmlDoc, string encodingXmlDoc)
```

### `LoadXml`

__Назначение__: Прочитать Xml-файл. Может автоматически определить кодировку на основании кодировки, использующейся внутри Xml.

__Параметры__:

* `filePath` - Путь до Xml-файла.
* `encoding` - Кодировка, которую необходимо использовать для чтения Xml-файла.

``` csharp
public static XmlDocument LoadXml(string filePath, Encoding encoding = null)
```
