---
title: Перехват формирования SQL-запроса к БД
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, база данных, сервис данных, пример
summary: Примеры перехватов формирования SQL-запросов
toc: true
permalink: ru/fo_intercept-formation-sql-query.html
lang: ru
---

[SQLDataService](fo_sql-data-service.html) позволяет осуществлять перехват формирования SQL-запроса к БД за счёт применения [делегатов OnGenerateSQLSelect, AfterGenerateSQLSelectQuery, OnCreateCommand](fo_sql-data-service.html).

`Примеры перехватов формирования SQL-запросов`:

``` csharp
static void Main()
{
    //...
    // создаем датасервис
    IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
    IDataService ds = mainUnityContainer.Resolve<IDataService>();
    ICSSoft.STORMNET.Business.SQLDataService sqldataservice = (ICSSoft.STORMNET.Business.SQLDataService)ds; 
    // перед формированием 
    sqldataservice.OnGenerateSQLSelect+=new ICSSoft.STORMNET.Business.OnGenerateSQLSelectEventHandler(ds_OnGenerateSQLSelect);  
    // после формирования
    sqldataservice.AfterGenerateSQLSelectQuery+=new ICSSoft.STORMNET.Business.AfterGenerateSQLSelectQueryEventHandler(ds_AfterGenerateSQLSelectQuery); 
    // строка посылается на выполение ...
    sqldataservice.OnCreateCommand+=new ICSSoft.STORMNET.Business.OnCreateCommandEventHandler(ds_OnCreateCommand);
    //...
} 
public static void ds_OnGenerateSQLSelect(object sender, ICSSoft.STORMNET.Business.GenerateSQLSelectQueryEventArgs e)
{
    //...
    if (e.CustomizationStruct.View == null) return;
    //для Словарей ограничим общеупотребительными значениями...
    if(e.CustomizationStruct.View.Name == "СловарьL")
    {
        e.CustomizationStruct.LimitFunction = AddОбщУпотр(e.CustomizationStruct.LimitFunction);
    }
    //...
}

public static void ds_AfterGenerateSQLSelectQuery(object sender, ICSSoft.STORMNET.Business.GenerateSQLSelectQueryEventArgs e)
{
    //...
    //для View ПеремИАрхив...
    if (e.CustomizationStruct.View.Name == "ПеремИАрхив")
    {
        e.GeneratedQuery = System.Text.RegularExpressions.Regex.Replace(e.GeneratedQuery.ToUpper(),
        "^SELECT","SELECT TOP 100"); 
    }
    //...
}

private void ds_OnCreateCommand(object sender, CreateCommandEventArgs e)
{
    //...
    //Для строки удаления Проживаний
    if (e.Command.CommandText.StartsWith("DELETE FROM \"Проживание\""))
    {
        //...
    }
    //...
}
```
