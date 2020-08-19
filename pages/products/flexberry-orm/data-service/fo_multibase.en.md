---
title: Support for multiple datastores in one application
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data services
summary: Features of the application work with different databases
toc: true
permalink: en/fo_multibase.html
lang: en
autotranslated: true
hash: 8bdc88c4b4a36a41f72aeab42f81f388f9e055a4088e526d6bb912ddcd9e9b7c
---

## Substitution line data service

[SQLDataService](fo_sql-data-service.html) has a special delegate

```csharp
    /// <summary> 
    /// The delegate to change the connection string (the organization works with multiple databases) 
    /// </summary> 
    /// <param name="types">an Array of types, which is derived from objects coming to a data service</param> 
    /// <returns>the New connection string, if you return an empty value or null string is not changed</returns> 
    public delegate string ChangeCustomizationStringDelegate(System.Type[] types);

    /// <summary> 
    /// The delegate to change the connection string 
    /// </summary> 
    public static ChangeCustomizationStringDelegate ChangeCustomizationString = null;
```

which allows you to change the connection string for the passed type. Instance of the data service is not changing, only the connection string.
Additionally there is a special property [SQLDataService](fo_sql-data-service.html), allowing to cancel the action this delegate (this allows you to have several data services that work exclusively with their database without reconfiguring):

```csharp
/// <summary> 
/// Not to change the connection string of the shared delegate ChangeCustomizationString 
/// </summary> 
public bool DoNotChangeCustomizationString
{
  get { return _doNotChangeCustomizationString; }
  set { _doNotChangeCustomizationString = value; }
}
```

## Substitution StorageName

[Information](fo_methods-class-information.html) contains the delegate

```csharp
/// <summary> 
/// Delegate for changing ClassStorageName (you can substitute non-correctable.dbo.table_name, for example) 
/// </summary> 
/// <param name="classType">class Type</param> 
/// <param name="originalStorageName">Original StorageName</param> 
/// <returns>new StorageName (if empty or null, then we take the original)</returns> 
public delegate string ChangeClassStorageNameDelegate(Type classType, string originalStorageName);

/// <summary> 
/// Delegate for changing ClassStorageName (you can substitute non-correctable.dbo.table_name, for example) 
/// </summary> 
public static ChangeClassStorageNameDelegate ChangeClassStorageName = null;
```

which allows you to substitute in the dynamics [table name and schema that stores the class](fo_storing-data-objects.html).
In order to implement support for multiple schemas is required to issue a line like this: **dbo.Tiplady**.
This delegate is called once for each type, since the returned value is cached. If in addition the schema name to specify a table name, then you need to make sure that one transaction will not be appeals to different databases because this will result in an exception when executing the entire operation.

{% include warning.html content="Need to add quotes inside the return value, because the ID is in fact breaking the standard logic of the appeal to the table. Quotes are always being added to package IDs (for MSSQL)." %}

```sql
имя_базы.[dbo].название_класса
```

Quotes escaped in `web.config` if necessary, a standard XML tools.

{% include note.html content="it Should be borne in mind that [business server](fo_business-server.html) trying to upgrade all the received objects in a single transaction. It is therefore necessary to avoid a situation where [actual storage refreshable](fo_storing-data-objects.html) are in different databases. Otherwise, the business server will attempt to perform a closing transaction at the source than at which a transaction is opened, which will lead to an error." %}



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}