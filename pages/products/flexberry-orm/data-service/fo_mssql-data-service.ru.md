---
title: MSSQLDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, mssql, сервис данных
summary: Cервис данных для работы с MS SQL Server
toc: true
permalink: ru/fo_mssql-data-service.html
lang: ru
---

`MSSQLDataService` - это [сервис данных](fo_data-service.html) для работы с MS SQL Server напрямую, минуя ODBC, является реализацией [абстрактного класса SQLDataService](fo_sql-data-service.html).

При указании MSSQLDataService в качестве сервиса данных используется строка `ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService`.

## Особенности использования

* MSSQLDataService требует наличия `SQL Client`, однако скорость работы возрастает приблизительно на 25%.
* MSSQLDataService [особым образом обрабатывает значения NULL и string.Empty](fo_updating-objects-empty-rows.html).

*См. также статью* [Обработка регистров в именах объектов для СУБД](fo_processing-registers-names.html).
