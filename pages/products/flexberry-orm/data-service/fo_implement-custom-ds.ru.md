---
title: Реализация произвольного сервиса данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных
summary: Особенности реализации сервиса данных
toc: true
permalink: ru/fo_implement-custom-ds.html
lang: ru
---

В общем случае [сервис данных](fo_data-service.html) должен реализовывать интерфейс ICSSoft.STORMNET.Business.IDataService.

## Реализация произвольного сервиса данных

Если необходимо реализовать собственный [сервис данных](fo_data-service.html), следует учитывать следующее:

1. Необходимо учитывать атрибуты хранения. Установку этих атрибутов можно проверить методами [Information](fo_methods-class-information.html): GetAssemblyStorageName, GetClassStorageName, GetPropertyStorageName.
2. Классы, [атрибуты, мастера, детейлы могут быть нехранимыми](fo_not-stored-attributes.html), что указывается NotStoredAttribute. Метод [Information](fo_methods-class-information.html).GetStorablePropertyNames возвращает список хранимых атрибутов.
3. Сначала всё вычитывается в соответствии с порядком, указанным атрибутом [LoadingOrderAttribute](fo_order-loading-property.html), затем, всё остальное. Метод [Information](fo_methods-class-information.html).GetLoadingOrder возвращает порядок.
4. В атрибутах, мастерах могут не допускаться пустые значения, что указывается атрибутом NotNullAttribute.

С целью оптимизации, нужно обновлять только изменённые атрибуты [Information](fo_methods-class-information.html).GetAlteredProperyNames.

## Реализация сервиса данных для реляционного хранилища

Если требуется реализовать [сервис данных](fo_data-service.html) для реляционного хранилища, рекомендуется унаследоваться от [готовых сервисов данных Flexberry Platform](fo_standard-data-services.html), в частности, от общего предка реляционных сервис данных [SQLDataService](fo_sql-data-service.html), либо [ODBCDataService](fo_odbc-data-service.html). Затем, согласно особенностей хранилища, которое планируется использовать, необходимо соответствующим образом перегрузить методы.

Ниже приводится пример [сервиса данных](fo_data-service.html), выполняющего работу с Microsoft SQL Server напрямую, через SQLClient:

``` csharp
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

В этом примере переопределены всего два метода: один теперь выполняет подсоединение к источнику напрямую, другой учитывает специфику указания в запросы функции ISNULL.
