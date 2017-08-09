---
title: Сборка мобильного приложения 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: true
permalink: ru/gma_build-mobile-app.html
lang: ru
---

## Описание

На данном шаге описан процесс добавления и сборки мобильных приложений. Для того чтобы собрать мобильное приложения для платформы iOS, необходимо на каждом шаге заменить платформу `android` на `ios`.

## Сборка мобильного приложения 

1.В командной строке переходим в папку с приложением.

![](/images/pages/guides/mobile-app/jump-mobile-app.png)

2.Для того чтобы добавить платформу в приложение Cordova, необходимо в командной строке выполнить команду `cordova platform add имя_платформы`. С полным списоком поддерживаемых платформ можно ознакомится [здесь](https://cordova.apache.org/docs/en/latest/guide/support/index.html)

![](/images/pages/guides/mobile-app/add-new-platforms.png)

{% include note.html content="Перед первой сборкой приложения желательно произвести предварительную проверку платформ на соотвествие всем требования для построения приложений на добавленных платформах. Предварительная проверка делается при помощи команды `cordova requirements`" %}

3.Для сборки мобильных приложений, для всех добавленных платформ, в приложение Cordova, в командной строке выполним команду `cordova build`.

![](/images/pages/guides/mobile-app/mobile-app-build1.png)
![](/images/pages/guides/mobile-app/mobile-app-build2.png)

{% include note.html content="Для сборки мобильного приложения под конкретную платформу, используйте команду `cordova build имя_платформы` " %}

В результате выполения данного шага будут созданы установочные файлы мобильных приложений. Далее будет рассмотрен пройцесс запуска мобильных приложений на устройстве.

## Вы можете

* [Перейти на следующий шаг ->](gma_launch-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gma_setting_ember-mobile-app.html)
