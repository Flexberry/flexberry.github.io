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

<p>Для реализации offline режима в процессе генерации используется blueprint flexberry-application-offline. Данный blueprint запускает генерацию необходимых для offline-режима сервисов, адаптеров и сериализаторов на основе метаданных. Перечисленные компоненты создаются в дополнение к основным.</p>
<p>К offline сервисам относятся</p>
<ul>
<li>offline-globals - реализует активацию режима offline и получение схемы offline базы данных;</li>
<li>store - в котором задается схема offline базы данных, полученная из offline-globals.</li>
</ul>
<p>Адаптеры и сериализаторы генерируются для каждой модели, полученной из метаданных.</p>
<p>Команда запуска генерации:</p>
<p style="text-align: left;"><em>ember generate flexberry-application-offline app --enable-offline --metadata-dir="путь к метаданным" --skip-confirmation</em></p>
<p>Параметр <em>--enable-offline</em> означает, что offline режим для приложения будет активирован по умолчанию. В случае вызова генерации без данного флага, будут созданы необходимые компоненты, но сам режим по умолчанию будет выключен.</p>
