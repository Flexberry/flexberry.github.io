---
title: CASE Insensitive для БД
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, For code validate, Public, БД
toc: true
permalink: ru/fo_case-insensitive-for-db.html
---

## Нечувствительность к регистру в сервисе данных

Flexberry ORM позволяет настроить приложение таким образом, чтобы в генерируемом сервисом данных запросе применялась функция `UPPER` для обеих сторон выражения сравнения строк (то есть при сравнении строк не будет учитываться, в каком регистре записаны символы).

Для того, чтобы это настроить, необходимо в конфигурационном файле указать следующий параметр:

```xml
<add key="CaseInsensitive" value="true">
```
