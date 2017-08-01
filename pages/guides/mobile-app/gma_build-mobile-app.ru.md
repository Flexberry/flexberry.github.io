---
title: Сборка мобильного приложения 
keywords: Mobile, Phobe, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_build-mobile-app.html
lang: ru
summary: Руководство по созданию мобильных приложений на платформе Flexberry.
---

## Сборка мобильного приложения 

1.В командной строке переходим в папку с приложением.

![](/images/pages/guides/mobile-app/jump-mobile-app.png)

2.Для того чтобы добавить платформу к проекту, необходимо в командной строке выполним команду `cordova platform add имя_платформы`. С полным списоком поддерживаемых платформ можно ознакомится [здесь](https://cordova.apache.org/docs/en/latest/guide/support/index.html)

![](/images/pages/guides/mobile-app/add-new-platforms.png)

{% include note.html content="Перед первой сборкой приложения желательно произвести предварительную проверку платформ на соотвествие всем требования для построения приложений на добавленных платформах. Предварительная проверка делается при помощи команды `cordova requirements`" %}

3.Для сборки мобильных приложений, для всех добавленных платформ в проекте Cordova, в командной строке выполним команду `cordova build`.

![](/images/pages/guides/mobile-app/mobile-app-build1.png)
![](/images/pages/guides/mobile-app/mobile-app-build2.png)

{% include note.html content="Для сборки мобильного приложения под конкретную платформу, используйте команду `cordova build имя_платформы` " %}

## Вы можете

* [Запуск мобильного приложения](gma_launch-mobile-app.html)
* [Настройка Ember приложения](gma_setting_ember-mobile-app.html)
