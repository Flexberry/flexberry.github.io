---
title: Офлайн режим работы ember-приложения
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd2_offline.html
folder: products/ember-flexberry-data/offline/
lang: ru
summary: 
---

## Offline

* Indexeddb
* Dexie

Для реализации offline-режима в процессе генерации используется blueprint `flexberry-application-offline`. Данный blueprint запускает генерацию необходимых для offline-режима сервисов, адаптеров и сериализаторов на основе метаданных. Перечисленные компоненты создаются в дополнение к основным.

К offline-сервисам относятся:

* `offline-globals` - реализует активацию режима offline и получение схемы offline-базы данных;
* `store` - в котором задается схема offline-базы данных, полученная из offline-globals.

Адаптеры и сериализаторы генерируются для каждой модели, полученной из метаданных.

Перед генерацией следует убедиться, что в репозитории свежие метаданные. В противном случае может возникнуть ошибка парсинга модели. Свежие метаданные нужно сгенерировать через [Flexberry Designer](https://flexberry.github.io/ru/fdo_landing_page.html) и положить в папку `vendor/flexberry`.

Команда запуска генерации:

```git
уmber generate flexberry-application-offline app --enable-offline --metadata-dir="путь к метаданным" --skip-confirmation
```

Параметр `--enable-offline` означает, что offline-режим для приложения будет активирован по умолчанию. В случае вызова генерации без данного флага будут созданы необходимые компоненты, но сам режим по умолчанию будет выключен.

## Включение офлайн режима

Для включения offline-режима нужно:

* выключить флаг `setOnlineAvailable`; для этого требуется переопределить offline-globals в tests/dummy/app/services/offline-globals.js следующим образом:

```javascript
      import OfflineGlobals from 'ember-flexberry-gis/services/offline-globals';
      export default OfflineGlobals.extend({
        init() {
          this._super(...arguments);
          this.setOnlineAvailable(false);
        }
      });
```

* в addon/services/local-storage.js проставить флаг `available`: true
* в environment.js проставить флаг `config.APP.offline.offlineEnabled` = true

После этого при запуске приложения должна появиться offline-БД (можно увидеть в хроме  во вкладке Application и слева найти IndexedDB) и запросы к бэку не должны посылаться.
