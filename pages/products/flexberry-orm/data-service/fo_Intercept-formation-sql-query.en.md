--- 
title: Intercept form a SQL-query to the database 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, database, data service example 
summary: Examples of interceptions of forming SQL queries 
toc: true 
permalink: en/fo_intercept-formation-sql-query.html 
lang: en 
autotranslated: true 
hash: db0dfc5683862d2d6477dfbead0ecf98880f05b01673168debcd367d01b13a86 
--- 

[SQLDataService](fo_sql-data-service.html) enables the interception of the formation of a SQL query to the database through the use of [delegates OnGenerateSQLSelect, AfterGenerateSQLSelectQuery, OnCreateCommand](fo_sql-data-service.html). 

`Примеры interceptions formation SQL запросов`: 

``` csharp
static void Main()
{
    //... 
    // create a DataService 
    ICSSoft.STORMNET.Business.SQLDataService ds = (ICSSoft.STORMNET.Business.SQLDataService)ICSSoft.STORMNET.Business.DataServiceProvider.DataService; 
    // before generating 
    ds.OnGenerateSQLSelect+=new ICSSoft.STORMNET.Business.OnGenerateSQLSelectEventHandler(ds_OnGenerateSQLSelect);  
    // after the formation of the 
    ds.AfterGenerateSQLSelectQuery+=new ICSSoft.STORMNET.Business.AfterGenerateSQLSelectQueryEventHandler(ds_AfterGenerateSQLSelectQuery); 
    // string is sent to the implementation ... 
    ds.OnCreateCommand+=new ICSSoft.STORMNET.Business.OnCreateCommandEventHandler(ds_OnCreateCommand);
    //... 
} 
public static void ds_OnGenerateSQLSelect(object sender, ICSSoft.STORMNET.Business.GenerateSQLSelectQueryEventArgs e)
{
    //... 
    if (e.CustomizationStruct.View == null) return;
    //for Dictionaries restrict common values... 
    if(e.CustomizationStruct.View.Name == "СловарьL")
    {
        e.CustomizationStruct.LimitFunction = AddОбщУпотр(e.CustomizationStruct.LimitFunction);
    }
    //... 
}

public static void ds_AfterGenerateSQLSelectQuery(object sender, ICSSoft.STORMNET.Business.GenerateSQLSelectQueryEventArgs e)
{
    //... 
    //to View Premierhip... 
    if (e.CustomizationStruct.View.Name == "Premierhip")
    {
        e.GeneratedQuery = System.Text.RegularExpressions.Regex.Replace(e.GeneratedQuery.ToUpper(),
        "^SELECT","SELECT TOP 100"); 
    }
    //... 
}

private void ds_OnCreateCommand(object sender, CreateCommandEventArgs e)
{
    //... 
    //For the row deletion Is 
    if (e.Command.CommandText.StartsWith("DELETE FROM \"Bed\""))
    {
        //... 
    }
    //... 
}
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/