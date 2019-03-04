--- 
title: the Architecture of a Cordova app 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, architecture 
sidebar: guide-mobile-app_sidebar 
toc: true 
permalink: en/gms_architecture-mobile-app.html 
lang: en 
autotranslated: true 
hash: f586d0500e7cb00929852278037937432094c0fee551622940f140fe3154b2a5 
--- 

## Description 

In this step, you will consider the architecture of a Cordova app and web app Ember.js developed with the help of technology Flexberry Ember. 

## Architecture Apache Cordova 

[**Apache Cordova**](http://cordova.apache.org/) is a platform for developing mobile applications with open source. It allows you to use standard web technologies such as HTML5, CSS3, and JavaScript [JavaScript-frameworks](https://www.reclamare.ua/blog/javascript-frejmvorki/) for cross-platform development. 

In Apache Cordova application has several components. The following diagram shows the high-level architecture of a Cordova app. 

![](/images/pages/guides/mobile-app/cordovaapparchitecture-EN.png) 

Based Cordova app lezhit WebView component, which is the download of developed web applications. For the web-application requires local file for filename `index.html` that references CSS, JavaScript, images, media files, or other resources. Learn more about the architecture of a Cordova app can be read [here](https://cordova.apache.org/docs/en/7.x/guide/overview/index.html) 

## Architecture Flexberry Ember 

Using technology developed Flexberry Ember full client web application. For the interaction client web application with the backend using a REST API (OData Protocol). 

The following diagram shows the architecture of the web application Flexberry Ember. 

![](/images/pages/guides/mobile-app/ember-architecture.PNG) 

When creating a mobile app using the Cordova platform interaction web application with a backend does not require any modifications, it remains the same. 
The following diagram shows the architecture of a Cordova app to interact with the app Flexberry Ember 

![](/images/pages/guides/mobile-app/cordova-ember-architecture.PNG) 

In the end, was considered the architecture of a Cordova application and Flexberry Ember. Next will be considered part of the necessary software supply for the development of mobile applications. 

## You can 

* [Go to next step ->](gma_po-mobile-app.html) 
* [<- Back to previous step](gma_landing-page.html) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/