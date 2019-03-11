--- 
title: Build mobile applications 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market 
sidebar: guide-mobile-app_sidebar 
toc: true 
permalink: en/gma_build-mobile-app.html 
lang: en 
autotranslated: true 
hash: 5fe4e538da614e2a5d41cebedd2c58565883510596ad9ba762d5f9371a71ac13 
--- 

## Description 

This step describes the process of adding and build mobile applications. To assemble the mobile application for the iOS platform, it is necessary at each step to change the platform `android` on `ios`. 

## Build mobile apps 

1.In command prompt go to the folder with the application. 

![](/images/pages/guides/mobile-app/jump-mobile-app.png) 

2.In order to add a platform to your Cordova app, you need a command prompt to run the command `cordova platform add имя_платформы`. With a comprehensive list of supported platforms can be found [here](https://cordova.apache.org/docs/en/latest/guide/support/index.html) 

![](/images/pages/guides/mobile-app/add-new-platforms.png) 

{% include note.html content="Before the first build of the application, it is desirable to produce pre-tested platforms fulfills all the requirements to build applications on added platforms. Pre-check is done with the command `cordova requirements`" %} 

3.To build mobile apps for added platforms to the Cordova app, in the command prompt, run the command `cordova build`. 

![](/images/pages/guides/mobile-app/mobile-app-build1.png) 
![](/images/pages/guides/mobile-app/mobile-app-build2.png) 

{% include note.html content="To build mobile apps for a specific platform, use the command `cordova build имя_платформы`" %} 

In the result of the execution of this step will create the installation files for mobile applications. Next will be considered process launch mobile applications on the device. 

## You can 

* [Go to next step ->](gma_launch-mobile-app.html) 
* [<- Back to previous step](gma_setting_ember-mobile-app.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}