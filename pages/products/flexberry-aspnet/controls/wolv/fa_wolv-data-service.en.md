--- 
title: Service data WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-data-service.html 
lang: en 
autotranslated: true 
hash: 1840cbaa7db8aeba28f4a4eddfd22162512c7995fd14d17198935f05e2153446 
--- 

## data Service WOLV 

[WOLV](fa_web-object-list-view.html) there is a public property `DataService` through which it is possible to used the data service and/or install your. 

This was used public property: 

```csharp
public IDataService DataService { get; set; }
``` 

Having used the service data, you can add your [handlers of some events](fo_sql-data-service.html), for example: 

```csharp
(webObjectListView.DataService as SQLDataService).AfterUpdateObjects += delegate(object sender, DataObjectsEventArgs args)
{
    // ... 
}
``` 

{% include note.html content="Without a cast to a specific type `DataService`, events bind will fail." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}