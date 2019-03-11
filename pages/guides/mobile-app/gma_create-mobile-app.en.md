--- 
title: creating a Cordova app 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market 
sidebar: guide-mobile-app_sidebar 
toc: true 
permalink: en/gma_create-mobile-app.html 
lang: en 
autotranslated: true 
hash: 1c63d9862fb3688c040c9db946075c0fefebac513ffaba6767b0ea01c49e52be 
--- 

## Description 

In this step will be described the process of creating and configuring a Cordova app. 

## create the Cordova app 

1.Run command prompt (terminal). 

2.Go to directory where will be stored your Cordova app. 

3.Using `cordova create` create the application. A full description of the command syntax can be found [sdes](https://cordova.apache.org/docs/en/latest/reference/cordova-cli/index.html#cordova-create-command) 

![](/images/pages/guides/mobile-app/terminal.png) 

All subsequent steps should be performed in the directory application: 

4.Create a folder with any name, (example: `ember-app`), in this folder you should place the ember app. 

![](/images/pages/guides/mobile-app/create-finder-ember-app.png) 

5.Next, create a folder named `scripts` in which the file is stored with a script to download the relevant application package and generate the current version of application folder `www` Apache Cordova. 

![](/images/pages/guides/mobile-app/create-finder-scripts.png) 

6.In the folder create a file `scripts` `buildEmberApp.js` with the following content [codom](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js). 

![](/images/pages/guides/mobile-app/create-file-buildEmberApp.png) 

7.In the code file to find the function `process.chdir('demoapp');`, and model folder name `demoapp` in the name of the created folder **step 4** (example: `ember-app`). 

![](/images/pages/guides/mobile-app/update-name-app-code.png) 

8.To run the script in the file `buildEmberApp.js` when command execution Apache Cordova, you need the configuration file `config.xml` to set the path to the file. Okruhem the configuration file and add manual `<hook src="scripts/buildEmberApp.js" type="before_prepare" />` to a file. 

![](/images/pages/guides/mobile-app/add-hook-config-cordova.PNG) 

The result of this step was created a Cordova app and made the initial setup of the application. Next will be described the configuration process Flexberry Ember application. 

## You can 

* [Go to next step ->](gma_setting_ember-mobile-app.html) 
* [<- Back to previous step](gma_po-mobile-app) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}