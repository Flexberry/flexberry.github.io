--- 
title: Example neranenah object properties 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM data types 
summary: using calculated properties of objects 
toc: true 
permalink: en/fo_nonstored-calculated-properties.html 
lang: en 
autotranslated: true 
hash: cfa3956cfad830d40d607ad04f5768ff31786d4436aad8a78d57e9d68b9ed039 
--- 

This example shows how to use [calculated properties](fo_not-stored-attributes.html). 

Example of defining a calculated property for the object `Person`: 

```csharp
[ICSSoft.STORMNET.NotStored())
[StrLen(255))
[DataServiceExpression(typeof(SQLDataService), "isnull(@FirstName@,\'\') \' \' isnull(@LastName@,\'\')"))
public virtual string FullName
{
    get
    {
        return string.Format("{0} {1}", fFirstName, fLastName);
    }
    set
    {
    }
}
``` 

In the attribute `DataServiceExpression` defined the expression that will be used [service data](fo_data-service.html) when you run the query from the table. 
Is equivalent to this expression on the C# code is written in the getter of the properties. 

```csharp
IDataService dataService = DataServiceProvider.DataService;
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Person), Person.Views.Person_E);

// Load all the data objects. Nechranice property will be computed using the expressions in the getter. 
ICSSoft.STORMNET.DataObject[) persons = dataService.LoadObjects(lcs);

// Load in the form of string representation, the properties are separated from each other by semicolons. Nechranice property will be computed using the expressions in the attribute DataServiceExpression. 
ObjectStringDataView[) osdvpersons = dataService.LoadStringedObjectView(';', lcs);

Console.WriteLine("OK.");
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/