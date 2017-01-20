---
title: Реализация собственного сервис данных
sidebar: product--sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/implement-a-custom--data-service.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll, ICSSoft.STORMNET.Business.DRDataService.dll, ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.ODBCDataService, ICSSoft.STORMNET.Business.OracleDataService, ICSSoft.STORMNET.Business.PostgresDataService
* '''Предназначение''': Описание доступных способов реализации собственных [сервисов данных](data-service.html).
</td>
</tr></tbody></table></a>
</div>

# Реализация собственного сервис данных
В общем случае, [сервис данных](data-service.html) должен реализовывать интерфейс `ICSSoft.STORMNET.Business.IDataService`.

## Реализация произвольного сервиса данных
Если необходимо реализовать собственный [сервис данных](data-service.html), следует учитывать следующее:
# Необходимо учитывать атрибуты хранения. Установку этих атрибутов можно проверить методами `[Information-obtainingmetadata|Information]: GetAssemblyStorageName, GetClassStorageName, GetPropertyStorageName`.
# Классы, [атрибуты, мастера, детейлы могут быть нехранимыми](not-stored--attributes.html), что указывается `NotStoredAttribute`. Метод `[Information-obtainingmetadata|Information].GetStorablePropertyNames` возвращает список хранимых атрибутов.
# Сначала всё вычитывается в соответствии с порядком, указанным атрибутом `[LoadingOrderAttribute](order-of-loading-property--data-object.html)`, затем, всё остальное. Метод `[Information-obtainingmetadata|Information].GetLoadingOrder` возвращает порядок.
# В атрибутах, мастерах могут не допускаться пустые значения, что указывается атрибутом `NotNullAttribute`.

С целью оптимизации, нужно обновлять только изменённые атрибуты (`[Information-obtainingmetadata|Information].GetAlteredProperyNames`).

## Реализация сервиса данных для реляционного хранилища
Если требуется реализовать [сервис данных](data-service.html) для реляционного хранилища, рекомендуется унаследоваться от [готовых сервисов данных `Flexberry Platform`](standard-data-services.html), в частности, от общего предка реляционных [сервис данных](data-service.html) `[SQLDataService](s-q-l-data-service.html)`, либо `[ODBCDataService](o-d-b-c-data-service.html)`. Затем, согласно особенностей хранилища, которое Вы решили использовать, необходимо соответствующим образом перегрузить методы.

Ниже приводится пример [сервиса данных](data-service.html), выполняющего работу с `Microsoft SQL Server` напрямую, через `SQLClient`:
```
public class MSSQLDataService:ICSSoft.STORMNET.Business.SQLDataService
{
    public override System.Data.IDbConnection GetConnection()
    {
        return new System.Data.SqlClient.SqlConnection(this.CustomizationString);
    }

    public override string GetIfNullExpression(params string[] identifiers)
    {
        string result = identifiers[identifiers.Length-1];
        for (int i= identifiers.Length-2;i>=0;i--)
        result = string.Concat("ISNULL(",identifiers[i],", ",result,")");
        return result;
    } 
}
```

В этом примере переопределены всего два метода: один теперь выполняет подсоединение к источнику напрямую, другой учитывает специфику указания в запросы функции `ISNULL`.
