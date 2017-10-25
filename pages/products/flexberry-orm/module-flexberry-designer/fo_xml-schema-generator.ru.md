---
title: Плагин для генерации XSD-схемы
sidebar:  flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM, plugins
summary: Особенности XMLSchemaGenerator
toc: true
permalink: ru/fo_xml-schema-generator.html
lang: ru
---


`XMLSchemaGenerator` - плагин Flexberry для генерации XSD-схемы базы данных. Результатом работы генератора является XSD-файл со схемой базы данных и XML-файл с пустой базой данных.

`XMLSchemaGenerator` включает следующие настройки:

* Включение\отключение ограничений ссылочной целостности (в том числе и уникальность первичного ключа). За это отвечает свойство EnforceConstraints в XSD-файле.
* Включение\отключение строгих имен для сборок (данная настройка необходима для генерации схемы для разных версий сборок). Эта настройка подменяет PublickKeyToken в cгенерированной XSD-схеме на null.

`XMLSchemaGenerator` поддерживает любые пользовательские типы данных для свойств объектов. Для корректной работы с ними необходимо, чтобы пользовательский тип данных поддерживал интерфейс `System.Xml.Serialization.IXmlSerializable`.

По умолчанию в схему базы данных попадут стандартные таблицы: `STORMAdvLimit`, `STORMFILTERDETAIL`, `STORMFILTERLOOKUP`, `STORMFILTERSETTING`, `STORMNETLOCKDATA`, `STORMSETTINGS`. Изменять их описание в XSD-файле не рекомендуется.

Сгенерированная схема содержит теги с настройками для работы с XML базой данных через `System.Data.DataSet`. Его поддержка осуществлена в [XMLFileDataService](fo_xml-file-ds.html).
