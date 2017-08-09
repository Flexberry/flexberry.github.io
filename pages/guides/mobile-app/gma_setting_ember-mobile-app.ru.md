---
title: Настройка Ember приложения 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_setting_ember-mobile-app.html
lang: ru
---

## Описание

На данном шаге описаны настройки Ember приложения для локальной работы приложения. Данные настройки необходимо применить, т.к. разработанные веб-приложения в Ember не предназначены для локальной работы.

## Основные настройки

Для локальной работы Ember приложения в Apache Cordova необходимо сделать следующие:

{% include note.html content="Все последующие действия должны выполняться в директории Ember приложения" %}

1.В файле `environment.js` расположенного в папке `config` необходимо изменить следующие:

* Параметр `baseURL` заменяем на `rootURL`.
* В параметре `locationType` заменить значение `auto` на `none`.

![](/images/pages/guides/mobile-app/edit-ember-environment.PNG)

2.В файле `index.html` расположенного в папке `app` всем тегам `script` ссылающиеся на сторонние источники (например `src="//cdn.polyfill.io/v1/polyfill.js?features=es6"`), необходимо дописать протокол `https` или `http`.

![](/images/pages/guides/mobile-app/edit-index-ember-src.PNG)

## Настройка папки размещения выходных файлой

Для настройка папки размещения выходных файлой ember приложения в папку `www` при выполнение сборки мобильного приложения, необходимо добавить параметр `"output-path": "../www"` в файл конфигурации `.ember-cli`, который располагается в корневой директории `Ember приложения`, данный файл можно открыть в любом редакторе кода.

![](/images/pages/guides/mobile-app/add-new-param-embercli.png)

{% include note.html content="Если не будет установлен данный параметр, тогда необходимо будет вручную переносить выходные файлы ember приложения из папки dist в папку www мобильного приложения" %}

## Настройка оторбражения иконок

Для корректного отображения иконок в Ember приложение, необходимо удалить `/assets/` из пути до иконок, в файле `font-icon.css` расположенный в папке `vendor` Ember приложения.

![](/images/pages/guides/mobile-app/edit-font-icon.PNG)

В результате выполения данного шага была произведена настройка Ember приложения для локальной работы в браузере. Далее будет рассмотрен процесс сборки мобильных приложений на платформе Cordova.

## Вы можете

* [Перейти на следующий шаг ->](gma_build-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gma_create-mobile-app.html)
