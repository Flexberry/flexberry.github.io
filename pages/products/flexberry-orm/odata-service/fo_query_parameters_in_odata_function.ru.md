---
title: Получение параметров запроса в OData функции
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM ODataService, DataService, OData, OData-function
summary: Получение параметров запроса в OData функции
toc: true
permalink: ru/fo_query_parameters_in_odata_function.html
lang: ru
---

### Получение параметров запроса в OData функции

#### Регистрация функции

Для регистрации функции с определенными параметрами запроса необходимо выполнить следующие шаги.

```csharp
using Function = NewPlatform.Flexberry.ORM.ODataService.Functions.Function;

// Создаем словарь для хранения типов параметров.
var parameterTypes = new Dictionary<string, Type>
{
    { "id", typeof(int) },
    { "name", typeof(string) },
};

// Регистрируем функцию с заданными параметрами.
token.Functions.Register(new Function("SearchData", SearchData, typeof(List<CustomType>), parameterTypes));
```

В конструктор Function передается название функции, сама функция, тип возвращаемого значения и параметры запроса.

#### Создание функции для обработки запроса

Определить функцию, которая будет обрабатывать запрос и получать параметры.

```csharp
public static object SearchData(QueryParameters queryParameters, IDictionary<string, object> parameters)
{
    var lcs = queryParameters.CreateLcs(typeof(CustomType));
    ...
    return list;
}
```
