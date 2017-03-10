---
title: DRDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_d-r-data-service.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Сервис данных](fo_data-service.html)
* **Программная библиотека**: ICSSoft.STORMNET.Business.DRDataService
* **Предназначение**: Сервис данных для MS SQL Server, реализующий "грязное чтение" данных `(WITH (NOLOCK))`.

<<<<<<< HEAD
<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](fo_flexberry-orm.html)
* '''Компонент''': [Сервис данных](fo_data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.DRDataService
* '''Предназначение''': Сервис данных для MS SQL Server, реализующий "грязное чтение" данных `(WITH (NOLOCK))`.
</td>
</tr></tbody></table></a>
</div>

# DRDataService
`DRDataService` - [сервис данных](fo_data-service.html), наследник [SQLDataService](fo_sql-data-service.html), обладает тем же функционалом, что и обычный `[MSSQLDataService](fo_mssql-data-service.html)`, но при чтении данных используется "[грязное чтение (dirty read)](http://msdn.microsoft.com/ru-ru/library/ms173763.aspx)".
=======
## DRDataService
`DRDataService` - [сервис данных](fo_data-service.html), наследник [SQLDataService](fo_s-q-l-data-service.html), обладает тем же функционалом, что и обычный [MSSQLDataService](fo_m-s-s-q-l-data-service.html), но при чтении данных используется "[грязное чтение (dirty read)](http://msdn.microsoft.com/ru-ru/library/ms173763.aspx)".

## Получение экземпляра DRDataService
Экземпляр DRDataService можно получить через [UnityFactory](fo_unity-factory.html), выполнив следующий код:
>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f

```cs
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

Строка соединения в данном примере настраивается так, как указано в статье про `[DataServiceProvider]`(fo_data-service-provider-data-service.html)
