---
title: Поддержка полей byte[] & varbinary(MAX) в сервисе данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fo_byte-varbinary--m-a-x-in-data-service.html
---

Временами в классах требуется поддержка атрибутов - бинарных массивов.
Это может быть поле для хранения сериализованных объектов, картинок и т.д.
Раньше приходилось использовать строковые поля, однако это не есть хорошо - в MS SQL есть специальный тип данных для хранения больших объектов - varbinary(MAX);

Для использования **varbinary &amp; byte&#91;&#93;** требуется:

1. Выделяем через typedef новый тип данных на диаграммах:

![](/images/pages/products/flexberry-orm/byte-varbinary-MAX-in-data-service/byteArray.png)
2. В карте .NET типов отмечаем byte&#91;&#93;

![](/images/pages/products/flexberry-orm/byte-varbinary-MAX-in-data-service/TypesNet.png)

3. В карте SQL типов выставляем varbinary(MAX)

![](/images/pages/products/flexberry-orm/byte-varbinary-MAX-in-data-service/TypesSQL.png)

Готово!

