---
title: Сериализация объекта данных в строку XML и десериализация
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_aggregating-function.html
---

Для того, чтобы сериализовать [объект данных](fo_dataobject.html) необходимо подключить сборку [ICSSoft.STORMNET.Tools](fo_ics-soft-stormnet-tools.html). Для преобразования в XML предназначен класс [ToolXML](fo_ics-soft-stormnet-tools.html) данной сборки, который поддерживает два варианта сериализации, описанные далее. 

## Сериализация объектов данных специальным способом

Специальные методы [ToolXML](fo_ics-soft-stormnet-tools.html) для [DataObject](fo_dataobject.html):

*  `DataObject2XMLDocument` - конвертация объекта данных к `XmlDocument`,
*  `XMLDocument2DataObject` - обратное преобразование. 

Есть возможность при сериализации [объекта данных](fo_dataobject.html) сериализовать также и [мастера](fd_master-association.html), а не только его тип и [первичный ключ](fo_primary-keys-objects.html), путём передачи соответствующих флагов при вызове метода.
Методы `DataObject2XMLDocument` и `XMLDocument2DataObject` описаны в [статье](fo_ics-soft-stormnet-tools.html).

{% include note.html content="Все использующиеся типы данных должны возвращать значение по `ToString()`, а также иметь статический метод `Parse`, конструирующий новый экземпляр." %}

{% include note.html content="В метод `ToolXML.XMLDocument2DataObject()` обязательно нужно передавать сконструированный объект данных, иначе произойдёт `ArgumentNullException`." %}

`Пример сериализации и десериализации` [объекта данных](fo_dataobject.html).

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

[ToolXML](fo_ics-soft-stormnet-tools.html) поддерживает метод для SOAP-сериализации. Этот вариант менее привлекателен, поскольку выходная строка содержит много лишней информации и [объект данных](fo_dataobject.html) должен быть помечен атрибутом `Serializable`

`Пример сериализации и десериализации` [объекта данных](fo_dataobject.html).

```
DataObjectWithKeyGuid dataObjectWithKeyGuid = new DataObjectWithKeyGuid();
KeyGuid g = Guid.NewGuid();
dataObjectWithKeyGuid.LinkToMaster = g;
Console.WriteLine("Записали Guid " + dataObjectWithKeyGuid.LinkToMaster);
var serializedObj = ToolXML.ObjectToString(dataObjectWithKeyGuid);

DataObjectWithKeyGuid dObjFromXML = (DataObjectWithKeyGuid)ToolXML.ObjectFromString(serializedObj);
```
