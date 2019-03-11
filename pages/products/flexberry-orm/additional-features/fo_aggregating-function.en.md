--- 
title: Serialization and deserialization of data objects 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, SOAP 
summary: Serialization of the data object to XML string and deserialize 
toc: true 
permalink: en/fo_aggregating-function.html 
lang: en 
autotranslated: true 
hash: ba3a1136e1cdcef4d067b9ada9f6a62c7770e75e1df0a1f4447cbf404bde1a23 
--- 

In order to serialize [data object](fo_data-object.html) you need to connect the Assembly [ICSSoft.STORMNET.Tools](fo_ics-soft-stormnet-tools.html). To convert the XML class is designed `ToolXML` this Assembly, which supports two methods of serialization, described later. 

## Serialization of data objects in a special way 

Special methods [ToolXML](fo_ics-soft-stormnet-tools.html) for [DataObject](fo_data-object.html): 

* `DataObject2XMLDocument` is to convert the data object to `XmlDocument`, 
* `XMLDocument2DataObject` - inverse transformation. 

There is a possibility when you serialize a data object to serialize and also [master](fd_master-association.html), not just its type [primary key](fo_primary-keys-objects.html) by passing the appropriate flags when calling the method. 
Methods `DataObject2XMLDocument` and `XMLDocument2DataObject` described in [article](fo_ics-soft-stormnet-tools.html). 

{% include note.html content="All used data types must return a value for `ToString()` and also have a static method `Parse`, constructing a new instance." %} 

{% include note.html content="To the method `ToolXML.XMLDocument2DataObject()` is required to transfer the constructed data object otherwise there will be `ArgumentNullException`." %} 

### Example of serialization and deserialization of the data object. 

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

##-based Serialization SOAP 

[ToolXML](fo_ics-soft-stormnet-tools.html) supports a method for the SOAP serialization. This option is less attractive because the output contains many redundant information and [data object](fo_data-object.html) must be marked `Serializable` 

### Example of serialization and deserialization of the data object. 

```csharp
DataObjectWithKeyGuid dataObjectWithKeyGuid = new DataObjectWithKeyGuid();
KeyGuid g = Guid.NewGuid();
dataObjectWithKeyGuid.LinkToMaster = g;
Console.WriteLine("Recorded Guid " + dataObjectWithKeyGuid.LinkToMaster);
var serializedObj = ToolXML.ObjectToString(dataObjectWithKeyGuid);

DataObjectWithKeyGuid dObjFromXML = (DataObjectWithKeyGuid)ToolXML.ObjectFromString(serializedObj);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}