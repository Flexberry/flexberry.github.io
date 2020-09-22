---
title: Run application Flexberry Ember as mobile apps Apache Cordova
sidebar: flexberry-ember-2_sidebar
keywords:
toc: true
permalink: en/ef2_cordova.html
folder: products/ember-flexberry/mobile/
lang: en
autotranslated: true
hash: e1603fab3ef45e51c19b453f9b5c2a1446bc04b1127aeee4c58e1338f2000464
summary: describes how to run Flexberry Ember apps as mobile apps Apache Cordova.
---

## The application to run as mobile apps Apache Cordova
Applications can be converted to a hybrid mobile application based on the [Apache Cordova](https://cordova.apache.org/).

## Installation

* Install Ember CLI and Cordova (>= 6.0.0) using NPM.

```bash
npm install -g ember-cli
npm install -g cordova
```

* Create a Cordova project

```bash
cordova create hello com.example.hello HelloWorld
```

* Creation of project Ember (if necessary)
If the project already exists, then, depending on the project requirements, you need to configure the directory in which the build will be the application's Ember. Its typical file name is `.ember-cli`.

Read more about create a Cordova project, you can read the [documentation](https://cordova.apache.org/docs/en/dev/guide/cli/index.html).


## Features application configuration Ember

For correct operation of the application Flexberry Ember inside Cordova may require minor adjustments.

* Routing must be enabled `hash` mode.
* Parameter `output-path` (file `.ember-cli` or argument `ember build`) needs to refer to `www` folder in the root of your Cordova app.
* The paths to the resources should not be absolute, because he `index.html` in apk is not in the root.
* VCS need an empty folder `www`; otherwise it will error "Current working directory is not a Cordova-based project."

## Setup automated build application

In order to build Cordova apps was automatic build of Ember app, you can use the [hooks](https://cordova.apache.org/docs/en/dev/guide/appdev/hooks/).

To do this:

* Create a NodeJS script to run `ember build`. [Example script](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js).
* To set the hook in the configuration file of your Cordova project (`config.xml`). [Example configuration file](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/config.xml).

## Security settings

A mobile platform, to increase security of applications, set a number of restrictions on access to the network.

For iOS 9 you need to configure Transport Security in *-info.plist as follows:

```xml
<key>NSAppTransportSecurity</key>
<dict>
    <key>NSAllowsArbitraryLoads</key>
    <true/>
</dict>
```

or this:

```xml
<key>NSAppTransportSecurity</key>
<dict>
  <key>NSExceptionDomains</key>
  <dict>
    <key>yourserver.com</key>
    <dict>
      <!--Include to allow subdomains-->
      <key>NSIncludesSubdomains</key>
      <true/>
      <!--Include to allow HTTP requests-->
      <key>NSTemporaryExceptionAllowsInsecureHTTPLoads</key>
      <true/>
      <!--Include to specify the minimum TLS version-->
      <key>NSTemporaryExceptionMinimumTLSVersion</key>
      <string>TLSv1.1</string>
    </dict>
  </dict>
</dict>
```

## The demo application

As example, consider [a demo application on GitHub](https://github.com/Flexberry/flexberry-cordova-ember-demo).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}