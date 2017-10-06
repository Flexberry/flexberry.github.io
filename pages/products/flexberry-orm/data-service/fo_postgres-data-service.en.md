---
title: Data service for working with PostgreSQL
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data service, postgres
summary: Особенности работы c PostgreSQL
toc: true
permalink: en/fo_postgres-data-service.html
lang: en
---

`PostgresDataService`- это [сервис данных](fo_data-service.html) для работы с PostgreSQL, является реализацией [абстрактного класса SQLDataService](fo_sql-data-service.html).

При указании PostgresDataService в качестве сервиса данных используется строка `ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService`.

## Особенности работы с PostgreSQL

Для обеспечения регистронезависимости Postgres необходимо подключить [следующий модуль](http://www.postgresql.org/docs/current/static/citext.html)
Это необходимо для корректной работы ограничений.
