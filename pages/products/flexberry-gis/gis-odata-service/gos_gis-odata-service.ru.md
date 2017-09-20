---
title: gis-odata-service
keywords: ember
sidebar: flexberry-gis_sidebar
toc: false
permalink: ru/gos_gis-odata-service.html
folder: products/flexberry-gis/
lang: ru
---

## Особенности настройки OData-сервиса для ГИС-приложений
Необходимо изменить файл `Web.config`, отредактировав соответствующую строку таким образом для MSSQL сервер:
```xml
<appSettings>
...
  <add key="DataServiceType" value="NewPlatform.Flexberry.ORM.GisMSSQLDataService, NewPlatform.Flexberry.ORM.GisMSSQLDataService" />
...
</appSettings>
```
или для PostgreSQL сервер:
```xml
<appSettings>
...
  <add key="DataServiceType" value="NewPlatform.Flexberry.ORM.GisPostgresDataService, NewPlatform.Flexberry.ORM.GisPostgresDataService" />
...
</appSettings>
```

## Использование в запросе к ODataService
Для фильтрации объектов данных классов Map и LayerMatedata по условию пересечения их поля "boundingBox" с заданным полигоном (типа Geography на уровне БД под MS SQL , или аналогичного типа Geometry на уровне БД под PostgreSQL + PostGIS),
реализована пользовательская функция geo.intersects(geography1=geo1, geography2=geo2)  в OData, где "geo1" и "geo2" могут быть как полями объектов данных типа Edm.Geography, так и географически привязанными геометриями заданными в формате EWKT, например так: geo.intersects(geography1=boundingBox, geography2=geography'SRID=<Код используемой системы координат>;POLYGON(<Координаты полигона>)'), где geo.intersects -  пользовательская функция geo.intersects OData, geography - метод для преобразования строки с координатами в формате EWKT в тип Edm.Geography.

