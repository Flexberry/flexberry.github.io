---
title: CASE Insensitive для БД
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, For code validate, Public, БД
toc: true
permalink: ru/fo_c-a-s-e--insensitive-for--d-b.html
---

### CASE Insensitive для БД

В конфигурационном файле нужно указать параметр

```xml
<add key="CaseInsensitive" value="true">
```

для того, чтобы в генерируемом [сервисом данных](data-service.html) запросе применялась функция UPPER для обеих сторон выражения сравнения строк.
