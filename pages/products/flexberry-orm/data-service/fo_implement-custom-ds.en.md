--- 
title: the Implementation of a random data service 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: features of the implementation of the service data 
toc: true 
permalink: en/fo_implement-custom-ds.html 
lang: en 
autotranslated: true 
hash: ad17367b146fb50c53cee21694e4da72732a2448b0586d20b8f0aa3235fa849b 
--- 

In the General case [service data](fo_data-service.html) must implement interface ICSSoft.STORMNET.Business.IDataService. 

## implementation of a random data service 

If you must implement your own [service data](fo_data-service.html), should consider the following: 

1. You must consider the attributes of storage. The installation of these attributes can be checked by the methods of [Information](fo_methods-class-information.html): GetAssemblyStorageName, GetClassStorageName, GetPropertyStorageName. 
2. Classes, [attributes, master detaili can be nejanilini](fo_not-stored-attributes.html), which indicated NotStoredAttribute. The [Information](fo_methods-class-information.html).GetStorablePropertyNames returns the list of stored attributes. 
3. First of all be deducted in accordance with the procedure specified by the attribute [LoadingOrderAttribute](fo_order-loading-property.html), then all the rest. The [Information](fo_methods-class-information.html).GetLoadingOrder returns order. 
4. In attributes, the wizards could not be allowed empty values, which is specified by the attribute NotNullAttribute. 

To optimize, you need to update only the changed attributes [Information](fo_methods-class-information.html).GetAlteredProperyNames. 

## the implementation of the service data for relational storage 

If you want to implement the [service data](fo_data-service.html) for relational storage, it is recommended to unasledovala [ready data services Platform Flexberry](fo_standard-data-services.html), in particular, from a common ancestor relational data service [SQLDataService](fo_sql-data-service.html), or [ODBCDataService](fo_odbc-data-service.html). Then, according to features of the repository that you want to use the appropriate overload methods. 

Below is an example of [data service](fo_data-service.html) working with Microsoft SQL Server directly, using the SQLClient: 

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

In this example, the only overridden two methods, one now performs the connection to the source directly, the other takes into account the specific instructions in the query ISNULL. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}