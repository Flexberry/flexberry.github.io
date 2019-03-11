--- 
title: SQLDataService 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: Opportunities, methods, demands, activities, and events 
toc: true 
permalink: en/fo_sql-data-service.html 
lang: en 
autotranslated: true 
hash: 8bbea0f68de7b0ad390d3c7beba961b4f2944a0cc01b81640707977539ce235e 
--- 

[Service data](fo_data-service.html) working with relational storage. 

Is an abstract class, inherited from it 

* [`MSSQLDataService`](fo_mssql-data-service.html), 
* [`ODBCDataService`](fo_odbc-data-service.html), 
* [`OracleDataService`](fo_oracle-data-service.html), 
* [`PostgresDataService`](fo_postgres-data-service.html). 

## Main features SQLDataService 

Because `SQLDataService` implements the interface `ICSSoft.STORMNET.Business.IDataService`, it supports all the methods defined in this interface](fo_data-service.html). 
It should be noted that some of the methods only declared in the class `SQLDataService`, and their implementation must be implemented in descendant classes. 

### Additional ways to load data 

#### LoadRawValues 

__Assign__: Download without create objects when you need to get DISTINCT data. 
Standard methods zachetki get [PrimaryKey] in(fo_primary-keys-objects.html) to allow the correct creation of data objects. Accordingly, DISTINCT [PrimaryKey] in(fo_primary-keys-objects.html) in the query has no effect (keys are unique, so no grouping the results will not happen - they are all different). This method returns a regular two-dimensional array (as it does `ADO.NET`). 

__Settings__: 

`customizationStruct` - Structure [LoadingCustomizationStruct](fo_loading-customization-struct.html) defining what and how to ship. Must be specified `Distinct`. 

__Signature__: 

```csharp
    virtual public object[][] LoadRawValues(LoadingCustomizationStruct customizationStruct) 
``` 

__Example__: 

```csharp
SQLDataService ds = (SQLDataService)DataServiceProvider.DataService;
View v = new View();
v.DefineClassType = typeof (Door);
v.AddProperty("Street.Name");
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Door), v);
lcs.Distinct = false; //Get a two-dimensional array of properties without DISTINCT in the upper SELECT-e 
object[][] loadDistinctValues = ds.LoadRawValues(lcs);
string s = loadDistinctValues.Length.ToString();

lcs.Distinct = true; //Get a two-dimensional array with DISTINCT properties at the top SELECT-e 
object[][] loadDistinctValues1 = ds.LoadRawValues(lcs);
string s1 = loadDistinctValues1.Length.ToString();
``` 

#### SecondLoadObject 

__Assign__: Method for decide data object. 
Previously loaded properties are not overwritten, the modified properties are not overwritten. Replaced the piece the properties of the clone data. It is recommended to read the description in the article [Dochitcu data object](fo_additional-loading.html). 

__Settings__: 

* `dataObjectView` - view 
* `dataObject` - bject data that you want to download 
* `checkExistingObject` is to check whether the object exists in the repository 
* `dataObjectCache` - cache 

__Signature__: 

```csharp
protected virtual void SecondLoadObject(View dataObjectView, DataObject dataObject, bool checkExistingObject, DataObjectCache dataObjectCache) 
``` 

## Additional ways to update data 

### UpdateObjectsOrdered 

__Assign__: to Update the data objects in the specified order. 

`SQLDataService` knows himself to build the order query to update the data objects. This is especially true when there are a large number of diverse objects in a single transaction. Unfortunately, it is not always possible to automatically compute the correct order of requests. First and foremost, this applies to situations when the graph types, there are cycles. To solve this problem, we propose to use the method that performs updating of objects sequentially in the order in which they come in this method. 

__Settings__: 

* `objects` - refreshable 
* `alwaysThrowException` - If an error occurred in the database, don't try to fulfill other requests, just cocked an error and roll back the transaction. 

__Signature__: 

```csharp
virtual public void UpdateObjectsOrdered(ref DataObject[] objects, bool alwaysThrowException = true)
``` 

__Example__: 

