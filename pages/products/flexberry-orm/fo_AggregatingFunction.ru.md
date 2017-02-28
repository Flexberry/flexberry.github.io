---
title: Сериализация объекта данных в строку XML и десериализация
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_aggregating-function.html
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">

* **Продукт**: [Flexberry ORM](flexberry-o-r-m.html)
* **Компонент**: [Объект данных](dataobject.html)
* **Программная библиотека**: ICSSoft.STORMNET.Tools.dll, ICSSoft.STORMNET.DataObject.dll
* **Предназначение**: [Объект данных](dataobject.html)  можно сериализовать и десериализовать с помощью разных подходов.

</td>
</tr></tbody></table></a>
</div>

## Сериализация объектов данных специальным способом
Для того, чтобы сериализовать [объект данных](dataobject.html) необходимо подключить сборку `ICSSoft.STORMNET.Tools`.

Конвертация объекта данных к `XmlDocument` происходит вызовом метода `ToolXML.DataObject2XMLDocument`, обратно — вызовом `ToolXML.XMLDocument2DataObject`.

Данный вид сериализации написан специально для `ICSSoft.STORMNET.DataObject` и основан на переборе свойств, детейлов и ссылок на мастеров с последующим занесением всего этого в `XMLDocument`.

Есть возможность при сериализации [объекта данных](dataobject.html) сериализовать также и [мастера](master--association.html), а не только его тип и [первичный ключ](primary-keys-objects.html), путём передачи соответствующих флагов при вызове метода.

```cs
/// <summary>
/// Получение XML документа из объекта данных
/// </summary>
/// <param name="dataObject"> Сам объект данных </param>
/// <param name="serializeAggregators"> Следует ли сериализовать детейлы </param>
/// <param name="setObjectLoadingStateLoaded"> Установить LoadingState объекта в Loaded </param>
/// <param name="setObjectStatusCreated"> Установить ObjectStatus объекта в Created </param>
/// <param name="serializeMasters"> Проводить полную сериализацию мастеров объектов </param>
/// <returns> Сериализованное представление объекта </returns>
public static XmlDocument DataObject2XMLDocument(
	ref ICSSoft.STORMNET.DataObject dataObject, 
	bool serializeAggregators,
	bool setObjectLoadingStateLoaded, 
	bool setObjectStatusCreated, 
	bool serializeMasters)
```

(((<msg type=note>Все использующиеся типы данных должны возвращать значение по `ToString()`, а также иметь статический метод `Parse`, конструирующий новый экземпляр.</msg>)))

(((<msg type=note>В метод `ToolXML.XMLDocument2DataObject()` обязательно нужно передавать сконструированный объект данных, иначе произойдёт `ArgumentNullException`.</msg>)))

**Пример сериализации и десериализации [объекта данных](dataobject.html).**

```cs
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
`ToolXML` поддерживает метод для SOAP-сериализации. Этот вариант менее привлекателен, поскольку выходная строка содержит много лишней информации и [объект данных](dataobject.html) должен быть помечен атрибутом `&#0091;Serializable&#0093;`

**Пример сериализации и десериализации [объекта данных](dataobject.html).**

```
DataObjectWithKeyGuid dataObjectWithKeyGuid = new DataObjectWithKeyGuid();
KeyGuid g = Guid.NewGuid();
dataObjectWithKeyGuid.LinkToMaster = g;
Console.WriteLine("Записали Guid " + dataObjectWithKeyGuid.LinkToMaster);
var serializedObj = ToolXML.ObjectToString(dataObjectWithKeyGuid);

DataObjectWithKeyGuid dObjFromXML = (DataObjectWithKeyGuid)ToolXML.ObjectFromString(serializedObj);
```
