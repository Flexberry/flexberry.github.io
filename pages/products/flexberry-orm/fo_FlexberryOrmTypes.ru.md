---
title: Поддерживаемая во Flexberry ORM типизация
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_flexberry-orm-types.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](fo_flexberry-orm.html)
* '''Предназначение''': Описаны основные поддерживаемые во [Flexberry ORM](fo_flexberry-orm.html) типы.
</td>
</tr></tbody></table></a>
</div>
# Поддерживаемая во Flexberry ORM типизация
[Flexberry ORM](fo_flexberry-orm.html) позволяет осуществлять работу со следующими типами:
* .Net основные типы (такие как int, string, float и др.)
* .Net [Nullable-типы](nullable-types.html).
* [Пользовательские типы](convert-type-property-object-data-to-type-storage.html).
* [Nullable-типы NullableDateTime, NullableInt, NullableDecimal](nullable-types.html) (располагаются в сборке ICSSoft.STORMNET.UserDataTypes):
* Дополнительные типы [Flexberry ORM](fo_flexberry-orm.html) (эти типы сериализуются в формате XML посредством Implicit-преобразований и хранятся в базе данных (или любом другом хранилище) в сериализованном виде; поиск по атрибутам указанных типов можно производить только по подстроке и это является основным отличием от полноценной структуры данных; располагаются в сборке ICSSoft.STORMNET.UserDataTypes).
** Contact - контакт пользователя (клиента или любой другой личности).
** Event - календарное событие.
** GeoData - геоданные (`Feature`).
** Image - изображение.
** WebFile - Веб-файл.
* [Типы на основе объектов данных](dataobject-as-attribute-type.html).

# Карта типов
Ключевым элементом для определения отображения используемых на [диаграмме классов](fd_class-diagram.html) типов в типы базы данных или программного кода является [карта типов](types-map.html).
