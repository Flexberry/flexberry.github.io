---
title: Архитектура приложения Cordova
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, architecture
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gms_architecture-mobile-app.html
lang: ru
---

## Описание

На данном шаге будет рассмотрена архитектура приложения Cordova и веб-приложения Ember.js разработанного при помощи технологии Flexberry Ember.

## Архитектура Apache Cordova

[**Apache Cordova**](http://cordova.apache.org/) - это платформа разработки мобильных приложений с открытым исходным кодом. Она позволяет использовать стандартные веб-технологии, такие как HTML5, CSS3, JavaScript и [JavaScript-фреймворки](https://www.reclamare.ua/blog/javascript-frejmvorki/) для кросс-платформенной разработки.

В приложении Apache Cordova есть несколько компонентов. На следующей диаграмме показан высокоуровневый вид архитектуры приложения Cordova.

![](/images/pages/guides/mobile-app/cordovaapparchitecture-ru.png)

В основе приложения Cordova лежит компонент WebView, при помощи которого происходит загрузка разработанного веб-приложения. Для работы веб-приложения требуется локальный файл с именем `index.html` , который ссылается на CSS, JavaScript, изображения, мультимедийные файлы или другие ресурсы. Более подробно о архитектуре приложения Cordova можно узнать [здесь](https://cordova.apache.org/docs/en/7.x/guide/overview/index.html)

## Архитектура Flexberry Ember

При использовании технологии Flexberry Ember разрабатываются полноценные клиентские веб-приложения. Для взаимодействия клиентского веб-приложения с бакэндом используется REST API (протокол OData).

На следующей диаграмме показана архитектура веб-приложения Flexberry Ember.

![](/images/pages/guides/mobile-app/ember-architecture.PNG)

При создание мобильного приложения на платформе Cordova взаимодействие веб-приложения с бакэндом не требует каких либо доработок, оно остается прежним.
На следующей диаграмме показана архитектура приложения Cordova в взаимодействие с приложение Flexberry Ember

![](/images/pages/guides/mobile-app/cordova-ember-architecture.PNG)

В итоге была рассмотрена архитектура приложений Cordova и Flexberry Ember. Далее будет рассмотрен состав необходимого программного обеспечния для разработки мобильных приложений.

## Вы можете

* [Перейти на следующий шаг ->](gma_po-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gma_landing-page.html)
