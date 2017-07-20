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

1. Запускаем командную строку.
2. Переходим в директорию будущего месторасполежения приложения.
3. При помощи команды [`cordova create`](https://cordova.apache.org/docs/en/latest/reference/cordova-cli/index.html#cordova-create-command) создаем первичную структуру приложения. 
4. Переходи в директорию с `Ember приложением` для настройка папки размещения выходных файлой при выполнение команды `ember build` в папку `www` мобильного приложения.
5. Находим файл конфигурации `.ember-cli` и открываем его в любом редакторе кода. Добавляем параметр `"output-path": "путь до мобильного приложения/www"`, между фигурными скобка.
6. Выполняем команду `ember build` для создания/обновления выходных файлов приложения.

## Вы можете

* [Построение мобильного приложения](gma_build-mobile-app.html)
* [Введение](gma_landing-page.html)
