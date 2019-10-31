---
title: ember-flexberry-gis
keywords: ember
sidebar: ember-flexberry-gis_sidebar
toc: true
permalink: en/efg_landing_page.html
lang: en
autotranslated: true
hash: cfe2f8c942cf039203fbc8a208b03482fe50faa8b15cbc5d4bfe7a1fa585943a
published: true
---

## Product information

[ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis) is [ember-addon](https://ember-cli.com/extending/#developing-addons-and-blueprints), which can optionally be installed in any [ember-flexberry](https://github.com/Flexberry/ember-flexberry) application, adding to it an ability to add custom interactive map, the data having geograficheskoy binding.

An example of an interactive map integrated into the ember-flexbery the application as follows:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/efg_landing_page/map-and-layers-example.png)

The sample application includes several examples of such maps are located at [flexberry.github.io/ember-flexberry-gis](http://flexberry.github.io/ember-flexberry-gis/)

## The product

The main elements that make up the addon ember-flexberry-gis:

* ember-flexberry-data model for an interactive map (or several maps) and models for the layers filling карту;
* ember-flexberry-data serializer to these моделям;
* Base classes of ranting and controllers for list form view/edit interactive карты;
* Form of maps and layers that can optionally be included in the application if there are large number of maps and layers for них;
* ember-components of an interactive map, its layers, and its инструментов;

## Model of the interactive map and its layers

In the form of UML diagrams the data model of the interactive map and its layers as follows:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/efg_landing_page/map-and-layers-diagram.png)

The map is a class with the following fields set:

Field name |field Type |Description
:--------------------------|:-----------------|:-----------------
`Name`| `String` | Name card
`Description`| `String` | Description card
`KeyWords`| `String` | Klevye words separated by commas (use the search form if available)
`AnyText`| `String` | a Calculated field that includes the name of the card, its description and keywords (used by search form when available, full-text search)
`Lat`| `Double` | Latitude of map center
`Lng`| `Double` | Longitude of the center of the map
`Zoom`| `Double` | Value "proximity" cards range from 0 to 18
`Public`| `Bool` | Flag: is the map obselescence
`Scale`| `Int` | Clochicine value gaststube/precision, for example, if the map data is presented in the scale 1:10000, the value of this field is 10000
`CoordinateReferenceSystem`| `String` | Serialized JSON description of the coordinate system of the map (default `{"code":"EPSG:3857"}`)
`BoundingBox`| `Geography` | Optional bounding rectangle map border (used by search form when available, for search by intersection of the specified edges as well as one of the map tools to priblizheniya to its borders). Type `Geography` is only supported by specialized data services, which supports geographic/geometric data types: for MSSQL it [GisMSSQLDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService), and for PostgreSQL it is [GisPostgresDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService), exactly one of these data services should be installed in the server part of the application and is specified in configurationa the application file as the main, if the application you plan to use ember-flexberry-gis

The map layer represents the class which is detalam card, with the following set of fields and with a hierarchical reference to itself:

Field name |field Type |Description
:--------------------------|:-----------------|:-----------------
`Name`| `String` | Name layer
`Description`| `String` | layer Description
`KeyWords`| `String` | Klevye words separated by commas (use the search form if available)
`AnyText`| `String` | a Calculated field that includes the name of the layer, its description and keywords (used by search form when available, full-text search)
`Index`| `Int` | Position of the layer in the hierarchy of map layers (the layers with a smaller index are arranged in a hierarchy of layers closer to the root of the hierarchy "below")
`Visibility`| `Bool` | Flag: shows Wiimote layer on the map (`true` - layer visible on the map, `false` - invisible)
`Type`| `String` | layer Type (the name of one of the types of layers that are implemented either in ember-flexberry-gis or complementary Addons ember-flexberry-gis or applied, for example `group`, `tile`, `wfs`, `wms-wfs`, `wms-signle-tile`, `kml`, `geojson`, `osm`, and .t.n.)
`Settings`| `String` | Serialized JSON description of the layer view `{"opacity":1,"bounds":[[-90,-180],[90,180]],"wgs84bbox":[[],[]],"bbox":[[],[]],"displaySettings":{"dateFormat":"DD.MM.YYYY","featuresPropertiesSettings":{"displayPropertyIsCallback":false,"displayProperty":null,"excludedProperties":[],"localizedProperties":{"ru":{},"en":{}}}},"url":"http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}` here settings `opacity`, `bounds`, `wgs84bbox`, `bbox`, `displaySettings`, are common to most layer types, the rest of the settings vary depending on the layer type, here are sample settings for a layer type `tile`, and the only specific setting yavyaetsya `"url":"http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"`, layers of other types of such settings can be more
`Scale`| `Int` | Clochicine value gaststube/precision, for example, if layer data is presented in the scale 1:10000, the value of this field is 10000
`CoordinateReferenceSystem`| `String` | Serialized JSON description of the coordinate system of the layer (for example `{"code":"EPSG:3857"}`)
`BoundingBox`| `Geography` | Optional bounding rectangle of the boundary layer (used by search form when available, for search by intersection of the specified edges as well as one of the map tools to priblizheniya to the borders of the layer). Type `Geography` is only supported by specialized data services, which supports geographic/geometric data types: for MSSQL it [GisMSSQLDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService), and for PostgreSQL it is [GisPostgresDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService)exactly one of these data services should be installed in the server part of the application and is specified in configurationa the application file as the main, if the application you plan to use ember-flexberry-gis

## Supported map layers

* Auxiliary layers
* [group](efg_group.html)
* Layers represented by georeferenced raster images
* [tile](efg_tile.html)
* [osm](efg_osm.html)
* [yandex-tile](efg_yandex-tile.html)
* [wms](efg_wms.html)
* [wms-single-tile](efg_wms-single-tile.html)
* [esri-tile](efg_esri-tile.html)
* Layers are presented "raw" vector data
* [wfs](efg_wfs.html)
* [esri-dynamic](efg_esri-dynamic.html)
* [geojson](efg_geojson.html)
* [kml](efg_kml.html)
* Combined layers
* [wms, wfs](efg_wms-wfs.html)
* [wms esri](efg_wms-esri.html)
* Layers designed for geocoding and reverse geocorona
* [geocoder-osm-ru](efg_geocoder-osm-ru.html)
* [geocoder-osm overpass](efg_geocoder-osm-overpass.html)
* [geocoder-yandex](efg_geocoder-yandex.html)

## JS API map

When working with a map it is sometimes necessary to control some of its functions from the different modules of the card, and sometimes from external applications in which it is embedded, and card a [JS API](efg_jsapi.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}