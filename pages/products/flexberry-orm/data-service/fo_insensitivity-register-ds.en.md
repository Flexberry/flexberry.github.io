---
title: Insensitivity to the register in the data service
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, database, data service
summary: Method for setting case sensitivity
toc: true
permalink: en/fo_insensitivity-register-ds.html
lang: en
---

[Flexberry ORM](fo_flexberry-orm.html) позволяет настроить приложение таким образом, чтобы в генерируемом [сервисом данных](fo_data-service.html) запросе применялась функция [UPPER](https://docs.microsoft.com/ru-ru/sql/t-sql/functions/upper-transact-sql) для обеих сторон выражения сравнения строк (то есть при сравнении строк не будет учитываться, в каком регистре записаны символы).

Для того чтобы это настроить, необходимо в конфигурационном файле указать следующий параметр:

```xml
<add key="CaseInsensitive" value="true">
```
