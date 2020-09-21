---
title: Offline mode ember-application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/efd2_offline.html
folder: products/ember-flexberry-data/offline/
lang: en
autotranslated: true
hash: 27b6ff4ee58c1b894cb218fc6def42e7518211763760bea4dd8cffec68361c2b
summary:
---

## Offline

* Indexeddb
* Dexie

<p>To implement offline mode in the generation process used blueprint flexberry-application-offline. This blueprint runs the generation needed for offline-mode services, adapters and serializers based on the metadata. These components are created in addition to the main.</p>
<p>To the offline services include</p>
the <ul>
the <li>offline-globals - implements the activation of the offline mode and obtain schema offline database данных;</li>
the <li>store - in scheme which is set to the offline database obtained from offline-globals.</li>
</ul>
<p>Adapters and serializers are generated for each model obtained from the metadata.</p>
<p>start Command generate:</p>
<p style="text-align: left;"><em>ember generate flexberry-application-offline app --enable-offline --metadata-dir="path to metadata" --skip-confirmation</em></p>
<p>Option <em>--enable-offline</em> means that the offline mode for the app will be enabled by default. In the event call generated without this flag, will create the necessary components, but the default mode will be turned off.</p>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}