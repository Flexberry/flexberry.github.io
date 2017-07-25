---
title: Создание мобильного приложения 
keywords: Mobile, Phobe, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_create-mobile-app.html
lang: ru
summary: Руководство по созданию мобильных приложений на платформе Flexberry.
---

## Создание мобильного приложения

1.Запускаем командную строку.

2.Переходим в директорию где будет хранится мобильное приложение.

3.При помощи команды [`cordova create`](https://cordova.apache.org/docs/en/latest/reference/cordova-cli/index.html#cordova-create-command) создаем каркас для мобильного приложения. 

![](/images/pages/guides/mobile-app/terminal.png)

Все последующие действия должны выполняться в директории приложения:

4.Создаем папку с произвольным именем, (например: `ember-app`), в данной папке будет хранится ember приложение.

![](/images/pages/guides/mobile-app/create-finder-ember-app.png)

5.Создаем папку с именем `scripts`.

![](/images/pages/guides/mobile-app/create-finder-scripts.png)

6.В папке `scripts` создаем файл `buildEmberApp.js` со следущим содержимым [кодом](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js)

![](/images/pages/guides/mobile-app/create-file-buildEmberApp.png)

7.В коде файла находим функцию `process.chdir('demoapp');`, и заменяем имя папки `demoapp` на имя созданой папки на **шаге 4** (например: `ember-app`).

![](/images/pages/guides/mobile-app/update-name-app-code.png)

8.Переходим в директорию с `Ember приложением`, находим файл конфигурации `.ember-cli` и открываем его в любом редакторе кода. Добавляем параметр `"output-path": "../www"`, между фигурными скобка.

{% include note.html content="После параметра необходимо установить знак припенания `,`" %}

![](/images/pages/guides/mobile-app/add-new-param-embercli.png)

## Вы можете

* [Построение мобильного приложения](gma_build-mobile-app.html)
* [Введение](gma_landing-page.html)
