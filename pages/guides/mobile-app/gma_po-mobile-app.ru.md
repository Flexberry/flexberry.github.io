---
title: Состав программного обеспечения 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_po-mobile-app.html
lang: ru
---

## Описание

На данном шаге будет представлен список требуемого программного обечпечения для разработки мобильных приложений на платформе Apache Cordova.

Базовое программное обеспечение:
- [Node.js](https://nodejs.org/en/download/) версии: от 5.11 до 6.11
- [Bower](https://bower.io/#install-bower)
- [Ember-CLI](https://guides.emberjs.com/v2.14.0/getting-started/#toc_installation) версии 2.4.3
- [Apache Cordova](https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli)

## Android

Для разработки мобильного приложения для платформы Android необходимо следующие программное обеспечение:
- [Java Development Kit (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Android Studio и Platform SDK](https://developer.android.com/studio/index.html)

После установки программного обеспечения необходимо произвести [настройку переменных среды](https://cordova.apache.org/docs/en/7.x/guide/platforms/android/index.html#setting-environment-variables).

Разрабатывать мобильные приложения для Andoroid можно под управлением любой ОС.  

## iOS

Разработку мобильных приложения для  iOS, выполняется только в операционной системе OS X на компьютерах на базе Intel Xcode® 7.0 (минимальная требуемая версия) работает только в OS X версии 10.10.4 (Yosemite) или выше и включает в себя iOS 9 SDK (Software Development Kit).

Необходимое программное обеспечение:
- [Xcode](https://itunes.apple.com/us/app/xcode/id497799835?mt=12). После установки Xcode для запуска Cordova необходимо включить несколько инструментов командной строки. В командной строке выполните команду: `xcode-select --install`.
- Инструмент [ios-deploy](https://www.npmjs.com/package/ios-deploy) позволяет запускать приложения iOS на устройстве из командной строки.

В результате выполнения данного шага была произведена установка и настройка необходимого программного обеспечения для создания мобильных приложения на платформе Apache Cordova. Далее будет описан процесс создания приложения Cordova.

## Вы можете

* [Перейти на следующий шаг ->](gma_create-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gms_architecture-mobile-app.html)