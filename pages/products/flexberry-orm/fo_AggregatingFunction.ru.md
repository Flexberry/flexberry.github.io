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

* **Продукт**: [FlexberryORM|Flexberry ORM)
* **Компонент**: [DataObject|Объект данных)
* **Программная библиотека**: ICSSoft.STORMNET.Tools.dll, ICSSoft.STORMNET.DataObject.dll
* **Предназначение**: [DataObject|Объект данных)  можно сериализовать и десериализовать с помощью разных подходов.

</td>
</tr></tbody></table>
</div>

Для того, чтобы сериализовать [DataObject|объект данных) необходимо подключить сборку `[ICSSoftSTORMNETTools|ICSSoft.STORMNET.Tools)`. Для преобразования в XML предназначен класс `[ICSSoftSTORMNETTools#ToolXML|ToolXML)` данной сборки, который поддерживает два варианта сериализации, описанные далее. 

## Сериализация объектов данных специальным способом

Специальные методы `[ICSSoftSTORMNETTools#ToolXML|ToolXML)` для `[DataObject)`:

*  `DataObject2XMLDocument` - конвертация объекта данных к `XmlDocument`,
*  `XMLDocument2DataObject` - обратное преобразование. 

Есть возможность при сериализации [DataObject|объекта данных) сериализовать также и [Master-Association|мастера), а не только его тип и [Primary-keys-objects|первичный ключ), путём передачи соответствующих флагов при вызове метода.
Методы `DataObject2XMLDocument` и `XMLDocument2DataObject` описаны в [ICSSoftSTORMNETTools|статье).

{% include note.html content="Все использующиеся типы данных должны возвращать значение по `ToString()`, а также иметь статический метод `Parse`, конструирующий новый экземпляр." %}

{% include note.html content="В метод `ToolXML.XMLDocument2DataObject()` обязательно нужно передавать сконструированный объект данных, иначе произойдёт `ArgumentNullException`." %}

`Пример сериализации и десериализации [DataObject|объекта данных).`

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

`[ICSSoftSTORMNETTools#ToolXML|ToolXML)` поддерживает метод для SOAP-сериализации. Этот вариант менее привлекателен, поскольку выходная строка содержит много лишней информации и [DataObject|объект данных) должен быть помечен атрибутом `&#0091;Serializable&#0093;`

`Пример сериализации и десериализации [DataObject|объекта данных).`

```
DataObjectWithKeyGuid dataObjectWithKeyGuid = new DataObjectWithKeyGuid();
KeyGuid g = Guid.NewGuid();
dataObjectWithKeyGuid.LinkToMaster = g;
Console.WriteLine("Записали Guid " + dataObjectWithKeyGuid.LinkToMaster);
var serializedObj = ToolXML.ObjectToString(dataObjectWithKeyGuid);

DataObjectWithKeyGuid dObjFromXML = (DataObjectWithKeyGuid)ToolXML.ObjectFromString(serializedObj);
```
