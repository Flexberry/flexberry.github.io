---
title: Список поддерживаемых браузеров
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_browsers.html
lang: ru
---

## Браузеры

На данный момент технология [Flexberry ASP.NET](fa_flexberry-asp-net.html) поддерживает следующие браузеры:

* Internet Explorer 8 и старше (за исключением Object.Create(), т.к. он появился только в IE9).
* Chrome версии 20 и старше
* Opera версии 15 и старше
* Mozilla Firefox версии 20 и старше
* Safari OS X

## Мобильные устройства

* GalaxyTab2 7.0 под Android 4.1.2: chrome, firefox, opera, dolphin, стандартный предустановленный браузер.
* Nokia lumia под WinPhone8: в IE и UC Browser.
* IPhone5: Safari.
* Nokia lumia под WinPhone7: IE9.

## Мобильные браузеры

* Safari для iOS 3–7+.
* Android browser 2.2+, 3.1+, 4+.
* Chrome для Android 4+ и iOS.
* Firefox для Android.
* Win8 IE10/11.
* Иные WebKit browsers(webOS, Blackberry 7+).

## Выводы и рекомендации

Приложения на базе [Flexberry ASP.NET](fa_flexberry-asp-net.html) работают практически на всех платформах, но не на всех браузерах.
Для того чтобы работало нормально нужно поддержать обработку касаний.

Какие плюсы у этого:

* функционал такой же как для десктопных браузеров.
* инструментарий для разработки тот же: asp.net,  javaScript, leaflet.

Минусы:

* есть вероятность, что не везде будет работать, но покрытие устройств большое.
* есть вероятность, что будет медленно работать (в т.ч. за счет передачи большого объема данных – требуется оптимизация на клиенте).
* требуется подключение к сети + приличный трафик (см предыдущее).
