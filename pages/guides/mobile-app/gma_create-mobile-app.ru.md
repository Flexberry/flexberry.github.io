---
title: Создание приложения Cordova
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_create-mobile-app.html
lang: ru
---

## Описание

На данном шаге будет описан процесс создания и настройки приложения Cordova.

## Создание приложения Cordova

1.Запускаем командную строку (терминал).

2.Переходим в директорию где будет хранится приложение Cordova.

3.При помощи команды `cordova create` создаем приложение. С полным описанием синтаксиса команды, можно ознакомится [здесь](https://cordova.apache.org/docs/en/latest/reference/cordova-cli/index.html#cordova-create-command)

![](/images/pages/guides/mobile-app/terminal.png)

Все последующие действия должны выполняться в директории приложения:

4.Создаем папку с произвольным именем, (например: `ember-app`), в данной папке необходимо разместить ember приложение.

![](/images/pages/guides/mobile-app/create-finder-ember-app.png)

5.Далее создаем папку с именем `scripts`, в которой будет хранится файл со скриптом, для загрузки актуальных пакетов приложения и генерации актуальной версии приложения в папку `www` Apache Cordova.

![](/images/pages/guides/mobile-app/create-finder-scripts.png)

6.В папке `scripts` создаем файл `buildEmberApp.js` со следущим содержимым [кодом](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js).

![](/images/pages/guides/mobile-app/create-file-buildEmberApp.png)

7.В коде файла находим функцию `process.chdir('demoapp');`, и заменяем имя папки `demoapp` на имя созданой папки на **шаге 4** (например: `ember-app`).

![](/images/pages/guides/mobile-app/update-name-app-code.png)

8.Для выполнения скрипта в файле `buildEmberApp.js` при выполнение команд Apache Cordova, необходимо в файле конфигурации `config.xml` прописать путь до файла. Окрываем файл конфигурации и добавляем инструкцию `<hook src="scripts/buildEmberApp.js" type="before_prepare" />` в файл. 

![](/images/pages/guides/mobile-app/add-hook-config-cordova.PNG)

В результате выполнения данного шага было создано приложение Cordova и произведены первоначальные настройки приложения. Далее будет описан процесс настройки Flexberry Ember приложения.

## Вы можете

* [Перейти на следующий шаг ->](gma_setting_ember-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gma_po-mobile-app)
