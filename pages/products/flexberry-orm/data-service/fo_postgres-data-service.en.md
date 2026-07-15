---
title: data Service for PostgreSQL
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data services, postgres
summary: features of c PostgreSQL
toc: true
permalink: en/fo_postgres-data-service.html
lang: en
autotranslated: true
hash: 23c1cc66f10f0d34f889c037087309272ce70bb632331520c7045cceb318b709
---

`PostgresDataService` is [service data](fo_data-service.html) to work with PostgreSQL is an implementation of the [abstract class SQLDataService](fo_sql-data-service.html).

When specifying PostgresDataService as a service data string is used `ICSSoft.STORMNET.Business.PostgresDataService, ICSSoft.STORMNET.Business.PostgresDataService`.

## features of PostgreSQL

To ensure registronacional Postgres you need to connect [next module](http://www.postgresql.org/docs/current/static/citext.html)
It is necessary for the correct operation of the restrictions.

## Updates in PostgresDataService

### Npgsql versions by target framework

| Target Framework | Npgsql Version | Comment |
|------------------|----------------|---------|
| .NET Framework 4.5/4.6.1 | 4.0.17 | Long-term support |
| .NET Standard 2.0 / .NET 6 / .NET 7 | 5.0.18 | Current version |
| .NET 10.0 | 10.0.0 | Latest version |

### DateOnly and TimeOnly Support

Starting from .NET 6, `PostgresDataService` supports `DateOnly` and `TimeOnly` types:

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

### Updated Constructors

Old constructors without `IBusinessServerProvider` have been removed. Now the following parameters are required:

```csharp
public PostgresDataService(
    ISecurityManager securityManager, 
    IAuditService auditService, 
    IBusinessServerProvider businessServerProvider,
    IConverterToQueryValueString converterToQueryValueString, 
    INotifyUpdateObjects notifierUpdateObjects = null)
```

### SQL Functions for DateOnly/TimeOnly

Support for new functions:

| Function | Description | SQL (PostgreSQL) |
|----------|-------------|------------------|
| `funcDayNumber` | Day number from 0001-01-01 | `{0} - date '0001-01-01'` |
| `funcDayOfYear` | Day of year (DOY) | `EXTRACT (DOY FROM {0})` |
| `funcSSPart` | Seconds | `SECOND` |

## CRP Generation Issues

> **Important:** After regenerating CRP, Flexberry Designer versions before .NET 6+ do not support `DateOnly` type in .NET 6+ projects.

After updating the .crp file and running the code generator, the generated PostgreSQL SQL script may contain incorrect data types for tables with `DateOnly` (e.g., `TIMESTAMP(3)` instead of `DATE`).

**Solution:** Manually replace the table creation script with `DateOnly` in the generated .sql file so that the column uses the correct `DATE` type.

```sql
-- Example incorrect SQL (before correction)
CREATE TABLE DateOnlyTable (
    Id SERIAL PRIMARY KEY,
    Attr TIMESTAMP(3)  -- Incorrect type
);

-- Example correct SQL (after correction)
CREATE TABLE DateOnlyTable (
    Id SERIAL PRIMARY KEY,
    Attr DATE  -- Correct type for DateOnly
);
```