---
title: Cервис данных для работы с PostgreSQL
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных, postgres
summary: Особенности работы c PostgreSQL
toc: true
permalink: ru/fo_postgres-data-service.html
lang: ru
---

`PostgresDataService`- это [сервис данных](fo_data-service.html) для работы с PostgreSQL, является реализацией [абстрактного класса SQLDataService](fo_sql-data-service.html).

При указании PostgresDataService в качестве сервиса данных используется строка `ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService`.

## Особенности работы с PostgreSQL

Для обеспечения регистронезависимости Postgres необходимо подключить [следующий модуль](http://www.postgresql.org/docs/current/static/citext.html)
Это необходимо для корректной работы ограничений.
