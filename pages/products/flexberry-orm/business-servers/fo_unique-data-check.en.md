--- 
title: Check uniqueness of data entered in the business server 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, business servers, an example of limitations 
summary: the Use of business servers and restrictions to verify the uniqueness of objects 
toc: true 
permalink: en/fo_unique-data-check.html 
lang: en 
autotranslated: true 
hash: fc2e3b3f2982e7ea6d8219f5255c85982f6584690c0db452b8757568d9a6aebb 
--- 

If there is a need to verify the uniqueness of data entered, it is necessary in the method `OnUpdate` [business server](fo_bs-wrapper.html) to generate read data and compare the data from the database with the entered data. 

Be aware that the object can be either just created or already be in the database. If the object is already in the database, he will get the list reads the data and there is a conflict. 

## Example 

![](/images/pages/products/flexberry-orm/additional-features/templates.PNG) 

You want to check the uniqueness of the entered credit card number. 

* Add [business server](fo_bs-wrapper.html) `КредитнаяКартаБС` and put the object `КредитнаяКарта` link to the business server. 
* The method [OnUpdate](fo_bs-example.html) should [subtract](fo_sql-query.html) all Creditcart client and check if there are any cards with that number. 

Code using [LinqProvider](fo_linq-provider.html): 

```csharp
var ds = (SQLDataService)DataServiceProvider.DataService;
var кредитныеКарты = ds.Query<КредитнаяКарта>(КредитнаяКарта.Views.КредитнаяКартаE)
                     .Count(k => k.Клиент.__PrimaryKey == UpdatedObject.Клиент.__PrimaryKey 
                              && k.Номер == UpdatedObject.Номер);
``` 

Thus, in the variable `кредитныеКарты` is the amount of the desired credit cards. If these cards are already there, this number will be unequal to zero. However, if `Карта` not created and updated, it will return unit, as our database is information about the updated credit card. 

We should Refine the condition by adding a comparison with the current map, using `LinqProvider`: 

```csharp
var ds = (SQLDataService)DataServiceProvider.DataService;
var кредитныеКарты = ds.Query<КредитнаяКарта>(КредитнаяКарта.Views.КредитнаяКартаE)
                     .Count(k => k.Клиент.__PrimaryKey == UpdatedObject.Клиент.__PrimaryKey 
                              && k.Номер == UpdatedObject.Номер
                              && k.__primaryKey != UpdatedObject.__PrimaryKey);
``` 

Thus, it verifies the uniqueness of the numbers generated `Кредитной карты`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/