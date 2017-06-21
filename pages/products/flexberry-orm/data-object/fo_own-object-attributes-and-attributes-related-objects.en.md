---
title: Доступ к собственным атрибутам объекта и атрибутам связанных объектов
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: en/fo_own-object-attributes-and-attributes-related-objects.html
folder: products/flexberry-orm/
lang: en
---

## Получение значения свойства

Доступ к атрибутам [объекта данных](fo_dataobject.html) и атрибутам связанных объектов стандартен.

```csharp
SimpleDataObject sdo = new SimpleDataObject();
sdo.Name="Something";//Доступ к собственному атрибуту
sdo.Master.DblAttr=3.14;//Доступ к мастеровому атрибуту
sdo.Details[0).StringAttr="DetailAttribute";//Доступ к детейловому атрибуту
```

## Cтроготипизированное получение свойств в виде строк

Вместо того, чтобы использовать названия атрибутов в виде строк-констант, например:

```csharp
var propertyName = ((Пользователь)dataObject).Наименование;
```

Можно использовать строготипизированный доступ с использованием лямбды методами [`Information`](fo_information-class-as-metadata-supervisor.html):

```csharp
var propertyName = Information.ExtractPropertyName<Пользователь>(x => x.Наименование);
```

```csharp
var propertyName = Information.ExtractPropertyPath<Пользователь>(x => x.Наименование.Полномочия);
```

{% include important.html content="Для получения полей мастера нужо использовать **ExtractPropertyPath**" %}

Все доступные методы: [`ExtractPropertyName`](fo_information-class-as-metadata-supervisor), [`ExtractPropertyPath`](fo_information-class-as-metadata-supervisor), [`ExtractPropertyInfo`](fo_information-class-as-metadata-supervisor).
