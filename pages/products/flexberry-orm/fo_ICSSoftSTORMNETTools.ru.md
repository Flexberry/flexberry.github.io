---
title: ICSSoft.STORMNET.Tools.XmlTools
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_i-c-s-soft-s-t-o-r-m-n-e-t-tools.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': ICSSoft.STORMNET.Tools
* '''Программная библиотека''': ICSSoft.STORMNET.Tools.dll
* '''Предназначение''': Инструменты для работы с Xml.
</td>
</tr></tbody></table></a>
</div>

# ICSSoft.STORMNET.Tools.XmlTools
`ICSSoft.STORMNET.Tools.XmlTools` - класс для работы с XML.

В классе реализованы следующие методы:

{| border="1"
! Метод
! Описание
|-
| `GetXDocumentByXElement`
| __Назначение__: Конвертация `System.Xml.Linq.XElement` в `System.Xml.XmlDocument`.

__Параметры__:

`xElement` - xElement для конвертации.

`versionXmlDoc` - Версия создаваемого xml документа.

`encodingXmlDoc` - Кодировка для создаваемого xml документа.

__Возвращаемый результат__: Результат конвертации - `XmlDocument`.

__Сигнатура__:

```cs
public static XmlDocument GetXDocumentByXElement(XElement xElement, string versionXmlDoc, string encodingXmlDoc) ```
|-
| `LoadXml`
| __Назначение__: Прочитать Xml-файл. Может автоматически определить кодировку на основании кодировки, использующейся внутри Xml.

__Параметры__:

`filePath` - Путь до Xml-файла.

`encoding` - Кодировка, которую необходимо использовать для чтения Xml-файла.

__Сигнатура__:

```cs
public static XmlDocument LoadXml(string filePath, Encoding encoding = null) ```
|}


