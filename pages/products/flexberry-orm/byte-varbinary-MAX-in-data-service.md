---
title: Поддержка полей byte[] & varbinary(MAX) в сервисе данных
sidebar: product--sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/byte-varbinary--m-a-x-in-data-service.html
folder: product--folder
lang: ru
---

Временами в классах требуется поддержка атрибутов - бинарных массивов.
Это может быть поле для хранения сериализованных объектов, картинок и т.д.
Раньше приходилось использовать строковые поля, однако это не есть хорошо - в MS SQL есть специальный тип данных для хранения больших объектов - varbinary(MAX);

Для использования '''varbinary &amp; byte&#91;&#93;''' требуется:
1. Выделяем через typedef новый тип данных на диаграммах:
![](/images/pages/img/page/byte-varbinary-MAX-in-data-service/byteArray.png)
2. В карте .NET типов отмечаем byte&#91;&#93;
![](/images/pages/img/page/byte-varbinary-MAX-in-data-service/TypesNet.png)
3. В карте SQL типов выставляем varbinary(MAX)
![](/images/pages/img/page/byte-varbinary-MAX-in-data-service/TypesSQL.png)

Готово!

