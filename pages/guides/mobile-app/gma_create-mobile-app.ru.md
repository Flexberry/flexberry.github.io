---
title: Создание мобильного приложения 
keywords: Mobile, Phobe, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: false
permalink: ru/gma_create-mobile-app.html
lang: ru
summary: Руководство по созданию мобильных приложений на платформе Flexberry.
---

## Создание мобильного приложения

1.Запускаем командную строку.

2.Переходим в директорию где будет хранится мобильное приложение.

3.При помощи команды [`cordova create`](https://cordova.apache.org/docs/en/latest/reference/cordova-cli/index.html#cordova-create-command) создаем каркас для мобильного приложения. 

Все последующие действия должны выполняться в директории приложения:

4.Создаем папку с произвольным именем, (например: `ember-app`), в данной папке будет хранится ember приложение.

5.Создаем папку с именем `scripts`, в данной папке создаем файл `buildEmberApp.js` со следущим содержимым [кодом](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js).

6.В коде файла находим функцию `process.chdir('demoapp');`, и заменяем имя папки `demoapp` на имя созданой папки на **шаге 4** (например: `ember-app`).

7.Переходим в директорию с `Ember приложением`, находим файл конфигурации `.ember-cli` и открываем его в любом редакторе кода. Добавляем параметр `"output-path": "../www"`, между фигурными скобка.

{% include note.html content="После параметра `"output-path": "../www"` необходимо установить знак припенания `,`, если после данного параметра следую другие параметры" %}


## Вы можете

* [Построение мобильного приложения](gma_build-mobile-app.html)
* [Введение](gma_landing-page.html)
