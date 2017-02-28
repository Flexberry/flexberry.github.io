---
title: DRDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_d-r-data-service.html
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">

* **Продукт**: [Flexberry ORM](flexberry-o-r-m.html)
* **Компонент**: [Сервис данных](data-service.html)
* **Программная библиотека**: ICSSoft.STORMNET.Business.DRDataService
* **Предназначение**: Сервис данных для MS SQL Server, реализующий "грязное чтение" данных `(WITH (NOLOCK))`.

</td>
</tr></tbody></table></a>
</div>

## DRDataService
`DRDataService` - [сервис данных](data-service.html), наследник [SQLDataService](s-q-l-data-service.html), обладает тем же функционалом, что и обычный `[MSSQLDataService](m-s-s-q-l-data-service.html)`, но при чтении данных используется "[грязное чтение (dirty read)](http://msdn.microsoft.com/ru-ru/library/ms173763.aspx)".

## Получение экземпляра DRDataService
Экземпляр DRDataService можно получить через `[UnityFactory](unity-factory.html)`, выполнив следующий код:

```
 IUnityContainer container = UnityFactory.CreateContainer();
 IDataService dataService = container.Resolve<IDataService>("DRDataService");
 dataService.CustomizationString = DataServiceProvider.DataService.CustomizationString;
```

## Настройка DataService

Настройка `DRDataService` через конфигурационный файл :

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
    <section name="unity" type="Microsoft.Practices.Unity.Configuration.UnityConfigurationSection, Microsoft.Practices.Unity.Configuration"/>
  </configSections>

  <unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
    <container>
     <register name="DRDataService" type="ICSSoft.STORMNET.Business.IDataService, ICSSoft.STORMNET.Business" mapTo="ICSSoft.STORMNET.Business.DRDataService, ICSSoft.STORMNET.Business.DRDataService">
        <constructor/>
      </register>
    </container>
  </unity>
</configuration>
```

Строка соединения в данном примере настраивается так, как указано в статье про `[DataServiceProvider](data-service-provider-data-service.html)`
