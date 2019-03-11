--- 
title: Imposing restrictions on the LookUp in the Web 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Restrictions 
toc: true 
permalink: en/fa_lookup-limit-web.html 
lang: en 
autotranslated: true 
hash: 04768470055c851384a6bede1855f0d85f9eb0043b7ff05faff98727e5941a8c 
--- 

By default, when opening forms in the [LookUp](fa_lookup-overview.html) displays the entire list of objects from which to select. However, often the situation arises when a complete list is impossible. 

Therefore, the question arises: how to limit the output if you picked up on LookUp? 

## Example 1 

Given the following diagram. 

![](/images/pages/products/flexberry-aspnet/controls/lookup/filter-ex-diagram.png) 

When you create a new object of type `Кредит` need to choose `Клиента` to which the loan. For example, that a restriction to lend only to persons resident in the city of Perm (i.e. if `Прописка` Client contains a "Perm"). 

To build a restriction using [LINQProvider](fo_linq-provider.html): 

```csharp
var ds = (SQLDataService) DataServiceProvider.DataService;
IQueryable<Клиент> limit = ds.Query<Клиент>(Клиент.Views.КлиентL).Where(klient => klient.Прописка.Contains("Perm"));
``` 

Then get a restraining function using the class `LinqToLcs`: 

```csharp
Function onlyPermKlients = LinqToLcs.GetLcs(limit.Expression, Клиент.Views.КлиентL).LimitFunction;
``` 

Restrict LookUp field by selecting `LimitFunction`: 

```csharp
ctrlКлиент.LimitFunction = onlyPermKlients;
``` 

As a result, when you open lookup-shape Clients this page displays only Clients that have the word "Perm" in the field "Registration". 

## Example 2 

The more difficult task: let the residence, is in a class thus, it is necessary to impose restrictions on master: 

![](/images/pages/products/flexberry-aspnet/controls/lookup/kredit-diagramm.png) 

Additionally, you want to show only working `КредитныхИнспекторов`. 

1.Build constraints by filling a LINQ provider: 

```csharp
var ds = (SQLDataService) DataServiceProvider.DataService;
IQueryable<Клиент> limit1 = ds.Query<Клиент>(Клиент.Views.КлиентL).Where(klient => klient.Прописка.Город == "Perm");
IQueryable<КредитныйИнспектор> limit2 = ds.Query<КредитныйИнспектор>(КредитныйИнспектор.Views.КредитныйИнспекторL).Where(insp => insp.Работает);
``` 

2.To restrictive feature: 

```csharp
Function onlyPermKlients = LinqToLcs.GetLcs(limit1.Expression, Клиент.Views.КлиентL).LimitFunction;
Function onlyWorkingInspektors = LinqToLcs.GetLcs(limit2.Expression, КредитныйИнспектор.Views.КредитныйИнспекторL).LimitFunction;
``` 

3.The limitations on LookUpы for Customers and Inspectors: 

```csharp
ctrlКлиент.LimitFunction = onlyPermKlients;
ctrlКредитныйИнспектор.LimitFunction = onlyWorkingInspektors;
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}