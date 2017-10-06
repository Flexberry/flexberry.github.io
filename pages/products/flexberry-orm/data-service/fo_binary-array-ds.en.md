---
title: Support for binary arrays with data service
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data service, data types
summary: Configuring binary arrays
toc: true
permalink: en/fo_binary-array-ds.html
lang: en
---

Иногда возникает задача поддержки атрибутов типа бинарный массив. Это может быть поле для хранения [сериализованных объектов](fo_aggregating-function.html), картинок и т.д.

Для поддержки бинарных массивов требуется:

* Выделить через [typedef](fd_typedef.html) новый тип данных на [диаграммах](fd_class-diagram.html):

![](/images/pages/products/flexberry-orm/data-service/byte-array.png)

* В [карте .NET типов](fd_types-map.html) отметить byte[]

![](/images/pages/products/flexberry-orm/data-service/types-net.png)

* В [карте SQL типов](fd_types-map.html) выставить varbinary(MAX)

![](/images/pages/products/flexberry-orm/data-service/types-sql.png)
