--- 
title: layer Type tile 
keywords: ember 
sidebar: ember-flexberry-gis_sidebar 
toc: true 
permalink: en/efg_tile.html 
lang: en 
autotranslated: true 
hash: 2f7cfe34b188124507af79531c2714078b511477d84d1c6244ad51b0fe1e4d96 
published: true 
--- 

## Specification 

The name of the tile layer (from the English. tile - tile) due to the fact that we get these layers of a certain card is drawn from a set of pre-prepared images of a fixed size called tiles, which are placed on a dense grid next to each other (like the tile in the bathroom, only without the gaps). 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tiles-map.png) 

The tiles are prepared in such a way that at maximum zoom the entire map is covered by only one tile which depicts the entire area of the map, but makes is the least detailed, as if "from the height of bird flight." 
At minimum scale, the map covered a large enough number of tiles, each of which is highly detailed and depicts the corresponding section of the map. 
Plenty of available map scales from maximum to minimum uniformly divided into a finite number, each of which is defined in accordance with clochicine designation, this so-called zoom (from the English. zoom level - zoom in/zoom level) that takes a value of 0 to the maximum scale, and minimum for some n, typically n = 18, but may be less so and more. 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tiles-pyramid.png) 

Any tiled layer is requesting the thus prepared image from specialized tile services, telling them the zoom and coordinates of the images corresponding to the viewable map area. 
However, since for any value of the zoom grid consists of a finite number of tiles, namely 2^(zoom*2) (i.e. 1, 4, 16, 64, ...), the geographical coordinates of the viewable area of the map, before accessing the service are converted to integer coordinates tylovay grid for the specified zoom. 
And here by way of "marking" tiles these integer coordinates, and the method of converting geographic coordinates into integer coordinates of the grid tile services are divided into two types: 

* TMS (Tile Map Service) is a kind of tile services that implement [the specification TMS](http://wiki.osgeo.org/wiki/Tile_Map_Service_Specification) developed by the organization [The Open Source Geospatial Foundation](https://www.osgeo.org/); 
* And the so-called Slippy map - a kind of tile services once developed and popularized by the community of Open Street Map, and then taken over by Google maps and other similar services, also contributed to the popularization of this разновидности; 

The second is more popular, but no advantages over using the TMS they give, as well as TMS do not give any advantages over the Slippy map.
As already mentioned the main page is how the tiles "labeled" integers and when obrashenii to the service to convert the geographic coordinates to integer grid coordinates. 

Services TMS coordinate axis of the mesh directed in a traditional way, and the coordinates of the origin tylovay grid (minx, miny): 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tile-map-service-tiles-grid.png) 

In the Slippy map services the Y-axis inverted, and the coordinates of the origin tylovay grid (minx, maxy): 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/slippy-map-tiles-grid.png) 

For a General understanding of the difference that is enough of the formula of reduction of coordinates we will not consider here. 

In both variants of services of each tile uniquely identificireba URL ohms, which value will be the request for obtaining the image. 
In the General case, this URL has the form `http://{s}.somedomain.com/{z}/{x}/{y}.png` where `{s}` is the name of one of the top-level domain, this parameter is optional and if present allows the client to evenly load all the queries provided by the service capacity, `{z}`, `{x}`, `{y}` accordingly, the zoom and coordinates of the requested image in tylovay the grid, and in the end set the desired format of the resulting image, and if the service supports several formats, you can specify any of them. 

## Ember-layer component and its properties 

Ember-component which implements the work with silovymi layers is on the way [`ember-flexberry-gis/addon/components/layers/tile-layer`](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/components/layers/tile-layer.js) and supports the following set of properties that match the same name with them to the settings in the object `settings` in the model layer: 

The name of the property |Type of property |Description 
:--------------------------|:-----------------|:----------------- 
`url`| `String` | Template URL for making requests for the tiles 

## Examples of usage 

Example of adding a map tile layer Open Street Map (OSM): 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tile-osm-example.png) 

Example of adding a map tile layer Google maps: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tile-google-map-example.png) 

Example of adding a map tile layer Google maps with satellite imagery: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tile-google-satellite-example.png) 

Example of adding a map tile layer Google hybrid maps combining satellite imagery with symbols of roads and other objects with the usual hand-drawn maps: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_tile/tile-google-hybrid-example.png) 

Please note that when you add any tiled layers, always specify the coordinate system for OSM is EPSG:3857, for Google EPSG:4326 (although there are other supported coordinate systems), other services can use some other coordinate system. 
If, when you add a tile layer, specify the coordinate system that is not supported by the service, then the tiles will be shifted, or even reversed. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}