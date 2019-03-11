--- 
title: yandex layer Type-tile 
keywords: ember 
sidebar: ember-flexberry-gis_sidebar 
toc: true 
permalink: en/efg_yandex-tile.html 
lang: en 
autotranslated: true 
hash: dc69170782c38793002a2b6f5786dfd36bcd52de68f36d9d314c27e75da6b60e 
published: true 
--- 

## Specification 

Layer `yandex-tile` is designed to display on interactive map tile layers [Google maps](https://yandex.ru/maps), 
however, how the layer is somewhat different from the work [classic tile layers](efg_tile.html). 
The fact that Yandex does not give direct access to talovym services [Google maps](https://yandex.ru/maps), their use is permitted only through the official [API Yandex maps](https://tech.yandex.ru/maps/). 
Layer `yandex-tile` loads [API Yandex maps](https://tech.yandex.ru/maps/), adds inside interactive maps [full instance of Yandex-maps](https://tech.yandex.ru/maps/doc/jsapi/2.1/ref/reference/Map-docpage/) with the specified settings, and then throughout the interactive map just proxy it (displacement map, changing zoom, etc.) on a sub-Yandex-map. 

## Ember-layer component and its properties 

Ember-component layer is located in a special addon [ember-flexberry-gis-yandex](https://github.com/Flexberry/ember-flexberry-gis-yandex) along the way [ember-flexberry-gis-yandex/addon/components/layers/tile-yandex-layer](https://github.com/Flexberry/ember-flexberry-gis-yandex/blob/develop/addon/components/layers/tile-yandex-layer.js) and supports the following set of properties that match the same name with them to the settings in the object `settings` in the model layer: 

The name of the property |Type of property |Description 
:--------------------------|:-----------------|:----------------- 
`type`| `String` | the Type of desired tile layer Yandex-maps, which can take values of 'map' (the usual hand-drawn map), the 'satellite (satellite images)', 'hybrid' (a hybrid map combining hand-drawn roads and other facilities with the backing of satellite imagery), and has a default value of 'map' 
`jsApiUrl`| `String` | URL to download the API, Yandex maps, default is 'https://api-maps.yandex.ru/2.1/' 
`detectLanguageAutomatically`| `Boolean` | Flag: shows whether auto-detect the language of the app to use it as a 'lang' parameter in the URL-e, when you download the API Yandex maps. By default have the value `true`. If you enable it the value `false`, then you will need to explicitly specify one of the supported languages ('ru_RU', 'ru_UA', 'en_US', 'en_RU', 'uk_UA', 'tr_TR') in the attribute `jsApiUrl` in the form of ULR-parameter 'lang': https://api-maps.yandex.ru/2.1/?lang=en_us 

## Examples of usage 

Example of adding to the map tile layer is drawn Yandex-maps: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_yandex-tile/yandex-tile-map-example.png) 

Example of adding a map tile layer Yandex-maps with satellite imagery: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_yandex-tile/yandex-tile-satellite-example.png) 

Example of adding a map tile layer Hybrid Yandex-maps, combining hand-drawn roads and other facilities with the backing of satellite images: 

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_yandex-tile/yandex-tile-hybrid-example.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}