```csharp
protected void UpdateButtonClick(object sender, EventArgs e)
{
    SQLDataService ds = (SQLDataService)DataServiceProvider.DataService;
    var ko = ds.Query<КритерийОценки>(КритерийОценки.Views.КритерийОценкиE).First(o => o.Описание.StartsWith("kirlim"));
    ko.Описание = "kirlim-birlim";
DataObject[] dObjs = new DataObject[] { ko };
    ds.UpdateObjectsOrdered(ref dObjs);
}
``` 

### operations within the specified connection and transaction 

#### LoadObjectByExtConn 

__Assign__: Loading the object with the specified connectie within the specified transaction 

__Settings__: 

* `dataObjectView` - Representation that will be counted towards the object. 
* `dobject` - the Object that will be zachityvalis/counted. 
* `сlearDataObject` - whether zachitka to clear the fields of an existing data object. 
* `сheckExistingObject` - to Verify the existence encountered sacide objects. 
* `dataObjectCache` - the object Cache. 
* `connection` - Connecte through which will occur zachitka. 
* `transaction` Transaction, which will be held zachitka. 

__Signature__: 

```csharp
public virtual void LoadObjectByExtConn(
    View dataObjectView,
    DataObject dobject, 
    bool сlearDataObject, 
    bool сheckExistingObject, 
    DataObjectCache dataObjectCache, 
    IDbConnection connection, 
    IDbTransaction transaction) 
``` 

#### LoadObjectsByExtConn 

__Assign__: Loading objects using the specified connection and transaction 

__Settings__: 

* `customizationStruct` - Structure that defines what and how to ship. 
* `state` - a State of proof-reading(for the subsequent decide if you use [batch reading](fo_reading-portion.html), the serving size specified in `customizationStruct.LoadingBufferSize`) 
* `dataObjectCache` - object Cache for zazitky. 
* `connection` - Connecte through which will be fulfilled zachitka. 
* `transaction` Transaction, which will be performed zachitka. 

__Signature__: 

```csharp
public virtual DataObject[] LoadObjectsByExtConn(
    LoadingCustomizationStruct customizationStruct,
    ref object state, 
    DataObjectCache dataObjectCache,
    IDbConnection connection,
    IDbTransaction transaction)
``` 

#### ReadFirstByExtConn 

__Appointment___: Receive the first portion when [batch reading](fo_reading-portion.html) using the specified connection and transaction. In addition to portions of data objects, the data service returns a status read `state`. This condition is transmitted to the data service to retrieve the next portion (see next method). 
Analogue to the previous method, but instead of adjusting the structure of the sample is determined by the text of the query.

__Settings__: 

* `Query` - Text query to fetch data 
* `state` - a State of proof-reading(for the subsequent decide) 
* `LoadingBufferSize` - size portions 
* `Connection` - Connecte through which will be fulfilled zachitka 
* `Transaction` Transaction, which will be performed zachitka 

__Signature__: 

```csharp
public virtual object[][] ReadFirstByExtConn(string Query, ref object State, int LoadingBufferSize, System.Data.IDbConnection Connection, System.Data.IDbTransaction Transaction)
``` 

#### ReadNextByExtConn 

__Appointment___: Receiving another batch [batch reading](fo_reading-portion.html). Must be preceded by a call to one of the above two methods of obtaining status `state`. 

__Settings__: 

* `state` - a State of proof-reading(for the subsequent decide) 
* `LoadingBufferSize` - size portions 

__Signature__: 

```csharp
public virtual object[][] ReadNextByExtConn(ref object State, int LoadingBufferSize)
``` 

#### UpdateObjectsByExtConn 

__Assign__: to Update the store objects using the specified connection and transaction. 

{% include note.html content="If the option `alwaysThrowException`=`true`, always cocked an error. Otherwise, execution continues. However, while there is a risk of premature end of the transaction, with the transition to the other mode queries in autocommit transaction. A manifestation of the problem are errors like: The COMMIT TRANSACTION request has no corresponding BEGIN TRANSACTION." %} 

__Settings__: 

