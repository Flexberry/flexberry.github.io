---
title: gis-odata-service
keywords: ember
sidebar: flexberry-gis_sidebar
toc: false
permalink: ru/gos_gis-odata-service.html
folder: products/flexberry-gis/
lang: ru
---

### Особенности настройки OData-сервиса для ГИС-приложений

##### Перед использованием необходимо скачать проекты с github:
[https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService) и 
[https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService) или загрузить сборки с nuget.org [NewPlatform.Flexberry.ORM.GisPostgresDataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.GisPostgresDataService) и [NewPlatform.Flexberry.ORM.GisMSSQLDataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.GisMSSQLDataService). 

##### После успешной компиляции подключить созданные сборки к проекту. Требуется также версия ODataService не ниже 2.0.1-alpha01. Её можно загрузить с nuget.org [NewPlatform.Flexberry.ORM.ODataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService/2.0.1-alpha01). 

##### Необходимо изменить файл `Web.config`, отредактировав соответствующую строку таким образом для MSSQL сервер:
```xml
<appSettings>
...
  <add key="DataServiceType" 
  value="NewPlatform.Flexberry.ORM.GisMSSQLDataService, NewPlatform.Flexberry.ORM.GisMSSQLDataService" />
...
</appSettings>
```
или для PostgreSQL сервер:
```xml
<appSettings>
...
  <add key="DataServiceType" 
  value="NewPlatform.Flexberry.ORM.GisPostgresDataService, NewPlatform.Flexberry.ORM.GisPostgresDataService" />
...
</appSettings>
```
### Типы данных
##### MSSQL сервер
Поле в таблице для MSSQL сервера должна иметь тип `Geography`
##### PostgreSQL сервер
Поле в таблице для PostgreSQL сервера должна иметь тип `Geography`
##### .Net
Тип свойства в классе наследуемом от  `DataObject` должен быть `Microsoft.Spatial.Geography`

### Использование
##### В запросе к ODataService
Фильтрация объектов данных в опции запроса $filter возможна с использованием пользовательской функции `geo.intersects(geography1=geo1, geography2=geo2)` в OData, где "geo1" и "geo2" могут быть как полями объектов данных типа Edm.Geography, так и географически привязанными геометриями заданными в формате EWKT, например так: `geo.intersects(geography1=boundingBox, geography2=geography'SRID=<Код используемой системы координат>;POLYGON(<Координаты полигона>)')`, где `geography` - метод для преобразования строки с координатами в формате EWKT в тип Edm.Geography.

Пример запроса: 
`http://localhost/odata/КлассСМножествомТиповs?$filter=PropertyInt eq 5 and geo.intersects(geography1=PropertyGeography, geography2=geography'SRID=4326;POINT(3 3)')`.

##### В запросе к LINQProvider
```c#
    Geography geo = "POINT(3 3)".CreateGeography();
    ViewAttribute gisView = new ViewAttribute("gisView", new string[] {
    "Name as \'Наименование\'",
    "Description as \'Описание\'",
    "KeyWords as \'Ключевые слова\'",
    "Lat as \'Широта\'",
    "Lng as \'Долгота\'",
    "Zoom as \'Зум\'",
    "Public as \'Общая\'",
    "Scale as \'Масштаб\'",
    "CoordinateReferenceSystem as \'Система координат\'",
    "BoundingBox as \'Граница\'"});
    list = ds.Query<Map>(new View(gisView, typeof(Map))).Where(d => d.BoundingBox.GeoIntersects(geo)).ToList();
```
##### В запросе с использованием LCS
```c#
    var ldef = ExternalLangDef.LanguageDef;
    var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Map), new View(gisView, typeof (Map)));
    lcs.LimitFunction =
        ldef.GetFunction(ldef.funcGeoIntersects, new VariableDef(ldef.GeographyType, "BoundingBox"), geo);
    list = ds.LoadObjects(lcs);
```
