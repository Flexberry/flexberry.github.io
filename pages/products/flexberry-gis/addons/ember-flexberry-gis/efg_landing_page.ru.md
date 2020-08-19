---
title: ember-flexberry-gis
keywords: ember
sidebar: ember-flexberry-gis_sidebar
toc: true
permalink: ru/efg_landing_page.html
lang: ru
published: true
---

## Информация о продукте

[ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis) представляет из себя [ember-аддон](https://ember-cli.com/extending/#developing-addons-and-blueprints), который может опционально устанавливаться в любое [ember-flexberry](https://github.com/Flexberry/ember-flexberry) приложение, добавляя к нему возможность размещать на настраиваемой интерактивной карте, данные, имеющие географичекую привязку.

Пример готовой интерактивной карты, интегрированной в ember-flexbery приложение выглядит следующим образом:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/efg_landing_page/map-and-layers-example.png)

Демонстрационное приложение, включающее в себя несколько примеров таких карт располагается по адресу [flexberry.github.io/ember-flexberry-gis](http://flexberry.github.io/ember-flexberry-gis/)

## Состав продукта

Основные элементы, из которых состоит аддон ember-flexberry-gis:

* ember-flexberry-data модель для интерактивной карты (либо нескольких карт), а также модели для слоев наполняющих карту;
* ember-flexberry-data сериалайзеры к этим моделям;
* Базовые классы роутов и контроллеров для списковой формы и формы просмотра/редактирования интерактивной карты;
* Форма поиска карт и слоев, которая может опционально включаться в приложение при наличии в нем большого числа карт и слоев для них;
* ember-компоненты интерактивной карты, её слоев, а также её инструментов;

## Модель интерактивной карты и её слоев

В виде UML-диаграммы модель данных интерактивной карты и её слоев выглядит следующим образом:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/efg_landing_page/map-and-layers-diagram.png)

Карта представляет из себя класс со следующим набором полей:

Наименование поля          |Тип поля          |Описание          
:--------------------------|:-----------------|:-----------------
`Name`| `String` | Наименование карты 
`Description`| `String` | Описание карты 
`KeyWords`| `String` | Клчевые слова разделенные запятыми (используются формой поиска, при её наличии) 
`AnyText`| `String` | Вычислимое поле, включающее наименование карты её описание и ключевые слова (используются формой поиска, при её наличии, для полнотекстового поиска) 
`Lat`| `Double` | Широта центра карты 
`Lng`| `Double` | Долгота центра карты 
`Zoom`| `Double` | Значение "приближения" карты в диапазоне от 0 до 18 
`Public`| `Bool` | Флаг: является ли карта общедуступной
`Scale`| `Int` | Целочисенное значение мастштаба/точности, например, если данные карты представлены в масштабе 1:10000, то значение этого поля будет 10000
`CoordinateReferenceSystem`| `String` | Сериализованное JSON-описание системы координат карты (по умолчанию `{"code":"EPSG:3857"}`)
`BoundingBox`| `Geography` | Опциональный прямоугольник ограничивающий границы карты (используется формой поиска, при её наличии, для поиска по пересечению с заданными границами, а также одним из инструментов карты для пиближения к её границам). Тип `Geography` поддерживается только специализированными сервисами данных, в которых реализована поддержка географических/геометрических типов данных: для MSSQL это [GisMSSQLDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService), а для PostgreSQL это [GisPostgresDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService), именно один из этих сервисов данных должен быть установлен в серверной части приложения и указан в конфиграционном файле приложения в качестве основного, если в приложении планируется использовать ember-flexberry-gis

Слой карты представляет из себя класс являющийся детейлом карты, со следующим набором полей и с иерархической ссылкой на самого себя:

