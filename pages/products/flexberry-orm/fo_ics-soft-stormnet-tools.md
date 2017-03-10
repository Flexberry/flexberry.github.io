---
title: ICSSoft.STORMNET.Tools.XmlTools
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_ics-soft-stormnet-tools.html
folder: products/flexberry-orm/
lang: ru
---

`ICSSoft.STORMNET.Tools.XmlTools` - класс для работы с XML.

В классе реализованы следующие методы:

## GetXDocumentByXElement

 __Назначение__: Конвертация `System.Xml.Linq.XElement` в `System.Xml.XmlDocument`.

 __Параметры__:

 `xElement` - xElement для онвертации.

 `versionXmlDoc` - Версия создаваемого xml документа.

 `encodingXmlDoc` - Кодировка для создаваемого xml документа.

 __Возвращаемый результат__: Результат конвертации - `XmlDocument`.

 __Сигнатура__:

```csharp
public static XmlDocument GetXDocumentByXElement(XElement xElement, string versionXmlDoc, string encodingXmlDoc) 
```

## LoadXml

__Назначение__: Прочитать Xml-файл. Может автоматически определить кодировку на основании кодировки, использующейся внутри Xml.

__Параметры__:

`filePath` - Путь до Xml-файла.

`encoding` - Кодировка, которую необходимо использовать для чтения Xml-файла.

__Сигнатура__:

```csharp
public static XmlDocument LoadXml(string filePath, Encoding encoding = null) 
```


