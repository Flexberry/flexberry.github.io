---
title: Запуск мобильного приложения 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market
sidebar: guide-mobile-app_sidebar
toc: false
permalink: ru/gma_launch-mobile-app.html
lang: ru
---

## Описание

На данном шаге описан процесс запуска мобильных приложений Android и iOS на различных устройствах, для тестирования работоспособности разработанных приложений.

## Запуск мобильного приложения Android

Запустить созданое приложение для мобильных устройств в Apache Cordova существует три способа:

### На эмуляторе устройства

Для запуска приложение на эмуляторе устройства в командной строке выполните команду `cordova emulate android`, при выполнение данной команды происходит сборка и запуск мобильного приложения.

![](/images/pages/guides/mobile-app/cordova-emulate-android.png)
![](/images/pages/guides/mobile-app/cordova-emulate-android1.png)

{% include note.html content="Если после запуска эмулятора, не произошел запуск приложения, тогда в командной строке выполните команду cordova run android." %}

![](/images/pages/guides/mobile-app/cordova-run-mobile-app.png)

### На подключеном к компьютеру устройстве

Для запуска мобильного приложения на телефоне в командной строке выполните команду `cordova run android`.

![](/images/pages/guides/mobile-app/cordova-run-mobile-app2.png)

{% include note.html content="Если Cordova не обнаружить подключенный телефон к компьютеру, запуск приложения будет произведен на эмуляторе устройства." %}

### Через приложение Android Studio 

Открывем и запускам приложения через [Android Studio](https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#opening-a-project-in-android-studio).

## Запуск мобильного приложения iOS

Чтобы просмотреть мобильное приложение для iOS, необходимо открыть файл рабочей области `platforms/ios/Flexberry Ember Demo.xcodeproj` в **Xcode** или в командной строки выполнить команду `$ open ./platforms/ios/Flexberry\ Ember\ Demo.xcodeproj/`.

Убедитесь, что `Flexberry Ember Demo` проект выбран на левой панели (1).

![](/images/pages/guides/mobile-app/open-xcode.png)

Запустить созданое мобильное приложение можно двумя способами:

### На эмуляторе устройства

1.Выберите нужное устройство из меню **Scheme** панели инструментов (например: `iPhone 6S Simulator`), как указано на рисунке выше под цифром (2).

2.Нажмите кнопку «Выполнить» (3) на той же панели инструментов слева от **Scheme**. При нажатие на кнопку создается, развертывается и запускается приложение в симуляторе. Для отображения приложения открывается отдельное приложение-симулятор.

![](/images/pages/guides/mobile-app/open-mobeli-app.png)

{% include note.html content="За один раз можно запустить только один симулятор, поэтому, если хотите протестировать приложение в другом симуляторе, нужно выйти из симулятора и запустить другой симулятор в Xcode." %}

### На подключеном к компьютеру устройстве

Перед развертыванием необходимо выполнить следующие действия:

1.Создайте профиль `Provisioning Profile` в [iOS Provisioning Portal](https://developer.apple.com/ios/manage/overview/index.action).

2.Убедитесь, что для параметра `Signing Identity` в разделе `Code Signing` в настройках сборки задано ваше имя профиля.

![](/images/pages/guides/mobile-app/signing-identity.png)

Для развертывания на устройстве:

1.Используйте `USB-кабель` для подключения устройства к компьютеру `Mac`.

2.Выберите название проекта в раскрывающемся списке **Scheme** окна `Xcode` .

3.Выберите устройство в раскрывающемся списке `Device`. Если устройство подключено через USB, но не отображается, нажмите кнопку `Organizer`, чтобы устранить любые ошибки.

4.Нажмите кнопку `Run`, чтобы создать, развернуть и запустить приложение на устройстве.

![](/images/pages/guides/mobile-app/iphone-5s.png)

В результате выполения данного шага был описан процесс запуска мобильных приложений. Далее будет представлены статьи о том как опубликовать созданное приложение в AppStore или Google Play.

## Вы можете

* [Перейти на следующий шаг ->](gma_publish-mobile-app.html)
* [<- Вернутся на предыдущий шаг](gma_build-mobile-app.html)
