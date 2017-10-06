---
title: Стандартные сервисы данных Flexberry ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных
summary: Иерархия наследования и описание сервисов данных
toc: true
permalink: ru/fo_standard-data-services.html
lang: ru
---

## Иерархия наследования сервисов данных

![](/images/pages/products/flexberry-orm/data-service/i-data-service-inheritance.png)

## Описание стандартных сервисов данных

Существуют следующие [сервисы данных](fo_data-service.html):

* [ICSSoft.STORMNET.Business.XMLFileDataService](fo_xml-file-ds.html) для доступа к данным в формате `XML`-файлов.
* [ICSSoft.STORMNET.Business.SQLDataService](fo_sql-data-service.html) — базовый предок всех сервисов данных, работающих с реляционными источниками.
* [ICSSoft.STORMNET.Business.MSSQLDataService](fo_mssql-data-service.html) — сервис данных для доступа к `MS SQL Server` напрямую, минуя `ODBC`. Требует наличия `SQL Client`, однако скорость работы возрастает приблизительно на 25%.
* [ICSSoft.STORMNET.Business.DRDataService](fo_dr-data-service.html) — сервис данных для `MS SQL Server`, реализующий "грязное чтение" данных (`WITH (NOLOCK)`).
* [ICSSoft.STORMNET.Business.OracleDataService](fo_oracle-data-service.html) — сервис данных для доступа к `Oracle` напрямую, минуя `ODBC`. Требует наличия [клиентского ПО Oracle](fo_tools-oracle-ds.html).
* [ICSSoft.STORMNET.Business.PostgresDataService](fo_postgres-data-service.html)
* [ICSSoft.STORMNET.Business.ODBCDataService.ODBCDataService](fo_odbc-data-service.html) для доступа к реляционным источникам данных. Независим от реляционной СУБД, важно использование стандартов `ANSI` и `ODBC`.
 [ICSSoft.STORMNET.Business.ODBCDataService.ODBCAccessDataService](fo_odbc-access-ds.html) — [ODBCDataService](fo_odbc-data-service.html), оптимизированный для доступа к данным `СУБД Access`. **Использование БД Access возможно только в х86-версии!**
* [ICSSoft.STORMNET.Business.ODBCDataService.ODBCMySqlDataService](fo_odbc-mysql-data-service.html) — [ODBCDataService](fo_odbc-data-service.html), оптимизированный для доступа к данным `СУБД MySQL` (версия 4.1 и более поздние, с некоторыми ограничениями на объектную структуру).
