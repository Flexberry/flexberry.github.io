---
title: Поддержка DateOnly и TimeOnly в Flexberry ORM
keywords: Programming
sidebar: flexberry-orm_sidebar
toc: true
permalink: ru/fo_date-only-time-only.html
lang: ru
---

## Обзор

Типы `DateOnly` и `TimeOnly` появились в .NET 6.0. Flexberry ORM поддерживает эти типы, при этом при компиляции для более ранних версий.NET используется `DateTime` в качестве замены.

## Конвертация типов

### Information.SetPropValueByName

Метод `SetPropValueByName` автоматически выполняет конвертацию между временными типами:

| Из | В | Поведение |
|---|---|---|
| `DateTime` | `DateOnly` | Используется только дата, время отбрасывается |
| `TimeSpan` | `TimeOnly` | Преобразование через `TimeOnly.FromTimeSpan` |
| `string` | `DateOnly/TimeOnly` | Парсинг через `Parse/TryParse` |

Пример:
```csharp
var obj = new MyEntity();

// DateTime конвертируется в DateOnly (только дата)
Information.SetPropValueByName(obj, "BirthDate", new DateTime(2026, 5, 20, 14, 30, 0));
// результат: BirthDate = new DateOnly(2026, 5, 20)

// TimeSpan конвертируется в TimeOnly (только время)
Information.SetPropValueByName(obj, "StartTime", new TimeSpan(14, 30, 45));
// результат: StartTime = new TimeOnly(14, 30, 45)
```

### Information.ParsePropertyValue

Метод `ParsePropertyValue` преобразует строковые представления в типы `DateOnly` и `TimeOnly`:

```csharp
var result = Information.ParsePropertyValue(typeof(MyEntity), "BirthDate", "2026-05-20");
// результат: new DateOnly(2026, 5, 20)

var result = Information.ParsePropertyValue(typeof(MyEntity), "StartTime", "14:30:45");
// результат: new TimeOnly(14, 30, 45)
```

## Функции LCS

Для работы с датами доступны следующие функции:

| Функция | Описание | Возвращаемый тип |
|---------|----------|------------------|
| `funcDayNumber` | Номер дня от 0001-01-01 | Numeric |
| `funcDayOfYear` | День от начала года (1-366) | Numeric |
| `funcSSPart` | Секунды от DateTime | Numeric |

Пример использования:
```csharp
var langDef = ExternalLangDef.LanguageDef;
var varDef = new VariableDef(langDef.DateTimeType, "MyDateField");

// Найти записи, где день от начала года = 150
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(MyEntity), view);
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ,
    langDef.GetFunction(langDef.funcDayOfYear, varDef),
    150);
```

## LINQ Provider

### Поддерживаемые свойства и методы

**DateOnly:**
- `Year`, `Month`, `Day`
- `DayOfWeek`
- `DayNumber` — номер дня от 0001-01-01
- `DayOfYear` — день от начала года

**TimeOnly:**
- `Hour`, `Minute`, `Second`
- `TimeOfDay`

Пример LINQ-запросов:

```csharp
// Равенство
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate == new DateOnly(2026, 5, 20))
    .ToList();

// Сравнение
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate > new DateOnly(2025, 1, 1))
    .ToList();

// Год и месяц
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate.Year == 2026 && x.BirthDate.Month == 5)
    .ToList();

// День недели
var result = ds.Query<MyEntity>()
    .Where(x => x.BirthDate.DayOfWeek == DayOfWeek.Monday)
    .ToList();

// Секунды времени
var result = ds.Query<MyEntity>()
    .Where(x => x.MeetingTime.Second == 30)
    .ToList();
```

### LCS-запросы

LINQ-выражения с `DateOnly` и `TimeOnly` автоматически транслируются в LCS:

```csharp
var langDef = ExternalLangDef.LanguageDef;
var varDef = new VariableDef(langDef.DateTimeType, "BirthDate");

// Год
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ,
    langDef.GetFunction(langDef.funcYearPart, varDef),
    2026);

// День года
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ,
    langDef.GetFunction(langDef.funcDayOfYear, varDef),
    150);

// День от 0001-01-01
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcG,
    langDef.GetFunction(langDef.funcDayNumber, varDef),
    739000);
```

## Работа с базами данных

### PostgreSQL

**SQL-представление:**
```csharp
DateOnly -> cast(value as date)
TimeOnly -> cast(value as time)

DateOnly -> date '2026-05-20'
TimeOnly -> time '14:30:45.123456'
```

**Поддерживаемые функции:**
- `EXTRACT (HOUR FROM ...)`, `EXTRACT (MINUTE FROM ...)`, `EXTRACT (SECOND FROM ...)`
- `EXTRACT (DOY FROM ...)` — день года
- Вычитание дат для номера дня

### MSSQL

**SQL-представление:**
```csharp
DateOnly -> '20260520' (формат yyyyMMdd)
TimeOnly -> '14:30:45.fff' (формат HH:mm:ss.fff)
```

**Поддерживаемые функции:**
- `datepart(hour, ...)`, `datepart(minute, ...)`
- `DATEDIFF(day, '0001-01-01', ...)` — номер дня
- `DATEPART(dy, ...)` — день года

### Oracle

**SQL-представление:**
```csharp
DateOnly -> TO_DATE('2026-05-20', 'YYYY-MM-DD')
TimeOnly -> TO_TIMESTAMP('14:30:45.123', 'HH24:MI:SS.FF3')
```

**Поддерживаемые функции:**
- `TO_CHAR(..., 'SS')` — секунды
- `TO_NUMBER(TO_CHAR(..., 'DDD'))` — день года
- Вычитание дат для номера дня

## Nullable-типы

Поддерживаются как `DateOnly?`, так и `TimeOnly?`:

```csharp
public virtual System.DateOnly? OptionalDate { get; set; }
public virtual System.TimeOnly? OptionalTime { get; set; }
```

Примеры:

```csharp
// Присваивание null
obj.OptionalDate = null;
obj.OptionalTime = null;

// Проверка на null в LINQ
var result = ds.Query<MyEntity>()
    .Where(x => x.OptionalDate != null)
    .ToList();

// LCS с nullable
var varDef = new VariableDef(langDef.DateTimeType, "OptionalDate");
var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(MyEntity), view);
lcs.LimitFunction = langDef.GetFunction(
    langDef.funcEQ, 
    varDef, 
    new DateOnly?(new DateOnly(2026, 5, 20)));
```

## Типы данных в функциональном языке

`DateOnly` и `TimeOnly` отображаются на `DateTimeType` в функциональном языке, что обеспечивает единообразный API для всех временных типов.

## Пример определения объекта

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
