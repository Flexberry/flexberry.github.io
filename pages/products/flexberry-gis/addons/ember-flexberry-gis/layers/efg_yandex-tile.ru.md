---
title: Тип слоя yandex-tile
keywords: ember
sidebar: ember-flexberry-gis_sidebar
toc: true
permalink: ru/efg_yandex-tile.html
lang: ru
published: true
---

## Спецификация

Слой `yandex-tile` предназначен для отображения на интерактивной карте тайловых слоёв [Яндекс-карт](https://yandex.ru/maps),
однако то, как организована работа слоя несколько отличается от работы [классических тайловых слоев](efg_tile.html).
Дело в том, что Яндекс не дает прямого доступа к тайловым сервисам [Яндекс-карт](https://yandex.ru/maps), их использование допускается только через официальное [API Яндекс-карт](https://tech.yandex.ru/maps/).
Слой `yandex-tile` подгружает [API Яндекс-карт](https://tech.yandex.ru/maps/), добавляет внутрь интерактивной карты [полноценный экземпляр Яндекс-карты](https://tech.yandex.ru/maps/doc/jsapi/2.1/ref/reference/Map-docpage/) с заданными настройками, и затем на протяжении всей работы с интерактивной картой просто проксирует её события (смещение карты, изменение zoom-а, и т.п.) на вложенную Яндекс-карту.

## Ember-компонент слоя и его свойства

Ember-компонет слоя располагается в специальном аддоне [ember-flexberry-gis-yandex](https://github.com/Flexberry/ember-flexberry-gis-yandex) по пути [ember-flexberry-gis-yandex/addon/components/layers/tile-yandex-layer](https://github.com/Flexberry/ember-flexberry-gis-yandex/blob/develop/addon/components/layers/tile-yandex-layer.js) и поддерживает следующий набор свойств, которые соответствуют одноименным с ними настройкам в объекте `settings` в модели слоя:

Наименование свойства      |Тип свойства      |Описание          
:--------------------------|:-----------------|:-----------------
`type`| `String` | Тип желаемого тайлового слоя Яндекс-карты, который может принимать значения 'map' (обычная рисованная карта), 'satellite (спутниковые снимки)', 'hybrid' (гибридная карта совмещающая рисованные дороги и прочие объекты с подложкой из спутниковых снимков), и по умолчанию имеет значение 'map'
`jsApiUrl`| `String` | URL для загрузки API Яндекс-карт, по умолчаню имеет значение 'https://api-maps.yandex.ru/2.1/'
`detectLanguageAutomatically`| `Boolean` | Флаг: показывает нужно ли автоматически определять язык приложения, чтобы использовать его в качестве параметра 'lang' в URL-е, при загрузке API Яндекс-карт. По умолчанию имет занчение `true`. Если проставить ему значение `false`, тогде потребуется явно указывать один из поддерживаемых языков ('ru_RU', 'ru_UA', 'en_US', 'en_RU', 'uk_UA', 'tr_TR') в свойстве `jsApiUrl` в виде ULR-параметра 'lang': https://api-maps.yandex.ru/2.1/?lang=ru_RU

## Примеры использования

Пример добавления на карту тайлового слоя рисованной Яндекс-карты:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_yandex-tile/yandex-tile-map-example.png)

Пример добавления на карту тайлового слоя Яндекс-карты со спутниковыми снимками:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_yandex-tile/yandex-tile-satellite-example.png)

Пример добавления на карту тайлового слоя Гибридной Яндекс-карты, совмещающей рисованные дороги и прочие объекты с подложкой из спутниковых снимков:

![](/images/pages/products/flexberry-gis/addons/ember-flexberry-gis/layers/efg_yandex-tile/yandex-tile-hybrid-example.png)
