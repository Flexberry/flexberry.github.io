---
title: LINQProvider
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary:
toc: true
permalink: ru/fo_linq-provider.html
lang: ru
---

## Поддержка Linq

Класс `LinqToLcs` пространства имен `ICSSoft.STORMNET.Business.LINQProvider` предназначен для поддержки [LINQ](http://ru.wikipedia.org/wiki/LINQ) запросов к сервисам данных [`SQLDataService`](fo_sql-data-service.html).

Основные возможности LINQProvider описаны в статье [Возможности LinqProvider](fo_linq-provider-faetures.html).

## Как использовать LinqProvider

Для работы LINQ-провайдера необходима ссылка на System.Linq

``` csharp
 using System.Linq;
```

**А также проектная ссылка на `ICSSoft.STORMNET.Business.MSSQLDataService.DLL`.**

При подключении пространства имен `ICSSoft.STORMNET.Business.LINQProvider` объектам [SQLDataService](fo_sql-data-service.html) добавляется метод Query со следующими прототипами:

``` csharp
public static IQueryable<T> Query<T>(this SQLDataService ds, string viewName) where T : DataObject

public static IQueryable<T> Query<T>(this SQLDataService ds, View view, IEnumerable<View> resolvingViews = null) where T : DataObject

public static IQueryable<T> Query<T>(this SQLDataService ds) where T : DataObject
```

Параметры:

| Имя | Описание |
|:----|:----|
| `ds` | [Сервис данных, наследник SQLDataService](fo_sql-data-service.html), для выполнения запроса|
| `view` | [Представление](fd_view-definition.html), используемое для загрузки объектов|
| `resolvingViews` | [Представление](fd_view-definition.html) мастеров, содержащие их детейлы, используемые в запросе (если таких нет, то `null`)|

В последнем варианте перегрузки [представление](fd_view-definition.html) для загрузки объектов будет формироваться динамически (в него попадут свойства, использующиеся в запросе).

При этом, если представление задается статически, предпочтительнее использовать второй вариант перегрузки, указав представление в виде `Тип.Views.ИмяТипа`.

Метод возвращает `IQueryable`, которому можно передать запрос с помощью методов-расширений LINQ:
* [Where](http://msdn.microsoft.com/en-us/library/system.linq.queryable.where.aspx)
* [First](http://msdn.microsoft.com/en-us/library/system.linq.queryable.first.aspx)
* [Any](http://msdn.microsoft.com/en-us/library/system.linq.queryable.any.aspx)
* [All](http://msdn.microsoft.com/en-us/library/bb534754.aspx)
* [Count](http://msdn.microsoft.com/en-us/library/bb534754.aspx)
* [FirstOrDefault](http://msdn.microsoft.com/ru-ru/library/system.linq.queryable.firstordefault.aspx)

{% include note.html content="Вычитка происходит каждый раз при вызове ToList или ToArray, так что желательно сначала получить коллекцию данных, а потом работать с ней." %}

## Примеры использования

### Получение первого подходящего объекта

``` csharp
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.Business.LINQProvider;
//...
var ds = (SQLDataService)DataServiceProvider.DataService; // Cервис данных.
Кошка cat = ds.Query<Кошка>(Кошка.Views.КошкаE).First(o => o.Кличка.Contains("ош")); // Получение объекта.
Console.WriteLine(cat.Кличка); //Использование.
```

### Получение первого подходящего объекта (с генерацией `TOP 1` в тексте запроса при применении `FirstOrDefault` и `First`)

``` csharp
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.Business.LINQProvider;
//...
var ds = (SQLDataService)DataServiceProvider.DataService; // Cервис данных.
Кошка cat = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.Кличка.Contains("ош")).Take(1).FirstOrDefault(); // Получение объекта.
Console.WriteLine(cat.Кличка); //Использование.
```

### Получение коллекции объектов

``` csharp
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.Business.LINQProvider;
//...
var ds = (SQLDataService)DataServiceProvider.DataService; // Сервис данных.
IQueryable<Кошка> objs = ds.Query<Кошка>(Кошка.Views.КошкаE); 
IQueryable<Кошка> query = from o in objs where o.PrimaryKey == "6211E0DE-3E7A-4A68-866A-AB206A005B1C" select o; // Получить кошек по заданному значению ключа.
List<Кошка> data = query.ToList(); // Вычитать данные в коллекцию.
Console.WriteLine(data[0).Кличка); // Пользуемся полученными данными.
```

#### Следующий код эквивалентен предыдущему

``` csharp
var ds = (SQLDataService)DataServiceProvider.DataService; // Сервис данных.
IQueryable<Кошка> objs = ds.Query<Кошка>(Кошка.Views.КошкаE).Where(o => o.PrimaryKey == "6211E0DE-3E7A-4A68-866A-AB206A005B1C"); // Получить кошек по заданному значению ключа.
List<Кошка> data = objs.ToList(); // Вычитать данные в коллекцию.
Console.WriteLine(data[0).Кличка); // Пользуемся полученными данными.
```
