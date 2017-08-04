---
title: Архитектура приложения Cordova
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, architecture
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gms_architecture-mobile-app.html
lang: ru
---

## Описание

На данном шаге будет рассмотрена архитектура приложения Cordova и Ember.js.

## Архитектура Apache Cordova

В приложении Apache Cordova есть несколько компонентов. На следующей диаграмме показан высокоуровневый вид архитектуры приложения Cordova.

![](/images/pages/guides/mobile-app/cordovaapparchitecture-ru.png)

В основе приложения Cordova лежить веб-браузер WebView, при помощи которого происходит загрузка разработанного веб-приложения. Для работы веб-приложения требуется локальный файл с именем `index.html` , который ссылается на CSS, JavaScript, изображения, мультимедийные файлы или другие ресурсы. Более подробно о архитектуре приложения Cordova можно узнать [здесь](https://cordova.apache.org/docs/en/7.x/guide/overview/index.html)

## Архитектура Ember-flexberry

На следующей диаграмме показана архитектура приложения Ember.js разрабатываемого при использование технологии ember-flexberry.

![](/images/pages/guides/mobile-app/ember-architecture.PNG)

На следующей диаграмме показана архитектура приложения Cordova взаимодействие с приложение Ember.js

![](/images/pages/guides/mobile-app/cordova-ember-architecture.png)

## Вы можете

* [Перейти на следующий шаг ->](gma_po-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gma_landing-page.html)
