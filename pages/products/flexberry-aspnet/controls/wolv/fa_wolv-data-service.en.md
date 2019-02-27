--- 
title: Service data WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-data-service.html 
lang: en 
autotranslated: true 
hash: f95a49ba51774c7710c273c5bf6bdfb0474aa8ce3c3a67fbd6ce629379e315dc 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/