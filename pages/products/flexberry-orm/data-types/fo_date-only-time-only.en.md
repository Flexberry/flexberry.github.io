---
title: DateOnly and TimeOnly support
keywords: Programming
sidebar: flexberry-orm_sidebar
toc: true
permalink: en/fo_date-only-time-only.html
lang: en
---

## Overview

Types `DateOnly` and `TimeOnly` appeared in .NET 6.0. Flexberry ORM supports these types, while for compilation for earlier .NET versions, `DateTime` is used as a replacement.

## Type conversion

### Information.SetPropValueByName

The `SetPropValueByName` method automatically performs conversion between time types:

| From | To | Behavior |
|---|---|---|
| `DateTime` | `DateOnly` | Only date is used, time is discarded |
| `TimeSpan` | `TimeOnly` | Conversion via `TimeOnly.FromTimeSpan` |
| `string` | `DateOnly/TimeOnly` | Parsing via `Parse/TryParse` |

Example:
```csharp
var obj = new MyEntity();

// DateTime converts to DateOnly (date only)
Information.SetPropValueByName(obj, "BirthDate", new DateTime(2026, 5, 20, 14, 30, 0));
// result: BirthDate = new DateOnly(2026, 5, 20)

// TimeSpan converts to TimeOnly (time only)
Information.SetPropValueByName(obj, "StartTime", new TimeSpan(14, 30, 45));
// result: StartTime = new TimeOnly(14, 30, 45)
```

### Information.ParsePropertyValue

The `ParsePropertyValue` method converts string representations to `DateOnly` and `TimeOnly` types:

```csharp
var result = Information.ParsePropertyValue(typeof(MyEntity), "BirthDate", "2026-05-20");
// result: new DateOnly(2026, 5, 20)

var result = Information.ParsePropertyValue(typeof(MyEntity), "StartTime", "14:30:45");
// result: new TimeOnly(14, 30, 45)
```

## LCS functions

The following functions are available for date handling:

| Function | Description | Return type |
|---------|----------|------------------|
| `funcDayNumber` | Day number from 0001-01-01 | Numeric |
| `funcDayOfYear` | Day of year (1-366) | Numeric |
| `funcSSPart` | Seconds from DateTime | Numeric |

Usage example:
```csharp
var langDef = ExternalLangDef.LanguageDef;
var varDef = new VariableDef(langDef.DateTimeType, "MyDateField");

// Find records where day of year = 150
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(MyEntity), view);
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ,
    langDef.GetFunction(langDef.funcDayOfYear, varDef),
    150);
```

## LINQ Provider

### Supported properties and methods

**DateOnly:**
- `Year`, `Month`, `Day`
- `DayOfWeek`
- `DayNumber` — day number from 0001-01-01
- `DayOfYear` — day of year

**TimeOnly:**
- `Hour`, `Minute`, `Second`
- `TimeOfDay`

LINQ query examples:

```csharp
// Equality
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate == new DateOnly(2026, 5, 20))
    .ToList();

// Comparison
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate > new DateOnly(2025, 1, 1))
    .ToList();

// Year and month
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate.Year == 2026 && x.BirthDate.Month == 5)
    .ToList();

// Day of week
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate.DayOfWeek == DayOfWeek.Monday)
    .ToList();

// Seconds of time
var result = ds.Query<MyEntity>()
    .Where(x => x.MeetingTime.Second == 30)
    .ToList();
```

### LCS queries

LINQ expressions with `DateOnly` and `TimeOnly` are automatically translated to LCS:

```csharp
var langDef = ExternalLangDef.LanguageDef;
var varDef = new VariableDef(langDef.DateTimeType, "BirthDate");

// Year
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ,
    langDef.GetFunction(langDef.funcYearPart, varDef),
    2026);

// Day of year
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ,
    langDef.GetFunction(langDef.funcDayOfYear, varDef),
    150);

// Day from 0001-01-01
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcG,
    langDef.GetFunction(langDef.funcDayNumber, varDef),
    739000);
```

## Working with databases

### PostgreSQL

**SQL representation:**
```csharp
DateOnly -> cast(value as date)
TimeOnly -> cast(value as time)

DateOnly -> date '2026-05-20'
TimeOnly -> time '14:30:45.123456'
```

**Supported functions:**
- `EXTRACT (HOUR FROM ...)`, `EXTRACT (MINUTE FROM ...)`, `EXTRACT (SECOND FROM ...)`
- `EXTRACT (DOY FROM ...)` — day of year
- Date subtraction for day number

### MSSQL

**SQL representation:**
```csharp
DateOnly -> '20260520' (format yyyyMMdd)
TimeOnly -> '14:30:45.fff' (format HH:mm:ss.fff)
```

**Supported functions:**
- `datepart(hour, ...)`, `datepart(minute, ...)`
- `DATEDIFF(day, '0001-01-01', ...)` — day number
- `DATEPART(dy, ...)` — day of year

### Oracle

**SQL representation:**
```csharp
DateOnly -> TO_DATE('2026-05-20', 'YYYY-MM-DD')
TimeOnly -> TO_TIMESTAMP('14:30:45.123', 'HH24:MI:SS.FF3')
```

**Supported functions:**
- `TO_CHAR(..., 'SS')` — seconds
- `TO_NUMBER(TO_CHAR(..., 'DDD'))` — day of year
- Date subtraction for day number

## Nullable types

Both `DateOnly?` and `TimeOnly?` are supported:

```csharp
public virtual System.DateOnly? OptionalDate { get; set; }
public virtual System.TimeOnly? OptionalTime { get; set; }
```

Examples:

```csharp
// Assign null
obj.OptionalDate = null;
obj.OptionalTime = null;

// Null check in LINQ
var result = ds.Query<MyEntity>()
    .Where(x => x.OptionalDate != null)
    .ToList();

// LCS with nullable
var varDef = new VariableDef(langDef.DateTimeType, "OptionalDate");
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(MyEntity), view);
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ, 
    varDef, 
    new DateOnly?(new DateOnly(2026, 5, 20)));
```

## Data types in functional language

`DateOnly` and `TimeOnly` are mapped to `DateTimeType` in the functional language, ensuring a unified API for all time types.

## Object definition example

```csharp
public class MyEntity : ICSSoft.STORMNET.DataObject
{
#if NET6_0_OR_GREATER
    private System.DateOnly fBirthDate;
    private System.TimeOnly fMeetingTime;
#else
    private System.DateTime fBirthDate;
    private System.DateTime fMeetingTime;
#endif

#if NET6_0_OR_GREATER
    public virtual System.DateOnly BirthDate
    {
        get => fBirthDate;
        set => fBirthDate = value;
    }

    public virtual System.TimeOnly MeetingTime
    {
        get => fMeetingTime;
        set => fMeetingTime = value;
    }

    public virtual System.DateOnly? OptionalDate { get; set; }
    public virtual System.TimeOnly? OptionalTime { get; set; }
#endif
}
```
