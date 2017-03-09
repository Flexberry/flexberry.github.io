---
title: OracleDataService
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_oracle-data-service.html
---

OracleDataService- это [сервис данных](fo_data-service.html) для работы с Oracle Server напрямую, минуя ODBC; является реализацией [абстрактного класса SQLDataService](fo_sql-data-service.html).

При указании OracleDataService в качестве сервиса данных используется строка "`ICSSoft.STORMNET.Business.OracleDataService, ICSSoft.STORMNET.Business.OracleDataService`".

## Особенности использования

* [OracleDataService требует наличия клиентского ПО Oracle.](fo_minimum-required-client-set-for-oracle-data-service.html)

Существуют ограничения на именование каталогов, в которых размещается ПО, использующее ORACLE. Если ПО разместить в каталоге, в наименовании которого присутствуют спецсимволы (точки, скобки), то всё может перестать работать. Для исправления этой ситуации достаточно переименовать каталог.
