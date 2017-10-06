---
title: Using Postgres as a repository
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data service, postgres
summary: Example of using Postgres DBMS
toc: true
permalink: en/fo_switching-storages.html
lang: en
---

Для реализации поставленной задачи необходим установленный [Postgres](http://www.postgresql.org/).

* Далее следует создать базу данных, выполнив скрипт

```sql
FlexberryORM\Database\POSTGRES\create.sql
```

* Затем в `app.config` найти секцию `appSettings`.
* Закомментировать опции `DataServiceType` и `CustomizationStrings` и затем переименовать опции `DataServiceType_POSTGRE` и `CustomizationStrings_POSTGRE` в `DataServiceType` и `CustomizationStrings`.
* Изменить опцию `CustomizationStrings` для корректного подключения к серверу и базе данных.
* Запустить пример, чтобы убедиться, что он работает: проверить создание и загрузку объектов данных.
