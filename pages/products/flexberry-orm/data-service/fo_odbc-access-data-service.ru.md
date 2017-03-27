---
title: ODBCAccessDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_odbc-access-data-service.html
folder: products/flexberry-orm/
lang: ru
---

## ODBCAccessDataService

ODBCAccessDataService - это [сервис данных](fo_data-service.html), представляющий реализацию [ODBCDataService](fo_odbc-data-service.html), оптимизированный для доступа к данным СУБД Access.

При указании ODBCAccessDataService в качестве сервиса данных используется строка "`ICSSoft.STORMNET.Business.ODBCAccessDataService, ICSSoft.STORMNET.Business.ODBCDataService`".

## Особенности использования

* В качестве строки подключения ODBCAccessDataService используются строки вида "`Driver={Microsoft Access Driver (*.mdb)};Dbq=D:\Temp\Flexberry.mdb;`" (в данном примере подключение будет производиться к БД "Flexberry.mdb", расположенной по пути "D:\Temp\").
* Из-за версии используемого драйвера, работа с ODBCAccessDataService возможно только в приложениях, скомпилированных под x86.
