--- 
title: Designing data services 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services 
summary: how to create a data service to the application 
toc: true 
permalink: en/fo_construction-ds.html 
lang: en 
autotranslated: true 
hash: 2d735b09e206410d3fa88e9c70423dee694d50815da1db333fbb725472b0f764 
--- 

Create [service data](fo_data-service.html) in different ways: 

1.To construct [service data](fo_data-service.html) 

```csharp
IDataService ds = new ODBCDataService();			
ds.CustomizationString="DSN=LibNetSample";
``` 

2.In WinForms applications you can throw» «[service data](fo_data-service.html) on the form as the control, and then configure it using standard window editing properties in the Visual Studio environment. 

3.[Get it from service provider data](fo_ds-provider.html). 

```csharp
IDataService ds = DataServiceProvider.DataService;
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}