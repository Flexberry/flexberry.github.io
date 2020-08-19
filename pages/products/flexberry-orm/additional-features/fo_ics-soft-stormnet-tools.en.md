--- 
title: ICSSoft.STORMNET.Tools.XmlTools 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, libraries, serialization, deserialization 
summary: Tools for serialization-deserialization 
toc: true 
permalink: en/fo_ics-soft-stormnet-tools.html 
lang: en 
autotranslated: true 
hash: ae0a5e23d6236ca73e1d4c2ded194546dcce7bad53810a34363c6e68dfe57a7b 
--- 

Library `ICSSoft.STORMNET.Tools.dll` provides various tools to work in her classes implemented functions: 

* serialization-deserialization 
* XML, 
* send mail 
* file compression 
* processing of signed assemblies 
* , etc. 

Data helper methods recommended for application developers. 

## Tools for serialization-deserialization 

When choosing between binary and SOAP serialization should be aware that binary form serialization more productive and result strings get shorter. 

### ICSSoft.STORMNET.Tools.ToolXML 

`ICSSoft.STORMNET.Tools.ToolXML` tool for serialization-deserialization in XML. 

This class implements two versions of the transformation in XML: 

* SOAP serialization using a standard `System.Runtime.Serialization.Formatters.Soap.SoapFormatter`, 
* implemented conversion [data object](fo_data-object.html) to `XmlDocument` applicable only for [`ICSSoft.STORMNET.DataObject`](fo_data-object.html) (this variant is available in the [article](fo_aggregating-function.html)). 

The class implements the following methods: 

#### [`XMLDocument2DataObject`](fo_aggregating-function.html) 

__Appointment___: Receiving a data object from previously received XML document. This method is written specifically for [`ICSSoft.STORMNET.DataObject`](fo_data-object.html) 

__Settings__: 

* `dataObject` - the data Object to deserialize 
* `xmlDoc` - Serialized object data 

``` csharp
public static void XMLDocument2DataObject(ref ICSSoft.STORMNET.DataObject dataObject, XmlDocument xmlDoc)
``` 

#### [`DataObject2XMLDocument`](fo_aggregating-function.html) 

__Appointment___: Receiving an XML document from a data object. This type of serialization is written specifically for [`ICSSoft.STORMNET.DataObject`](fo_data-object.html) and is based on iterating properties, datalow and references to masters, followed by the entry of all this `XMLDocument`. 

__Settings__: 

* `dataObject` - the object data 
* `serializeAggregators` - to serialize detaily 
* `setObjectLoadingStateLoaded` - Set LoadingState object in Loaded 
* `setObjectStatusCreated` - Set the ObjectStatus object Created in 
* `serializeMasters` - to do a complete serialization of the masters of objects 

__The result is__: a Serialized representation of the object 

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

__Assign__: the serialization of the object using `SoapFormatter`.

__Settings__: 

`o` - the Object to serialize 

__The result is__: a Serialized object 

``` csharp
public static string ObjectToString(object o)
``` 

#### [`ObjectFromString`](fo_aggregating-function.html) 

__Assign__: deserialize the object using the `SoapFormatter`. 

__Settings__: 

`s` - Serialized object 

__The result is__: Restored object 

``` csharp
public static object ObjectFromString(string s)
``` 

### ICSSoft.STORMNET.Tools.ToolBinarySerializer 

`ICSSoft.STORMNET.Tools.ToolBinarySerializer` tool for binary serialization-deserialization. 

Serialized bytes are converted to `ToBase64String`. The implementation is based on применении`System.Runtime.Serialization.Formatters.Binary.BinaryFormatter`. 

The class implements the following methods: 

#### `ObjectToString` 

__Assign__: the serialization of the object using `BinaryFormatter`. 

__Settings__: 

`o` Object 

__The result is__: String. 

``` csharp
public static string ObjectToString(object o)
``` 

#### `ObjectFromString` 

__Assign__: deserialize the object using the `BinaryFormatter`. 

__Settings__: 

* `s` - Serialized object 
* `binder` - Binder, which you must specify when deserialization need to implement your own logic for searching types. 

__The result is__: the Restored object. 

``` csharp
// 1. 
public static object ObjectFromString(string s)

// 2. 
public static object ObjectFromString(string s, SerializationBinder binder)
``` 

## ICSSoft.STORMNET.Tools.XmlTools 

`ICSSoft.STORMNET.Tools.XmlTools` class for working with XML. 

The class implements the following methods: 

### `GetXDocumentByXElement` 

__Assign__: Conversion `System.Xml.Linq.XElement` in `System.Xml.XmlDocument`. 

__Settings__: 

* `xElement` - xElement to convert. 
* `versionXmlDoc` Version of the generated xml document. 
* `encodingXmlDoc` - Encoding for the generated xml document. 

__The result is__: the Result of conversion - `XmlDocument`. 

``` csharp
public static XmlDocument GetXDocumentByXElement(XElement xElement, string versionXmlDoc, string encodingXmlDoc)
``` 

### `LoadXml` 

__Assign__: Read an Xml file. Can automatically detect the encoding based on the encoding, used inside Xml. 

__Settings__: 

* `filePath` - Path to the Xml file. 
* `encoding` - the character Encoding to use to read the Xml file. 

``` csharp
public static XmlDocument LoadXml(string filePath, Encoding encoding = null)
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}