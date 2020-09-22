---
title: Запуск приложения Flexberry Ember в качестве мобильного приложения Apache Cordova
sidebar: flexberry-ember-2_sidebar
keywords: 
toc: true
permalink: ru/ef2_cordova.html
folder: products/ember-flexberry/mobile/
lang: ru
summary: Описание способа запуска Flexberry Ember-приложения в качестве мобильного приложения Apache Cordova.
---

## Запуск приложения в качестве мобильного приложения Apache Cordova
Приложения могут быть преобразованы в "гибридные" мобильные приложения на основе [Apache Cordova](https://cordova.apache.org/).

## Установка

* Установить Ember CLI и Cordova (>= 6.0.0) при помощи NPM.

```bash
npm install -g ember-cli
npm install -g cordova
```

* Создание проекта Cordova

```bash
cordova create hello com.example.hello HelloWorld
```

* Создание проекта Ember (при необходимости)
  Если проект уже существует, то, в зависимости от требований проекта, нужно настроить директорию, в которую будет происходить билд приложения Ember. Обычно это файл `.ember-cli`.

Подробнее о создании проекта Cordova можно прочитать в [документации](https://cordova.apache.org/docs/en/dev/guide/cli/index.html).


## Особенности конфигурации приложения Ember

Для корректной работы приложения Flexberry Ember внутри Cordova могут потребоваться небольшие настройки.

* В роутинге должен быть включен `hash`-режим.
* Параметр `output-path` (в файле `.ember-cli` или в качестве аргумента `ember build`) должен ссылаться на папку `www` в корне приложения Cordova.
* Пути к ресурсам не должны быть абсолютными, т.к. сам `index.html` в apk располагается не в корне.
* В VCS нужна пустая папка `www`; иначе будет ошибка "Current working directory is not a Cordova-based project."

## Настройка автоматической сборки приложения

Для того, чтобы при билде приложения Cordova происходил автоматическая сборка приложения Ember можно воспользоваться [хуками](https://cordova.apache.org/docs/en/dev/guide/appdev/hooks/).

Для этого нужно:

* Создать NodeJS-скрипт для запуска `ember build`. [Пример скрипта](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js).
* Установить хук в конфигурационном файле проекта Cordova (`config.xml`). [Пример конфигурационного файла](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/config.xml). 

## Настройки безопасности

Многие мобильные платформы, для повышения безопасности приложений, устанавливают ряд ограничений на доступ в сеть.

Для iOS 9 нужно настроить Transport Security в *-info.plist следующим образом:

```xml
<key>NSAppTransportSecurity</key>
<dict>
    <key>NSAllowsArbitraryLoads</key>
    <true/>
</dict>
```

или так:

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
      <!--Include to specify minimum TLS version-->
      <key>NSTemporaryExceptionMinimumTLSVersion</key>
      <string>TLSv1.1</string>
    </dict>
  </dict>
</dict>
```

## Демонстрационное приложение

В качестве примера можно рассматривать [демонстрационное приложение на GitHub](https://github.com/Flexberry/flexberry-cordova-ember-demo).
