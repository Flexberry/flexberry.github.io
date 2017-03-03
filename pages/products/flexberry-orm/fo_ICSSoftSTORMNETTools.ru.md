---
title: ICSSoft.STORMNET.Tools.XmlTools
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_i-c-s-soft-s-t-o-r-m-n-e-t-tools.html
---

# icssoft-stormnet-tools-xml-tools
`ICSSoft.STORMNET.Tools.XmlTools` - класс для работы с XML.

В классе реализованы следующие методы:

## get-x-document-by-x-element
 __Назначение__: Конвертация `System.Xml.Linq.XElement` в `System.Xml.XmlDocument`.

 __Параметры__:

 `xElement` - xElement для онвертации.

 `versionXmlDoc` - Версия создаваемого xml документа.

 `encodingXmlDoc` - Кодировка для создаваемого xml документа.

 __Возвращаемый результат__: Результат конвертации - `XmlDocument`.

 __Сигнатура__:

```cpp
public static XmlDocument GetXDocumentByXElement(XElement xElement, string versionXmlDoc, string encodingXmlDoc) 
```

## load-xml

__Назначение__: Прочитать Xml-файл. Может автоматически определить кодировку на основании кодировки, использующейся внутри Xml.

__Параметры__:

`filePath` - Путь до Xml-файла.

`encoding` - Кодировка, которую необходимо использовать для чтения Xml-файла.

__Сигнатура__:

```cpp
public static XmlDocument LoadXml(string filePath, Encoding encoding = null) 
```