Наименование поля          |Тип поля          |Описание          
:--------------------------|:-----------------|:-----------------
`Name`| `String` | Наименование слоя 
`Description`| `String` | Описание слоя 
`KeyWords`| `String` | Клчевые слова разделенные запятыми (используются формой поиска, при её наличии) 
`AnyText`| `String` | Вычислимое поле, включающее наименование слоя, его описание и ключевые слова (используются формой поиска, при её наличии, для полнотекстового поиска) 
`Index`| `Int` | Позиция слоя в иерархии слоев карты (слои с меньшим индексом располагаются в иерархии слоев ближе к корню иерархии и "ниже") 
`Visibility`| `Bool` | Флаг: показывает виимость слоя на карте (`true` - слой видим на карте, `false` - невидим) 
`Type`| `String` | Тип слоя (наименования одного из типов слоев, реализованных либо в ember-flexberry-gis, либо в аддонах дополняющих ember-flexberry-gis, либо с прикладной стороны, например `group`, `tile`, `wfs`, `wms-wfs`, `wms-signle-tile`, `kml`, `geojson`, `osm`,  и .т.п.)
`Settings`| `String` | Сериализованное JSON-описание слоя вида `{"opacity":1,"bounds":[[-90,-180],[90,180]],"wgs84bbox":[[],[]],"bbox":[[],[]],"displaySettings":{"dateFormat":"DD.MM.YYYY","featuresPropertiesSettings":{"displayPropertyIsCallback":false,"displayProperty":null,"excludedProperties":[],"localizedProperties":{"ru":{},"en":{}}}},"url":"http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}`, здесь настройки `opacity`, `bounds`, `wgs84bbox`, `bbox`, `displaySettings`, являются общими для большинства типов слоев, остальные же настройки варьируются в зависимости от используемого типа слоя, здесь приведен пример настроек для слоя типа `tile`, и единственной специфичной для него настройкой явяется `"url":"http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"`, у слоев других типов таких настроек может быть больше
`Scale`| `Int` | Целочисенное значение мастштаба/точности, например, если данные слоя представлены в масштабе 1:10000, то значение этого поля будет 10000
`CoordinateReferenceSystem`| `String` | Сериализованное JSON-описание системы координат слоя (например `{"code":"EPSG:3857"}`)
`BoundingBox`| `Geography` | Опциональный прямоугольник ограничивающий границы слоя (используется формой поиска, при её наличии, для поиска по пересечению с заданными границами, а также одним из инструментов карты для пиближения к границам слоя). Тип `Geography` поддерживается только специализированными сервисами данных, в которых реализована поддержка географических/геометрических типов данных: для MSSQL это [GisMSSQLDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisMSSQLDataService), а для PostgreSQL это [GisPostgresDataService](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.GisPostgresDataService), именно один из этих сервисов данных должен быть установлен в серверной части приложения и указан в конфиграционном файле приложения в качестве основного, если в приложении планируется использовать ember-flexberry-gis

## Поддерживаемые типы слоев карты

* Вспомогательные слои
  * [group](efg_group.html)
* Слои представленные географически привязанными растровыми изображениями
  * [tile](efg_tile.html)
  * [osm](efg_osm.html)
  * [yandex-tile](efg_yandex-tile.html)
  * [wms](efg_wms.html)
  * [wms-single-tile](efg_wms-single-tile.html)
  * [esri-tile](efg_esri-tile.html)
* Слои представленные "сырыми" векторными данными
  * [wfs](efg_wfs.html)
  * [esri-dynamic](efg_esri-dynamic.html)
  * [geojson](efg_geojson.html)
  * [kml](efg_kml.html)
* Комбинированные слои
  * [wms-wfs](efg_wms-wfs.html)
  * [wms-esri](efg_wms-esri.html)
* Слои предназначенные для геокодирования и обратного геокоирования
  * [geocoder-osm-ru](efg_geocoder-osm-ru.html)
  * [geocoder-osm-overpass](efg_geocoder-osm-overpass.html)
  * [geocoder-yandex](efg_geocoder-yandex.html)
  
## JS API карты

При работе с картой иногда возникает необходимость управлять отдельными её функциями из разных модулей самой карты, а иногда и из внешних приложений, в которые она встраивается, и для этого у карты имеется [JS API](efg_jsapi.html).
