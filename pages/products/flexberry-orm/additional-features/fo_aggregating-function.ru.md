---
title: Сериализация и десериализация объектов данных
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, SOAP
summary: Сериализация объекта данных в строку XML и десериализация
toc: true
permalink: ru/fo_aggregating-function.html
lang: ru
---

Для того чтобы сериализовать [объект данных](fo_data-object.html) необходимо подключить сборку [ICSSoft.STORMNET.Tools](fo_ics-soft-stormnet-tools.html). Для преобразования в XML предназначен класс `ToolXML` данной сборки, который поддерживает два варианта сериализации, описанные далее. 

## Сериализация объектов данных специальным способом

Специальные методы [ToolXML](fo_ics-soft-stormnet-tools.html) для [DataObject](fo_data-object.html):

*  `DataObject2XMLDocument` - конвертация объекта данных к `XmlDocument`,
*  `XMLDocument2DataObject` - обратное преобразование. 

Есть возможность при сериализации объекта данных сериализовать также и [мастера](fd_master-association.html), а не только его тип и [первичный ключ](fo_primary-keys-objects.html), путём передачи соответствующих флагов при вызове метода.
Методы `DataObject2XMLDocument` и `XMLDocument2DataObject` описаны в [статье](fo_ics-soft-stormnet-tools.html).

{% include note.html content="Все использующиеся типы данных должны возвращать значение по `ToString()`, а также иметь статический метод `Parse`, конструирующий новый экземпляр." %}

{% include note.html content="В метод `ToolXML.XMLDocument2DataObject()` обязательно нужно передавать сконструированный объект данных, иначе произойдёт `ArgumentNullException`." %}

### Пример сериализации и десериализации объекта данных.

``` csharp
DataObject sdo = new SimpleDataObject();
((SimpleDataObject)sdo).Name="Simple data object name";
((SimpleDataObject)sdo).IntAttr = 991;

XmlDocument xmldoc = ToolXML.DataObject2XMLDocument(ref sdo);

Console.WriteLine("SimpleDataObject in XML:");
Console.WriteLine(xmldoc.InnerXml);

DataObject sdorestoredfromxml = new SimpleDataObject();
ToolXML.XMLDocument2DataObject(ref sdorestoredfromxml, xmldoc);

Console.WriteLine("Restored SimpleDataObject Name:");
Console.WriteLine( ((SimpleDataObject)sdorestoredfromxml).Name );

Console.Read();
```

## Сериализация на основе SOAP

[ToolXML](fo_ics-soft-stormnet-tools.html) поддерживает метод для SOAP-сериализации. Этот вариант менее привлекателен, поскольку выходная строка содержит много лишней информации и [объект данных](fo_data-object.html) должен быть помечен атрибутом `Serializable`

### Пример сериализации и десериализации объекта данных.

```csharp
DataObjectWithKeyGuid dataObjectWithKeyGuid = new DataObjectWithKeyGuid();
KeyGuid g = Guid.NewGuid();
dataObjectWithKeyGuid.LinkToMaster = g;
Console.WriteLine("Записали Guid " + dataObjectWithKeyGuid.LinkToMaster);
var serializedObj = ToolXML.ObjectToString(dataObjectWithKeyGuid);

DataObjectWithKeyGuid dObjFromXML = (DataObjectWithKeyGuid)ToolXML.ObjectFromString(serializedObj);
```
