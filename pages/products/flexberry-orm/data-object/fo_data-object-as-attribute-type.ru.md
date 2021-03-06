---
title: Атрибут класса типа объекта данных
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, типы данных, атрибуты
summary: Нюансы использования объекта данных в качестве типа для атрибута класса
toc: true
permalink: ru/fo_data-object-as-attribute-type.html
lang: ru
---

Если на [диаграмме классов](fd_class-diagram.html) нужен [атрибут](fo_attributes-class-data.html) с типом, унаследованным от [DataObject](fo_data-object.html), нужно:

* сгенерировать и откомпилировать этот тип,
* создать требуемый класс с этим атрибутом, прописать Namespace и полное имя сборки (относительно папки с Flexberry Designer) в которой определён указанный тип.

Только после этого производится генерация такого класса.

![](/images/pages/products/flexberry-orm/data-object/data-object-as-attribute-type.GIF)

Использование атрибута класса с объектом данных в качестве типа атрибута не рекомендуется использовать в общем случае, если есть возможность использовать [мастера](fd_master-association.html) или [детейлы](fo_detail-associations-properties.html).

Основной особенностью данного решения является то, что между классами в таком случае нет связи (в данном случае между классами `Зоопарк` и `ДиректорЗоопарка`). Соответственно, поле `Директор` класса `Зоопарк` будет содержать не ссылку на `ДиректорЗоопарка`, а хранить [сериализованный объект](fo_aggregating-function.html) `ДиректорЗоопарка`.

Применение атрибута класса с [объектом данных](fo_data-object.html) в качестве типа может быть полезным при сохранении специализированных настроек, когда возвращается объектная модель настроек, а не просто строка.
