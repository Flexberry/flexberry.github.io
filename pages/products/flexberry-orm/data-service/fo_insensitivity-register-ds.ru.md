---
title: Нечувствительность к регистру в сервисе данных
sidebar: flexberry-orm_sidebar
keywords: CaseInsensitive, upper, compare, like, ilike
summary: Способ настройки нечуствительности к регистру
toc: true
permalink: ru/fo_insensitivity-register-ds.html
lang: ru
---

[Flexberry ORM](fo_flexberry-orm.html) позволяет настроить приложение таким образом, чтобы в генерируемом [сервисом данных](fo_data-service.html) запросе применялась функция [UPPER](https://docs.microsoft.com/ru-ru/sql/t-sql/functions/upper-transact-sql) для обеих сторон выражения сравнения строк (то есть при сравнении строк не будет учитываться, в каком регистре записаны символы).

Для того чтобы это настроить, необходимо в конфигурационном файле указать следующий параметр:

```xml
<add key="CaseInsensitive" value="true">
```