* `objects` Objects to update. 
* `dataObjectCache` - the object Cache. 
* `alwaysThrowException` - If an error occurred in the database, don't try to fulfill other requests, just cocked an error and roll back the transaction. 
* `connection` - Konekcija (remember to close). 
* `transaction` Transaction (don't forget to complete). 

__Signature__: 

```csharp
public virtual void UpdateObjectsByExtConn(ref DataObject[] objects, DataObjectCache dataObjectCache, bool alwaysThrowException, IDbConnection connection, IDbTransaction transaction)
``` 

## Generation of the texts of SQL queries 

### GenerateQueriesForUpdateObjects 

__Assign__: Generating requests to modify objects 

__Settings__: 

* `deleteQueries` Queries for delete (output parameter) 
* `deleteTables` - Table that will be conducted the deletion of data (output parameter) 
* `updateQueries` - Generated requests for change (output parameter). 
* `updateTables` - Tables, which will be held modifying the database (output parameter). 
* `insertQueries` - Generated requests to add (output parameter). 
* `insertTables` - Table that will be inserting data (output parameter). 
* `tableOperations` Operation that will be performed on the tables (output parameter). 
* `queryOrder` - the Order of execution of the generated queries specified by a table name (output parameter). 
* `checkLoadedProps` is to Check whether the workload properties. 
* `processingObjects` - Current processed objects (i.e., objects that the service plans to confirm data in the database in the current transaction). An output parameter. 
* `dataObjectCache` - Cache data objects.
* `auditObjects` is a List of objects that you want to record in the audit (the output parameter). Is filled in case when passed is not null and current audit service is enabled. 
* `dobjects` - the objects for which the generated queries. 

__Signature__: 

```csharp
public virtual void GenerateQueriesForUpdateObjects(
    StringCollection deleteQueries,
    StringCollection deleteTables,
    StringCollection updateQueries,
    StringCollection updateTables,
    StringCollection insertQueries,
    StringCollection insertTables,
    SortedList tableOperations,
    StringCollection queryOrder,
    bool checkLoadedProps,
    System.Collections.ArrayList processingObjects,
    DataObjectCache dataObjectCache,
    params ICSSoft.STORMNET.DataObject[] dobjects)
``` 

In this overload returns a list of additional objects that require the creation of audit records: 

```csharp
public virtual void GenerateQueriesForUpdateObjects(
    StringCollection deleteQueries,
    StringCollection deleteTables,
    StringCollection updateQueries,
    StringCollection updateTables,
    StringCollection insertQueries,
    StringCollection insertTables,
    SortedList tableOperations,
    StringCollection queryOrder,
    bool checkLoadedProps,
    ArrayList processingObjects, 
    DataObjectCache dataObjectCache,
    List<DataObject> auditObjects,
    params ICSSoft.STORMNET.DataObject[] dobjects)
``` 

### GenerateSQLSelect 

__Assign__: Receive a request to read data 

__Settings__: 

* `customizationStruct` - customize sample 
* `StorageStruct` - returns the corresponding structure of the sample 

__The result is___: request 

__Signature__: 

```csharp
// 1. 
public virtual string GenerateSQLSelect(LoadingCustomizationStruct customizationStruct, bool ForReadValues, out STORMDO.Business.StorageStructForView[] StorageStruct, bool Optimized)

// 2. 
public virtual string GenerateSQLSelect(LoadingCustomizationStruct customizationStruct, bool Optimized)
``` 

### GetLeftJoinExpression 

__Assign__: to LeftJoin expression 

__Settings__: 

* `subTable` the name of the table 
* `subTableAlias` - the table alias 
* `parentAliasWithKey` 
* `subTableKey` 
* `subJoins` 
* `baseOutline` 

__Signature__: 

```csharp
public virtual void GetLeftJoinExpression(string subTable, string subTableAlias, string parentAliasWithKey, string subTableKey, string subJoins, string baseOutline, out string FromPart, out string WherePart)
``` 

### GetInnerJoinExpression 

__Assign__: to InnerJoin expression 

__Settings__: 

* `subTable` the name of the table 
* `subTableAlias` - the table alias 
* `parentAliasWithKey` 
* `subTableKey` 
* `subJoins` 
* `baseOutline` 
* `FromPart` 
* `WherePart` 

__Signature__: 

```csharp
public virtual void GetInnerJoinExpression(string subTable, string subTableAlias, string parentAliasWithKey, string subTableKey, string subJoins, string baseOutline, out string FromPart, out string WherePart)
``` 

### GetJoinTableModifierExpression 

__Assign__: Return a modifier to access a table (e.g. WITH (NOLOCK)) 

You can overload this method in the data service-the successor to return its corresponding modifier. 
Basic `SQLDataService` returns an empty string.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}