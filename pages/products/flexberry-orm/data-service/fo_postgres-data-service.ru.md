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

## Обновления в PostgresDataService

### Версии Npgsql по целевым фреймворкам

| Целевой фреймворк | Версия Npgsql | Комментарий |
|-------------------|---------------|-------------|
| .NET Framework 4.5/4.6.1 | 4.0.17 | Долгосрочная поддержка |
| .NET Standard 2.0 / .NET 6 / .NET 7 | 5.0.18 | Актуальная версия |
| .NET 10.0 | 10.0.0 | Последняя версия |

### Поддержка DateOnly и TimeOnly

Начиная с .NET 6, `PostgresDataService` поддерживает типы `DateOnly` и `TimeOnly`:

```csharp
#if NET6_0_OR_GREATER
if (valType == typeof(DateOnly))
{
    return "cast(" + value + " as date)";
}

if (valType == typeof(TimeOnly))
{
    return "cast(" + value + " as time)";
}
#endif
```

### Изменённые конструкторы

Удалены устаревшие конструкторы без `IBusinessServerProvider`. Теперь обязательными являются следующие параметры:

```csharp
public PostgresDataService(
    ISecurityManager securityManager, 
    IAuditService auditService, 
    IBusinessServerProvider businessServerProvider,
    IConverterToQueryValueString converterToQueryValueString, 
    INotifyUpdateObjects notifierUpdateObjects = null)
```

### SQL-функции для DateOnly/TimeOnly

Поддержка новых функций:

| Функция | Описание | SQL (PostgreSQL) |
|---------|----------|------------------|
| `funcDayNumber` | Номер дня от 0001-01-01 | `{0} - date '0001-01-01'` |
| `funcDayOfYear` | День года (DOY) | `EXTRACT (DOY FROM {0})` |
| `funcSSPart` | Секунды | `SECOND` |

## Проблемы с генерацией CRP

> **Важно:** После регенерации CRP Flexberry Designer в версиях до .NET 6+ не поддерживает тип `DateOnly` в проектах .NET 6+.

После обновления .crp файла и запуска генератора кода, сгенерированный PostgreSQL SQL-скрипт может содержать некорректные типы данных для таблиц с `DateOnly` (например, `TIMESTAMP(3)` вместо `DATE`).

**Решение:** Вручную заменить скрипт создания таблицы с `DateOnly` в сгенерированном .sql файле, чтобы столбец использовал корректный тип `DATE`.

```sql
-- Пример некорректного SQL (до исправления)
CREATE TABLE DateOnlyTable (
    Id SERIAL PRIMARY KEY,
    Attr TIMESTAMP(3)  -- Некорректный тип
);

-- Пример корректного SQL (после исправления)
CREATE TABLE DateOnlyTable (
    Id SERIAL PRIMARY KEY,
    Attr DATE  -- Корректный тип для DateOnly
);
```