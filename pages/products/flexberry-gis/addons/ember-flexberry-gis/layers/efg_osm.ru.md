---
title: Тип слоя osm
keywords: ember
sidebar: ember-flexberry-gis_sidebar
toc: true
permalink: ru/efg_osm.html
lang: ru
published: true
---

## Спецификация

Слой `osm` это обычный [тайловый слой](efg_tile.html), в котором уже заданы все необходимые настройки для отображения тайлов сервиса [Open Street Map](https://www.openstreetmap.org).

## Ember-компонент слоя и его свойства

Ember-компонент слоя `osm` располагается по пути [ember-flexberry-gis/addon/components/layers/osm-layer](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/components/layers/osm-layer.js)

## Примеры использования

Пример добавления слоя `osm`:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_osm/osm-layer-example.png)

Обратите внимание, система координат и прочие атрибуты характерные для [тайловых слоев](efg_tile.html), не указываются,
указывается только тип слоя `osm`, имя слоя, при необходимости еще опциональные описание и ключевые слова.
