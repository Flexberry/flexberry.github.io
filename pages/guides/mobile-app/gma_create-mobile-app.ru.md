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

4.Создаем папку с произвольным именем, (например: `ember-app`), в данной папке необходимо разместить ember приложение.

![](/images/pages/guides/mobile-app/create-finder-ember-app.png)

5.Создаем папку с именем `scripts`.

![](/images/pages/guides/mobile-app/create-finder-scripts.png)

6.В папке `scripts` создаем файл `buildEmberApp.js` со следущим содержимым [кодом](https://github.com/Flexberry/flexberry-cordova-ember-demo/blob/master/scripts/buildEmberApp.js)

![](/images/pages/guides/mobile-app/create-file-buildEmberApp.png)

7.В коде файла находим функцию `process.chdir('demoapp');`, и заменяем имя папки `demoapp` на имя созданой папки на **шаге 4** (например: `ember-app`).

![](/images/pages/guides/mobile-app/update-name-app-code.png)

8.Для настройка папки размещения выходных файлой ember приложения в папку `www` при выполнение сборки мобильного приложения, необходимо добавить параметр `"output-path": "../www"` в файл конфигурации `.ember-cli`, который располагается в корневой директории `Ember приложения`, данный файл можно открыть в любом редакторе кода.

![](/images/pages/guides/mobile-app/add-new-param-embercli.png)

{% include note.html content="Если не будет установлен данный параметр, тогда необходимо будет вручную переносить выходные файлы ember приложения из папки dist в папку www мобильного приложения" %}

## Вы можете

* [Построение мобильного приложения](gma_build-mobile-app.html)
* [Введение](gma_landing-page.html)
