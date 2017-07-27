---
title: Сборка мобильного приложения 
keywords: Mobile, Phobe, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_build-mobile-app.html
lang: ru
summary: Руководство по созданию мобильных приложений на платформе Flexberry.
---

## Корректировка файлов Ember приложения

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

## Сборка мобильного приложения 

1.В командной строке переходим в папку с приложением.

![](/images/pages/guides/mobile-app/jump-mobile-app.png)

2.Для того чтобы добавить платформу к проекту, необходимо в командной строке ввести команду `cordova platform add имя_платформы`. С полным списоком поддерживаемых платформ можно ознакомится [здесь](https://cordova.apache.org/docs/en/latest/guide/support/index.html)

![](/images/pages/guides/mobile-app/add-new-platforms.png)

{% include note.html content="Перед первой сборкой приложения желательно произвести предварительную проверку платформ на соотвествие всем требования для построения приложений на добавленных платформах. Предварительная проверка делается при помощи команды `cordova requirements`" %}

3.Создаем мобильные приложения, для это необходимо ввести команду `cordova build`.

![](/images/pages/guides/mobile-app/mobile-app-build1.png)
![](/images/pages/guides/mobile-app/mobile-app-build2.png)

## Вы можете

* [Запуск мобильного приложения](gma_launch-mobile-app.html)
* [Создание мобильного приложения](gma_create-mobile-app.html)