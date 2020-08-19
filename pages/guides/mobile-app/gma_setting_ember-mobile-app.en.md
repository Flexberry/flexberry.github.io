--- 
title: setting up Ember apps 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market 
sidebar: guide-mobile-app_sidebar 
toc: true 
permalink: en/gma_setting_ember-mobile-app.html 
lang: en 
autotranslated: true 
hash: 1e9f50910ce32203ee3d10b854c489071df0f0b8166878bf59b4bfc4f8d3d22f 
--- 

## Description 

This step describes the settings Ember app for local application. These settings must be applied, because the developed web apps in Ember is not intended for local work. 

## Basic settings 

For local Ember app in Apache Cordova, you must do the following: 

{% include note.html content="All subsequent actions should be executed in the directory Ember app" %} 

1.In the file `environment.js` located in the folder `config` you must change the following: 

* Parameter `baseURL` model on `rootURL`. 
* PstrflocationType` parameter to replace the value `auto` on `none`. 

![](/images/pages/guides/mobile-app/edit-ember-environment.PNG) 

2.In the file `index.html` located in the folder `app` all tags `script` referencing third party sources (e.g. `src="//cdn.polyfill.io/v1/polyfill.js?features=es6"`), we need to add the Protocol `https` or `http`. 

![](/images/pages/guides/mobile-app/edit-index-ember-src.PNG) 

## configure drop folder output filoi 

To configure the drop folder output filoi ember apps in a folder `www` when you build a mobile app, you must add the parameter `"output-path": "../www"` in the configuration file `.ember-cli`, which is located in the root directory `Ember приложения`, this file can be opened in any code editor. 

![](/images/pages/guides/mobile-app/add-new-param-embercli.png) 

{% include note.html content="Unless this option is set, then you will need to manually transfer the output files ember app from the dist folder into the www directory mobile app" %} 

## setting otobrazheniya icons 

For correct display of icons in the Ember application, you need to remove `/assets/` from the path to the icons in the file `font-icon.css` located in the folder `vendor` Ember application. 

![](/images/pages/guides/mobile-app/edit-font-icon.PNG) 

In the result of the execution of this step was made to configure the Ember application for local work in the browser. Next, you will learn the build process of mobile applications on the Cordova platform. 

## You can 

* [Go to next step ->](gma_build-mobile-app.html) 
* [<- Back to previous step](gma_create-mobile-app.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}