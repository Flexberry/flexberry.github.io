--- 
title: gis-odata-service 
keywords: ember 
sidebar: flexberry-gis_sidebar 
toc: false 
permalink: en/gos_gis-odata-service.html 
folder: products/flexberry-gis/ 
lang: en 
autotranslated: true 
hash: cd05c84e9ba2fc7cdb8329b5137f216eb26959733c28da6164a6fff502ff7920 
--- 

### Features configuring OData service for GIS applications 

##### Before use, you must download the project from github: 
[https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService) and 
[https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService) or to load assemblies from nuget.org [NewPlatform.Flexberry.ORM.GisPostgresDataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.GisPostgresDataService) and [NewPlatform.Flexberry.ORM.GisMSSQLDataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.GisMSSQLDataService). 

##### After a successful compilation connect created Assembly to the project. Required also version ODataService not lower 2.0.1-alpha01. It can be downloaded from nuget.org [NewPlatform.Flexberry.ORM.ODataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService/2.0.1-alpha01). 

##### Need to modify the file `Web.config` by editing the appropriate string for MSSQL server: 
```xml
<appSettings>
...
  <add key="DataServiceType" 
  value="NewPlatform.Flexberry.ORM.GisMSSQLDataService, NewPlatform.Flexberry.ORM.GisMSSQLDataService" />
...
</appSettings>
``` 
or for PostgreSQL server: 
```xml
<appSettings>
...
  <add key="DataServiceType" 
  value="NewPlatform.Flexberry.ORM.GisPostgresDataService, NewPlatform.Flexberry.ORM.GisPostgresDataService" />
...
</appSettings>
``` 
### data Types 
##### MSSQL server 
Field in the table to MSSQL server must be of type `Geography` 
##### PostgreSQL server 
A field in a table to PostgreSQL server should be of type `Geography` 
##### .Net 
Type properties the class inherits from `DataObject` should be `Microsoft.Spatial.Geography` 

### Use 
##### In the request to the ODataService 
Filtering data objects in option $filter query using a custom function `geo.intersects(geography1=geo1, geography2=geo2)` in OData, where "geo1" and "geo2" can be fields of data objects of type Edm.Geography and geographically referenced geometry specified in EWKT format, for example: `geo.intersects(geography1=boundingBox, geography2=geography'SRID=<Code of the coordinate system used>;POLYGON(<coordinates of the polygon>)')` where `geography` method to convert a string with coordinates in the format in EWKT type Edm.Geography. 

Example query: 
`http://localhost/odata/Classknotesaction?$filter=PropertyInt eq 5 and geo.intersects(geography1=PropertyGeography, geography2=geography'SRID=4326;POINT(3 3)')`. 

##### In the request to LINQProvider 
```c#
    Geography geo = "POINT(3 3)".CreateGeography();
    ViewAttribute gisView = new ViewAttribute("gisView", new string[] {
    "Name as \'Name\'",
    "Description as \'Description\'",
    "KeyWords as \'Keywords\'",
    "As the Lat \'Latitude\'",
    "Lng as \'Longitude\'",
    "Zoom as \'Zoom\'",
    "Public as \'General\'",
    "Scale as \'Scale\'",
    "CoordinateReferenceSystem as \'coordinate System is\'",
    "BoundingBox as \'the Border\'"});
    list = ds.Query<Map>(new View(gisView, typeof(Map))).Where(d => d.BoundingBox.GeoIntersects(geo)).ToList();
``` 
##### In the query using LCS 
```c#
    var ldef = ExternalLangDef.LanguageDef;
    var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof (Map), new View(gisView, typeof (Map)));
    lcs.LimitFunction =
        ldef.GetFunction(ldef.funcGeoIntersects, new VariableDef(ldef.GeographyType, "BoundingBox"), geo);
    list = ds.LoadObjects(lcs);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}