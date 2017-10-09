---
title: Типы данных во Flexberry ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, типы данных
summary: Краткая характеристика поддерживаемых Flexberry ORM типов
toc: true
permalink: ru/fo_flexberry-orm-types.html
lang: ru
---

[Flexberry ORM](fo_flexberry-orm.html) позволяет осуществлять работу со следующими типами:

* .Net основные типы (такие как int, string, float и др.)
* .Net [Nullable-типы](fd_nullable-types.html).
* [Пользовательские типы](fo_convert-type-property.html).
* [Nullable-типы NullableDateTime, NullableInt, NullableDecimal](fd_nullable-types.html) (располагаются в сборке ICSSoft.STORMNET.UserDataTypes):
* Дополнительные типы [Flexberry ORM](fo_flexberry-orm.html) (эти типы сериализуются в формате XML посредством Implicit-преобразований и хранятся в базе данных (или любом другом хранилище) в сериализованном виде; поиск по атрибутам указанных типов можно производить только по подстроке и это является основным отличием от полноценной структуры данных; располагаются в сборке ICSSoft.STORMNET.UserDataTypes).
    * Contact - контакт пользователя (клиента или любой другой личности).
    * Event - календарное событие.
    * GeoData - геоданные (`Feature`).
    * Image - изображение.
    * WebFile - Веб-файл.
* [Типы на основе объектов данных](fo_data-object-as-attribute-type.html).

## Карта типов

Ключевым элементом для определения отображения используемых на [диаграмме классов](fd_class-diagram.html) типов в типы базы данных или программного кода является [карта типов](fd_types-map.html).
