---
title: Настройка Ember приложения 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_setting_ember-mobile-app.html
lang: ru
---

## Основные настройки

Для корректной работы Ember приложения в Apache Cordova необходимо сделать следующие:

{% include note.html content="Все последующие действия должны выполняться в директории www приложения" %}

1.В файле `index.html` необходимо изменить следующие:

1.1.Необходимо из файла удалить строку с тегом `<base href="/" />`.

![](/images/pages/guides/mobile-app/edit-index-ember.PNG)

1.2.В строке с тегом `<meta name="ember-app/config/environment" ...` в атрибуте `content` необходимо поправить значения у следующих параметров:

1.2.1.Параметр `baseURL` или `rootURL` удалить у него значение `/`.

![](/images/pages/guides/mobile-app/edit-index-ember-baseURL.PNG)

1.2.2.Параметр `locationType` заменить значение `auto` на `none`.

![](/images/pages/guides/mobile-app/edit-index-ember-locType.PNG)

1.3Всем тегам `script` ссылающиеся на сторонние источники (например `src="//cdn.polyfill.io/v1/polyfill.js?features=es6"`), необходимо дописать протокол `https` или `http`.

![](/images/pages/guides/mobile-app/edit-index-ember-src.PNG)

## Настройка папки размещения выходных файлой

Для настройка папки размещения выходных файлой ember приложения в папку `www` при выполнение сборки мобильного приложения, необходимо добавить параметр `"output-path": "../www"` в файл конфигурации `.ember-cli`, который располагается в корневой директории `Ember приложения`, данный файл можно открыть в любом редакторе кода.

![](/images/pages/guides/mobile-app/add-new-param-embercli.png)

{% include note.html content="Если не будет установлен данный параметр, тогда необходимо будет вручную переносить выходные файлы ember приложения из папки dist в папку www мобильного приложения" %}

## Настройка оторбражения иконок

Для корректного отображения иконок в Ember приложение, необходимо удалить `/assets/` из путь до иконок, в файле `font-icon.css` расположенный в папке `vendor` Ember приложения.

![](/images/pages/guides/mobile-app/edit-font-icon.PNG)

## Вы можете

* [Запуск мобильного приложения](gma_launch-mobile-app.html)
* [Создание мобильного приложения](gma_create-mobile-app.html)